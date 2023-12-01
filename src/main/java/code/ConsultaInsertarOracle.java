package code;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import libs.Leer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ConsultaInsertarOracle {
    public static void InsertarVenta(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aqui comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em = emf.createEntityManager();

        LocalDate Fecha= LocalDate.now() ;

        try {
            Boolean clienteExistente = false;
            Boolean productoExistente = false;

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            ClientesEntity cliente = null;
            ProductosEntity producto = null;
            String NIFCliente;
            short CodProducto;

            do {
                NIFCliente = Leer.pedirCadena("Introduce el NIF del Cliente ");
                String jpgl = "from ClientesEntity ";
                List<ClientesEntity> ListClientes = em.createQuery(jpgl, ClientesEntity.class).getResultList();

                for (ClientesEntity c : ListClientes) {
                    if (c.getNif().equals(NIFCliente)) {
                        clienteExistente=true;
                        cliente=c;
                    }
                }
                if (clienteExistente==false){
                    System.out.println("Cliente no encontrado, Introduce un NIF que Exista");
                }

            } while (clienteExistente == false);

            do {
                CodProducto = (short) Leer.pedirEntero("Introduce el CODIGO del Producto");
                String jpgl2 = "from ProductosEntity";

                List<ProductosEntity> ListProductos = em.createQuery(jpgl2, ProductosEntity.class).getResultList();
                for (ProductosEntity p : ListProductos) {
                    if (p.getCodProducto().equals(CodProducto)) {
                        productoExistente=true;
                        producto=p;
                    }
                }
                if (productoExistente==false){
                    System.out.println("Producto no encontrado, Introduce un Codigo de producto que Exista");
                }
            } while (productoExistente == false);


            int unidades = Leer.pedirEntero("Introduce la cantidad de unidades");

            //cada consulta añade informacion a nuestro contexto de persistencia

            VentaprodEntity venta = new VentaprodEntity();

            venta.setIdCliente(cliente.getId());
            venta.setIdProducto(producto.getId());
            venta.setUnidades((short) unidades);
            venta.setFecha(Date.valueOf(Fecha));

            em.persist(venta);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //Aseguramos que la conexion se cierra, y el contexto de persistencia termina
            em.close();
        }
    }
}
