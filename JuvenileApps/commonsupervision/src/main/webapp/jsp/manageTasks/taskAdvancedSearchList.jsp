<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/22/2007	 Hien Rodriguez - Create JSP -->
<!-- 09/10/2007	 Young - Add show hide options for advanced search -->
<!-- 09/20/2007	 Young - Removed the navigation buttons from this jsp.Are on the details page-->
<!-- 10/12/2007	 Young - Removed extra alerts-->
<!-- 01/29/2009	 Young -(JIMS200055932) Officer drop down list not loaded with correct data-->
<!-- 05/18/2009	 Young -(59578) CS Tasks - Advance query not working -->
<!-- 01/04/2010  C Shimek - #63123 revised workgroup, task status and severity level to multi select. Also added resetDate() js to prevent
     BeanUtil.populate error on Refresh button and other minor js validation tweaks -->
<!-- 02/05/2010	 RYoung  - #63176 CS Tasks - Omit Division field unless user is cleared -->
<!-- 03/08/2010	 RYoung  - #63176 CS Tasks - Allow supervisor and Officer to continue to show -->
<!-- 05/28/2010	 RYoung  - #65716 CS Tasks - Hide Transfer Task Button Temporarily -->
<!-- 06/14/2010	 RYoung  - #65955 CS Task Query Improvements -->
<!-- 07/30/2010  CShimke - Corrected small issue of searching by SPN value only with status select = Please Select  -->
<!-- 11/02/2010  RYoung  - #65801 default task status to Submitted for advanced search  -->
                        
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.PDCodeTableConstants" %>
<%@page import="naming.Features"%>

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
<title><bean:message key="title.heading" /> - manageTasks/taskAdvancedSearchList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script>
function onLoadCheck(){
	var theLength = document.getElementsByName('TaskId').length;
	for (var i = 0; i < theLength; i++) {
		if (document.getElementsByName('TaskId')[i].checked){
			var el = document.getElementsByName('TaskId')[i];			
		}
	}

	if (el){
		toggleButtons(el)
	}
	var offset = document.forms[0]['tasklistTypeId'].selectedIndex ;
	var taskListTypeSelect = document.forms[0]['tasklistTypeId'].options[ offset ].value;
}

function toggleButtons(el){
	
	if (el.checked){

		<jims2:isAllowed requiredFeatures="<%=Features.CSCD_TASKS_CLOSE_ADV%>">		
			<bean:define id="featureToClose" type="java.lang.String">
			canCloseAnything	
		</bean:define>
		</jims2:isAllowed>
		
		//checking the TD to see if it has an ID - b/c if it does not, there is no next action for this task 	
		<logic:equal name="featureToClose" value="">

		if ( el.parentElement.id == "" ) {
			show("closeButton", 1, "inline");
			
		}else{
		
			show("closeButton", 0, "inline");
		}
		</logic:equal>
		
		<logic:equal name="featureToClose" value="canCloseAnything">

			show("closeButton", 1, "inline");

		</logic:equal>
		
		switch (el.taskStatus)
		
		{	
			case "A":
			show("continueButton", 1, "inline");
			//show("transferButton", 1, "inline");
			show("acceptButton", 0);
			break
			
			case "C":
			show("closeButton", 0, "inline");
			show("continueButton", 0, "inline");
			show("taskDetailsButton", 1, "inline");
			show("acceptButton", 0);
			break
			
			case "S":
			//show("transferButton", 1, "inline");
			show("taskDetailsButton", 1, "inline");
			show("acceptButton", 1, "inline");
			break
			
			case "O":
			//show("transferButton", 1, "inline");
			show("taskDetailsButton", 1, "inline");
			show("acceptButton", 1, "inline");
			break
			
		}
	}
}

function resetForm(theSelect){
	theSelect.selectedIndex = 0
	checkWG(theSelect);
}

