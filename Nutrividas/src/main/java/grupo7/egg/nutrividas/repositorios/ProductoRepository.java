package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombreAndMarca(String nombre,String marca);
    Optional<Producto> findByNombreAndMarca(String nombre,String marca);
    Page<Producto> findByCategoria(Categoria categoria,Pageable pageable);
    Page<Producto> findByAptoCeliacos(Boolean aptoCeliacos,Pageable pageable);
    Page<Producto> findByAptoHipertensos(Boolean aptoHipertensos,Pageable pageable);
    Page<Producto> findByAptoIntoleranteLactosa(Boolean aptoIntoleranteLactosa,Pageable pageable);
    Page<Producto> findByAptoDiabeticos(Boolean aptoDiabeticos,Pageable pageable);

    @Modifying
    @Query("UPDATE Producto p SET p.alta = 1 WHERE p.id = :id")
    void habilitarProducto(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Producto p SET p.foto = :foto WHERE p.id = :id")
    void actualizarFoto(Foto foto, Long id);

}
