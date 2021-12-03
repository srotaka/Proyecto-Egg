package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.enums.MarcaTarjeta;
import grupo7.egg.nutrividas.enums.TipoTarjeta;
import grupo7.egg.nutrividas.repositorios.TarjetaRepository;
import grupo7.egg.nutrividas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import grupo7.egg.nutrividas.entidades.Tarjeta;

import java.time.LocalDate;


@Service
public class TarjetaServicio {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Tarjeta crearTarjeta(String nombre, String apellido, Long numeroTarjeta,
                                Integer codigoSeguridad, TipoTarjeta tipoTarjeta, LocalDate fechaVencimiento,
                                Long idUsuario) throws Exception{
        Tarjeta tarjeta = new Tarjeta();
        validarDatosDeTarjeta(nombre, apellido, numeroTarjeta, codigoSeguridad, tipoTarjeta, fechaVencimiento);
        MarcaTarjeta marca = obtenerMarcaDeTarjeta(numeroTarjeta);

        tarjeta.setNombre(nombre);
        tarjeta.setApellido(apellido);
        tarjeta.setNumeroTarjeta(numeroTarjeta);
        tarjeta.setCodigoSeguridad(codigoSeguridad);
        tarjeta.setTipoTarjeta(tipoTarjeta);
        tarjeta.setMarcaTarjeta(marca);
        tarjeta.setFechaVencimiento(fechaVencimiento);
        tarjeta.setUsuario(usuarioRepository.findById(idUsuario).get());
        tarjeta.setAlta(true);
        return tarjetaRepository.save(tarjeta);
    }

    @Transactional
    public Tarjeta modificarTarjeta(Long idTarjeta, String nombre, String apellido, Long numeroTarjeta,
                                Integer codigoSeguridad, TipoTarjeta tipoTarjeta, MarcaTarjeta marca,
                                    LocalDate fechaVencimiento, Long idUsuario) throws Exception{

        Tarjeta tarjeta = tarjetaRepository.findById(idTarjeta).get();

        validarDatosDeTarjeta(nombre, apellido, numeroTarjeta, codigoSeguridad, tipoTarjeta, fechaVencimiento);

        tarjeta.setNombre(nombre);
        tarjeta.setApellido(apellido);
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
                                      LocalDate fechaVencimiento) throws Exception{
        if(nombre == null|| nombre.trim().isEmpty()){
            throw new Exception("El nombre en la tarjeta debe ser obligatorio");
        }
        if(apellido == null|| apellido.trim().isEmpty()){
            throw new Exception("El apellido en la tarjeta debe ser obligatorio");
        }

        Integer largoDeNumero = obtenerLargoDeNumero(numeroTarjeta);

        if(largoDeNumero != 12 || numeroTarjeta == null){
            throw new Exception("El numero de la tarjeta es invalido");
        }

        Integer largoDeCodigo = obtenerLargoDeNumero(codigoSeguridad.longValue());

        if(largoDeCodigo != 3 || codigoSeguridad == null){
            throw new Exception("El codigo de seguridad es invalido");
        }

        if(tipoTarjeta == null){
            throw new Exception("El tipo de tarjeta es obligatorio");
        }
        if(fechaVencimiento == null){
            throw new Exception("La fecha de vencimiento es obligatoria");
        }
        if(fechaVencimiento.isAfter(LocalDate.now())){
            throw new Exception("La fecha de vencimiento es inválida");
        }
        if(tarjetaRepository.obtenerTarjetaPorNumero(numeroTarjeta) != null){
            throw new Exception("La tarjeta ya fue ingesada anteriormente");
        }

    }

    public MarcaTarjeta obtenerMarcaDeTarjeta(Long numeroTarjeta) throws Exception{
        Integer primerNumero = obtenerPrimerNumero(numeroTarjeta);

        if(primerNumero == 4){
            return MarcaTarjeta.VISA;
        }else if(primerNumero == 5){
            return MarcaTarjeta.MASTERCARD;
        }else{
            throw new Exception("El numero de la tarjeta es invalido");
        }
    }

    public Integer obtenerPrimerNumero(Long numeroTarjeta){
        Integer vuelta = 1;
        while(vuelta < 11){
            numeroTarjeta = numeroTarjeta/10;
            vuelta ++;
        }
        return numeroTarjeta.intValue();
    }

    public Integer obtenerLargoDeNumero(Long numeroTarjeta){
        Integer digitos = 0;
        while(numeroTarjeta !=0){
            numeroTarjeta = numeroTarjeta/10;
            digitos++;
        }
        return digitos;
    }

    @Transactional
    public void habilitarTarjeta(Long idTarjeta) throws Exception {
        tarjetaRepository.findById(idTarjeta).orElseThrow(
                () -> new Exception("No se halló una tarjeta con el id "+idTarjeta));
        tarjetaRepository.habilitarTarjeta(idTarjeta);
    }

    @Transactional
    public void deshabilitarPersona(Long idTarjeta) throws Exception {
        tarjetaRepository.findById(idTarjeta).orElseThrow(
                () -> new Exception("No se halló una tarjeta con el id "+idTarjeta));
        tarjetaRepository.deleteById(idTarjeta);
    }
}
