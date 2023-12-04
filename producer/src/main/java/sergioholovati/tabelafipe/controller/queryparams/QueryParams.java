package sergioholovati.tabelafipe.controller.queryparams;



import lombok.Data;
import lombok.Getter;
import org.jboss.resteasy.reactive.RestQuery;

@Data
@Getter
public class QueryParams {
    @RestQuery("page_size")
    Integer size = 5;
    @RestQuery("page_index")
    Integer index = 1;
}
