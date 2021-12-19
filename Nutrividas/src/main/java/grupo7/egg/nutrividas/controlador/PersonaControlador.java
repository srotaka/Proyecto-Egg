package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Persona;
import grupo7.egg.nutrividas.enums.Sexo;
import grupo7.egg.nutrividas.repositorios.PersonaRepository;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.servicios.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/persona")
@PreAuthorize("hasAnyRole('COMEDOR','ADMIN')")
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ComedorServicio comedorServicio;

    @GetMapping
    public ModelAndView mostrarPersonas(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                        @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") Sort order,
                                        HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("personas");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("personas", personaServicio.listarPersonas(page, size, order));
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
}
