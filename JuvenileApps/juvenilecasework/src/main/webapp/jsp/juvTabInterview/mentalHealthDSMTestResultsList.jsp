<!DOCTYPE HTML>
<!-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page -->
<!--MODIFICATIONS -->
<!-- 01/18/2007	Debbie Williamson	Create JSP -->
<!-- 07/20/2009 C Shimek    #61004 added timeout.js  -->
<!-- 07/24/2013 CShimek		#75848 - Revise DSM IV to DSM V -->

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
<title><bean:message key="title.heading"/> - mentalHealthDSMtestResultsList.jsp</title>
<!-- Javascript for emulated navigation -->
<script type="text/javascript"~ src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

</head> 
<!--END HEADER TAG-->

<body topmargin="0" leftmargin="0" >
<html:form action="/displayMentalHealthDSMIVSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|321">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/></td>
	</tr>
	<tr>
		<td align="center" class="header">DSM V <bean:message key="prompt.results"/> <bean:message key="prompt.list"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<div class="spacer"></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on hyperlinked Test Date to view details.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>

<!--juv profile header start-->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="profileheader"/>
	</tiles:insert>
<!--juv profile header end-->
<div class="spacer"></div>
<!-- begin detail table -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
					<tiles:put name="tabid" value="interviewinfotab"/>
					<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
				</tiles:insert>	
				<tr>
					<td bgcolor='#33cc66' height="5"></td>
				</tr>
			</table>

			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top"><tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="mentalhealthtab"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>		
											</td>
										</tr>
										<tr>
											<td bgcolor='#6699FF' height="5"></td>								  	  
										</tr>
									</table>

			         				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top" align="center">
												<div class="spacer"></div>
													<table width='98%' border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td valign="top">
																<!--tabs start-->
																	<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																		<tiles:put name="tabid" value="dsm"/>
																		<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																	</tiles:insert>	
																<!--tabs end-->
															</td>
														</tr>
														<tr>
														  	<td bgcolor='#ff6666' height="5"></td>
														</tr>
													</table>
<!-- END OF RED TABS -->
													<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">							
														<tr>
															<td align="center">
																<div class="spacer"></div>
																<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																	<tr>
																		<td class="detailHead">DSM V Results</td>
																	</tr>																
																	<tr>
																		<td>
																			<table width='100%' border="0" cellpadding="2" cellspacing="1">
																				<logic:empty name="testingSessionForm" property="dsmResultsList"> 
																					<tr class="detailHead">
																						<td colspan="4">No Previous DSM V Test Results Available.</td>
																					</tr>
																				</logic:empty>

																				<logic:notEmpty name="testingSessionForm" property="dsmResultsList"> 
																					<tr>
																						<td>
																							<table width='100%' border="0" cellpadding="2" cellspacing="1">
																								<tr class="formDeLabel">  
																				                	<td valign="top"><bean:message key="prompt.test"/> <bean:message key="prompt.date"/></td>
																				                	<td valign="top"><bean:message key="prompt.programReferral#"/></td>
																				                	<td valign="top"><bean:message key="prompt.serviceProvider"/></td>																			                  
															                					</tr>
															                 <!-- Begin Pagination Header Tag-->
																								<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
																								<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
																								   		 maxIndexPages="10"export="offset,currentPageNumber=pageNumber" scope="request">
																					   					<input type="hidden" name="pager.offset" value="<%= offset %>">
    																		<!-- End Pagination header stuff -->
    																							<logic:iterate indexId='index' id="resultsIndex" name="testingSessionForm" property="dsmResultsList" >
    																		<!-- Begin Pagination item wrap -->																										
																									<pg:item>
      																									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
    																			        		        	<td valign="top"><a href="/<msp:webapp/>displayMentalHealthDSMIVSummary.do?submitAction=View&selectedValue=<bean:write name="resultsIndex" property="testId"/>">
      												                                							<bean:write name="resultsIndex" property="testDate" formatKey="date.format.mmddyyyy" /></a>
																											</td>
    																			                  			<td valign="top"><bean:write name="resultsIndex" property="programReferralNum" /></td>
    																			                  			<td valign="top"><bean:write name="resultsIndex" property="serviceProviderName" /></td>																									                 
    																			                		</tr>
    																								</pg:item>
																		   <!-- End Pagination item wrap -->
												                           						</logic:iterate>
											                            	<!-- Begin Pagination navigation Row-->
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
        																					</pg:pager>
																						</table>
																					</td>
																				</tr>
																			</logic:notEmpty>
																		</table>
                                   										<div class='spacer'></div>
<!-- BEGIN BUTTON TABLE -->
																		<table border="0" width="100%">
																			<tr>
																				<td align="center">
																					<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>									
																					<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
																				</td>
																			</tr>
																		</table>
<!-- END BUTTON TABLE -->
  																		<div class='spacer'></div>
																	</td>
																</tr>
															</table>
															<div class='spacer4px'></div>
														</td>
													</tr>
												</table>
												<div class='spacer'></div>  
<!-- END RED TABLE -->
											</td>
										</tr>
									</table>
									<div class='spacer'></div>  
<!-- END OF BLUE TABLE -->
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- end detail table -->
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>