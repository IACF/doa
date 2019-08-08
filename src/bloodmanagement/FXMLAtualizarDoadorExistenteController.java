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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Igor
 */
public class FXMLAtualizarDoadorExistenteController implements Initializable {

    @FXML
    private TextArea txtbAdc;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtAltura;

    @FXML
    private CheckBox cbDoador;

    @FXML
    private CheckBox cbCheckup;

    @FXML
    private CheckBox cbCheckup2; 
    
    @FXML
    private Label lblNomeDoador;
    
    private float peso, altura;
    
    private String adicionais;
    
    private boolean ehDoador, fezExames, passouExames;
    
    Pessoa pessoa = new Pessoa();
    
    private static boolean flag;
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        BloodManagement.mudarTela("principal", 0);
    }
    
    public static void setarFlag(){
        flag = true;
    }
    
    @FXML
    private void atualizarDados(){
        // aqui vcs conectam as variáveis com o banco
        // aqui vcs setam as variáveis logo da pessoa
        
        pessoa.setFezExames(cbCheckup.isSelected());
        pessoa.setExamesOK(cbCheckup2.isSelected());
        pessoa.setDoadorMedula(cbDoador.isSelected());
        pessoa.setAdicionais(txtbAdc.getText());
        
        fezExames = (cbCheckup.selectedProperty().getValue());
        passouExames = (cbCheckup2.selectedProperty().getValue());
        ehDoador = (cbDoador.selectedProperty().getValue());
        adicionais = (txtbAdc.getText());
                
            if(txtPeso.getText() == null ||
               txtAltura.getText() == null){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Campos vazios encontrados!!!");
                dialogoErro.showAndWait();
            }else{
                
                if (!(fezExames)){
                    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                    dialogoErro.setTitle("ERRO");
                    dialogoErro.setHeaderText("Necessário ao candidato a doador realizar" +
                            "exames de Check-Up!!");
                    pessoa.setFezExames(fezExames);
                    dialogoErro.showAndWait();
                }else if (!(passouExames)){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque não passou nos exames de Check-Up");
                    pessoa.setExamesOK(passouExames);
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }
                
                try{
                    peso = (Float.parseFloat(txtPeso.getText()));
                }catch(NumberFormatException e){
                    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                    dialogoErro.setTitle("ERRO");
                    dialogoErro.setHeaderText("Peso inválido digitado!!");
                    dialogoErro.showAndWait();
                }
        
                if(peso < 50){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque possui peso abaixo do permitido");
                    pessoa.setPeso(peso);
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }
        
                try{
                    altura = (Float.parseFloat(txtAltura.getText()));
                }catch(NumberFormatException e){
                    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                    dialogoErro.setTitle("ERRO");
                    dialogoErro.setHeaderText("Altura inválida digitada!!");
                    dialogoErro.showAndWait();
                }
        
                if(altura < 100){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque possui altura abaixo da permitida");
                    pessoa.setAltura(altura);
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }
   
                //AQUI FAZER AS ATUALIZAÇÕES DO BANCO
                
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("SUCESSO");
                dialogoInfo.setHeaderText("Atualização foi realizada com sucesso!!");
                dialogoInfo.showAndWait();
                
                //AQUI FAZ A CONEXÃO COM O BANCO PARA AUMENTAR NÚMERO DE BOLSAS
                
                
                
                //AQUI SÓ MOSTRO A JANELA DE SUCESSO NA OPERAÇÃO
                
                if(flag){
                    dialogoInfo.setTitle("SUCESSO");
                    dialogoInfo.setHeaderText("Doação foi realizada com sucesso!!");
                    dialogoInfo.showAndWait();
                }
                
                BloodManagement.mudarTela("principal", 0);
                
            }
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblNomeDoador.setText("Jão da Silva");
        //lblNomeDoador.setText(pessoa.getNome());
    }    
    
}
