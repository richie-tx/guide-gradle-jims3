<!DOCTYPE HTML>

<%-- Modified version of familyMemberCreate page --%>
<%--MODIFICATIONS --%>
<%-- 10/05/2011	 CShimek	Create JSP --%>
<%-- 01/30/2012	 CShimek	Removed Marital Status input block and validation script, also enabled decease select toggle --%>
<%-- RCapestani 10/15/2015  Task #30777 MJCW: IE11 conversion of "Data Admin-HCJPD"  link on UILeftNav (UI-Suspicious Members) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 

<script type="text/javascript">

$(function() {

    $( "#memDOB" ).datepicker({
      changeMonth: true,
      changeYear: true,
    });
    $( "#dlExpDate" ).datepicker({
        changeMonth: true,
        changeYear: true,
      });
});
function toggleDeceased()
{
	var btns = document.getElementsByName("deceased");
	if(btns[0].checked == true) // Yes is selected
	{
		document.suspiciousMemberForm["causeOfDeathId"].disabled = false;
	}
	else
	{
		document.suspiciousMemberForm["causeOfDeathId"].value="";
		document.suspiciousMemberForm["causeOfDeathId"].disabled = true;
	}
}

function newWindow() 
{
	document.suspiciousMemberForm["memberSSN1"].focus();
	msgWindow = open("/<msp:webapp/>jsp/searchSuspiciousMembers/suspiciousMembersUpdatePopup.jsp","matchingMembersPopupWindow",'resizable=yes, scrollbar=yes, width=800,height=400');
    if (msgWindow.opener == null) 
		  msgWindow.opener = self;
}

