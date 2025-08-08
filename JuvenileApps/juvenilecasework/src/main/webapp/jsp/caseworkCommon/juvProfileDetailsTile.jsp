<%-- 03/05/2007  CShimek	#39715 added logic tags around display name field --%>
<%-- 03/04/2013  CShimek	#75072 added Education Id field --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.Features" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<div>&nbsp;</div>
<table cellpadding=4 cellspacing=1 border=0 width='98%' class="borderTableBlue">
	<tr>
		<td valign=top class=detailHead colspan="4"><bean:message key="prompt.juvenileMasterInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.name"/></td>
		<logic:notEqual name="juvenileProfileMainForm" property="action" value="UPDATE">
			<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">				
				<td class=formDe colspan="3"><bean:write name="juvenileProfileHeader" property="juvenileName"/></td>
			</logic:notEqual>			
		</logic:notEqual>			
		<logic:equal name="juvenileProfileMainForm" property="action" value="UPDATE">
			<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="name"/></td>
		</logic:equal>			
		<logic:equal name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">				
			<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="name"/></td>
		</logic:equal>			
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.preferredFirstName"/></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="preferredFirstName"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="race"/></td>
		<td class=formDeLabel><bean:message key="prompt.sex"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="sex"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.hispanic"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="hispanicDescription"/></td>
		<td class=formDeLabel><bean:message key="prompt.multiracial"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="multiracialDescription"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="dateOfBirth"/></td>
		<td class=formDeLabel><bean:message key="prompt.verifiedDOB"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="verifiedDOBDescription"/></td>
	</tr>
	<tr>
    <td class=formDeLabel><bean:message key="prompt.birthCountry"/></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="birthCountry"/></td>
	</tr>
	<tr> 	
		<td class=formDeLabel><bean:message key="prompt.birthState"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="birthState"/></td>
		<td class=formDeLabel><bean:message key="prompt.birthCounty"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="birthCounty"/></td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.birthCity"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="cityId"/></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.usCitizen"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="isUSCitizen"/></td>
  </tr>

	<tr>
		<td class=formDeLabel><bean:message key="prompt.nationality"/></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="nationality"/></td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.ethnicity"/></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="ethnicity"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.primaryLanguage"/></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="primaryLanguage"/></td>
  </tr>
  <tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.secondaryLanguage"/></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="secondaryLanguage"/></td>
	</tr>

	<tr class=detailHead><td colspan="4"><bean:message key="prompt.adoptionInformation"/></td></tr>
	<tr>
		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.isAdopted" /></td>
		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="adoptedAsYesNo"/></td>
	</tr>
  <logic:equal name="juvenileProfileMainForm" property="adopted" value="true">
  	<tr>
  		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.failedPlacements" /></td>
  		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="failedPlacements"/></td>
  	</tr>
  	<tr valign='top'>
  		<td class=formDeLabel valign="top"><bean:message key="prompt.adoptionComments" /></td>
  		<td class=formDe colspan="3"><bean:write name="juvenileProfileMainForm" property="adoptionComments"/></td>  
  	</tr>
  </logic:equal>
	<!-- wrap in feature 163421-->
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_ID_V%>'>
	<tr class=detailHead><td colspan="4"><bean:message key="prompt.idInfo"/></td></tr>
	<tr>
		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.dlNumber"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="driverLicenseNum"/></td>
		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.dlState"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="driverLicenseState"/></td>
	</tr>
	<tr>
		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.dlClass"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="driverLicenseClass"/></td>
		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.dlExpDate"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="driverLicenseExpireDate"/></td>
	</tr>
	<tr>
		<td class=formDeLabel valign="top" nowrap><bean:message key="prompt.SSN"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="SSN"/></td>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.stateidSID"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="SID"/></td>
  </tr>
  <tr>
		<td class=formDeLabel><bean:message key="prompt.sheriffOffice#"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="SONum"/></td>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.educationID"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="educationId"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.dhsNumber"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="DHSNum"/></td>
	   	<%-- JIMS200077276 Started--%>
		<td class=formDeLabel nowrap><bean:message key="prompt.studentID"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="studentId"/></td>  
		<%-- JIMS200077276 Ended--%>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.tsdsID"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="TSDSId"/></td>
		<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.alienReg#"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="alienNum"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.fbiNumber"/></td>
		<td class=formDe colspan="4"><bean:write name="juvenileProfileMainForm" property="FBINum"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.passportNumber"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="passportNumber"/></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.countryOfIssuance"/></td>
		<logic:notEqual name="juvenileProfileMainForm" property="countryOfIssuance" value="">
			<td class=formDe><bean:write name="juvenileProfileMainForm" property="countryOfIssuanceDesc"/></td>
		</logic:notEqual>
		<logic:equal name="juvenileProfileMainForm" property="countryOfIssuance" value="">
			<td class=formDe></td>
		</logic:equal>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.expirationDate"/></td>
		<td class=formDe colspan="4"><bean:write name="juvenileProfileMainForm" property="passportExpirationDate"/></td>
	</tr>
	</jims2:isAllowed>
	<tr class=detailHead><td colspan="4"><bean:message key="prompt.attributes"/></td></tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.natural"/><br> <bean:message key="prompt.eyeColor"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="naturalEyeColor"/></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.natural"/><br> <bean:message key="prompt.hairColor"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="naturalHairColor"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.complexion"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="complexion"/></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.religion"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="religion"/></td>
	</tr>
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_ID_V%>'>
	<tr class=detailHead><td colspan="4"><bean:message key="prompt.dnaInfo"/></td></tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.dnaSample#"/></td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="DNASampleNum"/></td>
		<td class=formDeLabel nowrap><bean:message key="prompt.dateSent"/><br> to DPS</td>
		<td class=formDe><bean:write name="juvenileProfileMainForm" property="dateSenttoDPS"/></td>
	</tr>
	</jims2:isAllowed>
</table>
