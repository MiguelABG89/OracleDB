package code;

import entities.ClientesEntity;
import entities.ProductosEntity;
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

        try {
            //cada consulta añade informacion a nuestro contexto de persistencia
            String jpgl = "from ClientesEntity";
            String jpgl2 = "from VentaprodEntity where idCliente= ? 1";
            List<ClientesEntity> es = em.createQuery(jpgl, ClientesEntity.class).getResultList();
            for (ClientesEntity c:es) {
                int cantidadProductos=0;
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
            String jpgl = "from ProductosEntity ";
            String jpgl2 = "from VentaprodEntity where idProducto= ? 1";
            List<ProductosEntity> es = em.createQuery(jpgl, ProductosEntity.class).getResultList();

            for (ProductosEntity p:es) {
                int cantidadProductos=0;
                Query q = em.createQuery(jpgl2).setParameter(1,p.getId());
                List<VentaprodEntity> listaArticulos = q.getResultList();
                for (VentaprodEntity v: listaArticulos) {
                    cantidadProductos+= v.getUnidades();
                }
                System.out.println("---------------*--------------");
                System.out.println(p.getDescripcion());
                System.out.println(cantidadProductos);
                System.out.println("---------------*--------------");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //Aseguramos que la conexion se cierra, y el contexto de persistencia termina
            em.close();
        }
    }
}
