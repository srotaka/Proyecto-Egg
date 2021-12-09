package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {

    @Query("UPDATE Marca m SET m.alta = true WHERE m.id = :id")
    void habilitar(@Param("id") Long id);

    boolean existsByNombre(String nombre);
    Optional<Marca> findByNombre(String nombre);
}
