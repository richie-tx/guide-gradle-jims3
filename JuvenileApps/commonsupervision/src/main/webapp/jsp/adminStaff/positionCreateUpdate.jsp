<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/08/2008   C Shimek    defect#54337 added logic tags to only display CJAD field when officer assigned to position -->
<!-- 11/12/2008   D Gibler    defect#55411 Modified js to allow cjad to be fewer than 9 characters -->
<!-- 12/18/2008   C Shimek    defect#53459 added clearErrorMessage() to submit button  -->
<!-- 01/29/2009   C Shimek    defect#56837 added return to clearErrorMessage() and made it second script on submit button to activate validation  -->
<!-- 03/06/2009   C Shimek    moved clearAllValArrays to beginning of performValidation() so all CustomStrutsBasedJS entries would be called -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features"%>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG--><head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - adminStaff/positionCreateUpdate.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/organization.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>

<script>
function clearErrorMessage()
{
	document.getElementById( "errors" ).removeNode(true);
	return true;
}

function presetCaseload(){
	var hasCaseLoadElem=document.getElementsByName('position.hasCaseload')[0];
		var hasCaseLoadVal="true";
	<logic:equal name="adminStaffForm" property="action" value="create">
		if(hasCaseLoadElem.checked){
			if(hasCaseLoadElem.value=="true"){
				hasCaseLoadVal="true";
			}
			else{
				hasCaseLoadVal="false";
			}
		}
		else{
			hasCaseLoadElem=document.getElementsByName('position.hasCaseload')[1];
			if(hasCaseLoadElem.checked){
				if(hasCaseLoadElem.value=="true"){
					hasCaseLoadVal="true";
				}
				else{
					hasCaseLoadVal="false";
				}
			}
		}
	</logic:equal>
	<logic:equal name="adminStaffForm" property="action" value="update">
	    hasCaseLoadVal=hasCaseLoadElem.value;
	</logic:equal>
	if(hasCaseLoadVal=='true'){
	show("POIRow", 1, "row")
			show("CSTSRow",1,"row");
			show("posNameRequired", 0)
	}
	else{
		show("posNameRequired", 1, "inline")
				show("POIRow", 0)
				show("CSTSRow",0,"row");
	}
}

function renderPOI(theRadio){
	
	if (theRadio.checked){
		if (theRadio.value == "true" ){
			show("POIRow", 1, "row")
			show("CSTSRow",1,"row");
			show("posNameRequired", 0)
			}else {
				show("posNameRequired", 1, "inline")
				show("POIRow", 0)
				show("CSTSRow",0,"row");
				}
			}
		}
		
function hidePOI(toHideVal){
	if(toHideVal){
		show("POIRow", 1, "row")
		show("CSTSRow",1,"row");
		show("posNameRequired", 0)
	}else {
		show("posNameRequired", 1, "inline")
		show("POIRow", 0)
		show("CSTSRow",0,"row");
	}
}

<logic:iterate indexId="organizationIterIndex" id="organizationIter" name="adminStaffForm" property="position.organizations">
organizations[<bean:write name="organizationIterIndex"/>] = new suborganization("<bean:write name="organizationIter" property="organizationId" />", "<bean:write name="organizationIter" property="description" />");
<logic:notEmpty  name="organizationIter" property="children">	
<logic:iterate indexId="organizationIterIndex2" id="organizationIter2" name="organizationIter" property="children">
	var innerOrganization = new suborganization("<bean:write name="organizationIter2" property="organizationId" />", "<bean:write name="organizationIter2" property="description" />");
	organizations[<bean:write name="organizationIterIndex"/>].suborganizations[<bean:write name="organizationIterIndex2"/>] = innerOrganization;
	<logic:notEmpty name="organizationIter2" property="children">
	<logic:iterate indexId="organizationIterIndex3" id="organizationIter3" name="organizationIter2" property="children">
		var innerOrganization = new suborganization("<bean:write name="organizationIter3" property="organizationId" />", "<bean:write name="organizationIter3" property="description" />");
		organizations[<bean:write name="organizationIterIndex"/>].suborganizations[<bean:write name="organizationIterIndex2"/>].suborganizations[<bean:write name="organizationIterIndex3"/>] = innerOrganization;
	</logic:iterate>
	</logic:notEmpty>
