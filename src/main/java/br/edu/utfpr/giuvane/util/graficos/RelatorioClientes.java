/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.util.graficos;

import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.TipoLancamento;
import java.math.BigDecimal;

/**
 *
 * @author Giuvane Conti
 */
public class RelatorioClientes {
    private Cliente cliente;
    private BigDecimal valor;
    private TipoLancamento tipo;
    
    public RelatorioClientes(Cliente cliente, BigDecimal valor, TipoLancamento tipo) {
        this.cliente = cliente;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }
    
    
   
}
