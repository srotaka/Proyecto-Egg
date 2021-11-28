package grupo7.egg.Nutrividas.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private Integer cantidadDePersonas;
    private Integer telefono;

    @OneToMany(mappedBy = "comedor")
    private List<Persona> personas;

    @OneToMany(mappedBy = "comedor")
    private List<Canasta> canastas;

    private String biografia;
    private Boolean alta;

    public Comedor() {
    }

    public Comedor(Long id, String nombre, String direccion, String localidad, String provincia, Integer cantidadDePersonas, Integer telefono, List<Persona> personas, List<Canasta> canastas, String biografia, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.cantidadDePersonas = cantidadDePersonas;
        this.telefono = telefono;
        this.personas = personas;
        this.canastas = canastas;
        this.biografia = biografia;
        this.alta = alta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getCantidadDePersonas() {
        return cantidadDePersonas;
    }

    public void setCantidadDePersonas(Integer cantidadDePersonas) {
        this.cantidadDePersonas = cantidadDePersonas;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Canasta> getCanastas() {
        return canastas;
    }

    public void setCanastas(List<Canasta> canastas) {
        this.canastas = canastas;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
}
