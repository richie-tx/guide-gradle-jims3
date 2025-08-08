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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.form.JuvenileNonComplianceForm" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.NONCOMPLIANCE_NOTICE.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.NONCOMPLIANCE_NOTICE.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: 0.0in;
    	margin-right: 0.0in;
		margin-top: 0.0in; 
		margin-bottom: 0.0in;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
div.header
	{ 	
		font-size:16;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
div.title
	{ 	
		font-size:12;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
img.display
    {
   		display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;
    }
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.bold 
	{font-weight: bold;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
p.text-margin-0px 
	{margin: 0em;}
p.text-margin-10px
	{margin: 10px;}
span.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
span.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
span.underline
	{	border-bottom:1px solid #555;
		}
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
td.arial-font-10
	{font-size:10;
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
			<td align="center" width="100%"><u><b>Violation of Probation Sanction Notice</b></u></td>
		</tr>
	</table>	
</div>
<!--  JUVENILE INFORMATION -->
<div class="title">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="100%" padding-top="10"><b>Name: <%=reportInfo.getJuvenileFullName()%></b></td>
		</tr>
		<tr align="center">
			<td align="center" width="100%"><b>Juvenile Number: <%=reportInfo.getJuvenileNum()%></b></td>
		</tr>
		<tr align="center">
			<td align="center" width="100%"><b>Date: <%=reportInfo.getSanctionAssignedDateStr()%></b></td>
		</tr>
	</table>	
</div>
<table class="arial-font-10" width="100%" border-style="none">
	<tr>
		<td align="left" colspan="2" padding-top="10">On <%=reportInfo.getNonComplianceDateStr()%>, you were in violation of probation by:</td>
	</tr>
	<c:set var="violationsList" value="${reportInfo.probationViolationList}" scope="session"/>
		<c:forEach var="violation" items="${violationsList}" varStatus="status">
			<tr>
				<td align="left" width="10%">&#8226;</td> 
				<td align="left" width="90%"><c:out value="${violation.description}"/></td>
			</tr>
		</c:forEach>
		<tr>
			<td align="left" colspan="2">As a result, the following response(s) is (are) imposed:</td>
		</tr>
	<c:set var="sanctionsList" value="${reportInfo.selectedSanctionsList}" scope="session"/>
		<c:forEach var="sanction" items="${sanctionsList}" varStatus="status">
			<tr>
				<td align="left" width="10%">&#8226;</td> 
				<td align="left" width="90%"><c:out value="${sanction.otherText}"/>: <c:out value="${sanction.comments}"/><br/></td>
			</tr>
		</c:forEach>
</table>
	
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="2">The following time frame has been allotted for completion of the imposed sanction(s).</td>
		</tr>
		<tr align="center">
			<td align="left">Date Imposed: <%=reportInfo.getSanctionAssignedDateStr()%></td>
			<td align="left">Date Completion Required: <%=reportInfo.getSanctionCompleteByDateStr()%></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="2">Failure to successfully complete the sanctions imposed may result in further sanctions or a return to Court.</td>
		</tr>
		<tr align="center">
			<td align="left" padding-top="15">_________________________________</td>
			<td align="left" padding-top="15">_________________________________</td>
		</tr>
		<tr align="center">
			<td align="left">Probationer</td>
			<td align="left">Date</td>
		</tr>
		<tr align="center">
			<td align="left" padding-top="15">_________________________________</td>
			<td align="left" padding-top="15">_________________________________</td>
		</tr>
		<tr align="center">
			<td align="left">Probation Officer</td>
			<td align="left">Date</td>
		</tr>
	</table>	
</div>
</body>
</pdf>
