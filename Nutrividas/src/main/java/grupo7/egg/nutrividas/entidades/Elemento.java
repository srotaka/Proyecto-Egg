package grupo7.egg.nutrividas.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Entity
@Table(name="elementos")
@SQLDelete(sql = "UPDATE Elementos SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Elemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El producto es obligatorio")
    @ManyToOne
    private Producto producto;

    @NotNull(message = "La cantidad necesaria es obligatoria")
    @Positive(message = "La cantidad no puede ser menor a 1")
    private Integer cantidadNecesaria;
    private Integer cantidadComprada;
    private Boolean fueComprado;
    @ManyToOne
    private Canasta canasta;
    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;
}
