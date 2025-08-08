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
<title><bean:message key="title.heading"/>/facilityModifyAdmitRecordSummary.jsp</title>
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
<html:form action="/submitJuvenileFacilityModifyAdmit" target="content" styleId="summaryForm">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<logic:notEqual name="admitReleaseForm" property="action" value="confirm">
<table width='100%' id="summary">
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Modify Admission Record Summary</td>
	</tr>
</table>
</logic:notEqual>
<%-- END HEADING TABLE --%>

<%-- BEGIN HEADING TABLE CONFIRMATION --%>
<logic:equal name="admitReleaseForm" property="action" value="confirm">
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Modify Admission Confirmation</td>
	</tr>
	<tr>
		<td class="confirm">Admission Record Successfully Modified.</td>
	</tr>
</table>
</logic:equal>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
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

<%-- Observation status starts --%>
<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='8' class='paddedFourPix'>Observation Status</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.specialAttention"/></td>
		<td class='formDe' width="29%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="specialAttentionDesc"/></td>
		<logic:notEqual name="admitReleaseForm"  property="saReason" value= "">
			<td class='formDeLabel' valign='top' width="1%" colspan='1' nowrap><bean:message key="prompt.reason"/></td>
			<td class='formDe' width="79%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="saReasonStr"/></td>
		</logic:notEqual>
	</tr>
	<logic:notEqual name="admitReleaseForm"  property="saReasonOtherComments" value="">
		<tr id="saReasonOther1">
			<td class='formDeLabel' colspan="8">Other Reason Comments</td>					
		</tr>
		<tr id="saReasonOther2" >
	    	<td colspan='8' class='formDe'><bean:write name="admitReleaseForm" property="saReasonOtherComments"/></td>
	    </tr>
    </logic:notEqual>
</table>
<%-- Observation status ends --%>

<%--Release Information starts --%>
<jims2:if name="admitReleaseForm" property="facilityStatus" op="equal" value="N">
	<jims2:or name="admitReleaseForm" property="facilityStatus" value="T" op="equal"/>
	<jims2:then> 
	
	<div class='spacer'></div>
	<div class='spacer'></div>
	
	<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
		<tr class='facDetailHead'>
			<td align='left' colspan='8' class='paddedFourPix'>Release Information</td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseReason"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseReason"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseDate"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseDate"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseTime"/></td>
			<td class='formDe' width="24%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="releaseTime"/></td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releasedTo"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releasedToDesc"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseAuthority"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseAuthorityDesc"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releasedBy"/></td>
			<td class='formDe' width="24%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="releasedBy"/></td>
		</tr>
		<tr>
			<logic:notEqual  name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason" value="T">
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
				<td class='formDe' width="24%" colspan='7'  valign='top' nowrap><span title='<bean:write name="admitReleaseForm" property="outcomeDesc"/>'><bean:write name="admitReleaseForm" property="outcome"/></span></td>
			</logic:notEqual>
		</tr>
		<logic:equal  name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason" value="T">
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.temporaryReleaseReason"/></td>
				<td class='formDe' width="24%" colspan='7' nowrap><bean:write name="admitReleaseForm" property="tempReleaseReasonDesc"/></td>
			</tr>
			<logic:notEmpty name="admitReleaseForm" property="tempReleaseReasonOtherComments">
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Other Temporary Release Reason Comments</td>	
			    <td colspan='7' class='formDe'><div style="width: 1100px; word-wrap: break-word"><bean:write name="admitReleaseForm" property="tempReleaseReasonOtherComments"/></div></td>
			</tr>
		    </logic:notEmpty>				
		</logic:equal>
	</table>
	</jims2:then>
</jims2:if>

<logic:equal name="admitReleaseForm" property="facilityStatus" value="">		
	<logic:notEmpty name="admitReleaseForm" property="releasedTo">
		<div class='spacer'></div>
		<div class='spacer'></div>
	
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='8' class='paddedFourPix'>Release Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseReason"/></td>
				<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseReason"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseDate"/></td>
				<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseDate"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseTime"/></td>
				<td class='formDe' width="24%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="releaseTime"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releasedTo"/></td>
				<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releasedToDesc"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseAuthority"/></td>
				<td class='formDe' width="24%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseAuthorityDesc"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releasedBy"/></td>
				<td class='formDe' width="24%" colspan='3' nowrap><bean:write name="admitReleaseForm" property="releasedBy"/></td>
			</tr>
			<tr>
				<logic:notEqual  name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason" value="T">
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
					<td class='formDe' width="24%" colspan='7'  valign='top' nowrap><span title='<bean:write name="admitReleaseForm" property="outcomeDesc"/>'><bean:write name="admitReleaseForm" property="outcome"/></span></td>
				</logic:notEqual>
			</tr>
			<logic:equal  name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason" value="T">
				<tr>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.temporaryReleaseReason"/></td>
					<td class='formDe' width="24%" colspan='7' nowrap><bean:write name="admitReleaseForm" property="tempReleaseReasonDesc"/></td>
				</tr>
				<logic:notEmpty name="admitReleaseForm" property="tempReleaseReasonOtherComments">
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Other Temporary Release Reason Comments</td>	
				    	<td colspan='7' class='formDe'><bean:write name="admitReleaseForm" property="tempReleaseReasonOtherComments"/></td>
			    </logic:notEmpty>				
			</logic:equal>
		</table>
	</logic:notEmpty>				
