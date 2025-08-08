<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 05/2006  DAW	Create jsp --%>
<%-- 05/20/10 LDeen	ER#64892 fix begin date so that user must use calendar to enter date --%>
<%-- 12/29/10 CShimek  #68559 revised code to display current comments in Program Referral Details block --%>
<%-- 06/25/12 CShimek  #73793 add code for outcome description --%>
<%-- 09/10/2012	RYOUNG  	# Use standard javascript for comment validation--%>
<%-- 11/13/2012 DWilliamson ER#73898 Create Deceased Juvenile Warning --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- programReferralUpdate.jsp</title>
<%-- Javascript for emulated navigation --%>
<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">

<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/programReferral.js"></script>


<%-- STRUTS VALIDATION --%>
<html:javascript formName="updateProgramReferral" />

<script type='text/javascript'>
$(document).ready(function(){
	var closingEndDate = sessionStorage.getItem("casefileClosingEndDate");
	$("#closingEndDate").val( closingEndDate );
	sessionStorage.removeItem("casefileClosingEndDate");
	
	if ( $("#closingEndDate").val() != "" 
			&& $("#outComeId").val()  == "" ){
		$("#outComeId").prop("disabled", true);
	}
		
	if($("#outComeId").val() == 'S')
	{
		$("#ocSubOptionalId").val( $("#selectedOutcomeSubcategoryId").val() );
	}
	else if($("#outComeId").val() == 'X')
	{
		$("#ocSubRequiredId").val( $("#selectedOutcomeSubcategoryId").val() );
	}
	
	
	$("#submitId").click(function(){
		if ( $("#closingEndDate").val() != "" ){
			sessionStorage.setItem("casefileClosingEndDate", $("#closingEndDate").val());
		}
	})
	
	
	$("#outComeId").on("change", function(){
		$("#ocSubOptionalId").val("");
		$("#ocSubRequiredId").val("");
	});
	
	removeSessionId("#backBtn");
	removeSessionId("#cancelBtn");
	
	
		
})
var beginCal = new CalendarPopup();
	beginCal.showYearNavigation();

function validateReferralUpdateFields(theForm) {

	var msg = "";
	var actionNameValue = document.getElementById('actionName').value ;
	
	if (actionNameValue != "<%=ProgramReferralConstants.ACTION_COMPLETE%>")
	{
		
		//Check Entry Date
		var entryDate1 = document.getElementById('entryDate').value ;
		if (entryDate1 == "") 
	    {
			msg = "Entry Date is required.\n";
			document.getElementById('entryDate').focus();
	    } else {
		    if(checkDateFormat(entryDate1))
		    {         
		        msg = "Entry date is invalid.  The valid format is MM/DD/YYYY.\n";
		        document.getElementById('entryDate').focus();
		    }  	
	    } 	

		//Check Assigned Hours
	    var assignedHours1 = document.getElementById('assignedHours').value ;
	    if (assignedHours1.value == "")
	    {
	    	if (msg == ""){
	    		document.getElementById('assignedHours').focus();	
	    	}
	    	msg += "Assigned Hours is required.\n";
	    } else {
		    var assignedHours1 = Trim(assignedHours1);
		    var assignedHoursRegex = /^([0-9]{1,3})$/
		    if(assignedHoursRegex.test(assignedHours1) == false)
		    {
		    	if (msg == ""){
		    		document.getElementById('assignedHours').focus();
		    	}
		    	msg += "Assigned Hours must be numeric.\n";
		    }
	    }
	}
	
	if (actionNameValue == "<%=ProgramReferralConstants.ACTION_COMPLETE%>")
	{
	
		var endDate1 = document.getElementById('endDate').value ;
		if (endDate1 == "") 
	    {
	    	msg = "End Date is required.\n";
	    	document.getElementById('endDate').focus();
	    }
		if (endDate1 != "") {
		    if(checkDateFormat(endDate1))
		    {         
		        msg = "End date is invalid.  The valid format is MM/DD/YYYY.\n";
		        document.getElementById('endDate').focus();
		    } else {
	      	   if (dateIsFutureDate(endDate1))
	      	   {	             
	          	   msg = "End Date cannot be a future date.\n";;
	          	   document.getElementById('endDate').focus();
	       	   }
		    }
	    }
		//end date is valid date in mm/dd/yyyy format
		if (msg == "")  { 
		   var date1 = document.getElementById('beginDatex').value;
		   var date2 = document.getElementById('endDate').value;
	       var chk = compareDates(date1,'<bean:message key="date.format.mmddyyyy"/>',date2,'<bean:message key="date.format.mmddyyyy"/>');

		   if( chk == 1 )
		   {
			  document.getElementById('endDate').focus();  
	          msg += "End Date cannot be prior to Program Referral Begin Date - " + date1 + ".\n";
		   }
		}
		
		if (msg == ""
				&& $("#closingEndDate").val() != "" )  {
			   var date1 = document.getElementById('endDate').value;
			   var date2 = $("#closingEndDate").val();
			  
		       var chk = compareDates(date1,'<bean:message key="date.format.mmddyyyy"/>',date2,'<bean:message key="date.format.mmddyyyy"/>');

			   if( chk == 1 )
			   {
				  document.getElementById('endDate').focus();  
		          msg += "Program Referral End Date cannot be after Casefile Closing End Date - " + date2 + ".\n";
			   }
		}
		
	}
	
	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;
}

