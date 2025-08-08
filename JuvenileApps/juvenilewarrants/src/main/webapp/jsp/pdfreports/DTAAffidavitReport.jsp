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
	<meta name="title" value="<%=PDFReport.DTAAFFIDAVIT_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.DTAAFFIDAVIT_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
<!-- STYLE SHEET LINK -->

<style>
span.underline
	{	border-bottom:1px solid #555;
		}
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
td.indent-15px
	{text-indent:15px;}
td.indent-35px
	{text-indent:35px;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%" border-style="none">
						<tr align="center">
							<td align="center">
								****NOT VALID WITHOUT COURT SEAL****
							</td>
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
			<td align="center" width="40%">The State Of Texas</td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">Affidavit</td>
		</tr>
		<tr align="center">
			<td align="center" width="40%"></td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">For</td>
		</tr>
		<tr align="center">
			<td align="center" width="40%"></td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">Directive To Apprehend</td>
		</tr>
		<tr align="center">
			<td align="center" width="40%">County Of Harris</td><td align="center" width="20%">&#167;</td><td align="left" width="40%" class="indent-35px">Warrant Number: <%=reportInfo.getWarrantNum()%></td>
		</tr>
</table>	
</div>
<div class="body">
<!-- First MAIN Paragraph -->
<table width="100%" border-style="none" class="margin-top-30px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml("I, the undersigned affiant, am employed as a peace officer with the " + reportInfo.getOfficerAgencyName() + 
						" in Harris County, Texas.  I believe and have reason to believe that " + 
						reportInfo.getFirstName() + " " + reportInfo.getMiddleName() + " " + reportInfo.getLastName() + " " + reportInfo.getSuffix() +
						", hereinafter the Respondent, engaged in delinquent conduct by committing the " + reportInfo.getLevel() + " " + reportInfo.getDegree() + 
						" offense of " + reportInfo.getChargeDescription() + " in Harris County, Texas on or about " + reportInfo.getOffenseDateString() + 
						".  Affiant's belief is based on the following:") %>
			</td>
		</tr>
</table>
<!--  Affidavit statement -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td align="left" class="indent-15px">			
				<%=StringEscapeUtils.escapeXml(reportInfo.getAffidavitStatement()) %>
			</td>
		</tr>
</table>	
<!--  Affiant statement -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml("Affiant determined the Respondent was " + reportInfo.getJuvenileAge() + 
						" years of age at the time of the offense by: ") %>
			</td>
		</tr>
</table>
<!--  Name of Verification of Age -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td align="left" class="indent-15px">			
				<%=StringEscapeUtils.escapeXml(reportInfo.getAgeVerification()) %>
			</td>
		</tr>
</table>	
<!--  WHEREFORE statement -->
<table width="100%" border-style="none" class="margin-top-24px">
		<tr>
			<td align="left">			
				<%=StringEscapeUtils.escapeXml("WHEREFORE, PREMISES CONSIDERED, affiant respectfully requests that a Directive to Apprehend " + 
						"issue for " + reportInfo.getFirstName() + " " +  reportInfo.getMiddleName() + " " + reportInfo.getLastName() + 
						" " + reportInfo.getSuffix() + " and that they be placed in the custody of the Chief Juvenile Probation " +
						"Officer at the Juvenile Detention Center in Harris County, Texas.") %>
			</td>
		</tr>
</table>	
<!--  AFFIANT Sign -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">
				_____________________________
			</td>
		</tr>
</table>
<table width="100%" border-style="none">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">AFFIANT</td>
		</tr>
</table>
<table width="100%" border-style="none">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">
				_____________________________
			</td>
		</tr>
</table>
<table width="100%" border-style="none">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">PRINT NAME</td>
		</tr>
</table>
<!--  Sworn statement -->
<table width="100%" border-style="none" class="margin-top-60px">
		<tr>
			<td align="left">			
				Sworn to and subscribed before me on the ________ day of __________________, ________.
			</td>
		</tr>
</table>
<!--  	Presiding Judge Signature Block -->
<table width="100%" border-style="none" class="margin-top-40px">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">			
				_____________________________
			</td>
		</tr>
</table>
<table width="100%" border-style="none" class="margin-top-4px">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">			
				Presiding Judge
			</td>
		</tr>
</table>
<table width="100%" border-style="none" class="margin-top-4px">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">			
				_____ District Court
			</td>
		</tr>
</table>
<table width="100%" border-style="none" class="margin-top-4px">
		<tr>
			<td align="left" width="60%">							
			</td>
			<td align="left" width="40%">			
				Harris County, Texas
			</td>
		</tr>
</table>
</div>
</body>
</pdf>
