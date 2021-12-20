package grupo7.egg.nutrividas.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="usuarios")
@SQLDelete(sql = "UPDATE Usuarios SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo 'documento' es obligatorio")
    @Min(value = 10000000,message = "El campo 'dni' debe contener 8 dígitos")
    @Max(value = 99999999,message = "El campo 'dni' debe contener 8 dígitos")
    private Long dni;

    @NotBlank(message = "El campo 'nombre' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String nombre;

    @NotBlank(message = "El campo 'apellido' es obligatorio")
    @Pattern(regexp = "^[\\p{L} .'-]+$",message="solo se permiten letras" )
    private String apellido;

    @NotNull(message = "El campo 'telefono' es obligatorio")
    @Min(value = 10000000,message = "El campo 'teléfono' debe contener 8 dígitos")
    @Max(value = 99999999,message = "El campo 'teléfono' debe contener 8 dígitos")
    private Long telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Tarjeta> tarjetas;
    @OneToOne
    private Foto foto;
    @OneToOne
    private Credencial credencial;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean alta;
}

