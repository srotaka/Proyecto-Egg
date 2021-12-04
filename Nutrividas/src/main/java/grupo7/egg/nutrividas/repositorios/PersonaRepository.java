package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {

    @Modifying
    @Query("UPDATE Persona p SET p.alta = 1 WHERE p.id = :id")
    void habilitarPersona(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Persona p SET p.foto = :foto WHERE p.id = :id")
    void actualizarFoto(@Param("foto")Foto foto,@Param("id") Long id);
}
