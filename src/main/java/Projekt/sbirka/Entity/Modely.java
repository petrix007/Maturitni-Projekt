package Projekt.sbirka.Entity;

import javax.persistence.*;

@Entity
@Table
public class Modely {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int znacka_id;
    @Column
    private int model;
    @Column
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZnacka_id() {
        return znacka_id;
    }

    public void setZnacka_id(int znacka_id) {
        this.znacka_id = znacka_id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }


}
