<!DOCTYPE HTML>
<%-- User clicks the "Add School History" button from previous page --%>
<%--MODIFICATIONS --%>
<%-- 06/23/2005	Hien Rodriguez	Create JSP --%>
<%-- 04/26/2012	C Shimek		#73326 revised Grade Repeated prompts --%>
<%-- 05/7/2012	D Gibler		#73387 Added home school --%>
<%-- 11/5/2012	C Shimek		#74552 Add Specific Name field for Private, GED and OCC schools --%>
<%-- 06/03/2013	C Shimek		#75579 Added onchange to Enrollment Status and Program Attending to pre-select appropriate values in Program Attending and School Attendance Status --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>
<%@ include file="../jQuery.fw" %>




<%--BEGIN HEADER TAG--%>
<head>
<html:javascript formName="juvenileSchoolHistoryForm" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - interviewInfoSchoolEducationalHistoryCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<%@include file="../jQuery.fw"%>
<script type="text/javascript" >


function validateContactFields(theForm){

	var typeElem=document.getElementById('paId');
	var theDropDownVal=typeElem.value;
	var educationService = $("input[name='educationService']:checked").val();
	var handicappingCondition =  $("#hanConId").val();
	
	if ( ! $("input[name='educationService']:checked").length > 0 ){
	 	alert("'Was the youth or is the youth currently designated Special Education or is the youth currently receiving 504 services?' is required");
	 	return false;
	}
	if ( educationService != "NO SE OR 504 SERVICES"
			&& handicappingCondition == "" ) {
		alert("Handicapping Condition is required");
		return false;
	}
	
	
	
	return true;
	
   
 }
 
 function validateProgramAttending(theForm){
	 if(document.getElementById("paId").selectedIndex < 1){
		 alert("Program Attending is required")
		 document.getElementById("paId").focus();
		return false;		 
	 }
	
 }
 

function wipeOutSchools()
{
	var mySchoolElem=document.getElementsByName('schoolId')[0];
	mySchoolElem.options.length = 0;
	mySchoolElem.options[0] = new Option( "Please Select", "", false, false );
	specificSchName.value = ""; 
}

function wipeOutHomeSchools()
{
	var mySchoolElem=document.getElementsByName('homeSchoolId')[0];

	mySchoolElem.options.length = 0;
	mySchoolElem.options[0] = new Option( "Please Select", "", false, false );
}



function refreshPage(theForm)
{
	document.getElementsByName("schoolDistrictId")[0].selectedIndex="";
	document.getElementsByName("schoolId")[0].selectedIndex="";

	if(typeof document.getElementsByName("homeSchoolDistrictId")[0]!="undefined"){
		document.getElementsByName("homeSchoolDistrictId")[0].selectedIndex="";
		document.getElementsByName("homeSchoolId")[0].selectedIndex="";
	}
	document.getElementById("lAttendedDate").value = "";
	document.getElementById("eligibilityEnrollmentDate").value = "";
	document.getElementById("verifiedDate").value="";
	document.getElementsByName("lastAttendedDateString").value = "";
	document.getElementsByName("eligibilityEnrollmentDate").value = "";
	document.getElementsByName("verifiedDateString").value="";
	document.getElementsByName("gradeLevelId")[0].selectedIndex="";
	document.getElementsByName("appropriateGradeLevelId")[0].selectedIndex="";
	document.getElementsByName("splEduCategoryId")[0].selectedIndex=""
	document.getElementsByName("exitTypeId")[0].selectedIndex="";	//enrollment status
	document.getElementsByName("gradeAverageId")[0].selectedIndex="";
	document.getElementsByName("gradesRepeatNumber")[0].value="";
	document.getElementById("gradeRepeatTotal").value="";
	document.getElementsByName("gradesRepeatedId")[0].selectedIndex="";		
	document.getElementsByName("participationId")[0].selectedIndex="";	
	document.getElementsByName("programAttendingId")[0].selectedIndex="";
	document.getElementsByName("ruleInfractionId")[0].selectedIndex="";	
	document.getElementsByName("schoolAttendanceStatusId")[0].selectedIndex="";
	document.getElementsByName("truancyHistory")[0].value="";
	
}

