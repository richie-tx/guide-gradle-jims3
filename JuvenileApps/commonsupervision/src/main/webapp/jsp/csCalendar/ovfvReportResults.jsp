<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/09/2010  CShimek   #66759 Revised default sort to date -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.CSEventsReportConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<title><bean:message key="title.heading"/> - csCalendar/ovfvReportResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleCSEventsSearch" target="content">
<div align="center">
<!-- BEGIN MAIN TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
<!-- END BLUE TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->
		 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		 		<tr>
		 			<td><img src="/<msp:webapp/>images/spacer.gif" height="5" /></td>
		 		</tr>
		 		<tr>
		 			<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
		 				<table width="100%">
		 					<tr>
		 						<td align="center" class="header">
		 							<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
		 							<logic:equal name="csCalendarEventsSearchForm" property="calendarCategory" value="<%=CSEventsReportConstants.CS_CALENDAR_CATEGORY_FIELD_VISITS %>">
		 								<bean:message key="prompt.fieldVisit"/>
		 								<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/CSCD_Caseload.htm#|4">
		 							</logic:equal>
		 							<logic:equal name="csCalendarEventsSearchForm" property="calendarCategory" value="<%=CSEventsReportConstants.CS_CALENDAR_CATEGORY_OFFICE_VISITS %>">
		 								<bean:message key="prompt.officeVisit"/>
		 								<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/CSCD_Caseload.htm#|8">
		 							</logic:equal>
		 							<logic:equal name="csCalendarEventsSearchForm" property="calendarCategory" value="<%=CSEventsReportConstants.CS_CALENDAR_CATEGORY_OTHER_EVTS %>">
		 								<bean:message key="prompt.otherEvents"/>
		 								<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/CSCD_Caseload.htm#|9">
		 							</logic:equal>
		 							<bean:message key="prompt.report"/>
		 						</td>
		 					</tr>
		 				</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
<!-- END ERROR TABLE -->
<!-- BEGIN CASLOAD HEADER TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">	
							<tr class="paddedFourPix">
						  		<td class=formDeLabel><bean:message key="prompt.currentCaseload"/></td>
						 	</tr>
						 	<tr>
						 		<td bgcolor="#cccccc" colspan="2">
						 			<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="headerLabel"><bean:message key="prompt.officer"/></td>
											<td class="headerData"><bean:write name="csCalendarEventsSearchForm" property="selectedOfficerNameNPosition" /></td>
										</tr>
										<tr>
											<td class="headerLabel" nowrap width="1%"><bean:message key="prompt.dateRange"/></td>
											<td class="headerData"><bean:write name="csCalendarEventsSearchForm" property="startDateStr"/> - <bean:write name="csCalendarEventsSearchForm" property="endDateStr"/></td>
										</tr>
									</table>				
						 		</td>
					  		</tr>
						</table>	
<!-- END CASLOAD HEADER TABLE -->
						<div class="spacer4px"></div>
<!-- BEGIN BODY TABLE -->
						<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td colspan="6">
									<logic:equal name="csCalendarEventsSearchForm" property="calendarCategory" value="<%=CSEventsReportConstants.CS_CALENDAR_CATEGORY_FIELD_VISITS %>">
		 								<bean:message key="prompt.fieldVisit"/>
		 							</logic:equal>
		 							<logic:equal name="csCalendarEventsSearchForm" property="calendarCategory" value="<%=CSEventsReportConstants.CS_CALENDAR_CATEGORY_OFFICE_VISITS %>">
		 								<bean:message key="prompt.officeVisit"/>
		 							</logic:equal>
		 							<logic:equal name="csCalendarEventsSearchForm" property="calendarCategory" value="<%=CSEventsReportConstants.CS_CALENDAR_CATEGORY_OTHER_EVTS %>">
		 								<bean:message key="prompt.otherEvents"/>
		 							</logic:equal>
									- Total Count: <bean:write name="csCalendarEventsSearchForm" property="csEventsCount" />
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding="1" cellspacing="1" border="0">
										<tr class="formDeLabel">
											<td nowrap width="15%"><bean:message key="prompt.superviseeName"/><jims2:sortResults beanName="csCalendarEventsSearchForm" results="csEventsList" primaryPropSort="defendantName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1" levelDeep="2"/></td>
											<td nowrap width="10%"><bean:message key="prompt.SPN"/><jims2:sortResults beanName="csCalendarEventsSearchForm" results="csEventsList" primaryPropSort="defendantId" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="2"/></td>
											<td nowrap width="12%"><bean:message key="prompt.outcome"/><jims2:sortResults beanName="csCalendarEventsSearchForm" results="csEventsList" primaryPropSort="outcome" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="2"/></td>
											<td nowrap width="30%"><bean:message key="prompt.resultsEntered"/><jims2:sortResults beanName="csCalendarEventsSearchForm" results="csEventsList" primaryPropSort="resultUserNPositionName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" levelDeep="2"/></td>
											<td nowrap width="15%"><bean:message key="prompt.type"/><jims2:sortResults beanName="csCalendarEventsSearchForm" results="csEventsList" primaryPropSort="csEventTypeDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="5" levelDeep="2"/></td>
											<td nowrap width="8%"><bean:message key="prompt.date"/><jims2:sortResults beanName="csCalendarEventsSearchForm" results="csEventsList" primaryPropSort="csEventDate" primarySortType="DATE"  defaultSortOrder="ASC" defaultSort="true" sortId="6" levelDeep="2"/></td>
										</tr>
										<logic:iterate id="eachCalenderReport" name="csCalendarEventsSearchForm" property="csEventsList" indexId="index1">   
											<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td><A href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="eachCalenderReport" property="defendantId" />"><bean:write name="eachCalenderReport" property="defendantName"/></A></td>
												<td><bean:write name="eachCalenderReport" property="defendantId" /></td>
												<td><A href="/<msp:webapp/>handleCSEventsSearch.do?submitAction=View&selectedCSEventId=<bean:write name="eachCalenderReport" property="csEventId"/>"><bean:write name="eachCalenderReport" property="outcome" /></A></td>
												<td><bean:write name="eachCalenderReport" property="resultUserNPositionName" /></td>
												<td><bean:write name="eachCalenderReport" property="csEventTypeDesc" /></td>
												<td><bean:write name="eachCalenderReport" property="csEventDateStr" /></td>
											</tr>
										</logic:iterate>
   									</table>												
								</td>
							</tr>
					  	</table>
<!-- END BODY TABLE -->
					  <div class="spacer4px"></div>
<!-- BEGIN BUTTON TABLE -->
					  	<table cellpadding="2" cellspacing="1" border="0">
					  		<tr>
					  			<td align="center">
					  				<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					  				<input type="button" value="<bean:message key="button.print" />" onClick="window.print();"/>
					  				<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
					  			</td>
					  		</tr>
					  	</table>	
<!-- END BUTTON TABLE -->
		 			</td>
		 		</tr>
		 	</table>
<!-- END BLUE BORDER TABLE -->	
		</td>
	</tr>
</table>
<!-- END MAIN TABLE -->
</div>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>