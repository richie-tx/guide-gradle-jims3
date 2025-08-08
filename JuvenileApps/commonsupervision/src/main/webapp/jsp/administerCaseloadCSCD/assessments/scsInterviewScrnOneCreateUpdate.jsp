<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/23/2009 Bhavani Jangay - Created JSP -->
<!-- 08/11/2009 LDeen - Defect #61487 remove TinyMCE-leave spell check -->
<!-- 09/22/2009 CShimek #62009 revised "document" href located in instructions -->
<!-- 12/02/2009 C Shimek       - #62245 removed the work 'allowed' in block heading, other changes for this defect were made in CSCQuestionTag.java -->
<!-- 02/16/2009 C Shimek       - #63449 revised submit button to save and continue button -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<!--  jims2 CUSTOM TAG lIBRARIES FOR QUESTION ANSWER DISPLAY-->
<%@ taglib uri="../../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/scsInterviewScrnOneCreateUpdate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assessments/spellcheck.js"></script>

<script>
function validateScsScreenOneForm(theForm)
{
	customValRequired("assessmentDateStr", "Assessment Date is required");
	addMMDDYYYYDateValidation("assessmentDateStr", "Assessment Date must be in the format MM/DD/YYYY");
	if(validateCustomStrutsBasedJS(theForm)){
	   return true;
	} else {
	   return false;
	}   	   
}
</script>


</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySCSInterviewCreateUpdate" target="content" focus="assessmentDateStr">
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
										<logic:equal name="scsInterviewAssessmentForm" property="action" value="update">
											<bean:message key="title.update"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|58"></logic:equal>
										<logic:equal name="scsInterviewAssessmentForm" property="action" value="create">
											<bean:message key="prompt.new"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|56"></logic:equal>
										 <bean:message key="title.SCSInterview"/></div>
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
											<li>Enter required fields and answers to questions below. Click Save & Continue button.</li>
											<li>Read questions verbatim. It might be easier for you to print out this <a href="SCS Interview Questions.doc">document</a> and make notes by hand, as opposed to asking questions and keying the information while the defendant is talking. This might prove distracting for the defendant, and the person entering the information might try to enter in too much and/or lose valuable information. Tape recording the session is not advised - see your supervisor if you wish to do this. If you handwrite the information during the interview, you can review the raw data after the interview and key it in later in a manner that is more sensible and provides a better summary of the information. Once you go through formal SCS training, it is recommended that you do a formal SCS on the invidual to help you better supervise him or her.</li>
										</ul>
									</div>
									<div class="required" align="left"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></div>
									<!-- END instructions -->
									
									<!-- assessments date start -->
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.assessmentDate"/></td>
											<td class="formDe">
												<SCRIPT LANGUAGE="JavaScript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT>
												<html:text name="scsInterviewAssessmentForm" property="assessmentDateStr" size="10" maxlength="10" title="(mm/dd/yyyy)" />
												<A HREF="#" onClick="cal1.select(scsInterviewAssessmentForm.assessmentDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar" /></A>												
											</td>
										</tr>
									</table>
									<!-- assessments date end -->
									
									<div class="spacer4px"></div>
									
									<!-- scs interview assessment start -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.scsInterviewQuestions"/> (Max. characters on all questions: 500)
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border="0">
													<jims2:cscquestions name="scsInterviewAssessmentForm" property="scsIntrvScreenOneQuestionsList" type="input" columns="1"/>
												</table>
											</td>
										</tr>
									</table>
									<!-- scs interview assessment end -->												

									<div class="spacer4px"></div>
									
									<!-- BEGIN BUTTON TABLE -->
									<html:hidden name="scsInterviewAssessmentForm" property="scsScreenType" value="screenOne" />
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<logic:notEqual name="scsInterviewAssessmentForm" property="action" value="create">
											    	<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
											    </logic:notEqual>
											    <logic:equal name="scsInterviewAssessmentForm" property="assessmentIncomplete" value="true">
											    	<html:submit property="submitAction" onclick="return validateScsScreenOneForm(this.form);"> <bean:message key="button.saveContinue" /></html:submit>
												</logic:equal> 
												<logic:equal name="scsInterviewAssessmentForm" property="assessmentIncomplete" value="false">
													<html:submit property="submitAction" onclick="return validateScsScreenOneForm(this.form);"> <bean:message key="button.saveContinue" /></html:submit>
										<%--		<html:submit property="submitAction" onclick="return validateScsScreenOneForm(this.form);"> <bean:message key="button.submit" /></html:submit> --%>
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
