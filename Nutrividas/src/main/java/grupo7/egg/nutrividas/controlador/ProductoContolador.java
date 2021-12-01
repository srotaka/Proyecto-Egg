package grupo7.egg.nutrividas.controlador;


import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.servicios.ProductoServicio;
import grupo7.egg.nutrividas.util.paginacion.Paged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoContolador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping(value = "",params = {"page","size"})
    public Paged<Producto> buscarTodos(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "5") int size) {

        return productoServicio.buscarTodos(page,size);
    }

    @GetMapping(value = "",params = {"categoria","page","size"})
    public Paged<Producto> buscarCategoria(@RequestParam(value = "categoria") String categoria,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                            @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order
                                            ) throws Exception {

        return productoServicio.buscarPorCategoria(categoria,page,size,getSort(order));
    }

    public Sort getSort(String order) throws Exception {
        switch (order){
            case "OrderByNombreASC":
                return Sort.by(Sort.Direction.ASC,"nombre");
            case "OrderByNombreDESC":
                return Sort.by(Sort.Direction.DESC,"nombre");
            case "OrderByPrecioASC":
                return Sort.by(Sort.Direction.ASC,"precio");
            case "OrderByPrecioDESC":
                return Sort.by(Sort.Direction.DESC,"precio");
            case "OrderByMarcaASC":
                return Sort.by(Sort.Direction.ASC,"marca");
            case "OrderByMarcaDESC":
                return Sort.by(Sort.Direction.DESC,"marca");
            default:
                throw new Exception("");
        }
    }
}
