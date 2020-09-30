package com.rostertwo;

import java.io.*;
import java.net.Socket;

public class ChatClientHandlerTask implements Runnable {
  private Socket client;
  private BufferedReader in;
  private BufferedWriter out;
  String username;
  private volatile boolean exit = false;
  
  public ChatClientHandlerTask(Socket client) throws IOException {
    this.client = client;
    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
  }
  
  @Override
  public void run() {
    try {
      System.out.println("Thread started with name: " + Thread.currentThread().getName());
      String message;
  
      while (!exit) {
        message = in.readLine();

        // finish interaction when command "quit"
        if (client.isClosed() || message == null || message.equalsIgnoreCase("quit")) {
          if(!client.isClosed()){
            System.out.println(username + " left our chat");
            in.close();
            for (ChatClientHandlerTask chatClientHandlerTask : MultiClientChatServer.chatClientsList) {
              if (chatClientHandlerTask.equals(this)) {
                MultiClientChatServer.chatClientsList.remove(this);
              }
//              else if ((chatClientHandlerTask.username != null)) {
//                out.write("User " + chatClientHandlerTask.username + " left our chat");
//                out.newLine();
//                out.flush();
//              }
            }
            out.close();
            client.close();
          }
          stop();
//          break;
          
          // set username
        } else if (message.startsWith("username:")) {
          username = message.split(":")[1];
          System.out.println("New user arrived from " + Thread.currentThread().getName() + ": " + username);
          for (ChatClientHandlerTask chatClientHandlerTask : MultiClientChatServer.chatClientsList) {
            if ((chatClientHandlerTask.username != null) && !chatClientHandlerTask.equals(this)) {
              out.write("User " + chatClientHandlerTask.username + " joined our chat");
              out.newLine();
              out.flush();
            }
          }
          // send personal message
        } else if (message.startsWith("@")) {
          StringBuilder stringBuilder = new StringBuilder(message.split(",")[0]);
          String to = stringBuilder.deleteCharAt(0).toString();
          System.out.println("Received personal message from " + username + " to " + to + ": " + message);
          String finalMessage = "You have a personal message from " + username + ": " + message;
          MultiClientChatServer.chatClientsList
              .stream()
              .filter((c) -> c.username.equalsIgnoreCase(to))
              .forEach((c) -> c.send(finalMessage));
        } else {
          // send to all except to sender
          System.out.println("Received message from " + username + ": " + message);
          String finalMessage = username + ": " + message;
          MultiClientChatServer.chatClientsList
              .stream()
              .filter((c) -> c.username != null)
              .filter((c) -> !c.equals(this))
              .forEach((c) -> c.send(finalMessage));
        }
        
        
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * sends message to the client
   * @param message
   */
  private void send(String message) {
    try {
      out.write(message);
      out.newLine();
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * sets exit value to true
   */
  public void stop() {
    exit = true;
  }
  
}
