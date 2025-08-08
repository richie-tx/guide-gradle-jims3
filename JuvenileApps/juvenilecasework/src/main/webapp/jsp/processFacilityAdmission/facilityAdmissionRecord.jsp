<!DOCTYPE HTML>
<!--  07/24/2014  Created jsp ugopinath -->


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

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/facilityAdmit.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/facilityAdmissionRecord.jsp</title>
<html:javascript formName="admitReleaseForm"/>

<%--HELP JAVASCRIPT FILE --%> 


<script type="text/javascript"> 

/**
 * checkValidFacilityReason
 */
function checkValidFacilityReason(el)
{
	//**********************************
	//check if reason is Diversion
	//if yes, check Facility TTJJDPlcement Type=D
	//and Secure Status=N
	var codeWithDetType = el.value.split('|');	
	if(codeWithDetType[0]=="DIV" || codeWithDetType[0]=="DIP")
	{
		//alert("DIV - add code to check stuff");
		
		var tjpc = document.getElementById('assocTypeSel').value.split('|');			
		if(tjpc[1]!='D' || document.getElementById("nsec").checked==false)
		{
			alert("Admit Reason of 'Diversion' can only be selected for Facility with TJJDPlacementType='D' and secure status 'N'.");
			//document.getElementById('assocTypeSel').focus();
			return false;
		}
	}	
	//check the detention type for the admit reason
	if(codeWithDetType[1]=="P")
	{
		//check PIA status of booking referral
		<logic:notEqual name="admitReleaseForm" property="bookingRefPIAStatus" value="P">
			alert("Admit Reason invalid. Admit Reason has Detention Type P, Booking Referral PIA Status is not P.");
			return false;
		</logic:notEqual>
		
	}
	return true;
}
</script>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>

	
<html:form action="/displayJuvenileFacilityAdmitSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Facility Admission Record</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
	
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

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
		<td class='formDe' width="29%" colspan='1' nowrap> 
			<html:radio name="admitReleaseForm" property="specialAttentionCd" value="CO" styleId="sa1"/>Close Observation
			<html:radio name="admitReleaseForm" property="specialAttentionCd" value="CW" styleId="sa2"/>Constant Watch
			<html:radio name="admitReleaseForm" property="specialAttentionCd" value="NO" styleId="sa3"/>None
		</td>
		<td class='formDe' width="79%" colspan='1'>
			<table id="saReasonId" class="hidden">
				<tr>
					<td class='formDeLabel' valign='top' width="1%" colspan='1' nowrap><bean:message key="prompt.reason"/></td>
					<td class='formDe' width="79%" colspan='1' nowrap> 
					 <logic:iterate id="saReasonIndex" name="admitReleaseForm" property="specialAttentionReasons" indexId="idx">										 	
											
						                          <input type="checkbox" id="<bean:write name='saReasonIndex' property='code'/>" name="selectedSAReasons" value="<bean:write name='saReasonIndex' property='description'/>" ><bean:write name="saReasonIndex" property="description"/> 
										 	
										 </logic:iterate>
					</td>
				</tr>
			</table>
			<html:hidden property="saReasonStr" styleId="defaultSAReason"/>
		</td>
	</tr>
	<tr id="saReasonOther1" class="hidden" nowrap>
		<td class='formDeLabel' colspan="8"><bean:message key="prompt.2.diamond"/>
		<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilitySplAttn.do?submitAction=Link&detentionId=<bean:write name="admitReleaseForm" property="detentionId"/>')">Other Reason Comments</a>
			<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
				<tiles:put name="tTextField" value="saReasonOtherComments"/>
                <tiles:put name="tSpellCount" value="spellBtn1" />
            </tiles:insert>
            (Max. characters allowed:50)
        </td>					
	</tr>
	<tr id="saReasonOther2" class="hidden">
    	<td colspan='4' class='formDeLabel'>
	    	<html:text name="admitReleaseForm" property="saReasonOtherComments" styleId ="saR" maxlength="50" size="70"/>
      	</td>
    </tr>
