package sergioholovati.tabelafipe.domain.fipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FipeModeloDTO {

    private String nome;
    private Integer codigo;
}
