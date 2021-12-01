package grupo7.egg.nutrividas.util.paginacion;

import org.springframework.data.domain.Page;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class Paged<T>{
    private Page<T> page;
    private Paging paging;

    public Paged(Page<T> page, Paging paging) {
        this.page = page;
        this.paging = paging;
    }
}
