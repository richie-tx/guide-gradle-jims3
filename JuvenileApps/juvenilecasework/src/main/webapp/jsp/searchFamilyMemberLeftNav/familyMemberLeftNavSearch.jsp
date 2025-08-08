<!DOCTYPE HTML>
<%-- User selects the "Search Family Member" link in the "Navigation Tree" --%>
<%--MODIFICATIONS --%>
<%-- 07/26/2012	CShimek		Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK LOCAL REFERENCE-->
<%@include file="../jQuery.fw"%> 

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" /> - familyMemberLeftNavSearch.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/familyMember.js"></script>


<script type='text/javascript'>

$(document).ready(function() {
    $('#searchById').on('input', function() {      
        $('#lNameId, #fNameId, #mNameId, #dateOfBirth, #ssn1, #ssn2, #ssn3').val('');
        $('#sexId').val('');
    });
});


function validateInput(){
	var msg = "";
	var nameRegExp = /^[a-zA-Z0-9 ]*$/;
	var numericRegExp = /^[0-9]*$/;
	var indx = 0;
	var result = "";
	
	if (!document.getElementById("searchById").value.trim() &&
	        !document.getElementById("lNameId").value.trim() &&
	        !document.getElementById("fNameId").value.trim() &&
	        !document.getElementById("mNameId").value.trim() &&
	        !document.getElementById("dateOfBirth").value.trim() &&
	        !document.getElementById("ssn1").value.trim() &&
	        !document.getElementById("ssn2").value.trim() &&
	        !document.getElementById("ssn3").value.trim() &&
	        !document.getElementById("sexId").value.trim() &&
	        !document.getElementById("licenseNum").value.trim()) {
	        alert("At least one search criteria is required.");
	        return false; // Prevent further actions
	    }
	
	var searchById = document.getElementById("searchById");

	if(Trim(searchById.value) != "")
    {
		if (!($.isNumeric(searchById.value))) {
			msg = "Family Member Id is invalid. Please retry search.";			
		}
		
		if (msg != "") {
			alert(msg)
			return false;
		}
		else
		{
		  return true;
		}		
    }


	var driverLicenseNum = document.getElementById("licenseNum");
	
	if(Trim(driverLicenseNum.value) != "")
    {
		if (!($.isNumeric(driverLicenseNum.value))) {
			msg = "Driver License Number is invalid. Please retry search.";			
		}
		
		if (msg != "") {
			alert(msg)
			return false;
		}
		else
		{
		  return true;
		}		
    }
	
	
	var fld1 = document.getElementById("lNameId");
	var val = Trim(fld1.value);	
	if (val != "") {
		indx = val.indexOf('*');		
		if (indx == 0 || indx == 1) {
			msg = "Member Last Name must be at least 2 valid characters before the *. \n";
			fld1.focus();
		}
		if (indx > 1){
			val = val.substring(0, val.length - 1);
			indx = -1;
		}
		if (indx == -1) {
			if (nameRegExp.test(val,nameRegExp) == false){
				msg = "Member Last Name must be alphabetic.\n";
				fld1.focus();
			}
		}
	}
	fld1 = document.getElementById("fNameId");
	val = Trim(fld1.value);
	if (val != "") {		
		indx = val.indexOf('*');
		if (indx > 1){
			val = val.substring(0, val.length - 1);
		}		
		if (nameRegExp.test(val,nameRegExp) == false){
			if (msg == ""){
				fld1.focus();
			}
			msg += "Member First Name must be alphabetic.\n";
		}
	}
	fld1 = document.getElementById("mNameId");
	val = Trim(fld1.value);
	if (val != "") {
		indx = val.indexOf('*');
		if (indx > 1){
			val = val.substring(0, val.length - 1);
		}
		if (nameRegExp.test(val,nameRegExp) == false){
			if (msg == ""){
				fld1.focus();
			}
			msg += "Member Middle Name must be alphabetic.\n";
		}
	}
	fld1 = document.getElementById("dateOfBirth");
	val = Trim(fld1.value);
	if (val != ""){
		result = validateDOB(fld1.value);
		alert(" " + result);
		if (result != ""){
			if (msg == ""){
				fld1.focus();
			}
			msg += result;
		}
	}
	fld1 = document.getElementById("ssn1");
	var fld2 = document.getElementById("ssn2");
	var fld3 = document.getElementById("ssn3");
	val = Trim(fld1.value) + Trim(fld2.value) + Trim(fld3.value);
	if (val > ""){
		if (val.length < 9){
			if (msg == ""){
				fld1.focus();
			}
			msg += "Social Security Number is invalid or incomplete.";
		} 
		if (val.length == 9){
			val = Trim(fld1.value);
			result = numericRegExp.test(val,numericRegExp);
			if (result == false){
				if (msg == ""){
					fld1.focus();
				}
				msg += "Social Security Number must be numeric value1.";
			} 
			if (result == true){
				val = Trim(fld2.value);
				result = numericRegExp.test(val,numericRegExp);
				if (result == false){
					if (msg == ""){
						fld2.focus();
					}
					msg += "Social Security Number must be numeric value2.";
				} 
			} 
			if (result == true){
				val = Trim(fld3.value);
				result = numericRegExp.test(val,numericRegExp);
				if (result == false){
					if (msg == ""){
						fld3.focus();
					}
					msg += "Social Security Number must be numeric value3.";
				} 
			}
		}
	} 
	if (msg == "") {
		spinner();
		return true;
	}	
	alert(msg)
	return false;
}

