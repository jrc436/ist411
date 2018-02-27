/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import server.HttpRequest;
import view.View;

/**
 *
 * @author dr
 */
public class Controller {

    private final ModelController db;
    private final Map<String, ViewController> controllerMap; 
    private final FileController lookupFile;
    public Controller(String file) {
        controllerMap = new HashMap<>();
        db = ModelController.readDB(file);
        DefaultViewController def = new DefaultViewController();
        controllerMap.put("/hello", def);
        controllerMap.put("/address", new AddressFormController());
        AddressListController alc = new AddressListController();
        controllerMap.put("/list", alc);
        controllerMap.put("/json", alc);
        controllerMap.put("/data", alc);
        this.lookupFile = new FileController();
    }

    public View respondToLocationRequest(HttpRequest request) {
        if (controllerMap.containsKey(request.getLocation())) {
            return controlHelp(controllerMap.get(request.getLocation()), request); 
        }
        return controlHelp(lookupFile, request);      
    }
    private View controlHelp(ViewController control, HttpRequest request) {
        return control.processRequest(request.getLocation(), request.getParams(), db);
    }
    public void saveDB(String filename) {
        db.save(filename);
    }

}
