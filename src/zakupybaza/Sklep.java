/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zakupybaza;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author student
 */
@Entity
@Table(name = "sklep", catalog = "test", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sklep.findAll", query = "SELECT s FROM Sklep s"),
    @NamedQuery(name = "Sklep.findById", query = "SELECT s FROM Sklep s WHERE s.id = :id"),
    @NamedQuery(name = "Sklep.findByNazwa", query = "SELECT s FROM Sklep s WHERE s.nazwa = :nazwa")})
public class Sklep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nazwa", nullable = false)
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sklep", fetch = FetchType.EAGER)
    private Set<Pozycja> pozycjaSet = new HashSet<Pozycja>();

    public Sklep() {
    }

    public Sklep(Integer id) {
        this.id = id;
    }

    public Sklep(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
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

    @XmlTransient
    public Set<Pozycja> getPozycjaSet() {
        return pozycjaSet;
    }

    public void setPozycjaSet(Set<Pozycja> pozycjaSet) {
        this.pozycjaSet = pozycjaSet;
    }
    public void addPozycja(Pozycja p)
    {
        this.getPozycjaSet().add(p);
        p.setSklep(this);
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.nazwa);
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
        final Sklep other = (Sklep) obj;
        if (this.nazwa != other.nazwa) {
            return false;
        }
        if (!Objects.equals(this.pozycjaSet, other.pozycjaSet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sklep{" + "nazwa=" + nazwa + ", pozycje zakupowe=\n" + pozycjaSet + '}';
    }


    
}
