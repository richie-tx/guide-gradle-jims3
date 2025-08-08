<!DOCTYPE HTML>
<%-- User selects the "School" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 06/23/2005	Hien Rodriguez	Create JSP --%>
<%-- 12/21/2005	C. Shimek   	Move District/School to col. 2 display in School History block ER#23912 --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/20/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek        #73565 added age > 20 check (juvUnder21) to Add School button --%>
<%-- 10/08/2012 C Shimek        #74395 added Community GED display --%>
<%-- 03/08/2013 C Shimek        #75009 added View hyperlinks for Education Tab  --%>
<%-- 03/28/2013 C Shimek        #75311 revised View hyperlinks to work with new Document Tab --%>
<%-- 08/6/2015  R Young      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - interviewInfoSchool.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<%@include file="../jQuery.fw"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#addSchoolHistory").click(function(){
		console.log("Add school history ");
		localStorage.removeItem("originalGradeLevel");
		localStorage.removeItem("originalSelectedGradeLevel");
		spinner();
	})
})
</script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.school"/> <bean:message key="prompt.details"/></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="juvenileTraitsForm" property="action" value="confirm">
		<tr>
			<td class="confirm">Trait(s) successfully added.</td>
		</tr>
		<br>
	</logic:equal>  
<%-- 	<logic:notEqual name="juvenileSchoolHistoryForm" property="confirmationMsg" value="">
		<tr>
			<td class="confirm"><bean:write name="juvenileSchoolHistoryForm" property="confirmationMsg" /></td>
		</tr>
		<br>
	</logic:notEqual>  --%>
	<tr>
		<td>
			<ul>
				<li>Select Trait Type, Trait Type Description, then click View button to see list.</li>
				<li>Select Add More Traits button to add more traits.</li>
				<li>Select Add School History button to add more school history information.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  		<td valign='top'>
<%-- BEGIN GREEN TABS TABLE --%>  		
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
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END GREEN TABS TABLE --%> 
<%-- BEGIN GREEN BORDER TABLE --%> 			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align="center">
						<div class="spacer"></div>	
<%-- BEGIN BLUE TABS TABLE --%> 											
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="SCHOOL" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
            					<td bgcolor='#6699ff' align="left">
            					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_DOC%>'>
            				<%-- 		&nbsp;<a href='/<msp:webapp/>displayJuvenileSchool.do?submitAction=Tab&action=View'>View School Details</a> <b>|</b>  --%>
            					    &nbsp;<a href='/<msp:webapp/>displayJuvenileProfileDocuments.do?submitAction=Link'>View Documents List</a> 
            					</td>
            					</jims2:isAllowed>
            				</tr>
            			
            			</table>
<%-- END BLUE TABS TABLE --%>
<%-- BEGIN BLUE BORDER TABLE --%> 
			   			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
<%-- END RED TABS TABLE --%>									
									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
													<tiles:put name="tabid" value="school"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
											</td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>   	
<%-- END RED TABS TABLE --%>
<%-- BEGIN RED BORDER TABLE --%> 
            						<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
            							<tr>
            								<td valign="top" align="center">
    											<div class="spacer"></div>
											 		<table width='100%' border="0" cellpadding="2" cellspacing="0">
														<tr>
															<td valign='top' align="center">
																<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|176">
																<logic:equal name="juvenileSchoolHistoryForm" property="action" value="view">					
<%-- BEGIN TRAITS TABLE --%>	
																	<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp"> 
																		<tiles:put name="actionPath" value="/handleJuvenileProfileTraits"/>
																	</tiles:insert>
<%-- END TRAITS TABLE --%>
																</logic:equal>
																<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
																<pg:pager index="center"  maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
											    					maxIndexPages="10"  export="offset,currentPageNumber=pageNumber" scope="request">
									    							<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
																<div class="spacer"></div>	
<%-- BEGIN SCHOOL HISTORY TABLE --%>
																<table width='98%' border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
																	<tr class='detailHead'>
																		<td colspan='6' align="left">
																			<a href="javascript:showHideMulti('schoolHist', 'sChar', 1, '/<msp:webapp/>')">
   																			<img border='0' src="/<msp:webapp/>images/contract.gif" name="schoolHist"/></a>
																			School History
																		</td>
																	</tr>
																	<tr id="sChar0" class="visibleTR">
																		<td>
																			<table width="100%" cellpadding="2" cellspacing="1">
																				<logic:empty name="juvenileSchoolHistoryForm" property="schoolHistory">
																					<tr class='alternateRow'>
																						<td>No School History Information Available</td>
																					</tr>
																				</logic:empty>
																				<%-- display detail header --%> 
																				<logic:notEmpty name="juvenileSchoolHistoryForm" property="schoolHistory">
																					<tr class="formDeLabel">
																						<td align="left"><bean:message key="prompt.entryDate" /></td>
																						<td align="left"><bean:message key="prompt.gradeLevel" /></td>
																						<td align="left"><bean:message key="prompt.districtSchool" /></td>
																						<td align="left"><bean:message key="prompt.appropriateLevel" /></td>
																						<td align="left"><bean:message key="prompt.enrollmentStatus" /></td>
																						<td align="left"><bean:message key="prompt.school" /> Services</td>																				
																					</tr>
