package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Comedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComedorRepository extends JpaRepository<Comedor,Long> {

    @Query("SELECT c FROM Comedor c WHERE c.id = :id")
    Comedor buscarComedorPorId(@Param("id") Long id);

    @Query("SELECT c FROM Comedor c WHERE c.nombre = :nombre")
    Comedor buscarComedorPorNombre(@Param("nombre") String nombre);

    @Modifying
    @Query("UPDATE Comedor c SET c.alta = false WHERE c.id = :id")
    void deshabilitarComedor(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Comedor c SET c.alta = true WHERE c.id = :id")
    void habilitarComedor(@Param("id") Long id);
}
