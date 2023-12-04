package sergioholovati.tabelafipe.domain.marca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarcaDTO {

    private Long id;
    private String nome;
    private Long codigo;
}
