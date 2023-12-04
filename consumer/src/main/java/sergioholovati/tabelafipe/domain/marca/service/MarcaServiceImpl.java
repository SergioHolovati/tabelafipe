package sergioholovati.tabelafipe.domain.marca.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import sergioholovati.tabelafipe.client.service.MarcasFipeServiceImpl;
import sergioholovati.tabelafipe.domain.carro.service.CarroService;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;


@Slf4j
@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    private static final String LOG_SALVANDO_POST = "Log inicial salvando post: {}";
    private static final String LOG_SALVANDO_MARCA = "Log inicial salvando marca : {}";

    private final MarcaRepository marcaRepository;
    private final CarroService carroService;
    private final MarcasFipeServiceImpl marcasFipeService;
    private final GenericMapper mapper;

    @Inject
    public MarcaServiceImpl(MarcaRepository marcaRepository,
                            CarroService carroService,
                            MarcasFipeServiceImpl marcasFipeService,
                            GenericMapper mapper){

        this.marcaRepository = marcaRepository;
        this.carroService = carroService;
        this.marcasFipeService = marcasFipeService;
        this.mapper = mapper;
    }

    @Override
    public List<Marca> buscarMarcas() {
        return marcaRepository.buscarTodos();
    }

    @Transactional
    @Incoming("sincronizar-marcas-fipe")
    public void sincronizarMarcas(MarcaExtension marcaExtension) {
        log.info(LOG_SALVANDO_POST, marcaExtension.getNome());

        Long codigo = marcaExtension.getCodigo();

        if(!marcaJaCadastrada(codigo)){
            log.info(LOG_SALVANDO_MARCA, marcaExtension.getNome());
            marcaRepository.salvar(mapper.converter(marcaExtension, Marca.class));
            Marca marca = marcaRepository.buscarPorCodigo(marcaExtension.getCodigo());
            var fipeModelosDto = marcasFipeService.sincronizarCarrosPorMarca(codigo);
            carroService.sincronizarCarrosPorMarca(fipeModelosDto,marca);
        }
    }

    private Boolean marcaJaCadastrada(Long codigo) {
        return marcaRepository.marcaCadastrada(codigo);
    }
}
