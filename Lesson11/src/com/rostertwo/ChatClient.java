package com.rostertwo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  private static final String SERVER_HOST = "localhost";
  private static final int SERVER_PORT = 4999;
  
  public static void main(String[] args) {
    Thread messageReaderThread = null;
    
    try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
  
      // a Runnable that reads messages from the InputStream and writes them to the console
      Runnable messageReader = () -> {
        String message;
        try {
          while (null != (message = in.readLine())) {
            System.out.println(message);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      };
      messageReaderThread = new Thread(messageReader);
      messageReaderThread.start();
      
      // get username from console and send to the server
      Scanner scanner = new Scanner(System.in);
      getAndSendUsername(out, scanner);
      
      // read messages from console and send them
      String message;
      while (!(message = scanner.nextLine()).equalsIgnoreCase("quit")) {
        out.write(message);
        out.newLine();
        out.flush();
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // interrupt messageReader thread
      messageReaderThread.interrupt();
    }
  }
  
  /**
   * Gets username from console and sends to the server
   * @param out - output stream
   * @throws IOException
   */
  private static void getAndSendUsername(BufferedWriter out, Scanner scanner) throws IOException {
    System.out.print("Enter your name: ");
    String username = scanner.nextLine();
    out.write("username:" + username);
    out.newLine();
    out.flush();
  }
  
}