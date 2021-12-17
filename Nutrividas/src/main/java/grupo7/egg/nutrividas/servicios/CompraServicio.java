package grupo7.egg.nutrividas.servicios;
import grupo7.egg.nutrividas.entidades.*;
import grupo7.egg.nutrividas.repositorios.CompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CompraServicio {

    private CompraRepository compraRepository;

    @Transactional
    public Compra crearCompra(List<DetalleCompra> detallesCompras, Usuario usuario, Tarjeta tarjeta){

        Compra compra = new Compra();
        compra.setDetalleCompras(detallesCompras);
        compra.setUsuario(usuario);
        Double precioFinal = 0.0;
        for (DetalleCompra d: detallesCompras) {
            precioFinal += d.getSubtotal();
        }
        compra.setPrecioFinal(precioFinal);
        compra.setTarjeta(tarjeta);
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
