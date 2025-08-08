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
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/> 
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
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<title><bean:message key="title.heading"/>/facilityReleaseRecord.jsp</title>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileFacilityRelease" target="content">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="escapeView">	
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Escape Record</td>
		</tr>
	</table>
</logic:equal>	
<%-- END HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="returnEscapeView">	
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Return Escapee Record</td>
		</tr>
	</table>
</logic:equal>	
<%-- END HEADING TABLE --%>

<%-- END HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="releaseView">	
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Release Record</td>
		</tr>
	</table>
</logic:equal>	
<%-- END HEADING TABLE --%>


<%-- END TEMP RELEASE HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="tempReleaseView">	
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Temporary Release Record</td>
		</tr>
	</table>
</logic:equal>	
<%-- END TEMP RELEASE HEADING TABLE --%>

<%-- END TEMP RELEASE HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="retTempReleaseView">	
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Return Temporary Release Record</td>
		</tr>
	</table>
</logic:equal>	
<%-- END TEMP RELEASE HEADING TABLE --%>

<%-- END IN TRANSFER HEADING TABLE --%>
<logic:equal name="admitReleaseForm" property="action" value="inTransferView">	
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - In Transfer Record</td>
		</tr>
	</table>
</logic:equal>	
<%-- END IN TRANSFER HEADING TABLE --%>


<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
		<td valign="top" colspan="4">
           <tiles:insert page="../caseworkCommon/juvenileFacilityDetailsTile.jsp" flush="false">
	        	 <tiles:put name="detailsForm" beanName="juvenileBriefingDetailsForm" />	
	        </tiles:insert>
          </td>
       </tr> 
</table>

<div class='spacer'></div>

<!-- Observation Status -->
<tiles:insert page="../caseworkCommon/juvenileFacilityObservationStatusTile.jsp" flush="false">
   	 <tiles:put name="admitReleaseForm" beanName="admitReleaseForm" />	
</tiles:insert>
<!-- Observation Status -->

<div class='spacer'></div>
<%--Return information starts --%> 
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
	<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="E" op="equal">
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T" op="equal"/>
		<jims2:then>
			<div class='spacer'></div>
			<div class='spacer'></div> 
			<%--Return Information starts --%>
			<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
				<tr class='facDetailHead'>
					<td align='left' colspan='8' class='paddedFourPix'>Return Information</td>
				</tr>
				<tr>
					<%-- <html:hidden styleId="returnTime" name="admitReleaseForm" property="returnTime"/> --%>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.returnReason"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<html:select name="admitReleaseForm" property="returnReason" styleId="returnReason">
							<html:option key="select.generic" value="" />
							<html:optionsCollection property="returnReasons" value="code" label="descriptionWithCode"/> 				
						</html:select>
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.returnDate"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap> 
						<html:text name="admitReleaseForm" property="returnDate" styleId="returnDate" size="10" maxlength="10" size="10"/>
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.returnTime"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<input type="text" name="returnTime" id="returnTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="returnTime"/>"/>
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.returnStatus"/></td>
					<td class='formDe' width="15%" colspan='1' valign='top' nowrap><bean:write name="admitReleaseForm" property="returnStatus"/></td>
				</tr>
			</table> 
		</jims2:then>
	</jims2:if>
</logic:notEqual>
<%--Return  information ends --%>

