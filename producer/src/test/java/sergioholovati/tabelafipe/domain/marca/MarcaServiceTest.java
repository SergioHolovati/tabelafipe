package sergioholovati.tabelafipe.domain.marca;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.domain.marca.service.MarcaService;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class MarcaServiceTest {

    @Inject
    MarcaService marcaService;
    Carro carro;
    Marca marca;
    CarroDTO carroDto;
    @Autowired
    GenericMapper mapper;
    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    CarroRepository carroRepository;

    @Nested
    public class Dado_uma_marca {
        @BeforeEach
        void setup(){
            marca = Marca.builder()
                    .id(1L)
                    .nome("teste de marca")
                    .codigo(1L)
                    .build();
            marcaRepository.persist(marca);
        }

        @Nested
        public class Quando_buscar_todas_as_marcas{
            private List<MarcaDTO> marcaDTOS;

            @BeforeEach
            void setup(){
                marcaDTOS =  marcaService.buscarMarcas(new QueryParams());
            }

            @Test
            public void Entao_deve_retornar_uma_lista(){
                assertFalse(marcaDTOS.isEmpty());
            }
        }

        @Nested
        public class Quando_buscar_marca_por_codigo{
            private MarcaDTO marcaDto;

            @BeforeEach
            void setup(){
                marcaDto =  marcaService.buscarMarcaDtoPorCodigo(1L);
            }

            @Test
            public void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertEquals(marcaDto.getCodigo(),1L);
            }
        }

        @Nested
        public class Dado_um_carroDto {
            @Nested
            public class Quando_buscar_carros_por_codigo_da_marca{
                private MarcaDTO marcaDto;
                private List<CarroDTO> carros;


                @BeforeEach
                void setup(){
                    carro = Carro.builder()
                            .id(1L)
                            .marca(marca)
                            .codigo(1L)
                            .nome("teste de carro")
                            .observacao("teste integracao")
                            .build();

                    carroRepository.persist(carro);
                    carros =  marcaService.buscarCarrosPorCodigoMarca(1L,new QueryParams());
                }

                @Test
                public void Entao_nao_deve_retornar_uma_lista_de_carros(){
                    assertNotNull(carros);
                }
            }
        }

    }

}
