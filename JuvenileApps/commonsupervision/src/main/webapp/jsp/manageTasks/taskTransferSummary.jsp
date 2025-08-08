<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	 Hien Rodriguez - Create JSP -->

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
<title><bean:message key="title.heading" /> - manageTasks/taskDetails.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleTasksSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|17">

	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="tasksTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
						height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header"><bean:message
								key="title.CSCD" />&nbsp;<bean:message key="prompt.transfer"/>&nbsp;Task Summary </td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
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
								<li>Click appropriate button.</li>
							</ul></td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE -->
					<!-- BEGIN SELECT SEARCH TABLE -->
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr class="paddedFourPix">
							<td class="formDeLabel"><bean:message key="prompt.superviseeInfo" /></td>
						</tr>
						<tr>
							<td bgcolor="#cccccc">
								
								<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="headerLabel" width="1%"><bean:message key="prompt.name" /></td>
									<td class="headerData"><bean:write name="tasksSearchForm" property="name" /></td>
									<td class="headerLabel" width="1%" nowrap><bean:message key="prompt.SPN" /></td>
									<td class="headerData"><bean:write name="tasksSearchForm" property="spn" /></td>
								</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
					<!-- BEGIN DETAIL TABLE -->
					<table width="98%" border="0" cellspacing="0" cellpadding="2"
						class="borderTableBlue">
						<tr class="detailHead">
							<td><bean:message key="prompt.taskInfo" /></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="4" cellspacing="1">
							
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.transfer"/>&nbsp;To</td>
									<td class="formDe" colspan="3"><bean:write name="tasksSearchForm" property="transferTo" /></td>
								</tr>
								
								<tr>
									<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.createDate" /></td>
									<td class="formDe"><bean:write name="tasksSearchForm" property="createDate" formatKey="date.format.mmddyyyy" /></td>
									<td class="formDeLabel" valign="top" nowrap width="1%"><bean:message key="prompt.dueDate" /></td>
									<td class="formDe"><bean:write name="tasksSearchForm" property="dueDate" formatKey="date.format.mmddyyyy" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.tasklistType" /></td>
									<td class="formDe" colspan="3"><bean:write name="tasksSearchForm" property="tasklistTypeDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.severityLevel" /></td>
									<td class="formDe" colspan="3"><bean:write name="tasksSearchForm"  property="severityLevelDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.status" /></td>
									<td class="formDe" colspan="3"><bean:write name="tasksSearchForm" property="status" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.subject" /></td>
									<td class="formDe" colspan="3"><bean:write name="tasksSearchForm" property="subject" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.taskSubject" /></td>
									<td class="formDe" colspan="3"><bean:write name="tasksSearchForm" property="taskSubject" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" colspan="4"><bean:message key="prompt.taskText" /></td>
								</tr>
								<tr>
									<td class="formDe" colspan="4"><bean:write name="tasksSearchForm" property="taskText" /></td>
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
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.back" /></html:submit>&nbsp; 
								<%--<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.next"></bean:message></html:submit>&nbsp;--%>
												<html:submit property="submitAction">
													<bean:message key="button.finish" />
												</html:submit>
<!-- 									
								<input type=submit value="<bean:message key='button.next' />" 
								onClick="return changeFormActionURL('tasksSearchForm','/<msp:webapp/>executeTask.do?taskId=<bean:write name="tasksSearchForm" property="taskId" />', false);"></input>
-->									
								<%--<html:submit property="submitAction" onclick="return  disableSubmit(this, this.form);">
									<bean:message key="button.viewOrder"></bean:message></html:submit>&nbsp;
								<html:submit property="submitAction" onclick="alert('This option is not available yet')">
									<bean:message key="button.transferTask"></bean:message></html:submit>&nbsp; 
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.closeTask"></bean:message></html:submit>&nbsp; 
								<html:submit property="submitAction" onclick="return  disableSubmit(this, this.form);">
									<bean:message key="button.paperFileReceived"></bean:message></html:submit>&nbsp;--%> 
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
									<bean:message key="button.cancel"></bean:message></html:submit>
							</td>
						</tr>
					</table>
					
					
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