<!-- Release Information starts -->
<jims2:if name="admitReleaseForm" property="action" value="releaseView" op="equal">
<jims2:or name="admitReleaseForm" property="action" value="inTransferView" op="equal"/>
<jims2:then>
<div class='spacer'></div>
<div class='spacer'></div>
	<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="P" op="equal">
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="D" op="equal"/>
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="V" op="equal"/>
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R" op="equal"/>
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="Q" op="equal"/>
		<jims2:then>
			<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
				<tr class='facDetailHead'>
					<td align='left' colspan='8' class='paddedFourPix'>Release Information</td>
				</tr>
				<tr>
					<html:hidden styleId="releaseReason" name="admitReleaseForm" property="releaseReason"/>
					<html:hidden styleId="facility" name="admitReleaseForm" property="detainedFacility"/>
					
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseReason"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseReasonDesc"/></td>
					
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseDate"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap> 
						<html:text name="admitReleaseForm" property="releaseDate" styleId="releaseDate" size="10" maxlength="10" size="10"/>
					</td>
					
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseTime"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<input type="text" name="releaseTime" id="releaseTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="releaseTime"/>" />
					</td>
				</tr>
				<tr>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedTo"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<html:select name="admitReleaseForm" property="releasedTo" styleId="releasedTo">
							<html:option key="select.generic" value="" />
							<html:optionsCollection property="releaseTo" value="code" label="descriptionWithCode"/> 				
						</html:select>
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedAuthority"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<input type="text" name="releaseAuthority" id="releaseAuthority" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="releaseAuthority"/>" />
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedBy"/></td>
					<td class='formDe' width="15%" colspan='1' valign='top' nowrap><input type="text" name="releasedBy" id="releasedBy" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="releasedBy"/>" /></td>
				</tr>
				<tr>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.outcome"/></td>
					<td class='formDe' width="15%" nowrap>
						<html:select name="admitReleaseForm" property="outcome" styleId="outcome">
							<html:option key="select.generic" value="" />
							<html:optionsCollection property="outcomes" value="code" label="description"/> 				
						</html:select>
					</td>
					<logic:equal name="admitReleaseForm" property="action" value="releaseView">
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.custodyPerson"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="prompt.custodylastName"/></td>
						<td class='formDe' width="15%" colspan='1' nowrap>
						<input type="text" name="custodylastName" id="custodylastName" size="45" maxlength="30" /><%-- value="<bean:write name="admitReleaseForm" property="custodylastName"/>" --%>
						</td>
						<td class='formDeLabel' colspan= '1' valign='top' align="right" width='1%' nowrap><bean:message key="prompt.custodyfirstName"/></td>
						<td class='formDe' width="15%" colspan='1' nowrap><input type="text" name="custodyfirstName" id="custodyfirstName" size="35" maxlength="30" /></td>
					</logic:equal>
					<logic:notEqual name="admitReleaseForm" property="action" value="releaseView">
						<td class='formDe' colspan='4'></td>						
					</logic:notEqual>
				</tr>
				<tr>
				<logic:equal name="admitReleaseForm" property="releaseReason" value="N">
						<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.transferToFacility"/></td>
						<td class='formDe' width="15%" colspan='5' nowrap>
							<html:select name="admitReleaseForm" property="transferToFacility" styleId="transferToFacility">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="facilities" value="code" label="descriptionWithCode"/> 				
							</html:select>
						</td>
					</logic:equal>
				</tr>
			</table> 
		</jims2:then>
	</jims2:if>
</jims2:then>
</jims2:if>
<!-- Release Information ends -->

<!-- Temp Release Information starts -->
<logic:equal name="admitReleaseForm" property="action" value="tempReleaseView">	
<div class='spacer'></div>
<div class='spacer'></div>
	<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="P" op="equal">
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="D" op="equal"/>
		<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="V" op="equal"/>
		<jims2:then>
			<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
				<tr class='facDetailHead'>
					<td align='left' colspan='8' class='paddedFourPix'>Temporary Release Information</td>
				</tr>
				<tr>
					<html:hidden styleId="releaseReason" name="admitReleaseForm" property="releaseReason"/>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseReason"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseReasonDesc"/></td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseDate"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap> 
						<html:text name="admitReleaseForm" property="releaseDate" styleId="releaseDate" size="10" maxlength="10" size="10"/>
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseTime"/></td>
					<td class='formDe' width="15%" colspan='3' nowrap>
						<input type="text" name="releaseTime" id="releaseTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="releaseTime"/>" />
					</td>
				</tr>
				<tr>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedTo"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<html:select name="admitReleaseForm" property="releasedTo" styleId="releasedTo">
							<html:option key="select.generic" value="" />
							<html:optionsCollection property="releaseTo" value="code" label="descriptionWithCode"/> 				
						</html:select>
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedAuthority"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap>
						<input type="text" name="releaseAuthority" id="releaseAuthority" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="releaseAuthority"/>" />
					</td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releasedBy"/></td>
					<td class='formDe' width="15%" colspan='3' valign='top' nowrap><input type="text" name="releasedBy" id="releasedBy" size="5" maxlength="5" value="<bean:write name="admitReleaseForm" property="releasedBy"/>" /></td>
				</tr>
				<tr>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.temporaryReleaseReason"/></td>
					<td class='formDe' width="15%" colspan=7' nowrap>
						<html:select name="admitReleaseForm" property="tempReleaseReason" styleId="tempReleaseReason">
							<html:option key="select.generic" value="" />
							<html:optionsCollection property="tempReleaseReasons" value="code" label="descriptionWithCode"/> 				
						</html:select>
					</td>
				</tr>
				<tr id="tempReleaseReasonOther1" class="hidden" nowrap>
					<td class='formDeLabel' colspan="8"><bean:message key="prompt.2.diamond"/>Other Temporary Release Reason Comments 
						<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
							<tiles:put name="tTextField" value="tempReleaseReasonOtherComments"/>
			                <tiles:put name="tSpellCount" value="spellBtn2" />
			            </tiles:insert>
			            (Max. characters allowed:50)
			        </td>					
				</tr>
				<tr id="tempReleaseReasonOther2" class="hidden">
			    	<td colspan='8' class='formDe'>
				    	<html:text name="admitReleaseForm" property="tempReleaseReasonOtherComments" styleId = "tempReleaseReasonOtherComments" style="width:50%"  />
			      	</td>
			    </tr>
			</table> 
		</jims2:then>
	</jims2:if>
