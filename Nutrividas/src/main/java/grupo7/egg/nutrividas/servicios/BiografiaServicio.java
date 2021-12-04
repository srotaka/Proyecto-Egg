package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Biografia;
import grupo7.egg.nutrividas.repositorios.BiografiaRepository;
import grupo7.egg.nutrividas.repositorios.ComedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiografiaServicio {

    @Autowired
    BiografiaRepository biografiaRepository;

    @Autowired
    ComedorRepository comedorRepository;

    public Biografia crearBiografia(String detalle, Long idComedor){
        Biografia biografia = new Biografia();

        biografia.setDescripcion(detalle);
        biografia.setComedor(comedorRepository.buscarComedorPorId(idComedor));

        return biografiaRepository.save(biografia);
    }
}
