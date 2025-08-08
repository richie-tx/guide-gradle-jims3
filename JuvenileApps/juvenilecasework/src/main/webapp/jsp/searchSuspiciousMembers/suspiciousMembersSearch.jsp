<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- LShimek		05/09/2005	Create JSP --%>
<%-- RCapestani 10/15/2015  Task #30777 MJCW: IE11 conversion of "Data Admin-HCJPD"  link on UILeftNav (UI-Suspicious Members) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - suspiciousMembersSearch.jsp</title>

<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type='text/javascript'>
function setSearchBy()
{
	var sbId = document.getElementById("SearchBy");
	for (x=0; x<sbId.options.length; x++)
	{
		if (sbId.options[x].value == document.forms[0].selectedSearchTypeId.value)
		{
			sbId.selectedIndex = x;
		}		
	}		
	evalSearch(sbId,"N");
}

function evalSearch(el, clrInd)
{
	var fld = "";
	if (el.options[el.options.selectedIndex].value == "FAM")
	{	
		showHide('familyMembeSearch', 1);
		showHide('familySearchReq', 1);
		showHide('juvenileSearch', 0);
		showHide('juvenileSearchReq', 0);
		fld = document.getElementsByName("lastName");
	} else {
		showHide('familyMembeSearch', 0);
		showHide('familySearchReq', 0);
		showHide('juvenileSearch', 1);
		showHide('juvenileSearchReq', 1);
		fld = document.getElementsByName("juvenileNumber");
	}	
	fld[0].focus();
	if (clrInd == "Y")
 	for(var i = 0; i < document.forms[0].length; i++)
 	{
  		if( document.forms[0].elements[i].type == 'text')
   		{
   		 document.forms[0].elements[i].value = "";
   		}
	}
 	document.forms[0].selectedSearchTypeId.value = el.options[el.options.selectedIndex].value;
}

