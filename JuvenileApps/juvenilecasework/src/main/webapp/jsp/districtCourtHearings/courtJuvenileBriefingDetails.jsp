<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
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



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

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
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style type="text/css">
	#command {
		text-align: center;
		margin-top: 20px;
		margin-bottom: 10px;
	}
	
	#command div {
		display: inline-block;
	}
	
	#command input{
		width: 150px;
	}
</style>
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtNameSearch.js"></script>

<title><bean:message key="title.heading" />/courtJuvenileBriefingDetails.jsp</title>
<script type='text/javascript'>
$(document).ready(function () {
	$("#bannedLink").click(function(){
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="courtHearingForm" property="juvenileNumber"/>';	
		 var url = webApp + "displayJuvenileProfileTraitsSearch.do?submitAction=Detention&juvenileNum=" +juvNum;
		 var newWin = window.open(url, "pictureWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
			newWin.focus();
		return false;
	});
	  // from court briefing page.User-story no: 77458
    $("#submitReferralInquiry").on('click', function (e) {
    	$(this).prop("disabled",true);
    	spinner();
    	$('form').attr('action',"/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=REFERRAL INQUIRY");
		$('form').submit();
    });
    
    $("#back").click(function(){
    	$('form').attr('action',"/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=Link");
    	$('form').submit();
    });
   
    $("#courtActivity").click(function(e){
    	$(this).prop("disabled", true);
    	spinner();
    	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=courtActivityByYouth&fromPage=cancel');
		$('form').submit();
    });
});
</script>
</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/displayCourtBriefingDetails" target="content" styleId="courtNameSearchForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Juvenile Court Briefing</h2></td>
			</tr>
		</table>
		 <!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
		<!-- END ERROR TABLE -->
	    <!-- END Error Message Table -->
		<%-- BEGIN INSTRUCTION TABLE --%>
		
		<%-- END INSTRUCTION TABLE --%>
		
		<!-- HEADER Information  starts-->
		<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue" colspan="5" id="nameSearchTb2">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1" class="borderTableBlue" colspan="5" id="nameSearchTb2">
						<tr class="crtDetailHead">
							<td colspan="4" align="center" class='paddedFourPix' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Juvenile Information
							 <div align="right" style="display:inline;float:right">
								 JPO of Record: <span title='<bean:write name="courtHearingForm" property="jpoOfRecord"/>'><bean:write name="courtHearingForm" property="jpoOfRecID"/></span></div>
							</td>							
						</tr>
		<!-- HEADER Information  ENDS-->	
		<%-- BEGIN DETAIL TABLE --%>
		<%--general info start --%>
										<tr>
											<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.name"/></td>
											<td class='formDe'>
												<bean:write name="courtHearingForm" property="juvenileName"/>&nbsp;
													&nbsp;
												<logic:notEqual name="courtHearingForm" property="profileDetail.preferredFirstName" value="">
													<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="courtHearingForm" property="profileDetail.preferredFirstName"/></span>
												&nbsp;
												</logic:notEqual>
												<logic:equal name="courtHearingForm" property="dualStatus" value="FDS">
													<span style="color: purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="courtHearingForm" property="dualStatus" />)</span>
											 	</logic:equal>
												<logic:equal name="courtHearingForm" property="dualStatus" value="DS">
													<span style="color: purple; font-weight: bold;" title="Dual Status">(<bean:write name="courtHearingForm" property="dualStatus" />)</span>
											 	</logic:equal>
											 	<logic:equal name="courtHearingForm" property="inSpecialtyCourt" value="true">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: green; font-weight: bold; margin-left: 35px;" title="<bean:write name="courtHearingForm" property="specialtyCourtDescription"/>">SC</span>
												</logic:equal>
											 	<logic:equal name="courtHearingForm" property="rectype" value="I.JUVENILE">
													&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
												</logic:equal>
												<logic:equal name="courtHearingForm" property="rectype" value="S.JUVENILE">
													&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
												</logic:equal></td>
											 </td>
											 <td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.currentAge"/></td>
											 <td class='formDe'><bean:write name="courtHearingForm" property="juvAge"/></td>											 
										</tr>
										 <tr>
											<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile#"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="juvenileNumber"/></td>
											<td class='formDeLabel'><bean:message key="prompt.race"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="raceId"/></td>
										</tr>
										<tr>
											<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.dob"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
											<td class='formDeLabel' valign='top'><bean:message key="prompt.hispanic"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="hispanic"/></td>
											
										</tr>
										<tr>
											<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.sex"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="sexId"/></td>
											<td class='formDeLabel'><bean:message key="prompt.multiracial"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="multiracial"/></td>
										</tr>
										<tr>
											<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.sidNum"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="SID"/></td>
											<td class='formDeLabel' valign='top'><bean:message key="prompt.ethnicity"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="ethnicity"/></td>											
										</tr>
										<tr>
											<td class='formDeLabel'><bean:message key="prompt.SSN"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="ssn" /></td>
											<td class='formDeLabel'>Master Status</td>
											<td class='formDe'>
												<span title="<bean:write name="courtHearingForm" property="masterStatus" />">
													<bean:write name="courtHearingForm" property="masterStatusId" />
												</span>
											</td>
										</tr>
										<tr>											
											<td class='formDeLabel'><bean:message key="prompt.lastprobendDate"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="lastprobendDate"/></td>
											<td class='formDeLabel'>Facility : Status</td>
											<logic:notEqual name="courtHearingForm" property="profileDetail.detentionFacilityId"  value="">
												<td class='formDe'>
													<strong><span title='<bean:write name="courtHearingForm" property="profileDetail.detentionFacility"/>'><bean:write name="courtHearingForm" property="profileDetail.detentionFacilityId" /> : </span></strong>
													<span title='<bean:write name="courtHearingForm" property="profileDetail.detentionStatus"/>'><bean:write name="courtHearingForm" property="profileDetail.detentionStatusId" /></span>
												</td>
											</logic:notEqual>
											<logic:equal name="courtHearingForm" property="profileDetail.detentionFacilityId"  value="">
												<td class='formDe'></td>
											</logic:equal>																																	
										</tr>
										<tr>
											<logic:notEmpty name="courtHearingForm" property="finalReleaseDate">
											<td class='formDeLabel' width="12%" nowrap="nowrap"><bean:message key="prompt.finalreleaseDate"/></td>
											<td class='formDe'><bean:write name="courtHearingForm" property="finalReleaseDate"/></td>
											</logic:notEmpty>													
										</tr>
										
				<%-- begin School section --%>																
				<tr class='crtDetailHead'>
					<td colspan='4' align='center' class='paddedFourPix' ><bean:message key="prompt.schoolInfo"/></td>
				</tr>
				<tr>
					<td class='formDeLabel'><bean:message key="prompt.school"/></td>
					<logic:notEmpty name="courtHearingForm" property="school.specificSchoolName">
						<td class='formDe' colspan='3'><bean:write name="courtHearingForm" property="school.specificSchoolName"/></td>
					</logic:notEmpty>
					<logic:empty name="courtHearingForm" property="school.specificSchoolName">
						<td class='formDe' colspan='3'><bean:write name="courtHearingForm" property="school.school"/></td>
					</logic:empty>
				</tr>
				<tr>
					<td class='formDeLabel'><bean:message key="prompt.district"/></td>
					<td class='formDe' colspan='3'><bean:write name="courtHearingForm" property="school.schoolDistrict"/></td>
				</tr>
				<tr>
					<td class='formDeLabel'><bean:message key="prompt.gradeLevel"/></td>
					<td class='formDe' colspan='3'><bean:write name="courtHearingForm" property="school.gradeLevelDescription"/>&nbsp;
			<%-- based on the Juv's Grade Level Code, display a specific icon --%>
				<logic:notEmpty name="courtHearingForm" property="school.appropriateLevelCode">
				<logic:equal name="courtHearingForm" property="school.appropriateLevelCode" value="APP">
							<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" title='Appropriate'/>
				</logic:equal>

				<logic:equal name="courtHearingForm" property="school.appropriateLevelCode" value="BEH">
							<img src="/<msp:webapp/>images/grade_level_behind.png" alt="Behind" title='Behind'/>
				</logic:equal>

				<logic:equal name="courtHearingForm" property="school.appropriateLevelCode" value="ADV">
							<img src="/<msp:webapp/>images/grade_level_advanced.gif" alt="Advanced" title='Advanced'/>
				</logic:equal>
				</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td class='formDeLabel'><bean:message key="prompt.enrollmentDate"/></td>
				<td class='formDe' colspan='3'><bean:write name="courtHearingForm" property="school.lastAttendedDateString"/></td>
			</tr>
			<tr>
				<td class='formDeLabel'><bean:message key="prompt.attendance"/> <bean:message key="prompt.status"/></td>
				<td class='formDe' colspan='3'>
					<bean:write name="courtHearingForm" property="school.schoolAttendanceStatusDescription" />
				<span style="color:blue; font-weight: bold;  margin-left:30px;">
	  				<span title="Special Education">
	  					<logic:equal name="courtHearingForm" 
	  									property="school.educationService"
	  									value="SPECIAL EDUCATION">
	  						SE
	  					</logic:equal>
	  				</span>
	  				<span title="504 Services">
	  					<logic:equal name="courtHearingForm"
	  									property="school.educationService" 
	  									value="504 SERVICES">
	  						504
	  					</logic:equal>
	  				</span>
	  			</span>
				</td>
			</tr>
		<%-- end School section --%>																
		<%-- begin Residential section --%>																
		<tr class='crtDetailHead'>
			<td colspan='4' align='center' class='paddedFourPix' ><bean:message key="prompt.residential"/> <bean:message key="prompt.info"/></td>
		</tr>
			
		<tr>
			<td class='formDeLabel'><bean:message key="prompt.address"/></td>
			<td class='formDe' colspan='3'>
			<logic:notEmpty name="courtHearingForm" property="memberAddress">
				<div>
				<bean:write name="courtHearingForm" property="memberAddress.streetAddress"/>
				<%-- based on the Address validation, display a specific icon --%>
					<logic:notEmpty name="courtHearingForm" property="memberAddress.validated">
						<logic:equal name="courtHearingForm" property="memberAddress.validated" value="Y">&nbsp;<img src="/<msp:webapp/>images/grade_level_appropriate.png" title="Verified" />
						</logic:equal>
						<logic:equal name="courtHearingForm" property="memberAddress.validated" value="N">&nbsp;<img src="/<msp:webapp/>images/red_x.gif" title="Unverified" />
						</logic:equal>
					</logic:notEmpty>	
																						
				</div>
				<logic:notEqual name="courtHearingForm" property="memberAddress.cityStateZip" value="">
				<div>
					<bean:write name="courtHearingForm" property="memberAddress.cityStateZip"/>
				</div>	
				</logic:notEqual>
			</logic:notEmpty>
			</td>	
		</tr>
		<tr>
			<td class='formDeLabel'><bean:message key="prompt.county"/></td>
			<td class='formDe' colspan='3'>
				<logic:notEmpty name="courtHearingForm" property="memberAddress">
					<bean:write name="courtHearingForm" property="memberAddress.county"/>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td class='formDeLabel'>Phone</td>
			<td class='formDe'>
			<logic:notEmpty name="courtHearingForm" property="memberAddress">
				<logic:notEqual name="courtHearingForm" property="memberContact" value="null">
					<bean:write name="courtHearingForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber"/>
					<logic:notEmpty name="courtHearingForm" property="memberContact.contactPhoneNumber.ext">Ext <bean:write name="courtHearingForm" property="memberContact.contactPhoneNumber.ext"/></logic:notEmpty>
				</logic:notEqual>
			</logic:notEmpty>
			</td>
			<td class='formDeLabel' width='1%' nowrap='nowrap'>Priority</td>
			<td class='formDe'><logic:notEmpty name="courtHearingForm" property="memberAddress"><bean:write name="courtHearingForm" property="memberContact.primaryInd"/></logic:notEmpty></td>
		</tr>
		<tr>
			<td class='formDeLabel'><bean:message key="prompt.type"/></td>
			<td class='formDe'><logic:notEmpty name="courtHearingForm" property="memberAddress"><bean:write name="courtHearingForm" property="memberContact.contactType" /></logic:notEmpty></td>
			<td class='formDeLabel'><bean:message key="prompt.unknown"/></td>
			<logic:notEmpty name="courtHearingForm" property="memberAddress">
				<logic:equal name="courtHearingForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
					<td class='formDe'>Yes</td>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="courtHearingForm" property="memberAddress">
				<logic:notEqual name="courtHearingForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
					<td class='formDe'></td>
				</logic:notEqual>
			</logic:notEmpty>
			<logic:empty name="courtHearingForm" property="memberAddress">
				<td class='formDe'></td>
			</logic:empty>
		</tr>
		</table>
	</td>
</tr>
	<%-- end Residential section --%>																
	<%--general info end --%>
		<div class='spacer'></div>
		<%-- guardian start --%>
			
				<tr>
				<td colspan="6">
				<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue'>
					<tr class='crtDetailHead'>
						<td  colspan='7' align='center' class='paddedFourPix' >Guardian Information
						<logic:empty name="courtHearingForm" property="guardians">(No Guardian Information)</logic:empty>
						</td>
					</tr>
					<logic:notEmpty name="courtHearingForm" property="guardians">
					<tr class='formDeLabel'>
						<td></td>
						<td></td>
						<td><bean:message key="prompt.name"/></td>
						<td><bean:message key="prompt.relationship"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.oln"/></td>
						<td><bean:message key="prompt.address"/></td>
						<td><bean:message key="prompt.language"/></td>
					</tr>
			   		<logic:iterate name="courtHearingForm" property="guardians" id="juvGuardiansIter" indexId="indexer">      			
					<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
						<td>
							<logic:equal  name="juvGuardiansIter" property="inHomeStatus" value="true">
								<img title='Home' src='/<msp:webapp/>images/home-s.jpg' />
							</logic:equal>
						</td>
						<td>
							<logic:equal  name="juvGuardiansIter" property="primaryContact" value="true">
								<img title='Primary Contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
							</logic:equal>
						</td>
						<td>
							<bean:write name="juvGuardiansIter" property="nameOfPerson.formattedName"/>
						</td>
						<td><bean:write name="juvGuardiansIter" property="relationshipType"/></td>
						<td>
							<logic:notEqual name="juvGuardiansIter" property="driverLicenceNumber" value="">																		 																			  
							<bean:write name="juvGuardiansIter" property="driverLicenceNumber"/><bean:write name="juvGuardiansIter" property="driverLicenseState"/>	 
							</logic:notEqual>
							<logic:equal name="juvGuardiansIter" property="driverLicenceNumber" value="">
							<bean:write name="juvGuardiansIter" property="stateIssuedIdNum"/><bean:write name="juvGuardiansIter" property="stateIssuedIdState"/>
							</logic:equal>
					</td>
					<td>
							<div>
								<bean:write name="juvGuardiansIter" property="guardianAddress.streetAddress"/>
							<%-- based on the Address validation, display a specific icon --%>
								<logic:notEmpty name="juvGuardiansIter" property="guardianAddress.validated">
								<logic:equal name="juvGuardiansIter" property="guardianAddress.validated" value="Y">&nbsp;<img src="/<msp:webapp/>images/grade_level_appropriate.png" title="Verified" />
								</logic:equal>
								<logic:equal name="juvGuardiansIter" property="guardianAddress.validated" value="N">&nbsp;<img src="/<msp:webapp/>images/red_x.gif" title="Unverified" />
								</logic:equal>
								</logic:notEmpty>		  																	
							</div>
						<logic:notEqual name="juvGuardiansIter" property="guardianAddress.cityStateZip" value="">
							<div>
								<bean:write name="juvGuardiansIter" property="guardianAddress.cityStateZip"/>
							</div>	
						</logic:notEqual>
					
						</td>
						<td><bean:write name="juvGuardiansIter" property="language"/></td>
					</tr>
						</logic:iterate>
				</table> <%-- guardian table end --%>
					</td>
					</tr>
				</logic:notEmpty>
					<%-- guardian end --%>
					<!-- Detention Visit was added as per Regina, but Carla didn't want it. So leaving the code below commented. -->
	<%-- <div class='spacer'></div>
			Detention Visitation info start
			<tr>
				<td colspan="6">
					<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
						<tr class='crtDetailHead' align="center">
						<logic:equal name="courtHearingForm" property="detVisitBanned" value="true">
							<td nowrap align="center" bgcolor="red" class="jpoAlert"><b><bean:message key="prompt.detention"/> Visitation</b>
								<logic:empty name="courtHearingForm" property="detVisits">(No Detention Visits)</logic:empty>
							</td>
							<td nowrap align="right" bgcolor="red" class="jpoAlert"><b><a id="bannedLink" href="#">Banned</b></td>
						</logic:equal>
						<logic:notEqual name="courtHearingForm" property="detVisitBanned" value="true">
							<td class='paddedFourPix' align="center" ><bean:message key="prompt.detention"/> Visitation
							<logic:empty name="courtHearingForm" property="detVisits">(No Detention Visits)</logic:empty>
							</td>
						</logic:notEqual>
						</tr>
	     			<logic:notEmpty name="courtHearingForm" property="detVisits">
	     				<tr>
	      					<td colspan='6'>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class='formDeLabel'>
											<td >Name</td>
											<td>Relationship</td>
											<td>ID/Type</td>
										</tr>
				      					<logic:iterate name="courtHearingForm" property="detVisits" id="detVisitsIter" indexId="indexer">  
				      						<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
												<td><bean:write name="detVisitsIter" property="memOrContactName"/></td>
												<td><bean:write name="detVisitsIter" property="relationship"/></td>
												<td><bean:write name="detVisitsIter" property="idType"/></td>
											</tr>
				      					</logic:iterate>
			      					</table>
							</td>
						</tr>
					</logic:notEmpty>
					</table>	
				</td>
				</tr> --%>
				<%--button table start --%>
				<html:hidden name="courtHearingForm" property="juvenileNumber"/>
		</table>
 	</html:form>
		<div id="command">
			<div>
				<input type="button" id="back" value="Back" />
			</div>
			<div>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTREFINQ%>'>
					<input type="button" id="submitReferralInquiry" value="Referral Summary" />
				</jims2:isAllowed>
			</div>
			<div>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTVTYYOUTH%>'>
					<input type="button" id="courtActivity" value="Court Activity" />
				</jims2:isAllowed>
			</div>
		</div>
		<div align='center'>
			<script type="text/javascript">renderBackToTop()</script>
		</div>
</body>
</html:html>