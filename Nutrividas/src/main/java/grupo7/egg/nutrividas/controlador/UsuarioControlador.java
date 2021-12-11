
package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.entidades.Tarjeta;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("usuario")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private FotoServicio fotoServicio;
    
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
                    usuario.getApellido(), usuario.getTelefono());
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
            usuarioServicio.crearUsuario(usuario.getDni(), usuario.getNombre(), usuario.getApellido(), usuario.getCredencial().getMail(), usuario.getTelefono(),usuario.getCredencial().getUsername(),usuario.getCredencial().getPassword());
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
