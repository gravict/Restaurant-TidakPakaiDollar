/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurant_tidakpakaidollar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author LEGION
 */
public class Restaurant_tidakpakaidollar implements Runnable {

    /**
     * @param args the command line arguments
     */
    Socket clientSocket;
    BufferedReader in;
    DataOutputStream out;
    public Thread clientThread;
    public String response;
    boolean running;
    
    public Restaurant_tidakpakaidollar() throws IOException
    {
        clientSocket = new Socket("localhost", 6000);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new DataOutputStream(clientSocket.getOutputStream());
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Restaurant_tidakpakaidollar app = new Restaurant_tidakpakaidollar();
        FormLogin loginForm = new FormLogin(app);
        loginForm.setVisible(true);        
    }
    
    public String getMessageFromServer() throws IOException {
        return in.readLine();
    }

    public void sendMessageToServer(String message) {
        try {
            out.writeBytes(message + "\n");
        } catch (Exception e) {
            System.out.println("Error di send message client");
        }
    }
    
    public void startThread(){
        if (clientThread == null) {
            running = true;
            clientThread = new Thread(this, "Client thread");
            clientThread.start();
        }
    }
    
    public void stopThread(){
        running = false;

        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.out.println("Error di stop thread");
        }

        clientThread = null;
    }

    @Override
    public void run() {
        while (running) {
            try {
                response = getMessageFromServer();
                
                if (response == null) {
                    System.out.println("Server menutup koneksi");
                    break;
                }

                System.out.println("Response server: " + response);

            } catch (IOException ex) {
                if (running) {
                    System.out.println("Error di thread client");
                }
                break;
            }
        }
    }
}