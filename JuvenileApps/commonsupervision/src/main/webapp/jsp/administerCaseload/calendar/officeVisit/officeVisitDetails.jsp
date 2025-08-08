<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/10/2008	 Aswin Widjaja - Create JSP -->
<!-- 03/23/2009  CShimek       - 58068 added Finish and Schedule Next button -->
<!-- 04/02/2009  slin          - 58458 various UI fixes -->
<!-- 08/23/2010  CShimek       - 64357 added hidden field for eventTypeCd which is needed for create/update event, value being lost in complex flow -->
<!-- 10/14/2010  CShimek       - 67865 revised finish and finishandScheduleNext buttons from changeFormActionURL call to straight struts method call -->
<!-- 08/19/2011  RCapestani    - 71051 added code to display and hide client address and employment information -->
<!-- 09/21/2011  RCapestani    - 71306 add code to display reschedule reason -->
<!-- 04/12/2012  TSVines       - 72079 added the class=buttonBar" to the <tr> -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - csCalendar - officeVisitDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">

function flipText( idOfTD, textToSet ){
	if (document.getElementById( idOfTD ).innerHTML.indexOf("Address") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Address")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Address" ;
			show("addressRow", 1, "row")
		}else {
			document.getElementById( idOfTD ).innerHTML = "View Address" ;
			show("addressRow", 0)
		}
	}else if (document.getElementById( idOfTD ).innerHTML.indexOf("Employment") > 0)
	{
		if (document.getElementById( idOfTD ).innerHTML == "View Employment")
		{
			document.getElementById( idOfTD ).innerHTML = "Hide Employment" ;
			show("employmentRow", 1, "row")
		}else 
		{
			document.getElementById( idOfTD ).innerHTML = "View Employment" ;
			show("employmentRow", 0)
		}
	}
}

