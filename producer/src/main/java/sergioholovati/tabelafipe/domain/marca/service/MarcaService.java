package sergioholovati.tabelafipe.domain.marca.service;

import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;

import java.util.List;

public interface MarcaService {

    List<MarcaDTO> buscarMarcas(QueryParams page);

    MarcaDTO buscarMarcaDtoPorCodigo(Long codigo);

    List<CarroDTO> buscarCarrosPorCodigoMarca(Long codigo, QueryParams queryParams);

    void sincronizarMarcas();

}