/* function validateDOB(fldValue){
	fldName = "Date of Birth";
	var msg = "";
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];
	var numericRegExp = /^[0-9]*$/;

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldName + " is not a valid date.\n";
		return msg;	
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;	
	} 

    if (month < 1 || month > 12) {
		msg = fldName + " has invalid month value.\n";
		return msg;		
    }
    if (day < 1 || day > 31) {
    	msg = fldName + " has invalid day value.\n";
		return msg;		
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
    	msg = fldName + " has invalid day value.\n";
		return msg;	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
        	msg = fldName + " has invalid day value.\n";
			return msg;	
        }
    }    
    if (msg == ""){
		var dt = fldValue + " 00:00";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime > curDateTime){
			msg = fldName + " can not be future value.\n";
			return msg;				
		}
    }
    return msg;
}  */
    
function setCursor()
{
	 document.getElementById("lNameId").focus();
}
</script>
</head>
<%--END HEADER TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="setCursor()">
<html:form action="/displayFamilyMemberLeftNavSearchResults"  >
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Family <bean:message key="prompt.member"/> <bean:message key="prompt.search"/></td>
		</tr>
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors>&nbsp;</td>		  
		</tr>   	  
	</table>
<%-- END HEADING TABLE --%>
	<div class="spacer" ></div>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="100%" border="0">
		<tr>
			<td>
				<ul>
					<li>Enter the search criteria and click Submit button.</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" /></td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN SEARCH TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead" valign="top"><bean:message key="prompt.searchFor"/> <bean:message key="prompt.members"/></td>
		</tr>
		<tr>
			<td>
				<table width="100%" cellspacing="1">
					<tr>
						<td class="formDeLabel" nowrap>
							<bean:message key="prompt.member" /><bean:message key="prompt.member"/> <bean:message key="prompt.lastName"/>
						</td>
						<td class="formDe">
							<html:text name="juvenileMemberSearchForm" property="name.lastName" size="30" maxlength="30" styleId="lNameId"/>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap>
							<bean:message key="prompt.member"/> <bean:message key="prompt.firstName"/>
						</td>
						<td class="formDe">
							<html:text name="juvenileMemberSearchForm" property="name.firstName" size="25" maxlength="25" styleId="fNameId"/>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap>
							<bean:message key="prompt.member"/> <bean:message key="prompt.middleName"/>
						</td>
						<td class="formDe">
							<html:text name="juvenileMemberSearchForm" property="name.middleName" size="25" maxlength="25" styleId="mNameId"/>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap><bean:message key="prompt.sex"/></td>
						<td class="formDe">
							<select name="sexId" id="sexId"><option value="">Please Select</option>
								<option value="FEMALE">FEMALE</option>
                                <option value="MALE">MALE</option>
                                <option value="UNKNOWN">UNKNOWN</option></select>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap><bean:message key="prompt.dateOfBirth"/></td>
						<td class="formDe"><html:text name="juvenileMemberSearchForm" property="dateOfBirth" styleId="dateOfBirth" size="10"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.ssn"/></td>
						<td class="formDe">
							<html:text name="juvenileMemberSearchForm" property="ssn.SSN1" size="3" maxlength="3" styleId ="ssn1" onkeyup="return autoTab(this, 3);" />
							<html:text name="juvenileMemberSearchForm" property="ssn.SSN2" size="2" maxlength="2" styleId ="ssn2" onkeyup="return autoTab(this, 2);" />
							<html:text name="juvenileMemberSearchForm" property="ssn.SSN3" size="4" maxlength="4" styleId ="ssn3" onkeyup="return autoTab(this, 4);" />
						</td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap>Family Member ID</td>
						<td class="formDe"><html:text name="juvenileMemberSearchForm" property="searchById" styleId="searchById" size="5"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" nowrap>Driver License Number</td>
						<td class="formDe"><html:text name="juvenileMemberSearchForm" property="driverLicenseNum" styleId="licenseNum" size="12"/></td>
					</tr>						
				</table>
			</td>
		</tr>
	</table>
<%-- END SEARCH TABLE --%>
	<div class=spacer></div>
<%-- BEGIN BUTTON TABLE --%>
	<table border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit onclick="return validateInput() && disableSubmit(this, this.form)" property="submitAction"><bean:message key="button.submit"/></html:submit> 
				<html:submit property="submitAction"><bean:message key="button.refresh" /></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%>
	<div class=spacer></div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>