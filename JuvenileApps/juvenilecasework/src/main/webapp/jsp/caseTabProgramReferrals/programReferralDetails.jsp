<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>
<%-- 06/25/2012	CShimek 	73793 add outcome description  --%>
<%-- 07/12/2012 CShimek     #73565 added age > 20 check (juvUnder21) to buttons, show Back and Cancel only if true --%>
<%-- 09/10/2012	RYOUNG  	# Use standard javascript for comment validation--%>

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
<%@ page import="naming.Features" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="ui.common.UIUtil" %>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript">
$(document).ready(function(){
	removeSessionId("#closeReferralBtn");
	removeSessionId("#finishBtn");
	removeSessionId("#cancelBtn");
	
	
	function removeSessionId(id){
		$(id).click(function(){
			sessionStorage.removeItem("outComeId");
			sessionStorage.removeItem("ocSubOptionalId");
			sessionStorage.removeItem("ocSubRequiredId");
			sessionStorage.removeItem("casefileClosingEndDate");
		})
	}
	
	//US 176334 - disable cancel & withdraw buttons if program is attended, absent, or excused
	var programRefs = document.getElementsByName('existingReferrals');	
	//console.log('attendance status count: ', programRefs.length);
	if(programRefs && programRefs.length > 0)
	{
		var attendanceStatus;
		for(i = 0; i < programRefs.length; i++)
		{	
			attendanceStatus = programRefs[i].value;
			if(attendanceStatus)
			{
				console.log("Attendance Status: ", attendanceStatus);
				
				document.getElementById("withdrawReferralSubmitBtn").disabled = true; 
				document.getElementById("cancelReferralSubmitBtn").disabled = true; 
				
				break;
			}
		}
		
	}

})
function checkOutcome()
{
	<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>">
  		<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>" >  
			if (document.forms[0]['programReferral.outComeCd'].selectedIndex < 1) 
			{
				alert("Outcome selection is required.");
				document.forms[0]['programReferral.outComeCd'].focus();
				return false;
			}
		</logic:equal>
	</logic:notEqual>	
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen)
	{
		alert("Your input has been truncated to " + maxlen +" characters!");
  		field.value = field.value.substring(0, maxlen - 1);
	}
}

</script>
<%--BEGIN HEADER TAG--%>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- programReferralDetails.jsp</title>
<!-- Javascript for emulated navigation -->
<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/programReferral.js"></script>

</head>

<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleProgramReferral" target="content">

<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|341">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|344">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|345">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|343">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|236">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|241">
</logic:equal>     

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" />
			<jims:if name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>" op="equal">								
			<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" op="equal"/>
				<jims:then> 		
				- Cancel
				</jims:then>
			</jims:if>
			<jims:if name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>" op="equal">										
				<jims:then> 		
					- Update
				</jims:then>
			</jims:if>
			<jims:if name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" op="equal">								
			<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" op="equal"/>
				<jims:then> 		
					- 
				</jims:then>
			</jims:if>
			<bean:message key="prompt.programReferral" />&nbsp;
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
				<bean:message key="title.details" />
			</logic:equal>	
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
				<bean:message key="title.summary" />
			</logic:equal>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
				<bean:message key="title.confirmation" />
			</logic:equal>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" >
				<bean:message key="title.confirmation" />
			</logic:equal> 
		</td>
	</tr>
 	<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
		<tr>
			<td align="center" class="confirm" >Program Referral information has been saved.</td>
		</tr>
	</logic:equal>
	<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" >
		<tr>
			<td align="center" class="confirm" >Program Referral information has been cancelled.</td>
		</tr>
	</logic:equal>  
</table>
<table width="100%">
	<div class='spacer'></div>
  	<tr>
  		<td align="center" class="errorAlert"><html:errors></html:errors></td>
  	</tr>
</table>
<!-- END HEADING TABLE -->
<div class='spacer'></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
	<tr>
		<td>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
				<ul>
					<li>Select a status-changing (Update, Accept, Withdraw, etc) button, as appropriate, to update the Referral.</li>	    
					<li>Select a hyperlinked Event Details:Date/Time to view an Event Details page.</li>
					<li>Select the Back button to return to the previous page.</li>
				</ul>
			</logic:equal>	
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
				<ul>
					<li>Review information, then select the Finish button to save the information.</li>	    
					<li>Select the Back button to return to the previous page to change information.</li>
				</ul>
			</logic:equal>
			<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
				<ul>
					<li>Select the Return to Schedule New Event button to return to the list.</li>
				</ul>
			</logic:equal>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<%--header info start--%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%--header info end--%>
