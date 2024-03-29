package Projekt.sbirka.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
@Entity
@Table
public class Modely {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "sbirka_id")
    private Sbirka sbirka_id;
    @ManyToOne
    @JoinColumn(name = "znacka_id")
    private Znacka znacka_id;
    @Column
    private String nazev;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Users getUserByModel(){return sbirka_id.getUsers_id();}
    @JsonProperty("sbirka_id")
    public Sbirka getSbirka_id() {
        return sbirka_id;
    }

    public void setSbirka_id(Sbirka sbirka_id) {
        this.sbirka_id = sbirka_id;
    }

    public Znacka getZnacka_id() {
        return znacka_id;
    }

    public void setZnacka_id(Znacka znacka_id) {
        this.znacka_id = znacka_id;
    }
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }


    public String getPopisSbirka(){
        return sbirka_id.getPopis();
    }
}