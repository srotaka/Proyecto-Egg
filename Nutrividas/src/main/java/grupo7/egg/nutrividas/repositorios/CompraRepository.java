package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

    @Modifying
    @Query("UPDATE Compra c SET c.alta = true WHERE c.id = :id")
    void habilitar(@Param("id") Long id);
    List<Compra> findByUsuario_id(Long idUsuario);
}
