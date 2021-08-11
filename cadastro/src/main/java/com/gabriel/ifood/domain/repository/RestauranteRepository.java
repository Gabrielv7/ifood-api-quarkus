package com.gabriel.ifood.domain.repository;


import com.gabriel.ifood.domain.model.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RestauranteRepository implements PanacheRepository<Restaurante> {

}
