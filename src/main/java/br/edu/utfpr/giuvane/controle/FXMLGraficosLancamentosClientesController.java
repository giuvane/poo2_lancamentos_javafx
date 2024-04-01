/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.controle;

import br.edu.utfpr.giuvane.modelo.dao.ClienteDAO;
import br.edu.utfpr.giuvane.modelo.dao.ClienteDAOImpl;
import br.edu.utfpr.giuvane.modelo.dao.LancamentoDAO;
import br.edu.utfpr.giuvane.modelo.dao.LancamentoDAOImpl;
import br.edu.utfpr.giuvane.modelo.vo.Cliente;
import br.edu.utfpr.giuvane.util.graficos.RelatorioClientes;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Giuvane Conti
 */
public class FXMLGraficosLancamentosClientesController implements Initializable {

    @FXML
    private BarChart<String, BigDecimal> barChart;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private NumberAxis numberAxis;
    
    private LancamentoDAO lancamentoDao;
    private List<RelatorioClientes> relatorioClientes;
    
    private ObservableList<String> observableListClientes;
    private List<String> listClientes = new ArrayList<String>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lancamentoDao = new LancamentoDAOImpl();
        
        relatorioClientes = lancamentoDao.valorTipoGastosClientes();
        
        Cliente cliAtual = new Cliente();
        for (RelatorioClientes rc : relatorioClientes) {
            if (rc.getCliente().equals(cliAtual)) {
                
            } else {
                listClientes.add(rc.getCliente().getNome());
                cliAtual = rc.getCliente();
            }
            
        }
        
        observableListClientes = FXCollections.observableArrayList(listClientes);
        
        //categoryAxis.setCategories(observableListClientes);
        Long idAux = 0L;
        boolean primeiro = true;
        XYChart.Series<String, BigDecimal> series = new XYChart.Series<>();
        for (RelatorioClientes rc : relatorioClientes) {
            
            if (primeiro) {
                series.setName(rc.getCliente().getNome());
                series.getData().add(new XYChart.Data(rc.getTipo().toString(), rc.getValor()));
                idAux = rc.getCliente().getCodigo();
            }
            
            if (!rc.getCliente().getCodigo().equals(idAux) && !primeiro ) {
                barChart.getData().add(series);
                idAux = rc.getCliente().getCodigo();
                series = new XYChart.Series<>();
                series.setName(rc.getCliente().getNome());
                series.getData().add(new XYChart.Data(rc.getTipo().toString(), rc.getValor()));
            } else if (!primeiro) {
                series.setName(rc.getCliente().getNome());
                series.getData().add(new XYChart.Data(rc.getTipo().toString(), rc.getValor()));
            }
            
            primeiro = false;   
        }
        barChart.getData().add(series);
                   
    }    
    
}
