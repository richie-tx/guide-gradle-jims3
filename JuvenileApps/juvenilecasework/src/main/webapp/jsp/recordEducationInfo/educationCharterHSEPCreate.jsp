<!DOCTYPE HTML>
<%-- User selects the "School" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<!-- 01/21/2011 D Williamson Create JSP  -->
<!-- 03/04/2011 C Shimek     modified validation to no longer validate dates as they are now defaulted to current date -->
<!-- 05/15/2012 C Shimek	 #73498 added Not Applicable option to Score --> 
<%-- 08/6/2015  R Young      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.Features"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - educationCharterHSEPCreate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">


function onloadForm()
{
	var indx = document.educationCharterDetailsForm.hsepProgramCodeId.selectedIndex;
	var theVal = document.educationCharterDetailsForm.hsepProgramCodeId.options[indx].value;
	if (theVal == "OTHR"){
		show("otherField", 1, "row");
	}else {
		show("otherField", 0);
	}	
}

function checkForOther(theSelect){
	var theVal = theSelect.options[theSelect.selectedIndex].value;
	if (theVal == "OTHR"){
		show("otherField", 1, "row");
	}else {
		show("otherField", 0);
	}
}
function validateInput(theForm){
	var msg = "";
	var results = new Array(13);
	var flds = new Array(13);
	flds[0] = "hsepProgramCodeId";
	flds[1] = "otherProgramCode";
	// Changes for ER: JIMS200077481 starts
	//flds[2] = "readingRetest";
	//flds[3] = "readingTestDateStr";
	//flds[4] = "readingScore";
	//flds[5] = "readingBeforePlacementSelected";
	//flds[6] = "writingTestDateStr";
	//flds[7] = "writingScore";
	//flds[8] = "writingBeforePlacementSelected";
	// Changes for ER: JIMS200077481 ends
	flds[2] = "rlaTestDateStr";
	flds[3] = "rlaScore";
	flds[4] = "rlaBeforePlacementSelected";
	flds[5] = "mathTestDateStr";
	flds[6] = "mathScore";
	flds[7] = "mathBeforePlacementSelected";
	flds[8] = "scienceTestDateStr";
	flds[9] = "scienceScore";
	flds[10] = "scienceBeforePlacementSelected";
	flds[11] = "socialStudiesTestDateStr";
	flds[12] = "socialStudiesScore";
	flds[13] = "socialStudiesBeforePlacementSelected";
	
	//flds[14] = flds[4]; // changes for ER JIMS200077481
			
	if (theForm.hsepProgramCodeId.selectedIndex == 0){
		results[0] = "GED Program selection is required.\n";
	} else if (theForm.hsepProgramCodeId.options[theForm.hsepProgramCodeId.selectedIndex].value == 'OTHR'){
		var pgmDesc = Trim(theForm.otherProgramCode.value);
		theForm.otherProgramCode.value = pgmDesc;
		if (pgmDesc == ""){	
			results[1] = "Other GED Program is required.\n";
		}
	}
	
/**	if (theForm.retestPresent.value != "0"){
		var rtChecked = 0;
		var rtFld1 = document.getElementsByName("readingRetest");
		if (rtFld1[0] != null && rtFld1[0].checked == true) {
			rtChecked = 1;
		}
		rtFld2 = document.getElementsByName("writingRetest");
		if (rtFld2[0] != null && rtFld2[0].checked == true) {
			rtChecked = 1;
		}
		rtFld3 = document.getElementsByName("mathRetest");
		if (rtFld3[0] != null && rtFld3[0].checked == true) {
			rtChecked = 1;
		}
		rtFld4 = document.getElementsByName("scienceRetest");
		if (rtFld4[0] != null && rtFld4[0].checked == true) {
			rtChecked = 1;
		}
		rtFld5 = document.getElementsByName("socialStudiesRetest");
		if (rtFld5[0] != null && rtFld5[0].checked == true) {
			rtChecked = 1;
		}
		// true if previous test Not Applicabe selected on all categories
		if (rtFld1[0] == null &&  rtFld2[0] == null && rtFld3[0] == null && rtFld4[0] == null && rtFld5[0] == null) {
			trChecked = -1;
		}
		if (rtChecked == 0) {
			results[2] = "At least 1 Retest selection is required.\n";
		}
	} */
	// changes for ER JIMS200077481 starts
	//theForm.readingScore.value = Trim(theForm.readingScore.value);
	//theForm.writingScore.value = Trim(theForm.writingScore.value); 
	theForm.rlaScore.value = Trim(theForm.rlaScore.value); 
	// changes for ER JIMS200077481 ends
	theForm.mathScore.value = Trim(theForm.mathScore.value);
	theForm.scienceScore.value = Trim(theForm.scienceScore.value);
	theForm.socialStudiesScore.value = Trim(theForm.socialStudiesScore.value);
	theForm.totalScoreScore.value=Trim(theForm.totalScoreScore.value);
//	results[3] = validateDate(theForm.readingTestDateStr.value, "Language Arts, Reading Test Date");
	results[4] = validateScore(theForm.rlaScore.value, "Reasoning Through Language Arts (RLA)", "rlaNA"); // changes for ER JIMS200077481
	results[5] = validatePlacements(theForm.rlaBeforePlacementSelected, theForm.rlaAfterPlacementSelected, "Reasoning Through Language Arts (RLA)", "rlaNA");
	// changes for ER JIMS200077481 starts
//	results[6] = validateDate(theForm.writingTestDateStr.value, "Language Arts, Writing Test Date");
//	results[7] = validateScore(theForm.writingScore.value, "Language Arts, Writing Score", "writingNA");
//	results[8] = validatePlacements(theForm.writingBeforePlacementSelected, theForm.writingAfterPlacementSelected, "Language Arts, Writing", "writingNA");
// changes for ER JIMS200077481 ends

//	results[9] = validateDate(theForm.mathTestDateStr.value, "Mathematics Test Date");
	results[7] = validateScore(theForm.mathScore.value, "Mathematics Score", "mathNA");
	results[8] = validatePlacements(theForm.mathBeforePlacementSelected, theForm.mathAfterPlacementSelected, "Mathematics", "mathNA");

//	results[12] = validateDate(theForm.scienceTestDateStr.value, "Science Test Date");
	results[9] = validateScore(theForm.scienceScore.value, "Science Score", "scienceNA");
	results[10] = validatePlacements(theForm.scienceBeforePlacementSelected, theForm.scienceAfterPlacementSelected, "Science", "scienceNA");

//	results[15] = validateDate(theForm.socialStudiesTestDateStr.value, "Social Studies Test Date");
	results[12] = validateScore(theForm.socialStudiesScore.value, "Social Studies Score", "socialStudiesNA");
	results[13] = validatePlacements(theForm.socialStudiesBeforePlacementSelected, theForm.socialStudiesAfterPlacementSelected, "Social Studies", "socialStudiesNA");

//	var scores = theForm.readingScore.value + theForm.writingScore.value + theForm.mathScore.value +
//				 theForm.scienceScore.value + theForm.socialStudiesScore.value;
//	if (scores == "" && theForm.retestPresent.value == "0"){
//		results[18] = "At least 1 score value must be entered.";
//	}
	
	for (x=0; x < 13; x++) {
		if (results[x] > ""){
			msg += results[x];
		}	
	}	
	if (msg > ""){
		alert(msg);
		for (x=0; x < 13; x++) {
			if (results[x] > ""){
				var ff = document.getElementsByName(flds[x]);
				ff[0].focus();
				break;
			}	
		}
		return false;	
	}
	
	
	if(theForm.writingRlaPass.checked && theForm.writingRlaFail.checked){
		alert(" Either of Pass or Fail for RLA should be selected not both"); return false;
	}
	if(!theForm.writingRlaPass.checked && !theForm.writingRlaFail.checked)
	{	
		if(theForm.rlaScoreNotApplicable.checked){}else{
		alert("Either of Pass or Fail for RLA must be selected");return false;}
	}
	
	
	if(theForm.writingRlaPass.checked && theForm.rlaScoreNotApplicable.checked){
	alert(" Either of Pass/Fail or Not applicable for RLA should be selected not both"); return false;
	}
	
	if(theForm.writingRlaFail.checked && theForm.rlaScoreNotApplicable.checked){
		alert(" Either of Pass/Fail or Not applicable for RLA should be selected not both"); return false;
		}
	
	if(theForm.writingMathPass.checked && theForm.writingMathFail.checked)
	{alert("Either of Pass or Fail for Mathematics should be selected not both");return false;}

	if(!theForm.writingMathPass.checked && !theForm.writingMathFail.checked){
		if(theForm.mathScoreNotApplicable.checked){}else{
	alert("Either of Pass or Fail for Mathematics must be selected");return false;}}
	
	if(theForm.writingMathPass.checked && theForm.mathScoreNotApplicable.checked){
		alert(" Either of Pass/Fail or Not applicable for Math should be selected not both"); return false;
		}
		
	if(theForm.writingMathFail.checked && theForm.mathScoreNotApplicable.checked){
			alert(" Either of Pass/Fail or Not applicable for Math should be selected not both"); return false;
			}
	if(theForm.writingSciencePass.checked && theForm.writingScienceFail.checked)
	{alert("Either of Pass or Fail for Science should be selected not both");return false;}

	if(!theForm.writingSciencePass.checked && !theForm.writingScienceFail.checked){
		if(theForm.scienceScoreNotApplicable.checked){}else{
	alert("Either of Pass or Fail for Science must be selected");return false;}}
	
	if(theForm.writingSciencePass.checked && theForm.scienceScoreNotApplicable.checked){
		alert(" Either of Pass/Fail or Not applicable for Science should be selected not both"); return false;
		}
		
	if(theForm.writingScienceFail.checked && theForm.scienceScoreNotApplicable.checked){
			alert(" Either of Pass/Fail or Not applicable for Science should be selected not both"); return false;
			}
	
	if(theForm.writingSocialPass.checked && theForm.writingSocialFail.checked)
	{alert("Either of Pass or Fail for Social Studies should be selected not both");return false;}

	if(!theForm.writingSocialPass.checked && !theForm.writingSocialFail.checked){
		if(theForm.socialStudiesScoreNotApplicable.checked){}else{
	alert("Either of Pass or Fail for Social Studies must be selected");return false;}}
	
	
	if(theForm.writingSocialPass.checked && theForm.socialStudiesScoreNotApplicable.checked){
		alert(" Either of Pass/Fail or Not applicable for Social should be selected not both"); return false;
		}
		
	if(theForm.writingSocialFail.checked && theForm.socialStudiesScoreNotApplicable.checked){
			alert(" Either of Pass/Fail or Not applicable for Social should be selected not both"); return false;
			}
	
	
	if(theForm.totalScoreScore.value == ""){
		if(!theForm.writingTotalIncomplete.checked){
		alert("Must select Incomplete option for no Total Score (or) enter the score to choose between pass or fail"); return false;
		}
	}
	if(theForm.totalScoreScore.value != ""){
		if(theForm.writingTotalPass.checked && theForm.writingTotalFail.checked){
			alert("Any of the options of Pass/Fail for Total Score should be selected not all ");return false;
		}
	}
	
	if(theForm.writingTotalPass.checked && theForm.writingTotalFail.checked && theForm.writingTotalIncomplete.checked){
		alert("Any of the options of Pass/Fail or Incomplete for Total Score should be selected not all"); return false;
	}
	
	if(!theForm.writingTotalPass.checked && !theForm.writingTotalFail.checked && !theForm.writingTotalIncomplete.checked){
		alert("Any of the options of Pass/Fail or Incomplete for Total Score should be selected "); return false;
	}
	
	if(theForm.totalScoreScore.value != ""){
		if(theForm.writingTotalIncomplete.checked){
		alert("Total Score should not entered for Incomplete"); return false;
		}
	}
	if(theForm.writingTotalIncomplete.checked){
		if(theForm.writingTotalPass.checked)
			{alert("Either Incomplete or Pass/Fail should selected"); return false;	}	
		if(theForm.writingTotalFail.checked)
		{alert("Either Incomplete or Pass/Fail should selected"); return false;	}		
		
	}
// found issue on reselecting placement if page accessed via back button 
// on Summary page; this code resolves the issue

	// changes for ER JIMS200077481 starts
	theForm.rlaBeforePlacement.value = false;
	theForm.rlaAfterPlacement.value = false;
	theForm.incomplete.value = false;
	if (theForm.incomplete.checked){
		theForm.incomplete.value = true;
	}
	if (theForm.rlaBeforePlacementSelected.checked){
		theForm.rlaBeforePlacement.value = true;
	}
	if (theForm.rlaAfterPlacementSelected.checked){
		theForm.rlaAfterPlacement.value = true;
	}
	
	

		//theForm.writingBeforePlacement.value = false;
	//theForm.writingAfterPlacement.value = false;
	//if (theForm.writingBeforePlacementSelected.checked){
		//theForm.writingBeforePlacement.value = true;
	//}
	//if (theForm.writingAfterPlacementSelected.checked){
		//theForm.writingAfterPlacement.value = true;
	//}
	// changes for ER JIMS200077481 ends

	theForm.mathBeforePlacement.value = false;
	theForm.mathAfterPlacement.value = false;
	if (theForm.mathBeforePlacementSelected.checked){
		theForm.mathBeforePlacement.value = true;
	}
	if (theForm.mathAfterPlacementSelected.checked){
		theForm.mathAfterPlacement.value = true;
	}
	
	theForm.scienceBeforePlacement.value = false;
	theForm.scienceAfterPlacement.value = false;
	if (theForm.scienceBeforePlacementSelected.checked){
		theForm.scienceBeforePlacement.value = true;
	}
	if (theForm.scienceAfterPlacementSelected.checked){
		theForm.scienceAfterPlacement.value = true;
	}
	
	theForm.socialStudiesBeforePlacement.value = false;
	theForm.socialStudiesAfterPlacement.value = false;
	if (theForm.socialStudiesBeforePlacementSelected.checked){
		theForm.socialStudiesBeforePlacement.value = true;
	}
	if (theForm.socialStudiesAfterPlacementSelected.checked){
		theForm.socialStudiesAfterPlacement.value = true;
	}

	return true;	
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    	alert("Only numbers are allowed in Total Score");
        return false;
    }
    return true;
}

