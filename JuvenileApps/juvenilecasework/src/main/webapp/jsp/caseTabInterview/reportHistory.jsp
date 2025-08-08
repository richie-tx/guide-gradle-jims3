<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>
<%-- 10/26/2007 	CShimek		#46353 revised time display format to show AM/PM %>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- reportHistory.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

</head>

<html:form action="/displayReportDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|85"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0"> 


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.mjcwConductInterview"/> - <bean:message key="title.reportHistory"/></td>
	</tr> 
</table> 
<%-- END HEADING TABLE --%> 

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
		maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>	
	
<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<table width="98%" border="0"> 
	<tr> 
		<td> 
		  <ul> 
			  <li>Select Report Creation Date to See the report details.</li>
	      <li>Select a social history report to e-mail it.</li>
  		</ul>
  	</td>
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<div class='spacer'></div>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
  	<td valign=top>
  		<%-- BEGIN TAB TABLE --%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>	
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>	

		<%-- BEGIN BORDER TABLE BLUE TABLE --%>
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
        							<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
        								<tr>
        									<td class=detailHead><bean:message key="prompt.reportHistory"/></td>
        
        								</tr>
        								<tr>
        									<td>
        										<table width='100%' cellpadding=4 cellspacing=1>
        										<logic:empty name="juvenileInterviewForm" property="reportHistory">
        											<tr bgcolor='#cccccc'> 
        													<td colspan="4" class=subHead>No Report History Records Available </td> 
        											</tr> 
        											</logic:empty>
        											<logic:notEmpty name="juvenileInterviewForm" property="reportHistory">
        												<tr class=formDeLabel>
        													<td valign=top nowrap><bean:message key="prompt.reportCreationDate"/></td>
        													<td valign=top nowrap><bean:message key="prompt.reportType"/></td>
        													<td valign=top nowrap><bean:message key="prompt.email"/></td>
        	
        												</tr>						
        												<%int RecordCounter = 0; String bgcolor = "";%>
        												<logic:iterate id="reportIter" name="juvenileInterviewForm" property="reportHistory">
        													<%-- Begin Pagination item wrap --%>
        													<pg:item>
        													<tr class=<%RecordCounter++;  
																	  bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
        														out.print(bgcolor);%>
																	height="100%">
        														<td valign=top>
        															<a href="/<msp:webapp/>displayReportDetails.do?submitAction=Link&reportId=<bean:write name='reportIter' property='reportId'/>">
        																<bean:write name="reportIter" property="creationDate" formatKey="date.format.mmddyyyy"/></a>&nbsp;
        															<bean:write name="reportIter" property="creationDate" formatKey="time.format.hhmma"/>
        														</td>
        														<td valign=top><bean:write name="reportIter" property="reportType"/></td>
        														<td valign=top>
        															<logic:equal name="reportIter" property="reportType" value="SOCIAL HISTORY REPORT">
          															<a href="/<msp:webapp/>displayEmailSocialHistoryReport.do?submitAction=Link&reportId=<bean:write name='reportIter' property='reportId'/>">Click Here</a>
        															</logic:equal>
        														</td>
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
											
											<table align='center'>
              					<tr align="center">
              						<td>
                						<div class='spacer'></div>
              							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
              							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
              						</td>
                        </tr>
                  </table>
                  <div class='spacer'></div>
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
					
		
	</pg:pager>
<%-- End Pagination Closing Tag --%>				
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>	
</html:form>
</html:html>