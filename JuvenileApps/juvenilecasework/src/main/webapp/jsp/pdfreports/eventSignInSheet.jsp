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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.workshopcalendar.form.CalendarEventListForm" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.EVENT_SIGN_IN_SHEET.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.EVENT_SIGN_IN_SHEET.getReportName()%>"/>
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
			<td width="100%"> <p align="center" class="arial-font-10">
<b>Attendance must be documented in JIMS2 within 7 days of event.</b></p>
			</td>	
		</tr>
	</table> 		
</div>
	<table class="arial-font-10" width="100%" border-style="all" align="center" padding-top="10" page-break-inside="auto"> 
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="left" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="10%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="36%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="14%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="38%">
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="6" border="0" background-color="none">
				<p align="left" class="arial-font-10">
                	<b>Date/Time: </b><i><fmt:formatDate value="${reportInfo.currentEvent.eventDate}" pattern="MMM d, yyyy" var="formattedDate" />
                	<c:out value="${formattedDate}"/> <c:out value="${reportInfo.currentEvent.eventTimeFormatted}"/></i><br/><br/>
                	<b><i>${reportInfo.currentEvent.serviceProviderName}</i></b><i>, the following juveniles are scheduled for this event:</i><br/><br/>
                </p>
			</td>		
		</tr>	
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Program Name:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i><c:out value="${reportInfo.currentEvent.programName}"/></i></p>
			</td>			
		</tr>
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Service Name:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i>${reportInfo.currentEvent.serviceName}</i></p>
			</td>			
		</tr>
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Event Type:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i>${reportInfo.currentEvent.eventType}</i></p>
			</td>			
		</tr>	
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Session Length:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i>${reportInfo.currentEvent.eventSessionLength} hours</i></p>
			</td>			
		</tr>
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Total Scheduled:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i>${reportInfo.currentEvent.currentEnrollment}</i></p>
			</td>			
		</tr>
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Location Unit:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i>${reportInfo.currentEvent.serviceLocationName}</i></p>
			</td>			
		</tr>
		<tr width="100%">
			<td padding-bottom="0" padding-top="0" align="left" colspan="2">
				<p align="center" class="arial-font-10"><nobr><b>Instructor Name:</b></nobr></p>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" colspan="4">
				<p align="center" class="arial-font-10"><i>${reportInfo.currentEvent.instructorName}</i></p>
			</td>			
		</tr>
		</table>
		<table class="arial-font-10" width="100%" border-style="all" align="center" padding-top="10" page-break-inside="auto">
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="5" align="left" width="1%">
			</td>
			<td padding-bottom="5" padding-top="5" align="left" colspan="2">
				<p align="center" class="arial-font-10"><b><u>Name</u></b></p>
			</td>
			<td padding-bottom="5" padding-top="5" align="left" width="14%">
				<p align="center" class="arial-font-10"><b><u>Juvenile<br/> Number</u></b></p>
			</td>
			<td padding-bottom="5" padding-top="5" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="5" align="left" width="38%">
				<p align="center" class="arial-font-10"><b><u>Signature</u></b></p>
			</td>			
		</tr>
		<c:forEach var="programReferral" items="${reportInfo.programReferralList}" varStatus="loop">
			<tr width="100%"> 
				<td padding-bottom="5" padding-top="0" align="left" width="1%">
				</td>
				<td padding-bottom="5" padding-top="0" align="left" colspan="2">
					<p align="center" class="arial-font-10"><u>${programReferral.juvenileName}</u></p>
				</td>
				<td padding-bottom="5" padding-top="0" align="left" width="14%">
					<p align="center" class="arial-font-10"><u>${programReferral.juvenileId}</u></p>
				</td>
				<td padding-bottom="5" padding-top="0" align="center" width="1%">
				</td>
				<td padding-bottom="5" padding-top="0" align="left" width="38%">
					________________________________________
				</td>			
			</tr>	
			<tr width="100%"> 
				<td padding-bottom="5" padding-top="0" align="left" colspan="2">
				</td>
				<td padding-bottom="5" padding-top="0" align="left" colspan="4">
					<p align="center" class="arial-font-10">JPO: ${programReferral.officerFullName}</p>
				</td>			
			</tr>			
		</c:forEach>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" colspan="2">
				_________________________________
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="14%">
				_______
			</td>
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="38%">
				________________________________________
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" colspan="2">
				_________________________________
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="14%">
				_______
			</td>
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="38%">
				________________________________________
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" colspan="2">
				_________________________________
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="14%">
				_______
			</td>
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="38%">
				________________________________________
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" colspan="2">
				_________________________________
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="14%">
				_______
			</td>
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="38%">
				________________________________________
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" colspan="2">
				_________________________________
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="14%">
				_______
			</td>
			<td padding-bottom="5" padding-top="0" align="center" width="1%">
			</td>
			<td padding-bottom="5" padding-top="0" align="left" width="38%">
				________________________________________
			</td>			
		</tr>		
	</table>
</body>
</pdf>
