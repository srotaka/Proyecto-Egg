package grupo7.egg.nutrividas.util.paginacion;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paged<T>{
    private Page<T> page;
    private Paging paging;
}
