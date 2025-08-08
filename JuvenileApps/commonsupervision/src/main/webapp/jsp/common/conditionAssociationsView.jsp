<!DOCTYPE HTML>
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<%-- 10/13/2015  RYoung          - #30612 MJCW: IE11 conversion of Common Supervision link on UILeftNav (UI)--%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="ui.common.UIUtil" %> 


<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>Common Supervision - common/conditionAssociationsView.jsp</title>
</head>

<tiles:importAttribute name="associatedCourtPolicies"/>
<tiles:importAttribute name="associatedDepartmentPolicies"/>

<bean:define id="userAgencyId" type="java.lang.String"><%=UIUtil.getCurrentUserAgencyID()%></bean:define>

<bean:define id="courtPolicyTitle" value="title.CSCourtPolicy" type="java.lang.String"/>
<bean:define id="courtPolicyAssoTitle" value="prompt.courtPolicies" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" value="prompt.group1" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" value="prompt.group3" type="java.lang.String"/>
<bean:define id="deptPolicyTitle" value="prompt.policyTableTitle" type="java.lang.String"/>
<bean:define id="deptPolicyAssoTitle" value="prompt.departmentPolicies" type="java.lang.String"/>
<bean:define id="departmentGroup1Caption" value="prompt.group1" type="java.lang.String"/>
<bean:define id="departmentGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="departmentGroup3Caption" value="prompt.group3" type="java.lang.String"/>

<logic:equal name="userAgencyId" value="JUV">
<%
courtPolicyTitle="title.consequence";
courtPolicyAssoTitle="prompt.consequences";
courtGroup1Caption="prompt.category";
courtGroup2Caption="prompt.type";
courtGroup3Caption="prompt.subtype";
departmentGroup1Caption="prompt.category";
departmentGroup2Caption="prompt.type";
departmentGroup3Caption="prompt.subtype";
%>
</logic:equal>
<logic:equal name="userAgencyId" value="PTR">
<%
courtPolicyTitle="title.complianceStandards";
courtPolicyAssoTitle="title.complianceStandards";
courtGroup1Caption="prompt.complianceType";
courtGroup2Caption="prompt.complianceSubType";
courtGroup3Caption="prompt.subTypeDetail";
deptPolicyTitle="title.procedures";
deptPolicyAssoTitle="title.procedures";
departmentGroup1Caption="prompt.procedureType";
departmentGroup2Caption="prompt.procedureSubType";
departmentGroup3Caption="prompt.subTypeDetail";
%>
</logic:equal>

<!-- Associations -->
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:message key="prompt.associations"/></td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="0">
				<tr bgcolor="#f0f0f0">
					<td width="1%" class="subhead"><a href="javascript:showHide('assocCtPolExpand', 'span', '/<msp:webapp/>')" border="0">
						<img src=/<msp:webapp/>images/contract.gif name="assocCtPolExpand" border="0"></a></td>
					<td class="subhead"><bean:message key="prompt.associated"/>&nbsp;<bean:message key="<%=courtPolicyAssoTitle%>"/></td>
					<!--<td class=subhead><bean:message key="prompt.associated"/> <bean:message key="prompt.court"/> <bean:message key="prompt.policies"/></td>-->
				</tr>
				<tr>
					<td colspan="2">
						<span id="assocCtPolExpandSpan" class="visible">
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="prompt.name"/></td>
									<!--<td class=formDeLabel><bean:message key="prompt.court"/> <bean:message key="prompt.policyName"/></td>-->
									<td class="formDeLabel"><bean:message key="<%=courtGroup2Caption%>"/></td>
									<td class="formDeLabel"><bean:message key="<%=courtGroup3Caption%>"/></td>
								</tr>
								<% int rowCount = 0; %>

								<logic:iterate id="objIter" name="associatedCourtPolicies">
									<% if ( ++rowCount % 2 > 0 ) { %> 
									<tr>
									<% } else { %>
									<tr class="alternateRow">
									<% } %>
	
									<td class="subHead"><a href="/CommonSupervision/displayCourtPolicyView.do?policyId=<bean:write name="objIter" property="objId" />&viewOnly=true"><bean:write name="objIter" property="objName" /></a></td>
									<td><bean:write name="objIter" property="group2Name" /></td>
									<td><bean:write name="objIter" property="group3Name" /></td>
								</tr>
								</logic:iterate>
							</table>
						</span>
					</td>
				</tr>
				<tr bgcolor="#f0f0f0">
					<td width="1%" class="subhead"><a href="javascript:showHide('assocDeptPolExpand', 'span', '/<msp:webapp/>')" border="0">
						<img src=/<msp:webapp/>images/contract.gif name="assocDeptPolExpand" border="0"></a></td>
					<td class="subhead"><bean:message key="prompt.associated"/>&nbsp;<bean:message key="<%=deptPolicyAssoTitle%>"/></td>
					<!--<td class=subhead><bean:message key="prompt.associated"/> <bean:message key="prompt.department"/> <bean:message key="prompt.policies"/></td>-->
				</tr>
				<tr>
					<td colspan="2">
						<span id="assocDeptPolExpandSpan" class="visible">
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="prompt.name"/></td>
									<!--<td class=formDeLabel><bean:message key="prompt.department"/> <bean:message key="prompt.policyName"/></td>-->
									<td class="formDeLabel"><bean:message key="<%=departmentGroup2Caption%>"/></td>
									<td class="formDeLabel"><bean:message key="<%=departmentGroup3Caption%>"/></td>
								</tr>
								<% rowCount = 0; %>


								<logic:iterate id="objIter" name="associatedDepartmentPolicies">
									<% if ( ++rowCount % 2 > 0 ) { %> 
									<tr>
									<% } else { %>
									<tr class="alternateRow">
									<% } %>

									<td><a href="/CommonSupervision/displayDepartmentPolicyView.do?policyId=<bean:write name="objIter" property="objId" />&viewOnly=true"><bean:write name="objIter" property="objName" /></a></td>
									<td><bean:write name="objIter" property="group2Name" /></td>
									<td><bean:write name="objIter" property="group3Name" /></td>
								</tr>
								</logic:iterate>
							</table>
						</span>
					</td>
				</tr>
			</table>

		</td>
	</tr>
</table>

