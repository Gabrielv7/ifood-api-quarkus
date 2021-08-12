package com.gabriel.ifood.service;

import com.gabriel.ifood.domain.model.Prato;
import com.gabriel.ifood.domain.model.Restaurante;
import com.gabriel.ifood.domain.repository.PratoRepository;
import com.gabriel.ifood.domain.repository.RestauranteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@ApplicationScoped
public class RestauranteService {

    @Inject
    RestauranteRepository restauranteRepository;

    @Inject
    PratoRepository pratoRepository;

    @Transactional
    public Restaurante adicionar (Restaurante restaurante){

        restauranteRepository.persist(restaurante);

        return restaurante;

    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante dto){

        var restaurante = buscarOuFalharRestaurante(id);

        restaurante.setNome(dto.getNome());

        restauranteRepository.persist(restaurante);

        return restaurante;

    }

    @Transactional
    public void deletar(Long id){

        var restaurante = buscarOuFalharRestaurante(id);

        restauranteRepository.delete(restaurante);

    }

    public List<Prato> buscarPratos(Long id){

        var restaurante = buscarOuFalharRestaurante(id);

        return pratoRepository.list("restaurante", restaurante);
    }

    @Transactional
    public Prato adicionarPrato(Long Restauranteid, Prato prato){

        buscarOuFalharRestaurante(Restauranteid);

       Long idRestaurante = prato.getRestaurante().getId();

       var idRestaurantePayload = buscarOuFalharRestaurante(idRestaurante);

       prato.setRestaurante(idRestaurantePayload);

       pratoRepository.persist(prato);

       return prato;


    }

    @Transactional
    public Prato atualizarPrato(Long idRestaurante, Long idPrato, Prato dto){

        buscarOuFalharRestaurante(idRestaurante);

        var prato = buscarOuFalharPrato(idPrato);

        prato.setNome(dto.getNome());
        prato.setPreco(dto.getPreco());

        pratoRepository.persist(prato);

        return prato;
    }

    @Transactional
    public void deletePrato(Long idRestaurante, Long idPrato){

        buscarOuFalharRestaurante(idRestaurante);

        var prato =  buscarOuFalharPrato(idPrato);

        pratoRepository.delete(prato);

    }


    public Prato buscarOuFalharPrato (Long id){

        return pratoRepository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

    }

    public Restaurante buscarOuFalharRestaurante(Long id){

        return restauranteRepository.findByIdOptional(id).
                orElseThrow(NotFoundException::new);
    }

}
