<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
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
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<html:base />
<title>facilityHistoryDetentionRecord.jsp</title>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileBriefingDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">
<div class='spacer'></div>
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.juvenileProfile" /> - Facility Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<div class='spacer'></div>

<div align="left">
<table width="98%" border="0" align="left">
	<tr>
		<td>
			<ul>
				<li>Select "Close Window" button to close the window.</li>
			</ul>
		</td>
	</tr>
</table>
</div>
<%-- END INSTRUCTION TABLE --%>

<div align="left">
<table width="99%" border="0" cellpadding="0" cellspacing="1" align="center">
	<tr>
		<td valign='top' colspan="12">
		<%--Admission Information start --%>
		<table width="100%"  cellpadding="0"   border="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='detailHead'>
				<td class='paddedFourPix' colspan ="12">Juvenile Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' valign='top' width="1%"><bean:message key="prompt.name"/></td>
				<td class='formDe' colspan='3' width="50%" nowrap="nowrap"><bean:write name="assignedReferralsForm" property="profileDetail.formattedName"/></td>
					
	    		<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"> <bean:message key="prompt.juvenile#"/></td>
				<td class='formDe' colspan='2' width="25%"><bean:write name="assignedReferralsForm" property="profileDetail.juvenileNum"/></td>
						
				<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.phone"/></td>
	    		<td class='formDe' colspan='3' width="25%">
	    			<logic:notEmpty name="assignedReferralsForm" property="memberContact">
						<bean:write name="assignedReferralsForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber"/>
					</logic:notEmpty>
					<logic:notEmpty name="assignedReferralsForm" property="memberContact">
							Ext <bean:write name="assignedReferralsForm" property="memberContact.contactPhoneNumber.ext"/>
					</logic:notEmpty>
				</td>
			</tr>
		</table>
		
		<div class='spacer'></div>
		<div class='spacer'></div>
		
		<table  width="100%" cellpadding="0" border="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='detailHead'>
				<td class='paddedFourPix' colspan="12">Observation Status</td>
			</tr>
			<tr>
				<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.specialAttention"/></td>
				<td class='formDe' colspan='2' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.specialAttentionDesc"/></td>
			    <logic:notEmpty name="assignedReferralsForm" property="admissionInfo.saReasonDesc">
				    <td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.specialAttentionReason"/></td>
				    <td class='formDe' width="48%" colspan='6'><bean:write name="assignedReferralsForm" property="admissionInfo.saReasonDesc"/></td>
			    </logic:notEmpty>
			</tr>
			<logic:notEmpty name="assignedReferralsForm" property="admissionInfo.saReasonOtherComments">
					<tr>
						<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"  colspan='8'><bean:message key="prompt.specialAttentionOther"/></td>
					</tr>
					<tr>
						<td class='formDe' valign='top' width="99%" nowrap="nowrap"  colspan='8'><bean:write name="assignedReferralsForm" property="admissionInfo.saReasonOtherComments"/></td>
					</tr>
			</logic:notEmpty>
		</table>
		
	<div class='spacer'></div>
	<div class='spacer'></div>
	
	 <logic:notEmpty name="assignedReferralsForm" property="traits">
		   <table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue' align="center">
				<tr class='detailHead'>
					<td class='paddedFourPix' colspan="12">Detention Trait</td>
				</tr>
								
				<tr>
					<td colspan='12'>
						<table width="100%" cellpadding="2" cellspacing="1" border='0'>
							<thead>
								<tr class='formDeLabel'>
									<td><bean:message key="prompt.entryDate"/></td>
									<td><bean:message key="prompt.traitTypeDescription"/></td>
									<td><bean:message key="prompt.traitStatus"/></td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="traitsList" name="assignedReferralsForm" property="traits" indexId="indexer"> 
									<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>" height="100%">
										<td><bean:write name='traitsList' property='entryDate' formatKey="date.format.mmddyyyy" /></a></td>
										<td><bean:write name="traitsList" property="traitTypeDescription" /></td>
										<td><bean:write name="traitsList" property="status" /></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</logic:notEmpty>
		
		<div class='spacer'></div>
		<div class='spacer'></div>
		
		<logic:notEmpty name="assignedReferralsForm" property="admissionInfo.releaseTo">
			<table width="100%" cellpadding="0"   border="0" cellspacing="1" class='borderTableBlue' align="center">
				<tr class='detailHead'>
					<td colspan='12' class='paddedFourPix'>Release Details</td>
				</tr>
				<tr>
					<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseReason"/></td>
		    		<td class='formDe' width="25%" colspan='1' nowrap="nowrap"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.releaseReasonDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.releaseReason"/></span></td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseDate"/></td>
		    		<td class='formDe' width="25%"colspan='1'><bean:write name="assignedReferralsForm" property="admissionInfo.releaseDate" formatKey="date.format.mmddyyyy"/></td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseTime"/></td>
		    		<td class='formDe' colspan='1' width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.releaseTime"  formatKey="time.format.HHmm"/></td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releasedTo"/></td>
		    		<td class='formDe' colspan='8' width="50%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.relToDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.releaseTo"/></span></td>
		    		
		    	</tr>
		    	<tr>
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releasedBy"/></td>
		    		<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.relByDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.releaseBy"/></span></td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseAuthority"/></td>
		    		<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.relAuthByDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.releaseAuthorizedBy"/></span></td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.temporaryReleaseReason"/></td>
		    		<td class='formDe' width="50%" colspan='8' nowrap="nowrap"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.tempReleaseReasonCdDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.tempReleaseReasonCd"/></span></td>
		    		
		    	</tr>
		    	<tr>
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap">Person Assuming Custody</td>
		    		<td class='formDe' colspan='1' width="25%">&nbsp</td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap">Last Name</td>
		    		<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.custodylastName"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.custodylastName"/></span></td>
		    		
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap">First Name</td>
		    		<td class='formDe' width="50%" colspan='8' nowrap="nowrap"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.custodyfirstName"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.custodyfirstName"/></span></td>
		    		
		    	</tr>
		    	<logic:notEmpty name="assignedReferralsForm" property="admissionInfo.tempReleaseOtherComments">
			    	<tr>
			    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"  colspan='12'><bean:message key="prompt.temporaryReleaseReasonOtherComments"/></td>
			    	</tr>
			    	<tr>
			    		<td class='formDe' valign='top' nowrap="nowrap"  colspan='12'><bean:write name="assignedReferralsForm" property="admissionInfo.tempReleaseOtherComments"/></td>
			    	</tr>
		    	</logic:notEmpty>
		    </table>
		    <div class='spacer'></div>
			<div class='spacer'></div>	
	    </logic:notEmpty>
		
	    <table width="100%" cellpadding="0"   border="0" cellspacing="1" class='borderTableBlue' align="center" >
	    		<tr class='detailHead'>
					<td colspan='12' class='paddedFourPix'>Admission Information</td>
				</tr>
				
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitDate"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.admitDate" formatKey="date.format.mmddyyyy"/></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitTime"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.admitTime" formatKey="hh:mm a"/></td>
			    	
			    	<td class='formDeLabel'  width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitAuthorizedBy"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.admitAuthority"/></td>
			    </tr>
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.originalAdmitDate"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.originalAdmitDate" formatKey="date.format.mmddyyyy"/></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitReason"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.admitReasonDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.admitReason"/></span></td>
			    	
			    	<td class='formDeLabel' valign='top' width="1%"  nowrap="nowrap"><bean:message key="prompt.escapeStatus"/></td>
			    	<td class='formDe'  colspan='8'  width="50%">
				   		<logic:equal  name="assignedReferralsForm" property="admissionInfo.facilityStatus" value="E">	
				   		ESCAPED
				    	</logic:equal>
				    	<logic:equal  name="assignedReferralsForm" property="admissionInfo.facilityStatus" value="R">	
				   		RETURNED
				    	</logic:equal>
			    	</td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.admittedBy"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.admittedByJPODesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.admittedByJPO"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.facility"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="profileDetail.detentionFacility"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.detainedFacility"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.secureIndicator"/></td>
				   	<jims2:if name="assignedReferralsForm" property="admissionInfo.secureStatus" value="S" op="equal">
						<jims2:then>
			    			<td class='formDe' colspan='8' colspan='1' width="50%"><span title='SECURE'><bean:write name="assignedReferralsForm" property="admissionInfo.secureStatus"/></span></td>
			    		</jims2:then>
			    		<jims2:else>
			    			<td class='formDe' colspan='8' width="50%"><span title='NON-SECURE'><bean:write name="assignedReferralsForm" property="admissionInfo.secureStatus"/></span></td>
			    		</jims2:else>
		    		</jims2:if>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.facilitySequence"/></td>
			    	<td class='formDe' colspan='1'  width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.facilitySequenceNumber"/></td>
			    	
					<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.detainedDate"/></td>
			    	<td class='formDe' colspan='9'  width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.detainedDate" formatKey="date.format.mmddyyyy"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.bookingReferral"/></td>
			    	<td class='formDe' colspan='1'  width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.referralNumber"/></td>
			    	
			   		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.bookingOffense"/></td>
			   		<td class='formDe' colspan='1'  width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.bookingOffenseDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.bookingOffenseCd"/></span</td> 
			   		
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.bookingSupervision"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.bookingSupervisionNum"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.currentReferral"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.currentReferral"/></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.currentOffense"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.currentOffenseDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.currentOffense"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.currentSupervision"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.currentSupervisionNum"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.referralSource"/></td>
			    	<td class='formDe' colspan="1" width="50%"><span title='<bean:write name="assignedReferralsForm" property="admissionInfo.referralSourceDesc"/>'><bean:write name="assignedReferralsForm" property="admissionInfo.referralSource"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.referralOfficer"/></td>
			    	<td class='formDe' colspan='9' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.referralOfficer"/></td>
			    </tr>
			    <logic:notEmpty  name="assignedReferralsForm" property="admissionInfo.comments">
				    <tr>
				    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap" colspan='12'><bean:message key="prompt.admissionOrFacilityComments"/></td>
				    </tr>
				    
				    <tr>
				    	<td class='formDe' colspan='12' wrap><bean:write name="assignedReferralsForm" property="admissionInfo.comments"/></td>
				    </tr>
			    </logic:notEmpty>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.floorLocation"/></td>
			    	<td class='formDe' colspan="1" width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.floorNum"/></td>
			    	
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.roomLocation"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="assignedReferralsForm" property="admissionInfo.roomNum"/>
				   		<logic:notEqual name="assignedReferralsForm" property="admissionInfo.multipleOccupyUnit" value="">
				   			- <bean:write name="assignedReferralsForm" property="admissionInfo.multipleOccupyUnit"/>	
				   		</logic:notEqual>
			    	</td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.unitLocation"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.unit"/></td
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.valuableReceipt"/></td>
			    	<td class='formDe' colspan='1' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.receiptNumber"/></td>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.locker"/></td>
			    	<td class='formDe' colspan='9' width="50%"><bean:write name="assignedReferralsForm" property="admissionInfo.lockerNumber"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.floorRoomUnitChange"/></td>
			    	<td class='formDe' colspan='1' width="25%">
				   		<logic:equal name="assignedReferralsForm" property="admissionInfo.locationChangeFlag" value="true">Yes</logic:equal>
				   		<logic:equal name="assignedReferralsForm" property="admissionInfo.locationChangeFlag" value="false">No</logic:equal>
			    	</td>
					<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.secureStatusChange"/></td>
			    	<td class='formDe' colspan='1'  width="25%">
			    		<logic:equal name="assignedReferralsForm" property="admissionInfo.secureIndicatorChangeFlag" value="true">Yes</logic:equal>
				   		<logic:equal name="assignedReferralsForm" property="admissionInfo.secureIndicatorChangeFlag" value="false">No</logic:equal>
				   	</td>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.admitReasonChange"/></td>
			    	<td class='formDe' colspan='8' width="50%">
			    		<logic:equal name="assignedReferralsForm" property="admissionInfo.reasonChangeFlag" value="true">Yes</logic:equal>
				   		<logic:equal name="assignedReferralsForm" property="admissionInfo.reasonChangeFlag" value="false">No</logic:equal>
			    	</td>
			    </tr>
			    <logic:notEmpty  name="assignedReferralsForm" property="admissionInfo.changeExplanation">
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap" colspan='12'><bean:message key="prompt.changeExplanation"/></td>
			    </tr>
			    <tr>
			    	<td class='formDe' nowrap="nowrap" colspan='12'><bean:write name="assignedReferralsForm" property="admissionInfo.changeExplanation"/></td>
			    </tr>
			    </logic:notEmpty>
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.escapeAttempts"/></td>
			    	<td class='formDe' colspan='12'><bean:write name="assignedReferralsForm" property="admissionInfo.escapeAttempts"/></td>
			    </tr>
			    <logic:notEmpty  name="assignedReferralsForm" property="admissionInfo.escapeAttemptComments">
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap" colspan='12'><bean:message key="prompt.escapeAttemptComments"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDe' nowrap="nowrap" colspan='12'><bean:write name="assignedReferralsForm" property="admissionInfo.escapeAttemptComments"/></td>
			    </tr>
			    </logic:notEmpty>
			    <logic:notEmpty  name="assignedReferralsForm" property="firstDetainedDate">
			      <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap">First Detained Date</td>
			    	<td class='formDe' colspan='1' width="50%"><bean:write name="assignedReferralsForm" property="firstDetainedDate" formatKey="date.format.mmddyyyy"/></td>
					<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap">Last Detained Date</td>
			    	<td class='formDe' colspan='9'><bean:write name="assignedReferralsForm" property="lastDetainedDate" formatKey="date.format.mmddyyyy"/></td>					
			    </tr>
			    </logic:notEmpty>
		 </table>	
	<%--Admission Information end --%>
	
		<%--buton table start --%>
		<table border="0" width="100%">
			<tr>
				<td align="center">
					<html:button property="submitAction" onclick="window.close();"><bean:message key="button.close"/></html:button>   
				</td>
			</tr>
		</table>
		<%--buton table end--%>
		</td>
	</tr>
</table>
<%-- END  TABLE --%>
</html:form>
</body>
</html:html>