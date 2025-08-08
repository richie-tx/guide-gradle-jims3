/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software
 * License version 1.1, a copy of which has been included with this
 * distribution in the LICENSE.txt file.  */

package mojo.km.messaging.exception;


/**
   Extend this abstract class to create your own log layout format.
   
   @author Ceki G&uuml;lc&uuml;

 * @modelguid {C15DCE86-AA9A-4F38-9D63-DE98E67753DF}
*/
  
public abstract class Layout {

  // Note that the line.separator property can be looked up even by
  // applets.
	/** @modelguid {8B482508-0167-4CD0-B11C-FE67A1DF157E} */
  public final static String LINE_SEP = System.getProperty("line.separator");
	/** @modelguid {BB55DF57-7798-4250-AB80-B2335B88284D} */
  public final static int LINE_SEP_LEN  = LINE_SEP.length();



  /**
     Returns the content type output by this layout. The base class
     returns "text/plain". 
	 * @modelguid {A7A6790A-5897-43C3-A72A-D1A600C097CF}
  */
  public
  String getContentType() {
    return "text/plain";
  }

  /**
     Returns the header for the layout format. The base class returns
     <code>null</code>.  
	 * @modelguid {950FE770-20FC-4709-B990-DCB3DFA29073}
	 */
  public
  String getHeader() {
    return null;
  }

  /**
     Returns the footer for the layout format. The base class returns
     <code>null</code>.  
	 * @modelguid {6C90A4E1-768E-4662-8F29-B1EB1000ADA6}
	 */
  public
  String getFooter() {
    return null;
  }



  /**
     If the layout handles the throwable object contained within
     {@link LoggingEvent}, then the layout should return
     <code>false</code>. Otherwise, if the layout ignores throwable
     object, then the layout should return <code>true</code>.

     <p>The {@link SimpleLayout}, {@link TTCCLayout}, {@link
     PatternLayout} all return <code>true</code>. The {@link
     org.apache.log4j.xml.XMLLayout} returns <code>false</code>.

     @since 0.8.4 
	 * @modelguid {6829A74C-58CD-45A4-AEA4-E9DC7F9A549D}
	 */
  abstract
  public
  boolean ignoresThrowable();

}
