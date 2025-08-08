<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>





<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/> 
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<title><bean:message key="title.heading"/>/facilityReferralTransferRecordSummary.jsp</title>
<script type="text/javascript">

$(document).ready(function(){	
	$("#gotoBriefing").click(function(){
		spinner();
	})
	
})
</script>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/submitJuvenileFacilityRelease" target="content" styleId="summaryForm">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="referralTransferView">
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Booking Transfer Summary</td>
	</tr>
</table>
</logic:equal>
<%-- END HEADING TABLE --%>

<%-- BEGIN HEADING TABLE CONFIRMATION --%>
<logic:equal name="admitReleaseForm" property="referralTransferAction" value="confirm">
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Booking Transfer Confirmation</td>
	</tr>
	<tr>
		<td class="confirm">Admission Record has been successfully Transferred {From: <bean:write name="admitReleaseForm" property="transferFromReferral"/> To: <bean:write name="admitReleaseForm" property="bookingReferral"/>}.</td>
	</tr>
</table>
</logic:equal>
<%-- END HEADING TABLE --%>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
		<td valign="top" colspan="4">
           <tiles:insert page="../caseworkCommon/juvenileFacilityDetailsTile.jsp" flush="false">
	        	 <tiles:put name="detailsForm" beanName="juvenileBriefingDetailsForm" />	
	        </tiles:insert>
          </td>
       </tr> 
</table>

<div class='spacer'></div>
<div class='spacer'></div>

<%-- END INSTRUCTION TABLE --%>

<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='6' class='paddedFourPix'>Referral Transfer Information</td>
	</tr>
	<tr>
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.transferFrom"/></td>
		<td class='formDe' width="5%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="transferFromReferral"/></td>
		
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.transferTo"/></td>
		<td class='formDe' width="5%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingReferral"/></td>
		
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/>#</td>
		<td class='formDe' width="5%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingPetitionNum"/></td>
	</tr>
	<tr>
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.bookingOffense"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><span title='<bean:write name="admitReleaseForm" property="bookingOffense"/>'><bean:write name="admitReleaseForm" property="bookingOffenseCd"/></span></td>
		
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.offenseLevel"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingOffenseLevel"/></td>
		
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.bookingSupervision"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingSupervisionNum"/></td>
	</tr>
</table>

<div class='spacer'></div>
<div class='spacer'></div>

<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='6' class='paddedFourPix'>Current Referral Information</td>
	</tr>
	<tr>
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.currentReferral"/></td>
		<td class='formDe' width="5%"  colspan="1" nowrap><bean:write name="admitReleaseForm" property="currentReferral"/></td>
		
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/>#</td>
		<td class='formDe' width="4%" colspan="1"  nowrap><bean:write name="admitReleaseForm" property="bookingPetitionNum"/></td>
	
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.currentOffense"/></td>
		<td class='formDe' width="5%" colspan="2" nowrap><span title='<bean:write name="admitReleaseForm" property="currentOffense"/>'><bean:write name="admitReleaseForm" property="currentOffenseCd"/></span></td>
	</tr>
	<tr>
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.offenseLevel"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="currentOffenseLevel"/></td>
		
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.currentSupervision"/></td>
		<td class='formDe' width="1%"  colspan="4" nowrap><bean:write name="admitReleaseForm" property="currentSupervisionNum"/></td>
	</tr>
</table>

<br>

	 <logic:notEqual name="admitReleaseForm" property="action" value="confirm">
	 <table border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 		
				<html:submit property="submitAction" styleId="finish"><bean:message key="button.finish"/></html:submit>			
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table>
	</logic:notEqual>
</html:form>

	<logic:equal name="admitReleaseForm" property="action" value="confirm">
	<table border="0" width="100%" align="center">
			<tr>
				<td align="right" width="1%">
					<html:form action="/displayJuvenileProfileSearch.do?isFacility=true" target="content"> 
						<html:submit onclick="spinner()" property="submitAction">Search Facility Admissions</html:submit>
					</html:form>
				</td>
				<td align="left" width="1%">
					<input type="button" id="gotoBriefing" onclick="goNav('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=facilityLink&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>&supervisionNum=<bean:write name="admitReleaseForm" property="currentSupervisionNum"/>');" value="View Facility Briefing"/>
				</td>
			</tr>
	</table>
	</logic:equal>
</body>
</html:html>