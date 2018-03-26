package com.zl;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer
{
  public static boolean shut = false;
  
  public static void main(String[] args)
  {
    HttpServer httpServer = new HttpServer();
    httpServer.await();
  }
  
  public void await()
  {
    ServerSocket server = null;
    
    int port = 8080;
    try
    {
      server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    }
    catch (IOException e2)
    {
      e2.printStackTrace();
    }
    System.out.println("Server started");
    while (!shut) {
      try
      {
        Socket socket = null;
        socket = server.accept();
        
        Request request = new Request(socket.getInputStream());
        Response response = new Response(request);
        request.parse();
        response.process(socket.getOutputStream());
        socket.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    try
    {
      server.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    System.out.println("server close.");
  }
}
