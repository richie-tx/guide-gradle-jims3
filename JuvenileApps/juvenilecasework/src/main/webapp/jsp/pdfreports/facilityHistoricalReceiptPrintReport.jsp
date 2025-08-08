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
<jsp:useBean id="receiptsForm" class="ui.juvenilecase.populationReport.form.JuvenileFacilityReceiptForm" scope="session" />
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.FACILITY_HISTORICAL_RECEIPT_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.FACILITY_HISTORICAL_RECEIPT_REPORT_NAME.getReportName()%>"/>
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
		margin-bottom: .0in;}
div.header
	{ 	
		font-size:14;
		font-family:"Times New Roman", Times, serif;
		line-height: 80%;}
div.body
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
		text-align:left;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;}
table.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;}
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
p.body-times-font-12
	{	font-size:12;
		font-family:"Times New Roman", Times, serif;}
th.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.times-font-16
	{font-size:16;
		font-family:"Times New Roman", Times, serif;
		text-align:center;}
p.times-font-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;}
p.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;}
p.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;}
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
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr width="100%">
			<td width="10%" align="left">
				<img src="images/HarrisCountySeal.jpg" width="40" height="40"/>
			</td>
			<td width="90%" class="times-font-16" align="center">
				<p class="text-margin-10px bold centered">HISTORICAL RECEIPT LIST</p>
			</td>
		</tr>	
	</table>
</div>
<div class="body">
<!--  CASEFILE REPORT SECTION HEADER -->
<table width="100%" border-style="none" align="center" class="margin-top-20px">
	<tr align="center" width="100%" >
		<td valign="top" align="right" width="55%"><p class="times-font-12"><b>FACILITY: </b></p></td>
		<td valign="top" align="left" width="55%"><p class="times-font-12"><%=receiptsForm.getFacilityId()%></p></td>
	</tr>
</table>
<table width="100%" border-style="none" align="center" class="margin-top-10px times-font-10">
	<tr align="left">
		<th align="left" width="20%"><b>JUV. NO</b></th>
		<th align="left" width="30%"><b>JUVENILE NAME</b></th>
		<th align="left" width="20%"><b>RACE</b></th>
		<th align="left" width="20%"><b>SEX</b></th>
		<th align="left" width="10%"><b>D.O.B.</b><br/><br/></th>
	</tr>
	<tr align="left">
		<td><p class="times-font-9"><%=receiptsForm.getJuvenileNumber()%></p></td>
		<td><p class="times-font-9"><%=receiptsForm.getJuvenileName()%></p></td>
		<td><p class="times-font-9"><%=receiptsForm.getJuvenileRace()%></p></td>
		<td><p class="times-font-9"><%=receiptsForm.getJuvenileSex()%></p></td>
		<td><p class="times-font-9"><%=receiptsForm.getJuvenileDateOfBirth()%></p></td>
	</tr>
</table>
<table width="100%" border-style="none" align="center">
	<tr>
		<td align="left" width="100%" colspan="2">
			<p class="times-font-9">
			<br/><br/>------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			<br/><br/>
			</p>
		</td>
	</tr>
</table>
<!--  JOURNAL ENTRIES -->
<table  width="100%" border-style="none" align="center" class="times-font-10">
	<tr align="left">
		<th align="center" width="17%">FACILITY<br/>CODE</th>
		<th align="center" width="17%">ADMIT<br/>DATE</th>
		<th align="center" width="17%">RELEASE<br/>DATE</th>
		<th align="center" width="17%">RECEIPT<br/>NUMBER</th>
		<th align="center" width="17%">LOCKER<br/>NUMBER</th>
		<th align="center" width="17%">REFERRAL<br/>NUMBER</th>
	</tr>
</table>
<table>
	<tr>
		<td align="left" width="100%" colspan="6">
			<p class="times-font-9">
			------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			<br/>
			</p>
		</td>
	</tr>
</table>
<table  width="95%" border-style="none" align="center" class="times-font-9">
	<c:set var="historicalReceiptList" value="${receiptsForm.historicalReceipts}" scope="session"/> 
	<c:forEach var="receipt" items="${historicalReceiptList}" varStatus="status">
		<tr>
			<td align="center" width="10%">	
				<c:out value="${receipt.facilityCode}"/>
			</td>
			<td align="center" width="25%">	
				<fmt:formatDate value="${receipt.admitDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				<c:out value="${formattedDate}"/>
			</td>
			<td align="center" width="15%">
				<fmt:formatDate value="${receipt.releaseDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				<c:out value="${formattedDate}"/>	
			</td>
			<td align="center" width="20%">	
				<c:out value="${receipt.receiptNumber}"/>
			</td>
			<td align="center" width="15%">	
				<c:out value="${receipt.lockerNumber}"/>
			</td>
			<td align="center" width="15%">	
				<c:out value="${receipt.referralNumber}"/>
			</td>
		</tr>
	</c:forEach> 	
</table>
</div>		
</body>
</pdf>