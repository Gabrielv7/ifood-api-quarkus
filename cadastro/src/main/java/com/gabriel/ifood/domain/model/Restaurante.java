package com.gabriel.ifood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    private String proprietario;

    private String cnpj;


    @ManyToOne
    private Localizacao localizacao;

    @CreationTimestamp
    private Date dataCriacao;

    @UpdateTimestamp
    private Date dataAtualizacao;

}
