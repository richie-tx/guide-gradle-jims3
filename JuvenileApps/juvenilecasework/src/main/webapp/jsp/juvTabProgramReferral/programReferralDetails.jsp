<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>
<%-- 06/21/2012		C. Shimek	73793 add outcome description display field in Program Referral Details block --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="ui.common.UIUtil" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;-<bean:message key="title.juvenileProfile"/>&nbsp;- programReferralDetails.jsp</title>
<%-- Javascript for emulated navigation --%>
<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">

<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/juvProgramReferral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>


<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleProgramReferral" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|436">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/>&nbsp;- <bean:message key="title.juvenileProfile"/>&nbsp;- <bean:message key="prompt.programReferral" />&nbsp;<bean:message key="title.details" /></td>
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
        <li>Select the Update button to update the Program Referral.</li>	    
        <li>Select a hyperlinked Event Details:Date/Time to view an Event Details page.</li>
        <li>Select the Back button to return to the previous page.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader" />
</tiles:insert> <%--header info end--%>
<%--juv profile header end--%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
		<table width='100%' border="0" cellpadding="0" cellspacing="0">
			<tr>
				<%--tabs start--%>
				<td valign=top>
				    <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="programreferraltab"/>
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>	
				</td>
				<%--tabs end--%>
			</tr>
			<tr>
				<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
			</tr>
		</table>

		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			<tr> 
				<td>
					<!-- BEGIN HISTORY TABLE -->
					 <div class='spacer'></div>
			      <table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	            <tr>
	              <td align='left' width='1%' class='detailHead'><a href="javascript:showHideMulti('assignmentHistory', 'hchar', 1,'/<msp:webapp/>');" ><img border='0' src="/<msp:webapp/>images/expand.gif" name="assignmentHistory"></a>&nbsp;Program Referral History</td>
	            </tr>
	
	            <tr id="hchar0" class='hidden'>
	              <td>
	                <table align="center" width="100%" cellpadding="2" cellspacing="1">  
	                	<tr>
							<logic:empty name="programReferralForm" property="programReferral.prAssignmentHistoryList">
							<td valign='top'>This Program Referral has no History</td>
							</logic:empty>
							<!-- Task 39399  -->
							<logic:notEmpty name="programReferralForm" property="programReferral.prAssignmentHistoryList">
	                  		<td valign='top'>
	                  			<table width='100%' cellpadding="2" cellspacing="1">
	                  				<tr bgcolor='#cccccc' class='subHead' align="left">                  					
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
		      <table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            <tr>
              <td align='left' width='1%' class=detailHead><a href="javascript:showHideMulti('events', 'cchar', 1,'/<msp:webapp/>');" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="events"></a>&nbsp;Event Details for: <bean:write name="programReferralForm" property="programReferral.juvServiceProviderName"/></td>
            </tr>
            <tr id="cchar0" class='hidden'>
						 <td>
              <table align="center" width="100%" cellpadding="0" cellspacing="0">  
              	<tr>
									<logic:empty name="programReferralForm" property="programReferral.juvenileEvents">
										<td valign=top align="left">Juvenile has no events</td>
									</logic:empty>

									<logic:notEmpty name="programReferralForm" property="programReferral.juvenileEvents">
	              		<td>
	              			<table width='100%' cellpadding="2" cellspacing="1">
	              				<tr bgcolor='#cccccc' class=subHead>                  					
	              					<td><bean:message key="prompt.dateTime" /></td>
													<td><bean:message key="prompt.locationUnit" /></td>
		            					<td><bean:message key="prompt.service" /></td>
		            					<td><bean:message key="prompt.eventStatus" /></td>
		            				</tr>
			            			<logic:iterate id="eventIt" name="programReferralForm" property="programReferral.juvenileEvents" indexId="indexer">
											    <tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
				          					<td><a href='javascript:newCustomWindow("/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIt" property="eventId" />","detailWin", 700, 380);'>
															<bean:write name="eventIt" property="startDatetime" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
														</td>
														<td><bean:write name="eventIt" property="location"/></td>
														<td><bean:write name="eventIt" property="serviceName"/></td>
														<td><bean:write name="eventIt" property="eventStatus"/></td>
														
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

				<%-- BEGIN ATTENDEE TABLE --%>
				<div class='spacer'></div>		
         <table  align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
           <tr>
             <td align='left' width='1%' class=detailHead><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
           </tr>
           <tr id="achar0" class='hidden'>
						 <td>
            
	             <table align="center" width="100%" cellpadding="0" cellspacing="0">  
	             	<tr>
									<logic:empty name="programReferralForm" property="programReferral.existingReferrals">
										<td valign=top align="left">Juvenile has no past attendance for program</td>
									</logic:empty>
	
									<logic:notEmpty name="programReferralForm" property="programReferral.existingReferrals">
	               		<td valign=top>
	               			<table width='100%' cellpadding="2" cellspacing="1">
	               				<tr bgcolor='#cccccc' class=subHead>
	               					<td><bean:message key="prompt.programReferral#" /></td>
	               					<td><bean:message key="prompt.date" /></td>
	               					<td><bean:message key="prompt.service" /></td>
	               					<td align=center><bean:message key="prompt.attended" /></td>
	               					<td align=center><bean:message key="prompt.absent" /></td>
	               					<td align=center><bean:message key="prompt.excused" /></td>
	               					<td align=center>Hours</td> 
	               				</tr>
	                			<logic:iterate id="programRef" name="programReferralForm" property="programReferral.existingReferrals" indexId="indexer">
											    <tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
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
	
					<%-- BEGIN PROGRAM REFERRAL DETAILS TABLE --%>
					<div class='spacer'></div>
		 			<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		 				<tr>
		 					<td align='left' class=detailHead><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - Program: 
		 					<a id="gotoServiceProvider" href="#"> 
		 						<bean:write name="programReferralForm" property="programReferral.providerProgramName"/> 
		 					</a>
		 					    (<bean:write name="programReferralForm" property="programReferral.referralId"/>)
		 					    <input id="juvServiceProviderId" type="hidden" value="<bean:write name="programReferralForm" property="programReferral.juvServiceProviderId"/>" />
		 					</td> 
		 				</tr>
		 				<tr>
					    <td valign=top align=center>
						    <table cellpadding=2 cellspacing=1 width='100%'>
	                <tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.beginDate" /></td>
									<td class="formDe"><bean:write name="programReferralForm" property="programReferral.beginDateStr" /></td>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.endDate" /></td>
									<td class="formDe"><bean:write name="programReferralForm" property="programReferral.endDateStr" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.assignedHours" /></td>
									<td class="formDe"><bean:write name="programReferralForm" property="programReferral.assignedHours"/></td>
									<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.courtOrdered" /></td>	
									<bean:define id="progRef" name="programReferralForm" property="programReferral"/>		
									<td class="formDe"><jims:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>
								</tr>
								 <tr>
									 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.referralStatus" /></td>
									 <td class="formDe"><bean:write name="programReferralForm" property="programReferral.referralState.description"/></td>
									 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.sentDate" /></td>
									 <td valign=top class="formDe"><bean:write name="programReferralForm" property="programReferral.sentDate" formatKey="date.format.mmddyyyy"/></td>
								 </tr>
								 <tr>
									 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.outcome" /></td>
									 <td class="formDe"><bean:write name="programReferralForm" property="programReferral.outComeDescription"/></td>
									 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.acknowledgementDate" /></td>
									 <td valign=top class="formDe"><bean:write name="programReferralForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
								 </tr>
								 <tr>
									 <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.outcome" /> <bean:message key="prompt.description" /></td>
									 <td class="formDe"><bean:write name="programReferralForm" property="programReferral.outComeSubcategoryDescription"/></td>
									 <td class="formDeLabel" width="1%" nowrap><div title="Cumulative Credit Hours Attended">Total Hours</div></td>
									 <td valign=top class="formDe">
									 	<div title="Cumulative Credit Hours Attended">
									 		<bean:write name="programReferralForm" property="programReferral.totalCreditHours"/>
									 	</div>
									 </td>
								 </tr>

								 <tr>
									 <td class="formDeLabel" width="1%" nowrap>JPO Name</td>
									 <td class="formDe" colspan="3"><bean:write name="programReferralForm" property="programReferral.officerLastName" />, <bean:write name="programReferralForm" property="programReferral.officerFirstName" /></td>
								 </tr>

								<tr>
									<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" />
									    <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>">
									       (Max. characters allowed: 1000)
									    </logic:equal>
									</td>
	                            </tr>
	                            <tr>
									<jims:if name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" op="equal">								
									<jims:or name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" op="equal"/>
	   								<jims:then> 
										<td class="formDe" colspan="4">
											<div  class='scrollingDiv100'>
												<table>
													<logic:notEmpty name="programReferralForm" property="programReferral.referralComments">																							
														<logic:iterate  id="refComment" name="programReferralForm" property="programReferral.referralComments">
															<tr style="height:100%"><td><bean:write name="refComment" property="commentText"/> [<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ]</td></tr>
														</logic:iterate>
													</logic:notEmpty>
													<logic:notEmpty name="programReferralForm" property="programReferral.comments">														
														<tr style="height:100%"><td><bean:write name="programReferralForm" property="programReferral.comments"/> [<bean:write name="programReferralForm" property="programReferral.currentDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="programReferralForm" property="programReferral.currentUserName"/>  ]</td></tr>													
													</logic:notEmpty>
												</table>
											</div>											
										</td>
									</jims:then>
									</jims:if>
									<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>">
											<td class="formDe" colspan="4"><html:textarea name="programReferralForm" property="programReferral.comments" styleId="progRefCommentUpdate" style="width:100%" rows="3"/></td>						 									
									</logic:equal>
									<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
											<td class="formDe" colspan="4"><bean:write name="programReferralForm" property="programReferral.comments" /></td>						 									
									</logic:equal>
										</tr>
					        </table>
						    </td>
							</tr>
			      </table>
						<%-- END PROGRAM REFERRAL DETAILS TABLE --%>
			
						<%-- BEGIN BUTTON TABLE --%>
						<div class='spacer'></div>
						<table border="0" cellpadding=1 cellspacing=1 align=center>
							<tr>
								<td align="center"><html:submit property="submitAction"><bean:message key="button.back"/></html:submit></td>
							</tr>    
						</table>
			    </td>
			  </tr>
			</table>
    </td>
  </tr>
</table>

<%-- END DETAIL TABLE --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

