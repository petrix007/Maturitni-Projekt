package Projekt.sbirka.Entity;

import javax.persistence.*;

@Entity
@Table
public class Pics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int model_pic;
    @Column
    private String obr;
    @Column
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel_pic() {
        return model_pic;
    }

    public void setModel_pic(int model_pic) {
        this.model_pic = model_pic;
    }


    public String getObr() {
        return obr;
    }

    public void setObr(String obr) {
        this.obr = obr;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }


}
