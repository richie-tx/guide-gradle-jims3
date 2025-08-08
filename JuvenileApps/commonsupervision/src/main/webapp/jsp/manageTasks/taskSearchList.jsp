<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/08/2007	 Hien Rodriguez - Create JSP -->
<!-- 03/19/2008	 Richard Young  - JIMS200050284 Fix null pointer -->
<!-- 09/18/2008  CShimek - defect#54335  revised instructions to match PT -->
<!-- 02/10/2008	 Ryoung  - 56356 Tasklist - display buttons after Back button is used  -->
<!-- 03/16/2009	 Ryoung  - 57101 Tasklist - Tasks: Blank Page Displays when Next Button selected Amended Order Task  -->
<!-- 03/18/2009  CShimek - 57748 created new script for workgroup onload to not reset selected to 0 -->
<!-- 02/08/2010  RYoung  - 62993 Task Details button disappears -->
<!-- 03/30/2010  DWilliamson - 64445 Pre-select radio button when only 1 result --> 
<!-- 05/28/2010	 RYoung  - #65716 CS Tasks - Hide Transfer Task Button Temporarily -->  
<!-- 08/06/2010	 RYoung  - #66538 Allow code to use the OPEN status -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - manageTasks/taskSearchList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
<script>
function onLoadCheck(){
	var theLength = document.getElementsByName('taskId').length;
	for (var i = 0; i < theLength; i++) {
		if (document.getElementsByName('taskId')[i].checked){
			var el = document.getElementsByName('taskId')[i];			
		}
	}

	if (el){
		toggleButtons(el)
	}
}

function toggleButtons(el){
	
	if (el.checked){

		<jims:isAllowed requiredFeatures="<%=Features.CSCD_TASKS_CLOSE_ADV%>">		
			<bean:define id="featureToClose" type="java.lang.String">
			canCloseAnything	
		</bean:define>
		</jims:isAllowed>
		
		//checking the TD to see if it has an ID - b/c if it does not, there is no next action for this task 	
		<logic:equal name="featureToClose" value="">

		if ( el.parentElement.id == "" ) {
			show("closeButton", 1, "inline");
			show("acceptButton", 1, "inline");
			
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
			show("continueButton", 1, "inline")
			show("taskDetailsButton", 1, "inline")
			//show("transferButton", 1, "inline")
			show("acceptButton", 0)
			break
			
			case "C":
			show("continueButton", 0, "inline")
			show("taskDetailsButton", 1, "inline")
			show("acceptButton", 0)
			break
			
			case "S":
			//show("transferButton", 1, "inline")
			show("taskDetailsButton", 1, "inline")
			show("acceptButton", 1, "inline")
			break
			
			case "O":
			//show("transferButton", 1, "inline")
			show("taskDetailsButton", 1, "inline")
			show("acceptButton", 1, "inline")
			break
			
		}
	}
}
	function checkWG(){
		var offset = document.forms[0]['tasklistTypeId'].selectedIndex ;
		var textSelection = document.forms[0]['tasklistTypeId'].options[ offset ].value;
		
		if(textSelection == "WG"){

			show("workGroupRow", 1, "row");
			document.forms[0]["workgroupIds"].selectedIndex = 0;
		}else{
			show("workGroupRow", 0, "row");
		}
			//show("resultsfound", 0);
	}

function resetForm(theSelect){
	theSelect.selectedIndex = 0
	checkWG(theSelect);
}


function checkInputs(theForm){
	if (theForm.tasklistTypeId.selectedIndex == 0){
		alert("Tasklist Type selection is required");
		return false;
	}
	resetPagination(theForm);
	return true;
}

function checkWorkgroupSelection(theForm){
    if(theForm.tasklistTypeId[theForm.tasklistTypeId.selectedIndex].value == "WG"){
        if (theForm.workgroupIds.selectedIndex == 0){
			alert("Workgroup selection is required");
			return false;
		}
    }
    return true;
}

