/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zakupybaza;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@Entity
@Table(name = "pozycja", catalog = "test", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pozycja.findAll", query = "SELECT p FROM Pozycja p"),
    @NamedQuery(name = "Pozycja.findById", query = "SELECT p FROM Pozycja p WHERE p.id = :id"),
    @NamedQuery(name = "Pozycja.findByNazwa", query = "SELECT p FROM Pozycja p WHERE p.nazwa = :nazwa"),
    @NamedQuery(name = "Pozycja.findByIlosc", query = "SELECT p FROM Pozycja p WHERE p.ilosc = :ilosc"),
    @NamedQuery(name = "Pozycja.findByCena", query = "SELECT p FROM Pozycja p WHERE p.cena = :cena")})
public class Pozycja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nazwa", nullable = false, length = 10)
    private String nazwa;
    @Basic(optional = false)
    @Column(name = "Ilosc", nullable = false)
    private int ilosc;
    @Basic(optional = false)
    @Column(name = "Cena", nullable = false)
    private int cena;
    @JoinColumn(name = "Sklep", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Sklep sklep;

    public Pozycja() {
    }

    public Pozycja(Integer id) {
        this.id = id;
    }

    public Pozycja(Integer id, String nazwa, int ilosc, int cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.cena = cena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Sklep getSklep() {
        return sklep;
    }

    public void setSklep(Sklep sklep) {
        sklep.getPozycjaSet().add(this);
        this.sklep = sklep;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nazwa);
        hash = 97 * hash + this.ilosc;
        hash = 97 * hash + this.cena;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pozycja other = (Pozycja) obj;
        if (this.ilosc != other.ilosc) {
            return false;
        }
        if (this.cena != other.cena) {
            return false;
        }
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        if (!Objects.equals(this.sklep, other.sklep)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pozycja{" + "nazwa=" + nazwa + ", ilosc=" + ilosc + ", cena=" + cena + ", sklep=" + sklep.getNazwa() + '}';
    }

    
}
