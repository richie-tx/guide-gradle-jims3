<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 03/12/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/closeCase/closeCaseConfirm.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitCloseCase" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Close_Supervision/HC_Case/Close_Supervision_HC_Case.htm#|4">
<div align="center">
<!-- BEGIN MAIN TABLE -->
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
						<!--blue tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
						<!--blue tabs end-->
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
						<div class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.closeCase"/>&nbsp;<bean:message key="title.confirmation"/>
						</div>
						<div class="spacer"></div>
						
						<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>								
						<!-- END ERROR TABLE -->
				
						<!-- BEGIN SUCCESS DETAIL TABLE -->
						<logic:equal name="caseAssignmentForm" property="closeCasesSuccess" value="true">
							<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
								<tr>
									<td align="center">
										<br>
										<div class="confirm">
											Case(s) successfully closed.
										</div>
										<br>
									</td>
								</tr>
							</table>
						</logic:equal>
						<!-- END SUCCESS DETAIL TABLE -->
						
						<!-- BEGIN FAILURE SUMMARY TABLE -->
						<logic:equal name="caseAssignmentForm" property="closeCasesSuccess" value="false">
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td>Close Case Result</td>								
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td width="10%" nowrap>SPN</td>
												<td width="15%" nowrap>Case Number</td>
												<td width="10%" nowrap>Result</td>
												<td nowrap>Failure Reason</td>
											</tr>
											<logic:iterate id="eachCase" name="caseAssignmentForm" property="closeCasesResultBeanList" indexId="casesIndexId">
												<tr class=<% out.print((casesIndexId.intValue() % 2 == 0)?"normalRow":"alternateRow"); %>>
													<td><bean:write name="eachCase" property="defendantId" /></td>
													<td><bean:write name="eachCase" property="caseNum" /></td>
													<td><bean:write name="eachCase" property="result" /></td>
													<td>
														<table width="100%" cellpading="1" cellspacing="0" border="0">
															<logic:iterate id="eachFailureReason" name="eachCase" property="reasonList" >
																<tr>
																	<td nowrap><bean:write name="eachFailureReason" /></td>
																</tr>
															</logic:iterate>
														</table>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</logic:equal>
						<!-- END FAILUER SUMMARY TABLE -->
						
						<br>
						<!-- BEGIN BUTTON TABLE -->
					<table border="0" width="100%">
						<tr>
							<td align="center">
							 <span>
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.caseloadSearch" /></html:submit>
							</span>
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
<!-- END MAIN TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
