package grupo7.egg.nutrividas.entidades.ubicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocalidadCensal {

    private Integer id;
    private String nombre;
    private Municipio municipio;
    private Provincia provincia;
}
