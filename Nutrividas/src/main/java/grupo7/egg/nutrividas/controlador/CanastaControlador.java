package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.servicios.CanastaServicio;
import grupo7.egg.nutrividas.servicios.ElementoServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/canasta")
public class CanastaControlador {

    @Autowired
    private ElementoServicio elementoServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CanastaServicio canastaServicio;

    @Autowired
    private FotoServicio fotoServicio;


    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("canasta");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("canasta", flashMap.get("canasta"));
        } else {
            mav.addObject("canasta", new Canasta());
        }

        mav.addObject("productos", productoServicio.listarProductos());
        mav.addObject("title", "Crear Canasta");
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@ModelAttribute Canasta canasta, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView("/canasta");

        try {
            canastaServicio.crearCanasta(canasta.getDescripcion(), canasta.getCantidadDePersonas(), canasta.getElementos(), canasta.getComedor());
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("canasta", canasta);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/canasta/crear");
        }

        return redirectView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("canasta");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("canasta", flashMap.get("canasta"));
        } else {
            mav.addObject("canasta", new Canasta());
        }

        mav.addObject("productos", productoServicio.listarProductos());
        mav.addObject("title", "Crear Canasta");
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@ModelAttribute Canasta canasta, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView("/canasta");

        try {
            canastaServicio.crearCanasta(canasta.getDescripcion(), canasta.getCantidadDePersonas(), canasta.getElementos(), canasta.getComedor());
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("canasta", canasta);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/canasta/crear");
        }

        return redirectView;
    }

    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable Long id) {
        canastaServicio.habilitarCanasta(id);
        return new RedirectView("/canasta");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        canastaServicio.deshabilitarCanasta(id);
        return new RedirectView("/canasta");
    }

    @Value("${picture.canastas.location}")
    public String CANASTAS_UPLOADED_FOLDER;

    @PostMapping("/imagen/actualizar")
    public void  uploadImage(@RequestParam("id")Long id, @RequestParam("imagen") MultipartFile multipartFile,
                             UriComponentsBuilder componentsBuilder){

        Canasta canasta = canastaServicio.buscarPorId(id);
        Foto foto;
        if(canasta.getFoto() == null){
            foto = fotoServicio.crearFoto(CANASTAS_UPLOADED_FOLDER,String.valueOf(id),canasta.getDescripcion(),multipartFile);
        }else{
            foto = fotoServicio.actualizarFoto(canasta.getFoto(),CANASTAS_UPLOADED_FOLDER,String.valueOf(id),canasta.getDescripcion(),multipartFile);
        }

        canastaServicio.crearFoto(foto,id);
    }
}
