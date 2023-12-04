package sergioholovati.tabelafipe.carro;

import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.carro.service.CarroService;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class CarroServiceTest {
    @Inject
    CarroService carroService;
    Carro carro;

    Marca marca;
    @Autowired
    GenericMapper mapper;
    @Autowired
    CarroRepository carroRepository;
    @Autowired
    MarcaRepository marcaRepository;

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
            marcaRepository.persist(marca);
            carroRepository.persist(carro);
        }

        @Nested
        public class Quando_buscar_todos_os_carros{
            private List<Carro> carroList;

            @BeforeEach
            void setup(){
                carroList =  carroService.buscarCarroPorMarca(marca);
            }

            @Test
            public void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertFalse(carroList.isEmpty());
            }

        }

    }
}
