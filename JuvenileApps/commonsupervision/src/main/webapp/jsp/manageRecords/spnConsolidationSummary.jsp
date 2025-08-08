<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/11/2006	 Hien Rodriguez - Create JSP -->
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
<title><bean:message key="title.heading" /> - manageRecords/spnConsolidationSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin=0 leftmargin="0">
<html:form action="/submitSpnConsolidation" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|2">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
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
				<!-- BEGIN SUMMARY PAGE SECTION -->
					<logic:notEqual name="spnConsolidationForm" property="action" value="confirm">
					<!-- BEGIN HEADING TABLE -->
						<table width=100%>
							<tr>							
								<td align="center" class="header"><bean:message key="prompt.spnConsolidation" />&nbsp;<bean:message key="prompt.summary" /></td>
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
									<li>Review SPN information.  Click Finish to consolidate.</li>
								</ul></td>
							</tr>									
						</table>
					<!-- END INSTRUCTION TABLE -->
					<!-- BEGIN SPN INFORMATION TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr>
								<td class=detailHead colspan="2"><bean:message key="prompt.spnConsolidation" />&nbsp;<bean:message key="prompt.information"/></td>	
							</tr>												 
							<tr>
								<td>
									<table width="100%" cellpadding="4" cellspacing="1">									
										<tr>
											<td class="formDeLabelBottomBorder"><bean:message key="prompt.toBaseSpn" /></td>
											<td class="formDeBottomBorder" colspan="3"><bean:write name="spnConsolidationForm" property="baseSpn" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.name" /></td>
											<td class="formDe"><bean:write name="spnConsolidationForm" property="toPartyName" /></td>
											<td class="formDeLabel"><bean:message key="prompt.dob" /></td>
											<td class="formDe"><bean:write name="spnConsolidationForm" property="toDateOfBirthAsString" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
											<td class="formDe"><bean:write name="spnConsolidationForm" property="toSex" /></td>
											<td class="formDeLabel"><bean:message key="prompt.race" /></td>
											<td class="formDe"><bean:write name="spnConsolidationForm" property="toRace" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.jailIndicator" /></td>
											<td class="formDe" colspan="3"><bean:write name="spnConsolidationForm" property="toJailIndicator" /></td>
										</tr>
										<tr>
											<td colspan=4><img src="/<msp:webapp/>images/spacer.gif"></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.fromAliasSpn" /></td>
											<td class="formDe" colspan="3"><bean:write name="spnConsolidationForm" property="aliasSpn" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					<!-- END SPN INFORMATION TABLE -->
					</logic:notEqual>
				<!-- END SUMMARY PAGE SECTION -->
						
				<!-- BEGIN CONFIRMATION PAGE SECTION -->
					<logic:equal name="spnConsolidationForm" property="action" value="confirm">
						<table width=98% align=center>							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
						<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td align="center" class="confirm">SPN Consolidation successful.</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</table>
					</logic:equal>
				<!-- END CONFIRMATION PAGE SECTION -->
						<br>
					<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<logic:notEqual name="spnConsolidationForm" property="action" value="confirm">
								<tr>											
									<td align=center>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>								
								</tr>		
							</logic:notEqual>
							<logic:equal name="spnConsolidationForm" property="action" value="confirm">
								<tr>											
									<td align=center>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newConsolidation"/></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.mainPage"></bean:message></html:submit>
									</td>								
								</tr>		
							</logic:equal>
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
