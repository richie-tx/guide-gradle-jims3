<!DOCTYPE HTML>
<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- July 2010 - djn - create jsp --%>

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
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="ui.common.UIUtil" %>



<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>JIMS2 <bean:message key="title.juvenileCasework"/>&nbsp;- programReferralTransfer.jsp</title>

<!-- Javascript for emulated navigation -->
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">
<html:base />
</head>

<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitProgramReferralTransfer" target="content">

<!-- Help file reference -->
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|239">
</logic:equal>
<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|240">
</logic:equal>     

<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" /> -
    <bean:message key="prompt.transferProgramReferral" />&nbsp;
        <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
             <bean:message key="title.summary" />
        </logic:equal>
        <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
             <bean:message key="title.confirmation" />
        </logic:equal> 
	</td>
  </tr>
  <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
	  <tr>
	    <td align="center" class="confirm">Program Referral has been transferred.</td>
	  </tr>
  </logic:equal>
</table>
<table width="100%">
  <div class="spacer"></div>
  <tr>
  	<td align="center" class="errorAlert"><html:errors></html:errors></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      	<logic:equal name="programReferralForm" property="action" value="<%=UIConstants.SUMMARY%>" >
      		<ul>
        		<li>Review information, then select the Finish button to save the information.</li>	    
        		<li>Select the Back button to return to the previous page to change information.</li>
      		</ul>
 		</logic:equal>	
        <logic:equal name="programReferralForm" property="action" value="<%=UIConstants.CONFIRM%>" >
	      	<ul>
	        	<li>Select Referral List button to return to the Program Referral list.</li>
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


<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'>

  		<!--tabs start-->
  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="programreferraltab" />
  			<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
  		</tiles:insert>
  		<!--tabs end-->

			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td>
                    
 				<!-- BEGIN HISTORY TABLE -->
					 <div class='spacer'></div>
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
	                  					<td align="left"><bean:message key="prompt.supervisionNumber" /></td>
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
                
				<!-- BEGIN EVENTS TABLE -->
	          <div class='spacer'></div>
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
	      											<td><bean:message key="prompt.locationUnit" /></td>
	                  					<td align="left"><bean:message key="prompt.service" /></td>
	                  					<td align="left"><bean:message key="prompt.eventStatus" /></td>
		                 				</tr>

		                   			<logic:iterate id="eventIt" name="programReferralForm" property="programReferral.juvenileEvents"  indexId="index">
		                          <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
		                  					<td align="left"><a href="javascript:newCustomWindow( '/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIt" property="eventId" />', 'winName',700,380)">
		       												<bean:write name="eventIt" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></a>
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
							<!-- BEGIN ATTENDEE TABLE -->
              <div class='spacer'></div>
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
					<!-- END ATTENDEE TABLE -->
						
	<!-- BEGIN PROGRAM REFERRAL DETAILS TABLE -->
     <div class='spacer'></div>
	<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - <bean:write name="programReferralForm" property="programReferral.providerProgramName"/></td> 
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
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.outcome" /></td>
					<td class="formDe"><bean:write name="programReferralForm" property="programReferral.outComeDescription"/></td>
					<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.acknowledgementDate" /></td>
					<td valign='top' class="formDe"><bean:write name="programReferralForm" property="programReferral.acknowledgementDate" formatKey="date.format.mmddyyyy"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" />
					</td>
                </tr>
		        <tr  height="100%">
					<td class="formDe" colspan="4">
						<div  class='scrollingDiv100'>
						<table>
							<logic:notEmpty name="programReferralForm" property="programReferral.referralComments">																							
							<logic:iterate  id="refComment" name="programReferralForm" property="programReferral.referralComments">
							<tr  height="100%">
                            	<td><bean:write name="refComment" property="commentText"/> [<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ]</td>
                            </tr>
							</logic:iterate>
							</logic:notEmpty>
							<logic:notEmpty name="programReferralForm" property="programReferral.comments">														
							<tr>
                            	<td><bean:write name="programReferralForm" property="programReferral.comments"/> [<bean:write name="programReferralForm" property="programReferral.currentDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="programReferralForm" property="programReferral.currentUserName"/>  ]</td>
                            </tr>													
							</logic:notEmpty>
						</table>
						</div>											
					</td>
				</tr>
		     </table>
		   </td>
		</tr>
	</table>
	<!-- END PROGRAM REFERRAL DETAILS TABLE -->								
		        
    <%-- BEGIN CASEFILE TABLE --%>
	<div class='spacer'></div>
	<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class=detailHead><bean:message key="prompt.transfer" /> <bean:message key="prompt.programReferral" /> - <bean:message key="prompt.select" /> <bean:message key="prompt.casefile" /></td>
		</tr>
		<tr>
			<td>
				<table cellpadding=2 cellspacing=1 width='100%'>
					<tr bgcolor='#cccccc'>
						<td width='1%'></td>
						<td align="left" class=subHead><bean:message key="prompt.supervision" />&nbsp;#</td>
						<td align="left" class=subHead><bean:message key="prompt.sequence" />&nbsp;#</td>
						<td align="left" class=subHead><bean:message key="prompt.probationOfficer" />&nbsp;
										  <bean:message key="prompt.name" /></td>
						<td align="left" class=subHead><bean:message key="prompt.supervision" />&nbsp;
										  <bean:message key="prompt.type" /></td>
						<td align="left" class=subHead><bean:message key="prompt.caseStatus" /></td>
					</tr>
                    <tr>
                    	<td width='1%'></td>
        				<td align="left"><bean:write name="programReferralForm" property="selectedCasefile.supervisionNum"/></td>
						<td align="left"><bean:write name="programReferralForm" property="selectedCasefile.sequenceNum"/></td>
						<td align="left"><bean:write name="programReferralForm" property="selectedCasefile.probationOfficer"/></td>
						<td align="left"><bean:write name="programReferralForm" property="selectedCasefile.supervisionType"/></td>
						<td align="left"><bean:write name="programReferralForm" property="selectedCasefile.caseStatus"/></td>
        			</tr>
  				</table>
  			</td>
  		</tr>
  	 </table>
    <%-- END casefile TABLE --%>
                   
	<!-- BEGIN BUTTON TABLE -->
	<div class="spacer"></div>
	<table border="0" width="100%">
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
        			<html:submit property="submitAction"><bean:message key="button.referralList"/></html:submit>
				</td>
   			</tr> 
		</logic:equal>   
	</table>
	<div class="spacer"></div>
	<!-- END BUTTON TABLE -->

  				</td>
  			</tr>
  		</table>
   	</td>
  </tr>
</table>
<!-- END DETAIL TABLE -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
