<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



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

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - familyConstellationGuardianView.jsp</title>
</head>
<%--END HEADER TAG--%>


<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayFamilyConstellationGuardianUpdateAction">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|270">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/>  <bean:message key="prompt.constellation"/> 
			 <bean:message key="prompt.guardian"/> <bean:message key="prompt.details"/> 
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
			<logic:notEmpty name="juvenileFamilyForm" property="nextGuardian">
			<li>Press Next to see any additional guardians </li>
			</logic:notEmpty>
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_FFC_U%>'>
				<li>Press Update to update this guardian </li>
			</jims2:isAllowed>
		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
<div class='spacer'></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="100%">
	<tr>
		<td >
		  <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  			<tiles:put name="headerType" value="profileheader" />
  		</tiles:insert>
	  </td>
	</tr>
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE  Main Table Begin --%>
<div class='spacer'></div>
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
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
		</table>

		<%-- end green tabs --%> <%-- begin outer green border --%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			<tr>
				<td valign="top" align="center">
			   <%-- begin red tabs (3rd row) --%>
  		 		<div class='spacer'></div>
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<tiles:insert page="../caseworkCommon/constellationInfoTabs.jsp" flush="true">
									<tiles:put name="tabid" value="FamilyFinancial" />
								</tiles:insert>
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
						</tr>
				</table>

								
				<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
						<tr>
							<%-- Be sure to include the autoTab.js --%>
							<td valign="top" align="center">

							<%-- BEGIN Family constellation List TABLE -Table 4 Begin--%>
							<nested:nest property="currentGuardian">
							  <div class='spacer'></div>
								<table width="98%" cellspacing="0" cellpadding="4" border="0" class="borderTableBlue">
										<tr>
											<td align="left" class="detailHead" colspan="4">Family Constellation Guardian Information</td>
										</tr>
										<tr>
											<td colspan="4"><%--BEGIN Member 1 Inner Table --%>
											<table width="100%" cellspacing="1" class="borderTableGrey">
													<tr bgcolor="#cccccc">
														<td valign="top" class="subHead"><bean:message key="prompt.member"/> #</td> 
                                						<td valign="top" class="subHead"><bean:message key="prompt.name"/></td> 
                                						<td valign="top" class="subHead"><bean:message key="prompt.relationship"/></td> 
                                						<td valign="top" class="subHead"><bean:message key="prompt.deceased"/></td> 
													</tr>
													<tr class="normalRow">
														<td valign="top"><nested:write property="memberNumber" /></td>
														<td valign="top"><nested:write property="name.formattedName" /></td>
														<td valign="top"><nested:write property="relationshipToJuv" /></td>
														<td valign="top"><nested:write property="deceased" /></td>
													</tr>
											</table>
											<%--END Member 1 Inner Table --%></td>
										</tr>
						    	    <tr>
										<td align="center">
                           					<tiles:insert page="../caseworkCommon/employmentSummary.jsp" flush="false">
                            					<tiles:put name="employmentInfoList" beanName="juvenileFamilyForm" beanProperty="currentGuardian.employmentInfoList"/>	
                            					<tiles:put name="rowIdHeader" value="pChar0"/>
                          					</tiles:insert>
              				  	 		</td>
              				  		</tr>
										<tr>
											<td align="center">
											<table width="100%" cellpadding="2" cellspacing="1">
												<%-- BEGIN Family Financial Info --%>
													<tr>
														<td valign="top" class="detailHead" colspan="4"><bean:message key="prompt.family"/>
																<bean:message key="prompt.financialInformation"/> of <bean:message key="prompt.guardian"/>
														</td>
													</tr>
													<tr>
													   <td valign="top" colspan="4">
                                <tiles:insert page="../caseworkCommon/familyFinancialInfo.jsp" flush="false">
                                <tiles:put name="familyFinancialInfo" beanName="juvenileFamilyForm" beanProperty="currentGuardian" />	
                                </tiles:insert>
                                </td>
                             </tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1"><bean:message key="prompt.notes"/></td>
														<td colspan="3" class="formDe"><nested:write property="notes" />
														</td>
													</tr>
											</table>
											<%-- End Inner Table 1 --%></td>
										</tr>
								</table>
							</nested:nest>

											<%-- BEGIN BUTTON TABLE --%>
											<div class="spacer"></div>
											<table border="0" width="100%">
												<tr>
													<td align="center">
														<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
															<logic:notEmpty name="juvenileFamilyForm" property="nextGuardian">
																<html:submit property="submitAction"><bean:message key="button.next"/></html:submit> 
  														</logic:notEmpty>		
  														<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_FFC_U%>'>				
																	<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
  														</jims2:isAllowed>
															<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
														</td>
													</tr>
											</table>
											<div class="spacer"></div>
											<%-- END BUTTON TABLE --%>
											</td>
										</tr>
								</table>
								<div class='spacer'></div>
								</td>
							</tr>
					</table>
					<div class='spacer'></div>
					<%-- end outer red --%>
		</td>
	</tr>
</table>
<%-- end outer green --%>

<div class='spacer'></div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

