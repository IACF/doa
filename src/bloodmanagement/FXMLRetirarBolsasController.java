/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import bloodmanagementmodels.FatorRH;
import bloodmanagementmodels.TipoSangue;
import bloodmanagementmodels.Registros;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Igor
 */
public class FXMLRetirarBolsasController implements Initializable {

    @FXML
    private Label lblAMenos, lblAMais, lblANull,
                  lblBMenos, lblBMais, lblBNull,
                  lblABMenos, lblABMais, lblABNull,
                  lblOMenos, lblOMais, lblONull;
    
    @FXML
    private TextField txtQTD;
    
    @FXML
    private ChoiceBox<TipoSangue> cbTipo;
    
    @FXML
    private ChoiceBox<FatorRH> cbRH;
    
    private int quantidadeBanco, quantidadePedida;
    
    private final List<TipoSangue> sangue = new ArrayList<>();
    private ObservableList<TipoSangue> obsSangue;
    
    private final List<FatorRH> rh = new ArrayList<>();
    private ObservableList<FatorRH> obsRh;
    
    Registros registro = new Registros();
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        BloodManagement.mudarTela("principal", 0);
    }
    
    
    @FXML
    private void retirarBolsas(){
        
            try{
                quantidadePedida = Integer.parseInt(txtQTD.getText());
            }catch(NumberFormatException e){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Dado inválido digitado!!");
                dialogoErro.showAndWait();
            }
        
            if(quantidadePedida <= 0){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Quantidade de bolsas inválida digitada!!");
                dialogoErro.showAndWait();
            }
            
            if(cbTipo.getValue() == null ||cbRH.getValue() == null){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Tipo de sangue e/ou tipo de RH não " +
                                          "foram selecionados!!");
                dialogoErro.showAndWait();
            }
            
            switch (cbRH.getValue().getNome()) {
                case "+":
                    switch(cbTipo.getValue().getNome()){
                        case "A":
                            //quantidadeBanco = registros.getAMais();
                            quantidadeBanco = Integer.parseInt(lblAMais.getText());
                            break;
                        case "B":
                            //quantidadeBanco = registros.getBMais();
                            quantidadeBanco = Integer.parseInt(lblBMais.getText());
                            break;
                        case "AB":
                            //quantidadeBanco = registros.getABMais();
                            quantidadeBanco = Integer.parseInt(lblABMais.getText());
                            break;
                        case "O":
                            //quantidadeBanco = registros.getOMais();
                            quantidadeBanco = Integer.parseInt(lblOMais.getText());
                            break;
                    }
                    break;
                case "-":
                    switch(cbTipo.getValue().getNome()){
                        case "A":
                            //quantidadeBanco = registros.getAMenos();
                            quantidadeBanco = Integer.parseInt(lblAMenos.getText());
                            break;
                        case "B":
                            //quantidadeBanco = registros.getBMenos();
                            quantidadeBanco = Integer.parseInt(lblBMenos.getText());
                            break;
                        case "AB":
                            //quantidadeBanco = registros.getABMenos();
                            quantidadeBanco = Integer.parseInt(lblABMenos.getText());
                            break;
                        case "O":
                            //quantidadeBanco = registros.getOMenos();
                            quantidadeBanco = Integer.parseInt(lblOMenos.getText());
                            break;
                    }
                    break;
                default:
                    switch(cbTipo.getValue().getNome()){
                        case "A":
                            //quantidadeBanco = registros.getANull();
                            quantidadeBanco = Integer.parseInt(lblANull.getText());
                            break;
                        case "B":
                            //quantidadeBanco = registros.getBNull();
                            quantidadeBanco = Integer.parseInt(lblBNull.getText());
                            break;
                        case "AB":
                            //quantidadeBanco = registros.getABNull();
                            quantidadeBanco = Integer.parseInt(lblABNull.getText());
                            break;
                        case "O":
                            //quantidadeBanco = registros.getONull();
                            quantidadeBanco = Integer.parseInt(lblONull.getText());
                            break;
                    }
                    break;
            }
        
            if(quantidadeBanco < quantidadePedida){
                Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
                dialogoErro.setTitle("AVISO");
                dialogoErro.setHeaderText("Não foi possível obter a quantidade desejada!!");
                dialogoErro.showAndWait();
                BloodManagement.mudarTela("principal", 0);
            }else{
                
                //AQUI FAZ A ALTERAÇÃO DO NÚMERO DE BOLSAS
                
                Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
                dialogoErro.setTitle("SUCESSO");
                dialogoErro.setHeaderText("Retirada efetuada com sucesso!!");
                dialogoErro.showAndWait();
                BloodManagement.mudarTela("principal", 0);
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTiposSangue();
        carregarFatoresRH();
        carregarQuantidades();
    }    
    
    public void carregarTiposSangue(){
        
        TipoSangue a = new TipoSangue(1, "A");
        TipoSangue b = new TipoSangue(2, "B");
        TipoSangue ab = new TipoSangue(3, "AB");
        TipoSangue o = new TipoSangue(4, "O");
        
        sangue.add(a);
        sangue.add(b);
        sangue.add(ab);
        sangue.add(o);
        
        obsSangue = FXCollections.observableArrayList(sangue);
        
        cbTipo.setItems(obsSangue);
    }
    
    public void carregarFatoresRH(){
        
        FatorRH mais = new FatorRH(1,"+");
        FatorRH menos = new FatorRH(2,"-");
        FatorRH nulo = new FatorRH(3,"Null");
        
        rh.add(mais);
        rh.add(menos);
        rh.add(nulo);
        
        obsRh = FXCollections.observableArrayList(rh);
        
        cbRH.setItems(obsRh);
        
    }
    
    public void carregarQuantidades(){
        // aqui entra a conexão com o banco, linkando com as variáveis lbl
        String teste = "olá";
        
        //lblAMais.setText(Integer.toString(registro.getAMais()));
        lblAMais.setText(teste);
        //lblBMais.setText(Integer.toString(registro.getBMais()));
        lblBMais.setText(teste);
        //lblABMais.setText(Integer.toString(registro.getABMais()));
        lblABMais.setText(teste);
        //lblOMais.setText(Integer.toString(registro.getOMais()));
        lblOMais.setText(teste);
        //lblAMenos.setText(Integer.toString(registro.getAMenos()));
        lblAMenos.setText(teste);
        //lblBMenos.setText(Integer.toString(registro.getBMenos()));
        lblBMenos.setText(teste);
        //lblABMenos.setText(Integer.toString(registro.getABMenos()));
        lblABMenos.setText(teste);
        //lblOMenos.setText(Integer.toString(registro.getOMenos()));
        lblOMenos.setText(teste);
        //lblANull.setText(Integer.toString(registro.getANull()));
        lblANull.setText(teste);
        //lblBNull.setText(Integer.toString(registro.getBNull()));
        lblBNull.setText(teste);
        //lblABNull.setText(Integer.toString(registro.getABNull()));
        lblABNull.setText(teste);
        //lblONull.setText(Integer.toString(registro.getONull()));
        lblONull.setText(teste);
    }
}
