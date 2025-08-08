package ui.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;

/**
 * exists to provide helper methods for pdf functionality 
 * related to BFO pdf generation of reports
 * @author rcarter
 *
 */
public class BFOPdfManager {
	 
	private static BFOPdfManager instance = null;
	
	/**
	 * constructor
	 */
	private BFOPdfManager(){

	}
	
	/**
	 * static method to get one and only one single instance of manager
	 * @return
	 */
	public static BFOPdfManager getInstance(){
		if(instance == null){
			instance = new BFOPdfManager();
		}
		return instance;
	}
	
	public void createPDFReport(HttpServletRequest aRequest, HttpServletResponse aResponse, PDFReport report){
        ServletContext sc = aRequest.getSession().getServletContext(); 
        // based on PDFReport, get the correct url and name 
        String url = aResponse.encodeRedirectURL(report.getReportUrl());
        String filename = report.getReportName();
        // based on report name, get the correct url and name from the map. 
        aResponse.resetBuffer();
        RequestDispatcher dispatcher = sc.getRequestDispatcher(url); 
		//aResponse.setContentType("application/x-file-download");
        aResponse.setContentType("application/pdf");
	    aResponse.setHeader("Content-disposition", "attachment; filename="
		+ filename);   
	    aResponse.setHeader("Cache-Control", "max-age=" + 120);

        try { 
        	dispatcher.include(aRequest,aResponse); 
	    } catch (Exception e) { 
	          e.printStackTrace(); 
	    }  
		
	}
    /**
     * 
     * @param aRequest
     * @param aResponse
     * @param report
     * @param smForm 
     * @return
     */
    public byte[] createPDFByteReport(HttpServletRequest aRequest, HttpServletResponse aResponse, PDFReport report, SuspiciousMemberForm smForm)
    {
	ServletContext sc = aRequest.getSession().getServletContext();
	String url = aResponse.encodeRedirectURL(report.getReportUrl());
	StringBuffer sb = new StringBuffer();
	sb.append(report.getReportName()).append("	from member_").append(smForm.getSelectedFromId()).append("	to member_").append(smForm.getSelectedToId()).append(".pdf");
	//String filename = report.getReportName() + "" + "from member_" + smForm.getSelectedFromId() + "to member_"  + smForm.getSelectedToId() + ".pdf";
	aResponse.resetBuffer();

	RequestDispatcher dispatcher = sc.getRequestDispatcher(url);
	aResponse.setContentType("application/pdf");
	aResponse.setHeader("Content-disposition", "attachment; filename="
		+ sb.toString());
	aResponse.setHeader("Cache-Control", "max-age=" + 120);

	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	try
	{
	    // Wrap the response's OutputStream with ByteArrayOutputStream 
	    final ServletOutputStream responseOutputStream = aResponse.getOutputStream();
	    final ServletOutputStream sos = new ServletOutputStream() {
		@Override
		public void write(int b)
		{
		    baos.write(b);
		    try
		    {
			responseOutputStream.write(b);
		    }
		    catch (IOException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}

		@Override
		public void write(byte[] b, int off, int len)
		{
		    baos.write(b, off, len);
		    try
		    {
			responseOutputStream.write(b, off,len);
		    }
		    catch (IOException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    };

	    final HttpServletResponse wrappedResponse = new HttpServletResponseWrapper(aResponse) {
		@Override
		public ServletOutputStream getOutputStream()
		{
		    return sos;
		}

		@Override
		public PrintWriter getWriter()
		{
		    return new PrintWriter(sos, true);
		}
	    };
	    dispatcher.include(aRequest, wrappedResponse);
	    responseOutputStream.flush();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return baos.toByteArray();
    }
	    
	
	
	
	/**
	 * U.S #55947
	 * Added for court Subpoenas as it needs to print multiple pdfs on a single click.
	 * @param aRequest
	 * @param aResponse
	 * @param report
	 * #83693
	 *//*
	public void createMultiplePDFReport(HttpServletRequest aRequest, HttpServletResponse aResponse, PDFReport report){
	        ServletContext sc = aRequest.getSession().getServletContext(); 
	        // based on PDFReport, get the correct url and name 
	        String url = aResponse.encodeRedirectURL(report.getReportUrl());
	        String filename = report.getReportName();
	        // based on report name, get the correct url and name from the map. 
	        aResponse.resetBuffer();
	        RequestDispatcher dispatcher = sc.getRequestDispatcher(url); 
	        aResponse.setContentType("application/octet-stream");
		aResponse.setHeader("content-disposition", "attachment; filename="+ filename);   
		aResponse.setHeader("Cache-Control", "max-age=" + 120);
	        try { 
	            	dispatcher.include(aRequest,aResponse);
		    } catch (Exception e) { 
		          e.printStackTrace(); 
		    }  
			
		}*/
}