</logic:equal>
<!--Temp Release Information ends -->

<%--Escape Information summary for return starts --%>
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
	<logic:equal name="admitReleaseForm" property="action" value="returnEscapeView">	
	<div class='spacer'></div>
	<div class='spacer'></div>
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='10' class='paddedFourPix'>Escape Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeStatus"/></td>
				<td class='formDe'  width="20%">
					<logic:equal  name="admitReleaseForm" property="escapeStatus" value="E">ESCAPED</logic:equal>
					<logic:equal  name="admitReleaseForm" property="escapeStatus" value="R">RETURNED</logic:equal>
				</td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="escapeDate"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="escapeTime"/></td>
				<tr>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeFrom"/></td>
					<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="escapeFromDesc"/>'><bean:write name="admitReleaseForm" property="escapeFrom"/></span></td>
					<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
					<td class='formDe' width="15%" colspan='3'  valign='top' nowrap><span title='<bean:write name="admitReleaseForm" property="outcomeDesc"/>'><bean:write name="admitReleaseForm" property="outcome"/></span></td>
				</tr>
			</tr>
		</table>	
	</logic:equal>
</logic:notEqual>
<%--Escape Information summary for return ends --%>
	
<%--Escape Information starts --%>	 
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
<logic:equal name="admitReleaseForm" property="action" value="escapeView">	
		<div class='spacer'></div>
		<div class='spacer'></div>
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='10' class='paddedFourPix'>Escape Information</td>
			</tr>
			<tr>
				<html:hidden styleId="escapeStatus" name="admitReleaseForm" property="escapeStatus"/>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.releaseReason"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="releaseReasonDesc"/></td>
							
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeStatus"/></td>
				<td class='formDe'  width="20%" valign="top">
					<logic:equal  name="admitReleaseForm" property="escapeStatus" value="E">ESCAPED</logic:equal>
					<logic:equal  name="admitReleaseForm" property="escapeStatus" value="R">RETURNED</logic:equal>
				</td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.escapeDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap> 
					<html:text name="admitReleaseForm" property="escapeDate" styleId="escapeDate" size="10"/>
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.escapeTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap>
					<input type="text" name="escapeTime" id="escapeTime" size="4" maxlength="4" value="<bean:write name="admitReleaseForm" property="escapeTime"/>" />
				</td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.escapeFrom"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap>
						<html:select property="escapeFrom" styleId="escapeFromId" name="admitReleaseForm">
							<html:option key="select.generic" value="" />
							<html:optionsCollection property="escapeCodes" value="code" label="descriptionWithCode"/>						
						</html:select>
				</td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.outcome"/></td>
				<td class='formDe' width="24%" colspan='4'  valign='top' nowrap><span title='<bean:write name="admitReleaseForm" property="outcomeDesc"/>'><bean:write name="admitReleaseForm" property="outcome"/></span></td>
			</tr>
		</table>
</logic:equal>
</logic:notEqual>
<%--Escape Information ends --%>
	
