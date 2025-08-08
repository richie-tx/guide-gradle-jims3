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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
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

<title><bean:message key="title.heading"/>/facilityModifyAdmitRecord.jsp</title>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileFacilityModifyAdmit" target="content">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Modify Admission Record</td>
	</tr>
</table>
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

<%-- BEGIN DETAIL TABLE --%>
<!-- Observation Status -->
<tiles:insert page="../caseworkCommon/juvenileFacilityObservationStatusTile.jsp" flush="false">
   	 <tiles:put name="admitReleaseForm" beanName="admitReleaseForm" />	
</tiles:insert>
<!-- Observation Status -->

<%--Release Information starts  --%>
<jims2:if name="admitReleaseForm" property="facilityStatus" op="equal" value="N">
	<jims2:or name="admitReleaseForm" property="facilityStatus" value="T" op="equal"/>
	<jims2:then> 
	
	<div class='spacer'></div>
	<div class='spacer'></div>
	
	<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
		<tr class='facDetailHead'>
			<td align='left' colspan='8' class='paddedFourPix'>Release Information</td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseReason"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason"/></td>
			
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseDate"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap> 
				<html:text name="admitReleaseForm" property="releaseDate" styleId="releaseDate" size="10" maxlength="10" size="10"/>
			</td>
			
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseTime"/></td>
			<td class='formDe' width="24%" colspan='3' nowrap>
				<input type="text" name="releaseTime" id="releaseTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="releaseTime"/>" />
			</td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releasedTo"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap>
				<html:select name="admitReleaseForm" property="releasedTo" styleId="releasedTo">
					<html:option key="select.generic" value="" />
					<html:optionsCollection property="releaseTo" value="code" label="descriptionWithCode"/> 				
				</html:select>
			</td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseAuthority"/></td>
			<td class='formDe' width="24%" colspan='1' nowrap>
				<input type="text" name="releaseAuthority" id="releaseAuthority" size="3" maxlength="3" value="<bean:write name="admitReleaseForm" property="releaseAuthority"/>" />
			</td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedBy"/></td>
			<td class='formDe' width="49%" colspan='3' nowrap>
				<input type="text" name="releasedBy" id="releasedBy" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="releasedBy"/>" />
			</td>
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
			<tr>
				<logic:notEmpty name="admitReleaseForm" property="tempReleaseReasonOtherComments">
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Other Temporary Release Reason Comments</td>
					<td class='formDe' width="24%" colspan='7' nowrap><bean:write name="admitReleaseForm" property="tempReleaseReasonOtherComments"/></td>
				</logic:notEmpty>
			</tr>
		</logic:equal>
	</table>
 </jims2:then>
</jims2:if>
	
<logic:equal name="admitReleaseForm" property="facilityStatus" value="">	
		<logic:equal name="admitReleaseForm" property="releasedTo" value="TRN">
				<div class='spacer'></div>
				<div class='spacer'></div>
			
				<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
					<tr class='facDetailHead'>
						<td align='left' colspan='8' class='paddedFourPix'>Release Information</td>
					</tr>
					<tr>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseReason"/></td>
						<td class='formDe' width="24%" colspan='1' nowrap><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason"/></td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseDate"/></td>
						<td class='formDe' width="24%" colspan='1' nowrap> 
							<html:text name="admitReleaseForm" property="releaseDate" styleId="releaseDate" size="10" maxlength="10" size="10"/>
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseTime"/></td>
						<td class='formDe' width="49%" colspan='3' nowrap>
							<input type="text" name="releaseTime" id="releaseTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="releaseTime"/>" />
						</td>
					</tr>
					<tr>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedBy"/></td>
						<td class='formDe' width="24%" colspan='1' nowrap>
							<input type="text" name="releasedBy" id="releasedBy" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="releasedBy"/>" />
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releasedTo"/></td>
						<td class='formDe' width="24%" colspan='1' nowrap>
							<html:select name="admitReleaseForm" property="releasedTo" styleId="releasedToId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="releaseTo" value="code" label="descriptionWithCode"/> 				
							</html:select>
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.releaseAuthority"/></td>
						<td class='formDe' width="24%" colspan='1' nowrap>
							<input type="text" name="releaseAuthority" id="releaseAuthority" size="3" maxlength="3" value="<bean:write name="admitReleaseForm" property="releaseAuthority"/>" />
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
						<td class='formDe' width="24%" colspan='1'  valign='top' nowrap><span title='<bean:write name="admitReleaseForm" property="outcomeDesc"/>'><bean:write name="admitReleaseForm" property="outcome"/></span></td>
					</tr>
				</table>	
		</logic:equal>
	</logic:equal>
<%--Release Information ends--%>

