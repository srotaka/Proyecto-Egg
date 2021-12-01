package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista,Long> {
}
