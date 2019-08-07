/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doa2;

import BolsaDeSangue.BolsaDeSangue;
import Individuo.Individuo;
import Sangue.Sangue;

/**
 *
 * @author italo
 */
public class Doa2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sangue s = new Sangue();
        s.setTipoSanguineo("AB");
        s.setFatorRh("-");
        System.out.println("executou");
        Individuo i = new Individuo();
        i = i.find("85918095570");
        System.out.println(i.getNome());
        BolsaDeSangue b = new BolsaDeSangue(s.getId());
//        b.save();
        b.retirada(s.getId());
//        i.setAltura(1.9);
//        i.setCheckUp(false);
//        i.setCpf("85918095570");
//        i.setDataNascimento("08/10/1995");
//        i.setDoadorMedula(true);
//        i.setNome("José testes");
//        i.setObservacoes("alguma coisa");
//        i.setOrgaoExp("SSP");
//        i.setUf("PE");
//        i.setPeso(75.6);
//        i.setRg("1587486890");
//        i.setSangue_id(s.getId());
//        i.setTelefone("99998998888");
//        i.update();
        
    }
    
}
