package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion,Long> {

    @Query("UPDATE Direccion d SET d.alta = true WHERE d.id = :id")
    void habilitarDireccion(@Param("id") Long id);

    boolean existsByCalleAndNumeroAndLocalidad(String calle,Integer numero,String localidad);
    Optional<Direccion> findByCalleAndNumeroAndLocalidad(String calle, Integer numero, String localidad);
}