function setSpecificSchoolName(el)
{
	var fld1 = document.getElementById("specificSchName");
	fld1.value = "";
	fld1.readonly = false;
	if (el.checked == true)
	{	
		fld1.value = "UNKNOWN";
		fld1.readonly = true;
	}
}



function checkSelectES(el)
{
	var elOptVal = el.options[el.selectedIndex].value;
	if (elOptVal == "N"){
		el2 = document.getElementById("sasId");
		for (x=0; x<el2.options.length; x++)
		{
			if (el2.options[x].value == "EN"){
				el2.options.selectedIndex = x;
				break;
			}
		}	
	}
	if (elOptVal == "E"){
		el2 = document.getElementById("sasId");
		for (x=0; x<el2.options.length; x++)
		{
			if (el2.options[x].value == "SE"){
				el2.options.selectedIndex = x;
				break;
			}
		}	
	}
	if (elOptVal == "G"){
		el2 = document.getElementById("sasId");
		for (x=0; x<el2.options.length; x++)
		{
			if (el2.options[x].value == "GR"){
				el2.options.selectedIndex = x;
				break;
			}
		}
		el2 = document.getElementById("paId");
		for (x=0; x<el2.options.length; x++)
		{
			if (el2.options[x].value == "GD"){
				el2.options.selectedIndex = x;
				break;
			}
		}
	}
}
function checkSelectPA(el)
{
	var elOptVal = el.options[el.selectedIndex].value;
	if (elOptVal == "HS"){
		el2 = document.getElementById("sasId");
		for (x=0; x<el2.options.length; x++)
		{
			if (el2.options[x].value == "HS"){
				el2.options.selectedIndex = x;
				break;
			}
		}
	}
	
	else if(elOptVal=="SE"){
	    resetHandicappedCon(true);
	    showHide('splEdu', 1 );
		
	}
	else{
		resetHandicappedCon(true);
		showHide('splEdu', 0 );
	}
}


 function resetHandicappedCon(isSplEd){
	if(isSplEd){
		document.getElementsByName("splEduCategoryId")[0].selectedIndex="";

	} 
 }
 
 function showHideAwardDate(val){
		document.getElementById("awardDateStrId").value = "";
		document.getElementById('awardDateId').className=val;
		if (val == 'visible'){
			document.getElementById("awardDateStrId").focus();
		}
	}
 
 function showHideCompletionDate(val){
		document.getElementById("completionDate").value = "";
		document.getElementById('completionId').className=val;
		if (val == 'visible'){
			document.getElementById("completionDate").focus();
		}
	}
 
 function defaultRadioToFalse(){
	 
	 $('input:radio[id=gedCompletedNo]').attr('checked',true);
	 $('input:radio[id=awardedNo]').attr('checked',true);
 }

</script>  

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="defaultRadioToFalse();" topmargin="0" leftmargin="0" > 
<html:form action="/processJuvenileSchool" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|178">
<input type="hidden" name="backFlowIndicator" value="infoPage" />

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create School History Information</td>	  	    	 
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter information as appropriate.</li>  		
				<li>Select School District, then select Go button to populate School Name selection.</li>
				<li>Select Next button to view Summary information.</li>
			</ul>
		</td>
	</tr>
	<tr>     	
		<td class="required"><bean:message key="prompt.diamond" />&nbsp;Required Fields&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN GREEN TABS TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvenileNum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />							
						</tiles:insert>	
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>
<%-- END GREEN TABS BORDER TABLE --%>				
<%-- BEGIN GREEN TABS BORDER TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
<%-- BEGIN BLUE TABS TABLE --%>							
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="SCHOOL" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
							</tr>
						</table>
<%-- END BLUE TABS TABLE --%>	
<%-- BEGIN BLUE TABS BORDER TABLE --%>							
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
													<tiles:put name="tabid" value="school"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
											</td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>
<%-- BEGIN RED TABS BORDER TABLE --%>									
									<table width='98%' align="center" cellpadding="0" cellspacing="0" class="borderTableRed"> 
										<tr>
											<td valign="top" align="center">
												<div class="spacer"></div>
