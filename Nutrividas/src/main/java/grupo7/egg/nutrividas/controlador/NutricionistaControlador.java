package grupo7.egg.nutrividas.controlador;

import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Nutricionista;
import grupo7.egg.nutrividas.exeptions.InvalidDataException;
import grupo7.egg.nutrividas.servicios.ComedorServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.NutricionistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nutricionista")
public class NutricionistaControlador {

    @Autowired
    private NutricionistaServicio nutricionistaServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private ComedorServicio comedorServicio;

    @GetMapping("/crear")
    public ModelAndView crearNutricionista(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("nutricionista", flashMap.get("producto"));
        } else {
            mav.addObject("nutricionista", new Nutricionista());
        }
        mav.addObject("comedores",comedorServicio.listarComedoresSinNutricionista());
        mav.addObject("titulo", "Crear nutricionista");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarNutricionista(@PathVariable("id")Long id,HttpServletRequest request){

        ModelAndView mav = new ModelAndView();
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("nutricionista", flashMap.get("producto"));
        } else {
            mav.addObject("nutricionista", new Nutricionista());
        }
        mav.addObject("comedores",comedorServicio.listarComedoresSinNutricionista());
        mav.addObject("titulo", "Editar nutricionista");
        mav.addObject("action", "modificar");

        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardarNutricionista(@ModelAttribute @Valid Nutricionista nutricionista, BindingResult result, RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/nutricionista");
        try{
            String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            if (result.hasErrors()){
                throw new InvalidDataException(errorMsg,result);
            }

            nutricionistaServicio.crearNutricionista(nutricionista.getDocumento(), nutricionista.getNombre(),
                    nutricionista.getApellido(), nutricionista.getMatricula(), nutricionista.getFechaNacimiento(),
                    nutricionista.getTelefono(), nutricionista.getCredencial().getMail(),
                    nutricionista.getCredencial().getUsername(),nutricionista.getCredencial().getPassword());
            attributes.addFlashAttribute("exito", "El nutricionista se ha creado con éxito");
        }catch (Exception e){
            attributes.addFlashAttribute("nutricionista", nutricionista);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/nutricionista/crear");
        }
        return redirectView;
    }

    @PostMapping("/modificar")
    public RedirectView modificarNutricionista(@ModelAttribute @Valid Nutricionista nutricionista, BindingResult result,RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/nutricionista");
        try{
            String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            if (result.hasErrors()){
                throw new InvalidDataException(errorMsg,result);
            }

            nutricionistaServicio.crearNutricionista(nutricionista.getDocumento(), nutricionista.getNombre(),
                    nutricionista.getApellido(), nutricionista.getMatricula(),
                    nutricionista.getFechaNacimiento(), nutricionista.getTelefono(),
                    nutricionista.getCredencial().getMail(), nutricionista.getCredencial().getUsername(),
                    nutricionista.getCredencial().getPassword());
            attributes.addFlashAttribute("exito", "El nutricionista se ha actualizado con éxito");
        }catch (Exception e){
            attributes.addFlashAttribute("nutricionista", nutricionista);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/nutricionista/modificar");
        }
        return redirectView;
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
}
