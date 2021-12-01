package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Categoria;
import grupo7.egg.nutrividas.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productoServicio {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(String nombre, String marca, Double precio, Categoria categoria,
                                  Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                  Boolean aptoDiabeticos) throws Exception {

        if(nombre == null || nombre.trim().isEmpty()){
            throw new Exception("El nombre del producto es obligatorio");
        }
        if(marca == null || marca.trim().isEmpty()){
            throw new Exception("La marca es obligatoria");
        }
        if(productoRepository.existsByNombreAndMarca(nombre,marca)){
            throw new Exception("El producto ya existe");
        }

    }
}
