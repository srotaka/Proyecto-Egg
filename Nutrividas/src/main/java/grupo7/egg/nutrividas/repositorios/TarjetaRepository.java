package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.entidades.Tarjeta;
import grupo7.egg.nutrividas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    @Query("SELECT t FROM Tarjeta t WHERE t.numeroTarjeta = :numeroTarjeta")
    Tarjeta obtenerTarjetaPorNumero(@Param("numeroTarjeta") Long numeroTarjeta);

    @Query("SELECT t FROM Tarjeta t WHERE t.usuario = :usuario")
    List<Tarjeta> obtenerTarjetasPorUsuario(@Param("usuario") Usuario usuario);

    @Query("SELECT t FROM Tarjeta t WHERE t.usuario = :usuario AND t.alta = true")
    List<Tarjeta> obtenerTarjetasPorUsuarioHabilitadas(@Param("usuario") Usuario usuario);

    @Query("SELECT t FROM Tarjeta t WHERE t.usuario = :usuario")
    List<Tarjeta> obtenerTarjetasDeUsuario(@Param("usuario") Usuario usuario);

    @Modifying
    @Query("UPDATE Tarjeta t SET t.alta = true WHERE t.id = :id")
    Tarjeta habilitarTarjeta(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Tarjeta t SET t.alta = false WHERE t.id = :id")
    Tarjeta deshabilitarTarjeta(@Param("id") Long id);

    boolean existsByNumeroTarjetaAndUsuario(Long numeroTarjeta, Usuario usuario);
    Optional<Tarjeta> findByNumeroTarjetaAndUsuario(Long numeroTarjeta, Usuario usuario);
}
