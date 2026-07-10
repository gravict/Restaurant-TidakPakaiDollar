/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantserver;

import com.restaurant.services.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giffert
 */
public class HandleSocket extends Thread{
    RestaurantServer server;
    Socket client;
    DataOutputStream out;
    BufferedReader in;
    
    public HandleSocket(RestaurantServer pParent, Socket pClient) throws IOException
    {
        this.server = pParent;
        this.client = pClient;
        out = new DataOutputStream(client.getOutputStream());
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
            
    public void sendChat(String chat) throws IOException
    {
        out.writeBytes(chat + "\n");        
    }
    
    @Override
    public void run() {
        while (true)
        {
            try {
                String chat = in.readLine();

                if (chat == null) break;

                if (chat.contains("LOGOUT")) {
                    server.handleRequest(chat, this.client);
                    break; 
                }
                else
                {
                    out.writeBytes(server.handleRequest(chat, this.client) + "\n");
                }                
            } catch (IOException ex) {
                Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    } 

    
    
    
    
}
