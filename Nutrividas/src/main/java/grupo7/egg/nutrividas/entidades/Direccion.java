package grupo7.egg.nutrividas.entidades;

import grupo7.egg.nutrividas.enums.Provincia;
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

@Entity
@Table(name="direcciones")
@SQLDelete(sql = "UPDATE direcciones SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo 'calle' es obligatorio")
    private String calle;

    @NotNull(message = "El campo 'número' es obligatorio")
    @Max(value = 9999,message = "El campo 'número' debe contener como máximo 4 dígitos")
    private Integer numero;

    @NotNull(message = "El  'código postal' es obligatorio")
    @Min(value = 1000,message = "El campo ''código postal' debe contener 4 dígitos")
    @Max(value = 9999,message = "El campo ''código postal' debe contener 4 dígitos")
    private Integer codigoPostal;
    @NotBlank(message = "El campo 'localidad' es obligatorio")
    private String localidad;
    @NotBlank(message = "El campo 'provincia' es obligatorio")
    private String provincia;
    private String pais;
    private Boolean alta;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
