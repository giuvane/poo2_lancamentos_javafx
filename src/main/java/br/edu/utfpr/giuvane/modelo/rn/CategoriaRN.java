/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.rn;

import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAO;
import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAOImpl;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Giuvane Conti
 */
public class CategoriaRN {
    
    private CategoriaDAO categoriaDao;
    
    public CategoriaRN() {
        categoriaDao = new CategoriaDAOImpl();
    }
        
    
    public void salvar(Categoria categoria) {
        // Validações antes de salvar categoria
        try {
            categoriaDao.salvar(categoria);
        } catch (HibernateException he) {
            System.out.println(he);
        }
        
    }
    
    public List<Categoria> listaFiltroLike(String like) {
        return categoriaDao.listarFiltroLike(like);
    }
    
    public List<Categoria> listaFiltroLikeCriteria(String like) {
        return categoriaDao.listarFiltroLikeCriteria(like);
    }
    
    public List<Categoria> listaTodos() {
        return categoriaDao.listarTodos();
    }
    
    public Categoria listarUm(Long id) {
        return categoriaDao.listarUm(id);
    }
    
    public void excluir(Categoria categoria) {
        categoriaDao.excluir(categoria);
    }
    
    public void atualizar(Categoria categoria) {
        categoriaDao.atualizar(categoria);
    }
        
}
