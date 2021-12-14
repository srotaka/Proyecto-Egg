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

    @NotBlank(message = "El campo 'nombre' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    @Column(nullable = false)
    private String descripcion;

    @NotNull(message = "La cantidad de peronas es obligatoria")
    @Positive(message = "La cantidad de peronas no puede ser menor a 1")
    @Column(nullable = false)
    private Integer cantidadDePersonas;

    @Column(nullable = false)
    private Double precio;


    @OneToMany(mappedBy = "canasta")
    List<Elemento> elementos;

    /*@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "elementos_canasta", joinColumns = @JoinColumn(name = "elementos_canasta"))
    @MapKeyColumn(name = "producto_key", length = 50)
    @Column(name = "producto_val", length = 100)
    private Map<Producto,Integer> productos;*/

    @ManyToOne
    private Comedor comedor;

    @OneToOne
    private Foto foto;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}
