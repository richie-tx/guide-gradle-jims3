<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/15/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="naming.CaseloadConstants"%>
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
<title><bean:message key="title.heading" /> - admininsterCaseload/assessments/staticForm.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/AnchorPosition.js"></script>
<script>
	function setPeriod(val)
	{
		period=val;
	}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayStaticFormSummary" target="content"><div align="center">
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
												<logic:equal name="assessmentForm" property="action" value="create">
													<bean:message key="title.create"/>
												</logic:equal>
												<bean:message key="prompt.static99"/>
										   </td>
                                        </tr>
										<!-- END HEADING -->
                                    </table>     
									<!-- END HEADING -->
									<!-- BEGIN ERRORS TABLE -->
									<table width="100%">
										<tr>		  
											<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
										</tr>   	  
									</table>
									<!-- END ERRORS TABLE -->
									<!-- BEGIN instructions -->
									<div class="instructions">
										<li>Fill in answers. Click Next.</li>
									</div>
									<div class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></div>
									<!-- END instructions -->

									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.static"/>&nbsp;<bean:message key="prompt.entry"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%"  border="0" cellspacing="1" cellpadding="2">
													<tr>
														<td class="formDeLabel" nowrap><span class="diamond"></span><bean:message key="prompt.assessmentDate"/></td>
														<td class="formDe">
															<SCRIPT LANGUAGE="JavaScript" ID="js1">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															</SCRIPT>
															<html:text name="assessmentForm" property="assessmentDate" size="10" maxlength="10"/>
															<a href="#" onClick="cal1.select(document.forms[0].assessmentDate,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar"/></a>
															
															<%--script>
																document.myForm.eventDate.value=getCurrentDate();
															</script--%>
															
														</td>
													</tr>
													<tr>
														<td colspan="2" class="formDeLabel"><bean:message key="prompt.indexOffense"/></td>
													</tr>
													<tr>
														<td colspan="2"  class="formDe">
															<html:select size="1" name="assessmentForm" property="indexOffenseId">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<html:optionsCollection name="assessmentForm" property="offenses" value="code" label="description" /> 
															</html:select>
															<%--select>
																<option>Please Select</option>
																<option>Agg Kidnapping BI/Sexual Abuse</option>
																<option>Sexual Performance of Child - Employ to Induce Authorize</option>
																<option>Agg Kidnapping Interfere Performance Safe Release</option>
																<option>Agg Kidnapping Use as Shield/Hostage Safe Release</option>
																<option>Online Solicitation of a Minor Under 14 Years of Age</option>
																<option>Online Solicitation of a Minor to Meet w/Int Sex/Contact</option>
															</select--%>
														</td>
													</tr>
													<tr>
														<td colspan="2" class="formDeLabel"><bean:message key="prompt.numberOfPriorSexOffenses"/> (prior to Index offense)
															<li>Index offense is the most recent offense with a sexual element.</li>
															<li>Consider convictions and charges for sex offenses (any offense with a sexual element) prior to index offense.</li>
															<li>Deferred adjudication would count as an offense conviction.</li>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<table cellpadding="2" cellspacing="1" width="100%">																					
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;1</td>
																	<td class="formDe"><html:text name="assessmentForm" property="conviction1" size="20" maxlength="20"/></td>
																	<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;1</td>
																	<td class="formDe"><html:text name="assessmentForm" property="charge1" size="20" maxlength="20"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;2</td>
																	<td class="formDe"><html:text name="assessmentForm" property="conviction2" size="20" maxlength="20"/></td>
																	<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;2</td>
																	<td class="formDe"><html:text name="assessmentForm" property="charge2" size="20" maxlength="20"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.conviction"/>&nbsp;3</td>
																	<td class="formDe"><html:text name="assessmentForm" property="conviction3" size="20" maxlength="20"/></td>
																	<td class="formDeLabel"><bean:message key="prompt.charge"/>&nbsp;3</td>
																	<td class="formDe"><html:text name="assessmentForm" property="charge3" size="20" maxlength="20"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.conviction"/>&nbsp;4</td>
																	<td class="formDe"><html:text name="assessmentForm" property="conviction4" size="20" maxlength="20"/></td>
																	<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.charge"/>&nbsp;4</td>
																	<td class="formDe"><html:text name="assessmentForm" property="charge4" size="20" maxlength="20"/></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.anyStrangerVictim"/>
														<li>Victim has known the offender for less than 24 hours prior to the offense.</li>
														<li>Information can come from any source available.</li>
														</td>
														<td class="formDe">Yes <html:radio property="anyStrangerVictim" value="" /> No <html:radio property="anyStrangerVictim" value="" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.anyUnrelatedVictim"/>
														<li>A relationship sufficiently close that marriage would normally be prohibited - score as "0."</li>
														<li>See scoring guides for examples.</li>
														<li>Information can come from any source available.</li>
														</td>
														<td class="formDe">Yes <html:radio property="anyUnrelatedVictim" value="" /> No <html:radio property="anyUnrelatedVictim" value="" /></td>
													</tr>
													<tr>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.victimsGender"/>
															<li>Information can come from any source available.</li></td>
													</tr>
													<tr>
														<td colspan="2" class="formDe"><html:radio property="victimsGender" value="" />
															<html:select property="victimsVictim">
																<html:optionsCollection label="Male Offender">
																	<html:option value="0">FEMALE VICTIM</html:option>
																	<html:option value="1">MALE VICTIM</html:option>
																</html:optionsCollection>
																<html:optionsCollection label="Female Offender">
																	<html:option value="2">FEMALE VICTIM WITH
																	NO MALE CO-DEFENDANT</html:option>
																	<html:option value="3">MALE VICTIM</html:option>
																</html:optionsCollection>
															</html:select>
														</td>
													</tr>
													<tr>
														<td  class="formDeLabel"><bean:message key="prompt.anyConvictionsForNonContactSexOffenses"/>
														<li>Convictions only.</li>
														<li>Indecent Exposure, Illegal Pornography, etc.</li>
														</td>
														<td class="formDe">Yes <html:radio property="anyConvictionsForNonContactSexOffenses" value="" /> No <html:radio property="anyConvictionsForNonContactSexOffenses" value="" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.everLivedWithAnIntimatePartnerTwoYears"/>
														<li>"Married" - two adults living together as lovers and sharing bills for at least two continuous years.</li>
														<li>Male/Male or Female/Female relationships would count if living as lovers.	</li>
														</td>
														<td class="formDe">Yes <html:radio property="everLivedWithAnIntimatePartnerTwoYears" value="" /> No <html:radio property="everLivedWithAnIntimatePartnerTwoYears" value="" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.age"/>
														<li>Age at commencement of the period at risk (e.g., released from prison, released on community supervision).</li>
														</td>
														<td class="formDe">
															<html:select property="age">
																<html:option value="">18-24 years old</html:option>
																<html:option value="">25 and over</html:option>
															</html:select>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.indexAssaultiveConviction"/> (Non-Sexual by Title)
														<li>Any assaultive conviction SENTENCED AT THE SAME TIME as the index sexual offense.</li>
														<li>Regardless of the date the offenses were committed.</li>
														<li>Include Homicide, Wounding, Assault, Robbery, Arson, Abduction, etc.</li>
														</td>
														<td class="formDe">Yes <html:radio property="indexAssaultiveConvictionNonSexual" value="" /> No <html:radio property="indexAssaultiveConvictionNonSexual" value="" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.priorAssaultiveConviction"/> (Non-Sexual by Title)
														<li>Not including index offense.</li>
															<li>Conviction only.</li>
														</td>
														<td class="formDe">Yes <html:radio property="priorAssaultiveConvictionNonSexual" value="" /> No <html:radio property="priorAssaultiveConvictionNonSexual" value="" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.moreThanFourSentencingOccasions"/>
														<li>Number of occasions sentenced.</li>
														<li>For any criminal offense (not including traffic tickets).</li>
														<li>Include juvenile.</li>
														<li>Include Index Offense and all prior sentencing occasions.</li>
														</td>
														<td class="formDe">Yes <html:radio property="moreThanFourSentencingOccasions" value="" /> No <html:radio property="moreThanFourSentencingOccasions" value="" /></td>
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
											<td><html:textarea property="comments" rows="5" style="width:100%"/></td>
										</tr>
									</table>
									<!--assessment list end-->

									<div class="spacer4px"></div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return performValidation(this.form)"> <bean:message key="button.next" /></html:submit>
						                        <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
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
