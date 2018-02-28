/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import server.HttpRequest;
import view.AddressDataView;
import view.AddressJsonView;
import view.AddressListView;
import view.AddressView;
import view.HelloView;
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
        controllerMap.put(HelloView.getURLString(), def);
        controllerMap.put(AddressView.getURLString(), new AddressFormController());
        AddressListController alc = new AddressListController();
        controllerMap.put(AddressListView.getURLString(), alc);
        controllerMap.put(AddressJsonView.getURLString(), alc);
        controllerMap.put(AddressDataView.getURLString(), alc);
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
}
