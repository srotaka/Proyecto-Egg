package grupo7.egg.nutrividas.servicios;


import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.CanastaRepository;
import grupo7.egg.nutrividas.repositorios.ElementoRepository;
import grupo7.egg.nutrividas.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ElementoServicio {

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private ProductoServicio productoServicio;

    @Transactional
    public Elemento crearElementoDeCanasta(Long idProducto, Integer cantidadNecesaria,Long idCanasta){

        if(elementoRepository.existsByProducto_IdAndCanasta_Id(idProducto,idCanasta)){
            throw new FieldAlreadyExistException("Ya existe el elemento en esta canasta");
        }
        if(idProducto == null){
            throw new FieldInvalidException("El producto es obligatorio");
        }
        if(cantidadNecesaria == null || cantidadNecesaria <= 0){
            throw new FieldInvalidException("Error en cantidad Necesaria");
        }
        Elemento elemento = new Elemento();

        elemento.setProducto(productoServicio.obtenerProductoPorId(idProducto));
        elemento.setCantidadNecesaria(cantidadNecesaria);
        elemento.setCantidadComprada(0);
        //elemento.setCanasta(canastaServicio.buscarPorId(idCanasta));
        elemento.setFueComprado(true);

        return elementoRepository.save(elemento);
    }

    @Transactional
    public Elemento editarElementoDeCanasta(Long idElemento,Long idProducto, Integer cantidadNecesaria, Long idCanasta){

        Elemento elemento = elementoRepository.findById(idElemento).orElseThrow(
                () -> new NoSuchElementException("No existe un elemento con en id '"+idElemento+"'")
        );
        //Opción 2 validación: Buscar Canasta e iterar lista de elementos
        if(elementoRepository.existsByProducto_IdAndCanasta_Id(idProducto,idCanasta)&&
        elementoRepository.findByProducto_IdAndCanasta_id(idProducto,idCanasta).get().getId() != idElemento){
            throw new FieldAlreadyExistException("Ya existe el elemento en esta canasta");
        }

        if(idProducto == null){
            throw new FieldInvalidException("El producto es obligatorio");
        }
        if(cantidadNecesaria == null || cantidadNecesaria <= 0){
            throw new FieldInvalidException("Error en cantidad Necesaria");
        }

        elemento.setProducto(productoServicio.obtenerProductoPorId(idProducto));
        elemento.setCantidadNecesaria(cantidadNecesaria);
        elemento.setCantidadComprada(0);
        //elemento.setCanasta(canastaServicio.buscarPorId(idCanasta));
        elemento.setFueComprado(true);

        return elementoRepository.save(elemento);
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

    public void comprarElemento(Long idElemento){
        elementoRepository.comprarElemento(idElemento);
    }

    public void deshabilitarElemento(Long id){
        Elemento elemento = elementoRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("El elemento que desea eliminar no existe"));
        elementoRepository.delete(elemento);
    }
}
