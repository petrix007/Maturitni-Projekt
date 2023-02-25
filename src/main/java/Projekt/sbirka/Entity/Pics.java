package Projekt.sbirka.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table
public class Pics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "model_pic")
    private Modely model_pic;
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

    @JsonProperty("model_pic")
    public Modely getModel_pic() {
        return model_pic;
    }

    public void setModel_pic(Modely model_pic) {
        this.model_pic = model_pic;
    }

    public int getIdOfPicModel(){
        return model_pic.getId();
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