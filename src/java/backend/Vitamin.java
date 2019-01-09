/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zsobe31
 */
@Entity
@Table(name = "vitamin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vitamin.findAll", query = "SELECT v FROM Vitamin v")
    , @NamedQuery(name = "Vitamin.findById", query = "SELECT v FROM Vitamin v WHERE v.id = :id")
    , @NamedQuery(name = "Vitamin.findByNev", query = "SELECT v FROM Vitamin v WHERE v.nev = :nev")})
public class Vitamin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @Lob
    @Column(name = "leiras")
    private String leiras;

    public Vitamin() {
    }

    public Vitamin(Integer id) {
        this.id = id;
    }

    public Vitamin(Integer id, String nev, String leiras) {
        this.id = id;
        this.nev = nev;
        this.leiras = leiras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }
    
    // az adatok listázása
    public static List<Vitamin> getAllVitamin(EntityManager em){
        List<Vitamin> elemek = new ArrayList();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllVitamin");
        List<Object[]> lista = spq.getResultList();
        for(Object[] elem : lista){
            Vitamin e = new Vitamin();
            e = em.find(Vitamin.class, elem[0]);
            elemek.add(e);
        }
        return elemek;
    }
    
    public static Vitamin findVitaminById(EntityManager em, int id){
        List<Vitamin> elemek = Vitamin.getAllVitamin(em);
        Vitamin e = em.find(Vitamin.class, id);
        return e;
    }
    
    public static Vitamin findVitaminByNev(EntityManager em, String nev){
        List<Vitamin> elemek = Vitamin.getAllVitamin(em);
        Vitamin e = em.find(Vitamin.class, nev);
        return e;
    }
    
    // hozzáad
        public static void addNewVitamin(EntityManager em, String nev, String leiras){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewVitamin");
        em.getTransaction().begin();
        spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("leirasIN", String.class, ParameterMode.IN);
        spq.setParameter("nevIN", nev);    
        spq.setParameter("leirasIN", leiras);    
        spq.execute();
        em.getTransaction().commit();
    }
        
    // töröl
        public static void deleteVitamin(EntityManager em, int id){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteVitamin");
        em.getTransaction().begin();
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);    
        spq.execute();
        em.getTransaction().commit();
    }
        
    // módosít
    public static void updateVitamin(EntityManager em, String nev, String leiras, int id){
        Vitamin e = Vitamin.findVitaminById(em, id);
        em.getTransaction().begin();
        e.setNev(nev);
        e.setLeiras(leiras);
        em.getTransaction().commit();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vitamin)) {
            return false;
        }
        Vitamin other = (Vitamin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Vitamin[ id=" + id + " ]";
    }
    
}
