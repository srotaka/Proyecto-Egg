package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Comedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComedorRepository extends JpaRepository<Comedor,Long> {

    @Query("SELECT c FROM Comedor c WHERE c.id = :id")
    Comedor buscarComedorPorId(@Param("id") Long id);
}
