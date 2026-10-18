☕ SISCAFÉ - Sistema de Gestão de Cafeteria
Sistema de gestão de cafeteria desenvolvido em JavaFX com padrão MVC, DAO, Interfaces e Herança.
Projeto refatorado para arquitetura limpa, com filtro de dados por usuário logado.

Java
JavaFX
MySQL
MVC

📋 Índice
Sobre o Projeto
Funcionalidades
Arquitetura MVC
Tecnologias
Estrutura de Pastas
Modelo de Dados
Instalação
Configuração do Banco
Como Usar
Padrões de Projeto
Autores
📖 Sobre o Projeto
O SISCAFÉ é um sistema de ponto de venda e gestão de estoque para cafeterias. O sistema foi inicialmente desenvolvido com toda lógica nos Controllers e foi refatorado para o padrão MVC profissional exigido em trabalhos acadêmicos.

Problema resolvido nesta versão:

✅ Cada funcionário só vê os produtos e vendas que ele mesmo criou (WHERE username = ?)
✅ Admin vê tudo
✅ SQL isolado em DAOs, Controllers magros
✅ Herança, Interfaces e Polimorfismo implementados
✨ Funcionalidades
👨‍💼 Admin
Dashboard com gráficos de receita e clientes
Gestão completa de produtos (CRUD)
Visualização de todas as vendas
Relatórios JasperReports
👨‍🍳 Employee
Cardápio com produtos disponíveis
Carrinho de compras com Spinner de quantidade
Controle automático de estoque
Pagamento com cálculo de troco
Geração de recibo
Histórico filtrado: só vê suas próprias vendas
🔐 Autenticação
Login com role (admin / Employee)
Recuperação de senha por pergunta secreta
Sessão com data.username e data.role
🏗️ Arquitetura MVC
Antes (God Controller)
Controller (1000 linhas)
├── SQL direto
├── Regra de negócio
└── Manipulação de tela
Depois (MVC Limpo)
Model/          → Entidades + Herança
dao/interfaces/ → Contratos (Interface)
dao/impl/       → Acesso ao Banco (DAO)
Controller/     → Só chama DAO, zero SQL
Diagrama de Herança
BaseEntity (abstract)
├── Product
├── Receipt
└── CustomerItem
Person (abstract)
└── Employee
IGenericDAO<T> (interface)
├── IProductDAO
├── IReceiptDAO
├── ICustomerDAO
└── IEmployeeDAO
🛠️ Tecnologias
Java 17+
JavaFX 17 - Interface gráfica
MySQL 8.0 - Banco de dados
JasperReports 6.x - Relatórios
SceneBuilder - Design FXML
NetBeans / IntelliJ
📁 Estrutura de Pastas
src/
├── Controller/
│   ├── MainFormController.java        # Admin - Dashboard + Inventory
│   ├── EmployeeFormController.java    # Employee - Menu + Vendas
│   ├── CardProductController.java     # Card de produto (GridPane)
│   └── FXMLDocumentController.java   # Login / Registro
│
├── Model/
│   ├── BaseEntity.java                # Classe abstrata pai (id, date)
│   ├── Person.java                    # Classe abstrata pai (username, role)
│   ├── Employee.java                  # extends Person
│   ├── Product.java                   # extends BaseEntity
│   ├── Receipt.java                   # extends BaseEntity
│   ├── CustomerItem.java              # extends BaseEntity
│   ├── productData.java               # Legado - compatibilidade FXML
│   ├── customersData.java             # Legado - compatibilidade FXML
│   └── data.java                      # Sessão estática
│
├── dao/
│   ├── interfaces/
│   │   ├── IGenericDAO.java           # Interface genérica com findByUsername()
│   │   ├── IProductDAO.java
│   │   ├── IReceiptDAO.java
│   │   ├── ICustomerDAO.java
│   │   └── IEmployeeDAO.java
│   └── impl/
│       ├── ProductDAOImpl.java        # SQL de produto
│       ├── ReceiptDAOImpl.java        # SQL de recibo
│       ├── CustomerDAOImpl.java       # SQL de carrinho
│       └── EmployeeDAOImpl.java       # SQL de login
│
├── daoCafeteria/
│   └── database.java                  # Conexão Singleton
│
└── View/
    ├── FXMLDocument.fxml
    ├── MainForm.fxml
    ├── Employee.fxml
    └── CardProduct.fxml
