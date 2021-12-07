package grupo7.egg.nutrividas.entidades.ubicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Localidad {

    private Integer total;
    private List<LocalidadCensal> localidades;
}