</logic:iterate>
</logic:notEmpty>
</logic:iterate>


function checkForCLO(){
	var jobElem=document.getElementsByName('position.jobTitleId')[0];
	var jobVal=jobElem.options[jobElem.selectedIndex].value;
	if(jobVal == "<%=PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER%>"  || jobVal == "<%=PDCodeTableConstants.STAFF_JOB_TITLE_CLO%>"){
		
		show('courtsRow',1,"row");
		if(jobVal == "<%=PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER%>" ){
			show('courtsRowDiamond',0,"inline");
		}
		else{
			show('courtsRowDiamond',1,"inline");
		}
		
	}
	else{
	
		show('courtsRow',0,"row");
	}
	
	setJobTitleDesc();
}

function checkPositionType(){
	var posTypeElem=document.getElementsByName('position.positionTypeId')[0];
	var posTypeVal=posTypeElem.options[posTypeElem.selectedIndex].value;
	if(posTypeVal=="<%=PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR%>"){
		var programUnitElem=document.getElementsByName('position.programUnitId')[0];
			programUnitElem.selectedIndex=0;
			updateOrganization2(document.forms[0], 'position.divisionId','position.programUnitId','position.programSectionId')
			show('programUnitReq',0,"inline");
		}
		else{
			show('programUnitReq',1,"inline");
		}
	
	setPositionTypeDesc();
}


