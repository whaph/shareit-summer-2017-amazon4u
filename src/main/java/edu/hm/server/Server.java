package edu.hm.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jupiter on 4/19/17.
 */
public class Server {
    public static final int PORT = 8082;


    public void listen(int port) throws IOException {
        ServerSocket servSock = new ServerSocket(port);

        while(true){
            Socket sock = servSock.accept();
            BufferedReader fromClient = new BufferedReader(
                    new InputStreamReader(sock.getInputStream()));
            String request = "";

            for (String line = fromClient.readLine();
                 line != null && line.length() > 0;
                 line = fromClient.readLine()){
                request += line;
            }

            System.out.println(request);
            //TODO parse request

            String response = ""; // add response

            BufferedWriter toClient = new BufferedWriter(
                    new OutputStreamWriter(sock.getOutputStream()));


            if(servSock.isBound()) {
                toClient.write("HTTP/1.1 200 Ok\r\n");
                toClient.write("Content-length: " + response.length() + "\r\n");
                toClient.write(response);
                toClient.write("\r\n");
                toClient.flush();

            }
        }

    }
}