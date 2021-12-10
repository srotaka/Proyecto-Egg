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
@SQLDelete(sql = "UPDATE Comedores SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Comedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @OneToOne
    private Direccion direccion;
    private Integer cantidadDePersonas;
    private Long telefono;
    @OneToOne
    private Biografia biografia;
    @OneToMany(mappedBy = "comedor")
    private List<Persona> personas;
    @OneToMany(mappedBy = "comedor")
    private List<Canasta> canastas;
    @ManyToOne
    private Nutricionista nutricionista;
    @OneToOne
    private Foto foto;
    private Boolean alta;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