function validateDate(fldValue, fldName)
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;

	if (fldValue == "") {
		msg = fldName + " is required.\n";
		return msg;
	}
// check for future date	
	var dt = fldValue + " 00:00";
	var fldDateTime = new Date(dt);
	var curDateTime = new Date();
	if (fldDateTime > curDateTime){
		msg = fldName + " can not be future value.\n";
		return msg;				
	}    	
 	return msg;
}
function validateScore(fldValue, fldName, IdName)
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	var notApp = document.getElementById(IdName);
	if (notApp == null) {
		if (fldValue == "") {
			msg = fldName + " is required.\n";
			return msg;
		}
	}
	if (notApp != null) {
		if (fldValue == "" && notApp.checked == false) {
			msg = fldName + " entry or Not Applicable selection is required.\n";
			return msg;
		}
		if (fldValue != "" && notApp.checked == true) {
			msg = fldName + " entry with Not Applicable selection is not allowed.\n";
			return msg;
		}
		if (notApp.checked == true) {
			return msg;
		}	
	}
	if (numericRegExp.test(fldValue,numericRegExp) == false){ 
		msg = fldName + ' must be a number from 100 to 200.\n'; // changes for ER JIMS200077481
		return msg;
	}	
	
	if (fldValue > 200 || fldValue < 100){
		msg = fldName + ' must be a number from 100 to 200.\n'; // changes for ER JIMS200077481
		return msg;
	}
	return msg;
}
function validatePlacements(fldValue1, fldValue2, fldName, IdName)
{
	msg = "";
	var notApp = document.getElementById(IdName);
	if (notApp == null) {
		if (fldValue1.checked == true && fldValue2.checked == true){
			msg = fldName + ' - only 1 placement selection is allowed for this category.\n';
		}	
	}	
	if (notApp != null) {
		if (notApp.checked == true){
			if (fldValue1.checked == true || fldValue2.checked == true){
				msg = fldName + ' placement selection not allowed when Not Applicable is selected.\n';
			}			
		}
		if (notApp.checked == false){
			if (fldValue1.checked == true && fldValue2.checked == true){
				msg = fldName + ' - only 1 placement selection is allowed for this category.\n';
			}	
		}	
	}
	return msg;
}

