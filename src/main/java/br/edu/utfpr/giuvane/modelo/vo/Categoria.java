/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Giuvane Conti
 */
@Entity
@Table(name = "categoria")
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;
   
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
    
    @ElementCollection
    @CollectionTable(
            name="subcategorias",
            joinColumns = @JoinColumn(name="subcategoria_categoria_id")
    )
    @Column(name = "descricao_subcategoria")
    private List<String> subCategorias;
    
    @OneToMany(mappedBy = "categoria")
    private List<Lancamento> lancamentos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<String> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<String> subCategorias) {
        this.subCategorias = subCategorias;
    }
    
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
    
    @PrePersist
    public void prePersist() {
        System.out.println("Pré persist em categoria.....");
    }
    
    public void postPersist() {
        System.out.println("Pós persist em categoria......");
    }

    @Override
    public String toString() {
        return nome;
    }
    
    

}
