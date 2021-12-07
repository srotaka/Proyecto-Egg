package grupo7.egg.nutrividas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalControlador {

    @GetMapping("/")
    public ModelAndView inicio(){
        return new ModelAndView("index");
    }

}
