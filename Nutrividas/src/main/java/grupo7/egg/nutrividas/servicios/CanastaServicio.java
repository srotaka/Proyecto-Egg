package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.CanastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CanastaServicio {

    @Autowired
    private CanastaRepository canastaRepository;

    @Autowired
    private ElementoServicio elementoServicio;

    @Transactional
    public Canasta crearCanasta(String descripcion, Integer cantidadPersonas,
                                List<Elemento> elementos, Comedor comedor){

        if(canastaRepository.existsByDescripcionAndComedor(descripcion,comedor)){
            throw new FieldAlreadyExistException("La canasta que desea crear ya existe");
        }
        System.out.println("Elemento: "+elementos.size());
        System.out.println("Elemento id:"+elementos.get(0).getId());
        Canasta canasta = new Canasta();
        canasta.setDescripcion(descripcion);
        canasta.setCantidadDePersonas(cantidadPersonas);
        canasta.setElementos(elementos);
        canasta.setComedor(comedor);
        canasta.setAlta(true);
        Double precioFinal = 0.0;
        for (Elemento e: elementos) {
            System.out.println("Precio :"+e.getProducto().getPrecio());
            precioFinal += (e.getProducto().getPrecio() * e.getCantidadNecesaria());
        }
       /* for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
           precioFinal += entry.getKey().getPrecio() * entry.getValue();
        }*/
        canasta.setPrecio(precioFinal);
        return canastaRepository.save(canasta);
    }

    @Transactional
    public Canasta modificarCanasta(Long id,String descripcion, Integer cantidadPersonas,
                                    List<Elemento> elementos, Comedor comedor){

        Canasta canasta = canastaRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("La canasta que desea modificar no existe"));
        if(canastaRepository.existsByDescripcionAndComedor(descripcion,comedor)
                && canastaRepository.findByDescripcionAndComedor(descripcion,comedor).getId() != id){
            throw new FieldAlreadyExistException("Ya existe una canasta con la misma descripción para el comedor '"
            +comedor+"' ");
        }

        canasta.setDescripcion(descripcion);
        canasta.setCantidadDePersonas(cantidadPersonas);
        canasta.setElementos(elementos);
        canasta.setComedor(comedor);
        canasta.setAlta(true);
        Double precioFinal = 0.0;
        for (Elemento e: elementos) {
            precioFinal += (e.getProducto().getPrecio() * e.getCantidadNecesaria());
        }
        canasta.setPrecio(precioFinal);
        return canastaRepository.save(canasta);
    }


    @Transactional(readOnly = true)
    public Canasta buscarPorId(Long id){
        return canastaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("La canasta que desea modificar no existe"));
    }

    @Transactional(readOnly = true)
    public List<Canasta> buscarCanastasPorComedor(Comedor comedor){
        return canastaRepository.findByComedor(comedor);
    }


    @Transactional(readOnly = true)
    public List<Canasta> listarCanastas(){
        return canastaRepository.findAll();
    }

    @Transactional
    public void habilitarCanasta(Long id){
        Canasta canasta = canastaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("La canasta que desea modificar no existe"));
        canastaRepository.deleteById(id);
    }

    @Transactional
    public void deshabilitarCanasta(Long id){
        Canasta canasta = canastaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("La canasta que desea modificar no existe"));
        /*canasta.getElementos().forEach(e -> elementoServicio.deshabilitarElemento(e.getId()));*/
        canastaRepository.deleteById(id);
    }

    @Transactional
    public void crearFoto(Foto foto, Long id){
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }
        canastaRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló una canasta con el id '"+id+"'"));

        canastaRepository.actualizarFoto(foto,id);
    }

}
