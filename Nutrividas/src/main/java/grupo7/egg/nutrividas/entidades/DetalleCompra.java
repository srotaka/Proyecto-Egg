package grupo7.egg.nutrividas.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="detalle_compras")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Canasta canasta;

    private Integer cantidad;

    @ManyToOne
    private Usuario usuario;

    private Double subtotal;

    @ManyToOne
    private Compra compra;

    private Boolean asignado;
}
