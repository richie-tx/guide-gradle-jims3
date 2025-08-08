/*
 * Created on Sep 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.struts;

import java.util.StringTokenizer;
import java.util.Vector;
import mojo.km.config.MojoProperties;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ApplicationStartupPlugIn implements PlugIn
{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy()
	{
		System.out.println("[ApplicationStartup.destroy]");
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
	 */
	public void init(ActionServlet arg0, ModuleConfig arg1) throws ServletException
	{
		System.out.println("[ApplicationStartup.init]");
		MojoProperties.getInstance().setActiveServerName(mojo.km.naming.ServerNames.GUI);
		
		String[] startups = getAsArray(this.runAtStartup, this.delimiter);
		for (int x=0; x < startups.length; x++) {
			System.out.println("[ApplicationStartup] [Initializing] " + startups[x]);
			
			Class c;
			IStartup istartup = null;
			try { 			
				c = Class.forName(startups[x]);				
				istartup = (IStartup) c.newInstance();
				
				istartup.init();
				
			} catch (ClassNotFoundException cnfe) {
				System.out.println("[ApplicationStartup] [ClassNotFoundException] " + startups[x]);
			} catch (IllegalAccessException iae) {
				System.out.println("[ApplicationStartup] [IllegalAccessException] " + startups[x]);
			} catch (InstantiationException ie) {
				System.out.println("[ApplicationStartup] [InstantiationException] " + startups[x]);
			} catch (ExceptionInInitializerError eiie) {
				System.out.println("[ApplicationStartup] [ExceptionInInitializerError] " + startups[x]);
			} catch (SecurityException se) {
				System.out.println("[ApplicationStartup] [SecurityException] " + startups[x]);
			} catch (ClassCastException cce) {
				System.out.println("[ApplicationStartup] [ClassCastException] " + startups[x] + " does not implement IStartup.");
			} catch (Exception e) {
				System.out.println("[ApplicationStartup] [Exception] " + startups[x] + " + " + e.getMessage());
			}
		}

	}
	
	private String runAtStartup;
	private String delimiter = ",";
	
	public void setRunAtStartup(String startups) {
		this.runAtStartup = startups;
	}
	
	public String getRunAtStartup() {
		return this.runAtStartup;
	}

	
	public void setDelimiter(String delim) {
		this.delimiter = delim;
	}
	
	public String getDelimiter() {
		return this.delimiter;
	}
	
	/*
	* Returns a String[] based off the deliminted string.
	*/
	private String[] getAsArray(String delimitedString, String delimiter)
	{
		if (delimitedString == null)
		{
			return new String[0];
		}
		StringTokenizer st = new StringTokenizer(delimitedString, delimiter);
		Vector items = new Vector();
		while (st.hasMoreTokens())
		{
			String token = st.nextToken();
			items.add(token.trim());
		}
		String[] retArray = new String[items.size()];
		items.copyInto(retArray);
		return retArray;
	}	

}
