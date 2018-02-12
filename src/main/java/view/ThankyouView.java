package view;

import model.AddressModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dr
 */
public class ThankyouView extends View {

    private final AddressModel am;
    public ThankyouView(AddressModel am) {
        this.am = am;
    }
    
    @Override
    public String makeHTML() {

        String html = "<html><body>";

        html += "Thank you, " + am.getName() + "!";
        
        html += "</body></html>";

        return html;
        
    }
    
}
