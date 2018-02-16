/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.View;

/**
 *
 * @author drdea
 */
public class HttpResponse {
    public static byte[] makeOKResponse(View v) {
        return makeResponse(v, ResponseEnum.OK);
    }
    public static byte[] make404response(View v) {
        return makeResponse(v, ResponseEnum.NotFound);
    }
    public static byte[] makeResponse(View v, ResponseEnum response) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringBuilder sb1 = new StringBuilder();
        sb1.append(response.getHttpResponseLine());
        sb1.append("Content-Type: ");
        sb1.append(v.getType());
        sb1.append(System.lineSeparator());
        sb1.append(System.lineSeparator());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(System.lineSeparator());
        sb2.append(System.lineSeparator());
        try {
            bos.write(sb1.toString().getBytes());
            bos.write(v.makeBytes());
            bos.write(sb2.toString().getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(HttpResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bos.toByteArray();
    }
}
