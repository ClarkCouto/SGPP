package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class JPAUtil {
    private static EntityManagerFactory emf;
    public static EntityManager getEntityManager() {
            System.out.println("Entrou try getEntityManager!");
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("SGPP-PU");
        }
        System.out.println("Passou try getEntityManager!");
        return emf.createEntityManager();
    }
    public void fechaEntityManager(){
        emf.close();
    }
}