<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/25/2009	 CShimek        - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
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
<title><bean:message key="title.heading" /> - posttrial/caseAssignmentDataControlSearch.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="spnSplitForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/posttrial/caseAssignmentDataControlSearch.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCaseAssignmentDataControlSearchResults" target="content" focus="caseNum">
<input type="hidden" name="helpFile" value="#|#">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
<!-- BEGIN TAB TABLE -->    	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">CSCD - <bean:message key="prompt.caseAssignment"/> <bean:message key="prompt.dataControl"/> <bean:message key="prompt.search"/></td>						
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
									<li>Enter the required field(s) and click Submit to see results.</li>
								</ul></td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.requiredFields"/></td>												
							</tr>										
						</table>
<!-- END INSTRUCTION TABLE -->																					
<!-- BEGIN SEARCH TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead" colspan="2"><bean:message key="prompt.searchByCase" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1" border="0">		
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.case#" /></td>
											<td class="formDe" ><html:text name="caseAssignmentDataControlForm" property="caseNum" maxlength="12" size="15"/></td>
										</tr>										 
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.CDI" /></td>
											<td class="formDe"><html:text name="caseAssignmentDataControlForm" property="cdi" maxlength="3" size="4"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
<!-- END SEARCH TABLE -->
						<br>
<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return validateInputs(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit"/></html:submit>
									<html:submit property="submitAction" onclick="return resetInputs(this.form);"><bean:message key="button.refresh"/></html:submit>
								</td>
							</tr>
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
			<br>			
		</td>
	</tr>
</table>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>