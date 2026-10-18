package Model;

import java.sql.Date;

/**
 * Classe abstrata base para todas as entidades com ID e Data. Demonstra HERANÇA
 * - Pai de Product, Receipt e CustomerItem.
 *
 * @author Elga - SISCAFÉ MVC
 */
public abstract class BaseEntity {

    protected int id;
    protected Date date;

    public BaseEntity(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Método abstrato obriga filhos a implementarem
     */
    public abstract String getDisplayName();
}
