package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.NutricionistaRepository;
import grupo7.egg.nutrividas.util.Validations;
import grupo7.egg.nutrividas.util.paginacion.Paged;
import grupo7.egg.nutrividas.util.paginacion.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;


@Service
public class NutricionistaServicio {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Transactional
    public Nutricionista crearNutricionista(Nutricionista dto){

        if(nutricionistaRepository.existsByMatriculaOrDocumento(dto.getMatricula(),dto.getDocumento())){
            throw new FieldAlreadyExistException("Ya existe un nutricionista registrado con la misma matrícula o documento");
        }

        Validations.validDateBirth(dto.getFechaNacimiento());
        dto.setNombre(Validations.formatNames(dto.getNombre()));
        dto.setApellido(Validations.formatNames(dto.getApellido()));
        dto.setAlta(true);
        return nutricionistaRepository.save(dto);
    }

    @Transactional
    public Nutricionista modificarNutricionista(Nutricionista dto){
        Nutricionista nutricionista = nutricionistaRepository.findById(dto.getId()).orElseThrow(()->
                new NoSuchElementException("El nutricionista que desea editar no existe"));

        if(nutricionistaRepository.existsByMatriculaOrDocumento(dto.getMatricula(),dto.getDocumento()) &&
                nutricionistaRepository.findByMatriculaOrDocumento(dto.getMatricula(),dto.getDocumento()).get().getId() != dto.getId()){
            throw new FieldAlreadyExistException("Ya existe un nutricionista registrado con la misma matrícula o documento");
        }

        Validations.validDateBirth(dto.getFechaNacimiento());
        dto.setNombre(Validations.formatNames(dto.getNombre()));
        dto.setApellido(Validations.formatNames(dto.getApellido()));
        dto.setAlta(true);
        return nutricionistaRepository.save(dto);
    }

    @Transactional
    public void habilitarNutricionista(Long id) {
        nutricionistaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("El nutricionista que desea dar de alta no existe"));
        nutricionistaRepository.habilitarNutricionista(id);
    }

    @Transactional
    public void deshabilitarPersona(Long id){
        nutricionistaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("El nutricionista que desea eliminar no existe"));
        nutricionistaRepository.deleteById(id);
    }

    @Transactional
    public void modificarFoto(Long id, Foto foto){
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }
        nutricionistaRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló ningún nutricionista con el id '"+id+"'"));
        nutricionistaRepository.actualizarFoto(foto,id);
    }

    @Transactional(readOnly = true)
    public Paged<Nutricionista> buscarTodos(int page, int size){
        PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "apellido"));
        Page<Nutricionista> nutricionistasPage = nutricionistaRepository.findAll(request);
        return new Paged(nutricionistasPage, Paging.of(nutricionistasPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Nutricionista> buscarPorTodosCampos(String busqueda,int page, int size){
        PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "apellido"));
        Page<Nutricionista> nutricionistasPage = nutricionistaRepository.buscarPorTodosCampos(busqueda,request);
        return new Paged(nutricionistasPage, Paging.of(nutricionistasPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Nutricionista> buscarPorMatricula(Long matricula,int page, int size){
        PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "apellido"));
        Page<Nutricionista> nutricionistasPage = nutricionistaRepository.findByMatriculaContaining(matricula,request);
        return new Paged(nutricionistasPage, Paging.of(nutricionistasPage.getTotalPages(), page, size));
    }

}
