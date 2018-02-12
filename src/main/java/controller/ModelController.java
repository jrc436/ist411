/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AddressListModel;
import model.AddressModel;

/**
 *
 * @author drdea
 */
public class ModelController {
    private final AddressListModel addresses;
    private final ObjectMapper om;
    
    ModelController() {
        this(new AddressListModel(), new ObjectMapper());
    }
    private ModelController(AddressListModel model, ObjectMapper om) {
        this.addresses = model;
        this.om = om;
    }
    static ModelController readDB(String file) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AddressListModel address = new AddressListModel();
        File f = new File(file);
        if (f.exists()) {
            try {
                address = AddressListModel.readJsonFromFile(om, new File(file));
            } catch (IOException ex) {
                Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Loaded: "+file);
        return new ModelController(address, om);
    }
    void addAddress(AddressModel am) {
        this.addresses.add(am);
    }
    void save(String filename) {
        try {
            addresses.saveJsonToFile(new File(filename), om);
        } catch (IOException ex) {
            Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    AddressListModel getAddresses() {
        return addresses;
    }
}
