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
                if (chat.contains("GET_LOGIN;")) {
                    String[] users = chat.split(";");
                    String role = checkLogin(users[1], users[2]);
                    if(role.equals("")) {
                        out.writeBytes("LOGIN_FAILED\n");
                    }
                    else {
                        out.writeBytes("LOGIN_SUCCESS;" + role + "\n");
                    }
                }
                else if (chat.contains("GET_REGISTER;")) {
                    String[] users = chat.split(";");
                    if(registerDB(users[1], users[2], users[3], users[4])) {
                        out.writeBytes("REGISTER_SUCCESS\n");
                    }
                    else {
                        out.writeBytes("REGISTER_FAILED\n");
                    }
                }
                else if (chat.contains("GET_DETAILS")) {
                    out.writeBytes(getDetails(chat.split(";")[1]) + "\n");
                }
                else if (chat.contains(";LOGIN")) {
                    System.out.println(chat.split(";")[0] + " LOG IN");                    
                } 
                else if (chat.contains("LOGOUT")) {
                    server.removeClient(client);
                    System.out.println("Client logout: " + client.getInetAddress());
                } 
                else {
                    
                }
            } catch (IOException ex) {
                    Logger.getLogger(HandleSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    } 

    private static Boolean registerDB(java.lang.String username, java.lang.String password, java.lang.String fullname, java.lang.String phone) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.registerDB(username, password, fullname, phone);
    }

    private static String checkLogin(java.lang.String username, java.lang.String password) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.checkLogin(username, password);
    }

    private static String getDetails(java.lang.String username) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.getDetails(username);
    }
    
    
    
}
