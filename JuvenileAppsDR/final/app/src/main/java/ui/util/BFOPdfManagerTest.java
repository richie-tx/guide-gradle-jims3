package ui.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BFOPdfManagerTest
{
    private static BFOPdfManagerTest instance = null;
	
	/**
	 * constructor
	 */
	private BFOPdfManagerTest(){

	}
	
	/**
	 * static method to get one and only one single instance of manager
	 * @return
	 */
	public static BFOPdfManagerTest getInstance(){
		if(instance == null){
			instance = new BFOPdfManagerTest();
		}
		return instance;
	}
	
	public void createPDFReport(HttpServletRequest aRequest, HttpServletResponse aResponse, PDFReport report) throws Exception{
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
	          //e.printStackTrace();
		throw e;
	    }  
		
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
