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
import java.util.List;

@Entity
@Table(name="canastas")
@SQLDelete(sql = "UPDATE canastas SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Canasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer cantidadDePersonas;

    @JoinColumn
    @OneToMany
    List<Elemento> elementos;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
