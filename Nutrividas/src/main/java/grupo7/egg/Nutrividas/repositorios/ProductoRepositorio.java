package grupo7.egg.Nutrividas.repositorios;


import grupo7.egg.Nutrividas.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    @Modifying
    @Query("UPDATE Producto p SET p.alta = true WHERE p.id = :id")
    void habilitar(@Param("id")Long id);

    //List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByAptoCeliacos(Boolean aptoCeliacos);
    List<Producto> findByAptoHipertensos(Boolean aptoHipertensos);
    List<Producto> findByAptoIntoleranteLactosa(Boolean aptoIntoleranteLactosa);
    List<Producto> findByAptoDiabeticos(Boolean aptoDiabeticos);
    boolean existsByNombreAndMarca(String nombre,String marca);
}
