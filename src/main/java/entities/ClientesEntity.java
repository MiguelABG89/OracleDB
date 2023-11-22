package entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "CLIENTES", schema = "ROOT", catalog = "")
public class ClientesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;
    @Basic
    @Column(name = "NIF", nullable = false, length = 10)
    private String nif;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 14)
    private String nombre;
    @Basic
    @Column(name = "LOCALIDAD", nullable = true, length = 14)
    private String localidad;
    @OneToMany(mappedBy = "clientesByIdCliente")
    private Collection<VentaprodEntity> ventaprodsById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesEntity that = (ClientesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (localidad != null ? !localidad.equals(that.localidad) : that.localidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (localidad != null ? localidad.hashCode() : 0);
        return result;
    }

    public Collection<VentaprodEntity> getVentaprodsById() {
        return ventaprodsById;
    }

    public void setVentaprodsById(Collection<VentaprodEntity> ventaprodsById) {
        this.ventaprodsById = ventaprodsById;
    }
}
