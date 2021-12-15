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

    @NotBlank(message = "El campo nombre es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String nombre;

    @NotBlank(message = "El campo 'apellido' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String apellido;

    @NotNull(message = "El campo 'documento' es obligatorio")
    @Min(value = 10000000,message = "El campo 'dni' debe contener 8 dígitos")
    @Max(value = 99999999,message = "El campo 'dni' debe contener 8 dígitos")
    private Long documento;

    @NotNull(message = "El campo 'matricula' es obligatorio")
    @Min(value = 100000000000L,message = "El campo 'matrícula' debe contener 12 caracteres")
    @Max(value = 999999999999L,message = "El campo 'matrícula' debe contener 12 caracteres")
    private Long matricula;

    @NotBlank(message = "El campo 'fecha de nacimiento' es obligatorio")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El campo 'telefono' es obligatorio")
    @Min(value = 10000000,message = "El campo 'teléfono' debe contener 8 dígitos")
    @Max(value = 99999999,message = "El campo 'teléfono' debe contener 8 dígitos")
    private Long telefono;

    @NotBlank(message = "El campo 'mail' es obligatorio")
    @Email(message = "El formato de email debe ser válido")
    private String mail;

    @NotEmpty(message = "Debe seleccionar al menos un comedor")
    @OneToMany(mappedBy = "nutricionista")
    private List<Comedor> comedores;

    @OneToOne
    private Foto foto;

    @OneToOne
    private Credencial credencial;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