function validateInputs(theForm)
{
	var msg = ""; 
	var numericRegExp = /^[0-9]*$/;
// name validate	
	theForm.memberLastName.value = Trim(theForm.memberLastName.value);
	var result = validateName(theForm.memberLastName.value,"Last name",true,2);
	if (result != "")
	{	
		msg = result;
		theForm.memberLastName.focus();
	}
	theForm.memberFirstName.value = Trim(theForm.memberFirstName.value);
	var result = validateName(theForm.memberFirstName.value,"First name",true,2);
	if (result != "")
	{
		if (msg == "")
		{
			theForm.memberFirstName.focus();
		}			
		msg += result;
	}
	theForm.memberMiddleName.value = Trim(theForm.memberMiddleName.value);
	if (theForm.memberMiddleName.value != "") 
	{		
		var result = validateName(theForm.memberMiddleName.value,"Middle name",false,1);
		if (result != "")
		{
			if (msg == "")
			{
				theForm.memberMiddleName.focus();
			}			
			msg += result;
		}
	}	
// ssn validate
	var ssn1 = Trim(theForm.memberSSN1.value);
	var ssn2 = Trim(theForm.memberSSN2.value);
	var ssn3 = Trim(theForm.memberSSN3.value);
	result = ssn1 + ssn2 + ssn3; 
	if (result == "")
	{
		if (msg == "")
		{
			theForm.memberSSN1.focus();
		}			
		msg += "Social Security # is required.\n";
	}
	if (result != "" && result.length < 9)
	{
		if (msg == "")
		{
			theForm.memberSSN1.focus();
		}			
		msg += "Complete Social Security # is required.\n";	
	}		
	if (result.length == 9)
	{
		if (numericRegExp.test(ssn1,numericRegExp) == false )
		{
			if (msg == "")
			{
				theForm.memberSSN1.focus();
			}			
			msg += "Social Security # must be numeric.\n";	
		} else if ( numericRegExp.test(ssn2,numericRegExp) == false)
			{
				if (msg == "")
				{
					theForm.memberSSN2.focus();
				}			
				msg += "Social Security # must be numeric.\n";	
			} else if (numericRegExp.test(ssn3,numericRegExp) == false)
				{
					if (msg == "")
					{
						theForm.memberSSN3.focus();
					}			
					msg += "Social Security # must be numeric.\n";	
				}
	}				

// DOB validate
	if (theForm.memberDOB.value > "")
	{	
		var dob = theForm.memberDOB.value
		result = validateDate(dob, "Date of Birth", true);
		if (result == "")
		{
			var cntry = dob.substring(6,8);
			if (cntry < 19)
			{	
 	    		if (msg == ""){
	    			theForm.memberDOB.focus();
	    		}	
  	    		msg += "Date of Birth year cannot be less than 1900.";
			}		
		} else {
			if (msg == ""){
    			theForm.memberDOB.focus();
    		}
			msg += result;
		}			
	}
// State Id validate
	if (theForm.sidNum.value > "")
	{
		theForm.sidNum.value = Trim(theForm.sidNum.value); 	
		if (numericRegExp.test(theForm.sidNum.value,numericRegExp) == false)
		{
			if (msg == "")
			{
			theForm.sidNum.focus();
			}			
			msg += "State ID # must be numeric.\n";	
		} else {
			result = TrimZeroes(theForm.sidNum.value);
			if (result == "")
			{
				if (msg == "")
				{
					theForm.sidNum.focus();
				}			
				msg += "State ID # can not be all zeroes.\n";	
			}		
		}	
	}		
// Alien Reg validate
	if (theForm.alienRegNumber.value > "")
	{
		theForm.alienRegNumber.value = Trim(theForm.alienRegNumber.value); 	
		if (numericRegExp.test(theForm.alienRegNumber.value,numericRegExp) == false)
		{
			if (msg == "")
			{
				theForm.alienRegNumber.focus();
			}			
			msg += "Alien Reg.# must be numeric.\n";	
		} else {
			result = TrimZeroes(theForm.alienRegNumber.value);
			if (result == "" && theForm.alienRegNumber.value > "")
			{
				if (msg == "")
				{
					theForm.alienRegNumber.focus();
				}			
				msg += "Alien Reg.# can not be all zeroes.\n";	
			}		
		}	
	}
// deceased validate
	var result = document.getElementsByName("deceased");
	if (result[0].checked == true)
	{
		if (theForm.causeOfDeathId.selectedIndex == 0)
		{
			if (msg == "")
			{
				theForm.causeOfDeathId.focus();
			}			
			msg += "Cause of Death selection required when Family Memeber Deceased equal 'Yes'.\n";	
		}			
	}		
// driver license validates
	theForm.dlNumber.value = Trim(theForm.dlNumber.value);
	theForm.dlExpDateStr.value = Trim(theForm.dlExpDateStr.value); 
	if (theForm.dlNumber.value > "" || theForm.dlStateId.selectedIndex > 0 ||
		theForm.dlExpDateStr.value > "" || theForm.dlClassId.selectedIndex > 0)
		{
			if (theForm.dlNumber.value == "")
			{
				if (msg == "")
				{
					theForm.dlNumber.focus();
				}			
				msg += "Driver License Number is required to complete Driver License information.\n";				
			} else {
				if (numericRegExp.test(theForm.dlNumber.value,numericRegExp) == false)
				{
					if (msg == "")
					{
						theForm.dlNumber.focus();
					}			
					msg += "Driver License number must be numeric.\n";		
				} else {
					result = TrimZeroes(theForm.dlNumber.value);
					if (result == "")
					{
						if (msg == "")
						{
							theForm.alienRegNumber.focus();
						}			
						msg += "Driver License number can not be all zeroes.\n";	
					}		
				}	
			}			
			if (theForm.dlStateId.selectedIndex == 0)
			{
				if (msg == "")
				{
					theForm.dlStateId.focus();
				}			
				msg += "Driver License State selection is required to complete Driver License information.\n";				
			}
			if (theForm.dlExpDateStr.value == "")
			{
				if (msg == "")
				{
					theForm.dlExpDateStr.focus();
				}			
				msg += "Driver License Expiration Date is required to complete Driver License information.\n";				
			} else {
				result = validateDate(theForm.dlExpDateStr.value, "Driver License Expiration Date",false)
				if (result != "")
				{
					if (msg == "")
					{
						theForm.dlExpDateStr.focus();
					}			
					msg += result;		
				}		
			}				
			if (theForm.dlClassId.selectedIndex == 0)
			{
				if (msg == "")
				{
					theForm.dlClassId.focus();
				}			
				msg += "Driver License Class selection is required to complete Driver License information.\n";				
			}				
		}	
	theForm.stateIssuedCardNumber.value = Trim(theForm.stateIssuedCardNumber.value); 
	if (theForm.stateIssuedCardNumber.value > "")
	{	
		if (numericRegExp.test(theForm.stateIssuedCardNumber.value,numericRegExp) == false)
		{
			if (msg == "")
			{
				theForm.stateIssuedCardNumber.focus();
			}			
			msg += "State Issued Id Card# must be numeric.\n";
		} else {
			result = TrimZeroes(theForm.stateIssuedCardNumber.value);
			if (result == "")
			{
				if (msg == "")
				{
					theForm.stateIssuedCardNumber.focus();
				}			
				msg += "State Issued Id Card# can not be all zeroes.\n";	
			}	
		}	
	}			
// relationship to juvenile validate

// marital status validates
			
// end of validates
	if (msg == "")
	{			
		return true;
	}
	alert(msg);
	return false;	
}

