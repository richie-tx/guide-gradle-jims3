<!DOCTYPE HTML>
<!--  09/18/2014  Created jsp ugopinath -->


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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/groups.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type="text/javascript">

$(document).ready(function(){	
	$("#gotoBriefing").click(function(){
		spinner();
	})
	
	$("#finish").click(function(){
		spinner();
	})
	
	$("#viewCasefileDetails").click(function(){
		spinner();
	})
	
	$("#searchFacilityAd").click(function(){
		spinner();
	})
	
	$("#generateDraft").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/submitJuvenileFacilityAdmit.do?submitAction=Generate Draft',
	        type: 'post',
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
	$("#generateFinal").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/submitJuvenileFacilityAdmit.do?submitAction=Generate Final',
	        type: 'post',
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
})
</script>
<html:base />
<title><bean:message key="title.heading"/>/facilityAdmissionRecordSummary.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	
<html:form action="/submitJuvenileFacilityAdmit" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<logic:equal name="admitReleaseForm" property="action" value="summary">
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Facility Admission Summary</td>
	</tr>
	</logic:equal>
	<logic:equal name="admitReleaseForm" property="action" value="confirm">
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Facility Admission Confirmation</td>
	</tr>
	
	<tr>
		<td class="confirm">Juvenile successfully admitted into facility. Notification sent to the JPO of any active casefile for the Juvenile.</td>
	</tr>
	</logic:equal>
	<logic:equal name="admitReleaseForm" property="action" value="failure">	
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Facility Admission  Confirmation</td>
		</tr>
		<tr>
			<td class="errorAlert">Youth admission already processed. Please see facility history.</td>
		</tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
			<td valign="top" colspan="4">
              <tiles:insert page="../caseworkCommon/juvenileFacilityDetailsTile.jsp" flush="false">
	          <tiles:put name="detailsForm" beanName="admitReleaseForm" />	
	          </tiles:insert>
	          <div class='spacer'></div>
          </td>
       </tr> 
      
       <tr>
			<td>					
				<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue' align="center">
					<tr class='facDetailHead'>
						<td class='paddedFourPix' colspan="8">Observation Status								
						</td>										
					</tr>
					<tr>
						<td class='formDeLabel' valign='top' width="5%" nowrap>Special Attention</td>
						<td class='formDe' width="30%"> 
							<logic:equal name="admitReleaseForm" property="specialAttentionCd" value="CW">Constant Watch</logic:equal>
							<logic:equal name="admitReleaseForm" property="specialAttentionCd" value="CO">Close Observation</logic:equal>
							<logic:equal name="admitReleaseForm" property="specialAttentionCd" value="NO">None</logic:equal>
						</td>										
						<logic:notEqual name="admitReleaseForm" property="specialAttentionCd" value="NO">
							<td class='formDeLabel' valign='top' width="5%" nowrap>Reason</td>
							<td class='formDe' colspan="3"><bean:write name="admitReleaseForm" property="saReasonStr"/></td>	
						</logic:notEqual>	
					</tr>	
					<logic:notEqual name="admitReleaseForm" property="specialAttentionCd" value="NO">	
					<logic:notEqual name="admitReleaseForm"  property="saReasonOtherComments" value="">
						<tr>
							<td class='formDeLabel' colspan="1" nowrap>Other Reason Comments</td>					
	      					<td colspan='10' class='formDe'>
	                 			<bean:write name="admitReleaseForm" property="saReasonOtherComments"/>
	      					</td>
	      				</tr>
	      			</logic:notEqual>
	      			</logic:notEqual>
				</table>
	      		<div class='spacer'></div>
			</td>
		</tr>
		
		 <tr>
			<td>					
				<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue' align="center">
					<tr class='facDetailHead'>
						<td class='paddedFourPix' colspan="8">Referral Information</td>										
					</tr>
					<tr>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Booking Referral</td>
						<td class='formDe' width='5%'> <bean:write name="admitReleaseForm" property="bookingReferral"/></td>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Offense/Allegation</td>
						<td class='formDe' width='20%'> <bean:write name="admitReleaseForm" property="bookingOffense"/></td>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Supervision No</td>
						<td class='formDe' width='10%'> <bean:write name="admitReleaseForm" property="bookingSupervisionNum"/></td>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Petition No</td>
						<td class='formDe'> <bean:write name="admitReleaseForm" property="bookingPetitionNum"/></td>
					</tr>
					<tr>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Current Referral</td>
						<td class='formDe' width='5%'> <bean:write name="admitReleaseForm" property="currentReferral"/></td>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Offense/Allegation</td>
						<td class='formDe'> <bean:write name="admitReleaseForm" property="currentOffense"/></td>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Supervision No</td>
						<td class='formDe' width='10%'> <bean:write name="admitReleaseForm" property="currentSupervisionNum"/></td>
						<td class='formDeLabel' valign='top' width='1%' nowrap>Petition No</td>
						<td class='formDe'> <bean:write name="admitReleaseForm" property="currentPetitionNum"/></td>
					</tr>
				</table>
				<div class='spacer'></div>
			</td>
		</tr>
		
		 <tr>
			<td>					
				<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue' align="center">
					<tr class='facDetailHead'>
						<td class='paddedFourPix' colspan="8">Facility Admission Information</td>										
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.facility" /></td>								
							<td class="formDe"><bean:write name="admitReleaseForm" property="detainedFacilityStr"/></td>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.status" /></td>	
							<logic:equal name="admitReleaseForm" property="secureStatus" value="S">						
								<td class="formDe" nowrap="nowrap" >SECURE</td>
							</logic:equal>
							<logic:notEqual name="admitReleaseForm" property="secureStatus" value="S">						
								<td class="formDe" nowrap="nowrap" >NON-SECURE</td>
							</logic:notEqual>							
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reason" /></td>								
							<td class="formDe" colspan="6"><bean:write name="admitReleaseForm" property="admitReasonStr"/></td>			
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.date" /></td>								
						<td class="formDe"><bean:write name="admitReleaseForm" property="admitDateStr"/> </td>							
						<td class="formDeLabel" width="1%" nowrap>Military Time</td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="admitTime" /></td>	
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.admittedBy" /></td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="admitBy"/></td>	
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.authorizedBy" /></td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="admitAuthority" /></td>	
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.valuables" /></td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="valuablesReceipt"/></td>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.locker" /></td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="locker"/></td>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.referringSource" /></td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="referralSource"/></td>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.referringOfficers" /></td>
						<td class="formDe"><bean:write name="admitReleaseForm" property="referralOfficers"/></td>							
					</tr>
					<tr>
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.location"/></td>
						<td  colspan='10' class='formDe' nowrap> 
							<table>
							<tr>
								<td class="formDeLabel" width="6%" nowrap id="loc1"><bean:write name="admitReleaseForm" property="locationOneLabel"/></td>
								<td class="formDe"><bean:write name="admitReleaseForm" property="floorLocation"/></td>
								<td class="formDeLabel"  width="6%" nowrap id="loc2"><bean:write name="admitReleaseForm" property="locationTwoLabel"/></td>
								<td class="formDe"><bean:write name="admitReleaseForm" property="unitLocation"/></td>
								<td class="formDeLabel" width="6%" nowrap id="loc3"><bean:write name="admitReleaseForm" property="locationThreeLabel"/></td>
								<td class="formDe" colspan="4"><bean:write name="admitReleaseForm" property="roomLocation"/>
									<logic:notEmpty name="admitReleaseForm" property="multipleOccupancyUnit">	
										<bean:write name="admitReleaseForm" property="multipleOccupancyUnit"/>
									</logic:notEmpty>					
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class='formDeLabel' colspan="1" nowrap><bean:message key="prompt.facilityAdmissionComments"/>					
      					<td colspan='10' class='formDe'>
                 			<bean:write name="admitReleaseForm" property="admissionComments"/>
      					</td>
      				</tr>
				</table>
			</td>
		</tr>
