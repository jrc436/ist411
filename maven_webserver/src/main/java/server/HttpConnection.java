/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.NotFoundView;
import view.View;

/**
 *
 * @author drdea
 */
public class HttpConnection implements Runnable {
    private final Socket connectionSocket;
    private final Controller control;
    public HttpConnection(Controller control, Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
        this.control = control;
    }
    @Override
    public void run() {
        try {
            handleIncomingTcpConnection(control, connectionSocket);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());      
            Logger.getLogger(HttpConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void handleIncomingTcpConnection(Controller controller, Socket clientSocket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();
        String inputLine = br.readLine();
        if (inputLine != null) {
                
            HttpRequest req = HttpRequest.fromString(inputLine);
            if (req == null) {
                return;
            }
            System.out.println("Parsed: " + inputLine);
            View v = controller.respondToLocationRequest(req);
            if (v.isOKStatus()) {
                out.write(HttpResponse.makeOKResponse(v));
            } else {
                out.write(HttpResponse.make404response(new NotFoundView()));
            }
            System.out.println("Responded.");     
        }
        this.connectionSocket.close();
    }
}
