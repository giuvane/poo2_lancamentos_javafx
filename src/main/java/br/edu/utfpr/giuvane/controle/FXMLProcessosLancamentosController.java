/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.LancamentoDAO;
import br.edu.utfpr.giuvane.modelo.dao.LancamentoDAOImpl;
import br.edu.utfpr.giuvane.modelo.rn.LancamentoRN;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.Lancamento;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableCell;
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
public class FXMLProcessosLancamentosController implements Initializable {

    @FXML
    private TableView<Lancamento> tvLancamentos;
    @FXML
    private TableColumn<Lancamento, Integer> tcLancamentoCodigo;
    @FXML
    private TableColumn<Lancamento, String> tcLancamentoTipo;
    @FXML
    private TableColumn<Lancamento, Cliente> tcLancamentoCliente;
    @FXML
    private TableColumn<Lancamento, LocalDate> tcLancamentoDtVencimento;
    @FXML
    private TableColumn<Lancamento, LocalDate> tcLancamentoDtPagamento;
    @FXML
    private TableColumn<Lancamento, Categoria> tcLancamentoCategoria;
    @FXML
    private TableColumn<Lancamento, BigDecimal> tcLancamentoValor;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    @FXML
    private Label lblLancamentoCodigo;
    @FXML
    private Label lblLancamentoDescricao;
    @FXML
    private Label lblLancamentoCliente;
    @FXML
    private Label lblLancamentoCategoria;
    @FXML
    private Label lblLancamentoObs;
    @FXML
    private Label lblLancamentoTipo;
    @FXML
    private Label lblLancamentoDtVencimento;
    @FXML
    private Label lblLancamentoDtPagamento;
    @FXML
    private Label lblLancamentoValor;
 
    private List<Lancamento> listLancamentos;
    private ObservableList<Lancamento> observableListLancamentos;
    
    private LancamentoDAO lancamentoDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lancamentoDao = new LancamentoDAOImpl();
        this.carregarTableViewLancamentos();
        
        // Utilizado para não carregar o texto "Label" nos atributos do tipo Label
        this.selecionarItemTableViewLancamentos(null);
        
        // Listener acionado quando um item do TableView é selecionado
        // newValue sempre irá passar um obj do tipo Cliente, pois o tipo genérico do TableView foi definido como Cliente
        tvLancamentos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewLancamentos(newValue));
    }
    
    public void carregarTableViewLancamentos() {
        tcLancamentoCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        tcLancamentoTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tcLancamentoCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        
        tcLancamentoDtVencimento.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
        // Lambda expression utilizada para formatar a data da célula
        tcLancamentoDtVencimento.setCellFactory(column -> {
            TableCell<Lancamento, LocalDate> cell = new TableCell<Lancamento, LocalDate>() {
                //private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        //this.setText(format.format(item));
                        this.setText(item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }
                }
            };

            return cell;
        });
        
        tcLancamentoDtPagamento.setCellValueFactory(new PropertyValueFactory("dataPagamento"));
        // Lambda expression utilizada para formatar a data da célula
        tcLancamentoDtPagamento.setCellFactory(column -> {
            TableCell<Lancamento, LocalDate> cell = new TableCell<Lancamento, LocalDate>() {
                //private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        //this.setText(format.format(item));
                    }
                }
            };

            return cell;
        });      
        
        tcLancamentoCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
        tcLancamentoValor.setCellValueFactory(new PropertyValueFactory("valor"));
        
        listLancamentos = this.lancamentoDao.listarTodos();

        this.observableListLancamentos = FXCollections.observableArrayList(listLancamentos);
        tvLancamentos.setItems(this.observableListLancamentos);
    }
    
    public void selecionarItemTableViewLancamentos(Lancamento lancamento){
        if (lancamento != null) {
            lblLancamentoCodigo.setText(String.valueOf(lancamento.getCodigo()));
            lblLancamentoDescricao.setText(lancamento.getDescricao());
            lblLancamentoCliente.setText(lancamento.getCliente().getNome());
            lblLancamentoCategoria.setText(lancamento.getCategoria().getNome());
            lblLancamentoTipo.setText(lancamento.getTipo().name());
            lblLancamentoDtVencimento.setText(lancamento.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            lblLancamentoDtPagamento.setText(lancamento.getDataPagamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            lblLancamentoValor.setText(String.format("%.2f", lancamento.getValor()));
            lblLancamentoObs.setText(lancamento.getObservacao());
            
        } else {
            lblLancamentoCodigo.setText("");
            lblLancamentoDescricao.setText("");
            lblLancamentoCliente.setText("");
            lblLancamentoCategoria.setText("");
            lblLancamentoTipo.setText("");
            lblLancamentoDtVencimento.setText("");
            lblLancamentoDtPagamento.setText("");
            lblLancamentoValor.setText("");
            lblLancamentoObs.setText("");
        }
    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Lancamento lancamento = new Lancamento();

        boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessosLancamentosDialog(lancamento);
        if (buttonConfirmarClicked) {
            lancamentoDao.salvar(lancamento);
            this.carregarTableViewLancamentos();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException {
        Lancamento lancamento = tvLancamentos.getSelectionModel().getSelectedItem();
        if (lancamento != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessosLancamentosDialog(lancamento);
            if (buttonConfirmarClicked) {
                lancamentoDao.atualizar(lancamento);
                this.carregarTableViewLancamentos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um lançamento na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Lancamento lancamento = tvLancamentos.getSelectionModel().getSelectedItem();
        if (lancamento != null) {
            lancamentoDao.excluir(lancamento);
            this.carregarTableViewLancamentos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneProcessosLancamentosDialog(Lancamento lancamento) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLProcessosLancamentosController.class.getResource("/fxml/FXMLProcessosLancamentosDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Lançamentos de Despesas e Receitas");
        Scene scene = new Scene(page);
        scene.getStylesheets().add("/styles/Styles.css");
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLProcessosLancamentosDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setLancamento(lancamento);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
        return controller.isBtnConfirmarClicked();

    }
    
}
