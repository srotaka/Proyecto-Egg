package grupo7.egg.nutrividas.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="credenciales")
@SQLDelete(sql = "UPDATE Credenciales SET alta = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{El nombre de usuario es obligatorio\"}")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "El campo 'mail' es obligatorio")
    @Email(message = "El formato de email debe ser válido")
    @Column(unique = true)
    private String mail;
    @NotBlank(message = "La 'contraseña' es obligatoria")
    private String password;
    private Boolean alta;

    @JoinTable(
            name = "credenciales_roles",
            joinColumns = @JoinColumn(name = "credencial_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="rol_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Rol> roles;


    @CreatedDate
    @Column( updatable = false)
    private LocalDateTime creacion;

    @LastModifiedDate
    private LocalDateTime modificacion;

    private Boolean habilitado;
}
