<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  08/14/2009  C Shimek   created JSP (Missing for ER54249 changes -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features"%>
<%@ page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - adminStaff/retirePositionSummary.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handlePositionSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|31">
<input type="hidden" name="selectedValue" value="<bean:write name="adminStaffForm" property="position.positionId"/> "/>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN SUPERVISION TABS TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><!--tabs start--> <tiles:insert
							page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="setupTab" />
						</tiles:insert> <!--tabs end--></td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END SUPERVISION TABS TABLE -->				
<!-- BEGIN BLUE BORDER TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN MANAGE FEATURES TABS TABLE -->						
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top">
										<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
											<tiles:put name="tabid" value="positionsTab" />
										</tiles:insert>
									</td>
								</tr>
							</table>
<!-- END MANAGE FEATURES TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->					
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
										<table width="100%">
											<tr>
												<td align="center" class="header">
													<bean:message key="title.CSCD" /> - Retire Position Summary
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
<!-- END ERROR TABLE --> 
<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Click Finish to retire position.</li>
													</ul>
												</td>
											</tr>
										</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN POSITION INFO TABLE -->
										<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
											<tr>
												<td class="detailHead">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td class="detailHead"><bean:message key="prompt.position"/> <bean:message key="prompt.information"/></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<tiles:insert page="positionInfoTile.jsp" flush="true">
														<tiles:put name="position" beanName="adminStaffForm" beanProperty="position"/>
													</tiles:insert>
												</td>
											</tr>
										</table>
<!-- END POSITION INFO TABLEe-->
										<br>
<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="disableSubmit(this, this.form)" ><bean:message key="button.finish" /></html:submit>			
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->					
							<br>
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->		
			</td>
		</tr>
	</table>
</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>