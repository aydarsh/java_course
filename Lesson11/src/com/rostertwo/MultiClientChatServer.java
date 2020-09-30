package com.rostertwo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;

/**
 * 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов. Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения. Каждое сообщение сервер подписываем именем клиента и рассылает всем клиентам.
 * 2. Усовершенствовать задание 1:
 *  a. добавить возможность отправки личных сообщений.
 *  b. добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */
public class MultiClientChatServer {
  private static final int SERVER_PORT = 4999;
  private static final int POOL_SIZE = 5;
  static List<ChatClientHandlerTask> chatClientsList = new CopyOnWriteArrayList<>();
  
  public static void main(String[] args) {
    ExecutorService executorService = null;
    try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
      executorService = Executors.newFixedThreadPool(POOL_SIZE);
      System.out.println("Waiting for clients");
      while (true) {
        Socket socket = serverSocket.accept();
        ChatClientHandlerTask chatClientHandlerTask = new ChatClientHandlerTask(socket);
        chatClientsList.add(chatClientHandlerTask);
        executorService.execute(chatClientHandlerTask);
      }
      
    } catch (IOException e) {
      System.out.println("Exception caught when trying to listen on port "
          + SERVER_PORT + " or listening for a connection");
      System.out.println(e.getMessage());
    } finally {
      if (executorService != null) {
        executorService.shutdown();
      }
    }
  } // main
}