<%--Admission Information starts --%>
<logic:notEqual name="admitReleaseForm" property="facilityStatus" value="">	
	<div class='spacer'></div>
	<div class='spacer'></div>
		<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
			<tr class='facDetailHead'>
				<td align='left' colspan='10' class='paddedFourPix'>Admission Information</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facility"/></td>
				<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="detainedFacilityStr"/>'><bean:write name="admitReleaseForm" property="detainedFacility"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitReason"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="admitReasonStr"/>'><bean:write name="admitReleaseForm" property="admitReasonCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap>Secure Status</td>
				<logic:equal  name="admitReleaseForm" property="secureStatus" value="S">
		    		<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='SECURE'><bean:write name="admitReleaseForm" property="secureStatus"/></span></td>
		    	</logic:equal>
		    	<logic:equal  name="admitReleaseForm" property="secureStatus" value="N">
		    		<td class='formDe' width="15%" valign='top' colspan='1' nowrap><span title='NON-SECURE'><bean:write name="admitReleaseForm" property="secureStatus"/></span></td>
		    	</logic:equal>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitAuthorizedBy"/></td>
				<td class='formDe' colspan= '2' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="admitAuthority"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitDate"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitDateStr"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admitTime"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitTime"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.admittedBy"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="admitBy"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="petition"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingReferral"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="bookingReferral"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingOffense"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="bookingOffense"/>'><bean:write name="admitReleaseForm" property="bookingOffenseCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.bookingSupervision"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="bookingSupervisionNum"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralSource"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralSource"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentReferral"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="currentReferral"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentOffense"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><span title='<bean:write name="admitReleaseForm" property="currentOffense"/>'><bean:write name="admitReleaseForm" property="currentOffenseCd"/></span></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.currentSupervision"/></td>
				<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="currentSupervisionNum"/></td>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.referralOfficer"/></td>
				<td class='formDe' colspan= '3' valign='top' width='15%' nowrap><bean:write name="admitReleaseForm" property="referralOfficers"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.location"/></td>
				<td  colspan='10' class='formDe' nowrap> 
					<table>
						<tr>
							<td><bean:write name="admitReleaseForm" property="locationOneLabel"/><bean:write name="admitReleaseForm" property="floorLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="locationTwoLabel"/><bean:write name="admitReleaseForm" property="unitLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="locationThreeLabel"/><bean:write name="admitReleaseForm" property="roomLocation"/></td>
							<td><bean:write name="admitReleaseForm" property="multipleOccupancyUnit"/></td>
						</tr>
					</table> 
				</td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.reasonForChange"/></td>
				<td  colspan='9' class='formDe' nowrap><bean:write name="admitReleaseForm" property="reasonForChange"/></td>
			</tr>
			<tr>
				<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.escapeAttempts"/></td>
				<td class='formDe' width="15%" colspan='9' nowrap><bean:write name="admitReleaseForm" property="numOfEscapeAttempts"/></td>
			</tr>
			<logic:notEmpty name="admitReleaseForm" property="escapeComments">
			<tr id="escapeComments1">
				<td class='formDeLabel' colspan="10">Escape Attempts Comments
				         (Max. characters allowed:350)
			    </td>					
			</tr>
			<tr id="escapeComments2">
			   	<td colspan='10' class='formDe'><div style="width: 1100px; word-wrap: break-word"><bean:write name="admitReleaseForm" property="escapeComments"/></div></td>
			</tr>
			</logic:notEmpty>
			<tr id="admissionComments1">
				<td class='formDeLabel' colspan="8" nowrap><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityAdmissionComments.do?submitAction=Link&juvenileNum=<bean:write name="admitReleaseForm" property="juvenileNum"/>','comments',1000,800)"><bean:message key="prompt.facilityAdmissionComments"/></a>
					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
						<tiles:put name="tTextField" value="admissionComments"/>
		                <tiles:put name="tSpellCount" value="spellBtn3" />
		            </tiles:insert>
		            (Max. characters allowed:350)
		        </td>					
			</tr>
			<tr id="admissionComments2">
		    	<td colspan='8' class='formDeLabel'>
			    	<html:textarea name="admitReleaseForm" property="admissionComments" styleId = "admissionComments"  rows="4"  style="width:100%"/>
		      	</td>
		    </tr>
		</table>
</logic:notEqual>
<%--Admission Information ends --%>

<div class='spacer'></div>
<div class='spacer'></div>

<%--Detention hearing Information ends --%>
<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='8' class='paddedFourPix'>Detention Hearing Information</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.dateDetained"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="detainedDate" formatKey="date.format.mmddyyyy"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.nextHearingDate"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="nextHearingDate" formatKey="date.format.mmddyyyy"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.court"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="courtId"/></td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.facilitySequence"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap><bean:write name="admitReleaseForm" property="facilitySeqNum"/></td>
	</tr>
</table>
<%--Detention hearing Information ends --%>

<!-- Hidden fields starts -->
<html:hidden styleId="detainedDate" name="admitReleaseForm" property="detainedDate"/>
<html:hidden styleId="PIAStatus" name="admitReleaseForm" property="bookingRefPIAStatus"/>
<html:hidden styleId="placementType" name="admitReleaseForm" property="placementType"/>
<html:hidden styleId="admitDate" name="admitReleaseForm" property="admitDateStr"/>
<html:hidden styleId="admitTime" name="admitReleaseForm" property="detResp.admitTime"/> 

<input type="hidden" id="xReleaseDate" name="xReleaseDate" value='<bean:write name="admitReleaseForm" property="detResp.releaseDate" formatKey="date.format.mmddyyyy"/>'/> 
<input type="hidden" id="xEscapeDate" name="xEscapeDate" value='<bean:write name="admitReleaseForm" property="headerInfo.relocationDate" formatKey="date.format.mmddyyyy"/>'/> 

<html:hidden styleId="xReleaseTime" name="admitReleaseForm" property="detResp.releaseTime"/>
<html:hidden styleId="xEscapeTime" name="admitReleaseForm" property="headerInfo.relocationTime"/>
<!-- Hidden fields ends -->	

<br>
 <table border="0" width="100%">
	<TBODY>
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 		
				<html:submit property="submitAction" styleId="next"><bean:message key="button.next"/></html:submit>			
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</TBODY>
</table>
</html:form>

</body>
</html:html>