function checkDateFormat(dateField)
{
  if (dateField.length != 10)
	{
     return true;
  } 

  var mm = dateField.substring(0,2);
  var dd = dateField.substring(3,5);
  var yyyy = dateField.substring(6,10);
  var sl1 = dateField.substring(2,3);
  var sl2 = dateField.substring(5,6);
  
  var dateRegex =  /^\d{2}$/
  var yearRegex = /^\d{4}$/

  if (dateRegex.test(mm) == false || dateRegex.test(dd) == false || yearRegex.test(yyyy) == false)
  {
     return true;
  }
  if (sl1 != "/" || sl2 != "/") {
    return true;
  }
  if (mm == "00" || mm > 12 || dd == "00" || yyyy == "0000" ) {
    return true;
  }
  
  if( (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) && (dd > 31) )
  {
    return true;
  }
  
  if( (mm == 4 || mm == 6 || mm == 9 || mm == 11)  && (dd > 30)  ) {
    return true;
  }

  var leapYr = yyyy % 4;
  if( (mm == 2)  &&  ((leapYr == 0 && dd > 29) || leapYr != 0 && dd > 28)  ) {
    return true;
  }
}

function dateIsFutureDate(dateField)
{
	var mm = dateField.substring(0,2);
	var dd = dateField.substring(3,5);
	var yyyy = dateField.substring(6,10);
	
	var curDate = new Date();
	var curYear = curDate.getFullYear();
	var curMonth = curDate.getMonth() + 1;
	var curDay = curDate.getDate();
	if (curYear < yyyy) {
		return true;
	}
	if (curYear == yyyy) {
		if (curMonth < mm ) {
			return true;
		} else if( (curMonth == mm)  &&  (curDay < dd) ) {
			return true;
		} 
	}
	return false;             
}

function removeSessionId(id){
	$(id).click(function(){
		sessionStorage.removeItem("outComeId");
		sessionStorage.removeItem("ocSubOptionalId");
		sessionStorage.removeItem("ocSubRequiredId");
		sessionStorage.removeItem("casefileClosingEndDate");
	})
}
</script>
</head>
<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/handleProgramReferral" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|343">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.update" />&nbsp;<bean:message key="prompt.programReferral" /></td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Enter information as required.</li>
				<li>Enter date through the calendar icon.</li>	    
				<li>Select the Submit button to view the Summary screen.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;<bean:message key="prompt.requiredFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<html:hidden styleId="actionName" name="programReferralForm" property="programReferral.currentAction.actionName" />
			<html:hidden styleId="closingEndDate" name="programReferralForm" property="casefileClosingEndDate" />
<%--tabs start--%>
      		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        		<tiles:put name="tabid" value="programreferraltab" />
        		<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      		</tiles:insert>
<%--tabs end--%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>  
						<div class='spacer'></div>
