/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BolsaDeSangue;

import Sangue.Sangue;
import java.util.ArrayList;
import model.IDAO;

/**
 *
 * @author italo
 */
public class BolsaDeSangue implements IDAO<Object>{
    
    private BolsaDeSangueDAO dao;
    private int id;
    private int sangue_id;
    
    public BolsaDeSangueDAO getDao() {
        return dao;
    }

    public BolsaDeSangue(int sangue_id) {
        this.setSangue_id(sangue_id);
        this.dao = new BolsaDeSangueDAO(this);
    }

    public void setDao(BolsaDeSangueDAO dao) {
        this.dao = dao;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSangue_id() {
        return sangue_id;
    }

    public void setSangue_id(int sangue_id) {
        this.sangue_id = sangue_id;
    }
    
    
    @Override
    public void save() {
        this.dao.save();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        this.dao.delete();
    }

    @Override
    public Object find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void retirada(int sangue_id) {
        this.dao.retirada(sangue_id);
    }
}
