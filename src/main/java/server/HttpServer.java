/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author dr
 */
public class HttpServer {

    private static final String DBFILENAME = "addressserver.db.json";
    private static final int port = 12345;
    private static final int backlog = 200;
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
        System.out.println("Waiting for connection.....");
        ServerSocket socket = new ServerSocket(port, backlog);
        // make the pol about the same size as the backlog
        LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, backlog, 300, TimeUnit.SECONDS, tasks);
        server.startEventLoop(socket, pool);
    }

    private void startEventLoop(ServerSocket socket, ThreadPoolExecutor pool) throws IOException {
        while (true) {
            System.out.println("Event loop - waiting");

            Socket connectionSocket = socket.accept(); 
            
            System.out.println("Incoming connection");
            HttpConnection conn = new HttpConnection(controller, connectionSocket);
            pool.execute(conn);
        }
        
    }
   
}
