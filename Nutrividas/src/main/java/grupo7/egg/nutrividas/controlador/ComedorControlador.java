package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.enums.Sexo;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.servicios.DireccionSevicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.ProvinciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comedor")
public class ComedorControlador {

    @Autowired
    private ComedorServicio comedorServicio;

    @Autowired
    private ProvinciaServicio provinciaServicio;

    @Autowired
    private FotoServicio fotoServicio;

    private DireccionSevicio direccionSevicio;

    @GetMapping(value ="/{id}")
    public ModelAndView mostrarComedor(@PathVariable("id") Long id,
                                         HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("comedores");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("comedor", comedorServicio.buscarPorId(id));
        return mav;
    }

    @GetMapping
    public ModelAndView mostrarComedores(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "8") int size,
                                         @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                         HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("comedores");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("comedores", comedorServicio.listarPaginaComedores(page, size, getSort(order)));

        return mav;
    }

    @GetMapping(value = "/signup")
    public ModelAndView signupComedor(HttpServletRequest request, Principal principal){

        ModelAndView mav = new ModelAndView("signupComedor");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        mav.addObject("provincias", provinciaServicio.obtenerProvincias());

        if (principal != null) {
            mav.setViewName("redirect:/ ");
        }

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("comedor", flashMap.get("comedor"));
        } else {

            mav.addObject("comedor", new Comedor());
        }

        return mav;
    }

    @PostMapping(value = "/registro")
    public ModelAndView saveComedor(@Valid @ModelAttribute Comedor comedor, BindingResult result, HttpServletRequest request, RedirectAttributes attributes){

        ModelAndView mav = new ModelAndView("signupComedor");
        if (result.hasErrors()) {
            mav.addObject("comedor", comedor);
            mav.setViewName("signupComedor");
            return mav;
        }


        try {
            Comedor comedorCreado = comedorServicio.crearComedor(comedor.getNombre(), comedor.getDireccion().getCalle(), comedor.getDireccion().getNumero(), comedor.getDireccion().getCodigoPostal(), comedor.getDireccion().getLocalidad(), comedor.getDireccion().getProvincia(), comedor.getCantidadDePersonas(), comedor.getTelefono(), comedor.getCredencial().getUsername(), comedor.getCredencial().getMail(), comedor.getCredencial().getPassword());

            request.login(comedor.getCredencial().getMail(), comedor.getCredencial().getPassword());
            mav.setViewName("redirect:/");
        } catch (ServletException e) {
            attributes.addFlashAttribute("error", "Error al realizar auto-login");
        } catch (Exception e) {
            attributes.addFlashAttribute("comedor", comedor);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/signup/comedor");
        }


        return mav;
    }
    
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarComedor(@PathVariable("id") Long id, HttpServletRequest request){

        ModelAndView mav = new ModelAndView("editarComedores");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("comedor", flashMap.get("comedor"));
        } else {
            mav.addObject("provincias", provinciaServicio.obtenerProvincias());
            mav.addObject("comedor", comedorServicio.buscarPorId(id));
        }
        mav.addObject("title", "Editar Comedor");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','COMEDOR')")
    @PostMapping("/modificar")
    public ModelAndView modificar(@Valid @ModelAttribute Comedor comedor, BindingResult result, RedirectAttributes attributes) {

        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.addObject("comedor",comedorServicio.buscarPorId(comedor.getId()));
            mav.addObject("titulo", "Editar Comedor");
            mav.addObject("accion", "guardar");
            mav.setViewName("editarComedores");
        }
        try {
            comedorServicio.modificarComedor(comedor.getId(),comedor.getNombre(),comedor.getDireccion().getCalle(),
                    comedor.getDireccion().getNumero(),comedor.getDireccion().getCodigoPostal(),comedor.getDireccion().getLocalidad(),
                    comedor.getDireccion().getProvincia(),comedor.getCantidadDePersonas(), comedor.getTelefono(), comedor.getBiografia().getDescripcion());
            attributes.addFlashAttribute("exito", "La edicion ha sido realizada satisfactoriamente");
            mav.setViewName("redirect:/comedor");
        } catch (Exception e) {
            attributes.addFlashAttribute("provincias", provinciaServicio.obtenerProvincias());
            attributes.addFlashAttribute("comedor", comedor);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/modificar/{" + comedor.getCredencial().getUsername()+"}");
        }

        return mav;
    }

    public Sort getSort(String order){
        switch (order){
            case "OrderByNombreASC":
                return Sort.by(Sort.Direction.ASC,"nombre");
            case "OrderByNombreDESC":
                return Sort.by(Sort.Direction.DESC,"nombre");
            default:
                throw new FieldInvalidException("El parámetro de orden ingresado es inválido");
        }
    }

    @Value("${picture.comedores.location}")
    public String COMEDORES_UPLOADED_FOLDER;

    @PreAuthorize("hasAnyRole('ADMIN','COMEDOR')")
    @PostMapping("/imagen/actualizar")
    public void  uploadImage(@RequestParam("id")Long id,@RequestParam("imagen") MultipartFile multipartFile,
                             UriComponentsBuilder componentsBuilder){

        Comedor comedor = comedorServicio.buscarPorId(id);
        Foto foto;
        if(comedor.getFoto() == null){
            foto = fotoServicio.crearFoto(COMEDORES_UPLOADED_FOLDER,String.valueOf(id),comedor.getNombre(),multipartFile);
        }else{
            foto = fotoServicio.actualizarFoto(comedor.getFoto(),COMEDORES_UPLOADED_FOLDER,String.valueOf(id),comedor.getNombre(),multipartFile);
        }

        comedorServicio.guardarFoto(foto,comedor.getId());
    }

    @GetMapping(value ="/biografia/{id}")
    public ModelAndView mostrarBiografiaComedor(@PathVariable("id") Long id,
                                       HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("comedores");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("biografia", comedorServicio.buscarPorId(id).getBiografia());
        return mav;
    }

}
