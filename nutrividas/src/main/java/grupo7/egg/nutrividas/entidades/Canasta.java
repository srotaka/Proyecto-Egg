package grupo7.egg.Nutrividas.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Canasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer cantidadDePersonas;

    //Falta la lista de elementos con los productos, getters y setters y constructores
}
