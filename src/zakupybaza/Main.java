/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zakupybaza;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author student
 */
public class Main {

    
    public static void main(String[] args) 
    {
        EntityManager em = Utils.getEntityManager();
        loadRecords(); 
        
        em.getTransaction().begin();                                      
        
        Query sklepy = em.createNamedQuery("Sklep.findAll");
        
        List<Sklep> sklepyList = (List<Sklep>) sklepy.getResultList();
        for(Sklep s : sklepyList)
        {
            s.getPozycjaSet().size();
            Set<Pozycja> temp = s.getPozycjaSet();
            if(!temp.isEmpty())
            {
                for(Pozycja p:temp)
                {
                    System.out.println("Za "+p.getNazwa()+" w sklepie "+s.getNazwa()+" nalezy wydac "+p.getCena()*p.getIlosc()+" zl poniewaz sztuk="+p.getIlosc()+" a cena jednostkowa="+p.getCena());
                }
            }
            else
            {
                System.out.println("Sklep "+s.getNazwa()+" nie ma pozycji zakupowych");
            }
        }
        
        em.getTransaction().commit();
        em.close();
        
    }
    public static void loadRecords()
    {
        EntityManager em = Utils.getEntityManager();
        em.getTransaction().begin();
        
        
        Sklep zabka = new Sklep();
        zabka.setNazwa("Zabka");
        em.persist(zabka);
        
        Sklep auchan = new Sklep();
        auchan.setNazwa("Auchan");
        em.persist(auchan);
        
        Sklep aldi = new Sklep();
        aldi.setNazwa("Aldi");
        em.persist(aldi);
        
        Sklep biedronka = new Sklep();
        biedronka.setNazwa("Biedronka");
        em.persist(biedronka);
        
        Sklep carrefour = new Sklep();
        carrefour.setNazwa("Carrefour");
        em.persist(carrefour);

        Pozycja p1 = new Pozycja();
        p1.setNazwa("Gwozdzie");
        p1.setIlosc(10);
        p1.setCena(25);
        p1.setSklep(aldi);

        
        Pozycja p2 = new Pozycja();
        p2.setNazwa("Deska");
        p2.setIlosc(15);
        p2.setCena(35);
        p2.setSklep(aldi);
        
        Pozycja p3 = new Pozycja();
        p3.setNazwa("Blacha");
        p3.setIlosc(15);
        p3.setCena(150);
        p3.setSklep(auchan);

        
        Pozycja p4 = new Pozycja();
        p4.setNazwa("Słupek");
        p4.setIlosc(350);
        p4.setCena(185); 
        p4.setSklep(auchan);
        
        Pozycja p5 = new Pozycja();
        p5.setNazwa("Folia");
        p5.setIlosc(3);
        p5.setCena(5); 
        p5.setSklep(carrefour);
        
        Pozycja p6 = new Pozycja();
        p6.setNazwa("Tasma");
        p6.setIlosc(6);
        p6.setCena(10); 
        p6.setSklep(carrefour);
        
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.persist(p6);
        
        
        em.getTransaction().commit();
        em.close();
    }
}
