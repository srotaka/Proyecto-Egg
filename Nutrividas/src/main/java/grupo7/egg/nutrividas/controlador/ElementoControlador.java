package grupo7.egg.nutrividas.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/elemento")
public class ElementoControlador {

  /*  @Autowired
    private ElementoServicio elementoServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CanastaServicio canastaServicio;


    @GetMapping("/crear")
    public ModelAndView crear(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("elemento");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("elemento", flashMap.get("elemento"));
        } else {
            mav.addObject("elemento", new Elemento());
        }

        mav.addObject("productos", productoServicio.listarProductos());
        mav.addObject("canasta", );
        mav.addObject("title", "Crear Libro");
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public Elemento crearElementoDeCanasta(@ModelAttribute @Valid Elemento elemento){
        return elementoServicio.crearElementoDeCanasta(elemento.getId(), elemento.getCantidadNecesaria(), elemento.getCanasta().getId());
    }

    @PostMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void editarElementoDeCanasta(@RequestBody @Valid Long idElemento,Long idProducto, Integer cantidadNecesaria, Long idCanasta){
        elementoServicio.editarElementoDeCanasta(idElemento, idProducto, cantidadNecesaria, idCanasta);
    }



    @PostMapping("/comprar")
    @ResponseStatus(HttpStatus.CREATED)
    public void comprarCantidadDeElemento(@RequestParam(""))  */
}
