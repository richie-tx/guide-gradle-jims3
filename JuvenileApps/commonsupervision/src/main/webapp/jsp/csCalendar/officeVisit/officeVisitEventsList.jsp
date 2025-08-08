<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja	Create JSP -->
<%-- 06/05/2008	 Leslie Deen	#52283 Add Help File --%>
<!-- 10/08/2008  C Shimek       defect#52885 added script to reset button to reset page -->
<!-- 02/16/2009  C Shimek       #57092 added onload script to render buttons when page entered via back button -->
<!-- 03/11/2010  RYoung         defect#64357 removed all the code lookups and using only the response event -->
<!-- 06/07/2011  C Shimek       #70019 added display message for no office visit scheduled, this is NOT from original defect but something that was added after initial resolve. -->
<!-- 09/08/2011  RYoung         #70878 Print OV Casenote from Calendar -->
<!-- 04/08/2011  RCapestani     #71051 added code to display print and other buttons and deselect radio buttons and checkboxes -->
<!-- 04/12/2012  TSVines        #72079 Removed update button on the Closed Group Office Visits list -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.Features" %>
<%@page import="naming.UIConstants"%>
<%@page import="ui.common.ComplexCodeTableHelper"%>
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
<title>Common Supervision - csCalendar/officeVisit/officeVisitEventsList.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>

function renderButtonOVButtons(ovRadio, status, eventType, outcome){

	for (i=0; i< document.forms(0).length; i++){
		if (document.forms(0).elements[i].type == "checkbox"){
			document.forms(0).elements[i].checked = false;
		}
	}
	
	if (ovRadio.checked){
		show("allOVButtons", 1, "inline")
		show("openButtonsOV", 0)
		show("printButtonOV", 0)
		show("openButtonsGV", 0)
		show("closeButtonsGV", 0)
		show("alwaysShowButtonsOV", 0)
		
		if (status=="O"){
			if(eventType=="OV") {
				show("openButtonsOV", 1, "inline")
				show("alwaysShowButtonsOV", 1, "inline")
				show("printButtonOV", 1, "inline")
				document.getElementById("printOVButton").disabled=true;
				document.getElementById("printGVButton").disabled=true;
			} else if(eventType=="GV") {
				show("openButtonsGV", 1, "inline")
				show("printButtonGV", 1, "inline")
			}
			
		}else if (status=="C"){
			if(outcome != "RE") {
				if(eventType=="OV") {
					//closed OV will show update/print appt
					show("alwaysShowButtonsOV", 1, "inline")
					show("printButtonOV", 1, "inline")
					document.getElementById("printOVButton").disabled=false;
					document.getElementById("printGVButton").disabled=true;
				} else if(eventType=="GV") {
					show("closeButtonsGV", 1, "inline")
				}
			}
		}
	}
}

function renderButtonGVButtons(gvRadio, status, eventType, outcome){
	
	for (i=0; i< document.forms(0).length; i++){
		if (document.forms(0).elements[i].type == "checkbox"){
			document.forms(0).elements[i].checked = false;
		}
	}
	
	if (gvRadio.checked){
		show("printButtonGV", 0)
		show("printButtonOV", 0)
		
		if (status=="O"){
			if(eventType=="GV") {
				show("printButtonGV", 1, "inline")
				document.getElementById("printGVButton").disabled=true;
				show("allOVButtons", 0, "inline")
			}
			
		}else if (status=="C"){
			if(outcome != "RE") {
				if(eventType=="GV") {
					show("printButtonGV", 1, "inline")
					document.getElementById("printGVButton").disabled=false;
					show("allOVButtons", 0, "inline")
				}
			}
		}
	}
}

function checkAll(theCheckbox, groupName) {
	var checkboxes = document.forms[0][groupName];
	show("allOVButtons", 0, "inline");
	show("alwaysShowButtonsOV", 0, "inline");
	document.getElementById("printOVButton").disabled=true;
	var gvb = document.getElementById("printGVButton");
	if (gvb != null) {
		gvb.disabled=true;
	}	
	for (i=0; i< document.forms(0).length; i++){
		if (document.forms(0).elements[i].type == "radio"){
			document.forms(0).elements[i].checked = false;
		}
	}
	if(checkboxes.length > 0) {
		for(i=0;i<checkboxes.length;i++) {
			checkboxes[i].checked = theCheckbox.checked;
		}
	} else {
		checkboxes.checked = theCheckbox.checked;
	}
}

