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
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<title><bean:message key="title.heading"/>/facilityTemporaryReleaseDecision.jsp</title>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileFacilityRelease" target="content">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>

	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility - Temporary Release Decision</td>
		</tr>
	</table>

<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select a Temporary Release Decision</li>
				<li>Enter Comments</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>



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
<%-- <div class='spacer'></div>

<!-- Observation Status -->
<tiles:insert page="../caseworkCommon/juvenileFacilityObservationStatusTile.jsp" flush="false">
   	 <tiles:put name="admitReleaseForm" beanName="admitReleaseForm" />	
</tiles:insert> --%>
<!-- Observation Status -->

<div class='spacer'></div>

<%--Admission Information starts --%>
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
	<div class='spacer'></div>
	<div class='spacer'></div>
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='10' class='paddedFourPix'>Admission Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facility"/></td>
				<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="detainedFacilityStr"/>'><bean:write name="admitReleaseForm" property="detainedFacility"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitReason"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="admitReasonStr"/>'><bean:write name="admitReleaseForm" property="admitReasonCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Secure Status</td>
				<logic:equal  name="admitReleaseForm" property="secureStatus" value="S">
		    		<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='SECURE'><bean:write name="admitReleaseForm" property="secureStatus"/></span></td>
		    	</logic:equal>
		    	<logic:equal  name="admitReleaseForm" property="secureStatus" value="N">
		    		<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='NON-SECURE'><bean:write name="admitReleaseForm" property="secureStatus"/></span></td>
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
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitBy"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="petition"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingReferral"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="bookingReferral"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingOffense"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="bookingOffense"/>'><bean:write name="admitReleaseForm" property="bookingOffenseCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingSupervision"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="bookingSupervisionNum"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralSource"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralSource"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentReferral"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="currentReferral"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentOffense"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="currentOffense"/>'><bean:write name="admitReleaseForm" property="currentOffenseCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentSupervision"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="currentSupervisionNum"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralOfficer"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralOfficers"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.location"/></td>
				<td  colspan='10' class='formDe' nowrap> 
					<table>
						<tr>
							<td><bean:write name="admitReleaseForm" property="locationOneLabel"/><bean:write name="admitReleaseForm" property="floorLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="locationTwoLabel"/><bean:write name="admitReleaseForm" property="unitLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="locationThreeLabel"/><bean:write name="admitReleaseForm" property="roomLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="multipleOccupancyUnit"/></td>
						</tr>
					</table> 
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.reasonForChange"/></td>
				<td  colspan='9' class='formDe' nowrap><bean:write name="admitReleaseForm" property="reasonForChange"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeAttempts"/></td>
				<td class='formDe' width="15%" colspan='9' nowrap><bean:write name="admitReleaseForm" property="numOfEscapeAttempts"/></td>
			</tr>
			<logic:notEmpty name="admitReleaseForm" property="escapeComments">
			<tr id="escapeComments1">
				<td class='formDeLabel' colspan="10">Escape Attempts Comments
				         (Max. characters allowed:350)
			    </td>					
			</tr>
			<tr id="escapeComments2">
			   	<td colspan='10' class='formDe'><div style="width: 1100px; word-wrap: break-word"><bean:write name="admitReleaseForm" property="escapeComments"/></div></td>
			</tr>
			</logic:notEmpty>
			<%-- <tr id="admissionComments1">
				<td class='formDeLabel' colspan="8" nowrap><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityAdmissionComments.do?submitAction=Link&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>','comments',1000,800)"><bean:message key="prompt.facilityAdmissionComments"/></a>
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
						<tiles:put name="tTextField" value="admissionComments"/>
		                <tiles:put name="tSpellCount" value="spellBtn3" />
		            </tiles:insert>
		            (Max. characters allowed:350)
		        </td>					
			</tr>
			<tr id="admissionComments2">
		    	<td colspan='8' class='formDeLabel'>
			    	<html:textarea name="admitReleaseForm" property="admissionComments" styleId = "admissionComments"  rows="4"  style="width:100%"/>
		      	</td>
		    </tr> --%>
		</table>
</logic:notEqual>
<%--Admission Information ends --%>



<div class='spacer'></div>
<div class='spacer'></div>

<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.temporaryReleaseAllowed"/></td>
		
		<td class='formDe' colspan='4' nowrap>
			
				<input type="radio" name="temporaryReleaseAllowed"  styleId="acceptedDecision" value="YES">Yes&nbsp;&nbsp;&nbsp;&nbsp;      
				
				<input type="radio" name="temporaryReleaseAllowed"  styleId="rejectedDecision" value="NO">No 
		</td>
		
						
	</tr>

	<tr>
		<td class='formDeLabel' colspan='5'>Comments 
			<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
				<tiles:put name="tTextField" value="tempReleaseDecisionComments"/>
	               <tiles:put name="tSpellCount" value="spellBtn2" />
	           </tiles:insert>
	           (Max. characters allowed:100)
	       </td>					
	</tr>
				
	<tr>
    	<td colspan='5' class='formDeLabel'>
	    	<html:textarea name="admitReleaseForm" property="tempReleaseDecisionComments" styleId = "tempReleaseDecisionComments" rows="4" style="width:100%"  />
      	</td>
    </tr>
	
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.authorizingOfficer"/></td>
		<td class='formDe' colspan='4' nowrap><bean:write name="admitReleaseForm" property="authorizingOfficerName"/></td>
		<%--  <td class='formDe' colspan='8' nowrap><input type="text" class='formDe' name="authorizingOfficerName" size="45" readonly value="<bean:write name="admitReleaseForm" property="authorizingOfficerName"/>" /></td> --%>
		
	</tr>		    
			    
	
</table>





 <table border="0" width="100%">
	<TBODY>
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 		
				<html:submit property="submitAction" styleId="nextTmpDecision"><bean:message key="button.next"/></html:submit>			
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</TBODY>
</table>
</html:form>

</body>
</html:html>