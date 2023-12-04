package sergioholovati.tabelafipe.domain.carro.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.fipe.FipeModeloDTO;
import sergioholovati.tabelafipe.domain.fipe.FipeModelosDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;
import java.util.Optional;


@Slf4j
@ApplicationScoped
public class CarroServiceImpl implements CarroService{

    private static final String LOG_SINCRONIZANDO_CARRO_DA_MARCA = "sincronizando carro : {} da marca : {} ";

    private final CarroRepository carroRepository;
    private final GenericMapper mapper;

    @Inject
    public CarroServiceImpl(CarroRepository carroRepository, GenericMapper mapper) {
        this.carroRepository = carroRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Carro> buscarCarroPorMarca(Marca marca) {
        return carroRepository.buscarPorMarca(marca);
    }

    @Override
    public void sincronizarCarrosPorMarca(FipeModelosDTO fipeModelosDto, Marca marca) {
        List<FipeModeloDTO> modelos = fipeModelosDto.getModelos();

        modelos.forEach(modelo -> {
            long codigo = modelo.getCodigo().longValue();
            if(!carroJaCadastrado(codigo)){
                log.info(LOG_SINCRONIZANDO_CARRO_DA_MARCA, modelo.getNome(),  marca.getNome());
                carroRepository.salvar(modelo,marca,codigo);
            }
        });
    }

    private Boolean carroJaCadastrado(Long codigoCarro){
       return carroRepository.carroExiste(codigoCarro);
    }
}
