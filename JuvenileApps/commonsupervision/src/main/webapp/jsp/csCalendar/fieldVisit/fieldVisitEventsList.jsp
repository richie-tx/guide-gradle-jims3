<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->
<!-- 02/16/2009  C Shimek       #57092 added onload script to render buttons when page entered via back button -->
<!-- 12/09/2010  C Shimek       #68627 revised openMapquest() to use new mapquest page  -->

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
<title><bean:message key="title.heading" /> - csCalendar/fieldVisit/fieldVisitEventsList.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>	
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>	


<script>
function checkNumeric(input)
{
var regexp = /^[0-9]+$/; //defect Fix: JIMS200076930 changed the incorrect regx from[1-9] to [0-9]
if(regexp .test(input))
{
return true;
}
return false;
}


function checktime(thetime) {
	var a,b,c,f,err=0;
	a=thetime;
	if (a.length != 5) err=1;
	b = a.substring(0, 2);
	c = a.substring(2, 3); 
	f = a.substring(3, 5); 
	if (/\D/g.test(b)) err=1; //not a number
	if (/\D/g.test(f)) err=1; 
	if (b<0 || b>12) err=1;
	if (f<0 || f>59) err=1;
	if (c != ':') err=1;
	if (err==1) {
	return false;	
	}
  return true;	
}

function openMapquest(streetNum, street, city, state, zip, country){
	if (country == null || country == ""){
		country = 'us';
	}
// This link worked on the "classic" page	
//	openWindow('http://www.mapquest.com/maps/map.adp?address='+streetNum+'+'+street+'+&city='+city+'&state='+state+'&zipcode='+zip+'&country='+country+'&cid=lfmaplink')
// this link works on the new page.
// If the address is bad or not in their database, mapquest will show map for city only with SEARCH FOR of city, state. ie Houston, TX
	openWindow('http://www.mapquest.com/maps/map.adp?city=' + city + '&state=' + state + '&address=' + streetNum + '+' + street + '&zip=' + zip + '&country=' + country +'&zoom=12');
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


function checkForm(theForm){	
	var a = 0;
	var b = 0;
	var c = 0;
	var d = 0;
	
	var length =document.getElementById("count").value;
	if(length > 0){
	for(i=0; i<length; i++){
		var sTime = document.getElementsByName("eventsList["+i+"].startTime1")[0].value
		if(sTime!=null && sTime!=""){
			if(!checktime(sTime)) { 						                   
				a = 1;
			   break;
		      }	
	       }
    
		var eTime = document.getElementsByName("eventsList["+i+"].endTime1")[0].value
		if(eTime!=null && eTime!=""){
			if(!checktime(eTime)) { 						                   
				b = 1;
			   break;
		      }	
	       }

		var seq = document.getElementsByName("eventsList["+i+"].sequenceNum")[0].value
		if(seq!=null && seq!=""){
			if(!checkNumeric(seq)) { 
				d = i;						                   
				c = 1;
			   break;
		      }	
	       }
      }
  }
	
	if(a ==1 || b==1){ 
		alert("Time is not in proper 12 hour format, ie 03:15") 	   
    return false;
	}
	if(c ==1){ 		
		alert("Please Enter a Valid Number") 
		document.getElementsByName("eventsList["+d+"].sequenceNum")[0].focus();	   
    return false;
	}
   return true;	
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

function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedFVEventId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onload="checkForSingleResult(); checkExistingSelection();">
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
													<logic:equal name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|26">
														Reorder Field Visit Itinerary - <bean:write name="csCalendarFVForm" property="currentItinerary.eventDate" formatKey="date.format.mmddyyyy"/>
													</logic:equal>
													<logic:notEqual name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|25">
														Field Visit Itinerary
													</logic:notEqual>
													
											</td>
										</tr>
										<tr>
											<logic:equal name="status" value="confirm">
												<td class="confirm">
													<!--<script>
														if(location.search=="?fv" || action=="update"){
															document.write("Field Visit successfully saved.")
															}else if(action=="delete"){
																document.write("Field Visit successfully deleted.")
																}else if(location.search=="?itinUpdate"){
																	document.write("Itinerary successfully updated.")
																	}else	if(location.search=="?itinReorder"){
																		document.write("Itinerary successfully reordered.")
																	}
																</script>-->
																Field Visit successfully saved.													
													
												</td>
											</logic:equal>
											
											<logic:equal name="status" value="confirmDelete">
										       <tr>
												<td align="center" class="confirm"><div><span class='cautionText boldText2px'>A Casenote exists referencing this event that will need to be deleted.</span></div>
                                                </td>
											   </tr>
										    </logic:equal>
											<logic:equal name="status" value="confirmSuccess">
												<td class="confirm">
													Itinerary successfully reordered.
												</td>
											</logic:equal>
											
											<logic:equal name="status" value="success">
												<td class="confirm">
													Field Visits successfully rescheduled.
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
													<logic:equal name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
														<li>Click field visit Order # to view details.</li>
													</logic:equal>
													<logic:notEqual name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
														<li>Select a field visit and click appropriate button below.</li>
													</logic:notEqual>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!-- BEGIN DETAIL TABLE -->
									
									<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
										<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
										<tiles:put name="displayEvents" value="false"/>
										<tiles:put name="updatable" value="true"/>
									</tiles:insert>
								
									<div class="spacer4px"></div>
								
									<div class="borderTableBlue" style="width:98%">
										
										<logic:equal name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
											<logic:notEmpty name="csCalendarFVForm" property="eventsList">
												<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitEventListTile.jsp" flush="true">
													<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="eventsList" />
													<tiles:put name="view" value="true"/>
													<tiles:put name="reorder" value="true"/>
													<tiles:put name="attrPrefix" value="eventsList"/>
												</tiles:insert>
											</logic:notEmpty>
										</logic:equal>
										
										<logic:notEqual name="csCalendarFVForm" property="activityFlow" value="reorderItinerary">
											<logic:notEmpty name="csCalendarFVForm" property="eventsList">
												<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitEventListTile.jsp" flush="true">
													<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="eventsList" />
													<tiles:put name="view" value="false"/>
												</tiles:insert>
											</logic:notEmpty>
										</logic:notEqual>
										
										
									</div>
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
														<input type="submit" value="<bean:message key='button.saveContinue'/>" name="submitAction" onclick=" return checkForm(this.form) && changeFormActionURL(this.form, '/<msp:webapp/>submitCSFVEventUpdate.do',false);" >
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
													</td>
												</tr>
												<tr>
													<td align="center">
														<input type="submit" value="<bean:message key='button.reorderItinerary'/>" name="submitAction" onclick="changeFormActionURL(this.form, '/<msp:webapp/>displayCSFVReorderItinerary.do',false);" >
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_CREATE%>'>
														<input type="button" value="Add Field Visit" name="add" onClick="goNav('/<msp:webapp/>handleCSEventType.do?submitAction=Next&selectedEventTypeCd=FV&eventDateAsLong=<bean:write name='csCalendarFVForm' property='currentItinerary.eventDate.time' />')">
														
														</jims2:isAllowed>
													</td>
												</tr>
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<input type="button" value="Print Itinerary" name="print" onClick="window.print()">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALRP_CREATE%>'>
														<%--<input type="button" value="Print Field Report Form" name="print"> --%>
														<input type="button" name="submitAction" value="<bean:message key='button.printFieldReport'/>" onclick="processFVPrintForm(this.form);" id = "printFieldReportButton">
														</jims2:isAllowed>
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>