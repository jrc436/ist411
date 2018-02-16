/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AddressListModel;
import view.AddressDataView;
import view.AddressJsonView;
import view.AddressListView;
import view.NotFoundView;
import view.View;

/**
 *
 * @author drdea
 */
public class AddressListController implements ViewController {
    protected final Map<String, Class<? extends View>> urlToView;
    public AddressListController() {
        urlToView = new HashMap<>();
        urlToView.put(AddressListView.getURLString(), AddressListView.class);
        urlToView.put(AddressDataView.getURLString(), AddressDataView.class);
        urlToView.put(AddressJsonView.getURLString(), AddressJsonView.class);
    }
    @Override
    public View processRequest(String location, Map<String, String> params, ModelController db) {
        try {
            return urlToView.get(location).getConstructor(AddressListModel.class).newInstance(db.getAddresses());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(AddressListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new NotFoundView();
    }
}
