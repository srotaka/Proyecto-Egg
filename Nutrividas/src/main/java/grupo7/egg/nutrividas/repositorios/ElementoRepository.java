package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementoRepository extends JpaRepository<Elemento,Long> {

    @Query("SELECT e FROM Elemento e WHERE e.id = :id")
    Elemento obtenerElementoPorId(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Elemento e SET e.fueComprado = true WHERE e.id = :id")
    void comprarElemento(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Elemento e SET e.cantidadComprada = :cantidadComprada WHERE e.id = :id")
    void comprarCantidadDeElemento(@Param("id") Long id, @Param("cantidadComprada") Integer cantidadComprada);

    @Modifying
    @Query("UPDATE Elemento e SET e.cantidadNecesaria = :cantidadNecesaria WHERE e.id = :id")
    void cambiarCantidadNecesaria(@Param("id") Long id, @Param("cantidadNecesaria") Integer cantidadNecesaria);

    @Modifying
    @Query("UPDATE Elemento e SET e.cantidadNecesaria = :cantidadNecesaria WHERE e.id = :id")
    void actualizarCantidad(@Param("id") Long id, @Param("cantidadNecesaria") Integer cantidadNecesaria);

    boolean existsByProducto_IdAndCanasta_Id(Long prductoId, Long canastaId);
    Optional<Elemento> findByProducto_IdAndCanasta_id(Long prductoId, Long canastaId);
    List<Elemento> findByProducto_Id(Long prductoId);

    @Query("SELECT e FROM Elemento e WHERE e.usuario.credencial.mail = :usuarioMail AND e.asignado=false")
    List<Elemento> obtenerElementosSesion(@Param("usuarioMail") String mail);
    @Query("SELECT e FROM Elemento e WHERE e.producto= :producto AND e.usuario = :usuario AND e.asignado=false")
    Optional<Elemento> existeElementoSesion(@Param("producto") Producto producto,@Param("usuario") Usuario usuario);
}
