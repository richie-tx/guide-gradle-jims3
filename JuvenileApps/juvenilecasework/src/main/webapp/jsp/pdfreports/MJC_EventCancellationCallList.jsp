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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm" scope="session" />
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
table.arial-font-11
	{font-size:11;
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
	<table class="arial-font-11" width="100%" align="center" padding-top="10">
		<tr width="100%"> 
			<td padding-bottom="8" padding-top="0" align="left" width="100%">
				<b><i>Date: ${reportInfo.currentDate}</i></b> 
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="8" padding-top="0" align="left" width="100%">
				<b><i>The following event has been cancelled</i></b> 
			</td>		
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="left" width="100%">
				<b><i>Event Id: ${reportInfo.eventId}</i></b> 
			</td>			
		</tr>
	</table>		
</div>
	<table class="arial-font-10" width="100%" align="center" padding-top="5">
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="0" align="left" width="17%">
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="30%">
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="30%">
			</td> 
			<td padding-bottom="0" padding-top="0" align="left" width="23%">
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="5" padding-top="0" align="left" width="17%">
				<b>Juveile Number</b> 
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="30%">
				<b>Juvenile Name</b>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="30%">
				<b>Contact Name</b>
			</td>
			<td padding-bottom="0" padding-top="0" align="left" width="23%">
				<b>Contact Number (Type)</b>
			</td>			
		</tr>
	<c:forEach var="cancellationJuv" items="${reportInfo.cancellationList}" varStatus="loop">
	<c:set var="counter" value="${0}"/>
	<c:forEach var="guardian" items="${cancellationJuv.guardianResponseEvents}" varStatus="loop">
		<c:if test="${ counter == '0'}">
			<tr width="100%"> 
				<td padding-bottom="0" padding-top="0" align="left" width="17%">
					${cancellationJuv.juvenileId}					
				</td>
				<td padding-bottom="0" padding-top="0" align="left" width="30%">
					${cancellationJuv.juvenileName}
				</td>
				<td padding-bottom="0" padding-top="0" align="left" width="30%">
					${guardian.lastName},${guardian.firstName}
				</td> 
				<td padding-bottom="0" padding-top="0" align="left" width="23%">
					<logic:notEqual name="guardian"  property="homePhoneNumber" value="">
					${guardian.homePhoneNumber} (home)<br/>
					</logic:notEqual>
					<logic:notEqual name="guardian"  property="workPhoneNumber" value="">
					${guardian.workPhoneNumber} (work)<br/>
					</logic:notEqual>
					<logic:notEqual name="guardian"  property="mobilePhoneNumber" value="">
					${guardian.mobilePhoneNumber} (cell)
					</logic:notEqual>
				</td>			
			</tr>	
			</c:if>		
			<c:if test="${ counter >= '1'}">
			<tr width="100%"> 
				<td padding-bottom="0" padding-top="0" align="left" width="16%">				
				</td>
				<td padding-bottom="0" padding-top="0" align="left" width="30%">
				</td>
				<td padding-bottom="0" padding-top="0" align="left" width="30%">
					${guardian.lastName},${guardian.firstName}
				</td> 
				<td padding-bottom="0" padding-top="0" align="left" width="24%">
					<logic:notEqual name="guardian"  property="homePhoneNumber" value="">
					${guardian.homePhoneNumber} (home)<br/>
					</logic:notEqual>
					<logic:notEqual name="guardian"  property="workPhoneNumber" value="">
					${guardian.workPhoneNumber} (work)<br/>
					</logic:notEqual>
					<logic:notEqual name="guardian"  property="mobilePhoneNumber" value="">
					${guardian.mobilePhoneNumber} (cell)
					</logic:notEqual>
				</td>			
			</tr>	
			</c:if>
			<c:set var="counter" value="${counter + 1}" />
	</c:forEach> 
	</c:forEach>
	</table>
</body>
</pdf>
