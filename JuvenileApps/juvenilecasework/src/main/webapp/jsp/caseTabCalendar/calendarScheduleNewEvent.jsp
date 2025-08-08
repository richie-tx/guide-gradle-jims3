<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 12/2006	MTobler		Create jsp --%>
<%-- 7/17/2007	LDeen		Defect #43737 correcting validation messages --%>
<%-- 5/11/2012	RYoung		ER #73310 Display casefile Info used to create event --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="ui.common.UIUtil" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - calendarScheduleNewEvent.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>

<html:base />

<html:javascript formName="scheduleCalendarEvent"/>
<!-- <script type="text/javascript">
$(document).ready(function () {
$("#curSerServiceTypeCode").on('change',function(){
		var startDate = $("#eventFromDate").val();
		var endDate = $("#eventToDate").val();
		if(startDate==''||endDate=='')
			{
				alert('Start and End date is required')
				return false;
			}
			else
			 return true;

});

});
</script> -->
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin='0'>
<html:form action="/handleScheduleEvent" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|142"> 
<input type="hidden" name="uniqueId" value=""/>

<%-- BEGIN Heading TABLE --%>
<table width='100%'>
  <tr>
		<logic:equal name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
	    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Schedule Juvenile for<br>Past Pre-Scheduled Event(s)</td>
    </logic:equal>

		<logic:notEqual name="scheduleNewEventForm" property="secondaryAction" value="<%=UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR%>" >
	    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.scheduleNewCalendarEvent" /></td>
    </logic:notEqual>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END Heading TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Enter Start and End date then Select an <b>Event Type</b> from the dropdown selection list.</li>
      </ul>
		</td>
  </tr>
</table>

<%-- BEGIN Casefile Header TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END Casefile Header TABLE --%>

<%-- BEGIN Detail TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' cellpadding="2" cellspacing="0" border="0">
	<tr>
		<td>

  		<%--tabs start--%> 
  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="calendartab" />
  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
  		</tiles:insert> 
  		<%--tabs end--%>
						
			<table align="center" width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>		  
						<%-- this table houses the drop down that controls which data tables get displayed. 
							 the various tables that follow are hidden and are shown depending upon the item chosen here 
						--%>
						  <div class='spacer'></div>		  
					    <table align="center" width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">      
								
								<tr>											 
									<td class="detailHead" colspan='4'><bean:message key="prompt.dateRange" /></td>
									<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.from" /></td>
									<td class="formDe">
										<html:text name="scheduleNewEventForm" property="eventFromDate" styleId="eventFromDate" size="10" maxlength="10" title="mm/dd/yyyy" ></html:text>
									</td>
									<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.to" /></td>
									<td class="formDe">	
										<html:text name="scheduleNewEventForm" property="eventToDate" styleId="eventToDate" size="10" maxlength="10" title="mm/dd/yyyy" ></html:text>
									</td>	
									<td class="detailHead" colspan='2'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.eventType" /></td>								  
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.select" /></td>
									<td class="formDe">
										<html:select  name="scheduleNewEventForm" property="currentService.serviceTypeCode" styleId="curSerServiceTypeCode">
											<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection name="scheduleNewEventForm" property="currentService.serviceTypeList" value="serviceTypeCode" label="description" />					
										</html:select>
									</td>               
								</tr>
						</table>
						<div class='spacer'></div>	
					</td>
				</tr>
			</table>	
	  </td>
	</tr>			
</table>	
<%-- Begin Detail Table --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

