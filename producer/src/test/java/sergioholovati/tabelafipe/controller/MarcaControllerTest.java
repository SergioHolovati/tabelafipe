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
    private Marca marca;
    private List<CarroDTO> carroDTOList;
    private MarcaDTO marcaDto;
    private Carro carro;

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    CarroRepository carroRepository;

    @Autowired
    GenericMapper mapper;

    @BeforeEach
    void setup(){
        carroDTOList = new ArrayList<>();
        marca = Marca.builder()
                .id(1L)
                .nome("teste de marca")
                .codigo(1L)
                .build();
        marcaRepository.persist(marca);
        carro = Carro.builder()
                .id(1L)
                .marca(marca)
                .codigo(1L)
                .nome("teste de carro")
                .observacao("teste integracao")
                .build();
        carroRepository.persist(carro);
        carroDTOList.add(mapper.converter(carro,CarroDTO.class));
        marcaDto = mapper.converter(marca, MarcaDTO.class);
    }


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
                .when().get("api/rest/v1/marcas/{codigoMarca}",1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
    @Test
    public void testeBuscarCarrosPorCodigoDaMarca() {
        given()
                .when().get("api/rest/v1/marcas/{codigoMarca}/carros",1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }


    @Test
    public void testeSincronizarMarca() {
        given()
                .when().get("api/rest/v1/marcas/sincronizar")
                .then()
                .statusCode(204);
    }
}