function performValidation(myPageForm){
	var poiValueGlobal="";
	var jobElem=document.getElementsByName('position.jobTitleId')[0];
	var jobVal=jobElem.options[jobElem.selectedIndex].value;
	var hasCaseLoadVal="true";
	clearAllValArrays();
	<logic:equal name="adminStaffForm" property="action" value="create">
		var hasCaseLoadElem=document.getElementsByName('position.hasCaseload')[0];
		var posTypeElem=document.getElementsByName('position.positionTypeId')[0];	
		var posTypeVal=posTypeElem.options[posTypeElem.selectedIndex].value;
		var divisionElem=document.getElementsByName('position.divisionId')[0];
		var divisionVal=divisionElem.options[divisionElem.selectedIndex].value;
	
		if(hasCaseLoadElem.checked){
			if(hasCaseLoadElem.value=="true"){
				hasCaseLoadVal="true";
			}
			else{
				hasCaseLoadVal="false";
			}
		}
		else{
			hasCaseLoadElem=document.getElementsByName('position.hasCaseload')[1];
			if(hasCaseLoadElem.checked){
				if(hasCaseLoadElem.value=="true"){
					hasCaseLoadVal="true";
				}
				else{
					hasCaseLoadVal="false";
				}
			}
		}

		customValRequired("position.positionTypeId","Positition Type is required.","");
		customValRequired("position.jobTitleId","Job Title is required.","");
		if(jobVal == "<%=PDCodeTableConstants.STAFF_JOB_TITLE_CLO%>"){
			if(validateSelectedCourts(myPageForm)){
//				alert("AAAAA");
			}
			else{
				return false;
			}
		}
		else{
//			alert("BBBBBB");
		}
		customValRequired("position.divisionId","Division is required.","");
		if(posTypeVal=="<%=PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR%>"){
//			alert("CCCCC");
		}
		else{
			if(divisionVal!=null && divisionVal!=""){
				customValRequired("position.programUnitId","Program Unit is required.","");
			}
		}
	</logic:equal>  // end of create
	
	<logic:equal name="adminStaffForm" property="action" value="update">
		customValRequired("position.positionTypeId","Positition Type is required.","");
		customValRequired("position.jobTitleId","Job Title is required.","");
		customValRequired("position.programUnitId","Program Unit is required.","");
		var hasCaseLoadTxt=document.getElementById('hasCaseLoadTxt');
		hasCaseLoadVal = hasCaseLoadTxt.value;
	</logic:equal>
	
	customValRequired("position.hasCaseload","Has Caseload is required.","");
	if(hasCaseLoadVal=="true"){
		customValRequired("position.officerTypeId","CSTS Officer Type is required.","");
//		var posPOI=document.getElementsByName('position.probOfficerInd')[0];
		var posPOI=document.getElementsByName('position.probOfficerInd');
		var posPOIVal=posPOI[0].value;
		if(posPOIVal.length ==2){
			// Convert _ to space
			var s=posPOIVal;
			if(s.substring( 0,1 ) == '_'){
				s=' ' + s.substring(1,2);
			}
			if(s.substring( 1,2 ) == '_'){
				s= s.substring(0,1) + ' ';
			}
			posPOI.value=s;
			poiValueGlobal=s;
			if(Trim(s)==''){
				posPOI.value=='';
				alert("Probation Officer Indicator is required");
				posPOI[0].focus();
				return false;
			}
			customValMask("position.probOfficerInd","Probation Officer Indicator can contain only alpha numeric and the following symbols @ = * $ % ? # + & ! : ","/^[a-zA-Z0-9 @=\*$%\?#\+&!:]*$/");         
		} 
		else{
			alert("Probation Officer Indicator must be 2 characters in length");
			posPOI[0].focus();
			return false;
		
		}
		
//		customValMaxLength("position.probOfficerInd","Probation Officer Indicator cannot be greater than 2 charactes","2");
//		customValMinLength("position.probOfficerInd","Probation Officer Indicator cannot be less than 2 charactes","2");
	}
	else{

		customValRequired("position.positionName","Position Name is required.","");
	}
	
	addNumericValidation("position.phone.areaCode","Area code must be numeric.");
	customValMaxLength("position.phone.areaCode","Area code must be 3 digits","3");
	customValMinLength("position.phone.areaCode"," Area code must be 3 digits ","3");
	addNumericValidation("position.phone.prefix","Prefix must be numeric.");
	customValMaxLength("position.phone.prefix","Prefix must be 3 digits","3");
	customValMinLength("position.phone.prefix"," Prefix must be 3 digits ","3");
	addNumericValidation("position.phone.last4Digit","Last 4 Digits  must be numeric.");
	customValMaxLength("position.phone.last4Digit","Last 4 Digits must be 4 digits","4");
	customValMinLength("position.phone.last4Digit"," Last 4 Digits must be 4 digits ","4");
	addDB2FreeTextValidation("position.locationDetails","Location Details  must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / along with spaces are allowed");
	addDB2FreeTextValidation("position.positionName","Position Name  must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / along with spaces are allowed.");
	<logic:equal name="adminStaffForm" property="action" value="update">
		<logic:notEqual name="adminStaffForm" property="position.vacant" value="true">
		    addAlphaNumericValidation("position.user.cjad","CJAD must be alphanumeric.");
		    customValMaxLength("position.user.cjad","CJAD must be 9 digits","9");
		</logic:notEqual>
	</logic:equal>
	addMMDDYYYYDateValidation("position.effectiveDateAsStr","Effective Date is invalid.  Valid format is mm/dd/yyyy.");
	customValRequired("position.effectiveDateAsStr","Effective Date is required.","");
	if(validateCustomStrutsBasedJS(myPageForm)){
		if(hasCaseLoadVal=="true"){
			var posPOIElem=document.getElementsByName('position.probOfficerInd')[0];
			posPOIElem.value=poiValueGlobal;
			return true;
		}
	}
	else
		return false;
}

//Set Position Type description
function setPositionTypeDesc()
{
		//retrieve selected value of Position Type
	var position_type_obj = document.getElementById("positionType");
	var position_type_desc = position_type_obj.options[position_type_obj.selectedIndex].text;
	
		//set Position Type property
	var position_type_desc_obj = document.getElementById("positionTypeDesc");
	position_type_desc_obj.value = position_type_desc;
	
}//end of setPositionTypeDesc()

//Set CSTS Officer Type description
function setOfficerTypeDesc()
{
		//retrieve selected value of CSTS Officer Type
	var officer_type_obj = document.getElementById("officerType");
	var officer_type_desc = officer_type_obj.options[officer_type_obj.selectedIndex].text;
	
		//set CSTS Officer Type property
	var officer_desc_obj = document.getElementById("officerTypeDesc");
	officer_desc_obj.value = officer_type_desc;
	
}//end of setOfficerTypeDesc()