<%-- display detail info --%>  
										   											<logic:iterate id="schoolIndex" name="juvenileSchoolHistoryForm" property="schoolHistory" indexId="index">
<%-- Begin Pagination item wrap --%>
																					<logic:notEqual name="schoolIndex" property="schoolDistrict" value="GED">
																						<pg:item>
																							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																								<td valign='top' align="left">
																									<a  onclick="spinner()" href="/<msp:webapp/>processJuvenileSchool.do?submitAction=View&schoolHistoryId=<bean:write name="schoolIndex" property="schoolHistoryId"/>">
																									<bean:write name="schoolIndex" property="createDate" format="MM/dd/yyyy" /></a>
																								</td>															
																								<td valign='top' align="left"><bean:write name="schoolIndex" property="gradeLevelDescription" /></td>
																								<td valign='top' align="left">
																								<logic:empty name="schoolIndex" property="specificSchoolName">
																									<bean:write name="schoolIndex" property="schoolDistrict" />/<bean:write name="schoolIndex" property="school" />
																									<logic:notEmpty name="schoolIndex" property="instructionType">:&nbsp;
																									    <bean:write name="schoolIndex" property="instructionType" />
																									</logic:notEmpty>
																								</logic:empty>     
																								<logic:notEmpty name="schoolIndex" property="specificSchoolName">
																									<bean:write name="schoolIndex" property="specificSchoolName" /> <!-- U.S #39059 -->
																								</logic:notEmpty>
																								</td>
																								<td valign='top' align="left"><bean:write name="schoolIndex" property="appropriateLevelDescription" /></td>
																								<td valign='top' align="left"><bean:write name="schoolIndex" property="exitTypeDescription" /></td>
																								<td valign='top' align="left"><bean:write name="schoolIndex" property="educationService" /></td>
																							</tr>
																						</pg:item>
																					</logic:notEqual>
<%-- End Pagination item wrap --%>
																					</logic:iterate>
																				</logic:notEmpty>	
																				<tr>
																					<td colspan="6" align="center">
																						<table>
																							<td align="center">
																								<pg:index>
																									<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																										<tiles:put name="pagerUniqueName" value="pagerSearch" />
																										<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
																									</tiles:insert>
																							 	</pg:index>
																							 </td>
																						</table> 	
																			    	</td>
																		    	</tr>
																	
																				<tr>
																					<td colspan="6" align="center">
																						<html:form action="/processJuvenileSchool" target="content">
																							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_SCL_U%>'>
																								<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">	
																								<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">			
																									<html:submit styleId="addSchoolHistory" property="submitAction"><bean:message key="button.addSchoolHistory" /></html:submit>
																								</logic:notEqual>
																								<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																								<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																									<html:submit styleId="addSchoolHistory" property="submitAction"><bean:message key="button.addSchoolHistory" /></html:submit>
																								</jims2:isAllowed>
																								</logic:equal>
																								</logic:equal>		
																							</jims2:isAllowed>
																						</html:form>
																					</td>
																				</tr>																	
																			</table>
<%-- END SCHOOL HISTORY TABLE --%>			
																		</td>
																	</tr>	
																</table>														
															</pg:pager>
															</td>
														</tr>
													</table> 
      												<div class="spacer"></div>
<%-- BEGIN GED PROGRAM TABLE --%>
													<table width='98%' border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
														<tr class='detailHead'>
															<td colspan='6' align="left">
														 		<a href="javascript:showHideMulti('GEDinfo', 'pChar', 1, '/<msp:webapp/>')">
   																<img border='0' src="/<msp:webapp/>images/contract.gif" name="GEDinfo"/></a>
																Community GED
															</td>
														</tr>
														<tr id="pChar0" class="visibleTR">
															<td colspan='6' align="left">
																<table width="100%" border="0" cellpadding="2" cellspacing="1">
																	<logic:empty name="juvenileSchoolHistoryForm" property="schoolHistory">
																		<tr class='alternateRow'>
																			<td colspan="6">No Community GED Information Available</td>
																		</tr>
																	</logic:empty>
																	<logic:notEmpty name="juvenileSchoolHistoryForm" property="schoolHistory">
																		<tr class="formDeLabel">
																			<td align="left"><bean:message key="prompt.entryDate" /></td>
																			<td align="left"><bean:message key="prompt.GED"/>	<bean:message key="prompt.program" /></td>	
																			<td align="left"><bean:message key="prompt.verifiedDate" /></td>
																			<td align="left"><bean:message key="prompt.enrollmentStatus" /></td>
																			<td align="left"><bean:message key="prompt.GED" />	<bean:message key="prompt.completed" /></td>	
																			<td align="left"><bean:message key="prompt.GEDAwarded"/></td>																			
																		</tr>
