<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/22/2007	 Hien Rodriguez - Create JSP -->
<!-- 09/10/2007	 Richard Y - Add show hide options for advanced search -->
<!-- 09/20/2007	 Richard Y - Removed the navigation buttons from this jsp.Are on the details page-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.PDCodeTableConstants" %>

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
	for (var i = 0; i < document.myForm.task.length; i++) {
		if (document.myForm.task[i].checked){
			var el = document.myForm.task[i];
		}
	}
	if (el){
		toggleButtons(el)
	}
}

function toggleButtons(el){
	if (el.checked){
		switch (el.value)
		{
			case "continue":
			show("continueButton", 1, "inline")
			show("acceptButton", 0)
			break

			default:
			show("acceptButton", 1, "inline")
			show("continueButton", 0)
		}
	}
}

function resetForm(theSelect){
	theSelect.selectedIndex = 0
	checkWG(theSelect);
}

function checkWG(theSelect){
	if(theSelect.options[theSelect.selectedIndex].value == "WG"){
		show("workGroupRow", 1, "row");
		show("divisionRow", 0, "row");
		show("supervisorRow", 0, "row");
		show("officerRow", 0, "row");
	}else{
		show("workGroupRow", 0, "row");
		show("divisionRow", 1, "row");
		show("supervisorRow", 1, "row");
		show("officerRow", 1, "row");
	}
}

function checkAdvancedSearchCriteria(el){
	if(document.getElementById("tasklistTypeId").value == "AL"){
		if(document.getElementById("divisionId").value == ""  &&
				document.getElementById("staffPositionId").value == "" &&
				document.getElementById("officerStaffId").value == "" &&
				document.getElementById("taskStatusId").value  == "" &&
				document.getElementById("createDateAsString").value == "" &&
				document.getElementById("createDate2AsString").value == "" &&
				document.getElementById("dueDateAsString").value == "" &&
				document.getElementById("dueDate2AsString").value == ""&&
				document.getElementById("severityLevelId").value == "" &&
				document.getElementById("court").value == ""){
			alert("At least one search criteria is required");
			return false;
		
		}
	}else{
		if(document.getElementById("workgroupId").value == ""  &&
				document.getElementById("taskStatusId").value  == "" &&
				document.getElementById("createDateAsString").value == "" &&
				document.getElementById("createDate2AsString").value == "" &&
				document.getElementById("dueDateAsString").value == "" &&
				document.getElementById("dueDate2AsString").value == ""&&
				document.getElementById("severityLevelId").value == "" &&
				document.getElementById("court").value == ""){
			alert("At least one search criteria is required");
			return false;
		}
	
	}	
		
		if(document.getElementById("createDate2AsString").value!="" || document.getElementById("createDateAsString").value!=""){
		alert("");
			customValRequired("createDate2AsString", "End Date is required");
			customValRequired("createDateAsString", "Begin Date is required");
			addMMDDYYYYDateValidation("createDate2AsString", "End Date must be in the format MM/DD/YYYY");
			addMMDDYYYYDateValidation("createDateAsString", "Begin Date must be in the format MM/DD/YYYY");
			if(validateCustomStrutsBasedJS(el)){
				if(compareDate1GreaterEqualDate2(document.getElementById("createDate2AsString").value,document.getElementById("createDateAsString").value)){
					
				}
				else{
					alert("Begin Date must be less than or equal to end date");
					document.getElementById("createDateAsString").focus();
					return false;
				}
			}
			else{
				return false;
			}
			
		}
		
		if(document.getElementById("dueDate2AsString").value!="" || document.getElementById("dueDateAsString").value!=""){
		alert("Due date JS");
			customValRequired("dueDate2AsString", "End Date is required");
			customValRequired("dueDateAsString", "Begin Date is required");
			addMMDDYYYYDateValidation("dueDate2AsString", "End Date must be in the format MM/DD/YYYY");
			addMMDDYYYYDateValidation("dueDateAsString", "Begin Date must be in the format MM/DD/YYYY");
			if(validateCustomStrutsBasedJS(el)){
				if(compareDate1GreaterEqualDate2(document.getElementById("dueDate2AsString").value,document.getElementById("dueDateAsString").value)){
					
				}
				else{
					alert("Begin Date must be less than or equal to end date");
					document.getElementById("dueDateAsString").focus();
					return false;
				}
			}
			else{
				return false;
			}
			
		}
		<%--clearAllValArrays();--%>
			if(document.getElementById("divisionId").value!=""){
				var theGenerElem=document.getElementById("staffPositionId");
				var theGenerVal=theGenerElem.options[theGenerElem.selectedIndex].value;
				if(theGenerVal==""){
					alert("A Supervisor must be selected.");
					return false;	
				}
			}
		
		return true;
	}
	

