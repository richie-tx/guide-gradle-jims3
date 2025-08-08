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

<table width="98%" border="0" cellspacing="1" cellpadding="4">
							<tr>
								<td colspan="2" class="detailHead">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHideMulti('supervisionCondition', 'sc', 15, '/<msp:webapp/>')" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="supervisionCondition"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="title.supervisionCondition" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							
												<tr id="sc0" class="hidden">
													<td class="formDeLabel"><bean:message key="prompt.name" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionName"/></td>
												</tr>
											<tr id="sc1" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.group1"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group1Name"/></td>
											</tr>
											<tr id="sc2" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.group2"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group2Name"/></td>
											</tr>
											<tr id="sc3" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.group3"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group3Name"/></td>
											</tr>
											<tr id="sc4" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.standard" /></td>
												<td class="formDe"><logic:equal name="supervisionConditionForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="supervisionConditionForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
											</tr>
											<tr id="sc5" class="hidden">
												<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.literal" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionLiteral" filter="false"/></td>
											</tr>
											<tr id="sc15" class="hidden">
												<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.spanishLiteral" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionSpanishLiteral"  filter="false"/></td>
											</tr>
											<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
												<tr id="sc6" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.supervisionType" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="dispSelSupTypes"/></td>
												</tr>
											</logic:equal>
											<tr id="sc7" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.documents" /></td>
												<td class="formDe">
													<bean:write name="supervisionConditionForm" property="selDocuments"/>
												</td>
											</tr>
											<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
												<tr id="sc6" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.severityLevel" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="severityLevel"/></td>
												</tr>										
												<tr id="sc8" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.jurisdiction" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="jurisdiction"/></td>
												</tr>
											</logic:equal>	
											<tr id="sc9" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.effectiveDate" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="effectiveDate"/></td>
											</tr>
											
											<%-- <logic:equal name="supervisionConditionForm" property="action" value="update"> --%>
											<logic:notEmpty name="supervisionConditionForm" property="inactiveDate">
												<tr id="sc10" class="hidden">
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="inactiveDate" /></td>
												</tr>
											</logic:notEmpty>
											<%-- </logic:equal> --%>
											<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
												<tr id="sc11" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.eventType" /></td>
													<td class="formDe">
														<bean:write name="supervisionConditionForm" property="selectedEventTypes"/>
													</td>
												</tr>
												<tr id="sc12" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.eventCount" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="eventCountDesc"/></td>
												</tr>
												<tr id="sc13" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.period" /></td>
													<td class="formDe">
														<logic:notEmpty name="supervisionConditionForm" property="periodValue">
																		<logic:notEqual name="supervisionConditionForm" property="periodValue" value="0">
																		<bean:write name="supervisionConditionForm" property="periodValue"/> <bean:write name="supervisionConditionForm" property="period"/>
																		</logic:notEqual>
																		</logic:notEmpty></td>
												</tr>
											</logic:equal>
											<tr id="sc14" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.notes" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="notes"/></td>
											</tr>
							
						</table>
						
			</html:html>