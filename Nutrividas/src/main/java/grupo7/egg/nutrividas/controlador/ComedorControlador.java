package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Persona;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.enums.Sexo;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.PersonaRepository;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/comedor")
public class ComedorControlador {

    @Autowired
    private ComedorServicio comedorServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @GetMapping
    public ModelAndView mostrarComedores(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                         @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                         HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("comedores");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("comedores", comedorServicio.listarComedores(page, size, getSort(order)));
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearComedor(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("comedor-formulario");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("comedor", flashMap.get("comedor"));
        } else {
            mav.addObject("comedor", new Comedor());
        }

        mav.addObject("title", "Ingresar Comedor");
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardarComedor(@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long documento, @RequestParam LocalDate fechaNacimiento,
                                       @RequestParam Double altura, @RequestParam Double peso,
                                       @RequestParam Boolean aptoCeliacos, @RequestParam Boolean aptoHipertensos,
                                       @RequestParam Boolean aptoDiabeticos, @RequestParam Boolean aptoIntoleranteLactosa,
                                       @RequestParam Sexo sexo, @RequestParam Long idComedor, RedirectAttributes attributes){
        RedirectView redirectView = new RedirectView("/comedor");

        //modificar parametros
        try {
            //comedorServicio.crearComedor(nombre, apellido, documento, fechaNacimiento, peso, altura, aptoIntoleranteLactosa, aptoCeliacos, aptoHipertensos, aptoDiabeticos, sexo, idComedor);
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/comedor/crear");
        }

        return redirectView;
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

}
