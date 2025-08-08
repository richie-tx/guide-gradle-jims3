<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@ page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="java.net.URLEncoder"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
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
	<meta name="title" value="<%=PDFReport.OICWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.OICWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
<!-- STYLE SHEET LINK -->

<style>
span.underline
	{	border-bottom:1px solid #555;}
table.margin-top-4px
	{	margin-top: 4px;}
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
div.body-10
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.body-12-times-new-roman
	{	font-size:12;
		font-family:"Times New Roman", Times, serif;
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
td.indent-15px
	{text-indent:15px;}
td.indent-35px
	{text-indent:35px;}
td.bold
	{font-weight:bold;}
td.bold-underline
	{font-weight:bold; text-decoration: underline;}
td.times-font-14
	{ 	font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%" border-style="none">
						<tr align="left">
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
			<td align="center" width="30%">
			</td>
			<td align="center" width="40%" class = "times-font-14">
				Cause: <%=reportInfo.getPetitionNum()%>
			</td>
			<td align="left" width="30%" class="indent-35px, bold">
			</td>
		</tr>
		<tr align="center">
			<td align="center" width="30%">THE STATE OF TEXAS</td>
			<td align="center" width="40%">&#167;</td>
			<td align="left" width="30%" class="indent-35px, bold"><%=reportInfo.getCourtWithOrdinal()%> DISTRICT COURT</td>
		</tr>
		<tr align="center">
			<td align="center" width="30%">COUNTY OF HARRIS</td>
			<td align="center" width="40%">&#167;</td>
			<td align="left" width="30%" class="indent-35px, bold">HARRIS COUNTY, TEXAS</td>
		</tr>
</table>	
</div>
<div class="body-12-times-new-roman">
<!--  Title-->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td  align="center" class="bold">
				<span class="underline">ORDER OF IMMEDIATE CUSTODY</span>
			</td>
		</tr>
</table>
<!--  Warrant Number -->
<table width="100%" border-style="none" class="margin-top-8px">
		<tr>
			<td  align="center" class="bold">
				<span class="underline">Warrant Number: <%=reportInfo.getWarrantNum()%></span>
			</td>
		</tr>
</table>
<!--  In Matter Of -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td  align="left">
				<%=StringEscapeUtils.escapeXml("IN THE MATTER OF " + reportInfo.getFirstName() + " " +  reportInfo.getMiddleName() + 
						" " + reportInfo.getLastName() + " " + reportInfo.getSuffix() + ", Juvenile Respondent")%>
			</td>
		</tr>
</table>
<!--  FIRST Paragraph -->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td  align="left">
				<%=StringEscapeUtils.escapeXml("In the " + reportInfo.getCourtWithOrdinal() + " District Court of Harris County, Texas on this the ")%><span class="underline">
						<%=StringUtil.isNotNull(DateUtil.dateToString(reportInfo.getFileStampDate(), "MMM dd, yyyy"))%></span>  
						<%=StringEscapeUtils.escapeXml("it having been made known to the Court that the Juvenile Respondent, " + 
						reportInfo.getFirstName() + " " +  reportInfo.getMiddleName() + " " + reportInfo.getLastName() + " " + reportInfo.getSuffix() + 
						", a child over the age of 10 years and under the age of 18 years is charged on a petition filed by the District Attorney of " + 
						"Harris County, Texas with having engaged in delinquent conduct/conduct indicating a need for supervision, and that " + 
						reportInfo.getWarrantOriginatorJudge() + ", Presiding Judge, is seeking the detention of said child. ")%>
			</td>
		</tr>
</table>
<!--  SECOND FURTHER Paragraph -->
<% if(reportInfo.getCautionsSelected() != null && !reportInfo.getCautionsSelected().equals("")){ // do not show the second paragraph if cautions selected is not populated %>
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td  align="left">
				<%=StringEscapeUtils.escapeXml("FURTHER, it appears that said child " + reportInfo.getCautionsSelected() + 
						" " +  reportInfo.getCautionComments())%>
			</td>
		</tr>
</table>
<% } %>
<!--  THIRD Paragraph -->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td  align="left">
				<%=StringEscapeUtils.escapeXml("IT IS THEREFORE ORDERED that any probation officer or peace officer take the said Juvenile " +
						"Respondent and place said child in the custody and control of the Superintendent of the Harris County Juvenile " +
						"Detention Home, Houston, Harris County, Texas, who shall provide maintenance, medical examination, emergency medical " + 
						"care and/or treatment as may be necessary for the health and welfare of said child, until final hearing can be had " + 
						"on the case or pending further orders of the Court.")%>
			</td>
		</tr>
</table>
<!--  Given Under Hand -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td  align="left">
				Given under my hand on this__________day of___________________, __________.
			</td>
		</tr>
</table>
<!--  	Presiding Judge Signature -->
<table width="100%" border-style="none" class="margin-top-70px">
		<tr>
			<td align="left" width="50%">							
			</td>
			<td align="left" width="50%">			
				_____________________________
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>		
			<td align="left" width="50%">
				Judge
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">							
			</td>		
			<td align="left" width="50%">
				<%=reportInfo.getCourtWithOrdinal()%> Court
			</td>
		</tr>
</table>
</div>
</body>
</pdf>
