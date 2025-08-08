<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Contract Service Provider display tables for CommonSupervision  -->
<!-- Requires case_util.js -->
<!-- 10/05/2006		CShimek 	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%-- 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> --%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %> 

<head>
<title>Common Supervision - common/contractServiceProviderInfo.jsp</title>
</head>


<!-- BEGIN PROVIDER INFORMATION TABLE -->
<table cellpadding="1" cellspacing="0" border="0" width="98%">
	<tr>
		<td bgcolor="#cccccc">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td class="headerLabel" width="1%" nowrap><bean:message key="prompt.provider" />&nbsp;<bean:message key="prompt.name" /></td>
					<td colspan="3" class="headerData"><bean:write name="contractForm" property="serviceProviderName" /></td>
				</tr>
				<tr>
					<td class="headerLabel"><bean:message key="prompt.startDate" /></td>
					<td class="headerData"><bean:write name="contractForm" property="serviceProviderStartDate" /></td>
					<td class="headerLabel" width="1%"><bean:message key="prompt.inHouse" /></td>
					<td class="headerData"><bean:write name="contractForm" property="inHouse" /></td>
				</tr>
				<tr>
					<td class="headerLabel"><bean:message key="prompt.program" />&nbsp;<bean:message key="prompt.name" /></td>
					<td class="headerData" colspan="3"><bean:write name="contractForm" property="programName" /></td>
				</tr>
				<tr>
					<td class="headerLabel"><bean:message key="prompt.targetIntervention" /></td>
					<td class="headerData" colspan="3"><bean:write name="contractForm" property="targetIntervention" /></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END PROVIDER INFORMATION TABLE -->
<br>
<!-- BEGIN SERVICE INFORMATION TABLE -->  <%--img border=0 src="../../common/images/expand.gif" name="servInfo" --%>
<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">
			<table width="100%" cellpadding="2" cellspacing="0">
				<tr>
					<td width="1%"><a href="javascript:showHide('servInfo', 'row',/<msp:webapp/>)" border="0"><img src="/<msp:webapp/>images/expand.gif" name="servInfo" border="0" ></a></td>
					<td class="detailHead"><bean:message key="prompt.serviceInfo" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr id="servInfoSpan" class="hidden">
		<td>
			<table width="100%" cellpadding="4" cellspacing="1">
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.name" /></td>
					<td class="formDe"><bean:write name="contractForm" property="serviceName" /></td>
					<td class="formDeLabel" nowrap><bean:message key="prompt.code" /></td>
					<td class="formDe"><bean:write name="contractForm" property="serviceCode" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.type" /></td>
					<td class="formDe" colspan="3"><bean:write name="contractForm" property="serviceType" /> </td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.maxEnrollment" /></td>
					<td class="formDe"><bean:write name="contractForm" property="serviceMaxEnrollment" /></td>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.cost" /></td>
					<td class="formDe"><bean:write name="contractForm" property="serviceCost" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.service"/> <bean:message key="prompt.location"/> Unit(s)</td>
					<td class="formDe" colspan="3"><bean:write name="contractForm" property="serviceLocation" /></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END SERVICE INFORMATION TABLE -->
<br>
