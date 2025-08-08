<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/20/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<tiles:importAttribute name="goalInfo"/>

<html:base />

<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class=detailHead colspan=2 nowrap>Goal Details</td>
	</tr>
	<tr>
		<td colspan=2>
			<table align="center" width='100%' cellpadding="2" cellspacing="1">
        <%-- 03 oct 2007 - mjt - Defect 41711 ... Status is shown only for Details --%>
				<logic:equal name="caseplanForm" property="action" value="viewDetail">
	  				<tr>
	  					<td class=formDeLabel valign=top width='1%' nowrap> Goal Status</td>
	  					<td class=formDe><bean:write name="caseplanForm" property="currentGoalInfo.statusStr"/></td>
	  				</tr>
        		</logic:equal> 
				<tr>
					<td class=formDeLabel valign=top width='1%' nowrap> <bean:message key="prompt.domain"/> <bean:message key="prompt.type"/></td>
					<td class=formDe><bean:write name="caseplanForm" property="currentGoalInfo.domainTypeStr"/></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap> <bean:message key="prompt.personsResponsible"/></td>
					<td class=formDe >
						<table width='100%' cellpadding="0" cellspacing="0">
							<logic:notEmpty name="caseplanForm" property="currentGoalInfo.personsResponsibleIds">
								<logic:iterate name="caseplanForm" property="currentGoalInfo.personsResponsibleIds" id="idIndex">
									<tr>
										<td><bean:write name="idIndex"/></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</td>
				</tr>
				<tr>
					<td class=formDeLabel> <bean:message key="prompt.time"/> <bean:message key="prompt.frame"/></td>
					<td class=formDe>
						<bean:write name="caseplanForm" property="currentGoalInfo.timeFrameStr"/>
					 	<logic:equal name="caseplanForm" property="currentGoalInfo.timeFrameCd" value="OT">
							- <bean:write name="caseplanForm" property="currentGoalInfo.otherTimeFrameDesc" />
						</logic:equal>  
					</td>
				</tr>
				<tr>
					<td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.goal"/></td>
				</tr>
				<tr>
					<td class=formDe colspan=4><bean:write name="goalInfo" property="goal"/></td>
				</tr>
				
				<%--Start of adding Intervention JIMS200075816 --%>
				<tr>
					<td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.intervention"/></td>
				</tr>
				<tr>
					<td class=formDe colspan=4><bean:write name="goalInfo" property="intervention"/></td>
				</tr>
				<%--End of adding Intervention JIMS200075816 --%>
				<logic:notEqual name="caseplanForm" property="action" value="create">
				<tr>
					<td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.progress"/> <bean:message key="prompt.notes"/></td>
				</tr>
				</logic:notEqual>
				<%-- this is a unique field, since it doesn't get set during goal create, but it will be set during goal update --%>
				
					<tr>
						<td class=formDe colspan=4><bean:write name="goalInfo" property="progressNotes"/></td>
					</tr>
					<logic:notEqual name="caseplanForm" property="action" value="create">
						<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusStr" value="PENDING">
							<tr>
								<td class=formDeLabel  colspan=4><bean:message key="prompt.endRecommendations"/></td>
							</tr>
							<tr>
								<td class=formDe  colspan=4> <bean:write name="goalInfo" property="endRecommendations"/></td>
							</tr>
						</logic:notEqual>
					</logic:notEqual>
			</table>
		</td>
	</tr>
</table>
<%-- END GOAL INFORMATION TABLE --%>	