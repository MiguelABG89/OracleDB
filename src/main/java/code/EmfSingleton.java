package code;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmfSingleton {
    private static EmfSingleton emfSInstancia=
            new EmfSingleton();
    static private final String PERSISTENCE_UTIL_NAME="default";
    private EntityManagerFactory emf= null;
    public static EmfSingleton getInstance(){
        return emfSInstancia;
    }
    private EmfSingleton(){
    }
    public EntityManagerFactory getEmf(){
        if(this.emf == null)
            this.emf= Persistence.createEntityManagerFactory(PERSISTENCE_UTIL_NAME);
        return this.emf;
    }
}
