package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Categoria;
import grupo7.egg.nutrividas.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class productoServicio {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto crearProducto(String nombre, String marca, Double precio, Categoria categoria,
                                  Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                  Boolean aptoDiabeticos) throws Exception {
        Producto producto = new Producto();

        validarDatosDelProducto(nombre, marca, precio);

        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        producto.setAptoIntoleranteLactosa(aptoIntoleranteLactosa);
        producto.setAptoCeliacos(aptoCeliaco);
        producto.setAptoHipertensos(aptoHipertenso);
        producto.setAptoDiabeticos(aptoDiabeticos);
        producto.setAlta(true);

        return producto;
    }

    public void validarDatosDelProducto(String nombre, String marca, Double precio) throws Exception{

        if(nombre == null || nombre.trim().isEmpty()){
            throw new Exception("El nombre del producto es obligatorio");
        }
        if(marca == null || marca.trim().isEmpty()){
            throw new Exception("La marca del producto es obligatoria");
        }
        if(productoRepository.existsByNombreAndMarca(nombre,marca)){
            throw new Exception("El producto ya existe");
        }
        if(precio == null || precio < 0){
            throw new Exception("El precio del producto no puede ser ni nulo o negativo");
        }
    }

    @Transactional
    public void modificarProducto(Long id, String nombre, String marca, Double precio, Categoria categoria,
                                  Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                  Boolean aptoDiabeticos) throws Exception{
        validarDatosDelProducto(nombre, marca, precio);
        productoRepository.modificarProducto(id, nombre, marca, precio, aptoIntoleranteLactosa, aptoCeliaco, aptoHipertenso, aptoDiabeticos);
    }

    @Transactional
    public List<Producto> obtenerProductos(){
        return productoRepository.obtenerProductosHabilitados();
    }

    @Transactional
    public void deshabilitarProducto(Long id) {
        productoRepository.deshabilitarProducto(id);
    }

    @Transactional
    public void habilitarProducto(Long id) {
        productoRepository.habilitarProducto(id);
    }

    @Transactional
    public Producto obtenerProductoPorId(Long id){
        return productoRepository.buscarProductoPorId(id);
    }
}
