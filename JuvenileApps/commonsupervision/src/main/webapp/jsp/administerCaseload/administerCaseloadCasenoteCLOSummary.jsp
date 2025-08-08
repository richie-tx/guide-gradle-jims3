<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	 Hien Rodriguez - Create JSP -->
<!-- 11/08/2010	 RYoung         - Add disable submit to finish button -->

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
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>CommonSupervision/administerCaseload/administerCaseloadCasenoteCLOSummary.jsp</title>
	
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
		
</head>

<body topmargin="0" leftmargin="0">
	<html:form action="/reassignSuperviseeToCLOSummaryAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|21">
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
											<tiles:put name="tab" value="caseloadTab" />
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
												<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;Reassign Supervisee to CLO - Casenote Summary</td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Review CLO selection, click Finish.</li>
													</ul>
												</td>
											</tr>
										</table>
										<!-- END INSTRUCTION TABLE -->
										
										<!--header start--> 
										<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
										</tiles:insert> 
										<!--header end-->
										
										<div class="spacer4px"></div>
										<!-- BEGIN DETAIL TABLE -->
										
										<!-- COURT SELECTED TILE START -->
										<%-- 
										<tiles:insert page="../common/courtSelectedTile.jsp" flush="true">
										</tiles:insert>
										--%>
										<!-- COURT SELECTED TILE END -->
										
										<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td>Court Selected</td>
											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="4" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%">Court</td>
															<td class="formDe">
																<bean:write name="caseAssignmentForm" property="reassignedCourtId" />
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap>CLO Name</td>
															<td class="formDe">
																<bean:write name="caseAssignmentForm" property="officerName" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										
										<div class="spacer4px"></div>
										
										<!-- WORKGROUP SELECTED TILE START -->
										<%-- 
										<tiles:insert page="../common/workgroupSelectedTile.jsp" flush="true">
										</tiles:insert>
										 --%>
										<!-- WORKGROUP SELECTED TILE END -->
										
										<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td>Workgroup Selected</td>
											</tr>
											<tr>
												<td>
													<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr>
															<td>
																<table width="100%" border="0" cellpadding="2" cellspacing="1">
																	<tr class="formDeLabel">															
																		<td>Name</td>
																		<td>Description</td>
																	</tr>
																	<tr>
																	 <td><bean:write name="caseAssignmentForm" property="reassignedWorkGroupName"/></td> 
																	 <td><bean:write name="caseAssignmentForm" property="reassignedWorkGroupDescription"/></td>																 
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										
										<div class="spacer4px"></div>
										<!-- add casenote start-->
										<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
											<tr class="detailHead">
												<td>New Casenote Information</td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="4" cellspacing="1">
														<tr>
															<td class="formDeLabel">Casenote</td>
														</tr>
														<tr class="formDe">
															<td>
																<bean:write name="caseAssignmentForm" property="casenoteText" filter="false"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<br>
										
										<!--add casenotes end-->
										<!-- END DETAIL TABLE -->
										<!-- BEGIN BUTTON TABLE -->
										<table cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<html:submit property="submitAction">
														<bean:message key="button.back" />
													</html:submit> 
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
														<bean:message key="button.finish" />
													</html:submit> 
													<html:submit property="submitAction">
														<bean:message key="button.cancel" />
													</html:submit> 
												</td>
											</tr>
										</table>
										<br>
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
