package sergioholovati.tabelafipe.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;
import sergioholovati.tabelafipe.domain.marca.service.MarcaServiceImpl;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class MarcaControllerTest {



    @Inject
    MarcaServiceImpl marcaService;


    @BeforeAll
    static void setup() {
        MarcaDTO marcaDTO = MarcaDTO.builder().id(1L).codigo(1L).build();
        CarroDTO carroDTO = CarroDTO.builder().build();
        MarcaServiceImpl marcaService = Mockito.mock(MarcaServiceImpl.class);

        when(marcaService.buscarMarcas(any())).thenReturn(List.of(marcaDTO));
        when(marcaService.buscarMarcaDtoPorCodigo(any())).thenReturn(marcaDTO);
        when(marcaService.buscarCarrosPorCodigoMarca(any(),any())).thenReturn(List.of(carroDTO));
    }
    @Test
     void testeBuscarMarcas() {
        given()
                .when().get("api/rest/v1/marcas")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
     void testeBuscarMarcasPorCodigo() {
        given()
                .when().get("api/rest/v1/marcas/1")
                .then()
                .statusCode(404);
    }
    @Test
     void testeBuscarCarrosPorCodigoDaMarca() {
        given()
                .when().get("api/rest/v1/marcas/{codigoMarca}/carros",1)
                .then()
                .statusCode(404);
    }


    @Test
     void testeSincronizarMarca() {
        given()
                .when().get("api/rest/v1/marcas/sincronizar")
                .then()
                .statusCode(201);
    }
}
