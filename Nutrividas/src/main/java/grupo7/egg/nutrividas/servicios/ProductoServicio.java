package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Categoria;
import grupo7.egg.nutrividas.repositorios.ProductoRepository;
import grupo7.egg.nutrividas.util.Validations;
import grupo7.egg.nutrividas.util.paginacion.Paged;
import grupo7.egg.nutrividas.util.paginacion.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
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

        return null;
    }

    @Transactional(readOnly = true)
    public Paged<Producto> buscarTodos(int page, int size){
        PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "nombre"));
        Page<Producto> productPage = productoRepository.findAll(request);
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Producto> buscarPorCategoria(String categoria,int page, int size) throws Exception {

        Pageable request = PageRequest.of(page - 1, size, Sort.Direction.ASC, "nombre");
        Page<Producto> productPage = productoRepository.findByCategoria(Validations.getCategoria(categoria),request);
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }
}
