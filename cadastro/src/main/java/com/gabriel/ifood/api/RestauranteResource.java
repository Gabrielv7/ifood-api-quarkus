package com.gabriel.ifood.api;

import com.gabriel.ifood.domain.model.Restaurante;
import com.gabriel.ifood.domain.repository.RestauranteRepository;
import com.gabriel.ifood.service.RestauranteService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import org.jboss.resteasy.annotations.Status;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
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
    @Path("/{id}")
    public Restaurante buscar(@PathParam("id") Long id){

        return restauranteService.buscarOuFalhar(id);

    }

    @POST
    @Transactional
    public Response adicionar(Restaurante dto){

        var restauranteSalvo = restauranteService.adicionar(dto);

        return Response.status(Response.Status.CREATED).entity(restauranteSalvo)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id,Restaurante dto){

        return Response.ok(restauranteService.atualizar(id, dto)).build();

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id){

        restauranteService.deletar(id);

    }


}
