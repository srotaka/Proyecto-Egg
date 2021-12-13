package grupo7.egg.nutrividas.entidades;

import grupo7.egg.nutrividas.enums.MarcaTarjeta;
import grupo7.egg.nutrividas.enums.TipoTarjeta;
import grupo7.egg.nutrividas.entidades.Usuario;
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
@Table(name="tarjetas")
@SQLDelete(sql = "UPDATE Tarjetas SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String nombre;
    @NotBlank(message = "El campo 'apellido' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String apellido;
    @NotNull(message = "El campo 'número' es obligatorio")
    @Min(value = 100000000000L,message = "El campo 'número' debe contener 12 caracteres")
    @Max(value = 999999999999L,message = "El campo 'número' debe contener 12 caracteres")
    private Long numeroTarjeta;
    @NotNull(message = "El campo 'código de seguridad' es obligatorio")
    @Min(value = 100,message = "El campo 'código de seguridad' debe contener 3 dígitos")
    @Max(value = 999,message = "El campo 'código de seguridad' debe contener 3 dígitos")
    private Integer codigoSeguridad;
    private TipoTarjeta tipoTarjeta;
    private MarcaTarjeta marcaTarjeta;
    private LocalDate fechaVencimiento;
    @ManyToOne
    private Usuario usuario;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}

