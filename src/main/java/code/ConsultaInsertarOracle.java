package code;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import libs.Leer;

import java.util.Date;
import java.util.List;

public class ConsultaInsertarOracle {
    public static void InsertarVenta(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aqui comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em = emf.createEntityManager();

        Date Fecha ;
        try {
            ClientesEntity clienteExistente = null;
            ProductosEntity productoExistente  = null;

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            do {
                int idCliente= Leer.pedirEntero("Introduce el NIF del Cliente ");
                String jpgl = "from ClientesEntity where nif = ? 1";
                Query query = em.createQuery(jpgl).setParameter(1,idCliente);
                List<ClientesEntity> clientes = query.getResultList();
                if (clientes.isEmpty()){
                    System.out.println("Cliente no encontrado, Introduce un NIF que Exista");
                }else {
                    clienteExistente=clientes.get(0);
                }
            }while (clienteExistente==null);



            int idProducto=Leer.pedirEntero("Introduce el CODIGO del Producto");
            String jpgl2 = "from ProductosEntity where codProducto= ? 1";
            Query query2 = em.createQuery(jpgl2).setParameter(1,idProducto);
            List<ProductosEntity> productos = query2.getResultList();


            int unidades=Leer.pedirEntero("Introduce la cantidad de unidades");


            //cada consulta añade informacion a nuestro contexto de persistencia






            VentaprodEntity venta = new VentaprodEntity();

            em.persist();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //Aseguramos que la conexion se cierra, y el contexto de persistencia termina
            em.close();
        }
    }
}
