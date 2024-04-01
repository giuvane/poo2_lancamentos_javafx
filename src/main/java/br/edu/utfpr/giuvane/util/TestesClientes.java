/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.util;

import br.edu.utfpr.giuvane.modelo.dao.conexao.ConexaoHibernate;
import br.edu.utfpr.giuvane.modelo.dao.generic.GenericDAO;
import br.edu.utfpr.giuvane.modelo.dao.generic.GenericDAOImpl;
import br.edu.utfpr.giuvane.modelo.rn.ClienteRN;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.Endereco;
import javax.persistence.EntityManager;

/**
 *
 * @author Giuvane Conti
 */
public class TestesClientes {
    public static void main(String[] args) {
        ClienteRN clienteRN = new ClienteRN();
        
        GenericDAO<Cliente> genericDao = new GenericDAOImpl<Cliente>();
        
        Endereco end = new Endereco();
        end.setBairro("Centro");
        end.setCep("85892-000");
        end.setComplemento("apto 04");
        end.setEstado("PR");
        end.setLogradouro("Av. Curitiba");
        end.setNumero("681");
        
        Cliente cliente = new Cliente();
        cliente.setEndereco(end);
        cliente.setNome("Giuvane Conti");
        cliente.setCpf("054.099.389-17");
        cliente.setAtivo(Boolean.TRUE);
        
        clienteRN.salvar(cliente);
        
        Cliente cliente2 = new Cliente();
        cliente2.setEndereco(end);
        cliente2.setNome("João");
        cliente2.setCpf("011.211.311-45");
        cliente2.setAtivo(Boolean.TRUE);
        
        genericDao.save(cliente2);
        
        ConexaoHibernate.close();
    }
}
