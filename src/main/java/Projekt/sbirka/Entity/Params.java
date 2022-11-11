package Projekt.sbirka.Entity;

import javax.persistence.*;

@Entity
@Table
public class Params {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int model_param;
    @Column
    private String nazev;
    @Column
    private String hodnota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel_param() {
        return model_param;
    }

    public void setModel_param(int model_param) {
        this.model_param = model_param;
    }


    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getHodnota() {
        return hodnota;
    }

    public void setHodnota(String hodnota) {
        this.hodnota = hodnota;
    }
}
