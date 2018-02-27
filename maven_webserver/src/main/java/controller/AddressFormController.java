/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Map;
import model.AddressModel;
import view.AddressView;
import view.ThankyouView;
import view.View;

/**
 *
 * @author drdea
 */
public class AddressFormController implements ViewController {
    @Override
    public View processRequest(String location, Map<String, String> params, ModelController db) {
        if (params.isEmpty()) {
            return new AddressView();
        }
        AddressModel am = new AddressModel(params);
        if (am.isValid()) {
            db.addAddress(am);
            return new ThankyouView(am);
        }
        else {
            return new AddressView(am);
        }
    }
}
