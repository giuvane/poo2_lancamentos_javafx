/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import br.edu.utfpr.giuvane.modelo.dao.conexao.ConexaoHibernate;
import br.edu.utfpr.giuvane.modelo.vo.Lancamento;
import br.edu.utfpr.giuvane.util.graficos.RelatorioClientes;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Giuvane Conti
 */
public class LancamentoDAOImpl implements LancamentoDAO {

    EntityManager manager;
    
    public LancamentoDAOImpl() {
        manager = ConexaoHibernate.getInstance();
    }
    
    @Override
    public void salvar(Lancamento lancamento) {
        manager.getTransaction().begin();
        manager.persist(lancamento);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Lancamento lancamento) {
        manager.getTransaction().begin();
        manager.merge(lancamento);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(Lancamento lancamento) {
        manager.getTransaction().begin();
        manager.remove(lancamento);
        manager.getTransaction().commit();
    }

    @Override
    public List<Lancamento> listarTodos() {
        List<Lancamento> lancamentos;
        
        Query query = manager.createQuery("SELECT l FROM Lancamento l");
        lancamentos = query.getResultList();
        
        return lancamentos;
    }

    @Override
    public Lancamento ListarUm(Long id) {
        Lancamento lan;
        
        Query query = manager.createQuery("SELECT l FROM Lancamento l WHERE l.codigo = " + id);
        lan = (Lancamento)query.getSingleResult();
        
        return lan;
    }
    
    @Override
    public List<Lancamento> ListarLancPorCliente(Long id) {
        List<Lancamento> lancamentos;
        
        Query query = manager.createQuery("SELECT l FROM Lancamento l WHERE l.cliente.codigo = " + id);
        lancamentos = query.getResultList();
        
        return lancamentos;
    }
    
    @Override
    public BigDecimal somaLancamentos() {
        
        String query = "SELECT SUM(l.valor) FROM Lancamento l";
        TypedQuery<BigDecimal> typedQuery = manager.createQuery(query, BigDecimal.class);
        
        BigDecimal soma = typedQuery.getSingleResult();
        
        return soma;
    }
    
    public List<RelatorioClientes> valorTipoGastosClientes() {
        String select = "SELECT NEW br.edu.utfpr.giuvane.util.graficos.RelatorioClientes"
            + "(l.cliente, sum(l.valor), l.tipo) FROM Lancamento l"
            + " GROUP BY l.cliente, l.tipo";
        Query query = manager.createQuery(select);
        
        List<RelatorioClientes> relatorioClientes = query.getResultList();
        
        return relatorioClientes;
    }
    
}
