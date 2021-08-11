package com.gabriel.ifood.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
public class Prato{

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    private String descricao;

    @ManyToOne
    private Restaurante restaurante;

    public BigDecimal preco;


}
