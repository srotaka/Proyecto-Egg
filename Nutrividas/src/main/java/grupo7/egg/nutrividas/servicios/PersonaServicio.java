package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Persona;
import grupo7.egg.nutrividas.enums.Sexo;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.ComedorRepository;
import grupo7.egg.nutrividas.repositorios.PersonaRepository;
import grupo7.egg.nutrividas.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;


@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
     private ComedorRepository comedorRepository;

    @Transactional
    public Persona crearPersona(String nombre, String apellido, LocalDate fechaNacimiento,Double peso,
                                Double altura, Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                Boolean aptoDiabeticos, Sexo sexo, Long idComedor) throws Exception {

        validarDatos(nombre,apellido,fechaNacimiento,peso,altura,sexo);
        Persona persona = new Persona();
        persona.setNombre(Validations.formatNames(nombre));
        persona.setApellido(Validations.formatNames(apellido));
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setPeso(peso);
        persona.setAltura(altura);
        persona.setAptoIntoleranteLactosa(aptoIntoleranteLactosa);
        persona.setAptoHipertensos(aptoHipertenso);
        persona.setAptoDiabeticos(aptoDiabeticos);
        persona.setAptoCeliacos(aptoCeliaco);
        persona.setEdad(calcularEdad(fechaNacimiento));
        persona.setIMC(calcularIMC(peso,altura));
        persona.setSexo(sexo);
        persona.setComedor(comedorRepository.buscarComedorPorId(idComedor));

        return personaRepository.save(persona);
    }

    @Transactional
    public Persona modificarPersona(Long id, String nombre, String apellido, LocalDate fechaNacimiento,Double peso,
                                Double altura, Boolean aptoIntoleranteLactosa, Boolean aptoCeliaco, Boolean aptoHipertenso,
                                Boolean aptoDiabeticos, Sexo sexo, Long idComedor) throws Exception {

        validarDatos(nombre,apellido,fechaNacimiento,peso,altura,sexo);
        Persona persona = personaRepository.getById(id);
        persona.setNombre(Validations.formatNames(nombre));
        persona.setApellido(Validations.formatNames(apellido));
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setPeso(peso);
        persona.setAltura(altura);
        persona.setAptoIntoleranteLactosa(aptoIntoleranteLactosa);
        persona.setAptoHipertensos(aptoHipertenso);
        persona.setAptoDiabeticos(aptoDiabeticos);
        persona.setAptoCeliacos(aptoCeliaco);
        persona.setEdad(calcularEdad(fechaNacimiento));
        persona.setIMC(calcularIMC(peso,altura));
        persona.setSexo(sexo);
        persona.setComedor(comedorRepository.buscarComedorPorId(idComedor));

        return personaRepository.save(persona);
    }

    public void validarDatos(String nombre,String apellido, LocalDate fechaNacimiento, Double peso,
                             Double altura, Sexo sexo) throws Exception {

        if(nombre == null || nombre.trim().isEmpty()){
            throw new Exception("El nombre es obligatorio");
        }
        if(apellido == null || apellido.trim().isEmpty()){
            throw new Exception("El apellido es obligatorio");
        }
        if(fechaNacimiento == null){
            throw new Exception("La fecha es obligatoria");
        }
        if(fechaNacimiento.getYear()<LocalDate.now().getYear()-100 || fechaNacimiento.isAfter(LocalDate.now())){
            throw new Exception("La edad de la persona es inválida");
        }

        if(peso<=0){
            throw new Exception("El peso ingresado es inválido");
        }
        if(altura<=0){
            throw new Exception("La altura ingresada es inválida");
        }
        if(sexo!=null){
            throw new Exception("El género no puede ser nulo");
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
    public void habilitarPersona(Long id) throws Exception {
        personaRepository.findById(id).orElseThrow(
                () -> new Exception("No se halló una persona con el id "+id));
        personaRepository.habilitarPersona(id);
    }

    @Transactional
    public void deshabilitarPersona(Long id) throws Exception {
        personaRepository.findById(id).orElseThrow(
                () -> new Exception("No se halló una persona con el id "+id));
        personaRepository.deleteById(id);
    }

    @Transactional
    public void modificarFoto(Long id, Foto foto) throws Exception{
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }

        personaRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló una persona con el id '"+id+"'"));

        personaRepository.actualizarFoto(foto,id);
    }

}
