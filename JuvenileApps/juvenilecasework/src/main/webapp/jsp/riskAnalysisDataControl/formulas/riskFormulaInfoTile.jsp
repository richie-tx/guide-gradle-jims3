<!DOCTYPE HTML>
<%-- CShimek  03/01/2012	Create Tile.  This tile does not collapse --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>

<title><bean:message key="title.heading" /> - riskFormulaInfoTile.jsp</title>

    <tiles:useAttribute id="formName" name="formName"/>
    <tiles:useAttribute id="boxTitle" name="boxTitle"/>
    <tiles:useAttribute id="borderClass" name="borderClass"/>
	
	<table width="100%" cellpadding="2" cellspacing="1" border="0" class="${borderClass}">
		<tr class="detailHead">
			<td colspan="4">${boxTitle}</td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.formulaName"/></td>
			<td class="formDe" width="45%"><bean:write name="${formName}" property="formulaName" /></td>
			<td class="formDeLabel"><bean:message key="prompt.riskType"/></td>
			<td class="formDe"><bean:write name="${formName}" property="riskTypeDesc" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.version"/></td>
			<td class="formDe"><bean:write name="${formName}" property="version" /></td>
			<td class="formDeLabel"><bean:message key="prompt.status"/></td>
			<td class="formDe"><bean:write name="${formName}" property="statusDesc" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
			<td class="formDe" ><bean:write name="${formName}" property="entryDate" formatKey="datetime.format.mmddyyyy" /></td>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.activationDate"/></td>
			<td class="formDe"><bean:write name="${formName}" property="activationDateStr"/></td>
		</tr>
	</table>        