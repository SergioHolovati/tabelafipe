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
                assertTrue(marcaDTOS.isEmpty());
            }
        }

    }

}
