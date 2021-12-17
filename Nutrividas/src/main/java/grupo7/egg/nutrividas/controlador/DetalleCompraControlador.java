package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.DetalleCompra;
import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.servicios.DetalleCompraServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/detalle_compra")
@AllArgsConstructor
public class DetalleCompraControlador {

    private DetalleCompraServicio detalleCompraServicio;

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

    @GetMapping("/agregar/{id}/{cantidad}")
    public void crearDetalleCompra(@PathVariable("id")Long idCanasta,@PathVariable("cantidad")Integer cantidad, HttpServletRequest request, HttpSession session){
        Optional<DetalleCompra> detalleCompra = detalleCompraServicio.existeDetalleCompraSesion(idCanasta,getCustomerLogged());
        if(detalleCompra.isPresent()){
            detalleCompraServicio.editarCantidad(detalleCompra.get().getId(),cantidad);
        }else{
            detalleCompraServicio.crearDetalleCompra(idCanasta,cantidad,getCustomerLogged());
        }

    }

    @GetMapping("/editar_cantidad/{id}/{cantidad}")
    public void editarCantidad(@PathVariable("id")Long idCanasta,@PathVariable("cantidad")Integer cantidad, HttpServletRequest request, HttpSession session){
        Optional<DetalleCompra> detalleCompra = detalleCompraServicio.existeDetalleCompraSesion(idCanasta,getCustomerLogged());
        if(detalleCompra.isPresent()){
            detalleCompraServicio.editarCantidad(detalleCompra.get().getId(),cantidad);
        }
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminarCandidad(@PathVariable("id")Long id){
        detalleCompraServicio.eliminarDetalleCompra(id);
        return new RedirectView("/compra/crear"); //---->
    }
}
