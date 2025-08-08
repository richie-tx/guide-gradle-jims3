<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/20/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.UIConstants" %>
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
<title><bean:message key="title.heading" /> - transferCSCD/transferUpdateSummary.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" 	src="/CommonSupervision/js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/updateTransferCaseHistoryConfirmationAction" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|7">
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
							<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							    <tiles:put name="tab" value="caseloadTab" />
						    </tiles:insert>
							<!--tabs end-->
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
							<!--header area start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td bgcolor="#cccccc" colspan="2">
										<!--header start-->
						                <tiles:insert page="../common/caseloadHeaderCase.jsp" flush="true"></tiles:insert>		
										<!--header end-->
									</td>
								</tr>
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
							<!--header area end-->
							<!--casefile tabs start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
											<tiles:put name="tab" value="SuperviseeTab"/> 
										</tiles:insert>	 
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
										<!-- BEGIN HEADING TABLE -->
										<div class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.transfer"/>&nbsp;<bean:message key="prompt.update"/>&nbsp;<bean:message key="title.summary"/></div>
										<!-- END HEADING TABLE -->
										<!-- BEGIN ERROR TABLE -->
										<table width="98%" align="center">							
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>		
										</table>
									    <!-- END ERROR TABLE -->
										<!-- BEGIN INSTRUCTION TABLE -->
										<div class=instructions>
											<ul>
												<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
											</ul>
										</div>
									    
										<!-- BEGIN PARTY NAME INFORMATION TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
											<tr>
												<td class="detailHead" colspan="2"><bean:message key="prompt.transferInformation"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="4" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferOutDate"/></td>
															<td class="formDe"><bean:write name="superviseeForm" property="transferCasesInfo.transferOutDate" formatKey="date.format.mmddyyyy"/>
															</td>
														</tr>
														<logic:notEmpty name="superviseeForm" property="transferCasesInfo.transferInDate">														
															<tr>
																<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferInDate"/></td>
																<td class="formDe"><bean:write name="superviseeForm" property="transferCasesInfo.transferInDate" />
																</td>
															</tr>
															<tr id="rejectedRow">
																<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.rejected"/></td>
																	<td class="formDe">
																		<logic:equal name="superviseeForm" property="transferCasesInfo.rejectedAsStr" value="Y">
																			<bean:message key="prompt.yes"/>
																		</logic:equal>
																		<logic:equal name="superviseeForm" property="transferCasesInfo.rejectedAsStr" value="N">
																			<bean:message key="prompt.no"/>
																	</logic:equal>
																   </td>
															</tr>
														</logic:notEmpty>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.texasCounty"/></td>
															<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="transferCasesInfo.transferTxCountyName"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.outOfState"/></td>
															<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="transferCasesInfo.transferStateName"/></td>
														</tr>
														<logic:notEmpty name="superviseeForm" property="transferCasesInfo.transferInDate">														
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.personID"/></td>
																<td class="formDe"><bean:write name="superviseeForm" property="transferCasesInfo.personId"/></td>
															</tr>
														</logic:notEmpty>
													</table>
												</td>
											</tr>
									</table>
									<!-- END MISCELLANEOUS INFORMATION TABLE -->
									<div class="spacer4px"></div>
									<!--button start -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>
										        <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!--button end -->
								</td>
							</tr>
						</table>
						<div class="spacer4px"></div>
						<!-- END DETAIL TABLE -->
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<br>
<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
