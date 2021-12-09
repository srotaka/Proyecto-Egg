package grupo7.egg.nutrividas.entidades.ubicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import grupo7.egg.nutrividas.entidades.ubicacion.Municipio;
import lombok.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MunicipioProvincia {

    private Integer total;
    private List<Municipio> municipios;

}
