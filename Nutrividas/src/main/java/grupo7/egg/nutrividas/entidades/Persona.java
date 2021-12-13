package grupo7.egg.nutrividas.entidades;

import grupo7.egg.nutrividas.enums.Sexo;
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

@Entity
@Table(name="personas")
@SQLDelete(sql = "UPDATE Personas SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo 'nombre' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String nombre;
    @NotBlank(message = "El campo 'apellido' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String apellido;
    @NotNull(message = "El campo 'documento' es obligatorio")
    @Min(value = 10000000,message = "El campo 'dni' debe contener 8 dígitos")
    @Max(value = 99999999,message = "El campo 'dni' debe contener 8 dígitos")
    private Long documento;
    private LocalDate fechaNacimiento;
    private Integer edad;
    @Positive(message = "La altura debe ser mayor a 0")
    private Double altura;
    @Positive(message = "La altura debe ser mayor a 0")
    private Double peso;
    private Boolean aptoCeliacos;
    private Boolean aptoHipertensos;
    private Boolean aptoDiabeticos;
    private Boolean aptoIntoleranteLactosa;
    private Double IMC;
    private Sexo sexo;

    @OneToOne
    private Foto foto;

    @ManyToOne
    private Comedor comedor;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
