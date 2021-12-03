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

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    @Query("SELECT t FROM Tarjeta t WHERE t.numeroTarjeta = :numeroTarjeta")
    Tarjeta obtenerTarjetaPorNumero(@Param("numeroTarjeta") Long numeroTarjeta);

    @Query("SELECT t FROM Tarjeta t WHERE t.usuario = :usuario")
    List<Tarjeta> obtenerTarjetasPorUsuario(@Param("usuario") Usuario usuario);

    @Modifying
    @Query("UPDATE Tarjeta t SET t.alta = 1 WHERE t.id = :id")
    void habilitarTarjeta(@Param("id") Long id);
}
