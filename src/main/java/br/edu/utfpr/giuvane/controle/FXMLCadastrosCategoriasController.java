/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAO;
import br.edu.utfpr.giuvane.modelo.dao.CategoriaDAOImpl;
import br.edu.utfpr.giuvane.modelo.rn.CategoriaRN;
import br.edu.utfpr.giuvane.modelo.vo.Categoria;
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
public class FXMLCadastrosCategoriasController implements Initializable {

    @FXML
    private TableView<Categoria> tvCategorias;
    @FXML
    private TableColumn<Categoria, String> tcCategoriaDescricao;
    @FXML
    private Label lblCategoriaCodigo;
    @FXML
    private Label lblCategoriaDescricao;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    
    private List<Categoria> listCategorias;
    private ObservableList<Categoria> observableListCategorias;
    
    private CategoriaDAO categoriaDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.categoriaDao = new CategoriaDAOImpl();
        this.carregarTableViewCategorias();
        
        // Utilizado para não carregar o texto "Label" nos atributos do tipo Label
        this.selecionarItemTableViewCategorias(null);
        
        
        // Listener acionado quando um item do TableView é selecionado
        // newValue sempre irá passar um obj do tipo Cliente, pois o tipo genérico do TableView foi definido como Cliente
        tvCategorias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewCategorias(newValue));
    }
    
    public void carregarTableViewCategorias() {
        tcCategoriaDescricao.setCellValueFactory(new PropertyValueFactory("nome"));

        listCategorias = this.categoriaDao.listarTodos();

        this.observableListCategorias = FXCollections.observableArrayList(listCategorias);
        tvCategorias.setItems(this.observableListCategorias);
    }
    
    public void selecionarItemTableViewCategorias(Categoria categoria){
        if (categoria != null) {
            lblCategoriaCodigo.setText(String.valueOf(categoria.getCodigo()));
            lblCategoriaDescricao.setText(categoria.getNome());
            
        } else {
            lblCategoriaCodigo.setText("");
            lblCategoriaDescricao.setText("");
        }

    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Categoria categoria = new Categoria();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosCategoriasDialog(categoria);
        if (buttonConfirmarClicked) {
            categoriaDao.salvar(categoria);
            this.carregarTableViewCategorias();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Categoria categoria = tvCategorias.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosCategoriasDialog(categoria);
            if (buttonConfirmarClicked) {
                categoriaDao.atualizar(categoria);
                this.carregarTableViewCategorias();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha uma categoria na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Categoria categoria = tvCategorias.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            categoriaDao.excluir(categoria);
            carregarTableViewCategorias();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosCategoriasDialog(Categoria categoria) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastrosCategoriasDialogController.class.getResource("/fxml/FXMLCadastrosCategoriasDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Categorias");
        Scene scene = new Scene(page);
        scene.getStylesheets().add("/styles/Styles.css");
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastrosCategoriasDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCategoria(categoria);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }
    
}
