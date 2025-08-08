<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.Features" %>
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
<title><bean:message key="title.heading" /> - csCalendar - scheduleNextFV.jsp</title>
      
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>      
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>		
function checkBackDate(theForm){
	 var theDate=new Date();
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
	 var theEventDate= new Date(theForm.eventDateStr.value);												 
		if (theEventDate <  theCurrentDate){
			return confirm("Event Date is in the past.\nClick Ok if you want to continue.")
		}
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
	<body topmargin=0 leftmargin="0">
		<html:form action="/submitCSFVEventUpdate" target="content">
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
									<td valign=top align=center>
										<!--header area start-->
										<table width=98% border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td bgcolor=#cccccc colspan=2><!--header start-->
													<!--header start-->
													<tiles:insert page="../../../common/superviseeHeader.jsp" flush="true">
													</tiles:insert>	
													<!--header end-->
												</td>
											</tr>
											<tr>
												<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
											</tr>
										</table>
										<!--header area end-->
										<!--casefile tabs start-->
										<table width=98% border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td valign=top>
													<!--tabs start-->
													<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
														<tiles:put name="tab" value="CalendarTab"/>
													</tiles:insert>	
													<!--tabs end-->
												</td>
											</tr>
											<tr>
												<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
											</tr>
										</table>
										<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
											<tr>
												<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
											</tr>
											<tr>
												<td valign=top align=center>
													<!-- BEGIN HEADING TABLE -->
													<table width=100%>
														<tr>
															<td align="center" class="header">CSCD - Add Event											
															</td>
																	</tr>
																</table>
																<!-- END HEADING TABLE -->
																<table width="98%" border="0">
												<tr>
													<td>
														<ul>
															<li>Enter required fields. Click Next.</li>
														</ul>
													</td>
												</tr>
													<tr>
													
												      <td class=required> <img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Field</td>
											         
													</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->
											<!-- BEGIN DETAIL TABLE -->
											<table width='98%' cellpadding="2" cellspacing="1"  class="borderTableBlue">
												<tr>
													<td class="formDeLabel" nowrap width=1%><img src="/<msp:webapp/>images/required.gif" height="10">Event Date</td>
													<td class="formDe">
														<SCRIPT LANGUAGE="JavaScript" ID="js1">
															var cal1 = new CalendarPopup();
															cal1.showYearNavigation();
														</SCRIPT>
														<html:text name="csCalendarFVForm" property="eventDateStr" size="10" maxlength="10"/>
														<A HREF="#" onClick="cal1.select(document.forms[0].eventDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border=0><img border=0 src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/></A>
														
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap width=1%>Event Type</td>
													<td class="formDe"><bean:message key="prompt.fieldVisit"/>
														
													</td>
												</tr>
											</table>
											<!-- End DETAIL TABLE -->
																
																<!-- BEGIN BUTTON TABLE -->
																
																
																
																<table border="0" width="100%">
																	<tr>
																		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																			<input type="submit" value="<bean:message key='button.next'/>" name="submitAction" onclick="return validateField(this.form) && checkBackDate(this.form) && changeFormActionURL(this.form, '/<msp:webapp/>handleScheduleNextFV.do?submitAction=Next',false);">
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
										</table><br>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<!-- END  TABLE -->
			</div>
			<br>
		</html:form>
	<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
