<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/20/2006  Hien Rodriguez  JIMS200027598 - Modify Labels for JUV & PTR agencies -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<tiles:importAttribute name="associatedConditions"/>
<tiles:useAttribute id="conditionGroup2Caption" name="conditionGroup2Caption" classname="java.lang.String"/>
<tiles:useAttribute id="conditionGroup3Caption" name="conditionGroup3Caption" classname="java.lang.String"/>

<head>
<title>Common Supervision - common/associatedConditionsView.jsp</title>
</head>

<!--removed associations start-->
<span id="autoRemove">
	<table width="100%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
		<tr class="detailHead">
			<td class="paddedFourPix">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="1%"><a href="javascript:showHideMulti('assocConditions', 'ara', 1,'/<msp:webapp/>')" >
							<img border="0" src="/<msp:webapp/>images/contract.gif" name="assocConditions"></a>
						</td>
						<td class="detailHead">&nbsp;Currently <bean:message key="prompt.associatedConditions" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="ara0" class="visibleTR">
			<td>
				<table width="100%" cellspacing="1">
					<tr bgcolor="#cccccc">
						<td class="subHead"><bean:message key="prompt.conditionName" /></td>
						<td class="subHead"><bean:message key="<%=conditionGroup2Caption%>" /></td>
						<td class="subHead"><bean:message key="<%=conditionGroup3Caption%>" /></td>
					</tr>
					<% int rowCount = 0; %>
					<logic:notEmpty name="associatedConditions">
					<logic:iterate id="objIter" name="associatedConditions">
						<% if ( ++rowCount % 2 > 0 ) { %> 
						<tr>
						<% } else { %>
						<tr class="alternateRow">
						<% } %>
							<td>
								<a href="/CommonSupervision/displaySupervisionConditionView.do?conditionId=<bean:write name="objIter" property="objId" />&viewOnly=true"><bean:write name="objIter" property="objName" /></a>
							</td>
							<td>
								<bean:write name="objIter" property="group2Name" />
							</td>
							<td>
								<bean:write name="objIter" property="group3Name" />
							</td>
						</tr>
					</logic:iterate>
				  </logic:notEmpty>
				</table>
			</td>
		</tr>
	</table>
</span>


