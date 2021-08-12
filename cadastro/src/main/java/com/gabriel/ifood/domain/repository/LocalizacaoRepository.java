package com.gabriel.ifood.domain.repository;

import com.gabriel.ifood.domain.model.Localizacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocalizacaoRepository implements PanacheRepository<Localizacao> {

}
