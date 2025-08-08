<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 07/02/2010 C Shimek - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C Shimek - #67758 revised comment max. from 500 to 3500 and added spell check -->
<!-- 06/10/2011 R Young  - #70346 Violation Report-copy Comments in each section(UI) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="naming.UIConstants" %>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/vrCommunityService.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/violationReports/vrCommunityService.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayVRCommunityServiceSummary" target="content" focus="create1Comments">
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
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
<!-- BEGIN SUPERVISEE TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE TABLE -->						
<!-- BEGIN GREEN TABS TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->						
<!-- BEGIN GREEN BORDER TABLE -->						
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.violationReport"/>&nbsp;-
												<bean:message key="prompt.communityService"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|22">
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Enter comments and click Next when complete.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<br>
<!-- BEGIN DETAIL TABLE -->
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.communityService"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.hoursOrdered"/></td>
														<td class="formDe"><bean:write name="violationReportsForm" property="hoursOrdered"/></td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap><bean:message key="prompt.hoursCompleted"/></td>
														<td class="formDe"><bean:write name="violationReportsForm" property="hoursCompleted"/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<br>
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/>
											<tiles:insert page="/jsp/common/spellCheckCaseHistTile.jsp" flush="false">
                          						<tiles:put name="tTextField" value="create1Comments"/>
                          						<tiles:put name="tSpellCount" value="spellBtn1" />
                        					</tiles:insert>
											 (Max. characters allowed: 3500)</td>
										</tr>
										<tr>
											<td class="formDe">
												<html:textarea name="violationReportsForm" property="create1Comments" 
													onmouseout="textLimit(this, 3500);" 
       												onkeyup="textLimit(this, 3500);" 
													style="width:100%" rows="30">
												</html:textarea>
											</td>
										</tr>
										<input type="hidden" name="prevComments" value="<bean:write name='violationReportsForm' property='previousCommunityServiceComments' />" >
									</table>
<!-- END DETAIL TABLE -->
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateNextInput(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.refreshCommunityService" /></html:submit>
												<input type="button" name="copyComments" value="<bean:message key="button.copyComments" />" onclick="loadPrevComments()" />
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->						
						<br>
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
