package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Comedor;
import grupo7.egg.nutrividas.entidades.Credencial;
import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.exeptions.InvalidDataException;
import grupo7.egg.nutrividas.mail.MailService;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.servicios.CredencialServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.NutricionistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/nutricionista")
public class NutricionistaControlador {

    @Autowired
    private NutricionistaServicio nutricionistaServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private ComedorServicio comedorServicio;

    @Autowired
    private CredencialServicio credencialServicio;

    @Autowired
    private MailService mailService;

    @GetMapping(value = "/signup")
    public ModelAndView signupNutricionista(HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("signupNutricionista");
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) {
            mav.setViewName("redirect:/ ");
        }

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("nutricionista", flashMap.get("nutricionista"));
        } else {
            mav.addObject("nutricionista", new Nutricionista());
        }

        return mav;
    }

    @PostMapping(value = "/registro")
    public ModelAndView saveNutricionista(@Valid @ModelAttribute Nutricionista nutricionista, BindingResult result, HttpServletRequest request, RedirectAttributes attributes){

        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.addObject("nutricionista", nutricionista);
            mav.setViewName("signupNutricionista");
            return mav;
        }

        try {

            Nutricionista nutricionistaCreado =nutricionistaServicio.crearNutricionista(nutricionista.getDocumento(), nutricionista.getNombre(),
                    nutricionista.getApellido(), nutricionista.getMatricula(), nutricionista.getFechaNacimiento(),
                    nutricionista.getTelefono(), nutricionista.getCredencial().getMail(),
                    nutricionista.getCredencial().getUsername(),nutricionista.getCredencial().getPassword());

            /*Foto foto;
            if(usuario.getFoto() == null){
                foto = fotoServicio.crearFoto(USUARIOS_UPLOADED_FOLDER,String.valueOf(usuarioCreado.getId()),usuario.getNombre()+"-"+usuario.getApellido(),usuario.getFoto());
            }else{
                foto = fotoServicio.actualizarFoto(usuarioCreado.getFoto(),USUARIOS_UPLOADED_FOLDER,String.valueOf(usuarioCreado.getId()),usuario.getNombre()+"-"+usuario.getApellido(),multipartFile);
            }
            usuarioServicio.crearFoto(foto,usuario.getId());*/
            mailService.sendWelcomeMail("Bienvenida",nutricionistaCreado.getCredencial().getMail(),nutricionista.getCredencial().getUsername(),nutricionista.getCredencial().getId(),"NUTRICIONISTA");
            //request.login(nutricionista.getCredencial().getMail(), nutricionista.getCredencial().getPassword());
            mav.setViewName("redirect:/");

        } catch (Exception e) {
            attributes.addFlashAttribute("nutricionista", nutricionista);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/signup/nutricionista");
        }

        return mav;
    }

    @PostMapping("/modificar")
    public ModelAndView modificar(@Valid @ModelAttribute Nutricionista nutricionista, BindingResult result, RedirectAttributes attributes) {

        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.addObject("nutricionista",nutricionistaServicio.buscarPorId(nutricionista.getId()));
            mav.addObject("titulo", "Editar Nutricionista");
            mav.addObject("accion", "guardar");
            mav.setViewName("editarNutricionista");
        }
        try {
            nutricionistaServicio.modificarNutricionista(nutricionista.getId(), nutricionista.getNombre(), nutricionista.getApellido(), nutricionista.getDocumento(),
                    nutricionista.getMatricula(), nutricionista.getFechaNacimiento(),
                    nutricionista.getTelefono());
            attributes.addFlashAttribute("exito", "La edicion ha sido realizada satisfactoriamente");
            mav.setViewName("redirect:/");
        } catch (Exception e) {
            attributes.addFlashAttribute("nutricionista", nutricionista);
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/modificar/{" + nutricionista.getCredencial().getUsername()+"}");
        }

        return mav;
    }

    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable Long id){
        nutricionistaServicio.habilitarNutricionista(id);
        return new RedirectView("/nutricionista");
    }

    @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitar(@PathVariable Long id){
        nutricionistaServicio.deshabilitarPersona(id);
        return new RedirectView("/nutricionista");
    }

    @GetMapping(value = "",params = {"page","size"})
    public ModelAndView buscarNutricionista(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                        HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }
        mav.addObject("nutricionistaPagina", nutricionistaServicio.buscarTodos(page,size));
        return mav;
    }

    @GetMapping(value = "",params = {"matricula","page","size"})
    public ModelAndView buscarNutricionistaPorMatricula(@RequestParam("matricula") Long matricula,
                                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                            HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }
        mav.addObject("nutricionistaPagina", nutricionistaServicio.buscarPorMatricula(matricula,page,size));
        return mav;
    }

    @GetMapping(value = "",params = {"search","page","size"})
    public ModelAndView buscarNutricionistaGeneral(@RequestParam("search") String search,
                                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                        HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }
        mav.addObject("nutricionistaPagina", nutricionistaServicio.buscarPorTodosCampos(search,page,size));
        return mav;
    }

    @Value("${picture.users.location}")
    public String USUARIOS_UPLOADED_FOLDER;

    @PostMapping("/imagen/crear")
    public void  uploadImage(@RequestParam("id")Long id,@RequestParam("imagen") MultipartFile multipartFile,
                             UriComponentsBuilder componentsBuilder){

        Nutricionista nutricionista = nutricionistaServicio.buscarPorId(id);
        Foto foto = fotoServicio.crearFoto(USUARIOS_UPLOADED_FOLDER ,String.valueOf(id),nutricionista.getNombre()+"-"+nutricionista.getApellido(),multipartFile);
        nutricionistaServicio.modificarFoto(nutricionista.getId(),foto);
    }

    @GetMapping("/comedores/{username}")
    public ModelAndView mostrarComedoresPorNutricionista(@PathVariable String username, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("comedoresNutricionista");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        Credencial credencial = credencialServicio.buscarCredencialPorUsername(username);
        Nutricionista nutricionista = nutricionistaServicio.buscarNutricionistaPorCredencial(credencial.getId());
        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("comedores", comedorServicio.listarComedoresPorNutricionista(nutricionista.getId()));

        return mav;
    }

    @GetMapping("/asignarComedor/{username}")
    public ModelAndView mostrarTodosLosComedores(@PathVariable String username, HttpServletRequest request){
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        ModelAndView mav = new ModelAndView("asignacionComedor");
        Credencial credencial = credencialServicio.buscarCredencialPorUsername(username);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito-name"));
            mav.addObject("error", flashMap.get("error-name"));
        }
        mav.addObject("comedores", comedorServicio.listarComedoresSinNutricionista());
        return mav;
    }

    @PostMapping("/asignar/{id}/{username}")
    public RedirectView asignarNutricionista(@PathVariable Long id, @PathVariable String username, HttpServletRequest request){
        Credencial credencial = credencialServicio.buscarCredencialPorUsername(username);
        Nutricionista nutricionista = nutricionistaServicio.buscarNutricionistaPorCredencial(credencial.getId());
        comedorServicio.asignarNutricionistaAComedor(id, nutricionista.getId());
        return new RedirectView("/");
    }

    @PostMapping("/desasignar/{id}")
    public RedirectView desasignar(@PathVariable Long id){
        comedorServicio.desasignarNutricionistaAComedor(id);
        return new RedirectView("/");
    }
}
