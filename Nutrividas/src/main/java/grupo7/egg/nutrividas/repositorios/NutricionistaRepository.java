package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista,Long> {

    boolean existsByMatriculaOrDocumento(Long matricula, Long documento);
    Optional<Nutricionista> findByMatriculaOrDocumento(Long matricula, Long documento);
    Optional<Nutricionista> findById(Long id);
    @Query("SELECT n FROM Nutricionista n CONCAT_WS(l.nombre,l.apellido, l.matricula,l.documento,l.comedor.nombre) LIKE %:search%")
    Page<Nutricionista> buscarPorTodosCampos(String busqueda, Pageable pageable);
    Page<Nutricionista> findByMatriculaContaining(Long matricula,Pageable pageable);
    /*
    Optional<Nutricionista> findByDocumento(Long documento);
    Optional<Comedor> findByComedor_Nombre(String comedor);*/

    @Modifying
    @Query("UPDATE Nutricionista n SET n.alta = true WHERE n.id = :id")
    void habilitarNutricionista(Long id);

    @Modifying
    @Query("UPDATE Nutricionista n SET n.foto = :foto WHERE n.id = :id")
    void actualizarFoto(Foto foto, Long id);

}
