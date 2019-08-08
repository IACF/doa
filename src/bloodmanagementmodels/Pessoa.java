/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodmanagementmodels;

import java.time.LocalDate;

/**
 *
 * @author Igor
 */
public class Pessoa {
    
    private double peso, altura;
    private String orgaoRG, adicionais, nome, uf, tipoS, fator_rh;
    private long cpf, rg, tel;
    private boolean doadorMedula, examesOK, fezExames;
    private LocalDate nascimento;

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getOrgaoRG() {
        return orgaoRG;
    }

    public void setOrgaoRG(String orgaoRG) {
        this.orgaoRG = orgaoRG;
    }

    public String getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(String adicionais) {
        this.adicionais = adicionais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTipoS() {
        return tipoS;
    }

    public void setTipoS(String tipoS) {
        this.tipoS = tipoS;
    }

    public String getFator_rh() {
        return fator_rh;
    }

    public void setFator_rh(String fator_rh) {
        this.fator_rh = fator_rh;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public boolean isDoadorMedula() {
        return doadorMedula;
    }

    public void setDoadorMedula(boolean doadorMedula) {
        this.doadorMedula = doadorMedula;
    }

    public boolean isExamesOK() {
        return examesOK;
    }

    public void setExamesOK(boolean examesOK) {
        this.examesOK = examesOK;
    }

    public boolean isFezExames() {
        return fezExames;
    }

    public void setFezExames(boolean fezExames) {
        this.fezExames = fezExames;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
    
}
