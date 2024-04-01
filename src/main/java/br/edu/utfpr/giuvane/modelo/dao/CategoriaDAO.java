/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import java.util.List;

/**
 *
 * @author Giuvane Conti
 */
public interface CategoriaDAO {
    void salvar(Categoria categoria);
    
    void atualizar(Categoria categoria);
    
    void excluir(Categoria categoria);
    
    List<Categoria> listarTodos();
    
    List<Categoria> listarTodosNamedQuery();
    
    List<Categoria> listarTodosCriteria();
    
    List<Categoria> listarFiltroLike(String like);
    
    List<Categoria> listarFiltroLikeCriteria(String like);
    
    Categoria listarUm(Long id);
    
    Categoria listarUmCriteria(Long id);
}
