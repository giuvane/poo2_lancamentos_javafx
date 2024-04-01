/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import br.edu.utfpr.giuvane.modelo.vo.Lancamento;
import br.edu.utfpr.giuvane.util.graficos.RelatorioClientes;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Giuvane Conti
 */
public interface LancamentoDAO {
    void salvar(Lancamento categoria);
    
    void atualizar(Lancamento categoria);
    
    void excluir(Lancamento categoria);
    
    List<Lancamento> listarTodos();
    
    Lancamento ListarUm(Long id);
    
    List<Lancamento> ListarLancPorCliente(Long id);
    
    BigDecimal somaLancamentos();
    
    List<RelatorioClientes> valorTipoGastosClientes();
}