</table>
<%-- Observation status ends --%>
<div class='spacer'></div>
<%-- Referral section begins --%>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">      
	 <tr>
		<td>					
			<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue'>
				<tr class='facDetailHead'>
					<td align='left' colspan="8">Referral Information							
					</td>										
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
</table>
	<div class='spacer'></div>
<%-- Referral section ends --%>

<table width="99%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue' align="center">      
	 <tr>
		<td>							
			<table width="100%" cellpadding="2" cellspacing="1" >
				
		</table>
		<table width="100%" cellpadding="2" cellspacing="1" >
			<tr class='facDetailHead'>
					<td align='left' colspan="8">Facility Admission Information							
					</td>										
				</tr>
				<tr>					
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.facility" /></td>								
					<td class="formDe" width="40%" >	
						
							<html:select name="admitReleaseForm" property="detainedFacility" styleId="assocTypeSel">								
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="facilities" value="codeWithJuvTJPCPlacementType" label="descriptionWithCode" />				
							</html:select>	
						<html:hidden property="detainedFacility" styleId="detainedFacility"/>
							<logic:iterate name="admitReleaseForm" property="facilities" id="facsIter" indexId="facsIndexer">  
								<html:hidden name="facsIter" property="code" indexed="true"/>
								<html:hidden name="facsIter" property="locationOneLabel" indexed="true"/>
								<html:hidden name="facsIter" property="locationTwoLabel" indexed="true"/>
								<html:hidden name="facsIter" property="locationThreeLabel" indexed="true"/>
							</logic:iterate>				
					</td>
					<td  class="formDe" width="10%">
						<table id="secureStatId1" class="hidden"><tr><td valign='top' class="formDeLabel"  nowrap>
							<bean:message key="prompt.2.diamond"/><bean:message key="prompt.status" /></td></tr>
						</table>						
					</td>
					<td class="formDe" width="20%" nowrap>	
						<table id="secureStatId2" class="hidden"><tr><td>		
							 <html:radio name="admitReleaseForm" property="secureStatus" value="S" styleId="sec"/>Secure
								<html:radio name="admitReleaseForm" property="secureStatus" value="N" styleId="nsec"/>Non-Secure</td></tr>
						</table>
							
					</td>
					<td class="formDe" colspan="4"></td>
			</tr>
			<tr id="admitReasonId1" class="hidden">
					<td class="formDeLabel">						
								<bean:message key="prompt.2.diamond"/><bean:message key="prompt.reason" /></b>
						</td>								
					<td class="formDe" colspan="6" >
					
							<html:select property="reasonCode" styleId="admitReason">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="admitReasons" value="codeWithDetType" label="descriptionWithCode"/>			
							</html:select>
					</td>
			</tr>						
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date" /></td>								
				<td class="formDe" >
					<html:text property="admitDateStr" styleId="admitDate" size="10" maxlength="10" /> 							
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/>Military Time</td>
				<td class="formDe"  width="7%"><html:text property="admitTime" size="4" maxlength="5" styleId="admitTime"/></td>	
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.admittedBy" /></td>
				<td class="formDe" width="13%"><html:text property="admitBy" size="5" maxlength="5" styleId="admitBy"/></td>
				<td class="formDe" colspan="4"></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.valuables" /></td>
				<td class="formDe"><html:text property="valuablesReceipt" size="10" maxlength="10" styleId="valsId"/></td>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.locker" /></td>
				<td class="formDe"><html:text property="locker" size="6" maxlength="6"/></td>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.referringSource" /></td>
				<td class="formDe"><html:text property="referralSource" size="2" maxlength="2" styleId="refSrcId" disabled="true"/></td>
				<td class="formDe" colspan="4"></td>										
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap" ><bean:message key="prompt.2.diamond"/><bean:message key="prompt.authorizedBy" /></td>
				<td class="formDe" ><html:text property="admitAuthority" size="5" maxlength="5" styleId="authBy"/></td>	
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.referringOfficers" /></td>
				<td class="formDe"><html:text property="referralOfficers" size="19" maxlength="19" styleId="refOffId"/></td>	
				<td class="formDe" colspan="6"></td>	
			</tr>
			<tr>
				<td width="100%" colspan="8" class="formDe">
						<table width="100%" id="locId1" class="hidden">
							<tr><td class='formDeLabel' colspan="8">Location</td>							
							</tr>	
							<tr>
								<td class="formDeLabel" width="2%" nowrap="nowrap" styleId="loc1"><span class="loc1Span"><bean:write name="admitReleaseForm" property="locationOneLabel"/></span></td>
								<td class="formDe" width="10%"><html:text property="floorLocation" size="1" maxlength="1"/></td>
								<td class="formDeLabel"  width="2%" nowrap="nowrap" styleId="loc2"><span class="loc2Span"><bean:write name="admitReleaseForm" property="locationTwoLabel"/></span></td>
								<td class="formDe" width="10%"><html:text property="unitLocation" size="1" maxlength="1"/></td>
								<td class="formDeLabel" width="2%" nowrap="nowrap" styleId="loc3"><span class="loc3Span"><bean:write name="admitReleaseForm" property="locationThreeLabel"/></span></td>
								<td class="formDe" width="10%"><html:text property="roomLocation" size="3" maxlength="3" styleId="roomLoc"/></td>
								<td class="formDe">
									<table class="hidden" id="locId12">
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.mou"/></td>
											<td>
												<html:select property="multipleOccupancyUnit" name="admitReleaseForm" styleId="mou">
																				   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																				  <jims2:codetable codeTableName='MULTIPLE_OCCUPANCY_UNIT'></jims2:codetable>
											</td>									</html:select>  
										</tr>	
									</table>
								</td>								
							</tr>
						</table>
					</td>
				</tr>	
				
				<tr>
					<td class='formDeLabel' colspan="8" nowrap><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityAdmissionComments.do?submitAction=Link&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>','comments',1000,800)"><bean:message key="prompt.facilityAdmissionComments"/></a>
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
		                   						<tiles:put name="tTextField" value="admissionComments"/>
		                   						<tiles:put name="tSpellCount" value="spellBtn2" />
		                 						</tiles:insert>
		                 						(Max. characters allowed:350)</td>					
				</tr>
				<tr>
   					<td colspan='8' class='formDeLabel'>
              			<html:textarea name="admitReleaseForm" property="admissionComments" rows="2" style="width:100%" styleId="admissionCommentsId"></html:textarea>
   					</td>
   			</tr>
				
		</table>
	</td>
