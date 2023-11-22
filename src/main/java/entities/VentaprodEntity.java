package entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "VENTAPROD", schema = "ROOT", catalog = "")
public class VentaprodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;
    @Basic
    @Column(name = "ID_CLIENTE", nullable = false, precision = 0)
    private Long idCliente;
    @Basic
    @Column(name = "ID_PRODUCTO", nullable = false, precision = 0)
    private Long idProducto;
    @Basic
    @Column(name = "UNIDADES", nullable = false, precision = 0)
    private Short unidades;
    @Basic
    @Column(name = "FECHA", nullable = true)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", nullable = false,insertable = false,updatable = false)
    private ClientesEntity clientesByIdCliente;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID", nullable = false,insertable = false,updatable = false)
    private ProductosEntity productosByIdProducto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Short getUnidades() {
        return unidades;
    }

    public void setUnidades(Short unidades) {
        this.unidades = unidades;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentaprodEntity that = (VentaprodEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;
        if (idProducto != null ? !idProducto.equals(that.idProducto) : that.idProducto != null) return false;
        if (unidades != null ? !unidades.equals(that.unidades) : that.unidades != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idCliente != null ? idCliente.hashCode() : 0);
        result = 31 * result + (idProducto != null ? idProducto.hashCode() : 0);
        result = 31 * result + (unidades != null ? unidades.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public ClientesEntity getClientesByIdCliente() {
        return clientesByIdCliente;
    }

    public void setClientesByIdCliente(ClientesEntity clientesByIdCliente) {
        this.clientesByIdCliente = clientesByIdCliente;
    }

    public ProductosEntity getProductosByIdProducto() {
        return productosByIdProducto;
    }

    public void setProductosByIdProducto(ProductosEntity productosByIdProducto) {
        this.productosByIdProducto = productosByIdProducto;
    }
}
