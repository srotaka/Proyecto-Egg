package grupo7.egg.nutrividas.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotBlank(message = "El campo nombre es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    @Column(name = "nombre")
    private String nombre;
    @OneToOne
    private Direccion direccion;

    @NotNull(message = "La cantidad de peronas es obligatoria")
    @Positive(message = "La cantidad de peronas no puede ser menor a 1")
    private Integer cantidadDePersonas;

    @NotNull(message = "El campo 'telefono' es obligatorio")
    @Min(value = 10000000,message = "El campo 'teléfono' debe contener 8 dígitos")
    @Max(value = 99999999,message = "El campo 'teléfono' debe contener 8 dígitos")
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

    @OneToOne
    private Credencial credencial;

    private Boolean alta;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
