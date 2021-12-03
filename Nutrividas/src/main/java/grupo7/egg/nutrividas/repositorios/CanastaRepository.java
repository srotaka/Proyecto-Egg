package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Comedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CanastaRepository extends JpaRepository<Canasta,Long> {

    @Modifying
    @Query("UPDATE Canasta c SET c.alta = true WHERE c.id = :id")
    void habilitar(@Param("id") Long id);

    boolean existsByDescripcionAndComedor(String descripcion, Comedor comedor);

    List<Canasta> findByComedor(Comedor comedor);
}
