/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dr
 */
public class AddressListModel extends Model {

    private final ArrayList<AddressModel> addresses;
    public AddressListModel() {
        addresses = new ArrayList<>();
    }

    public void add(AddressModel m) {
        // guarantees AddressListModel is always valid
        if (m.isValid()) {
            addresses.add(m);   
        }
    }
    public Iterator<AddressModel> getAddresses() {
        return addresses.iterator();
        
    }
    public void serializeToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (AddressModel am : addresses) {
                pw.write(am.serializeToString() + "\n");
            }        
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(AddressListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static AddressListModel deserializeFromJson(ObjectMapper om, JsonNode node) throws JsonProcessingException {
        return om.treeToValue(node, AddressListModel.class);
    }
    public static AddressListModel readJsonFromFile(ObjectMapper om, File f) throws IOException {
        return om.readValue(f, AddressListModel.class);
    }
    
    public static AddressListModel deserializeFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            AddressListModel o = new AddressListModel();

            
            try {
                for (String line; (line = br.readLine()) != null;) {
                    // process the line.
                    AddressModel am = AddressModel.deserializeFromString(line);
                    if (am != null) {
                        o.add(am);
                    }
                }
            } catch (IOException ex) {

            }
            return o;
        } catch (FileNotFoundException ex) {
            return new AddressListModel(); // empty
        } catch (IOException ex) {
            return new AddressListModel(); // empty
        }
    }

    @Override
    public boolean isValid() {
        return true; 
    }

    
}
