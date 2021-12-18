package grupo7.egg.nutrividas.controlador;


import grupo7.egg.nutrividas.entidades.Foto;
import grupo7.egg.nutrividas.entidades.Producto;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.exeptions.InvalidDataException;
import grupo7.egg.nutrividas.servicios.ElementoServicio;
import grupo7.egg.nutrividas.servicios.FotoServicio;
import grupo7.egg.nutrividas.servicios.MarcaServicio;
import grupo7.egg.nutrividas.servicios.ProductoServicio;
import lombok.AllArgsConstructor;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/producto")
public class ProductoContolador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private MarcaServicio marcaServicio;

    @Autowired
    private ElementoServicio elementoServicio;

    @GetMapping("/crear")
    public ModelAndView crearProducto(HttpServletRequest request){
        ModelAndView mav= new ModelAndView("crearProducto");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("producto", flashMap.get("producto"));
        } else {
            mav.addObject("producto", new Producto());
        }
        mav.addObject("marcas", marcaServicio.listarMarcas());
        mav.addObject("titulo", "Crear producto");
        mav.addObject("accion", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarProducto(@PathVariable("id")Long id,HttpServletRequest request,RedirectAttributes attributes){
        ModelAndView mav= new ModelAndView("crearProducto");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        try{

            if (flashMap != null) {
                mav.addObject("error", flashMap.get("error"));
                mav.addObject("producto", flashMap.get("producto"));
            } else {
                mav.addObject("producto", productoServicio.obtenerProductoPorId(id));
            }

            mav.addObject("marcas", marcaServicio.listarMarcas());
            mav.addObject("titulo", "Editar producto");
            mav.addObject("accion", "modificar");

        }catch (Exception e){
            attributes.addFlashAttribute("error", e.getMessage());
            mav.setViewName("redirect:/producto");
        }
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView crearProducto(@ModelAttribute @Valid Producto producto,BindingResult result, RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/producto");
        try{
            String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            if (result.hasErrors()){
                throw new InvalidDataException(errorMsg,result);
            }

            productoServicio.crearProducto(producto.getNombre(),producto.getMarca().getId(),producto.getPrecio(),producto.getCategoria(),
                    producto.getAptoIntoleranteLactosa(),producto.getAptoCeliacos(),producto.getAptoHipertensos(),
                    producto.getAptoDiabeticos());
            attributes.addFlashAttribute("exito", "El producto se ha creado con éxito");
        }catch (Exception e){
            attributes.addFlashAttribute("producto", producto);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/producto/crear");
        }
        return redirectView;
    }

    @PostMapping("/modificar")
    public RedirectView modificarProducto(@ModelAttribute @Valid Producto producto,BindingResult result, RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/producto");
        try{
            String errorMsg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            if (result.hasErrors()){
                throw new InvalidDataException(errorMsg,result);
            }

            productoServicio.modificarProducto(producto.getId(),producto.getNombre(),producto.getMarca().getId(),producto.getPrecio(),producto.getCategoria(),
                    producto.getAptoIntoleranteLactosa(),producto.getAptoCeliacos(),producto.getAptoHipertensos(),
                    producto.getAptoDiabeticos());
            attributes.addFlashAttribute("exito", "El producto se ha actualizado con éxito");
        }catch (Exception e){
            attributes.addFlashAttribute("producto", producto);
            attributes.addFlashAttribute("error", e.getMessage());
            redirectView.setUrl("/producto/editar/"+producto.getId());
        }
        return redirectView;
    }

    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable("id") Long id){
        productoServicio.habilitarProducto(id);
        return new RedirectView("/producto");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView deshabilitar(@PathVariable("id") Long id){
        elementoServicio.eliminarElementosProducto(id);
        productoServicio.deshabilitarProducto(id);
        return new RedirectView("/producto");
    }

    @GetMapping
    public ModelAndView buscarProductos(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                        @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                                    HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("productos");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }
        mav.addObject("productos", productoServicio.buscarTodos(page,size,getSort(order)));
        mav.addObject("rutaActual",obtenerRutaActual(""));
        return mav;
    }

    @GetMapping(value = "/filtrar")
    public ModelAndView buscarPorTodosCaompos(@RequestParam(value = "busqueda") String busqueda,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                           @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                              HttpServletRequest request){

        ModelAndView mav = new ModelAndView("productos");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("productos", productoServicio.buscarPorTodosCampos(busqueda,page,size,getSort(order)));
        mav.addObject("rutaActual", obtenerRutaActual(request.getRequestURL()+"?busqueda="+busqueda));
        return mav;
    }
    @GetMapping(value = "/apto")
    public ModelAndView buscarAptoPatologías(@RequestParam(value = "apto") String apto,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                              @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                              HttpServletRequest request){

        ModelAndView mav = new ModelAndView("productos");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("productos", productoServicio.buscarApto(true,apto,page,size,getSort(order)));
        mav.addObject("rutaActual", obtenerRutaActual(request.getRequestURL()+"?apto="+apto));
        return mav;
    }

    @GetMapping(value = "/cat")
    public ModelAndView buscarCategoria(@RequestParam(value = "categoria") String categoria,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                            @RequestParam(value = "order", required = false, defaultValue = "OrderByNombreASC") String order,
                                            HttpServletRequest request){

        ModelAndView mav = new ModelAndView("productos");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
        }

        mav.addObject("productos", productoServicio.buscarPorCategoria(categoria,page,size,getSort(order)));

        mav.addObject("rutaActual", obtenerRutaActual(request.getRequestURL()+"?categoria="+categoria));

        return mav;
    }


    @Value("${picture.products.location}")
    public String PRODUCTOS_UPLOADED_FOLDER;

    @PostMapping("/imagen/actualizar")
    public void  uploadImage(@RequestParam("id")Long id,@RequestParam("imagen") MultipartFile multipartFile,
                             UriComponentsBuilder componentsBuilder){

        Producto producto = productoServicio.obtenerProductoPorId(id);
        Foto foto;
        if(producto.getFoto() == null){
            foto = fotoServicio.crearFoto(PRODUCTOS_UPLOADED_FOLDER ,String.valueOf(id),producto.getNombre()+"-"+producto.getMarca(),multipartFile);
        }else{
            foto = fotoServicio.actualizarFoto(producto.getFoto(),PRODUCTOS_UPLOADED_FOLDER ,String.valueOf(id),producto.getNombre()+"-"+producto.getMarca(),multipartFile);
        }

        productoServicio.crearFoto(foto,id);
    }

    public Sort getSort(String order){
        switch (order){
            case "OrderByNombreASC":
                return Sort.by(Sort.Direction.ASC,"nombre");
            case "OrderByNombreDESC":
                return Sort.by(Sort.Direction.DESC,"nombre");
            case "OrderByPrecioASC":
                return Sort.by(Sort.Direction.ASC,"precio");
            case "OrderByPrecioDESC":
                return Sort.by(Sort.Direction.DESC,"precio");
            case "OrderByMarcaASC":
                return Sort.by(Sort.Direction.ASC,"marca");
            case "OrderByMarcaDESC":
                return Sort.by(Sort.Direction.DESC,"marca");
            default:
                throw new FieldInvalidException("El parámetro de orden ingresado es inválido");
        }
    }

    public static String obtenerRutaActual(String ruta) {

        String uri = "";
        System.out.println("ruta: "+ruta);
        if(ruta.length() > 29){
            uri = ruta.substring(30);
        }
        String removeParameterPage = "";
        if(!uri.equals("")){
            removeParameterPage = uri.replaceAll("/[&?]page=\\d+/","");
        }

        if(removeParameterPage.contains("?")){
            return removeParameterPage.concat("&");
        }else{
            return removeParameterPage.concat("?");
        }

    }

}
