<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<html:base />
	
	<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- gangAssessmentReferralDetails.jsp</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<%-- Javascript for emulated navigation --%>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
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
</head>

<body footer="myfooter" footer-height="5mm" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!--  Header information -->
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" width="100%"><h2>GANG ASSESSMENT REFERRAL REPORT</h2></td>
		</tr>
		<tr></tr>
</table>
</div>
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center" >HARRIS COUNTY PROBATION JUVENILE CENTER</td>
		</tr>
</table>	
<html:form action="/displayGangAssessmentReferralList" target="content">
 <div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" colspan="2"><p><b>Referral Date: </b><bean:write name="assessmentReferralForm" property="referralDate"/></p></td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="left" colspan="0"><p><b>Person Making Referral: </b><bean:write name="assessmentReferralForm" property="personMakingRef"/></p></td>
			<td align="left" nowrap="nowrap" width="25%"><p><b>Placement Facility: </b><bean:write name="assessmentReferralForm" property="placementFacility"/></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="0"><p><b>Juvenile Name: </b><bean:write name="assessmentReferralForm" property="juvenileName"/></p></td>
			<td align="left" width="25%"><p><b>Juvenile#: </b><bean:write name="assessmentReferralForm" property="juvenileNum"/></p></td>
		</tr>
		<tr>
			<td align="left" colspan="0"><p><b>Date of Birth: </b><bean:write name="assessmentReferralForm" property="dateOfBirth"/></p></td>
			<td align="left" width="25%"><p><b>Gender: </b><bean:write name="assessmentReferralForm" property="gender"/></p></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="0"><p><b>Language: </b><bean:write name="assessmentReferralForm" property="language"/></p></td>
			<td align="left" width="25%"><p><b>Race/Ethnicity: </b><bean:write name="assessmentReferralForm" property="raceOrEthinicity"/></p></td>
		</tr>
			<tr>
			<td align="left" colspan="2">
			<hr color="Black" size="0.5">
		</td></tr>
	 </table>

		 <table width="100%" border-style="none">	
			 <tr align="center">
				<td align="center" colspan="0"><p><b>PARENT(S) WAS NOTIFIED THAT A GANG ASSESSMENT WAS REQUESTED FOR THE YOUTH?</b></p></td>
			</tr>
		</table>
		<table  width="100%" border-style="none">
			<tr align="center" >
				<td  align="center" width="100%" colspan="0"><p><bean:write name="assessmentReferralForm" property="parentNotifiedGangAssReq"/></p></td>
			</tr>				
			<tr><td align="left" colspan="0">
			<hr color="Black" size="0.5"></td></tr>
		</table>
	 <table width="100%" border-style="none">	
		 <tr align="center">
			<td align="center" colspan="0"><p><b>REASON FOR REFERRAL</b></p></td>
		</tr>
	</table>
	<table  width="100%" border-style="none">	
		<logic:notEmpty name="assessmentReferralForm"  property="selectedReasonForReferralsList">
			<logic:iterate indexId="index" id="reasonForReferralId" name="assessmentReferralForm" property="selectedReasonForReferralsList">
				<tr align="center" width="100%"><td align="center" width="100%" colspan="0"><p align="center">
				<bean:write name="reasonForReferralId"/></p></td>
				</tr>
			</logic:iterate> 
		</logic:notEmpty>
		
		<tr><td align="left" colspan="0">
		<hr color="Black" size="0.5">
		</td></tr>
	 </table>
	 
	<table width="100%" border-style="none">		
			<tr align="center">
				<td align="left" colspan="0"><p><b>NAME OF GANG SUSPECTED</b></p></td>
			</tr>
			<tr></tr>
			<tr>	
				<td align="left" colspan="0"><p>
					<b>Gang Name:</b> <bean:write name="assessmentReferralForm" property="gangName"/></p>
				</td>
			</tr>
			<logic:equal name="assessmentReferralForm"  property="gangName" value="HYBRID">
			<logic:notEmpty name="assessmentReferralForm" property="descHybrid">
				<tr align="center">
					<td align="left" colspan="0"><p><b>Describe Hybrid: </b>
							<bean:write name="assessmentReferralForm" property="descHybrid"/></p>
					</td>
				</tr>
			</logic:notEmpty>
			</logic:equal>
			<logic:equal name="assessmentReferralForm"  property="gangName" value="OTHER">
				<tr align="center">
					<td align="left" colspan="0"><p><b>Other Gang Name: </b>
						<bean:write name="assessmentReferralForm" property="otherGangName"/></p>
					</td>
				</tr>
			</logic:equal>
			<logic:notEmpty name="assessmentReferralForm" property="cliqueSet">
				<tr>
					<td align="left" colspan="0"><p>
						<b>Clique/Set:</b> <bean:write name="assessmentReferralForm" property="cliqueSet"/></p>
					</td>
				</tr>
			</logic:notEmpty>
			<logic:equal name="assessmentReferralForm"  property="cliqueSet" value="OTHER">
				<logic:notEmpty name="assessmentReferralForm" property="otherCliqueSet">
					<tr align="center">
						<td align="left" colspan="0"><p><b>Other Clique Set: </b>
							<bean:write name="assessmentReferralForm" property="otherCliqueSet"/></p>
						</td>
					</tr>
				</logic:notEmpty>
			</logic:equal>
			<tr><td align="left" colspan="0">
		<hr color="Black" size="0.5"></td></tr>
	</table>
	 <table width="100%" border-style="none">	
		 <tr align="center">
			<td align="center" colspan="0"><p><b>LEVEL OF GANG INVOLVEMENT</b></p></td>
		</tr>
	</table>
	<table  width="100%" border-style="none">
		<tr align="center" >
			<td  align="center" width="100%" colspan="0"><p><bean:write name="assessmentReferralForm" property="lvlOfGangInvolvement"/></p></td>
		</tr>				
		<tr><td align="left" colspan="0">
		<hr color="Black" size="0.5"></td></tr>
	</table>
	</table>
	<!-- check if its not empty then print after rob removes the constraint -->
	<logic:notEmpty name="assessmentReferralForm" property="parentNotified">
		 <table width="100%" border-style="none">	
			 <tr align="center">
				<td align="center" colspan="0"><p><b>PARENT(S) WAS NOTIFIED THAT A GANG ASSESSMENT WILL BE COMPLETED WITH THE YOUTH?</b></p></td>
			</tr>
		</table>
		<table  width="100%" border-style="none">
			<tr align="center" >
				<td  align="center" width="100%" colspan="0"><p><bean:write name="assessmentReferralForm" property="parentNotified"/></p></td>
			</tr>				
			<tr><td align="left" colspan="0">
			<hr color="Black" size="0.5"></td></tr>
		</table>
	</logic:notEmpty>	
	<table  width="100%" border-style="none">
		<tr>
			<td  align="left" colspan="0"><p><b>Comments: </b><bean:write name="assessmentReferralForm" property="comments"/></p></td>
		</tr>
		<tr><td align="left" colspan="0">
		<hr color="Black" size="0.5"></td></tr>
	</table>
	
	
 <logic:notEmpty name="assessmentReferralForm" property="acceptedStatus">
	<table  width="100%" border-style="none">
		<tr align="center">
			<td align="left"><p><b>Status: </b>
				<bean:write name="assessmentReferralForm" property="acceptedStatus"/></p>
			</td>
			<td align="left" colspan="0"><p><b>Recommendations: </b><bean:write name="assessmentReferralForm" property="recommendations"/></p></td>
		</tr>
		<tr align="center">
			<logic:notEmpty name="assessmentReferralForm"  property="rejectionReason">
				<td align="left" colspan="2"><p><b>Rejection Reason: </b>
					<bean:write name="assessmentReferralForm" property="rejectionReason"/></p>
				</td>
			</logic:notEmpty>
		</tr>
		
		<tr><td align="left" colspan="2">
		<hr color="Black" size="0.5"></td></tr>
	</table>
	<table  width="100%" border-style="none">
		<tr>
			<td  align="left" colspan="0"><p><b>Conclusion: </b><bean:write name="assessmentReferralForm" property="conclusion"/></p></td>
		</tr>
	</table>
 </logic:notEmpty>
</div>
<div class="spacer"></div>
<div class="spacer"></div>
	<table border="0" width="100%">
	  <tr>
		<td align="center">
	   	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GANGASSESS_V%>'>
		   	<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">	
					 <html:submit property="submitAction"><bean:message key="button.print"/></html:submit>
			</logic:equal>
		 </jims2:isAllowed>
		<html:button property="submitAction" onclick="window.close();"><bean:message key="button.close"/></html:button>
		</td>
	  </tr>
	</table>

<div class="spacer"></div>
		<%-- END BUTTON TABLE --%>
<%-- END DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
