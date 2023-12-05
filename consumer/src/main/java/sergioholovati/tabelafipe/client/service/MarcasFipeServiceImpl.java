package sergioholovati.tabelafipe.client.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sergioholovati.tabelafipe.domain.fipe.FipeModelosDTO;

@ApplicationScoped
public class MarcasFipeServiceImpl {

    @RestClient
    MarcasFipeService marcasFipeService;

    @GET
    @Path("/carros/marcas/{codigoMarca}/modelos")
    public FipeModelosDTO sincronizarCarrosPorMarca(Long codigoMarca) {
        return marcasFipeService.sincronizarCarrosPorMarca(codigoMarca);
    }
}
