<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 03/28/2013 C Shimek    #74731 added primary contact indicator -- blue star  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
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

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<title><bean:message key="title.heading"/> - familyConstellationSummary.jsp</title>
</head> 
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayFamilyConstellationList">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|228">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="title.details"/>
 			<br><bean:message key="prompt.family"/> <bean:message key="prompt.number"/>: <bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber"/>
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
<table width="98%" border="0"> 
	<tr> 
		<td> 
			<ul> 
				<li>Clicking on Member # displays member details.</li> 
			</ul>
		</td> 
	</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding="1" cellspacing="0" border="0" width='98%'> 
	<tr> 
		<td>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
		</td> 
	</tr> 
</table> 
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE  Main Table Begin --%> 
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center"> 
	<tr> 
		<td> 
<%-- begin green tabs (1st row) --%> 
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
		<td bgcolor='#33cc66' height="5"></td> 
	</tr> 
</table> 
<%-- end green tabs --%> 

<%-- begin outer green border --%> 
<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> 
	<tr> 
		<td valign="top" align="center"> 
<%-- begin red tabs (3rd row) --%>
			<div class='spacer'></div>
<%-- BEGIN Constellation Members List TABLE --%> 
			<table width='98%' cellspacing="0" cellpadding="2" class="borderTableBlue"> 
				<tr> 
					<td class="detailHead" valign="top"><bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.members"/></td> 
				</tr> 
				<tr>
					<td> 
						<table width='100%' cellspacing="1"> 
							<tr bgcolor='#cccccc'> 
								<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td> 
								<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.guardian"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.primaryCareGiver"/></td> 
								<td valign="top" class="subHead"><bean:message key="prompt.inHomeStatus"/> </td> 
								<td valign="top" class="subHead"><bean:message key="prompt.involvementLevel"/> </td> 
							</tr> 
							<logic:iterate id="membersList" name="juvenileFamilyForm" property="currentConstellation.memberList" indexId="index">
								<logic:equal name="membersList" property="delete" value="false">
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
										<td>	
											<logic:notEmpty name="membersList" property="suspiciousMember">
												<logic:notEqual name="membersList" property="suspiciousMember" value="">
													<bean:message key="prompt.suspiciousMember"/>
												</logic:notEqual>
											</logic:notEmpty>								
											<a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name='membersList' property='memberNumber'/>&clearFamAction=true">
											<bean:write name="membersList" property="memberNumber"/></a>
										</td>
										<td>
											<bean:write name="membersList" property="memberName.formattedName" />
											<logic:equal name="membersList" property="primaryContact" value="true"> <img src='../../images/starBlueIcon.gif'></logic:equal>
										</td>
										<td>
											<logic:equal name="membersList" property="guardian" value="true"> Yes</logic:equal>
											<logic:notEqual name="membersList" property="guardian" value="true"> No</logic:notEqual>
										</td>
										<td><bean:write name="membersList" property="relationshipToJuv"/></td>
										<td>
											<logic:equal name="membersList" property="primaryCareGiver" value="true"> Yes</logic:equal>
											<logic:notEqual name="membersList" property="primaryCareGiver" value="true"> No</logic:notEqual> 
										</td>
										<td>
											<logic:equal name="membersList" property="inHomeStatus" value="true"> Yes</logic:equal>
											<logic:notEqual name="membersList" property="inHomeStatus" value="true"> No</logic:notEqual>
										</td>
										<td nowrap="nowrap"><bean:write name="membersList" property="involvementLevel"/></td>
									</tr> 
								</logic:equal>
							</logic:iterate>
						</table>
					</td> 
				</tr> 
			</table> 
<%-- END Constellation Members List TABLE --%> 
            <%int refCounterVal=0; %>
			<nested:iterate id="guardianList" name="juvenileFamilyForm" property="currentConstellation.guardiansList">
				<%refCounterVal++; %>
				<div class='spacer'></div>
				<table width='98%' cellspacing="0" cellpadding="2" border="0" class="borderTableBlue"> 
					<tr> 
						<td class="detailHead" valign="top"> <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.guardian"/> <bean:message key="prompt.information"/></td> 
					</tr>
					<tr>
						<td align="center">
							<table width='100%' cellspacing="1" cellpadding="4" border="0"> 
								<tr> 
									<td colspan="4"> 