<div class='spacer'></div>
<!-- BEGIN DETAIL TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
<!--tabs start-->
	  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
	  			<tiles:put name="tabid" value="programreferraltab" />
	  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
	  		</tiles:insert>
<!--tabs end-->
<!-- BEGIN BLUE BORDER TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
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
												<td align="left" valign='top'>This Program Referral has no History</td>
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
																<td align="left"><bean:write name="historyIt" property="casefileId"/></td>
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
	          			<div class='spacer'></div>
<!-- BEGIN EVENTS TABLE -->
						<table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td width='1%' class='detailHead'><a href="javascript:showHideMulti('events', 'cchar', 1,'/<msp:webapp/>');" ><img border='0' src="/<msp:webapp/>images/expand.gif" name="events"></a>&nbsp;Event Details for: <bean:write name="programReferralForm" property="programReferral.juvServiceProviderName"/></td>
							</tr>
							<tr id="cchar0" class='hidden'>
								<td>
									<table align="center" width="100%" cellpadding="2" cellspacing="1">  
										<tr>
											<logic:empty name="programReferralForm" property="programReferral.juvenileEvents">
												<td align="left" valign='top'>Juvenile has no events</td>
											</logic:empty>
											<logic:notEmpty name="programReferralForm" property="programReferral.juvenileEvents">
												<td valign='top'>
													<table width='100%' cellpadding="2" cellspacing="1">
														<tr bgcolor='#cccccc' class='subHead'>                  					
															<td align="left"><bean:message key="prompt.dateTime" /></td>
															<td align="left"><bean:message key="prompt.locationUnit" /></td>
															<td align="left"><bean:message key="prompt.service" /></td>
															<td align="left"><bean:message key="prompt.eventStatus"/></td>
														</tr>
	
			                   							<logic:iterate id="eventIt" name="programReferralForm" property="programReferral.juvenileEvents"  indexId="index">
			                          						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
			                  									<td align="left"><a href="javascript:newCustomWindow( '/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIt" property="eventId" />', 'winName',700,380)">
			       													<bean:write name="eventIt" property="startDatetime" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
			       												</td>
			       												<td align="left"><bean:write name="eventIt" property="location"/></td>
			       												<td align="left"><bean:write name="eventIt" property="serviceName"/></td>
			       												<td align="left"><bean:write name="eventIt" property="eventStatus"/></td>
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
<!-- END EVENTS TABLE -->	
						<div class='spacer'></div>
<!-- BEGIN ATTENDEE TABLE -->
			            <table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td width='1%' class='detailHead'><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');" ><img border='0' src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
							</tr>
							<tr id="achar0" class='hidden'>
	       						<td>
			                		<table align="center" width="100%" cellpadding="2" cellspacing="1">  
			                  			<tr>
	          								<logic:empty name="programReferralForm" property="programReferral.existingReferrals">
	          									<td align="left" valign='top'>Juvenile has no past attendance for program</td>
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
						                  					<td align=center>Credit Hours</td>
						                  				</tr>
							                  			<logic:iterate id="programRef" name="programReferralForm" property="programReferral.existingReferrals" indexId="index">							                  				  
	        												<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
			                  									<td><bean:write name="programRef" property="programReferralId"/></td>
			                  									<td><bean:write name="programRef" property="startDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></td>
	          													<td><bean:write name="programRef" property="serviceName"/></td>
	          													<td align="center">			
	          														<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">x</logic:equal>	          														        																				
	          													</td>	
	          													<td align="center">			
	          														<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>">x</logic:equal>						
			          											</td>		
	        		  											<td align="center">			
	          														<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>">x</logic:equal>						
	          													</td>
	          													<td align="center">
	          														<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">			
																		<bean:write name="programRef" property="creditHours"/>	
																	</logic:equal>				
																</td>
																<input type="hidden" name='existingReferrals' value='<bean:write name="programRef" property="attendanceStatusCd" />' />																		
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
<!-- END ATTENDEE TABLE -->
			           	<div class='spacer'></div>					
