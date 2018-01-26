/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zakupybaza;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author student
 */
public class Utils 
{
    
static EntityManager em;
/**
 *
 * @author Daniel
     *
 */
   
    public static EntityManager getEntityManager() 
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testPU");
        em = entityManagerFactory.createEntityManager();
        return em;
    }
}

