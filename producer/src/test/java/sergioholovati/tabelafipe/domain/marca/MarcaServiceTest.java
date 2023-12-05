package sergioholovati.tabelafipe.domain.marca;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import sergioholovati.tabelafipe.client.service.MarcasFipeServiceImpl;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.carro.service.CarroService;
import sergioholovati.tabelafipe.domain.carro.service.CarroServiceImpl;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.domain.marca.service.MarcaService;
import sergioholovati.tabelafipe.domain.marca.service.MarcaServiceImpl;
import sergioholovati.tabelafipe.infrastructure.kafka.KafkaResource;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
 class MarcaServiceTest {

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    GenericMapper genericMapper;

    @Inject
    MarcaServiceImpl marcaService;

    @Inject
    CarroService carroService;
    @Inject
    MarcasFipeServiceImpl marcasFipeService;

    @Inject
    KafkaResource kafkaResource;

    QueryParams params;

    Carro carro;
    Marca marca;
    MarcaExtension marcaExtension;
    @BeforeEach
    void setup(){

        Marca marca = Marca.builder().id(1L).nome("teste").codigo(1L).build();
        carro = Carro.builder().id(1L).codigo(1L).nome("teste").marca(marca).build();
        marcaRepository = mock(MarcaRepository.class);
        genericMapper = mock(GenericMapper.class);
        carroService = mock(CarroService.class);
        marcasFipeService = mock(MarcasFipeServiceImpl.class);
        kafkaResource = mock(KafkaResource.class);
        marcaService = new MarcaServiceImpl(marcaRepository,carroService,marcasFipeService,kafkaResource,genericMapper);

        CarroDTO carroDTO = CarroDTO.builder().build();
        MarcaDTO marcaDTO = MarcaDTO.builder().build();

        marcaExtension = new MarcaExtension();
        marcaExtension.setNome("teste");
        marcaExtension.setCodigo(1L);

        params = new QueryParams();
        params.setIndex(1);
        params.setSize(1);

        when(carroService.buscarCarroPorMarca(any(),any())).thenReturn(List.of(carroDTO));
        when(genericMapper.converterCollection(any(),any())).thenReturn(List.of(marcaDTO));
        when(genericMapper.converter(any(),any())).thenReturn(marcaDTO);
    }


    @Nested
     class Dado_uma_marca {

        @Nested
         class Quando_buscar_todas_as_marcas{
            private List<MarcaDTO> marcaDTOS;

            @BeforeEach
            void setup(){
                marcaDTOS =  marcaService.buscarMarcas(new QueryParams());
            }

            @Test
             void Entao_deve_retornar_uma_lista(){
                assertFalse(marcaDTOS.isEmpty());
            }
        }
        @Nested
        class Quando_buscar_marcaDto_por_codigo{
            private MarcaDTO marcaDTO;

            @BeforeEach
            void setup(){
                marcaDTO =  marcaService.buscarMarcaDtoPorCodigo(1L);
            }

            @Test
            void Entao_deve_retornar_uma_lista(){
                assertNotNull(marcaDTO);
            }
        }
        @Nested
        class Quando_buscar_carros_por_codigo_marca{
            private List<CarroDTO> carroDTOS;

            @BeforeEach
            void setup(){
               carroDTOS  =  marcaService.buscarCarrosPorCodigoMarca(1L,new QueryParams());
            }

            @Test
            void Entao_deve_retornar_uma_list(){
                assertFalse(carroDTOS.isEmpty());
            }
        }
        @Nested
        class Quando_sincronizar_marcas{
            private List<MarcaDTO> marcaDTOS;

            @BeforeEach
            void setup(){
                 marcaService.sincronizarMarcas();
            }

            @Test
            void Entao_deve_chamar_a_service_do_client(){
                verify(marcasFipeService,times(1)).sincronizarMarcas();
            }
        }

    }

}
