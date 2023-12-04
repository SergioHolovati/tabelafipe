package sergioholovati.tabelafipe.client.service;

import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(configKey="fipe-api")
public interface MarcasFipeService {

    @GET
    @Path("/carros/marcas")
    List<MarcaExtension> sincronizarMarcas();

}
