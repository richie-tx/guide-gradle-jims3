<!DOCTYPE HTML>

<%-- Used for searching of juvenile profile in MJCW --%>
<%--MODIFICATIONS --%>
<%-- DWilliamson  06/03/2005	Create JSP --%>

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
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- STRUTS VALIDATION --%>
<html:javascript formName="scheduleNewEventForm"/>
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - calendarEventJuvenileSearch.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>


<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvProfileSearch.js"></script>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<script type="text/javascript">
$(document).ready(function () {
	
	$("#juvName").hide();
	$("#juvNumber").hide();
	//$("#searchType").val("");
	var searchType = $("#searchType").val();
	//$("#referralAction").val("referralSearchResults");
	if(searchType=="JNUM")
	{
			$("#juvNumber").show();
			$("#juvName").hide();			
			$("#juvnameResults").hide();
			//$("#searchType").val("JNUM");
	}
	else if(searchType=="JNAM")
	{
			$("#juvName").show();
			$("#juvnameResults").show();
			$("#juvNumber").hide();
			//$("#searchType").val("JNAM");
	}
	
	
	$("#searchType").on("change",function(){
		var searchType = $("#searchType").val();
		
		if(searchType=="JNUM"){
			$("#juvNumber").show();
			$("#juvName").hide();			
			$("#juvnameResults").hide();
			//$("#searchType").val("JNUM");
		}
		if( searchType=="JNAM"){
			
			$("#juvName").show();
			$("#juvnameResults").show();
			$("#juvNumber").hide();
			//$("#searchType").val("JNAM");
		}
	
	
	}); //on change of search type.

	
});
function validateForm(theForm)
{	
	 if(SearchValidator(theForm) && validateScheduleNewEventForm(theForm))
	{
		spinner();
		return true;
	}
	return false;
} 
function SearchValidator(thisForm){
	   var focusSet = false;
	   var msg = ""; 
	
	   return true;
}
function Select()
{	
	$("#searchType").val("JNAM");
	var juvNum = $('input:radio[name=selectedValue]:checked').val();	
	$('form').attr('action',"/JuvenileCasework/submitJuvenileServiceEvent.do?submitAction=Select&juvenileNum="+juvNum);
	$('form').submit();
}

</script>

</head>



<%--BEGIN FORM TAG--%>
<html:form action="/submitJuvenileServiceEvent" target="content" focus="juvenileLastName" onsubmit="return validateForm(this);">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Workshop Calendar - Add Youth to Session</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0" align="center">
    <tr>
     <td>
  	  <ul>
        	<li>Select the type of Search you wish.</li>
    		<li>Enter required fields and other search values then select button to search.</li> 
     </ul>	
		</td>
  </tr>
  <%-- <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
  </tr>
  <tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
  </tr>
  <tr>     	
   	<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
  </tr> --%>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%-- BEGIN summary CONFLICTING EVENTS TABLE --%>
		 <table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">  
			<tr>
				<td class="detailHead">Selected Event</td>
			</tr>
			<%-- <tr>
									<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.select" /></td>
									<td class="formDe">
										<html:select  name="scheduleNewEventForm" property="currentService.serviceTypeCode" styleId="curSerServiceTypeCode">
											<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection name="scheduleNewEventForm" property="currentService.serviceTypeList" value="serviceTypeCode" label="description" />					
										</html:select>
									</td>               
								</tr> --%>
				<td valign="top" align="center">
					<table width='100%' cellpadding="4" cellspacing="1">			
						<tr bgcolor='#cccccc'>
							<!--<td class="subHead">Service Type Code</td> -->
							<td class="subHead">Event ID</td>
							<td class="subHead">Event Date</td>
							<td class="subHead">Event Time</td> 
							<td class="subHead">Max Attendance</td>
							<td class="subHead">Location Unit</td>
							<td class="subHead">Instructor</td>
							<td class="subHead">Service Provider</td>
							<td class="subHead">Service Name</td>
						</tr>
						<tr>
							<%--<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.serviceTypeCode"/></td> --%>
							<td class="formDe"><bean:write name="scheduleNewEventForm" property="eventId"/></td>	
							<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr"/></td>						
							<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.eventTime"/></td> 
							<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.maxAttendance"/></td>
							<td class="formDe"><bean:write name="scheduleNewEventForm" property="currentService.location"/></td>
							<td class="formDe" nowrap="nowrap"><bean:write name="scheduleNewEventForm" property="currentService.currentEvent.instructorName"/></td>
							<td class="formDe" nowrap="nowrap"><bean:write name="scheduleNewEventForm" property="currentService.serviceProvider"/></td>	
							<td class="formDe" nowrap="nowrap"><bean:write name="scheduleNewEventForm" property="currentService.service"/></td>					
						</tr>
				</table>
			</td>
			</tr>
		</table> 
		
		
</div>
<%-- END CONFLICTING EVENTS TABLE --%>

<div class='spacer'></div>
<div class='spacer'></div>
<div class='spacer'></div>
<div class='spacer'></div>
<div class='spacer'></div>
<%-- BEGIN INQUIRY TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
   <tr>
     <td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.searchBy"/></td>
     <td class="formDe">
		 <html:select property="searchType" styleId="searchType">
		 	<html:option value=""><bean:message key="select.generic" /></html:option>
         	<html:option value="JNUM">JUVENILE NUMBER</html:option>
        	<html:option value="JNAM">JUVENILE NAME</html:option>
  		</html:select> 
	 </td>
  </tr>
