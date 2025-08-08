<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/09/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/wisconsinNeedsCreateUpdate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayWisconsinCreateUpdate" target="content">
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
		<!-- BEGIN SUPERVISEE INFORMATION -->
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>		
		<!-- END SUPERVISEE INFORMATION -->	
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
										<logic:equal name="wisconsinAssessmentForm" property="action" value="create">
											<logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.assessment"/>&nbsp;-
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|8">
											</logic:equal>
											<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.reassessment"/>&nbsp;-
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|51">
											</logic:notEqual>
										</logic:equal>
										
										<logic:equal name="wisconsinAssessmentForm" property="action" value="update">
											<logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.assessment"/>&nbsp;-
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|48">
											</logic:equal>
											<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.reassessment"/>&nbsp;-
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|12">
											</logic:notEqual>
										</logic:equal>
									
                                        <bean:message key="prompt.needs"/> 
                                    </div>
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
											<li>Complete all questions and then select Next button.</li>
										</ul>
									</div>
									<div class="required" align="left">All fields are required.</div>
									<!-- END instructions -->
									
									<!--Needs assessment start-->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.needs"/>&nbsp;<bean:message key="prompt.assessment"/></td>
										</tr>		
										
										<tr>
        									<td>
          										<table width='100%' cellpadding="2" cellspacing="1" border="0">
          											<jims2:cscquestions name="wisconsinAssessmentForm" property="needsAssessmentQuestionsList" type="input" columns="1"/>
          										</table>
      									 	</td>
      								 	</tr>							
									</table>
									<!--needs assessment end-->
									
									<!-- Start  BUTTON TABLE -->
									<html:hidden name="wisconsinAssessmentForm" property="wisconsinScreenType" value="needs" />
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<!--<input type="Reset" value="Reset" onclick="setCellText('tnScore','-6');setCellText('needsLevel','4')"/>
												<input type="button" value="Calculate Score" name="" onClick="setCellText('tnScore','-6');setCellText('needsLevel','4');show('nextButton', 1, 'inline')">
												-->
											</td>
										</tr>
										<tr>
											<td align="center">
											    <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
											    <logic:equal name="wisconsinAssessmentForm" property="assessmentIncomplete" value="true">
											    	<html:submit property="submitAction"><bean:message key="button.saveContinue" /></html:submit>
											    </logic:equal>	
											    <logic:equal name="wisconsinAssessmentForm" property="assessmentIncomplete" value="false">
													<html:submit property="submitAction"><bean:message key="button.next" /></html:submit>
												</logic:equal>	
											    <html:reset><bean:message key="button.reset"/></html:reset>
											    <html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
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
<!-- END  MAIN TABLE -->
<br>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
