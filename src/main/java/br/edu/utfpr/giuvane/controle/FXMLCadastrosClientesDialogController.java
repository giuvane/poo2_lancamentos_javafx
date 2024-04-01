package br.edu.utfpr.giuvane.controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.utfpr.giuvane.modelo.rn.ClienteRN;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.modelo.vo.Endereco;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Giuvane Conti
 */
public class FXMLCadastrosClientesDialogController implements Initializable {

    @FXML
    private Label lblClienteNome;
    @FXML
    private Label lblClienteCPF;
    @FXML
    private Label lblClienteSituacao;
    @FXML
    private TextField txtClienteNome;
    @FXML
    private TextField txtClienteCpf;
    @FXML
    private TextField txtEndLogradouro;
    @FXML
    private TextField txtEndNumero;
    @FXML
    private TextField txtEndComplemento;
    @FXML
    private TextField txtEndBairro;
    @FXML
    private TextField txtEndCep;
    @FXML
    private TextField txtEndCidade;
    @FXML
    private TextField txtEndEstado;
    @FXML
    private CheckBox cbClienteSituacao;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    
    private Cliente cliente;
    private Endereco endereco;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    public void handleButtonConfirmar() {
        
        if (validarEntradaDeDados()) {
            
            cliente.setNome(txtClienteNome.getText());
            cliente.setCpf(txtClienteCpf.getText());
            cliente.getEndereco().setLogradouro(txtEndLogradouro.getText());
            cliente.getEndereco().setNumero(txtEndNumero.getText());
            cliente.getEndereco().setComplemento(txtEndComplemento.getText());
            cliente.getEndereco().setBairro(txtEndBairro.getText());
            cliente.getEndereco().setCep(txtEndCep.getText());
            cliente.getEndereco().setCidade(txtEndCidade.getText());
            cliente.getEndereco().setEstado(txtEndEstado.getText());
            
            cliente.setAtivo(cbClienteSituacao.isSelected());
            
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

        if (txtClienteNome.getText() == null || txtClienteNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (txtClienteCpf.getText() == null || txtClienteCpf.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
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

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the btnConfirmarClicked
     */
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    /**
     * @param btnConfirmarClicked the btnConfirmarClicked to set
     */
    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        
        if (this.cliente.getCodigo() != null) {
            this.txtClienteNome.setText(cliente.getNome());
            this.txtClienteCpf.setText(cliente.getCpf());
            this.txtEndLogradouro.setText(cliente.getEndereco().getLogradouro());
            this.txtEndNumero.setText(cliente.getEndereco().getNumero());
            this.txtEndComplemento.setText(cliente.getEndereco().getComplemento());
            this.txtEndBairro.setText(cliente.getEndereco().getBairro());
            this.txtEndCep.setText(cliente.getEndereco().getCep());
            this.txtEndCidade.setText(cliente.getEndereco().getCidade());
            this.txtEndEstado.setText(cliente.getEndereco().getEstado());
            this.cbClienteSituacao.setSelected(cliente.getAtivo());
            
            /*
            if (cliente.getAtivo()) {
                this.cbClienteSituacao.setSelected(true);
            } else {
                this.cbClienteSituacao.setSelected(false);
            }
            */
        }
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
