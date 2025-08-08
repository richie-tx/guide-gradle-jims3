<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/12/2007	HRodriguez	Create JSP -->
<%-- 02/28/2008	LDeen		Defect #49819 integrate help    --%>
<%-- 11/20/2008 C Shimek    Defect #55178 added on page sort to workgroup list by name --%>
<%-- 04/23/2009 C Shimek    Defect #59067 replaced included tile for Program Unit Assignment display with local code as change to tile to remove workgroup affects 9 other jsps and as many action changes --%>

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
	<title>CommonSupervision/administerCaseload/administerCaseloadWorkgroupList.jsp</title>
	
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
	<script type="text/javascript">
		function hideButton(){
			show("Next", 0);
		}
		
		function showButton(){
			show("Next", 1);
		}
	</script>
</head>

<body topmargin="0" leftmargin="0" onload="hideButton();">
	<html:form action="/reassignSuperviseeToPUWorkgroupAction" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|15">
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
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;
												Reassign Supervisee to Program Unit - Select Workgroup
											</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select a Workgroup.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!--header start-->
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
									<br>
									
									<!--PROGRAM UNIT ASSIGNMENT START-->
							<%-- 		<tiles:insert page="../common/programUnitAssignmentTile.jsp" flush="true">
									</tiles:insert>  --%>
									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignment" /></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="4" cellspacing="1">
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programUnit" /></td>
														<td class="formDe"><bean:write name="caseAssignmentForm" property="programUnitName" /></td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programUnit" /> <bean:message key="prompt.assignmentDate" /></td>
														<td class="formDe"><bean:write name="caseAssignmentForm" property="programUnitAllocationDate" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>			
									<!--PROGRAM UNIT ASSIGNMENT END-->
									
									<br>
									
									<!-- BEGIN DETAIL TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr class="detailHead">
											<td width="1%"></td>
											<td>
												<bean:message key="prompt.name" />
												<jims2:sortResults beanName="caseAssignmentForm" results="workGroupsList" primaryPropSort="workgroupName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="2" hideMe="true" />
											</td>
											<td><bean:message key="prompt.description" /></td>
										</tr>
					                    <logic:iterate id="workGroupIndex" name="caseAssignmentForm" property="workGroupsList" indexId="index">
										<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											<td width="1%">
												<input type="radio" name="workGroupId" value=<bean:write name="workGroupIndex" property="workgroupId" /> onclick="showButton();"/>
											</td>
											<td>
											<a href="javascript:openWindow('/<msp:webapp/>handleWorkgroupSelection.do?submitAction=<bean:message key="button.details"/>&workgroupId=<bean:write name="workGroupIndex" property="workgroupId"/>')"><bean:write name="workGroupIndex" property="workgroupName"/></a>
											</td>
											<td><bean:write name="workGroupIndex" property="workgroupDescription"/></td>
										</tr>
										</logic:iterate>
									</table>
									<!-- END DETAIL TABLE -->
									<br>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0">
										<tr align="center">
											<td>
												<html:submit property="submitAction">
													<bean:message key="button.back" />
												</html:submit> 
											</td>
											<td id="Next">
												<html:submit property="submitAction">
													<bean:message key="button.next" />
												</html:submit> 
											</td>
											<td>
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>