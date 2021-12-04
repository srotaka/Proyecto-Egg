package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Biografia;
import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.enums.Provincia;
import grupo7.egg.nutrividas.repositorios.ComedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComedorServicio {

    @Autowired
    private ComedorRepository comedorRepository;

    @Autowired
    private BiografiaServicio biografiaServicio;

    @Transactional
    public Comedor crearComedor(String nombre, String direccion, String localidad, Provincia provincia,
                                Integer cantidadDePersonas, Long telefono, String detalleBiografia) throws Exception{
        Comedor comedor = new Comedor();
        validarDatosDeComedor(nombre, direccion, localidad, provincia, cantidadDePersonas, telefono);

        comedor.setNombre(nombre);
        comedor.setDireccion(direccion);
        comedor.setLocalidad(localidad);
        comedor.setProvincia(provincia);
        comedor.setCantidadDePersonas(cantidadDePersonas);
        comedor.setTelefono(telefono);
        comedor.setAlta(true);
        return comedorRepository.save(comedor);
    }

    @Transactional
    public Comedor modificarComedor(Long id, String nombre, String direccion, String localidad, Provincia provincia,
                                Integer cantidadDePersonas, Long telefono, String detalleBiografia) throws Exception{
        Comedor comedor = comedorRepository.buscarComedorPorId(id);
        validarDatosDeComedor(nombre, direccion, localidad, provincia, cantidadDePersonas, telefono);

        Biografia biografia = biografiaServicio.crearBiografia(detalleBiografia,id);
        comedor.setNombre(nombre);
        comedor.setDireccion(direccion);
        comedor.setLocalidad(localidad);
        comedor.setProvincia(provincia);
        comedor.setCantidadDePersonas(cantidadDePersonas);
        comedor.setTelefono(telefono);
        comedor.setBiografia(biografia);
        comedor.setAlta(true);
        return comedorRepository.save(comedor);
    }

    @Transactional
    public void deshabilitarComedor(Long idComedor) throws Exception {
        comedorRepository.findById(idComedor).orElseThrow(
                () -> new Exception("No se halló un comedor con el id " + idComedor));
        comedorRepository.deshabilitarComedor(idComedor);
    }

    @Transactional
    public void habilitarComedor(Long idComedor) throws Exception {
        comedorRepository.findById(idComedor).orElseThrow(
                () -> new Exception("No se halló un comedor con el id " + idComedor));
        comedorRepository.habilitarComedor(idComedor);
    }

    public void validarDatosDeComedor(String nombre, String direccion, String localidad, Provincia provincia,
                                      Integer cantidadDePersonas, Long telefono) throws Exception{
        if(nombre==null || nombre.trim().isEmpty()){
            throw new Exception("El nombre del comedor es obligatorio");
        }

        if(direccion==null || direccion.trim().isEmpty()){
            throw new Exception("La direccion del comedor es obligatoria");
        }

        if(localidad==null || localidad.trim().isEmpty()){
            throw new Exception("La localidad del comedor es obligatoria");
        }

        if(provincia==null){
            throw new Exception("La provincia del comedor es obligatoria");
        }

        if(cantidadDePersonas < 0 || cantidadDePersonas == null){
            throw new Exception("La cantidad de personas es invalida");
        }

        if(telefono == null){
            throw new Exception("El telefono es obligatorio");
        }

        if(comedorRepository.buscarComedorPorNombre(nombre) != null){
            throw new Exception("Ya existe un comedor con ese nombre");
        }
    }
}
