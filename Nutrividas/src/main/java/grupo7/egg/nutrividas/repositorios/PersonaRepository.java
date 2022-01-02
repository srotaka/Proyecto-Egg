package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Persona;
import grupo7.egg.nutrividas.enums.CategoriaIMC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Page<Persona> findByComedor_id(Long idComedor,Pageable pageable);
    Page<Persona> findByCeliacoAndComedor_id(boolean celiaco,Long comedorId,Pageable pageable);
    Page<Persona> findByDiabeticoAndComedor_id(boolean diabetico,Long comedorId,Pageable pageable);
    Page<Persona> findByHipertensoAndComedor_id(boolean hipertenso,Long comedorId,Pageable pageable);
    Page<Persona> findByIntoleranteLactosaAndComedor_id(boolean intoleranteLactosa,Long comedorId,Pageable pageable);
    Page<Persona> findByCategoriaIMCAndComedor_id(CategoriaIMC categoriaIMC,Long comedorId, Pageable pageable);
    @Query(value = "SELECT p FROM Persona p WHERE CONCAT(trim(p.nombre),trim(p.apellido),trim(p.documento),trim(p.altura),trim(p.peso)) LIKE %:busqueda% AND p.comedor.id = :idComedor")
    Page<Persona> buscarPorTodosCampos(@Param("busqueda") String busqueda,@Param("idComedor") Long idComedor, Pageable pageable);

    @Query("SELECT COUNT(p.id)*100/ (SELECT COUNT(p.id) FROM Persona p) FROM Persona p  WHERE p.categoriaIMC = :categoriaIMC AND p.comedor.id = :idComedor")
    Double porcentajePorCategoriaIMC(@Param("categoriaIMC")CategoriaIMC categoriaIMC,@Param("idComedor") Long idComedor);

    @Modifying
    @Query("UPDATE Persona p SET p.alta = 1 WHERE p.id = :id")
    void habilitarPersona(@Param("id") Long id);


    boolean existsByDocumento(Long documento);
    Optional<Persona> findByDocumento(Long documento);
}
