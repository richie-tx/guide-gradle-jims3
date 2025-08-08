<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/26/2009	 CShimek        - Create JSP -->

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
<title><bean:message key="title.heading" /> - posttrial/caseAssignmentDataControlOfficer.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript">
function validateInputs(theForm){
	var msg = validateDate(theForm.positionAssignmentDateStr.value , "Position Assignment Date", true);
	if (msg != ""){
		theForm.positionAssignmentDateStr.focus(); 
	}
// only in update, input date can not be previous to any other assignment date	
	if (msg == ""){
		if (theForm.pgMode.value == "<%=UIConstants.UPDATE%>" ){
			var inDateFields = theForm.positionAssignmentDateStr.value.split("/");
			var inMM = inDateFields[0];
			var inDD = inDateFields[1]
   			var inYR = inDateFields[2];
			var inDate = new Date(inYR, inMM - 1, inDD);
			var laDateFields = theForm.compareDate.value.split("/");
			var laMM = laDateFields[0];
			var laDD = laDateFields[1]
   			var laYR = laDateFields[2];
			var laDate = new Date(laYR, laMM - 1, laDD);
			if (inDate < laDate){
				msg = "Position Assignment Date can not be previous to latest assignment date " + theForm.compareDate.value + ".\n";
				theForm.positionAssignmentDateStr.focus(); 
			}	
		}	 
	}	
	if (theForm.selectedOfficerId.selectedIndex == 0){
		if (msg == ""){
			theForm.selectedOfficerId.focus();
		}	
		msg += "Officer selection required.";
	}		
	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;	
}
function validateDate(fldValue, fldName, futureDateEdit)
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
		msg = fldName + " is not in the proper format.\n";
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
function setOfficerSelect(){
	var selOffIds = document.getElementsByName("selectedOfficerId");
	var currOffId = document.getElementsByName("theOfficerId");
	if (selOffIds.length > 0 && currOffId.length > 0){
		for (x = 0;  x <selOffIds[0].options.length; x++){
			if (selOffIds[0].options[x].value == currOffId[0].value){
				selOffIds[0].options[x].selected = true;
			}	
		}	
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setOfficerSelect()">
<html:form action="/displayCaseAssignmentDataControlSummary" target="content" focus="positionAssignmentDateStr">
<input type="hidden" name="helpFile" value="">
<input type="hidden" name="pgMode" value="<bean:write name='caseAssignmentDataControlForm' property='secondaryAction'/>" >
<input type="hidden"  name="compareDate" value="<bean:write name='caseAssignmentDataControlForm' property='latestPositionAssignmentDate' formatKey="date.format.mmddyyyy" />" size="30" /> 
<input type="hidden"  name="theOfficerId" value="<bean:write name='caseAssignmentDataControlForm' property='currentOfficerId'  />" />
<div align="center">  
<!-- Begin Pagination Header Tag -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
<!-- BEGIN TAB TABLE -->    	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>  
<!-- END TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">CSCD -
								<logic:equal name="caseAssignmentDataControlForm" property="secondaryAction" value="<%=UIConstants.UPDATE%>">
									<bean:message key="prompt.update"/>
								</logic:equal>	
								<logic:equal name="caseAssignmentDataControlForm" property="secondaryAction" value="<%=UIConstants.CORRECT%>">
									<bean:message key="prompt.correct"/>
								</logic:equal>	
								<bean:message key="prompt.caseAssignment"/> - <bean:message key="prompt.officer"/></td>						
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
								<td>
									<ul>
										<li>Enter required field(s). Click Next.</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.requiredFields"/></td>												
							</tr>
						</table>
<!-- END INSTRUCTION TABLE -->	
<!-- BEGIN SUPERVISEE INFO TABLE -->    	
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top" align="center">
									<tiles:insert page="../common/caseAssignmentDataControlHeader.jsp" flush="true"></tiles:insert> 		
								</td>
							</tr>
						</table>  
<!-- END SUPERVISEE INFO TABLE -->	
						<div class="spacer4px"></div>	
						<script type="text/javascript" ID="js1">
							var cal1 = new CalendarPopup();
							cal1.showYearNavigation();
						</script>																			
<!-- BEGIN PGM UNIT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /> <bean:message key="prompt.date" /></td>
											<td class="formDe"><bean:write name="caseAssignmentDataControlForm" property="pgmUnitAssignmentDateStr"/></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.division" /> / <bean:message key="prompt.programUnit" /></td>
											<td class="formDe"><bean:write name="caseAssignmentDataControlForm" property="divisionPgmUnitDesc"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>  
<!-- END PGM UNIT TABLE -->
						<div class="spacer4px"></div>
<!-- BEGIN OFFICER ASSIGNMENT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead"><bean:message key="prompt.officer" /> <bean:message key="prompt.assignment" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.position" /> <bean:message key="prompt.assignment" /> <bean:message key="prompt.date" /></td>
											<td>
												<html:text name="caseAssignmentDataControlForm" property="positionAssignmentDateStr" maxlength="10" size="10" />
												<A HREF="#" onClick="cal1.select(document.getElementById('positionAssignmentDateStr'),'anchor1','MM/dd/yyyy'); return false;"
					                                NAME="anchor1" ID="anchor1"><bean:message key="prompt.2.calendar"/></A>&nbsp;
											</td> 
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.officer" /></td>
											<td>
											    <html:select name="caseAssignmentDataControlForm" property="selectedOfficerId">
							                	    <html:option value=""><bean:message key="select.generic" /></html:option>
							                    	<html:optionsCollection name="caseAssignmentDataControlForm" property="officerList" value="staffPositionId" label="displayLiteral" />
							                	</html:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>  
<!-- END OFFICER ASSIGNMENT TABLE -->
			
<!-- BEGIN BUTTON TABLE  -->
			 			<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="return validateInputs(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table> 
<!-- END BUTTON TABLE2 -->
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>