package grupo7.egg.nutrividas.servicios;

import grupo7.egg.nutrividas.entidades.Credencial;
import grupo7.egg.nutrividas.entidades.Rol;
import grupo7.egg.nutrividas.exeptions.FieldAlreadyExistException;
import grupo7.egg.nutrividas.exeptions.NotFoundException;
import grupo7.egg.nutrividas.repositorios.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CredencialServicio implements UserDetailsService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RolServicio rolServicio;

    @Transactional
    public Credencial crear(String usename, String mail, String password, List<Rol> roles){

        if(credencialRepository.findByMail(mail).isPresent()){
            throw new FieldAlreadyExistException("Ya existe una cuenta asociada a el mail '"+mail+"' ");
        }
        if(credencialRepository.findByUsername(usename).isPresent()){
            throw new FieldAlreadyExistException("Elnombre de usuario ya existe");
        }

        Credencial credencial = new Credencial();
        setDates(credencial,usename,mail,password,roles);
        return credencialRepository.save(credencial);
    }

    @Transactional
    public void editar(String usename,String mail,String password,List<Rol> roles){
        Credencial credencial = credencialRepository.findByMail(mail)
                .orElseThrow(() -> new NoSuchElementException("The credencial with mail '"+mail+"' doesn't exists"));

        final Rol ROLEDEFAULT = rolServicio.buscarPorNombre("credencial");

        if(roles.isEmpty() || roles == null){
            roles = new ArrayList<>();
            roles.add(ROLEDEFAULT);
        }

        List<Rol> rolesToRemove =  credencial.getRoles();
        List<Rol> rolesToAdd = roles;

        if(rolesToAdd !=null && rolesToRemove!=null){
            rolesToRemove.removeAll(roles);
            rolesToAdd.removeAll(rolesToRemove);
        }

        credencial.setUsername(usename);
        credencial.setMail(mail);
        credencial.setPassword(encoder.encode(password));

        if(rolesToRemove != null){
            rolesToRemove.forEach(rol -> credencialRepository.eliminarRelacionCredencialRol(credencial.getId(),rol.getId()));
        }

        if(rolesToAdd != null){
            for (Rol rol  : rolesToAdd) {
                if(credencialRepository.existsRelation(credencial.getId(),rol.getId())>0){
                    credencialRepository.guardarRelacion(credencial.getId(),rol.getId());
                }
            }
        }

        credencialRepository.save(credencial);
    }

    @Transactional
    public void update(String credencialname,String mail,String password){
        Credencial credencial= credencialRepository.findByMail(mail)
                .orElseThrow(() -> new NoSuchElementException("The credencial with mail '"+mail+"' doesn't exists"));

        credencial.setUsername(credencialname);
        credencial.setMail(mail);
        credencial.setPassword(encoder.encode(password));
        credencialRepository.save(credencial);
    }


    private final Boolean DISCHARGE = Boolean.TRUE;
    public void setDates(Credencial credencial,String credencialname,String mail,String password,List<Rol> roles){

        final Rol ROLEDEFAULT = rolServicio.buscarPorNombre("credencial");

        if(roles.isEmpty() || roles == null){
            roles = new ArrayList<>();
            roles.add(ROLEDEFAULT);
        }

        credencial.setUsername(credencialname);
        credencial.setMail(mail);
        credencial.setPassword(encoder.encode(password));
        credencial.setRoles(roles);
        credencial.setAlta(DISCHARGE);
    }

    private final String MESSAGE = "El nombre de usuario no existe %s";
    private final String MESSAGEDISHARGE = "La cuenta se encuentra deshabilitada %s";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Credencial credencial = credencialRepository.findByMailOrUsername(username,username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE, username)));
        /*
        if(Credencial.getDischarged() == null || Credencial.getDischarged() == false){
            throw new credencialnameNotFoundException(String.format("The account is disabled", credencialname));
        }*/


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("usernameSession",credencial.getUsername());
        session.setAttribute("emailSession",credencial.getMail());


        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        for(Rol rol: credencial.getRoles()){
            roles.add(new SimpleGrantedAuthority("ROLE_"+rol.getNombre()));
        }

        return new User(credencial.getMail(),credencial.getPassword(), roles);
    }

    @Transactional(readOnly = true)
    public Credencial findByMail(String mail){
        return credencialRepository.findByMail(mail).orElseThrow(
                ()-> new NoSuchElementException("No existe un usuario registrado con ese mail"));
    }

    @Transactional(readOnly = true)
    public Optional<Credencial> findBycredencialname(String credencialname){
        return credencialRepository.findByUsername(credencialname);
    }

    @Transactional
    public void deshabilitar(Long id){
        if(!credencialRepository.findById(id).isPresent()){
            throw new NotFoundException("No existe un usuario asociado al id '"+id+"' ");
        }
        credencialRepository.deleteById(id);
    }

    @Transactional
    public void habilitar(Long id){
        if(!credencialRepository.findById(id).isPresent()){
            throw new NotFoundException("No existe un usuario asociado al id '"+id+"' ");
        }
        credencialRepository.habilitar(id);
    }
}
