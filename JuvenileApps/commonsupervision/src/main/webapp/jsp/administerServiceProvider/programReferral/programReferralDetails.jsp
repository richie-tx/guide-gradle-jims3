<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- may 2007 - daw - create jsp --%>
<%-- RCapestani 10/13/2015  Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- programReferralDetails.jsp</title>
<%--Javascript for emulated navigation --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />

<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/casework_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" >
<html:form action="/handleProgramReferral" target="content">


<logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
  <logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
    <logic:notEqual name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
       <input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|391">
    </logic:notEqual>
  </logic:notEqual>
</logic:notEqual>    

<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
	 <input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|389">
</logic:equal>	

<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
     <input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|392">
</logic:equal>

<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
     <input type="hidden" name="helpFile" value="commonsupervision/asp_Program_Referral/Service_Provider_Program_Referral.htm#|393">
</logic:equal>

<%--BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.serviceProvider" /> - <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>"> Update </logic:equal><bean:message key="prompt.programReferral" />
      <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" > 
  		  <bean:message key="title.details" />
  		</logic:equal>	
  
      <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
        <bean:message key="title.summary" />
      </logic:equal>
  
      <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
        <bean:message key="title.confirmation" />
      </logic:equal> 
  	</td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
  <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
	  <tr>
	    <td align="center" class="confirm" >Program Referral information has been saved. Probation Officer has been notified.</td>
	  </tr>
  </logic:equal> 
</table>
<%--END HEADING TABLE --%>

<%--BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
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
	  <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>">
	   <ul>
        <li>Enter information as required. </li>
    		<li>Select the Submit button to view the Summary screen.</li>
	   </ul>
	  </logic:equal>
    </td>
  </tr>
</table>
<%--END INSTRUCTION TABLE --%>

<%--BEGIN HEADER INFO TABLE --%>
<tiles:insert page="/jsp/common/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%--END HEADER INFO TABLE --%>

