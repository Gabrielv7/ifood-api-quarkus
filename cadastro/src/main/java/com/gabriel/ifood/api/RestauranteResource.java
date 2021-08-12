package com.gabriel.ifood.api;

import com.gabriel.ifood.domain.model.Prato;
import com.gabriel.ifood.domain.model.Restaurante;
import com.gabriel.ifood.domain.repository.RestauranteRepository;
import com.gabriel.ifood.service.RestauranteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteRepository restauranteRepository;

    @Inject
    RestauranteService restauranteService;

    @GET
    public List<Restaurante> listar(){
        return restauranteRepository.listAll();
    }

    @GET
    @Path("/{restauranteId}")
    public Restaurante buscar(@PathParam("restauranteId") Long id){

        return restauranteService.buscarOuFalharRestaurante(id);

    }

    @POST
    public Response adicionar(Restaurante dto){

        var restauranteSalvo = restauranteService.adicionar(dto);

        return Response.status(Response.Status.CREATED).entity(restauranteSalvo)
                .build();
    }

    @PUT
    @Path("/{restauranteId}")
    public Response atualizar(@PathParam("restauranteId") Long id,Restaurante dto){

        return Response.ok(restauranteService.atualizar(id, dto)).build();

    }

    @DELETE
    @Path("/{restauranteId}")
    public void deletar(@PathParam("restauranteId") Long id){

        restauranteService.deletar(id);

    }

    @GET
    @Path("/{restauranteId}/pratos")
    public List<Prato> buscarPratos(@PathParam("restauranteId") Long id){

        return restauranteService.buscarPratos(id);
    }

    @POST
    @Path("/{restauranteId}/pratos")
    public Response adicionarPrato(@PathParam("restauranteId") Long id, Prato dto){

       var pratoSalvo  = restauranteService.adicionarPrato(id, dto);

       return Response.status(Response.Status.CREATED).entity(pratoSalvo).build();

    }

    @PUT
    @Path("/{restauranteId}/pratos/{idPrato}")
    public Response atualizarPrato (@PathParam("restauranteId") Long idRestaurante,
                                @PathParam("idPrato") Long idPrato,
                                Prato dto){

    var prato = restauranteService.atualizarPrato(idRestaurante,idPrato,dto);

    return Response.status(Response.Status.OK).entity(prato).build();

    }

    @DELETE
    @Path("/{restauranteId}/pratos/{idPrato}")
    public void deletePrato(@PathParam("restauranteId") Long idRestaurante,
                            @PathParam("idPrato") Long idPrato){

      restauranteService.deletePrato(idRestaurante,idPrato);

    }

}
