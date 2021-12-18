package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.enums.Provincia;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.ComedorRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ComedorServicio {

    @Autowired
    private ComedorRepository comedorRepository;

    @Autowired
    private BiografiaServicio biografiaServicio;

    @Autowired
    private DireccionSevicio direccionSevicio;

    @Autowired
    private RolServicio rolServicio;

    @Autowired
    private CredencialServicio credencialServicio;

    private final String ROL = "COMEDOR";

    @Transactional
    public Comedor crearComedor(String nombre, String calle, Integer numero, Integer codigoPostal, String localidad,String provincia,
                                Integer cantidadDePersonas, Long telefono,
                                String username, String mail, String password){
        if(direccionSevicio.existeDireccion(calle, numero, localidad)){
            throw new FieldAlreadyExistException("La dirección '"+calle+" "+numero+","+localidad+" ya se encuentra registrada");
        }

        String detalleBiografia = "";
        validarDatosDeComedor(nombre, cantidadDePersonas,telefono);
        Direccion direccion = direccionSevicio.createDireccion(calle,numero,codigoPostal,localidad,provincia);
        Biografia biografia = biografiaServicio.crearBiografia(detalleBiografia);
        Comedor comedor = new Comedor();
        comedor.setNombre(nombre);
        comedor.setDireccion(direccion);
        comedor.setCantidadDePersonas(cantidadDePersonas);
        comedor.setTelefono(telefono);
        comedor.setBiografia(biografia);
        comedor.setAlta(true);

        List<Rol> roles = new ArrayList<>();
        roles.add(rolServicio.buscarPorNombre(ROL));
        Credencial credencial = credencialServicio.crear(username,mail,password,roles);
        comedor.setCredencial(credencial);
        return comedorRepository.save(comedor);
    }

    @Transactional
    public Comedor modificarComedor(Long id,String nombre, String calle, Integer numero, Integer codigoPostal, String localidad,String provincia,
                                    Integer cantidadDePersonas, Long telefono, String detalleBiografia,
                                    String username, String mail, String password){

        Comedor comedor = comedorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("El comedor que desa modificar no existe"));

        validarDatosDeComedor(nombre,cantidadDePersonas, telefono);
        Direccion direccion = direccionSevicio.updateDireccion(comedor.getDireccion().getId(),calle,numero,codigoPostal,localidad,provincia);
        Biografia biografia = biografiaServicio.editarBiografia(comedor.getBiografia().getId(),detalleBiografia);
        comedor.setNombre(nombre);
        //comedor.setDireccion(direccion);
        comedor.setCantidadDePersonas(cantidadDePersonas);
        comedor.setTelefono(telefono);
        comedor.setAlta(true);
        List<Rol> roles = new ArrayList<>();
        roles.add(rolServicio.buscarPorNombre(ROL));
        Credencial credencial = credencialServicio.crear(username,mail,password,roles);
        comedor.setCredencial(credencial);
        return comedorRepository.save(comedor);
    }

    @Transactional
    public void deshabilitarComedor(Long idComedor){
        Comedor comedor = comedorRepository.findById(idComedor).orElseThrow(
                () -> new NoSuchElementException("No se halló un comedor con el id " + idComedor));
        comedorRepository.delete(comedor);
    }

    @Transactional
    public void habilitarComedor(Long idComedor){
        comedorRepository.findById(idComedor).orElseThrow(
                () -> new NoSuchElementException("No se halló un comedor con el id " + idComedor));
        comedorRepository.habilitarComedor(idComedor);
    }

    public void validarDatosDeComedor(String nombre,Integer cantidadDePersonas, Long telefono){
        if(nombre==null || nombre.trim().isEmpty()){
            throw new FieldInvalidException("El nombre del comedor es obligatorio");
        }
        if(cantidadDePersonas < 0 || cantidadDePersonas == null){
            throw new FieldInvalidException("La cantidad de personas es invalida");
        }
        if(telefono == null){
            throw new FieldInvalidException("El telefono es obligatorio");
        }
        if(!comedorRepository.buscarComedorPorNombre(nombre).isEmpty()){
            throw new FieldInvalidException("Ya existe un comedor con ese nombre");
        }
    }

    @Transactional(readOnly = true)
    public Comedor buscarPorId(Long id){
        return comedorRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("No existe un comedor asociado al id '"+id+"' ")
        );
    }

    @Transactional(readOnly = true)
    public Comedor buscarPorMail(String mail){
        return comedorRepository.findByCredencial_mail(mail).orElseThrow(
                ()-> new NoSuchElementException("No existe un comedor asociado al mail '"+mail+"' ")
        );
    }

    @Transactional(readOnly = true)
    public List<Comedor> listarComedoresSinNutricionista(){
        return comedorRepository.findByNutricionistaIsNull();
    }


    @Transactional(readOnly = true)
    public Paged<Comedor> listarPaginaComedores(int page, int size, Sort order){
        Pageable request = PageRequest.of(page - 1, size, order);
        Page<Comedor> comedorPage = comedorRepository.findAll(request);
        return new Paged(comedorPage, Paging.of(comedorPage.getTotalPages(), page, size));
    }

    @Transactional
    public List<Comedor> listarComedores(){
        return comedorRepository.findAll();
    }

    @Transactional
    public void guardarFoto(Foto foto, Long id){
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }
        comedorRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló un comedor con el id '"+id+"'"));

        comedorRepository.actualizarFoto(foto,id);
    }

    @Transactional
    public Comedor buscarComedorPorCredencial(Long id){
        if(comedorRepository.buscarComedorPorCredencial(id) != null){
            return comedorRepository.buscarComedorPorCredencial(id);
        }else{
            return null;
        }
    }
}
