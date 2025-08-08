<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose	Create JSP -->
<!-- 08/28/2006  H Rodriguez  	ER#34507 Implement new UI Guidelines for all date fields -->
<!-- 05/13/2008  L Deen		  	Removed CSCD from page title  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - posttrial/advancedSuperviseeSearch.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="advSuperviseeSearchForm" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script type="text/javascript">
function validateSearch(theForm)
{	
	if (theForm["superviseeName.lastName"].value == "" &&
		theForm["superviseeName.firstName"].value == "" &&
		theForm["superviseeName.middleName"].value == "" &&
		theForm.dateOfBirthAsStr.value == "" &&
		theForm.raceId.value == "" &&
		theForm.sexId.value == "" &&
		theForm["ssn.ssn1"].value == "" &&
		theForm["ssn.ssn2"].value == "" &&
		theForm["ssn.ssn3"].value == "" &&
		theForm.dlStateId.value == "" &&
		theForm.dlNum.value == "" &&
		theForm.SID.value == "" &&
		theForm.FBI.value == "" &&
	    theForm.CJIS.value == "")
	{		
     	alert("At least 1 search field is required for search.");
     	theForm["superviseeName.lastName"].focus();
     	return false;
   	}
   	
    if (theForm["superviseeName.lastName"].value == ""){
   		if (theForm["superviseeName.firstName"].value > "" ||
   			theForm["superviseeName.middleName"].value > "" ||
   			theForm.dateOfBirthAsStr.value > "" ||
   			theForm.raceId.value > "" ||
 			theForm.sexId.value > "")
      	{		
	        alert("Last Name is required for this section.");
		    theForm["superviseeName.lastName"].focus();
		    return false;
      	}      	
    }
    if(theForm["superviseeName.lastName"].value!=null && theForm["superviseeName.lastName"].value!="" && theForm["superviseeName.lastName"].value.length <2){
    	 alert("Last Name must be at least 2 characters long.");
		 theForm["superviseeName.lastName"].focus();
    	return false;
    }
    if(theForm["superviseeName.firstName"].value!=null && theForm["superviseeName.firstName"].value!="" && theForm["superviseeName.firstName"].value.length <2){
    	 alert("First Name must be at least 2 characters long.");
		 theForm["superviseeName.firstName"].focus();
    	return false;
    }
    
    if (theForm["ssn.ssn1"].value == ""){ 
   		if (theForm["ssn.ssn2"].value > "" ||
 			theForm["ssn.ssn3"].value > "")
      	{		
	        alert("First(3) Social Security is required for this section.");
		    theForm["ssn.ssn1"].focus();
		    return false;
      	}      	
    }
    if (theForm["ssn.ssn2"].value == ""){
   		if (theForm["ssn.ssn1"].value > "" ||
 			theForm["ssn.ssn3"].value > "")
      	{		
	        alert("Middle(2) Social Security is required for this section.");
		    theForm["ssn.ssn2"].focus();
		    return false;
      	}      	
    }
    if (theForm["ssn.ssn3"].value == ""){
   		if (theForm["ssn.ssn1"].value > "" ||
 			theForm["ssn.ssn2"].value > "")
      	{		
	        alert("Last(4) Social Security is required for this section.");
		    theForm["ssn.ssn3"].focus();
		    return false;
      	}      	
    }
    
    if (theForm.dlStateId.value > "" && theForm.dlNum.value == "")
  	{		
        alert("Driver's License Number is required for search when Driver's License State is selected.");
	    theForm.dlNum.focus();
	    return false;
  	}
  	if (theForm.dlStateId.value == "" && theForm.dlNum.value > "")
  	{		
        alert("Driver's License State is required for search when Driver's License Number is entered.");
	    theForm.dlStateId.focus();
	    return false;
  	}
    
   	return true;
}
</script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySuperviseeSearchResults" target="content" focus="superviseeName.lastName">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|2">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
			height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header">
										CSCD - <bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.advancedSearch" /></td>
						        </tr>
							</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>You may search using 1 or more sections.</li>
									</ul></td>
								</tr>
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN SEARCH TABLE -->
							<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="required" colspan="4"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredForThisSection" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
								</tr>
								<tr>
									<td class="detailHead" colspan="4"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.supervisee" />&nbsp;&nbsp;<a href="/<msp:webapp/>displaySuperviseeSearch.do?submitAction=Link"><bean:message key="button.basicSearch"/></a></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.lastName" /></td>
									<td class="formDe" colspan="3"><html:text property="superviseeName.lastName" maxlength="75" size="30" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.firstName" /></td>
									<td class="formDe" colspan="3"><html:text property="superviseeName.firstName" maxlength="50" size="30" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.middleName" /></td>
									<td class="formDe" colspan="3"><html:text property="superviseeName.middleName" maxlength="50" size="30" /></td>
								</tr>
								
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.dateOfBirth" /></td>
									<td class="formDe" colspan="3">
										<SCRIPT LANGUAGE="JavaScript" ID="js1">
											var cal1 = new CalendarPopup();
											cal1.showYearNavigation();
										</SCRIPT>
										<html:text name="superviseeSearchForm" property="dateOfBirthAsStr" maxlength="10" size="10" />
										<A HREF="#" onClick="cal1.select(document.forms[0].dateOfBirthAsStr,'anchor1','MM/dd/yyyy'); return false;"
											NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.race" /></td>
									<td class="formDe" colspan="3"><html:select property="raceId" size="1">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="superviseeSearchForm" property="raceList" value="code" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
									<td class="formDe" colspan="3"><html:select property="sexId" size="1">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="superviseeSearchForm" property="sexList" value="code" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td class="required" colspan="4"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredForThisSection" /></td>
								</tr>
								<tr>
									<td class="detailHead" colspan="4"><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.ssn" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.ssn" /></td>
									<td class="formDe" colspan="3"><html:text property="ssn.ssn1" maxlength="3" size="3"></html:text> - <html:text
										property="ssn.ssn2" maxlength="2" size="2"></html:text> - <html:text
										property="ssn.ssn3" maxlength="4" size="4">
									</html:text></td>
								</tr>
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td class="required" colspan="4"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredForThisSection" /></td>
								</tr>
								<tr>
									<td class="detailHead" colspan="4"><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.driverLicense" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.driverLicense" />&nbsp;<bean:message key="prompt.state" /></td>
									<td class="formDe" colspan="3"><html:select property="dlStateId" size="1">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="superviseeSearchForm" property="dlStateList" value="code" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.driverLicense" />&nbsp;<bean:message key="prompt.number" /></td>
									<td class="formDe" colspan="3"><html:text property="dlNum" maxlength="10" size="10" /></td>
								</tr>
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td class="required" colspan="4"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.atLeastOneRequiredForThisSection" /></td>
								</tr>
								<tr>
									<td class="detailHead" colspan="4"><bean:message key="prompt.searchByID" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.SID#" /></td>
									<td class="formDe" colspan="3"><html:text property="SID" maxlength="10" size="10" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.FBI#" /></td>
									<td class="formDe" colspan="3"><html:text property="FBI" maxlength="10" size="10" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.CJIS#" /></td>
									<td class="formDe" colspan="3"><html:text property="CJIS" maxlength="14" size="14" /></td>
								</tr>
							</table>
							<input type="hidden" name="selectedValue" value="<%=UIConstants.ADVANCED%>"/>
						<!-- END SEARCH TABLE -->
							<br>
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return (validateSearch(this.form) && validateAdvSuperviseeSearchForm(this.form) && disableSubmit(this,this.form))"><bean:message key="button.submit"/></html:submit>
										<html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
										<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>
							</table>
						<!-- END BUTTON TABLE -->
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
