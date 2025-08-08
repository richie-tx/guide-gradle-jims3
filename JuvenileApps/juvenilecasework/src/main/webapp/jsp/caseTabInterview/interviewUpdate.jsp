<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 05/31/2006		AWidjaja Create JSP--%>
<%-- 07/16/2012 	CShimek     #73565 added age > 20 check (juvUnder21) to Submit button and input block display --%>
<%-- 10/24/2012 	DGibler		#73746 MJCW: Add Street Number suffix field --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCalendarConstants"%>
<%@ page import="ui.common.UIUtil"%>
<%@ page import="naming.UIConstants"%>

<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" />&nbsp;<bean:message 	key="title.juvenileCasework" />&nbsp;- interviewUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>


<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/interviewUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js" type="text/javascript"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>

<logic:equal name="juvenileInterviewForm" property="action" value="create">
	<html:javascript formName="juvenileInterviewForm" />
</logic:equal>
<logic:equal name="juvenileInterviewForm" property="action" value="record">
	<html:javascript formName="juvenileInterviewForm" />
</logic:equal>

<elogic:if name="juvenileInterviewForm" property="action" op="notEqual" value="create">
	<elogic:and name="juvenileInterviewForm" property="action" op="notEqual" value="record"  />
	<elogic:then>
		<html:javascript formName="updateInterview" />
	</elogic:then>
</elogic:if>


<script language="javascript" type="text/javascript">

$(document).ready(function(){
	console.log("Loaded" + '<bean:write name="juvenileInterviewForm" property="currentInterview.interviewDate" formatKey="date.format.mmddyyyy" />');
	console.log("Id:" + $("#typeId").val() );
	if ("UE" === $("#interviewTypeId").val() ) {
			console.log("display none");
			$("#progressReportId1").css("display", "none");
			$("#progressNotesLbl").css("display", "none");
	} else {
			$("#progressNotesLbl").css("display", "block");
			$("#progressNotesLbl").attr('colspan',4);
			$("#progressReportId1").css("display", "block");
	}
	
	$("#interviewTypeId").change(function(){
	    console.log("Id:" + $("#typeId").val() );
		$("#typeId").val( $("#interviewTypeId").val() );
		console.log( $("#interviewTypeId").val() );
		if ("UE" === $("#interviewTypeId").val() ) {
			console.log("display none");
			$("#progressReportId1").css("display", "none");
			$("#progressNotesLbl").css("display", "none");
		} else {
			$("#progressNotesLbl").css("display", "block");
			$("#progressNotesLbl").attr('colspan',4);
			$("#progressReportId1").css("display", "block");
		}
	})
})
var excused = false ;
function attendStatusCallback(widget)
{
	if( widget.options[ widget.selectedIndex ].text == "<%=UIConstants.ATTENDANCE_EXCUSED_STATUS%>" || widget.options[ widget.selectedIndex ].text == "<%=UIConstants.ATTENDANCE_ABSENT_STATUS%>")
	{
		excused = true ;
		document.forms[0]["addlAttendees"].value = "" ;
		document.forms[0]["addlAttendees"].disabled = "disabled" ;
		document.forms[0]["selectedAttendeeNames"].disabled = "disabled" ;
	}
	else
	{				
		excused = false ;
		document.forms[0]["addlAttendees"].disabled = "" ;
		document.forms[0]["selectedAttendeeNames"].disabled = "" ;
	}		
	
}

function validateAttendance(theForm)
{
	 errStr = "";	
	 if (theForm["attendanceStatusCd"] != null && theForm["attendanceStatusCd"].value == "")
	 {
	 	errStr = "Please select an attendance status\n";
	  	theForm["attendanceStatusCd"].focus();
	 }
	 if(theForm["addlAttendees"].value != "" && !excused )
	 {
	  	addNumericValidation("addlAttendees","Additional Attendees must be numeric");		
		if(!validateCustomStrutsBasedJS(theForm) )
			return false;
		var attendeeVal = parseInt(theForm["addlAttendees"].value);
		if( attendeeVal > 19  )
		{
			alert("Additional Attendees cannot be more than 19");
			theForm["addlAttendees"].focus();
			return false;
		}
	  }
	 if (errStr == "")
		return true;

	 alert(errStr);
	
	 return false;	
}




