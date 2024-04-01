/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.ClienteDAO;
import br.edu.utfpr.giuvane.modelo.dao.ClienteDAOImpl;
import br.edu.utfpr.giuvane.modelo.rn.ClienteRN;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.Endereco;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Giuvane Conti
 */
public class FXMLCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableColumn<Cliente, String> tcClienteNome;
    @FXML
    private TableColumn<Cliente, String> tcClienteCpf;
    @FXML
    private Label lblClienteCodigo;
    @FXML
    private Label lblClienteNome;
    @FXML
    private Label lblClienteCpf;
    @FXML
    private Label lblClienteEndereco;
    @FXML
    private Label lblClienteSituacao;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableClientes;
    
    private ClienteDAO clienteDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDAOImpl();
        this.carregarTableViewClientes();
        
        // Utilizado para não carregar o texto "Label" nos atributos do tipo Label
        this.selecionarItemTableViewClientes(null);
        
        
        // Listener acionado quando um item do TableView é selecionado
        // newValue sempre irá passar um obj do tipo Cliente, pois o tipo genérico do TableView foi definido como Cliente
        tvClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
                
    }
    
    public void carregarTableViewClientes() {
        tcClienteNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tcClienteCpf.setCellValueFactory(new PropertyValueFactory("cpf"));

        listClientes = this.clienteDao.listarTodos();

        this.observableClientes = FXCollections.observableArrayList(listClientes);
        tvClientes.setItems(this.observableClientes);
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente){
        if (cliente != null) {
            lblClienteCodigo.setText(String.valueOf(cliente.getCodigo()));
            lblClienteNome.setText(cliente.getNome());
            lblClienteCpf.setText(cliente.getCpf());
            lblClienteEndereco.setText(cliente.getEndereco().toString());
            lblClienteSituacao.setText(cliente.getAtivo() ? "Ativo" : "Inativo");
        } else {
            lblClienteCodigo.setText("");
            lblClienteNome.setText("");
            lblClienteCpf.setText("");
            lblClienteEndereco.setText("");
            lblClienteSituacao.setText("");
        }

    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        cliente.setEndereco(endereco);
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
        if (buttonConfirmarClicked) {
            clienteDao.salvar(cliente);
            this.carregarTableViewClientes();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
            if (buttonConfirmarClicked) {
                clienteDao.atualizar(cliente);
                this.carregarTableViewClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDao.excluir(cliente);
            this.carregarTableViewClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastrosClientesDialogController.class.getResource("/fxml/FXMLCadastrosClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        scene.getStylesheets().add("/styles/Styles.css");
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastrosClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
        return controller.isBtnConfirmarClicked();

    }
    
}