//Set job title description
function setJobTitleDesc()
{
		//retrieve selected value of job title
	var job_title_obj = document.getElementById("jobTitle");
	var job_title_desc = job_title_obj.options[job_title_obj.selectedIndex].text;
	
		//set job title property
	var job_title_desc_obj = document.getElementById("jobTitleDesc");
	job_title_desc_obj.value = job_title_desc;
	
}//end of setJobTitleDesc()

//Set Program Unit description
function setProgramUnitDesc()
{
		//retrieve selected value of Program Unit
	var program_unit_obj = document.getElementById("pu");
	var program_unit_desc = program_unit_obj.options[program_unit_obj.selectedIndex].text;
	
		//set Program Unit property
	var program_unit_desc_obj = document.getElementById("programUnitDesc");
	program_unit_desc_obj.value = program_unit_desc;
	
}//end of setProgramUnitDesc()

//Change program unit actions
function changeProgramUnit()
{
	updateOrganization3(document.forms[0], 'position.divisionId','position.programUnitId','position.programSectionId');
	setProgramUnitDesc();
}//end of changeProgramUnit()

//Set Program Section description
function setProgramSectionDesc()
{
		//retrieve selected value of program section
	var program_section_obj = document.getElementById("programSection");
	var program_section_desc = program_section_obj.options[program_section_obj.selectedIndex].text;
	
		//set program section property
	var program_section_desc_obj = document.getElementById("programSectionDesc");
	program_section_desc_obj.value = program_section_desc;
	
}//end of setProgramSectionDesc()

//Set Location description
function setLocationDesc()
{
		//retrieve selected value of location
	var location_obj = document.getElementById("location");
	var location_desc = location_obj.options[location_obj.selectedIndex].text;
	
		//set location property
	var location_desc_obj = document.getElementById("locationDesc");
	location_desc_obj.value = location_desc;
	
}//end of setLocationDesc()

function setFocus()
<logic:equal name="adminStaffForm" property="action" value="create">
{
	var poiValue = document.getElementsByName("position.probOfficerInd");
	if (poiValue != null && poiValue.length > 0 )
	{
		poiValue[0].focus();
		return;
	}
}	
</logic:equal>
<logic:equal name="adminStaffForm" property="action" value="update">
{	
	var posName = document.getElementsByName("position.positionName");
    if (posName != null && posName.length > 0 )
	   {	
		posName[0].focus();
		return;
	   }
}
</logic:equal>	   	

</script>

</head>
<body topmargin="0" leftmargin="0" onload="reloadOrganization(document.forms[0], 'position.divisionId','position.programUnitId','position.programSectionId','<bean:write name="adminStaffForm" property="position.programUnitId"/>', '<bean:write name="adminStaffForm" property="position.programSectionId"/>'); presetCaseload(); setFocus();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<script language="JavaScript" id="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</script>

