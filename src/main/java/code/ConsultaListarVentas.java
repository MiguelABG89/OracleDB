package code;

import entities.ClientesEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import libs.Leer;

import java.util.List;

public class ConsultaListarVentas {
    public static void ListadoVentas(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aqui comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em = emf.createEntityManager();

        List<VentaprodEntity> listaProductos = null;
        String NombreCliente;
        boolean ClienteValido=false;
        try {

            String jpgl = "from ClientesEntity ";
            List<ClientesEntity> ListClientes = em.createQuery(jpgl, ClientesEntity.class).getResultList();
            do {
                for (ClientesEntity c : ListClientes){
                    System.out.println("******************************");
                    System.out.println(c.getNombre());

                }
                System.out.println("******************************");

                NombreCliente = Leer.pedirCadena("Introduce el Nombre del Cliente ");
                for (ClientesEntity c:ListClientes) {
                    if (c.getNombre().toLowerCase().contains(NombreCliente.toLowerCase())){
                        ClienteValido=true;
                        Query listarVentas = em.createQuery("from VentaprodEntity where idCliente = ?1");
                        listarVentas.setParameter(1,c.getId() );
                        listaProductos =listarVentas.getResultList();
                    }
                }
                if (ClienteValido==false){
                    System.out.println("Cliente no encontrado, Introduce un NIF que Exista");
                }
            }while (ClienteValido==false);

            // Se consiguen las ventas del cliente a base de su id

            for (VentaprodEntity v : listaProductos) {
                System.out.println("******************************");
                System.out.println(v.getClientesByIdCliente().getNombre());
                System.out.println("Producto comprado: " + v.getProductosByIdProducto().getDescripcion());
                System.out.println("Unidades compradas: " + v.getUnidades());
                System.out.println("Fecha de compra: " + v.getFecha());
                System.out.println("******************************\n");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }
}
