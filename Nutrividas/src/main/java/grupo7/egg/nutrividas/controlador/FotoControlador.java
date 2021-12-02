package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.servicios.FotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fotos")
public class FotoControlador {

    @Autowired
    private FotoServicio fotoServicio;

    public final String PRODUCTOS_UPLOADED_FOLDER = "src/main/resources/static/img/";

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[]  uploadImage(@PathVariable("id") Long id){
        return fotoServicio.obtenerFotoPorId(id);
    }
}
