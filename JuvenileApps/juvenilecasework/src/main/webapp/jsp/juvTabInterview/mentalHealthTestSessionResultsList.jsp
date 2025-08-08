<!DOCTYPE HTML>
<!-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page -->
<!--MODIFICATIONS -->
<!-- 01/18/2007	Debbie Williamson	Create JSP -->
<!-- 07/20/2009  C Shimek   - #61004 added timeout.js  -->
<!-- 07/26/2013  CShimek    - fixed erroneous green bar at end of Juvenile Profile tabs  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!-- Javascript for emulated navigation -->
<title><bean:message key="title.heading"/> - mentalHealthTestSessionResultsList.jsp</title>

<script type="text/javascript"~ src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>


</head> 
<!--END HEADER TAG-->

<body topmargin="0" leftmargin="0" >
<html:form action="/displayMentalHealthTestSessionSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|316">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/></td>
	</tr>
	<tr>
		<td align="center" class="header"><bean:message key="prompt.testing"/> <bean:message key="prompt.session"/> <bean:message key="prompt.results"/> <bean:message key="prompt.list"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<div class='spacer'></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on hyperlinked Session Date to view details.</li>
			</ul>
		</td>
	</tr> 
</table>
<!-- END INSTRUCTION TABLE -->
<div class='spacer'></div>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!--juv profile header start-->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<!--juv profile header end-->
<div class='spacer'></div>
<!-- BEGIN DETAIL TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<!-- BEGIN PROFILE TABS TABLE -->		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td>
						<!--tabs start-->
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66" height="5"></td>
				</tr>
			</table>
<!-- END PROFILE TABS TABLE -->	
<!-- BEGIN GREEN TABS BORDER TABLE -->				
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
		  				<div class='spacer'></div>
<!-- BEGIN INNER DETAILS ALIGNMENT TABLE -->
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
<!-- BEGIN INTERVIEW TABS TABLE -->								
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
											<!--tabs start-->
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="mentalhealthtab"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
												<%--script type="text/javascript"~>renderInterviewInfo("Mental Health")</script--%>
											<!--tabs end-->
											</td>
										</tr>
										<tr>
										  	<td bgcolor='#6699FF' height="5"></td>
										 </tr>
									</table>
<!-- END INTERVIEW TABS TABLE -->
<!-- BEGIN BLUE TABS BORDER TABLE -->		
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top" align="center">
												<div class='spacer'></div>
<!-- BEGIN MENTAL HEALTH TABS TABLE -->												
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign="top">
															<!--tabs start-->
																<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																	<tiles:put name="tabid" value="testingsession"/>
																	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																</tiles:insert>	
															<%--script type="text/javascript"~>renderTestResultsTabs("Hosp")</script--%>
															<!--tabs end-->
														</td>
													</tr>
													<tr>
														<td bgcolor='#ff6666' height="5"></td>
													</tr>
												</table>
<!-- END MENTAL HEALTH TABS TABLE -->
<!-- BEGIN RED TABS BORDER TABLE -->		
												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td align="center">
															<div class='spacer'></div>
<!-- BEGIN RESULTS TABLE -->																
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td class="detailHead">Previous Juvenile Testing Results</td>
																</tr>																
																<tr>
																	<td>
<!-- BEGIN RESULTS DETAILS TABLE -->																	
																		<table width='100%' border="0" cellpadding="2" cellspacing="1">
																			<logic:empty name="testingSessionForm" property="testResultsList"> 
																				<tr class="detailHead">
																					<td colspan="4">No Previous Testing Results Available.</td>
																				</tr>
																			</logic:empty>

																			<logic:notEmpty name="testingSessionForm" property="testResultsList"> 
																				<tr class="formDeLabel">  
																					<td valign="top"><bean:message key="prompt.session"/> <bean:message key="prompt.date"/></td>
																					<td valign="top"><bean:message key="prompt.programReferral#"/></td>
																					<td valign="top"><bean:message key="prompt.serviceProvider"/></td>
																					<td valign="top"><bean:message key="prompt.eventType"/></td>
																				</tr>
																					<!-- Begin Pagination Header Tag-->
																				<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
																				<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
																					   		 maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
																						<input type="hidden" name="pager.offset" value="<%= offset %>">
																				<!-- End Pagination header stuff -->
																				<logic:iterate id="resultsIndex" name="testingSessionForm" property="testResultsList" indexId="index">
<!-- Begin Pagination item wrap -->																										
																					<pg:item>
																						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																							<td valign="top">
																								<a href="/<msp:webapp/>displayMentalHealthTestSessionSummary.do?submitAction=View&selectedValue=<bean:write name="resultsIndex" property="testSessID"/>">
																								<bean:write name="resultsIndex" property="sessionDate" formatKey="date.format.mmddyyyy" /></a>
																							</td>
																							<td valign="top"><bean:write name="resultsIndex" property="programReferralNum" /></td>
																							<td valign="top"><bean:write name="resultsIndex" property="serviceProviderName" /></td>
																							<td valign="top"><bean:write name="resultsIndex" property="eventType" /></td>
																						</tr>
																					</pg:item>
<!-- End Pagination item wrap -->
 								                            					</logic:iterate>
<!-- Begin Pagination navigation Row-->
																				<tr>
																					<td colspan="4">
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
																					</td>
																				</tr>
																			</pg:pager>
																			</logic:notEmpty>
																		</table>	
<!-- END RESULTS DETAILS TABLE -->
																	</td>
																</tr>
															</table>
<!-- END RESULTS TABLE -->																
<!-- BEGIN BUTTON TABLE -->
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction">
																			<bean:message key="button.back"></bean:message>
																		</html:submit>									
																		<html:submit property="submitAction">
																			<bean:message key="button.cancel"></bean:message>
																		</html:submit>
																	</td>
																</tr>
															</table>
<!-- END BUTTON TABLE -->
															<div class='spacer'></div>
														</td>
													</tr>
												</table>
<!-- END RED TABS BORDER TABLE -->												
												<div class='spacer4PX'></div>
											</td>
										</tr>
									</table>
<!-- END BLUE TABS BORDER TABLE -->									
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
<!-- END INNER DETAILS ALIGNMENT TABLE -->						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<!-- END GREEN TABS BORDER TABLE -->				
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
</html:form>
<div align="center">
	<script type="text/javascript">renderBackToTop()</script>
</div>
</body>
</html:html>