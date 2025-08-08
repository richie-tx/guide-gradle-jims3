<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/21/2005	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<html:html>

<bean:define id="conditionGroup1Caption" name="supervisionConditionForm" property="conditionGroup1Caption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="supervisionConditionForm" property="conditionGroup2Caption" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" name="supervisionConditionForm" property="conditionGroup3Caption" type="java.lang.String"/>

<table width="100%" cellspacing=1 cellpadding=4>
	<tr>
		<td colspan=2 class=detailHead>
			<table width=100% cellpadding=0 cellspacing=0>
				<tr>
					<td width=1%><a href="javascript:showHideMulti('supervisionCondition', 'sc', 15, '/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="supervisionCondition"></a></td>
					<td class=detailHead>&nbsp;Supervision Condition</td>
					<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
				</tr>
			</table>
	
		</td>
	</tr>
	<tr id="sc10" class="hidden">
		<td width=1% nowrap class=formDeLabel><bean:message key="prompt.name" /></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="conditionName"/>
		</td>
	</tr>
	<tr id="sc0" class="hidden">
		<td class=formDeLabel width=1% nowrap><bean:message key="<%=conditionGroup1Caption%>"/></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="group1Name"/></td>
	</tr>
	<tr id="sc1" class="hidden">
		<td class=formDeLabel width=1% nowrap><bean:message key="<%=conditionGroup2Caption%>"/></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="group2Name"/></td>
	</tr>
	<tr id="sc2" class="hidden" width=1% nowrap><td class=formDeLabel><bean:message key="<%=conditionGroup3Caption%>"/></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="group3Name"/></td>
	</tr>
	<tr id="sc3" class="hidden">
		<td class=formDeLabel valign=top width=1% nowrap><bean:message key="prompt.literal" /></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="fixedLiteral"  filter="false"/></td>
	</tr>
	
	<tr id="sc4" class="hidden">
		<td class=formDeLabel valign=top><bean:message key="prompt.spanishLiteral" /></td>
		<td class=formDe>
			Esto es un ejemplo de una condición especial que se creó B/C que el usuario no podría localizar una condición existente ya en JIMS2. esto es un ejemplo de una condición especial que se creó B/C que el usuario no podría localizar una condición existente ya en JIMS2.
		</td>
	</tr>
	<tr id="sc5" class="hidden">
		<td class=formDeLabel valign=top width=1% nowrap><bean:message key="prompt.details" /></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="conditionLiteral"  filter="false"/></td>
	</tr>

	<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
		<tr id="sc6" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.supervisionType" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="dispSelSupTypes"/></td>
		</tr>
	</logic:equal>
	<tr id="sc7" class="hidden">
		<td class=formDeLabel nowrap><bean:message key="prompt.documents" /></td>
		<td class=formDe>
			<bean:write name="supervisionConditionForm" property="selDocuments"/>
		</td>
	</tr>
	<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
		<tr id="sc6" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.severityLevel" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="severityLevel"/></td>
		</tr>
		<tr id="sc7" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.jurisdiction" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="jurisdiction"/></td>
		</tr>
	</logic:equal>	

	<tr id="sc8" class="hidden">
		<td class=formDeLabel nowrap><bean:message key="prompt.effectiveDate" /></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="effectiveDateSpecial" /></td>
	</tr>

	<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
		<tr id="sc11" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.eventType" /></td>
			<td class=formDe>
				<bean:write name="supervisionConditionForm" property="selectedEventTypes"/>
			</td>
		</tr>
		<tr id="sc12" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.eventCount" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="eventCountDesc"/></td>
		</tr>
		<tr id="sc13" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.period" /></td>
			<td class=formDe>
			<logic:notEmpty name="supervisionConditionForm" property="periodValue">
														<logic:notEqual name="supervisionConditionForm" property="periodValue" value="0">
														<bean:write name="supervisionConditionForm" property="periodValue"/> <bean:write name="supervisionConditionForm" property="period"/>
														</logic:notEqual>
														</logic:notEmpty>
			</td>
		</tr>
	</logic:equal>
<%--	
	<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
		<tr id="sc5" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.severityLevel" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="severityLevel"/></td>
		</tr>	
		<tr id="sc6" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.eventType" /></td>
			<td class=formDe>
				<bean:write name="supervisionConditionForm" property="selectedEventTypes"/>
			</td>
		</tr>
		<tr id="sc7" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.eventCount" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="eventCountDesc"/></td>
		</tr>
		<tr id="sc8" class="hidden">
			<td class=formDeLabel nowrap><bean:message key="prompt.period" /></td>
			<td class=formDe><bean:write name="supervisionConditionForm" property="periodValue"/> <bean:write name="supervisionConditionForm" property="period"/></td>
		</tr>
	</logic:equal>
--%>	
	<tr id="sc9" class="hidden">
		<td class=formDeLabel nowrap><bean:message key="prompt.notes" /></td>
		<td class=formDe><bean:write name="supervisionConditionForm" property="notes"/></td>
	</tr>
</table>
							
</html:html>