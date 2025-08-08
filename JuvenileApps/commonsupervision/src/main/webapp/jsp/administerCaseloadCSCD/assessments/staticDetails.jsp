<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/15/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="naming.CaseloadConstants"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<title><bean:message key="title.heading" /> - admininsterCaseload/assessments/staticDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
	function setPeriod(val)
	{
		period=val;
	}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleStaticForm" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!--header area start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<!--header start-->
									<tiles:insert page="../common/caseloadHeader.jsp" flush="true">
									</tiles:insert> 
									<!--header end-->
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<!--header area end-->
						<!--casefile tabs start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
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
									<table class="header">
                                        <tr>
                                           <td><bean:message key="title.cscd"/>&nbsp;-
												<logic:equal name="assessmentForm" property="action" value="update">
													<bean:message key="title.update"/>
												</logic:equal>
												<bean:message key="prompt.static99"/>&nbsp;<bean:message key="title.summary"/>
										   </td>
                                        </tr>
										<!-- END HEADING -->
                                    </table>
									<!-- BEGIN ERRORS TABLE -->
									<table width="100%">
										<tr>		  
											<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
										</tr>   	  
									</table>
									<!-- END ERRORS TABLE -->
    
									<!-- BEGIN instructions -->
									<div class="instructions">
										<li>Review Entries. Click Finish.</li>
									</div>
									<!-- END instructions -->

									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.static"/>&nbsp;<bean:message key="prompt.entry"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%"  border="0" cellspacing="1" cellpadding="4">
													<tr>
														<td class="formDeLabel" nowrap><bean:message key="prompt.assessmentDate"/></td>
														<td class="formDe">																			
															<bean:write name="assessmentForm" property="assessmentDate"  formatKey="date.format.mmddyyyy"/> 
														</td>
													</tr>
													<tr>
														<td colspan="2" class="formDeLabel"><bean:message key="prompt.indexOffense"/></td>
													</tr>
													<tr>
														<td colspan="2" class="formDe">
															<bean:write name="assessmentForm" property="indexOffense" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.numberOfPriorSexOffenses"/> (prior to Index offense)</td>
													</tr>
													<tr>
															<td colspan="2">
																<table cellpadding="2" cellspacing="1" width="100%">
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;1</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="conviction1" /></td>
																		<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;1</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="charge1" /></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;2</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="conviction2" /></td>
																		<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;2</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="charge2" /></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;3</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="conviction3" /></td>
																		<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;3</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="charge3" /></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;4</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="conviction4" /></td>
																		<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;4</td>
																		<td class="formDe"><bean:write name="assessmentForm" property="charge4" /></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.anyStrangerVictim"/></td>
															<td class="formDe"><bean:write name="assessmentForm" property="anyStrangerVictim" /></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.anyUnrelatedVictim"/></td>
															<td class="formDe"><bean:write name="assessmentForm" property="anyUnrelatedVictim" /></td>
														</tr>
														<tr>
															<td colspan="2" class="formDeLabel"><bean:message key="prompt.victimsGender"/></td>
														</tr>
														<tr>
															<td colspan="2" class="formDe">
																	<bean:write name="assessmentForm" property="victimsGender" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.anyConvictionsForNonContactSexOffenses"/></td>
																<td class="formDe"><bean:write name="assessmentForm" property="anyConvictionsForNonContactSexOffenses" /></td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.everLivedWithAnIntimatePartnerTwoYears"/></td>
																<td class="formDe"><bean:write name="assessmentForm" property="everLivedWithAnIntimatePartnerTwoYears" /></td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.age"/></td>
																<td class="formDe">
																	<bean:write name="assessmentForm" property="age" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.indexAssaultiveConviction"/>&nbsp;(Non-Sexual by Title)</td>
																<td class="formDe"><bean:write name="assessmentForm" property="indexAssaultiveConvictionNonSexual" /></td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.priorAssaultiveConviction"/>&nbsp;(Non-Sexual by Title)</td>
																<td class="formDe"><bean:write name="assessmentForm" property="priorAssaultiveConvictionNoSexual" /></td>
															</tr>
															<tr>
																<td class="formDeLabelBottomBorder"><bean:message key="prompt.moreThanFourSentencingOccasions"/></td>
																<td class="formDeBottomBorder"><bean:write name="assessmentForm" property="moreThanFourSentencingOccasions" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">
																	<div align="right"><bean:message key="prompt.total"/>&nbsp;<bean:message key="prompt.score"/></div>
																</td>
																<td class="formDe" id="totalScore"><bean:write name="assessmentForm" property="totalScore" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">
																	<div align="right"><bean:message key="prompt.level"/></div>
																</td>
																<td class="formDe" id="level"><bean:write name="assessmentForm" property="level" /></td>
															</tr>
														</table>
										            </td>
												</tr>
											</table>
											<div class="spacer4px"></div>
											<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
												<tr class="formDeLabel">
													<td><bean:message key="prompt.comments"/></td>
												</tr>
												<tr class="formDe">
													<td><bean:write name="assessmentForm" property="comments" /></td>
												</tr>
											</table>
											<!--assessment list end-->

											<div class="spacer4px"></div>
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" cellpadding="2" cellspacing="1">
												<tr>
													<td align="center">
														<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
														<logic:notEqual name="assessmentsForm" property="action" value="view"> 
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
														</logic:notEqual>
														<logic:equal name="assessmentsForm" property="action" value="view">    
															<html:submit property="submitAction" id="updateButton" class="hidden"><bean:message key="button.update" /></html:submit>
															<html:submit property="submitAction" id="deleteButton" class="hidden"><bean:message key="button.print" /></html:submit>
															
														</logic:equal> 
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</tr>
											</table>
											<!-- END BUTTON TABLE -->
											<%--script>
												if (location.search=="?view"){
													show("finishButton", 0)
													show("printButton", 1, "inline")
													show("updateButton", 1, "inline")
												}
											</script--%>
										</td>
									</tr>
								</table>
								<div class="spacer4px"></div>
							</td>
						</tr>
					</table>
		<!--casefile tabs end-->
					<br>
				</td>
			</tr>
		</table>
		<br>
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
