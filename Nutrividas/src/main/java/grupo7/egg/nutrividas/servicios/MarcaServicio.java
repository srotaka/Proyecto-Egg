package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Marca;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.repositorios.MarcaRepository;
import grupo7.egg.nutrividas.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MarcaServicio {

    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public Marca crearMarca(Marca marcaDto) {

        if (marcaRepository.existsByNombre(marcaDto.getNombre())) {
            throw new FieldAlreadyExistException("La marca '" + marcaDto.getNombre() + "' ya se encuentra registrada");
        }
        Marca marca = new Marca();
        marca.setNombre(Validations.formatNames(marcaDto.getNombre()));
        marca.setAlta(true);
        return marcaRepository.save(marca);
    }

    @Transactional
    public Marca editarMarca(Long id, String nombre) {

        Marca marca = marcaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No existe una marca registrada con el id '" + id + "'"));

        if (marcaRepository.existsByNombre(nombre) && marcaRepository.findByNombre(nombre).get().getId() != id) {
            throw new FieldAlreadyExistException("La marca '" + nombre + "' ya se encuentra registrada");
        }
        marca.setId(id);
        marca.setNombre(nombre);
        marca.setAlta(true);
        return marcaRepository.save(marca);
    }

    @Transactional
    public void habilitarMarca(Long id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No existe una marca registrada con el id '" + id + "'"));
        marcaRepository.habilitar(id);
    }

    @Transactional
    public void deshabilitarMarca(Long id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No existe una marca registrada con el id '" + id + "'"));
        marcaRepository.delete(marca);
    }

    @Transactional(readOnly = true)
    public List<Marca> listarMarcas(){
        return marcaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Marca buscarPorId(Long id){
        return marcaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No existe una marca registrada con el id '" + id + "'"));
    }
}
