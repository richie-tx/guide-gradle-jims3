<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<HTML>
<HEAD>
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
<title><bean:message key="title.heading" /> - reports/reportsMain.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>	
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</HEAD>

<body topmargin="0" leftmargin="0" >
	<div align="center">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" id="mainTable">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
			</tr>
			<tr>
				<td valign="top">
					<table width=100% border="0" cellpadding="0" cellspacing="0" id="commonSupervisionTabsTable">
						<tr>
							<td valign="top">
								<!--tabs start-->
								<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tabid" value="setupTab"/>
								</tiles:insert>
								<!--tabs end-->
							</td>
						</tr>
						<tr>
							<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
						</tr>
					</table>
					<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue" id="blueBorderTable">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
						</tr>
						<tr>
							<td valign="top" align=center>
								<table width=98% border="0" cellpadding="0" cellspacing="0" id="managerFeaturesTabTable">
									<tr>
										<td valign="top">
											<!--tabs start-->
                                            <tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
												<tiles:put name="tabid" value="reportsTab"/>
											</tiles:insert>						
										</td>
									</tr>
									<tr>
										<td bgcolor=#33cc66>
											<table border=0 cellpadding=2 cellspacing=1 id="reportsSubMenuTable">
												<tr>
                                                   <td>&nbsp;<a href="/<msp:webapp/>caseAssignmentReportSearchAction.do?submitAction=Setup">View Assignments(CSCD)</a> <b>|</b> <a href="">Assessments</a> <b>|</b> <a href="">Office/Field Visits</a> <b>|</b> <a href="">PTS Reports</a> </td></tr>
											</table>
                                        </td>
									</tr>
								</table>
								<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen" id="greenBorderTable">
									<tr>
										<td><img src="/<msp:webapp/>images/spacer.gif" height=30></td>
									</tr>
									<tr>
										<td valign="top" align=center>		
											<table width=98% border="0" cellpadding="0" cellspacing="0" >								
												<tr>
													<td valign="top" align=center class=subHead><br><br><br><br>Welcome To Reports<br><br><br><br><br><br><br><br><br><br><br></td>
												</tr>
											</table>
										</td>
									</tr>									
								</table>								
							</td>							 
						</tr>
					</table>
					
				</td>
			</tr>			
		</table>
	</div>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</HTML>
