package sergioholovati.tabelafipe.controller;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.dto.CarroDTO;
import sergioholovati.tabelafipe.domain.marca.dto.MarcaDTO;
import sergioholovati.tabelafipe.domain.marca.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("api/rest/v1/marcas")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MarcaController {

    private final MarcaService marcaService;

    @Inject
    public MarcaController(MarcaService marcaService){
        this.marcaService = marcaService;
    }

    @GET
    public Response buscarMarcas(@BeanParam QueryParams params, @Context UriInfo uriInfo){
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(uri).entity(marcaService.buscarMarcas(params)).build();

    }

    @GET
    @Path("/{codigoMarca}")
    public Response buscarMarcasPorCodigo(@PathParam("codigoMarca")Long codigoMarca, @Context UriInfo uriInfo){
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(uri).entity(marcaService.buscarMarcaDtoPorCodigo(codigoMarca)).build();
    }

    @GET
    @Path("/{codigoMarca}/carros")
    public Response buscarCarrosPorCodigoMarca(@PathParam("codigoMarca")Long codigoMarca, @BeanParam QueryParams queryParams, @Context UriInfo uriInfo){
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok(uri).entity(marcaService.buscarCarrosPorCodigoMarca(codigoMarca, queryParams)).build();

    }

    @GET
    @Path("/sincronizar")
    @APIResponse(
        responseCode = "201",
        description = "Sincronizar base interna com base da fipe"
    )
    public Response sincronizarMarcasFipe( @Context UriInfo uriInfo){
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        marcaService.sincronizarMarcas();
        return  Response.created(uri).build();
    }
}
