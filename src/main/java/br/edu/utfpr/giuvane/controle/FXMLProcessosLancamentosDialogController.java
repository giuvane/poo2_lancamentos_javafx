package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAO;
import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAOImpl;
import br.edu.utfpr.giuvane.modelo.dao.ClienteDAO;
import br.edu.utfpr.giuvane.modelo.dao.ClienteDAOImpl;
import br.edu.utfpr.giuvane.modelo.rn.CategoriaRN;
import br.edu.utfpr.giuvane.modelo.rn.ClienteRN;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.Lancamento;
import br.edu.utfpr.giuvane.modelo.vo.TipoLancamento;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Giuvane Conti
 */
public class FXMLProcessosLancamentosDialogController implements Initializable {
    @FXML
    private RadioButton radioLancamentoDespesa;
    @FXML
    private RadioButton radioLancamentoReceita;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ComboBox cbLancamentoCategoria;
    @FXML
    private ComboBox cbLancamentoCliente;
    @FXML
    private TextField txtLancamentoDescricao;
    @FXML
    private DatePicker dpLancamentoDtVencimento;
    @FXML
    private DatePicker dpLancamentoDtPagamento;
    @FXML
    private TextField txtLancamentoValor;
    @FXML
    private TextArea txtLancamentoObs;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;

    private List<Categoria> listCategorias;
    private List<Cliente> listClientes;
    private ObservableList<Categoria> observableListCategorias;
    private ObservableList<Cliente> observableListClientes;
    
    private CategoriaDAO categoriadao;
    private ClienteDAO clienteDao;
    
    private Lancamento lancamento;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.categoriadao = new CategoriaDAOImpl();
        this.clienteDao = new ClienteDAOImpl();
        this.lancamento = new Lancamento();
        
        this.carregarComboBoxCategorias();
        this.carregarComboBoxClientes();
        
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                    if (toggleGroup.getSelectedToggle() != null) {
                        System.out.println(new_toggle);
                    }                
                }
        });
    }
    
    
    
    public void carregarComboBoxCategorias() {
        listCategorias = categoriadao.listarTodos();
        observableListCategorias = FXCollections.observableArrayList(listCategorias);
        cbLancamentoCategoria.setItems(observableListCategorias);
    }
    
    public void carregarComboBoxClientes() {
        listClientes = clienteDao.listarTodos();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        cbLancamentoCliente.setItems(observableListClientes);
    }
    
    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            if (radioLancamentoDespesa.isSelected()) {
                this.lancamento.setTipo(TipoLancamento.DESPESA);
            } else {
                this.lancamento.setTipo(TipoLancamento.RECEITA);
            }
            this.lancamento.setCategoria((Categoria) cbLancamentoCategoria.getSelectionModel().getSelectedItem());
            this.lancamento.setCliente((Cliente) cbLancamentoCliente.getSelectionModel().getSelectedItem());
            this.lancamento.setDescricao(txtLancamentoDescricao.getText());
            this.lancamento.setDataVencimento(dpLancamentoDtVencimento.getValue());
            this.lancamento.setDataPagamento(dpLancamentoDtPagamento.getValue());
            this.lancamento.setValor(BigDecimal.valueOf(Double.valueOf(txtLancamentoValor.getText())));
            this.lancamento.setObservacao(txtLancamentoObs.getText());
            
            btnConfirmarClicked = true;
            dialogStage.close();
        }
        
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }
    
    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (cbLancamentoCategoria.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Categoria inválida!\n";
        }
        if (cbLancamentoCliente.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cliente inválido!\n";
        }
        if (dpLancamentoDtVencimento.getValue() == null) {
            errorMessage += "Data de vencimento inválida!\n";
        }
        if (dpLancamentoDtPagamento.getValue() == null) {
            errorMessage += "Data de pagamento inválida!\n";
        }
        if (txtLancamentoDescricao.getText() == null || txtLancamentoDescricao.getText().length() == 0) {
            errorMessage += "Descrição inválida!\n";
        }
        if (txtLancamentoValor.getText() == null || txtLancamentoValor.getText().length() == 0) {
            errorMessage += "Valor inválido!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    /**
     * @return the lancamento
     */
    public Lancamento getLancamento() {
        return lancamento;
    }

    /**
     * @param lancamento the lancamento to set
     */
    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }
    
}