function checkWGonload(){
	var offset = document.forms[0]['tasklistTypeId'].selectedIndex ;
	var textSelection = document.forms[0]['tasklistTypeId'].options[ offset ].value;

	if(textSelection == "WG"){
		show("workGroupRow", 1, "row");
	}else{
		show("workGroupRow", 0, "row");
	}
}
//slin:this function resets the pagination to always be the 1st page
function resetPagination(theForm){		
	document.getElementsByName("pager.offset")[0].value=0;	
}

function refreshTasks(theButton, theForm){
	resetPagination(theForm);
	disableSubmit(theButton, theForm);
	return true;
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("taskId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>
<body onload="checkWGonload( ); checkForSingleResult(); onLoadCheck();";>
	<div align="center">
	
<!--  BEGIN MAIN PAGE TABLE -->	
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">  
<!--  BEGIN TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
						<!-- TABS START -->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="tasksTab" />
							</tiles:insert>
						<!-- TABS END -->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>./images/spacer.gif" height="5"></td>
					</tr>
				</table>
				
<!--  END TABS TABLE -->						
<!--  BEGIN BLUE BORDER TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.task" />&nbsp;<bean:message key="prompt.search" /></td>
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
										<li>To Search Tasks change Tasklist Type search and click Submit or click Advanced Search hyperlink to perform more advanced search.</li>
										<li>Select task and click appropriate button.</li>
									</ul></td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->
<html:form action="/handleTaskSearch" target="content" focus="tasklistTypeId"> 
							<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|1">
<!-- BEGIN SELECT SEARCH TABLE -->
							<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead" colspan="2"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.tasks" />
									&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>handleTaskSearch.do?submitAction=<bean:message key="button.advancedSearch"/>"><bean:message key="button.advancedSearch" /></a></td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.tasklistType" /></td>
												<td class="formDe">
													<html:select property="tasklistTypeId" size="1" onchange="checkWG()">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<jims:codetable codeTableName="<%=PDCodeTableConstants.TASK_LIST_TYPES%>" />
													</html:select></td>
											</tr>
											<!-- Defect Fix:JIMS200076934 Change the 'g' of id "workgroupRow" to 'G' to 
											              match the ids(show method) in java script.This solves the inconsistent behaviour across browser. -->
											<tr class="hidden" id="workGroupRow">
											  <jims2:sortResults hideMe="true" beanName="tasksSearchForm" results="workgroupList" 
											primaryPropSort="workgroupName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="19" />
												<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.workgroups" /></td>
												<td class="formDe" colspan="3" >
													<html:select property="workgroupIds" size="3" onchange="multiSelectFix(this)" multiple="true">
													<html:option value="" ><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="tasksSearchForm" property="workgroupList" value="workgroupId" label="workgroupName" />
													</html:select>
												</td>
											</tr>
											
										</table>
									</td>
								</tr>
							</table>
						  
						  <!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
						       		<html:submit property="submitAction" onclick="return checkInputs(this.form)&& checkWorkgroupSelection(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit> 
									<html:submit property="submitAction" onclick="return refreshTasks(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit>
							   </td>
							</tr>
						</table>
					<!-- END BUTTON TABLE -->
					</html:form>
				<html:form action="/handleTaskSearch" target="content">	  
									
<!-- END SELECT SEARCH TABLE --> 
<%--							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td id="resultsfound" align="center" class="visible"> --%>
<!-- BEGIN SEARCH USERS RESULTS SECTION -->
							<tiles:insert page="taskSearchResultsList.jsp" flush="true"> </tiles:insert>
							</html:form>
<!-- END SEARCH USERS RESULTS SECTION -->
<%--									</td>
								</tr>
							</table>	--%>	
							<br>
						</td>

					</tr>
				</table>
<!--  END BLUE BORDER TABLE -->				
			</td>
			
		</tr>
		
	</table>
<!--  END MAIN PAGE TABLE -->	
	<br>
<%--	</html:form> --%>
	</div>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>

</html:html>