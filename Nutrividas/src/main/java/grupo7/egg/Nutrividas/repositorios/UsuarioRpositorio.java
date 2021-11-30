package grupo7.egg.Nutrividas.repositorios;

import grupo7.egg.Nutrividas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRpository extends JpaRepository<Usuario,Long> {
}
