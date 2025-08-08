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
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>





<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;- otherProgramReferralDetails.jsp</title>
<%-- Javascript for emulated navigation --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />

<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/casework_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/handleProgramReferral" target="content">

<input type="hidden" name="helpFile" value="commonsupervision/asp_Program Referral/Service_Provider_Program_Referral.htm#|391">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.serviceProvider" />&nbsp;-&nbsp;<bean:message key="prompt.programReferral" />&nbsp;		
			<bean:message key="title.details" />
		</td>
  </tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
  <tr>
    <td>	 
  	  <ul>
    		<li>Select the Close Window button to close this window.</li>	    
  	  </ul> 	 
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  <tr>
    <td width='1%' class="detailHead">Program Referral - <bean:write name="programReferralForm" property="programReferral.juvenileName" /> </td>
  </tr>
  <tr>
    <td valign="top" align="center">			
      <%-- BEGIN EVENTS TABLE --%>
      <div class='spacer'></div>
      <table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
        <tr>
          <td width='1%' class="detailHead"><a href="javascript:showHideMulti('events', 'cchar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="events"></a>&nbsp;Event Details</td>
        </tr>
          <tr id="cchar0" class='hidden'>
    				 <td>
                <table align="center" width="100%" cellpadding="2" cellspacing="1">  
                	<tr>
        						<logic:empty name="programReferralForm" property="otherProgramReferral.juvenileEvents">
        							<td valign="top">Juvenile has no events</td>
        						</logic:empty>

         						<logic:notEmpty name="programReferralForm" property="otherProgramReferral.juvenileEvents">
                  		<td valign="top">
                  			<table width='100%' cellpadding="2" cellspacing="1">
                  				<tr bgcolor='#cccccc' class="subHead">                  					
                  					<td><bean:message key="prompt.dateTime" /></td>
          									<td><bean:message key="prompt.locationUnit" /></td>
                  					<td><bean:message key="prompt.service" /></td>
                  				</tr>

                    			<logic:iterate id="eventIt" name="programReferralForm" property="otherProgramReferral.juvenileEvents" indexId="index">
          							    <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">   
                    					<td><a href="javascript: openWindow('/<msp:webapp/>handleProgramReferral.do?submitAction=<bean:message key="button.details"/>&eventId=<bean:write name="eventIt" property="eventId" />',380, 700)">
            										<bean:write name="eventIt" property="eventDate" formatKey="datetime.format.mmddyyyyHHmm"/> </a>
            									</td>
            									<td><bean:write name="eventIt" property="location"/></td>
            									<td><bean:write name="eventIt" property="serviceName"/></td>
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
      		</td>
        </tr>
        <tr>
          <td valign="top" align="center">			
             <%-- BEGIN ATTENDEE TABLE --%>
            <table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              <tr>
                <td width='1%' class="detailHead"><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
              </tr>
              <tr id="achar0" class='hidden'>
        				 <td>
                 
                  <table align="center" width="100%" cellpadding="2" cellspacing="1">  
                  	<tr>
          						<logic:empty name="programReferralForm" property="otherProgramReferral.existingReferrals">
          							<td valign="top">Juvenile has no past attendance for program</td>
          						</logic:empty>

          						<logic:notEmpty name="programReferralForm" property="otherProgramReferral.existingReferrals">
                    		<td valign="top">
                    			<table width='100%' cellpadding="2" cellspacing="1">
                    				<tr bgcolor='#cccccc' class="subHead">
                    					<td><bean:message key="prompt.programReferral#" /></td>
                    					<td><bean:message key="prompt.date" /></td>
                    					<td><bean:message key="prompt.service" /></td>
                    					<td align="center"><bean:message key="prompt.attended" /></td>
                    					<td align="center"><bean:message key="prompt.absent" /></td>
                    					<td align="center"><bean:message key="prompt.excused" /></td>
                    				</tr>

                      			<logic:iterate id="programRef" name="programReferralForm" property="otherProgramReferral.existingReferrals" indexId="index">
                              <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
                      					<td><bean:write name="programRef" property="programReferralId"/></td>
                      					<td><bean:write name="programRef" property="startDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
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
            <%-- END ATTENDEE TABLE --%>
      		</td>
        </tr>
        <%-- END Program Referral History TABLE --%>	

        <%-- BEGIN PROGRAM REFERRAL DETAILS TABLE --%>
        <tr>
          <td valign="top" align="center">
       			<table align="center" width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
       				<tr>
       					<td class="detailHead"><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /></td> 
       				</tr>
      
       				<tr>
    				    <td valign="top" align="center">
  					    <table cellpadding="2" cellspacing="1" width='100%'>
              <tr>
								<td class="formDeLabel"><bean:message key="prompt.beginDate" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.beginDateStr" /></td>
								<td class="formDeLabel"><bean:message key="prompt.endDate" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.endDateStr"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.courtOrdered" /></td>
								<bean:define id="progRef" name="programReferralForm" property="otherProgramReferral"/>		
								<td class="formDe"><jims:displayBoolean name="progRef" property="courtOrdered" trueValue="Yes" falseValue="No"/></td>								
								<td class="formDeLabel"><bean:message key="prompt.assignedHours" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.assignedHours"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.sentDate" /></td>								
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.sentDate" formatKey="date.format.mmddyyyy" /></td>
								<td class="formDeLabel"><bean:message key="prompt.acknowledgementDate" /></td>								
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.acknowledgementDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.programOutcome" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.outComeDescription"/></td>
								<td class="formDeLabel" nowrap><bean:message key="prompt.referralStatus" /></td>
								<td class="formDe"><bean:write name="programReferralForm" property="otherProgramReferral.referralState.description"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments" /></td>
              </tr>
              <tr>
								<td class="formDe" colspan="4">
									<div  class='scrollingDiv100'>
										<table>
											<logic:notEmpty name="programReferralForm" property="otherProgramReferral.referralComments">																							
												<logic:iterate  id="refComment" name="programReferralForm" property="otherProgramReferral.referralComments">
													<tr><td>[<bean:write name="refComment" property="commentsDate" formatKey="datetime.format.mmddyyyyHHmm"/> - <bean:write name="refComment" property="userName"/>  ] <bean:write name="refComment" property="commentText"/></td></tr>
												</logic:iterate>
											</logic:notEmpty>
										</table>
									</div>											
								</td>								
              </tr>
             </table>
           </td>
         </tr>
      </table>
		  </td>
		</tr>
     <%-- END PROGRAM REFERRAL DETAILS TABLE --%>

    <%-- BEGIN BUTTONS TABLE --%>
	 <tr>
  		<td align="center"><input name="button" type="button" value='Close Window' onClick="javascript:window.close();"></td>
		</tr>
</table>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

