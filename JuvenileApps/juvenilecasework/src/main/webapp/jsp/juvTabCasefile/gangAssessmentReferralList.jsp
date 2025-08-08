<!DOCTYPE HTML>
<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/21/2005		DWilliamson	Create JSP --%>
<%-- 07/20/2009     C Shimek    #61004 added timeout.js  --%>
<%-- 07/20/2013     C Shimek    #61004 started changes but put on hold --%>
<%-- 09/12/2013     C Shimek    #76047 made changes for Trait Status Update  --%>
<%-- 10/25/2013     C Shimek    #76302 commented out logic tag for gangsForm confirmation message --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>




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
	<html:base/>
	
	<%-- Javascript for emulated navigation --%>
	<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- gangAssessmentReferralList.jsp</title>
	
	<!--JQUERY FRAMEWORK-->
	<%@include file="../jQuery.fw"%>
	
	<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/gangReferrals.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
	<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
</head>
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/handleGangAssessmentReferralCreate" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Assessment Referral List</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class="spacer4px" />

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select referral date to view assessment document/details.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 

<br>

<%-- BEGIN DETAILS TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN PROFILE/GREEN TABS TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END PROFILE/GREEN TABS TABLE --%>

<%-- BEGIN GREEN TABS BORDER TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>	
<%-- BEGIN BLUE TABS TABLE --%>	
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="/jsp/caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
    									<tiles:put name="tabid" value="assessmentReferral"/>
    									<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
    								</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
					  	   </tr>
            			</table>
<%-- END BLUE TABS TABLE --%>	
<%-- BEGIN BLUE TABS BORDER TABLE --%>									
						<table width="98%" align="center" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
									<table width='98%' border="0" cellpadding="6" cellspacing="0" class="borderTableBlue">
										<tr> 		<!-- table title bar -->
					       					<td valign='top' colspan='6' class='detailHead'>Gang Assessment Referral</td>
					       				</tr>
										<tr>
											<td>
												<%-- Begin Pagination Header Tag --%>
													<bean:define id="paginationResultsPerPage" type="java.lang.String">
					  				 					<bean:message key="pagination.recordsPerPage"></bean:message>
													</bean:define> 
													<pg:pager index="center"  maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
													<input type="hidden" name="pager.offset" value="<%= offset %>">
																								
											   		<table width='100%' cellpadding="6" cellspacing="1">
								                 		<tr bgcolor='#cccccc' class='subhead' width="10%">
								                 			<td></td>
															<td><bean:message key="prompt.referralDate"/></td>
															<td><bean:message key="prompt.assessmentType"/></td>
															<td><bean:message key="prompt.IDNumber"/></td>
															<td><bean:message key="prompt.personMakingReferral"/></td>
															<td><bean:message key="prompt.recommendation"/></td>
															<td><bean:message key="prompt.status"/></td>
														</tr>
														</logic:notEmpty>
														<logic:empty name="assessmentReferralForm" property="gangAssessmentRefList" >
															<tr>
																<td colspan="6">No Gang Assessment Referral Information Available</td>
															</tr>
														</logic:empty>
														<logic:iterate id="gangAssessmentRefIndex" name="assessmentReferralForm" property="gangAssessmentRefList" indexId="index">
															<%-- Begin Pagination item wrap--%>
															<pg:item>
																<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow");%>">
																	<logic:equal name="index" value="0">
																		<logic:equal name="assessmentReferralForm" property="status" value="PENDING">
																			<td>
																				<jims2:isAllowed requiredFeatures="<%=Features.JCW_CF_GANGASSESS_U%>">
																					<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">  
																						<input type="radio" name="updateAssessment" value='<bean:write name="gangAssessmentRefIndex" property="assessmentIDNumber"/>'/>
																					</logic:equal>
																				</jims2:isAllowed>
																			</td>
																		</logic:equal>
																		<logic:notEqual name="assessmentReferralForm" property="status" value="PENDING">
																			<td></td>
																		</logic:notEqual>
																	</logic:equal>
																	
																	<logic:notEqual name="index" value="0">
																		<td></td>
																	</logic:notEqual>
																	<td>
																		<a href="javascript:newCustomWindow('/<msp:webapp/>displayGangAssessmentReferralList.do?submitAction=<bean:message key="button.view"/>&assessId=<bean:write name="gangAssessmentRefIndex" property="assessmentIDNumber"/>')"><bean:write name="gangAssessmentRefIndex" property="referralDate" formatKey="date.format.mmddyyyy"/></a>
																	</td>
																	<td><bean:write name="gangAssessmentRefIndex" property="assessmentType"/></td>
																	<td><bean:write name="gangAssessmentRefIndex" property="assessmentIDNumber"/></td>
																	<td><bean:write name="gangAssessmentRefIndex" property="personMakingReferral"/></td>
																	<td><bean:write name="gangAssessmentRefIndex" property="recommendations"/></td>
																	<td><bean:write name="gangAssessmentRefIndex" property="acceptedStatus"/></td>
																</tr>
															</pg:item>
														</logic:iterate>
													</table>
													
													<%-- Begin Pagination navigation Row--%>
													<table align="center">
														<tr>
															<td><pg:index>
																<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																	<tiles:put name="pagerUniqueName" value="pagerSearch" />
																	<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
																</tiles:insert>
															</pg:index></td>
														</tr>
													</table>
													</pg:pager>
													<%-- End Pagination navigation Row--%>
													<div class="spacer"></div>
													<table align="center">
														<tr>
															<td>
									  							<jims2:isAllowed requiredFeatures="<%=Features.JCW_CF_GANGASSESS_U%>">  
																	<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
																		<logic:equal name="assessmentReferralForm" property="status" value="PENDING">
																			<html:submit property="submitAction" disabled="true"styleId="updateBtn"><bean:message key="button.updateAssessment" /></html:submit>
																		</logic:equal>
																	</logic:equal>	
																</jims2:isAllowed> 
									  						</td>
									  						<td>
									  						<jims2:isAllowed requiredFeatures="<%=Features.JCW_CF_GANGASSESS_C%>"> 
										  						<logic:notEqual name="assessmentReferralForm" property="status" value="PENDING">
										  					 		<td><html:submit property="submitAction"><bean:message key="button.createGangAssessmentRef"/></html:submit></td>
										  					 	</logic:notEqual>
									  					 	</jims2:isAllowed> 
														  </td>
											    		</tr>
													</table>
												</td>
											</tr>
											<div class="spacer"></div>
								 		</table> 
								 		<div class="spacer"></div>
								 	</td>
								</tr>
							</table>
							<div class="spacer"></div>	
							<table align="center">   
								 <tr>
			  						<td><html:button property="button.back" styleId="gangRefListBack"><bean:message key="button.back"/></html:button></td>
			  				 		<td><input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do')"></td>
				    			</tr>
							</table>
				   			<div class="spacer"></div>
<%-- END GREEN TABS BORDER TABLE --%>						
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
<%-- END DETAIL TABLE --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>