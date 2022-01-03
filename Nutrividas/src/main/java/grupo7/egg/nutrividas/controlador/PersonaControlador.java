package grupo7.egg.nutrividas.controlador;

import com.lowagie.text.DocumentException;
import grupo7.egg.nutrividas.entidades.Menu;
import grupo7.egg.nutrividas.entidades.Persona;
import grupo7.egg.nutrividas.enums.CategoriaIMC;
import grupo7.egg.nutrividas.enums.Sexo;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.exeptions.InvalidDataException;
import grupo7.egg.nutrividas.repositorios.PersonaRepository;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.servicios.PersonaServicio;
import grupo7.egg.nutrividas.util.MenuPDFExporter;
import grupo7.egg.nutrividas.util.Validations;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.simple.xhtml.XhtmlForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/persona")
//@PreAuthorize("hasAnyRole('NUTRICIONISTA','ADMIN')")
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ComedorServicio comedorServicio;

    @GetMapping("/comedor/{id}")
    public ModelAndView mostrarPersonasPorComedor(@PathVariable("id")Long idComedor,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                        @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                        HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("personas");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("personas", personaServicio.listarPersonasPorComedor(idComedor,page, size,getSort(order)));
        mav.addObject("porcentajes",obtenerPorcentajesIMC(idComedor));
        mav.addObject("idComedor",idComedor);
        mav.addObject("rutaActual",obtenerRutaActual(""));
        mav.addObject("menu",new Menu());
        return mav;
    }

    @GetMapping(value = "/comedor/{id}",params = {"imc"})
    public ModelAndView mostrarPersonasPorIMC(@PathVariable("id")Long idComedor,
                                              @RequestParam(value = "imc")String imc,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                  @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                                  HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("personas");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("personas", personaServicio.listarPersonasPorIMC(Validations.getCategoriaIMC(imc),idComedor,page, size,getSort(order)));
        mav.addObject("porcentajes",obtenerPorcentajesIMC(idComedor));
        mav.addObject("menu",new Menu());
        mav.addObject("idComedor",idComedor);
        mav.addObject("rutaActual","?imc="+imc+"&");

        return mav;
    }

    @GetMapping(value = "/comedor/{id}",params = {"patologia"})
    public ModelAndView mostrarPersonasPorPatologia(@PathVariable("id")Long idComedor,
                                              @RequestParam(value = "patologia")String patologia,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                              @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                              HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("personas");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("personas", personaServicio.listarPersonasPorPatologia(patologia,true,idComedor,page, size,getSort(order)));
        mav.addObject("porcentajes",obtenerPorcentajesIMC(idComedor));
        mav.addObject("menu",new Menu());
        mav.addObject("idComedor",idComedor);
        mav.addObject("rutaActual","?patologia="+patologia+"&");

        return mav;
    }

    @GetMapping(value = "/comedor/{id}",params = {"busqueda"})
    public ModelAndView mostrarPersonasPorTodoLosCampos(@PathVariable("id")Long idComedor,
                                                    @RequestParam(value = "busqueda")String busqueda,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                    @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                                    HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("personas");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("personas", personaServicio.listarPorTodosLosCampos(busqueda,idComedor,page, size,getSort(order)));
        mav.addObject("porcentajes",obtenerPorcentajesIMC(idComedor));
        mav.addObject("menu",new Menu());
        mav.addObject("idComedor",idComedor);
        mav.addObject("rutaActual","?busqueda="+busqueda+"&");

        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearPersona(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("persona-formulario");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("persona", flashMap.get("persona"));
        } else {
            mav.addObject("persona", new Persona());
        }

        mav.addObject("comedores", comedorServicio.listarComedores());
        mav.addObject("title", "Ingresar Persona");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarPersona(@PathVariable Long id, HttpServletRequest request, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("persona-formulario");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        try {
            if (flashMap != null) {
                mav.addObject("error", flashMap.get("error"));
                mav.addObject("persona", flashMap.get("persona"));
            } else {
                mav.addObject("persona", personaServicio.buscarPorId(id));
            }

            mav.addObject("comedores", comedorServicio.listarComedores());
            mav.addObject("title", "Editar Persona");
            mav.addObject("action", "modificar");
        } catch(Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/persona");
        }

        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardarPersona(@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long documento, @RequestParam LocalDate fechaNacimiento,
                                @RequestParam Double altura, @RequestParam Double peso,
                                @RequestParam Boolean aptoCeliacos, @RequestParam Boolean aptoHipertensos,
                                @RequestParam Boolean aptoDiabeticos, @RequestParam Boolean aptoIntoleranteLactosa,
                                @RequestParam Sexo sexo, @RequestParam Long idComedor, RedirectAttributes attributes){
        RedirectView redirectView = new RedirectView("/persona");


        try {
            personaServicio.crearPersona(nombre, apellido, documento, fechaNacimiento, peso, altura, aptoIntoleranteLactosa, aptoCeliacos, aptoHipertensos, aptoDiabeticos, sexo, idComedor);
            attributes.addFlashAttribute("exito", "La creación ha sido realizada satisfactoriamente");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/persona/crear");
        }

        return redirectView;
    }

    @PostMapping("/modificar")
    public RedirectView modificarPersona(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido,
                                  @RequestParam Long documento, @RequestParam LocalDate fechaNacimiento,
                                  @RequestParam Double altura, @RequestParam Double peso,
                                  @RequestParam Boolean aptoCeliacos, @RequestParam Boolean aptoHipertensos,
                                  @RequestParam Boolean aptoDiabeticos, @RequestParam Boolean aptoIntoleranteLactosa,
                                  @RequestParam Sexo sexo, @RequestParam Long idComedor, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView("/persona");

        try {
            personaServicio.modificarPersona(id, nombre, apellido, documento, fechaNacimiento, peso,
                    altura, aptoIntoleranteLactosa, aptoCeliacos, aptoHipertensos,
                    aptoDiabeticos, sexo, idComedor);
            attributes.addFlashAttribute("exito", "La actualización ha sido realizada satisfactoriamente");
        } catch (Exception e) {
            Persona persona = personaRepository.findById(id).get();
            attributes.addFlashAttribute("persona", persona);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/persona/editar/" + persona.getId());
        }

        return redirectView;
    }

    @PostMapping("/habilitar/{id}")
    public RedirectView habilitarPersona(@PathVariable Long id){
        personaServicio.habilitarPersona(id);
        return new RedirectView("/persona");
    }

    @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitarPersona(@PathVariable Long id){
        personaServicio.deshabilitarPersona(id);
        return new RedirectView("/persona");
    }

    @PostMapping("/export/pdf")
    public RedirectView exportToPDF(@ModelAttribute Menu menu, HttpServletResponse response) throws DocumentException, IOException {

        RedirectView redirectView = new RedirectView("/persona/comedor/9");


        /*response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+menu.getTitulo()+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        MenuPDFExporter exporter = new MenuPDFExporter(menu);
        //exporter.export(response);*/

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+menu.getTitulo()+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        MenuPDFExporter exporter = new MenuPDFExporter(menu);
        exporter.export2(response);

        return redirectView;
    }

    public String obtenerRutaActual(String ruta) {

        String uri = "";

        if(ruta.length() > 29){
            uri = ruta.substring(30);
        }
        String removeParameterPage = "";
        if(!uri.equals("")){
            removeParameterPage = uri.replaceAll("/[&?]page=\\d+/","");
        }

        if(removeParameterPage.contains("?")){
            return removeParameterPage.concat("&");
        }else{
            return removeParameterPage.concat("?");
        }

    }

    public List<Double> obtenerPorcentajesIMC(Long idComedor){
        List<Double> porcentajesIMC = new ArrayList();

        for(CategoriaIMC c : CategoriaIMC.values()){
            porcentajesIMC.add(personaServicio.obtenerPorcentajeIMC(c,idComedor));
        }

        return porcentajesIMC;
    }

    public Sort getSort(String order){
        switch (order){
            case "OrderByNombreASC":
                return Sort.by(Sort.Direction.ASC,"nombre");
            case "OrderByNombreDESC":
                return Sort.by(Sort.Direction.DESC,"nombre");
            case "OrderByApellidooASC":
                return Sort.by(Sort.Direction.ASC,"apellido");
            case "OrderByApellidoDESC":
                return Sort.by(Sort.Direction.DESC,"apellido");
            case "OrderByPesoASC":
                return Sort.by(Sort.Direction.ASC,"peso");
            case "OrderByPesoDESC":
                return Sort.by(Sort.Direction.DESC,"peso");
            default:
                throw new FieldInvalidException("El parámetro de orden ingresado es inválido");
        }
    }
}
