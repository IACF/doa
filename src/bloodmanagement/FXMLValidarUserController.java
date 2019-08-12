/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import Individuo.Individuo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Igor
 */

public class FXMLValidarUserController implements Initializable {

    @FXML
    private TextField txtCPF;
    
    private static long cpf_analisar;
    
    Individuo ind = new Individuo();
    private boolean flagCPF = false;
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        txtCPF.clear();
        BloodManagement.mudarTela("principal", 0);
    }
    
    @FXML
    private void verificarCPF(){
        
        flagCPF = false;
        
        try{
            cpf_analisar = (Long.parseLong(txtCPF.getText()));
            flagCPF = true;
        }catch(NumberFormatException e){
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("CPF inválido digitado!!");
            dialogoErro.showAndWait();
            flagCPF = false;
        }
        
        if(flagCPF){
        //AQUI FAZ A VALIDAÇÃO DO CPF NO BANCO
            if((ind.find(Long.toString(cpf_analisar)).getCpf()) == null){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("CPF digitado não foi encontrado!!");
                dialogoErro.showAndWait();
            }else{
                System.out.println("txtCPF "+txtCPF.getText());
                FXMLAtualizarDoadorExistenteController.setarCPFvalidar(txtCPF.getText());
                BloodManagement.mudarTela("atualizar", 0);        
            }
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
}
