/*
 * Created on Mar 14, 2005
 *
 */
package mojo.km.reporting;

import mojo.km.messaging.reporting.ReportRequestEvent;
/**
 * @author RDassharma
 *
 */
public interface IReport {
	
	public byte[] getByteOutput(ReportRequestEvent event)throws Exception ;
	
	public String getContentType();
	
	public String getTemplate();
}
