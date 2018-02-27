/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Map;
import view.FileView;
import view.NotFoundView;
import view.View;

/**
 *
 * @author drdea
 */
public class FileController implements ViewController {

    @Override
    public View processRequest(String location, Map<String, String> params, ModelController db) {
        View view = new FileView(location);
        if (!view.isOKStatus()) {
            view = new NotFoundView();
        }
        return view;
    }
    
}
