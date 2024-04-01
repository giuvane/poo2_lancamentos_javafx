/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.rn;

import br.edu.utfpr.giuvane.modelo.dao.ClienteDAO;
import br.edu.utfpr.giuvane.modelo.dao.ClienteDAOImpl;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Giuvane Conti
 */
public class ClienteRN {
    
    private ClienteDAO clienteDao;
    
    public ClienteRN() {
        clienteDao = new ClienteDAOImpl();
    }
    
    public void salvar(Cliente cliente) {
        // Validações antes de salvar cliente
        try {
            clienteDao.salvar(cliente);
        } catch (HibernateException he) {
            System.out.println(he); 
        }
    }
    
    public List<Cliente> listaTodos() {
        return clienteDao.listarTodos();
    }
    
    public Cliente listarUm(Long id) {
        return clienteDao.ListarUm(id);
    }
    
    public List<Cliente> listarFitroLike(String like) {
        // regra de negócio para listar clientes
        return clienteDao.ListarFiltroLike(like);
    }
    
    public void atualizar(Cliente cliente) {
        clienteDao.atualizar(cliente);
    }
    
    public void excluir(Cliente categoria) {
        clienteDao.excluir(categoria);
    }
    
}
