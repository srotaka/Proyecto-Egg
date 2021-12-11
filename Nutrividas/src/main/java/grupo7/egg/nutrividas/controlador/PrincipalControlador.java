package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.servicios.CredencialServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
public class PrincipalControlador {

    @Autowired
    private CredencialServicio credencialServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public ModelAndView inicio(){
        return new ModelAndView("index");
    }

    @GetMapping("/contacto")
    public ModelAndView contacto(){
        return new ModelAndView("contacto");
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
        }
        if(flashMap != null){
            modelAndView.addObject("success", flashMap.get("success"));
        }

        if (principal != null) {
            modelAndView.setViewName("redirect:/ ");
        }

        return modelAndView;
    }

    @GetMapping(value = "/signup/usuario")
    public ModelAndView signup(HttpServletRequest request,Principal principal){
        ModelAndView mav = new ModelAndView("signup");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) {
            mav.setViewName("redirect:/ ");
        }

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("usuario", flashMap.get("usuario"));
        } else {
            Usuario usuario = new Usuario();
            //usuario.setRol(Rol.USER);
            mav.addObject("usuario", usuario);
        }

        return mav;
    }

    @Value("${picture.users.location}")
    public String USUARIOS_UPLOADED_FOLDER;

    @PostMapping(value = "/registro/usuario")
    public ModelAndView saveCustomer(@Valid @ModelAttribute Usuario usuario, BindingResult result, HttpServletRequest request, RedirectAttributes attributes){

        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.addObject("usuario", usuario);
            mav.setViewName("signup/usuario");
            return mav;
        }

        try {

            Usuario usuarioCreado =usuarioServicio.crearUsuario(usuario.getDni(), usuario.getNombre(), usuario.getApellido(), usuario.getCredencial().getMail(), usuario.getTelefono(),usuario.getCredencial().getUsername(),usuario.getCredencial().getPassword());

            /*Foto foto;
            if(usuario.getFoto() == null){
                foto = fotoServicio.crearFoto(USUARIOS_UPLOADED_FOLDER,String.valueOf(usuarioCreado.getId()),usuario.getNombre()+"-"+usuario.getApellido(),usuario.getFoto());
            }else{
                foto = fotoServicio.actualizarFoto(usuarioCreado.getFoto(),USUARIOS_UPLOADED_FOLDER,String.valueOf(usuarioCreado.getId()),usuario.getNombre()+"-"+usuario.getApellido(),multipartFile);
            }
            usuarioServicio.crearFoto(foto,usuario.getId());*/

            request.login(usuario.getCredencial().getMail(), usuario.getCredencial().getPassword());
            mav.setViewName("redirect:/");
        } catch (ServletException e) {
            attributes.addFlashAttribute("error", "Error al realizar auto-login");
        } catch (Exception e) {
            attributes.addFlashAttribute("usuario", usuario);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/signup/usuario");
        }


        return mav;
    }

    @GetMapping(value = "/signup/comedoro")
    public ModelAndView signupComedor(HttpServletRequest request,Principal principal){
        ModelAndView mav = new ModelAndView("signupComedor");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) {
            mav.setViewName("redirect:/");
        }

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("usuario", flashMap.get("usuario"));
        } else {
            Usuario usuario = new Usuario();
            //usuario.setRol(Rol.USER);
            mav.addObject("usuario", usuario);
        }

        return mav;
    }

}
