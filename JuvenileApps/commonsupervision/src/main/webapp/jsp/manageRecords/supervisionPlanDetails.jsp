<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/11/2008 C Shimek        - created JSP

<%-- NOTE: 
	This is a modified copy of supervisionPlanDetails.jsp located in administerCaseloadCSCD/assessments and
	is intended to be used for popup window display only.  The detail layout of this page should always match
	the other page --%>
	 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.PDCodeTableConstants" %>
<%@page import="naming.Features" %>

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
<title><bean:message key="title.heading" /> - manageRecords/supervisionPlanDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/handleSupervisionPlanDetails" target="content">
<div align="center">
<!-- BEGIN MAIN TABLE -->	
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td align="center">
<!-- BEGIN HEADING TABLE -->
			<table width="100%">
				<tr>							
					<td align="center" class="header">
						<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.supervisionPlan"/>&nbsp;<bean:message key="prompt.details" />
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
<!-- START SUPERVISION PLAN DATE TABLE -->
			<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
				<tr>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.supervisionPlanDate"/></td>
					<td class="formDe"><bean:write name="supervisionPlanForm" property="supervisionPlanDateStr"/></td>
				</tr>
			</table>
			<div class="spacer4px"></div>
<!-- END SUPERVISION PLAN DATE TABLE-->
<!-- START SUPERVISION PLAN TABLE -->
			<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
					<td>
						<bean:message key="title.supervisionPlan"/>
					</td>
					<td align="center" class="bodyText">
						<span class="errorAlert">
							<bean:write name="supervisionPlanForm" property="statusDesc" /> 
						</span>
					</td>
					<td align="right" nowrap>
						<a href="javascript:openWindow('/CommonSupervision/displaySupervisionOrderConditions.do?submitAction=Link&defendantId=<bean:write name="supervisionPlanForm" property="defendantId" />');" class=blackSubNav>View Conditions</a>				
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div style="width:100%; text-align:center;">Last Change: <span class="boldText"><bean:write name="supervisionPlanForm" property="lastChageDateStr"/> <bean:write name="supervisionPlanForm" property="lastChangeUserName"/></span></div>
						<table width="100%" cellpadding="2" cellspacing="1">
							<tr>
								<td>
									<div class="borderTable" style="padding:2px">
										<DIV class="formDeLabel spacer"><B><bean:message key="prompt.problem" /></B></DIV>
										<bean:write name="supervisionPlanForm" property="problem" filter="false"/>
									</div>
									<div class="spacer4px"></div>
									<div class="borderTable" style="padding:2px">
										<DIV class="formDeLabel spacer"><B><bean:message key="prompt.behavioralObjective" /></B></DIV>
										<bean:write name="supervisionPlanForm" property="behaviorObjective" filter="false"/>
									</div>
									<div class="spacer4px"></div>
									<div class="borderTable" style="padding:2px">
										<DIV class="formDeLabel spacer"><B><bean:message key="prompt.offenderActionPlan" /></B></DIV>
										<bean:write name="supervisionPlanForm" property="offenderActionPlan" filter="false"/>
									</div>
									<div class="spacer4px"></div>
									<div class="borderTable" style="padding:2px">
										<DIV class="formDeLabel spacer"><B><bean:message key="prompt.csoActionPlan" /></B></DIV>
										<bean:write name="supervisionPlanForm" property="csoActionPlan" filter="false"/>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!--  END SUPERVISION PLAN TABLE -->
			<div class="spacer4px"></div>
<!-- BEGIN BUTTON TABLE -->
			<table border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td align="center">
						<input type="button" name="close" value="Close Window" onclick="window.close()") >
					</td>   
				</tr>
			</table>
<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
<br>
<!-- END  MAIN TABLE -->
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>