<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 11/14/2012	CShimek	#74585 added Parental Rights Terminated field --%>

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
<html:base />

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<title><bean:message key="title.heading" /> - familyConstellationUpdateSummary.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitFamilyConstellationDetailsUpdate">


<logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|238">
</logic:notEqual>
<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|239">
	<%-- BEGIN HEADING TABLE --%>
</logic:equal>	
	
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.update"/> <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> 
			 <logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm"><bean:message key="prompt.summary"/></logic:notEqual>
			 <logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm"><bean:message key="prompt.confirmation"/></logic:equal>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">
    <tr>
    	<td align="center" class="confirm">The following Family Constellation #
			  <bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber" /> has been successfully updated.
			</td>
    </tr>
	</logic:equal>

	<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="summary">
		<tr>
			<td>
  			<ul>
  				<li>Verify that information is correct and select Finish button to update Guardian.</li>
  				<li>If any changes are needed, select Back button to return to previous page.</li>
  			</ul>
			</td>
		</tr>
	</logic:equal>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="100%">
	<tr>
		<td>
		  <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert>
		</td>
	</tr>
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td><%-- begin green tabs (1st row) --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
				</tr>
			</table>
			<%-- end green tabs --%> 
			<%-- begin outer green border --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  			<tr>
	  				<td valign="top" align="center">
						  <%-- begin red tabs (3rd row) --%>
	  					<div class='spacer'></div>
	  					<table width="98%" border="0" cellpadding="0" cellspacing="0">
	 						<tr>
	 							<td valign="top">
	 							  	<tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
	 									<tiles:put name="tabid" value="ConstellationMembers" />
	 								</tiles:insert>
	 							</td>
	 						</tr>
	 						<tr>
	 							<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
	 						</tr>
	  					</table>
<%-- end red tabs --%>
<%--begin red outer border --%>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	    					<tr>
	    						<td valign="top" align="center">
		    						<nested:nest property="currentConstellation">
<%-- BEGIN Members List TABLE --%>
			    						<div class='spacer'></div>
			    						<table width="98%" cellspacing="0" cellpadding="2" class="borderTableBlue">
			   								<tr>
			   									<td class="detailHead" valign="top"><bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.members"/></td>
			   								</tr>
		    								<logic:empty name="juvenileFamilyForm" property="currentConstellation">
		   										<tr>
		   											<td  class="subHead">No Members Available</td>
		   										</tr>
		   									</logic:empty>

		   									<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation">
		   										<logic:empty name="juvenileFamilyForm" property="currentConstellation.memberList">
		   											<tr>
		   												<td  class="subHead">No Members Available</td>
		   											</tr>
		   										</logic:empty>
		   										<%int RecordCounter = 0;
		   										String bgcolor = "";%>
		   										<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
			        								<tr>
			        									<td>
				          									<table width="100%" cellspacing="1">
																<tr bgcolor="#cccccc">
																	<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td> 
																	<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
																	<td valign="top" class="subHead"><bean:message key="prompt.guardian"/></td> 
																	<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td> 
																	<td valign="top" class="subHead"><bean:message key="prompt.primaryCareGiver"/></td> 
																	<td valign=top class=subHead><bean:message key="prompt.dh"/> </td>
																	<td valign="top" class="subHead">Over 21?</td> 
																	<td valign=top class=subHead><bean:message key="prompt.visit"/> </td> 
																	<td valign=top class=subHead><bean:message key="prompt.parentalRightsTerminated"/> </td> 
																	<td valign="top" class="subHead"><bean:message key="prompt.inHomeStatus"/> </td> 
																	<td valign="top" class="subHead"><bean:message key="prompt.involvementLevel"/> </td> 
																</tr>
																<nested:iterate id="memberList" property="memberList" >
																	<logic:equal name="memberList" property="delete" value="false">
																		<tr class=<%RecordCounter++;
																			bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
				                  											out.print(bgcolor);%>
																			>
																			<td>
				    															<logic:notEmpty name="memberList" property="suspiciousMember">
				      																<logic:notEqual name="memberList" property="suspiciousMember" value="">
				      																		<bean:message key="prompt.suspiciousMember"/>
				      																</logic:notEqual>
				    															</logic:notEmpty>
			
				  																<a href='/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&amp;selectedValue=<bean:write name="memberList" property="memberNumber"/>'>
																					<bean:write name="memberList" property="memberNumber" /></a>
																			</td>
																			<td><bean:write name="memberList" property="memberName.formattedName" /></td>
																			<td>
																				<logic:equal name="memberList" property="guardian" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="guardian" value="true"> No</logic:notEqual>
																			</td>
																			<td><bean:write name="memberList" property="relationshipToJuv"/></td>
																			<td>
																				<logic:equal name="memberList" property="primaryCareGiver" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="primaryCareGiver" value="true"> No</logic:notEqual>
																			</td>
																			<td>
																				<logic:equal name="memberList" property="detentionHearing" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="detentionHearing" value="true"> No</logic:notEqual>
																			</td>
																			<td>
																				<logic:equal name="memberList" property="over21" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="over21" value="true"> No</logic:notEqual>
																			</td>
																			<td>
																				<logic:equal name="memberList" property="detentionVisitation" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="detentionVisitation" value="true"> No</logic:notEqual>
																			</td>
																			<td>
																				<logic:equal name="memberList" property="parentalRightsTerminated" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="parentalRightsTerminated" value="true"> No</logic:notEqual>
																			</td>
																			<td>
																				<logic:equal name="memberList" property="inHomeStatus" value="true"> Yes</logic:equal>
																				<logic:notEqual name="memberList" property="inHomeStatus" value="true"> No</logic:notEqual>
																			</td>
																			<td nowrap><bean:write name="memberList" property="involvementLevel"/></td>
																		</tr>
																	</logic:equal>
																</nested:iterate>
															</table>
														</td>
													</tr>
												</logic:notEmpty>
											</logic:notEmpty>
										</table>
									</nested:nest>
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="summary">
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit> 
													<html:submit property="submitAction" styleId="constellationSummaryFinish"><bean:message key="button.finish" /></html:submit> 
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</logic:equal>
												<logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="summary">
													<html:submit property="submitAction"><bean:message key="button.returnToConstellation" /></html:submit> 
												</logic:notEqual>
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class="spacer"></div>
								</td>
	  						</tr>
						</table>
						<div class="spacer"></div>
<%-- end outer red --%>
					</td>
				</tr>
			</table>
<%-- end outer green --%>
		</td>
	</tr>
</table>
<div class="spacer"></div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>