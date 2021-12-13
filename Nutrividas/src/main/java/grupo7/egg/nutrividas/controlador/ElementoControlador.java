package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.servicios.ElementoServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/elemento")
public class ElementoControlador {

     @Autowired
     private ElementoServicio elementoServicio;

     @Autowired
     private UsuarioServicio usuarioServicio;


    public Usuario getCustomerLogged(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail;
        if (principal instanceof UserDetails){
            mail = ((UserDetails) principal).getUsername();
        }else{
            mail = principal.toString();
        }

        return usuarioServicio.buscarPorMail(mail);
    }


    @GetMapping("/agregar/{id}")
    public void crearElementoDeCanasta(@PathVariable("id")Long idProducto, HttpServletRequest request, HttpSession session){
        elementoServicio.crearElemento(idProducto,getCustomerLogged());
    }

    @PostMapping("/editar")
    public void editarCandidad(@RequestParam("id")Long id, @RequestParam("cantidad") Integer cantidad,@RequestParam("asignado") Boolean asignado){
        elementoServicio.editarCantidad(id,cantidad,asignado);
    }

}
