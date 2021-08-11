package com.gabriel.ifood.service;

import com.gabriel.ifood.domain.model.Restaurante;
import com.gabriel.ifood.domain.repository.RestauranteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class RestauranteService {

    @Inject
    RestauranteRepository restauranteRepository;

    public Restaurante adicionar (Restaurante restaurante){

        restauranteRepository.persist(restaurante);

        return restaurante;

    }

    public Restaurante atualizar(Long id, Restaurante dto){

        var restaurante = buscarOuFalhar(id);

        restaurante.setNome(dto.getNome());

        restauranteRepository.persist(restaurante);

        return restaurante;

    }

    public void deletar(Long id){

        var restaurante = buscarOuFalhar(id);

        restauranteRepository.delete(restaurante);

    }


    public Restaurante buscarOuFalhar(Long id){

        return restauranteRepository.findByIdOptional(id).
                orElseThrow(NotFoundException::new);
    }

}
