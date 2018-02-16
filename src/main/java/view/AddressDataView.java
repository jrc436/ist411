/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Iterator;
import model.AddressListModel;
import model.AddressModel;

/**
 *
 * @author drdea
 */
public class AddressDataView extends View {
    private final AddressListModel database;

    public AddressDataView(AddressListModel database) {
        this.database = database;
    }
    public static String getURLString() {
        return "/data";
    }

    @Override
    public String makeHTML() {
        String html = "<html><body>";
        html += "<ul>";
        Iterator<AddressModel> addresses = database.getAddresses();
        while (addresses.hasNext()) {
            AddressModel am = addresses.next();
            html += am.serializeToString()+System.lineSeparator();
        }

        html += "</ul>";
	
        
        html += "</body></html>";

        return html;

    }
    
}
