package Projekt.sbirka.Entity;

import javax.persistence.*;

@Entity
@Table
public class Modely {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int sbirka_id;
    @Column
    private int znacka_id;
    @Column
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getSbirka_id() {
        return sbirka_id;
    }

    public void setSbirka_id(int sbirka_id) {
        this.sbirka_id = sbirka_id;
    }

    public int getZnacka_id() {
        return znacka_id;
    }

    public void setZnacka_id(int znacka_id) {
        this.znacka_id = znacka_id;
    }
    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }


}
