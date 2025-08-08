<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->
<!-- 03/11/2010  C Shimek      - 64385 isAllowed tag in place around Quadrant field but had syntax error2 -->
<!-- 12/09/2011  R Capestani   - 71943 add Field Visit Officer and Radio Call Number -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
<%@page import="naming.Features"%>

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
<title><bean:message key="title.heading" /> - csCalendar - fieldVisit - itineraryUpdate.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="itineraryUpdate"/>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
		<script>
			function validateQuadrant(theForm) {
				var	quadrantSelect = theForm["currentItinerary.quadrantCd"];
				if(quadrantSelect != null) {
					if(quadrantSelect[quadrantSelect.selectedIndex].value == "") {
						alert("Quadrant is required.");						
						quadrantSelect.focus();
						return false;
					}
				}
				
				return true;
			}

			function validateField(theForm){
				clearAllValArrays();
				add12HrTimeValidation("currentItinerary.inOfficeFrom","In OfficeFrom is not in proper 12 hour format, ie 03:15","");
			    add12HrTimeValidation("currentItinerary.inOfficeTo","In OfficeTo is not in proper 12 hour format, ie 03:15","");
			    add12HrTimeValidation("currentItinerary.inFieldFrom","In FieldFrom is not in proper 12 hour format, ie 03:15","");
			    add12HrTimeValidation("currentItinerary.inFieldTo","In FieldTo is not in proper 12 hour format, ie 03:15","");
		         return validateCustomStrutsBasedJS(theForm);	
			}
		</script>
		
		</head>
		<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
			<html:form action="/displayCSFVItinerarySummary" target="content" onsubmit="return validateQuadrant(this) &&  validateField(this) && validateItineraryUpdate(this)">
				<div align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top">
								<table width=100% border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign="top">
											<!--tabs start-->
											<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
												<tiles:put name="tab" value="calendarTab"/>
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
										<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
									</tr>
									<logic:equal name="csCalendarFVForm" property="context" value="S">
										<tr>
											<td valign="top" align="center">
												<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
												<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
												<!-- END SUPERVISEE INFORMATION TABLE  -->
											</td>
										</tr>	
										<!-- BEGIN GREEN TABS TABLE -->		
										<tr>
											<td valign="top" align="center"> 
												<table width="98%" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
													</tr>						
													<tr>
														<td valign="top">
															<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
																<tiles:put name="tab" value="CalendarTab"/> 
															</tiles:insert>					
														</td>
													</tr>
													<tr>
														<td  bgcolor="#33cc66"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
													</tr>
												</table>
											</td>
										</tr>
												
										<!-- END GREEN TABS TABLE -->				
										<tr>
											<td valign="top" align="center">
												<!-- BEGIN GREEN BORDER TABLE -->					
												<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
												</logic:equal>
									
									<tr>
										<td valign="top" align="center">
										
											<!-- BEGIN HEADING TABLE -->
											<table width="100%">
												<tr>
													<td align="center" class="header">CSCD -
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="create">
															Add Field Visit
															<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|56">
														</logic:equal>
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
															Reschedule Field Visit
															<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|58">
														</logic:equal>														
														<logic:equal name="csCalendarFVForm" property="secondaryActivityFlow" value="createItinerary">
														  <logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">
														    Reschedule Multiple Field Visits
														  </logic:equal>
															- Create Itinerary
														</logic:equal>
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="updateItinerary">
															Update Field Visit Itinerary
															<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|27">
														</logic:equal>
														
														</td>
												</tr>
											</table>
											<!-- END HEADING TABLE -->
											<!-- BEGIN INSTRUCTION TABLE -->
											<table width="98%" border="0">
												<tr>
													<td>
														<ul>
															<li>Enter required fields. Click Next.</li>
														</ul>
													</td>
												</tr>
												<tr>
													<td class="required"><bean:message key="prompt.3.diamond"/>Required Field</td>
												</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->
											<!-- BEGIN DETAIL TABLE -->
											<table width="98%" cellpadding="2" cellspacing="0"  class="borderTableBlue">
												<tr class="detailHead">
													<td colspan="4">Itinerary Information - <bean:write name="csCalendarFVForm" property="currentItinerary.eventDate" formatKey="date.format.mmddyyyy"/>
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" cellpadding="2" cellspacing="1">
															<jims:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_QUAD_SEARCH%>'>
															   <tr id=quadrant>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/>Quadrant</td>
																	<td colspan="3" class="formDe">
																		<html:select name="csCalendarFVForm" property="currentItinerary.quadrantCd">
																			<option value=""><bean:message key="select.generic"/></option>
																			<html:optionsCollection name="csCalendarFVForm" property="quadrantList" value="supervisionCode" label="description"/>
																		</html:select>
																	</td>
																</tr>
															</jims:isAllowed>		
															
															<tr><!-- FV Officer Name Added -->
																<td class="formDeLabel" nowrap>FV Officer</td>
																<td colspan="3" class="formDe">
																	<bean:write name="csCalendarFVForm" property="currentItinerary.fvOfficer"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>In Office From</td>
																<td colspan="3" class="formDe">																	
																  <html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentItinerary.inOfficeFrom"/>
							                                       <html:select name="csCalendarFVForm" property="currentItinerary.inOfficeAMPMId1">
								                                       <html:option value="AM">AM</html:option>
								                                       <html:option value="PM">PM</html:option>
					                                               </html:select>
																	to																	
																 <html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentItinerary.inOfficeTo"/>
							                                       <html:select name="csCalendarFVForm" property="currentItinerary.inOfficeAMPMId2">
								                                       <html:option value="AM">AM</html:option>
								                                       <html:option value="PM">PM</html:option>
					                                               </html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/>In Field From</td>
																<td colspan="3" class="formDe">																
																  <html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentItinerary.inFieldFrom"/>
							                                        <html:select name="csCalendarFVForm" property="currentItinerary.inFieldAMPMId1">
								                                       <html:option value="AM">AM</html:option>
								                                       <html:option value="PM">PM</html:option>
					                                                </html:select>
																	to	
																  <html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentItinerary.inFieldTo"/>																																
							                                         <html:select name="csCalendarFVForm" property="currentItinerary.inFieldAMPMId2">
								                                       <html:option value="AM">AM</html:option>
								                                       <html:option value="PM">PM</html:option>
					                                                 </html:select>	
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/>Mobile/Pager</td>
																<td colspan="3" class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.mobilePager.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
																	-
																	<html:text name="csCalendarFVForm" property="currentItinerary.mobilePager.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
																	-
																	<html:text name="csCalendarFVForm" property="currentItinerary.mobilePager.fourDigit" size="4" onkeyup="return autoTab(this, 4);"/>
																</td>
															</tr>
															<tr><!-- Radio Call Number Added -->
																<td class="formDeLabel" nowrap>Radio Call Number</td>
																
																	<td class="formDe" colspan=3><html:text name="csCalendarFVForm" property="currentItinerary.radioCallNum" size="8" maxlength="8"/></td>
																
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>Passenger 1</td>
																<td class="formDe" colspan="3">
																	<table border="0" cellspacing="1">
											                              <tr>
											                                <td class="formDeLabel" colspan=2>Last</td>
											                              </tr>
											                              <tr>
											                                <td class="formDe" colspan=2><html:text name="csCalendarFVForm" property="currentItinerary.passenger1.lastName" size="30" maxlength="75"/></td>
											                              </tr>
											                              <tr>
											                                <td class="formDeLabel">First</td>
											                                <td class="formDeLabel">Middle</td>
											                              </tr>
											                              <tr>
											                                <td class="formDe"><html:text name="csCalendarFVForm" property="currentItinerary.passenger1.firstName" size="25" maxlength="50"/></td>
											                                <td class="formDe"><html:text name="csCalendarFVForm" property="currentItinerary.passenger1.middleName" size="25" maxlength="50"/></td>
											                              </tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>Passenger 2</td>
																<td class="formDe" colspan="3">
																	<table border="0" cellspacing="1">
																		<tr>
																			<td class="formDeLabel" colspan=2>Last</td>
																		</tr>
																		<tr>
																			<td class="formDe" colspan=2><html:text name="csCalendarFVForm" property="currentItinerary.passenger2.lastName" size="30" maxlength="75"/></td>
																		</tr>
																		<tr>
																			<td class="formDeLabel">First</td>
																			<td class="formDeLabel">Middle</td>
																		</tr>
																		<tr>
																			<td class="formDe"><html:text name="csCalendarFVForm" property="currentItinerary.passenger2.firstName" size="25" maxlength="50"/></td>
																			<td class="formDe"><html:text name="csCalendarFVForm" property="currentItinerary.passenger2.middleName" size="25" maxlength="50"/></td>
																		</tr>
																	</table>
																</td>
															</tr>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>Passenger 3</td>
																<td class="formDe" colspan="3">
																	<table border="0" cellspacing=1>
																		<tr>
																			<td class="formDeLabel" colspan=2>Last</td>
																		</tr>
																		<tr>
																			<td class="formDe" colspan=2><html:text name="csCalendarFVForm" property="currentItinerary.passenger3.lastName" size="30" maxlength="75"/></td>
																		</tr>
																		<tr>
																			<td class="formDeLabel">First</td>
											                                <td class="formDeLabel">Middle</td>
																		</tr>
																		<tr>
																			<td class="formDe"><html:text name="csCalendarFVForm" property="currentItinerary.passenger3.firstName" size="25" maxlength="50"/></td>
																			<td class="formDe"><html:text name="csCalendarFVForm" property="currentItinerary.passenger3.middleName" size="25" maxlength="50"/></td>
																		</tr>
																	</table>
																</td>
															</tr>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/>Car Type</td>
																<td colspan="3" class="formDe">
																	<html:select name="csCalendarFVForm" property="currentItinerary.carTypeCd">
																		<html:optionsCollection name="csCalendarFVForm" property="carTypeList" value="supervisionCode" label="description"/>
																	</html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap width="1%">Mileage In</td>
																<td class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.mileageIn" size="8" maxlength="8"/>
																</td>
																<td class="formDeLabel" nowrap width="1%">Mileage Out</td>
																<td class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.mileageOut" size="8" maxlength="8"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap width="1%">Automobile License #</td>
																<td class="formDe" colspan="3">
																	<html:text name="csCalendarFVForm" property="currentItinerary.autoLicense" size="8" maxlength="8"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap width="1%">Year</td>
																<td class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.autoYear" size="4" maxlength="4"/>
																</td>
																<td class="formDeLabel" nowrap width="1%">Make</td>
																<td class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.autoMake" size="15" maxlength="15"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap width="1%">Model</td>
																<td class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.autoModel" size="15" maxlength="15"/>
																</td>
																<td class="formDeLabel" nowrap width="1%">Color</td>
																<td class="formDe">
																	<html:text name="csCalendarFVForm" property="currentItinerary.autoColor" size="15" maxlength="15"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<!-- End DETAIL TABLE -->
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<input type="button" value="Back" name="return" onClick="history.go(-1)">
														
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="updateItinerary">
															<input type="submit" value="<bean:message key='button.next'/>" name="submitAction"
																onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVItinerarySummary.do',false);"> 
														</logic:equal>
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
															<input type="submit" value="<bean:message key='button.next'/>" name="submitAction"
																onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVEventUpdate.do?submitAction=Update',false);">	
														</logic:equal>
														
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="create">
															<logic:equal name="csCalendarFVForm" property="context" value="S">
															   <logic:notEqual name="csCalendarFVForm" property="itineraryCreate" value="Y">
																<input type="submit" value="<bean:message key='button.next'/>" name="submitAction"
																onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCaseloadSelection.do?submitAction=Create Field Visit',false);">	
															  </logic:notEqual>
															  <logic:equal name="csCalendarFVForm" property="itineraryCreate" value="Y">
															    <input type="submit" value="<bean:message key='button.next'/>" name="submitAction" 
																onclick="return validateQuadrant(this.form) &&  validateField(this.form) && validateItineraryUpdate(this.form) && changeFormActionURL(this.form, '/<msp:webapp/>handleCaseloadSelection.do?submitAction=Create Field Visit',true);">	
															  </logic:equal>
															</logic:equal>
															
															<logic:equal name="csCalendarFVForm" property="context" value="P">															    
																<html:submit property="submitAction"><bean:message key="button.next" /></html:submit>															
															</logic:equal>														
															
														</logic:equal>		
														
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">										
														<input type="submit" value="<bean:message key='button.next'/>" name="submitAction"
																onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVRescheduleEvents.do?submitAction=Link',false);">
														</logic:equal>		
														
														<input type="button" value="Cancel" name="return" onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVItinerarySummary.do?submitAction=Cancel',true);">
													</td>
												</tr>
											</table>
											<!-- END BUTTON TABLE -->
											<!-- END DETAIL TABLE -->
										</td>
									</tr>
								</table><br>
							</td>
						</tr>
					</table><br>
				</td>
			</tr>
		</table>
	</div>
	<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>