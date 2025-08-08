<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/28/2008	 Mo Chowdhury - Create JSP -->
<!-- 04/09/2009	 RRY - Fix defect #57496 Task Test field accepts more than specified max -->
<!-- 04/09/2009	 RRY - Added double quote character to regex -->
<!-- 03/01/2010	 C Shimek  #64141 added max. length to case number (12 from none) and subject (50 from 75) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - posttrial/createTask.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript">
function validateInput(theForm){
	var msg = "";
	var result = false;
	var numericRegExp = /^[0-9]*$/;
	var alphaNumericRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 ]*$/;
	var alphaNumericWithSymbolsRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 './\\()\;\x26\-]*$/;
	var commentRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\x27\x3B\x22\x26\x2f\-]*$/;

	theForm.taskDueDateStr.value = trimAll(theForm.taskDueDateStr.value);
	theForm.taskCaseNumber.value = trimAll(theForm.taskCaseNumber.value);
	theForm.taskCdi.value = trimAll(theForm.taskCdi.value);
	
	if (theForm.taskDueDateStr.value > ""){
		result = isDateX(theForm.taskDueDateStr.value);
		if (result == false){
			msg = "Due Date is not valid date value. \n";
			theForm.taskDueDateStr.focus();
		} else {
			result = compareDate1GreaterEqualDate2(theForm.taskDueDateStr.value, getCurrentDate());
			if (result == false){
				msg = "Due Date must be current or future date. \n";
				theForm.taskDueDateStr.focus();
			}	
		}	
	}
	if (theForm.taskCaseNumber.value == ""){
		if (msg == ""){
		   theForm.taskCaseNumber.focus();
		}   
	   	msg += "Case# is required. \n";
	} else if (alphaNumericRegExp.test(theForm.taskCaseNumber.value,alphaNumericRegExp) == false) {
		if (msg == ""){
		   theForm.taskCaseNumber.focus();
		}   
	   	msg += "Case# must be alphanumeric. \n";
	}
	
	if (theForm.taskCdi.value == ""){
		if (msg == ""){
		   theForm.taskCdi.focus();
		}   
	   	msg += "CDI is required. \n";
	} else if (alphaNumericRegExp.test(theForm.taskCdi.value,alphaNumericRegExp) == false) {
		if (msg == ""){
		   theForm.taskCdi.focus();
		}   
	   	msg += "CDI must be alphanumeric. \n";
	}
	
	if (theForm.taskSubject.value == ""){
		if (msg == ""){
		   theForm.taskSubject.focus();
		}   
	   	msg += "Subject is required. \n";
	} else if (alphaNumericWithSymbolsRegExp.test(theForm.taskSubject.value,alphaNumericWithSymbolsRegExp) == false) {
		if (msg == ""){
		   theForm.taskSubject.focus();
		}   
	   	msg += "Subject contains invalid character. \n";
	}
	
	if (theForm.taskNextActionGroupId.selectedIndex == 0){
		if (msg == ""){
		   theForm.taskNextActionGroupId.focus();
		}   
	   	msg += "Next Action Group selection is required. \n";
	}
	
	if (theForm.taskNextActionId.selectedIndex == 0 && theForm.taskNextActionGroupId.selectedIndex > 0){
		if (msg == ""){
		   theForm.taskNextActionId.focus();
		}   
	   	msg += "Next Action selection is required. \n";
	}
	
	if (theForm.taskText.value == ""){
		if (msg == ""){
		   theForm.taskText.focus();
		}   
	   	msg += "Task Text is required. \n";
	}
	if (theForm.taskText.value.length > 1000){
		msg = "Task Text can not be greater than 1000 characters.\n";
		theForm.taskText.focus();
	} else if (theForm.taskText.value.length > 0) {
		if (commentRegExp.test(theForm.taskText.value,commentRegExp) == false) {
			msg += "Task Text contains invalid character or begins with space.\n";
			theForm.taskText.focus();
		}
	}
	
	if (msg != ""){
		alert(msg);
		return false;
	} 
	return true;       
}
//isDate function in common_supervision_util.js would always return false, even on valid date
// isDateX is a copy of isDate in common_supervision_util.js and works as expected
function isDateX(value) {
	// To require a 2 digits day, 2 digits month, 4 digits year and no dash entry, use this line:
	 var datePat = /^(\d{2})(\/)(\d{2})\2(\d{4})$/;
	
	var matchArray = value.match(datePat); // is the format ok?
	if (value != "")
		{	
			if (matchArray == null) {
				return false;
			}
			month = matchArray[1]; // parse date into variables
			day = matchArray[3];
			year = matchArray[4];
			if (month < 1 || month > 12) { // check month range
				return false;
			}
			if (day < 1 || day > 31) {
				return false;
			}
			if ((month==4 || month==6 || month==9 || month==11) && day==31) {
				return false
			}
			if (month == 2) { // check for february 29th
				var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
				if (day>29 || (day==29 && !isleap)) {
					return false;
				 }
			}
	}
	return true;  // date is valid
	}

// FUNCTION TO FILTER NEXT ACTION GROUPS  -->
function filterNextActionSet()
{
	var group1Id = document.forms[0].taskNextActionGroupId.options[ document.forms[0].taskNextActionGroupId.selectedIndex].value;
	var group2IdSelect = "";
	if (group1Id > "")
	{
		updateGroup2(document.forms[0]);
		if (document.forms[0].taskNextActionId.options.length > 1)
		{
			group2IdSelect = document.forms[0].group2IdSelect.value;
			for (x = 0; x < document.forms[0].taskNextActionId.options.length; x++)
			{
				if (document.forms[0].taskNextActionId.options[x].value == group2IdSelect)
				{
					document.forms[0].taskNextActionId.selectedIndex = x;
					break;
				}
			}
		}
	}
}