🗄️ Modelo de Dados
SQL para criar o banco com filtro por usuário
CREATE DATABASE cafeteria;
USE cafeteria;
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    question VARCHAR(200),
    answer VARCHAR(200),
    date DATE,
    role VARCHAR(20) -- admin / Employee
);
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prod_id VARCHAR(50) UNIQUE,
    prod_name VARCHAR(100),
    type VARCHAR(50),
    stock INT,
    price DOUBLE,
    status VARCHAR(20),
    image VARCHAR(500),
    date DATE,
    username VARCHAR(100), -- QUEM CRIOU O PRODUTO
    FOREIGN KEY (username) REFERENCES employee(username)
);
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    prod_id VARCHAR(50),
    prod_name VARCHAR(100),
    type VARCHAR(50),
    quantity INT,
    price DOUBLE,
    date DATE,
    image VARCHAR(500),
    em_username VARCHAR(100) -- QUEM VENDEU
);
CREATE TABLE receipt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    total DOUBLE,
    date DATE,
    em_username VARCHAR(100) -- DONO DA VENDA
);
🚀 Instalação
1. Clone o repositório
git clone https://github.com/seu-usuario/SISCAFE.git
cd SISCAFE
2. Configure o banco
Crie o banco cafeteria no MySQL
Execute o SQL acima
Ajuste usuário/senha em daoCafeteria/database.java:
return DriverManager.getConnection("jdbc:mysql://localhost/cafeteria","root","");
3. Adicione as bibliotecas
JavaFX SDK 17
MySQL Connector/J 8.0
JasperReports 6.20.0 + dependências
4. Rode o projeto
Abra no NetBeans
Clean and Build (Shift + F11)
Run FXMLDocument.java
💻 Como Usar
Login: Use admin ou cadastre um novo Employee
Inventário: Admin cadastra produtos (com imagem)
Menu: Employee adiciona produtos ao carrinho via Cards
Pagamento: Informe valor pago → calcula troco → grava em receipt
Clientes: Lista filtrada WHERE em_username = data.username
Filtro por usuário implementado:
// ProductDAOImpl.java
String sql = "SELECT * FROM product WHERE username = ?";
ps.setString(1, data.username);
// ReceiptDAOImpl.java
String sql = "SELECT * FROM receipt WHERE em_username = ?";
ps.setString(1, data.username);
🎯 Padrões de Projeto
Padrão	Onde está	Exemplo
MVC	Todo projeto	Model não conhece View, Controller chama DAO
DAO	dao/impl/	ProductDAOImpl isola todo SQL
Interface	dao/interfaces/	IGenericDAO<T> define contrato
Herança	Model/	Product extends BaseEntity
Polimorfismo	Controllers	IProductDAO dao = new ProductDAOImpl()
📝 Documentação
Todas as classes estão documentadas com Javadoc:

/**
 * @author Elga - SISCAFÉ MVC
 * Classe abstrata base para todas entidades com ID e Data
 * Demonstra HERANÇA - Pai de Product e Receipt
 */
public abstract class BaseEntity { ... }
👩‍💻 Autores
Elga - Desenvolvimento inicial e refatoração MVC
Projeto acadêmico - Sistema de Gestão de Cafeteria
📄 Licença
Este projeto é para fins acadêmicos. Livre para estudo e modificação.

🔗 Links Úteis
JavaFX Documentation
MySQL Connector
JasperReports
⭐ Se este projeto te ajudou, deixe uma estrela no GitHub!
