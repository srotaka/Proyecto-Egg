package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.apellido = :apellido")
    Usuario obtenerUsuarioPorNombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    Usuario obtenerUsuarioPorMail(@Param("mail") String mail);

    @Query("UPDATE Usuario u SET u.alta = 1")
    void deshabilitarUsuario(@Param("id") Long id);
}
