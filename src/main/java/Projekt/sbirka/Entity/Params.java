package Projekt.sbirka.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table
public class Params {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "model_param")
    private Modely model_param;
    @Column
    private String popis;
    @Column
    private String hodnota;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @JsonProperty("model_param")
    public Modely getModel_param() {
        return model_param;
    }

    public void setModel_param(Modely model_param) {
        this.model_param = model_param;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }


    public String getHodnota() {
        return hodnota;
    }

    public void setHodnota(String hodnota) {
        this.hodnota = hodnota;
    }

    public String GetNeco(){
        return model_param.getSbirka_id().getPopis();
    }
}