<!-- BEGIN HISTORY TABLE -->
							<table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td width='1%' class='detailHead'><a href="javascript:showHideMulti('assignmentHistory', 'hchar', 1,'/<msp:webapp/>');" ><img border='0' src="/<msp:webapp/>images/expand.gif" name="assignmentHistory"></a>&nbsp;Program Referral History</td>
								</tr>
								<tr id="hchar0" class='hidden'>
									<td>
										<table align="center" width="100%" cellpadding="2" cellspacing="1">  
											<tr>
												<logic:empty name="programReferralForm" property="programReferral.prAssignmentHistoryList">
													<td valign='top' align="left">This Program Referral has no History</td>
												</logic:empty>
												<logic:notEmpty name="programReferralForm" property="programReferral.prAssignmentHistoryList">
													<td valign='top'>
														<table width='100%' cellpadding="2" cellspacing="1">
															<tr bgcolor='#cccccc' class='subHead'>                  					
																<td align="left"><bean:message key="prompt.supervisionNumber"/></td>
																<td align="left"><bean:message key="prompt.transferCreateDate"/></td>
																<td align="left"><bean:message key="prompt.casefileAssignDate"/></td>
																<td align="left"><bean:message key="prompt.casefileEndDate"/></td>
									    						<td align="left"><bean:message key="prompt.referralNum"/></td>
															</tr>
															<logic:iterate id="historyIt" name="programReferralForm" property="programReferral.prAssignmentHistoryList"  indexId="index">
																<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																	<td><bean:write name="historyIt" property="casefileId"/></td>
		                  											<td><bean:write name="historyIt" property="programReferralAssignmentDate" formatKey="date.format.mmddyyyy"/></td>
		                  											<td align="left"><bean:write name="historyIt" property="casefileAssignDate"/></td>
			                  										<td align="left"><bean:write name="historyIt" property="casefileEndDate"/></td>
			                  										<td align="left"><bean:write name="historyIt" property="cntrlRefNum"/></td>																
																</tr>
															</logic:iterate>     
														</table>
													</td>
												</logic:notEmpty>
											</tr>
										</table>
									</td>
								</tr>
		       				</table>			
<!-- END HISTORY TABLE -->	
<%-- BEGIN EVENTS TABLE --%>
							<div class='spacer'></div>
            				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              					<tr>
                					<td width='1%' class='detailHead'><a href="javascript:showHideMulti('events', 'cchar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="events"></a>&nbsp;Event Details for:&nbsp;<bean:write name="programReferralForm" property="programReferral.providerProgramName"/></td>
              					</tr>
					            <tr id="cchar0" class='hidden'>
      								<td>
                  						<table align="center" width="100%" cellpadding="2" cellspacing="1">  
                  							<tr>
				          						<logic:empty name="programReferralForm" property="programReferral.juvenileEvents">
          											<td valign='top' align="left">Juvenile has no events</td>
          										</logic:empty>
				          						<logic:notEmpty name="programReferralForm" property="programReferral.juvenileEvents">
                							  		<td valign='top'>
														<table width='100%' cellpadding="2" cellspacing="1">
															<tr bgcolor='#cccccc' class='subHead'>                  					
																<td align="left"><bean:message key="prompt.dateTime" /></td>
																<td align="left"><bean:message key="prompt.locationUnit" /></td>
																<td align="left"><bean:message key="prompt.service" /></td>
															</tr>
							                    			<logic:iterate id="eventIt" name="programReferralForm" property="programReferral.juvenileEvents" indexId="index">
                            									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">    
                    												<td align="left"><a href="javascript: openWindow('/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIt" property="eventId" />',380, 700)">
            															<bean:write name="eventIt" property="eventDate" formatKey="datetime.format.mmddyyyyHHmm"/></a>
            														</td>
            														<td align="left"><bean:write name="eventIt" property="location"/></td>
            														<td align="left"><bean:write name="eventIt" property="serviceName"/></td>
                    											</tr>
            												</logic:iterate>     
                        								</table>
                  									</td>
        										</logic:notEmpty>
                  							</tr>
                  						</table>
                					</td>
              					</tr>
            				</table>			
<%-- END EVENTS TABLE --%>
			      			<div class='spacer'></div>						
            				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              					<tr>
                					<td width='1%' class='detailHead'><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');"><img border="0" src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
              					</tr>
								<tr id="achar0" class='hidden'>
        							<td>
