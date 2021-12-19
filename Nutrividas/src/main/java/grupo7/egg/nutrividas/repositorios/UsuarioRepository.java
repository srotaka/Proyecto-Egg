package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.apellido = :apellido")
    Optional<Usuario> obtenerUsuarioPorNombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    Optional<Usuario> findByDni(@Param("dni") Long dni);

    Optional<Usuario> findByCredencial_mail(String mail);

    @Modifying
    @Query("UPDATE Usuario u SET u.alta = true")
    void habilitarUsuario(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Usuario u SET u.foto = :foto WHERE u.id = :id")
    void actualizarFoto(Foto foto, Long id);

    @Query("SELECT u FROM Usuario u WHERE u.credencial.id = :id")
    Usuario buscarUsuarioPorCredencial(@Param("id") Long id);

}
