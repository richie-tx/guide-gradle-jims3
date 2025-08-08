<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/24/2012	CShimek Create JSP --%>
<%-- 10/28/2013  CShimek        #76282 Made cosmetic change to Driver License/ID Card Information block on expiration date line --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<%-- BEGIN MEMBER INFORMATION TABLE --%>
<table width='98%' border="0" cellspacing="1" class="borderTableBlue"  cellpadding="4">
	<tr>
		<td colspan='4' valign="top" class="detailHead"><bean:message key="prompt.member"/> <bean:message key="prompt.info"/>&nbsp;&nbsp;
		
			<logic:equal name="juvenileMemberForm" property="secondaryAction" value="">  <%-- should be true when tile not used in popup --%>
				<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
					<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>        		                    		             
							<a id="updateMemberLink" data-href="/<msp:webapp/>displayCreateFamilyMember.do?submitAction=<bean:message key="button.update"/>">Update Member </a>
						</jims2:isAllowed>
					</logic:notEqual>
				</logic:equal>	
			</logic:equal>
			 (<bean:write name="juvenileMemberForm" property="memberNumber"/>)
		</td>
	
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.name"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
	</tr>
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.over21"/></td> 
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="over21Str"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.socialSecurity#"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="ssn.formattedSSN"/></td>
		<td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="dateOfBirth"/></td>
	</tr> 
	<tr>  
		<td class="formDeLabel"><bean:message key="prompt.sex"/></td>
		<td class="formDe" colspan='3'><bean:write name="juvenileMemberForm" property="sex"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.usCitizen"/></td>
		<td class="formDe" colspan="3"> <bean:write name="juvenileMemberForm" property="isUSCitizen"/> </td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.nationality"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="nationality"/></td>
	</tr>
	<tr>  
		<td class="formDeLabel"><bean:message key="prompt.ethnicity"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="ethnicity"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.primaryLanguage"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="primaryLanguage"/></td>
	</tr>
	<tr>  
		<td class="formDeLabel"><bean:message key="prompt.secondaryLanguage"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="secondaryLanguage"/></td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.isFamilyMemberDeceased"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="deceasedYesNo"/></td>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.causeOfDeath"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="causeofDeath"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.juvenileAgeAtFamilyMemberDeath"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="juvenileAgeAtDeath"/></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.isFamilyMemberIncarcerated"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="incarceratedYesNo"/></td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top" colspan="4"><bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.comments"/></td>
	</tr>
	<tr>
		<td class="formDe" colspan="4"><bean:write name="juvenileMemberForm" property="familyMemberComments"/>&nbsp;</td>
	</tr>
</table>
<%-- END MEMBER INFORMATION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DRIVER LICENSE/ID INFORMAITON TABLE --%>
<%-- <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'> --%>
<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_ID_V%>'> 
<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td valign="top" class="detailHead" colspan='4'>
			<table border="0" cellpadding="0" cellspacing="2">
				<tr>
					<td width='1%'>
						<a href="javascript:showHideMulti('DLINFO', 'pDLINFO', 6, '/<msp:webapp/>')">
						<img border=0 src="/<msp:webapp/>images/expand.gif" name="DLINFO"/>
						</a>
					</td>
					<td class="detailHead">Driver License/ID Card Information</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr id="pDLINFO0" class="hidden">
		<td class="formDeLabel"><bean:message key="prompt.number"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseNum"/></td>
		<td class="formDeLabel"><bean:message key="prompt.state"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseState"/></td>
	</tr>
	<tr id="pDLINFO1" class="hidden">
		<td class="formDeLabel"><bean:message key="prompt.expirationDate"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseExpirationDate"/></td>
		<td class="formDeLabel"><bean:message key="prompt.class"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="driverLicenseClass"/></td>
	</tr>
	<tr id="pDLINFO2" class="hidden">
		<td class="formDeLabel" width="1%" nowrap="nowrap">
			<bean:message key="prompt.state" />
			<bean:message key="prompt.issued" />
			<bean:message key="prompt.idCard#" />
		</td>
		<td class="formDe" width="40%"><bean:write name="juvenileMemberForm" property="stateIssuedIdNum"/></td>
		<td class="formDeLabel" width="1%"><bean:message key="prompt.state"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="issuedByState"/></td>
	</tr>
	<tr id="pDLINFO3" class="hidden">
		<td class="formDeLabel" width="1%" nowrap="nowrap">
			<bean:message key="prompt.passportNumber" /></td>
		<td class="formDe" width="40%"><bean:write name="juvenileMemberForm" property="passportNum"/></td>
		<td class="formDeLabel" width="1%"><bean:message key="prompt.countryOfIssuance"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="countryOfIssuance"/></td>
	</tr>
	<tr id="pDLINFO5" class="hidden">  
		<td class="formDeLabel"><bean:message key="prompt.stateidSID"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="sidNum"/></td>
		<td class="formDeLabel" width="15%"><bean:message key="prompt.alienReg#"/></td>
		<td class="formDe"><bean:write name="juvenileMemberForm" property="alienNum"/></td>
	</tr>
	<tr id="pDLINFO4" class="hidden">
		<td class="formDeLabel"><bean:message key="prompt.expirationDate"/></td>
		<td class="formDe" colspan="3"><bean:write name="juvenileMemberForm" property="passportExpirationDate"/></td>
	</tr>