<%-- BEGIN ATTENDEE TABLE --%>
					                	<table align="center" width="100%" cellpadding="2" cellspacing="1">  
               								<tr>
												<logic:empty name="programReferralForm" property="programReferral.existingReferrals">
													<td valign='top' align="left">Juvenile has no past attendance for program</td>
												</logic:empty>
												<logic:notEmpty name="programReferralForm" property="programReferral.existingReferrals">
													<td valign='top'>
														<table width='100%' cellpadding="2" cellspacing="1">
															<tr bgcolor='#cccccc' class='subHead'>
																<td align="left"><bean:message key="prompt.programReferral#" /></td>
																<td align="left"><bean:message key="prompt.date" /></td>
																<td align="left"><bean:message key="prompt.service" /></td>
																<td align='center'><bean:message key="prompt.attended" /></td>
																<td align='center'><bean:message key="prompt.absent" /></td>
																<td align='center'><bean:message key="prompt.excused" /></td>
															</tr>
												 			<logic:iterate id="programRef" name="programReferralForm" property="programReferral.existingReferrals" indexId="index">
																<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																	<td align="left"><bean:write name="programRef" property="programReferralId"/></td>
																	<td align="left"><bean:write name="programRef" property="startDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
																	<td align="left"><bean:write name="programRef" property="serviceName"/></td>
																	<td align="center">			
																		<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">x</logic:equal>						
																	</td>	
																	<td align="center">			
																		<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>">x</logic:equal>						
																	</td>		
																	<td align="center">			
																		<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>">x</logic:equal>						
																	</td>	
																</tr>
															</logic:iterate>     
														</table>
                  									</td>
        										</logic:notEmpty>
											</tr>
										</table>
									</td>
								</tr>
							</table>
<%-- END ATTENDEE TABLE --%>
							<div class='spacer'></div>
