/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.util;

import br.edu.utfpr.giuvane.modelo.rn.CategoriaRN;
import br.edu.utfpr.giuvane.modelo.rn.ClienteRN;
import br.edu.utfpr.giuvane.modelo.rn.LancamentoRN;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.Endereco;
import br.edu.utfpr.giuvane.modelo.vo.Lancamento;
import br.edu.utfpr.giuvane.modelo.vo.TipoLancamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author Giuvane Conti
 */
public class TestesLancamentos {
    public static void main(String[] args) {
        LancamentoRN lancamentoRN = new LancamentoRN();
        
        ClienteRN clienteRN = new ClienteRN();
        CategoriaRN categoriaRN = new CategoriaRN();
        
        Cliente cli = clienteRN.listarUm(1L);
        Categoria cat = categoriaRN.listarUm(2L);
        
        Lancamento lancamento = new Lancamento();
        lancamento.setCliente(cli);
        lancamento.setCategoria(cat);
        lancamento.setDataPagamento(LocalDate.now());
        lancamento.setDataVencimento(LocalDate.of(2023, Month.MAY, 31));
        lancamento.setDescricao("Mouse sem fio");
        lancamento.setObservacao("Modelo x...");
        lancamento.setTipo(TipoLancamento.DESPESA);
        lancamento.setValor(new BigDecimal("70.0"));
        
        lancamentoRN.salvar(lancamento);
        
        List<Lancamento> lancs = lancamentoRN.listarLancPorCliente(1L);
        
        Cliente cliTeste = clienteRN.listarUm(1L);
        
        BigDecimal somaLancamentos = lancamentoRN.somaLancamentos();
        
        System.out.println("Soma lançamentos: " + somaLancamentos);
    }
}
