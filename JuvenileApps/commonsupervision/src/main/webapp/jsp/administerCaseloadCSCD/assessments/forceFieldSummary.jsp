<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/04/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="../../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/forceFieldSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitForceFieldAssessment" target="content">
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
								
									<!-- BEGIN HEADING -->
									<div class="header"><bean:message key="title.CSCD"/>&nbsp;-
										<logic:equal name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
											<logic:equal name="forceFieldAssessmentForm" property="action" value="create">
												<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.forceFieldAnalysis"/>&nbsp;<bean:message key="title.confirmation"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|22">
											</logic:equal>
											<logic:equal name="forceFieldAssessmentForm" property="action" value="update">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.forceFieldAnalysis"/>&nbsp;<bean:message key="title.confirmation"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|25">
											</logic:equal>
										</logic:equal>
										
										<logic:notEqual name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
											<logic:equal name="forceFieldAssessmentForm" property="action" value="create">
												<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.forceFieldAnalysis"/>&nbsp;<bean:message key="title.summary"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|21">
											</logic:equal>
											<logic:equal name="forceFieldAssessmentForm" property="action" value="update">
											<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.forceFieldAnalysis"/>&nbsp;<bean:message key="title.summary"/> 
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|24">
											</logic:equal>
										</logic:notEqual>
										
										<logic:equal name="forceFieldAssessmentForm" property="action" value="delete">
												<bean:message key="prompt.delete"/>&nbsp;<bean:message key="prompt.forceFieldAnalysis"/>&nbsp;<bean:message key="title.summary"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|26">
										</logic:equal>
										
										<!--<logic:equal name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
											<logic:equal name="forceFieldAssessmentForm" property="action" value="create">
												<bean:message key="title.confirmation"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|22">
											</logic:equal>
											<logic:equal name="forceFieldAssessmentForm" property="action" value="update">
												<bean:message key="title.confirmation"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|25">
											</logic:equal>  
										</logic:equal> -->
										
										<!--<logic:notEqual name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
											<bean:message key="title.summary"/>  
										</logic:notEqual>  -->                                               
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
										<logic:present name="confirmMessage">
											<tr id="confirmations">
												<td class="confirm">
													<bean:write name="confirmMessage" />
												</td>
											</tr>
										</logic:present>
										
										<logic:equal name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
											<tr>
												<td>
													<ul>
														<li>Click appropriate button below.</li>
													</ul>
												</td>
											</tr>
										</logic:equal>
										
										<logic:notEqual name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
											<tr>
												<td>
													<ul>
														<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button. </li>
													</ul>
												</td>
											</tr>
										</logic:notEqual>
									</table>									
								<!-- END instructions -->
								
									<div class="spacer4px"></div>
								
								<!--Assessment date start-->
									<table width="98%" cellpadding="4" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate" /></td>
											<td class="formDe">
												<bean:write name="forceFieldAssessmentForm" property="assessmentDateStr" />
											</td>
										</tr>
									</table>
								<!--Assessment date end-->
								
									<div class="spacer4px"></div>
									
								<!-- Force Field Assessment Start -->	
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
        									<td>
          										<table width="100%" cellpadding="2" cellspacing="1">
          											<jims2:cscquestions name="forceFieldAssessmentForm" property="forceFieldQuestionsList" type="summary" columns="1"/>
          										</table>
      									 	 </td>
      								 	 </tr>
  								 	 </table>
  								 <!-- Force Field Assessment End -->	

									<div class="spacer4px"></div>
									
								 <!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<logic:equal name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
													 <html:button property="submitAction" onclick="openWindow('submitForceFieldAnalysisPrintRequest.do?submitAction=Print')"><bean:message key="button.print"/></html:button>	
													 <input type="button" value="<bean:message key='button.assessmentsList' />"  onclick="goNav('/<msp:webapp/>displayAssessmentSummary.do?submitAction=Link')"></input>
												</logic:equal>
											
												 <!--  Summary Page -->
												 <logic:notEqual name="forceFieldAssessmentForm" property="secondaryAction" value="confirm">
												 	 <html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>	
												 	 
												 	 <logic:notEqual name="forceFieldAssessmentForm" property="action" value="delete">
												 	 	 <logic:equal name="forceFieldAssessmentForm" property="assessmentIncomplete" value="true">
												 	 	 	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.saveAsDraft" /></html:submit>
												 	 	 </logic:equal>
												 	 </logic:notEqual>
														 	 
												 	 <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>       
												 	 <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>                                                      
												 </logic:notEqual>
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
