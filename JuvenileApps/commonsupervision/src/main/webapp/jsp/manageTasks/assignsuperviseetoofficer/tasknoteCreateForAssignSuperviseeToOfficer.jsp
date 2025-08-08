<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/11/2008  CShimek  defect#54131 revised program unit assessment insert to use ..Assign..Tile.jsp instead of ..Reassign..Tile.jsp -->
<!-- 02/17/2010  RYoung   Activity #61809 Initiate Program Unit referral -->
<!-- 03/05/2010  CShimek  Revise tinyMCE for casenote to match new standard, found while testing activity #64921 -->
<!-- 03/17/2010  CShimek  #64494 revised validateProgramReferralInfo() to handle possible state whereo new referral block not displayed on page -->
<!-- 05/21/2010  CShimek  #65598 added individual check for programBeginDateAsStr and reasonForPlacementId as these field display for different states when referralDateAsStr may be present of page  -->
<!-- 08/24/2010  D Williamson - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
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
<title><bean:message key="title.heading" /> - manageTasks/assignsuperviseetoofficer/tasknoteCreateForAssignSuperviseeToOfficer.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" type="text/javascript" src="/CommonSupervision/js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script LANGUAGE="JavaScript" ID="js1">
  var cal1 = new CalendarPopup();
  cal1.showYearNavigation();


function validateField(theForm){
    clearAllValArrays();
    trimCasenote('casenoteText');
    customValRequired("casenoteText","Casenote Text is required","");
	customValMaxLength("casenoteText","Casenote Text cannot be more than 3500 characters","3500");
	addDefinedTinyMCEFieldMask("casenoteText","Casenote Text cannot have % or _ entries","");
    if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(theForm.casenoteText.value, 'Casenote Text is required')){
		return true;
	}else {
		return false;
	}
 }

function validateProgramReferralInfo(theForm) {
	var msg1 = "";
	var result = "";
	if ( typeof( theForm.programEndDateAsStr ) != "undefined" ){
		
		 msg1 = validateDate( theForm.programEndDateAsStr.value, "Program End Date");

		if ( msg1 != "" ){
			theForm.programEndDateAsStr.focus(); 
		}

		if ( theForm.reasonForDischargeId.selectedIndex == 0 ){
			if ( msg1 == "" ){
				theForm.reasonForDischargeId.focus();
			}
			msg1 += "Reason for Discharge is required! \n" ;
		}	
	}

	if ( typeof( theForm.referralDateAsStr ) != "undefined" ){
		result = validateDate ( theForm.referralDateAsStr.value, "Referral Date ");
		if (result != ""){
			if ( msg1 == "" ){
				theForm.referralDateAsStr.focus(); 
			}
			msg1 += result;
		}
	}
	if ( typeof( theForm.programBeginDateAsStr ) != "undefined" ){		
		result = validateDate ( theForm.programBeginDateAsStr.value, "Program Begin Date ");
		if (result != ""){
			if ( msg1 == "" ){
				theForm.programBeginDateAsStr.focus(); 
			}
			msg1 += result;
		}				
	}
	if ( typeof( theForm.reasonForPlacementId ) != "undefined" ){	
		if ( theForm.reasonForPlacementId.selectedIndex == 0 ){
			if ( msg1 == "" ){
				theForm.reasonForPlacementId.focus(); 
			}
			msg1 += "Reason for Placement is required! " ;
		}
	}	
	if ( msg1 == "" ){
		return true;
	}
	alert( msg1 );
	return false;	    	
}	

function validateDate( fldValue, fldName )
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	if (fldValue == "")
	{
		msg = fldName + " is required.\n";
		return msg;
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldName + " is not a valid date.\n";
		return msg;	
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;	
	} 

    if (month < 1 || month > 12)
    {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if (day < 1 || day > 31) {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		msg = fldName + " is not valid.\n";
		return msg;	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
			msg = fldName + " is not valid.\n";
			return msg;	
        }
    }
    var dt = fldValue + " " + "00:00";
    var fldDateTime = new Date(dt);
    var curDateTime = new Date();
    if (fldDateTime > curDateTime){
	    msg = fldName + " cannot be future value.";   
 		return msg;
    }	    
 	return msg;
}	

</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/addcasenoteforofficerassignment" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|6">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>

			<td valign="top">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--blue tabs start--> <tiles:insert
						page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> <!--blue tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>

					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header">
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;							
									Assign Supervisee to Officer - Casenote
							</td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> 
					
					<!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td>
							<ul>
								<li>Enter Casenote. Click Next</li>
							</ul>
							</td>
						</tr>
						<tr>
							<td class="required"><img src="/<msp:webapp/>images/required.gif"
								title="required" alt="required image" border="0" width="10" height="9">
							Required Field</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE -->
					
					<!--header start--> 
					<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
					</tiles:insert> 
					<!--header end-->
					<br>
					
					<!-- BEGIN DETAIL TABLE --> 
					
						<!-- PROGRAM UNIT ASSIGNMENT START -->
						<tiles:insert page="../../common/selectedOffToAssignSupToTile.jsp" flush="true">
						</tiles:insert>
						<!-- PROGRAM UNIT ASSIGNMENT END -->
						<!-- PROGRAM UNIT ASSIGNMENT START -->
						<tiles:insert page="../../administerCaseloadCSCD/programReferrals/programReferralReassignOfficerTile.jsp" flush="true">
						</tiles:insert>
						<!-- PROGRAM UNIT ASSIGNMENT END -->
					<span id="addCN"> <!-- add casenote start-->
					<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
						<tr class="detailHead">
							<td>New Casenote Information</td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><img
										src="/<msp:webapp/>images/required.gif" title="required"
										alt="required image" border="0" width="10" height="9">Casenote</td>
								</tr>
								<tr class="formDe">
									<td>
										<nest:define id="agencyId" name="caseAssignmentForm" property="agencyId"/>
										<html:textarea name="caseAssignmentForm" property="casenoteText" onkeyup="textCounter(this.form.casenoteText,1000);"  ondblclick="myReverseTinyMCEFix(this)"
														styleClass="mceEditor" style="width:100%" rows="15" >
										</html:textarea>
										<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
									         <tiles:put name="agencyCode"><%=agencyId%></tiles:put>
								        </tiles:insert> 
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<br>
					<!--add casenotes end--> </span>
					<!-- END DETAIL TABLE -->
					<!-- BEGIN BUTTON TABLE -->
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:submit property="submitAction">
									<bean:message key="button.back" />
								</html:submit> 
								<html:submit property="submitAction" onclick="return myTinyMCEFix() && validateProgramReferralInfo(this.form) && validateField(this.form) && disableSubmit(this, this.form);">
									<bean:message key="button.assignsuperviseetoofficer.selectofficer.addCasenote" /> 
								</html:submit> 
								<html:reset><bean:message key="button.reset"/></html:reset> 
								<html:submit property="submitAction">
									<bean:message key="button.cancel" />
								</html:submit>
							</td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --></td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>