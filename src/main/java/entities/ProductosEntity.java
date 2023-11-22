package entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "PRODUCTOS", schema = "ROOT", catalog = "")
public class ProductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;
    @Basic
    @Column(name = "COD_PRODUCTO", nullable = false, precision = 0)
    private Short codProducto;
    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 20)
    private String descripcion;
    @Basic
    @Column(name = "LINEA_PRODUCTO", nullable = true, length = 10)
    private String lineaProducto;
    @Basic
    @Column(name = "PRECIO", nullable = true, precision = 0)
    private Short precio;
    @OneToMany(mappedBy = "productosByIdProducto")
    private Collection<VentaprodEntity> ventaprodsById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Short codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

    public Short getPrecio() {
        return precio;
    }

    public void setPrecio(Short precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductosEntity that = (ProductosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (codProducto != null ? !codProducto.equals(that.codProducto) : that.codProducto != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (lineaProducto != null ? !lineaProducto.equals(that.lineaProducto) : that.lineaProducto != null)
            return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (codProducto != null ? codProducto.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (lineaProducto != null ? lineaProducto.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        return result;
    }

    public Collection<VentaprodEntity> getVentaprodsById() {
        return ventaprodsById;
    }

    public void setVentaprodsById(Collection<VentaprodEntity> ventaprodsById) {
        this.ventaprodsById = ventaprodsById;
    }
}
