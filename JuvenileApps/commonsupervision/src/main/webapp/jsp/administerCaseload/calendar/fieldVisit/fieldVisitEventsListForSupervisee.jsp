<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.UIConstants"%>
<%@ page import="naming.Features" %>
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
<title><bean:message key="title.heading" /> - csCalendar - fieldVisitEventsList.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>
function openMapquest(streetNum, street, city, state, zip, country){
	openWindow('http://www.mapquest.com/maps/map.adp?address='+streetNum+'+'+street+'+&city='+city+'&state='+state+'&zipcode='+zip+'&country='+country+'&cid=lfmaplink')
}

var statusVal="closed";

function setStatus(status, outcome){
	
	show("viewButton", 0)
	show("updateButton", 0)
	show("enterResultsButton", 0)
	show("rescheduleButton", 0)
			
	if(outcome == "RE") {
		show("viewButton", 1, "inline")
	} else {
		show("updateButton", 1, "inline")
		show("viewButton", 1, "inline")
		if (status == "O"){
			show("enterResultsButton", 1, "inline")
			show("rescheduleButton", 1, "inline")
		}
	}
}
	
</script>
</head>
<body topmargin=0 leftmargin="0">
	<html:form action="/handleCSFVSelection" target="content">
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top>
						<table width=100% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
									<!--tabs start-->
									<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="calendarTab"/>
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
						</table>
						<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
									<tiles:insert page="../../../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
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
												<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
													<tiles:put name="tab" value="CalendarTab"/> 
												</tiles:insert>					
											</td>
										</tr>
										<tr>
											<td  bgcolor=#33cc66><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
										</tr>
									</table>
								</td>
							</tr>		
							<!-- END GREEN TABS TABLE -->				
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN GREEN BORDER TABLE -->					
									<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td valign=top align=center>
												<!-- BEGIN HEADING TABLE -->
												<table width=100%>
													<tr>
														<td align="center" class="header">
															CSCD - 
																<logic:equal name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
																	Reorder Field Visit Itinerary - <bean:write name="csCalendarFVForm" property="currentItinerary.eventDate" formatKey="date.format.mmddyyyy"/>
																</logic:equal>
																<logic:notEqual name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
																	Field Visit - <bean:write name="csCalendarFVForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
																</logic:notEqual>
														</td>
													</tr>
													<tr>
														<logic:equal name="status" value="confirm">
															<td class="confirm">
																Field Visit successfully saved.
															</td>
														</logic:equal>
													</tr>
												</table>
												<!-- END HEADING TABLE -->
												<!-- BEGIN INSTRUCTION TABLE -->
												<table width="98%" border="0" cellpadding=0 cellspacing=0>
													<tr>
														<td>
															<ul>
																<li>Click Events link to view all the events for that day. Click Add New Event to add an event.</li> 
															</ul>
														</td>
													</tr>
												</table>
												<!-- END INSTRUCTION TABLE -->
												<!-- BEGIN DETAIL TABLE -->				
									<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead">Field Visits List for <bean:write name="csCalendarFVForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
												
											</script></td>
										</tr>
										
										
										<tr>
											<td>
												<table width='100%' cellpadding="2" cellspacing="1" class="notFirstColumn" id="fvlist">
													<tr class="formDeLabel">
														<td width=1%></td>
														<td nowrap>Event Type
															<jims2:sortResults beanName="csCalendarFVForm" results="eventsForSupervisee" primaryPropSort="eventType" primarySortType="STRING" sortId="1" levelDeep="4"/></td>
														<td nowrap>Purpose
															<jims2:sortResults beanName="csCalendarFVForm" results="eventsForSupervisee" primaryPropSort="purpose" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" levelDeep="4"/></td>
														<td nowrap>Time
															<jims2:sortResults beanName="csCalendarFVForm" results="eventsForSupervisee" primaryPropSort="startTime" primarySortType="DATE" sortId="3" levelDeep="4"/></td>
														<td nowrap>Outcome
															<jims2:sortResults beanName="csCalendarFVForm" results="eventsForSupervisee" primaryPropSort="outcome" primarySortType="STRING" sortId="4" levelDeep="4"/></td>
													</tr>
													<%int RecordCounter = 0;
												        String bgcolor = "";%>
													<logic:iterate id="eventsIter" name="csCalendarFVForm" property="eventsForSupervisee">
													<tr
														class=<%RecordCounter++;
														bgcolor = "alternateRow";
														if (RecordCounter % 2 == 1)
															bgcolor = "normalRow";
														out.print(bgcolor);%>>
														<td width=1%>
															<input type="radio" name="selectedFVEventId" 
																value="<bean:write name='eventsIter' property='fvEventId' />" 
																onclick="setStatus('<bean:write name='eventsIter' property='eventStatusCd' />', 
																	'<bean:write name='eventsIter' property='outcomeCd' />')">
														</td>
														<td><a href="/<msp:webapp/>handleCSFVSelection.do?submitAction=View&selectedFVEventId=<bean:write name='eventsIter' property='fvEventId'/>">
															FIELD VISIT</a>
														</td>
														<td><bean:write name="eventsIter" property="purpose"/></td>
														<td><bean:write name="eventsIter" property="startTime" formatKey="time.format.hhmma"/> - <bean:write name="eventsIter" property="endTime" formatKey="time.format.hhmma"/></td>
														<td>
															<logic:empty name="eventsIter" property="outcomeCd">
																SCHEDULED
															</logic:empty>
															<logic:notEmpty name="eventsIter" property="outcomeCd">
																<bean:write name="eventsIter" property="outcome"/>
															</logic:notEmpty>
														</td>
													</tr>
													</logic:iterate>
												</table>
											</td>
										</tr>
									</table>
										
										
																<!-- pagination -->
												<!--				<script type="text/javascript">renderPagination()</script>-->
																<!-- pagination -->	
										<!-- END DETAIL TABLE -->
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<logic:equal name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<input type="submit" value="<bean:message key='button.saveContinue'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>submitCSFVEventUpdate.do',false);" >
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</tr>											
											</logic:equal>
											
											<logic:notEqual name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
												<tr>
													<td align="center">
														<input type="submit" value="<bean:message key='button.view'/>" name="submitAction" id="viewButton" class="hidden"  >
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_UPDATE%>'>
														<input type="submit" value="<bean:message key='button.update'/>" name="submitAction" id="updateButton" class="hidden"  >
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFVR_CREATE%>'>
														<input type="submit" value="<bean:message key='button.enterResults'/>" name="submitAction" id="enterResultsButton" class="hidden"  >
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_RESCHEDULE%>'>
														<input type="submit" value="<bean:message key='button.reschedule'/>" name="submitAction" id="rescheduleButton" class="hidden"  >
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_CREATE%>'>
															<input type="submit" value="<bean:message key='button.addFieldVisit'/>" name="submitAction"
																onclick="changeFormActionURL(this.form, '/<msp:webapp/>handleCaseloadSelection.do?submitAction=Create Field Visit',true);">	
														</jims2:isAllowed>
													</td>
												</tr>
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</tr>
											</logic:notEqual>
										</table>
										<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table><br>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	</div>
	<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
