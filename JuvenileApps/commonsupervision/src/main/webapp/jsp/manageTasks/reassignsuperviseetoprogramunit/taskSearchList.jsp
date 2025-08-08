<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/08/2007	 Hien Rodriguez - Create JSP -->
<!-- 08/16/2010  DWilliamson - #66993 Hide Advanced Search button temporarily -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.PDCodeTableConstants"%>

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
<title><bean:message key="title.heading" /> - manageTasks/taskBasicSearchList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" 	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/manageTasksUtil.js"></script>
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


	function checkWG(theSelect){
	if(theSelect.options[theSelect.selectedIndex].value == "WG"){
	show("workGroupRow", 1, "row");
	}else{
	show("workGroupRow", 0), "row";
	}
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
	return true;
}
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="showWG(document.forms[0].tasklistTypeId)">

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
						<td valign=top>
						<!-- TABS START -->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="tasksTab" />
							</tiles:insert>
						<!-- TABS END -->
						</td>
					</tr>
					<tr>
						<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
				</table>
				<SCRIPT>
					function showWG(theSelect){
					if(theSelect.options[theSelect.selectedIndex].value == "WG"){
						show("workGroupRow", 1, "row");
					}else{
					show("workGroupRow", 0), "row";
						}
}
				</SCRIPT>
<!--  END TABS TABLE -->						
<!--  BEGIN BLUE BORDER TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.tasks" /></td>
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
										<li>Select tasks and click appropriate button.</li>
										<li>Administer Compliance must be for the same supervisee.</li>
									</ul></td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE --> 
							<html:form action="/handleTaskSearch" target="content" focus="tasklistTypeId">
<!-- BEGIN SELECT SEARCH TABLE -->
							<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr>
									<td class=detailHead colspan="2"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.tasks" />
									&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>handleTaskSearch.do?submitAction=<bean:message key="button.advancedSearch"/>"><bean:message key="button.advancedSearch" /></a></td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.tasklistType" /></td>
												<td class="formDe">
													<html:select property="tasklistTypeId" size="1" onchange="checkWG(this)">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<jims:codetable codeTableName="<%=PDCodeTableConstants.TASK_LIST_TYPES%>" />
													</html:select></td>
											</tr>
											<!-- Defect Fix:JIMS200076934 Change the 'g' of id "workgroupRow" to 'G' to 
											              match the ids(show method) in java script.This solves the inconsistent behaviour across browser. -->
											<tr class="hidden" id="workGroupRow">
												<td class="formDeLabel" width="1%" nowrap valign="top"><bean:message key="prompt.workgroups" /></td>
												<td class="formDe" colspan="3">
													<html:select property="workgroupIds" size="3" multiple="true">
														<html:optionsCollection name="tasksSearchForm" property="workgroupList" value="workgroupId" label="workgroupName" />
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap></td>
												<td class="formDe">
													<html:submit property="submitAction" onclick="return checkInputs(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit> 
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
							</table>
<!-- END SELECT SEARCH TABLE --> 
							</html:form>
							<html:form action="/handleTaskSearch" target="content">
							<br>
<!-- BEGIN SEARCH USERS RESULTS SECTION -->
							<tiles:insert page="taskSearchResultsList.jsp" flush="true"> </tiles:insert>
<!-- END SEARCH USERS RESULTS SECTION -->
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
	</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>