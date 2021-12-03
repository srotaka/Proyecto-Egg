package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Biografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiografiaRepository extends JpaRepository<Biografia,Long> {

}