</script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkWG(document.forms[0].tasklistTypeId)">

	<div align="center">
<!-- BEGIN MAIN BODY TABLE --> 	
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
		</tr>
		<tr>
			<td valign=top>
<!-- BEGIN TAB TABLE --> 			
			<table width=100% border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign=top>
					<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="tasksTab" />
						</tiles:insert>
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
<!-- END TAB TABLE --> 						
<!-- BEGIN BLUE BORDER TABLE --> 			
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
<!-- BEGIN HEADING TABLE -->
					<table width=100%>
						<tr>
							<td align="center" class="header"><bean:message key="prompt.advancedSearch" />&nbsp;
								<bean:message key="prompt.tasks" /></td>
						</tr>
					</table>
<!-- END HEADING TABLE --> 
<!-- BEGIN ERROR TABLE -->
					<table width=98% align=center>
						<tr>
							<td align="center" class="errorAlert"><html:errors /></td>
						</tr>
					</table>
<!-- END ERROR TABLE --> 
<!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td><ul>
								<li>Select tasks and click appropriate button.</li>
								<li>Administer Compliance must be for the same supervisee.</li>
							</ul></td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE --> 
					<!-- BEGIN SELECT SEARCH TABLE -->
					<html:form action="/handleTaskSearch" target="content" focus="tasklistTypeId">
					<table align="center" width=98% border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
						<tr>
							<td class=detailHead colspan="2"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.tasks" />
							&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>handleTaskSearch.do?submitAction=<bean:message key="button.basicSearch"/>"><bean:message key="button.basicSearch" /></a></td>
						</tr>
						<tr>
							<td>
							<table width=100% border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.tasklistType" /></td>
									<td class="formDe" colspan=3><html:select property="tasklistTypeId" size="1" onchange="checkWG(this)">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<jims:codetable codeTableName="<%=PDCodeTableConstants.TASK_LIST_TYPES%>"/>
									</html:select></td>
								</tr>
								<!-- Defect Fix:JIMS200076934 Change the 'g' of id "workgroupRow" to 'G' to 
											              match the ids(show method) in java script.This solves the inconsistent behaviour across browser. -->
								<tr class=hidden id=workGroupRow>
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="workgroupList" 
											primaryPropSort="workgroupName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="15" />
								<td class=formDeLabel width=1% valign=top><bean:message key="prompt.workgroups" /></td>
									<td class=formDe colspan=3><html:select property="workgroupId" size="3">
										<html:optionsCollection name="tasksSearchForm" property="workgroupList" value="workgroupId" label="workgroupName" />
								 	</html:select></td>
								</tr>
								
								<tr class=hidden id=divisionRow>
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="divisionCollection" 
											primaryPropSort="positionName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="16" />
									<td class=formDeLabel width=1%><bean:message key="prompt.division" /></td>
									<td class=formDe colspan=3>
										<html:select size="1" property="divisionId">
											<html:option value=""><bean:message key="select.generic"/></html:option>
											<html:optionsCollection property="divisionCollection" value="divisionId" label="positionName" />
											</html:select>
										<html:submit property="submitAction"> <bean:message key="button.getSupervisors" /></html:submit>
										</td>
								</tr>								
								<tr class=hidden id=supervisorRow>
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="supervisors" 
											primaryPropSort="positionName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="16" />
									<td class=formDeLabel width=1%><bean:message key="prompt.supervisor" /></td>
									<td class=formDe colspan=3>
										<html:select size="1" property="staffPositionId">
											<html:option value=""><bean:message key="select.generic"/></html:option>
										<html:optionsCollection property="supervisors" value="staffPositionId" label="positionName" />
										</html:select>
										<html:submit property="submitAction"> <bean:message key="button.getOfficers" /></html:submit>
									</td>
								</tr>							
								<tr class=hidden id=officerRow>
								<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="officerList" 
											primaryPropSort="positionName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="17" />
									<td class=formDeLabel width=1%><bean:message key="prompt.officer" /> / <bean:message key="prompt.staff" /></td>
									<td class=formDe colspan=3>
										<html:select size="1" property="officerStaffId">
											<html:option value=""><bean:message key="select.generic"/></html:option>
											<html:optionsCollection property="officerList" value="staffPositionId" label="positionName" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td class=formDeLabel width=1%><bean:message key="prompt.status" /></td>
									<td class=formDe colspan=3><html:select property="taskStatusId" size="1">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<jims:codetable codeTableName="<%=PDCodeTableConstants.TASKSTATUS%>"/>
									</html:select></td>
								</tr>
								<tr>
									<td class=formDeLabel width=1% nowrap><bean:message key="prompt.createDate" />&nbsp;<bean:message key="prompt.range" /></td>
									<td class=formDe colspan=3>
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
									<td class=formDeLabel width=1% nowrap><bean:message key="prompt.dueDate" />&nbsp;<bean:message key="prompt.range" /></td>
									<td class=formDe colspan=3>
										<html:text name="tasksSearchForm" property="dueDateAsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].dueDateAsString,'anchor3','MM/dd/yyyy'); return false;"
												NAME="anchor3" ID="anchor3" border="0"><bean:message key="prompt.2.calendar"/></A>
												
					                    <html:text name="tasksSearchForm" property="dueDate2AsString" maxlength="10" size="10" />
											<A HREF="#" onClick="cal1.select(document.forms[0].dueDate2AsString,'anchor4','MM/dd/yyyy'); return false;"
												NAME="anchor4" ID="anchor4" border="0"><bean:message key="prompt.2.calendar"/></A>
									</td>
								</tr>
								<tr>
									<jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="severityLevels" 
											primaryPropSort="description" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="17" />
									<td class=formDeLabel nowrap><bean:message key="prompt.severityLevel" /></td>
									<td colspan=3 class=formDe><html:select property="severityLevelId" size="1">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection property="severityLevels" value="supervisionCode" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td class=formDeLabel nowrap><bean:message key="prompt.court#" /></td>
									<td class=formDe><html:text property="court" maxlength="5" size="5"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" width=1% nowrap></td>
									<td class="formDe" colspan=3>
										<html:submit property="submitAction" onclick="return checkAdvancedSearchCriteria(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit> 
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</html:form>
					<html:form action="/handleTaskSearch" target="content">
<!-- END SELECT SEARCH TABLE --> 
				<br>
<!-- BEGIN SEARCH USERS RESULTS SECTION --> 
				<tiles:insert page="taskSearchResultsList.jsp" flush="true"> </tiles:insert>
<!-- END SEARCH USERS RESULTS SECTION -->
						<br>
<!-- BEGIN BUTTON TABLE -->
						
						</html:form>
<!-- END BUTTON TABLE -->
					</tr>
				</table>
				</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
			<br>
		</td>
		</tr>
	</table>
<!-- END MAIN BODY TABLE -->	
	<br>
	</div>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>