package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Persona;
import grupo7.egg.nutrividas.enums.CategoriaIMC;
import grupo7.egg.nutrividas.enums.Sexo;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.ComedorRepository;
import grupo7.egg.nutrividas.repositorios.PersonaRepository;
import grupo7.egg.nutrividas.util.Validations;
import grupo7.egg.nutrividas.util.paginacion.Paged;
import grupo7.egg.nutrividas.util.paginacion.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
     private ComedorRepository comedorRepository;

    @Transactional
    public Persona crearPersona(String nombre, String apellido, Long documento,LocalDate fechaNacimiento,Double peso,
                                Double altura, Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                Boolean aptoDiabeticos, Sexo sexo, Long idComedor){


        if (personaRepository.existsByDocumento(documento)){
            throw new FieldAlreadyExistException("Ya existe una persona registrada con el documento '"+documento+"'");
        }

        validarDatos(nombre,apellido,documento,fechaNacimiento,peso,altura,sexo);
        Persona persona = new Persona();
        persona.setNombre(Validations.formatNames(nombre));
        persona.setApellido(Validations.formatNames(apellido));
        persona.setDocumento(documento);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setPeso(peso);
        persona.setAltura(altura);
        persona.setIntoleranteLactosa(aptoIntoleranteLactosa);
        persona.setHipertenso(aptoHipertenso);
        persona.setDiabetico(aptoDiabeticos);
        persona.setCeliaco(aptoCeliaco);
        persona.setEdad(calcularEdad(fechaNacimiento));

        Double imc = calcularIMC(peso,altura);
        persona.setIMC(imc);
        if(imc<18.5){
            persona.setCategoriaIMC(CategoriaIMC.BAJO);
        }else if(imc<24.9){
            persona.setCategoriaIMC(CategoriaIMC.NORMAL);
        }else if(imc<29.9){
            persona.setCategoriaIMC(CategoriaIMC.SOBREPESO);
        }else{
            persona.setCategoriaIMC(CategoriaIMC.OBESIDAD);
        }

        persona.setSexo(sexo);
        persona.setComedor(comedorRepository.findById(idComedor).orElseThrow(
                () ->new NoSuchElementException("No existe un comedor asociado con el id '"+idComedor+"' ")));

        return personaRepository.save(persona);
    }

    @Transactional
    public Persona modificarPersona(Long id, String nombre, String apellido,Long documento,LocalDate fechaNacimiento,Double peso,
                                Double altura, Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                Boolean aptoDiabeticos, Sexo sexo, Long idComedor){

        validarDatos(nombre,apellido,documento,fechaNacimiento,peso,altura,sexo);
        Persona persona = personaRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("La persona que desea modificar no existe"));

        if (personaRepository.existsByDocumento(documento) &&
        personaRepository.findByDocumento(documento).get().getId() != id){
            throw new FieldAlreadyExistException("Ya existe una persona registrada con el documento '"+documento+"'");
        }

        persona.setNombre(Validations.formatNames(nombre));
        persona.setApellido(Validations.formatNames(apellido));
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setPeso(peso);
        persona.setAltura(altura);
        persona.setIntoleranteLactosa(aptoIntoleranteLactosa);
        persona.setHipertenso(aptoHipertenso);
        persona.setDiabetico(aptoDiabeticos);
        persona.setCeliaco(aptoCeliaco);
        persona.setEdad(calcularEdad(fechaNacimiento));
        Double imc = calcularIMC(peso,altura);
        persona.setIMC(imc);
        if(imc<18.5){
            persona.setCategoriaIMC(CategoriaIMC.BAJO);
        }else if(imc<24.9){
            persona.setCategoriaIMC(CategoriaIMC.NORMAL);
        }else if(imc<29.9){
            persona.setCategoriaIMC(CategoriaIMC.SOBREPESO);
        }else{
            persona.setCategoriaIMC(CategoriaIMC.OBESIDAD);
        }
        persona.setSexo(sexo);
        persona.setComedor(comedorRepository.findById(idComedor).orElseThrow(
                () ->new NoSuchElementException("No existe un comedor asociado con el id '"+idComedor+"' ")));

        return personaRepository.save(persona);
    }

    public void validarDatos(String nombre,String apellido,Long documento,LocalDate fechaNacimiento, Double peso,
                             Double altura, Sexo sexo) {

        if(nombre == null || nombre.trim().isEmpty()){
            throw new FieldInvalidException("El nombre es obligatorio");
        }
        if(apellido == null || apellido.trim().isEmpty()){
            throw new FieldInvalidException("El apellido es obligatorio");
        }
        Validations.validDocument(documento);
        if(fechaNacimiento == null){
            throw new FieldInvalidException("La fecha es obligatoria");
        }
        Validations.validDateBirth(fechaNacimiento);
        if(peso<=0){
            throw new FieldInvalidException("El peso ingresado es inválido");
        }
        if(altura<=0){
            throw new FieldInvalidException("La altura ingresada es inválida");
        }
        if(sexo==null){
            throw new FieldInvalidException("El género no puede ser nulo");
        }
    }

    public Double calcularIMC(Double peso, Double altura){
        return peso/(Math.pow(altura/100,2));
    }

    public Integer calcularEdad(LocalDate fechaNacimiento){

        LocalDate fechaNac = fechaNacimiento;
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac, ahora);
        return periodo.getYears();
    }

    @Transactional
    public void habilitarPersona(Long id) {
        personaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No se halló una persona con el id "+id));
        personaRepository.habilitarPersona(id);
    }

    @Transactional
    public void deshabilitarPersona(Long id){
        personaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No se halló una persona con el id "+id));
        personaRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Paged<Persona> listarPersonasPorComedor(Long idComedor,int page, int size, Sort order){
        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Persona> personaPage = personaRepository.findByComedor_id(idComedor,request);
        return new Paged(personaPage, Paging.of(personaPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Persona> listarPersonasPorPatologia(String patologia,Boolean apto,Long idComedor,int page, int size, Sort order){
        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Persona> personaPage = null;

        switch (patologia){
            case "celiacos":
                personaPage = personaRepository.findByCeliacoAndComedor_id(apto,idComedor,request);
                break;
            case  "diabeticos":
                personaPage = personaRepository.findByDiabeticoAndComedor_id(apto,idComedor,request);
                break;
            case "hipertensos":
                personaPage = personaRepository.findByHipertensoAndComedor_id(apto,idComedor,request);
                break;
            case "intolerantesLactosa":
                personaPage = personaRepository.findByIntoleranteLactosaAndComedor_id(apto,idComedor,request);
                break;
        }
        return new Paged(personaPage, Paging.of(personaPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Persona> listarPersonasPorIMC(CategoriaIMC categoriaIMC,Long comedorId, int page, int size, Sort order){
        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Persona> personaPage = personaRepository.findByCategoriaIMCAndComedor_id(categoriaIMC,comedorId,request);
        return new Paged(personaPage, Paging.of(personaPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public Paged<Persona> listarPorTodosLosCampos(String busqueda, Long idComedor,int page, int size, Sort order){
        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Persona> personaPage = personaRepository.buscarPorTodosCampos(busqueda,idComedor,request);
        return new Paged(personaPage, Paging.of(personaPage.getTotalPages(), page, size));
    }

    @Transactional(readOnly = true)
    public List<Persona> listarTodo(){
        return personaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Persona buscarPorId(Long id){
        return personaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No se halló una persona asociada con el id "+id)
        );
    }

    @Transactional(readOnly = true)
    public Double obtenerPorcentajeIMC(CategoriaIMC categoriaIMC,Long idComedor){
        return personaRepository.porcentajePorCategoriaIMC(categoriaIMC,idComedor);
    }

}
