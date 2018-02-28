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
    private final String dbFileName;
    private ModelController(String file) {
        this(new AddressListModel(), new ObjectMapper(), file);
    }
    private ModelController(AddressListModel model, ObjectMapper om, String file) {
        this.addresses = model;
        this.om = om;
        this.dbFileName = file;
    }
    static ModelController readDB(String file) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AddressListModel address = null;
        File f = new File(file);
        if (f.exists()) {
            try {
                address = AddressListModel.readJsonFromFile(om, new File(file));
            } catch (IOException ex) {
                Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            return new ModelController(file);
        }
        System.out.println("Loaded: "+file);
        return new ModelController(address, om, file);
    }
    void addAddress(AddressModel am) {
        synchronized(addresses) {
            this.addresses.add(am);
            this.save();
        }
    }
    private void save() {
        try {
            synchronized(addresses) {
                addresses.saveJsonToFile(new File(dbFileName), om);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    AddressListModel getAddresses() {
        synchronized(addresses) {
            return addresses;
        }
    }
}
