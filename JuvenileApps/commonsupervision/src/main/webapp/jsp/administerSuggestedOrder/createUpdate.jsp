<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/14/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/createUpdate.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="suggestedOrderForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySuggestedOrderSelectOffense" target="content" focus="orderName">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>    	
  	</tr>
  	<tr>
    	<td valign="top" align="center">
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true" />																				
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">					
					<!-- BEGIN HEADING TABLE -->						
						<table width="98%">
							<tr>
								<td align="center" class="header">
									<logic:equal name="suggestedOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|4">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="update">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|11">
										<bean:message key="prompt.update" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|18">
										<bean:message key="prompt.copy" />
									</logic:equal>
									<bean:message key="title.suggestedOrder" />
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
					<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<logic:equal name="suggestedOrderForm" property="action" value="create">
									<td><ul>
										<li>Enter the required fields.</li>
										<li>Select the Add Offense(s) button if you need to select 1 or multiple offenses for this order.</li>
										<li>Select the No Offenses button if you do not need any offenses for this order.</li>
									</ul></td>
								</logic:equal>
								<logic:notEqual name="suggestedOrderForm" property="action" value="create">
									<td><ul>
										<li>Enter the required fields.</li>
										<li>Select the Update Offense(s) button if you wish to update or add offenses to this order.</li>
										<li>Select the Skip Offenses button if you do not need any offenses for this order.</li>
									</ul></td>
								</logic:notEqual>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
							</tr>
						</table>						
					<!-- BEGIN SUGGESTED ORDER TABLE -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">						
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.suggestedOrder" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.name" /></td>
											<td class="formDe"><html:text property="orderName" maxlength="30" size="35"/></td>	
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.description" /></td>
											<td class="formDe"><html:text property="orderDescription" maxlength="100" size="70"/></td>	
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br>						
						<!-- BEGIN BUTTON TABLE -->						
						<table width="98%" border="0">
							<tr>
								<td align="center">																						
									<logic:equal name="suggestedOrderForm" property="action" value="create">										
										<html:submit property="submitAction" onclick="return validateSuggestedOrderForm(this.form)&& disableSubmit(this, this.form);"><bean:message key="button.addOffenses"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return validateSuggestedOrderForm(this.form)&& disableSubmit(this, this.form);"><bean:message key="button.noOffenses"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction"><bean:message key="button.reset"></bean:message></html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																											
									</logic:equal>
									<logic:notEqual name="suggestedOrderForm" property="action" value="create">										
										<html:submit property="submitAction" onclick="return validateSuggestedOrderForm(this.form)&& disableSubmit(this, this.form);"><bean:message key="button.updateOffenses"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return validateSuggestedOrderForm(this.form)&& disableSubmit(this, this.form);"><bean:message key="button.skipOffenses"></bean:message></html:submit>&nbsp;
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																											
									</logic:notEqual>													
								</td>
							</tr>
						</table>
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table><br>	
		</td>
	</tr>
</table>
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
