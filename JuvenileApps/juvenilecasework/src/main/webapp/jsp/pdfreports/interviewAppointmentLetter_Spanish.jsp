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
<jsp:useBean id="eventAppointmentLetterTO" class="messaging.calendar.to.EventAppointmentLetterTO" scope="session"/>
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.INTERVIEW_APPOINTMENT_LETTER_ES.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.INTERVIEW_APPOINTMENT_LETTER_ES.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: .0in;
    	margin-right: .0in;
		margin-top: .0in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.header
	{ 	
		font-size:12;
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
img.display
    {	display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;}
span.underline
	{	border-bottom:1px solid #555;}
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
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.line-space-125
	{line-height: 125%;}
p.line-space-150
	{line-height: 150%;}
p.text-margin-10px
	{margin: 10px;}
p.text-margin-top-6
	{margin-top: 6px;}
p.text-margin-top-10
	{margin-top: 10px;}
p.text-margin-top-25
	{margin-top: 25px;}
p.text-margin-bottom-25
	{margin-bottom: 25px;}
p.text-margin-bottom-40
	{margin-bottom: 40px;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
p.rightAlign
	{text-align:right;}
table.title
	{
		
	}
td.borderBottom
{
	border-bottom:2pt solid gray;
}

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer" align="center">
				<img margin-top="0" width="500" src="images/JuvenileJustice_Footer.gif"/>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="left" cellpadding="0">
		<tr align="left">
			<td valign="top" margin-left="0">
				<p class="leftAlign">
					<img margin-top="0" height="75" width="75" src="images/HarrisCountySeal.jpg"/>
				</p>
			</td>
			<td width="90%" class="arial-font-12" align="left">
				<table width="100%" align="left" padding="3">
				<tr>
					<td class="borderBottom" colspan="2" cellpadding="5">
						<p class="leftAlign bold">HARRIS COUNTY</p>
						<p class="leftAlign bold">JUVENILE PROBATION DEPARTMENT</p>
					</td>
				</tr>
				<tr>
					<td align="left">
						<c:if test="${eventAppointmentLetterTO.locationUnitName != null}">
						<p class="arial-font-10 bold text-margin-top-6"><%=eventAppointmentLetterTO.getLocationUnitName() %></p>
						</c:if>
						<p class="arial-font-10 text-margin-top-6"><%=eventAppointmentLetterTO.getLocationUnitAddress().getStreetAddress() %></p>
						<p class="arial-font-10 text-margin-top-6 text-margin-bottom-40"><%=eventAppointmentLetterTO.getLocationUnitAddress().getCityStateZip() %></p>
					</td>
					<td align="right">
						<p class="arial-font-8 text-margin-top-10 line-space-150"><b><%=eventAppointmentLetterTO.getManagerFirstName()%> <%=eventAppointmentLetterTO.getManagerLastName()%></b></p>
						<p class="arial-font-8 line-space-150">Administrator</p>
						<p class="arial-font-8 line-space-150">Office <%=eventAppointmentLetterTO.getManagerPhone() %></p>
					</td>
				</tr>
				</table>
			</td>
		</tr>	
	</table>
	
	<p class="line-space-150 arial-font-10 text-margin-bottom-40">
	<c:if test="${eventAppointmentLetterTO.guardianAddress != null}">
		<%=eventAppointmentLetterTO.getGuardianFirstName() %> <%=eventAppointmentLetterTO.getGuardianMiddleName() %> <%=eventAppointmentLetterTO.getGuardianLastName() %>
		<br/><%=eventAppointmentLetterTO.getGuardianAddress().getStreetAddress() %>
		<br/><%=eventAppointmentLetterTO.getGuardianAddress().getCityStateZip() %>
	</c:if>
	</p>
	
	<table width="100%" border-style="none" padding="0">
		<tr>
			<td>
				<p class="arial-font-10 text-margin-bottom-40"><%=eventAppointmentLetterTO.getCurrentDate()%></p>
				<p class="arial-font-10 text-margin-bottom-25">
				Estimados Padres 
				<%=eventAppointmentLetterTO.getMemberRelationship() %> <%=eventAppointmentLetterTO.getGuardianFirstName() %> <%=eventAppointmentLetterTO.getGuardianLastName() %>
				</p>
				<p class="arial-font-10 text-margin-bottom-25 line-space-125">
				He planificado una cita para usted y su hija/hijo,
				<%=eventAppointmentLetterTO.getJuvenileFirstName() %> <%=eventAppointmentLetterTO.getJuvenileMiddleName() %> <%=eventAppointmentLetterTO.getJuvenileLastName() %>, para venir a
				<%=eventAppointmentLetterTO.getServiceLocationName() %> el
				<%=eventAppointmentLetterTO.getEventDate() %> a las <%=eventAppointmentLetterTO.getEventTime() %>.
				<c:if test="${not empty eventAppointmentLetterTO.eventComments}">
					Durante la entrevista me gustaria discutir lo siguiente:
					<%=eventAppointmentLetterTO.getEventComments() %>
				</c:if>
				</p>
				<c:if test="${not empty eventAppointmentLetterTO.inventoryDocuments}">
				<p class="arial-font-10 text-margin-bottom-25 line-space-150">
						Favor de traer los siguientes documentos:<br/>
	    				<c:forEach var="document" items="${eventAppointmentLetterTO.inventoryDocuments}" varStatus="loop">
							${document}<br/>
						</c:forEach>
				</p>
				</c:if>
				<p class="arial-font-10 text-margin-bottom-25">
				Si usted tiene cualquier pregunta favor de hablar al <%=eventAppointmentLetterTO.getOfficerPhone() %>.
				</p>
			</td>
		</tr>
		
		<tr>
			<td align="right">
				<table>
				<tr>
				<td>
				<p class="arial-font-10 text-margin-bottom-40 leftAlign">
				Sinceramente,
				</p>
				<p class="line-space-150 arial-font-10 text-margin-bottom-40">
				<%=eventAppointmentLetterTO.getOfficerFirstName() %> <%=eventAppointmentLetterTO.getOfficerLastName() %><br/>
				Oficial Juvenil de Probacion
				</p>
				</td>
				</tr>	
				</table>
			</td>
		</tr>
	</table>
	
	<table width="100%">
		<tr align="left">
			<td align="center">
				<p class="arial-font-10 centered"><b>Para ayuda adicional en Espanol favor de comunicarse al siguiente telfono: <%=eventAppointmentLetterTO.getInterviewLocationPhone() %>.</b></p>
			</td>
		</tr>
	</table>
	
</div>
</body>
</pdf>