package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista,Long> {

    boolean existsByMatriculaOrDocumento(Long matricula, Long documento);
    Optional<Nutricionista> findByMatriculaOrDocumento(Long matricula, Long documento);
    Optional<Nutricionista> findById(Long id);
    @Query(value = "SELECT n FROM nutricionistas n CONCAT_WS(n.nombre,n.apellido, n.matricula,n.documento,n.comedor.nombre) LIKE %:busqueda%",nativeQuery = true)
    Page<Nutricionista> buscarPorTodosCampos(@Param("busqueda") String busqueda, Pageable pageable);
    Page<Nutricionista> findByMatriculaContaining(Long matricula,Pageable pageable);
    /*
    Optional<Nutricionista> findByDocumento(Long documento);
    Optional<Comedor> findByComedor_Nombre(String comedor);*/

    @Query("SELECT n FROM Nutricionista n WHERE n.matricula = :matricula")
    List<Nutricionista> findByMatricula(Long matricula);

    @Query("SELECT n FROM Nutricionista n WHERE n.documento = :documento")
    List<Nutricionista> findByDocumento(Long documento);

    @Modifying
    @Query("UPDATE Nutricionista n SET n.alta = true WHERE n.id = :id")
    void habilitarNutricionista(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Nutricionista n SET n.foto = :foto WHERE n.id = :id")
    void actualizarFoto(@Param("foto") Foto foto,@Param("id")Long id);

    @Query("SELECT n FROM Nutricionista n WHERE n.credencial.id = :id")
    Nutricionista buscarNutricionistaPorCredencial(@Param("id") Long id);

}
