
package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Tarjeta;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("usuario")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("usuario-formulario");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(flashMap != null){
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("usuario", flashMap.get("usuario"));
        }else{
            mav.addObject("usuario", new Usuario());
        }    
        mav.addObject("title", "crear usuario");
        mav.addObject("action", "guardar");
        
        return mav;
    }
    
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id")Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("usuario-formulario");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(flashMap != null){
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("usuario", flashMap.get("usuario"));
        }else{
            mav.addObject("usuario", new Usuario());
        }    
        mav.addObject("title", "editar usuario");
        mav.addObject("action", "guardar");
        
        return mav;
    }
    
    
    @PostMapping("/modificar")
    public RedirectView modificar(@ModelAttribute Usuario usuario, RedirectAttributes attributes){
        RedirectView redirectview = new RedirectView("/usuario");
        
        try{
            usuarioServicio.modificarUsuario(usuario.getId(), usuario.getDni(), usuario.getNombre(),
                    usuario.getApellido(), usuario.getMail(), usuario.getTelefono());
            attributes.addFlashAttribute("exito", "La actualizacion se realizo con exito");
        }catch(Exception e){
            attributes.addFlashAttribute("usuario", usuario);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectview.setUrl("/usuario/editar/" + usuario.getId());        
        }
        
        return redirectview;
    }
    
    
     @PostMapping("/guardar")
    public RedirectView guardar(@ModelAttribute Usuario usuario, RedirectAttributes attributes){
        RedirectView redirectView = new RedirectView("/usuario");
        
        try{
            usuarioServicio.crearUsuario(usuario.getDni(), usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getTelefono());
            attributes.addFlashAttribute("exito", "La creacion se realizo con exito");
        }catch(Exception e){
            attributes.addFlashAttribute("usuario", usuario);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/usuario/crear/");
        }
        
        return redirectView;
    }
    
    
    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable Long id, RedirectAttributes attributes){
        try {
            usuarioServicio.habilitarUsuario(id);
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/usuario");        
    }
    
    
    @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitar(@PathVariable Long id, RedirectAttributes attributes){
        try {
            usuarioServicio.deshabilitarUsuario(id);
        } catch (Exception e) {
           attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/usuario");
    }
    
}
