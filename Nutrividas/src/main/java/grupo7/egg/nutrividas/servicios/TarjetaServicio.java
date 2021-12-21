package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Usuario;
import grupo7.egg.nutrividas.enums.MarcaTarjeta;
import grupo7.egg.nutrividas.enums.TipoTarjeta;
import grupo7.egg.nutrividas.exeptions.FieldInvalidException;
import grupo7.egg.nutrividas.exeptions.InvalidDataException;
import grupo7.egg.nutrividas.repositorios.TarjetaRepository;
import grupo7.egg.nutrividas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import grupo7.egg.nutrividas.entidades.Tarjeta;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;


@Service
public class TarjetaServicio {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Tarjeta crearTarjeta(String nombre, Long numeroTarjeta,
                                Integer codigoSeguridad, LocalDate fechaVencimiento,
                                Usuario usuario) {

        if(tarjetaRepository.existsByNumeroTarjetaAndUsuario(numeroTarjeta,usuario)){
            return tarjetaRepository.findByNumeroTarjetaAndUsuario(numeroTarjeta,usuario).get();
        }

        Tarjeta tarjeta = new Tarjeta();
        MarcaTarjeta marca = obtenerMarcaDeTarjeta(numeroTarjeta);
        TipoTarjeta tipoTarjeta = obtenerTipoDeTarjeta(numeroTarjeta);

        tarjeta.setNombre(nombre);
        tarjeta.setNumeroTarjeta(numeroTarjeta);
        tarjeta.setCodigoSeguridad(codigoSeguridad);
        tarjeta.setTipoTarjeta(tipoTarjeta);
        tarjeta.setMarcaTarjeta(marca);
        tarjeta.setFechaVencimiento(fechaVencimiento);
        //tarjeta.setUsuario(usuarioRepository.findById(idUsuario).get());
        tarjeta.setUsuario(usuario);
        tarjeta.setAlta(true);
        return tarjetaRepository.save(tarjeta);
    }

    /*@Transactional
    public Tarjeta modificarTarjeta(Long idTarjeta, String nombre,Long numeroTarjeta,
                                    Integer codigoSeguridad, MarcaTarjeta marca,
                                    LocalDate fechaVencimiento, Long idUsuario) throws Exception {

        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta).get();
        TipoTarjeta tipoTarjeta = obtenerTipoDeTarjeta(numeroTarjeta);

        tarjeta.setNombre(nombre);
        tarjeta.setNumeroTarjeta(numeroTarjeta);
        tarjeta.setCodigoSeguridad(codigoSeguridad);
        tarjeta.setTipoTarjeta(tipoTarjeta);
        tarjeta.setMarcaTarjeta(marca);
        tarjeta.setFechaVencimiento(fechaVencimiento);
        tarjeta.setUsuario(usuarioRepository.findById(idUsuario).get());
        tarjeta.setAlta(true);
        return tarjetaRepository.save(tarjeta);
    }

    public void validarDatosDeTarjeta(String nombre, String apellido, Long numeroTarjeta,
                                      Integer codigoSeguridad, TipoTarjeta tipoTarjeta,
                                      LocalDate fechaVencimiento, MarcaTarjeta marca) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre en la tarjeta debe ser obligatorio");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new Exception("El apellido en la tarjeta debe ser obligatorio");
        }

        Integer largoDeNumero = Long.toString(numeroTarjeta).length();

        if (largoDeNumero != 16 || numeroTarjeta == null) {
            throw new Exception("El numero de la tarjeta es invalido");
        }

        Integer largoDeCodigo = Integer.toString(codigoSeguridad).length();

        if (largoDeCodigo != 3 || codigoSeguridad == null) {
            throw new Exception("El codigo de seguridad es invalido");
        }

        if (tipoTarjeta == null) {
            throw new Exception("El tipo de tarjeta es obligatorio");
        }
        if (fechaVencimiento == null) {
            throw new Exception("La fecha de vencimiento es obligatoria");
        }
        if (fechaVencimiento.isAfter(LocalDate.now())) {
            throw new Exception("La fecha de vencimiento es inválida");
        }
        if (tarjetaRepository.obtenerTarjetaPorNumero(numeroTarjeta) != null) {
            throw new Exception("La tarjeta ya fue ingesada anteriormente");
        }

        if (obtenerMarcaDeTarjeta(numeroTarjeta) != marca) {
            throw new Exception("La marca de la tarjeta no coincide con el numero de tarjeta");
        }

    }*/

    public MarcaTarjeta obtenerMarcaDeTarjeta(Long numeroTarjeta) {

        String primerNumero = Long.toString(numeroTarjeta).substring(0,1);

        if (primerNumero.equals("4")) {
            return MarcaTarjeta.VISA;
        } else if (primerNumero.equals("5")) {
            return MarcaTarjeta.MASTERCARD;
        } else {
            throw new FieldInvalidException("El numero de la tarjeta es invalido");
        }
    }

    public TipoTarjeta obtenerTipoDeTarjeta(Long numeroTarjeta){
        String segundoNumero = Long.toString(numeroTarjeta).substring(1,2);

        if(segundoNumero.equals("5")){
            return TipoTarjeta.DEBITO;
        }
        return TipoTarjeta.CREDITO;
    }

    @Transactional
    public Tarjeta habilitarTarjeta(Long idTarjeta) throws Exception {
        tarjetaRepository.findById(idTarjeta).orElseThrow(
                () -> new Exception("No se halló una tarjeta con el id " + idTarjeta));
        return tarjetaRepository.habilitarTarjeta(idTarjeta);
    }

    @Transactional
    public Tarjeta deshabilitarTarjeta(Long idTarjeta) throws Exception {
        tarjetaRepository.findById(idTarjeta).orElseThrow(
                () -> new Exception("No se halló una tarjeta con el id " + idTarjeta));
        return tarjetaRepository.deshabilitarTarjeta(idTarjeta);
    }

    @Transactional
    public void eliminarTarjeta(Long idTarjeta) throws Exception {
        tarjetaRepository.findById(idTarjeta).orElseThrow(
                () -> new Exception("No se halló una tarjeta con el id " + idTarjeta));
        tarjetaRepository.eliminarTarjeta(idTarjeta);
    }

    @Transactional
    public List<Tarjeta> obtenerTarjetasDeUsuarioHabilitadas(Long idUsuario){
        return tarjetaRepository.obtenerTarjetasPorUsuarioHabilitadas(usuarioRepository.findById(idUsuario).get());
    }

    @Transactional
    public List<Tarjeta> obtenerTarjetasDeUsuario(Long idUsuario){
        return tarjetaRepository.obtenerTarjetasPorUsuario(usuarioRepository.findById(idUsuario).get());
    }

}
