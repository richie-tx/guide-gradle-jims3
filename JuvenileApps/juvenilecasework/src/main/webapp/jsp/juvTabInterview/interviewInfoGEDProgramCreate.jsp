<!DOCTYPE HTML>
<%-- User clicks the "Add GED Program button on School History page --%>
<%--MODIFICATIONS --%>
<%-- 10/09/2012	C Shimek		Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ include file="../jQuery.fw" %>




<%--BEGIN HEADER TAG--%>
<head>
<html:javascript formName="juvenileSchoolHistoryForm" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/> - interviewInfoGEDProgramCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">

function displayDependentFlds()
{
	var fld1 = document.getElementById("pgmId");
	if (fld1.options[fld1.selectedIndex].value == "OTHR") {
		document.getElementById("otherPgmId").className = "visible";
	}
	if (document.getElementsByName("awarded")[0].checked == true) {
		document.getElementById("awardDateId").className = "visible";
	}
}

function validateInputs()
{
	var msg = "";
	var val = "";
	var result = "";
	var enrollDateValid = false;
	var fld1 = document.getElementById("pgmId");
	
	if (fld1.selectedIndex == 0) {
		
		msg = "GED Program selection is required.\n";
		fld1.focus();
		
	} else {
		if (fld1.options[fld1.selectedIndex].value == "OTHR") {
			fld2 = document.getElementById("otherPgmDescId");
			val = trim(fld2.value);
			if (val == "") {
				msg = "Other GED Program is required for 'OTHER' GED Program selection.\n"
				fld1.focus();
			}	
		} else {
			document.getElementById("otherPgmDescId").value = "";
		}	
	}
	
	fld1 = document.getElementById("enrollmentDateStr");
	
	if (fld1.value == "") {
		if (msg == "") {
			fld1.focus();
		}	
		msg += "Enrollment Date is required.\n"
	} else {
		result = validateDate("Enrollment Date", fld1.value);
		if (result > "") {
			if (msg == "") 	{
				fld1.focus();
			}
			msg += result;
		} else {
			enrollDateValid = true;
		}	
	}
	
	fld2 = document.getElementById("verifiedDate");
	val = trim(fld2.value);
	
	if (val != ""){
		result = validateDate("Verified Date", fld2.value);
		if (result > "") {
			if (msg == "") 	{
				fld2.focus();
			}
			msg += result;
		} else {
			if (enrollDateValid == true){
				var dt1 = val + " 00:00";
				var fldDateTime = new Date(dt1);
				var dt2 = fld1.value + " 00:00";
				var enrollDateTime = new Date(dt2);
				if (enrollDateTime > fldDateTime){
					msg += "Verified Date can not be previous to Enrollment Date.\n";
				}
			}
		}
	}
	fld2 = document.getElementById("completionDate");
	val = trim(fld2.value);
	if (val > ""){
		result = validateDate("Completion Date", fld2.value);
		if (result > "") {
			if (msg == "") 	{
				fld2.focus();
			}
			msg += result;
		} else {
			if (enrollDateValid == true){
				var dt1 = val + " 00:00";
				var fldDateTime = new Date(dt1);
				var dt2 = fld1.value + " 00:00";
				var enrollDateTime = new Date(dt2);
				if (enrollDateTime > fldDateTime){
					msg += "Completion Date can not be previous to Enrollment Date.\n";
				}
			}
		}
	}
	
	if (document.getElementsByName("awarded")[0].checked == true){
		fld2 = document.getElementById("awardDateStrId");
		val = trim(fld2.value);
		if (val > ""){
			result = validateDate("GED Awarded Date", fld2.value);
			if (result > "") {
				if (msg == "") 	{
					fld2.focus();
				}
				msg += result;
			} else {
				if (enrollDateValid == true){
					var dt1 = val + " 00:00";
					var fldDateTime = new Date(dt1);
					var dt2 = fld1.value + " 00:00";
					var enrollDateTime = new Date(dt2);
					if (enrollDateTime > fldDateTime){
						msg += "GED Awarded Date can not be previous to Enrollment Date.\n";
					}
				}
			}
		}	
	}
// end of edits	
	if (msg == "") {
		return true;
	}
	alert(msg);
	return false;
}