<%--Return information starts --%>  <!-- Bug fix : 27106 --> <!-- The bug got updated to check only for status R. Requirements issue. -->
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
<%-- <jims2:if name="admitReleaseForm" property="facilityStatus" value="T" op="equal">
	<jims2:or name="admitReleaseForm" property="facilityStatus" value="E" op="equal"/>    The bug got updated to check only for status R. Requirements issue--%>
	<jims2:if name="admitReleaseForm" property="facilityStatus" value="R" op="equal">
	   <jims2:then>
			<logic:equal name="admitReleaseForm" property="returnStatus" value="YES"> <!-- bug fix #26763 -->
				<div class='spacer'></div>
				<div class='spacer'></div> <%--Return Information starts --%>
				
				<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
					<tr class='facDetailHead'>
						<td align='left' colspan='8' class='paddedFourPix'>Return Information</td>
					</tr>
					<tr>
						<html:hidden styleId="returnTime" name="admitReleaseForm" property="returnTime"/>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnReason"/></td>
						<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="returnReasonDesc"/>'><bean:write name="admitReleaseForm" property="returnReason"/></span></td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnDate"/></td>
						<td class='formDe' width="15%" colspan='1' nowrap> 
							<html:text name="admitReleaseForm" property="returnDate" styleId="returnDate" size="10" maxlength="10" size="10"/>
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnTime"/></td>
						<td class='formDe' width="15%" colspan='1' nowrap>
							<input type="text" name="returnTime" id="returnTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="returnTime"/>" />
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnStatus"/></td>
						<td class='formDe' width="15%" colspan='1' valign='top' nowrap><bean:write name="admitReleaseForm" property="returnStatus"/></td>
					</tr>
				</table> 
		 	</logic:equal>
	</jims2:then>
	</jims2:if>
<%--Return information ends --%>

<%--Escape Information starts --%>
<logic:equal name="admitReleaseForm" property="facilityStatus" value="E">

	<div class='spacer'></div>
	<div class='spacer'></div>
	
	<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
		<tr class='facDetailHead'>
			<td align='left' colspan='10' class='paddedFourPix'>Escape Information</td>
		</tr>
		<tr>
			<html:hidden styleId="escapeStatus" name="admitReleaseForm" property="escapeStatus"/>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeStatus"/></td>
			<td class='formDe'  width="20%" valign="top">
				<logic:equal  name="admitReleaseForm" property="escapeStatus" value="E"><span title='ESCAPED'><bean:write name="admitReleaseForm" property="escapeStatus"/></span></logic:equal>
				<logic:equal  name="admitReleaseForm" property="escapeStatus" value="R"><span title='RETURNED'><bean:write name="admitReleaseForm" property="escapeStatus"/></span></logic:equal>
			</td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.escapeDate"/></td>
			<td class='formDe' width="15%" colspan='1' nowrap> 
				<html:text name="admitReleaseForm" property="escapeDate" styleId="escapeDate" size="10" maxlength="10" size="10" />
			</td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeTime"/></td>
			<td class='formDe' width="15%" colspan='1' nowrap>
				<input type="text" name="escapeTime" id="escapeTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="escapeTime"/>" />
			</td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeFrom"/></td>
			<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="escapeFromDesc"/>'><bean:write name="admitReleaseForm" property="escapeFrom"/></span></td>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
			<td class='formDe' width="24%" colspan='3'  valign='top' nowrap><span title='<bean:write name="admitReleaseForm" property="outcomeDesc"/>'><bean:write name="admitReleaseForm" property="outcome"/></span></td>
		</tr>
	</table>
</logic:equal>
<%--Escape Information ends --%>

