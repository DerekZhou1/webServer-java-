package com.zl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response
implements ServletResponse
{
	OutputStream os = null;
	Request request = null;
	PrintWriter pw = null;
	static String Web_Root = System.getProperty("user.dir") + File.separator + "webRoot";
	static String SHUTDOWN = "/shutdown";
	static int BUFFER_SIZE = 1024;
	static String Default_Page = "/index.html";

	public Response(Request request)
	{
		this.request = request;
	}

	public void getResponse(String url, InputStream is) {}

	public void process(OutputStream os)
	{
		this.os = os;
		String url = this.request.getUri();
		if (url.toLowerCase().startsWith(SHUTDOWN))
		{
			HttpServer.shut = true;
		}
		else if (url.toLowerCase().startsWith("/servlet/"))
		{
			processServlet(request, this);
		}
		else
		{
			sendStaticMessage(os);
		}
	}

	public void processServlet(Request request,Response response){
		String url =request.getUri();
			String servletName = url.substring(url.lastIndexOf("/")+1);
			String className = this.getClass().getPackage().getName()+'.'+servletName;
			try {
				Class servletClass = Class.forName(className);
				Servlet servlet =(Servlet)servletClass.newInstance();
				servlet.service(request, this);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public void sendStaticMessage(OutputStream os)
	{
		File file = null;
		FileInputStream fis = null;
		String url = this.request.getUri();
		int i = url.lastIndexOf("/");
		String requestName = url.substring(i);
		if (requestName.equals("/")) {
			file = new File(Web_Root, Default_Page);
		} else {
			file = new File(Web_Root, url);
		}
		if (file.exists())
		{
			int j = requestName.lastIndexOf(".");
			if (j != -1)
			{
				String postfix = requestName.substring(j + 1);
				String responseDate = "HTTP/1.1 200 OK\r\nServer: Apache-Coyote/1.1\r\nAccept-Ranges: bytes\r\nETag: W/\"1253-1502441222000\r\nLast-Modified: Fri, 11 Aug 2017 08:47:02 GMT\r\nContent-Type: text/html\r\nDate: Tue, 12 Sep 2017 07:35:13 GMT\r\n";
				switch (postfix)
				{
				case "ico": 
					responseDate = responseDate + "Content-Type: image/x-icon\r\n";
					break;
				case "html": 
					responseDate = responseDate + "Content-Type: text/html\r\n";
					break;
				case "jpg": 
					responseDate = responseDate + "Content-Type:  image/jpeg\r\n";
					break;
				}
				try
				{

					responseDate = responseDate + "\r\n";
					os.write(responseDate.getBytes());

					fis = new FileInputStream(file);
					byte[] buffer = new byte[BUFFER_SIZE];

					int m = fis.read(buffer, 0, BUFFER_SIZE);
					while (m != -1)
					{
						os.write(buffer);
						m = fis.read(buffer, 0, BUFFER_SIZE);
					}
					fis.close();
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("404");
		}
	}

	public String getCharacterEncoding()
	{
		return null;
	}

	public String getContentType()
	{
		return null;
	}

	public ServletOutputStream getOutputStream()
			throws IOException
	{
		return null;
	}

	public PrintWriter getWriter()
			throws IOException
	{
		this.pw = new PrintWriter(this.os);
		return this.pw;
	}

	public void setCharacterEncoding(String charset) {}

	public void setContentLength(int len) {}

	public void setContentType(String type) {}

	public void setBufferSize(int size) {}

	public int getBufferSize()
	{
		return 0;
	}

	public void flushBuffer()
			throws IOException
	{}

	public void resetBuffer() {}

	public boolean isCommitted()
	{
		return false;
	}

	public void reset() {}

	public void setLocale(Locale loc) {}

	public Locale getLocale()
	{
		return null;
	}
}
