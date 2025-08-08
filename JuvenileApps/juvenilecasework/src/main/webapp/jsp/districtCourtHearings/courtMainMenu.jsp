<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtMainMenu.js"></script>

<html:base />
<title><bean:message key="title.heading"/>/courtMainMenu.jsp</title>

 <style>
    .w3-btn, .w3-btn-block {
    border: none;
    display: inline-block;
    outline: 0;
    padding: 6px 16px;
    vertical-align: middle;
    overflow: hidden;
    text-decoration: none !important;
    color: #fff;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    white-space: nowrap;
    width:100%
} 

.w3-btn-block {
    width: 100%;
}

.w3-dark-grey, .w3-hover-dark-grey:hover {
    color: #fff !important;
    background-color: #616161 !important;
} 

.w3-btn,.w3-hover-dark-grey {
    -webkit-transition: background-color .3s,color .15s,box-shadow .3s,opacity 0.3s;
    transition: background-color .3s,color .15s,box-shadow .3s,opacity 0.3s;
} 

[id^="submit"]{
	border: none;
    display: inline-block;
    outline: 0;
    padding: 6px 16px;
    vertical-align: middle;
    overflow: hidden;
    text-decoration: none !important;
    color: #fff !important;;
    background-color: #000;
    text-align: center;
    cursor: pointer;
    white-space: nowrap;
    width:100%;
  	color: #fff !important;
    background-color: #616161 !important;
}
</style> 
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/processJuvenileDistrictCourtHearings" target="content">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Court Main Menu</h2></td>
	</tr>
</table>
<br/>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->
<br/>
<table width="98%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
	<tr class='crtDetailHead'>
		<td align='left' colspan="8" class='paddedFourPix'>&nbsp;</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqCourtId"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.courtId"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>	
			<html:text name="courtHearingForm" property="courtId" styleId="courtId" maxlength="3" size="3"/>
		</td>
	    <td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqDate"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.date"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<html:text name="courtHearingForm" property="courtDate" styleId="courtDate"  maxlength="10" size="10"/>
		</td> 
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqJuvNum"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.juvNo"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<html:text name="courtHearingForm" property="juvenileNumber" styleId="juvNum" maxlength="14" size="14"/>
		</td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqRef"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.refNo"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<html:text name="courtHearingForm" property="referralNumber" styleId="refNum"  maxlength="4" size="4"/>
		</td> 
	</tr>
</table>
<br>
<table border="0" width="98%" align="center">
	<tr>
	<br/>
		<td align="right" width="45%">
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTDCKTDISP%>'>
				<html:submit property="submitAction"  styleId="submitCourtDocket" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.courtDocketDisplay" /></html:submit>
			</jims2:isAllowed>
		</td>
		<td align="left" width="55%">
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTN%>'>
				<html:submit property="submitAction"  styleId="submitCourtAction" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.courtAction" /></html:submit>
			</jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td align="right" width="45%">
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTANCIADD%>'>
			<html:submit property="submitAction"  styleId="submitAncillarySettingAdd" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.addAncillarySetting"/></html:submit>
			</jims2:isAllowed>
		</td>
		<td align="left" width="55%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTANCIDISP%>'>
			<html:submit property="submitAction"  styleId="submitAncillarySettingDisp" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.displayAncillarySetting" /></html:submit>
		</jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td align="right" width="45%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTINISETTNG%>'>
			<html:submit property="submitAction"  styleId="submitInitialSetting" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.initialSetting"/></html:submit>
		</jims2:isAllowed>
		</td>
		<td align="left" width="55%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTPETUPDATE%>'>
			<html:submit property="submitAction"  styleId="submitPetitionUpdate" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.petitionUpdate" /></html:submit>
		</jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td align="right" width="45%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTMASBRIEFG%>'>
			<html:submit property="submitAction"  styleId="submitRefDisp" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.masterBriefing" /></html:submit>
		</jims2:isAllowed>
		</td>
		<td align="left" width="55%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTREFINQ%>'>
			<html:submit property="submitAction"  styleId="submitReferralSearch" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.referralInquiry" /></html:submit><br />
		</jims2:isAllowed>
		</td>
		
	</tr>
	<tr>
		<td align="right" width="45%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTNAMESEAR%>'>
			<html:submit property="submitAction"  styleId="submitNameSearch" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.nameSearch" /></html:submit>
		</jims2:isAllowed>
		</td>
		<td align="left" width="55%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTANCILACTVTY%>'>
			<html:submit property="submitAction"  styleId="submitAncillaryCrtAct" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.ancillaryCourtActivity" /></html:submit>
		</jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td align="right" width="40%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTNUMINQ%>'>
			<button class="w3-btn-block  w3-dark-grey" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;"  id="submitNumberInquiry">
				NUMBER INQUIRY(ENTER: NUMBER IN &quot;JUV NO&quot;) & TYPE-IN REF) 
			</button>
		</jims2:isAllowed>
		</td>
		<td align="left" width="60%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTVTYYOUTH%>'>
			<button class="w3-btn-block  w3-dark-grey" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;"  id="submitYouthCrtAct">
			<%-- <html:submit property="submitAction"  styleId="submitYouthCrtAct" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.courtActivityByYouth" /></html:submit> --%>
			COURT ACTIVITY BY YOUTH (ENTER NUMBER IN &quot;JUV NO&quot;) 
			</button>				
		</jims2:isAllowed>
		</td>
	</tr>
	<tr>
		<td align="right" width="40%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTPETCJIS%>'>
		<html:submit property="submitAction"  styleId="submitPetCJIS" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.PetCJISSearch" /></html:submit>
			<!-- <button class="w3-btn-block  w3-dark-grey" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;"  id="submitPetCJIS">
				PETITION/CJIS SEARCH 
			</button> -->
		</jims2:isAllowed>
		</td>	
		<td align="left" width="60%">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTDALOGNUM%>'>			
			<html:submit property="submitAction"  styleId="submitDALogNum" style="width: 100%; font-size: 15px; text-align: left; padding: 5px 5px;" ><bean:message key="button.DALognum" /></html:submit>						
		</jims2:isAllowed>
		</td>	
	</tr>
</table>
<table border="0" width="100%" align="left">
	<tr>
		<td align="right" width="43%">
		</td>
		<td align="left" width="57%">
		<br/>
			<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="goNav('/appshell/displayHome.do')">
  				<bean:message key="button.cancel"></bean:message>
  			</html:button>
		</td>
	<tr>
</table>
<html:hidden styleId="action" name="courtHearingForm" property="action"/>
<input type="hidden" id="holidaysList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="holidaysList"/>'/>
</html:form>
</body>
</html:html>