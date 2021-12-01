package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CanastaRepository extends JpaRepository<Canasta,Long> {

    @Query("SELECT c FROM Canasta c WHERE c.id = :id")
    Canasta buscarCanastaPorId(@Param("id") Long id);
}
