/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Map;
import view.View;

/**
 *
 * @author drdea
 */
interface ViewController {
    View processRequest(String location, Map<String, String> params, ModelController db);
}