<%--BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width='98%' border="0" cellpadding="2" cellspacing="2" class="borderTableBlue">
  <tr>
	   <td width='1%' class="detailHead">Program Referral - <bean:write name="programReferralForm" property="programReferral.juvenileName" /> </td>
  </tr>
  <tr>
    <td valign="top" align="center">			
      <%--BEGIN EVENTS TABLE --%>
    	<div class='spacer'></div>
      <table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
        <tr>
          <td width='1%' class="detailHead"><a href="javascript:showHideMulti('events', 'cchar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="events"></a>&nbsp;Event Details</td>
        </tr>
        <tr id="cchar0" class='hidden'>
				  <td>
            <table align="center" width="100%" cellpadding="2" cellspacing="1">  
          	  <tr>

	  					<logic:empty name="programReferralForm" property="programReferral.juvenileEvents">
	  						<td valign="top">Juvenile has no events</td>
	  					</logic:empty>
	
	  					<logic:notEmpty name="programReferralForm" property="programReferral.juvenileEvents">
	            		<td valign="top">
	            			<table width="100%" cellpadding="2" cellspacing="1">
	            				<tr bgcolor="#cccccc" class="subHead">                  					
	            					<td><bean:message key="prompt.dateTime" /></td>
	    									<td><bean:message key="prompt.locationUnit" /></td>
	            					<td><bean:message key="prompt.service" /></td>
	            					<td><bean:message key="prompt.eventStatus" /></td>
	            				</tr>
	              			<logic:iterate id="eventIter" name="programReferralForm" property="programReferral.juvenileEvents" indexId="index" >
			                      <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%"> 
			            				<td><a href="javascript:newCustomWindow('/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIter" property="eventId" />', 'details', 600, 340 );">
			      										<bean:write name="eventIter" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
			      						</td>
			      						<td><bean:write name="eventIter" property="location"/></td>
			      						<td><bean:write name="eventIter" property="serviceName"/></td>
			      						<td><bean:write name="eventIter" property="eventStatus"/></td>
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
	    <%--END EVENTS TABLE --%>
		</td>
	</tr>
	<tr>
	  <td valign="top" align="center">			

			<%--BEGIN ATTENDEE TABLE --%>
			<div class='spacer'></div>		
			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
				  <td width='1%' class="detailHead"><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
				</tr>
        <tr id="achar0" class='hidden'>
					<td>
            <table align="center" width="100%" cellpadding="2" cellspacing="1">  
            	<tr>
    						<logic:empty name="programReferralForm" property="programReferral.existingReferrals">
    							<td valign="top">Juvenile has no past attendance for program</td>
    						</logic:empty>

     						<logic:notEmpty name="programReferralForm" property="programReferral.existingReferrals">
               		<td valign="top">
               			<table width="100%" cellpadding="2" cellspacing="1">
               				<tr bgcolor="#cccccc" class="subHead">
               					<td><bean:message key="prompt.programReferral#" /></td>
               					<td><bean:message key="prompt.date" /></td>
               					<td><bean:message key="prompt.service" /></td>
               					<td align="center"><bean:message key="prompt.attended" /></td>
               					<td align="center"><bean:message key="prompt.absent" /></td>
               					<td align="center"><bean:message key="prompt.excused" /></td>
               				</tr>

                 			<logic:iterate id="programRef" name="programReferralForm" property="programReferral.existingReferrals"  indexId="index" >
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
      <%--END ATTENDEE TABLE --%>
 		</td>
  </tr>

   <tr>
     <td valign="top" align="center">
       <%--BEGIN Program Referral History TABLE --%>
       <div class="spacer"></div>
   		<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">			
   			<tr>
          <td width="1%" class="detailHead"><a href="javascript:showHideMulti('history', 'hchar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="history"></a>&nbsp;Juvenile Program Referral History</td>
        </tr>
        <tr id="hchar0" class="hidden">
  				<td>							 
  				 <table cellpadding="2" cellspacing="1" width="100%">
     				<logic:empty name="programReferralForm" property="programReferral.juvenileReferralHistory">
     					<tr bgcolor="#cccccc">
     						<td colspan="4" class="subHead">No Past Program Referrals Available.</td>
     					</tr>
     				</logic:empty>
 
     				<logic:notEmpty name="programReferralForm" property="programReferral.juvenileReferralHistory">
     					<tr valign="top">
     					  <td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.lastActionDateTime" />&nbsp;</td>
     					  <td class="formDeLabel"><bean:message key="prompt.referralStatus" />&nbsp;</td>					
     					  <td class="formDeLabel"><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.name" />&nbsp;</td>
     					  <td class="formDeLabel"><bean:message key="prompt.program" />&nbsp;</td>
     					  <td class="formDeLabel"><bean:message key="prompt.beginDate" />&nbsp;</td>
     					</tr>
     					<logic:iterate id="programReferralIndex" name="programReferralForm" property="programReferral.juvenileReferralHistory"  indexId="index" >
                 <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
       					  <td><a href="javascript:newCustomWindow('/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="prompt.other"/>&referralId=<bean:write name="programReferralIndex" property="referralId" />', 'referrals', 600, 340);">
       						  <bean:write name="programReferralIndex" property="lastActionDate" formatKey="datetime.format.mmddyyyyhhmmAMPM" /></a>
       					  </td>
       					  <td><bean:write name="programReferralIndex" property="referralStatusDescription" />&nbsp; <bean:write name="programReferralIndex" property="referralSubStatusDescription" /></td>					
       					  <td><bean:write name="programReferralIndex" property="officerName" /></td>
       					  <td><bean:write name="programReferralIndex" property="providerProgramName" /></td>	
       					  <td><bean:write name="programReferralIndex" property="beginDate" formatKey="date.format.mmddyyyy" /></td>
       					</tr>
     					</logic:iterate>
     				</logic:notEmpty>   
     			</table>
   			</td>
 			</tr>
 		</table>	
     <%--END Program Referral History TABLE --%>	
	 	</td>
 	</tr>		
  <tr>
    <td valign="top" align="center">
     <%--BEGIN PROGRAM REFERRAL DETAILS TABLE --%>
      <div class='spacer'></div>
 			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
 				<tr>
 					<td class="detailHead"><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" />
 					-
 					&nbsp;<bean:message key="prompt.program" />:&nbsp;<bean:write name="programReferralForm" property="programReferral.providerProgramName"/>
		 					                     (Program ID:&nbsp;<bean:write name="programReferralForm" property="programReferral.referralId"/>)</td>
 				
 				</tr>
 				<tr>
			    <td valign="top" align="center" colspan="2">
				    <table cellpadding="2" cellspacing="1" width="100%">
		          <tr>
								<td class="formDeLabel"><bean:message key="prompt.beginDate" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="programReferral.beginDateStr" /></td>
								<td class="formDeLabel"><bean:message key="prompt.endDate" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="programReferral.endDateStr"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.assignedHours" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="programReferral.assignedHours"/></td>
								 <td class="formDeLabel"><bean:message key="prompt.courtOrdered" /></td>	
								 <bean:define id="progRef" name="programReferralForm" property="programReferral"/>		
								 <td class="formDe"><jims:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>
							</tr>
				 
							<tr>
							 <td class="formDeLabel"><bean:message key="prompt.referralStatus" /></td>
							 <td class="formDe"><bean:write name="programReferralForm" property="programReferral.referralState.description"/></td>
							 <td class="formDeLabel"><bean:message key="prompt.sentDate" /></td>
							 <td valign="top" class="formDe"><bean:write name="programReferralForm" property="programReferral.sentDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
				 
							<tr>
								 <td class="formDeLabel"><bean:message key="prompt.outcome" /></td>
								 <td class="formDe"><bean:write name="programReferralForm" property="programReferral.outComeDescription"/></td>
								 <td class="formDeLabel"><bean:message key="prompt.acknowledgementDate" /></td>
								 <td valign="top" class="formDe"><bean:write name="programReferralForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
							<tr>
									<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" />
										<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>">
            					<tiles:insert page="../../common/spellCheckTile.jsp" flush="false">
            						<tiles:put name="tTextField" value="programReferral.comments"/>
            						<tiles:put name="tSpellCount" value="spellBtn1" />
          						</tiles:insert>
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
												
													
													<logic:notEmpty name="programReferralForm" property="programReferral.referralComments">																							
														<logic:iterate  id="refComment" name="programReferralForm" property="programReferral.referralComments">
															[<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ] <bean:write name="refComment" property="commentText"/>
														</logic:iterate>
													</logic:notEmpty>
													
													<logic:notEmpty name="programReferralForm" property="programReferral.comments">														
															[<bean:write name="programReferralForm" property="programReferral.currentDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="programReferralForm" property="programReferral.currentUserName"/>  ] <bean:write name="programReferralForm" property="programReferral.comments"/>													
													</logic:notEmpty>
												
											</div>											
										</td>
									</jims:then>
								</jims:if>

								<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>">
									<td class="formDe" colspan="4"><html:textarea name="programReferralForm" property="programReferral.comments" style="width:100%" rows="3"  onmouseout="textCounter(this, 1000);" onkeyup="textCounter(this, 1000);"/></td>						 									
								</logic:equal>
								<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>">
									<td height="100%" class="formDe" colspan="4"><bean:write name="programReferralForm" property="programReferral.comments" /></td>						 									
								</logic:equal>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	  </td>
	</tr>
  <%--END PROGRAM REFERRAL DETAILS TABLE --%>

  <%--BEGIN BUTTONS TABLE --%>
  <tr>
    <td valign="top" align="center">    
			<table width='100%'>
				<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.VIEW%>" >
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>														

							<logic:notEmpty name="programReferralForm" property="programReferral.nextPossibleActions">																																						
 								<logic:iterate  id="actionIt" name="programReferralForm" property="programReferral.nextPossibleActions">
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPT%>" >
 										<html:submit property="submitAction"><bean:message key="button.accept"/></html:submit>
 									</logic:equal>
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPTWITHCHANGES%>" >
 										<html:submit property="submitAction"><bean:message key="button.acceptWithChanges"/></html:submit>
 									</logic:equal>
 									<jims2:isAllowed requiredFeatures="<%=Features.CSCD_PRG_TEN_REF_ACCEPT_CLOSE%>">
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ACCEPTANDCLOSE%>" >
 										<html:submit property="submitAction"><bean:message key="button.acceptAndClose"/></html:submit>
 									</logic:equal>
 									</jims2:isAllowed>
 								 	<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_REJECT%>" >
 										<html:submit property="submitAction"><bean:message key="button.reject"/></html:submit>
 									</logic:equal>
 								 	<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_CANCEL%>" >
 										<html:submit property="submitAction"><bean:message key="button.cancelReferral"/></html:submit>
 									</logic:equal>
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_COMPLETE%>" >
 										<html:submit property="submitAction"><bean:message key="button.closeReferral"/></html:submit>
 									</logic:equal>
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_UPDATE%>" >
 										<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
 									</logic:equal>
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_WITHDRAW%>" >
 										<html:submit property="submitAction"><bean:message key="button.withdraw"/></html:submit>
 									</logic:equal>	
 									<logic:equal name="actionIt" property="actionName" value="<%=ProgramReferralConstants.ACTION_ADDCOMMENTS%>" >
 										<html:submit property="submitAction"><bean:message key="button.addComments"/></html:submit>
 									</logic:equal>									
 								 </logic:iterate>	
							</logic:notEmpty>					
							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						</td>
					</tr>	
				</logic:equal>	

				<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.UPDATE%>" >
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
							<html:submit property="submitAction"><bean:message key="button.submit"/></html:submit>
							<html:reset><bean:message key="button.reset"></bean:message></html:reset>							
							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						</td>
					</tr>		
				</logic:equal>

				<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
							<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>								
							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						</td>
					</tr>		
				</logic:equal>
				<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
					<tr>
						<td align="center">
							<html:submit property="submitAction"><bean:message key="button.returnToReferralList"/></html:submit>  
						</td>
					</tr>							
				</logic:equal> 
       </table>
       <%-- END BUTTONS TABLE --%>						
	  </td>
	</tr>
 </table>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
