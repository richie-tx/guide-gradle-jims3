<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/19/2010  CShimek     #67005 Added sort to POI -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

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
	<title>CommonSupervision/administerCaseload/supervisorWorkload.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" >
	<html:form action="/supervisorWorkloadAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|9">
		<div align="center">
<!-- BEGIN FULL PAGE TABLE -->		
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">View Workload</td>
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
<!-- BEGIN DETAIL TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Caseload Summary for Supervisor <bean:write name="caseAssignmentForm" property="officerCaseload.selectedSupervisorName"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border="0">
													<tr class="formDeLabel">
														<td>
															<bean:message key="prompt.poi" />
															<jims2:sortResults beanName="caseAssignmentForm" results="caseloads" primaryPropSort="probationOfficerInd" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" hideMe="true"/>
														</td>
														<td><bean:message key="prompt.positionName" /></td>
														<td><bean:message key="prompt.cjad" /></td>
														<td><bean:message key="prompt.officerName" /></td>
														<td align="center">LOS0</td>
														<td align="center">LOS1</td>
														<td align="center">LOS2</td>
														<td align="center">LOS3</td>
														<td align="center">LOS4</td>
														<td align="center">IND</td>
														<td align="center">Workload</td>
													</tr> 

												    <logic:iterate id="caseloadIndex" name="caseAssignmentForm" property="caseloads">
								  						<tr class= "alternateRow">
															<td><bean:write name="caseloadIndex" property="probationOfficerInd"/></td>
															<td><bean:write name="caseloadIndex" property="positionName"/></td>
															<td><bean:write name="caseloadIndex" property="cjad"/></td>
															<td><bean:write name="caseloadIndex" property="officerName"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los0" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los1" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los2" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los3" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los4" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="ind" formatKey="number.format.double"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="workload" formatKey="number.format.double"/></td>	
														</tr>
														<tr>
															<td colspan="4" align="right"><bean:message key="prompt.numberOfSpns" /></td>
															<%-- <td align="center"><bean:write name="caseloadIndex" property="countTotal"/></td> --%>
															<td align="center"><bean:write name="caseloadIndex" property="los0Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los1Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los2Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los3Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="los4Count"/></td>
															<td align="center"><bean:write name="caseloadIndex" property="indCount"/></td>
														</tr>
													</logic:iterate>												
												</table>
											</td>
										</tr>
									</table>
<!-- END DETAIL TABLE -->
									<br/>
<!-- BEGIN BUTTON TABLE -->
									<table border="0">
										<tr align="center">
											<td>
												<input type="button" value="Close" onclick="window.close()" />																												
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
<!-- END FULL PAGE TABLE -->			
		</div>
	</html:form>
	<div align="center"><script type="text/javascript">renderBackToTop()</script></div>	
</body>
</html:html>