</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin='0' onload="onloadForm();">
<html:form action="displayGEDSummary" target="content">
<input type="hidden" name="retestPresent" value="<bean:write name="educationCharterDetailsForm" property="version" />" >
<!-- BEGIN HEADING TABLE -->
	<table width='100%'>
		<tr>
			<td align="center" class="header">
				<bean:message key="title.juvenileCasework" /> -
				<bean:message key="title.juvenileProfile" /> -
				<bean:message key="title.create" />&nbsp;<bean:message key="title.highSchoolEquivalencyProgram" />
			</td>
		</tr>
	</table>
	<!-- END HEADING TABLE -->
	<!-- BEGIN ERROR TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	<!-- END ERROR TABLE -->
	<div class='spacer'></div>
	<!-- BEGIN INSTRUCTION TABLE -->
	<table width="98%" border="0">
		<tr>
			<td>
			<ul>
				<li>Enter required information.</li>
				<li>Select Next button to view Summary information.</li>
			</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required"
				alt="required image" border='0' width='10' height='9'>&nbsp;Required
			Fields&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
		</tr>
	</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN JUVENILE PROFILE HEADER TABLE -->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader" />
	</tiles:insert>
<!-- END JUVENILE PROFILE HEADER TABLE-->
	<div class='spacer'></div>
