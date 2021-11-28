package grupo7.egg.nutrividas.repositorios;


import grupo7.egg.nutrividas.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
