package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,Integer> {

    @Query("SELECT p FROM Provincia p WHERE p.alta = 1")
    List<Provincia> obtenerProvincias();

}
