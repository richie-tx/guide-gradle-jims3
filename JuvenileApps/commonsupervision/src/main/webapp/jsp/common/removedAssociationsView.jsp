<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 04/04/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/08/2007  Hien Rodriguez  defect#37125 hardcoded subHeader for now, Justin will split
				all JSPs for each agency -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="ui.common.UIUtil" %> 
<tiles:importAttribute name="removedAssociations"/>
<tiles:importAttribute name="associationType"/>
<tiles:importAttribute name="group2Caption" />
<tiles:importAttribute name="group3Caption" />
<bean:define id="group2Caption" name="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="group3Caption" type="java.lang.String"/>
<bean:define id="userAgencyId" type="java.lang.String"><%=UIUtil.getCurrentUserAgencyID()%></bean:define>

<head>
<title>Common Supervision - common/removedAssociationsView.jsp</title>
</head>

<!-- Associations -->
<table width="100%" border="0" cellspacing=0 cellpadding=2 class=borderTableBlue>
	<tr class=detailHead>
		<td class=paddedFourPix>
			<table width=100% cellpadding=0 cellspacing=0>
				<tr>
					<td width=1%><a href="javascript:showHideMulti('removedAssoc', 'removedAssoc', 1, '/<msp:webapp/>')" border=0>
						<img border=0 src="/<msp:webapp/>images/contract.gif" name="removedAssoc"></a>
					</td>
					<td class=detailHead>&nbsp;<bean:message key="prompt.associationsRemovedDueTo"/>
						<logic:equal name="associationType" value="condition">
							<bean:message key="prompt.condition"/>
						</logic:equal>
						<logic:equal name="associationType" value="court">
							<bean:message key="prompt.court"/>&nbsp;<bean:message key="prompt.policy"/>
						</logic:equal>
						<logic:equal name="associationType" value="department">
							<bean:message key="prompt.department"/>&nbsp;<bean:message key="prompt.policy"/>
						</logic:equal>
							<bean:message key="prompt.modifications"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr id="removedAssoc0" class="visibleTR">
		<td>
			<table width=100% cellspacing=1>
				<tr bgcolor=#cccccc>
					<td class=subHead>
						<logic:equal name="associationType" value="condition">
							<bean:message key="prompt.policy"/> 
						</logic:equal>
						<logic:notEqual name="associationType" value="condition">
							<bean:message key="prompt.condition"/> 
						</logic:notEqual>
						<bean:message key="prompt.name"/>
					</td>
					
					<logic:equal name="associationType" value="condition">
						<logic:equal name="userAgencyId" value="CSC">
							<td class=subHead>Group 2</td>
							<td class=subHead>Group 3</td>
						</logic:equal>
						<logic:equal name="userAgencyId" value="JUV">
							<td class=subHead>Type</td>
							<td class=subHead>Subtype</td>
						</logic:equal>
						<logic:equal name="userAgencyId" value="PTR">
							<td class=subHead>Compliance/Procedure Subtype</td>
							<td class=subHead>Subtype Detail</td>
						</logic:equal>
					</logic:equal>

				<%--	<logic:equal name="associationType" value="condition">
						<td class=subHead><bean:message key="prompt.compliance"/>/<bean:message key="prompt.procedureSubType"/></td>  
					</logic:equal>--%>
					
					<logic:notEqual name="associationType" value="condition">
						<td class=subHead><bean:message key="<%=group2Caption%>" /></td>
						<td class=subHead><bean:message key="<%=group3Caption%>" /></td>
					</logic:notEqual>
					
				<%-- <td class=subHead><bean:message key="<%=group3Caption%>" /></td> --%>
				</tr>	
				
				<% int rowCount = 0; %>
				<logic:iterate id="objIter" name="removedAssociations">
					<% if ( ++rowCount % 2 > 0 ) { %> 
					<tr>
					<% } else { %>
					</tr><tr class=alternateRow>
					<% } %>
						<td class=subHead>
							<a href="/CommonSupervision/displayCourtPolicyView.do?policyId=<bean:write name="objIter" property="objId" />&viewOnly=true"><bean:write name="objIter" property="objName" /></a>
						</td>
						<td>
							<bean:write name="objIter" property="group2Name" />
						</td>
						<td>
							<bean:write name="objIter" property="group3Name" />
						</td>
					</tr>
				</logic:iterate>				
			</table>
		</td>
	</tr>
</table>


