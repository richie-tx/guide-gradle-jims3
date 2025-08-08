<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/24/2009	 Chris Walters - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="org.apache.struts.action.Action"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>
<%@page import="naming.UIConstants"%>
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
administersupervisee/updateSuperviseeCreditSummary.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="spnSplitForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">


</script>
</head>

<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSuperviseeCreditUpdate" target="content">
	<input type="hidden" name="helpFile"
		value="commonsupervision/spn/spn.htm#|3">
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
						page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>
					<!--tabs end--></td>
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
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header">CSCD - <bean:write
								name="superviseeCreditDataControlForm" property="updateAction" />
							Supervisee Credit Summary</td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
					<table width="98%" align="center">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<!-- END ERROR TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Review entries and click Finish.</li>
							</ul>
							</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE --> <tiles:insert
						page="superviseeInfoTile.jsp" flush="true">
					</tiles:insert> <br>
					<!-- BEGIN  TABLE -->
					<div class="spacer4px"></div>
					<!-- begin search TABLE -->
					<table width="98%" cellpadding="2" cellspacing="0"
						class="borderTableBlue">
						<tr class="detailHead">

							<td>CJAD Program Unit Assignment</td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="4" cellspacing="1">
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap">Division /
									Program Unit</td>
									<td class="formDe"><bean:write
										name="superviseeCreditDataControlForm"
										property="selectedDivisionPgmUnitName" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap">CJAD Program
									Unit Assignment Date</td>
									<td class="formDe"><bean:write
										name="superviseeCreditDataControlForm"
										property="programUnitAssignDate" /></td>
								</tr>
							</table>

							</td>
						</tr>
					</table>
					<div id="officerAssignmentTable">
					<div class="spacer4px"></div>
					<table width="98%" cellpadding="2" cellspacing="0"
						class="borderTableBlue">
						<tr class="detailHead">
							<td>Officer Assignment</td>

						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="4" cellspacing="1">
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap">Officer</td>
									<td class="formDe"><bean:write
										name="superviseeCreditDataControlForm"
										property="selectedOfficerName" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</div>


					<div class="spacer4px"><html:submit property="submitAction">
						<bean:message key="button.back" />
					</html:submit> <html:submit property="submitAction">
						<bean:message key="button.finish" />
					</html:submit> <html:submit property="submitAction">
						<bean:message key="button.cancel" />
					</html:submit></div>

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