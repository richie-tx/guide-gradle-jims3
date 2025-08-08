<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/08/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 02/16/2009 C Shimek       - #63449 revised submit button to save and continue button -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!--  jims2 CUSTOM TAG lIBRARIES FOR QUESTION ANSWER DISPLAY-->
<%@ taglib uri="../../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.CSCAssessmentConstants"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/scsScreenOneCreateUpdate.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>
function validateScsScreenOneForm(theForm)
{
	clearAllValArrays();
	customValRequired("assessmentDateStr","<bean:message key='errors.required' arg0='Assessment Date'/>","");
	addMMDDYYYYDateValidation("assessmentDateStr","<bean:message key='errors.date' arg0='Assessment Date'/>","");
	return validateCustomStrutsBasedJS(theForm);
}
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySCSCreateUpdate" target="content" focus="assessmentDateStr">
<div align="center">
<!-- BEGIN MAIN TABLE -->	
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
		<!-- BEGIN BLUE TABS TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
		<!-- END BLUE TABS TABLE -->  
		<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
		<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>		
		<!-- END SUPERVISEE INFORMATION TABLE  -->	
                        <div class="spacer4px"></div> 
		 <!-- BEGIN GREEN TABS TABLE -->	
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="AssessmentsTab" />
									</tiles:insert> 
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
		<!-- END GREEN TABS TABLE -->	
		<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
								
									<!-- BEGIN HEADING -->
									<div class="header"><bean:message key="title.CSCD"/>&nbsp;-
										<logic:equal name="scsAssessmentForm" property="action" value="update">
											<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|33"></logic:equal>
										<logic:equal name="scsAssessmentForm" property="action" value="create">
											<bean:message key="prompt.new"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|27"></logic:equal>
										 <bean:message key="title.SCSInventory"/></div>
										<!-- END HEADING -->
										
									<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
									<!-- END ERROR TABLE -->
									
									<!-- BEGIN instructions -->
									<div class="instructions" align="left">
										<ul>
											<li>Select appropriate scores. Click Next.</li>
										</ul>
									</div>
									<div class="required" align="left"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></div>
									<!-- END instructions -->
									
									<!-- assessments date start -->
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.assessmentDate"/></td>
											<td class="formDe">
												<SCRIPT LANGUAGE="JavaScript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT>
												<html:text name="scsAssessmentForm" property="assessmentDateStr" size="10" maxlength="10" title="(mm/dd/yyyy)" />
												<A HREF="#" onClick="cal1.select(scsAssessmentForm.assessmentDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar" /></A>												
											</td>
										</tr>
									</table>
									<!-- assessments date end -->
									
									<div class="spacer4px"></div>
									
									<!-- scs assessment start -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.SCS"/>&nbsp;<bean:message key="prompt.score"/>&nbsp;<bean:message key="prompt.entry"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border="0">
													<jims2:cscquestions name="scsAssessmentForm" property="scsScreenOneQuestionsList" type="input" columns="2"/>
												</table>
											</td>
										</tr>
									</table>
									<!-- scs end -->													

									<div class="spacer4px"></div>
									
									<!-- BEGIN BUTTON TABLE -->
									<html:hidden name="scsAssessmentForm" property="scsScreenType" value="screenOne" />
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<logic:notEqual name="scsAssessmentForm" property="action" value="create">
											    	<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
											    </logic:notEqual>
											    <logic:equal name="scsAssessmentForm" property="assessmentIncomplete" value="true">
											    	<html:submit property="submitAction" onclick="return validateScsScreenOneForm(this.form);"> <bean:message key="button.saveContinue" /></html:submit>
												</logic:equal> 
												<logic:equal name="scsAssessmentForm" property="assessmentIncomplete" value="false"> <!-- update -->
													<html:submit property="submitAction" onclick="return validateScsScreenOneForm(this.form);"> <bean:message key="button.saveContinue" /></html:submit>
											<%-- 	<html:submit property="submitAction" onclick="return validateScsScreenOneForm(this.form);"> <bean:message key="button.submit" /></html:submit> --%>
												</logic:equal>	
												<html:reset><bean:message key="button.reset"/></html:reset>
						                        <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<!-- END GREEN BORDER TABLE -->	
						<div class="spacer4px"></div>
					</td>
				</tr>
			</table>
			<!-- END BLUE BORDER TABLE -->
			<br>
		</td>
	</tr>
</table>
<br>
<!-- END  MAIN TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
