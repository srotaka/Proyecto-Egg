
package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.mail.MailService;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private MailService mailService;

    @GetMapping(value = "/compras/{id}")
    public ModelAndView signup(@PathVariable("id")Long id,HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("historialCompras");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);

        /*if (principal != null) {
            mav.setViewName("redirect:/ ");
        }*/

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("usuario", flashMap.get("usuario"));
        } else {
            mav.addObject("usuario", usuarioServicio.buscarPorId(id));
        }

        return mav;
    }


    @PostMapping("/modificar")
    public RedirectView modificarUsuario(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @RequestParam Long telefono, RedirectAttributes redirect, RedirectAttributes attributes){
        RedirectView redirectView = new RedirectView("/");
        Usuario usuario = usuarioServicio.buscarPorId(id);

        try{
            usuarioServicio.modificarUsuario(id, dni, nombre, apellido, telefono);

        }catch(Exception e){
            redirect.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/modificar/{" + usuario.getCredencial().getUsername()+"}");
            return redirectView;
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

    @Value("${picture.users.location}")
    public String USUARIOS_UPLOADED_FOLDER;

    @PostMapping("/imagen/actualizar")
    public void  uploadImage(@RequestParam("id")Long id, @RequestParam("imagen") MultipartFile multipartFile,
                             UriComponentsBuilder componentsBuilder){

        Usuario usuario = usuarioServicio.buscarPorId(id);
        Foto foto;
        if(usuario.getFoto() == null){
            foto = fotoServicio.crearFoto(USUARIOS_UPLOADED_FOLDER,String.valueOf(id),usuario.getNombre()+"-"+usuario.getApellido(),multipartFile);
        }else{
            foto = fotoServicio.actualizarFoto(usuario.getFoto(),USUARIOS_UPLOADED_FOLDER,String.valueOf(id),usuario.getNombre()+"-"+usuario.getApellido(),multipartFile);
        }

        usuarioServicio.crearFoto(foto,id);
    }
    
}
