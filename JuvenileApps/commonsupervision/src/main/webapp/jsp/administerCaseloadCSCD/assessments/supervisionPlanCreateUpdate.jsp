<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/11/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 02/06/2009 Debbie Williamson - Changed Behavior Objective label to Behavioral Objective - ER #56728 -->
<!-- 08/23/2010 Debbie Williamson - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->
<!-- 05/06/2013 R.Young	 	 ER #75492 RapidSpellWeb file changesV3.2.0 for CS -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/supervisionPlanCreateUpdate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/assessments/supervisionPlan.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySupervisionPlanUpdate" target="content" focus="supervisionPlanDateStr">
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
										<logic:equal name="supervisionPlanForm" property="action" value="create">
											<bean:message key="prompt.new"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|5">
										</logic:equal>
										<logic:equal name="supervisionPlanForm" property="action" value="update">
											<bean:message key="title.update"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|9">
										</logic:equal>
										<logic:equal name="supervisionPlanForm" property="action" value="copy">
											<bean:message key="prompt.copy"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|1">
										</logic:equal>	
										
										<bean:message key="title.supervisionPlan"/>
                                    </div>
									<!-- END HEADING -->
									
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									
									<!-- BEGIN instructions -->
									<div class="instructions" align="left">
										<ul>
											<li>Enter Supervision Plan information, Click Submit.</li>
										</ul>
									</div>
									<div class="required" align="left"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></div>
									<!-- END instructions -->
									
									<!-- START supervisionplan date -->
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.supervisionPlanDate"/></td>
											<td class="formDe">
												<SCRIPT LANGUAGE="JavaScript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT>												
												<html:text name="supervisionPlanForm" property="supervisionPlanDateStr" size="10" maxlength="10" title="(mm/dd/yyyy)" />
												<A HREF="#" onClick="cal1.select(supervisionPlanForm.supervisionPlanDateStr,'anchor4','MM/dd/yyyy'); return false;" NAME="anchor4" ID="anchor4" border="0"><bean:message key="prompt.3.calendar"/></A>
											</td>
										</tr>										
									</table>
									<div class="spacer4px"></div>
									<!-- END supervisionplan date-->
									
									<!--  START Supervision Plan -->
									<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="true">
											<tiles:put name="agencyCode" beanName="supervisionPlanForm" beanProperty="agencyId"/>
									</tiles:insert>
									
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.3.diamond"/><bean:message key="title.supervisionPlan"/></td>
											<td align="right"><A href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderConditions.do?submitAction=Link&defendantId=<bean:write name="supervisionPlanForm" property="defendantId"/>');" class="blackSubNav"><bean:message key="prompt.viewConditions"/></A></td>
										</tr>
										<tr>
											<td colspan="2">
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
													<style>
														.mceEditor{																				
															width: "100%";
															height: "130";
														} 
													</style>
														<td>
														   <DIV class="formDeLabel spacer"><B><bean:message key="prompt.problem" /></B></DIV>	
                                                           <html:textarea  name="supervisionPlanForm" property="problem" styleClass="mceEditor" rows="7" style="width:100%"  ondblclick="myReverseTinyMCEFix(this)"/>																					
															<div class="spacer4px"></div>
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.behavioralObjective" /></B></DIV>
                                                            <html:textarea  name="supervisionPlanForm" property="behaviorObjective" styleClass="mceEditor" rows="7" style="width:100%"  ondblclick="myReverseTinyMCEFix(this)">
															</html:textarea>		
															<div class="spacer4px"></div>
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.offenderActionPlan" /></B></DIV>
                                                            <html:textarea  name="supervisionPlanForm" property="offenderActionPlan" styleClass="mceEditor" rows="7" style="width:100%"  ondblclick="myReverseTinyMCEFix(this)">
															</html:textarea>																						
															<div class="spacer4px"></div>
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.csoActionPlan" /></B></DIV>
                                                            <html:textarea  name="supervisionPlanForm" property="csoActionPlan" styleClass="mceEditor" rows="7" style="width:100%"  ondblclick="myReverseTinyMCEFix(this)">
															</html:textarea>		
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!--  END Supervision Plan -->
									
									<div class="spacer4px"></div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
											  <script>
											    customValRequired("supervisionPlanDateStr","<bean:message key='errors.required' arg0='Supervision Plan Date'/>","");
												addMMDDYYYYDateValidation("supervisionPlanDateStr","<bean:message key='errors.date' arg0='Supervision Plan Date'/>","");

												customValRequired("problem","Problem section is required.",null);																
												addDefinedTinyMCEFieldMask("problem","<bean:message key='errors.freeTextDB2' arg0='Problem section'/>");
												customValMinLength("problem","Problem section must be at least 10 characters.",17);
												customValMaxLength("problem","Problem section cannot be more than 800 characters.",1600);

												customValRequired("behaviorObjective","Behavioral Objective section is required.",null);																
												addDefinedTinyMCEFieldMask("behaviorObjective","<bean:message key='errors.freeTextDB2' arg0='Behavioral Objective section'/>");
												customValMinLength("behaviorObjective","Behavioral Objective section must be at least 10 characters.",17);
												customValMaxLength("behaviorObjective","Behavioral Objective section cannot be more than 800 characters.",1600);

												customValRequired("offenderActionPlan","Offender Action section is required.",null);																
												addDefinedTinyMCEFieldMask("offenderActionPlan","<bean:message key='errors.freeTextDB2' arg0='Offender Action Plan section'/>");
												customValMinLength("offenderActionPlan","Offender Action section must be at least 10 characters.",17);
												customValMaxLength("offenderActionPlan","Offender Action section cannot be more than 800 characters.",1600);

												customValRequired("csoActionPlan","CSO Action Plan section is required.",null);																
												addDefinedTinyMCEFieldMask("csoActionPlan","<bean:message key='errors.freeTextDB2' arg0='CSO Action Plan section'/>");
												customValMinLength("csoActionPlan","CSO Action Plan section must be at least 10 characters.",17);
												customValMaxLength("csoActionPlan","CSO Action Plan section cannot be more than 800 characters.",1600);
											
											  </script>
												<html:submit property="submitAction" onclick="return (myTinyMCEFix() && trimSupPlanFields(this.form) && validateCustomStrutsBasedJS(this.form) && validateSupPlanFieldsForBROnly(this.form)); "><bean:message key="button.submit" /></html:submit>
												<html:reset><bean:message key="button.reset" /></html:reset>
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
<div align="center">[<a href="#top">Back to Top</a>]</div></body>
</html:html>