function addressTable( rows, toShow )
{
	if( rows == 'edit' )
	{	
		// this is for the edit section
		for( var i = 0; i < 5; i++ )
		{
			show( ('addr' +i), toShow, 'row' ) ;
		}
	}
	else
	{	
		// this is for the summary section
		for( var i = 0; i < 5; i++ )
		{
			show( ('saddr' +i), toShow, 'row' ) ;
		}
	} 
}	
function validateFields(theForm)
{
	
  if( tText == 'Enter a new address' )
  {
  	retVal = retVal && validateAddress('juvenileInterviewForm','', 'currentInterview.newAddress');
  }
  
  var interviewLocation = theForm["currentInterview.juvLocUnitId"];
  var offset = interviewLocation.selectedIndex ;
  var tText =  interviewLocation.options[ offset ].text ;
  var retVal = 0;
  
  <logic:equal name="juvenileInterviewForm" property="action" value="create">
	retVal = validateJuvenileInterviewForm(theForm) && validateMultiSelectBox(
	  		theForm, 'currentInterview.personsInterviewed', 'Please select at least a person interviewed');
	</logic:equal>
  <logic:equal name="juvenileInterviewForm" property="action" value="record">
  	retVal = validateJuvenileInterviewForm(theForm) ;
  	return retVal;	
  </logic:equal>
 
  <logic:notEqual name="juvenileInterviewForm" property="action" value="create">
  	/* retVal = validateUpdateInterview(theForm); re-writing interview update as struts validation not working for update*/
  	retVal = validateInterviewUpdate(theForm);
  </logic:notEqual>

  return retVal;	
}
function validateInterviewUpdate(theForm) {
	var eventTime = theForm["currentInterview.interviewTimeStr"];
	var eventLocation = theForm["currentInterview.juvLocUnitId"];
		
	if (eventTime.value == null || eventTime.value == "") {
			alert("Interview Time is required.");
			return false;
	}
	
	if (eventLocation.value == null || eventLocation.value == "") {
		alert("Interview Location is required.");
		return false;
	}
	return true;
}
function validateDateTime(theForm)
{	
	var eventTime = theForm["currentInterview.interviewTimeStr"];
	var eventDate = theForm["currentInterview.interviewDateStr"];
	
	var scheduledDate;
	
	if(eventDate == null) 
	{
		eventDateStr = "<bean:write name='juvenileInterviewForm' property='currentInterview.interviewDate' formatKey='date.format.mmddyyyy'/>";
		scheduledDate = new Date(eventDateStr);
	}
	else
	{	
		if (eventDate.value == "")
		{
			alert("Please enter Interview Date");
			eventDate.focus();
			return false;
		}
		
		scheduledDate = new Date(eventDate.value)
		var today = new Date();
	
		if(scheduledDate > today)
		{
			alert("Interview Date can not be in future.");
			eventDate.focus();
			return false;
		}
	}
	
	if (eventTime.value == "")
	{
		alert("Please select Interview Time");
		eventTime.focus();
		return false;
	}
	
	var eventTimes = eventTime.value.split(':');
	var eventHrStr = eventTimes[0];
	var eventMinStr = eventTimes[1].split(' ')[0];
	var eventAMPM = eventTimes[1].split(' ')[1];

	var eventHr;
	var eventMin;
	
	if(eventHrStr.charAt(0) == '0') 
	{
		eventHr = parseInt(eventHrStr.charAt(1));
	}
	else
	{
		eventHr = parseInt(eventHrStr);
	}
		
	if(eventMinStr.charAt(0) == '0')
	{
		eventMin = parseInt(eventMinStr.charAt(1));
	}
	else
	{
		eventMin = parseInt(eventMinStr);
	}
		
	
	if(eventAMPM.toUpperCase() == "AM") 
	{
		if(eventHr == 12) 
		{
			eventHr = 0; 
		}
	} 
	else 
	{ //PM here
		if(eventHr != 12) 
		{
			eventHr += 12;
		}
	}
			
	scheduledDate.setHours(eventHr);
	scheduledDate.setMinutes(eventMin);
	
	today = new Date();
	
	if( scheduledDate > today)
	{
	   alert("Interview Time can not be in future.");
	   eventTime.focus();
	   return false;
	}	
	
	return true;
}	
	
function getDateObject(dateString,dateSeperator)
{
		//This function return a date object after accepting 
		//a date string ans dateseparator as arguments
		var curValue = dateString;
		var sepChar = dateSeperator;
		var curPos = 0;
		var cDate,cMonth,cYear;

		//extract day portion
		curPos = dateString.indexOf(sepChar);
		cMonth = dateString.substring(0,curPos);
		cMonth = cMonth -1;
		
		//extract month portion				
		endPos = dateString.indexOf(sepChar,curPos+1);			
		cDate = dateString.substring(curPos+1,endPos);

		//extract year portion				
		curPos = endPos;
		endPos = curPos +5;			
		cYear = curValue.substring(curPos+1,endPos);
		//Create Date Object
		dtObject = new Date(cYear,cMonth,cDate);	
		return dtObject;
}

function validateMultiSelectBox(theForm, elementName, msg)
{
	element = theForm[elementName];		

	if(element != null)
	{
		for(var i = 0; i < element.length; i++)
		{
			if(element[i] != null && 
					element[i].selected && 
					element[i].value != "")
			{
				return true;
			}
		}
		
	}
	alert(msg);
	element.focus();

	return false;
}
</script>
</head>

