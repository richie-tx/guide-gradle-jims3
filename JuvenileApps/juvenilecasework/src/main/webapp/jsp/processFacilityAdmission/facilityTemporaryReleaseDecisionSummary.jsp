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
<%@page import="mojo.km.utilities.DateUtil"%>




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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css"/>
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<html:base />
<title><bean:message key="title.heading"/>/facilityTemporaryReleaseDecisionSummary.jsp</title>
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
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="tempReleaseDecisionView">	
<table width='100%' id="summary">
	<tr>
		<td align="center" class="header">Process Juvenile Facility - Temporary Release Decision Summary</td>
	</tr>
</table>
</logic:equal>
<%-- END HEADING TABLE --%>






<%-- BEGIN HEADING TABLE CONFIRMATION --%>
<logic:equal name="admitReleaseForm" property="action" value="confirm">
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility - Temporary Release Decision Confirmation</td>
	</tr>
	<tr>
		<td class="confirm">Temporary Release Decision submitted. Email has been sent</td>
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




<%-- Facility Admission information --%>
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
	
	<div class='spacer'></div>
	<div class='spacer'></div>
	
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='8' class='paddedFourPix'>Admission Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.changeLabel"/></td>
				<td class='formDe' width="15%" valign='top' colspan='7' nowrap><bean:write name="admitReleaseForm" property="changeLabel"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facility"/></td>
				<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="detainedFacilityStr"/>'><bean:write name="admitReleaseForm" property="detainedFacility"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Secure Status</td>
				<logic:equal  name="admitReleaseForm" property="secureStatus" value="S">
		    		<td class='formDe' width="15%" valign='top' colspan='1' nowrap>SECURE</td>
		    	</logic:equal>
		    	<logic:equal  name="admitReleaseForm" property="secureStatus" value="N">
		    		<td class='formDe' width="15%" valign='top' colspan='1' nowrap>NON-SECURE</span></td>
		    	</logic:equal>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitAuthorizedBy"/></td>
				<td class='formDe' colspan= '2' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="admitAuthority"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitDateStr"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitTime"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admittedBy"/></td>
				<td class='formDe' width="15%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="admitBy"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingReferral"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="bookingReferral"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingOffense"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="bookingOffense"/>'><bean:write name="admitReleaseForm" property="bookingOffenseCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingSupervision"/></td>
				<td class='formDe' width="15%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="bookingSupervisionNum"/></td>
				
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentReferral"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="currentReferral"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentOffense"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="currentOffense"/>'><bean:write name="admitReleaseForm" property="currentOffenseCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentSupervision"/></td>
				<td class='formDe' width="15%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="currentSupervisionNum"/></td>
				
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/></td>
				<td class='formDe' colspan= '1' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="petition"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralSource"/></td>
				<td class='formDe' colspan= '1' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralSource"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralOfficer"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralOfficers"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitReason"/></td>
				<td class='formDe' width="15%" colspan='7' nowrap><bean:write name="admitReleaseForm" property="admitReasonStr"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.location"/></td>
				<td  colspan='10' class='formDe' nowrap> 
					<table>
						<tr>
							<td><bean:write name="admitReleaseForm" property="locationOneLabel"/>&nbsp;&nbsp;<bean:write name="admitReleaseForm" property="floorLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="locationTwoLabel"/>&nbsp;&nbsp;<bean:write name="admitReleaseForm" property="unitLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="locationThreeLabel"/>&nbsp;&nbsp;<bean:write name="admitReleaseForm" property="roomLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="multipleOccupancyUnit"/></td>
						</tr>
					</table> 
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.reasonForChange"/></td>
				<td  colspan='9' class='formDe' nowrap><bean:write name="admitReleaseForm" property="reasonForChange"/></td>
			</tr>
			<logic:notEmpty name="admitReleaseForm" property="admissionComments">
			<tr id="admissionComments1">
				<td class='formDeLabel' colspan="10">Facility Admission Comments</td>
			</tr>
			<tr id="admissionComments2">
		    	<td colspan='10' class='formDe'><div style="width: 1100px; word-wrap: break-word"><bean:write name="admitReleaseForm" property="admissionComments"/></div></td>
		    </tr>
		    </logic:notEmpty>
			<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeAttempts"/></td>
			<td class='formDe' width="15%" colspan='9' nowrap><bean:write name="admitReleaseForm" property="numOfEscapeAttempts"/></td>
			</tr>
			<logic:notEmpty name="admitReleaseForm" property="escapeComments">
				<tr id="escapeComments1">
					<td class='formDeLabel' colspan="10">Escape Attempts Comments</td>
				</tr>
				<tr id="escapeComments2">
				   	<td colspan='10' class='formDe'><div style="width: 1100px; word-wrap: break-word"><bean:write name="admitReleaseForm" property="escapeComments"/></div></td>
				</tr>
			</logic:notEmpty>
		</table>
</logic:notEqual>
<%-- Facility Admission information --%>
<div class='spacer'></div>
<div class='spacer'></div>

<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.temporaryReleaseAllowed"/></td>
		
		<td class='formDe'  colspan='9' nowrap><bean:write name="admitReleaseForm" property="temporaryReleaseAllowed"/></td>
			<%-- <logic:equal name="admitReleaseForm" property="temporaryReleaseAllowed" value=""> --%>
						
	</tr>
	
	<!-- <div style="width: 1100px; word-wrap: break-word"></div> -->
		
	<logic:notEmpty name="admitReleaseForm" property="tempReleaseDecisionComments">
				<tr id="tempReleaseDecisionComments">
					<td class='formDeLabel' colspan='10' width='1%'>Comments</td>
				</tr>
				<tr id="tempReleaseDecisionComments">
				   	<td colspan='10' class='formDe' nowrap><bean:write name="admitReleaseForm" property="tempReleaseDecisionComments"/></td>
				</tr>
			</logic:notEmpty>
	
				
	
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.authorizingOfficer"/></td>
		<td class='formDe'  colspan='9' valign='top' nowrap><bean:write name="admitReleaseForm" property="authorizingOfficerName"/></td>
	</tr>		    
			    
	
</table>

<%-- END  TABLE --%>
<br>
 	 <html:hidden property="action" styleId="action"/>
	 <logic:notEqual name="admitReleaseForm" property="action" value="confirm">
		 <table border="0" width="100%" id="summaryBtn">
				<tr>
					<td align="center">
						<html:submit property="submitAction"  styleId="back" ><bean:message key="button.back"/></html:submit> 		
						<html:submit property="submitAction" styleId="finish"><bean:message key="button.finish"/></html:submit>			
						<html:submit property="submitAction"  styleId="cancel" ><bean:message key="button.cancel"/></html:submit>
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
					<html:submit property="submitAction">Search Facility Admissions</html:submit>
				</html:form>
			</td>
			<td align="left" width="1%">
			<logic:equal name="admitReleaseForm" property="releaseAction" value="confirm">
					<input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='admitReleaseForm' property='currentSupervisionNum'/>');" value="<bean:message key='button.viewCasefile'/>"/>
				</logic:equal>
			<input type="button" id="gotoBriefing" onclick="goNav('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=facilityLink&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>&supervisionNum=<bean:write name="admitReleaseForm" property="currentSupervisionNum"/>');" value="View Facility Briefing"/>
				
			</td>
		</tr>
</table>
</logic:equal>

</body>
</html:html>