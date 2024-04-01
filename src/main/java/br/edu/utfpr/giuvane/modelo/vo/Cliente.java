/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.vo;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 *
 * @author Giuvane Conti
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long codigo;
    
    private String nome;
    private String cpf;
    
    @Embedded
    private Endereco endereco;
    private Boolean ativo;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Lancamento> lancamentos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    /**
     * @param lancamentos the lancamentos to set
     */
    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
    
    @PrePersist
    public void PrePersist() {
        System.out.println("Callback de pré persistência de novo cliente...");
    }
    
    @PostPersist
    public void PostPersist() {
        System.out.println("Callback de pós persistÊncia de novo cliente...");
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
