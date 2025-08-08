<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/28/2008	 Mo Chowdhury - Create JSP -->
<!-- 04/23/2009	 RYoung change remove quotes from message

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
<title><bean:message key="title.heading" /> - posttrial/createTaskConfirmation.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleTaskSearch" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Tasks/CSCD_Tasks.htm#|10">

<div align="center">
<!-- BEGIN PAGE TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><!--tabs start--> <tiles:insert
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
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header">
										<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.task"/>&nbsp;<bean:message key="prompt.confirmation"/></td>
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
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr>
								<td align="center">
									<br>
									<div class="confirm">										
										Task successfully created.								
									</div>
									<br>
								</td>
							</tr>
						</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->

<!-- END DETAIL TABLE -->
							<br>
<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.taskList" /></html:submit>
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>