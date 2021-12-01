package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombreAndMarca(String nombre,String marca);
}
