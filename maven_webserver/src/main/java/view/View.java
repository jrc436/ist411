/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author dr
 */
public abstract class View {
    public abstract String makeHTML ();
    public byte[] makeBytes () {
        return makeHTML().getBytes();
    }
    public String getType () {
        return "text/html";
    }
    public boolean isOKStatus () {
        return true;
    }
}