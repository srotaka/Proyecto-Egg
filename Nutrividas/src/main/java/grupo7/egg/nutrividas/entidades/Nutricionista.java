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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="nutricionistas")
@SQLDelete(sql = "UPDATE Nutricionistas SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{}")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String nombre;
    @NotBlank(message = "El campo 'apellido' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String apellido;
    @NotNull(message = "El campo 'documento' es obligatorio")
    @Pattern(regexp="\\d{8}",message = "El campo 'documento' debe contener 8 caracteres")
    private Long documento;
    @NotNull(message = "El campo 'matricula' es obligatorio")
    @Pattern(regexp="\\d{12}",message = "El campo 'matrícula' debe contener 12 caracteres")
    private Long matricula;
    @NotBlank(message = "El campo 'fecha de nacimiento' es obligatorio")
    private LocalDate fechaNacimiento;
    @NotBlank(message = "El campo 'telefono' es obligatorio")
    @Pattern(regexp="\\d{8}",message = "El campo 'teléfono' debe contener 8 caracteres")
    private Long telefono;
    @NotBlank(message = "El campo 'mail' es obligatorio")
    @Email(message = "El formato de email debe ser válido")
    private String mail;

    @NotEmpty(message = "Debe seleccionar al menos un comedor")
    @OneToMany(mappedBy = "nutricionista")
    private List<Comedor> comedores;

    @OneToOne
    private Foto foto;

    @NotEmpty(message = "Nombre de usuario y contraseña obligatorios")
    @OneToOne
    private Credencial credencial;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
