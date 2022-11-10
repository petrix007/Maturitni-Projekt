package Projekt.sbirka.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Sbirka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date zalozeno;
    @Column
    private String popis;
    @Column
    private int model_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getZalozeno() {
        return zalozeno;
    }

    public void setZalozeno(Date zalozeno) {
        this.zalozeno = zalozeno;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }


}
