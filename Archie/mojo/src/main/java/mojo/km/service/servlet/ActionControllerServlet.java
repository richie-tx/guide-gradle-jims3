/*
 * Created on May 7, 2004
 *  
 */
package mojo.km.service.servlet;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.struts.action.ActionServlet;

import mojo.km.context.ContextManager;
import mojo.km.context.Session;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.logging.LogUtil;
import mojo.km.security.IUserInfo;
import mojo.km.security.helper.SecurityUtil;
import mojo.tools.code.KeyWord;

public class ActionControllerServlet extends ActionServlet
{
	private static final String MAX_QUERY_ELAPSED_TIME_STRING = System.getProperty("jims2.log.perf.httprequest");

	private static long MAX_QUERY_ELAPSED_TIME = -1;

	private static final long MILLION = 1000000;

	/**
	 * Constructor for ActionControllerServlet.
	 */
	public ActionControllerServlet()
	{
		super();
	}

	/**
	 * @see org.apache.struts.action.ActionServlet#init()
	 */
	public void init() throws ServletException
	{
		super.init();

		if (MAX_QUERY_ELAPSED_TIME_STRING != null)
		{
			try
			{
				MAX_QUERY_ELAPSED_TIME = Long.valueOf(MAX_QUERY_ELAPSED_TIME_STRING).longValue();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionServlet#process(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void process(HttpServletRequest aRequest, HttpServletResponse aResponse) throws IOException,
			ServletException
	{
		IUserInfo user = SecurityUtil.getCurrentUser();

		this.setSubsystem(aRequest);

		StringBuilder baseBuffer = new StringBuilder(100);
		StringBuilder buffer = new StringBuilder(100);

		long starttime = System.nanoTime();

		baseBuffer.append(LogUtil.EXECUTE_PROCESS);
		baseBuffer.append(KeyWord.SPACE);
		baseBuffer.append(aRequest.getMethod());
		baseBuffer.append(KeyWord.SPACE);
		baseBuffer.append(aRequest.getRequestURI());
		baseBuffer.append(KeyWord.SPACE);
		baseBuffer.append(user.getJIMSLogonId());
		baseBuffer.append(KeyWord.SPACE);
		baseBuffer.append(user.getJIMS2LogonId());

		buffer.append("BEGIN ");
		buffer.append(baseBuffer);
		LogUtil.log(Level.DEBUG, buffer.toString());
		try
		{
			super.process(aRequest, aResponse);
		}
		finally
		{
			long endtime = System.nanoTime();
			long elapsedTime = (endtime - starttime) / MILLION;
			buffer = new StringBuilder(100);
			buffer.append("END ");
			buffer.append(baseBuffer);
			buffer.append(" | elapsedTime: ");
			buffer.append(elapsedTime);
			buffer.append(" ms");
			String bufferString = buffer.toString();
			LogUtil.log(Level.DEBUG, bufferString);

			if (MAX_QUERY_ELAPSED_TIME != -1 && elapsedTime > MAX_QUERY_ELAPSED_TIME)
			{
				this.logPerformance(elapsedTime, aRequest);
			}

			ContextManager.releaseCurrent();
		}
	}

	private void setSubsystem(HttpServletRequest aRequest)
	{
		String requestURI = aRequest.getRequestURI();
		String subsystem = null;
		String sourceName = null;
		if (requestURI.indexOf("JuvenileWarrants") != -1)
		{
			subsystem = "JW";
			sourceName = "Juvenile Warrants";
		}
		else if (requestURI.indexOf("JuvenileCasework") != -1)
		{
			subsystem = "JC";
			sourceName = "Juvenile Casework";
		}
		else if (requestURI.indexOf("CommonSupervision") != -1)
		{
			subsystem = "CS";
			sourceName = "Common Supervision";
		}
		else if (requestURI.indexOf("CommonFunctionality") != -1)
		{
			subsystem = "CF";
			sourceName = "Common Functionality";
		}
		else if (requestURI.indexOf("appshell") != -1)
		{
			subsystem = "AS";
			sourceName = "Appshell";
		}

		Session session = ContextManager.getSession();
		session.put("subsystem", subsystem);
		session.put("sourceName", sourceName);
	}

	private void logPerformance(long elapsedTime, HttpServletRequest aRequest)
	{
		String sourceId = null;
		String sourceName = null;
		StringBuilder buffer = new StringBuilder();
		try
		{			
			InetAddress localHost = InetAddress.getLocalHost();
			buffer.append("//");
			buffer.append(localHost.getHostAddress());
			buffer.append(":");
			buffer.append(aRequest.getServerPort());
			buffer.append("/");
			sourceName = aRequest.getRequestURI();
			buffer.append(sourceName);
			buffer.append(" | elapsedTime: ");
			buffer.append(elapsedTime);
			buffer.append(" ms");
			int index = sourceName.lastIndexOf("/");
			sourceName = sourceName.substring(index, sourceName.length());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String shortDesc = "poor performance: " + elapsedTime + " ms";

		ExceptionHandler.addWarning("HTTP Request Perf", sourceId, sourceName, elapsedTime, shortDesc, buffer.toString());
	}

}
