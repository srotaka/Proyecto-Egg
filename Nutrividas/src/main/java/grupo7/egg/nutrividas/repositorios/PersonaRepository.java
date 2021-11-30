package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