</logic:equal>

	
<div class='spacer'></div>
<div class='spacer'></div>
	
<%--Return information starts --%> 
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
<jims2:if name="admitReleaseForm" property="facilityStatus" value="R" op="equal">
	<%-- <jims2:or name="admitReleaseForm" property="facilityStatus" value="E" op="equal"/>
	  <jims2:or name="admitReleaseForm" property="facilityStatus" value="R" op="equal"/> --%>
	   <jims2:then>
	<logic:equal name="admitReleaseForm" property="returnStatus" value="YES"> <!-- bug fix #26763 -->
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='8' class='paddedFourPix'>Return Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnReason"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="returnReasonDesc"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="returnDate"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="returnTime"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnStatus"/></td>
				<td class='formDe' width="15%" colspan='1' valign='top' nowrap><bean:write name="admitReleaseForm" property="returnStatus"/></td>
			</tr>
		</table>
	</logic:equal>
</jims2:then>
</jims2:if>
</logic:notEqual>
<%--Release Information ends--%>
<%--Escape Information starts--%>
<logic:equal name="admitReleaseForm" property="facilityStatus" value="E">
<div class='spacer'></div>
	<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
		<tr class='facDetailHead'>
			<td align='left' colspan='10' class='paddedFourPix'>Escape Information</td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeStatus"/></td>
			<td class='formDe'  width="15%">
				<logic:equal  name="admitReleaseForm" property="escapeStatus" value="E">ESCAPED</logic:equal>
				<logic:equal  name="admitReleaseForm" property="escapeStatus" value="R">RETURNED</logic:equal>
			</td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeDate"/></td>
			<td class='formDe' width="10%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="escapeDate"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeTime"/></td>
			<td class='formDe' width="10%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="escapeTime"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeFrom"/></td>
			<td class='formDe' width="25%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="escapeFromDesc"/></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
			<td class='formDe' width="25%" colspan='1'  valign='top' nowrap><bean:write name="admitReleaseForm" property="outcomeDesc"/></td>
		</tr>
	</table>
</logic:equal>
<%--Escape Information ends --%>
<%--Admission Information starts --%>
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
				<td class='formDe' colspan= '2' valign='top' width='15%' nowrap><span title='<bean:write name="admitReleaseForm" property="admitAuthorityDesc"/>'><bean:write name="admitReleaseForm" property="admitAuthority"/></span></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitDateStr"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitTime"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admittedBy"/></td>
				<td class='formDe' width="15%" colspan='3' nowrap><span title='<bean:write name="admitReleaseForm" property="admitByDesc"/>'><bean:write name="admitReleaseForm" property="admitBy"/></span></td>
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
							<td><bean:message key="prompt.valuables" />&nbsp;&nbsp;<bean:write name="admitReleaseForm" property="valuablesReceipt"/></td>
							<td><bean:message key="prompt.locker" />&nbsp;&nbsp;<bean:write name="admitReleaseForm" property="lockerLocation"/></td>
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
		    	<td colspan='10' class='formDe'> <div style="width: 1100px; word-wrap: break-word"><bean:write name="admitReleaseForm" property="admissionComments"/></div></td>
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
<%--Admission Information ends --%>

<div class='spacer'></div>
<div class='spacer'></div>

<%--Detention hearing Information ends --%>
<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='8' class='paddedFourPix'>Detention Hearing Information</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.dateDetained"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="detainedDate"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.nextHearingDate"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="nextHearingDate"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.court"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="courtId"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facilitySequence"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="facilitySeqNum"/></td>
	</tr>
</table>
<%--Detention hearing Information ends --%>
<%-- END  TABLE --%>
<br>
 <html:hidden property="action" styleId="action"/>
 <logic:notEqual name="admitReleaseForm" property="action" value="confirm">
 <table border="0" width="100%" id="summaryBtn" align="center">
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