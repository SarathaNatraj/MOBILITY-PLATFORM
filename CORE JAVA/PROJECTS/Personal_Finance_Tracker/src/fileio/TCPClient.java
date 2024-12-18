package fileio;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            output.println("Hello, server!"); // Send message to server
            String response = input.readLine(); // Receive response from server
            System.out.println("Server Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
