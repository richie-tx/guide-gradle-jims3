<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 12/05/2012 C Shimek    #74731 added Primary Contact selection field  --%>
<%-- 03/28/2013 C Shimek    #74731 corrected validation set/pass member number when only 1 member(selected by default in onload) exist in constellation --%>
<%-- 04/01/2013 C Shimek	#75306 Revised page to not allow incarcerated member to be assigned as primary contact and/or guardian --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>
<title><bean:message key="title.heading"/> - familyConstellationGuardianSelection.jsp</title>
<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGuardian.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

</head> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/submitFamilyConstellationGuardianSelection">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|231">
<input type="hidden" name="primayContactMemberNumber" value="" id="pcMemNum"/>
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.guardian"/> <bean:message key="prompt.selection"/></td>	  	    	 
 	</tr>  	
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'><div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Members that are guardian(s) for this constellation. (maximum of two (2) guardians) </li>
				<li>At least one(1) member must be designated as guardian.</li>
				<li>At least one(1) and only one(1) member must be designated as primary guardian.</li>
			</ul>	
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<div class='spacer'><div>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding="1" cellspacing="0" border="0" width='100%'> 
	<tr> 
		<td> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
		</td> 
	</tr> 
</table>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<div class='spacer'><div>
<%-- BEGIN DETAIL TABLE  Main Table Begin --%> 
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td>
<%-- BEGIN GREEN TABS TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
	  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
	  					</tiles:insert>	
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
				</tr>
			</table>
<%-- END GREEN TABS TABLE --%>
<%-- BEGIN GREEN TABS BORDER TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class='spacer'><div>						
<%-- BEGIN BLUE TABS TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
								  <tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="GuardianInfo" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
							</tr>
						</table>
<%-- END BLUE TABS TABLE --%>
<%-- BEGIN BLUE TABS BORDER TABLE --%>
		  				<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class='spacer'><div>
<%-- BEGIN MEMBER LIST TABLE --%>									
									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
										<tr> 
											<td class="detailHead" valign="top"><bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.guardian"/>(s)</td>
										</tr>
										<tr>
											<td>
