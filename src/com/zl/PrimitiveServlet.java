package com.zl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrimitiveServlet
  implements Servlet
{
  public void destroy() {}
  
  public ServletConfig getServletConfig()
  {
    return null;
  }
  
  public String getServletInfo()
  {
    return null;
  }
  
  public void init(ServletConfig arg0)
    throws ServletException
  {}
  
  public void service(ServletRequest request, ServletResponse response)
    throws ServletException, IOException
  {
    PrintWriter pw = response.getWriter();
    pw.println("HTTP/1.1 200 OK\r\n");
    pw.println("");
    pw.println("this is servlet");
    pw.close();
  }
}