function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.subgroups = new Array();
}

var groups = new Array(); //the array of group1
function updateGroup2(theForm)
{
	var group1Id = theForm.taskNextActionGroupId.options[theForm.taskNextActionGroupId.selectedIndex].value;
	theForm.taskNextActionId.options.length = 0;
	theForm.taskNextActionId.options[0] = new Option( "Please Select", "", false, false );
	if(theForm.taskNextActionGroupId.selectedIndex == 0)
	{
		theForm.taskNextActionId.selectedIndex = 0; //reset choice back to default
		theForm.taskNextActionId.value="";
		theForm.taskNextActionId.disabled = true; //disable group2 choice
		
		return;
	}
	else
	{
		for(i in groups)
		{
			if(groups[i].id == group1Id)
			{
				for(j in groups[i].subgroups)
				{
					//alert(groups[i].subgroups[j].id+":"+groups[i].subgroups[j].name);
					theForm.taskNextActionId.options[theForm.taskNextActionId.options.length] = new Option( groups[i].subgroups[j].name, groups[i].subgroups[j].id);
				}
			}
		}
		if(theForm.taskNextActionId.options.length>1){
			theForm.taskNextActionId.disabled = false;
			theForm.taskNextActionId.value="";
		}
		else{
			theForm.taskNextActionId.selectedIndex = 0; //reset choice back to default
			theForm.taskNextActionId.value="";
			theForm.taskNextActionId.disabled = true;
		}
	}	
}

<logic:iterate indexId="groupIterIndex" id="groupIter" name="cscdTaskForm" property="taskNextActionGroups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="parentAction" />", "<bean:write name="groupIter" property="parentAction" filter="false" />");
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="nextActions">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate>
</script>
</head>
<body topmargin="0" leftmargin="0" onload="filterNextActionSet()" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCreateTaskSummary" target="content" focus="taskDueDateStr">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|8">
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<tiles:insert
								page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="tasksTab" />
							</tiles:insert>
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END BLUE TABS TABLE -->	
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
									<td align="center" class="header">
										<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.task"/>
									</td>
								</tr>
							</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors /></td>
								</tr>
							</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Enter Required fields. Click Next.</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td class="required" colspan="2"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction" />  <bean:message key="prompt.dateFieldsInstruction"/></td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->
							<SCRIPT LANGUAGE="JavaScript" ID="js1">
									var cal1 = new CalendarPopup();
									cal1.showYearNavigation();
							</SCRIPT>
<!-- BEGIN DETAIL TABLE -->
							<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.taskInfo" /></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="4" cellspacing="1">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.to"/></td>
												<td class="formDe"><bean:write name="cscdTaskForm" property="taskTo" /></td>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.dueDate" /></td>
												<td class="formDe">
													<html:text name="cscdTaskForm" property="taskDueDateStr" size="11" maxlength="10" />
														<a href="#" onClick="cal1.select(cscdTaskForm.taskDueDateStr,'anchorX','MM/dd/yyyy'); return false;"
															NAME="anchorX" ID="anchorX"><bean:message key="prompt.2.calendar"/></a> 
												</td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.case#"/></td>
												<td class="formDe"><html:text name="cscdTaskForm" property="taskCaseNumber" size="13" maxlength="12"/></td>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.CDI"/></td>
												<td class="formDe"><html:text name="cscdTaskForm" property="taskCdi" size="5" maxlength="3"/></td>
											</tr>   
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.subject" /></td>
												<td class="formDe" colspan="3"><html:text name="cscdTaskForm" property="taskSubject" size="52" maxlength="50"/></td>									
											</tr>
											<tr>
											    <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.severityLevel" /></td>
											    <td class="formDe" colspan="3">
											 	    <html:select name="cscdTaskForm" property="taskSeverityLevelId">
													    <html:option value=""><bean:message key="select.generic"/></html:option>
														<html:optionsCollection name="cscdTaskForm" property="taskSeverityLevels" value="code" label="description"/>
												    </html:select>	 																									
											    </td>
											</tr>
											<tr>
											    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.nextAction" /> Group</td>
											    <td class="formDe" colspan="3">
											 	    <html:select name="cscdTaskForm" property="taskNextActionGroupId" size="1" onchange="updateGroup2(this.form);" >
													    <html:option value=""><bean:message key="select.generic"/></html:option>
														<html:optionsCollection property="taskNextActionGroups" value="parentAction" label="parentAction"/>
												    </html:select>	 																									
											    </td>
											</tr>
											<tr>
											    <td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.nextAction" /></td>
											    <td class="formDe" colspan="3">
													<logic:empty name="cscdTaskForm" property="taskNextActions">
														<html:select property="taskNextActionId" disabled="true">
															<html:option value=""><bean:message key="select.generic" /></html:option>
														</html:select>
													</logic:empty>
													<input type="hidden" name="group2IdSelect" value="<bean:write name="cscdTaskForm" property="taskNextActionId" />" >
											    </td>
											</tr>
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.taskText" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan="4">
													<html:textarea name="cscdTaskForm" property="taskText" style="width:100%" rows="5"></html:textarea>
												</td>	
											</tr>
										</table>
									</td>
								</tr>
							</table>
<!-- END DETAIL TABLE -->
							<br>
<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back" /></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="return validateInput(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next" /></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>
							</table>
<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
<!-- BEGIN BLUE BORDER TABLE -->					
				<br>
			</td>
		</tr>
	</table>
</div>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
