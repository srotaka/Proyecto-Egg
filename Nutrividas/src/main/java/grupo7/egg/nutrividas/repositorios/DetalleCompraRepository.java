package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {

    @Modifying
    @Query("UPDATE DetalleCompra dc SET dc.cantidad = :cantidad WHERE dc.id = :id")
    void actualizarCantidad(@Param("cantidad")Integer cantidad, @Param("id") Long id);
    List<DetalleCompra> findByUsuario_id(Long usuarioId);
    List<DetalleCompra> findByCompra_id(Long idCompra);
    List<DetalleCompra> findByCanasta_id(Long idCanasta);

    @Query("SELECT d FROM DetalleCompra d WHERE d.usuario.credencial.mail = :usuarioMail AND d.asignado=false")
    List<DetalleCompra> obtenerDetallesCompraSesion(@Param("usuarioMail") String mail);
    @Query("SELECT d FROM DetalleCompra d WHERE d.canasta= :canasta AND d.usuario = :usuario AND d.asignado=false")
    Optional<DetalleCompra> existeDetalleCompraSesion(@Param("canasta") Canasta canasta, @Param("usuario") Usuario usuario);

}
