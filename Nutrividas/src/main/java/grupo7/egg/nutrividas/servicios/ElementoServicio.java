package grupo7.egg.nutrividas.servicios;


import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.repositorios.CanastaRepository;
import grupo7.egg.nutrividas.repositorios.ElementoRepository;
import grupo7.egg.nutrividas.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElementoServicio {

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CanastaRepository canastaRepository;

    public Elemento crearElementoDeCanasta(Long idProducto, Integer cantidadNecesaria, Long idCanasta) throws Exception{

        Elemento elemento = new Elemento();

        if(idProducto == null){
            throw new Exception("El producto es obligatorio");
        }
        if(cantidadNecesaria == null || cantidadNecesaria <= 0){
            throw new Exception("Error en cantidad Necesaria");
        }

        elemento.setProducto(productoRepository.buscarProductoPorId(idProducto));
        elemento.setCantidadNecesaria(cantidadNecesaria);
        elemento.setCantidadComprada(0);
        elemento.setCanasta(canastaRepository.buscarCanastaPorId(idCanasta));
        elemento.setFueComprado(true);

        return elemento;
    }

    @Transactional
    public void comprarCantidadDeElemento(Long idElemento, Integer cantidadComprada) throws Exception{
        Elemento elemento = elementoRepository.obtenerElementoPorId(idElemento);
        Integer cantidadNecesaria = elemento.getCantidadNecesaria();
        Integer cantidad = elemento.getCantidadComprada();

        // no puedo comprar mas de lo que se necesita
        if(cantidadComprada > cantidadNecesaria){
            throw new Exception("La cantidad a comprar no puede ser mayor a la necesaria");
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


}
