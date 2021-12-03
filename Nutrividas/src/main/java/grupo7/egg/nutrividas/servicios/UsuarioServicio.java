package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepository usuarioRepository;

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
    }

    public Integer obtenerLargoDeNumero(Long dni) {
        Integer digitos = 0;
        while (dni != 0) {
            dni = dni / 10;
            digitos++;
        }
        return digitos;
    }
}
