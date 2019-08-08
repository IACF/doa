/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import bloodmanagementmodels.Pessoa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Igor
 */
public class BloodManagement extends Application {
    
    private static Stage stage;
    private static Scene main, cadastro, retirar, CPF, atualizar;
    Pessoa pessoa = new Pessoa();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage = primaryStage;
        
        FXMLLoader telaPrincipal = new FXMLLoader(this.getClass().getResource("FXMLTelaPrincipal.fxml"));
        main = new Scene(telaPrincipal.load());
        
        FXMLLoader telaCadastro = new FXMLLoader(this.getClass().getResource("FXMLCadastrar_User.fxml"));
        cadastro = new Scene(telaCadastro.load());
        
        FXMLLoader telaRetirar = new FXMLLoader(this.getClass().getResource("FXMLRetirarBolsas.fxml"));
        retirar = new Scene(telaRetirar.load());
        
        FXMLLoader telaCPF = new FXMLLoader(this.getClass().getResource("FXMLValidarUser.fxml"));
        CPF = new Scene(telaCPF.load());
                
        FXMLLoader  AtualizarDoador = new FXMLLoader(this.getClass().getResource("FXMLAtualizarDoadorExistente.fxml"));
        atualizar = new Scene(AtualizarDoador.load());
        
        primaryStage.setTitle("Gerenciador de Hemocentro");
        primaryStage.setScene(main);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    public static void mudarTela(String s, int i){
        switch (i){
            case 0:
            switch (s) {
                case "principal":
                    stage.setScene(main);
                    break;
                case "cadastro":
                    stage.setScene(cadastro);
                    break;
                case "retirar":
                    stage.setScene(retirar);
                    break;
                case "CPF":
                    stage.setScene(CPF);
                    break;
                case "atualizar":
                    stage.setScene(atualizar);
                    break;
                default:
                    break;   
            }
            break;
            default:
                
                //AQUI VEREIFICAMOS SE O CARA DO CPF PODE DOAR
                
                /*
                if (!(pessoa.isFezExames())){
                    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                    dialogoErro.setTitle("ERRO");
                    dialogoErro.setHeaderText("Necessário ao candidato a doador realizar" +
                                              "exames de Check-Up!!");
                    dialogoErro.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }else if (!(pessoa.isExamesOK())){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque não passou nos exames de Check-Up");
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }else if(pessoa.getPeso() < 50){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque possui peso abaixo do permitido");
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }else if(pessoa.getAltura() < 100){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque possui altura abaixo da permitida");
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }else{
                */
                    FXMLAtualizarDoadorExistenteController.setarFlag();
                    stage.setScene(CPF);
            
              //}
        }
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
