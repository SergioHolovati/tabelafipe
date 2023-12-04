package sergioholovati.tabelafipe.client.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sergioholovati.tabelafipe.domain.fipe.FipeModelosDTO;


@RegisterRestClient(configKey="fipe-api")
public interface MarcasFipeService {

    @GET
    @Path("/carros/marcas/{codigoMarca}/modelos")
    FipeModelosDTO sincronizarCarrosPorMarca(@PathParam("codigoMarca") Long codigoMarca);

}
