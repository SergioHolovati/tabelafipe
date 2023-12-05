package sergioholovati.tabelafipe.domain.carro;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.carro.service.CarroServiceImpl;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@QuarkusTest
class CarroServiceTest {

    @Inject
    CarroRepository carroRepository;

    @Inject
    GenericMapper genericMapper;

    @Inject
    CarroServiceImpl carroService;

    QueryParams params;

    Carro carro;
    @BeforeEach
    void setup(){

        Marca marca = Marca.builder().id(1L).nome("teste").codigo(1L).build();
        carro = Carro.builder().id(1L).codigo(1L).nome("teste").marca(marca).build();
        carroRepository = mock(CarroRepository.class);
        genericMapper = mock(GenericMapper.class);
        carroService = new CarroServiceImpl(carroRepository,genericMapper);

        CarroDTO carroDTO = CarroDTO.builder().build();

        params = new QueryParams();
        params.setIndex(1);
        params.setSize(1);


        when(genericMapper.converterCollection(any(),any())).thenReturn(List.of(carroDTO));
        when(genericMapper.converter(any(),any())).thenReturn(carroDTO);
    }

    @Nested
     class Dado_um_carro {

        @Nested
         class Quando_buscar_todos_os_carros{
            private List<CarroDTO> carroDTOList;

            @BeforeEach
            void setup(){
                carroDTOList = carroService.buscarTodosCarros(params);
            }

            @Test
             void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertFalse(carroDTOList.isEmpty());
            }

        }
        @Nested
         class Quando_buscar_por_marca{
            private List<CarroDTO> carroDTOList;

            @BeforeEach
            void setup(){
                carroDTOList =  carroService.buscarCarroPorMarca(Marca.builder().build(),new QueryParams());
            }

            @Test
             void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertFalse(carroDTOList.isEmpty());
            }

        }
        @Nested
         class Quando_buscar_por_codigo{
            private CarroDTO carroDTO;

            @BeforeEach
            void setup(){
                carroDTO =  carroService.buscarCarroPorCodigo(1L);
            }

            @Test
             void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertNotNull(carroDTO);
            }

        }
    }
}