function validateName(fldValue,fldName,req,minLen)
{
	var nameRegExp = /[a-zA-Z0-9][a-zA-Z0-9 \/_\.\\'\-]*$/;
	var msg = "";
	var fld = Trim(fldValue);
	if (fld == "" && req == true)
	{	
		msg = fldName + " is required.\n";
		return msg;
	}
	if (fld.length < minLen)	
	{	
		msg = fldName + " must be at least " + minLen + " characters.\n";
		return msg;
	}
	if (nameRegExp.test(fldValue,nameRegExp) == false)
	{
		msg = fldName + " must be alphanumeric.\n";
		return msg;	
	}	
	return msg;
}

function validateDate(fldValue, fldName, futureDateEdit)
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
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
    if (futureDateEdit && msg == ""){
		var dt = fldValue + " 00:00";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime > curDateTime){
			msg = fldName + " can not be future value.\n";
			return msg;				
		}    	
    }
 	return msg;
}

function textLimit(field, maxlen)
{
	if (field.value.length > maxlen)
	{
		field.value = field.value.substring(0, maxlen);
		alert("Your input has been truncated to " + maxlen +" characters!");	
	}
}
function TrimZeroes(s)
{
	while( (s.substring( 0,1 ) == '0') )
	{ 
		s = s.substring( 1, s.length ); 
	}
	return s;
}
</script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<title><bean:message key="title.heading" /> - suspiciousMembersUpdate.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);"> 
<html:form action="/displaySuspiciousMembersUpdateSummary">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|00">
<%--<input type="hidden" name="popupWindow" id="popupId" value="<bean:write name="suspiciousMemberForm" property="popup" />" >
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.familyMember" /> <bean:message key="prompt.update" /></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
  		<ul>
  			<li>Complete fields and click Next button to view summary.</li>
  			<li>Select the Spell Check icon for the Family Member Comments field to check spelling.</li>
   			<li>Select Back button to return to the previous page. </li>
  		</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTUCTION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN FAMILY INFO INPUT TABLE --%>
<table width="98%" align="center" border="0" cellpadding="2"  cellspacing="1" class="borderTableBlue">
	<tr>
		<td colspan="4" valign="top" class="detailHead">
			<bean:message key="prompt.familyMember" /> <bean:message key="prompt.info" /> 
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.name" /></td>
		<td class="formDe" colspan="3">
<%-- BEGIN FAMILY MEMBER NAME INNER TABLE  --%>
			<table border="0" cellspacing="1">
				<tr>
					<td class="formDeLabel" colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
				</tr>
				<tr>		
					<td class="formDe" colspan='2'>
					<html:text name="suspiciousMemberForm" property="memberLastName" size="30" maxlength="75" styleId="focusId"/>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
					<td class="formDeLabel"><bean:message key="prompt.middle" /></td>
				</tr>
				<tr>		
					<td class="formDe"><html:text name="suspiciousMemberForm" property="memberFirstName" size="25" maxlength="50"/></td>
					<td class="formDe"><html:text name="suspiciousMemberForm" property="memberMiddleName" size="25" maxlength="50"/></td>
				</tr>
			</table>
