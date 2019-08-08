/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BolsaDeSangue;

import database.DBConnection;
import database.MySQLConnection;
import java.util.ArrayList;
import model.IDAO;

/**
 *
 * @author italo
 */
public class BolsaDeSangueDAO implements IDAO<Object>{
    protected String table = "BolsasDeSangue";
    private String baseName = "doa_project";
    private DBConnection database;
    private BolsaDeSangue bolsaDeSangue;

    public BolsaDeSangueDAO(BolsaDeSangue e) {
        bolsaDeSangue = e;
        database = new MySQLConnection(baseName);
    }

    @Override
    public void save() {
        String sangue_id = Integer.toString(bolsaDeSangue.getSangue_id());
        String fields[] = {"sangue_id"};
        String values[] = {sangue_id};
        
        database.insert(table, fields, values);
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void retirada(int sangue_id) {
        String query = "DELETE FROM `" + table + "` where `sangue_id`='" + sangue_id + " 'LIMIT 1";
        database.command(query);
    }

    @Override
    public Object find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> quantidade(int sangue_id) {
        String query = "SELECT COUNT(*) FROM `" + table + "` where `sangue_id`='" + sangue_id + "'";
        ArrayList<ArrayList<String>> rs = database.query(query);
        return rs.size() > 0 ? rs.get(0) : null;
    }
}
