package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Credencial;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.exeptions.BadCredentialsException;
import grupo7.egg.nutrividas.mail.MailService;
import grupo7.egg.nutrividas.servicios.CredencialServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.enums.Provincia;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import grupo7.egg.nutrividas.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
public class PrincipalControlador {

    @Autowired
    private ProvinciaServicio provinciaServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private NutricionistaServicio nutricionistaServicio;

    @Autowired
    private ComedorServicio comedorServicio;

    @Autowired
    private MailService mailService;

    @Autowired
    private CredencialServicio credencialServicio;



    @GetMapping("/")
    public ModelAndView inicio(){
        return new ModelAndView("index");
    }
    
    @GetMapping("/politica")
    public ModelAndView politica(){
        return new ModelAndView("politica-privacidad");
    }

    @GetMapping("/condiciones")
    public ModelAndView condiciones(){
        return new ModelAndView("terminos-condiciones");
    }

    @GetMapping("/contacto")
    public ModelAndView contacto(){
        return new ModelAndView("contacto");
    }
    
    @PostMapping("/confirmar")
    public RedirectView confirmacion(@RequestParam("tokenMail")Long tokenMail){
        credencialServicio.habilitarCuenta(tokenMail);
        return new RedirectView("/confirmado");
    }

    @GetMapping("/confirmado")
    public ModelAndView confirmado(){
        return new ModelAndView("confirmacion-mail");
    }
    
    @GetMapping("/pago")
    public ModelAndView pago(){
        return new ModelAndView("pago");
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false)String logout, Principal principal, HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("login");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (error != null) {
            modelAndView.addObject("error", "Usuario o contraseña incorrectos");
        }

        if (logout != null) {
            modelAndView.addObject("logout", "Ha salido correctamente de la plataforma");
             modelAndView.setViewName("redirect:/");
        }
        if(flashMap != null){
            modelAndView.addObject("success", flashMap.get("success"));
        }

        if (principal != null) {
            modelAndView.setViewName("redirect:/ ");
        }


        if(principal!= null && credencialServicio.findByMail(principal.getName()).getHabilitado() == false ){
            System.out.println("Mail: "+principal.getName());
            throw new BadCredentialsException("La cuenta se encuentra inhabilitada");
        }

        return modelAndView;
    }

    @GetMapping(value= "/seleccion")
    public ModelAndView seleccion(@RequestParam(required = false) String error, HttpServletRequest request, Principal principal){
        ModelAndView modelAndView = new ModelAndView("seleccionUsuario");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (error != null) {
            modelAndView.addObject("error", "Usuario o contraseña incorrectos");
        }

        if(flashMap != null){
            modelAndView.addObject("success", flashMap.get("success"));
        }

        if (principal != null) {
            modelAndView.setViewName("redirect:/ ");
        }

        return modelAndView;
    }

    @Value("${picture.users.location}")
    public String USUARIOS_UPLOADED_FOLDER;


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