function validateSearch(el)
{	
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	var lNameRegExp = /^([a-zA-Z0-9]{1})([a-zA-Z0-9']{1})([a-zA-Z0-9 \/\.\\'\()\x26\-]*)([*]?)$/;
	var fNameRegExp = /^([a-zA-Z0-9]{1})([a-zA-Z0-9 \/\.\\'\()\x26\-]*)([*]?)$/;
	var fldValue1 = "";
	var fldValue2 = "";
	var fldValue3 = "";	
	var fldValue4 = "";
	var fldValue5 = "";
	var fldValue6 = "";	
	var searchType = el.searchTypeId.options[el.searchTypeId.selectedIndex].value ;
	
	if( searchType == "FAM")
	{
		var lName = document.getElementsByName("lastName");
		var fName = document.getElementsByName("firstName");
		var ssn1x = document.getElementsByName("ssn1");	
		var ssn2x = document.getElementsByName("ssn2");	
		var ssn3x = document.getElementsByName("ssn3");	
		var ssn9 = document.getElementsByName("socialSecurityNumber");	// hidden field
		var famMemID = document.getElementsByName("searchMemberAId");
		ssn9[0].value = "";	
		fldValue6 = Trim(famMemID[0].value);
		fldValue1 = Trim(lName[0].value);
		fldValue2 = Trim(fName[0].value);
		if (fldValue1 == "" && fldValue2 > "")
		{
			msg = "Last Name is required to use first name for search.\n";
			lName[0].focus();
		}
		if (fldValue1 > "")
		{
			if (lNameRegExp.test(fldValue1,lNameRegExp) == false)
			{
				msg = "Last Name must be alphanumeric with no leading spaces.  The following symbols -'./&\\\\() are allowed after the first alphanumeric character.\n"; 
				lName[0].focus();
			}	
		}
		if (fldValue2 > "")
		{
			if (fNameRegExp.test(fldValue2,fNameRegExp) == false)
			{
				if (msg == "")
				{
					fName[0].focus();
				}		
				msg += "Last Name must be alphanumeric with no leading spaces.  The following symbols -'./&\\\\() are allowed after the first alphanumeric character.\n"; 
			}	
		}
		fldValue3 = Trim(ssn1x[0].value);
		fldValue4 = Trim(ssn2x[0].value);
		fldValue5 = Trim(ssn3x[0].value);
		if (fldValue3 > "" || fldValue4 > "" ||  fldValue5 > "")
		{
			var len = fldValue3.length + fldValue4.length + fldValue5.length;
			if (len < 9)
			{
				if (msg == "")
				{
					ssn1x[0].focus();
				}		
				msg += "Social Security Number requires all 9 digits for search."
			} 
			if (len == 9)
			{	
				if (numericRegExp.test(fldValue3,numericRegExp) == false || 
					numericRegExp.test(fldValue4,numericRegExp) == false || 
					numericRegExp.test(fldValue5,numericRegExp) == false )
					{
					if (msg == "")
					{
						ssn1x[0].focus();
					}
					msg += "Social Security Number must be numeric.\n"; 
				}		
				ssn9[0].value = fldValue3 + fldValue4 + fldValue5;	
				if (ssn9[0].value == "666666666" || ssn9[0].value == "777777777" ||
					ssn9[0].value == "888888888" || ssn9[0].value == "999999999")
				{	
					if (msg == "")
					{
						ssn1x[0].focus();
					}
					msg += "Social Security Number entered is not valid for Suspicious Member search.\n"; 
				 	
				}
			}	
		}	
		var x = fldValue1 + fldValue2 + fldValue3 + fldValue4 + fldValue5 + fldValue6;
		if (x == "")
		{	
			msg = "At least 1 search value required.";
			lName[0].focus();
		}				

	} else {
		var jNum = document.getElementsByName("juvenileNumber");		
		fldValue1 = Trim(jNum[0].value);
		if (fldValue1 == "")
		{
			msg = "Juvenile Number is required for search.";
		} else {
			if (numericRegExp.test(fldValue1,numericRegExp) == false )
			{
				msg += "Juvenile Number must be numeric.";
			}
		}
		if (msg != "")
		{
			jNum[0].focus();
		}							
	}
	if (msg == "")
	{
		return true;
	}
	alert(msg);
	return false;		
}

//imitates the reset button by clearing the text fields only
//param: theForm - the form object 
function refreshPage(el) 
{
 	for(var i = 0; i < el.length; i++)
 	{
   		if(el.elements[i].type == 'text')
   		{
     		el.elements[i].value = "";
   		}
	}
	var sbType = document.forms[0].selectedSearchTypeId.value;
	if (sbType == "FAM")
	{
		sbType = "lastName";
	} else {	
		sbType = "juvenileNumber"
	}
	var fldName = document.getElementsByName(sbType);
	fldName[0].focus();		
}

/* function autoTabSMS( input,len ) 
{
	if( input.value.length >= len )
	{
		input.value = input.value.slice( 0, len );
	  	if (input.name == 'ssn1')
	  	{		  	
	  		document.getElementsByName("ssn2")(0).focus();
	  	}
	  	if (input.name == 'ssn2')
	  	{		  	
	  		document.getElementsByName("ssn3")(0).focus();
	  	}	
	  	if (input.name == 'ssn3')
	  	{  	
		  	document.getElementById("lnId").focus();
	  	}  		
	}
	return true;
} */

function autoTab( input,len ) 
{
	if( input.value.length >= len )
	{
	  input.value = input.value.slice( 0, len );
	  input.form[( getIndex( input )+1 ) % input.form.length ].focus( );
	}

	return true;
}

</script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="setSearchBy()">
<html:form action="/displaySuspiciousMembersSearchResults" target="content" focus="searchTypeId">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">
<input type="hidden" name="selectedSearchTypeId" value="<bean:write name='suspiciousMemberForm' property="searchTypeId"/>" >
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center"><bean:message key="prompt.suspiciousMembers" /> - <bean:message key="prompt.search" /></td>
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
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Search By to change the search type.</li>
					<li>Enter search value(s) and click Submit to see results.</li>
				</ul>
			</td>
		</tr>
		<tr id="juvenileSearchReq" class="hidden">
			<td class="required"><bean:message key="prompt.2.diamond" />Required Fields</td>
		</tr>
		<tr id="familySearchReq" class="visible">
			<td class="required">
				At least one search field is required.&nbsp;&nbsp;&nbsp;
				+ Indicates Last Name is required to use this search field.
			</td>
		</tr>  
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN SEARCH BY TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.searchBy" /></td>
			<td class="formDe">
				<html:select property="searchTypeId" styleId="SearchBy" onchange="evalSearch(this,'Y')" >
					<option value="FAM">FAMILY MEMBER</option>
					<option value="JUV">JUVENILE</option>
				</html:select>
			</td>
		</tr>
	</table>
<%-- END SEARCH BY TABLE --%>	
	<div class="spacer4px"></div>
	<table width="100%" id="familyMembeSearch" cellpadding="0" cellspacing="0" class='visible'>
		<tr><td>
<%-- BEGIN SEARCH BY FANILY MEMBER --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.lastName" /></td>
				<td class="formDe"><html:text property="lastName" styleId="lnId" size="65" maxlength="75" /></td>
			</tr>
			<tr>
				<td class="formDeLabel">+<bean:message key="prompt.firstName" /></td>
				<td class="formDe"><html:text property="firstName" styleId="fnId"  size="50" maxlength="50" /></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.socialSecurityNumber" /></td>
				<td class="formDe">
					<html:text name='suspiciousMemberForm' property="ssn1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>-  
					<html:text name='suspiciousMemberForm' property="ssn2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>-
					<html:text name='suspiciousMemberForm' property="ssn3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>  
					<html:hidden name='suspiciousMemberForm' property="socialSecurityNumber" />
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.familyMember"/> ID</td>
				<td class="formDe"><html:text property="searchMemberAId" styleId="searchFamId1"  size="10" maxlength="15" /></td>
			</tr>
		</table>
<%-- END SEARCH BY FANILY MEMBER --%>
		</td></tr>		
	</table>
	<table width="100%" id="juvenileSearch" cellpadding="0" cellspacing="0" class='hidden'>
		<tr><td>
<%-- BEGIN SEARCH BY JUVENILE NUMBER --%>	
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.juvenileNumber" /></td>
				<td class="formDe"><html:text property="juvenileNumber" size="8" maxlength="8" /></td>
			</tr>
		</table>
<%-- END SEARCH BY JUVENILE NUMBER --%>
		</td></tr>		
	</table>
	<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" onclick="return validateSearch(this.form) && disableSubmit(this, this.form);" styleId="submitButton">
					<bean:message key="button.submit" />
				</html:submit>
				<input type="button" onclick="refreshPage(this.form)" value="<bean:message key='button.refresh'/>" /> 
			</td>
		</tr>
		<logic:equal name="suspiciousMemberForm" property="noMembersFound" value="true">
		<tr>
			<td><input type="button" onclick="goNav('/<msp:webapp/>displaySuspiciousMembersSearchResults.do?submitAction=Link')" value="<bean:message key='button.connectSuspiciousMember'/>"/>&nbsp;</td>
		</tr>
		</logic:equal>
	</table>
<%-- END BUTTON TABLE --%> 
</html:form>
<%-- END FORM --%>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>