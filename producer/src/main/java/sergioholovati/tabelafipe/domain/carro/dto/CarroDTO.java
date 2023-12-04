package sergioholovati.tabelafipe.domain.carro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarroDTO {


    private Long id;

    private String nome;

    private Long codigo;

    private String observacao;

    private Marca marca;
}
