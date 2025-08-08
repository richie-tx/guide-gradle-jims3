<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reportInfo" class="ui.juvenilecase.MAYSIInformationReportingBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.MAYSI_REQUEST_INFO.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.MAYSI_REQUEST_INFO.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: 0.0in;
    	margin-right: 0.0in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.header
	{ 	
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.margin-top-0px
	{	margin-top: 0px;}
table.margin-top-5px
	{	margin-top: 5px;}
table.margin-top-10px
	{	margin-top: 10px;}
table.margin-top-20px
	{	margin-top: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="100%"><b>MAYSI Information:</b></td>
		</tr>
		<tr align="left" padding-top="0">
			<td align="center" width="100%"><b>____________________________________________________________________</b></td>
		</tr>
	</table>	
</div>
<div class="body">
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none" padding-top="10">
		<tr align="center">
			<td align="left" width="100%"><b>Juvenile ID Number: </b><%=reportInfo.getJuvenileNum()%></td>
		</tr>
		<tr>
			<td align="left" width="100%"><b>Juvenile Admission Number: </b><%=reportInfo.getReferralNum()%></td>
		</tr>
		<tr align="center">
			<td align="left" width="100%"><b>Type of Facility: </b><%=reportInfo.getPlacementType()%></td>
		</tr>
		<tr align="center">
			<td align="left" width="100%"><b>Name of Facility: </b><%=reportInfo.getLocation( )%></td>
		</tr>
		<tr align="center">
			<td align="left" width="100%"><b>Age: </b><%=reportInfo.getCurrentAge( )%></td>
		</tr>
		<tr align="center">
			<td align="left" width="100%"><b>Gender: </b><%=reportInfo.getGender( )%></td>
		</tr>
		<tr>
			<td align="left" width="100%"><b>What is your ethnic background?: </b><%=reportInfo.getRace( )%></td>
		</tr>
		<tr>
			<td align="left" width="100%"><b>How long have you been here?: </b><%=reportInfo.getLength( )%></td>
		</tr>
		<tr>
			<td align="left" width="100%"><b>Has the youth taken a MAYSI before?: </b><%=reportInfo.getPreviousMAYSI( )%></td>
		</tr>
	</table>
</div>		
<div class="header">
	<table width="100%" border-style="none">
		<tr align="center" padding-top="10">
			<td align="left" width="100%"><b>Warning to be read to the youth taking MAYSI</b></td>
		</tr>
	</table>	
</div>
<div class="body">
	<table width="100%" border-style="none" padding-top="10">
		<tr align="center">
			<td align="left" width="100%">		
   				These are some questions about things that sometimes happen to people.  For each question, please answer yes 
   				or no to answer whether that question has been true for you in the past few months.  
   				Please answer these questions as well as you can.
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="100%">		
   				Any statement you make or any answer you give to the questions on this questionnaire cannot be used against 
			    you in any adjudication hearing. Do you understand? Do you have any questions?
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="100%">		
   				While nothing you say while answering these questions can be used against you, as was stated above, there is one exception.  
   				If you disclose that you are the victim of child abuse or neglect, or if you disclose that you have committed 
   				an offense involving child abuse or neglect, that information must be reported to law enforcement.
    		</td>
		</tr>
	</table>
</div>		
<div class="header">
	<table width="100%" border-style="none">
		<tr align="center" padding-top="10">
			<td align="left" width="100%"><b>WHEN READING THE RESULTS:</b></td>
		</tr>
	</table>	
</div>
<div class="body">
	<table width="100%" border-style="none" padding-top="10">
		<tr align="center">
			<td align="left" width="100%">		
   				If warning, then ask more focused questions and exercise greater vigilance and attention to the youth 
   				to make relevant observations.  In addition, seek clinical expertise via brief evaluation or emergency care 
   				or arrange a more comprehensive psychological/psychiatric evaluation.
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="100%">		
   				If caution, either ask more focused questions and/or provide greater vigilance and attention.
    		</td>
		</tr>
	</table>
</div>
</body>
</pdf>
