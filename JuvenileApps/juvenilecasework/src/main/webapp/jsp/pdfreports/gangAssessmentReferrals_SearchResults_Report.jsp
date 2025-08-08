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

<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.GangAssessmentReferralPrintReportBean" scope="session"/>

<pdf onload="pdf:130">

<head>
	<meta name="title" value="<%=PDFReport.GANG_ASSESSMENT_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.GANG_ASSESSMENT_REPORT.getReportName()%>"/>
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
		text-align:left;
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
							<b>Current Date</b> ${reportInfo.todaysDate}
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
						<logic:equal name="reportInfo" property="status" value="Completed"> 
							<p class="times-font-12" align="center" >Completed Gang Assessment Referrals </p>
						</logic:equal>
						<logic:equal name="reportInfo" property="status" value="Pending"> 
							<p class="times-font-12" align="center" >Pending Gang Assessment Referrals </p>
						</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="center" width="100%" colspan = "11"><p class="arial-font-8"><b>!!!!!CONFIDENTIAL!!!!!</b></p></td>
					</tr>
					
				</table>
			</td>
			 
			<td align="right" >
				<img src="images/HarrisCountySeal.jpg" width="50" height="50"/>  
			</td>	
			
		</tr>		
			
				
</table>
<table width="100%" border-style="none">
	<thead>
		<tr align="center">
			<td width="5%"></td>
			<td width="1%"><p class="times-font-9"><b>Referral Status: </b><c:out value="${reportInfo.status}"/></p></td>
			<td width="5%"><p class="times-font-9"><b>Records Found: </b><c:out value="${reportInfo.total}"/></p></td>
			<logic:notEmpty name="reportInfo" property="fromDate">	
			<td width="5%"><p class="times-font-9"><b>From Date: </b><c:out value="${reportInfo.fromDate}"/></p></td>
			<td width="5%"><p class="times-font-9"><b>To Date: </b><c:out value="${reportInfo.toDate}"/></p></td>	
			</logic:notEmpty>				
		</tr>
							
	</thead>
</table>
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td width="100%"><b>_____________________________________________________________________________________________</b></td>
		</tr>	
		<tr><td></td></tr>		
		<tr><td></td></tr>		
		<tr><td></td></tr>	
		<tr><td></td></tr>
</table>

<table width="100%" border-style="none">
	<thead>
		<tr align="left">
			<td width="9%"><p class="times-font-9"><b>Juvenile Name</b></p></td>
			<td width="7%" align = "left"><p class="times-font-9"><b>Juvenile #</b></p></td>
			<td width="5%" align = "left"><p class="times-font-9"><b>Referral<br/> Date</b></p></td>
			<td width="10%"><p class="times-font-9"><b>Person Making Referral</b></p></td>
			<td width="5%"><p class="times-font-9"><b>Recommendation</b></p></td> 
			<td width="6%"><p class="times-font-9"><b>Status</b></p></td>	
			<td width="6%"><p class="times-font-9"><b>JPO</b></p></td>
		</tr>
		<tr align="left">
			<td width="9%">--------------------------</td>
			<td width="7%" align = "left">-----------</td>
			<td width="5%">---------</td>		
			<td width="10%">--------------------------</td>
			<td width="5%">---------------------------</td>		
			<td width="6%">---------</td>
			<td width="6%">---------</td>
		</tr>				
	</thead>	
	<tbody>
		<c:set var="searchResults"  value="${reportInfo.gangAssessmentRefList}" scope="session"/>
			<c:forEach var="resultItems" items="${searchResults}" varStatus="status">			
				<tr>
					<td><p class="times-font-8"><c:out value="${resultItems.juvenileName}" /></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.juvenileNum}"/> </p></td>
					<td><p class="times-font-8"><fmt:formatDate value="${resultItems.referralDate}" pattern="MM/dd/yyyy" var="formattedDate" /><c:out value="${formattedDate}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.personMakingReferral}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.recommendations}"/></p></td>	
					<td><p class="times-font-8"><c:out value="${resultItems.acceptedStatus}"/></p></td>
					<td><p class="times-font-8"><c:out value="${resultItems.jpoOfRecord}"/></p></td>
				</tr>
			</c:forEach>
	</tbody>	
</table>
<br/><br/>

</body>
</pdf>