function checkOV(){
	show("allOVButtons", 0, "inline");
	show("alwaysShowButtonsOV", 0, "inline");
	document.getElementById("printOVButton").disabled=true;
	document.getElementById("printGVButton").disabled=true;
	for (i=0; i< document.forms(0).length; i++){
		if (document.forms(0).elements[i].type == "radio"){
			document.forms(0).elements[i].checked = false;
		}
	}
}

function resetPage(){
	show("allOVButtons", 0, "inline");
	for (i=0; i< document.forms(0).length; i++){
		if (document.forms(0).elements[i].type == "radio" || document.forms(0).elements[i].type == "checkbox"){
			document.forms(0).elements[i].checked = false;
		}
	}
}

function checkExistingSelection(){
	var rb = document.getElementsByName("selectedEventId");
	
	if (rb.length > 0){	
		for (x=0; x<rb.length; x++) {
			if (rb[x].checked == true){ 
				var hfld = document.getElementsByName(rb[x].value);
				var hfldValue = hfld[0].value;
				var sfld = hfldValue.split("/");
				var status = sfld[0];
				var eventType = sfld[1];
				var outcome = sfld[2];
				renderButtonOVButtons(rb[x], status, eventType, outcome)
			}
		}
	}	
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedEventId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}

function setPrintType(theType) {
	var hf = document.getElementsByName("eventTypeCd");
	hf[0].value = theType;
}

