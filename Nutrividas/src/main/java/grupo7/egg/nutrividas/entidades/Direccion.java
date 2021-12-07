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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @NotEmpty(message = "El campo 'calle' es obligatorio")
    private String calle;
    @Pattern(regexp="\\d{4}",message = "El número debe contener 4 dígitos")
    private Integer numero;
    @NotEmpty(message = "El campo 'código postal' es obligatorio")
    @Pattern(regexp="\\d{4}",message = "El código postal debe contener 4 dígitos")
    private String codigoPostal;
    @NotEmpty(message = "El campo 'localidad' es obligatorio")
    private String localidad;
    @NotEmpty(message = "El campo 'provincia' es obligatorio")
    private Provincia provincia;
    private String pais;
    private Boolean alta;

    @OneToOne
    private Comedor comedor;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
