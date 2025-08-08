<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!-- 10/05/2009  C Shimek   #62239 removed onmouseout() on comments to prevent double warning -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/lsirCreateUpdate.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>
function validateFields(theForm)
{
	clearAllValArrays();
	customValRequired("assessmentDateStr","<bean:message key='errors.required' arg0='Assessment Date'/>","");
	addMMDDYYYYDateValidation("assessmentDateStr","<bean:message key='errors.date' arg0='Assessment Date'/>","");
	<jims2:cscquestionValidation name="lsirAssessmentForm" property="lsirAssessmentQuestionsList" />
	customValMaxLength("comments","<bean:message key='errors.maxlength' arg0='Comments' arg1='250'/>","250");
	addDefinedDB2Mask("comments","<bean:message key='errors.freeTextDB2' arg0='Comments'/>");
	return validateCustomStrutsBasedJS(theForm);
}

</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayLSIRCreateUpdate" target="content" focus="assessmentDateStr">
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
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|40">
											</logic:equal>
											<logic:notEqual name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.reassessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|43">
											</logic:notEqual>
										</logic:equal>
										
										<logic:equal name="lsirAssessmentForm" property="action" value="update">
											<logic:equal name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.assessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|55">
											</logic:equal>
											<logic:notEqual name="lsirAssessmentForm" property="lsirAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.LSIR"/>&nbsp;<bean:message key="prompt.reassessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|44">
											</logic:notEqual>
										</logic:equal>
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
											<li>Enter LSI-R score information and select Next Button.</li>
										</ul>
									</div>
									<div class="required" align="left"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></div>
									<!-- END instructions -->
	
									<!--assessment date start-->
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.assessmentDate"/></td>
											<td class="formDe">
												<SCRIPT LANGUAGE="JavaScript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT>												
												<html:text name="lsirAssessmentForm" property="assessmentDateStr" size="10" maxlength="10" title="(mm/dd/yyyy)" />
												<A HREF="#" onClick="cal1.select(lsirAssessmentForm.assessmentDateStr,'anchor33','MM/dd/yyyy'); return false;" NAME="anchor33" ID="anchor33" border="0"><bean:message key="prompt.3.calendar"/></A>
											</td>
										</tr>
									</table>
									<div class="spacer4px"></div>
									<!--assessment date end-->
									
									
									<!--lsi start-->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.score"/>&nbsp;<bean:message key="prompt.entry"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border="0">
													<jims2:cscquestions name="lsirAssessmentForm" property="lsirAssessmentQuestionsList" type="input" requiredGif="../../../images/required.gif" columns="1"/>	          										
	          										<tr>
														<td class="formDeLabel" colspan="2">Comments  (Max Characters Allowed: 250)</td>
													</tr>
													<tr>
														<td class="formDe" colspan="2"><html:textarea name="lsirAssessmentForm" property="comments" rows="2" style="width:100%" onmouseout="textLimit( this, 250 )" onkeyup="textLimit( this, 250 )"/></td>
													</tr>
	          									</table>												
											</td>
										</tr>										
									</table>
									<!--lsi end-->

									<div class="spacer4px"></div>
									
										<!-- BEGIN BUTTON TABLE -->
									<html:hidden name="lsirAssessmentForm" property="lsirScreenType" value="lsir" />	
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<logic:notEqual name="lsirAssessmentForm" property="action" value="create">
											   		<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
											   	</logic:notEqual>	
												<html:submit property="submitAction" onclick="return validateFields(this.form);"> <bean:message key="button.submit" /></html:submit>
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
<!-- END  MAIN TABLE -->
<br>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
