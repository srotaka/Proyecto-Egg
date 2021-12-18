package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.*;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class NutricionistaServicio {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Autowired
    private CredencialServicio credencialServicio;

    @Autowired
    private RolServicio rolServicio;

    private final String ROL = "NUTRICIONISTA";

    @Transactional
    public Nutricionista crearNutricionista(Long documento, String nombre, String apellido, Long matricula, LocalDate nacimiento, Long telefono, String mail, String username, String password){

        Nutricionista nutricionista = new Nutricionista();
        if(!(nutricionistaRepository.findByMatricula(matricula).isEmpty()) ||
                !(nutricionistaRepository.findByDocumento(documento).isEmpty())){
            throw new FieldAlreadyExistException("Ya existe un nutricionista registrado con la misma matrícula o documento");
        }
        Validations.validDateBirth(nacimiento);
        nutricionista.setDocumento(documento);
        nutricionista.setNombre(Validations.formatNames(nombre));
        nutricionista.setApellido(Validations.formatNames(apellido));
        nutricionista.setFechaNacimiento(nacimiento);
        nutricionista.setMatricula(matricula);
        nutricionista.setTelefono(telefono);
        nutricionista.setAlta(true);

        List<Rol> roles = new ArrayList<>();
        roles.add(rolServicio.buscarPorNombre(ROL));
        Credencial credencial = credencialServicio.crear(username,mail,password,roles);
        nutricionista.setCredencial(credencial);
        return nutricionistaRepository.save(nutricionista);
    }

    @Transactional
    public Nutricionista modificarNutricionista(Nutricionista dto){
        Nutricionista nutricionista = nutricionistaRepository.findById(dto.getId()).orElseThrow(()->
                new NoSuchElementException("El nutricionista que desea editar no existe"));

        if(!(nutricionistaRepository.findByMatricula(dto.getMatricula()).isEmpty()) ||
                !(nutricionistaRepository.findByDocumento(dto.getDocumento()).isEmpty())){
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

    @Transactional(readOnly = true)
    public Nutricionista buscarPorId(Long id){
        return nutricionistaRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló ningún nutricionista con el id '"+id+"'"));
    }

    @Transactional
    public Nutricionista buscarNutricionistaPorCredencial(Long id){
        if(nutricionistaRepository.buscarNutricionistaPorCredencial(id) != null){
            return nutricionistaRepository.buscarNutricionistaPorCredencial(id);
        }else{
            return null;
        }
    }

}
