package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Rol;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.NoSuchElementException;
import grupo7.egg.nutrividas.repositorios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServicio {

    @Autowired
    private RolRepository rolRepository;

    @Transactional
    public Rol crear(String nombre){
        if(rolRepository.findByNombre(nombre).isPresent()){
            throw new FieldAlreadyExistException("El rol '"+nombre+"' ya existe");
        }
        Rol rol = new Rol();
        rol.setNombre(nombre);
        return rolRepository.save(rol);
    }

    @Transactional
    public Rol editar(Integer id,String nombre){
        if(rolRepository.findByNombre(nombre).isPresent()){
            throw new FieldAlreadyExistException("El rol '"+nombre+"' ya existe");
        }
        Rol rol = rolRepository.findById(id).orElseThrow(() ->new NoSuchElementException("No existe un rol asociado con el id '"+id+"' "));
        rol.setNombre(nombre);
        return rolRepository.save(rol);
    }

    @Transactional(readOnly = true)
    public Rol buscarPorId(Integer id){
        return rolRepository.findById(id).orElseThrow(() ->new NoSuchElementException("No existe un rol asociado con el id '"+id+"' "));
    }

    @Transactional(readOnly = true)
    public Rol buscarPorNombre(String nombre){
        return rolRepository.findByNombre(nombre).orElseThrow(() ->new NoSuchElementException("No existe un rol asociado con el nombre '"+nombre+"' "));
    }

    @Transactional(readOnly = true)
    public List<Rol> buscarTodos(){
        return rolRepository.findAll();
    }

}