<div class='spacer'></div>
<div class='spacer'></div>



	
<%--Admission Information starts --%>
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='8' class='paddedFourPix'>Admission Information</td>
			</tr>
			<tr>
				<td  colspan='8' align="left" nowrap> 
					<html:radio name="admitReleaseForm" property="changeLabelCd" value="FRUC" styleId="lc"/>Floor/Room/Unit Change
					<jims2:isAllowed requiredFeatures="<%=Features.JFA_MODIFY_ADMIT_REASON%>">  
						<html:radio name="admitReleaseForm" property="changeLabelCd" value="ADRC" styleId="ac"/>Admit Reason Change
					</jims2:isAllowed>					
					<html:radio name="admitReleaseForm" property="changeLabelCd" value="SESC" styleId="sc"/>Secure Status Change
					<html:radio name="admitReleaseForm" property="changeLabelCd" value="OTHR" styleId="oc"/>Other
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facility"/></td>
				<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="detainedFacilityStr"/>'><bean:write name="admitReleaseForm" property="detainedFacility"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Secure Status</td>
				<td  class='formDe' width="15%" colspan='1' nowrap> 
					<html:radio name="admitReleaseForm" property="secureStatus" value="S" styleId="ss1" disabled="true"/>Secure
					<html:radio name="admitReleaseForm" property="secureStatus" value="N" styleId="ss2" disabled="true"/>Non-Secure
				</td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitAuthorizedBy"/></td>
				<td class='formDe' colspan='3' valign='top' width='15%' nowrap><span title='<bean:write name="admitReleaseForm" property="admitAuthorityDesc"/>'><bean:write name="admitReleaseForm" property="admitAuthority"/></span></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitDateStr"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitTime"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admittedBy"/></td>
				<td class='formDe' width="15%" colspan='3' nowrap>
					<input type="text" name="admitBy" id="admitBy" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="admitBy"/>" />
				</td>
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
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralSource"/></td>
				<td class='formDe' colspan= '1' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralSource"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralOfficer"/></td>
				<td class='formDe' colspan= '1' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralOfficers"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="petition"/></td>
			</tr>
			<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitReason"/></td>
				<td class='formDe' width="15%" colspan='7' nowrap>
					<html:select property="reasonCode" styleId="admitReasonId" disabled="true" name="admitReleaseForm">
						<html:option key="select.generic" value="" />
						<html:optionsCollection property="admitReasons" value="codeWithDetType" label="descriptionWithCode"/>		
					</html:select>
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.location"/></td>
				<td  colspan='7' class='formDe' nowrap> 
					<table>
						<tr>
							<td><bean:write name="admitReleaseForm" property="locationOneLabel"/><input type="text" name="floorLocation" value="<bean:write name="admitReleaseForm" property="floorLocation"/>" maxlength="1" size="1" id="FL" disabled/></td>
							<td><bean:write name="admitReleaseForm" property="locationTwoLabel"/><input type="text" name="unitLocation" value="<bean:write name="admitReleaseForm" property="unitLocation"/>" maxlength="1" size="1" id="UL" disabled/></td>
							<td><bean:write name="admitReleaseForm" property="locationThreeLabel"/><input type="text" name="roomLocation" value="<bean:write name="admitReleaseForm" property="roomLocation"/>" maxlength="4" size="4" id="RL" disabled></td>
							<td>	
								<logic:equal name="admitReleaseForm" property="placementType" value="D">
								<bean:message key="prompt.mou"/>
									<html:select property="multipleOccupancyUnit" styleId="MOU" disabled="true" name="admitReleaseForm">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="multipleOccupancyUnits" value="code" label="description"/>				
									</html:select>
								</logic:equal>
							</td>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.valuables" />
							<input type="text" name="valuablesReceipt" value="<bean:write name="admitReleaseForm" property="valuablesReceipt"/>" maxlength="10" size="10" id="VL" disabled></td>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.locker" />
							<input type="text" name="lockerLocation" value="<bean:write name="admitReleaseForm" property="lockerLocation"/>" maxlength="6" size="6" id="LL" disabled></td>
						</tr>
					</table> 
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.reasonForChange"/></td>
				<td  colspan='7' class='formDe' nowrap> 
					<html:text property="reasonForChange" size="90" maxlength="90" styleId="reasonForChangeId"/>
				</td>
			</tr>
			<tr id="admissionComments1">  <!-- added for #51737 -->
				<td class='formDeLabel' colspan="8" nowrap><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityAdmissionComments.do?submitAction=Link&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>','comments',1000,800)"><bean:message key="prompt.facilityAdmissionComments"/></a>
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
						<tiles:put name="tTextField" value="admissionComments"/>
		                <tiles:put name="tSpellCount" value="spellBtn2" />
		            </tiles:insert>
		            (Max. characters allowed:350)
		        </td>					
			</tr>
			<tr id="admissionComments2">
		    	<td colspan='8' class='formDe'>
			    	<html:textarea name="admitReleaseForm" property="admissionComments" styleId="admissionComments"  rows="4"  style="width:100%"/>
		      	</td>
		    </tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeAttempts"/></td>
				<td class='formDe' colspan='7' nowrap>
					<input type="text" name="numOfEscapeAttempts" id="escapeAttempts" size="2" maxlength="2" value="<bean:write name="admitReleaseForm" property="numOfEscapeAttempts"/>" />
				</td>
			</tr>
			<tr id="escapeComments1">
				<td class='formDeLabel' colspan="8" nowrap>Escape Attempts Comments
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
						<tiles:put name="tTextField" value="escapeComments"/>
				        <tiles:put name="tSpellCount" value="spellBtn3" />
				     </tiles:insert>
				         (Max. characters allowed:350)
			    </td>					
			</tr>
			<tr id="escapeComments2">
			   	<td colspan='8' class='formDeLabel'>
			    	<html:textarea name="admitReleaseForm" property="escapeComments" styleId="escapeAttemptComments" rows="4" style="width:100%"/>
			   	</td>
			 </tr>
		</table>
