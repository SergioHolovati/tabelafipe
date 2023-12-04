package sergioholovati.tabelafipe.marca;

import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.domain.marca.service.MarcaService;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
public class MarcaServiceTest {

    @Inject
    MarcaService marcaService;
    Marca marca;
    @Autowired
    GenericMapper mapper;
    @Autowired
    MarcaRepository marcaRepository;


    @Nested
    public class Dado_uma_marca {
        @BeforeEach
        void setup() {
            marca = Marca.builder()
                    .id(1L)
                    .nome("teste de marca")
                    .codigo(1L)
                    .build();
        }

        @Nested
        public class Quando_buscar_todas_as_marcas {
            private List<Marca> marcaList;

            @BeforeEach
            void setup() {
                marcaList = new ArrayList<Marca>();
                marcaService.buscarMarcas().forEach(e -> {
                    marcaList.add(e);
                });
            }

            @Test
            public void Entao_deve_retornar_uma_lista() {
                assertTrue(marcaList.isEmpty());
            }
        }

    }

}
