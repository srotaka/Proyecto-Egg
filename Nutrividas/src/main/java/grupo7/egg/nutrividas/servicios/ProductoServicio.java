package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Marca;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Categoria;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
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

    @Autowired
    private MarcaServicio marcaServicio;

    @Transactional
    public Producto crearProducto(String nombre, Long idMarca, Double precio, Categoria categoria,
                                  Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                  Boolean aptoDiabeticos){

        Marca marca = marcaServicio.buscarPorId(idMarca);
        if(productoRepository.existsByNombreAndMarca_Nombre(nombre,marca.getNombre())){
            throw new FieldAlreadyExistException("Ya esite un producto registrado con el mismo nombre y marca");
        }

        validarDatosDelProducto(nombre,marca.getNombre(),precio);
        Producto producto = new Producto();
        producto.setNombre(Validations.formatText(nombre));
        producto.setMarca(marcaServicio.buscarPorId(idMarca));
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        producto.setAptoIntoleranteLactosa(aptoIntoleranteLactosa);
        producto.setAptoCeliacos(aptoCeliaco);
        producto.setAptoHipertensos(aptoHipertenso);
        producto.setAptoDiabeticos(aptoDiabeticos);
        producto.setAlta(true);

        return productoRepository.save(producto);
    }

    public void validarDatosDelProducto(String nombre, String marca, Double precio){

        if(nombre == null || nombre.trim().isEmpty()){
            throw new FieldInvalidException("El nombre del producto es obligatorio");
        }
        if(marca == null || marca.trim().isEmpty()){
            throw new FieldInvalidException("La marca del producto es obligatoria");
        }
        if(productoRepository.existsByNombreAndMarca_Nombre(nombre,marca)){
            throw new FieldInvalidException("El producto ya existe");
        }
        if(precio == null || precio < 0){
            throw new FieldInvalidException("El precio del producto no puede ser ni nulo o negativo");
        }
    }

    @Transactional
    public Producto modificarProducto(Long id,String nombre, Long idMarca, Double precio, Categoria categoria,
                                  Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                  Boolean aptoDiabeticos){

        Marca marca = marcaServicio.buscarPorId(idMarca);
        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No se encontró un producto con el id "+id));

        if(productoRepository.existsByNombreAndMarca_Nombre(nombre,marca.getNombre()) &&
                productoRepository.findByNombreAndMarca_Nombre(nombre,marca.getNombre()).get().getId() != id){
            new FieldAlreadyExistException("Ya esite un producto registrado con el mismo nombre y marca");
        }

        validarDatosDelProducto(nombre, marca.getNombre(), precio);

        producto.setNombre(Validations.formatText(nombre));
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
        return productoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No se encontró un producto con el id "+id));
    }

    @Transactional
    public void crearFoto(Foto foto, Long id){
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }
        productoRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló un producto con el id '"+id+"'"));

        productoRepository.actualizarFoto(foto,id);
    }


    @Transactional(readOnly = true)
    public Paged<Producto> buscarTodos(int page, int size){
        PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "nombre"));
        Page<Producto> productPage = productoRepository.findAll(request);
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Producto> buscarPorCategoria(String categoria,int page, int size,Sort order){

        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Producto> productPage = productoRepository.findByCategoria(Validations.getCategoria(categoria),request);
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Producto> buscarAptoCeliacos(Boolean apto,String patologia, int page, int size,Sort order) throws Exception {

        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Producto> productPage = null;

        switch (patologia){
            case "celiacos":
                productPage = productoRepository.findByAptoCeliacos(apto,request);
            case  "diabeticos":
                productPage = productoRepository.findByAptoDiabeticos(apto,request);
            case "hipertensos":
                productPage = productoRepository.findByAptoHipertensos(apto,request);
            case "intolerantesLactosa":
                productPage = productoRepository.findByAptoIntoleranteLactosa(apto,request);
        }
        return new Paged(productPage, Paging.of(productPage.getTotalPages(), page, size));
    }

}