<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- Used for displaying the previous MAYSI assessments list --%>
<%-- 05/10/2005	 DApte			Create JSP --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Button security features --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to New Assessment button --%>
<%-- 07/26/2013 C Shimek    cleaned up Assessment list display to be more consistent with other pages --%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%--@ taglib uri="/WEB-INF/msp.tld" prefix="msp" --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



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
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>
<title><bean:message key="title.heading"/> - mentalHealthAssessList.jsp </title>

<%--REQUIRED FIELD CHECK USING JAVASCRIPT FILE FOR THIS PAGE --%> 
<%--tiles:insert page="/js/casefileHeader.jsp" flush="true" --%>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/handleMAYSISelection" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='98%' align="center">	
	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.mentalAssessment"/>&nbsp;<bean:message key="prompt.list"/></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td align="left">
			<ul>
				<li>Select hyperlinked Assessment Date to view Mental Health Assessment Details.</li>
				<li>Select New Assessment button for new Mental Health Assessment.</li>
				<li>Select Maysi Text File button to review Maysi Test Results</li>
				<li>Select Back button to return to previous page.</li>
			</ul>	
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%>
<div class='spacer'></div>
<%-- BEGIN TABS ALIGNMENT TABLE  --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN JUVENILE PROFILE TABS TABLE  --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END JUVENILE PROFILE TABS TABLE  --%>	
<%-- BEGIN JUVENILE TABS GREEN BORDER TABLE --%>						  	
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
  					<div class="spacer"></div>
<%-- BEGIN INTERVIEW INFO TABS TABLE --%>  					
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="mentalhealthtab"/>
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
				  				<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
			  				</tr>
						</table>
<%-- END INTERVIEW INFO TABS TABLE --%> 
<%-- BEGIN INTERVIEW INFO TABS BLUE BORDER TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
<%-- BEGIN MENTAL HEALTH TABS TABLE --%> 									
									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
													<tiles:put name="tabid" value="maysi"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
											</td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>
<%-- END MENTAL HEALTH TABS TABLE --%> 
	           						<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|190">
<%-- END MENTAL HEALTH TABS RED BORDER TABLE --%>                  
			            			<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
			            				<tr>
			            					<td valign="top" align="center">
			    								<div class="spacer"></div>
<%-- BEGIN ASSESSMENT BLUE BORDER TABLE --%> 			    								
			              						<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
				              					  	<tr>
				              							<td>
<%-- BEGIN ASSESSMENT DETAILS LIST TABLE --%>					              							
				              								<table width='100%' border="0" cellpadding="2" cellspacing="1">
				       											<logic:empty name="mentalHealthForm" property="previousMAYSIResults">
				   												  	<tr><td class="detailHead" align="center" colspan=6>No previous MAYSI assessments.</td></tr>
				   												</logic:empty>
	
				  												<logic:notEmpty name="mentalHealthForm" property="previousMAYSIResults">
													               <tr class="detailHead" valign='top'>   
					         						     				<td><bean:message key="prompt.assessment"/> <bean:message key="prompt.date"/></td>
					         						     				<td><bean:message key="prompt.referralNumber"/></td>
					         						     				<td><bean:message key="prompt.testAge"/></td>
					         						     				<td><bean:message key="prompt.location"/> Unit</td>
					         						     				<td><bean:message key="prompt.typeOfFacility"/></td>
					         						     				<td><bean:message key="prompt.assessment"/> <bean:message key="prompt.status"/></td>
					         						     				<td><bean:message key="prompt.notAdministeredReason"/></td>
					         						  				</tr>
						                                    <!-- Begin Pagination Header Tag-->
	    															<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"/></bean:define>
	      															<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
	    																	   		 maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
	      													   		<input type="hidden" name="pager.offset" value="<%= offset %>">
	        																	<!-- End Pagination header stuff -->
	       															<logic:iterate id="maysiIndex" name="mentalHealthForm" property="previousMAYSIResults" indexId="index">
															<!-- Begin Pagination item wrap -->																										
		    									                     	<pg:item>
																			<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">	
		    																	<td>
																		  			<a href="/<msp:webapp/>displayMAYSIDetails.do?assessmentId=<bean:write name='maysiIndex' property='assessmentId' />&subAssessId=<bean:write name='maysiIndex' property='subAssessId' />&maysiDetailId=<bean:write name='maysiIndex' property='maysiDetailId' />">
		              																	<bean:write name="maysiIndex" property="assessDate" formatKey="date.format.mmddyyyy"/>
		              																</a>
																				</td>
		       																	<td><bean:write name="maysiIndex" property="referralNumber" /></td>	
		       							    									<td><bean:write name="maysiIndex" property="testAge" /></td>
		       							    									<td><bean:write name="maysiIndex" property="locationUnit" /></td>
		       							    									<td><bean:write name="maysiIndex" property="facilityType" /></td>
		       							    									<td><bean:write name="maysiIndex" property="assessmentOption" /></td>
		       							    									<logic:notEmpty name="maysiIndex" property="reasonNotDone">
		       							    										<td style="background:yellow;"><bean:write name="maysiIndex" property="reasonNotDone" /></td>
		       							    									</logic:notEmpty>
		       							    									<logic:empty name="maysiIndex" property="reasonNotDone">
		       							    										<td><bean:write name="maysiIndex" property="reasonNotDone" /></td>
		       							    									</logic:empty>
		       							    									
		       							    									
		       																</tr>
																	 	</pg:item>
	      													   <!-- End Pagination item wrap -->
		  						                           			</logic:iterate>
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
			       												</logic:notEmpty>						
			                      							</table>
<%-- END ASSESSMENT DETAILS LIST TABLE --%>			                      							
			                      							<div class="spacer"></div>
			               								</td>
			               							</tr>	
													<tr>
														<td>			               							
<%-- BEGIN BUTTON TABLE --%>
					                      					<table border="0" width="100%">
																<tr>
					                      							<td align="center">
					                     						    	<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_MAYSIN_U%>'>
					                     						      		<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
					                     						      		<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
					                     						      			<html:submit property="submitAction" onclick="spinner();"><bean:message key="button.newAssessment" /></html:submit>
					                     						      		</logic:notEqual>
					                     						      		<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																				<html:submit property="submitAction"><bean:message key="button.newAssessment" /></html:submit>
																			</jims2:isAllowed>
																			</logic:equal>
					                     						      		</logic:equal>	
					                     						      		<%--removed disabled="true"  for maysi text file ER:11125 --%>
					                                 					</jims2:isAllowed>
					                                 					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_MAYSI_TEXT_V%>'>
					                                 						<html:submit property="submitAction"><bean:message key="button.maysiTextFile" /></html:submit>
					                                 					</jims2:isAllowed>
					                            			    		<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					                						  		</td>
					                							</tr>
					                						</table>
<%-- END BUTTON TABLE --%>
														</td>
													</tr>	
			               						</table>
<%-- END ASSESSMENT BLUE BORDER TABLE --%> 
												<div class="spacer"></div>
			               					</td>
			               				</tr>
		              				</table>
<%-- END MENTAL HEALTH TABS RED BORDER TABLE --%> 		              				
									<div class="spacer"></div>
		         				</td>
		        			</tr>
		        		</table>
<%-- BEGIN INTERVIEW INFO TABS BLUE BORDER TABLE --%> 		        		
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
<%-- END JUVENILE TABS GREEN BORDER TABLE --%>			
			<div class="spacer"></div>
		</td>
	</tr>
</table>
<%-- END TABS ALIGNMENT TABLE  --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>