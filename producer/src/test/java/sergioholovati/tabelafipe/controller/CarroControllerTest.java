package sergioholovati.tabelafipe.controller;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.carro.service.CarroServiceImpl;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@QuarkusTest
class CarroControllerTest {

    @Inject
    CarroRepository carroRepository;

    @Inject
    GenericMapper genericMapper;

    @Inject
    CarroServiceImpl carroService;

    Carro carro;

    @BeforeEach
    void setup() {

        Marca marca = Marca.builder().id(1L).nome("teste").codigo(1L).build();
        carro = Carro.builder().id(1L).codigo(1L).nome("teste").marca(marca).build();
        genericMapper = mock(GenericMapper.class);
        carroService = mock(CarroServiceImpl.class);

        CarroDTO carroDTO = CarroDTO.builder().marca(marca).codigo(1L).nome("teste").id(1L).build();

        when(genericMapper.converterCollection(any(),any())).thenReturn(List.of(carroDTO));
        when(genericMapper.converter(any(),any())).thenReturn(carroDTO);

        when(carroService.buscarCarroPorCodigo(any())).thenReturn(CarroDTO.builder().build());
    }
    @Test
    void testeBuscarCarros() {
        given()
                .when().get("api/rest/v1/carro")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

}
