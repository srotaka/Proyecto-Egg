package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Direccion;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.repositorios.DireccionRepository;
import grupo7.egg.nutrividas.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class DireccionSevicio {

    @Autowired
    private DireccionRepository direccionRepository;

    private final String PAIS = "Argentina";

    @Transactional
    public Direccion createDireccion(String calle, Integer numero, Integer codigoPostal, String localidad,String provincia){
        if(direccionRepository.existsByCalleAndNumeroAndLocalidad(calle,numero,localidad)){
            throw new FieldAlreadyExistException("La dirección '"+calle+" "+numero+","+localidad+" ya se encuentra registrada");
        }
        Direccion direccion = new Direccion();
        direccion.setCalle(Validations.formatNames(calle));
        direccion.setNumero(numero);
        direccion.setLocalidad(Validations.formatNames(localidad));
        direccion.setProvincia(Validations.formatNames(provincia));
        direccion.setPais(PAIS);
        direccion.setAlta(true);
        return direccionRepository.save(direccion);
    }

    @Transactional
    public Direccion updateDireccion(Long id,String calle, Integer numero, Integer codigoPostal, String localidad,String provincia){

        Direccion direccion = direccionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("La dirección que desea modificar no existe"));

        if(direccionRepository.existsByCalleAndNumeroAndLocalidad(calle,numero,localidad)
        && direccionRepository.findByCalleAndNumeroAndLocalidad(calle,numero,localidad).get().getId() != id){
            throw new FieldAlreadyExistException("La dirección '"+calle+" "+numero+","+localidad+" ya se encuentra registrada");
        }
        direccion.setCalle(Validations.formatNames(calle));
        direccion.setNumero(numero);
        direccion.setLocalidad(Validations.formatNames(localidad));
        direccion.setProvincia(Validations.formatNames(provincia));
        direccion.setPais(PAIS);
        direccion.setAlta(true);
        return direccionRepository.save(direccion);
    }

    @Transactional
    public void habilitar(Long id){
        Direccion direccion = direccionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("La dirección que desea modificar no existe"));
        direccionRepository.habilitarDireccion(id);
    }

    @Transactional
    public void deshabilitar(Long id){
        Direccion direccion = direccionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("La dirección que desea modificar no existe"));
        direccionRepository.delete(direccion);
    }

    @Transactional(readOnly = true)
    public  boolean existeDireccion(String calle, Integer numero, String localidad){
        return direccionRepository.existsByCalleAndNumeroAndLocalidad(calle,numero,localidad);
    }
}
