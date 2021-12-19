package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.repositorios.DetalleCompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetalleCompraServicio {

    private DetalleCompraRepository detalleCompraRepository;

    private CanastaServicio canastaServicio;

    @Transactional
    public DetalleCompra crearDetalleCompra(Long idCanasta,Integer cantidad, Usuario usuario){
        if(idCanasta == null){
            throw new FieldInvalidException("La canasta es obligatoria");
        }
        if(usuario == null){
            throw new FieldInvalidException("La canasta es obligatoria");
        }

        DetalleCompra detalleCompra = new DetalleCompra();
        Canasta canasta = canastaServicio.buscarPorId(idCanasta);
        detalleCompra.setCanasta(canasta);
        detalleCompra.setUsuario(usuario);
        detalleCompra.setCantidad(cantidad); // -------- Revisar
        detalleCompra.setSubtotal(canasta.getPrecio()*cantidad);
        detalleCompra.setAsignado(false);
        return detalleCompraRepository.save(detalleCompra);
    }

    @Transactional
    public DetalleCompra editarCantidad(Long idDetalleCompra, Integer cantidad){
        if(idDetalleCompra == null){
            throw new FieldInvalidException("La canasta es obligatoria");
        }

        DetalleCompra detalleCompra = detalleCompraRepository.findById(idDetalleCompra).orElseThrow(
                () -> new NoSuchElementException("No existe un producto vinculado al id"+idDetalleCompra)
        );

        detalleCompra.setCantidad(cantidad); // -------- Revisar
        detalleCompra.setSubtotal(detalleCompra.getCanasta().getPrecio()*cantidad);
        detalleCompra.setAsignado(false);
        return detalleCompraRepository.save(detalleCompra);
    }

    @Transactional(readOnly = true)
    public Optional<DetalleCompra> existeDetalleCompraSesion(Long idCanasta, Usuario usuario){
       Canasta canasta = canastaServicio.buscarPorId(idCanasta);
        return detalleCompraRepository.existeDetalleCompraSesion(canasta,usuario);
    }

    @Transactional
    public void asignarACompra(DetalleCompra detalleCompra, Compra compra){
        detalleCompra.setCompra(compra);
        detalleCompra.setAsignado(true);
        detalleCompraRepository.save(detalleCompra);
    }

    @Transactional(readOnly = true)
    public List<DetalleCompra> buscarPorCanasta(Long idCanasta){
        return detalleCompraRepository.findByCanasta_id(idCanasta);
    }


    @Transactional(readOnly = true)
    public List<DetalleCompra> obtenerDetalleCompraSesion(String mail){
        return detalleCompraRepository.obtenerDetallesCompraSesion(mail);
    }

    @Transactional
    public void eliminarDetalleCompra(Long id){
        DetalleCompra detalleCompra = detalleCompraRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("El detalle de compra que desea eliminar no existe"));
        detalleCompraRepository.delete(detalleCompra);
    }
}
