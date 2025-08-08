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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.form.ClientSatisfactionSurveyPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.VENDOR_SATISFACTION_SURVEY.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.VENDOR_SATISFACTION_SURVEY.getReportName()%>"/>
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
					 	<c:out value="${reportInfo.currentDate}"/> <c:out value="${reportInfo.casefileId}"/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<c:forEach var="serviceProvider" items="${reportInfo.serviceProviderName}" varStatus="loop">
<body footer="myfooter">
<div class="header">
<!--  Header information -->
	<table width="100%" border-style="none" align="center" cellpadding="0"> 
		<tr width="100%"> 
			<td width="10%" valign="top"> <p> <img height="80" width="80" src="images/HarrisCountySeal.jpg" /> </p> </td> 
			<td width="80%" align="center">
				<p class="arial-font-16-B" align="center">HARRIS COUNTY<br/><br/>JUVENILE PROBATION DEPARTMENT</p>
				<p class="arial-font-16" align="center">Vendor Satisfaction Survey</p>
			</td> 
			<td align="right" width="10%" padding-bottom="5"><p class="arial-font-10"></p></td>				
		</tr>
	</table> 		
</div>
<div class="body">
	<table width="100%" border-style="all" align="center" padding-top="10"> 
		<tr width="100%"> 
			<td padding-bottom="10" padding-top="5" align="center" width="50%">
			</td>
			<td padding-bottom="5" padding-top="5" align="center" width="30%">
			</td>
			<td padding-bottom="5" padding-top="5" align="center" width="10%">
			</td>
			<td padding-bottom="5" padding-top="5" align="center" width="10%">
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="10" padding-top="5" align="center" width="50%" border="0.75" background-color="#3366FF">
				<p align="left" class="times-font-16">
                	Overall Satisfaction
                </p>
			</td>
			<td padding-bottom="5" padding-top="5" align="center" colspan="3" border="0.75" background-color="#3366FF">
				<p align="center" class="times-font-16">
                	${serviceProvider}
                </p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="2" border="0.75" background-color="#999999">
				<p align="left" class="arial-font-14">
                	Please select the appropriate answer
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#999999">
				<p align="left" class="arial-font-14">
                	Yes
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#999999">
				<p align="left" class="arial-font-14">
                	No
                </p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="2" border="0.75" background-color="#D9D9D9">
				<p align="left" class="times-font-14">
                	Was this service helpful?
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="2" border="0.75" background-color="#D9D9D9">
				<p align="left" class="times-font-14">
                	Was the program easy to get to?
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="2" border="0.75" background-color="#D9D9D9">
				<p align="left" class="times-font-14">
                	Are you interested in continuing the program?
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="2" border="0.75" background-color="#D9D9D9">
				<p align="left" class="times-font-14">
                	Did you enjoy the program?
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="2" border="0.75" background-color="#D9D9D9">
				<p align="left" class="times-font-14">
                	Are there things you would change about the program?
                </p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>
			<td padding-bottom="0" padding-top="5" border="0.75" background-color="#D9D9D9">
				<p align="center" class="times-font-14"></p>
			</td>			
		</tr>
		<tr width="100%"> 
			<td padding-bottom="0" padding-top="5" colspan="4" border="0.75" background-color="#999999">
				<p align="center" class="arial-font-14">
                	Please enter any additioal comments or concerns below:
                </p>
			</td>		
		</tr>
		<tr width="100%" height="380"> 
			<td padding-bottom="0" padding-top="5" colspan="4" border="0.75">
				<p align="center" class="arial-font-14">
                	
                </p>
			</td>		
		</tr>		
	</table>
			
</div>
</body>
</c:forEach>
</pdf>
