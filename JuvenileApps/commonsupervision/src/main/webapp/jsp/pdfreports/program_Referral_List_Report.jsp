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
<jsp:useBean id="form" class="ui.supervision.administerserviceprovider.JuvenileProgramReferralListBean" scope="session"/>
<pdf onload="pdf:95.8">
<head>
	<meta name="title" value="<%=PDFReport.PROGRAM_REFERRAL_LIST_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.PROGRAM_REFERRAL_LIST_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>

body
	{	size:letter-landscape;
		margin-left: -.18in;
    	margin-right: .0in;
		margin-top: -.13in; 
		margin-bottom: -.1in;
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

div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
		

table.margin-0
	{	margin: 0px;}
table.margin-bottom--30
	{	margin-bottom: -30px;}
table.margin-bottom-10
	{	margin-bottom: 10px;}
table.margin-bottom-15
	{	margin-bottom: 15px;}

p.bold 
	{	font-weight: bold;}
p.underline
	{	border-bottom:1px solid #555;}
p.space
	{	line-height: 125%;}
span.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;
		display: block;
}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-14
	{font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-16
	{font-size:16;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}

span.bold
	{	font-weight: bold;}
span.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
</style> 
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table align="center" width="100%">
				 <tr>
				 	<td align="center" vertical-align="top" width="100%">
						Page :<pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>

<body footer="myfooter">

	<table class="" border-style="none" border-width="0" padding="0" width="100%">
		<tr>
			<td width="5%" align="left" vertical-align="top" padding-top="0">
				<p><img src="images/HarrisCountySeal.jpg" width="75" height="75"/></p>
			</td>
			<td width="95%" align="center" vertical-align="top">
				<table  width="95%" border-style="none" border-width="0" padding="0">
					<tr>
						<td>
							<p class="arial-font-16" align="center">
							<b>HARRIS COUNTY<br/>JUVENILE PROBATION DEPARTMENT</b>
							</p>
						</td>
					</tr>
					<tr>
						<td vertical-align="top" align="center">
							<p class="arial-font-14" align="center">
								1200 Congress Houston, TX 77002
							</p>
						</td>
					</tr>
				</table>
			</td>	
		</tr>
	</table>
	
	<table class="margin-bottom-10" border-style="none" border-width="0" width="100%" padding="0">
		<tr>
			<td width="5%"></td>
			<td width="95%" vertical-align="top" align="center">
				<p align="center"><span class="arial-font-11 bold">Service Provider:</span> ${form.juvServiceProviderName}</p>
			</td>
		</tr>
	</table>
	
	<table class="margin-bottom-10" border-style="none" border-width="0" width="100%" padding="0">	
		<tr align="left">
			<td width="80px"><p class="arial-font-9 bold underline space">Juv No.</p></td>
			<td width="200px"><p class="arial-font-9 bold underline space">Juvenile Name</p></td>			
			<td width="180px"><p class="arial-font-9 bold underline space">Program</p></td>
			<td width="120px"><p class="arial-font-9 bold underline space">Referral Status</p></td>
			<td width="100px"><p class="arial-font-9 bold underline space">Begin Date</p></td>
			<td width="100px"><p class="arial-font-9 bold underline space">End Date</p></td>
			<td width="140px"><p class="arial-font-9 bold underline space">JPO Name</p></td>
			<td width="110px"><p class="arial-font-9 bold underline space">Contact</p></td>
		</tr>
	</table>
	
	<c:if test="${form.referralList != null}">
		<c:forEach var="referrals" items="${form.referralList}" varStatus="loop">
			<fmt:formatDate value="${referrals.beginDate}" pattern="MM/dd/yyyy" var="beginDate" />
			<fmt:formatDate value="${referrals.endDate}" pattern="MM/dd/yyyy" var="endDate" />
			<table width="950px" border-style="none" border-width="0px" padding="0px" line-height="100%">
				<tr align="left">
					<td width="80px" vertical-align="top"><p class="arial-font-9">${referrals.juvenileId}</p></td>
					<td width="200px" vertical-align="top"><p class="arial-font-9">${referrals.juvenileFullName}</p></td>					
					<td width="200px" vertical-align="top"><p class="arial-font-9">${referrals.providerProgramName}</p></td>
					<td width="120px" vertical-align="top"><p class="arial-font-9">${referrals.referralStatusDescription} ${referrals.referralSubStatusDescription}</p></td>
					<td width="100px" vertical-align="top"><p class="arial-font-9">${beginDate}</p></td>
					<td width="100px" vertical-align="top"><p class="arial-font-9">${endDate}</p></td>
					<td width="150px" vertical-align="top"><p class="arial-font-9">${referrals.officerFullName}</p></td>
					<td width="150px" vertical-align="top"><p class="arial-font-9">${referrals.contactName} <span class="arial-font-9"> &nbsp;&nbsp;&nbsp;${referrals.contactPhone} </span></p> </td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
	
</body>
</pdf>