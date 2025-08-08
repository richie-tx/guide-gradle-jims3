<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	 Hien Rodriguez - Create JSP -->
<!-- 01/20/2011	 LDEEN DOESN'T THINK THIS JSP IS BEING USED -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>

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
<title>CommonSupervision/administerCaseload/taskNoteConfirmation.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>


</head>
<body topmargin="0" leftmargin="0">
<html:form action="/handleCaseAssignment" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|11">
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
					<td valign="top"><!--blue tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> <!--blue tabs end--></td>
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
					<td valign="top" align="center"><!-- BEGIN DETAIL TABLE -->
					<table width="98%" border="0" cellpadding="2" cellspacing="1"
						class="borderTableBlue">
						<tr>
							<td><br>
							<div class="confirm"><logic:equal name="caseAssignmentForm"
								property="<%=CaseloadConstants.ACTIVITY_IND%>"
								value="<%=CaseloadConstants.WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR%>">
													Supervisee successfully allocated to Supervisor.
												</logic:equal> <logic:equal name="caseAssignmentForm"
								property="<%=CaseloadConstants.ACTIVITY_IND%>"
								value="<%=CaseloadConstants.WF_TASKLIST%>">
													New case assignment processed.
												</logic:equal></div>
							<br>
							</td>
						</tr>
					</table>
					<!-- END DETAIL TABLE --> <br>
					<!-- BEGIN BUTTON TABLE -->
					<table border="0" width="100%">
						<tr>
							<td align="center"><logic:equal name="caseAssignmentForm"
								property="<%=CaseloadConstants.SCENARIO%>"
								value="<%=CaseloadConstants.SC_ASSIGN_NEW_CASE%>">
								<!-- 
											<input type="button" value="<bean:message key="button.backToTasks" />" onClick="goNav('/<msp:webapp/>jsp/administerCaseload/tempStartFlow.jsp')" />
-->
								<html:submit property="submitAction">
									<bean:message key="button.backToTasks" />
								</html:submit>

							</logic:equal> <span class="hidden" id="caseloadButton"> <input
								type="button" value="Back to Caseload"
								onClick="goNav('../caseload/caseloadList.htm')"> </span></td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --> <%--			<script>
										if (location.search=="?officerCaseload"){
											show("caseloadButton", 1)
										}else show("tasksButton", 1)
									</script>--%></td>
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
