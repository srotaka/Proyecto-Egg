
package grupo7.egg.nutrividas.controlador;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorControlador implements ErrorController{
    
    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView errores(HttpServletResponse response){
        ModelAndView mav = new ModelAndView("error");
        String mensaje = "";
        int codigo = response.getStatus();
        
        switch(codigo){
            case 403:
            case 401:
                mensaje = "No tiene permiso para acceder a este servidor";
                break;
            case 404:
                mensaje = "Recurso requerido no disponible";
                break;
            case 500:
                mensaje = "Error Interno";
                break;
            default:
                mensaje = "Error Inesperado";
                break;
        }
        
        mav.addObject("codigo", codigo);
        mav.addObject("mensaje", mensaje);        
        
        return mav;
    }
    
}