</table>	
</jims2:isAllowed>		
<%-- END DRIVER LICENSE/ID INFORMAITON TABLE --%>

<div class='spacer'></div>
<%-- BEGIN ASSOCIATED JUVENILES TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td valign="top" class="detailHead" colspan='4' >
			<table border="0" cellpadding="0" cellspacing="2">
				<tr>
					<td width='1%'>
						<a href="javascript:showHideMulti('ASSOCJUV', 'pASSOCJUV', 1, '/<msp:webapp/>')">
						<img border="0" src="/<msp:webapp/>images/expand.gif" name="ASSOCJUV"/>
						</a>
					</td>
					<td class="detailHead">Associated Juveniles</td>
				</tr>
			</table>
		</td>
	</tr>
	<logic:empty name="juvenileMemberForm" property="associatedJuveniles">
		<tr id="pASSOCJUV0" class="hidden"> 
			<td class="formDe" colspan="4">No Associated Juveniles </td> 
		</tr> 
	</logic:empty>

	<logic:notEmpty name="juvenileMemberForm" property="associatedJuveniles">
		<tr id="pASSOCJUV0" class="hidden"> 
			<td colspan="4">
				<table width='100%' border="0" cellpadding="4" cellspacing="1">
					<tr bgcolor='#cccccc' >				
						<td class="formDeLabel" valign="top"><bean:message key="prompt.juvenile"/> <bean:message key="prompt.number"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.juvenile"/> <bean:message key="prompt.name"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.relationship"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.guardian"/></td>
					</tr>
					<logic:iterate id="assocJuvList" name="juvenileMemberForm" property="associatedJuveniles" indexId="idx1">
						<tr class="<%out.print((idx1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">				
							<td valign="top"><bean:write name="assocJuvList" property="juvId"/></td>
							<td valign="top"><bean:write name="assocJuvList" property="juvName.formattedName"/></td>
							<td><bean:write name="assocJuvList" property="relationType"/></td>
							<td>
								<logic:equal name="assocJuvList" property="guardian" value="true">
								YES
								</logic:equal>
								<logic:notEqual name="assocJuvList" property="guardian" value="true">
								NO
								</logic:notEqual>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</td>
		</tr>
	</logic:notEmpty>
</table>
<%-- END ASSOCIATED JUVENILES TABLE --%>			
<div class='spacer'></div>
<%-- BEGIN MARITAL STATUS TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<tr>
		<td valign="top" class="detailHead" colspan='4'>
			<table border="0" cellpadding="0" cellspacing="2">
				<tr>
					<td width='1%'>
						<a href="javascript:showHideMulti('MARITSTAT', 'pMARITSTAT', 1, '/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="MARITSTAT"/></a>
					</td>
					<td class="detailHead"><bean:message key="prompt.marital"/> <bean:message key="prompt.status"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr id="pMARITSTAT0" class="hidden">
		<td>
			<table width='100%' border="0" cellpadding="4" cellspacing="1">
				<logic:empty name="juvenileMemberForm" property="maritalList">
					<tr> 
						<td class="formDe" colspan="4">No Marital Status History</td> 
					</tr> 
				</logic:empty>

				<logic:notEmpty name="juvenileMemberForm" property="maritalList">
					<tr bgcolor='#cccccc'>				
						<td class="formDeLabel" valign="top"><bean:message key="prompt.entryDate"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.marital"/> <bean:message key="prompt.status"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.relationship"/> With</td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.marriage"/> <bean:message key="prompt.date"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.divorce"/> <bean:message key="prompt.date"/></td>
						<td class="formDeLabel" valign="top"><bean:message key="prompt.#OfChildren"/></td>
					</tr>
					
					<logic:iterate id="maritalList" name="juvenileMemberForm" property="maritalList" indexId="idx2">
						<tr class="<%out.print((idx2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">			
							<td valign="top"><bean:write name="maritalList" property="entryDate"/></td>
							<td valign="top"><bean:write name="maritalList" property="maritalStatus"/></td>
							<td>
								<logic:notEmpty name="maritalList" property="relatedFamMemId">
									<logic:notEqual name="maritalList" property="relatedFamMemId" value=""> 
										<bean:write name="maritalList" property="relatedFamMemId"/> - <bean:write name="maritalList" property="relatedFamMemName.formattedName"/>
									</logic:notEqual>
								</logic:notEmpty>
							&nbsp;
							</td>
							<td><bean:write name="maritalList" property="marriageDate"/></td>
							<td><bean:write name="maritalList" property="divorceDate"/> </td>
							<td><bean:write name="maritalList" property="numOfChildren"/></td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
			</table>
		</td>
	</tr>
</table>
<div class='spacer'></div>
<%-- END MARITAL STATUS TABLE --%>
