<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 08/23/2006		AWidjaja Create JSP--%>
<%-- 02/08/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.InterviewConstants" %>



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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewChecklist.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/interviewList.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/submitCompleteJuvInterviewTasks" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|81"> 


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >
		Juvenile Casework - Conduct Interview - Interview Checklist
	</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Click the appropriate link to proceed to the task to work on.</li>
		<li>To mark a task completed, click on Complete checkbox and select Complete Selected Task(s).</li>
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
		<td valign=top>
  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="casefiledetailstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign=top align=center>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign=top>
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
					  					<table border=0 cellpadding=2 cellspacing=1>
					  						<tr>
  				  							<td>&nbsp;<a href='/<msp:webapp/>displayJuvInterviewList.do?submitAction=Link'><bean:message key="prompt.viewInterviews"/></a> <b>|</b> </td>
  				  							<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message key="prompt.viewReportHistory"/></a> <b>|</b> </td>
  				  						</tr>
  				  					</table>
  			    			  </td>
  			  	      </tr>
							 </table>

							<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign=top align=center>
										  <div class='spacer'></div>
            						<logic:notEmpty name="juvenileInterviewForm" property="currentInterview.interviewTasks">
            						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            							<tr>
            								<td class=detailHead>Interview Checklist</td>
            							</tr>
            							<tr>
            								<td bgcolor='#f0f0f0'>
            								<%-- BEGIN INTERVIEW TABLE --%>
            								<table width='100%' cellpadding=4 cellspacing=1>
            									<tr class=formDeLabel>
            										<td valign=top nowrap width='1%'>Complete</td>
            										<td valign=top nowrap>Task</td>
            										<td valign=top nowrap>Interview Task Status</td>
            										<td valign=top>Link</td>
            									</tr>
            										<logic:iterate id="taskIter" name="juvenileInterviewForm" property="currentInterview.interviewTasks">
            											<tr class="normalRow">
            												<td>
            													<logic:notEqual name="taskIter" property="completed" value="true">
            														<input type="checkbox" name="currentInterview.selectedTasks" value="<bean:write name='taskIter' property='taskId'/>">
            													</logic:notEqual>
            												</td>
            												<td valign=top><bean:write name="taskIter" property="taskName"/></td>
            												<td valign=top>
            													<logic:equal name="taskIter" property="completed" value="true">
            														Completed
            													</logic:equal>
            													<logic:notEqual name="taskIter" property="completed" value="true">
            														In Progress
            													</logic:notEqual>
            												</td>
            												<td valign=top>
            													<logic:notEqual name="taskIter" property="completed" value="true">
            														<a href="/JuvenileCasework/juvenileCaseExecuteTask.do?taskId=<bean:write name="taskIter" property="execTaskId"/>">Click Here</a>
            													</logic:notEqual>
            												</td>
            											</tr>
            										</logic:iterate>
            								</table>
            								<%-- END INTERVIEW TABLE --%>
            								</td>
            							</tr>
            						</table>
            						</logic:notEmpty>
            						<%-- END DETAIL TABLE --%>
                   
            						<%-- BEGIN BUTTON TABLE --%>
												<div class='spacer'></div>
            						<table border="0" width="100%">
            							<logic:notEqual name="juvenileInterviewForm" property="currentInterview.interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
            								<tr id="completedBtns">
            									<td align="center">
            										<html:submit property="submitAction" styleId="completeSelectedTasksId"><bean:message key="button.completeSelectedTasks"/></html:submit>
            										<html:submit property="submitAction"><bean:message key="button.generateChecklist"/></html:submit>
            									</td>
            								</tr>
            							</logic:notEqual>
            						 
            							<logic:equal name="juvenileInterviewForm" property="currentInterview.interviewStatusCd" value="<%=InterviewConstants.INTERVIEW_STATUS_COMPLETE%>">
            								<tr id="newBtns">
            									<td align="center">
            										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_INTERVIEW_U%>'>
            											<input type="submit" name="submitAction" value="<bean:message key='button.requestAttorneyAppt'/>" id="attorneyAppt">            										 	
            											<input type="submit" name="submitAction" value="<bean:message key='button.printParentalStatement'/>" id="printParentalStatement">
            											<input type="submit" name="submitAction" value="<bean:message key='button.viewSocialHistoryData'/>" id="viewSocialHistoryData">
            											<input type="submit" name="submitAction" value="<bean:message key='button.parentalRights'/>" id="parentalRights">	
            										</jims2:isAllowed>
            									</td>
            								</tr>
            							</logic:equal>
            						</table>
            						
            						<%-- END BUTTON TABLE --%>
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
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<%-- END NOTES TABLE --%>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
