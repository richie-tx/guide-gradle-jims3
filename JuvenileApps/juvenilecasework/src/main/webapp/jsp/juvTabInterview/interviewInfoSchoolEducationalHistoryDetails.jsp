<!DOCTYPE HTML>
<%-- User clicks the "Add School History" button from previous page --%>
<%--MODIFICATIONS --%>
<%-- 06/23/2005	Hien Rodriguez	Create JSP --%>
<%-- 04/26/2012	C Shimek		#73326 revised Grade Repeated prompts --%>
<%-- 05/7/2012	D Gibler		#73387 Added home school --%>
<%-- 11/06/2012	C Shimek		#74552 added coding to display specified school name and address --%>
<%-- 08/6/2015  R Young      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
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
<title><bean:message key="title.heading"/> - interviewInfoSchoolEducationalHistoryDetails.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script> 

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0" >
<html:form action="/processJuvenileSchool" target="content">
<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|177">
</logic:equal>
<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|180">
</logic:equal>
<logic:notEqual name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">
	<logic:notEqual name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|179">
	</logic:notEqual>
</logic:notEqual>  
<input type="hidden" name="backFlowIndicator" value="" />         
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - 
			<logic:notEqual name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">
				<logic:notEqual name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">
					Create School History Information Summary
				</logic:notEqual>
			</logic:notEqual>
			<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">
				Create School History Information Confirmation
			</logic:equal>
			<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">
			 	School History Details
			</logic:equal>
		</td>
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<logic:notEqual name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">  	
			<td>
				<ul>
					<li>Review information and select Finish to add School information.</li>	  		
					<li>Select Back button to return to Create School Information page.</li>
				</ul>
			</td>
		</logic:notEqual>
		<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">
			<td align="center" class="confirm">School Information successfully added</td>
		</logic:equal>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN GREEN TABS TABLE --%>  		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
						<tiles:put name="juvenileNum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />							
						</tiles:insert>	
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>
<%-- BEGIN GREEN TABS TABLE --%> 
<%-- BEGIN GREEN TABS BORDER TABLE --%> 			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align=center>
						<div class='spacer'></div>   
<%-- BEGIN BLUE TABS TABLE --%> 						       
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
							</tr>
						</table>
<%-- BEGIN BLUE TABS BORDER --%> 
<%-- BEGIN BLUE TABS BORDER TABLE --%> 			
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
<%-- BEGIN RED TABS TABLE --%> 
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
<%-- BEGIN RED TABS BORDER TABLE --%> 									
									<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
										<tr>
											<td valign="top" align="center">
												<div class="spacer"></div>																							
