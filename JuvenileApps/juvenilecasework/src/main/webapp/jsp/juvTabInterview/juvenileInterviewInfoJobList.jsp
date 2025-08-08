<!DOCTYPE HTML>
<%-- User selects the "Jobs" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 06/10/2005	Hien Rodriguez	Create JSP --%>
<%-- 06/27/2005 Surya Modified --%>
<%-- 12/15/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/20/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add button --%>
<%-- 07/14/2015 R Capestani    	#27637  Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Jobs UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="X-UA-Compatible" content="IE=9, chrome=1" />
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileInterviewInfoJobList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Jobs</td> 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
	 	<td>
	 	  <ul>
	     	<li>Click on hyperlinked Entry Date to see details about that job.</li>
	 	  </ul>
	 	</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN DETAIL TABLE --%>  
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
		<td valign='top'>
   			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
					</td>
				</tr>
				<tr>
		  			<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
		  		</tr>
		  	</table>
			  	
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="job"/>
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
					  			<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
					  		</tr>
						</table>
			
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							
							<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|201">
							<tr>
								<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
							</tr> 
							<tr>
								<td valign='top' align='center'>																		
									<%-- BEGIN JOB TABLE --%>
									<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<%-- display detail header --%> 
										<logic:empty name="juvenileJobForm" property="jobs"> 
										    <tr class='detailHead'>
												<td align='left' colspan="4">No Jobs Available</td>
										    </tr>
										</logic:empty>

										<logic:notEmpty name="juvenileJobForm" property="jobs"> 
											<tr class='detailHead'>
												<td align='left'><bean:message key="prompt.entryDate" /></td>
												<td align='left'><bean:message key="prompt.placeEmployed" /></td>
												<td align='left'><bean:message key="prompt.workHours" /></td>
												<td align='left'><bean:message key="prompt.employmentStatus" /></td>										
											</tr>

											<%-- display detail info --%>
											<logic:iterate indexId="index" id="jobsIndex" name="juvenileJobForm" property="jobs" >
												<%-- Begin Pagination item wrap --%>
	                      						<pg:item>
													<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
														<td align='left'><a href="/<msp:webapp/>displayJuvenileJobDetails.do?jobNum=<bean:write name="jobsIndex" property="jobNum"/>">
														<bean:write name="jobsIndex" property="entryDateAsString" />
														</a>
														</td>
														<td align='left'><bean:write name="jobsIndex" property="employmentPlace" /></td>
														<td align='left'><bean:write name="jobsIndex" property="workHours" /></td>
														<td align='left'><bean:write name="jobsIndex" property="employmentStatus" /></td>    
													</tr>
											 	</pg:item>
										   		<%-- End Pagination item wrap --%>
											</logic:iterate>
										</logic:notEmpty>						
									</table>
									<%-- END JOB TABLE --%>						 
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
									<div align="center">
										<div class='spacer'></div> 
										<%-- BEGIN BUTTON TABLE --%>											
										<table border='0'>	
											<tr>
												<html:form action="/displayJuvenileJobsCreate" target="content">
													<td>
														<html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button>
													  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_JOBS_U%>'>
													  		<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
													  		<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
																<html:submit property="submitAction" ><bean:message key="button.addMoreJobs" /></html:submit>
															</logic:notEqual>
															<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
															<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																<html:submit property="submitAction" ><bean:message key="button.addMoreJobs" /></html:submit>
															</jims2:isAllowed>
															</logic:equal>
															</logic:equal>	
														</jims2:isAllowed>
													</td>
												</html:form>

												<html:form action="/displayJuvenileMasterInformation" target="content">
													<td>
														<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
													</td>
												</html:form>
											</tr>
										</table>
										<%-- END BUTTON TABLE --%>
									</div>
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

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

<%-- END FORM --%>
<div class='spacer'></div> 
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>