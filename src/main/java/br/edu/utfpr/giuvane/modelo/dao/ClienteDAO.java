/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import java.util.List;

/**
 *
 * @author Giuvane Conti
 */
public interface ClienteDAO {
    void salvar(Cliente categoria);
    
    void atualizar(Cliente categoria);
    
    void excluir(Cliente categoria);
    
    List<Cliente> listarTodos();
    
    Cliente ListarUm(Long id);
    
    List<Cliente> ListarFiltroLike(String like);
}
