/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.AddressModel;

/**
 *
 * @author dr
 */
public class AddressView extends View {

    private final AddressModel am;
    public AddressView() {
        this.am = null;
    }
    public AddressView(AddressModel am) {
        this.am = am;
    }
    public static String getURLString() {
        return "/address";
    }

    @Override
    public String makeHTML() {

        String html = "<html><body>";

        if (am == null) {
            html += "<form>Please enter your address:<br>"
                    + "Name: <input type='text' name='name'><br>"
                    + "Street: <input type='text' name='street'><br>"
                    + "State: <input type='text' name='state'><br>"
                    + "Zip: <input type='text' name='zip'><br>"
                    + "<input type='submit' value='Submit'><br>"
                    + "</form>";
        } else {
            html += String.format("<span style='color:red;'>There was an error in the form!</span><br><form>"
                    + "Name: <input type='text' value='%s' name='name'><br>"
                    + "Street: <input type='text' value='%s' name='street'><br>"
                    + "State: <input type='text' value='%s' name='state'><br>"
                    + "Zip: <input type='text' value='%s' name='zip'><br>"
                    + "<input type='submit' value='Submit'><br>"
                    + "</form>",
                    am.getName(), am.getStreet(), am.getState(), am.getZip());
        }

        html += "</body></html>";

        return html;
    }
    
    
    public String makeRowHTML () {
        return "<tr><td>"+am.getName()+"</td><td>"+am.getZip()+"</td></tr>";
    }

    public String makeBulletHTML () {
        return "<li>"+am.getName()+", "+am.getZip()+"</li>";
    }
    
}
