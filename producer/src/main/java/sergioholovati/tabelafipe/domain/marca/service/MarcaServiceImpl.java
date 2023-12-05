package sergioholovati.tabelafipe.domain.marca.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import sergioholovati.tabelafipe.client.service.MarcasFipeServiceImpl;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.service.CarroService;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.kafka.KafkaResource;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class MarcaServiceImpl implements MarcaService{

    private static final String LOG_ENVIANDO_MENSAGEM_SINCRONIZACAO_DE_MARCA = "Log enviando mensagem sincronização de marca {} ";
    private static final String LOG_BUSCANDO_MARCA_POR_CODIGO = "Buscando marca por codigo {}";

    private final MarcaRepository marcaRepository;
    private final CarroService carroService;
    private final MarcasFipeServiceImpl marcasFipeService;
    private final KafkaResource kafkaResource;
    private final GenericMapper mapper;

    @Inject
    public MarcaServiceImpl(MarcaRepository marcaRepository,
                            CarroService carroService,
                            MarcasFipeServiceImpl marcasFipeService,
                            KafkaResource kafkaResource,
                            GenericMapper mapper){

        this.marcaRepository = marcaRepository;
        this.carroService = carroService;
        this.marcasFipeService = marcasFipeService;
        this.kafkaResource = kafkaResource;
        this.mapper = mapper;
    }

    @Override
    public List<MarcaDTO> buscarMarcas(QueryParams params) {
        List<Marca> marcas = marcaRepository.buscarTodos(params);
        return mapper.converterCollection(marcas,MarcaDTO.class);
    }

    @Override
    public MarcaDTO buscarMarcaDtoPorCodigo(Long codigo) {
        return mapper.converter(buscarMarcaPorCodigo(codigo), MarcaDTO.class);
    }

    @Override
    public List<CarroDTO> buscarCarrosPorCodigoMarca(Long codigo, QueryParams queryParams) {
        Marca marca = buscarMarcaPorCodigo(codigo);
        return carroService.buscarCarroPorMarca(marca, queryParams);
    }

    @Override
    public void sincronizarMarcas() {
       List<MarcaExtension> marcas = marcasFipeService.sincronizarMarcas();
       marcas.forEach(marca-> {
           log.info(LOG_ENVIANDO_MENSAGEM_SINCRONIZACAO_DE_MARCA, marca);
           kafkaResource.send(marca);
       });
    }

    private Marca buscarMarcaPorCodigo(Long codigo) {
        log.info(LOG_BUSCANDO_MARCA_POR_CODIGO, codigo);
        return  marcaRepository.buscarPorCodigo(codigo);
    }

}
