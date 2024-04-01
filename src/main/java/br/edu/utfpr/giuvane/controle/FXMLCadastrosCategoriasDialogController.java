/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAO;
import br.edu.utfpr.giuvane.modelo.rn.CategoriaRN;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Giuvane Conti
 */
public class FXMLCadastrosCategoriasDialogController implements Initializable {

    @FXML
    private Label lblCategoriaDescricao;
    @FXML
    private TextField txtCategoriaDescricao;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    
    private Categoria categoria;
    
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

            categoria.setNome(txtCategoriaDescricao.getText());

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

        if (txtCategoriaDescricao.getText() == null || txtCategoriaDescricao.getText().length() == 0) {
            errorMessage += "Descrição inválida!\n";
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

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }
    

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        
        if (this.categoria.getCodigo() != null) {
            this.txtCategoriaDescricao.setText(categoria.getNome());
        }
    }
    
}