<!-- BEGIN DETAIL TABLE -->	
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert 	page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
				</tr>
			</table>
<!-- BEGIN GREEN BORDER TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<div class='spacer'></div>
<!-- BEGIN TABLE -->					
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top">
										<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
											<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
										</tiles:insert>
									</td>
								</tr>
								<tr>
									<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
								</tr>
							</table>

							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
								<tr>
									<td valign="top" align="center">
									<div class="spacer"></div>
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
												<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
													<tiles:put name="tabid" value="charter" />
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>
											</td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width="5" alt=""></td>
										</tr>
									</table>
									</td>
								</tr>
								<div class="spacer"></div>
								<tr>
									<td valign="top" align="center">
										<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
											<tr colspan='8'>
												<td valign="top" align="center"><!-- BEGIN SCHOOL INFO TABLE -->
												<div class='spacer'></div>
												<table width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
													<tr colspan='8'>
														<td valign="top" class='detailHead'>
															<bean:message key="title.highSchoolEquivalencyProgram" />
															<bean:message key="prompt.information" /> 
														</td>
													</tr>
													<tr>
														<td>
															<table border='0' cellpadding="2" cellspacing="1" width='100%'>
																<tr colspan='8'>
																	<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.GEDProgram" /></td>
																	<td class='formDe' colspan='8'>
																		<html:select property="hsepProgramCodeId" onchange="checkForOther(this)">
				  												              <html:option value=""><bean:message key="select.generic"/></html:option>
				  												              <html:optionsCollection property="hsepProgramCodeList" value="code" label="description"/>
				  												        </html:select>  
																	</td>
																</tr>
																<tr id="otherField" class="hidden">
																	<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.otherGEDProgram" /></td>
																	<td class="formDe" colspan="8"><html:text name="educationCharterDetailsForm" property="otherProgramCode" size="50" maxlength="50" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.category" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDeLabel'>
																			<bean:message key="prompt.retest" />
																		</td>
																	</logic:equal>	
																	<td class='formDeLabel' nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.testDate" /></td>
																	<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.score" /></td>
																	<td class='formDeLabel'><bean:message key="prompt.pass" />
																	<td class='formDeLabel'><bean:message key="prompt.fail" />
																	<td class='formDeLabel'><bean:message key="prompt.incomplete" />
																	<td class='formDeLabel'><bean:message key="prompt.during" /><br><bean:message key="prompt.placement" /></td>
																	<td class='formDeLabel'><bean:message key="prompt.after" /><br><bean:message key="prompt.placement" /></td>
																</tr>
																
																<%-- Changes for ER: JIMS200077481 MJCW: --%>
															<%-- READING --%>	
															<%--
																<tr>
																	<td class='formDe'><bean:message key="prompt.languageArtsReading" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDeBold'>
																			<logic:equal name="educationCharterDetailsForm" property="readingShowRetest" value="true">
																				<html:checkbox name="educationCharterDetailsForm" property="readingRetest" />
																			</logic:equal>	
																		</td>
																	</logic:equal>	
																	<td class="formDeBold">
																		<html:text name="educationCharterDetailsForm" property="readingTestDateStr" size="10" maxlength="10"  readonly="true"/>
																		 <a href="#" onClick="cal1.select(document.forms[0].readingTestDateStr,'anchor1','MM/dd/yyyy'); return false;"
																		name="anchor1" ID="anchor1" border="0"><img border="0" src="/<msp:webapp/>images/calendar2.gif"
																		title="mm/dd/yyyy" align="middle" alt="" /></a>
																	</td>
																	<td class='formDeBold'>
																		<html:text name="educationCharterDetailsForm" property="readingScore" size="3" maxlength="3" />
																		<logic:equal name="educationCharterDetailsForm" property="readingShowRetest" value="false">
																			<html:checkbox name="educationCharterDetailsForm" property="readingScoreNotApplicable" styleId="readingNA" /><bean:message key="prompt.notApplicable" />
																		</logic:equal>
																	</td>
																	<td class='formDeBold'><html:checkbox name="educationCharterDetailsForm" property="readingBeforePlacementSelected" /></td>
																	<td class='formDeBold'><html:checkbox name="educationCharterDetailsForm" property="readingAfterPlacementSelected" /></td>
																</tr>
															<%-- WRITING 	
																<tr>
																	<td class='formDe'><bean:message key="prompt.languageArtsWriting" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDeBold'>
																			<logic:equal name="educationCharterDetailsForm" property="writingShowRetest" value="true">
																				<html:checkbox name="educationCharterDetailsForm" property="writingRetest" />
																			</logic:equal>	
																		</td>
																	</logic:equal>	
																	<td class="formDeBold">
																		<html:text name="educationCharterDetailsForm" property="writingTestDateStr" size="10" maxlength="10"  readonly="true"/>
																		 <a href="#" onClick="cal1.select(document.forms[0].writingTestDateStr,'anchor2','MM/dd/yyyy'); return false;"
																		 name="anchor2" ID="anchor2" border="0"><img border="0" src="/<msp:webapp/>images/calendar2.gif"
																		title="mm/dd/yyyy" align="middle" alt="" /></a>
																	</td>
																	<td class='formDeBold'>
																		<html:text name="educationCharterDetailsForm" property="writingScore" size="3" maxlength="3" />
																		<logic:equal name="educationCharterDetailsForm" property="writingShowRetest" value="false">
																			<html:checkbox name="educationCharterDetailsForm" property="writingScoreNotApplicable" styleId="writingNA"/><bean:message key="prompt.notApplicable" />
																		</logic:equal>
																	</td>
																	<td class='formDeBold'><html:checkbox name="educationCharterDetailsForm" property="writingBeforePlacementSelected" /></td>
																	<td class='formDeBold'><html:checkbox name="educationCharterDetailsForm" property="writingAfterPlacementSelected" /></td>
																</tr>
																</logic:equal>	 --%>
																<tr>
																	<td class='formDe' align="left"><bean:message key="prompt.RLA" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDe' align="left">
																			<logic:equal name="educationCharterDetailsForm" property="rlaShowRetest" value="true">
																				<html:checkbox name="educationCharterDetailsForm" property="rlaRetest" />
																			</logic:equal>	
																		</td>
																	</logic:equal>	
																	<td class="formDe" align="left">
																		<html:text name="educationCharterDetailsForm" property="rlaTestDateStr" size="10" maxlength="10"  readonly="true" styleId="rlaTestDate"/>
																	</td>
																	<td class='formDe' align="left">
																		<html:text name="educationCharterDetailsForm" property="rlaScore" size="3" maxlength="3" />
																		<logic:equal name="educationCharterDetailsForm" property="rlaShowRetest" value="false">
																			<html:checkbox name="educationCharterDetailsForm" property="rlaScoreNotApplicable" styleId="rlaNA"/><bean:message key="prompt.notApplicable" />
																		</logic:equal>
																	</td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingRlaPass" styleId="writingRlaPass"/></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingRlaFail" styleId="writingRlaFail"/></td>
																	<td class='formDe' ></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="rlaBeforePlacementSelected" /></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="rlaAfterPlacementSelected" /></td>
																</tr>
																<%-- Changes for ER: JIMS200077481--%>
															<%-- MATH --%>	
																<tr>
																	<td class='formDe'><bean:message key="prompt.mathematics" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDe' align="left">
																			<logic:equal name="educationCharterDetailsForm" property="mathShowRetest" value="true">
																				<html:checkbox name="educationCharterDetailsForm" property="mathRetest" />
																			</logic:equal>	
																		</td>
																	</logic:equal>	
																	<td class="formDe" align="left">
																		<html:text name="educationCharterDetailsForm" property="mathTestDateStr" size="10" maxlength="10"  readonly="true" styleId="mathTestDate"/>
																	</td>
																	<td class='formDe' align="left">
																		<html:text name="educationCharterDetailsForm" property="mathScore" size="3" maxlength="3" />
																		<logic:equal name="educationCharterDetailsForm" property="mathShowRetest" value="false">
																			<html:checkbox name="educationCharterDetailsForm" property="mathScoreNotApplicable" styleId="mathNA"/><bean:message key="prompt.notApplicable" />
																		</logic:equal>
																	</td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingMathPass" styleId="writingMathPass"/></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingMathFail" styleId="writingMathFail" /></td>
																	<td class='formDe' ></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="mathBeforePlacementSelected" /></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="mathAfterPlacementSelected" /></td>
																</tr>
															<%-- SCIENCE --%>	
																<tr>
																	<td class='formDe'><bean:message key="prompt.science" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDe' align="left">
																			<logic:equal name="educationCharterDetailsForm" property="scienceShowRetest" value="true">
																				<html:checkbox name="educationCharterDetailsForm" property="scienceRetest" />
																			</logic:equal>	
																		</td>
																	</logic:equal>		
																	<td class="formDe" align="left">
																		<html:text name="educationCharterDetailsForm" property="scienceTestDateStr" size="10" maxlength="10" readonly="true" styleId="scienceTestDate"/>
																	</td>
																	<td class='formDe' align="left">
																		<html:text name="educationCharterDetailsForm" property="scienceScore" size="3" maxlength="3" />
																		<logic:equal name="educationCharterDetailsForm" property="scienceShowRetest" value="false">
																			<html:checkbox name="educationCharterDetailsForm" property="scienceScoreNotApplicable" styleId="scienceNA"/><bean:message key="prompt.notApplicable" />
																		</logic:equal>
																	</td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingSciencePass" styleId="writingSciencePass"/></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingScienceFail" styleId="writingScienceFail"/></td>
																	<td class='formDe' ></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="scienceBeforePlacementSelected" /></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="scienceAfterPlacementSelected" /></td>
																</tr>
															<%-- SOCIAL STUDIES --%>	
																<tr >
																	<td class='formDe'><bean:message key="prompt.socialStudies" /></td>
																	<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDe' align="left">
																			<logic:equal name="educationCharterDetailsForm" property="socialStudiesShowRetest" value="true">
																				<html:checkbox name="educationCharterDetailsForm" property="socialStudiesRetest" />
																			</logic:equal>	
																		</td>
																	</logic:equal>	
																	<td class="formDe" align="left">
																		<html:text name="educationCharterDetailsForm" property="socialStudiesTestDateStr" size="10" maxlength="10" readonly="true" styleId="socialStudiesTestDate"/>
															    	</td>
																	<td class='formDe' align="left">
																		<html:text name="educationCharterDetailsForm" property="socialStudiesScore" size="3" maxlength="3" />
																		<logic:equal name="educationCharterDetailsForm" property="socialStudiesShowRetest" value="false">
																			<html:checkbox name="educationCharterDetailsForm" property="socialStudiesScoreNotApplicable" styleId="socialStudiesNA"/><bean:message key="prompt.notApplicable" />
																		</logic:equal>
																	</td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingSocialPass" styleId="writingSocialPass"/></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingSocialFail" styleId="writingSocialFail"/></td>
																	<td class='formDe' ></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="socialStudiesBeforePlacementSelected" /></td>
																	<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="socialStudiesAfterPlacementSelected" /></td>
																</tr>
														<%-- Total Score --%>
														
															<tr>
																<td class='formDeLabel' align="left" ><bean:message key="prompt.totalScore"/>:</td>
																<logic:equal name="educationCharterDetailsForm" property="showRetest" value="true">
																		<td class='formDe' ></td>
																</logic:equal>
																<td class='formDe' ></td>
																<td class='formDe' align="left">
																	<html:text name="educationCharterDetailsForm" property="totalScoreScore" size="3" maxlength="3" styleId="totalScoreId" onkeypress="return isNumber(event)"/>
																</td>
																<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingTotalPass" styleId="writingTotalPass"/></td>
																<td class='formDe' align="left"><html:checkbox name="educationCharterDetailsForm" property="writingTotalFail" styleId="writingTotalFail"/></td>
																<td class='formDe' align="middle"><html:checkbox  name="educationCharterDetailsForm" property="writingTotalIncomplete" styleId="writingTotalIncomplete"/></td>
																<td class='formDe' ></td>
																<td class='formDe' ></td>
															</tr>
															
															</table>
												<!-- END SCHOOL INFO TABLE -->
														</td>
													</tr>
												</table>	
											<!-- end data entry -->
											</td>
										</tr>	
								<!-- END TABLE -->
										<tr>
											<td>
												<%-- Changes for ER: JIMS200077481 starts--%>
												<%--
												<html:hidden name="educationCharterDetailsForm" property="readingBeforePlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="readingAfterPlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="writingBeforePlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="writingAfterPlacement"/> --%>
												<html:hidden name="educationCharterDetailsForm" property="rlaBeforePlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="rlaAfterPlacement"/>
												<%-- Changes for ER: JIMS200077481 ends--%>
												<html:hidden name="educationCharterDetailsForm" property="mathBeforePlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="mathAfterPlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="scienceBeforePlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="scienceAfterPlacement"/>
												<html:hidden name="educationCharterDetailsForm" property="socialStudiesBeforePlacement" />
												<html:hidden name="educationCharterDetailsForm" property="socialStudiesAfterPlacement"/>
											</td>
										</tr>
										<div class="spacer4PX"></div>
										<tr>
											<td>
								 <!-- BEGIN BUTTON TABLE -->
												<table border="0" width="100%">
													<tr>
														<td align="center">
															<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														 	<html:submit property="submitAction" onclick="return validateInput(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>
														 	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
														 </td>
													</tr>
												</table>
<!-- END BUTTON TABLE -->												
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
									<div class='spacer'></div>
								</td>
							</tr>		
<!-- END RED BORDER TABLE -->									
									<div class='spacer'></div>
								</table>
<!-- END BLUE BORDER TABLE -->									
							</td>
						</tr>
					</table>
					<div class='spacer'></div>
					</td>
				</tr>
			</table>
<!-- END GREEN BORDER TABLE -->			
			</td>
		</tr>
	</table>
	<!-- END DETAIL TABLE -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>