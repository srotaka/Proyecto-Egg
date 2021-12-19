package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Credencial;
import grupo7.egg.nutrividas.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private NutricionistaServicio nutricionistaServicio;

    @Autowired
    private ComedorServicio comedorServicio;

    @Autowired
    private CredencialServicio credencialServicio;

    @Autowired
    private ProvinciaServicio provinciaServicio;

    @GetMapping(value = "/modificar/{username}")
    public ModelAndView editarPerfil(@PathVariable String username, HttpServletRequest request, RedirectAttributes attributes){

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        ModelAndView mav;
        Credencial credencial = credencialServicio.buscarCredencialPorUsername(username);
        Long id = credencial.getId();

        if(usuarioServicio.buscarUsuarioPorCredencial(id) != null){
            mav = new ModelAndView("editarUsuario");
            mav.addObject("usuario", usuarioServicio.buscarUsuarioPorCredencial(id));
            return modificarUsuario(id, mav, flashMap, attributes);

        }else if(comedorServicio.buscarComedorPorCredencial(id) != null){
            mav = new ModelAndView("editarComedores");
            mav.addObject("comedor", comedorServicio.buscarComedorPorCredencial(id));
            mav.addObject("provincias", provinciaServicio.obtenerProvincias());
            return modificarComedor(id, mav, flashMap, attributes);
        }else{
            mav = new ModelAndView("editarNutricionista");
            mav.addObject("usuario", nutricionistaServicio.buscarNutricionistaPorCredencial(id));
            return modificarNutricionista(id, mav, flashMap, attributes);
        }

    }

    public ModelAndView modificarUsuario(Long id, ModelAndView mav, Map<String, ?> flashMap, RedirectAttributes attributes){
        try {
            if(flashMap != null){
                mav.addObject("error", flashMap.get("error"));
            }else {
                mav.addObject("usuario", usuarioServicio.buscarUsuarioPorCredencial(id));
            }
            mav.addObject("title", "Editar Usuario");
        }catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/");
        }
        return mav;
    }

    public ModelAndView modificarComedor(Long id, ModelAndView mav, Map<String, ?> flashMap, RedirectAttributes attributes){
        try {
            if(flashMap != null){
                mav.addObject("error", flashMap.get("error"));
            }else {
                mav.addObject("comedor", comedorServicio.buscarComedorPorCredencial(id));
            }
            mav.addObject("title", "Editar Comedor");
        }catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/");
        }
        return mav;
    }

    public ModelAndView modificarNutricionista(Long id, ModelAndView mav, Map<String, ?> flashMap, RedirectAttributes attributes){
        try {
            if(flashMap != null){
                mav.addObject("error", flashMap.get("error"));
            }else {
                mav.addObject("nutricionista", nutricionistaServicio.buscarNutricionistaPorCredencial(id));
            }
            mav.addObject("title", "Editar Nutricionista");
        }catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/");
        }
        return mav;
    }
}
