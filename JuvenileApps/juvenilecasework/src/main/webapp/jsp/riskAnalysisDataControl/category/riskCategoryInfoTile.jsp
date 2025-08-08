<!DOCTYPE HTML>
<%-- CShimek  01/11/2012	Create Tile.  This tile does not collapse --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>

<title><bean:message key="title.heading" /> - riskCategoryInfoTile.jsp</title>

    <tiles:useAttribute id="formName" name="formName"/>
    <tiles:useAttribute id="categoryBoxTitle" name="categoryBoxTitle"/>
	
	<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		<tr class="detailHead">
			<td  colspan="4">${categoryBoxTitle}</td>
		</tr>
		<tr>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.categoryName"/></td>
			<td width="35%" class="formDe">
				<bean:write name="${formName}" property="category.categoryName"/>
			</td>
			<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
			<td width="35%" class="formDe">
				<bean:write name="${formName}" property="category.entryDate" formatKey="date.format.mmddyyyy" />
			</td>
		</tr>					
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.riskResultGroup"/></td>
			<td class="formDe">
				<bean:write name="${formName}" property="category.riskResultGroup"/>
			</td>
			<td class="formDeLabel"><bean:message key="prompt.version"/></td>
			<td class="formDe">
				<bean:write name="${formName}" property="category.version" />
			</td>
		</tr>					
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.description"/></td>
			<td class="formDe" colspan="3"><bean:write name="${formName}" property="category.categoryDescription" /></td>
		</tr>
		</logic:notEqual>
	</table>
        