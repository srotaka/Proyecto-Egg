package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Provincia;
import grupo7.egg.nutrividas.repositorios.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaServicio {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Transactional(readOnly = true)
    public List<Provincia> obtenerProvincias(){
        return provinciaRepository.obtenerProvincias();
    }
}
