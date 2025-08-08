<html>
<!--MODIFICATIONS -->
<!-- LDeen	03/29/04	Create JSP -->
<!-- LDeen	08/11/05	Revise JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ page import="messaging.error.reply.ErrorResponseEvent" %>
<%@ page import="mojo.km.config.ExceptionProperties" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.ExceptionConstants" %>
<%@ page import="naming.UIConstants" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - error.jsp</title>
</head>
<jsp:useBean id="exception" scope="page" class="java.lang.Exception" />
<%
	ExceptionProperties properties = ExceptionProperties.getInstance();
	
	ErrorResponseEvent errorEvent = new ErrorResponseEvent();
	errorEvent.setDateTimeStamp(new java.util.Date());
	errorEvent.setUserId(UIUtil.getCurrentUserID());

	// String message = properties.getReason(exception);
	String message = "General JSP Exception";

	if (message == null || "".equals(message))
	{
		message = exception.getMessage();
	}
	else if (message == null && exception.getMessage() == null)
	{
		message = exception.getClass().getName();
	}
	else if(exception.getMessage() != null)
	{
		message = message + " - " + exception.getMessage();
	}
		
	errorEvent.setMessage(message);

	errorEvent.setException(exception);

	if (message != null && message.length() > ExceptionConstants.MESSAGE_DISPLAY_MAX_LENGTH)
	{
		message = message.substring(0, ExceptionConstants.MESSAGE_DISPLAY_MAX_LENGTH);
		errorEvent.setMessage(message);
	}

	request.setAttribute(UIConstants.ERROR_EVENT, errorEvent);

	if (errorEvent != null && exception != null)
	{	
		System.err.println("User ID: "+errorEvent.getUserId());
		System.err.println(properties.getReason(errorEvent.getException()));
		System.err.println(properties.getDiagnosis(errorEvent.getException()));
		System.err.println(properties.getSolution(errorEvent.getException()));
		
		exception.printStackTrace();
	} 
	
%>

<!--BEGIN BODY TAG-->
<body>

<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header">Error Notice </td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<table align="center" width="98%" border="0">
   <tr>
     <td align="center">A system or application error has occurred. 
     <p>Please contact technical support so that the problem can be diagnosed and it can be determined what can be done to help you complete your work. </p></td>
   </tr>		
</table> 
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0" cellpadding=1 cellspacing=0>
	<tr>
	  <td width=48% align=center valign=top>
		<table>
			<tr><td style="font-weight:bold; text-align: center;">Weekdays:</td></tr>
			<tr><td align="center">Please contact JPD Data Corrections by:</td></tr>
			<tr><td align="center"><a href="mailto:Data.Corrections@hcjpd.hctx.net">Data.Corrections@hcjpd.hctx.net</a></td></tr>
		</table>
	  </td>
	  <td width=48% align=center valign=top>	    
		<table>
			<tr><td style="font-weight:bold; text-align: center;" >Evenings and Weekends:</td></tr>
			<tr><td align="center">Please contact JPD Data Corrections & US Help Desk by:</td></tr>
			<tr><td align="center"><a href="mailto:Data.Corrections@hcjpd.hctx.net">Data.Corrections@hcjpd.hctx.net</a></td></tr>
			<tr><td align="center"><a href="mailto:helpdesk@us.hctx.net">helpdesk@us.hctx.net</a></td></tr>
			
		</table>
	</td>
			
	</tr>
	<tr>
		<td colspan="3">When reporting the error, please provide the information below.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<br>
<!-- BEGIN INFORMATION TABLE -->
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width=98% align=center valign=top>
			<table class=borderTableBlue cellpadding=4 cellspacing=0 width=98%>
				<tr class=detailHead>
					<td class=detailHead>Error Information</td>
				</tr>
				<tr>
					<td align=center>
					<!--error table start-->
						<table border=0 cellspacing=1 cellpadding=2 width=100%>
							<tr>
								<td class=formDeLabel width=1% nowrap>Error Message</td>
								<td class=formDe colspan=3><bean:write name="errorEvent" property="message" /></td>
							</tr>
							<tr>
								<td class=formDeLabel width=1%>Date</td>
								<td class=formDe><bean:write name="errorEvent" property="dateTimeStamp" format="MM/dd/yyyy" /></td>
								<td class=formDeLabel width=1% nowrap>Time</td>
								<td class=formDe><bean:write name="errorEvent" property="dateTimeStamp" format="HH:mm:ss" /></td>
							</tr>
							<tr>
								<td class=formDeLabel width=1%>Error - Log ID</td>
								<td class=formDe><bean:write name="errorEvent" property="errorLogId" /></td>
								<td class=formDeLabel width=1% nowrap>User ID</td>
								<td class=formDe><bean:write name="errorEvent" property="userId" /></td>
							</tr>
						</table>
						<!--error table end-->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<br>
<br>
<table width="100%">
	<tr>
		<td align="center">We apologize for the inconvenience.</td>
	</tr>
</table>

</body>
</html>