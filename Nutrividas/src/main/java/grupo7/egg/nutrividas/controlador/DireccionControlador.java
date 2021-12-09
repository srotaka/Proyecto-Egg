package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.servicios.DireccionSevicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("direccion")
public class DireccionControlador {

    @Autowired
    private  DireccionSevicio direccionSevicio;

    @GetMapping("/provincias")
    public Map<Integer,String> listarProvincias(){
        return direccionSevicio.listarProvincias();
    }

    @GetMapping(value = "/municipios",params = "provincia")
    public List<String> listarMunicipios(@RequestParam("provincia") Integer provincia){
        return direccionSevicio.listarMunicipios(provincia);
    }

    @GetMapping(value = "/localidades",params = "provincia")
    public List<String> listarLocalidades(@RequestParam("provincia") Integer provincia){
        return direccionSevicio.listarLocalidades(provincia);
    }
}
