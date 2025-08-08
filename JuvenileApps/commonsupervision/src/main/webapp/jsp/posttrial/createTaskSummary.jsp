<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/28/2008	 Mo Chowdhury - Create JSP -->
<!-- 11/08/2010	 RYoung       - Add disable submit to finish button -->

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
<title><bean:message key="title.heading" /> - posttrial/createTaskSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitCreateTask" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|9">

<div align="center">
<!-- BEGIN PAGE TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign=top><!--tabs start--> <tiles:insert
							page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="tasksTab" />
						</tiles:insert> <!--tabs end--></td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" 	height="5"></td>
					</tr>
				</table>
<!-- END BLUE TABS TABLE -->				
<!-- BEGIN BLUE BORDER TABLE -->				
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
										<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.task"/>&nbsp;<bean:message key="prompt.summary"/></td>
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
											<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
										</ul>
									</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->
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
												<td class="formDeLabel" valign=top nowrap width="1%"><bean:message key="prompt.dueDate" /></td>
												<td class="formDe"><bean:write name="cscdTaskForm" property="taskDueDateStr" /></td>								
											</tr>
											
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.case#"/></td>
												<td class="formDe"><bean:write name="cscdTaskForm" property="taskCaseNumber" /></td>
												<td class="formDeLabel"><bean:message key="prompt.CDI"/></td>
												<td class="formDe"><bean:write name="cscdTaskForm" property="taskCdi" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.subject" /></td>
											</tr>
											<tr>	
												<td class="formDe" colspan="4"><bean:write name="cscdTaskForm" property="taskSubject" /></td>
											</tr>
											<tr>
											    <td class="formDeLabel" ><bean:message key="prompt.severityLevel" /></td>
											    <td class="formDe" colspan="3"><bean:write name="cscdTaskForm" property="taskSeverityLevel" /></td>
											</tr>
											<tr>
											    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.nextAction" /> Group</td>
												<td class="formDe" colspan="3"><bean:write name="cscdTaskForm" property="taskNextActionGroupId" /></td>
											</tr>
											<tr>
											    <td class="formDeLabel"><bean:message key="prompt.nextAction" /></td>
												<td class="formDe" colspan="3"><bean:write name="cscdTaskForm" property="taskNextAction" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.taskText" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan="4"><bean:write name="cscdTaskForm" property="taskText" /></td>	
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
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>
							</table>
<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
				<br>
			</td>
		</tr>
	</table>
<!-- END PAGE TABLE -->	
</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>