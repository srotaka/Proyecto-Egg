package grupo7.egg.Nutrividas.repositorios;

import grupo7.egg.Nutrividas.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