<%-- END FAMILY MEMBER NAME INNER TABLE  --%>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.socialSecurity#" /></td>
		<td class="formDe">
			<html:text name="suspiciousMemberForm" property="memberSSN1" styleId="ssn1Id" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> 
			<html:text name="suspiciousMemberForm" property="memberSSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" /> 
			<html:text name="suspiciousMemberForm" property="memberSSN3" maxlength="4" size="4" onkeyup="return autoTab(this, 4);" />
		</td>
		<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.dateOfBirth" /></td>
		<td class="formDe">
			<html:text name="suspiciousMemberForm" property="memberDOB" maxlength="10" size="10" styleId="memDOB" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
		<td class="formDe" colspan="3">
			<html:select name="suspiciousMemberForm" property="sexId" size="1">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="sexList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>	
		<td class="formDeLabel"><bean:message key="prompt.stateidSID" /></td>
		<td class="formDe"><html:text name="suspiciousMemberForm" property="sidNum" size="10" maxlength="10" /></td>
		<td class="formDeLabel"><bean:message key="prompt.alienReg#" /></td>
		<td class="formDe"><html:text name="suspiciousMemberForm" property="alienRegNumber" size="8" maxlength="8"></html:text></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.usCitizen" /></td>
		<td class="formDe" colspan="3">
			<html:select size="1" name="suspiciousMemberForm" property="usCitizenId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="usCitizenList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.nationality" /></td>		
		<td class="formDe" colspan="3">
			<html:select size="1" name="suspiciousMemberForm" property="nationalityId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="nationalityList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>		
		<td class="formDeLabel"><bean:message key="prompt.ethnicity" /></td>
		<td class="formDe" colspan="3">
			<html:select size="1" name="suspiciousMemberForm" property="ethnicityId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="ethnicityList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.primaryLanguage" /></td>
		<td class="formDe" colspan="3">
			<html:select size="1" name="suspiciousMemberForm" property="primaryLanguageId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="languageList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>	
		<td class="formDeLabel"><bean:message key="prompt.secondaryLanguage" /></td>
		<td class="formDe" colspan="3">
			<html:select name="suspiciousMemberForm" size="1" property="secondaryLanguageId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="languageList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.isFamilyMemberDeceased" /></td>
		<td class="formDe" colspan='3'>Yes <html:radio name="suspiciousMemberForm" property="deceased" onclick="toggleDeceased();" value="true" /> No <html:radio name="suspiciousMemberForm" property="deceased" value="false" onclick="toggleDeceased();"/></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.causeOfDeath" /></td>
		<td class="formDe" colspan='3'>
			<html:select property="causeOfDeathId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="causeOfDeathList" value="code" label="description" />
			</html:select>
		</td>	
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.isFamilyMemberIncarcerated" /></td>
		<td class="formDe" colspan='3'>Yes <html:radio name="suspiciousMemberForm" property="incarcerated" value="true" /> No <html:radio name="suspiciousMemberForm" property="incarcerated" value="false" /></td>
	</tr>
 	<script type='text/javascript'>toggleDeceased();</script>  
	<tr>
		<td class="formDeLabel" colspan="4" valign="top"><bean:message key="prompt.family" /> <bean:message key="prompt.member" /> <bean:message key="prompt.comments" />&nbsp;
			<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
				<tiles:put name="tTextField" value="comments"/>
				<tiles:put name="tSpellCount" value="spellBtn1" />
			</tiles:insert> 
			(Max. characters allowed: 255)
		</td>
	</tr>
	<tr>
		<td class="formDe" colspan="4"><html:textarea name="suspiciousMemberForm" property="comments" onmouseout="textLimit(this, 255);" onkeyup="textLimit(this, 255);"  cols="40" rows="2" style="width:100%" /></td>
	</tr>
</table>
<%-- END FAMILY INFO INPUT TABLE --%>			
	<div class='spacer4px'></div>
<%-- BEGIN DRIVER LICENSE/ID CARD INFORMATION --%>
<table width='98%'> 	
	<tr>
		<td class="required">+ All Driver's License information is required if any portion of D.L. information is entered.</td>
	</tr>
