package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Long> {

    Optional<Foto> findByRuta(String ruta);

}
