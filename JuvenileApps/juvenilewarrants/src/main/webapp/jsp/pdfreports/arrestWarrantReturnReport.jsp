<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@ page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@ page import="ui.common.StringUtil"%>
<%@ page import="ui.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<jsp:useBean id="reportInfo" class="ui.juvenilewarrant.ReportingBean" scope="session" />
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.ARRESTWARRANTRETURN_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.ARRESTWARRANTRETURN_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
<!-- STYLE SHEET LINK -->
<style>
span.underline
	{	border-bottom:1px solid #555;
		}
table.margin-top-8px
	{	margin-top: 8px;}
table.margin-top-12px
	{	margin-top: 12px;}
table.margin-top-24px
	{	margin-top: 24px;}
table.margin-top-30px
	{	margin-top: 30px;}
table.margin-top-40px
	{	margin-top: 40px;}
table.margin-top-50px
	{	margin-top: 50px;}
table.margin-top-60px
	{	margin-top: 60px;}
table.margin-top-70px
	{	margin-top: 70px;}
table.margin-top-80px
	{	margin-top: 80px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
table.indent-50px
	{	text-indent:50px;}
body
	{	margin-left: .2in;
    	margin-right: .2in;
		margin-top: .3in; 
		margin-bottom: .3in;}
div.header
	{ 	font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
div.photo
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		padding:11;
		text-align:left;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
#page1 
	{	footer:myfooter; footer-height:0.1in;}
#page2 
	{	footer:myfooter; footer-height:0.1in;}
td.indent-35px
	{text-indent:35px;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="left" width="50%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body>
<!--  Header information -->
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="40%">The State Of Texas</td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">Arrest Warrant</td>
		</tr>
		<tr align="center">
			<td align="center" width="40%"></td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">Return of Service</td>
		</tr>
		<tr align="center">
			<td align="center" width="40%">County Of Harris</td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">Warrant Number: <%=reportInfo.getWarrantNum()%></td>
		</tr>
</table>	
</div>
<div class="body">
<!--  MAIN Paragraph -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml(reportInfo.getExecutorName() + " - " + reportInfo.getExecutorDepartmentName() + 
						", the undersigned, a peace officer under the laws of the State of Texas, certifies" + 
						" that the foregoing arrest warrant came to hand on the day it was issued " + 
						StringUtil.isNotNull(DateUtil.dateToString(reportInfo.getWarrantActivationDate(), "MMM dd, yyyy")) + 
						" and that it was executed on the " + 
						StringUtil.isNotNull(DateUtil.dateToString(reportInfo.getCurrentServiceDate(), "MMM dd, yyyy")) + 
						" by taking custody of " + reportInfo.getFirstName() + " " +  reportInfo.getMiddleName() + " " + 
						reportInfo.getLastName() + " " + reportInfo.getSuffix() + " and placing him in jail in Harris County, Texas.") %>
			</td>
		</tr>
</table>
<!--  PEACE OFFICER WARRANT SIGNATURE -->
<table width="100%" border-style="none" class="margin-top-80px">
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">
				___________________________________________
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">
				Peace Officer Executing Warrant
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">
				<%=reportInfo.getExecutorDepartmentName()%>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">
				Law Enforcement Agency
			</td>
		</tr>
</table>	
</div>
</body>
</pdf>
