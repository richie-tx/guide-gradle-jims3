<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/23/2012  RYoung - #73271 Changed Grey-out FINISH button after selected. -->

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
	<title><bean:message key="title.heading" /> - assignSupervisee/superviseeToPUSummary.jsp</title>
	
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
		
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
	<html:form action="/displaySummaryForReAssignSuperviseeToProgramUnit" target="content">
	  <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|17">	 
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>

					<td valign=top>

						<table width=100% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
									<!--blue tabs start-->
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>
									<!--blue tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
						</table>
						<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>		
								<td valign=top align=center>
											
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header" ><bean:message key="title.CSCD"/>&nbsp;-
													Reassign Supervisee to Program Unit Summary
											</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
													
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Review Program Unit Assignment. Click Finish to assign Supervisee to Program Unit.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<!--header start-->
									<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
											
									<br>
									
									<!--PROGRAM UNIT ASSIGNMENT START-->
									<tiles:insert page="../../common/programUnitAssignmentTile.jsp" flush="true">
									</tiles:insert>
									<!--PROGRAM UNIT ASSIGNMENT END-->
									
									<!-- BEGIN DETAIL TABLE -->
									<br>
									<table width="98%" border="0" cellspacing=0 cellpadding=2 class=borderTableBlue>
										<tr class=detailHead>
											<td>Casenote Information</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="4" cellspacing="1">
													<tr>
														<td class=formDeLabel valign=top nowrap width=1%>Casenote Date</td>
														<td class=formDe>
															<bean:write name="caseAssignmentForm" property="casenoteDate"/>
														</td>
														<td class=formDeLabel valign=top nowrap width=1%>Casenote Time</td>
														<td class=formDe>
															<bean:write name="caseAssignmentForm" property="casenoteTime"/>
														</td>
													</tr>																			
													<tr>
														<td colspan=4 class=formDeLabel>Casenote</td>
													</tr>
													<tr>
														<td colspan=4 class=formDe>
															<bean:write name="caseAssignmentForm" property="casenoteText" filter="false"/>
														</td>
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
												<html:submit property="submitAction">
													<bean:message key="button.back" />
												</html:submit> 
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
													<bean:message key="button.assignsuperviseetoprogramunit.confirmation.confirm" />
												</html:submit> 
												<html:submit property="submitAction">
													<bean:message key="button.cancel" />
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
	<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