</script>
</head>
<body topmargin="0" leftmargin="0" onload="checkForSingleResult(); checkExistingSelection();">
	<html:form action="/handleCSOVSelection" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|2">
		<input type="hidden" name="eventTypeCd" value="">
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
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<logic:equal name="csCalendarOVForm" property="context" value="S">
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
															<td align="center" class="header">CSCD - Office Visits - <bean:write name="csCalendarOVForm" property="eventDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr>
															<td align="center" class="errorAlert"><html:errors></html:errors></td>
														</tr>
														<logic:equal name="state" value="confirm">
															<tr>
																<td align="center" class="confirm">Office Visit successfully saved.</td>
															</tr>
														</logic:equal>
														<logic:equal name="state" value="success">
														    <tr>
																<td align="center" class="confirm"><div><span class='cautionText boldText2px'>A Casenote exists referencing this event that will need to be deleted.</span></div>
				                                                </td>
															</tr>
														</logic:equal>
													</table>
													<!-- END HEADING TABLE -->
													<!-- BEGIN INSTRUCTION TABLE -->
													<table width="98%" border="0">
														<tr>
															<td>
																<ul>
																	<logic:equal name="csCalendarOVForm" property="context" value="S">
																		<li>Click Events link to view all the events for that day. Click Add New Event to add an event.</li>
																	</logic:equal>
																	<logic:equal name="csCalendarOVForm" property="context" value="P">
																		
																		<li>Select radio button for specific office visit and appropriate button.</li>
																		<li>Click Event Type hyperlink to view event details</li>
																		<li>Click Add Office Visit to add new event</li>
																		<li>Click Supervisee hyperlink to view supervisee details</li>
																	</logic:equal>
																</ul>
															</td>
														</tr>
													</table>
													<!-- END INSTRUCTION TABLE -->
													<!-- BEGIN OFFICE VISITS DETAIL TABLE -->
													<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr>
															<td class="detailHead">Office Visits List for 
																<bean:write name="csCalendarOVForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
															</td>
														</tr>
														<tr>
															<td>
																<span id="officeVisits">
																	<table width='100%' cellpadding="2" cellspacing="1" class="notFirstColumn" id="unique_id">
																		<logic:empty  name="csCalendarOVForm" property="officeVisits">
																			No Office Visits scheduled
																		</logic:empty>
																		<logic:notEmpty  name="csCalendarOVForm" property="officeVisits">
																			<tr class="formDeLabel">
																				<td nowrap width="1%"></td>
																				<td nowrap><bean:message key="prompt.eventType" />
																					<jims2:sortResults beanName="csCalendarOVForm" results="officeVisits" primaryPropSort="eventType" primarySortType="STRING" sortId="1" levelDeep="3"/>
																				</td>
																				<td nowrap><bean:message key="prompt.eventName" />
																					<jims2:sortResults beanName="csCalendarOVForm" results="officeVisits" primaryPropSort="eventName" primarySortType="STRING"  sortId="2" levelDeep="3"/>
																				</td>
																				<td nowrap><bean:message key="prompt.time" />
																					<jims2:sortResults beanName="csCalendarOVForm" results="officeVisits" primaryPropSort="startTime" primarySortType="TIME_AM_PM" defaultSort="true" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
																				</td>
																					<logic:equal name="csCalendarOVForm" property="context" value="P">
																				<td nowrap><bean:message key="prompt.supervisee" />
																					<jims2:sortResults beanName="csCalendarOVForm" results="officeVisits" primaryPropSort="partyEvent.lastName" primarySortType="STRING" sortId="4" levelDeep="3"/>
																				</td>
																					</logic:equal>	
																				<td nowrap><bean:message key="prompt.outcome" />
																					<jims2:sortResults beanName="csCalendarOVForm" results="officeVisits" primaryPropSort="outcomeDesc" primarySortType="STRING" sortId="5" levelDeep="3"/>
																				</td>
																			</tr>
																			<logic:iterate id="officeVisitsIter" name="csCalendarOVForm" property="officeVisits" indexId="index1">
																				<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																					<td nowrap width="1%">
																						<input type="radio" name="selectedEventId" value="<bean:write name='officeVisitsIter' property='eventId'/>" 
																							onclick="renderButtonOVButtons(this, '<bean:write name='officeVisitsIter' property='status'/>', 
																								'<bean:write name='officeVisitsIter' property='eventType'/>', 
																								'<bean:write name='officeVisitsIter' property='outcome'/>')">
																							<input type="hidden" name="<bean:write name='officeVisitsIter' property='eventId'/>"	
																								value="<bean:write name='officeVisitsIter' property='status'/>/<bean:write name='officeVisitsIter' property='eventType'/>/<bean:write name='officeVisitsIter' property='outcome'/>">
																					</td>
																					<td>
																						<logic:equal name="officeVisitsIter" property="eventType" value="GV">
																							<a href="/<msp:webapp/>handleCSGVSelection.do?submitAction=Link&selectedEventId=<bean:write name='officeVisitsIter' property='eventId'/>">
																							<bean:write name="officeVisitsIter" property="eventTypeDesc"/>
																					    	</a>
																						</logic:equal>
																						<logic:equal name="officeVisitsIter" property="eventType" value="OV">
																							<a href="/<msp:webapp/>displayCSOVDetails.do?submitAction=Link&selectedEventId=<bean:write name='officeVisitsIter' property='eventId'/>">
																								<bean:write name="officeVisitsIter" property="eventTypeDesc"/>
																							</a>
																						</logic:equal>
																					</td>
																					<td><bean:write name="officeVisitsIter"  property="eventName"/></td>
																					<td><bean:write name="officeVisitsIter"  property="startTime"/> - <bean:write name="officeVisitsIter"  property="endTime"/></td>
																					<logic:equal name="csCalendarOVForm" property="context" value="P">
																						<td><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='officeVisitsIter' property='superviseeId'/>">
																							<bean:write name="officeVisitsIter"  property="partyEvent.lastName"/>, <bean:write name="officeVisitsIter"  property="partyEvent.firstName"/></a>
																						</td>
																					</logic:equal>	
																					<td>
																						<logic:notEmpty name="officeVisitsIter"  property="outcomeDesc">
																							<bean:write name="officeVisitsIter"  property="outcomeDesc"/>
																						</logic:notEmpty>
																						<logic:empty name="officeVisitsIter"  property="outcomeDesc">
																							SCHEDULED
																						</logic:empty>
																					</td>
																				</tr>
																			</logic:iterate>
																		</logic:notEmpty>	
																	</table>
																</span>
															</td>
														</tr>
													</table>
													<!-- END OFFICE VISITS DETAIL TABLE -->
													<!-- BEGIN OFFICE VISITS DETAIL BUTTON TABLE -->
													<table border="0" width="100%">
														<tr class="buttonBar">
															<td align="center">
																<span id="allOVbuttons" class="hidden">
																	<span id="alwaysShowButtonsOV" class="hidden">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_UPDATE%>'>
																			<html:submit property="submitAction"><bean:message key="button.update" /></html:submit>
																		</jims2:isAllowed>
																	</span>
																	<span id="printButtonOV" class="hidden">
												    					 <html:submit property="submitAction" styleId="printOVButton" onclick="setPrintType('OV');" disabled="true"><bean:message key="button.printCasenote" /></html:submit>														
																	</span>
																	<span id="openButtonsOV" class="hidden">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_RESCHEDULE%>'>
																			<html:submit property="submitAction"><bean:message key="button.reschedule" /></html:submit>
																		</jims2:isAllowed>
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOVR_CREATE%>'>
																			<html:submit property="submitAction"><bean:message key="button.enterResults" /></html:submit>														
																		</jims2:isAllowed>
																	</span>
																	<span id="openButtonsGV" class="hidden">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOVR_CREATE%>'>
																			<html:submit property="submitAction"><bean:message key="button.enterResults" /></html:submit>														
																		</jims2:isAllowed>
																	</span>
																	
																	<span id="closeButtonsGV" class="hidden">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOVR_CREATE%>'>
																			<html:submit property="submitAction"><bean:message key="button.updateResults" /></html:submit>														
																		</jims2:isAllowed>
																	</span>
																</span>
															</td>
														</tr>
														<tr class="buttonBar">
															<td align="center" >
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_CREATE%>'>
																<input type="button" value="<bean:message key='button.addOfficeVisit'/>" name="submitAction" id="addOfficeVisitButton" 
																	onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSEventType.do?submitAction=Next&selectedEventTypeCd=OV&eventDateAsLong=<bean:write name='csCalendarOVForm' property='eventDate.time'/>',true);" >
															</jims2:isAllowed>		
															</td>
														</tr>
													</table>
													<!-- END OFFICE VISITS DETAIL BUTTON TABLE -->
													<div class="spacer4px"></div>
													<logic:iterate id="groupOfficeVisitGroupedByTimeIter" name="csCalendarOVForm" property="groupOfficeVisits">
													<!-- BEGIN OPEN OFFICE VISITS TABLE -->
														<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
															<tr class="detailHead">
																<td><bean:write name="groupOfficeVisitGroupedByTimeIter" property="eventName"/> - 
																	<bean:write name="groupOfficeVisitGroupedByTimeIter" property="eventDate" formatKey="date.format.mmddyyyy"/>
																	<bean:write name="groupOfficeVisitGroupedByTimeIter" property="startTime" /> - <bean:write name="groupOfficeVisitGroupedByTimeIter" property="endTime" />
																	</td>
																<td align="right">
																	<input type="button" value="<bean:message key='button.addAttendees'/>"
																	onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do?submitAction=Add Attendees&selectedGroupOVName=<bean:write name='groupOfficeVisitGroupedByTimeIter' property='eventName'/>',true);">
																	<input type="button" value="<bean:message key='button.update'/>"
																	onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do?submitAction=Update&selectedGroupOVName=<bean:write name='groupOfficeVisitGroupedByTimeIter' property='eventName'/>',true);">
																	<jims2:if name="groupOfficeVisitGroupedByTimeIter" property="atleaseOneEventOpen" value="true" op="equal">
																		<jims2:and name="groupOfficeVisitGroupedByTimeIter" property="atleaseOneEventClosed" value="false" op="equal"/>
																		<jims2:then>
																		<input type="button" value="<bean:message key='button.reschedule'/>"
																		onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do?submitAction=Reschedule&selectedGroupOVName=<bean:write name='groupOfficeVisitGroupedByTimeIter' property='eventName'/>',true);">
																	</jims2:then>
																	</jims2:if>
																</td>
															</tr>
															<tr>
																<td colspan="2" align="center">
																	<logic:equal name="groupOfficeVisitGroupedByTimeIter" property="atleaseOneEventOpen" value="true">
																		<div class="detailHead">Open Group Office Visits - <bean:write name="groupOfficeVisitGroupedByTimeIter" property="eventName"/></div>
																		<table width='100%' cellpadding="2" cellspacing="1" class="notFirstColumn" id="unique_id">
																			<tr class="formDeLabel">
																				<td nowrap width="1%"><input type="checkbox" name="checkAllOV" value="" onclick="checkAll(this, '<bean:write name='groupOfficeVisitGroupedByTimeIter' property='eventName' />')"></td>
																				<td nowrap width="32%"><bean:message key="prompt.eventType" /></td>														
																				<td nowrap width="57%"><bean:message key="prompt.supervisee" /></td>														
																				<td nowrap width="9%"><bean:message key="prompt.outcome" /></td>
																			</tr>
																			<logic:iterate id="groupOfficeVisitIter" name="groupOfficeVisitGroupedByTimeIter" property="sortedOfficeVisits" indexId="index2">
																				<logic:equal name="groupOfficeVisitIter" property="status" value="O">
																					<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																						<td nowrap width="1%"><input type="checkbox" name="selectedEventIds" id="<bean:write name='groupOfficeVisitGroupedByTimeIter' property='eventName'/>" value="<bean:write name='groupOfficeVisitIter' property='eventId'/>" onclick="checkOV()"></td>
																						<td><a href="/<msp:webapp/>handleCSGVSelection.do?submitAction=Link&selectedEventId=<bean:write name='groupOfficeVisitIter' property='eventId'/>">
																							<bean:write name="groupOfficeVisitIter" property="eventTypeDesc"/>
																						</a></td>
																						<td><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='groupOfficeVisitIter' property='superviseeId'/>">
																							<bean:write name="groupOfficeVisitIter" property="partyEvent.lastName"/>,
																							<bean:write name="groupOfficeVisitIter" property="partyEvent.firstName"/></a>
																						</td>
																						<td>SCHEDULED</td>
																					</tr>
																				</logic:equal>
																			</logic:iterate>
																				<tr class="buttonBar">
																					<td colspan="4" align="center">
																						<input type="submit" value="<bean:message key='button.enterResults'/>" 
																							   name="submitAction" 
																							   onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do?selectedGroupOVName=<bean:write 
																							   name='groupOfficeVisitGroupedByTimeIter' property='eventName'/>',false);" >
																					</td>
																				</tr>
																		</table>
																	</logic:equal>
																
																	<logic:equal name="groupOfficeVisitGroupedByTimeIter" property="atleaseOneEventClosed" value="true">
																		<div class="detailHead">Closed Group Office Visits - <bean:write name="groupOfficeVisitGroupedByTimeIter" property="eventName"/></div>
																		<table width='100%' cellpadding="2" cellspacing="1" class="notFirstColumn" id="unique_id">
																			<tr class="formDeLabel">
																				<td nowrap width="1%"></td>
																				<td nowrap width="32%"><bean:message key="prompt.eventType" /></td>
																				<td nowrap width="57%"><bean:message key="prompt.supervisee" /></td>
																				<td nowrap width="9%"><bean:message key="prompt.outcome" /></td>
																			</tr>
																			<logic:iterate id="groupOfficeVisitIter" name="groupOfficeVisitGroupedByTimeIter" property="sortedOfficeVisits" indexId="index3">
																				<logic:equal name="groupOfficeVisitIter" property="status" value="C">
																					<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																						<td nowrap width="1%">																		
																							<!-- if outcome is rescheduled, then do not show radio button so the user cannot perform any operation on it.-->
																							<input type="radio" name="selectedEventId" value="<bean:write name='groupOfficeVisitIter' property='eventId'/>" 
																								onclick="renderButtonGVButtons(this, '<bean:write name='groupOfficeVisitIter' property='status'/>', 
																								'<bean:write name='groupOfficeVisitIter' property='eventType'/>', 
																								'<bean:write name='groupOfficeVisitIter' property='outcome'/>')"
																								<logic:equal name="groupOfficeVisitIter" property="outcome" value="RE">
																									disabled="true"
																								</logic:equal>
																							>
																						</td>
																						<td><a href="/<msp:webapp/>handleCSGVSelection.do?submitAction=Link&selectedEventId=<bean:write name='groupOfficeVisitIter' property='eventId'/>">
																							<bean:write name="groupOfficeVisitIter" property="eventTypeDesc"/>
																						</a></td>
																						<td><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='groupOfficeVisitIter' property='superviseeId'/>">
																							<bean:write name="groupOfficeVisitIter" property="partyEvent.lastName"/>,
																							<bean:write name="groupOfficeVisitIter" property="partyEvent.firstName"/></a>
																						</td>
																						<td><bean:write name="groupOfficeVisitIter" property="outcomeDesc"/></td>
																					</tr>
																				</logic:equal>
																			</logic:iterate>
																		</table>
																		<div class="spacer">
																		
																		<!-- Check status; if closed; Hide "Update Selected Results" button -->
																		<logic:notEqual name="groupOfficeVisitIter" property="status" value="C">
																			<input type="submit" value="<bean:message key='button.updateSelectedResults'/>" name="submitAction" 
																				onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCSGVSelection.do?selectedGroupOVName=<bean:write 
																				name='groupOfficeVisitGroupedByTimeIter' property='eventName'/>',false);" >															
																		</logic:notEqual>
																		
																		<span id="printButtonGV" class="hidden">
												    					 <html:submit property="submitAction" styleId="printGVButton" onclick="setPrintType('GV');"><bean:message key="button.printCasenote" /></html:submit>														
																		</span>
																	</logic:equal>			
																</td>
															</tr>
														</table>
														<!-- END OPEN OFFICE VISITS TABLE -->
														<div class="spacer4px"></div>
													</logic:iterate>
											<!-- End DETAIL TABLE -->
													<input type="hidden" name="reset" value="true">							
											<!-- BEGIN BUTTON TABLE -->
													<table border="0" width="100%">
														<tr class="buttonBar">
															<td align="center">
															   <%if(!("confirm".equals((String)request.getAttribute("state")))) {%>
																	<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
																<%}%>
																<input type="button" value="Reset" name="reset" onClick="resetPage()">
																<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
															</td>
														</tr>
													</table>
											<!-- END BUTTON TABLE -->
												</td>
											</tr>
										</table>
										<!--  END GREEN BORDER TABLE -->
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>