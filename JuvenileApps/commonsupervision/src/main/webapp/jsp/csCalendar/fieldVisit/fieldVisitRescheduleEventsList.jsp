<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/08/2009	 lramasamy - Create JSP -->
<!--       #57092 added onload script to render buttons when page entered via back button -->

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
<title><bean:message key="title.heading" /> - csCalendar/fieldVisit/fieldVisitRecheduleEventsList.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
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
	//show("printFieldReportButton", 0)
			
	if(outcome == "RE") {
		show("viewButton", 1, "inline")
	} else {
		show("updateButton", 1, "inline")
		show("viewButton", 1, "inline")
		//show("printFieldReportButton", 1, "inline")
		if (status == "O"){
			show("enterResultsButton", 1, "inline")
			show("rescheduleButton", 1, "inline")
		}
	}
}

function processFVPrintForm(fvForm){
	var selectedFVEventId = 0;
	if(fvForm.selectedFVEventId.length!= null && fvForm.selectedFVEventId.length > 0) {
		for(i=0;i<fvForm.selectedFVEventId.length;i++) {
			if(fvForm.selectedFVEventId[i].checked) {
				selectedFVEventId = fvForm.selectedFVEventId[i].value;
			}
		}
	} else {
		selectedFVEventId = fvForm.selectedFVEventId.value;
	}

    openWindow('submitCSFVPrintReport.do?submitAction=Print&selectedFVEventId='+selectedFVEventId);
}
	
function checkExistingSelection(){
	var rb = document.getElementsByName("selectedFVEventId");
	if (rb.length > 0){	
		for (i=0; i<rb.length; i++) {
			if (rb[i].checked == true){ 
				var hfld = document.getElementsByName(rb[i].value);
				var hfldValue = hfld[0].value;
				var sfld = hfldValue.split("/");
				var status = sfld[0];
				var outcome = sfld[1];
				setStatus(status, outcome)
			}
		}
	}	
}	

function checkForm()
{
 	
	var myOption = (-1);

	if( document.getElementsByName("selectedFVEventId").length >= 1 )
	{
		for( i = 0; i < document.getElementsByName("selectedFVEventId").length; i++ )
		{
			if( document.getElementsByName("selectedFVEventId")[ i ].checked )
			{
				myOption = i;
			}
		}
	}
	if(myOption == (-1)){
		alert("Please Select a Field Visit");
		return false;
		}
	else
		return true;	
}


function validateField(theForm){
    clearAllValArrays();		      
    customValRequired("eventDateStr", "<bean:message key='errors.required' arg0='Event Date'/>","");
    addMMDDYYYYDateValidation("eventDateStr","Event Date must be in date format (mm/dd/yyyy). ");
    return validateCustomStrutsBasedJS(theForm);
}
</script>
</head>
<body topmargin="0" leftmargin="0" onload="checkExistingSelection()">
	<html:form action="/handleCSFVSelection" target="content">
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
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												CSCD - 													
													<logic:equal name="csCalendarFVForm" property="activityFlow" value="rescheduleView" >
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|62">
													</logic:equal>
													Reschedule Multiple Field Visits 
													<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule" >
													    -&nbsp;Date
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|65">														
													</logic:equal>														
													<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">
													    Summary
													    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|63">
													</logic:equal>
																										
											</td>
										</tr>
										<tr>
											<logic:equal name="status" value="confirm">
												<td class="confirm">												
												Field Visit successfully saved.
												</td>
											</logic:equal>
											<logic:equal name="status" value="confirmSuccess">
												<td class="confirm">
													Itinerary successfully reordered.
												</td>
											</logic:equal>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<logic:equal name="csCalendarFVForm" property="activityFlow" value="rescheduleView">
															<li>Select field visits to be rescheduled. Click Reschedule button.</li>
												   </logic:equal>
												   	<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
												   	<li>Enter required fields. Click Next.</li>
												   	<tr>
												      <td class=required> <img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Field</td>
											         </tr>
												   	</logic:equal>
												   	<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">
												   	<li>Review entries and click Finish. Click Back to make changes.</li>
												   	</logic:equal>
													
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
									<table width='98%' cellpadding="2" cellspacing="1"  class="borderTableBlue">
														<tr>
															<td class="formDeLabel" nowrap width="1%"><img src="/<msp:webapp/>images/required.gif" height="10">Reschedule Date</td>
															<td class="formDe">
																<SCRIPT type="text/javascript" ID="js1">
																	var cal1 = new CalendarPopup();
																	cal1.showYearNavigation();
																</SCRIPT>
																<html:text name="csCalendarFVForm" property="eventDateStr" size="10" maxlength="10"/>
																<A HREF="#" onClick="cal1.select(document.forms[0].eventDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border=0><img border=0 src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/></A>
																
															</td>
														</tr>
													</table>
									<div class=spacer4px></div>
									</logic:equal>
									
									<!-- BEGIN DETAIL TABLE -->
									<logic:equal name="csCalendarFVForm" property="activityFlow" value="rescheduleView">
									<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
										<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
										<tiles:put name="displayEvents" value="false"/>
										<tiles:put name="updatable" value="false"/>
									</tiles:insert>
								  </logic:equal>
								  
								  <logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">
									<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
										<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
										<logic:notEmpty name="csCalendarFVForm" property="eventsList">
										<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="eventsList" />										
										<tiles:put name="displayEvents" value="true"/>
										</logic:notEmpty>
										<tiles:put name="updatable" value="false"/>
									</tiles:insert>
								  </logic:equal>
									<div class="spacer4px"></div>
								
									<div class="borderTableBlue" style="width:98%">
										
									
										
										<logic:equal name="csCalendarFVForm" property="activityFlow" value="rescheduleView">
											<logic:notEmpty name="csCalendarFVForm" property="filteredList">
												<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitRescheduleEventListTile.jsp" flush="true">
													<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="filteredList" />
													<tiles:put name="view" value="true"/>
												</tiles:insert>
											</logic:notEmpty>
										</logic:equal>
										
											<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
											<logic:notEmpty name="csCalendarFVForm" property="filteredList">
												<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitRescheduleEventListTile.jsp" flush="true">
													<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="filteredList" />
													<tiles:put name="view" value="false"/>
												</tiles:insert>
											</logic:notEmpty>
										</logic:equal>
										
										<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">
											<logic:notEmpty name="csCalendarFVForm" property="filteredList">
												<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitRescheduleEventListTile.jsp" flush="true">
													<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="filteredList" />
													<tiles:put name="view" value="false"/>
												</tiles:insert>
											</logic:notEmpty>
										</logic:equal>
										
										
										
									</div>
																<!-- pagination -->
												<!--				<script type="text/javascript">renderPagination()</script>-->
																<!-- pagination -->	
										<!-- END DETAIL TABLE -->
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">				
																											
												
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="rescheduleView">														
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_RESCHEDULE%>'>
														<input type="submit" value="<bean:message key='button.reschedule'/>" name="submitAction" onclick="return checkForm() && changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVRescheduleEvents.do',false);" >
														</jims2:isAllowed>
														</logic:equal>
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
														<input type="submit" value="<bean:message key='button.next'/>" name="submitAction" onclick="return validateField(this.form) && changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVRescheduleEvents.do',false);" >
														</logic:equal>
														<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedulemultipleFV">
														<input type="submit" value="<bean:message key='button.finish'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>submitCSFVEventUpdate.do?submitAction=Link',false);" >
														</logic:equal>
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</tr>
										
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