<html:form action="/displayJuvInterviewSummary" target="content">

	<logic:equal name="juvenileInterviewForm" property="action" value="create">
		<input type="hidden" name="helpFile"
			value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|87">
	</logic:equal>
	<logic:equal name="juvenileInterviewForm" property="action" value="update">
		<input type="hidden" name="helpFile"
			value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|123">
	</logic:equal>

	<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"
		topmargin='0' leftmargin="0">

	<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header"><bean:message
				key="title.juvenileCasework" /> - <bean:message
				key="title.conductInterview" /> - <logic:equal
				name="juvenileInterviewForm" property="action" value="create">
				<bean:message key="title.create" />
			</logic:equal> <logic:equal name="juvenileInterviewForm" property="action"
				value="update">
				<bean:message key="title.update" />
			</logic:equal> <bean:message key="prompt.interview" /></td>
		</tr>
	</table>
	<%-- END HEADING TABLE --%>

    <%-- BEGIN ERROR TABLE --%>
	<table width='98%' align='center'>
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	<%-- END ERROR TABLE --%>
	<div class='spacer'></div>
    <%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">	
				<ul>
					<li>Complete the Interview form then click the <b>Submit</b> button to view summary.</li>
				</ul>
			</logic:equal>
			</td>
		</tr>
		<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
			<tr>
				<td class="required"><img src="/<msp:webapp/>images/required.gif" alt="">&nbsp;Required
				Fields &nbsp;&nbsp;&nbsp;*All date fields must be entered via calendar.<br>
				<img src="/<msp:webapp/>images/required.gif" alt=""><img src="/<msp:webapp/>images/required.gif" alt="">&nbsp;Required
				Fields &nbsp;&nbsp;&nbsp;Required for Validate Address</td>
			</tr>
		</logic:equal>
	</table>
	<%-- END INSTRUCTION TABLE --%>

	<%-- BEGIN HEADER INFO TABLE --%>
	<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp"
		flush="true">
		<tiles:put name="headerType" value="casefileheader" />
	</tiles:insert>
	<%-- END HEADER INFO TABLE --%>


	<%-- BEGIN DETAIL TABLE --%>
	<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign='top'><tiles:insert 	page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab" />
				<tiles:put name="casefileid"
					value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'><%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width='100%' border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign='top'><%--tabs start--%> 
										<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" value="interviewtab" />
											<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
										</tiles:insert> <%--tabs end--%>
									</td>
								</tr>
								<tr>
									<td bgcolor='#33cc66'>
									<table border='0' cellpadding='2' cellspacing='1'>
										<tr>
											<td>&nbsp;<a href='/<msp:webapp/>displayJuvInterviewList.do?submitAction=Link'><bean:message
												key="prompt.viewInterviews" /></a> <b>|</b></td>
											<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message
												key="prompt.viewReportHistory" /></a> <b>|</b></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>

							<table width='100%' border="0" cellpadding="0" cellspacing="0"
								class="borderTableGreen">
								<tr>
									<td valign='top' align='center'>
									<div class='spacer'></div>