</table>
<%-- END  TABLE --%>
<div class='spacer'></div>
<br>
 <table border="0" width="100%" align="center">
		<logic:equal name="admitReleaseForm" property="action" value="summary">
			<tr>
				<td align="center">
					<html:submit property="submitAction">
						<bean:message key="button.back"></bean:message>			
					</html:submit> 		
					<html:submit styleId="finish" property="submitAction" onclick="spinner()">
						<bean:message key="button.finish"></bean:message>
					</html:submit>			
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</td>
			</tr>
		</logic:equal>
		<logic:equal name="admitReleaseForm" property="action" value="confirm">
				<tr>
					<td align="center">
						<html:submit styleId="generateDraft" property="submitAction">
							<bean:message key="button.generateDraft"></bean:message>
						</html:submit>
						<html:submit styleId="generateFinal" property="submitAction">
							<bean:message key="button.generateFinal"></bean:message>
						</html:submit>
						<html:submit styleId="viewCasefileDetails" property="submitAction">
							<bean:message key="button.casefileDetails"></bean:message>
						</html:submit>
						<input type="button" id="searchFacilityAd" onclick="goNav('/<msp:webapp/>displayJuvenileProfileSearch.do?isFacility=true');"  value="<bean:message key="button.searchFacilityAdmissions"></bean:message>"/>
						<input type="button" id="gotoBriefing" onclick="goNav('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=facilityLink&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>&supervisionNum=<bean:write name="admitReleaseForm" property="currentSupervisionNum"/>');" value="View Facility Briefing"/>
					</td>	
				</tr>		
		</logic:equal>
		<logic:equal name="admitReleaseForm" property="action" value="failure">
				<tr>
					<td align="center">
						<html:submit styleId="viewCasefileDetails" property="submitAction">
							<bean:message key="button.casefileDetails"></bean:message>
						</html:submit>
						<input type="button" id="searchFacilityAd" onclick="goNav('/<msp:webapp/>displayJuvenileProfileSearch.do?isFacility=true');"  value="<bean:message key="button.searchFacilityAdmissions"></bean:message>"/>
						<input type="button" id="gotoBriefing" onclick="goNav('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=facilityLink&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>&supervisionNum=<bean:write name="admitReleaseForm" property="currentSupervisionNum"/>');" value="View Facility Briefing"/>
					</td>	
				</tr>		
		</logic:equal>
</table>
</html:form>
</body>
</html:html>