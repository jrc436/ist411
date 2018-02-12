/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.AddressListView;
import view.HelloView;
import view.NotFoundView;
import view.View;

/**
 *
 * @author drdea
 */
public class DefaultViewController implements ViewController {
    protected final Map<String, Class<? extends View>> urlToView;
    DefaultViewController() {
        urlToView = new HashMap<>();
        urlToView.put("/hello", HelloView.class);
    }
    @Override
    public View processRequest(String location, Map<String, String> params, ModelController db) {
        try {
            return urlToView.get(location).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DefaultViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new NotFoundView();
    }
    
}
