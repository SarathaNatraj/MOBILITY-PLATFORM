package fileio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345...");
            Socket clientSocket = serverSocket.accept(); // Wait for a connection

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String message = input.readLine(); // Read message from client
            System.out.println("Received: " + message);

            output.println("Hello, client!"); // Respond to client

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
