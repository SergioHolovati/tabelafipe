package sergioholovati.tabelafipe.domain.carro.service;

import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.fipe.FipeModelosDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import java.util.List;

public interface CarroService {

    List<Carro> buscarCarroPorMarca(Marca marca);

    void sincronizarCarrosPorMarca(FipeModelosDTO fipeModelosDto, Marca marca);
}
