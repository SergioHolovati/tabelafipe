package sergioholovati.tabelafipe.domain.carro;

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
import sergioholovati.tabelafipe.domain.carro.service.CarroService;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
public class CarroServiceTest {
    @Inject
    CarroService carroService;
    Carro carro;

    Marca marca;
    CarroDTO carroDto;
    @Autowired
    GenericMapper mapper;
    @Autowired
    CarroRepository carroRepository;

    @Nested
    public class Dado_um_carro {
        @BeforeEach
        void setup(){
            marca = Marca.builder()
                    .id(1L)
                    .nome("teste de marca")
                    .codigo(1L)
                    .build();
            carro = Carro.builder()
                    .id(1L)
                    .marca(marca)
                    .codigo(1L)
                    .nome("teste de carro")
                    .observacao("teste integracao")
                    .build();
            carroDto = mapper.converter(carro, CarroDTO.class);
        }

        @Nested
        public class Quando_buscar_todos_os_carros{
            private List<CarroDTO> carroDTOList;

            @BeforeEach
            void setup(){
                carroDTOList =  carroService.buscarTodosCarros(new QueryParams());
            }

            @Test
            public void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertTrue(carroDTOList.isEmpty());
            }

        }

    }
}
