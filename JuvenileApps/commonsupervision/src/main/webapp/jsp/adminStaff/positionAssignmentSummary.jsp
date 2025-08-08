<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 06/22/2012  RYoung    - #Added disable submit on the submit button -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features"%>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> -
adminStaff/positionAsssignmentCasenote.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="javascript" type="text/javascript"
	src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript"
	src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
</head>
<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitPositionUpdate" target="content">
	<logic:equal name="adminStaffForm" property="action" value="assign">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|26">
	</logic:equal>
	<logic:equal name="adminStaffForm" property="action" value="reassign">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|15">
	</logic:equal>

	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img
						src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="../common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="positionsTab" />
							</tiles:insert> <!--tabs end--></td>
						</tr>

					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
								alt=""></td>
						</tr>
						<tr>
							<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message
										key="title.CSCD" /> - <logic:equal name="adminStaffForm"
										property="action" value="assign">
											Assign											 
											</logic:equal> <logic:equal name="adminStaffForm" property="action"
										value="reassign">
											Reassign											 
											</logic:equal> <bean:message key="prompt.position" /> <bean:message
										key="prompt.summary" /></td>
								</tr>
							</table>
							<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors /></td>
								</tr>
							</table>
							<!-- END ERROR TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
									<ul>
										<li>Review assignment information and casenote. Click
										finish.</li>
									</ul>
									</td>
								</tr>
							</table>
							<!-- END INSTRUCTION TABLE --> <!--position header start-->
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr class="paddedFourPix">
									<td class="formDeLabel"><bean:message
										key="prompt.position" /> <bean:message
										key="prompt.information" /></td>
									<td align="right" class="formDeLabel">&nbsp;</td>
								</tr>
								<tr>
									<td bgcolor="#cccccc" colspan="2"><tiles:insert
										page="positionInfoHeaderTile.jsp" flush="true">
										<tiles:put name="position" beanName="adminStaffForm"
											beanProperty="position" />
									</tiles:insert></td>
								</tr>
							</table>
							<!--position header end--> <br>
							<!-- assignment info start--> <tiles:insert
								page="assignmentInfoTile.jsp" flush="true">

							</tiles:insert> <!-- assignment info end--> <br>
							<logic:equal name="adminStaffForm"
								property="position.hasCaseload" value="true">
								<!-- add casenote start-->
								<span id="casenoteSpan">
								<table width="98%" border="0" cellspacing="0" cellpadding="2"
									class="borderTableBlue">
									<tr class="detailHead">
										<td><bean:message key="prompt.new" /> <bean:message
											key="prompt.casenote" /> <bean:message
											key="prompt.information" /></td>
									</tr>
									<tr>
										<td>
										<table width="100%" cellpadding="4" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
													key="prompt.casenote" /> <bean:message key="prompt.date" /></td>
												<td class="formDe"><bean:write name="adminStaffForm"
													property="casenote.casenoteDateAsString" /></td>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
													key="prompt.casenote" /> <bean:message key="prompt.time" /></td>
												<td class="formDe"><bean:write name="adminStaffForm"
													property="casenote.casenoteTime" /></td>
											</tr>
											<tr>
												<td colspan="4" class="formDeLabel"><bean:message
													key="prompt.casenote" /></td>
											</tr>
											<tr>
												<td colspan="4" class="formDe"><bean:write
													name="adminStaffForm" property="casenote.casenoteText"
													filter="false" /></td>
											</tr>
										</table>
										</td>
									</tr>
								</table>
								<!--add casenotes end--> <br>
								</span>
							</logic:equal> <!--begin button table-->
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td><html:submit property="submitAction">
										<bean:message key="button.back" />
									</html:submit> <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
										<bean:message key="button.finish" />
									</html:submit> <html:submit property="submitAction">
										<bean:message key="button.cancel" />
									</html:submit></td>
								</tr>
							</table>
							<!--end button table--></td>
						</tr>
					</table>
					<br>
					</td>
				</tr>
			</table>

		

			<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
			</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
</html:html>
