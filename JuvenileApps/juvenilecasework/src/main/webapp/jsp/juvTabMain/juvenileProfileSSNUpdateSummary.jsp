<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- Used for updating Juvenile Profile SSN --%>
<%-- 08/14/2017	 ugopinath  		Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- Changes for JIMS200077276 ends -->
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>
<!-- Changes for JIMS200077276 Starts -->
<%@ page import="naming.Features" %>
<!-- Changes for JIMS200077276 ends -->



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<html:base />

<title><bean:message key="title.heading" /> - juvenileProfileSSNUpdateSummary.jsp</title>

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>




</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<%--BEGIN FORM TAG--%>
<html:form action="/displayJuvenileSSNUpdateSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|5">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.SSN" /> <bean:message key="title.update" /> 
		<logic:equal name="juvenileProfileMainForm" property="action" value="confirmSSN"> Confirmation</logic:equal>
		<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmSSN"> Summary</logic:notEqual></td>
	</tr>
	<logic:equal name="juvenileProfileMainForm" property="action" value="confirmSSN">
	<tr>
		<td align="center" class="confirm">SSN successfully updated.</td>
	</tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
	<table width="98%" border="0" align="center">   
		<tr>
			<td>
				<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmSSN">
					<ul>
						 <li>Review information and select the Finish button.</li>
		        	 	 <li>Select the Back button to change information.</li>
						
					</ul>
				</logic:notEqual>
				 <logic:equal name="juvenileProfileMainForm" property="action" value="confirmSSN">
				      <ul>
				        <li>Select the Juvenile Profile Master Update button.</li>
				      </ul>				 
	 			</logic:equal>
			</td>
		</tr>
	</table>

<%-- END INSTRUCTION TABLE --%>

<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%--BEGIN JUVENILE PROFILE HEADER--%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader" />
</tiles:insert>
<%--END JUVENILE PROFILE HEADER--%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'><%-- BEGIN JUVENILE PROFILE TABS TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
 					<td valign='top'>
						<tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    						<tiles:put name="tabid" value="maintab" />
    					</tiles:insert>
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
<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>						
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign='top'><%--tabs start--%> 
											  <tiles:insert page="/jsp/caseworkCommon/interviewInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="profileInfo" />
  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  											</tiles:insert> <%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td>
<%-- BEGIN TAB BLUE BORDER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign='top' align='center'>
<%-- BEGIN MASTER INFO TABLE --%>
												<div class='spacer'></div>
	  											<table cellpadding='2' cellspacing='1' border='0' width="98%" class="borderTableBlue">
													<tr>
														<td valign='top' class='detailHead' colspan="4"><bean:message key="prompt.juvenileMasterInfo"/> - SSN Update</td>
													</tr>
													
													<tr>
														<td class='formDeLabel' valign="top" nowrap='nowrap' width="3%"><bean:message key="prompt.SSN" /></td>
														<td class='formDe' valign="top">
															<bean:write name="juvenileProfileMainForm" property="completeSSN.SSN1" />- 
															<bean:write name="juvenileProfileMainForm" property="completeSSN.SSN2" />- 
															<bean:write name="juvenileProfileMainForm" property="completeSSN.SSN3"/>															
														</td>														
													</tr>							
													
  												</table>
<%-- END MASTER INFO TABLE --%>
  												<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
	  											<table width="98%" border="0" align="center">
													<tr>
														<td align="center">
															<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmSSN">
																<html:button property="org.apache.struts.taglib.html.BUTTON" styleId="masterUpdateBack"><bean:message key="button.back" /></html:button>&nbsp;
																<html:submit property="submitAction" styleId="masterUpdateSubmit"><bean:message key="button.finish" /></html:submit>&nbsp;
																<input type="button" id="masterUpdateCancel" value="<bean:message key='button.cancel' />" />
															</logic:notEqual>
															<logic:equal name="juvenileProfileMainForm" property="action" value="confirmSSN">
																<html:submit property="submitAction" styleId="masterUpdateReturn">				
																	<bean:message key="button.returnToJuvenileProfileMasterUpdate"></bean:message>
																</html:submit>																
															</logic:equal>
														</td>
													</tr>
	        									</table>
<%-- END BUTTON TABLE --%>        									
	        									<div class='spacer'></div>
		        							</td>
    		    						</tr>
        		  					</table>
	  							</td>
  							</tr>
    					</table>
<%-- END TAB BLUE BORDER TABLE --%>    				
						<div class='spacer'></div>
					</td>
  				</tr>
    		</table>
<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
			<div class='spacer'></div>
    	</td>
	</tr>
</table>

<logic:equal name="juvenileProfileMainForm" property="matchDetectedSSN" value="true">
  <html:hidden name="juvenileMemberSearchForm" property="matchDetectedSSN" styleId="matchDetectedSSN" value="true"/>
</logic:equal>


<%-- END DETAIL TABLE --%>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>

<html:errors></html:errors>

</html:html>
