<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8"%>
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

<jsp:useBean id="reportInfo" class="ui.juvenilecase.GALAttorneysettingsSearchResultsReportBean" scope="session"/>

<pdf onload="pdf:130">

<head>
	<meta name="title" value="<%=PDFReport.GALATTORNEYSETTINGS_SEARCH_RESULTS_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.GALATTORNEYSETTINGS_SEARCH_RESULTS_REPORT.getReportName()%>"/>
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
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}

td.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, serif;		
		color:#707070;}

td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
		
td.arial-font-12
	{
		font-size:12;
		font-family:"Arial"", Helvetica, sans-serif;
		text-align:center;
	}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, sans-serif;
		text-align:center;}

p.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;
		font-style: Italic;}

p.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, serif;text-align:left; }

p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-8-bold
	{font-size:8;
		font-family:"Times New Roman", Times, sans-serif;
		font-weight: bold;
		text-decoration:underline;
		text-align:left;}
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

span.arial-font-10
{font-size:8;
	font-family:"Times New Roman", Times, sans-serif;text-align:center;}

span.arial-font-9
{font-size:7;
	font-family:"Times New Roman", Times, sans-serif;text-align:center;}

span.times-font-9{
		font-size:6;
		font-family:"Times New Roman", Times, serif;
		font-style: Italic;
		color:#A8A8A8;
}
nobr { white-space:nowrap; }
.description {
  color: #fff;
}

.description em {
  color: #A8A8A8;
}
</style>
		<macrolist>
		<macro id="footer">	
		<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%" class="times-font-10">
						This document generated from JIMS2
					</td>
				 </tr>
				</table>
			</div>
		</macro>
		<macro id="myheader">
			<div class="header" align="center">
				<table width="90%" border-style="none">
					<tr>
						<td align="left"><p class="times-font-10"><b>Search GAL Attorney Setting</b></p></td>
						<td class="times-font-8">
							<b>Page</b> <pagenumber/><b> of </b><totalpages/>
						</td>
						<td class="times-font-8">
							<b>Current Date</b> ${reportInfo.todaysDate}
						</td>
					</tr>	
				</table>
			</div>
		</macro>
	</macrolist>
</head>

<body footer="footer" footer-height="5mm" header="myheader" header-height="40pt">
<!--  Header information -->

<table width="100%" border-style="none">
	<thead>
		<tr align="center">
			<td width="1%"><p class="times-font-9"><b>DATE</b></p></td>
			<td width="1%"><p class="times-font-9"><b>TIME</b></p></td>
			<td width="1%"><p class="times-font-9"><b>COURT</b></p></td>			
			<td width="5%"><p class="times-font-9"><b>PETITION#</b></p></td> 
			<td width="5%"><p class="times-font-9"><b>HRNG TYPE</b></p></td>
			<td width="8%"><p class="times-font-9"><b>CONNECTION</b></p></td>
			<td width="10%"><p class="times-font-9"><b>JUVENILE NAME</b></p></td>
			<td width="15%"><p class="times-font-9"><b>GAL ATTORNEY NAME</b></p></td> 			
		</tr>
		<tr>
			<td width="1%">--------</td>
			<td width="1%">---------</td>		
			<td width="1%">---------</td>			
			<td width="5%">-----------</td>
			<td width="5%">-------------</td>
			<td width="8%">---------------</td>
			<td width="15%">-------------------</td>		
			<td width="15%">-------------------</td>
		</tr>
		<tr>
				<td align="center" width="100%" colspan = "11"><p class="arial-font-8">!!!!!CONFIDENTIAL!!!!!</p></td>
	</tr>		
	</thead>	
	<tbody>
		<c:set var="searchResults"  value="${reportInfo.attorneySearchResults}" scope="session"/>
			<c:forEach var="resultItems" items="${searchResults}" varStatus="status">			
				<tr>
					<td><p class="times-font-8"><c:out value="${resultItems.courtDate}"/> </p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.courtTime}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.court}"/></p></td>					
					<td><p class="times-font-8"><c:out value="${resultItems.petitionNumber}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.hearingTypeDesc}" /></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.attorneyConnectionDesc}"/></p></td>
					<td width="15%"><p class="times-font-8"><c:out value="${resultItems.juvenileName}" /></p></td>
					<td width="15%"><p class="times-font-8"><c:out value="${resultItems.galName}"/></p></td>					
				</tr>
			</c:forEach>
	</tbody>
	
</table>
<br/><br/>

</body>
</pdf>