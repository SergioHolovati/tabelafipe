package sergioholovati.tabelafipe.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sergioholovati.tabelafipe.controller.request.CarroRequest;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CarroControllerTest {

    private Marca marca;
    private CarroDTO carroDto;
    private CarroRequest carroRequest;
    private Carro carro;
    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    CarroRepository carroRepository;

    @Autowired
    GenericMapper mapper;

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
        carroDto = mapper.converter(carro, CarroDTO.class);
        carroRequest = CarroRequest.builder()
                .observacao("teste de observacao")
                .build();
    }

    @Test
    public void testeBuscarCarros() {
        given()
                .when().get("api/rest/v1/carro")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeBuscarCarrosPorCodigo() {
        given()
                .when().get("api/rest/v1/carro/{codigo}", 1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeAtualizarObservacao() {
               given()
                .body("{\"observacao\": \"teste de update\"}")
                .header("Content-Type", "application/json")
                .when()
                .put("api/rest/v1/carro/atualizar/{id}",1)
                .then()
                .statusCode(200);
    }

}