</tr>		
</table>
		

<%-- END  TABLE --%>

<!-- Hidden fields starts -->		
		<html:hidden styleId="PIAStatus" name="admitReleaseForm" property="bookingRefPIAStatus"/>		
		<html:hidden styleId="detainedFacilityId" name="admitReleaseForm" property="detainedFacility"/>
		<html:hidden styleId="placementTypeId" name="admitReleaseForm" property="placementType"/>
		<html:hidden styleId="admitReasonStrId" name="admitReleaseForm" property="admitReasonStr"/>
		<html:hidden styleId="facilityStatusId" name="admitReleaseForm" property="facilityStatus"/>
		<html:hidden styleId="secureStatusId" name="admitReleaseForm" property="secureStatus"/>
		<html:hidden styleId="prevReleaseDate" name="admitReleaseForm" property="detResp.releaseDateStr"/>
		<html:hidden styleId="prevReleaseTime" name="admitReleaseForm" property="detResp.releaseTime"/>
		
		<html:hidden name="admitReleaseForm" property="bookingSupervisionNum" styleId="bookingSupervisionNum" />
		<html:hidden name="admitReleaseForm" property="currentSupervisionNum" styleId="currentSupervisionNum" />
<br>
 <table border="0" width="100%">
	<TBODY>
		<tr>
			<td align="center">
		
			<html:submit property="submitAction">
				<bean:message key="button.back"></bean:message>			
			</html:submit> 		
			<html:submit property="submitAction" styleId="next">
				<bean:message key="button.next"></bean:message>
			</html:submit>			
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
			</td>
		</tr>
	</TBODY>
</table>
</html:form>

</body>
</html:html>