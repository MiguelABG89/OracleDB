package code;

import entities.ClientesEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class ConsultasOracle {
    public static void listarClientes() {
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aqui comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em = emf.createEntityManager();
        int cantidadProductos=0;
        try {
            //cada consulta añade informacion a nuestro contexto de persistencia
            String jpgl = "from ClientesEntity";
            String jpgl2 = "from VentaprodEntity where idCliente= ? 1";
            List<ClientesEntity> es = em.createQuery(jpgl, ClientesEntity.class).getResultList();
            for (ClientesEntity c:es) {
                Query q = em.createQuery(jpgl2).setParameter(1,c.getId());
                List<VentaprodEntity> listaCompra = q.getResultList();
                for (VentaprodEntity p: listaCompra) {
                    cantidadProductos+= p.getUnidades();
                }
                System.out.println("---------------*--------------");
                System.out.println(c.getNombre());
                System.out.println(cantidadProductos);
                System.out.println("---------------*--------------");
                System.out.println("\n");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //Aseguramos que la conexion se cierra, y el contexto de persistencia termina
            em.close();
        }
    }
    public static void listarProductos() {
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aqui comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em = emf.createEntityManager();
        try {
            //cada consulta añade informacion a nuestro contexto de persistencia
            String jpgl = "from ClientesEntity";

            List<ClientesEntity> es = em.createQuery(jpgl, ClientesEntity.class).getResultList();

            for (ClientesEntity c:es) {
                System.out.println(c.getNombre());
                System.out.println(c.getLocalidad());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //Aseguramos que la conexion se cierra, y el contexto de persistencia termina
            em.close();
        }
    }
}
