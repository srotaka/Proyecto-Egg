
package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Tarjeta;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.enums.MarcaTarjeta;
import grupo7.egg.nutrividas.enums.TipoTarjeta;
import grupo7.egg.nutrividas.servicios.TarjetaServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/tarjeta")
@PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
public class TarjetaControlador {
    
    @Autowired
    private TarjetaServicio tarjetaServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;    
   
    
    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("tarjeta-formulario");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(flashMap != null){
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }else {
            mav.addObject("tarjeta", new Tarjeta());
        }         
       
        mav.addObject("title", "crear tarjeta");
        mav.addObject("action", "guardar");
        
        return mav;
    }
    
    
   /* @PostMapping("/modificar")
    public RedirectView modificar(@ModelAttribute Tarjeta tarjeta, RedirectAttributes attributes){
        RedirectView redirectview = new RedirectView("/tarjeta");
        
        try{
            tarjetaServicio.modificarTarjeta(tarjeta.getId(), tarjeta.getNombre(),  tarjeta.getId(), tarjeta.getCodigoSeguridad(),
                    tarjeta.getTipoTarjeta(), tarjeta.getMarcaTarjeta(), tarjeta.getFechaVencimiento(), tarjeta.getNumeroTarjeta());
                    attributes.addFlashAttribute("exito", "La actualizacion se realizo con exito");
        }catch(Exception e){
            attributes.addFlashAttribute("tarjeta", tarjeta);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectview.setUrl("/tarjeta/editar/" + tarjeta.getId()); //CONFIRMAR URL            
        }
        
        return redirectview;
    }*/
    
    
   /* @PostMapping("/guardar")
    public RedirectView guardar(@ModelAttribute Tarjeta tarjeta, RedirectAttributes attributes){
        RedirectView redirectView = new RedirectView("/tarjeta");
        
        try{
            tarjetaServicio.crearTarjeta(tarjeta.getNombre(), tarjeta.getApellido(), tarjeta.getId(), tarjeta.getCodigoSeguridad(), tarjeta.getTipoTarjeta(),
                    tarjeta.getFechaVencimiento(), tarjeta.getNumeroTarjeta());
            attributes.addFlashAttribute("exito", "La creacion se realizo con exito");
        }catch(Exception e){
            attributes.addFlashAttribute("tarjeta", tarjeta);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/tarjeta/crear/");
        }
        
        return redirectView;
    }*/
    
    
    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable Long id, RedirectAttributes attributes){
        try {
            tarjetaServicio.habilitarTarjeta(id);
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/tarjeta");
    }
    
    @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitar(@PathVariable Long id, RedirectAttributes attributes){
        try {
            tarjetaServicio.deshabilitarTarjeta(id);
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return new RedirectView("/tarjeta");
    }
    
}
