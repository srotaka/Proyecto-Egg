package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Categoria;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
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
import org.springframework.data.domain.Pageable;;import java.util.NoSuchElementException;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;


    @Transactional(readOnly = true)
    public Paged<Producto> buscarTodos(int page, int size){
        PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "nombre"));
        Page<Producto> productPage = productoRepository.findAll(request);
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Producto> buscarPorCategoria(String categoria,int page, int size,Sort order) throws Exception {

        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Producto> productPage = productoRepository.findByCategoria(Validations.getCategoria(categoria),request);
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }

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

        return productoRepository.save(producto);
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
    public Producto modificarProducto(Long id,String nombre, String marca, Double precio, Categoria categoria,
                                  Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                  Boolean aptoDiabeticos) throws Exception {

        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new Exception("No se encontró un producto con el id "+id));

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

        return productoRepository.save(producto);
    }

    @Transactional
    public void deshabilitarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Transactional
    public void habilitarProducto(Long id) {
        productoRepository.habilitarProducto(id);
    }

    @Transactional(readOnly = true)
    public Producto obtenerProductoPorId(Long id){
        return productoRepository.buscarProductoPorId(id);
    }

    @Transactional
    public void crearFoto(Foto foto, Long id){
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }

        Producto producto  = productoRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló un producto con el id '"+id+"'"));

        productoRepository.actualizarFoto(foto,id);
    }
}