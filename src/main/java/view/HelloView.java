/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author drdea
 */
public class HelloView extends View {

    public static String getURLString() {
        return "/hello";
    }
     
    @Override
    public String makeHTML() {
        String html = "<html><body>";
        html += "<p>Hello world!</p>";
        
        html += "</body></html>";

        return html;
    }
    
}
