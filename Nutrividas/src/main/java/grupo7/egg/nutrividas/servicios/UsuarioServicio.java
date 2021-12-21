package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.enums.MarcaTarjeta;
import grupo7.egg.nutrividas.enums.TipoTarjeta;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.TarjetaRepository;
import grupo7.egg.nutrividas.repositorios.UsuarioRepository;
import grupo7.egg.nutrividas.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TarjetaServicio tarjetaServicio;

    @Autowired
    private CredencialServicio credencialServicio;

    @Autowired
    private RolServicio rolServicio;

    private final String ROL = "USUARIO";
    @Transactional
    public Usuario crearUsuario(Long dni, String nombre, String apellido, String mail, Long telefono, String username, String password){

        if(usuarioRepository.findByDni(dni).isPresent()){
            throw new FieldAlreadyExistException("Ya existe un usuario registrado con el DNI '"+dni+"' ");
        }

        Usuario usuario = new Usuario();
        usuario.setDni(dni);
        usuario.setNombre(Validations.formatNames(nombre));
        usuario.setApellido(Validations.formatNames(apellido));
        usuario.setTelefono(telefono);
        usuario.setAlta(true);

        List<Rol> roles = new ArrayList<>();
        roles.add(rolServicio.buscarPorNombre(ROL));
        Credencial credencial = credencialServicio.crear(username,mail,password,roles);
        usuario.setCredencial(credencial);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario modificarUsuario(Long id, Long dni, String nombre, String apellido, Long telefono){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No existe un usuario vinculado al id '"+id+"'"));

        usuario.setDni(dni);
        usuario.setNombre(Validations.formatNames(nombre));
        usuario.setApellido(Validations.formatNames(apellido));
        usuario.setTelefono(telefono);

        return usuarioRepository.save(usuario);
    }

    public Integer obtenerLargoDeNumero(Long dni) {
        Integer digitos = 0;
        while (dni != 0) {
            dni = dni / 10;
            digitos++;
        }
        return digitos;
    }

    @Transactional
    public void agregarTarjetaAUsuario(Long idUsuario, String nombre, String apellido, Long numeroTarjeta,
                                       Integer codigoSeguridad, TipoTarjeta tipoTarjeta,
                                       LocalDate fechaVencimiento) throws Exception{
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List<Tarjeta> tarjetas = tarjetaRepository.obtenerTarjetasDeUsuario(usuario);
        Tarjeta tarjeta = tarjetaServicio.crearTarjeta(nombre, numeroTarjeta, codigoSeguridad, fechaVencimiento, usuario);

        tarjetas.add(tarjeta); //agrego la tarjeta creada a la lista de tarjetas
        usuario.setTarjetas(tarjetas); //seteo las tarjetas al usuario
    }

    @Transactional
    public void eliminarTarjetaDeUsuario(Long idTarjeta, Long idUsuario) throws Exception{
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List <Tarjeta> tarjetas = usuario.getTarjetas();
        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta).get();
        tarjetas.remove(tarjeta);
        usuario.setTarjetas(tarjetas);
        usuarioRepository.save(usuario);
        tarjetaServicio.eliminarTarjeta(idTarjeta);
    }

    @Transactional
    public void deshabilitarTarjetaDeUsuario(Long idTarjeta, Long idUsuario) throws Exception{
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List <Tarjeta> tarjetas = usuario.getTarjetas();
        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta).get();
        tarjetas.remove(tarjeta);
        tarjeta = tarjetaServicio.deshabilitarTarjeta(idTarjeta);
        tarjetas.add(tarjeta);
        usuario.setTarjetas(tarjetas);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void habilitarTarjetaDeUsuario(Long idTarjeta, Long idUsuario) throws Exception{
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List <Tarjeta> tarjetas = usuario.getTarjetas();
        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta).get();
        tarjetas.remove(tarjeta);
        tarjeta = tarjetaServicio.habilitarTarjeta(idTarjeta);
        tarjetas.add(tarjeta);
        usuario.setTarjetas(tarjetas);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public List<Tarjeta> buscarTarjetasDeUsuario(String username){
        Credencial credencial = credencialServicio.buscarCredencialPorUsername(username);
        Usuario usuario = usuarioRepository.buscarUsuarioPorCredencial(credencial.getId());

        return tarjetaServicio.obtenerTarjetasDeUsuario(usuario.getId());
    }

    @Transactional
    public void deshabilitarUsuario(Long idUsuario){
        Usuario usuario =usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new NoSuchElementException("No se halló un usuario con el id " + idUsuario));
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void habilitarUsuario(Long idUsuario){
        usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new NoSuchElementException("No se halló un usuario con el id " + idUsuario));
        usuarioRepository.habilitarUsuario(idUsuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return  usuarioRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("No existe un usuario vinculado al id '"+id+"'"));
    }

    @Transactional
    public void crearFoto(Foto foto, Long id){
        if(foto == null){
            throw new FieldInvalidException("La imagen no puede ser nula");
        }
        usuarioRepository.findById(id).orElseThrow(
                ()->new NoSuchElementException("No se halló un usuario con el id '"+id+"'"));

        usuarioRepository.actualizarFoto(foto,id);
    }

    @Transactional
    public Usuario buscarPorMail(String mail){
        return usuarioRepository.findByCredencial_mail(mail).orElseThrow(
                ()->new NoSuchElementException("No se halló un usuario con el email '"+mail+"'"));
    }

    @Transactional
    public Usuario buscarPorUsername(String username){
        return usuarioRepository.findByCredencial_username(username).orElseThrow(
                ()->new NoSuchElementException("No se halló un usuario con el username '"+username+"'"));
    }

    @Transactional
    public Usuario buscarUsuarioPorCredencial(Long id){
        if(usuarioRepository.buscarUsuarioPorCredencial(id) != null){
            return usuarioRepository.buscarUsuarioPorCredencial(id);
        }else{
            return null;
        }
    }
}
