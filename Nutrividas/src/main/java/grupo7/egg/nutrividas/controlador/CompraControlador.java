package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.servicios.CanastaServicio;
import grupo7.egg.nutrividas.servicios.CompraServicio;
import grupo7.egg.nutrividas.servicios.DetalleCompraServicio;
import grupo7.egg.nutrividas.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/compra")
@AllArgsConstructor
public class CompraControlador {

    private CompraServicio compraServicio;

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


    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView("crearCompra");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("compra", flashMap.get("compra"));
        } else {
            Compra compra = new Compra();
            compra.setDetalleCompras(detalleCompraServicio.obtenerDetalleCompraSesion(session.getAttribute("emailSession").toString()));
            mav.addObject("compra", compra);
        }

        mav.addObject("titulo", "Finalizar compra");
        mav.addObject("accion", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@Valid @ModelAttribute Compra compra, BindingResult result, RedirectAttributes attributes, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.addObject("compra", compra);
            mav.setViewName("carrito2");
            return mav;
        }

        try {
            Compra compraEfectuada = compraServicio.crearCompra(compra.getDetalleCompras(), getCustomerLogged());
            compraEfectuada.getDetalleCompras().stream().forEach(c -> detalleCompraServicio.asignarACompra(c,compraEfectuada));
            attributes.addFlashAttribute("exito", "La compra ha sido realizada satisfactoriamente");
            mav.setViewName("redirect:/comedor");

        } catch (Exception e) {
            attributes.addFlashAttribute("compra", compra);
            attributes.addFlashAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            mav.setViewName("redirect:/canasta");
        }

        return mav;
    }


}
