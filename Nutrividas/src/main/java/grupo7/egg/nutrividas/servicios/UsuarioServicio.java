package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Tarjeta;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.enums.MarcaTarjeta;
import grupo7.egg.nutrividas.enums.TipoTarjeta;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.TarjetaRepository;
import grupo7.egg.nutrividas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TarjetaRepository tarjetaRepository;

    @Autowired
    TarjetaServicio tarjetaServicio;

    @Transactional
    public Usuario crearUsuario(Long dni, String nombre, String apellido, String mail, Long telefono)throws Exception{
        Usuario usuario = new Usuario();
        validarDatosDeUsuario(dni, nombre, apellido, mail, telefono);
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setTelefono(telefono);
        usuario.setAlta(true);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario modificarUsuario(Long id, Long dni, String nombre, String apellido, String mail, Long telefono)throws Exception{
        Usuario usuario = usuarioRepository.findById(id).get();
        validarDatosDeUsuario(dni, nombre, apellido, mail, telefono);
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setTelefono(telefono);
        usuario.setAlta(true);

        return usuarioRepository.save(usuario);
    }

    public void validarDatosDeUsuario(Long dni, String nombre, String apellido, String mail, Long telefono) throws Exception{

        if(obtenerLargoDeNumero(dni) != 8){
            throw new Exception("El numero de dni es invalido");
        }

        if(dni==null){
            throw new Exception("El numero de dni es obligatorio");
        }

        if(nombre == null || nombre.trim().isEmpty()){
            throw new Exception("El nombre es obligatorio");
        }

        if(apellido == null || apellido.trim().isEmpty()){
            throw new Exception("El apellido es obligatorio");
        }

        if(usuarioRepository.obtenerUsuarioPorNombreYApellido(nombre, apellido) != null){
            throw new Exception("Ya existe un usuario con ese nombre y apellido");
        }

        if(usuarioRepository.obtenerUsuarioPorMail(mail) != null){
            throw new Exception("Ya existe un usuario con ese mail");
        }

        if(telefono == null){
            throw new Exception("El numero de telefono es obligatorio");
        }
        //ver de validar numero de telefono
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
        Tarjeta tarjeta = tarjetaServicio.crearTarjeta(nombre, apellido, numeroTarjeta, codigoSeguridad, tipoTarjeta, fechaVencimiento, idUsuario);

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
    public void deshabilitarUsuario(Long idUsuario) throws Exception{
        usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new Exception("No se halló un usuario con el id " + idUsuario));
        usuarioRepository.deshabilitarUsuario(idUsuario);
    }

    @Transactional
    public void habilitarUsuario(Long idUsuario) throws Exception{
        usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new Exception("No se halló un usuario con el id " + idUsuario));
        usuarioRepository.habilitarUsuario(idUsuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return  usuarioRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("No existe un usuario vincilado al id '"+id+"'"));
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
}