<%-- BEGIN SCHOOL INFO TABLE --%>
												<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr>
														<td colspan="4" class="detailHead"><bean:message key="prompt.youthInfo" /></td>
													</tr>
													<tr>
													<tr>
														<td align='center'>
															<table cellpadding='4' cellspacing='1' width='100%'>
															<tr>	
														<td class='formDeLabel' width='35%'>Was the youth or is the youth currently designated Special Education or is the youth currently receiving 504 services?</td>
														<td class='formDe'>
															<logic:equal name="juvenileSchoolHistoryForm" property="educationService" value="NO SE OR 504 SERVICES">
																NO SPECIAL EDUCATION OR 504 SERVICES
															</logic:equal>
															<logic:notEqual name="juvenileSchoolHistoryForm" property="educationService" value="NO SE OR 504 SERVICES" >
																<bean:write name="juvenileSchoolHistoryForm" property="educationService" />
															</logic:notEqual>
															
														</td>
														</tr>
														<tr>
														<td class='formDeLabel' width='35%'>Handicapping Condition</td>
														<td class='formDe'><bean:write name="juvenileSchoolHistoryForm" property="splEduCategoryDescription" /></td>														
														</tr>
														<tr>
															<td class='formDeLabel' colspan='1' nowrap><bean:message key="prompt.educationID" /></td>
															<td class='formDe'><bean:write name="juvenileSchoolHistoryForm" property="educationId"/></td>														
														</tr>
														<tr>
															<!-- Changes for JIMS200077276 Starts -->
															<td class="formDeLabel" width="1%" nowrap>HCJPD  <bean:message key="prompt.studentID" /></td>
															<td class=formDe width="40%"><bean:write name="juvenileSchoolHistoryForm" property="studentId"/></td>			    												
	    												</tr>
														</table>														
													</tr>
													
													<tr>
														<td colspan="4" class="detailHead">SCHOOL DETAILS</td>
													</tr>
														
													<tr>
														<td align='center'>
															<table cellpadding='4' cellspacing='1' width='100%'>
													<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">  	
														<tr>
														<td class='formDeLabel'><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.district" /></td>
														<td class='formDe' ><bean:write name="juvenileSchoolHistoryForm" property="schoolDistrictDescription"/></td>
															<td class='formDeLabel' width='1%' nowrap="nowrap"><bean:message key="prompt.entryDate" /></td>
															<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="entryDate" /></td>
														</tr>
													</logic:equal>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.name" /></td>
														<td class='formDe' width='40%' colspan='4'>
															<bean:write name="juvenileSchoolHistoryForm" property="schoolName"/>
															<logic:notEqual name="juvenileSchoolHistoryForm" property="instructionType" value="">:<bean:write name="juvenileSchoolHistoryForm" property="instructionType"/></logic:notEqual>		
														</td>
													</tr>
													<logic:equal name="juvenileSchoolHistoryForm" property="isAlternativeSchool" value="true">	
														<logic:equal name="juvenileSchoolHistoryForm" property="specificSchoolName"  value="">		
															<tr>	
																<td class='formDeLabel'>Home <bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.district" /></td>
																<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="homeSchoolDistrictDescription"/></td>
															</tr>	
															<tr>
																<td class='formDeLabel'>Home <bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.name" /></td>
																<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="homeSchoolDisplayLiteral"/></td>
															</tr>
														</logic:equal>
													</logic:equal>
													<logic:notEqual name="juvenileSchoolHistoryForm" property="specificSchoolName" value="">		
														<tr>	
															<td class='formDeLabel' valign="top">Specifed <bean:message key="prompt.name" /></td>
															<td class='formDe' colspan='4'>
																<bean:write name="juvenileSchoolHistoryForm" property="specificSchoolName"/>
																<div>
																	<bean:write name="juvenileSchoolHistoryForm" property="specificSchoolAddress"/>
																</div>
															</td>
														</tr>	
													</logic:notEqual>				
													<%-- <tr>
														<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails"> 
															<td class='formDeLabel'><bean:message key="prompt.enrollmentDate" /></td>
															<td class='formDe'  width="50%"><bean:write name="juvenileSchoolHistoryForm" property="lastAttendedDateString"/></td>
														</logic:equal> 
														<!-- Temp Fix until EducationId defect is fixed -->	
														<logic:notEqual name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">
															<td class='formDeLabel'><bean:message key="prompt.enrollmentDate" /></td>
															<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="lastAttendedDateString"/></td>
														</logic:notEqual>	
														<!-- Temp Fix until EducationId defect is fixed -->
													</tr> --%>	
													<tr>
													<tr>
														<td colspan="4" class="detailHead">ENROLLMENT AND PERFORMANCE DETAILS</td>
													</tr>
													
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.programAttending" /></td>
														<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="programAttendingDescription"/></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.enrollmentStatus" /></td>
														<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="exitTypeDescription"/></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.schoolAttendanceStatus" /></td>
														<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="schoolAttendanceStatusDescription"/></td>
													</tr>																																				
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.currentGradeLevel" /></td>	
														<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="gradeLevelDescription"/></td>
													</tr>
													<tr>
														<td class="formDeLabel" ><bean:message key="prompt.diamond" /> Academic Performance?</td>
														<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="academicPerformanceDesc"/></td>
													</tr>
													<logic:notEqual name="juvenileSchoolHistoryForm" property="reasonForGradeLevelChange" value="">
													<tr>
														<td  class='formDeLabel'>Reason for Lower Grade Level Change</td>
														<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="reasonForGradeLevelChange"/></td>
													</tr>	
													</logic:notEqual>																			
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.appropriateLevel?" /></td>								
														<td class='formDe' valign="top" colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="appropriateGradeLevelDescription"/></td>
													</tr>							
													<logic:equal name="juvenileSchoolHistoryForm" property="appropriateGradeLevelId" value="BEH">
														<tr>
															<td class='formDeLabel'><bean:message key="prompt.gradeRepeatTotal" /></td>
															<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="gradeRepeatTotal"/></td>
														</tr>							
														<tr>
															<td class='formDeLabel' width='1%' nowrap="nowrap"><bean:message key="prompt.mostRecentGradeRepeated" /></td>
															<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="gradesRepeatedDescription"/></td>
														</tr>
														<tr>	
															<td class='formDeLabel'><bean:message key="prompt.timesGradeRepeated" /></td>
															<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="gradesRepeatNumber"/></td>												
														</tr>																																	
													</logic:equal>
														<tr>	
															<td class='formDeLabel'><bean:message key="prompt.truancyHistory" /></td>
															<td class='formDe' colspan='4'>
															<logic:equal name="juvenileSchoolHistoryForm" property="truancy" value="true"><bean:message key="prompt.yes" /></logic:equal>
															<logic:equal name="juvenileSchoolHistoryForm" property="truancy" value="false"><bean:message key="prompt.no" /></logic:equal></td>												
														</tr>
													
													<logic:equal name="juvenileSchoolHistoryForm" property="schoolDistrictId" value="141">
													<tr>
														<td class="formDeLabel">Has the youth completed their <bean:message key="prompt.GED" />?</td>								
														<td class="formDe">
															<logic:equal name="juvenileSchoolHistoryForm" property="gedCompleted" value="true">
																<bean:message key="prompt.yes" />
															</logic:equal>
															<logic:equal name="juvenileSchoolHistoryForm" property="gedCompleted" value="false">
																<bean:message key="prompt.no" />
															</logic:equal>
														</td>				
													</tr>
													<logic:equal name="juvenileSchoolHistoryForm" property="gedCompleted" value="true">						
					    							<tr>								
														<td class="formDeLabel"><bean:message key="prompt.completionDate" /></td>
														<td class="formDe"><bean:write name="juvenileSchoolHistoryForm" property="completionDateStr" /></td>
													</tr>
													</logic:equal>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.GEDAwarded" /></td>
														<td class="formDe">
															<logic:equal name="juvenileSchoolHistoryForm" property="awarded" value="true">
																<bean:message key="prompt.yes" />
															</logic:equal>
															<logic:equal name="juvenileSchoolHistoryForm" property="awarded" value="false">
																<bean:message key="prompt.no" />
															</logic:equal>
														</td>												
													</tr>
													<logic:equal name="juvenileSchoolHistoryForm" property="awarded" value="true">
						    							<tr>									
															<td class="formDeLabel"><bean:message key="prompt.GEDAwarded" /> <bean:message key="prompt.date" /></td>
															<td class="formDe"><bean:write name="juvenileSchoolHistoryForm" property="awardedDateStr" /></td>
														</tr>																															
													</logic:equal>
													</logic:equal>	
													<tr>
														<td colspan="4" class="detailHead">VERIFICATION DETAILS</td>
													</tr>
													
													<tr>	
														<td class="formDeLabel" width='10%'><bean:message key="prompt.diamond" /><bean:message key="prompt.howSchoolInformationVerified" />?</td>
														<td class='formDe'><bean:write name="juvenileSchoolHistoryForm" property="schoolInfoVerifiedByDesc"/></td>
														
														<td class='formDeLabel' width='10%'><bean:message key="prompt.verifiedDate" /></td>
														<td class='formDe' width="40%"><bean:write name="juvenileSchoolHistoryForm" property="verifiedDateString"/></td>	
													</tr>													
													<tr>
														<logic:equal name="juvenileSchoolHistoryForm" property="schoolInfoVerifiedBy" value="O"> 
															<td class='formDeLabel'>Other verification method</td>
															<td class='formDe' colspan='4'><bean:write name="juvenileSchoolHistoryForm" property="schoolVerfifyOther"/></td>
														</logic:equal> 
													</tr>
													
												</table>
<%-- END SCHOOL INFO TABLE --%>		
 												<div class='spacer'></div>										
<%-- BEGIN BUTTON TABLE --%>
												<table width="98%">	
													<tr>
														<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="details">  	
															<td align="center">
																<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>		  					
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">				
																<bean:message key="button.finish"></bean:message>
																</html:submit>	
																<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
															</td>
														</logic:equal>
														<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="confirm">  
															<td align="center">
																<html:submit property="submitAction"><bean:message key="button.returnToSchoolDetails"></bean:message></html:submit>	
															</td>		
														</logic:equal>
														<logic:equal name="juvenileSchoolHistoryForm" property="secondaryAction" value="viewdetails">  
															<td align="center">
																<html:submit property="submitAction"><bean:message key="button.returnToSchoolDetails"></bean:message></html:submit>	
															</td>		
														</logic:equal>
													</tr>
												</table>
												</td>
												</tr>
												</table>
<%-- END BUTTON TABLE --%>
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
<%-- END RED TABS BORDER TABLE --%>				
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
<%-- END BLUE TABS BORDER TABLE --%>						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<%-- END GREEN TABS BORDER TABLE --%>				
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>