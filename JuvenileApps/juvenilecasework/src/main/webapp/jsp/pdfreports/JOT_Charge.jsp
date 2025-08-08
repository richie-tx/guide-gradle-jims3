<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@page import="ui.common.StringUtil"%>
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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.mentalhealth.form.JOTChargeReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.JOT_CHARGE.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.JOT_CHARGE.getReportName()%>"/>
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
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="100%"><b>Juvenile Offender Tracking Report</b></td>
		</tr>
		<tr align="center"></tr>
		<tr align="center" padding-top="10">
			<td align="center" width="100%"><b>(JOT)</b></td>
		</tr>
	</table>	
</div>
<div class="body">
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none" padding-top="10">
		<tr align="center">
			<td align="left" colspan="2"><b>Name: </b><%=reportInfo.getJuvenileName()%></td>
			<td align="left" width="25%"><b>Juvenile#: </b><%=reportInfo.getJuvenileNum()%></td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left"  colspan="3"><b>SID#: </b><%=StringUtil.isNotNull(reportInfo.getSidNumber())%></td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left" width="45%"><b>Race: </b><%=reportInfo.getRace( )%></td>
			<logic:equal name="reportInfo" property="multiracial" value="true">
				<td align="left" width="30%"><b>Multi-racial?: </b>YES</td>
			</logic:equal>
			<logic:equal name="reportInfo" property="multiracial" value="false">
				<td align="left" width="30%"><b>Multi-racial?: </b>NO</td>
			</logic:equal>
			<logic:equal name="reportInfo" property="hispanic" value="true">
				<td align="left" width="25%"><b>Hispanic?: </b>YES</td>
			</logic:equal>
			<logic:equal name="reportInfo" property="hispanic" value="false">
				<td align="left" width="25%"><b>Hispanic?: </b>NO</td>
			</logic:equal>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left" colspan="2"><b>Gender: </b><%=reportInfo.getGender( )%></td>
			<td align="left" width="25%"><b>Current Age#: </b><%=reportInfo.getCurrentAge( )%></td>
		</tr>
		<tr></tr>
		<tr align="center" padding-bottom="10">
			<td align="left"  colspan="2"><b>DOB: </b><%=reportInfo.getDateOfBirth( )%></td>
			<logic:equal name="reportInfo" property="verifiedDOB" value="true">
				<td align="left" width="25%"><b>Verified?: </b>YES</td>
			</logic:equal>
			<logic:equal name="reportInfo" property="verifiedDOB" value="false">
				<td align="left" width="25%"><b>Verified?: </b>NO</td>
			</logic:equal>
		</tr>
<!--  CHARGE INFORMATION -->
		<c:set var="jotChargeList" value="${reportInfo.jotCharges}" scope="session"/>
			<c:forEach var="jotCharge" items="${jotChargeList}" varStatus="status">
			<tr>
				<td align="left"  colspan="3"><b>CJIS Tracking #: </b>
					<c:if test= "${jotCharge.CJISNum != null }">
						<c:out value="${jotCharge.CJISNum}"/> - <c:out value="${jotCharge.CJISSuffixNum}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="left"  colspan="2"><b>Arrest Date: </b><%=StringUtil.isNotNull(reportInfo.getArrestDate())%></td>
				<td align="left" width="25%"><b>Arrest Time: </b><%=StringUtil.isNotNull(reportInfo.getArrestTime())%></td>
			</tr>
			<tr>
				<td align="left"  colspan="3"><b>Arresting Agency: <%=reportInfo.getArrestingAgency()%></b> 
					<%-- <c:out value="${jotCharge.lawEnforcementAgency}"/> --%>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2"><b>Charge: </b><c:out value="${jotCharge.charge}"/></td>
				<td align="left" width="25%"><b>JOT Log #: </b><c:out value="${jotCharge.daLogNum}"/></td>
			</tr>
			<tr>
				<td align="left" colspan="2"><b>Referral/Offense: </b><%=reportInfo.getReferralNum()%> / <c:out value="${jotCharge.offense}"/></td>
				<td align="left" width="25%"><b>Petition #: </b><c:out value="${jotCharge.petitionNum}"/>
				</td>
			</tr>
		</c:forEach>	
	</table>	

<!--  SUMMARY OF FACTS -->
<table width="100%" border="0" class="margin-top-30px">
	<logic:notEmpty name="reportInfo" property="summaryOF">
		<tr align="center">
			<td align="center" width="100%" border = "0"><u><b>SUMMARY OF FACTS</b></u></td>
		</tr>
	</logic:notEmpty>
</table>
</div>		
	<c:out value="${reportInfo.summaryOF}"  escapeXml="true"/>
</body>
</pdf>
