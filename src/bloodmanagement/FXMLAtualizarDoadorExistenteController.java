/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import BolsaDeSangue.BolsaDeSangue;
import Individuo.Individuo;
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
    
    private double peso, altura;
    
    private String adicionais;
    
    private boolean ehDoador, fezExames, passouExames;
    
    private static boolean flag;
    
    private boolean flagAltura=false, flagPeso=false;
    
    private static String cpf_analize;
    
    public static void setarCPFvalidar(String s){
        cpf_analize = s;
        
        System.out.println(s);
        System.out.println(cpf_analize);
    }
     
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        limparCampos();
        BloodManagement.mudarTela("principal", 0);
    }
    
    public static void setarFlag(){
        flag = true;
    }
    
    @FXML
    private void atualizarDados(){
        
        Individuo ind = new Individuo();
        Individuo atual = ind.find(cpf_analize);
        
        atual.setCheckUp(cbCheckup.selectedProperty().getValue());
        atual.setCheckUp2(cbCheckup2.selectedProperty().getValue());
        atual.setDoadorMedula(cbDoador.selectedProperty().getValue());
        atual.setObservacoes(atual.getObservacoes()+". "+ txtbAdc.getText()); // Verificar
        
            if(txtPeso.getText().trim().isEmpty()||
               txtAltura.getText().trim().isEmpty()){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Campos vazios encontrados!!!");
                dialogoErro.showAndWait();
            }else{
                
                if (!(atual.getCheckUp())){
                    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                    dialogoErro.setTitle("ERRO");
                    dialogoErro.setHeaderText("Necessário ao candidato a doador realizar" +
                            "exames de Check-Up!!");
                    dialogoErro.showAndWait();
                    limparCampos();
                    BloodManagement.mudarTela("principal", 0);
                }else if (!(atual.getCheckUp2())){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque não passou nos exames de Check-Up");
                    dialogoAviso.showAndWait();
                    limparCampos();
                    BloodManagement.mudarTela("principal", 0);
                }else{
                
                    try{
                        peso = (Double.parseDouble(txtPeso.getText()));
                        flagPeso= true;
                    }catch(NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Peso inválido digitado!!");
                        dialogoErro.showAndWait();
                        flagPeso = false;
                    }
        
                    if (flagPeso && peso <=0){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Peso inválido digitado!!");
                        dialogoErro.showAndWait();
                        flagPeso = false;
                    }else if (flagPeso){
                        atual.setPeso(peso);
                        if(atual.getPeso() < 50){
                            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                            dialogoAviso.setTitle("AVISO");
                            dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                            dialogoAviso.setContentText("porque possui peso abaixo do permitido");
                            dialogoAviso.showAndWait();
                            limparCampos();
                            BloodManagement.mudarTela("principal", 0);
                        }
                    }
        
                    try{
                        altura = (Float.parseFloat(txtAltura.getText()));
                        flagAltura = true;
                    }catch(NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Altura inválida digitada!!");
                        dialogoErro.showAndWait();
                        flagAltura = false;
                    }
        
                    if(flagAltura && altura <= 0){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Altura inválida digitada!!");
                        dialogoErro.showAndWait();
                        flagAltura = false;
                    }else if(flagAltura){
                        atual.setAltura(altura);
                        if(altura < 100){
                            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                            dialogoAviso.setTitle("AVISO");
                            dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                            dialogoAviso.setContentText("porque possui altura abaixo da permitida");
                            dialogoAviso.showAndWait();
                            limparCampos();
                            BloodManagement.mudarTela("principal", 0);
                        }
                    }
                
                    if (flagAltura && flagPeso){
                        
                        //AQUI FAZ AS ATUALIZAÇÕES DO BANCO
                        
                        atual.update();
                        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                        dialogoInfo.setTitle("SUCESSO");
                        dialogoInfo.setHeaderText("Atualização foi realizada com sucesso!!");
                        dialogoInfo.showAndWait();
                        
                        if(flag){
                            
                            //AQUI FAZ A CONEXÃO COM O BANCO PARA AUMENTAR NÚMERO DE BOLSAS
                        
                            BolsaDeSangue b = new BolsaDeSangue(atual.getSangue_id());
                            b.save();
                            
                            //AQUI MOSTRA A JANELA DE SUCESSO NA OPERAÇÃO
                            
                            dialogoInfo.setTitle("SUCESSO");
                            dialogoInfo.setHeaderText("Doação foi realizada com sucesso!!");
                            dialogoInfo.showAndWait();
                        }
                        limparCampos();
                        BloodManagement.mudarTela("principal", 0);
                        
                    }
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
        lblNomeDoador.setVisible(false);
    }
    
    public void limparCampos(){
        System.out.println("funcinoa");
        txtbAdc.clear();
        txtPeso.clear();
        txtAltura.clear();
        cbDoador.setSelected(false);
        cbCheckup.setSelected(false);
        cbCheckup2.setSelected(false);
        flagAltura = false;
        flagPeso = false;
    }
}
