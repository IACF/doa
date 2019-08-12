/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import BolsaDeSangue.BolsaDeSangue;
import Sangue.Sangue;
import bloodmanagementmodels.FatorRH;
import bloodmanagementmodels.TipoSangue;
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
    private Label lblAMenos, lblAMais, lblBMenos, lblBMais,
                  lblABMenos, lblABMais, lblOMenos, lblOMais;
    
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
    
    Sangue sAMais = new Sangue();
    Sangue sBMais = new Sangue();
    Sangue sABMais = new Sangue(); 
    Sangue sOMais = new Sangue();
    Sangue sAMenos = new Sangue();
    Sangue sBMenos = new Sangue(); 
    Sangue sABMenos = new Sangue();
    Sangue sOMenos = new Sangue();
    
    BolsaDeSangue AMais, BMais, ABMais, OMais,
                  AMenos, BMenos, ABMenos, OMenos;
    
    private boolean flagQtd = false, flagSangue = false;
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        limparCampos();
        BloodManagement.mudarTela("principal", 0);
    }
    
    
    @FXML
    private void retirarBolsas(){
            carregarBolsas();
            carregarQuantidades();
        
            try{
                quantidadePedida = Integer.parseInt(txtQTD.getText());
                flagQtd = true;
            }catch(NumberFormatException e){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Dado inválido digitado!!");
                dialogoErro.showAndWait();
                flagQtd = false;
            }
        
            if(flagQtd &&(quantidadePedida <= 0)){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Quantidade de bolsas inválida digitada!!");
                dialogoErro.showAndWait();
                flagQtd = false;
            }
            
            if(cbTipo.getSelectionModel().isEmpty()||cbRH.getSelectionModel().isEmpty()){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Tipo de sangue e/ou tipo de RH não " +
                                          "foram selecionados!!");
                dialogoErro.showAndWait();
                flagSangue = false;
            }else{
                flagSangue = true;
            }
            
            if(flagSangue && flagQtd){
                System.out.println("Entrei");
                switch (cbRH.getValue().getNome()) {
                    case "+":
                        switch(cbTipo.getValue().getNome()){
                            case "A":
                                quantidadeBanco = Integer.parseInt(lblAMais.getText());
                                break;
                            case "B":
                                quantidadeBanco = Integer.parseInt(lblBMais.getText());
                                break;
                            case "AB":
                                quantidadeBanco = Integer.parseInt(lblABMais.getText());
                                break;
                            case "O":
                                quantidadeBanco = Integer.parseInt(lblOMais.getText());
                                break;
                        }
                        break;
                    case "-":
                        switch(cbTipo.getValue().getNome()){
                            case "A":
                                quantidadeBanco = Integer.parseInt(lblAMenos.getText());
                                break;
                            case "B":
                                quantidadeBanco = Integer.parseInt(lblBMenos.getText());
                                break;
                            case "AB":
                                quantidadeBanco = Integer.parseInt(lblABMenos.getText());
                                break;
                            case "O":
                                quantidadeBanco = Integer.parseInt(lblOMenos.getText());
                                break;
                        }
                        break;
                    
                }
        
                if(quantidadeBanco < quantidadePedida){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Não foi possível obter a quantidade desejada!!");
                    dialogoAviso.showAndWait();
                    limparCampos();
                    BloodManagement.mudarTela("principal", 0);
                }else{
                    System.out.println("Entrei");
                    //AQUI FAZ A ALTERAÇÃO DO NÚMERO DE BOLSAS
                    
                    switch (cbRH.getValue().getNome()) {
                    case "+":
                        switch(cbTipo.getValue().getNome()){
                            case "A":
                                AMais.retirada(sAMais.getId(),quantidadePedida);
                                break;
                            case "B":
                                BMais.retirada(sBMais.getId(),quantidadePedida);
                                break;
                            case "AB":
                                ABMais.retirada(sABMais.getId(),quantidadePedida);
                                break;
                            case "O":
                                OMais.retirada(sOMais.getId(),quantidadePedida);
                                break;
                        }
                        break;
                    case "-":
                        switch(cbTipo.getValue().getNome()){
                            case "A":
                                AMenos.retirada(sAMenos.getId(),quantidadePedida);
                                break;
                            case "B":
                                BMenos.retirada(sBMenos.getId(),quantidadePedida);
                                break;
                            case "AB":
                                ABMenos.retirada(sABMenos.getId(),quantidadePedida);
                                break;
                            case "O":
                                OMenos.retirada(sOMenos.getId(),quantidadePedida);
                                break;
                        }
                        break;
                    }
                    
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("SUCESSO");
                    dialogoInfo.setHeaderText("Retirada efetuada com sucesso!!");
                    dialogoInfo.showAndWait();
                    limparCampos();
                    BloodManagement.mudarTela("principal", 0);
                }
            }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize");
        carregarTiposSangue();
        carregarFatoresRH();
        carregarBolsas();
        carregarQuantidades();
    }
    
    public void carregarTiposSangue(){
        System.out.println("tipoDeSangue");
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
        
        Sangue s = new Sangue();
        s.setFatorRh("+");
        s.setTipoSanguineo("O");
        BolsaDeSangue bb = new BolsaDeSangue(s.getId());
        lblOMais.setText(Integer.toString(bb.quantidade(s.getId())));
    }
    
    public void carregarFatoresRH(){
        System.out.println("FatorRh");
        FatorRH mais = new FatorRH(1,"+");
        FatorRH menos = new FatorRH(2,"-");
        
        rh.add(mais);
        rh.add(menos);
        
        obsRh = FXCollections.observableArrayList(rh);
        
        cbRH.setItems(obsRh);
        
    }
    
    public void carregarQuantidades(){
        
// Aqui entra a conexão com o banco, linkando com as variáveis lbl
        System.out.println("akjdhaskdjh");
        System.out.println((Integer.toString(AMais.quantidade(sAMais.getId()))));
        
//        Sangue s = new Sangue();
//        s.setFatorRh("+");
//        s.setTipoSanguineo("O");
//        BolsaDeSangue b = new BolsaDeSangue(s.getId());
//        lblOMais.setText(Integer.toString(b.quantidade(s.getId())));
//        
        
        lblAMais.setText(Integer.toString(AMais.quantidade(sAMais.getId())));
        lblBMais.setText(Integer.toString(BMais.quantidade(sBMais.getId())));
        lblABMais.setText(Integer.toString(ABMais.quantidade(sABMais.getId())));
//        lblOMais.setText(Integer.toString(OMais.quantidade(sOMais.getId())));
        
        
        lblAMenos.setText(Integer.toString(AMenos.quantidade(sAMenos.getId())));
        lblBMenos.setText(Integer.toString(BMenos.quantidade(sBMenos.getId())));
        lblABMenos.setText(Integer.toString(ABMenos.quantidade(sABMenos.getId())));
        lblOMenos.setText(Integer.toString(OMenos.quantidade(sOMenos.getId())));
        
    }
    
    public void carregarBolsas(){
        
        sAMais.setTipoSanguineo("A");
        sAMais.setFatorRh("+");
        AMais = new BolsaDeSangue(sAMais.getId());
        
        sBMais.setTipoSanguineo("B");
        sBMais.setFatorRh("+");
        BMais = new BolsaDeSangue(sBMais.getId());
        
        sABMais.setTipoSanguineo("AB");
        sABMais.setFatorRh("+");
        ABMais = new BolsaDeSangue(sBMais.getId());
        
        sOMais.setTipoSanguineo("O");
        sOMais.setFatorRh("+");
        OMais = new BolsaDeSangue(sOMais.getId());
        
        sAMenos.setTipoSanguineo("A");
        sAMenos.setFatorRh("-");
        AMenos = new BolsaDeSangue(sAMenos.getId());
        
        sBMenos.setTipoSanguineo("B");
        sBMenos.setFatorRh("-");
        BMenos = new BolsaDeSangue(sBMenos.getId());
        
        sABMenos.setTipoSanguineo("AB");
        sABMenos.setFatorRh("-");
        ABMenos = new BolsaDeSangue(sABMenos.getId());
        
        sOMenos.setTipoSanguineo("O");
        sOMenos.setFatorRh("-");
        OMenos = new BolsaDeSangue(sOMenos.getId());
    }
    
    public void limparCampos(){
        txtQTD.clear();
        cbTipo.getSelectionModel().clearSelection();
        cbRH.getSelectionModel().clearSelection();
        flagQtd = false;
        flagSangue = false;
    }
}
