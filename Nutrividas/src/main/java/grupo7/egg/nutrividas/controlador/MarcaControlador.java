package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Marca;
import grupo7.egg.nutrividas.servicios.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/marcas")
public class MarcaControlador {

    @Autowired
    private MarcaServicio marcaServicio;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Marca crearMarca(@RequestBody @Valid Marca marca){
        return marcaServicio.crearMarca(marca);
    }

    @PostMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Marca editarMarca(@PathVariable Long id, @RequestBody @Valid Marca marca){
        return marcaServicio.crearMarca(marca);
    }
}
