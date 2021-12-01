package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Comedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComedorRepository extends JpaRepository<Comedor,Long> {

}
