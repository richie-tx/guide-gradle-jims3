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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.FacilityOrientationLetterReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.FACILITY_ORIENTATION_LETTER_EN.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.FACILITY_ORIENTATION_LETTER_EN.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
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
td.times-font-12
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.times-font-12
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.times-font-16
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.times-font-10
	{font-size:11;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.constantia-font-12
	{font-size:12;
		font-family:"Constantia", Times, serif;
		line-height: 100%;}
p.constantia-font-14
	{font-size:14;
		font-family:"Constantia", Times, serif;
		line-height: 100%;}

p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
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
p.leftAlign
	{text-align:left;}
nobr { white-space:nowrap; }
pre  { white-space:pre; }

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%" class="times-font-10" font-size="10">
						<b>A BALANCED APPROACH TO JUVENILE JUSTICE</b> 
					</td>
				 </tr>
				  <tr>
					 <td align="center" width="50%" colspan="4" border-top = ".9"></td>
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
			<tr>
				<td align="top-left" width='10%'><img src="images/HarrisCountySeal.jpg" width="65" height="65"/></td>
				<td width='90%'>
					<table width="100%" border-style="none">
						<tr>
							<td width="75%"  valign='bottom' class="times-font-14 leftAlign"><p text-align="left"><h1 font-size="12">HARRIS COUNTY</h1><br/>JUVENILE PROBATION DEPARTMENT</p></td>
							<td width="25%"  valign='bottom' class="times-font-14 leftAlign"><p font-size ='8' text-align="right">HENRY GONZALES<br/>Executive Director<br/>Chief Juvenile Probation Officer</p></td>
						</tr>
						<tr>
							<td align="left" colspan="8" border-top = ".9"> </td>
						</tr>
						<tr>
							<td width="70%"  valign="top"  align ="left" class="times-font-12"><p font-size ='12' text-align="left">RESIDENTIAL SERVICES<br/>1200 Congress ST., Houston, Tx 77002</p></td>
							<td width="30%"  valign="top" align ="left" class="times-font-12"><p font-size ='8' text-align="right">DR. DIANA QUINTANA<br/>Assistant Executive Director<br/><br/>MELISSA DEHOYOS-WATSON<br/>Deputy Director<br/><br/>KEITH V. BRANCH<br/>Assistant Deputy Director</p></td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	<div class="body">
	<table>
		<tr>
			<td>
			<p></p><p></p><p></p><p></p>
			<p valign="top" class="leftAlign times-font-10">Dear <%=reportInfo.getContactName()%>,</p>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
				<p></p><p></p>
				<p class="leftAlign times-font-10">
		  			Greetings! Your child, <%=reportInfo.getJuvenileFullName()%> was recently placed in the custody of the Chief Probation Officer CJPO) at his/her court date.
		  			The Harris County Juvenile Probation Department is here to assist you to understand the programs and placements that are available to your child.
				</p>
			</td>
		</tr>
		<tr>
			<td> 
				<p></p>
				<p class="leftAlign times-font-10" font-size="11">You are scheduled to attend a meeting on <bean:write name="ScheduleNewEventForm" property="currentService.currentEvent.eventDateStr"/> to make you aware of what is expected during his/her time in placement.</p>
			</td>
		</tr>
		<tr>
			<td>
				<p></p>
				<p class="leftAlign times-font-10">Experts will be on site to answer questions you may have such as: 
					<ul marker-type="middle-dot">
						<li>
							<ul marker-type="middle-dot">
								<li>•	Will my child be enrolled in school?</li>
								<li>•	What if my child needs special education classes?</li>
								<li>•	How can I expect my child to act when he/she is in placement?</li>
								<li>•	What programs are available for my child?</li>
								<li>•	What will we have to do for probation?</li>
								<li>•	What is the role of the caseworker?</li>
							</ul>
						</li>
					</ul>
					</p>
			</td>
		</tr>
		<tr>
			<td>
				<p class="leftAlign times-font-10">Your attendance at this meeting is <b>recommended</b> and will be very helpful in getting ready for the change ahead.We look forward to seeing you!</p><p></p>
			</td>
		</tr>
		<tr>
			<td>
				<table colspan="2">
					<tr>
						<td>
							<p class="leftAlign times-font-10" width="100%"><b>Location:</b></p>
						</td>
						<td>
							<p class="leftAlign times-font-10" width="100%"><%=reportInfo.getLocation()%></p><p></p>
						</td>
					</tr>
					
					<tr>
						<td>
							<p class="leftAlign times-font-10" width="100%"><b>Time:</b></p>
						</td>
						<td>
							<p class="leftAlign times-font-10" width="100%"><bean:write name="ScheduleNewEventForm" property="currentService.currentEvent.eventTime"/> - <%=reportInfo.getEndTime()%> </p>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td>
							<p class="leftAlign times-font-10" width="100%">Please arrive at least 10 minutes early.</p><p></p>
						</td>
					</tr>
					
					<tr>
						<td>
							<p class="leftAlign times-font-10" width="100%"><b>Date:</b></p>
						</td>
						<td>
							<p class="leftAlign times-font-10" width="100%"><%=reportInfo.getEventDateStr()%>.</p>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<p></p>
				<p class="leftAlign times-font-10">Child care is not available.You will be responsible for the supervision of your children.</p>
			</td>
		</tr>
		<tr>
			<td>
				<p></p>
				<p class="leftAlign times-font-10">If you have any questions regarding the workshop please contact your child's caseworker,<br/><%=reportInfo.getOfficerName()%> <%=reportInfo.getOfficerPhone()%>.</p>
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</pdf>
