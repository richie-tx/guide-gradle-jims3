<!DOCTYPE HTML>
<%-- Used to display search casefile results --%>
<%--MODIFICATIONS --%>
<%-- 12/11/2006	Awidjaja	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>
<%@ page import="naming.InterviewConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>	
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title>Juvenile Casework - Interview List</title>
<%-- Javascript for emulated navigation --%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<link href="/<msp:webapp/>css/base.css" rel="stylesheet" type="text/css">
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|206">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Interview List</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
		  <ul>
        <li>Click on a hyperlinked interview date to view interview details.</li>
      </ul>
		</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>


<%--BEGIN FORM TAG--%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  	<td valign=top>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  			<tr>
  				<td valign=top>
  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="casefilestab"/>
  						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
  					</tiles:insert>				
  				</td>
  			</tr>
  			<tr>
  				<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
  			</tr>
      </table>
	
			<%-- BEGIN DETAIL TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>
						<%-- begin blue tabs (2nd row) --%>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign=top> 
									<%--tabs start--%>
									<tiles:insert page="../caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
										<tiles:put name="tabid" value="interview"/>
										<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
									</tiles:insert>			
									<%--tabs end--%>
								</td>
							</tr>
							<tr>
  		  				<td bgcolor='#6699ff'>
  		  					<table border=0 cellpadding=2 cellspacing=1>
  		  						<tr>
  		  							<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link'>View Interviews</a> <b>|</b></td>
  		  							<td align="left">&nbsp;<a href='/<msp:webapp/>displayJuvenileProfileReportHistory.do?submitAction=Link'>View Report History</a> <b>|</b></td>
  		  						</tr>
  		  					</table>
  		    			</td>
							</tr>
						</table>
					
						<%-- BEGIN Interview TABLE --%>
  					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr><td> <div class=spacer4px></div></td></tr>

							<logic:empty name="juvenileInterviewForm" property="todaysAppointmentGroupedByCasefileId">
  							<tr>
    							<td align=center>
         						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    									<tr>
    										<td class=detailHead nowrap valign=top>Today's Appointment(s)</td>
    									</tr>
    									<tr>
    										<td>
    											<table width='100%' cellpadding=4 cellspacing=1>
    									 			<tr bgcolor='#cccccc'><td class=subHead colspan=4 align="left">No Appointment is Available</td></tr>
    									 		</table>
    									 	</td>
    									</tr>
    								</table>
    							</td>
    						</tr>
  						</logic:empty>

  						<logic:notEmpty name="juvenileInterviewForm" property="todaysAppointmentGroupedByCasefileId">
  							<logic:iterate id="keysIter" indexId="idx" name="juvenileInterviewForm" property="todaysAppointmentGroupedByCasefileId">
  									<tr>
  										<td valign=top align=center>
  											<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
  												<tr>
  													<td class=detailHead nowrap valign=top>Today's Appointment(s) - Supervision #<bean:write name="keysIter" property="key"/></td>
  												</tr>
  												<tr>
  													<td>
  														<table width='100%' cellpadding=4 cellspacing=1>
  															<tr class=formDeLabel>
  																<td valign=top nowrap width='15%' align="left">Interview Date/Time</td>
  																<td valign=top nowrap width='25%' align="left">Interview Type</td>
  																<td valign=top width='50%' align="left">Location</td>
  																<td valign=top width='10%' align="left">Status</td>
  															</tr>
  															<% int RecordCounter = 0;%>
  															<logic:iterate id="apptIter" indexId="apptIndex" name="keysIter" property="value">
  																<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
  																	<td valign=top align="left">
  																		<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
  																			<a href='/<msp:webapp/>handleJuvenileProfileInterviewSelection.do?submitAction=View&currentInterview.interviewId=<bean:write name="apptIter" property="interviewId"/>&currentInterview.casefileId=<bean:write name="keysIter" property="key"/>'>
  																		</logic:equal>
  																		<bean:write name="apptIter" property="interviewDate" formatKey="date.format.mmddyyyy"/>
  																		<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>"></a></logic:equal>
  																		<bean:write name="apptIter" property="interviewDate" formatKey="time.format.hhmma"/>
  																	</td>

  																	<td valign=top align="left"><bean:write name="apptIter" property="interviewType"/></td>
  																	<td valign=top align="left"><bean:write name="apptIter" property="juvLocUnitDescription"/></td>
  																	<td valign=top align="left"><bean:write name="apptIter" property="eventStatusDescription"/></td>
  																</tr>
  															</logic:iterate>
  														</table>
  													</td>
  												</tr>
  											</table>
  										</td>
  									</tr>
									<tr><td><img src='/<msp:webapp/>images/spacer.gif' width=5></td></tr>
								</logic:iterate>	
							</logic:notEmpty>
								
							<logic:empty name="juvenileInterviewForm" property="interviewsGroupedByCasefileId">
								<tr>
									<td valign=top align=center>
										<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>											
												<td class=detailHead nowrap valign=top>&nbsp;All Interview(s)</td>
											</tr>
											<tr >
												<td colspan=2>
													<table width='100%' cellpadding=4 cellspacing=1>
														<tr bgcolor='#cccccc'><td class=subHead colspan=4 align="left"><bean:message key="prompt.noInterviewIsAvailable"/></td></tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</logic:empty>

							<logic:notEmpty name="juvenileInterviewForm" property="interviewsGroupedByCasefileId">
							<tr>
								<td valign=top align=center>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class=detailHead width='1%'><a href="javascript:showHideMulti('toggleAll', 'phCharAll', 1, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="toggleAll"></a></td>
											<td class=detailHead nowrap valign=top>&nbsp;All Interview(s)</td>
										</tr>
										<tr id="phCharAll0" class='hidden'>
										
											<td valign=top align=center colspan=4>
												<table width='100%' border="0" cellpadding="2" cellspacing="0">
													<logic:notEmpty name="juvenileInterviewForm" property="interviewsGroupedByCasefileId">
														<logic:iterate id="keysIter" indexId="idx" name="juvenileInterviewForm" property="interviewsGroupedByCasefileId">
															<tr>
																<td valign=top align=center>
																	<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																		<tr>
																			<td class=detailHead width='1%'><a href="javascript:showHideMulti('toggle<bean:write name='idx'/>', 'phChar<bean:write name='idx'/>-', 1, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="toggle<bean:write name='idx'/>"></a></td>
																			<td class=detailHead nowrap valign=top>&nbsp;Interview(s) for Supervision #<bean:write name="keysIter" property="key"/></td>
																		</tr>
																		<tr id="phChar<bean:write name='idx'/>-0" class=hidden>
																			<td colspan=2>
																				<table width='100%' cellpadding=4 cellspacing=1>
																					<tr class=formDeLabel>
																						<td valign=top nowrap width='15%' align="left">Interview Date/Time</td>
																						<td valign=top nowrap width='20%' align="left">Interview Type</td>
																						<td valign=top width='45%' align="left">Location</td>
																						<td valign=top width='10%' align="left">Interview Status</td>
																						<td valign=top width='10%' align="left">Attendance</td>
																					</tr>
																					
																					<% int RecordCounter = 0; %>
																					<logic:iterate id="apptIter" indexId="apptIndex" name="keysIter" property="value">
																						
																						<tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
																							<td valign=top align="left">
																								<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
																									<a href='/<msp:webapp/>handleJuvenileProfileInterviewSelection.do?submitAction=View&currentInterview.interviewId=<bean:write name="apptIter" property="interviewId"/>&currentInterview.casefileId=<bean:write name="keysIter" property="key"/>'>
																								</logic:equal>
																								<bean:write name="apptIter" property="interviewDate" formatKey="date.format.mmddyyyy"/>
																								<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>"></a></logic:equal>
																								<bean:write name="apptIter" property="interviewDate" formatKey="time.format.hhmma"/>
																							</td>
																							<td valign=top align="left">
																								<logic:notEmpty name="apptIter" property="interviewTypeId">
																									<bean:write name="apptIter" property="interviewType"/>
																								</logic:notEmpty>
																							</td>
																								
																							<td valign=top align="left"><bean:write name="apptIter" property="juvLocUnitDescription"/></td>
																							<td valign=top align="left"><bean:write name="apptIter" property="interviewStatusDescription"/></td>
																							<td valign=top align="left"><bean:write name="apptIter" property="attendanceStatusDescription"/></td>
																						</tr>
																					</logic:iterate>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>																				
												</table>
											</td>
										</tr>
									</table>
							  </td>
							</tr>
						</logic:notEmpty>
							
            <%-- BEGIN BUTTON TABLE --%>
            <table border="0" align="center">
            	<tr align="center">
                <td>
                	<html:submit property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:submit>
            		</td>
            		
                <html:form action="/displayJuvenileMasterInformation" target="content"> 
            	   	<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
               	</html:form>
            	</tr>
            </table>
            <div class=spacer></div>
            <%-- END BUTTON TABLE --%>
          </td>
        </tr>
      </table>					
      <%-- END CASEFILE TABLE --%>				 
      <div class=spacer></div>	
		</td>
	</tr>
</table>
		
<%-- END DETAIL TABLE --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>

</html:html>
