package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Canasta;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/canasta")
public class CanastaControlador {

    @Autowired
    private ElementoServicio elementoServicio;

    @Autowired
    private CanastaServicio canastaServicio;

    @Autowired
    private ComedorServicio comedorServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private ProductoServicio productoServicio;


    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView("crearCanasta2");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("canasta", flashMap.get("canasta"));
        } else {
            mav.addObject("canasta", new Canasta());
        }

        mav.addObject("comedores", comedorServicio.listarComedores());
        mav.addObject("elementos", elementoServicio.obtenerElemntosSesion(session.getAttribute("emailSession").toString()));
        mav.addObject("titulo", "Crear Canasta");
        mav.addObject("accion", "guardar");
        return mav;
    }
    protected Map<Producto,Integer> getMapElemento(){
        Map<Producto,Integer> productosElementos = new HashMap<>();
        List<Producto> productos = productoServicio.listarProductos();
        for (Producto p: productos) {
            productosElementos.put(p,1);
        }
        return productosElementos;
    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@Valid @ModelAttribute Canasta canasta, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.addObject("comedores", comedorServicio.listarComedores());
            mav.addObject("productos", getMapElemento());
            mav.addObject("titulo", "Crear Canasta");
            mav.addObject("accion", "guardar");
            mav.setViewName("crearCanasta2");
            return mav;
        }

        try {
            Canasta canastaCreada = canastaServicio.crearCanasta(canasta.getDescripcion(), canasta.getCantidadDePersonas(), canasta.getElementos(), canasta.getComedor());

            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
            mav.setViewName("redirect:/canasta");
        } catch (Exception e) {
            attributes.addFlashAttribute("canasta", canasta);
            attributes.addFlashAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            mav.setViewName("redirect:/canasta/crear");
        }

        return mav;
    }
/*
    @PostMapping("/guardar/2")
    public ModelAndView guarda2(@RequestParam RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView();


        try {
            canastaServicio.crearCanasta(canasta.getDescripcion(), canasta.getCantidadDePersonas(), canasta.getElementos(), canasta.getComedor());
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
            mav.setViewName("redirect:/canasta");
        } catch (Exception e) {
            attributes.addFlashAttribute("canasta", canasta);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/canasta/crear");
        }

        return mav;
    }*/

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes attributes){
        ModelAndView mav = new ModelAndView("crearCanastaFlor");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        try {
            if (flashMap != null) {
                mav.addObject("error", flashMap.get("error"));
                mav.addObject("canasta", flashMap.get("canasta"));
            } else {
                mav.addObject("canasta", canastaServicio.buscarPorId(id));
            }

            mav.addObject("comedores", comedorServicio.listarComedores());
            mav.addObject("productos", productoServicio.listarProductos());
            mav.addObject("titulo", "Editar Canasta");
            mav.addObject("accion", "modificar");

        }catch (Exception e){
            attributes.addFlashAttribute("error",e.getMessage());
            mav.setViewName("redirect:/canasta");
        }
        return mav;
    }

    @PostMapping("/modificar")
    public ModelAndView modificar(@Valid @ModelAttribute Canasta canasta, BindingResult result, RedirectAttributes attributes) {

        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.addObject("canasta",canastaServicio.buscarPorId(canasta.getId()));
            mav.addObject("comedores", comedorServicio.listarComedores());
            mav.addObject("productos", productoServicio.listarProductos());
            mav.addObject("titulo", "Crear Canasta");
            mav.addObject("accion", "guardar");
            mav.setViewName("crearCanastaFlor");
        }
        try {
            canastaServicio.crearCanasta(canasta.getDescripcion(), canasta.getCantidadDePersonas(), canasta.getElementos(), canasta.getComedor());
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
            mav.setViewName("redirect:/canasta");
        } catch (Exception e) {
            attributes.addFlashAttribute("canasta", canasta);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/canasta/editar"+canasta.getId());
        }

        return mav;
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
