<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/09/2006	 Hien Rodriguez - Create JSP -->
<!-- 11/12/2008  C Shimek       - Activity#55232 added new split topic button -->

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
<title><bean:message key="title.heading" /> - manageRecords/spnSplitConfirmation.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/submitSpnSplit" target="content">
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
							<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
					</table>
<!-- BEGIN BLUE BORDER TABLE -->					
					<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
						<tr>
							<td><img src=../../common/images/spacer.gif height=5></td>
						</tr>
						<tr>
							<td valign="top" align="center">
<!-- BEGIN BLUE CONFIRMATION BOX TABLE -->								
								<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
									<tr>
										<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
									</tr>
									<tr>
										<td>
											<br>
												<div class="confirm">
													<bean:write name="spnSplitForm" property="confirmationMessage" />
												</div>
											<br>
										</td>
									</tr>
								</table>
<!-- END BLUE CONFIRMATION BOX TABLE -->									
								<br>
<!-- BEGIN BUTTON TABLE -->
								<table border="0" width="100%">
									<tr>
										<td align="center">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSplit"></bean:message></html:submit>&nbsp;
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSplitTopic"></bean:message></html:submit>&nbsp;
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.mainPage"/></html:submit>
										</td>
									</tr>
								</table>
<!-- END BUTTON TABLE -->
							</td>
						</tr>
					</table>
<!-- END BLUE BORDER TABLE -->					
				</td>
			</tr>
		</table>
	</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>