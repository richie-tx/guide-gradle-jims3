<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja	Create JSP -->
<%-- 06/05/2008	 Leslie Deen	#52283 Add Help File --%>
<!-- 01/19/2012  RYoung  - Defect #72412 CSCD: Calendar - Field Validation missing on Event Date. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="naming.UIConstants"%>
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
<title>Common Supervision - csCalendar/fieldVisit/addCSEvent.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
	<html:javascript formName="addCSEvent"/>
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
											
	<script>
//this function confirms that the user wants to create an event in the past
	function checkBackDate(theForm){
		var theDate = new Date();
		var theEventDate = new Date(theForm.eventDateStr.value);
		var sub5Year = theDate.getFullYear()-5;
		var sub5dateStr = (theDate.getMonth() + "/" + theDate.getDate() + "/" + sub5Year);
		var newSub5Date = new Date( sub5dateStr );

		if ( theEventDate <  newSub5Date ){
			
			alert( "Event Date cannot be more than 5 years previous to current date." );
			theForm["eventDateStr"].focus();
			return false;
		}
		
		var plus5Year = theDate.getFullYear()+5;
		var plus5dateStr = (theDate.getMonth() + "/" + theDate.getDate() + "/" + plus5Year);
		var newPlus5Date = new Date( plus5dateStr );

		if ( theEventDate >  newPlus5Date ){
			
			alert( "Event Date cannot be more than 5 years to the future of current date." );
			theForm["eventDateStr"].focus();
			return false;
		}
	 	
	 	var theMonth = theDate.getMonth()+1;
		var theDay = theDate.getDate();
		var theYear = theDate.getYear();
		if (theMonth < 10){
				theMonth = "0"+theMonth;
			}
			if (theDay < 10){
				theDay = "0"+theDay;
			}

		 var dateStr = (theMonth + "/" + theDay + "/" + theYear);
		 var theCurrentDate = new Date(dateStr);
		if (theEventDate <  theCurrentDate){
			return confirm("Event Date is in the past.\nClick Ok if you want to continue.")
		}
	}
	
	function checkForOther(theSelect){
		var theVal = theSelect.options[theSelect.selectedIndex].innerHTML;
		
		if (theVal == "OTHER"){
			show("otherField", 1, "inline");
			theSelect.form["otherEventTypeName"].focus();
		}else {
			show("otherField", 0);
		}
	}
	 
	function validateOtherEventType(theForm) {
		if(theForm["selectedEventTypeCd"].value == "OT") {
			if(theForm["otherEventTypeName"].value == "") {
				alert("Please specify a name for Other Event Type.");
				theForm["otherEventTypeName"].focus();
				return false;
			}
		}
		
		return true;
	}
		
	</script>
</head>
		<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
			<html:form action="/handleCSEventType" target="content" focus="eventDateStr">
			<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|32">
		
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
											<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
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
									
									<logic:equal name="csCalendarDisplayForm" property="context" value="S">
										<tr>
											<td valign="top" align="center">
												<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
												<tiles:insert page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
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
															<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
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
												
												
									</logic:equal>
									
									<tr>
										<td valign=top align=center>
											<!-- BEGIN HEADING TABLE -->
											<table width=100%>
												<tr>
													<td align="center" class="header"><bean:message key="title.CSCD"/> - Add Event - Select Event Type</td>
												</tr>
											</table>
											
											<!-- END HEADING TABLE -->
											<%-- BEGIN ERROR TABLE --%>
											<table width="98%" align="center">
												<tr>
													<td align="center" class="errorAlert"><html:errors/></td>
												</tr>
											</table>
											<%-- END ERROR TABLE --%>
											
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
														<td class="required" colspan="2"><bean:message key="prompt.requiredFields"/>
													</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->
											<!-- BEGIN DETAIL TABLE -->
											<table width='98%' cellpadding="2" cellspacing="1"  class="borderTableBlue">
												<tr>
													<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.2.diamond"/>Event Date</td>
													<td class="formDe">
														<SCRIPT LANGUAGE="JavaScript" ID="js1">
															var cal1 = new CalendarPopup();
															cal1.showYearNavigation();
														</SCRIPT>
														<html:text property="eventDateStr" size="10"/>
														<A HREF="#" onClick="cal1.select(document.forms[0].eventDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border=0><img border=0 src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/></A>
														
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.2.diamond"/>Event Type</td>
													<td class="formDe">
														<html:select property="selectedEventTypeCd" onchange="checkForOther(this)">
															<option><bean:message key="select.generic"/></option>
															<html:optionsCollection property="eventTypeList" label="description" value="eventType"/>
															
														</html:select> <span id=otherField class=hidden><html:text property="otherEventTypeName" size="15" maxlength="15"/></span>
													</td>
												</tr>
											</table>
											<!-- End DETAIL TABLE -->
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
														<html:submit property="submitAction" onclick="return validateAddCSEvent(this.form) && validateOtherEventType(this.form) && checkBackDate(this.form)"><bean:message key="button.next" /></html:submit>
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</tr>
											</table>
											<!-- END BUTTON TABLE -->
											<!-- END DETAIL TABLE -->
										</td>
									</tr>
									
									<logic:equal name="csCalendarDisplayForm" property="context" value="S">
										</table><br></td></tr>
									</logic:equal>	
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
