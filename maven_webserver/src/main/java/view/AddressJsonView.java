/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Iterator;
import model.AddressListModel;

/**
 *
 * @author drdea
 */
public class AddressJsonView extends View {
     private final AddressListModel database;

    public AddressJsonView(AddressListModel database) {
        this.database = database;
    }
    public static String getURLString() {
        return "/json";
    }

    @Override
    public String makeHTML() {

        String html = "<html><body>";

        // code to make a table goes here
        // <table> ... </table>
        // each row:  <tr><td>John Doe</td><td>1000 Atherton St</td></tr>

        html += "<ul>";
        html += database.serializeToJsonString();
        html += "</ul>";
	
        
        html += "</body></html>";

        return html;

    }
}