<%-- BEGIN INTERVIEW TABLE --%>									
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
										<tr bgcolor='#f0f0f0'>
											<td align="left" class="detailHead" colspan="2">Interview Information</td>
										</tr>
										<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
										<tr>
											<td valign='top' align='center'>
												<table width='100%' border="0" cellpadding="2" cellspacing="1">
												<tr>
													<td valign='top' class='formDeLabel' colspan='4'>
													<table width='100%' cellpadding='0' cellspacing='0'>
														<tr>
															<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
																<td width='1%'>
																	<a 	href="javascript:showHideMulti('Photos', 'phChar', 1, '/<msp:webapp/>')"
																		border="0"><img border='0' src="/<msp:webapp/>images/expand.gif" name="Photos" alt=""></a>
																</td>
																<td class='formDeLabel' nowrap='nowrap' valign='top'>&nbsp;Photos</td>
															</logic:notEmpty>

															<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
																<td class='formDeLabel' nowrap='nowrap' valign='top'>&nbsp;Photos (Juvenile has no photos)</td>
															</logic:empty>
														</tr>
													</table>
													</td>
												</tr>
												<tr id="phChar0" class='hidden'>
													<td valign='top'>
													<table width='98%' cellpadding='2' cellspacing='2'>
														<tr bgcolor='white'>
															<td valign='top' width='70%'>
															<table width='98%' cellpadding='4' cellspacing='1'>

																<logic:notEmpty name='juvenilePhotoForm'
																	property='mostRecentPhoto'>
																	<tr>
																		<td><a href='javascript:newCustomWindow('
																			/<msp:webapp/>
																			getJuvenilePhoto.do?submitAction=Link&amp;juvenileNumber=
																			<bean:write name="juvenileInterviewForm" property="juvenileNum"/>&amp;selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>','juvPhoto',400,400)'  >
																		<img alt="Mug Shot Not Available"
																			src='/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&amp;juvenileNumber=<bean:write name="juvenileInterviewForm" property="juvenileNum"/>'
																			width="128" border='1'> </a></td>
																		<td><logic:greaterThan name='juvenilePhotoForm'
																			property='totalPhotosAvailable' value='1'>
																			<input type='button' value="View All Photos"
																				onclick="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&amp;selectedValue=','juvPhoto',450,450)" />
																		</logic:greaterThan></td>
																	</tr>
																	<tr>
																		<td align='left'>
																			<bean:write name='juvenilePhotoForm' property='mostRecentPhoto.entryDate' formatKey="date.format.mmddyyyy" />
																		</td>
																	</tr>
																</logic:notEmpty>
															</table>
															</td>
														</tr>
													</table>
													</td>
												</tr>
												<tr>
													<td class='formDeLabel' nowrap='nowrap' width='1%'>
														<bean:message key="prompt.2.diamond" /> <bean:message key="prompt.interviewDate" />
													</td>
													<td class='formDe'>
													<elogic:if name="juvenileInterviewForm" property="action" op="notEqual" value="create">
													<elogic:and name="juvenileInterviewForm" property="action" op="notEqual" value="record"  />
														<elogic:then>	
													
														<bean:write name="juvenileInterviewForm" property="currentInterview.interviewDate" formatKey="date.format.mmddyyyy" />
													</elogic:then>
													</elogic:if>
													<jims2:if name="juvenileInterviewForm" property="action" op="equal" value="create">
  														<jims2:or name="juvenileInterviewForm" property="action" op="equal" value="record" />
  															<jims2:then>	
													
														<%-- Calendar popup javascript --%>
														<html:text name="juvenileInterviewForm" styleId="interviewDateId" property="currentInterview.interviewDateStr" maxlength='10' size='10'/>
														
													</jims2:then>
													</jims2:if>
													</td>
													<td class='formDeLabel' nowrap='nowrap' width="1%">
														<bean:message key="prompt.2.diamond" /><bean:message key="prompt.interviewTime" />
													</td>
													<td class="formDe">
														<html:select property="currentInterview.interviewTimeStr">
															<html:option key="select.generic" value="" />
															<html:optionsCollection name="juvenileInterviewForm" property="workDays" value="description" label="description" />
														</html:select>
													</td>
												</tr>
												<tr>
													<td class='formDeLabel' valign='top' width='1%'>
														<logic:notEqual name="juvenileInterviewForm" property="action" value="update">
															<bean:message key="prompt.2.diamond" />
														</logic:notEqual>
														<bean:message key="prompt.personsInterviewed" />
													</td>
													<td class='formDe' colspan="3">
														<logic:equal name="juvenileInterviewForm" property="action" value="update">
														<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.selectedPersonsInterviewed">
															<logic:iterate id="personsIter" name="juvenileInterviewForm" property="currentInterview.selectedPersonsInterviewed">
																<bean:write name="personsIter" property="formattedName" />
																<br>
															</logic:iterate>
														</logic:notEmpty>
													</logic:equal>
													<logic:equal name="juvenileInterviewForm" property="action" value="create">
														<html:select name="juvenileInterviewForm" property="currentInterview.personsInterviewed" size="5" multiple="true" styleId="personsInterviewed">
															<%-- <html:option value=""><bean:message key="select.generic" /></html:option> --%> 
															<logic:iterate id="personsIter" name="juvenileInterviewForm" property="personsInterviewedList">
																<option value="<bean:write name='personsIter' property='formattedName'/>">
																	<bean:write name="personsIter" property="formattedName" />
																	<logic:notEmpty name="personsIter" property="relationship">
																		(<bean:write name="personsIter" property="relationship" />)
																	</logic:notEmpty>
																</option>
															</logic:iterate>
														</html:select>
														<html:hidden name="juvenileInterviewForm" property="currentInterview.personsInterviewedStr" styleId="personsInterviewedStrId"/>
													</logic:equal>
													<logic:equal name="juvenileInterviewForm" property="action" value="record">												
															
														
																<html:select name="juvenileInterviewForm" property="currentInterview.personsInterviewed" size="5" multiple="true">
    															<html:optionsCollection name="juvenileInterviewForm" property="personsInterviewedList" value="name" label="name" />
													</html:select>
													</logic:equal></td>
												</tr>
												<tr>
													<td class='formDeLabel' nowrap='nowrap' valign='top' width='1%'>Records Inventory</td>
													<td class='formDe' colspan="3">
														<html:select name="juvenileInterviewForm" property="currentInterview.recordsInventoryIds" size="5" multiple="true" styleId="inventory">
															<%-- <html:option value=""><bean:message key="select.generic" /></html:option> --%>
															<html:optionsCollection name="juvenileInterviewForm" property="recordsInventoryList" value="code" label="description" />
														</html:select>
													</td>
												</tr>
												<tr id='otherRow' class='hidden'>
													<td class='formDeLabel' nowrap='nowrap' width='1%'>Other Inventory Record(s)</td>
													<td class='formDe' colspan='3'>
														<html:text name="juvenileInterviewForm" property="currentInterview.otherInventoryRecords" maxlength="250" size="50" />
													</td>
												</tr>
												<tr>
													<td class='formDeLabel' nowrap='nowrap' width='1%'>
														<logic:notEqual name="juvenileInterviewForm" property="action" value="update">
															<bean:message key="prompt.2.diamond" />
														</logic:notEqual> 
														<bean:message key="prompt.interviewType" />
													</td>
													<td class='formDe' colspan='3'>
														<logic:equal name="juvenileInterviewForm" property="action" value="update">
															<bean:write name="juvenileInterviewForm" property="currentInterview.interviewType" />
														</logic:equal>
														<logic:notEqual name="juvenileInterviewForm" property="action" value="update">
															<html:select styleId= "interviewTypeId" name="juvenileInterviewForm" property="currentInterview.interviewTypeId">
															<html:option value=""><bean:message key="select.generic" /></html:option>
															<html:optionsCollection name="juvenileInterviewForm" property="currentInterview.interviewTypeList" 	value="code" label="description" />
														</html:select>
													</logic:notEqual></td>
												</tr>
												<tr>
													<td class='formDeLabel' width="1%"><bean:message
														key="prompt.2.diamond" />Interview <bean:message
														key="prompt.location" /> Unit</td>
													<td class='formDe' colspan='3'>
														<html:select name="juvenileInterviewForm" property="currentInterview.juvLocUnitId" styleId="juvLocUnitId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="juvenileInterviewForm" property="interviewLocationList" value="juvLocationUnitId" label="locationUnitName" />
														<option value="newaddress" 
															<logic:equal name="juvenileInterviewForm" property="currentInterview.juvLocUnitId" value="newaddress">
																selected
															</logic:equal>>Enter
														a new address</option>
													</html:select></td>
												</tr>

												<%-- the next set of tr's are to be initially hidden
    													and will be visible when -interview location- is a certain value --%>
												<%-- BEGIN ADDRESS INFORMATION SECTION --%>
												<tr id="newAddressSection" class='hidden'>
													<td colspan='4'>
													<table border='0' width='100%' cellpadding='0'
														cellspacing='0'>
														<tr class='detailHead' id="newAddressHeader">
															<td>
															<table width="100%">
																<tr>
																	<td class='detailHead'><bean:message
																		key="prompt.addressInfo" /></td>
																	<td align="right" class="detailHead"><input
																		type='button'
																		value="<bean:message key='button.validate' />"
																		name="submitAction"
																		onClick="return validateAddrAction('juvenileInterviewForm','/<msp:webapp/>validateInterviewAddress.do','', 'currentInterview.newAddress','/jsp/caseTabInterview/interviewUpdate.jsp', true);"></input>
																	<html:button
																		property="org.apache.struts.taglib.html.BUTTON"
																		onclick="return displayResearchWindow();">
																		<bean:message key="button.research"></bean:message>
																	</html:button></td>
																	<td align="right" class="detailHead" nowrap='nowrap'><bean:message
																		key="prompt.addressStatus" />:</td>
																	<td class="errorAlert"><%-- TODO replace logic tags with code table values --%>
																	<logic:equal name="juvenileInterviewForm"
																		property="addressStatus" value="">
																								UNPROCESSED
																						   </logic:equal> <logic:equal name="juvenileInterviewForm"
																		property="addressStatus" value="U">
																								UNPROCESSED
																						   </logic:equal> <logic:equal name="juvenileInterviewForm"
																		property="addressStatus" value="Y">
																								VALID
																						   </logic:equal> <logic:equal name="juvenileInterviewForm"
																		property="addressStatus" value="N">
																								INVALID
																						   </logic:equal></td>
																</tr>
															</table>
															</td>
														</tr>
														<tr>
															<td colspan='4'>
															<table width="100%" border="0" cellpadding='2'
																cellspacing='1'>
																<tr>
																	<td class='formDeLabel'><bean:message
																		key="prompt.diamond" /><bean:message
																		key="prompt.diamond" /><bean:message
																		key="prompt.streetNum" /></td>
																	<td class='formDe'><html:text size="9"
																		maxlength="9"
																		property="currentInterview.newAddress.streetNum" /></td>
																	<td class='formDeLabel'><bean:message key="prompt.streetNum" />&nbsp;<bean:message key="prompt.suffix" /></td>
																	<td class='formDe'>
																		<html:select property="currentInterview.newAddress.streetNumSuffixCode" size="1">
																			<html:option key="select.generic" value="" />
																			<html:optionsCollection property="currentInterview.streetNumSuffixList" value="code" label="description"/>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td class='formDeLabel'>
																		<bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.streetName" />
																	</td>
																	<td class='formDe' colspan="3"><html:text size="30" maxlength="50" property="currentInterview.newAddress.streetName" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.streetType" /></td>
																	<td class='formDe'>
																		<html:select property="currentInterview.newAddress.streetTypeCode">
																			<html:option key="select.generic" value="" />
																			<html:optionsCollection name="juvenileInterviewForm" property="currentInterview.streetTypeList" value="code" label="description" />
																		</html:select>
																	</td>
																	<td class='formDeLabel'><bean:message key="prompt.aptSuite" /></td>
																	<td class='formDe'>
																		<html:text size="10" maxlength="10" property="currentInterview.newAddress.aptNum" />
																	</td>
																</tr>
																<tr>
																	<td class='formDeLabel'>
																		<bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.city" />
																	</td>
																	<td class='formDe'><html:text size="15" maxlength="25" property="currentInterview.newAddress.city" /></td>
																	<td class='formDeLabel'>
																		<bean:message key="prompt.diamond" /><bean:message key="prompt.diamond" /><bean:message key="prompt.state" />
																	</td>
																	<td class='formDe'>
																		<html:select property="currentInterview.newAddress.stateCode" size="1" styleId="state1" onchange="javascript:enableCounty(this, 'county1')">
																			<option value="" selected='selected'>Please Select</option>
																			<html:optionsCollection name="juvenileInterviewForm" property="currentInterview.stateList" value="code" label="description" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message
																		key="prompt.diamond" /><bean:message
																		key="prompt.diamond" /><bean:message
																		key="prompt.zipCode" /></td>
																	<td class='formDe' colspan='4'><html:text size="6"
																		maxlength="5"
																		property="currentInterview.newAddress.zipCode" /> - <html:text
																		size="4" maxlength="4"
																		property="currentInterview.newAddress.additionalZipCode" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message
																		key="prompt.addressType" /></td>
																	<td class='formDe'><html:select
																		property="currentInterview.newAddress.addressTypeCode">
																		<html:option key="select.generic" value="" />
																		<html:optionsCollection
																			property="currentInterview.addressTypeList"
																			value="code" label="description" />
																	</html:select></td>
																	<td class='formDeLabel'><bean:message
																		key="prompt.county" /></td>
																	<td class='formDe'><span class="hidden"
																		id="emptySelect1"> <select disabled='disabled'>
																		<option>Please Select</option>
																	</select> </span> <span class="visible" id="county1Span"> <html:select
																		property="currentInterview.newAddress.countyCode"
																		styleId="county1">
																		<option value="" selected='selected'>Please
																		Select</option>
																		<html:optionsCollection
																			property="currentInterview.countyList" value="code"
																			label="description" />
																	</html:select> </span></td>
																</tr>
															</table>
															</td>
														</tr>
													</table>
													</td>
												</tr>
												<%-- END ADDRESS INFORMATION SECTION --%>

												<%-- this is where attendance is recorded;--%>
											<jims2:if name="juvenileInterviewForm" property="action" op="equal" value="create">
  												<jims2:or name="juvenileInterviewForm" property="action" op="equal" value="record" />
  													<jims2:then>	
													<tr>
														<div class='spacer'></div>
														<table align="center" width="100%" colspan="2"
															cellpadding="2" cellspacing="1" class="borderTableBlue">
															<tr>
																<%-- table titlebar --%>
																<td class='detailHead'><bean:message
																	key="prompt.juvenile" />&nbsp;<bean:message
																	key="prompt.attendance" /></td>
															</tr>
															<tr>
																<td valign='top' align='center'>
																<table width='100%' colspan="2" cellpadding="2"
																	cellspacing="1">
																	<tr>
																		<td class='formDeLabel' width='1%' nowrap='nowrap'>
																		<bean:message key="prompt.attendance" />&nbsp;<bean:message
																			key="prompt.status" /></td>
																		<td class='formDe'><bean:write
																			name="juvenileInterviewForm"
																			property="attendanceStatus" /></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message
																			key="prompt.additionalAttendees" /></td>
																		<td class="formDe"><html:text
																			name="juvenileInterviewForm" property="addlAttendees"
																			size="2" maxlength="2" /></td>
																	</tr>
																	<tr>
																		<td class='formDeLabel' valign='top' width='1%'>
																		<bean:message key="prompt.additionalAttendeeNames" />
																		</td>
																		<td class='formDe' colspan="3"><logic:empty
																			name="juvenileInterviewForm" property="contactNames">Name list is empty.</logic:empty>
																		<logic:notEmpty name="juvenileInterviewForm"
																			property="contactNames">
																			<html:select name="juvenileInterviewForm"
																				property="selectedAttendeeNames" multiple="true">
																				<%-- <html:option value="">
																					<bean:message key="select.generic" />
																				</html:option> --%>
																				<html:optionsCollection name="juvenileInterviewForm"
																					property="contactNames" value="formattedName"
																					label="formattedName" />
																			</html:select>
																		</logic:notEmpty></td>
																	</tr>
																</table>
																</td>
															</tr>
														</table>
													</tr>
												</jims2:then>
											</jims2:if>
												<logic:equal name="juvenileInterviewForm" property="action"
													value="update">
													<tr>
														<div class='spacer'></div>
														<table align="center" width="100%" colspan="2"
															cellpadding="2" cellspacing="1" class="borderTableBlue">
															<tr>
																<%-- table titlebar --%>
																<td class='detailHead'><bean:message
																	key="prompt.juvenile" />&nbsp;<bean:message
																	key="prompt.attendance" /></td>
															</tr>
															<tr>
																<td valign='top' align='center'>
																<table width='100%' colspan="2" cellpadding="2"
																	cellspacing="1">
																	<html:hidden name="juvenileInterviewForm"
																		property="serviceEventId" />
																	<tr>
																		<td class='formDeLabel' width='1%' nowrap='nowrap'>
																		<bean:message key="prompt.attendance" />&nbsp;<bean:message
																			key="prompt.status" /></td>
																		<td class='formDe'><html:select
																			onchange="attendStatusCallback(this);"
																			property="attendanceStatusCd"
																			styleId='attendanceDropdown'>
																			<html:option value="">
																				<bean:message key="select.generic" />
																			</html:option>
																			<html:optionsCollection
																				property="attendanceStatusList" value="code"
																				label="description" />
																		</html:select></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message
																			key="prompt.additionalAttendees" /></td>
																		<td class="formDe"><html:text
																			name="juvenileInterviewForm" property="addlAttendees"
																			size="2" maxlength="2" /></td>
																	</tr>
																	<tr>
																		<td class='formDeLabel' valign='top' width='1%'>
																		<bean:message key="prompt.additionalAttendeeNames" />
																		</td>
																		<td class='formDe' colspan="3"><logic:empty
																			name="juvenileInterviewForm" property="contactNames">Name list is empty.</logic:empty>
																		<logic:notEmpty name="juvenileInterviewForm"
																			property="contactNames">
																			<html:select name="juvenileInterviewForm"
																				property="selectedAttendeeNames" multiple="true">
																				<html:option value="">
																					<bean:message key="select.generic" />
																				</html:option>
																				<html:optionsCollection name="juvenileInterviewForm"
																					property="contactNames" value="formattedName"
																					label="formattedName" />
																			</html:select>
																		</logic:notEmpty></td>
																	</tr>
																</table>
																</td>
															</tr>
														</table>
													</tr>
												</logic:equal>

												<!-- JIMS200058316 begin  -->
												<jims2:if name="juvenileInterviewForm" property="action" op="equal" value="create">
  													<jims2:or name="juvenileInterviewForm" property="action" op="equal" value="record" />
  													<jims2:then>												
														<tr>
															<td class='formDeLabel' valign='top' nowrap='nowrap'
																colspan="4">Comments&nbsp; <tiles:insert
																page="/jsp/caseworkCommon/spellCheckTile.jsp"
																flush="false">
																<tiles:put name="tTextField"
																	value="currentInterview.userComments" />
																<tiles:put name="tSpellCount" value="spellBtn2" />
															</tiles:insert> (Max. characters allowed: 255)</td>
														</tr>
														<tr>
															<td class='formDe' colspan="4"><html:textarea
																name="juvenileInterviewForm"
																property="currentInterview.userComments"
																styleId="userCommentsId" rows="3"
																style="width:100%" /></td>
														</tr>
													</jims2:then>
												</jims2:if>	
												<elogic:if name="juvenileInterviewForm" property="action" op="notEqual" value="create">
													<elogic:and name="juvenileInterviewForm" property="action" op="notEqual" value="record"  />
														<elogic:then>																			
														<tr>
															<td class='formDeLabel' valign='top' nowrap='nowrap'
																colspan="4">Comments&nbsp;</td>
														</tr>
														<tr>
															<td class='formDe' colspan="4"><bean:write
																name="juvenileInterviewForm"
																property="currentInterview.userComments" /></td>
														</tr>
													</elogic:then>
												</elogic:if>
												<!-- JIMS200058316 end  -->
												
												<tr>
													<td class='formDeLabel' valign='top' nowrap='nowrap'
														colspan="4" id='summNotes'>Summary Notes&nbsp; <tiles:insert
														page="/jsp/caseworkCommon/spellCheckTile.jsp"
														flush="false">
														<tiles:put name="tTextField"
															value="currentInterview.summaryNote" />
														<tiles:put name="tSpellCount" value="spellBtn3" />
													</tiles:insert> (Max. characters allowed: 32000)</td>
												</tr>
												<tr>
												<logic:notEqual name="juvenileInterviewForm"
													property="action" value="record">
													<td class='formDe' colspan="4"><html:textarea
														name="juvenileInterviewForm"
														property="currentInterview.summaryNote"
														styleId="summaryNoteId1" rows="3"
														style="width:100%" /></td>
													</logic:notEqual>
													<logic:equal name="juvenileInterviewForm"
													property="action" value="record">
													<td class='formDe' colspan="4"><html:textarea
														name="juvenileInterviewForm"
														property="currentInterview.summaryNote"
														styleId="summaryNoteId2" rows="3"
														style="width:100%" disabled="true"/></td>
													</logic:equal>
												</tr>
												
												<!-- JIMS200058316 begin  -->
												<logic:equal name="juvenileInterviewForm" property="action"
													value="create">
													<tr>
														<td id="progressNotesLbl" class='formDeLabel' valign='top' nowrap='nowrap'
															colspan="4">Progress&nbsp;Notes&nbsp; <tiles:insert
															page="/jsp/caseworkCommon/spellCheckTile.jsp"
															flush="false">
															<tiles:put name="tTextField"
																value="currentInterview.progressReport" />
															<tiles:put name="tSpellCount" value="spellBtn4" />
														</tiles:insert> (Max. characters allowed: 3000)</td>
													</tr>
													<tr id="progressNotesField">
														<td class='formDe' colspan="4"><html:textarea
															name="juvenileInterviewForm"
															property="currentInterview.progressReport"
															styleId="progressReportId1" rows="3"
															style="width:100%" /></td>
													</tr>
												</logic:equal>
												<logic:equal name="juvenileInterviewForm" property="action"
													value="record">
													<tr>
														<td class='formDeLabel' valign='top' nowrap='nowrap'
															colspan="4" >Progress&nbsp;Notes&nbsp; <tiles:insert
															page="/jsp/caseworkCommon/spellCheckTile.jsp"
															flush="false">
															<tiles:put name="tTextField"
																value="currentInterview.progressReport" />
															<tiles:put name="tSpellCount" value="spellBtn4" />
														</tiles:insert> (Max. characters allowed: 3000)</td>
													</tr>
													<tr>
														<td class='formDe' colspan="4"><html:textarea
															name="juvenileInterviewForm"
															property="currentInterview.progressReport"
															styleId="progressReportId2" rows="3"
															style="width:100%" disabled="true"/></td>
													</tr>
												</logic:equal>

												<elogic:if name="juvenileInterviewForm" property="action" op="notEqual" value="create">
													<elogic:and name="juvenileInterviewForm" property="action" op="notEqual" value="record"  />
														<elogic:then>
														<tr>
															<td class='formDeLabel' valign='top' nowrap='nowrap'
																	colspan="4">Progress&nbsp;Notes&nbsp;</td>
														</tr>
														<tr>
															<td class='formDe' colspan="4"><bean:write
																	name="juvenileInterviewForm"
																	property="currentInterview.progressReport" /></td>
														</tr>
															
														</elogic:then>
													</elogic:if>
												<!-- JIMS200058316 end  -->
											</table>
											<div class='spacer'></div>
											<%-- END INTERVIEW TABLE --%></td>
										</tr>
										</logic:equal>
										<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
											<tr>
												<td class="subhead">Juvenile age is 21 or order, no updates allowed.</td>
											</tr>
										</logic:notEqual>
									</table>
									<%-- END TAB BLUE BORDER TABLE --%> <%-- BEGIN BUTTON TABLE --%>
									<div class='spacer'></div>
									<table width="100%">
										<tr>
											<td align="center">
											<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>	
												
												<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
														<html:submit property="submitAction" onclick="return (validateFields(this.form) &amp;&amp; validateDateTime(this.form) &amp;&amp; validateAttendance(this.form))" styleId="submitId">
												 		<bean:message key="button.submit" />
													</html:submit> 
												</logic:equal>
												<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.interviewTasks">
													<input type="button" name="submitAction" id="interviewChecklistId" value="<bean:message key='button.interviewChecklist'/>">
												</logic:notEmpty>
												<html:submit property="submitAction">
													<bean:message key="button.cancel" />
												</html:submit>
											</td>
										</tr>
									</table>
									<%-- END BUTTON TABLE --%></td>
								</tr>
							</table>
							<div class='spacer'></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<%-- END INTERVIEW INFO TABS OUTER TABLE --%></td>
		</tr>
	</table>
	<%-- END DETAIL TABLE --%>

	<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
	<table width='100%'>
		<tr>
			<td><html:hidden property="validStreetNum" value="" /> 
				<html:hidden property="validStreetName" value="" />
				<html:hidden property="validZipCode" value="" />
				<html:hidden property="validAddrNum" value="" />
				<html:hidden property="inputPage" value="" /> 
				<html:hidden property="currentAddressInd" value="" />
				<input id="typeId" type="hidden" />
				
			</td>
		</tr>
	</table>
	<%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%>

	</body>

</html:form>

<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:html>
