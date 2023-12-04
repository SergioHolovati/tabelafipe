package sergioholovati.tabelafipe.controller;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.controller.request.CarroRequest;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.carro.service.CarroService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("api/rest/v1/carro")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class CarroController {

    private final CarroService carroService;

    @Inject
    public CarroController(CarroService carroService) {

        this.carroService = carroService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response buscarCarros(@BeanParam QueryParams queryParam, @Context UriInfo uriInfo){
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(uri).entity(carroService.buscarTodosCarros(queryParam)).build();
    }

    @GET
    @Path("/{codigoCarro}")
    @Produces(APPLICATION_JSON)
    public Response buscarCarroPorCodigo(@PathParam("codigoCarro")Long codigoCarro, @Context UriInfo uriInfo){
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(uri).entity(carroService.buscarCarroPorCodigo(codigoCarro)).build();
    }

    @PUT
    @Path("/atualizar/{carroId}")
    @Consumes(APPLICATION_JSON)
    public Response atualizarCarro(@PathParam("carroId") Long carroId, CarroRequest carroRequest, @Context UriInfo uriInfo){
      URI uri = uriInfo.getAbsolutePathBuilder().build();
      return Response.accepted(uri).entity(carroService.atualizarCarro(carroId,carroRequest)).build();
    }
}
