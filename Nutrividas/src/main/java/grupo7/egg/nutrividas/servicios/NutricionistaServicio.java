package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Nutricionista;
import grupo7.egg.nutrividas.repositorios.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutricionistaServicio {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public Nutricionista crearNutricionista(String nombre,String apellido,Long matricula,Long telefono,String mail){
        return null;
    }

    public void validarDatos(String nombre,String apellido,Long matricula,Long telefono,String mail){

    }
}
