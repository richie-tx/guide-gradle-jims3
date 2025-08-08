<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	JJose	Create JSP --%>
<%-- 07/10/2007	LDeen	Defect #43531-remove hyperlinks for Member # --%>
<%-- 11/14/2012	CShimek	#74585 added Parental Rights Terminated field --%>
<%-- 12/05/2012 CShimek #74731 added Primary Contact selection field  --%>

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<title><bean:message key="title.heading"/> - familyConstellationCreateSummary.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitFamilyConstellationDetailsCreate">

<logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|230">
</logic:notEqual>
<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|239">
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.create"/> <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/>
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
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="summary">
				<ul> 
					<li>Verify that information is correct and select Finish button to proceed.</li> 
					<li>If any changes are needed, select Back button.</li> 
					<li>Clicking on Member # displays member details.</li> 
				</ul>
			</logic:equal>
		</td> 
	</tr> 
</table> 

<table align="center" width="100%">
	<tr>
		<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="confirm">
			<td align="center" class="confirm">
				<bean:message key="prompt.family"/> <bean:message key="prompt.number"/>: <bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber"/>
				<br>
				<bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> 
				<bean:message key="prompt.successfully"/> <bean:message key="prompt.created"/>
			</td>
		</logic:equal>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table cellpadding="1" cellspacing="0" border="0" width="100%"> 
	<tr> 
		<td>
			<%--header info start--%> 
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
			<%--header info end--%> 
		</td> 
	</tr> 
</table> 
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE  Main Table Begin --%> 
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center"> 
	<tr> 
		<td> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0" > 
				<tr> 
					<td valign=top> 
						<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
						<%--tabs end--%> 
					</td> 
				</tr> 
				<tr> 
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td> 
				</tr> 
			</table> 
<%-- end green tabs --%> 
<%-- begin outer green border --%> 
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
				<tr> 
					<td valign="top" align="center">
						<div class='spacer'></div> 
						<table width="98%" border="0" cellpadding="0" cellspacing="0" > 
							<tr> 
								<td valign="top"> 
									<tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="ConstellationInfo" />
									</tiles:insert> 
								</td> 
    			        	</tr> 
               				<tr> 
                 				<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td> 
               				</tr> 
             			</table> 
<%-- end green tabs --%> 
<%--begin blue outer border --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue"> 
							<tr> 
								<td valign=top align=center>
									<div class='spacer'></div>
<%-- BEGIN Constellation Members List TABLE --%> 
									<table width='98%' cellspacing='0' cellpadding='2' class='borderTableBlue'> 
										<tr> 
											<td class="detailHead" valign=top><bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.members"/></td> 
										</tr> 
										<tr> 
											<td> 
												<table width='100%' cellspacing='1'> 
													<tr bgcolor='#cccccc'> 
														<td valign="top" class="subHead" nowrap="nowrap"><bean:message key="prompt.member"/> #</td> 
														<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
														<td valign="top" class="subHead"><bean:message key="prompt.guardian"/></td> 
														<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td>
														<td valign="top" class="subHead"><bean:message key="prompt.primaryCareGiver"/></td>
														<td valign="top" class="subHead"><bean:message key="prompt.dh"/></td>
														<td valign="top" class="subHead"><bean:message key="prompt.visit"/></td>																 
														<td valign="top" class="subHead"><bean:message key="prompt.parentalRightsTerminated"/></td> 
														<td valign="top" class="subHead"><bean:message key="prompt.inHomeStatus"/></td> 
														<td valign="top" class="subHead"><bean:message key="prompt.involvementLevel"/></td> 
													</tr> 
													<logic:iterate indexId="index" id="membersList" name="juvenileFamilyForm" property="currentConstellation.memberList">
														<logic:equal name="membersList" property="delete" value="false">
															<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																<td>
																	<logic:notEmpty name="membersList" property="suspiciousMember">
																		<logic:notEqual name="membersList" property="suspiciousMember" value="">
																			<bean:message key="prompt.suspiciousMember"/>
																		</logic:notEqual>
																	</logic:notEmpty>
																	<bean:write name="membersList" property="memberNumber" /> 
																</td>
																<td><bean:write name="membersList" property="memberName.formattedName" /></td>
																<td>
																	<logic:equal name="membersList" property="guardian" value="true"> Yes</logic:equal>
																	<logic:notEqual name="membersList" property="guardian" value="true"> No</logic:notEqual>
																	<logic:equal name="membersList" property="primaryContact" value="true"><img src='../../images/starBlueIcon.gif'></logic:equal>
																</td>
																<td><bean:write name="membersList" property="relationshipToJuv"/></td>
																<td>
																	<logic:equal name="membersList" property="primaryCareGiver" value="true"> Yes</logic:equal>
																	<logic:notEqual name="membersList" property="primaryCareGiver" value="true"> No</logic:notEqual>
																</td>
																<td>
																	<logic:equal name="membersList" property="detentionHearing" value="true"> Yes</logic:equal>
																	<logic:notEqual name="membersList" property="detentionHearing" value="true"> No</logic:notEqual>
																</td>
																<td>
																	<logic:equal name="membersList" property="detentionVisitation" value="true"> Yes</logic:equal>
																	<logic:notEqual name="membersList" property="detentionVisitation" value="true"> No</logic:notEqual>
																</td>
																<td>
																	<logic:equal name="membersList" property="parentalRightsTerminated" value="true"> Yes</logic:equal>
																	<logic:notEqual name="membersList" property="parentalRightsTerminated" value="true"> No</logic:notEqual>
																</td>
																<td>
																	<logic:equal name="membersList" property="inHomeStatus" value="true"> Yes</logic:equal>
																	<logic:notEqual name="membersList" property="inHomeStatus" value="true"> No</logic:notEqual>
																</td>
																<td nowrap><bean:write name="membersList" property="involvementLevel"/></td>
															</tr> 
														</logic:equal>
													</logic:iterate>
													<tr>
														<td class="detailHead" valign="top" colspan="10">Youth lives with (TJJD defined)</td>
													</tr>
													<tr>
														<td align="left"><bean:write name="juvenileFamilyForm" property="youthLivesWithId"/> - <bean:write name="juvenileFamilyForm" property="youthLivesWithDesc"/></td>
													</tr>
												</table>
											</td> 
										</tr> 
									</table>
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<logic:equal name="juvenileFamilyForm" property="secondaryAction" value="summary">
													<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
													<html:submit property="submitAction" styleId="conCreateSummaryFinish"><bean:message key="button.finish" /></html:submit> 
													<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
												</logic:equal>
												<logic:notEqual name="juvenileFamilyForm" property="secondaryAction" value="summary">
													<html:submit property="submitAction"><bean:message key="button.familyConstellationList" /></html:submit> 
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
<!-- end outer green --> 
					</td> 
				</tr> 
      		</table>
			<div class="spacer"></div>
      <!-- end outer green --> 
		</td> 
	</tr> 
</table>
<div class='spacer'></div>
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>