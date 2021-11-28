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


@Entity
@Table(name="elementos")
@SQLDelete(sql = "UPDATE elementos SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Elemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;
    private Integer cantidadNecesaria;
    private Integer cantidadComprada;
    private Boolean fueComprado;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
