<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/24/2009	 Chris Walters - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administersupervisee/displaySuperviseeDataControlSearchResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySuperviseeCreditDataControl" >

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
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
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
					<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">CSCD - Supervisee Credit Data Control Search Results</td>						
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
								<td><ul>
										<li>Click Update to update the current supervisee credit record.</li>
										<li>Select a Credit History record and click Correct or Delete to perform those actions on historical records.</li>
									</ul>
								</td>
							</tr>										
						</table>
					<!-- END INSTRUCTION TABLE -->	
					<tiles:insert page="superviseeInfoTile.jsp" flush="true">
					</tiles:insert>

					<br>
					<!-- BEGIN  TABLE -->																									
							<span id="caseSearch" >
								<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">

										<td>Current Supervisee Credit</td>
									</tr>
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
												<tr class="formDeLabel">
													<td>CJAD Program Unit</td>
													<td>CJAD Program Unit Assignment Date</td>

													<td>Workload Credit Position</td>
													<td>Create Date</td>
												</tr>
												<tr>
													<td><bean:write name="superviseeCreditDataControlForm" property="programUnitName" /></td>
													<td><bean:write name="superviseeCreditDataControlForm" property="programUnitAssignDate" /></td>
													<td><span id="officerAssignmentInfo"><bean:write name="superviseeCreditDataControlForm" property="workloadCreditStaffPositionName" /></span></td>

													<td><span id="officerAssignmentCreateDate"><bean:write name="superviseeCreditDataControlForm" property="programUnitAssignDate" /></span></td>
												</tr>
											</table>

										</td>
									</tr>
								</table>

								<div class="spacer4px">
									<html:submit property="submitAction" ><bean:message key="button.update"/></html:submit>
								</div>

								<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td>Supervisee Credit History</td>
									</tr>
									<tr>
										<td>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="notFirstColumn" id="uniqueID">

												<tr class="formDeLabel">
													<td valign="top" width="1%"></td>
													<td>CJAD Program Unit<jims2:sortResults beanName="superviseeCreditDataControlForm" results="superviseeHistory" primaryPropSort="assignedProgramUnitName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1" levelDeep="2"/></td>
													<td>CJAD Program Unit Assignment Date<jims2:sortResults beanName="superviseeCreditDataControlForm" results="superviseeHistory" primaryPropSort="programUnitAssignmentDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="2" levelDeep="2"/></td>
													<td>Workload Credit Position<jims2:sortResults beanName="superviseeCreditDataControlForm" results="superviseeHistory" primaryPropSort="caseloadCreditStaffPositionName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="2"/></td>
													<td>Create Date<jims2:sortResults beanName="superviseeCreditDataControlForm" results="superviseeHistory" primaryPropSort="createDate" primarySortType="DATE"  defaultSortOrder="DESC" defaultSort="true" sortId="4" levelDeep="2"/></td>
												</tr>
												<logic:notEmpty name="superviseeCreditDataControlForm" property="superviseeHistory">
													<logic:iterate id="superviseeHistory" name="superviseeCreditDataControlForm" property="superviseeHistory" indexId="index">                																					           												        
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td align="center">																
																<html:radio name="superviseeCreditDataControlForm" property="selectedSuperviseeHistoryId" idName="superviseeHistory" value="superviseeHistoryId"></html:radio>
															</td>
															<td><bean:write name="superviseeHistory" property="assignedProgramUnitName" /></td>
															<td><bean:write name="superviseeHistory" property="programUnitAssignmentDate" /></td>
															<td><bean:write name="superviseeHistory" property="caseloadCreditStaffPositionName" /></td>		
															<td><bean:write name="superviseeHistory" property="createDate" /></td>
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												
											</table>
											</td>
										</tr>
									</table>

							</span>
						<br>
					<!-- END  TABLE -->
					
					<!-- BEGIN BUTTON TABLE -->
					<div class="spacer">
						<html:submit property="submitAction" ><bean:message key="button.correct"/></html:submit>
						<html:submit property="submitAction" ><bean:message key="button.delete"/></html:submit>
					</div>
					<div class="spacer">
						<html:submit property="submitAction" ><bean:message key="button.back"/></html:submit>
						<html:submit property="submitAction" ><bean:message key="button.cancel"/></html:submit>
					</div>
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

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>