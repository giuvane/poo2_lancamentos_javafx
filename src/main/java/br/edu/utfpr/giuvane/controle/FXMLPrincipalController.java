/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.conexao.ConexaoHibernate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Giuvane Conti
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private MenuItem miCadastrosCategorias;
    @FXML
    private MenuItem miCadastrosClientes;
    @FXML
    private MenuItem miProcessosLancamentos;
    @FXML
    private MenuItem miGraficosLancamentosMes;
    @FXML
    private MenuItem miRelatoriosQtdeProdutosEstoque;
    @FXML
    private MenuItem menuSair;
    @FXML
    private AnchorPane anchorPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManager manager = ConexaoHibernate.getInstance();
    }
    
    /**
     * Método responsável por abrir a tela de cadastro de categorias no AnchorPane da tela principal
     */
    @FXML
    public void handleMenuItemCadastroCategorias() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/FXMLCadastrosCategorias.fxml"));
        anchorPane.getChildren().setAll(ap);
    }
    
    /**
     * Método responsável por abrir a tela de cadastro de clientes no AnchorPane da tela principal
     */
    @FXML
    public void handleMenuItemCadastroClientes() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/FXMLCadastrosClientes.fxml"));
        anchorPane.getChildren().setAll(ap);
    }
    
    /**
     * Método responsável por abrir a tela de processos de lançamentos no AnchorPane da tela principal
     */
    @FXML
    public void handleMenuItemProcessosLancamentos() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/FXMLProcessosLancamentos.fxml"));
        anchorPane.getChildren().setAll(ap);
    }
    
    /**
     * Método responsável por abrir a tela de gráfico no AnchorPane da tela principal
     */
    @FXML
    public void handleMenuItemGraficosLancamentos() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/FXMLGraficosLancamentosClientes.fxml"));
        anchorPane.getChildren().setAll(ap);
    }
    
    @FXML
    public void sair() {
        System.exit(0);
    }
    
}