<%-- display detail info --%>  
										   								<logic:iterate id="pgmIndex" name="juvenileSchoolHistoryForm" property="schoolHistory" indexId="index2">
											   								<logic:equal name="pgmIndex" property="schoolDistrict" value="GED">
																				<tr class="<%out.print( (index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																					<td valign='top' align="left">
																					<a  onclick="spinner()" href="/<msp:webapp/>processJuvenileSchool.do?submitAction=View&schoolHistoryId=<bean:write name="pgmIndex" property="schoolHistoryId"/>">
																					<bean:write name="pgmIndex" property="createDate" format="MM/dd/yyyy" /></a>
																				</td>
																					<logic:equal name="pgmIndex" property="specificSchoolName" value="">
																						<td width="20%"><bean:write name="pgmIndex" property="programDesc" /></td>
																					</logic:equal>	
																					<logic:notEqual name="pgmIndex" property="specificSchoolName" value="">
																						<td><bean:write name="pgmIndex" property="specificSchoolName" /></td>
																					</logic:notEqual>
																					<td><bean:write name="pgmIndex" property="createDate" formatKey="date.format.mmddyyyy" /></td>
																					<td><bean:write name="pgmIndex" property="exitTypeDescription" /></td>
																					<td width="10%" nowrap="nowrap">
																						<logic:equal name="pgmIndex" property="gedCompleted" value="true"> 
																								<bean:message key="prompt.yes"/>-<bean:write name="pgmIndex" property="completionDate" formatKey="date.format.mmddyyyy" />
																						</logic:equal>
																						<logic:notEqual name="pgmIndex" property="gedCompleted" value="true"> 
																							<bean:message key="prompt.no"/>
																						</logic:notEqual>
																						
																					</td>
																					<td width="10%" nowrap="nowrap">
	
																						<logic:equal name="pgmIndex" property="gedAwarded" value="true"> 
																							<bean:message key="prompt.yes"/>-<bean:write name="pgmIndex" property="gedAwardedDate" formatKey="date.format.mmddyyyy" />
																						</logic:equal>
																						<logic:notEqual name="pgmIndex" property="gedAwarded" value="true"> 
																							<bean:message key="prompt.no"/>
																						</logic:notEqual>
																					</td>												    
																				</tr>
																				</logic:equal>
																		</logic:iterate>
																	</logic:notEmpty>	
																	<%-- <tr>
																		<td colspan="7" align="center">	
																			<html:form action="/processJuvenileSchool" target="content">
																		 		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_SCL_U%>'>
																					<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
																					<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">		
																						<html:submit property="submitAction"><bean:message key="button.addGEDProgram"/></html:submit>
																					</logic:notEqual>
																					<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																					<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																						<html:submit property="submitAction"><bean:message key="button.addGEDProgram"/></html:submit>
																					</jims2:isAllowed>
																					</logic:equal>
																					</logic:equal>		
																				</jims2:isAllowed> 
																			</html:form>
																		</td>
																	</tr>		 --%>															
																</table>
															</td>
														</tr>			
													</table>
<%-- END COMMUNITY GED TABLE --%>																
      												<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
													<table align="center">
													    <tr>
															<td>
															    <logic:notEqual name="juvenileSchoolHistoryForm" property="action" value="confirm">
												  					<td>
												  						<html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button>
												  					</td>
												  					<html:form action="/displayJuvenileMasterInformation" target="content">
												  						<td>
												  					   		<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												  					   	</td>
												  					</html:form>
												  					<html:form action="/processJuvenileSchool" target="content">
												  					<td>
												  						<html:submit property="submitAction"><bean:message key="button.PrintEnrollmentForm" /></html:submit>
												  					</td> 
												  					</html:form>
												 				</logic:notEqual>
															</td>
													    </tr>
												    </table>
<%-- END BUTTON TABLE --%>
												    <div class="spacer"></div>
											    </td>
	    									</tr>
	    								</table>
<%-- END RED BORDER TABLE --%>
	    								<div class="spacer"></div> 	
									</td>
								</tr>
						</table>
<%-- END BLUE BORDER TABLE --%> 						
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
<%-- END GREEN BORDER TABLE --%> 			
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>