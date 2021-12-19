package grupo7.egg.nutrividas.servicios;
import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.repositorios.CompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompraServicio {

    private CompraRepository compraRepository;


    @Transactional
    public Compra crearCompra(List<DetalleCompra> detallesCompras, Usuario usuario){

        List<DetalleCompra> detallesComprasFinal = detallesCompras.stream().filter(d -> d.getCantidad() > 0).collect(Collectors.toList());
        detallesComprasFinal.stream().forEach(d ->{ d.setSubtotal(d.getCanasta().getPrecio() * d.getCantidad());
        d.setUsuario(usuario);});

        Compra compra = new Compra();
        compra.setDetalleCompras(detallesComprasFinal);
        compra.setUsuario(usuario);
        Double precioFinal = 0.0;

        for (DetalleCompra d: detallesComprasFinal) {
            precioFinal += d.getSubtotal();
        }

        compra.setPrecioFinal(precioFinal);
        compra.setAlta(true);
        compra.setAsignada(false);
        return compraRepository.save(compra);
    }

    @Transactional
    public Compra pagarCompra(Long idCompra, Tarjeta tarjeta){
        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                () -> new NoSuchElementException("No se encontró una compra vinculada al id "+idCompra)
        );
        compra.setTarjeta(tarjeta);
        compra.setAsignada(true);
        return compraRepository.save(compra);
    }


    @Transactional(readOnly = true)
    public List<Compra> listarcomprasUsuario(Long idUsaurio){
        return compraRepository.findByUsuario_id(idUsaurio);
    }

    public Compra buscarPorId(Long idCompra){
        return compraRepository.findById(idCompra).orElseThrow(
                () -> new NoSuchElementException("No existe una compra vinculada al id "+idCompra)
        );
    }

    @Transactional
    public void habilitarCompra(Long idCompra){
        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                () -> new NoSuchElementException("No existe una compra vinculada al id "+idCompra)
        );
       compraRepository.deleteById(idCompra);
    }

    @Transactional
    public void deshabilitarCompra(Long idCompra){
        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                () -> new NoSuchElementException("No existe una compra vinculada al id "+idCompra)
        );
        compraRepository.habilitar(idCompra);
    }
}