<!-- BEGIN PROGRAM REFERRAL DETAILS TABLE -->
			 			<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			 				<tr>
			 					<td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - 
			 					<a id="gotoServiceProvider" href="#"> 
			 						<bean:write name="programReferralForm" property="programReferral.providerProgramName"/>
			 					</a>
			 					(<bean:write name="programReferralForm" property="programReferral.referralId" />)
			 					<input type="hidden" id="referralId" value='<bean:write name="programReferralForm" property="programReferral.referralId" />' />
			 					<input id="juvServiceProviderId" type="hidden" value="<bean:write name="programReferralForm" property="programReferral.juvServiceProviderId"/>" />
			 					</td> 
			 				</tr>
			 				<tr>
			  				    <td valign='top' align='center'>
	  							    <table cellpadding='2' cellspacing='1' width='100%'>
	                					<tr>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.beginDate" /></td>
											<td class="formDe"><bean:write name="programReferralForm" property="programReferral.beginDateStr" /></td>
											<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.endDate" /></td>
											<td class="formDe"><bean:write name="programReferralForm" property="programReferral.endDateStr" /></td>
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
											 <td class="formDeLabel" width="1%" nowrap='nowrap'>
											 	<logic:notEmpty name="programReferralForm" property="programReferral">
													<logic:equal name="programReferralForm" property="programReferral.currentUserType" value="<%=ProgramReferralConstants.JPO_USER%>">
														<logic:notEmpty name="programReferralForm" property="programReferral.currentAction">
													 		<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>">
													  			<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
													  				<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>">
													  					<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>">
											  								<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_UPDATE%>">
											  				 					<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>">
											  				 						<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_WITHDRAW%>">
	 										  				 							<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_REJECT%>">
	  										  				 								<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPT%>">
											 													<bean:message key="prompt.2.diamond"/>
											 				 								</logic:notEqual>
											 											</logic:notEqual>
																					</logic:notEqual>
																				</logic:notEqual>
											 								</logic:notEqual>
											 							</logic:notEqual>
											 						</logic:notEqual>
											 					</logic:notEqual>
											 				</logic:notEqual>
											 			</logic:notEmpty>
											 		</logic:equal>
												</logic:notEmpty>
											 	<bean:message key="prompt.outcome" /></td>
											 	<jims:if name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" op="equal">								
													<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" op="equal"/>
												 	<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" op="equal"/>
												  	<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>" op="equal"/>
												   	<jims:or name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" op="equal"/>
												    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>" op="equal"/>
												    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_WITHDRAW%>" op="equal"/>
												    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_REJECT%>" op="equal"/>
												    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPT%>" op="equal"/>
				   										<jims:then> 
												 			<td class="formDe"><bean:write name="programReferralForm" property="programReferral.outComeDescription"/></td>
												 		</jims:then>
											 	</jims:if>
											 	<logic:notEmpty name="programReferralForm" property="programReferral">
													<logic:equal name="programReferralForm" property="programReferral.currentUserType" value="<%=ProgramReferralConstants.JPO_USER%>">
														<logic:notEmpty name="programReferralForm" property="programReferral.currentAction">
													 		<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>">
													  			<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
													  				<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>">
																		<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>">
											  								<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_UPDATE%>">
											  				  					<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>">
																					<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_WITHDRAW%>">
																						<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_REJECT%>">
																							<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPT%>">										  				  
													  											<td valign='top' class="formDe">
															  										<html:select  name="programReferralForm" property="programReferral.outComeCd">
																										<html:option value=""><bean:message key="select.generic" /></html:option>
																										<html:optionsCollection name="programReferralForm" property="outComeList" value="code" label="description" />					
																									</html:select>
													  				  							</td>
													  		  								</logic:notEqual>
														 								</logic:notEqual>
														 							</logic:notEqual>
														 						</logic:notEqual>
																			</logic:notEqual>
																		</logic:notEqual>
																	</logic:notEqual>
																</logic:notEqual>
															</logic:notEqual>
														</logic:notEmpty>
													</logic:equal>
												</logic:notEmpty>
											 	<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.acknowledgementDate" /></td>
											 	<td valign='top' class="formDe"><bean:write name="programReferralForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
										 	</tr>
								<%-- 		 	<jims:if name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" op="equal">								
												<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" op="equal"/>
											 	<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" op="equal"/>
											  	<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>" op="equal"/>
											   	<jims:or name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" op="equal"/>
											    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>" op="equal"/>
											    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_WITHDRAW%>" op="equal"/>
											    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_REJECT%>" op="equal"/>
											    <jims:or name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPT%>" op="equal"/>
				   									<jims:then>  --%>
										 				<tr>
										 					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.outcome" /> <bean:message key="prompt.description"/></td>				 		
										 					<td class="formDe"><bean:write name="programReferralForm" property="programReferral.outComeSubcategoryDescription"/></td>
										 					 <td class="formDeLabel" width="1%" nowrap><div title="Cumulative Credit Hours Attended">Total Hours</div> </td>
									 						<td valign=top class="formDe">
										 						<div title="Cumulative Credit Hours Attended">
										 							<bean:write name="programReferralForm" property="programReferral.totalCreditHours"/>
										 						</div>
									 						</td>
									 															 						
										 				</tr>
										<%-- 			</jims:then>
										 	</jims:if>		--%>	
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" />
													<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>">
														<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>">
		 										  			<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>">
												    			<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>">
												        			<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
																		<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
					              											<tiles:put name="tTextField" value="programReferral.comments"/>
					              											<tiles:put name="tSpellCount" value="spellBtn1" />
					            										</tiles:insert>
					            										(Max. characters allowed: 1000)
					            					  				</logic:notEqual>
				            									</logic:notEqual>
			            				  					</logic:notEqual>
			            								</logic:notEqual>
			            							</logic:notEqual>
			            						</td>
	                  						</tr>
			              					<tr height="100%">
											<jims:if name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" op="equal">								
												<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" op="equal"/>
												<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>" op="equal"/>
												<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" op="equal"/>
			   										<jims:then> 
														<td class="formDe" colspan="4">
															<div  class='scrollingDiv100'>
																<table>
																	<logic:notEmpty name="programReferralForm" property="programReferral.referralComments">																							
																		<logic:iterate  id="refComment" name="programReferralForm" property="programReferral.referralComments">
																			<tr height="100%">
																				<td>
																					<bean:write name="refComment" property="commentText"/> [<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ]
																				</td>
																			</tr>
																		</logic:iterate>
																	</logic:notEmpty>
																	<logic:notEmpty name="programReferralForm" property="programReferral.comments">														
																		<tr height="100%">
																			<td>
																				<bean:write name="programReferralForm" property="programReferral.comments"/> [<bean:write name="programReferralForm" property="programReferral.currentDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="programReferralForm" property="programReferral.currentUserName"/>  ]
																			</td>
																		</tr>													
																	</logic:notEmpty>
																</table>
															</div>											
														</td>
													</jims:then>
											</jims:if>
											<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>">
												<td class="formDe" colspan="4"><html:textarea name="programReferralForm" property="programReferral.comments" style="width:100%" rows="3" styleId="progRefCommentsId"/></td>						 									
											</logic:equal>
											<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
												<td class="formDe" colspan="4"><bean:write name="programReferralForm" property="programReferral.comments" /></td>						 									
											</logic:equal>
	                   					</tr>
			             			</table>
				          		</td>
					      	</tr>
					   </table>
<!-- END PROGRAM REFERRAL DETAILS TABLE -->							
						<div class='spacer'></div>		        
<!-- BEGIN BUTTONS TABLE -->
						<table width='100%'>
							<tr>
								<td align="center">
									<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
										<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>	
											<logic:equal name="programReferralForm" property="programReferral.referralState.status" value="AC">
												<logic:equal name="programReferralForm" property="transferredProgRef" value="1">
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_TR%>'>	 
														<html:submit onclick="spinner();" property="submitAction"><bean:message key="button.transferReferral"/></html:submit>
										     		</jims2:isAllowed>
									     		</logic:equal>
											</logic:equal>	
																															
											<logic:notEmpty name="programReferralForm" property="programReferral.nextPossibleActions">																																						
												<logic:iterate  id="actionIt" name="programReferralForm" property="programReferral.nextPossibleActions">
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPT%>" >
														<html:submit property="submitAction"><bean:message key="button.accept"/></html:submit>
													</logic:equal>
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPTWITHCHANGES%>" >
														<html:submit property="submitAction"><bean:message key="button.acceptWithChanges"/></html:submit>
													</logic:equal>
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_REJECT%>" >
														<html:submit property="submitAction"><bean:message key="button.reject"/></html:submit>
													</logic:equal>													
												 	<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_CANCEL%>" >
												 	  <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_U%>'>
														<html:submit property="submitAction" styleId="cancelReferralSubmitBtn"><bean:message key="button.cancelReferral"/></html:submit>
													  </jims2:isAllowed>
													</logic:equal>
													<!-- added by AE task 148681 -->
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_TRANSFER%>" >
														<logic:equal name="programReferralForm" property="programReferral.isTentativeReferred" value="true">
															<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_TR%>'>
																<logic:equal name="programReferralForm" property="transferredProgRef" value="1">
																	<html:submit  onclick="spinner();"  property="submitAction"><bean:message key="button.transferReferral"/></html:submit>															
																</logic:equal>
															</jims2:isAllowed>
														</logic:equal>		
													</logic:equal>																								
													<!-- end -->		
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_COMPLETE%>" >
													    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_U%>'>
														<html:submit styleId="closeReferralBtn" property="submitAction"><bean:message key="button.closeReferral"/></html:submit>
														</jims2:isAllowed>
													</logic:equal>
													<!--<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_UPDATE%>" >
														<logic:notEmpty name="programReferralForm" property="programReferral">
															<logic:notEmpty name="programReferralForm" property="programReferral.outComeCd">
																<logic:notEqual name="programReferralForm" property="programReferral.outComeCd" value="">
																	<logic:notEqual name="programReferralForm" property="programReferral.outComeCd" value="null">
																		<bean:define id="showUpdate" value="false"/>
																	</logic:notEqual>
																</logic:notEqual>
															</logic:notEmpty>
														</logic:notEmpty>	
														<logic:notPresent name="showUpdate">
															<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
														</logic:notPresent>
													</logic:equal>-->
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_WITHDRAW%>" >
													    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_U%>'>
														<html:submit property="submitAction" styleId="withdrawReferralSubmitBtn"><bean:message key="button.withdraw" /></html:submit>
														</jims2:isAllowed>
													</logic:equal>			
													<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>" >
													    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_U%>'>
														<html:submit property="submitAction"><bean:message key="button.addComments"/></html:submit>
														</jims2:isAllowed>
													</logic:equal>														
												</logic:iterate>	
											</logic:notEmpty>					
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</logic:equal>	
										<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>" >
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
												<logic:notEmpty name="programReferralForm" property="programReferral">
													<logic:notEmpty name="programReferralForm" property="programReferral.currentAction">
														<logic:equal name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_UPDATE%>">
															<html:submit property="submitAction"><bean:message key="button.submit"/></html:submit>
														</logic:equal>
														<logic:notEqual name="programReferralForm" property="programReferral.currentAction.actionName" value="<%=ProgramReferralConstants.ACTION_UPDATE%>">
															<html:submit property="submitAction" onclick="return checkOutcome()"><bean:message key="button.submit"/></html:submit>
														</logic:notEqual>
													</logic:notEmpty>
												</logic:notEmpty>
												<html:reset><bean:message key="button.reset"></bean:message></html:reset>									
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</logic:equal>						
										<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">						
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<html:submit styleId="finishBtn" property="submitAction" onclick="return checkOutcome()"><bean:message key="button.finish"/></html:submit>								
											<html:submit styleId="cancelBtn" property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</logic:equal>
										<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CANCEL%>">		   					
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<html:submit property="submitAction" onclick="return checkOutcome()"><bean:message key="button.finish" /></html:submit>								
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</logic:equal>
										<jims:if name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" op="equal">								
											<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CANCEL_CONFIRM%>" op="equal"/>
							   					<jims:then> 						
													<html:submit property="submitAction"><bean:message key="button.returnToReferralList"/></html:submit>  
												</jims:then>
										</jims:if>
									</logic:equal>	
									<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
									</logic:notEqual>
	          					</td>
	        				</tr>
	      				</table>
<!--END BUTTONS TABLE -->	
				    </td>
	  			</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
   		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>