<%-- BEGIN PROGRAM REFERRAL TABLE --%>
			       			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
       							<tr>
       								<td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - <bean:message key="prompt.program" />:&nbsp;<bean:write name="programReferralForm" property="programReferral.providerProgramName"/></td>
       							</tr>
       			   				<tr>
      				   				<td>
			    	  				   <table cellpadding="2" cellspacing="1" width="100%">
        									<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_COMPLETE%>" >	
                      							<tr>
	          										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.beginDate" /></td>
				          						  	 <td valign="top" class="formDe">
	        			  								<html:text styleId="entryDate" size="10" maxlength="10" name="programReferralForm" readonly="true" property="programReferral.beginDateStr" /> 
	          											
	          						   	 			</td>
	          						     			<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.assignedHours" /></td>
	          						   	 			<td class="formDe"><html:text styleId="assignedHours" size="3" maxlength="3" name="programReferralForm" property="programReferral.assignedHours" /></td>
			           						   </tr>
			                				   <tr>
													<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.courtOrdered" /></td>
													<td class="formDe"><html:radio name="programReferralForm" property="programReferral.courtOrdered" value="true"/><bean:message key="prompt.yes" />
														<html:radio name="programReferralForm" property="programReferral.courtOrdered" value="false"/><bean:message key="prompt.no" /></td>
													<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.referralStatus" /></td>
													<td class="formDe"><bean:write name="programReferralForm" property="programReferral.referralState.description"/></td>
												</tr>
											</logic:notEqual>
		              						<logic:equal name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_COMPLETE%>" >	
	    		                    			<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.beginDate" /></td>
													<td class="formDe">
														<bean:write name="programReferralForm" property="programReferral.beginDateStr" />
														<input type="hidden" name="beginDatex" id="beginDatex" value="<bean:write name='programReferralForm' property='programReferral.beginDateStr' />" />
													</td>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.endDate" /></td>
				             						<td class="formDe">
			          									<html:text styleId="endDate" size="10" maxlength="10" name="programReferralForm" property="programReferral.endDateStr"/> 
		          											
		       											<html:hidden name="programReferralForm" property="programReferral.endDateStr" /> 
				       						   	  	</td>          			
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.assignedHours" /></td>
													<td class="formDe"><bean:write name="programReferralForm" property="programReferral.assignedHours"/></td>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.courtOrdered" /></td>	
														<bean:define id="progRef" name="programReferralForm" property="programReferral"/>		
													<td class="formDe"><jims:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.referralStatus" /></td>
													<td class="formDe"><bean:write name="programReferralForm" property="programReferral.referralState.description"/></td>
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.sentDate" /></td>
													<td valign='top' class="formDe"><bean:write name="programReferralForm" property="programReferral.sentDate" formatKey="date.format.mmddyyyy"/></td>
												</tr>
				                        		<tr>
		    			      						<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.outcome" /></td>
		          									<td valign='top' class="formDe">
		          								 		<html:select  name="programReferralForm" property="programReferral.outComeCd" styleId="outComeId">
		          											<html:option value="" disabled="true"><bean:message key="select.generic" /></html:option>
		          											<html:optionsCollection name="programReferralForm" property="outComeList" value="code" label="description" />					
		          										</html:select>
		          									</td>		
					    							<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.acknowledgementDate" /></td>
												 	<td valign='top' class="formDe"><bean:write name="programReferralForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>				  
	                				  			</tr>
	                				  			<tr id="ocSubBlank">
													<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.outcome"/> <bean:message key="prompt.description"/></td>
													<td class="formDe" colspan='3'>
													<select name="blankList" Id="ocSubBlankId" disabled="true">
														<option value="">Please Select</option>
													</select>
												</td>
												</tr>
	                				  			<tr id="ocSubOptional" class="hidden">
		    			      						<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.outcome" /> <bean:message key="prompt.description" /></td>
		          									<td valign='top' class="formDe" colspan="3">
		          								 		<html:select  name="programReferralForm" property="outComeSubcategoryOptCd" styleId="ocSubOptionalId">
		          											<html:option value=""><bean:message key="select.generic" /></html:option>
		          											<html:optionsCollection name="programReferralForm" property="outComeSubcategoryOptList" value="code" label="description" />					
		          										</html:select>
		          									</td>		
	                				  			</tr>
	                				  			<tr id="ocSubRequired" class="hidden">
		    			      						<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.outcome" /> <bean:message key="prompt.description" /></td>
		          									<td valign='top' class="formDe" colspan="3">
		          								 		<html:select  name="programReferralForm" property="outComeSubcategoryReqdCd" styleId="ocSubRequiredId">
		          											<html:option value=""><bean:message key="select.generic" /></html:option>
		          											<html:optionsCollection name="programReferralForm" property="outComeSubcategoryReqdList" value="code" label="description" />					
		          										</html:select>
		          									</td>		
	                				  			</tr>
	                				  			<html:hidden name="programReferralForm" property="programReferral.outComeSubcategoryCd" styleId="selectedOutcomeSubcategoryId"/>
												<html:hidden name="programReferralForm" property="programReferral.outComeSubcategoryDescription" styleId="selectedOutcomeSubcategoryDesc"/>
           									</logic:equal>
											<tr>
												<td class="formDeLabel" colspan="4" nowrap='nowrap'><bean:message key="prompt.current" />&nbsp;<bean:message key="prompt.comments" /></td>
											</tr>
											<logic:notEmpty name="programReferralForm" property="programReferral.referralComments">																							
												<logic:iterate  id="refComment" name="programReferralForm" property="programReferral.referralComments">
													<tr><td class="formDe"colspan="4"><bean:write name="refComment" property="commentText"/> [<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ]</td></tr>
												</logic:iterate>
											</logic:notEmpty>
											<logic:notEmpty name="programReferralForm" property="programReferral.comments">														
												<tr><td class="formDe" colspan="4"><bean:write name="programReferralForm" property="programReferral.comments"/> [<bean:write name="programReferralForm" property="programReferral.currentDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="programReferralForm" property="programReferral.currentUserName"/>  ]</td></tr>													
											</logic:notEmpty>
											<%--   <tr>
													<td class="formDe" colspan="4"><bean:write name="programReferralForm" property="programReferral.referralComments"/></td>
												</tr>  --%>
											<tr>
												<td class="formDeLabel" colspan="4" nowrap='nowrap'><bean:message key="prompt.comments" />&nbsp;
													<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
														<tiles:put name="tTextField" value="programReferral.comments"/>
														<tiles:put name="tSpellCount" value="spellBtn1" />
													</tiles:insert> (Max. characters allowed: 1000)
												</td>
											</tr>
											<tr> 
												<td class="formDe" colspan="4"><html:textarea name="programReferralForm" property="programReferral.comments" style="width:100%" rows="3" styleId="progRefCommentsId"/></td>
											</tr>
										</table>
                					</td>
             					</tr>
           					</table>
<%-- END PROGRAM REFERRAL --%>
							<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
				            <table width="100%">
								<tr>
									<td align="center">
										<html:submit styleId="backBtn" property="submitAction"><bean:message key="button.back"/></html:submit> 
										<html:submit property="submitAction" onclick="return validateReferralUpdateFields(this.form) && validateUpdateProgramReferral(this.form);" styleId="submitId"><bean:message key="button.submit"/></html:submit>
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>
										<html:submit styleId="cancelBtn" property="submitAction"><bean:message key="button.cancel"/></html:submit>
									</td>
								</tr>
            				</table>
<%-- END BUTTON TABLE --%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>