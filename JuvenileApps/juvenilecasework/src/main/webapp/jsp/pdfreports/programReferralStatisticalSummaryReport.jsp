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

<jsp:useBean id="reportInfo" class="ui.juvenilecase.programreferral.form.ProgramReferralForm" scope="session"/>

<pdf onload="pdf:130">

<head>
	<meta name="title" value="<%=PDFReport.PROGRAM_REFERRAL_STATISTICAL_SUMMARY.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.PROGRAM_REFERRAL_STATISTICAL_SUMMARY.getReportName()%>"/>
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
				<table width="90%" border-style="none">
					<tr>						
						<td align="left" class="times-font-8">
							<b>Page</b> <pagenumber/><b> of </b><totalpages/>
						</td>
						<td class="times-font-8" align="right">
							<b>Current Date</b> <%= (new java.util.Date()).toLocaleString()%>
						</td>
					</tr>	
				</table>
			</div>
		</macro>
	</macrolist>
</head>

<body footer="footer" footer-height="5mm">
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>						
			<td align="center" colspan="12">
				<table>
					<tr>
						<td align="center">
							<p class="times-font-12" align="center" >JIMS2</p>
						</td>
					</tr>
					<tr>						
						<td align="center"><p class="times-font-12">Active Program Referral Statistical Summary Report</p></td>		
					</tr>					
				</table>
			</td>
			<td align="right" ><img src="images/HarrisCountySeal.jpg" width="50" height="50"/></td>	
		</tr>		
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
</table>

<table width="100%" border-style="none">
	<thead>
		<tr align="center">
			<td width="5%"><p class="times-font-9"><b>Program Name</b></p></td>
			<td width="1%"><p class="times-font-9"><b>Count</b></p></td>			
		</tr>
		<tr>
			<td width="5%">--------------------</td>
			<td width="1%">---------</td>		
		</tr>				
	</thead>	
	<tbody>
		<c:set var="summary"  value="${reportInfo.groupMap}" scope="session"/>
			<c:forEach var="resultItems" items="${summary}" varStatus="status">			
				<tr>
					<td><p class="times-font-8"><c:out value="${resultItems.key}"/> </p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.value}"/> </p></td>				
				</tr>
			</c:forEach>
	</tbody>
	
</table>
<logic:notEmpty name="reportInfo" property="officerLastName">
<br/><br/>
<table width="98%" border-style="none">
	<tr>
		<td align="center" colspan="7">These are the results for the following JPO query: <nobr><span class="arial-font-8"><%=reportInfo.getOfficerLastName()%></span></nobr>,
		<nobr><span class="arial-font-8"><%=reportInfo.getOfficerFirstName()%></span></nobr>
		</td>
	</tr>		
</table>
</logic:notEmpty>
<br/><br/>

<table width="100%" border-style="none">
	<thead>
		<tr align="center">
			<td width="10%"><p class="times-font-9"><b>Juvenile #</b></p></td>
			<td width="10%"><p class="times-font-9"><b>Juvenile Name</b></p></td>
			<td width="10%"><p class="times-font-9"><b>Program ID#</b></p></td>
			<td width="25%"><p class="times-font-9"><b>Program Name</b></p></td>
			<td width="10%"><p class="times-font-9"><b>Start Date</b></p></td>
			<td width="10%"><p class="times-font-9"><b>Probation Officer</b></p></td>			
			<td width="25%"><p class="times-font-9"><b>Time In Program</b></p></td>
			
			 
		</tr>
		<tr>
			<td width="10%">---------------</td>
			<td width="10%">---------------</td>		
			<td width="10%">---------------</td>
			<td width="25%">---------------</td>
			<td width="10%">---------------</td>		
			<td width="10%">---------------</td>
			<td width="25%">---------------</td>			
		</tr>				
	</thead>	
	<tbody>
		<c:set var="searchResults"  value="${reportInfo.activeReferralList}" scope="session"/>
			<c:forEach var="resultItems" items="${searchResults}" varStatus="status">			
				<tr>
					<td><p class="times-font-8"><c:out value="${resultItems.juvenileId}" /></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.juvenileFullName}" /></p></td>	
					<td><p class="times-font-8"><c:out value="${resultItems.juvProgRefId}"/> </p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.providerProgramName}"/> </p></td>					
					<td><p class="times-font-8"><fmt:formatDate value="${resultItems.beginDate}" pattern="MM/dd/yyyy" var="formattedDate" /><c:out value="${formattedDate}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.formattedOfficerName}" /></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.timeInProgram}" /></p></td>					
				
				</tr>
			</c:forEach>
	</tbody>
	
</table>
<br/><br/>

</body>
</pdf>