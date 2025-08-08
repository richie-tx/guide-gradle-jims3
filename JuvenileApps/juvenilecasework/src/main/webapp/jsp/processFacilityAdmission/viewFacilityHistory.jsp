<%-- added for #51734 --%>

<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
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

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<html:base />
<title><bean:message key="title.heading" /> - viewFacilityHistory.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>


<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casefileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvFacilityHistory.js"></script>
</head>

<html:form action="/displayJuvenileFacilityHistory">
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Process Juvenile Facility - Juvenile Profile - Facility History List</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
   <tr>
     <td>
  	  <ul>
    	<li>Enter the required field and then select submit button to search.</li> 
     </ul>	
	</td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%-- SEARCH BY JUVENILE NUMBER --%>

<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
  <tr>
     <td class="facDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
     <td class="facDetailHead"><html:text property="juvenileNum" size="8" maxlength="8" styleId="juvenileNum"/></td>
  </tr>
</table>


<%-- BEGIN BUTTON TABLE --%>
<br>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
		    <html:submit property="submitAction"  styleId="submitBtnFacilityHistory"> <bean:message key="button.submit" /></html:submit>&nbsp;
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>

<br></br>
<logic:notEmpty name="juvenileProfileHeader" property="juvenileNum">
	<table align="center" cellpadding="1" cellspacing="0" border="0" width="98%">
		<tr>
			<td bgcolor='#cccccc'>
		        <table align="center" width="100%" border="0" cellspacing="1">
		        	<tr class="facDetailHead">
		            	<td class="formDeLabel" colspan="3" nowrap><bean:message key="prompt.profile" /> <bean:message key="prompt.info" />&nbsp;&nbsp;</td>
		            	<td class="formDeLabel" colspan="1" style="text-align: right;"><a href="javascript:openWindow('/JuvenileCasework/displayGuardianInfo.do?juvnum=<bean:write name="juvenileProfileHeader" property="juvenileNum"/>')">View Guardian Info</a></td>
		            </tr>
			  		<tr>
			  			<td class="headerLabel" width="16%"><bean:message key="prompt.juvenile" />&nbsp;#</td>
			  			<td class="headerData" 	width="27%"><logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><font color="red"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></font></logic:equal>
			  			<logic:notEqual name="juvenileProfileHeader" property="restrictedAccess" value="true"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></logic:notEqual></td>
			  				<td class="headerLabel" width="16%">
			  					<bean:message key="prompt.juvenileName" /> 			  					
			  				</td>
			  				<td class="headerData"><bean:write name="juvenileProfileHeader" property="juvenileName"/> 
			  					<logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><span title='RESTRICTED ACCESS'><font color="red">(RESTRICTED)</font></span></logic:equal>
			  					&nbsp;
							  	<logic:equal name="juvenileProfileHeader" property="traitTypeId" value="MER">
							  		<span style="color: #8417A0; font-weight: bold;" title="<bean:write name="juvenileProfileHeader" property="traitTypeDescription"/>">(M)</span>
							  	</logic:equal>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td class="headerLabel" nowrap><bean:message key="prompt.currentAge" /></td>
			  			<td class="headerData"><bean:write name="juvenileProfileHeader" property="age"/></td>
			  			<td class="headerLabel" nowrap><bean:message key="prompt.juvenile" /> <bean:message key="prompt.status" /></td>
			  			<td class="headerData"><bean:write name="juvenileProfileHeader" property="status"/>&nbsp;&nbsp;&nbsp;
				  			<logic:notEmpty name="juvenileProfileHeader" property="detentionFacilityId">
				    			<logic:notEqual name="juvenileProfileHeader" property="detentionFacilityId" value="">
					    			<bean:write name="juvenileProfileHeader" property="detentionFacilityId"/>:
					    			<bean:write name="juvenileProfileHeader" property="detentionStatusId"/>
				    			</logic:notEqual>
				  			</logic:notEmpty>
			  			</td>
			  		</tr>
			  	</table>
			</td>
		</tr>
	</table>	
	<br/></br>
	<table align="center" width="98%" border="0"  cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="facDetailHead">Facility History List</td>
		</tr>
		<tr>
			<td>
				<table cellpadding="1" cellspacing="1" border="0" width='100%'>
					<logic:empty name="admitReleaseForm" property="facilityHistoryList">
						<tr bgcolor='#cccccc'>
							<td colspan="5" class='subHead'>No Facility History Available</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="admitReleaseForm" property="facilityHistoryList">
						<tr bgcolor='#cccccc'>
							<td class='subHead' width='1%' nowrap="nowrap" align="left"><bean:message key="prompt.referral" /></td>
							<td class='subHead' nowrap="nowrap" align="left">(<bean:message key="prompt.plusSign" />)</td>
							<td class='subHead' width='1%' nowrap="nowrap" align="left"><bean:message key="prompt.facilityName" /></td>
							<td class='subHead' align="left"><bean:message key="prompt.admit" /><bean:message key="prompt.reason" /></td>	
							<td class='subHead' align="left"><bean:message key="prompt.offense" /></td>											
							<td class='subHead' align="left"><bean:message key="prompt.admitDateTime" /></td>
							<td class='subHead' align="left"><bean:message key="prompt.releaseDateTime" /></td>
							<td class='subHead' align="left"><bean:message key="prompt.releasedTo" /></td>
							<td class='subHead' align="left"><bean:message key="prompt.releasedBy" /></td>
							<td class='subHead' align="left"><bean:message key="prompt.releasedAuthority" /></td>
							
						</tr>
						<logic:iterate indexId="index" id="facilityHistory" name="admitReleaseForm" property="facilityHistoryList">
							<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
								<td><bean:write name="facilityHistory" property="referralNumber" /></td>
								<td><logic:notEmpty name="facilityHistory" property="originalAdmitDate">+</logic:notEmpty></td>
								<td>
									<span title='<bean:write name="facilityHistory" property="detainedFacilityDesc"/>'><bean:write name="facilityHistory" property="detainedFacility" /></span>
								</td>													
								<td>														
									<bean:write name="facilityHistory" property="admitReason" />-<bean:write name="facilityHistory"	property="secureStatus" />														
								</td>
								<td><span title="<bean:write name="facilityHistory" property="bookingOffenseDesc"/>"><bean:write name="facilityHistory" property="bookingOffenseCd"/></span></td>
								<td>
									<logic:notEmpty name="facilityHistory" property="admitDate">
										<bean:write name="facilityHistory" property="admitDate" formatKey="date.format.mmddyyyy"/>
										<bean:write name="facilityHistory" property="admitTime"  />
									</logic:notEmpty>
								</td>
								<td>
									<logic:notEmpty name="facilityHistory" property="releaseDate">
										<bean:write name="facilityHistory" property="releaseDate" formatKey="date.format.mmddyyyy"/>
										<bean:write name="facilityHistory" property="releaseTime" />
									</logic:notEmpty>
								</td>
								<td><logic:notEmpty name="facilityHistory" property="releaseTo"><span title="<bean:write name="facilityHistory" property="relToDesc"/>"><bean:write name="facilityHistory" property="releaseTo" /></span></logic:notEmpty></td>
								<td><span title="<bean:write name="facilityHistory" property="relByDesc"/>"><bean:write name="facilityHistory" property="releaseBy"/></span></td>
								<td><bean:write name="facilityHistory" property="releaseAuthorizedBy"/></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
			</table>
		</td>
	</table>
</logic:notEmpty>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
