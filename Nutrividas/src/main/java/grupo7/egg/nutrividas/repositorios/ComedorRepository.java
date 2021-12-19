package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.entidades.Direccion;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.util.paginacion.Paged;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComedorRepository extends JpaRepository<Comedor,Long> {

    Optional<Comedor> findByNombre(String nombre);

    @Query("SELECT c FROM Comedor c WHERE c.nombre = :nombre")
    List<Comedor>buscarComedorPorNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Comedor c WHERE c.nutricionista.id = :id")
    List<Comedor> buscarComedoresPorNutricionista(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Comedor c SET c.alta = true WHERE c.id = :id")
    void habilitarComedor(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Comedor c SET c.nutricionista.id = :idNutricionista WHERE c.id = :id")
    void asignarNutricionistaAComedor(@Param("idNutricionista") Long idNutricionista, @Param("id") Long id);

    boolean existsByDireccion(Direccion direccion);
    Comedor findByDireccion(Direccion direccion);
    Optional<Comedor> findByCredencial_mail(String mail);

    List<Comedor> findByNutricionistaIsNull();

    @Modifying
    @Query("UPDATE Comedor c SET c.foto = :foto WHERE c.id = :id")
    void actualizarFoto(Foto foto, Long id);

    @Query(value = "SELECT c FROM Comedor c WHERE c.alta = true")
    List<Comedor> findAll();

    @Query("SELECT c FROM Comedor c WHERE c.credencial.id = :id")
    Comedor buscarComedorPorCredencial(@Param("id") Long id);

}
