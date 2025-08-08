<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	HRodriguez	Create JSP -->
<%-- 02/28/2008	LDeen		Defect #49819 integrate help    --%>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="naming.UIConstants" %>

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
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>		
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<html:base />
</head>
<body topmargin="0" leftmargin="0">
	<html:form action="/transferCaseConfirmationAction" target="content">
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td valign="top">
								<!--blue tabs start-->
								<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tab" value="caseloadTab"/>
								</tiles:insert>
								<!--blue tabs end-->
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center">
								<!-- BEGIN HEADING TABLE -->
								<table width="100%">
									<tr>
										<td align="center" class="header" >
											<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
											<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">											
												Transfer Out Confirmation
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|6">
											</logic:equal>
											<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">											
												Transfer In Confirmation
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|10">
											</logic:equal>
										</td>
									</tr>
								</table>
								<!-- END HEADING TABLE -->
														
								<!-- BEGIN DETAIL TABLE -->
								<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
									<tr>
										<td>
											<br><div class="confirm">
												<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">							
													Supervisee case(s) successfully transferred out.
												</logic:equal>
												<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">							
													Supervisee case(s) successfully transferred in.
												</logic:equal>
											</div><br>
										</td>
									</tr>
								</table>
								<!-- END DETAIL TABLE -->
								
								<br>
												
								<!-- BEGIN BUTTON TABLE -->
								<table border="0" width="100%">
									<tr>
										<td align="center">
											<html:submit property="submitAction"  >
												<bean:message key="button.caseloadSearch" />
											</html:submit>
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
