package sergioholovati.tabelafipe.client.service;

import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class MarcasFipeServiceImpl {

    @Inject
    @RestClient
    MarcasFipeService marcasFipeService;

    @GET
    @Path("/carros/marcas")
    public List<MarcaExtension> sincronizarMarcas() {
        return marcasFipeService.sincronizarMarcas();
    }
}
