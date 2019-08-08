/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import bloodmanagementmodels.Pessoa;
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
    
    private long cpf_analisar;
    
    Pessoa pessoa = new Pessoa();
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        BloodManagement.mudarTela("principal", 0);
    }
    
    @FXML
    private void verificarCPF(){
        
        try{
            cpf_analisar = (Long.parseLong(txtCPF.getText()));
            BloodManagement.mudarTela("atualizar", 0);
        }catch(NumberFormatException e){
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("CPF inválido digitado!!");
            dialogoErro.showAndWait();
        }
        
        //AQUI FAZER A VALIDAÇÃO DO CPF NO BANCO
        
        /*
            if(cpf_analisar != CPF DO BANCO){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("CPF digitado não foi encontrado!!");
                dialogoErro.showAndWait();
            }else{
                BloodManagement.mudarTela("atualizar", 0);
            }
        */
 
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
