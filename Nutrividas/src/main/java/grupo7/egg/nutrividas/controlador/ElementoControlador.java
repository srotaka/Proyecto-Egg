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
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

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
        Optional<Elemento> elemento = elementoServicio.existeElementoSesion(idProducto,getCustomerLogged());
        if(elemento.isPresent()){
            elementoServicio.editarCantidad(elemento.get().getId(), elemento.get().getCantidadNecesaria()+1,false);
        }else {
            elementoServicio.crearElemento(idProducto, getCustomerLogged());
        }
    }

    @GetMapping("/editar_cantidad/{id}/{cantidad}")
    public void editarCantidad(@PathVariable("id")Long idProducto,@PathVariable("cantidad")Integer cantidad, HttpServletRequest request, HttpSession session){
        Optional<Elemento> elemento = elementoServicio.existeElementoSesion(idProducto,getCustomerLogged());
        System.out.println("Id cantidad"+elemento.get().getId());
        if(elemento.isPresent()){
            elementoServicio.editarCantidad(elemento.get().getId(), cantidad,false);
        }
    }

    @PostMapping("/editar/id")
    public void editarCandidad(@PathVariable("id")Long id, @RequestParam("cantidad") Integer cantidad,@RequestParam("asignado") Boolean asignado){
        elementoServicio.editarCantidad(id,cantidad,asignado);
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminarCandidad(@PathVariable("id")Long id){
        elementoServicio.eliminarElemento(id);
        return new RedirectView("/canasta/crear");
    }

    public void  actualizarContadorCanasta(HttpSession session){
        session.setAttribute("listaElementos", elementoServicio.obtenerElemntosSesion(session.getAttribute("usernameSession").toString()));

    }

}
