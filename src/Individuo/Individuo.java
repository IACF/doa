/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Individuo;

import Sangue.Sangue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.IDAO;

/**
 *
 * @author italo
 */
public class Individuo implements IDAO<Object>{

    private IndividuoDAO dao;
    private String nome;
    private String cpf;
    private String rg;
    private String orgaoExp;
    private LocalDate dataNascimento;
    private String uf;
    private Boolean doadorMedula;
    private Double altura;
    private Double peso;
    private Boolean checkUp;
    private String telefone;
    private String observacoes;
    private int sangue_id;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public int getSangue_id() {
        return sangue_id;
    }

    public void setSangue_id(int sangue_id) {
        this.sangue_id = sangue_id;
    }

    public IndividuoDAO getDao() {
        return dao;
    }

    public void setDao(IndividuoDAO dao) {
        this.dao = dao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoExp() {
        return orgaoExp;
    }

    public void setOrgaoExp(String orgaoExp) {
        this.orgaoExp = orgaoExp;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = LocalDate.parse(formatDate(dataNascimento), formatador);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Boolean getDoadorMedula() {
        return doadorMedula;
    }

    public void setDoadorMedula(Boolean doadorMedula) {
        this.doadorMedula = doadorMedula;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Boolean getCheckUp() {
        return checkUp;
    }

    public void setCheckUp(Boolean checkUp) {
        this.checkUp = checkUp;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Individuo() {
        this.dao = new IndividuoDAO(this);
    }
    
    
    @Override
    public void save() {
        this.dao.save();
    }

    @Override
    public void update() {
        this.dao.update();
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Individuo find(String cpf) {
        return this.dao.find(cpf);
    }

    @Override
    public ArrayList<Object> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String formatDate(String date) {
        String[] d = date.split("-");
        return d[2]+"/"+d[1]+"/"+d[0];
    }
}
