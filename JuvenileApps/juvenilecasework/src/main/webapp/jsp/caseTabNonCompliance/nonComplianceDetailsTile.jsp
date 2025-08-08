
<%--MODIFICATIONS --%>
<%-- 07/21/2011     C Shimek       	Create JSP Activity #70949--%>
<%-- 10/17/2011     C Shimek        removed Parent Informed select, now on Completion page --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>


<html:base />
<title><bean:message key="title.heading"/> - nonComplianceDetailsTile.jsp</title>

<%-- BEGIN DETAILS TABLE --%>
<table width='100%'>
	<tr> 
		<td class="formDeLabel"><bean:message key="prompt.nonComplianceDate" /></td>
		<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="nonComplianceDate" formatKey="date.format.mmddyyyy"/></td>
	</tr>
	<tr> 
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.sanctionsAssignedDate" /></td>
		<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="sanctionAssignedDate" formatKey="date.format.mmddyyyy"/></td>
	</tr>
	<tr> 
		<td class="formDeLabel" ><bean:message key="prompt.completeSanctionsBy" /></td>
		<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="sanctionCompleteByDate" formatKey="date.format.mmddyyyy" /></td>
	</tr>
<%-- 	<tr>
		<td class="formDeLabel"><bean:message key="prompt.parentInformed" />?</td>
		<td class="formDe">
			<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='true'>
				<bean:message key="prompt.yes" />
			</logic:equal>
			<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='false'>
				<bean:message key="prompt.no" />
			</logic:equal>
		</td>
	</tr>  --%>
	<tr>
		<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.vopLevel" /></td>
		<td class='formDe'><bean:write name="juvenileNonComplianceForm" property="probationViolationLevelDesc" /></td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan="2"><bean:message key="prompt.probationViolation" />s</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%">
				<logic:empty name="juvenileNonComplianceForm" property="probationViolationList">
					<tr>
						<td colspan="2">No Violations found to display</td>
					</tr>
				</logic:empty>
				<logic:notEmpty name="juvenileNonComplianceForm" property="probationViolationList">
					<logic:iterate id="pvIter" name="juvenileNonComplianceForm" property="probationViolationList" indexId="index">
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							<td ><bean:write name="pvIter" property="description" /></td>
						</tr>
					</logic:iterate> 
				</logic:notEmpty>																			
			</table>
		</td>
	</tr>
	<tr>
		<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.sanction" /> <bean:message key="prompt.level" /></td>
		<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="sanctionLevelDesc" /></td>
	</tr>
</table>	
<table width="100%" cellpadding="1" cellspacing="0">
	<tr class="formDeLabel">
		<td colspan="2"><bean:message key="prompt.sanction" /></td>
	</tr>
	<logic:empty name="juvenileNonComplianceForm" property="selectedSanctionsList">
		<tr>
			<td colspan="2">No Sanctions found to display</td>
		</tr>
	</logic:empty>
	<logic:notEmpty name="juvenileNonComplianceForm" property="selectedSanctionsList">
		<logic:iterate id="sanIter" name="juvenileNonComplianceForm" property="selectedSanctionsList" indexId="index">
			<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
				<td width="1%"><ul><li></li></ul></td>
				<td width="99%"><bean:write name="sanIter" property="otherText" /></td>
			</tr>
			<logic:notEmpty name="sanIter" property="comments" >
				<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
					<td>&nbsp;</td>
					<td><strong><bean:message key="prompt.comments"/>:</strong> <bean:write name="sanIter" property="comments" /></td>
				</tr>
			</logic:notEmpty>
			<tr>
				<td height="1" colspan="2" bgcolor="#888888"></td>
			</tr>	
		</logic:iterate> 
	</logic:notEmpty>
</table>
<%-- END DETAILS TABLE --%>
