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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="personas")
@SQLDelete(sql = "UPDATE personas SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private Double altura;
    private Double peso;
    private Boolean aptoCeliacos;
    private Boolean aptoHipertensos;
    private Boolean aptoDiabeticos;
    private Boolean aptoIntoleranteLactosa;
    private Double IMC;
    private Sexo sexo;

    @ManyToOne
    private Comedor comedor;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
