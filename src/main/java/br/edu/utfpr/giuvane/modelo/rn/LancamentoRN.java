/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.rn;

import br.edu.utfpr.giuvane.modelo.dao.LancamentoDAO;
import br.edu.utfpr.giuvane.modelo.dao.LancamentoDAOImpl;
import br.edu.utfpr.giuvane.modelo.vo.Lancamento;
import br.edu.utfpr.giuvane.util.graficos.RelatorioClientes;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Giuvane Conti
 */
public class LancamentoRN {
    private LancamentoDAO lancamentoDao;
    
    public LancamentoRN() {
        lancamentoDao = new LancamentoDAOImpl();
    }
    
    public void salvar(Lancamento lancamento) {
        // Validações antes de salvar lançamento
        try {
            lancamentoDao.salvar(lancamento);
        } catch (HibernateException he) {
            System.out.println(he);   
        } 
    }
    
    public void atualizar(Lancamento cliente) {
        lancamentoDao.atualizar(cliente);
    }
    
    public List<Lancamento> listaTodos() {
        return lancamentoDao.listarTodos();
    }
    
    public Lancamento listarUm(Long id) {
        return lancamentoDao.ListarUm(id);
    }
    
    public List<Lancamento> listarLancPorCliente(Long id) {
        return lancamentoDao.ListarLancPorCliente(id);
    }
    
    public void excluir(Lancamento categoria) {
        lancamentoDao.excluir(categoria);
    }
    
    public BigDecimal somaLancamentos() {
        return lancamentoDao.somaLancamentos();
    }
    
    public List<RelatorioClientes> valorTipoGastosClientes() {
        return lancamentoDao.valorTipoGastosClientes();
    }
}
