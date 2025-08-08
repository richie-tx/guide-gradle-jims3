<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.common.StringUtil"%>
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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.CalendarSchedulePrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.SCHEDULE_EVENTS_LIST.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.SCHEDULE_EVENTS_LIST.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
#watermarkbody { font-size:80; font-family:Helvetica; color:#F0F0F0; }
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;}
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
hr.left
	{text-align:left;}
p.arial-font-16-B
	{font-size:16;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.arial-font-16
	{font-size:16;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-14-B
	{font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.arial-font-14
	{font-size:14;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}

p.arial-font-8-bold
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.bold 
	{font-weight: bold;}
p.centered
	{text-align:center;}
p.left
	{text-align:left;}
p.text-margin-0px 
	{margin: 0em;}
p.text-margin-10px
	{margin: 10px;}
p.times-font-16
	{font-size:16;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.arial-font-10
	{font-size:10;
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
tr.border-bottom {
	border-bottom: 1pt solid black;}
tr.border-top {
	border-top: 1pt solid black;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.row0 {
	}
td.row1 {
	background-color:#bfc1c2}

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="left" width="100%">
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>

<body footer="myfooter">
<div class="header">
<!--  Header information -->
	<table width="100%" border-style="none" align="center" cellpadding="0"> 
		<tr width="100%"> 
			<td width="100%">
				<p align="center" class="arial-font-16-B">Harris County Juvenile Probation<br/><br/>Scheduled Events</p>
			</td>	
		</tr>
	</table> 		
</div>
	<table class="arial-font-10" width="100%" align="center" padding-top="10">
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="0" align="left" width="55%">
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="20%">
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="25%">
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="left" width="55%">
				<b>Name:</b> ${reportInfo.juvenileName} 
			</td>
			<td padding-bottom="0" padding-top="0" align="right" colspan="2">
				<b>Printed on Date:</b> ${reportInfo.todaysDate} <b>Time:</b> ${reportInfo.todaysTime}
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="left" colspan="3">
				<b>You are scheduled to attend the following events:</b> 
			</td>		
		</tr>
	</table>
	<c:forEach var="event" items="${reportInfo.calendarEvents}" varStatus="loop">
		<table class="arial-font-10" width="100%" align="center" padding-top="10"   page-break-inside="avoid"> 
			<tr width="100%"> 
				<td padding-bottom="0" padding-top="3" align="left" width="35%">
				</td>
				<td padding-bottom="0" padding-top="3" align="left" width="45%">
				</td>
				<td padding-bottom="0" padding-top="3" align="left" width="20%">
				</td>			
			</tr>
			<logic:notEmpty name="event" property="eventType">
				<tr width="100%" page-break-before="join">
					<td padding-bottom="0" padding-top="3" align="center" colspan="3">
						<p><b>
						------------------------------------------------------------------
						${event.calEventDate}
						------------------------------------------------------------------
						</b></p>
					</td>		
				</tr>
				<tr width="100%">
					<td padding-bottom="0" padding-top="3" align="center" colspan="3">
						<p><b>${event.eventType}</b></p>
					</td>		
				</tr>
				<tr width="100%"> 
					<td padding-bottom="0" padding-top="3" align="left" colspan="2">
						<p>Starts: ${event.startTime}</p>
					</td>	
					<td padding-bottom="0" padding-top="3" align="center" width="20%">
						<p>Ends: ${event.endTime}</p>
					</td>		
				</tr>
				<logic:notEmpty name="event" property="locationName">
					<tr width="100%">
						<td padding-bottom="0" padding-top="3" align="right" width="35%">
							<p>Location:</p>
						</td>
						<td padding-bottom="0" padding-top="3" align="left" colspan="2">
							<p>${event.locationName}</p>
						</td>
					</tr>
				</logic:notEmpty>
				<logic:notEmpty name="event" property="locationAddress1">
					<tr width="100%"> 
						<td padding-bottom="0" padding-top="3" align="left" width="35%">
						</td>
						<td padding-bottom="0" padding-top="3" align="left" colspan="2">
							<p>${event.locationAddress1}</p>
						</td>
					</tr>
				</logic:notEmpty>
				<logic:notEmpty name="event" property="serviceProNameNService">
					<tr width="100%">
						<td padding-bottom="0" padding-top="3" align="right" width="35%">
							<p>Provider/Service:</p>
						</td>
						<td padding-bottom="0" padding-top="3" align="left" colspan="2">
							<p>${event.serviceProNameNService}</p>
						</td>
					</tr>
				</logic:notEmpty>
				<logic:notEmpty name="event" property="eventComments">
					<tr width="100%">
						<td padding-bottom="0" padding-top="3" align="right" width="35%">
							<p>Event Comments:</p>
						</td>
						<td padding-bottom="0" padding-top="3" align="left" colspan="2">
							<p>${event.eventComments}</p>
						</td>
					</tr>
				</logic:notEmpty>
			</logic:notEmpty>
		</table>
	</c:forEach>
</body>
</pdf>
