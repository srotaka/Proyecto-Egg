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

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {


    Page<Producto> findByCategoria(Categoria categoria,Pageable pageable);
    List<Producto> findByAptoCeliacos(Boolean aptoCeliacos);
    List<Producto> findByAptoHipertensos(Boolean aptoHipertensos);
    List<Producto> findByAptoIntoleranteLactosa(Boolean aptoIntoleranteLactosa);
    List<Producto> findByAptoDiabeticos(Boolean aptoDiabeticos);
    boolean existsByNombreAndMarca(String nombre,String marca);


    @Modifying
    @Query("UPDATE Producto p SET p.alta = 1 WHERE p.id = :id")
    void habilitarProducto(@Param("id") Long id);

    @Query("SELECT p FROM Producto p WHERE p.id = :id")
    Producto buscarProductoPorId(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Producto p SET p.nombre = :nombre, p.marca = :marca, p.precio = :precio, " +
            "p.aptoIntoleranteLactosa = :aptoIntoleranteLactosa, p.aptoCeliacos = :aptoCeliacos, " +
            "p.aptoHipertensos = :aptoHipertensos, p.aptoDiabeticos = :aptoDiabeticos WHERE p.id = :id")
    void modificarProducto(@Param("id") Long id, @Param("nombre") String nombre, @Param("marca") String marca,
                           @Param("precio") Double precio, @Param("aptoIntoleranteLactosa") Boolean aptoIntoleranteLactosa,
                           @Param("aptoCeliacos") Boolean aptoCeliacos, @Param("aptoHipertensos") Boolean aptoHipertensos,
                           @Param("aptoDiabeticos") Boolean aptoDiabeticos);

    @Modifying
    @Query("UPDATE Producto p SET p.foto = :foto WHERE p.id = :id")
    void actualizarFoto(Foto foto, Long id);

}
