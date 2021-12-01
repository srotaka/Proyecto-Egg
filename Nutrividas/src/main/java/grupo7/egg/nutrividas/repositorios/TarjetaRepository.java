package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {
}
