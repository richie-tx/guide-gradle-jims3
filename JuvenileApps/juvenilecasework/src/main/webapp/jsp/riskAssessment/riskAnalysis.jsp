<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/13/2005		DWilliamson	Create Risk Analysis jsp--%>
<%-- 01/12/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 04/26/2011 Dawn Gibler		Implementation of multiple recommendations and scores --%>
<%-- 04/19/2012 C Shimek	    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 05/01/2012	C Shimek		#73346 Revise TJPC Risk button to TJJD Risk --%>
<%-- 07/16/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Create buttons --%>
<%-- 08/21/2012 C Shimek     	Corrected Cancel button invalid path error that occured on page 2 or higher, found while testing defect 74100--%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.RiskAnalysisConstants" %>




<head>
<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<html:base />
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- riskAnalysis.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type='text/javascript' src= " /<msp:webapp/>js/app.js"></script>
<script>
	$(document).ready(function(){
		 localStorage.removeItem('recommendationOverriddenDetention');
		 localStorage.removeItem('overRideType');
	})
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/handleReturn" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|359">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.riskAnalysisAssessment"/></td>
	</tr>
</table>
<%-- END HEADING TABLE --%> 
<logic:messagesPresent> <br />
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors></td>		  
	</tr>   	  
</table>
</logic:messagesPresent> 

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on hyperlinked Entry Date to view previous Risk Analysis Assessment.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<%-- BEGIN Program Referral History TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
			<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
						
