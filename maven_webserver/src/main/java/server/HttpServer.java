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
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import view.View;

/**
 *
 * @author dr
 */
public class HttpServer {

    private static final String DBFILENAME = "addressserver.db.json";
    private final Controller controller;

    public HttpServer() {
        controller = new Controller(DBFILENAME);
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        HttpServer server = new HttpServer();

        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Waiting for connection.....");
        server.startEventLoop(serverSocket);
    }

    private void startEventLoop(ServerSocket serverSocket) throws IOException {

        while (true) {
            System.out.println("Event loop - waiting");

            Socket connectionSocket = serverSocket.accept(); 

            System.out.println("Incoming connection");
            BufferedReader br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            OutputStream out = connectionSocket.getOutputStream();

            handleIncomingTcpConnection(br, out);
            //out.close();
        }
        
    }

    private void handleIncomingTcpConnection(BufferedReader br, OutputStream out) throws IOException {
        
        String inputLine = br.readLine();
        if (inputLine != null) {
                
            HttpRequest req = HttpRequest.fromString(inputLine);
            if (req == null) {
                return;
            }
            System.out.println("Parsed: " + inputLine);
            View v = controller.respondToLocationRequest(req);

            out.write("HTTP/1.1 ".getBytes());
            if (v.isOKStatus()) {
                out.write(("200 OK"+System.lineSeparator()).getBytes());
            } else {
                out.write(("404 Not Found"+System.lineSeparator()).getBytes());
            }
            out.write(("Content-Type: " + v.getType() + System.lineSeparator()).getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(v.makeBytes());
            out.write((System.lineSeparator()+System.lineSeparator()).getBytes());
            System.out.println("Responded.");     
        }
        controller.saveDB(DBFILENAME);
    }

}
