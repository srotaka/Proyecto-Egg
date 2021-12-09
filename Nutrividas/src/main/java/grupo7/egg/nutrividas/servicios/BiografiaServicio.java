package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Biografia;
import grupo7.egg.nutrividas.repositorios.BiografiaRepository;
import grupo7.egg.nutrividas.repositorios.ComedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class BiografiaServicio {

    @Autowired
    BiografiaRepository biografiaRepository;

    @Transactional
    public Biografia crearBiografia(String detalle){
        Biografia biografia = new Biografia();
        biografia.setDescripcion(detalle);
        return biografiaRepository.save(biografia);
    }

    @Transactional
    public Biografia editarBiografia(Long id,String detalle){
        Biografia biografia = biografiaRepository.findById(id).orElseThrow(()-> new NoSuchElementException(""));
        biografia.setDescripcion(detalle);
        return biografiaRepository.save(biografia);
    }
}
