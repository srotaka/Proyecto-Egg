package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.repositorios.CanastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CanastaServicio {

    @Autowired
    private CanastaRepository canastaRepository;

    @Autowired
    private ElementoServicio elementoServicio;

    @Transactional
    public Canasta crearCanasta(String descripcion, Integer cantidadPersonas,
                                List<Elemento> elementos, Comedor comedor) throws Exception {

        if(canastaRepository.existsByDescripcionAndComedor(descripcion,comedor)){
            throw new FieldAlreadyExistException("La canasta que desea crear ya existe");
        }

        validarDatosCanasta(descripcion,cantidadPersonas,elementos,comedor);
        Canasta canasta = new Canasta();
        canasta.setDescripcion(descripcion);
        canasta.setCantidadDePersonas(cantidadPersonas);
        canasta.setElementos(elementos);
        canasta.setComedor(comedor);
        canasta.setAlta(true);
        return canastaRepository.save(canasta);
    }

    @Transactional
    public Canasta modificarCanasta(Long id,String descripcion, Integer cantidadPersonas,
                                List<Elemento> elementos, Comedor comedor) throws Exception {

        Canasta canasta = canastaRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("La canasta que desea modificar no existe"));
        validarDatosCanasta(descripcion,cantidadPersonas,elementos,comedor);
        canasta.setDescripcion(descripcion);
        canasta.setCantidadDePersonas(cantidadPersonas);
        canasta.setElementos(elementos);
        canasta.setComedor(comedor);
        canasta.setAlta(true);
        return canastaRepository.save(canasta);
    }

    public void validarDatosCanasta(String descripcion, Integer cantidadPersonas,
                                 List<Elemento> elementos, Comedor comedor) throws Exception {
        if(descripcion == null || descripcion.trim().isEmpty()){
            throw new Exception("La descripción del producto es obligatorio");
        }
        if(cantidadPersonas == null || cantidadPersonas<0){
            throw new Exception("La cantidad de personas indicada es inválida");
        }
        if(elementos == null || elementos.isEmpty()){
            throw new Exception("Debe agregar al menos un producto a la canasta");
        }
        if(comedor == null){
            throw new Exception("El comedor es obligatorio");
        }
    }

    @Transactional(readOnly = true)
    public List<Canasta> listarCanastas(){
        return canastaRepository.findAll();
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
        canasta.getElementos().forEach(e -> elementoServicio.deshabilitarElemento(e.getId()));
        canastaRepository.deleteById(id);
    }

}
