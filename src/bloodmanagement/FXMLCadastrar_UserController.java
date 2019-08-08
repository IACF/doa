/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagement;

import bloodmanagementmodels.Estados;
import bloodmanagementmodels.FatorRH;
import bloodmanagementmodels.TipoSangue;
import bloodmanagementmodels.Pessoa;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Igor
 */
public class FXMLCadastrar_UserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private final List<TipoSangue> sangue = new ArrayList<>();
    private ObservableList<TipoSangue> obsSangue;
    
    private final List<FatorRH> rh = new ArrayList<>();
    private ObservableList<FatorRH> obsRh;
    
    private final List<Estados> estados = new ArrayList<>();
    private ObservableList<Estados> obsEstados;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtRG;

    @FXML
    private TextField txtOrgao;

    @FXML
    private ChoiceBox<Estados> spUF;

    @FXML
    private DatePicker dpNasc;

    @FXML
    private TextField txtAltura;

    @FXML
    private CheckBox chDoador;

    @FXML
    private TextField txtPeso;

    @FXML
    private ChoiceBox<TipoSangue> cbTipo;

    @FXML
    private ChoiceBox<FatorRH> cbRH;

    @FXML
    private CheckBox chCheckup;

    @FXML
    private TextField txtTel;

    @FXML
    private TextArea txtbAdc;
    
    @FXML
    private CheckBox chCheckup2;
    
    private double peso, altura;
    
    private long cpf, rg, tel;
    
    private final LocalDate sistema = LocalDate.now();
    
    private LocalDate nascimento;
    
    @FXML
    private void voltarTelaInincial(ActionEvent event) throws Exception{
        BloodManagement.mudarTela("principal", 0);
    }
    
    @FXML
    private void cadastrarDoador(ActionEvent event) throws Exception{
            
            if(txtNome.getText() == null ||
               txtCPF.getText() == null ||
               txtRG.getText() == null ||
               txtOrgao.getText() == null ||
               spUF.getValue() == null ||
               dpNasc.getValue() == null ||
               cbTipo.getValue() == null ||
               cbRH.getValue() == null ||
               txtAltura.getText() == null ||
               txtPeso.getText() == null ||
               txtTel.getText() == null ||
               dpNasc.getValue() == null){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Campos vazios encontrados!!!");
                dialogoErro.showAndWait();
            }else if (!(chCheckup.selectedProperty().getValue())){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Necessário ao candidato a doador" + 
                                          "realizar exames de Check-Up!!");
                dialogoErro.showAndWait();
            }else if (!(chCheckup2.selectedProperty().getValue())){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("AVISO");
                dialogoAviso.setHeaderText("Candidato a doador incapacitado de doar sangue,");
                dialogoAviso.setContentText("porque não passou nos exames de Check-Up");
                dialogoAviso.showAndWait();
                BloodManagement.mudarTela("principal", 0);
            }else{
            
            try{
                nascimento = dpNasc.getValue();
            }catch(NumberFormatException e){
                Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Data de nascimento inválida, verificar campo\n" +
                                          "antes de confirmar inscrição!");
                dialogoErro.showAndWait();
            }
            
            if((sistema.getYear() - dpNasc.getValue().getYear()) <= 18){
                if (((sistema.getYear() - dpNasc.getValue().getYear()) == 18)&&
                   ((sistema.getDayOfYear() - dpNasc.getValue().getDayOfYear()) < 0)){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Candidato a doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque possui idade inferior a 18 anos");
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }
            }else if ((sistema.getYear() - dpNasc.getValue().getYear()) >= 69){
                if (((sistema.getYear() - dpNasc.getValue().getYear()) == 69)&&
                   ((sistema.getDayOfYear() - dpNasc.getValue().getDayOfYear()) >= 0)){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("AVISO");
                    dialogoAviso.setHeaderText("Candidato a doador incapacitado de doar sangue,");
                    dialogoAviso.setContentText("porque possui idade superior a 69 anos");
                    dialogoAviso.showAndWait();
                    BloodManagement.mudarTela("principal", 0);
                }
            }else{           
                    try{
                        peso = Float.parseFloat(txtPeso.getText());
                    }catch(NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Peso inválido digitado!!");
                        dialogoErro.showAndWait();
                    }
            
                    if (peso < 50){
                        Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                        dialogoAviso.setTitle("AVISO");
                        dialogoAviso.setHeaderText("Candidato a doador incapacitado de doar sangue,");
                        dialogoAviso.setContentText("porque possui peso abaixo do permitido");
                        dialogoAviso.showAndWait();
                        BloodManagement.mudarTela("principal", 0);
                    }
        
                    try{
                        altura = (Float.parseFloat(txtAltura.getText()));
                    }catch(NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Altura inválido digitada!!");
                        dialogoErro.showAndWait();
                    }
        
                    if (altura < 100){
                        Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                        dialogoAviso.setTitle("AVISO");
                        dialogoAviso.setHeaderText("Candidato a doador incapacitado de doar sangue,");
                        dialogoAviso.setContentText("porque possui altura abaixo da permitida");
                        dialogoAviso.showAndWait();
                        BloodManagement.mudarTela("principal", 0);
                    }
        
                    try{
                        rg = (Long.parseLong(txtRG.getText()));
                    }catch (NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("RG inválido digitado!!");
                        dialogoErro.showAndWait();
                    }
        
                    try{
                        tel = (Long.parseLong(txtTel.getText()));
                    }catch (NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("Telefone inválido digitado!!");
                        dialogoErro.showAndWait();
                    }
         
                    try{
                        cpf = (Long.parseLong(txtCPF.getText()));
                    }catch(NumberFormatException e){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("CPF inválido digitado!!");
                        dialogoErro.showAndWait();
                    }
                    
                    //IF PARA GARANTIR QUE O CPF TEM 11 DÍGITOS, PODE PRECISAR 
                    //DE MODIFICAÇÃO
                    
                    if(cpf/10000000000L == 0 || cpf/1000000000000L > 0){
                        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
                        dialogoErro.setTitle("ERRO");
                        dialogoErro.setHeaderText("CPF inválido digitado!!");
                        dialogoErro.showAndWait();
                    }
                    
                    Pessoa pessoa = new Pessoa();
                    
                    pessoa.setDoadorMedula(chDoador.selectedProperty().getValue());
                    pessoa.setAdicionais(txtbAdc.getText());
                    pessoa.setOrgaoRG(txtOrgao.getText());
                    pessoa.setNome(txtNome.getText());
                    pessoa.setUf(spUF.getValue().getNome());
                    pessoa.setTipoS(cbTipo.getValue().getNome());
                    pessoa.setFator_rh(cbRH.getValue().getNome());
                    pessoa.setExamesOK(chCheckup2.isSelected());
                    pessoa.setFezExames(chCheckup.isSelected());
                    
                    //USEI VARIÁVEIS POR CAUSA DOS TRY/CATCH, PRA GARANTIR QUE
                    //A INSTÂNCIA PESSOA VAI TER O VALOR CORRETO
                    
                    pessoa.setNascimento(nascimento);
                    pessoa.setPeso(peso);
                    pessoa.setAltura(altura);
                    pessoa.setRg(rg);
                    pessoa.setTel(tel);
                    pessoa.setCpf(cpf);
                    
                    
                    //AQUI ADICIONA O DOADOR NO BANCO
                    
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("SUCESSO");
                    dialogoInfo.setHeaderText("Atualização foi realizada com sucesso!!");
                    dialogoInfo.showAndWait();
                    
                    BloodManagement.mudarTela("principal", 0);
                }
            }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTiposSangue();
        carregarFatoresRH();
        carregarUF();
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
    
    public void carregarUF(){
        Estados estado1 = new Estados(1,"AC");
        Estados estado2 = new Estados(2,"AL");
        Estados estado3 = new Estados(3,"AM");
        Estados estado4 = new Estados(4,"AP");
        Estados estado5 = new Estados(5,"BA");
        Estados estado6 = new Estados(6,"CE");
        Estados estado7 = new Estados(7,"DF");
        Estados estado8 = new Estados(8,"ES");
        Estados estado9 = new Estados(9,"GO");
        Estados estado10 = new Estados(10,"MA");
        Estados estado11 = new Estados(11,"MG");
        Estados estado12 = new Estados(12,"MS");
        Estados estado13 = new Estados(13,"MT");
        Estados estado14 = new Estados(14,"PA");
        Estados estado15 = new Estados(15,"PB");
        Estados estado16 = new Estados(16,"PE");
        Estados estado17 = new Estados(17,"PI");
        Estados estado18 = new Estados(18,"PR");
        Estados estado19 = new Estados(19,"RJ");
        Estados estado20 = new Estados(20,"RN");
        Estados estado21 = new Estados(21,"RO");
        Estados estado22 = new Estados(22,"RR");
        Estados estado23 = new Estados(23,"RS");
        Estados estado24 = new Estados(24,"SC");
        Estados estado25 = new Estados(25,"SE");
        Estados estado26 = new Estados(26,"SP");
        Estados estado27 = new Estados(27,"TO");
        
        estados.add(estado1);
        estados.add(estado2);
        estados.add(estado3);
        estados.add(estado4);
        estados.add(estado5);
        estados.add(estado6);
        estados.add(estado7);
        estados.add(estado8);
        estados.add(estado9);
        estados.add(estado10);
        estados.add(estado11);
        estados.add(estado12);
        estados.add(estado13);
        estados.add(estado14);
        estados.add(estado15);
        estados.add(estado16);
        estados.add(estado17);
        estados.add(estado18);
        estados.add(estado19);
        estados.add(estado20);
        estados.add(estado21);
        estados.add(estado22);
        estados.add(estado23);
        estados.add(estado24);
        estados.add(estado25);
        estados.add(estado26);
        estados.add(estado27);
        
        obsEstados = FXCollections.observableArrayList(estados);
        
        spUF.setItems(obsEstados);
    }
}
