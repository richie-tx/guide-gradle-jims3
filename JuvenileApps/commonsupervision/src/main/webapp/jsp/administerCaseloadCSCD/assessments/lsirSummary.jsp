<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/07/2008 Debbie Williamson - Converted PT to JSP -->
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/lsirSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitLSIRAssessment" target="content">
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
									
										<logic:equal name="lsirAssessmentForm" property="action" value="create">
											<logic:equal name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.assessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|41">
											</logic:equal>
											<logic:notEqual name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.reassessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|47">
											</logic:notEqual>
										</logic:equal>
									
										<logic:equal name="lsirAssessmentForm" property="action" value="update">
											<logic:equal name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.assessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|45">
											</logic:equal>
											<logic:notEqual name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.reassessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|49">
											</logic:notEqual>
										</logic:equal>
										
										<logic:equal name="lsirAssessmentForm" property="action" value="delete">
											<logic:equal name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.delete"/>&nbsp;<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.assessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|46">
											</logic:equal>
											<logic:notEqual name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.delete"/>&nbsp;<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.reassessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|46">
											</logic:notEqual>
										</logic:equal>
										
										<bean:message key="title.summary"/>  
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
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<ul>
													<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END instructions -->
									
									<!--assessment date start-->
												<table width="98%" cellpadding="4" cellspacing="1" class="borderTableBlue">
													<tr>
														<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate" /></td>
														<td class="formDe">
															<bean:write name="lsirAssessmentForm" property="assessmentDateStr" />
														</td>
													</tr>
												</table>
									<!--assessment date end-->
									
									<div class="spacer4px"></div>
									
									<!--lsi assessment start-->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.score"/>&nbsp;<bean:message key="prompt.entry"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="4" cellspacing="1">
													<jims2:cscquestions name="lsirAssessmentForm" property="lsirAssessmentQuestionsList" type="summary" columns="1"/>
														<tr>
															<td class="formDeLabel" colspan="2"><bean:message key="prompt.comments"/></td>
														</tr>
														<tr>
															<td class="formDe" colspan="2"><bean:write name="lsirAssessmentForm" property="comments"/></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<!--lsi assessment end-->
										
										<div class="spacer4px"></div>
										
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>
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
	<br>
<!-- END  MAIN TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
