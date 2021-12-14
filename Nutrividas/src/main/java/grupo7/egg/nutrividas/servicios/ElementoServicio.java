package grupo7.egg.nutrividas.servicios;


import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.CanastaRepository;
import grupo7.egg.nutrividas.repositorios.ElementoRepository;
import grupo7.egg.nutrividas.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ElementoServicio {

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private ProductoServicio productoServicio;

    @Transactional
    public Elemento crearElemento(Long idProducto, Usuario usuario){

        /*if(elementoRepository.existsByProducto_IdAndCanasta_Id(idProducto,idCanasta)){
            throw new FieldAlreadyExistException("Ya existe el elemento en esta canasta");
        }*/
        if(idProducto == null){
            throw new FieldInvalidException("El producto es obligatorio");
        }

        Elemento elemento = new Elemento();

        elemento.setProducto(productoServicio.obtenerProductoPorId(idProducto));
        elemento.setCantidadNecesaria(1);
        elemento.setCantidadComprada(0);
        elemento.setFueComprado(false);
        elemento.setUsuario(usuario);
        elemento.setAsignado(false);
        return elementoRepository.save(elemento);
    }

    @Transactional
    public Elemento editarCantidad(Long idElemento,Integer cantidadNecesaria,Boolean asignado){

        Elemento elemento = elementoRepository.findById(idElemento).orElseThrow(
                () -> new NoSuchElementException("No existe un elemento con en id '"+idElemento+"'")
        );
        //Opción 2 validación: Buscar Canasta e iterar lista de elementos
        /*if(elementoRepository.existsByProducto_IdAndCanasta_Id(idProducto,idCanasta)&&
        elementoRepository.findByProducto_IdAndCanasta_id(idProducto,idCanasta).get().getId() != idElemento){
            throw new FieldAlreadyExistException("Ya existe el elemento en esta canasta");
        }*/

        if(cantidadNecesaria == null || cantidadNecesaria <= 0){
            throw new FieldInvalidException("Error en cantidad Necesaria");
        }

        elemento.setCantidadNecesaria(cantidadNecesaria);
        elemento.setAsignado(asignado);
        return elementoRepository.save(elemento);
    }

    @Transactional
    public void eliminarElemento(Long id){
        Elemento elemento = elementoRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("El elemento que desea eliminar no existe"));
        elementoRepository.deleteById(elemento.getId());
    }

    @Transactional(readOnly = true)
    public Optional<Elemento> existeElementoSesion(Long idProducto, Usuario usuario){
        Producto producto = productoServicio.obtenerProductoPorId(idProducto);
        return elementoRepository.existeElementoSesion(producto,usuario);
    }

    @Transactional(readOnly = true)
    public List<Elemento> obtenerElemntosSesion(String mail){
        return elementoRepository.obtenerElementosSesion(mail);
    }

    @Transactional
    public void asignarACanasta(Elemento elemento, Canasta canasta){
        elemento.setCanasta(canasta);
        elemento.setAsignado(true);
        elementoRepository.save(elemento);
    }


    @Transactional
    public void comprarCantidadDeElemento(Long idElemento, Integer cantidadComprada){
        Elemento elemento = elementoRepository.obtenerElementoPorId(idElemento);
        Integer cantidadNecesaria = elemento.getCantidadNecesaria();
        Integer cantidad = elemento.getCantidadComprada();

        // no puedo comprar mas de lo que se necesita
        if(cantidadComprada > cantidadNecesaria){
            throw new FieldInvalidException("La cantidad a comprar no puede ser mayor a la necesaria");
        }

        // si la cantidad que se compra no lanza excepcion cambio la cantidad comprada a la cantidad anterior + lo comprado
        elementoRepository.comprarCantidadDeElemento(idElemento, cantidadComprada + cantidad);

        //modifico la nueva cantidad necesaria
        Integer nuevaCantidadNecesaria = cantidadNecesaria - cantidadComprada;
        elementoRepository.cambiarCantidadNecesaria(idElemento, nuevaCantidadNecesaria);

        //si la cantidad necesaria luego de comprar una cantidad x es igual a 0, se da al elemento por comprado
        if(nuevaCantidadNecesaria == 0){
            comprarElemento(idElemento);
        }
    }

    @Transactional
    public void comprarElemento(Long idElemento){
        elementoRepository.comprarElemento(idElemento);
    }


}
