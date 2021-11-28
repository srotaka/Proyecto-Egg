package grupo7.egg.Nutrividas.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private Integer altura;
    private Double peso;
    private List<String> enfermedades;
    private Double imc;
    private Boolean alta;

    public Persona() {
    }

    public Persona(Long dni, String nombre, String apellido, LocalDate fechaNacimiento, Integer edad, Integer altura, Double peso, List<String> enfermedades, Double imc, Boolean alta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.enfermedades = enfermedades;
        this.imc = imc;
        this.alta = alta;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public List<String> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(List<String> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
}