<%-- BEGIN DETAIL TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
											<%--tabs start--%>
												<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="riskAnalysis"/>
													<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>	
											<%--tabs end--%>
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
												<table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr>
														<td class='detailHead'></td>
													</tr>
													<tr>
														<td>
															<table border='0' cellpadding='2' cellspacing='1' width='100%'>		
																<logic:notEmpty  name="riskAnalysisForm" property="assessmentList">								
																	<tr>
																		<td valign='top' class='formDeLabel' NOWRAP width="100"><bean:message key="prompt.entryDate"/></td>
																		<td valign='top' class='formDeLabel' NOWRAP width="100"><bean:message key="prompt.supervisionNumber"/></td> 
																		<td valign='top' class='formDeLabel' NOWRAP width="250"><bean:message key="prompt.part"/></td>
																		<td valign='top' class='formDeLabel' NOWRAP width="75">ID Number</td>
																		<td valign='top' class='formDeLabel'><bean:message key="prompt.recommendation"/></td>
																	</tr>
																</logic:notEmpty>

																<logic:empty  name="riskAnalysisForm" property="assessmentList">								
																	<tr>
																		<td valign='top' class='formDeLabel'>No Assessments Available</td>
																	</tr>
																</logic:empty>
																		
																<logic:iterate id="riskIndex" name="riskAnalysisForm" property="assessmentList" indexId="index">
													<%-- Begin Pagination item wrap --%>
																	<pg:item>
																		<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																			<td valign='top'>
																				<a href='/<msp:webapp/>displayRiskAssessmentDetails.do?assessmentId=<bean:write name="riskIndex" property="assessmentID"/>
																								&assessmentType=<bean:write name="riskIndex" property="assessmentType"/>
																								&effectiveDate=<bean:write name="riskIndex" property="effectiveDate"/>'>
																				<bean:write name="riskIndex" property="assessmentDate" formatKey="datetime.format.mmddyyyyHHmm"/></a> <%-- date.format.mmddyyyy --%>
																			</td>
																			<td>
																				<bean:write name="riskIndex" property="casefileId"/>
																			</td>
                                    
																			<logic:equal name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL%>">
																				<td valign='top'><%=naming.RiskAnalysisConstants.RISK_TYPE_PRE_COURT_STAFFING_REFERRAL_USE_NAME%></td>
																			</logic:equal>
																				
																			<logic:notEqual name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL%>">
																				<logic:equal name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL%>">
																					<logic:notEqual name="riskIndex" property="effectiveDate" value="true">
																						<td valign='top'><%=naming.RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL_USE_NAME%></td>
																					</logic:notEqual>
																					<logic:equal name="riskIndex" property="effectiveDate" value="true">
																						<td valign='top'>DETENTION SCREENING INSTRUMENT</td>
																					</logic:equal>
																				</logic:equal>
																				<logic:equal name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE%>">
																					<td valign='top'>
																						<logic:equal name="riskIndex" property="completed" value="true">
																							<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Completed" />
																						</logic:equal>
																						<%=naming.RiskAnalysisConstants.RISK_TYPE_NON_COURT_REFERRAL_USE_NAME%> 
																					</td>
																				</logic:equal>
                                    	
																				<logic:equal name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE%>">
																					<td valign='top'>
																						<logic:equal name="riskIndex" property="completed" value="true">
																							<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Completed" />
																						</logic:equal>
																						<%=naming.RiskAnalysisConstants.RISK_TYPE_NON_COURT_REFERRAL_USE_NAME%>
																					</td>
																				</logic:equal>
																						
																				<logic:notEqual name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL%>">
																					<logic:notEqual name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE%>">
																						<logic:notEqual name="riskIndex" property="assessmentType" value="<%=naming.RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE%>">
																							<td valign='top'><bean:write name="riskIndex" property="assessmentTypeDesc"/></td>
																						</logic:notEqual>
																					</logic:notEqual>
																				</logic:notEqual>
																						
																			</logic:notEqual>
                                    
																			<td valign='top'><bean:write name="riskIndex" property="assessmentID"/></td>
																					
																			<logic:empty name="riskIndex" property="recommendations">
																				<td valign='top'><bean:write name="riskIndex" property="recommendation"/></td>
																			</logic:empty>
																					
																			<logic:notEmpty name="riskIndex" property="recommendations">
																				<td valign='top'>
																					<logic:iterate id="recommendationsIndex" name="riskIndex" property="recommendations" indexId="index">
																						<bean:write name="recommendationsIndex" property="resultGroupDisplayDesc"/>
																						<logic:notEmpty name="recommendationsIndex" property="resultGroupDisplayDesc">
																						-- 
																						</logic:notEmpty>
																						<bean:write name="recommendationsIndex" property="riskAnalysisRecommendation"/>
																						<logic:notEqual name="riskIndex" property="recommendationsSizeMinusOne" value="<%=index.toString() %>">,</logic:notEqual>
																					</logic:iterate>
																				</td>
																			</logic:notEmpty>
																		</tr>
																	</pg:item>
													<%-- End Pagination item wrap --%>
																</logic:iterate>
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
<%-- END Program Referral History TABLE --%>
<%-- BEGIN BUTTON TABLE --%>
												<div class='spacer'></div>
												<table border="0" width="100%">
													<tr>
								                       <td align="center">
									                       	<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																<logic:equal name="riskAnalysisForm" property="allowUpdates" value="true">
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_DC_RISK_GANG_COPY%>'>
																			<input type="button" value="<bean:message key='button.mhScreen' /> " name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewGang.do?riskAssessmentType=MH SCREEN', true);"/>
																		</jims2:isAllowed>
																	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISKANA_U%>'>
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_DETENTION%>'>
																			<input type="button" value="<bean:message key='button.detention' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewReferral.do?isNewReferral=true', true);"/>
																		</jims2:isAllowed>
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_TJPC_COURT_REF%>'>
																			<input type="button" value="TJJD Risk" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewCourtReferral.do', true);"/>
																		</jims2:isAllowed>
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_COURT_REF%>'>
																			<input type="button" value="<bean:message key='button.preCourtStaffing' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewReferral.do?isNewReferral=false', true);"/>
																		</jims2:isAllowed>		                           
																	
																		<input type="button" value="<bean:message key='button.preaFollowUp' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewInterview.do', true);"/>
																		<input type="button" value="<bean:message key='button.testing' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewTesting.do', true);"/>      
																		<input type="button" value="<bean:message key='button.community' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewCommunity.do', true);"/>      
																		<input type="button" value="<bean:message key='button.residential' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewResidential.do', true);"/>
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISK_GANG%>'>
																		<input type="button" value="<bean:message key='prompt.gang' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewGang.do?riskAssessmentType=gang', true);"/>
																		</jims2:isAllowed>
																		<input type="button" value="<bean:message key='button.progress' />" name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewProgress.do?riskAssessmentType=progress', true);"/>
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_DC_RISK_PROGRESS_COPY%>'>
																			<input type="button" value="<bean:message key='button.residential' /> <bean:message key='button.progress' /> " name="submitAction" onclick="spinner(); javascript:changeFormActionURL('/<msp:webapp/>displayNewProgress.do?riskAssessmentType=RESIDENTIAL PROGRESS', true);"/>
																		</jims2:isAllowed>
																	</jims2:isAllowed>
																</logic:equal>
															</logic:equal>	
								                       </td> 
													</tr>
													<tr>
														<td align='center'>
															<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
															<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.returnToCasefile" /></html:submit>
															<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.cancel" /></html:submit>
														</td>
													</tr>
												</table>
<%-- END BUTTON TABLE --%>
											</td>
										</tr>
									</table>
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
<%-- END CASEFILE INFO TABS INNER TABLE --%>						
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

</html:form>
<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>