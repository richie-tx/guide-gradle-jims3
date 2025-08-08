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

<jsp:useBean id="reportInfo" class="ui.juvenilecase.CasefileSearchResultsReportBean" scope="session"/>

<pdf onload="pdf:130">

<head>
	<meta name="title" value="<%=PDFReport.CASEFILE_SEARCH_RESULTS_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.CASEFILE_SEARCH_RESULTS_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	
		size:A4-landscape;
		margin-left: .1in;
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
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%" class="times-font-8">
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
<div>
<table width="100%" border-style="none">
	<tr>
		<td width="30%" valign="bottom">
			<p class="centered">
				<img height="45" width="45" src="images/HarrisCountySeal.jpg"/>
			</p>
		</td>
		
			<td width="70%" text-align="center">
			<p class="times-font-10">HARRIS COUNTY<br/>JUVENILE PROBATION DEPARTMENT<br/><i><span style="color:#A8A8A8; font-size:8;">1200 Congress Houston, TX 77002</span></i></p>
		</td>
	</tr>
</table>
</div>
<div class="spacer"></div>

<table width="90%" border-style="none">
	<tr>
		<td colspan="7" align="center"><p class="times-font-9"><b>Case Status Results with Youth In-home Addresses</b></p></td>
	</tr>
</table>

<div class="spacer"></div>
<br/><br/>
<table width="100%" border-style="none">
	<tr>
		<td align="left"><p class="times-font-8"><b>JPO: </b><c:out value="${reportInfo.officerLastName}"/><logic:notEmpty name="reportInfo" property="officerFirstName">, &nbsp;
		<c:out value="${reportInfo.officerFirstName}"/></logic:notEmpty></p></td>
	</tr>
	<tr>
		<td align="left"><p class="times-font-8"><b>Case Status: </b><c:out value="${reportInfo.statusSearchType}"/></p></td>
	</tr>
	<tr>
		<td align="left"><p class="times-font-8"><b>Total Casefiles: </b><c:out value="${reportInfo.numberOfCasefiles}"/></p></td>
	</tr>
</table>

<br/>
<table width="100%" border-style="none">
	<thead>
		<tr align="center">
			<td width="1%"><p class="times-font-8-bold">Juvenile Name</p></td>
			<td></td>			
			 <td width="1%"><p class="times-font-8-bold">Juvenile Number</p></td>
			 <td><p class="times-font-8-bold"><nobr>Youth In-home Address</nobr></p></td>
			 <td><p class="times-font-8-bold"><nobr>Supervision Type</nobr></p></td>
			 <td><p class="times-font-8-bold">Supervision#</p></td> 
			<td width="1%"><p class="times-font-8-bold">Expected End Date</p></td>
			<%--<td width="1%"><p class="times-font-8-bold">Zip Code</p></td> --%>
			
		</tr>
	</thead>
	<tbody>
		<c:set var="searchResults"  value="${reportInfo.casefileSearchResults}" scope="session"/>
			<c:forEach var="resultItems" items="${searchResults}" varStatus="status">			
				<tr>
					<td width="1%"><p class="times-font-8"><c:out value="${resultItems.juvenileFullName}"/> </p></td>
					<td></td>
					<td><p class="times-font-8"><c:out value="${resultItems.juvenileNum}"/></p></td>
					<%-- <td><p class="times-font-8"><c:out value="${resultItems.probationOfficerLastName}" />, <c:out value="${resultItems.probationOfficerFirstName}" /> <c:out value="${resultItems.probationOfficerMiddleName}" /> </p></td> --%>
					<td width="1%"><p class="times-font-8"><c:out value="${resultItems.memberAddress.streetAddress}"/><br /><c:out value="${resultItems.memberAddress.cityStateZip}"/></p></td>
					<td width="1%"><p class="times-font-8"><c:out value="${resultItems.supervisionType}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.supervisionNum}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.supervisionEndDateStr}" /></p></td>
					<%-- <td><p class="times-font-8"><c:out value="${resultItems.zipCode}"/></p></td> --%>
				</tr>
			</c:forEach>
	</tbody>
	
</table>
<br/><br/>

</body>
</pdf>