<%-- BEGIN MEMBER SELECTION TABLE --%>												
												<table width='100%' cellspacing="1" cellpadding="1">
													<logic:empty name="juvenileFamilyForm" property="currentConstellation">
				  										<tr bgcolor="#cccccc">
				  											<td colspan="6" class="subHead">No Members Available</td>
				  										</tr>
				  									</logic:empty>
				  									<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation">
				  										<logic:empty name="juvenileFamilyForm" property="currentConstellation.memberList">
				  											<tr bgcolor="#cccccc">
				  												<td colspan="6" class="subHead">No Members Available</td>
				  											</tr>
				  										</logic:empty>
				
														<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
															<tr bgcolor='#cccccc'>
																<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td> 
																<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
																<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.primaryCareGiver"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.dh"/></td>
																<td valign="top" class="subHead"><bean:message key="prompt.visit"/></td> 
																<td valign="top" class="subHead"><bean:message key="prompt.deceased"/></td> 
																<td valign="top" class="subHead"><bean:message key="prompt.incarcerated"/></td>
																<td valign="top" class="subHead" width='14%'><bean:message key="prompt.primary"/> <bean:message key="prompt.guardian"/></td>
																<td valign="top" class="subHead" width='1%'><bean:message key="prompt.guardian"/></td>
															</tr>
															<nested:nest property="currentConstellation">
																<nested:iterate id="memberList" property="memberList" indexId="index">
																	<logic:equal name="memberList" property="delete" value="false">
																		<bean:define name="memberList" property="detentionVisitationAsStr" id="currentDetVisit" type="java.lang.String"/>
																		<tr name="nestedMemberRow" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																			<td>
																				<logic:notEmpty name="memberList" property="suspiciousMember">
																					<logic:notEqual name="memberList" property="suspiciousMember" value="">
																						<bean:message key="prompt.suspiciousMember"/>
																					</logic:notEqual>
																				</logic:notEmpty>
																				<a href='/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&amp;selectedValue=<bean:write name="memberList" property="memberNumber"/>'>
																				<bean:write name="memberList" property="memberNumber" /></a>
																			</td>
																			<bean:define name="memberList" property="memberNumber" id="currentMemberId" type="java.lang.String"/>
																			<td><bean:write name="memberList" property="memberName.formattedName" /></td>
																			<nested:hidden property="relationshipToJuv" styleId='<%="relationship-" + currentMemberId%>'/>
																			<td valign="top"><bean:write name="memberList" property="relationshipToJuv" /></td>
																			
																			<td align="left">
																				<div>Yes<nested:radio property="primaryCareGiver" value="true" styleId='<%="primaryCareYES-" + currentMemberId%>'/>
																				No<nested:radio property="primaryCareGiver"  value="false" styleId='<%="primaryCareNO-" + currentMemberId%>'/></div>
																			
																			</td>
																			
																			<td valign="top">
																				<logic:equal name="memberList" property="detentionHearing" value="true"> YES</logic:equal>
																				<logic:notEqual name="memberList" property="detentionHearing" value="true"> NO</logic:notEqual>
																			</td>
																			<td valign="top">
																				<%-- <bean:write name="memberList" property="detentionVisitationAsStr" /></td> --%>
																			    <logic:equal name="memberList" property="detentionVisitation" value="true"> YES</logic:equal>
																				<logic:notEqual name="memberList" property="detentionVisitation" value="true"> NO</logic:notEqual>						
																			</td>
																			<td valign="top"><bean:write name="memberList" property="deceasedYesNo" /></td>
																			<td valign="top">
																				<bean:write name="memberList" property="incarceratedYesNo" />
																				<nested:hidden property="incarceratedYesNo" />
																			</td>
																			<td valign="top" align="left">
																				<logic:notEqual name="memberList" property="deceased" value="true">
																					<logic:notEqual name="memberList" property="incarcerated" value="true">
																						<nested:radio property="primaryContact" value="true" styleId='<%="primaryContact-" + currentMemberId%>'/>
																						<nested:hidden property="primaryContact" value="false" styleId='<%="primaryContactNO-" + currentMemberId%>'/>
																						<%-- <input type="radio" name="primaryContact" value=<bean:write name='memberList' property='primaryContact'/> id='<%="primaryContact-" + currentMemberId%>'> --%>
																					</logic:notEqual>	
																				</logic:notEqual>
																				<input type="hidden" name="memberNum" value=<bean:write name="memberList" property="memberNumber" /> />																					
																			</td>
																			<td align="center">
																				<input type="hidden" name="clearConstMemberGuardianCheckBoxes" value="" />											
																				<logic:notEqual name="memberList" property="deceased" value="true">
																					<logic:notEqual name="memberList" property="incarcerated" value="true">
																						<nested:multibox property="guardian" value="true" styleId='<%="guardian-" + currentMemberId%>'></nested:multibox>
																					</logic:notEqual>	
																				</logic:notEqual>
																			</td>
																		</tr>
																	</logic:equal>
				    									  		</nested:iterate>
															</nested:nest>
															<tr>
																<td class="detailHead" valign="top" colspan="10">Youth lives with (TJJD defined)</td>
															</tr>
															<tr>
															<td class='formDe' colspan="10"><bean:message key="prompt.2.diamond"/>
																<html:select name="juvenileFamilyForm" property="youthLivesWithId" styleId="youthLivesWith">
																	<html:option key="select.generic" value="" />
																	<html:optionsCollection property="youthLivesWith" value="code" label="descriptionWithCode"/> 				
																</html:select>
															</td>
															</tr>
														</logic:notEmpty>
							  						</logic:notEmpty>
												</table>
<%-- END MEMBER SELECTION TABLE --%>													
											</td>
										</tr>			
									</table>	
<%-- END MEMBER LIST TABLE --%>
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
												<html:submit property="submitAction" styleId="validateSelectionInputs"><bean:message key="button.next" ></bean:message></html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class="spacer"></div>
								</td>	
							</tr> 
						</table> 
<%-- END BLUE TABS BORDER TABLE --%>						
						<div class='spacer'><div> 
					</td> 
				</tr> 
			</table> 
<%-- END GREEN TABS BORDER TABLE --%>
		</td> 
	</tr> 
</table> 
<%-- END DETAIL TABLE  Main Table Begin --%> 
<div class='spacer'><div>
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>