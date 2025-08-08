<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/21/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />

<html:base />

<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class=detailHead><bean:message key="prompt.associated"/> <bean:message key="prompt.rules"/></td>
	</tr>
	<tr>
		<td>
			<table cellpadding=2 cellspacing=1 width='100%'>

			 <tr bgcolor='#cccccc'>
				<td></td>
				<td class=subHead><bean:message key="prompt.rule"/> <bean:message key="prompt.id"/></td>
				<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.name" /></td>
				<td class="subHead"><bean:message key="prompt.rule"/> <bean:message key="prompt.type" /></td>
				<td class="subHead"><bean:message key="prompt.standard" /></td>
				<td class="subHead"><bean:message key="prompt.monitor"/> <bean:message key="prompt.frequency" /></td>
				<td class=subHead><bean:message key="prompt.completion"/> <bean:message key="prompt.date"/></td>
				<td class=subHead><bean:message key="prompt.completion"/> <bean:message key="prompt.status"/></td>
			</tr>
			
      <logic:notEmpty name="caseplanForm" property="currentGoalInfo.associatedRules">
      	<logic:iterate id="rulesIndex" name="caseplanForm" property="currentGoalInfo.associatedRules" indexId="index">
          <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
    				<td>
    					<logic:notEqual name="status" value="view">
      					<input type="checkbox" name="currentGoalInfo.selectedRules" value="<bean:write name="rulesIndex" property="ruleId"/>"/>
    					</logic:notEqual>
     				</td>
     				<td>						
     					<a href="javascript:goNav('/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display Rule Details&selectedValue=<bean:write name="rulesIndex" property="ruleId"/>')"><bean:write name="rulesIndex" property="ruleId"/></a>																		
     				</td>
   					<td><bean:write name="rulesIndex" property="ruleName" /></td>
						<td><bean:write name="rulesIndex" property="ruleTypeDesc" /></td>
						<td><logic:equal name="rulesIndex" property="standard" value="true">
							 	STANDARD
							</logic:equal>
							<logic:equal name="rulesIndex" property="standard" value="false">
								CUSTOM
							</logic:equal>
						</td>
						<td><bean:write name="rulesIndex" property="ruleMonitorFreqDesc" /></td>
						<td nowrap><bean:write name="rulesIndex" property="ruleCompletionDate" formatKey="date.format.mmddyyyy" /></td>
						<td><bean:write name="rulesIndex" property="ruleCompletionStatus"/></td>										
					</tr>
				</logic:iterate>
			</logic:notEmpty>
			</table>
		</td>
	</tr>
</table>
