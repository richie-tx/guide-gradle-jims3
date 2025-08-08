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
<title>facilityAdmissionRecordDetails.jsp</title>
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
		<td align="center" class="header">Process Juvenile Facility Admission - Facility Details</td>
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
			<tr class='facDetailHead'>
				<td class='paddedFourPix' colspan ="12">Juvenile Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' valign='top' width="1%"><bean:message key="prompt.name"/></td>
				<td class='formDe' colspan='3' width="25%" nowrap="nowrap"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedName"/></td>
					
	    		<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"> <bean:message key="prompt.juvenile#"/></td>
				<td class='formDe' colspan='2' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></td>
						
				<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.phone"/></td>
	    		<td class='formDe' colspan='3' width="50%">
					<bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber"/>
					<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.ext">
							Ext <bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.ext"/>
					</logic:notEmpty>
				</td>
			</tr>
		</table>
		
		<div class='spacer'></div>
		<div class='spacer'></div>
		
		<table  width="100%" cellpadding="0" border="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td class='paddedFourPix' colspan="12">Observation Status</td>
			</tr>
			<tr>
				<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.specialAttention"/></td>
			    <td class='formDe' colspan='2' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.specialAttentionDesc"/></td>
			    <logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.saReasonDesc">
				    <td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.specialAttentionReason"/></td>
				    <td class='formDe' width="48%" colspan='6'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.saReasonDesc"/></td>
			    </logic:notEmpty>
			</tr>
			<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.saReasonOtherComments">
			<%-- 	<logic:notEqual name="juvenileBriefingDetailsForm" property="admissionInfo.specialAttention" value="NO">added for bug 40650 --%>
					<tr>
						<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"  colspan='8'><bean:message key="prompt.specialAttentionOther"/></td>
					</tr>
					<tr>
						<td class='formDe' valign='top' width="99%" nowrap="nowrap"  colspan='8'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.saReasonOtherComments"/></td>
					</tr>
				<%-- </logic:notEqual> --%>
			</logic:notEmpty>
		</table>
		
	<div class='spacer'></div>
	<div class='spacer'></div>
	
	 <logic:notEmpty name="juvenileBriefingDetailsForm" property="traits">
		   <table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue' align="center">
				<tr class='facDetailHead'>
					<td class='paddedFourPix' colspan="12">Detention Trait</td>
				</tr>
								
				<tr>
					<td colspan='12'>
						<table width="100%" cellpadding="2" cellspacing="1" border='0'>
							<thead>
								<%-- static, column titles --%>
								<tr class='formDeLabel'>
									<td><bean:message key="prompt.entryDate"/></td>
									<td><bean:message key="prompt.traitTypeDescription"/></td>
									<td><bean:message key="prompt.traitStatus"/></td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="traitsList" name="juvenileBriefingDetailsForm" property="traits" indexId="indexer"> 
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
		
		<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo">
		<table width="100%" cellpadding="0"   border="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td colspan='12' class='paddedFourPix'>Release Details</td>
			</tr>
			<tr>
				<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseReason"/></td>
	    		<td class='formDe' width="25%" colspan='1' nowrap="nowrap"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason"/></span></td>
	    		
	    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseDate"/></td>
	    		<td class='formDe' width="25%"colspan='1'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseDate" formatKey="date.format.mmddyyyy"/></td>
	    		
	    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseTime"/></td>
	    		<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTime"  formatKey="time.format.HHmm"/></td>
	    		
	    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releasedTo"/></td>
	    		<td class='formDe' colspan='8' width="50%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relToDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo"/></span></td>
	    		
	    	</tr>
	    	<tr>
	    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releasedBy"/></td>
	    		<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relByDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseBy"/></span></td>
	    		
	    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseAuthority"/></td>
	    		<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseAuthorizedBy"/></td>
	    		
	    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.temporaryReleaseReason"/></td>
	    		<td class='formDe' width="50%" colspan='8' nowrap="nowrap"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseReasonCdDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseReasonCd"/></span></td>
	    		
	    	</tr>
	    	<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseOtherComments">
		    	<tr>
		    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"  colspan='12'><bean:message key="prompt.temporaryReleaseReasonOtherComments"/></td>
		    	</tr>
		    	<tr>
		    		<td class='formDe' valign='top' nowrap="nowrap"  colspan='12'><div style="width: 1100px; word-wrap: break-word"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseOtherComments"/></div></td>
		    	</tr>
	    	</logic:notEmpty>
	    </table>
	    <div class='spacer'></div>
		<div class='spacer'></div>	
	    </logic:notEmpty>
		
	    <table width="100%" cellpadding="0"   border="0" cellspacing="1" class='borderTableBlue' align="center" >
	    		<tr class='facDetailHead'>
					<td colspan='12' class='paddedFourPix'>Admission Information</td>
				</tr>
				
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitDate"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitDate" formatKey="date.format.mmddyyyy"/></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitTime"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitTime" formatKey="hh:mm a"/></td>
			    	
			    	<td class='formDeLabel'  width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitAuthorizedBy"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitAuthority"/></td>
			    </tr>
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.originalAdmitDate"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.originalAdmitDate" formatKey="date.format.mmddyyyy"/></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.admitReason"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitReason"/></span></td>
			    	
			    	<td class='formDeLabel' valign='top' width="1%"  nowrap="nowrap"><bean:message key="prompt.escapeStatus"/></td>
			    	<td class='formDe'  colspan='8'  width="50%">
				   		<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="E">	
				   		ESCAPED
				    	</logic:equal>
				    	<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R">	
				   		RETURNED
				    	</logic:equal>
			    	</td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.admittedBy"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admittedByJPODesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admittedByJPO"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.facility"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionFacility"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacility"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.secureIndicator"/></td>
				   	<jims2:if name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus" value="S" op="equal">
						<jims2:then>
			    			<td class='formDe' colspan='8' colspan='1' width="50%"><span title='SECURE'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus"/></span></td>
			    		</jims2:then>
		    		<jims2:else>
		    			<td class='formDe' colspan='8' width="50%"><span title='NON-SECURE'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus"/></span></td>
		    		</jims2:else>
		    		</jims2:if>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.facilitySequence"/></td>
			    	<td class='formDe' colspan='1'  width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.facilitySequenceNumber"/></td>
			    	
					<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.detainedDate"/></td>
			    	<td class='formDe' colspan='9'  width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedDate" formatKey="date.format.mmddyyyy"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.bookingReferral"/></td>
			    	<td class='formDe' colspan='1'  width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralNumber"/></td>
			    	
			   		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.bookingOffense"/></td>
			   		<td class='formDe' colspan='1'  width="25%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCodeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCode"/></span></td>
			   		
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.bookingSupervision"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.bookingSupervisionNum"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.currentReferral"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.currentReferral"/></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.currentOffense"/></td>
			    	<td class='formDe' colspan='1' width="25%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.currentOffenseDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.currentOffense"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.currentSupervision"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.currentSupervisionNum"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.referralSource"/></td>
			    	<td class='formDe' colspan="1" width="50%"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSourceDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSource"/></span></td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.referralOfficer"/></td>
			    	<td class='formDe' colspan='9' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralOfficer"/></td>
			    </tr>
			    <logic:notEmpty  name="juvenileBriefingDetailsForm" property="admissionInfo.comments">
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap" colspan='12'><bean:message key="prompt.admissionOrFacilityComments"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDe' colspan='12'><div style="width: 900px; word-wrap: break-word"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.comments"/></div></td>
			    </tr>
			    </logic:notEmpty>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.floorLocation"/></td>
			    	<td class='formDe' colspan="1" width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.floorNum"/></td>
			    	
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.roomLocation"/></td>
			    	<td class='formDe' colspan='1' width="25%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.roomNum"/>
				   		<logic:notEqual name="juvenileBriefingDetailsForm" property="admissionInfo.multipleOccupyUnit" value="">
				   			- <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.multipleOccupyUnit"/>	
				   		</logic:notEqual>
			    	</td>
			    	
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.unitLocation"/></td>
			    	<td class='formDe' colspan='8' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.unit"/></td
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.valuableReceipt"/></td>
			    	<td class='formDe' colspan='1' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.receiptNumber"/></td>
			    	<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.locker"/></td>
			    	<td class='formDe' colspan='9' width="50%"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.lockerNumber"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.floorRoomUnitChange"/></td>
			    	<td class='formDe' colspan='1' width="25%">
				   		<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.locationChangeFlag" value="true">Yes</logic:equal>
				   		<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.locationChangeFlag" value="false">No</logic:equal>
			    	</td>
					<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.secureStatusChange"/></td>
			    	<td class='formDe' colspan='1'  width="25%">
			    		<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.secureIndicatorChangeFlag" value="true">Yes</logic:equal>
				   		<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.secureIndicatorChangeFlag" value="false">No</logic:equal>
				   	</td>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.admitReasonChange"/></td>
			    	<td class='formDe' colspan='8' width="50%">
			    		<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.reasonChangeFlag" value="true">Yes</logic:equal>
				   		<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.reasonChangeFlag" value="false">No</logic:equal>
			    	</td>
			    </tr>
			    <logic:notEmpty  name="juvenileBriefingDetailsForm" property="admissionInfo.changeExplanation">
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap" colspan='12'><bean:message key="prompt.changeExplanation"/></td>
			    </tr>
			    <tr>
			    	<td class='formDe' colspan='12'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.changeExplanation"/></td>
			    </tr>
			    </logic:notEmpty>
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap"><bean:message key="prompt.escapeAttempts"/></td>
			    	<td class='formDe' colspan='12'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.escapeAttempts"/></td>
			    </tr>
			    <logic:notEmpty  name="juvenileBriefingDetailsForm" property="admissionInfo.escapeAttemptComments">
			    <tr>
			    	<td class='formDeLabel' width="1%"  valign='top' nowrap="nowrap" colspan='12'><bean:message key="prompt.escapeAttemptComments"/></td>
			    </tr>
			    
			    <tr>
			    	<td class='formDe' colspan='12'><div style="width: 900px; word-wrap: break-word"><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.escapeAttemptComments"/></div></td>
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