function checkWG(){
	
	var offset = document.forms[0]['tasklistTypeId'].selectedIndex ;
	var textSelection = document.forms[0]['tasklistTypeId'].options[ offset ].value;
	document.forms[0].taskStatusIds.selectedIndex = 0;

	if(textSelection == "SP"){
		show("spnRow", 1, "row");
		show("workGroupRow", 0, "row");
		show("divisionRow", 0, "row");
		show("supervisorRow", 0, "row");
		show("officerRow", 0, "row");
		document.forms[0].staffPositionId.selectedIndex = 0;
		document.forms[0].organizationId.selectedIndex = 0;
		document.forms[0].officerStaffId.selectedIndex = 0;
		document.forms[0].taskStatusIds.selectedIndex = 3;
		document.forms[0].workgroupIds.selectedIndex = 0;
		document.getElementById("spn").focus();
	}else
		
	if(textSelection == "WG"){
		show("spnRow", 0, "row");
		show("workGroupRow", 1, "row");
		show("divisionRow", 0, "row");
		show("supervisorRow", 0, "row");
		show("officerRow", 0, "row");
		document.forms[0].staffPositionId.selectedIndex = 0;
		document.forms[0].organizationId.selectedIndex = 0;
		document.forms[0].officerStaffId.selectedIndex = 0;
		document.forms[0].taskStatusIds.selectedIndex = 3;
		document.getElementById("spn").value = "";
	}else{
		document.getElementById("spn").value = "";
		<jims2:isAllowed requiredFeatures="<%=Features.CSCD_TASKS_VIEW_ADV%>">		
			<bean:define id="featureViewDivisions" type="java.lang.String">
				canViewAnything	
			</bean:define>
		</jims2:isAllowed>
	
		show("workGroupRow", 0, "row");
		show("spnRow", 0, "row");
		
		<logic:equal name="featureViewDivisions" value="canViewAnything">
			show("divisionRow", 1, "row");
		</logic:equal>
			show("supervisorRow", 1, "row");
			show("officerRow", 1, "row");
		
		document.forms[0].workgroupIds.selectedIndex = 0;
		document.forms[0].taskStatusIds.selectedIndex = 3;
	}
}

function checkAdvancedSearchCriteria(el){

	if(document.getElementById("tasklistTypeId").value == "SP"){
	
		//Test empty values
		var SPN = document.getElementById("spn");
		
		if (SPN.value == "" ){
				alert("An 8-digit numeric SPN is required for Tasklist Advanced Search.");
				document.getElementById("spn").focus();
				return false;
		}
		
		//Test for regular expressions. Validating for numeric characters
		var isInteger = /^[0-9]*$/;
					
		if (isInteger.test(SPN.value,isInteger) == false ){
		        alert("An 8-digit numeric SPN is required for Tasklist Advanced Search.");
		        document.getElementById("spn").focus();
				return false;
		}
	}
	
	if (document.getElementById("createDateAsString").value == "" &&
	    document.getElementById("createDate2AsString").value == "" &&
    	document.getElementById("dueDateAsString").value == "" &&
		document.getElementById("dueDate2AsString").value == ""&&
		document.getElementById("court").value == "" &&
		checkSelect("taskStatusIds") == "N"){
			if(document.getElementById("tasklistTypeId").value == "AL"){
				if (document.forms[0].organizationId.selectedIndex == 0 &&
					document.forms[0].staffPositionId.selectedIndex == 0 &&
					document.forms[0].officerStaffId.selectedIndex == 0	){
						alert("At least one search criteria is required.");
						return false;
				}
			} else {
				if (checkSelect("workgroupIds") == "N" && document.getElementById("tasklistTypeId").value != "SP"){
					alert("At least one search criteria is required 2");
					return false;
				}	
			}	
				
		}	

	if(document.getElementById("createDate2AsString").value!="" || document.getElementById("createDateAsString").value!=""){
		customValRequired("createDate2AsString", "End Create Date is required");
		customValRequired("createDateAsString", "Begin Create Date is required");
		addMMDDYYYYDateValidation("createDate2AsString", "End Create Date must be in the format MM/DD/YYYY");
		addMMDDYYYYDateValidation("createDateAsString", "Begin Create Date must be in the format MM/DD/YYYY");
			if(!validateCustomStrutsBasedJS(el)){
				return false;
			}	
			if(!compareDate1GreaterEqualDate2(document.getElementById("createDate2AsString").value,document.getElementById("createDateAsString").value)){
				alert("Begin Create Date must be less than or equal to end date");
				document.getElementById("createDateAsString").focus();
				return false;
			}
	}
		
	if(document.getElementById("dueDate2AsString").value!="" || document.getElementById("dueDateAsString").value!=""){
		customValRequired("dueDate2AsString", "End Due Date is required");
		customValRequired("dueDateAsString", "Begin Due Date is required");
		addMMDDYYYYDateValidation("dueDate2AsString", "End Due Date must be in the format MM/DD/YYYY");
		addMMDDYYYYDateValidation("dueDateAsString", "Begin Due Date must be in the format MM/DD/YYYY");
			if(!validateCustomStrutsBasedJS(el)){
				return false;
			}	
			if(!compareDate1GreaterEqualDate2(document.getElementById("dueDate2AsString").value,document.getElementById("dueDateAsString").value)){
				alert("Begin Due Date must be less than or equal to end date");
				document.getElementById("dueDateAsString").focus();
				return false;
			}
		}
		<%--clearAllValArrays();--%>
		if(document.getElementById("organizationId").value!=""){
			var theGenerElem=document.getElementById("staffPositionId");
			var theGenerVal=theGenerElem.options[theGenerElem.selectedIndex].value;
			if(theGenerVal==""){
				alert("A Supervisor must be selected.");
				return false;	
			}
		} 

// special coding to handle STRUTS issue on multiple select, no clearing previous selection if user deselect value(s)
		document.forms[0].workgroupIdSelected.value = checkSelect("workgroupIds");
		document.forms[0].taskStatusIdSelected.value = checkSelect("taskStatusIds");
//** end special coding
		return true;
	}

