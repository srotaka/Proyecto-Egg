package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRpository extends JpaRepository<Usuario,Long> {
}
