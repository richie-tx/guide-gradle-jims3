/*
 * Created on Mar 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.messaging.reporting;

import mojo.km.messaging.ResponseEvent;
import java.io.OutputStream;

/**
 * @author RDassharma
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportResponseEvent extends ResponseEvent {

	private byte[] content = null;
	private String contentType = "";
	private int contentLength = 0;
	static private int CONTENT_INIT_SIZE = 13;
	private String fileName = "";
	
	
	/**
	 * @return
	 */
	public byte[] getContent()
	{
		return content;
	}

	/**
	 * @return
	 */
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * @param bs
	 */
	public void setContent(byte[] bs)
	{
		content = bs;
	}

	/**
	 * @param string
	 */
	public void setContentType(String string)
	{
		contentType = string;
	}

	/**
	 * @return
	 */
	public int getContentLength()
	{
		return content.length + CONTENT_INIT_SIZE;
	}
	/**
	 * @param string
	 */
	public void setFileName(String string)
	{
		fileName = string;
	}

	/**
	 * @return
	 */
	public String getFileName()
	{
		return fileName;
	}


}
