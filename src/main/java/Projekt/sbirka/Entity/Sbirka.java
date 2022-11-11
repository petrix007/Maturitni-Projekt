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
    private int user_id;
    @Column
    private Date zalozeno;
    @Column
    private String popis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

}