<%-- BEGIN SCHOOL INFO TABLE --%>
												<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
												
													<tr>
														<td colspan="4" class="detailHead"><bean:message key="prompt.youthInfo" /></td>
													</tr>
													
													<tr>
														<td align='center'>
															<table cellpadding='4' cellspacing='1' width='100%'>
																<tr>
																<td class="formDeLabel" width='25%'><bean:message key="prompt.diamond" />Was the youth or is the youth currently designated Special Education or is the youth currently receiving 504 services?</td>
			    													<td class="formDe">
			    														<html:radio name="juvenileSchoolHistoryForm" property="educationService" value="SPECIAL EDUCATION">Special Education</html:radio>
			    														<html:radio name="juvenileSchoolHistoryForm" property="educationService" value="504 SERVICES">504 Services</html:radio>
			    														<html:radio styleId="defaultEducationService" name="juvenileSchoolHistoryForm" property="educationService" value="NO SE OR 504 SERVICES">No Special Education or 504 Services</html:radio>
			    													</td>			    													
			    												</tr>
			    												<tr>
			    													<td class="formDeLabel">&#x2b; Handicapping Condition</td>
																	<td>
																		<html:select name="juvenileSchoolHistoryForm" property="splEduCategoryId" styleId="hanConId">
						    												<html:option key="select.generic" value="" />
						    												<html:optionsCollection property="splEduCategory" value="code" label="description"/>				
						    											</html:select>
																	</td>
			    												</tr>
			    												<tr>
																	<!-- Changes for JIMS200077276 Starts -->
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.educationID" /></td>
																	<td class=formDe width="40%"><bean:write name="juvenileSchoolHistoryForm" property="educationId"/></td>			    												
			    												</tr>
			    												<tr>
																	<!-- Changes for JIMS200077276 Starts -->
																	<td class="formDeLabel" width="1%" nowrap>HCJPD  <bean:message key="prompt.studentID" /></td>
																	<td class=formDe width="40%"><bean:write name="juvenileSchoolHistoryForm" property="studentId"/></td>			    												
			    												</tr>
			    											</table>
			    										</td>
													</tr>
													<tr>
														<td colspan="4" class="detailHead">SCHOOL DETAILS</td>
													</tr>
													<!-- Changes for JIMS200077276 Starts -->
													<tr>
														<td align='center'>
															<table cellpadding='4' cellspacing='1' width='100%'>
																<tr>	
																	<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.district" /></td>
																	<td class="formDe" width='40%'>
																		<html:select name="juvenileSchoolHistoryForm" property="schoolDistrictId" styleId="schDist" onchange="wipeOutSchools()">
																			<html:option key="select.generic" value="" />
																			<html:optionsCollection property="schoolDistricts" value="code" label="description"/>				
																		</html:select>
																		<html:submit property="submitAction"><bean:message key="button.go" /></html:submit>
			    														&nbsp;<a href="/<msp:webapp/>processJuvenileSchool.do?submitAction=Search">Search for school</a>
			    													</td>
			    													
																</tr>	
																<tr> 
																<html:hidden name="juvenileSchoolHistoryForm" property="instructionType" styleId="instructionType"/>
																	<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.name" /></td>
																	<td class="formDe" width='40%'>
																		<html:select name="juvenileSchoolHistoryForm" property="schoolId" styleId="schoolId">
																			<html:option key="select.generic" value="" />
																			<html:optionsCollection property="schools" value="schoolCode" label="schoolDisplayLiteral"/>				
																		</html:select>
																	</td>
																</tr>
																<logic:equal name="juvenileSchoolHistoryForm" property="isAlternativeSchool" value="true">
																	<html:hidden property="homeSchoolRequired" styleId="homeSchoolRequired" value="Y" />
																	<tr>	
																		<td class="formDeLabel"><bean:message key="prompt.diamond" />Home <bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.district" /></td>
																		<td class="formDe" colspan="4">
																			<html:select name="juvenileSchoolHistoryForm" property="homeSchoolDistrictId" styleId="homeSchoolDistrictId" onchange="wipeOutHomeSchools()">
																				<html:option key="select.generic" value="" />
																				<html:optionsCollection property="schoolDistricts" value="code" label="description"/>				
																			</html:select>
																			<html:submit property="submitAction"><bean:message key="button.go" /></html:submit>
				    														&nbsp;<a href="/<msp:webapp/>processJuvenileSchool.do?submitAction=Search&homeSchoolSearchInd=H">Search for school</a>
				    													</td>
																	</tr>	
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.diamond" />Home <bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.name" /></td>
																		<td class="formDe" colspan="4">
																			<html:select name="juvenileSchoolHistoryForm" property="homeSchoolId" styleId="homeSchoolId">
																				<html:option key="select.generic" value="" />
																				<html:optionsCollection property="homeSchools" value="schoolCode" label="schoolDisplayLiteral"/>				
																			</html:select>
																			
																		</td>
																	</tr>	
																</logic:equal> 
																<logic:notEqual name="juvenileSchoolHistoryForm" property="isAlternativeSchool" value="true">
																	<html:hidden property="homeSchoolRequired" value="N" />
																</logic:notEqual>
																<tr id="specificNamefld">	
								    								<td class="formDeLabel" valign="top">
								    									<bean:message key="prompt.diamond" />Specify <bean:message key="prompt.name" />
								    									<div class="bodyText">(Address Optional)</div>
								    								</td>
								    								<td class="formDe" colspan="4">
								    									<table width="100%" cellspacing="1">
								    										<tr>
											    								<td class="formDe" colspan="3">
											    									<html:text name="juvenileSchoolHistoryForm" property="specificSchoolName" size="50" maxlength="50"  styleId="specificSchName"/>&nbsp;
											    									<html:checkbox name="juvenileSchoolHistoryForm" property="unknownNameInd" onclick="setSpecificSchoolName(this)" styleId="unknownNameInd"/><strong><bean:message key="prompt.unknown" /></strong>
								    											</td>												
								    										</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.streetNumber" /></td>
																				<td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName" /></td>
																			</tr>
																			<tr>
																				<td class="formDe"><html:text name="juvenileSchoolHistoryForm" property="streetNum"  styleId="streetNum" size="10" maxlength="15" /></td>
																				<td class="formDe" colspan="2"><html:text name="juvenileSchoolHistoryForm" property="streetName" styleId="streetName" size="50" maxlength="50" /></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.city" /></td>
																				<td class="formDeLabel"><bean:message key="prompt.state" /></td>
																				<td class="formDeLabel"><bean:message key="prompt.zipCode" /></td>
																			</tr>
																			<tr>      
																				<td class="formDe"><html:text name="juvenileSchoolHistoryForm" property="city" size="15" maxlength="25" /></td>
																				<td class="formDe">
																					<html:select name="juvenileSchoolHistoryForm" property="stateId" size="1" styleId="stateId">
																						<html:option key="select.generic" value="" />
																						<html:optionsCollection property="states" value="code" label="description"/>
																					</html:select>
																				</td>
																				<td class="formDe">
																					<html:text name="juvenileSchoolHistoryForm" property="zipCode" styleId="zipCode"  size="5" maxlength="5"/>
																					-
																					<html:text name="juvenileSchoolHistoryForm" property="zipCodeExt" styleId="zipCodeExt" size="4" maxlength="4" />
																				</td>
																			</tr>
								    									</table>
								    								</td>	
								    							</tr>
													<%-- <tr>								
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.enrollmentDate" /></td>
														<td class="formDe" colspan="4">
															<html:text name="juvenileSchoolHistoryForm" size="10" maxlength="10" property="lastAttendedDateString" styleId="lAttendedDate"/>
														</td>												
													</tr>User Story 187919: JIMS2 Rewrite: Juvenile Casework - Juvenile Profile - School Details =>Create Enrollment & Performance Details --%>
													<%--Changes for JIMS200077279 starts --%>		
													<%-- <tr>								
														<td class="formDeLabel" nowrap><bean:message key="prompt.eligibilityEnrollmentDate" /></td>
														<td class="formDe">
															<html:text name="juvenileSchoolHistoryForm" size="10" maxlength="10" property="eligibilityEnrollmentDate" styleId="eligibilityEnrollmentDate"/> 
														<!-- Changes for JIMS200077276 ends -->
													</tr> --%>
													<%--Changes for JIMS200077279 ends --%>	
													<tr>
														<td colspan="4" class="detailHead">ENROLLMENT AND PERFORMANCE DETAILS</td>
													</tr>
													
													<tr>
					    								<td class="formDeLabel"> <bean:message key="prompt.diamond"/> <bean:message key="prompt.programAttending"/> </td>
					    								<td class="formDe" colspan="4">
					    									<html:select name="juvenileSchoolHistoryForm" property="programAttendingId" styleId="paId" >
					    										<html:option key="select.generic" value="" />
					    										<html:optionsCollection property="programAttending" value="code" label="description"/>				
					    									</html:select>
					    								</td>
					    							</tr>
					    							<tr>
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.enrollmentStatus" /></td>
														<td class="formDe" colspan="4">
															<html:select name="juvenileSchoolHistoryForm" property="exitTypeId" styleId="exitTypeId" onchange="checkSelectES(this)">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="exitTypes" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
													<tr>
					    								<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.schoolAttendanceStatus" /></td>
					    								<td class="formDe"  colspan="4">
					    									<html:select name="juvenileSchoolHistoryForm" property="schoolAttendanceStatusId" styleId="sasId">
					    										<html:option key="select.generic" value="" />
					    										<html:optionsCollection property="schoolAttendanceStatus" value="code" label="description"/>				
					    									</html:select>
					    								</td>
					    							</tr>		
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.currentGradeLevel" /></td>	
														<td class="formDe" colspan="4">
															<html:select name="juvenileSchoolHistoryForm" property="gradeLevelId" styleId="gradeLevelId">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="gradeLevels" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
													<tr class="changeReason" >
														<td class="formDeLabel">Reason for Lower Grade Level Change</td>
														<td class="formDe" colspan="4">
															<html:text name="juvenileSchoolHistoryForm"
																		 property="reasonForGradeLevelChange"
																		 styleId="gradeChangeReason"
																		 size="100" maxlength="100"/>
														</td>
													</tr>
													
													<tr>
														<td class="formDeLabel" ><bean:message key="prompt.diamond" /><bean:message key="prompt.academicPerformance" />?</td>								
														<td class="formDe" valign="top" colspan="4">
															<html:select name="juvenileSchoolHistoryForm" property="academicPerformance" styleId="academicPerformance">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="academicCodes" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
										
													<tr>
														<td class="formDeLabel" ><bean:message key="prompt.diamond" /><bean:message key="prompt.appropriateLevel" />?</td>								
														<td class="formDe" valign="top" colspan="4">
															<html:select name="juvenileSchoolHistoryForm" property="appropriateGradeLevelId" styleId="appropriateGradeLevelId">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="appropriateGradeLevels" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>																														
													
													<tr id="behindSelected1" class="hidden">	
					    								<td class="formDeLabel"><bean:message key="prompt.diamond"/><bean:message key="prompt.gradeRepeatTotal" />?</td>
					    								<td class="formDe" colspan="4"><html:text name="juvenileSchoolHistoryForm" size="2" maxlength="1" property="gradeRepeatTotal" styleId="gradeRepeatTotal"/></td>												
					    							</tr>		
													<tr id="behindSelected2" class="hidden">
														<td class="formDeLabel"><bean:message key="prompt.diamond"/><bean:message key="prompt.mostRecentGradeRepeated" /></td>
														<td class="formDe" colspan="4">
															<html:select name="juvenileSchoolHistoryForm" property="gradesRepeatedId" styleId="gradesRepeatedId">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="gradesRepeated" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
					    							<tr id="behindSelected3" class="hidden">	
					    								<td class="formDeLabel"><bean:message key="prompt.diamond"/><bean:message key="prompt.timesGradeRepeated" /></td>
					    								<td class="formDe" colspan="4"><html:text name="juvenileSchoolHistoryForm" size="2" maxlength="1" property="gradesRepeatNumber" styleId="gradesRepeatNumber"/></td>												
					    							</tr>																																	
					    											    							
													<tr>	
														<td class="formDeLabel" ><bean:message key="prompt.diamond" /><bean:message key="prompt.truancyHistory" /></td>
														<td class="formDe" colspan="4">Yes<nested:radio property="truancy" value="true"/>
																	No<nested:radio property="truancy"  value="false"/></td>
													</tr>
													
													<logic:equal name="juvenileSchoolHistoryForm" property="schoolDistrictId" value="141">
														<tr>
															<td class="formDeLabel">Has the youth completed their <bean:message key="prompt.GED" />?</td>
															<td class="formDe" nowrap="nowrap">
																<bean:message key="prompt.yes" /><html:radio name="juvenileSchoolHistoryForm" property="gedCompleted" value="true" styleId="gedCompletedYes" onclick="showHideCompletionDate('visible');" />
																<bean:message key="prompt.no" /><html:radio name="juvenileSchoolHistoryForm" property="gedCompleted" value="false" styleId="gedCompletedNo" onclick="showHideCompletionDate('hidden');" />
															</td>												
														</tr>
						    							<tr id="completionId" class="hidden">
															<td class="formDeLabel"><bean:message key="prompt.completionDate" /></td>
															<td class="formDe">
																<html:text name="juvenileSchoolHistoryForm" size="10" maxlength="10" property="completionDateStr" styleId="completionDate"/>
															</td>												
														</tr>
												 		<tr>
															<td class="formDeLabel"><bean:message key="prompt.GEDAwarded" /></td>
															<td class="formDe">
																<bean:message key="prompt.yes" /><html:radio name="juvenileSchoolHistoryForm" property="awarded" value="true" styleId="awardedYes" onclick="showHideAwardDate('visible');" />
																<bean:message key="prompt.no" /><html:radio name="juvenileSchoolHistoryForm" property="awarded" value="false" styleId="awardedNo" onclick="showHideAwardDate('hidden');" />
															</td>												
														</tr>
						    							<tr id="awardDateId" class="hidden">									
															<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.GEDAwarded" /> <bean:message key="prompt.date" /></td>
															<td class="formDe">
																<html:text name="juvenileSchoolHistoryForm" size="10" maxlength="10" property="awardedDateStr" styleId="awardDateStrId"/>
															</td>
														</tr>	
													</logic:equal>
													<tr>
														<td colspan="4" class="detailHead">VERIFICATION DETAILS</td>
													</tr>
													<tr>	
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.howSchoolInformationVerified" />?</td>
														<td class="formDe">
															<html:select name="juvenileSchoolHistoryForm" property="schoolInfoVerifiedBy" size="1" styleId="verificationCodeId">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="verificationCodes" value="code" label="description"/>
															</html:select>							
														</td>
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.verifiedDate" /></td>
														<td class="formDe" width="40%"><html:text name="juvenileSchoolHistoryForm" size="10" maxlength="10" property="verifiedDateString" styleId="verifiedDate"/>																				
													</tr>
													
													<tr id="otherVerificationSelected" class="hidden">
														<td class="formDeLabel"><bean:message key="prompt.diamond" />Please Specify the reason for Other</td>
														<td class="formDe" colspan="4">
															<html:text name="juvenileSchoolHistoryForm"
																		 property="schoolVerfifyOther"
																		 styleId="verfifyOther"
																		 size="30" maxlength="30"/>
														</td>
													</tr>																																																																																											
								                </table>
<%-- END SCHOOL INFO TABLE --%>		
												<div class="spacer"></div>			
<%-- BEGIN BUTTON TABLE --%>
												<table width="98%">
													<tr>
														<td align="center">
															<html:submit property="submitAction"><bean:message key="button.back" /></html:submit> 
															<html:submit property="submitAction" onclick="return (validateJuvenileSchoolHistoryForm(this.form))&& 
																	validateContactFields(this.form)&& validateProgramAttending(this.form)" styleId="nextButton">
																<bean:message key="button.next"></bean:message>
															</html:submit> 
															<input type="button" name="refresh" value="<bean:message key='button.refresh'/>" onclick="refreshPage(this.form)" />
															<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
														</td>
													</tr>
												</table>
													<div class="spacer"></div>
														</td>
													</tr>
												</table>
												<!-- Changes for JIMS200077276 Ends -->
												<div class="spacer"></div>
											</td>
										</tr>
									</table>	
<%-- END RED TABS BORDER TABLE --%>									
									<div class="spacer"></div>
								</td>
							</tr>
						</table>
<%-- BEGIN BLUE TABS BORDER TABLE --%>								
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
<%-- END GREEN TABS BORDER TABLE --%>				
			<div class="spacer"></div>	
		 </td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>