package grupo7.egg.nutrividas.repositorios;

import grupo7.egg.nutrividas.entidades.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial,Long> {

    @Modifying
    @Query("UPDATE Credencial c SET c.alta = true WHERE c.id = :id")
    void habilitar(@Param("id") Long id);

    Optional<Credencial> findByMailOrUsername(String mail, String username);
    Optional<Credencial> findByMail(String mail);
    Optional<Credencial> findByUsername(String username);

    /*Relations*/
    @Modifying
    @Query( value = "DELETE FROM credenciales_roles WHERE credencial_id= :idCredencial AND rol_id = :idRol", nativeQuery = true)
    void eliminarRelacionCredencialRol (@Param("idCredencial") Long idCredencial,@Param("idRol") Integer idRol);
    @Query( value = "SELECT count(*) FROM  credenciales_roles WHERE EXISTS( SELECT * FROM  credenciales_roles  WHERE credencial_id= :idCredencial AND rol_id = :idRol) LIMIT 1", nativeQuery = true)
    Integer existsRelation(@Param("idCredencial") Long idCredencial,@Param("idRol")Integer idRol);
    @Modifying
    @Query( value = "INSERT INTO credenciales_roles  (credencial_id, rol_id) values (:idCredencial ,:idRol)", nativeQuery = true)
    void guardarRelacion(@Param("idCredencial")Long idCredencial,@Param("idRol")Integer idRol);

    boolean existsByIdAndRoles_Id(Long idCredencial,Integer idRol);

    @Modifying
    @Query("UPDATE Credencial c SET c.habilitado = true WHERE c.id = :id")
    void habilitarCuenta(@Param("id")Long id);

    @Query("SELECT c FROM Credencial c WHERE c.username = :username")
    Credencial buscarCredencialPorUsername(@Param("username") String username);

}