<html:form action="/displayPositionSupSelection" target="content">
<logic:equal name="adminStaffForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|1"> 
</logic:equal>
<logic:equal name="adminStaffForm" property="action" value="update">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|17">
</logic:equal>



	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="/jsp/common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
						height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="/jsp/common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="positionsTab" />
							</tiles:insert> <!--tabs end--></td>
						</tr>

					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message
										key="title.CSCD" /> - 
										<logic:equal name="adminStaffForm" property="action" value="create">
											<bean:message key="prompt.create" /> 
										</logic:equal>
										<logic:equal name="adminStaffForm" property="action" value="update">
											<bean:message key="prompt.update" /> 
										</logic:equal>
										<bean:message key="prompt.position" />
									</td>
								</tr>
							</table>
							<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert">
									<span id="errors"><html:errors /></span>
									</td>
								</tr>
							</table>
							<!-- END ERROR TABLE --> 
							<!-- BEGIN INSTURCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
												<logic:equal name="adminStaffForm" property="action" value="create">
													<li>Enter Required Fields and Click Next.</li>	
												</logic:equal>
															<logic:equal name="adminStaffForm" property="action" value="update">
															<li>Update Fields and Click Next.</li>	
															<li>Click Retire Position to retire this position.</li>	
															</logic:equal>																					
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required" colspan="2"> <bean:message key="prompt.requiredFields" /></td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!-- position form begin-->
									<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>
												<table width="100%" cellpadding="2" cellspacing="0">
													<tr>
														<td class="detailHead"><bean:message key="prompt.position" /> <bean:message key="prompt.information" /></td>
														<td align="right"></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" nowrap width="20%">
															<logic:equal name="adminStaffForm" property="action" value="create">
																<bean:message key="prompt.diamond" />
															</logic:equal>
														<bean:message key="prompt.hasCaseload"/></td>
														<td class="formDe">
															<logic:equal name="adminStaffForm" property="action" value="create">
															YES<html:radio property="position.hasCaseload" value='true' onclick="renderPOI(this)"/> NO<html:radio property="position.hasCaseload" value='false' onclick="renderPOI(this)"/>																														
															
															</logic:equal>
															<logic:equal name="adminStaffForm" property="action" value="update">
																<html:hidden styleId="hasCaseLoadTxt" property="position.hasCaseload"/>
																<bean:write name="adminStaffForm" property="position.caseloadAsYESNO"/>
															</logic:equal>
															</td>
														</tr>
														
														<tr id="POIRow" >
															<td class="formDeLabel" nowrap>
																<logic:equal name="adminStaffForm" property="action" value="create">
																	<bean:message key="prompt.diamond"/>
																</logic:equal>	
																<bean:message key="prompt.probationOfficerIndicator"/>
															</td>
															<td colspan="3" class="formDe">
																<logic:equal name="adminStaffForm" property="action" value="create">
																	<html:text property="position.probOfficerInd" size="2" maxlength="2" />
																</logic:equal>
																<logic:equal name="adminStaffForm" property="action" value="update">
																	<html:hidden property="position.probOfficerInd"/>
																	<bean:write name="adminStaffForm" property="position.probOfficerInd"/>
																</logic:equal>
															</td>
														</tr>
																<tr>
																	<td class="formDeLabel" nowrap><span class="hidden" id="posNameRequired"><bean:message key="prompt.diamond"/></span><bean:message key="prompt.positionName"/></td>
																	<td colspan="3" class="formDe">
																		<html:text property="position.positionName" size="30" maxlength="50"/>
																	</td>
																</tr>
																
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.positionType"/></td>
																	<td class="formDe" colspan="3">
																	<logic:equal name="adminStaffForm" property="action" value="create">
																		<html:select styleId="positionType" property="position.positionTypeId" onchange="checkPositionType()">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection property="position.positionTypes" label="description" value="supervisionCode"/>
																		</html:select>
																	</logic:equal>
																	<logic:equal name="adminStaffForm" property="action" value="update">
																		<logic:equal name="adminStaffForm" property="position.hasStaffSubordinates" value="true">
																			<logic:notEqual name="adminStaffForm" property="position.positionTypeId" value="<%=PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR%>" >
																				<html:select styleId="positionType" styleClass="hidden" property="position.positionTypeId" onchange="checkPositionType()">
																					<html:option value=""><bean:message key="select.generic"/></html:option>
																					<html:optionsCollection property="position.positionTypes" label="description" value="supervisionCode"/>
																				</html:select>
																				<bean:write name="adminStaffForm" property="position.positionTypeDesc"/>
																			</logic:notEqual>
																			<logic:equal name="adminStaffForm" property="position.positionTypeId" value="<%=PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR%>" >
																				<bean:write name="adminStaffForm" property="position.positionTypeDesc"/>
																			</logic:equal>
																		</logic:equal>
																		<logic:notEqual name="adminStaffForm" property="position.hasStaffSubordinates" value="true">
																			<logic:notEqual name="adminStaffForm" property="position.positionTypeId" value="<%=PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR%>">
																				<html:select styleId="positionType" property="position.positionTypeId" onchange="checkPositionType()">
																					<html:option value=""><bean:message key="select.generic"/></html:option>
																					<html:optionsCollection property="position.positionTypes" label="description" value="supervisionCode"/>
																				</html:select>
																			</logic:notEqual>
																			<logic:equal name="adminStaffForm" property="position.positionTypeId" value="<%=PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR%>">
																				<bean:write name="adminStaffForm" property="position.positionTypeDesc"/>
																			</logic:equal>
																		</logic:notEqual>
																		
																	</logic:equal>
																	<html:hidden  property="position.positionTypeDesc" styleId="positionTypeDesc" />	
																	</td>
																</tr>
																<%--NOT IMPLEMENTED YET AS OF 04/17/2007 --%>
																<tr id="CSTSRow">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.cstsOfficerType"/></td>
																	<td class="formDe" colspan="3">
																		<html:select styleId="officerType" property="position.officerTypeId" onchange="setOfficerTypeDesc()">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection property="position.CSTSOfficerTypes" value="code" label="description" />																										
																		</html:select>
																		<html:hidden  property="position.officerTypeDesc" styleId="officerTypeDesc" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.diamond"/><bean:message key="prompt.jobTitle"/></td>
																	<td class="formDe" colspan="3">
																		<html:select size="1" styleId="jobTitle" property="position.jobTitleId" onchange="checkForCLO()">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection property="position.jobTitles" label="description" value="supervisionCode"/>
																		</html:select>
																		<html:hidden  property="position.jobTitleDesc" styleId="jobTitleDesc" />
																	</td>
																</tr>
																<tr id="courtsRow" class="hidden">
																	<td class="formDeLabel" valign="top"><span id="courtsRowDiamond" class="hidden"><bean:message key="prompt.diamond"/></span>Court(s)</td>
																	<td colspan="1" >
																		<table class="borderTableBlue" border="0" cellpadding="2" cellspacing="0" width="98%">
																	<tr>
																		<bean:define id="myPosition" name="adminStaffForm" property="position"/>
																		<tiles:insert page="/jsp/common/courts.jsp" flush="true">
																			<tiles:put name="beanName" beanName="myPosition" />
																			<tiles:put name="ASOSpecialDisplay" value="ASOSpecialDisplay" />
																			<tiles:put name="mode" value="select" />
																		</tiles:insert>		
																	</tr>
																	</table>	
																	</td>
																</tr>
																
																<tr id="divisionSelectRow">
																	<td class="formDeLabel" width="1%" nowrap>
																		<logic:equal name="adminStaffForm" property="action" value="create">
																			<bean:message key="prompt.diamond"/>
																		</logic:equal>	
																		<bean:message key="prompt.division"/>
																	</td>
																	<td class="formDe" colspan="3">
																	<logic:equal name="adminStaffForm" property="action" value="create">
																		<html:select size="1" property="position.divisionId" onchange="updateOrganization2(document.forms[0], 'position.divisionId','position.programUnitId','position.programSectionId')">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection property="position.organizations" value="organizationId" label="description" />
																		</html:select>
																	</logic:equal>
																	<logic:equal name="adminStaffForm" property="action" value="update">
																		<html:select size="1" property="position.divisionId" onchange="updateOrganization2(document.forms[0], 'position.divisionId','position.programUnitId','position.programSectionId')" styleClass="hidden">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection property="position.organizations" value="organizationId" label="description" />
																		</html:select>
																		<bean:write name="adminStaffForm" property="position.divisionDesc"/>
																	</logic:equal>
																	</td>
																</tr>
																<tr id="programUnitSelect">
																	<td class="formDeLabel" nowrap><span class="hidden" id="programUnitReq"><bean:message key="prompt.diamond"/></span><bean:message key="prompt.programUnit"/></td>
																	<td class="formDe" colspan="3">
																		<html:select property="position.programUnitId" styleId="pu" disabled="true" onchange="changeProgramUnit()" >
																			<html:option value=""><bean:message key="select.generic"/></html:option>
							
																		</html:select>
																		<html:hidden  property="position.programUnitDesc" styleId="programUnitDesc" />
																	</td>
																</tr>
																<tr id="programSectionSelect">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.programSection"/></td>
																	<td class="formDe" colspan="3">
																		<html:select property="position.programSectionId" styleId="sectionSelect" disabled="true" onchange="setProgramSectionDesc()" >
																		<html:option value=""><bean:message key="select.generic"/></html:option>
																			
																		</html:select>
																		<html:hidden  property="position.programSectionDesc" styleId="programSectionDesc" />
																	</td>
																</tr>
																
																<script>
																	updateOrganization2(document.forms[0], 'position.divisionId','position.programUnitId','position.programSectionId');
																	updateOrganization3(document.forms[0],'position.divisionId', 'position.programUnitId', 'position.programSectionId');
																	
																</script>
																
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
																	<td class="formDe" colspan="3">
																		<html:text property="position.phone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
																		<html:text property="position.phone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
																		<html:text property="position.phone.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.location"/></td>
																	<td class="formDe" colspan="3">
																		<html:select property="position.locationId" styleId="location" onchange="setLocationDesc()">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection property="position.locations" value="locationId" label="locationName" />																										
																		</html:select>
																		<html:hidden  property="position.locationDesc" styleId="locationDesc" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.locationDetails"/></td>
																	<td class="formDe" colspan="3">
																		<html:text property="position.locationDetails"/>
																	</td>
																</tr>
																
																<logic:equal name="adminStaffForm" property="action" value="create">
																<tr class="visibleTR" id="effectiveDate">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.effectiveDate"/></td>
																	<td class="formDe" colspan="3">
																		<html:text styleId="effectiveDateAsStr" property="position.effectiveDateAsStr" maxlength="10"/>
																		<a href="#" onClick="cal1.select(document.forms[0].effectiveDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar"/></a>
																	</td>
																</tr>
																</logic:equal>
																
																<logic:equal name="adminStaffForm" property="action" value="update">
																	<tr class="visibleTR" id="currentUser">
																		<td class="formDeLabel" nowrap><bean:message key="prompt.currentlyAssignedTo"/></td>
																		<td class="formDe" colspan="3">
																		<logic:equal name="adminStaffForm" property="position.vacant" value="false">
																			<bean:write name="adminStaffForm" property="position.user.userName.formattedName"/>
																		</logic:equal>
																		<logic:notEqual name="adminStaffForm" property="position.vacant" value="false">
																			<bean:message key="prompt.noOfficerAssigned"/>
																		</logic:notEqual>
																			</td>
																	</tr>
																	<logic:notEqual name="adminStaffForm" property="position.vacant" value="true">
																		<tr class="visibleTR" id="currentUserCJAD">
																			<td class="formDeLabel" nowrap><bean:message key="prompt.cjad"/></td>
																			<td class="formDe" colspan="3">
																				<html:text property="position.user.cjad" maxlength="9"/>
																			</td>
																		</tr>
																	</logic:notEqual>
																	<tr class="visibleTR" id="effectiveDate">
																		<td class="formDeLabel" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.effectiveDate"/></td>
																		<td class="formDe" colspan="3">
																			<html:text styleId="effectiveDateAsStr" property="position.effectiveDateAsStr" maxlength="10"/>
																			<a href="#" onClick="cal1.select(document.forms[0].effectiveDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar"/></a>
																		</td>
																	</tr>
																</logic:equal>
															</table></td>
														</tr>
													</table>
													<!-- position form end-->
													<logic:equal name="adminStaffForm" property="action" value="create">
													<script>
																renderPOI(document.forms[0]["position.hasCaseload"]);
															</script>
													</logic:equal>
													<logic:equal name="adminStaffForm" property="action" value="update">
													<script>
																hidePOI(<bean:write name="adminStaffForm" property="position.hasCaseload"/>);
															</script>
													</logic:equal>
													
													<br>
														<!-- BEGIN BUTTON TABLE -->
														<table border="0" width="100%">
															<tr>
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																		<html:submit property="submitAction" onclick="return performValidation(this.form) && clearErrorMessage() && disableSubmit(this, this.form) "><bean:message key="button.submit" /></html:submit>
																	<input type="Reset" value="Reset" name="clear" >																	
																	<html:cancel property="submitAction" onclick="javascript:document.forms[0].reset()"><bean:message key="button.cancel" /></html:cancel>
																</td>
															</tr>
														</table>
														<script>
															checkForCLO();
															checkPositionType();
															
														</script>
														
														
							<!-- END BUTTON TABLE -->
							</td>
						</tr>
					</table>
					<br>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
	</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
