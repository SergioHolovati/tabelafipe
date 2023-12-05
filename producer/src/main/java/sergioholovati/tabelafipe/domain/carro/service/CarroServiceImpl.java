package sergioholovati.tabelafipe.domain.carro.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.controller.request.CarroRequest;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
@Transactional
public class CarroServiceImpl implements CarroService{

    private static final String LOG_BUSCANDO_CARROS_POR_MARCA = "Buscando carros da marca {}";
    private final CarroRepository carroRepository;
    private final GenericMapper mapper;

    @Inject
    public CarroServiceImpl(CarroRepository carroRepository, GenericMapper mapper) {
        this.carroRepository = carroRepository;
        this.mapper = mapper;
    }


    @Override
    public List<CarroDTO> buscarTodosCarros(QueryParams queryParams){
        List<Carro> carros = carroRepository.buscarTodos(queryParams);
        return mapper.converterCollection(carros,CarroDTO.class);
    }

    @Override
    public CarroDTO buscarCarroPorCodigo(Long codigo) {
        Carro carro = carroRepository.buscarPorCodigo(codigo);
        return mapper.converter(carro, CarroDTO.class);
    }

    @Override
    public List<CarroDTO> buscarCarroPorMarca(Marca marca, QueryParams queryParams) {
        log.info(LOG_BUSCANDO_CARROS_POR_MARCA, marca);
        List<Carro> carros = carroRepository.buscarPorMarca(marca,queryParams);
        return mapper.converterCollection(carros, CarroDTO.class);
    }

    @Override
    public CarroDTO atualizarCarro(Long id,
                                   CarroRequest carroRequest) {
        Carro carro = carroRepository.buscarPorId(id);
        carro.setObservacao(carroRequest.getObservacao());
        carroRepository.salvar(carro);
        return mapper.converter(carro, CarroDTO.class);
    }
}
