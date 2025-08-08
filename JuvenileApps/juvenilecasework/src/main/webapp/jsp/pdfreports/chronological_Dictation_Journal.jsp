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
<jsp:useBean id="journalTO" class="messaging.journal.to.JournalTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.CHRONOLOGICAL_DICTATION_JOURNAL.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.CHRONOLOGICAL_DICTATION_JOURNAL.getReportName()%>"/>
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
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
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
<body footer="myfooter" footer-height="20mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr width="100%">
			<td width="10%" valign="top">
				<p class="centered">
					<img height="45" width="45" src="images/HarrisCountySeal.jpg"/>
				</p>
			</td>
			<td width="90%" class="helvetica-font-16" align="center">
			<p class="text-margin-0px bold centered">HARRIS COUNTY</p>
			<p class="text-margin-10px bold centered">JUVENILE PROBATION DEPARTMENT</p></td>
		</tr>	
	</table>
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" valign="top" width="92%">
				<p class="arial-font-12 bold centered">Probation Services Division/Court Services</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" valign="top" width="92%">
				<p class="arial-font-10 centered">1200 Congress Houston, TX 77002</p>
			</td>
		</tr>
	</table>	
</div>
<div class="body">
<!--  CASEFILE REPORT SECTION HEADER -->
	<table width="100%" border-style="none" align="center" class="margin-top-20px">
		<tr align="center">
			<td width="8%" >
			</td>
			<td align="center" width="92%" valign="top"><p class="arial-font-12 centered"><b>Casefile Chronological Dictation</b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center" class="margin-top-10px">
		<tr align="center">
			<td align="left" width="50%"><p class="arial-font-10 centered"><b>Juvenile:</b> <%=journalTO.getJuvenileName()%></p></td>
			<td align="left" width="50%"><p class="arial-font-10 centered"><b>Juvenile ID#:</b> <%=journalTO.getJuvenileId()%></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center" class="margin-top-0px" cellpadding="0">
		<tr align="center">
			<td align="left" width="50%"><p class="arial-font-10 centered"><b>Casefile:</b> <%=journalTO.getCaseFileId()%></p></td>
			<td align="left" width="50%"><p class="arial-font-10 centered"><b>Supervision Type:</b> <%=journalTO.getSupervisionType()%></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none" align="center" class="margin-top-0px" padding-top="0" padding-bottom="10">
		<tr align="center">
			<td align="left" width="50%"><p class="arial-font-10 centered"><b>Probation Officer:</b> <%=journalTO.getProbationOfficer()%></p></td>
			<td align="left" width="50%"></td>
		</tr>
	</table>
</div>
<!--  JOURNAL ENTRIES -->

		<c:set var="journalList" value="${journalTO.journalEntries}" scope="session"/> 
		<c:forEach var="journal" items="${journalList}" varStatus="status">
			<table width="100%" border-style="none" cellpadding="0">
				<tr>
					<td>
						<table width="100%" border-style="none" cellpadding="0" class="arial-font-9">
							<tr>
								<td align="left" width="15%">
										<b>Date/Time:</b>
								</td>
								<td align="left" width="85%">
								<fmt:formatDate value="${journal.date}" pattern="MM/dd/yyyy hh:mm a" var="formattedDate" />
									<b>
										<c:out value="${formattedDate}"/>
									</b>
								</td>
							</tr>
							<tr>
								<td align="left" width="15%">
										<b>Subject:</b>
								</td>
								<td align="left" width="85%">							
										<c:out value="${journal.subject}"/>
								</td>
							</tr>
							<tr>
								<td align="left" width="15%">				
										<b>Text: </b>
									
								</td>
								<td align="left" width="85%">	
									<c:out value="${journal.text}"/>
								</td>
							</tr>
							<tr>
								<td align="left" width="15%">				
										<b>Update Comments: </b>
									
								</td>
								<td align="left" width="85%">	
									<c:out value="${journal.updatecomments}"/>
								</td>
							</tr>
							<tr>
								<td align="left" width="15%">
									<b>Created By: </b>
								</td>
								<td align="left" width="85%">		
										<c:out value="${journal.createdBy}"/>
								</td>
							</tr>
							<tr>
								<td align="left" width="100%" colspan="2">
									<p class="arial-font-9">
									------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
									</p>
								</td>
							</tr>
						</table>
					</td>
				</tr>				
			</table>
			</c:forEach> 				
</body>
</pdf>
