<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 05/30/2006		AWidjaja Create JSP--%>
<%-- 02/08/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 04/19/2012     CShimek  #73232 added allowUpdate edit to buttons for closed casefile status  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.CodeHelper" %>
<%@ page import="ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm" %>
<%@ page import="java.util.Collection" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="naming.InterviewConstants" %>

	


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewList.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/interviewList.js"></script>
<script>
	$(document).ready(function(){
		var juvenileNum =  '<bean:write name="juvenileInterviewForm" property="juvenileNum" />';
		$("#exhibitB").click(function(){
			changeFormActionURL('/JuvenileCasework/handleExhibitBAction.do?submitAction=Exhibit B&juvenileNum=' + juvenileNum,false);
			disableSubmitButtonCasework($(this));
			spinner();
		});	
		
	})
</script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<html:form action="/handleJuvInterviewSelection" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|86"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
    	<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.mjcwConductInterview"/> - <bean:message key="title.interviewList"/></td>
	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
    <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Interview Date to see details.</li>
				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
					<li>Select Add Interview button to create an interview.</li>
				</logic:equal>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign='top'>
    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="casefiledetailstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign='top' align='center'>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign='top'>
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="interviewtab"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
								  <tr>
            				<td bgcolor='#33cc66'>
            					<table border='0' cellpadding='2' cellspacing='1'>
            						<tr>
            							<td>&nbsp;<a href='/<msp:webapp/>displayJuvInterviewList.do?submitAction=Link'><bean:message key="prompt.viewInterviews"/></a> <b>|</b> </td>
            							<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message key="prompt.viewReportHistory"/></a>  </td>
            						</tr>
            					</table>
              			</td>
            	    </tr>
							  	</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign='top' align='center'>
          					<%-- BEGIN Today's Interview TABLE --%>
							 			 <div class='spacer'></div>
            					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            						<tr>
            							<td align='left' class='detailHead'><bean:message key="title.todaysAppointment"/></td>
            						</tr>
            						<tr>
            							<td>
            								<table width='100%' cellpadding='4' cellspacing='1'>
            									<logic:empty name="juvenileInterviewForm" property="todayAppointments">
            										<tr bgcolor='#cccccc'><td align='left' class='subHead' colspan='4'>No Appointment is Available</td></tr>
            									</logic:empty>
            									<logic:notEmpty name="juvenileInterviewForm" property="todayAppointments">
            										<tr class='formDeLabel'>
            											<td align='left' valign='top' nowrap='nowrap' width='1%'><bean:message key="prompt.interviewDateAndTime"/></td>
            											<td align='left' valign='top' nowrap='nowrap' width='1%'><bean:message key="prompt.interviewType"/></td>
            											<td align='left' valign='top' width='1%'><bean:message key="prompt.locationUnit"/></td>
            											<td align='left' valign='top' width='1%'>Event <bean:message key="prompt.status"/></td>
            										</tr>										
            										<logic:iterate id="apptIter" indexId="apptIndex" name="juvenileInterviewForm" property="todayAppointments">
            											<tr class="<%out.print( (apptIndex.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
            												<td align='left' valign='top'>
            													<a onclick="spinner()" href='/<msp:webapp/>handleJuvInterviewSelection.do?submitAction=View&currentInterview.interviewId=<bean:write name="apptIter" property="interviewId"/>'>
            													<bean:write name="apptIter" property="interviewDate" formatKey="date.format.mmddyyyy"/></a>
            													<bean:write name="apptIter" property="interviewDate" formatKey="time.format.hhmma"/>
            												</td>
            												<td align='left' valign='top'><bean:write name="apptIter" property="interviewType"/></td>
            												<td align='left'valign='top'><bean:write name="apptIter" property="juvLocUnitDescription"/></td>
            												<td align='left' valign='top'><bean:write name="apptIter" property="eventStatusDescription"/></td>
            											</tr>
            										</logic:iterate>	
            									</logic:notEmpty>
            								
            								</table>
            							</td>
            						</tr>
            					</table>
            					<%-- END Interview TABLE --%>
            				</td>
            			</tr>

            			<tr>
            				<td>
            					<%--button table begin--%>
								<div class='spacer'></div>
            					<table border="0" cellpadding='1' cellspacing='1' align='center'>
            						<tr>
            							<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_INTERVIEW_U%>'>
            								<td align="center">
            									<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
            										<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
            											<html:submit onclick="spinner()" property="submitAction" ><bean:message key="button.addUnscheduledInterview"/></html:submit>
            											<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.recordsReceived"/></html:submit>
            										</logic:equal>	
            									</logic:equal>	
            								</td>
            							</jims2:isAllowed>
            						</tr>
            					 </table>
								 <div class='spacer'></div>
            				</td>
            			</tr>
            
            			<tr><td> <div class='spacer4px'></div></td></tr>
            
            			<tr>
            				<td valign='top' align='center'>
            					<%-- BEGIN Interview TABLE --%>
            					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            						<tr>
            							<td align='left' class='detailHead' nowrap='nowrap' valign='top'>&nbsp;<bean:message key="title.allInterviews"/></td>
            						</tr>
            						<tr>
            							<td colspan='2'>
            								<table width='100%' cellpadding='4' cellspacing='1'>
            									<logic:empty name="juvenileInterviewForm" property="allInterviews">
            										<tr bgcolor='#cccccc'><td align='left' class='subHead' colspan='4'><bean:message key="prompt.noInterviewIsAvailable"/></td></tr>
            									</logic:empty>
            									<logic:notEmpty name="juvenileInterviewForm" property="allInterviews">
            										<tr class='formDeLabel'>
            											<td align='left' valign='top' nowrap='nowrap'><bean:message key="prompt.interviewDateAndTime"/></td>
            											<td align='left' valign='top' nowrap='nowrap'><bean:message key="prompt.interviewType"/></td>
            											<td align='left' valign='top'><bean:message key="prompt.locationUnit"/></td>
            											<td align='left' valign='top'>Interview Status</td>
            											<td align='left' valign='top'>Attendance</td>
            										</tr>					

            										<logic:iterate id="apptIter" indexId="apptIndex" name="juvenileInterviewForm" property="allInterviews">
            											<%-- Begin Pagination item wrap --%>
            											<pg:item>
            											<tr class="<%out.print( (apptIndex.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
            												<td align='left' valign='top'>
            												<%-- Completed Interview - allow hyperlink to Interview Details page --%>
            													<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
            														<a onclick="spinner()" href='/<msp:webapp/>handleJuvInterviewSelection.do?submitAction=View&currentInterview.interviewId=<bean:write name="apptIter" property="interviewId"/>'>
            														<bean:write name="apptIter" property="interviewDate" formatKey="date.format.mmddyyyy"/></a>
            														<bean:write name="apptIter" property="interviewDate" formatKey="time.format.hhmma"/>
            													</logic:equal>
            													
            												<%-- Incompleted Interview - only allow hyperlink to Update Interview page when User is cleared for Update Option and case is not closed --%>
            													<logic:notEqual name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
            														<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
	            														<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_INTERVIEW_U%>'>
	            															<a onclick="spinner()" href='/<msp:webapp/>handleJuvInterviewSelection.do?submitAction=View&currentInterview.interviewId=<bean:write name="apptIter" property="interviewId"/>'>
	            															<bean:write name="apptIter" property="interviewDate" formatKey="date.format.mmddyyyy"/></a>
	            															<bean:define id="dateLink" value=""/>
	            														</jims2:isAllowed>
            														</logic:equal>
            														<logic:notPresent name="dateLink">
            															<bean:write name="apptIter" property="interviewDate" formatKey="date.format.mmddyyyy"/>
            														</logic:notPresent>
            														<bean:write name="apptIter" property="interviewDate" formatKey="time.format.hhmma"/>
            													</logic:notEqual>
            												</td>
            												
            												<td align='left' valign='top'>
            													<logic:notEmpty name="apptIter" property="interviewTypeId">
            														<bean:write name="apptIter" property="interviewType"/>
            													</logic:notEmpty>
            												</td>
            												<td align='left' valign='top'><bean:write name="apptIter" property="juvLocUnitDescription"/></td>

            												<td align='left' valign='top'>
            													<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
        															<logic:greaterThan name="apptIter" property="taskCount" value="0">
        																<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_INCOMPLETE%>">
        																	<a href="/<msp:webapp/>displayJuvInterviewChecklist.do?submitAction=Interview Checklist&currentInterview.interviewId=<bean:write name='apptIter' property='interviewId'/>">
        																</logic:equal>
        															</logic:greaterThan>
        														</logic:equal>	
        														<bean:write name="apptIter" property="interviewStatusDescription"/>
        														<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
	        														<logic:greaterThan name="apptIter" property="taskCount" value="0">
	        															<logic:equal name="apptIter" property="interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_INCOMPLETE%>">
	        																</a>
	        															</logic:equal>
	        														</logic:greaterThan>
	        													</logic:equal>	
        													</td>
            												<td align='left' valign='top'><bean:write name="apptIter" property="attendanceStatusDescription"/></td>
            											</tr>
            											</pg:item>
            											<%-- End Pagination item wrap --%>
            										</logic:iterate>	
            									</logic:notEmpty>
            								</table>
            								<%-- Begin Pagination navigation Row--%>
           									<table align="center">
            									<tr>
	            									<td>
	            										<pg:index>
	            											<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
	            												<tiles:put name="pagerUniqueName" value="pagerSearch"/>
	            												<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
	            											</tiles:insert>
	            										</pg:index>
	            									</td>
            									</tr>
            								</table>
            								<%-- End Pagination navigation Row--%>
            							</td>
            						</tr>
            					</table>
            					<%-- END Interview TABLE --%>		
	            				<div class='spacer'></div>
            				</td>
            			</tr>

	            			<tr>
	            				<td>
	            					<%--button table begin--%>
	            					<table border="0" cellpadding='1' cellspacing='1' align='center'>
	            						<tr>
	            							<td align="center">	
	            								<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>							
		            								<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_INTERVIEW_U%>'>
		            								<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
		            										<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
			            										<input type="submit" name="submitAction" value="<bean:message key='button.requestAttorneyAppt'/>" id="attorneyAppt">
				            									<html:submit property="submitAction" styleId="printParentalStatement"><bean:message key="button.printParentalStatement"/></html:submit>
				            									<input type="submit" name="submitAction" id="viewSocialHistoryData" value="<bean:message key='button.viewSocialHistoryData'/>" >
			            										<input type="submit" name="submitAction" id="parentalRights" value="<bean:message key='button.parentalRights'/>" >
			            										
			            									</logic:equal>	
		            									</logic:equal>		
		            								</jims2:isAllowed>
		            								<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_INTERVIEW_EXHIBITB%>'>
		            									<logic:equal name="juvenileInterviewForm" property="allowUpdates" value="true">
		            										<input type="submit" name="submitAction" id="exhibitB" value="<bean:message key='button.exhibitB'/>" >
		            									</logic:equal>
		            								</jims2:isAllowed>	
	            								<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
            					      </td>
            					    </tr>
            					  </table>
            					  <%--button table end--%>
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
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

</pg:pager>
<%-- End Pagination Closing Tag --%>

</html:form>
<div class='spacer'></div>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
