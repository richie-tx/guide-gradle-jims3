<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
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
<title><bean:message key="title.heading" /> - Staff Position History Report</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
</head>
<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handlePositionReport" target="content">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" align="center">
			<!-- BEGIN HEADING TABLE -->
			<table width=100% id="headingTable">
				<tr>
					<td align="center" class="header">
						<bean:message key="title.CSCD" /> - Position History Report					
					</td>
				</tr>
			</table>
			<!-- END HEADING TABLE -->			
			<!-- BEGIN ERROR TABLE -->
			<table width="98%" align="center">
				<tr>
					<td align="center" class="errorAlert"><html:errors /></td>
				</tr>
			</table>
			<!-- END ERROR TABLE --> <!-- BEGIN Results TABLE -->

			<div class="legendSmallText" style="width: 98%; text-align: left">
				<span class="inactivePOI">Italic POI codes</span> signify they are retired.
			</div>
			<!-- program unit history start--> 
			<span id="puReport">
			<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
				<tr class="detailHead">
					<td title="Probation Officer Indicator">
						<bean:message key="prompt.poi" /> 
						<jims:sortResults sortId="1" beanName="adminStaffReportForm" results="filteredList" 
						primaryPropSort="probOfficerInd" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" />
					</td>
					<td title="Position Name">
						<bean:message key="prompt.pos" /> 
						<bean:message key="prompt.name" /> 
						<jims:sortResults sortId="2" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="positionName" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td title="Position Type">
						<bean:message key="prompt.pos" /> 
						<bean:message key="prompt.type" /> 
						<jims:sortResults sortId="3" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="positionTypeDesc" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td>
						<bean:message key="prompt.name" /> 
						<jims:sortResults sortId="4" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="user.userName.formattedName" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td>
						<bean:message key="prompt.cjad" /> 
						<jims:sortResults sortId="5" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="user.cjad" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td>
						<bean:message key="prompt.cstsOfficerType" /> 
						<jims:sortResults sortId="6" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="officerTypeDesc" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td>
						<bean:message key="prompt.jobTitle" /> 
						<jims:sortResults sortId="7" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="jobTitleDesc" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td title="Program Unit">
						<bean:message key="prompt.pu" /> 
						<jims:sortResults sortId="8" beanName="adminStaffReportForm" results="filteredList"
							primaryPropSort="programUnitDesc" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td>
						<bean:message key="prompt.user" /> 
						<jims:sortResults sortId="9" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="createdBy.userName.formattedName" primarySortType="STRING" defaultSortOrder="DESC" />
					</td>
					<td>
						<bean:message key="prompt.effectiveDate" /> 
						<jims:sortResults sortId="10" beanName="adminStaffReportForm" results="filteredList"
						primaryPropSort="effectiveDateAsStr" primarySortType="DATE" defaultSortOrder="DESC" 
						secondPropSort="assignedDateAsTimeStr" secondarySortType="STRING" />
					</td>
				</tr>
				<%int ctr = 0; %>
				<logic:notEmpty name="adminStaffReportForm" property="filteredList">
					<nest:iterate id="retiredPOI" name="adminStaffReportForm"
						property="filteredList">

						<tr
							class='<% out.print( ((++ctr)%2 == 1)?"normalRow":"alternateRow" );%>'>
							<td class="boldText">
								<nest:equal property="retired" value="true">
									<i><nest:write property="probOfficerInd" /></i>
								</nest:equal> 
								<nest:equal property="retired" value="false">
									<nest:write property="probOfficerInd" />
								</nest:equal>
							</td>
							<td class="boldText"><nest:write property="positionName" /></td>
							<td><nest:write property="positionTypeDesc" /></td>
							<td><nest:write property="user.userName.formattedName" /></td>
							<td><nest:write property="user.cjad" /></td>
							<td><nest:write property="officerTypeDesc" /></td>
							<td><nest:write property="jobTitleDesc" /></td>
							<td><nest:write property="programUnitDesc" /></td>
							<td><nest:notEmpty property="createdBy">
								<nest:notEmpty property="createdBy.userName">
									<nest:write property="createdBy.userName.formattedName" />
								</nest:notEmpty>
							</nest:notEmpty> &nbsp;</td>
							<td><nest:write property="effectiveDateAsStr" /></td>
						</tr>
					</nest:iterate>
				</logic:notEmpty>
			</table>
			</span> <!-- program unit history end--> <!-- END Results TABLE --> <br>
			<!-- BEGIN BUTTON TABLE -->
			<table border="0" width="100%">
				<tr>
					<td align="center"><logic:notEmpty name="adminStaffReportForm"
						property="filteredList">
						<input type="button" value="Print" onClick="window.print()">
					</logic:notEmpty> 
					<input type="button" value="Close Window" onClick="window.close()" /></td>
				</tr>
			</table>
			<!-- END BUTTON TABLE --></td>
		</tr>
	</table>
	</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
