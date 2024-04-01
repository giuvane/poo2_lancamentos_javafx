/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import br.edu.utfpr.giuvane.modelo.dao.conexao.ConexaoHibernate;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Giuvane Conti
 */
public class CategoriaDAOImpl implements CategoriaDAO{
    
    EntityManager manager;
    
    public CategoriaDAOImpl() {
        manager = ConexaoHibernate.getInstance();
    }
    
    @Override
    public void salvar(Categoria categoria) {
        manager.getTransaction().begin();
        manager.persist(categoria);
        manager.getTransaction().commit();
    }
    
    @Override
    public void atualizar(Categoria categoria) {
        manager.getTransaction().begin();
        manager.merge(categoria);
        manager.getTransaction().commit();
    }
    
    @Override
    public void excluir(Categoria categoria) {
        manager.getTransaction().begin();
        manager.remove(categoria);
        manager.getTransaction().commit();
    }
    
    @Override
    public List<Categoria> listarTodos() {
        List<Categoria> categorias;
        
//      Query query = manager.createQuery("SELECT c FROM Categoria c");
        TypedQuery<Categoria> query = manager.createQuery("SELECT c FROM Categoria c", Categoria.class);
        
        categorias = query.getResultList();
        
        return categorias;
    }
    
    @Override
    public List<Categoria> listarTodosNamedQuery() {
        List<Categoria> categorias;
        
//      Query query = manager.createQuery("SELECT c FROM Categoria c");
        //TypedQuery<Categoria> query = manager.createQuery("SELECT c FROM Categoria c", Categoria.class);
        Query query = manager.createNamedQuery("Categoria.findAll");

        categorias = query.getResultList();
        
        return categorias;
    }
    
    @Override
    public List<Categoria> listarTodosCriteria() {
        List<Categoria> categorias;
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
        
        Root<Categoria> root = cq.from(Categoria.class);
        cq.select(root);
        
        TypedQuery<Categoria> query = manager.createQuery(cq);
        categorias = query.getResultList();
        return categorias;
    }
    
    @Override
    public Categoria listarUm(Long id) {
        Categoria categoria;
        
        Query query = manager.createQuery("SELECT c FROM Categoria c WHERE c.codigo = " + id);
        categoria = (Categoria)query.getSingleResult();
        
        //categoria = manager.find(Categoria.class, id);
        return categoria;
    }
    
    @Override
    public Categoria listarUmCriteria(Long id) {
        Categoria categoria;
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
        
        Root<Categoria> root = cq.from(Categoria.class);
        cq.select(root);
        
        // Criando um Predicate (Similar ao WHERE)
        Predicate predicate = cb.equal(root.get("codigo"), id);
        cq.where(predicate);
        
        TypedQuery<Categoria> query = manager.createQuery(cq);
        categoria = query.getSingleResult();
        return categoria;
    }
    
    @Override
    public List<Categoria> listarFiltroLike(String like) {
        List<Categoria> categorias;
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM Categoria c WHERE c.nome LIKE '%");
        sb.append(like);
        sb.append("%'");
        
        //Query query = manager.createQuery("SELECT c FROM Categoria c WHERE c.nome LIKE '%" + like + "%'");
        Query query = manager.createQuery(sb.toString());
        categorias = query.getResultList();
        
        //categoria = manager.find(Categoria.class, id);
        return categorias;
    }
    
    @Override
    public List<Categoria> listarFiltroLikeCriteria(String like) {
        List<Categoria> categorias;
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
        
        Root<Categoria> root = cq.from(Categoria.class);
        cq.select(root);
        
        // Criando um Predicate (Filtro utilizado no WHERE)
        Predicate predicate = cb.like(root.<String>get("nome"), "%"+like+"%");
        cq.where(predicate);
        
        TypedQuery<Categoria> query = manager.createQuery(cq);
        categorias = query.getResultList();
        return categorias;
    }
}
