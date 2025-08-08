<!DOCTYPE HTML>
<%-- CShimek  02/17/2012	Create Tile.  This is Question Answers display --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title><bean:message key="title.heading" /> - riskCategoryQuestionAnswersTile.jsp</title>
    <tiles:useAttribute id="formName" name="formName"/>
<!-- BEGIN ANSWERS TABLE -->
	<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		<logic:empty name="${formName}" property="answerList">
			<tr>
				<td class="formDe" colspan="4">No Answers found for this Question.</td>
			</tr>
		</logic:empty>
		<logic:iterate id="aId" name="${formName}" property="answerList">
			<tr>
				<td class="formDeLabel"width="1%" nowrap="nowrap"><bean:message key="prompt.entryDateTime" /></td>
				<td class="formDe" colspan="3"><bean:write name="aId" property="answerEntryDate"/></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.answerText" /></td>
				<td class="formDe" colspan="3"><bean:write name="aId" property="response"/></td>
			</tr>
			<tr class="formDeLabel">
				<td class="formDeLabel"><bean:message key="prompt.weight" /></td>
				<td class="formDe" width="35%"><bean:write name="aId" property="weight"/></td>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.subordinateQuestion"/></td>
				<td class="formDe" width="35%">
					<logic:notEqual name='aId' property='subordinateQuestionName' value="">
						<a href="javascript:newCustomWindow('/<msp:webapp/>displayRiskCategoryCreateSummary.do?submitAction=Link&questionID=<bean:write name="aId" property="subordinateQuestionId"/>', 'winName',830,500);">
							<bean:write name="aId" property="subordinateQuestionName"/>
						</a>
					</logic:notEqual>	
				</td>
			</tr>	
			<tr class="formDeLabel">	
				<td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
				<td class="formDe" width="35%"><bean:write name="aId" property="sortOrder"/></td>
				<td class="formDeLabel"><bean:message key="prompt.action"/></td>
				<td class="formDe" width="35%"><bean:write name="aId" property="action"/></td>
			</tr>
			<tr><td></td></tr>
		</logic:iterate>	
	</table>
   <!-- END ANSWERS TABLE -->