function checkSelect(fldName){
	 var fldIds = document.getElementById(fldName);
	 for (x = 0; x < fldIds.options.length; x++){
		if (fldIds.options[x].selected == true && x > 0){
			return "Y";
			break;
		}	
	 }
	 return "N";	
}

function resetSupervisorOfficer(theForm){
	theForm.staffPositionId.options.length = 1;
	theForm.officerStaffId.options.length = 1;
}

function resetOfficerStaff(theForm){
	theForm.officerStaffId.options.length = 1;
}

function checkDivisionSelection(){
	if(document.forms[0].organizationId.selectedIndex == 0){
		alert("A Division must be selected.");
		return false;	
	}
}

function checkSupervisorSelection(){
	if(document.forms[0].staffPositionId.selectedIndex == 0){
		alert("A Supervisor must be selected.");
		return false;	
	}
}
// this function necessary to prevent BeanUtils:populate error if dates contain invalid data
function clearDates(theForm){
	theForm.createDateAsString.value = "";
	theForm.createDate2AsString.value = "";
	theForm.dueDateAsString.value = ""; 
	theForm.dueDate2AsString.value = "";

}

function checkForSingleResult() {
    var rbs = document.getElementsByName("TaskId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}

</script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkWG(); checkForSingleResult(); onLoadCheck();">

	<div align="center">
<!-- BEGIN MAIN BODY TABLE --> 	
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN TAB TABLE --> 			
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="tasksTab" />
						</tiles:insert>
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END TAB TABLE --> 						
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
								<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.advancedSearch" />&nbsp;<bean:message key="prompt.tasks" /></td>
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
							<td><ul>
								<li>Enter at least 1 search criteria and click Submit.</li>
								</ul>
							</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE --> 
					<!-- BEGIN SELECT SEARCH TABLE -->
					<html:form action="/handleTaskSearch" target="content" focus="tasklistTypeId">
					<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|19">
					<input type="hidden" name="workgroupIdSelected" value="" />
					<input type="hidden" name="taskStatusIdSelected" value="" />
<!-- BEGIN BLUE BORDER FOR SEARCH TABLE -->					
					<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr>
							<td class="detailHead" colspan="2"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.tasks" />
							&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>handleTaskSearch.do?submitAction=<bean:message key="button.basicSearch"/>"><bean:message key="button.basicSearch" /></a></td>
						</tr>
						<tr>
							<td>
<!-- BEGIN SEARCH TABLE -->							
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.tasklistType" /></td>
									<td class="formDe" colspan="3">
										<html:select property="tasklistTypeId" size="1" onchange="checkWG(this)">
											<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:option value="AL">ACTION LIST</html:option>
											<html:option value="WG">WORKGROUP</html:option>
											<html:option value="SP">SPN</html:option>
										</html:select>
									</td>
								</tr>
								<!-- Defect Fix:JIMS200076934 Change the 'g' of id "workgroupRow" to 'G' to 
											              match the ids(show method) in java script.This solves the inconsistent behaviour across browser. -->
								<tr class="hidden" id="workGroupRow">
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="workgroupList" 
											primaryPropSort="workgroupName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="15" />
								<td class="formDeLabel" width="1%" valign="top"><bean:message key="prompt.workgroups" /></td>
									<td class="formDe" colspan="3">
										<html:select property="workgroupIds" size="3" multiple="true">
											<html:option value=""><bean:message key="select.generic" /></html:option> 
											<html:optionsCollection name="tasksSearchForm" property="workgroupList" value="workgroupId" label="workgroupName" />
								 		</html:select>
								 	</td>
								</tr>
								
								<tr class="hidden" id="divisionRow">
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="divisionCollection" 
											primaryPropSort="positionName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="16" />
									<td class="formDeLabel" width="1%"><bean:message key="prompt.division" /></td>
									<td class="formDe" colspan="3">
										<html:select size="1" property="organizationId" onchange="resetSupervisorOfficer(this.form)">
											<html:option value=""><bean:message key="select.generic"/></html:option>
											<html:optionsCollection property="divisionCollection" value="organizationId" label="assignedNameQualifiedByPositionName" />
											</html:select>
										<html:submit property="submitAction" onclick="return checkDivisionSelection()"> <bean:message key="button.getSupervisors" /></html:submit>
										<input type="hidden" name="currentUserStaffPosId" value=<bean:write name="tasksSearchForm" property="currentUserStaffPositionId" /> >
									</td>
								</tr>								
								<tr class="hidden" id="supervisorRow">
									<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="supervisors" 
												primaryPropSort="supervisorName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="16" />
									<td class="formDeLabel" width="1%"><bean:message key="prompt.supervisor" /></td>
									<td class="formDe" colspan="3">
										<html:select size="1" property="staffPositionId" onchange="resetOfficerStaff(this.form)">
											<html:option value=""><bean:message key="select.generic"/></html:option>
											<html:optionsCollection property="supervisors" value="staffPositionId" label="assignedNameQualifiedByPositionName" />
										</html:select>
										<html:submit property="submitAction" onclick="return checkSupervisorSelection()"> <bean:message key="button.getOfficers" /></html:submit>
									</td>
								</tr>							
								<tr class="hidden" id="officerRow">
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="officerList" 
											primaryPropSort="assignedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="17" />
									<td class="formDeLabel" width="1%"><bean:message key="prompt.officer" /> / <bean:message key="prompt.staff" /></td>
									<td class="formDe" colspan="3">
										<html:select size="1" property="officerStaffId">
											<html:option value=""><bean:message key="select.generic"/></html:option>
											<html:optionsCollection property="officerList" value="staffPositionId" label="assignedNameQualifiedByPositionName" />
										</html:select>
									</td>
								</tr>
								<tr class="hidden" id="spnRow">
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.SPN" /></td>
									<td class="formDe"><html:text property="spn" maxlength="8" size="8"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" valign="top"><bean:message key="prompt.status" /></td>
									<td class="formDe" colspan="3" >
										<html:select property="taskStatusIds" size="3" multiple="true">
											<html:option value=""><bean:message key="select.generic" /></html:option> 
											<jims2:codetable codeTableName="<%=PDCodeTableConstants.TASKSTATUS%>"/>
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.createDate" />&nbsp;<bean:message key="prompt.range" /></td>
									<td class="formDe" colspan="3">
										<SCRIPT LANGUAGE="JavaScript" ID="js1">
											var cal1 = new CalendarPopup();
											cal1.showYearNavigation();
										</SCRIPT>
										<html:text name="tasksSearchForm" property="createDateAsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].createDateAsString,'anchor1','MM/dd/yyyy'); return false;"
												NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A>
												
					                    <html:text name="tasksSearchForm" property="createDate2AsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].createDate2AsString,'anchor2','MM/dd/yyyy'); return false;"
												NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.2.calendar"/></A>
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.dueDate" />&nbsp;<bean:message key="prompt.range" /></td>
									<td class="formDe" colspan="3">
										<html:text name="tasksSearchForm" property="dueDateAsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].dueDateAsString,'anchor3','MM/dd/yyyy'); return false;"
												NAME="anchor3" ID="anchor3" border="0"><bean:message key="prompt.2.calendar"/></A>
												
					                    <html:text name="tasksSearchForm" property="dueDate2AsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].dueDate2AsString,'anchor4','MM/dd/yyyy'); return false;"
												NAME="anchor4" ID="anchor4" border="0"><bean:message key="prompt.2.calendar"/></A>
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.court#" /></td>
									<td class="formDe"><html:text property="court" maxlength="5" size="5"/></td>
								</tr>
								
							</table>
<!-- END SEARCH TABLE -->							
							</td>
						</tr>
					</table>
<!-- END BLUE BORDER FOR SEARCH TABLE -->	
					<br>				
<!-- BEGIN SEARCH BUTTON TABLE -->
					<table border="0" width="100%">
						<tr>
							<td align="center">
					       <html:submit property="submitAction" onclick="return checkAdvancedSearchCriteria(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit> 
						   <html:submit property="submitAction" onclick="return clearDates(this.form) && disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit>
						   <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
						   </td>
						</tr>
					</table>
<!-- END SEARCH BUTTON TABLE -->
				</html:form>
				<html:form action="/handleTaskSearch" target="content">
<!-- BEGIN SEARCH RESULTS SECTION --> 
					<tiles:insert page="taskSearchResultsList.jsp" flush="true"> </tiles:insert>
<!-- END SEARCH RESULTS SECTION -->
				</html:form>
				</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
			</td>
		</tr>
		<tr>
    		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  		</tr>
	</table>
<!-- END MAIN BODY TABLE -->	
</div>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>