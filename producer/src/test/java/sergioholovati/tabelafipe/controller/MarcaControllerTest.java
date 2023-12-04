package sergioholovati.tabelafipe.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.carro.repository.CarroRepository;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;
import sergioholovati.tabelafipe.domain.marca.repository.MarcaRepository;
import sergioholovati.tabelafipe.infrastructure.mapper.GenericMapper;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MarcaControllerTest {


    @Test
    public void testeBuscarMarcas() {
        given()
                .when().get("api/rest/v1/marcas")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeBuscarMarcasPorCodigo() {
        given()
                .when().get("api/rest/v1/marcas/1")
                .then()
                .statusCode(404);
    }
    @Test
    public void testeBuscarCarrosPorCodigoDaMarca() {
        given()
                .when().get("api/rest/v1/marcas/{codigoMarca}/carros",1)
                .then()
                .statusCode(404);
    }


    @Test
    public void testeSincronizarMarca() {
        given()
                .when().get("api/rest/v1/marcas/sincronizar")
                .then()
                .statusCode(201);
    }
}