</script>
</head>
	<body topmargin="0" leftmargin="0">
		<html:form action="/handleCSOVSelection" target="content">
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
										<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
											<tiles:put name="tab" value="caseloadTab"/>
										</tiles:insert>	
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
									<td align=center>
										<!--header start-->
										<tiles:insert page="../../../common/superviseeHeader.jsp" flush="true">
										</tiles:insert>	
										<!--header end-->
									</td>
								</tr>
								<tr>
									<td valign="top" align="center">										
										<!--casefile tabs start-->
										<table width="98%" border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
											</tr>
											<tr>
												<td valign="top">
													<!--tabs start-->
													<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
														<tiles:put name="tab" value="CalendarTab"/> 
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
													<!-- BEGIN HEADING TABLE -->
													<table width="100%">
														<tr>
															<td align="center" class="header"><bean:message key="title.CSCD"/> - 
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="create">
																	<bean:message key="prompt.create" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|5">
																</logic:equal>	
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="update">
																	<bean:message key="prompt.update" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|11">
																</logic:equal>	
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="reschedule">
																	Reschedule<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|9">
																</logic:equal>	
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="delete">
																	<bean:message key="prompt.delete" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|23">
																</logic:equal>
																<bean:message key="prompt.officeVisit" />
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="enterResults">
																	<bean:message key="prompt.results"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|7">
																</logic:equal>	
																<logic:equal name="csCalendarOVForm" property="activityFlow" value="view">
																	<bean:message key="prompt.details"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|3">
																</logic:equal>
																<logic:equal name="status" value="summary">
																	<bean:message key="prompt.summary"/>
																</logic:equal>
															</td>	
														</tr>		
														<tr>
															<td align="center" class="errorAlert"><html:errors></html:errors></td>
														</tr>
													</table>	
													<!-- BEGIN INSTRUCTION TABLE -->
													<div class=instructions>
														<ul>
															<li>
															<jims2:if name="csCalendarOVForm" property="activityFlow" value="update" op="equal">
																<jims2:or name="csCalendarOVForm" property="activityFlow" value="enterResults" op="equal"/>
																	<jims2:then>
																		Verify that information is correct and select Finish button. If any changes are needed, select Back button.
																	</jims2:then>
																
																	<jims2:else>
																		Click appropriate button.
																	</jims2:else>
															</jims2:if>
															</li>
														</ul>
													</div>
													<!-- END INSTRUCTION TABLE -->
													<!-- BEGIN DETAIL TABLE -->
													<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr class="detailHead">
															<td><bean:message key="prompt.officeVisit"/> <bean:message key="prompt.info"/></td>
															<td align="right">
																<a href="javascript:flipText('vAddress', 'View Address')" id="vAddress">Hide Address</a> | <a href="javascript:flipText('vEmployment', 'View Employment')" id="vEmployment">Hide Employment</a> | 
																<a href="javascript:openWindow('/<msp:webapp/>displayComplianceConditions.do?submitAction=Link&superviseeId=<bean:write name='csCalendarOVForm' property='currentOfficeVisit.superviseeId'/>')" class=blackSubNav>View Conditions</a>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<table width="100%" cellpadding="4" cellspacing="1">
																	<tr>
																		<td class="formDeLabel" nowrap>
																			<bean:message key="prompt.officeVisit"/> <bean:message key="prompt.date"/></td>
																		<td class="formDe">
																			<bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventDate" formatKey="date.format.mmddyyyy"/>
																		</td>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.eventName"/></td>
																		<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventName"/></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.startTime"/></td>
																		<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.startTime"/> <logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.startTime"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.AMPMId1"/></logic:notEmpty>
																		</td>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.endTime"/></td>
																		<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.endTime"/> <logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.endTime"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.AMPMId2"/></logic:notEmpty> 
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.supervisee"/> <bean:message key="prompt.phone"/></td>
																		<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.superviseePhone.formattedPhoneNumber"/></td>
																		<td class="formDeLabel">Alternate <bean:message key="prompt.phone"/></td>
																		<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.altPhone.formattedPhoneNumber"/></td>
																	</tr>
																	<tr id="addressRow">
																		<td class="formDeLabel"><bean:message key="prompt.address"/></td>
																		<td colspan="3" class="formDe"><bean:write name="superviseeForm" property="superviseeStreetNumber"/> <bean:write name="superviseeForm" property="superviseeStreetName"/><logic:notEmpty  name="superviseeForm" property="superviseeStreetName"></logic:notEmpty> <logic:notEmpty  name="superviseeForm" property="superviseeApartmentNumber"> <bean:message key="prompt.aptSuite"/> <bean:write name="superviseeForm" property="superviseeApartmentNumber"/><br> </logic:notEmpty>
																			<bean:write name="superviseeForm" property="superviseeCity"/><logic:notEmpty  name="superviseeForm" property="superviseeCity">, </logic:notEmpty><bean:write name="superviseeForm" property="superviseeState"/> <bean:write name="superviseeForm" property="superviseeZipCode"/> <logic:notEmpty  name="superviseeForm" property="superviseeZipCode"><br> </logic:notEmpty>
																		<%-- 	 <a href=# disabled>Update Address</a> --%>
																		</td>
																	</tr>
																	<tr id="employmentRow">
																		<td class="formDeLabel"><bean:message key="prompt.employment"/></td>
																		<td colspan="3" class="formDe"><bean:write name="superviseeForm" property="employerName"/><logic:notEmpty name="superviseeForm" property="employePhoneNum">, <bean:write name="superviseeForm" property="employePhoneNum"/></logic:notEmpty><logic:notEmpty name="superviseeForm" property="employerOccupation">, <bean:write name="superviseeForm" property="employerOccupation"/></logic:notEmpty> 
																		<%--     <a href=# disabled>Update Employment</a> --%>
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" colspan="4">Purpose of <bean:message key="prompt.officeVisit"/></td>
																	</tr>
																	<style>
																		.mceEditor{
																		width: "100%";
																		height: "150"
																		}
																	</style>
																	<tr class="formDe">
																		<td colspan="4" align="left">
																			<bean:write name="csCalendarOVForm" property="currentOfficeVisit.purpose" filter="false"/>																							
																		</td>
																	</tr>
																	<logic:equal name="csCalendarOVForm" property="activityFlow" value="reschedule">
																		<tr>
																			<td class="formDeLabel" colspan="4"><bean:message key="prompt.rescheduledReason"/> - <bean:message key="prompt.casenote"/></td>
																		</tr>
																		<style>
																			.mceEditor{
																			width: "100%";
																			height: "150"
																			}
																		</style>
																		<tr class="formDe">
																			<td colspan="4" align="left">
																				<bean:write name="csCalendarOVForm" property="currentOfficeVisit.rescheduleReason" filter="false"/>																							
																			</td>
																		</tr>
																	</logic:equal>
																	<!--OUTCOME and NARRATIVE only shows up if Results has been entered for an event (i.e. outcome is not scheduled or rescheduled). -->
																	<logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd">
																		<logic:equal name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd" value="RE">
																			<tr>
																				<td class="formDeLabel" colspan="4"><bean:message key="prompt.rescheduledReason"/> - <bean:message key="prompt.casenote"/></td>
																			</tr>
																			<tr class="formDe">
																				<td colspan="4" align="left">
																					<bean:write name="csCalendarOVForm" property="currentOfficeVisit.rescheduleReason" filter="false"/>																							
																				</td>
																			</tr>
																		</logic:equal>
																		<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd" value="RE">
																			<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd" value="SC">
																				<tr>
																					<td class="formDeLabel"><bean:message key="prompt.outcome"/></td>
																					<td class="formDe" colspan="3"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.outcomeDesc"/></td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" colspan="4"><bean:message key="prompt.narrative"/></td>
																				</tr>
																				<tr class="formDe">
																					<td colspan="4">
																						<bean:write name="csCalendarOVForm" property="currentOfficeVisit.narrative" filter="false"/>
																					</td>
																				</tr>
																			</logic:notEqual>
																		</logic:notEqual>
																	</logic:notEmpty>
																</table>
															</td>
														</tr>
													</table>
													<!-- BEGIN DETAIL TABLE --> 															
													<!-- BEGIN BUTTON TABLE -->
													<table border="0" width="100%">
														<logic:equal name="csCalendarOVForm" property="activityFlow" value="view">
															<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.status" value="C">
																<tr class="buttonBar">
																	<td align="center">
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_UPDATE%>'>
																		<html:submit property="submitAction"><bean:message key="button.update" /></html:submit>
																	</jims2:isAllowed>
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_RESCHEDULE%>'>
																		<html:submit property="submitAction"><bean:message key="button.reschedule" /></html:submit>
																	</jims2:isAllowed>	
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOVR_CREATE%>'>
																		<html:submit property="submitAction"><bean:message key="button.enterResults" /></html:submit>
																	</jims2:isAllowed>
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOOV_DELETE%>'>
																		<html:submit property="submitAction"><bean:message key="button.delete" /></html:submit>
																	</jims2:isAllowed>
																	</td>
																</tr>
																<tr class="buttonBar">
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																		<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																	</td>
																</tr>
															</logic:notEqual>
															
															<logic:equal name="csCalendarOVForm" property="currentOfficeVisit.status" value="C">
																<tr class="buttonBar">
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																		<logic:notEqual name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd" value="RE">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_UPDATE%>'>
																			<html:submit property="submitAction"><bean:message key="button.update" /></html:submit>
																		</jims2:isAllowed>
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALCOV_DELETE%>'>
																			<html:submit property="submitAction"><bean:message key="button.delete" /></html:submit>
																		</jims2:isAllowed>
																		</logic:notEqual>	
																		<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																	</td>	
																</tr>
															</logic:equal>
															
														</logic:equal>	
														<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="view">
															<tr class="buttonBar">
																<td align="center"> 
																	<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																	<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
																<%-- 	<input type="submit" value="<bean:message key='button.finish'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>submitCSOVUpdate.do',false);" >  --%>
																	<logic:equal name="csCalendarOVForm" property="activityFlow" value="enterResults">
																		<html:submit property="submitAction"><bean:message key="button.finishAndScheduleNext" /></html:submit>
															 	<%--	<input type="submit" value="<bean:message key='button.finishAndScheduleNext'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>submitCSOVUpdate.do',false);" > --%>
																	</logic:equal>
																	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																	<input type='hidden' name="currentOfficeVisit.eventTypeCd" value='OV' />
																</td>
															</tr>
														</logic:notEqual>
													</table>
													<!-- END BUTTON TABLE -->
												</td>
											</tr>
										</table>
										<br>
									</td>
								</tr>
							</table>
							<br>
						</td>
					</tr>
				</table>							
			</div>
			<br>
		</html:form>
		<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
	</body>
</html:html>
