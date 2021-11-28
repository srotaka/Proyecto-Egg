package grupo7.egg.nutrividas.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="comedores")
@SQLDelete(sql = "UPDATE comedores SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Comedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unique")
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private Integer cantidadDePersonas;
    private Long telefono;
    private String biografia;

    //Mapped by comedor ---> agregar hacemos la relación bidireccional añadiendo a Persona el atributo comedor
    @OneToMany
    @JoinColumn
    private List<Persona> personas;
    //Mapped by comedor ---> agregar hacemos la relación bidireccional añadiendo a Canasta el atributo comedor
    @OneToMany
    @JoinColumn
    private List<Canasta> canastas;

    private Boolean alta;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
