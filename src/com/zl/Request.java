package com.zl;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Request
implements ServletRequest
{
	InputStream is = null;
	String url = "";
	static int BUFFER_SIZE = 2048;

	public Request(InputStream is)
	{
		this.is = is;
	}

	public String getUri()
	{
		return this.url;
	}

	/**
	 * 解析request
	 */
	public void parse()
	{
		byte[] buffer = new byte[BUFFER_SIZE];
		int i=-1;
		try {
			i=is.read(buffer);
			StringBuffer sb = new StringBuffer(BUFFER_SIZE);
			if(i==-1){
				System.out.println("request is empty");
			}


			for (int j = 0; j < i; j++) {
				sb.append((char)buffer[j]);
			}
			System.out.println(sb.toString());
			this.url = parseUri(sb.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String parseUri(String request)
	{
		int i = request.indexOf(" ");
		int j = request.indexOf(" ", i + 1);
		String str = request.substring(i + 1, j);
		return str;
	}

	public Object getAttribute(String name)
	{
		return null;
	}

	public Enumeration<String> getAttributeNames()
	{
		return null;
	}

	public String getCharacterEncoding()
	{
		return null;
	}

	public void setCharacterEncoding(String env)
			throws UnsupportedEncodingException
	{}

	public int getContentLength()
	{
		return 0;
	}

	public String getContentType()
	{
		return null;
	}

	public ServletInputStream getInputStream()
			throws IOException
	{
		return null;
	}

	public String getParameter(String name)
	{
		return null;
	}

	public Enumeration<String> getParameterNames()
	{
		return null;
	}

	public String[] getParameterValues(String name)
	{
		return null;
	}

	public Map<String, String[]> getParameterMap()
	{
		return null;
	}

	public String getProtocol()
	{
		return null;
	}

	public String getScheme()
	{
		return null;
	}

	public String getServerName()
	{
		return null;
	}

	public int getServerPort()
	{
		return 0;
	}

	public BufferedReader getReader()
			throws IOException
	{
		return null;
	}

	public String getRemoteAddr()
	{
		return null;
	}

	public String getRemoteHost()
	{
		return null;
	}

	public void setAttribute(String name, Object o) {}

	public void removeAttribute(String name) {}

	public Locale getLocale()
	{
		return null;
	}

	public Enumeration<Locale> getLocales()
	{
		return null;
	}

	public boolean isSecure()
	{
		return false;
	}

	public RequestDispatcher getRequestDispatcher(String path)
	{
		return null;
	}

	public String getRealPath(String path)
	{
		return null;
	}

	public int getRemotePort()
	{
		return 0;
	}

	public String getLocalName()
	{
		return null;
	}

	public String getLocalAddr()
	{
		return null;
	}

	public int getLocalPort()
	{
		return 0;
	}

	public ServletContext getServletContext()
	{
		return null;
	}

	public AsyncContext startAsync()
	{
		return null;
	}

	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
	{
		return null;
	}

	public boolean isAsyncStarted()
	{
		return false;
	}

	public boolean isAsyncSupported()
	{
		return false;
	}

	public AsyncContext getAsyncContext()
	{
		return null;
	}

	public DispatcherType getDispatcherType()
	{
		return null;
	}
}
