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

<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
								<td colspan=2 class=detailHead>
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td width=1%><a href="javascript:showHideMulti('supervisionCondition', 'sc', 13, '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="supervisionCondition"></a></td>
											<td class=detailHead>&nbsp;<bean:message key="title.consequence"/></td>
											<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="sc11" class="hidden">
								<td width=1% nowrap class=formDeLabel><bean:message key="prompt.name" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="courtPolicyName" /></td>
							</tr>
							<tr id="sc0" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="prompt.category"/></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="group1Name"/></td>
							</tr>
							<tr id="sc1" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="prompt.type"/></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="group2Name"/></td>
							</tr>
							<tr id="sc2" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="prompt.subtype"/></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="group3Name"/></td>
							</tr>
							<tr id="sc3" class="hidden">
								<td class=formDeLabel><bean:message key="prompt.standard" /></td>
								<td class=formDe><logic:equal name="courtPolicyForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="courtPolicyForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
							</tr>
							<tr id="sc4" class="hidden">
								<td class="formDeLabel" nowrap><bean:message key="prompt.policySource" /></td>
								<td class=formDe>
									<logic:equal name="courtPolicyForm" property="departmentPolicy" value="true">
										<bean:message key="prompt.department"/>
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="departmentPolicy" value="true">
										<bean:message key="prompt.court"/>
									</logic:notEqual>
								</td>
							</tr>
							<tr id="sc5" class="hidden">
								<td class=formDeLabel valign=top width=1% nowrap><bean:message key="title.consequence"/></td>
								<td class=formDe>
									<bean:write name="courtPolicyForm" property="courtPolicyLiteral" filter="false"/>
								</td>
							</tr>								
							<tr id="sc6" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.effectiveDate" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="effectiveDate"/></td>
							</tr>
							
							<logic:equal name="courtPolicyForm" property="action" value="update">
								<tr id="sc12" class="hidden">
									<td class=formDeLabel width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
									<td class=formDe><bean:write name="courtPolicyForm" property="inactiveDate" /></td>
								</tr>
							</logic:equal>
							
							
							<tr id="sc10" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.notes" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="notes"/></td>
							</tr>
							
							</table>
							
						</html:html>