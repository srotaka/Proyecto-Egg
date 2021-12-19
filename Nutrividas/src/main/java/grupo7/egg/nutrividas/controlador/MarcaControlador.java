package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Marca;
import grupo7.egg.nutrividas.servicios.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaControlador {

    @Autowired
    private MarcaServicio marcaServicio;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Marca crearMarca(@RequestBody @Valid Marca marca){
        return marcaServicio.crearMarca(marca);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void editarMarca(@PathVariable Long id, @RequestBody @Valid Marca marca){
       marcaServicio.editarMarca(id,marca);
    }

    @GetMapping(value = "/exists",params = {"nombre"})
    public boolean existsByNombre(@RequestParam("nombre")String nombre){
        return marcaServicio.existsByNombre(nombre);
    }

    @GetMapping(value = "")
    public List<Marca> verTodas(){
        return marcaServicio.listarMarcas();
    }
}