</table>		
<table width="98%" align="center" border="0" cellpadding="2"  cellspacing="1" class="borderTableBlue">
	<tr>
		<td colspan='4' class="detailHead"><bean:message key="prompt.driverLicense"/>/<bean:message key="prompt.idCardInfo" /></td>
	</tr>
	<tr>	
		<td class="formDeLabel">+<bean:message key="prompt.number" /></td>
		<td class="formDe"><html:text name="suspiciousMemberForm" property="dlNumber" size="8" maxlength="8" /></td>
		<td class="formDeLabel">+<bean:message key="prompt.state" /></td>
		<td class="formDe"><html:select name="suspiciousMemberForm" property="dlStateId" size="1">
			<option value="">Please Select</option>
			<html:optionsCollection name="suspiciousMemberForm" property="stateList" value="code" label="description" />
		</html:select></td>
	</tr>
	<tr>	
		<td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.expirationDate" /></td>
		<td class='formDe'>
			<html:text name="suspiciousMemberForm" property="dlExpDateStr" size="10" maxlength="10" styleId="dlExpDate" />
		</td>
		<td class='formDeLabel' nowrap='nowrap'>+<bean:message key="prompt.class" /></td>
		<td class='formDe'>
			<html:select property="dlClassId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection property="dlClassList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap='nowrap' width="1%">
			<bean:message key="prompt.state" /> <bean:message key="prompt.issued" /> <bean:message key="prompt.idCard#" />
		</td>
		<td class="formDe"><html:text name="suspiciousMemberForm" property="stateIssuedCardNumber" size="20" maxlength="20"/></td>
		<td class="formDeLabel"><bean:message key="prompt.state" /> </td>
		<td class="formDe">
			<html:select name="suspiciousMemberForm" property="stateIssuedCardStateId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="suspiciousMemberForm" property="stateList" value="code" label="description" />
			</html:select>
		</td>
	</tr>
</table>
<%-- END DRIVER LICENSE/ID CARD INFORMATION --%>
 	<div class='spacer4px'></div>
<%-- BEGIN ASSOCIATED JUVENILE LIST TABLE --%> 	
<table width='98%' align="center" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td class='detailHead' colspan="5"><bean:message key="prompt.associatedJuvenilesList" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.juvenile#" /></td>
		<td class="formDeLabel"><bean:message key="prompt.family#" /></td>
		<td class="formDeLabel"><bean:message key="prompt.juvenileName" /></td>
		<td class="formDeLabel"><bean:message key="prompt.relationship" /> to Juvenile</td>
		<td class="formDeLabel"><bean:message key="prompt.gender" /></td>
	</tr>
	<logic:empty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
		<tr>
			<td>No Associated Juveniles found.</td>
		</tr>
	</logic:empty> 
	<logic:notEmpty  name="suspiciousMemberForm" property="associatedJuvenilesList" >
		<logic:iterate id="ajList" name="suspiciousMemberForm" property="associatedJuvenilesList" indexId="index1">
			<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				<td><bean:write name="ajList" property="juvId" /></td>
				<td><bean:write name="ajList" property="famMemberId" /></td>
				<td><bean:write name="ajList" property="juvName" /></td>
				<td><bean:write name="ajList" property="relationType" /></td>
				<td><bean:write name="ajList" property="sexDesc" /></td>
			</tr>
		</logic:iterate>
	</logic:notEmpty>
</table>
<%-- END ASSOCIATED JUVENILE LIST TABLE --%> 				
 	<div class='spacer4px'></div>
<%-- BEGIN MARITAL STATUS TABLE --%>
<%-- END MARITAL STATUS TABLE --%>			
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction">
				<bean:message key="button.back"></bean:message>
			</html:submit> 
			<html:submit  property="submitAction" onclick="return validateInputs(this.form) && disableSubmit(this, this.form)">
				<bean:message key="button.next"></bean:message>
			</html:submit> 
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE  --%>	
<logic:equal name="suspiciousMemberForm" property="popup" value="true">
	<script  type='text/javascript'>
		newWindow();
	</script>
</logic:equal>
<logic:notEqual name="suspiciousMemberForm" property="popup" value="true">
	<script type='text/javascript'>
		document.getElementsByName('memberLastName')[0].focus();
	</script>
</logic:notEqual>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>