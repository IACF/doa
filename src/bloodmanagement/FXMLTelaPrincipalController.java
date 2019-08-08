/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 *
 * @author Igor
 */
public class FXMLTelaPrincipalController implements Initializable {
    
    @FXML
    private ImageView image2;

    @FXML
    private ImageView image1;
    
    @FXML
    private void abrirTelaCadastro(ActionEvent event) throws Exception{
        BloodManagement.mudarTela("cadastro", 0);
    }
    
    @FXML
    private void abrirTelaRetirada(ActionEvent event){
        BloodManagement.mudarTela("retirar", 0);
    }
    
    @FXML
    private void abrirTelaAdd(ActionEvent event){
        BloodManagement.mudarTela("CPF", 1);
    }
    
    @FXML
    private void abrirTelaUpdate(ActionEvent event){
        BloodManagement.mudarTela("CPF", 0);
    }
    
    @FXML
    private void sairPrograma(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