</table>
<br>

<%-- SEARCH BY JUVENILE NUMBER --%>

<%--<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
   <tr>
     <td class="formDeLabel" nowrap='nowrap' width="3%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
     <td class="formDe"><html:text property="juvenileNum" size="8" maxlength="8" styleId="juvenileNum"/></td>
  </tr> 
  </table>--%>
  <br>
  
<%-- SEARCH BY JUVENILE NUMBER --%>
<span id="juvNumber" class='hidden'>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
  <tr>
     <td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
     <td class="formDe"><html:text property="juvenileNum" size="8" maxlength="8" styleId="juvenileNum"/></td>
  </tr>
</table>
</span>

<span id="juvName" class='visible'>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
   <tr>
    <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileLastName" /></td>
    <td class="formDe"><html:text property="juvenileLastName" size="60" maxlength="75" styleId="lastName"/></td>
   </tr>
   <tr>
    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.juvenileFirstName"/></td>
    <td class="formDe"><html:text property="juvenileFirstName" size="50" maxlength="50" styleId="firstName"/></td>
  </tr>
  <tr>
    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.juvenileMiddleName"/></td>
    <td class="formDe"><html:text property="juvenileMiddleName" size="50" maxlength="50" styleId="middleName"/></td>
  </tr>
 
</table>
</span>

<%-- END INQUIRY TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<br>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
		  <html:submit property="submitAction" styleId="submitBtn">
  			<bean:message key="button.searchJuvenile" />
  		</html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>

	<span id="juvnameResults" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<logic:notEmpty name ="scheduleNewEventForm" property="juvenileProfiles">
			<tr>
				<td>
					<table width='100%' cellpadding='4' cellspacing='2' bgcolor='#999999'>
							<tr  class="formDeLabel">
								<td></td>
								<td><bean:message key="prompt.juvenileNumber" /> <jims2:sortResults
										beanName="scheduleNewEventForm"
										results="juvenileProfiles" primaryPropSort="juvenileNum"
										primarySortType="STRING" secondPropSort="firstName"
										secondarySortType="STRING" sortId="1" levelDeep="2" /></td>
								<td><bean:message key="prompt.juvenileName" /> <jims2:sortResults
										beanName="scheduleNewEventForm"
										results="juvenileProfiles" primaryPropSort="lastName"
										primarySortType="STRING" secondPropSort="firstName"
										secondarySortType="STRING" sortId="1" levelDeep="2" /></td>
								<td><bean:message key="prompt.race" /> <jims2:sortResults
										beanName="scheduleNewEventForm"
										results="juvenileProfiles" primaryPropSort="race"
										primarySortType="STRING" sortId="2" levelDeep="2" /></td>
								<td><bean:message key="prompt.sex" /> <jims2:sortResults
										beanName="scheduleNewEventForm"
										results="juvenileProfiles" primaryPropSort="sex"
										primarySortType="STRING" sortId="3" levelDeep="2" /></td>
							</tr>

							<logic:iterate indexId="index" id="juvenile" name ="scheduleNewEventForm" property="juvenileProfiles">
							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
								<td valign='top'><input type="radio" name="selectedValue" value="<bean:write name="juvenile" property="juvenileNum"/>" id="juvRadio"/></td>
								
								<td valign='top'><bean:write name="juvenile" property="juvenileNum" /></td>
								<td valign='top'><bean:write name="juvenile" property="formattedName" /></td>
								<%-- <td valign='top'><bean:write name="juvenile" property="firstName" /></td>
								<td valign='top'><bean:write name="juvenile" property="middleName" /></td>
								<td valign='top'><bean:write name="juvenile" property="lastName" /></td> --%>
								<td valign='top'><bean:write name="juvenile" property="race"/></td>
								<td valign='top'><bean:write name="juvenile" property="sex"/></td>
								<%-- <td><a href="/<msp:webapp/>submitJuvenileServiceEvent.do?submitAction=Removed Selected&juvenileIndex=<%=(index.intValue())%>">Remove</a></td> --%>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
			<tr>
		<td align="center">
		  <html:submit property="submitAction" styleId="selectBtn" onclick="return Select();">
  			<bean:message key="button.select" />
  		</html:submit>
		</td>
	</tr>
		</logic:notEmpty>
	
	
	<div class='spacer'></div>
	<div class='spacer'></div>
	<div class='spacer'></div>
	<div class='spacer'></div>		
	</table>
	</span>
	
	<div class='spacer'></div>
	<div class='spacer'></div>
		 <table align="center" border="0" width="100%">
		 <tr>
		 <td>
			<div class="paddedFourPix" align="center" >
			 	
				&nbsp;		
	  			&nbsp;
	  			<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="document.location.href='/JuvenileCasework/handleJuvenileServiceEvent.do?submitAction=Workshop Calendar'">
  				<bean:message key="button.cancel"></bean:message>
  			</html:button>
	  		</div>
		</td>
	 </tr>
			
			</table>
		</tr>
	<br>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
<html:hidden  property="referralAction" value="juvenileSearchResults" styleId="referralAction"/>
</body>
</html:html>
