<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/09/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - manageRecords/spnConsolidation.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="spnConsolidationForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/displaySpnConsolidationSummary" target="content" focus="baseSpn">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|1">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
					<!--tabs end-->
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
								<td align="center" class="header"><bean:message key="prompt.spnConsolidation" /></td>						
							</tr>
						</table>
					<!-- END HEADING TABLE -->
					<!-- BEGIN ERROR TABLE -->
						<table width=98% align=center>							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
					<!-- END ERROR TABLE -->
					<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul>
									<li>Enter the From Alias SPN and the To Base SPN.  Click Next.</li>
								</ul></td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.requiredFields"/></td>												
							</tr>										
						</table>
					<!-- END INSTRUCTION TABLE -->																					
					<!-- BEGIN SPN TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr>
								<td class=detailHead colspan="2"><bean:message key="prompt.spnConsolidation" />&nbsp;<bean:message key="prompt.information"/></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding=2 cellspacing=1 border="0">		
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.toBaseSpn" /></td>
											<td class="formDe"><html:text property="baseSpn" maxlength="8" size="8"/></td>
										</tr>										 
										<tr>
											<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.2.diamond"/><bean:message key="prompt.fromAliasSpn" /></td>
											<td class="formDe"><html:text property="aliasSpn" maxlength="8" size="8"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					<!-- END SPN TABLE -->
						<br>
					<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return validateSpnConsolidationForm(this.form) && disableSubmit(this, this.form);">
										<bean:message key="button.next"></bean:message></html:submit>&nbsp;
									<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>
						</table>
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
