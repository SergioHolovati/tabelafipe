package sergioholovati.tabelafipe.domain.carro.service;

import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.controller.request.CarroRequest;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import java.util.List;

public interface CarroService {

    List<CarroDTO> buscarTodosCarros(QueryParams queryParams);

    CarroDTO buscarCarroPorCodigo(Long codigo);

    List<CarroDTO> buscarCarroPorMarca(Marca marca, QueryParams queryParams);

    CarroDTO atualizarCarro(Long id, CarroRequest carroRequest);
}
