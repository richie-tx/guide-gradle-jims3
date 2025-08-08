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

<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.GangAssessmentReferralPrintReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.GANG_ASSESSMENT_REFERRAL_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.GANG_ASSESSMENT_REFERRAL_REPORT.getReportName()%>"/>
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
			<td align="center" width="100%"><b>GANG ASSESSMENT REFERRAL REPORT</b></td>
		</tr>
		<tr></tr>
</table>
</div>
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center">HARRIS COUNTY PROBATION JUVENILE CENTER</td>
		</tr>
</table>
<div class="spacer"></div>
<br></br>
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="2"><p><b>Referral Date: </b><bean:write name="assessmentReferralForm" property="referralDate"/></p></td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left" colspan="2" width="50%"><p><b>Person Making Referral: </b><bean:write name="assessmentReferralForm" property="personMakingRef"/></p></td>
			<td align="left" width="50%"><p><b>Placement Facility: </b><bean:write name="assessmentReferralForm" property="placementFacility"/></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="2" width="50%"><p><b>Juvenile Name: </b><bean:write name="assessmentReferralForm" property="juvenileName"/></p></td>
			<td align="left" width="50%"><p><b>Juvenile#: </b><bean:write name="assessmentReferralForm" property="juvenileNum"/></p></td>
		</tr>
		<tr>
			<td align="left" colspan="2"><p><b>Date of Birth: </b><bean:write name="assessmentReferralForm" property="dateOfBirth"/></p></td>
			<td align="left" width="25%"><p><b>Gender: </b><bean:write name="assessmentReferralForm" property="gender"/></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="2" width="50%"><p><b>Language: </b><bean:write name="assessmentReferralForm" property="language"/></p></td>
			<td align="left" width="50%"><p><b>Race/Ethnicity: </b><bean:write name="assessmentReferralForm" property="raceOrEthinicity"/></p></td>
		</tr>
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>
	<table width="100%" border-style="none">	
		 <tr align="center">
			<td align="center" colspan="3"><p><b>PARENT(S) WAS NOTIFIED THAT A GANG ASSESSMENT WAS REQUESTED FOR THE YOUTH?</b></p></td>
		</tr>
	</table>
	<table  width="100%" border-style="none">
		<tr align="center" >
			<td align="center" width="100%" colspan="3"><p><bean:write name="assessmentReferralForm" property="parentNotifiedGangAssReq"/></p></td>
		</tr>
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>
	 <table width="100%" border-style="none">	
		 <tr align="center">
			<td align="center" colspan="3"><p><b>REASON FOR REFERRAL</b></p></td>
		</tr>
	</table>
	<table width="100%" border-style="none">	
			<c:set var="reasonForReferralList" value="${reportInfo.selectedReasonForReferralsList}" scope="session"/>
			<c:forEach var="reasonForReferral" items="${reasonForReferralList}" varStatus="status">
		 <tr align="center">
			 <td align="center" width="100%" colspan="3"><p align="center">
				<c:out value="${reasonForReferral}"/>
				</p>
			</td>
		 </tr>
			</c:forEach>	
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>
	
	<table width="100%" border-style="none">		
			<tr align="center">
				<td align="left" colspan="3"><p><b>NAME OF GANG SUSPECTED</b></p></td>
			</tr>
			<tr></tr>
			<tr>	
				<td align="left" colspan="3"><p>
					<b>Gang Name:</b> <bean:write name="assessmentReferralForm" property="gangName"/></p>
				</td>
			</tr>
			<logic:notEmpty name="assessmentReferralForm" property="descHybrid">
				<tr align="center">
					<td align="left" colspan="3"><p><b>Describe Hybrid: </b>
							<bean:write name="assessmentReferralForm" property="descHybrid"/></p>
					</td>
				</tr>
			</logic:notEmpty>
			<logic:equal name="assessmentReferralForm"  property="gangName" value="OTHER">
				<tr align="center">
					<td align="left" colspan="3"><p><b>Other Gang Name: </b>
						<bean:write name="assessmentReferralForm" property="otherGangName"/></p>
					</td>
				</tr>
			</logic:equal>
			<logic:notEmpty name="assessmentReferralForm" property="cliqueSet">
				<tr>
					<td align="left" colspan="3"><p>
						<b>Clique/Set:</b> <bean:write name="assessmentReferralForm" property="cliqueSet"/></p>
					</td>
				</tr>
			</logic:notEmpty>
			<logic:equal name="assessmentReferralForm"  property="cliqueSet" value="OTHER">
				<logic:notEmpty name="assessmentReferralForm" property="otherCliqueSet">
					<tr align="center">
						<td align="left" colspan="3"><p><b>Other Clique Set: </b>
							<bean:write name="assessmentReferralForm" property="otherCliqueSet"/></p>
						</td>
					</tr>
				</logic:notEmpty>
			</logic:equal>
			<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
			<tr><td align="left" colspan="4" border-top = ".5"  padding-bottom="10"></td></tr>
	</table>
	 <table width="100%" border-style="none">	
		 <tr align="center">
			<td align="center" colspan="3"><p><b>LEVEL OF GANG INVOLVEMENT</b></p></td>
		</tr>
	</table>
	<table  width="100%" border-style="none">
		<tr align="center" >
			<td align="center" width="100%" colspan="3"><p><bean:write name="assessmentReferralForm" property="lvlOfGangInvolvement"/></p></td>
		</tr>
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>
	<table width="100%" border-style="none">	
		 <tr align="center">
			<td align="center" colspan="3"><p><b>PARENT(S) WAS NOTIFIED THAT A GANG ASSESSMENT WILL BE COMPLETED WITH THE YOUTH?</b></p></td>
		</tr>
	</table>
	<table  width="100%" border-style="none">
		<tr align="center" >
			<td align="center" width="100%" colspan="3"><p><bean:write name="assessmentReferralForm" property="parentNotified"/></p></td>
		</tr>
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>	
	<table  width="100%" border-style="none">
		<tr>
			<td  align="left" colspan="3"><p><b>Comments: </b><bean:write name="assessmentReferralForm" property="comments"/></p></td>
		</tr>
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>
	
 <logic:notEmpty name="assessmentReferralForm" property="acceptedStatus">
	<table  width="100%" border-style="none">
		<tr align="center">
			<td align="left"><p><b>Status: </b>
				<bean:write name="assessmentReferralForm" property="acceptedStatus"/></p>
			</td>
			<td align="left" colspan="2"><p><b>Recommendations: </b><bean:write name="assessmentReferralForm" property="recommendations"/></p></td>
		</tr>
		<tr align="center">
			<logic:notEmpty name="assessmentReferralForm"  property="rejectionReason">
				<td align="left" colspan="2"><p><b>Rejection Reason: </b>
					<bean:write name="assessmentReferralForm" property="rejectionReason"/></p>
				</td>
			</logic:notEmpty>
		</tr>
		<tr><td align="left" colspan="4" padding-bottom="10"></td></tr>
		<tr><td align="left" colspan="4" border-top = ".5" padding-bottom="10"></td></tr>
	</table>
	<table  width="100%" border-style="none">
		<tr>
			<td  align="left" colspan="3"><p><b>Conclusion: </b><bean:write name="assessmentReferralForm" property="conclusion"/></p></td>
		</tr>
	</table>
 </logic:notEmpty>
</body>
</pdf>
