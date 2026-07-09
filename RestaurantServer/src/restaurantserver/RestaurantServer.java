/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurantserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Giffert
 */

public class RestaurantServer implements Runnable{
    String chatClient, chatServer;
    Socket incoming;
    ServerSocket server;
    Thread serverThread;
    BufferedReader in;
    DataOutputStream out;
    ArrayList<HandleSocket> clients = new ArrayList<HandleSocket>();
    /**
     * @param args the command line arguments
     */
    public RestaurantServer() {
        try
        {            
            server = new ServerSocket(6000);
            
            System.out.println("Restaurant Server berjalan...");
            System.out.println("Menunggu client...");
            
            if (serverThread == null){
                serverThread = new Thread (this, "Chat");
                serverThread.start();
            }
        } catch(Exception e) {
            System.out.println("Error di constructor: " + e);
        }  
    }
    public static void main(String[] args) {
         new RestaurantServer();
    }
    
    public void removeClient(Socket pSocket) {
        for (HandleSocket h : clients) {
            if (h.client.equals(pSocket)) {
                clients.remove(h);
                break;
            }
        }
        System.out.println("jumlah client: " + clients.size());
    }

    @Override
    public void run() {
        try {
            while (true) {
                incoming = server.accept();
                System.out.println("Client terkoneksi : " + incoming.getInetAddress());
                HandleSocket hs = new HandleSocket(this, incoming);
                hs.start();
                clients.add(hs);
            }
        } catch (Exception ex) {
            System.out.println("Error di server: " + ex);
        }
    }
    
}
