package sergioholovati.tabelafipe.carro;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.carro.service.CarroServiceImpl;
import sergioholovati.tabelafipe.domain.fipe.FipeModeloDTO;
import sergioholovati.tabelafipe.domain.fipe.FipeModelosDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class CarroServiceTest {
    @Inject
    CarroRepository carroRepository;

    @Inject
    GenericMapper genericMapper;

    @Inject
    CarroServiceImpl carroService;

    Carro carro;

    Marca marca;


    @BeforeEach
    void setup(){

        marca = Marca.builder().id(1L).nome("teste").codigo(1L).build();
        carro = Carro.builder().id(1L).codigo(1L).nome("teste").marca(marca).build();
        carroRepository = mock(CarroRepository.class);
        genericMapper = mock(GenericMapper.class);
        carroService = new CarroServiceImpl(carroRepository);
        when(genericMapper.converter(any(),any())).thenReturn(carro);
        when(carroRepository.buscarPorMarca(any())).thenReturn(List.of(carro));
    }

    @Nested
    class Dado_um_carro {
        @BeforeEach
        void setup(){

        }

        @Nested
        class Quando_buscar_todos_os_carros{
            private List<Carro> carroList;

            @BeforeEach
            void setup(){
                carroList =  carroService.buscarCarroPorMarca(marca);
            }

            @Test
            void Entao_deve_retornar_uma_lista_vazia(){
                assertFalse(carroList.isEmpty());
            }

        }

        @Nested
        class Quando_sincronizar_marcas{

            @BeforeEach
            void setup(){
                FipeModeloDTO fipeModeloDTO = FipeModeloDTO.builder()
                        .codigo(1)
                        .nome("teste")
                        .build();
                carroService.sincronizarCarrosPorMarca(FipeModelosDTO.builder().modelos(List.of(fipeModeloDTO)).build(),marca);
            }

            @Test
            void Entao_deve_retornar_uma_lista_vazia(){
                verify(carroRepository,times(1)).salvar(any(),any(),any());
            }

        }

    }
}
