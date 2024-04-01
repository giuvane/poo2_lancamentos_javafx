/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.util;

import br.edu.utfpr.giuvane.modelo.rn.LancamentoRN;
import br.edu.utfpr.giuvane.util.graficos.RelatorioClientes;
import java.util.List;

/**
 *
 * @author Giuvane Conti
 */
public class TesteRelatorios {
    public static void main(String[] args) {
        LancamentoRN lancamentoRN = new LancamentoRN();
    
        List<RelatorioClientes> relatorioClientes = lancamentoRN.valorTipoGastosClientes();
    }
}
