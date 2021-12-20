package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Elemento;
import grupo7.egg.nutrividas.servicios.CredencialServicio;
import grupo7.egg.nutrividas.servicios.ElementoServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/elemento")
@AllArgsConstructor
public class ElementoControlador {

     private ElementoServicio elementoServicio;


     private UsuarioServicio usuarioServicio;

     private CredencialServicio credencialServicio;

    private void getCredential(){

    }

    @GetMapping("/agregar/{id}")
    public void crearElementoDeCanasta(@PathVariable("id")Long idProducto, HttpServletRequest request, HttpSession session){
        Optional<Elemento> elemento = elementoServicio.existeElementoSesion(idProducto,session.getAttribute("emailSession").toString());
        if(elemento.isPresent()){
            elementoServicio.editarCantidad(elemento.get().getId(), elemento.get().getCantidadNecesaria()+1,false);
        }else {
            elementoServicio.crearElemento(idProducto,credencialServicio.findByMail(session.getAttribute("emailSession").toString()));
        }
    }

    @GetMapping("/editar_cantidad/{id}/{cantidad}")
    public void editarCantidad(@PathVariable("id")Long idProducto,@PathVariable("cantidad")Integer cantidad, HttpServletRequest request, HttpSession session){
        Optional<Elemento> elemento = elementoServicio.existeElementoSesion(idProducto,session.getAttribute("emailSession").toString());
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
        session.setAttribute("listaElementos", elementoServicio.obtenerElemntosSesion(session.getAttribute("emailSession").toString()));

    }

}
