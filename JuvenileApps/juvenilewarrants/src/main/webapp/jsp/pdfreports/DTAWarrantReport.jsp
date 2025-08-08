<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@ page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="java.net.URLEncoder"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ page import="ui.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<jsp:useBean id="reportInfo" class="ui.juvenilewarrant.ReportingBean" scope="session" />
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.DTAWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.DTAWARRANT_REPORT_NAME.getReportName()%>"/>
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
td.bold
	{font-weight:bold;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%" border-style="none">
						<tr align="center">
							<td align="center">****NOT VALID WITHOUT COURT SEAL****</td>
						</tr>
				</table>
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
			<td align="center" width="50%">The State Of Texas</td><td align="left" width="20%">&#167;</td><td align="left" width="30%">Directive To Apprehend</td>
		</tr>
		<tr align="center">
			<td align="center" width="50">County Of Harris</td><td align="left" width="20%">&#167;</td><td align="left" width="30%">Warrant Number: <%=reportInfo.getWarrantNum()%></td>
		</tr>
</table>		
</div>
<div class="body">
<!--  Intro -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td  align="left" class="bold">
				To the Sheriff Or Any Peace Officer Of Harris County, Texas   
			</td>
		</tr>
</table>
<!--  Greetings -->
<table width="100%" border-style="none" class="margin-top-40px">
		<tr>
			<td  align="left" class="bold">
				Greetings:
			</td>
		</tr>
</table>
<!--  First Paragraph -->
<table width="100%" border-style="none" class="margin-top-40px">
		<tr>
			<td  align="left">
				<%=StringEscapeUtils.escapeXml("WHEREAS, Complaint, in writing and under oath, has been made before me by " + 
						reportInfo.getOfficerName() + " - " + reportInfo.getOfficerAgencyName() + ", which complaint is attached hereto and expressly made a " +
						"part hereof for all purposes and said complaint having stated facts and information in my opinion " + 
						"sufficient to establish probable cause to believe " + reportInfo.getFirstName() + " " +  reportInfo.getMiddleName() + " " + 
						reportInfo.getLastName() + " " + reportInfo.getSuffix() + " engaged in Delinquent Conduct " + 
						"and for the issuance of this Directive to Apprehend;") %>
			</td>
		</tr>
</table>
<!--  Second Paragraph -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td  align="left">
				<%=StringEscapeUtils.escapeXml("You are therefore commanded to forthwith take custody of " + reportInfo.getFirstName() + " " +  
						reportInfo.getMiddleName() + " " + reportInfo.getLastName() + " " + reportInfo.getSuffix() + ", for the " + 
						reportInfo.getLevel() + " " + reportInfo.getDegree() + " offense of " + reportInfo.getChargeDescription() + " committed on or about " + 
						reportInfo.getOffenseDateString() + ", and said juvenile to the Harris County Juvenile Detention Center where juvenile shall be detained.") %>
			</td>
		</tr>
</table>
<!--  Herein -->
<table width="100%" border-style="none" class="margin-top-80px">
		<tr>
			<td  align="left">
				HEREIN FAIL NOT AND DUE RETURN MAKE HEREOF.
			</td>
		</tr>
</table>
<!--  Signed statement -->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td  align="left">
				Signed on this the _____ day of ___________________________, _______ at____o'clock _____.m.
			</td>
		</tr>
</table>
<!--  	MAGISTRATE signature -->
<table width="100%" border-style="none" class="margin-top-60px">
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
				MAGISTRATE
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				HARRIS COUNTY, TEXAS
			</td>
		</tr>
</table>
</div>
</body>
</pdf>