function otherSelectCheck(sel)
{
	show("otherPgmId", 0);
	if (sel.options[sel.selectedIndex].value == "OTHR")
	{
		show("otherPgmId", 1);
		document.getElementById("otherPgmDescId").focus();
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

function refreshPage(){
	document.getElementById("enrollmentDateStr").value = "";
	document.getElementById("verifiedDate").value = "";
	document.getElementById("enrollStatusId").selectedIndex="";
	document.getElementById("pLevelId").selectedIndex="";
	document.getElementById("pgmId").selectedIndex="";
	document.getElementById("otherPgmDescId").value = "";
	document.getElementById("otherPgmId").className = "hidden";
	document.getElementById("completionDate").value = "";
	document.getElementById("awardDateStrId").value = "";
	document.getElementById("completionDateId").value = "";
	document.getElementsByName("awarded")[0].checked = false;
	document.getElementsByName("awarded")[1].checked = true;
	document.getElementById("enrollmentDateStr").focus();
}

function validateDate(fldName, fldValue)
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
	var dt = fldValue + " 00:00";
	var fldDateTime = new Date(dt);
	var curDateTime = new Date();
	if (fldDateTime > curDateTime){
		msg = fldName + " can not be future value.\n";
	}	
 	return msg;
}


</script>  

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="displayDependentFlds();">
<html:form action="/displayGEDProgramSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|">

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
										<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
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
<%-- BEGIN GED INPUT TABLE --%>
												<table width='100%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr>
														<td colspan="2" class="detailHead"><bean:message key="prompt.GEDProgram" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.GEDProgram" /></td>
														<td class="formDe">
															<html:select name="juvenileGEDProgramForm" property="programId" styleId="pgmId" onchange="otherSelectCheck(this)">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="programs" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
					    							<tr id="otherPgmId" class="hidden">	
					    								<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.other" /> <bean:message key="prompt.GEDProgram" /></td>
					    								<td class="formDe"><html:text name="juvenileGEDProgramForm" size="50" maxlength="50" property="otherProgramDesc" styleId="otherPgmDescId"/></td>												
					    							</tr>
													<tr>								
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.enrollmentDate" /></td>
														<td class="formDe">
															<html:text name="juvenileGEDProgramForm" size="10" maxlength="10" property="enrollmentDateStr" styleId="enrollmentDateStr"/>
														</td>												
													</tr>	
													<tr>	
														<td class="formDeLabel"><bean:message key="prompt.verifiedDate" /></td>
														<td class="formDe"><html:text name="juvenileGEDProgramForm" size="10" maxlength="10" property="verificationDateStr" styleId="verifiedDate" />								
													</td>												
													</tr>																								
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.enrollmentStatus" /></td>	
														<td class="formDe">
															<html:select name="juvenileGEDProgramForm" property="enrollmentStatusId" styleId="enrollStatusId">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="enrollmentStatuses" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>							
													<tr>
														<!-- extra spaces added to this prompt to maintain alignment when Other GED Program is visible -->	
														<td class="formDeLabel" width="15%" nowrap="nowrap"><bean:message key="prompt.participation" /> <bean:message key="prompt.level" />&nbsp;&nbsp;&nbsp;&nbsp;</td>							
														<td class="formDe" valign="top">
															<html:select name="juvenileGEDProgramForm" property="participationLevelId" styleId="pLevelId">
																<html:option key="select.generic" value="" />
																<html:optionsCollection property="participationLevels" value="code" label="description"/>				
															</html:select>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel">Has the youth completed their <bean:message key="prompt.GED" />?</td>
														<td class="formDe" nowrap="nowrap">
															<bean:message key="prompt.yes" /><html:radio name="juvenileGEDProgramForm" property="gedCompleted" value="true" onclick="showHideCompletionDate('visible');" />
															<bean:message key="prompt.no" /><html:radio name="juvenileGEDProgramForm" property="gedCompleted" value="false" onclick="showHideCompletionDate('hidden');" />
														</td>												
													</tr>
					    							<tr id="completionId" class="hidden">
														<td class="formDeLabel"><bean:message key="prompt.completionDate" /></td>
														<td class="formDe">
															<html:text name="juvenileGEDProgramForm" size="10" maxlength="10" property="completionDateStr" styleId="completionDate"/>
														</td>												
													</tr>
											 		<tr>
														<td class="formDeLabel"><bean:message key="prompt.GEDAwarded" /></td>
														<td class="formDe">
															<bean:message key="prompt.yes" /><html:radio name="juvenileGEDProgramForm" property="awarded" value="true" onclick="showHideAwardDate('visible');" />
															<bean:message key="prompt.no" /><html:radio name="juvenileGEDProgramForm" property="awarded" value="false" onclick="showHideAwardDate('hidden');" />
														</td>												
													</tr>
					    							<tr id="awardDateId" class="hidden">									
														<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.GEDAwarded" /> <bean:message key="prompt.date" /></td>
														<td class="formDe">
															<html:text name="juvenileGEDProgramForm" size="10" maxlength="10" property="awardedDateStr" styleId="awardDateStrId"/>
														</td>
													</tr>																															

								                </table>
<%-- END GED INPUT TABLE --%>		
												<div class="spacer"></div>			
<%-- BEGIN BUTTON TABLE --%>
												<table width="98%">
													<tr>
														<td align="center">
															<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
																<bean:message key="button.back" />
															</html:submit> 
															<html:submit property="submitAction" onclick="return validateInputs() && disableSubmit(this, this.form)">
																<bean:message key="button.next"></bean:message>
															</html:submit> 
															<input type="button" name="reset" value="<bean:message key='button.refresh'/>" onclick="refreshPage()" />
															<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
																<bean:message key="button.cancel" />
															</html:submit>
														</td>
													</tr>
												</table>
<%-- END BUTTON TABLE --%>												
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