<%-- Begin Member Inner Table 2 --%> 
										<table width='100%' cellspacing="1" class="borderTableGrey"> 
											<tr bgcolor='#cccccc'> 
												<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td> 
												<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
												<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td> 
												<td valign="top" class="subHead"><bean:message key="prompt.deceased"/></td> 
												<td valign="top" class="subHead"><bean:message key="prompt.incarcerated"/></td> 
											</tr> 
											<tr class="normalRow">
												<td valign="top"><bean:write name="guardianList" property="memberNumber"/></td> 
												<td valign="top">
													<bean:write name="guardianList" property="name.formattedName"/>
													<logic:equal name="guardianList" property="primaryContact" value="true"> <img src='../../images/starBlueIcon.gif'></logic:equal>
												</td> 
												<td valign="top"><bean:write name="guardianList" property="relationshipToJuv"/></td> 
												<td valign="top"><bean:write name="guardianList" property="deceased"/></td> 
												<td valign="top"><bean:write name="guardianList" property="incarcerated"/></td> 
											</tr> 
										</table> 
<%-- End Member Inner Table 2 --%> 
									</td> 
								</tr> 
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'>
								<tr>
									<td colspan="5">
										<bean:define id="refCounter">pChar<%=refCounterVal%></bean:define>
										<tiles:insert page="../caseworkCommon/employmentSummary.jsp" flush="false">
											<tiles:put name="employmentInfoList" beanName="guardianList" beanProperty="employmentInfoList"/>	
											<tiles:put name="rowIdHeader" beanName="refCounter"/>
										</tiles:insert>
									</td>
								</tr>
								<tr> 
									<td valign="top" class="detailHead" colspan="5"><bean:message key="prompt.family"/> <bean:message key="prompt.financialInformation"/> of <bean:message key="prompt.guardian"/></td> 
								</tr>
								<tr>
									<td valign="top" colspan="4">
										<tiles:insert page="../caseworkCommon/familyFinancialInfo.jsp" flush="false">
											<tiles:put name="familyFinancialInfo" beanName="guardianList" />	
										</tiles:insert>
									</td>
								</tr> 
								</jims2:isAllowed>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1"><bean:message key="prompt.notes"/></td>
									<td colspan="3" class="formDe"><nested:write property="notes" /></td>
								</tr>
							</table>
						</td> 
					</tr> 
<%-- End Family Constellation Table --%> 
				</table> 
			</nested:iterate>
			<div class='spacer'></div>                                
<%-- End Tab Blue Border Table --%> 
			<table width='98%' cellspacing="0" cellpadding="2" border="0" class="borderTableBlue"> 
				<tr> 
					<td valign="top" class="detailHead"><bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.dynamics"/></td> 
				</tr>
				<tr> 
					<td>
						<table width='100%' bgcolor="#cccccc" cellspacing="1"> 
							<logic:empty name="juvenileFamilyForm" property="currentConstellation.traitList">
								<tr bgcolor="#f0f0f0"> 
									<td colspan="5" class="subhead">No Traits Listed</td> 
								</tr>
							</logic:empty>
							<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.traitList">
								<tr bgcolor="#f0f0f0"> 
									<td width="10%" class="subhead"><bean:message key="prompt.entryDate"/></td> 
									<td class="subhead"><bean:message key="prompt.dynamic"/></td> 
									<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.level"/></td> 
									<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.status"/></td> 
									<td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.comments"/> </td> 
								</tr> 
                           
								<logic:iterate id="traitsList" name="juvenileFamilyForm" property="currentConstellation.traitList"  indexId="index2">
									<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
										<td valign="top"><bean:write name="traitsList" property="entryDate"/></td> 
										<td valign="top"><bean:write name="traitsList" property="traitDesc" /></td>
										<td valign="top"><bean:write name="traitsList" property="traitLevel" /></td>
										<td valign="top"><bean:write name="traitsList" property="traitStatus" /></td>
										<td><bean:write name="traitsList" property="traitComments" /></td>
									</tr> 
								</logic:iterate>
							</logic:notEmpty>
						</table> 
					</td> 
				</tr> 
			</table>
			<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
			<table border="0" width="100%">
				<tr>
					<td align="center">
						<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
					</td>
				</tr>
			</table>
<%-- END BUTTON TABLE --%>
			<div class="spacer"></div>
		</td> 
	</tr> 
</table> 
<div class='spacer'></div>
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>