</logic:notEqual>
<%--Admission Information ends --%>

<div class='spacer'></div>
<div class='spacer'></div>

<%--Detention hearing Information ends --%>
<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='8' class='paddedFourPix'>Detention Hearing Information</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.dateDetained"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="detainedDate" formatKey="date.format.mmddyyyy"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.nextHearingDate"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="nextHearingDate" formatKey="date.format.mmddyyyy"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.court"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="courtId"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facilitySequence"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="facilitySeqNum"/></td>
	</tr>
</table>
<%--Detention hearing Information ends --%>
<%-- END  TABLE --%>

<!-- Hidden fields starts -->
	<html:hidden styleId="detainedDate" name="admitReleaseForm" property="detainedDate"/>
	<html:hidden styleId="PIAStatus" name="admitReleaseForm" property="bookingRefPIAStatus"/>
	<html:hidden styleId="placementType" name="admitReleaseForm" property="placementType"/>
	<html:hidden styleId="reasonCode" name="admitReleaseForm" property="reasonCode"/> <%-- bug fix : 51920--%>
	
	<html:hidden styleId="admitDate" name="admitReleaseForm" property="admitDateStr"/>
	<html:hidden styleId="admitTime" name="admitReleaseForm" property="admitTime"/>
	
	<html:hidden styleId="floor" name="admitReleaseForm" property="detResp.floorNum"/>
	<html:hidden styleId="unit" name="admitReleaseForm" property="detResp.unit"/>
	<html:hidden styleId="room" name="admitReleaseForm" property="detResp.roomNum"/>
	<html:hidden styleId="mulocpunit" name="admitReleaseForm" property="detResp.multipleOccupyUnit"/>
	<html:hidden styleId="valuables" name="admitReleaseForm" property="detResp.receiptNumber"/>
	<html:hidden styleId="locker" name="admitReleaseForm" property="detResp.lockerNumber"/>
	
	<html:hidden styleId="detSecureStatus" name="admitReleaseForm" property="detResp.secureStatus"/>
	<html:hidden styleId="admittedByJPO" name="admitReleaseForm" property="detResp.admittedByJPO"/>
	<html:hidden styleId="admitReasonCd" name="admitReleaseForm" property="detResp.admitReason"/>
	<html:hidden styleId="admitReasonDesc" name="admitReleaseForm" property="detResp.admitReasonDesc"/>
	<html:hidden styleId="comments" name="admitReleaseForm" property="detResp.comments"/>
	
	<html:hidden styleId="specialAttention" name="admitReleaseForm" property="detResp.specialAttention"/>
	<html:hidden styleId="saReason" name="admitReleaseForm" property="detResp.saReason"/>
	
	<html:hidden styleId="oldEscapeAttempts" name="admitReleaseForm" property="detResp.escapeAttempts"/>
	<html:hidden styleId="oldSaReasonOtherComments" name="admitReleaseForm" property="detResp.saReasonOtherComments"/>
	
  	<input type="hidden" id="xReleaseDate" name="xReleaseDate" value='<bean:write name="admitReleaseForm" property="detResp.releaseDate" formatKey="date.format.mmddyyyy"/>'/> 
	<html:hidden styleId="xReleaseTime" name="admitReleaseForm" property="detResp.releaseTime"/>
	<html:hidden styleId="xReleaseTo" name="admitReleaseForm" property="detResp.releaseTo"/>
	<html:hidden styleId="xReleaseBy" name="admitReleaseForm" property="detResp.releaseBy"/>
	<html:hidden styleId="xReleaseAuthorizedBy" name="admitReleaseForm" property="detResp.releaseAuthorizedBy"/>
	
	<input type="hidden" id="xEscapeDate" name="xEscapeDate" value='<bean:write name="admitReleaseForm" property="headerInfo.relocationDate" formatKey="date.format.mmddyyyy"/>'/>
	<html:hidden styleId="xEscapeTime" name="admitReleaseForm" property="headerInfo.relocationTime"/>
	
	<html:hidden styleId="xEscapeAttempts" name="admitReleaseForm" property="detResp.escapeAttempts"/>   
	<html:hidden styleId="xEscapeComments" name="admitReleaseForm" property="detResp.escapeAttemptComments"/>
	<html:hidden property="headerInfo.facilityStatus" styleId="facilityStatus"/>   
<!-- Hidden fields ends -->	

<br>
 <table border="0" width="100%">
	<TBODY>
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 		
				<html:submit property="submitAction" styleId="next"><bean:message key="button.next"/></html:submit>			
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</TBODY>
</table>
</html:form>

</body>
</html:html>