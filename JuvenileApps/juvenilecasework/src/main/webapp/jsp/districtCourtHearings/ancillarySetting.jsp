<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtAncillarySetting.js"></script>

<title><bean:message key="title.heading" />/ancillarySetting.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/submitJuvenileDistrictCourtAncillarySetting" target="content" styleId="ancillarySettingForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
					<logic:equal  name="courtHearingForm" property="action" value="ancillarySetting">
						<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Ancillary Court Add</h2></td> 
					</logic:equal>
					<logic:equal  name="courtHearingForm" property="action" value="ancillarySettingUpdate">
						<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Ancillary Court Update</h2></td>
					</logic:equal>
					<logic:equal  name="courtHearingForm" property="action" value="ancillaryUpdateFromCourtActivity">
						<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Ancillary Court Update</h2></td>
					</logic:equal>
			</tr>
		</table>
		 <!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
  
  <!-- END Error Message Table -->
		<br />
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<logic:equal  name="courtHearingForm" property="action" value="ancillarySetting">
							<li>All attributes are required.</li>						
							<li>To initiate a setting update, select the setting from the Ancillary Setting Display page.</li> 
						</logic:equal>
						<li>Court time must be in HHMM Format.</li>
						<li>Select Ancillary Setting Display button to view court/date setting records.</li>
					</ul>
				</td>
			</tr>
		</table>
		<%-- END INSTRUCTION TABLE --%>	
		
		<!-- Ancillary Add Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="ancillaryAddTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead">
							<td align='left' colspan="8" class='paddedFourPix'>&nbsp;</td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.court"/></td>
							<td class='formDe' colspan="3"><html:text name="courtHearingForm" property="courtId" styleId="courtId"  maxlength="10" size="10"/></td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date"/></td>
							<td class='formDe' colspan="3"><html:text name="courtHearingForm" property="courtDate" styleId="courtDate"  maxlength="10" size="10"/></td>
						</tr>
												<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.time"/></td>
							<td class='formDe' colspan="3"><html:text name="courtHearingForm" property="courtTime" styleId="courtTime"  maxlength="4" size="4"/></td>
						</tr>
						<tr>
							<td class='formDe' height="15" colspan="4"></td>
						</tr>
						<tr>	
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.petitionNumber"/></td>
							<td class='formDe' colspan="3"><html:text name="courtHearingForm" property="petitionNumber" styleId="petitionNumber"  maxlength="12" size="12"/></td>
						</tr>
						<tr>
							<td class='formDe' height="15" colspan="4"></td>
						</tr>
						<tr>	
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.typeCase"/></td>
			 				<td class='formDe'colspan='3' nowrap>
								<html:select name="courtHearingForm" property="typeCaseStr" styleId="typeCase">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="typeCase" value="code" label="descriptionWithCode"/> 				
								</html:select>
							</td>
						</tr>
						<tr>
							<td class='formDe' height="15" colspan="4"></td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.respondent"/></td>
							<td class='formDe' colspan="3">
							<html:text name="courtHearingForm" property="respondentName" styleId="respondentName"  maxlength="25" size="28"/>
							</b></td>
						</tr>
						<tr>
							<td class='formDe' height="15" colspan="4"></td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.settingReason"/></td>
							<td class='formDe'   colspan="3">
							<html:select name="courtHearingForm" property="hearingType" styleId="hearingType">
								<html:option key="select.generic" value=""/>
								<html:optionsCollection property="hearingTypes" value="code" label="descriptionWithCode"/> 				
							</html:select>
							</td>
						</tr>
						<tr>
							<td class='formDe' height="15" colspan="4"></td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.barNumber"/></td>
							<td class='formDe'><html:text name="courtHearingForm" property="barNumber" styleId="barNumber"  maxlength="8" size="8"/></b>
							<html:submit property="submitAction" styleId="validateBarNumberBtn"><bean:message key="button.validateBarNumber"/></html:submit>&nbsp;Or&nbsp;
							<html:submit  onclick="spinner()" property="submitAction"><bean:message key="button.searchAttorney" /></html:submit>
							</td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.aty"/></td>
							<td class='formDe'>	
								<html:radio name="courtHearingForm" property="attorneyConnection" value="HAT" styleId="attorneyConnectionHAT"/><b>Hired (HAT)&nbsp;</b>
								<html:radio name="courtHearingForm" property="attorneyConnection" value="AAT" styleId="attorneyConnectionAAT"/><b>Appointed (AAT)&nbsp;</b>
								<html:radio name="courtHearingForm" property="attorneyConnection" value="PDO" styleId="attorneyConnectionPDO"/><b>Public Defender (PDO)</b>&nbsp;</td>
								
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.attorney"/></td>
							<td class='formDe' colspan="3"><html:text name="courtHearingForm" property="attorneyName" styleId="attorneyName"  maxlength="50" size="65"/></b></td>
						</tr>
						<tr>
							<td class='formDe' height="15" colspan="4"></td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.caseFileDate"/></td>
							<td class='formDe' colspan="3">
								<html:text name="courtHearingForm" property="filingDate" styleId="filingDate"  maxlength="10" size="10"/></b></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- Ancillary Add Information ends-->
		<!-- Button Table Starts-->
				<br/>
		<table width="100%" border="0">
			<tr>
				<td align="center">
					<logic:equal  name="courtHearingForm" property="action" value="ancillarySetting">
					<html:submit property="submitAction" styleId="submitAncillarySettingAddBtn"><bean:message key="button.submit"/></html:submit> 
					</logic:equal>
					<logic:equal  name="courtHearingForm" property="action" value="ancillarySettingUpdate">
					<html:submit property="submitAction" styleId="updateSettingBtn"><bean:message key="button.update"/></html:submit>
					</logic:equal>
					<logic:equal  name="courtHearingForm" property="action" value="ancillaryUpdateFromCourtActivity">
					<html:submit property="submitAction" styleId="updateSettingBtn"><bean:message key="button.update"/></html:submit>
					</logic:equal>
<%-- 				<html:submit property="submitAction" styleId="ancillarySettingDisplayBtn"><bean:message key="button.ancillarySettingDisplay" /></html:submit> --%>
					<input onclick="spinner()" id="ancillarySettingDisplayBtn" type="button" name="ancillarySettingDisplayBtn"  value="<bean:message key='button.ancillarySettingDisplay'/>"/>
					<html:submit onclick="spinner()" property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu" /></html:submit>
				</td>
			</tr>
		</table>
		<!--  Button Table ends-->
		
		<!-- html hidden fields starts -->
	 	<html:hidden styleId="action" name="courtHearingForm" property="action"/>
	 	<html:hidden styleId="issueFlag" name="courtHearingForm" property="issueFlag"/>
	 	<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>
	 	<input type="hidden" id="hearingTypesList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="hearingTypesList"/>'/>
	 	<input type="hidden" id="hearingTypes" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="hearingTypes"/>'/>
		<!-- html hidden fields ends -->
		
	</html:form>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>