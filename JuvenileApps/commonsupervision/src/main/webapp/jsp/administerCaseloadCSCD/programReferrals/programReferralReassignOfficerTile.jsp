<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/1/2010  RYoung    ER for Initiate Program Unit Referral -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->
<%@ page import="naming.PDCodeTableConstants" %>

<!--BEGIN HEADER TAG-->

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<html:base />
<title>Common Supervision - administerCaseloadCSCD/programReferrals/programReferralReassignOfficerTile.jsp</title>
<!-- BEGIN SPACER TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
							<tr>
								<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="15"></td>
							</tr>
						</table>
<!-- END SPACER TABLE -->							
		<!-- PROGRAM UNIT ASSIGNMENT START -->
					<logic:equal name="caseAssignmentForm" property="programRefInfo" value="true">
						<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							
							<tr class="detailHead">
								<td>Program Referral Information</td>
							</tr>
							<tr>
								<td>
								<table width="100%" cellpadding="4" cellspacing="1" >
								
									<logic:equal name="caseAssignmentForm" property="closeProgramUnitRef" value="true">
									<tr>
										<td colspan="4" class="formDeLabel">Program Referral Closure Due To Program Unit Change: <bean:write name="caseAssignmentForm" property="programUnitName"/></td>
									</tr>
							
									<tr>
										<td class="formDeLabel" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programEndDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="programEndDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].programEndDateAsStr,'anchor0','MM/dd/yyyy'); return false;" NAME="anchor0" ID="anchor0" border="0"><bean:message key="prompt.3.calendar"/></A>
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForDischarge"/></td>
										<td class="formDe" colspan="3">
											<html:select size="1" name="caseAssignmentForm" property="reasonForDischargeId" >
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<jims:codetable codeTableName="<%=PDCodeTableConstants.JIMS2_DISCHARGE_REASON%>" sort="true"></jims:codetable>
											</html:select>
										</td>
									</tr>
									</logic:equal>
									
									<logic:equal name="caseAssignmentForm" property="stateReportingCode" value="W">
									<tr class="formDeLabel">
										<td colspan="4" class="formDeLabel">New Program Referral Due To Program Unit Change: <bean:write name="caseAssignmentForm" property="programUnitName"/></td>
									</tr>
							
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.referralDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="referralDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].referralDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
										</td>
									</tr>
									
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForPlacement"/></td>
										<td class="formDe" colspan="3">
											<html:select size="1" name="caseAssignmentForm" property="reasonForPlacementId" >
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<jims:codetable codeTableName="<%=PDCodeTableConstants.REASON_FOR_PLACEMENT%>" sort="true"></jims:codetable>
											</html:select>
										</td>
									</tr>
									</logic:equal>
										
									<logic:equal name="caseAssignmentForm" property="stateReportingCode" value="Y">
									<tr class="formDeLabel">
										<td colspan="4" class="formDeLabel">New Program Referral Due To Program Unit Change: <bean:write name="caseAssignmentForm" property="programUnitName"/></td>
									</tr>
							
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.referralDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="referralDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].referralDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programBeginDate"/></td>
										<td class="formDe" colspan="3">
											<html:text name="caseAssignmentForm" property="programBeginDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
											<A HREF="#" onClick="cal1.select(document.forms[0].programBeginDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reasonForPlacement"/></td>
										<td class="formDe" colspan="3">
											<html:select size="1" name="caseAssignmentForm" property="reasonForPlacementId" >
												<html:option value=""><bean:message key="select.generic" /></html:option>
												<jims:codetable codeTableName="<%=PDCodeTableConstants.REASON_FOR_PLACEMENT%>" sort="true"></jims:codetable>
											</html:select>
										</td>
									</tr>
									</logic:equal>
								</table>
								</td>
							</tr> 
						</table>
						<br>
					</logic:equal>
						<!-- PROGRAM UNIT ASSIGNMENT END -->
