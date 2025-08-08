<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- associated object List-->
<!-- 01/20/2006  C Shimek    defect #27425, made group2 and group3 titles dynamic --> 
<!-- 03/31/2006  H Rodriguez defect#300113 display correct labels for each agency --> 
<!-- 04/25/2008  L Deen		 defect#49140 add sorting -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
<title>Common Supervision - common/searchResultObjects.jsp</title>
</head>

<tiles:importAttribute name="beanIter"/>
<tiles:importAttribute name="form"/>
<tiles:importAttribute name="tableHeadingName"/>
<tiles:importAttribute name="viewURL" />

<tiles:importAttribute name="group2Caption" />
<tiles:importAttribute name="group3Caption" />
<bean:define id="group2Caption" name="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="group3Caption" type="java.lang.String"/>
<div align=center>
	<bean:write name="form" property="searchResultText"/>
</div>

<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<bean:size id="resultSize" name="form" property="assocSearchResults" />	
			<script type="text/javascript">
			renderScrollingArea(<bean:write name="resultSize" />);									
			</script>			
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<thead>
				<tr class="detailHead">
					<td width="1%"></td>
					<td><bean:write name="tableHeadingName" />
			<jims:sortResults beanName="form" results="assocSearchResults" primaryPropSort="objName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4" />	
					</td>
					<td><bean:message key="<%=group2Caption%>" />
					<jims:sortResults beanName="form" results="assocSearchResults" primaryPropSort="group2Name" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="4" />
					</td>
					<td><bean:message key="<%=group3Caption%>" />
					<jims:sortResults beanName="form" results="assocSearchResults" primaryPropSort="group3Name" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="4" />
					</td>
				</tr>
				</thead>
				<logic:iterate id="objIter" name="form" property="assocSearchResults" indexId="index">
					<tbody>
					<tr class="<% out.print( (index.intValue() %2 == 0) ? "alternateRow" : "normalRow"); %>">
						<td width="1%">
							<input type=checkbox name="selectInd" value="<bean:write name="objIter" property="objId" />" />
						</td>
						<td>
							<a href="<bean:write name="viewURL" /><bean:write name="objIter" property="objId" />"> <bean:write name="objIter" property="objName" /></a>
						</td>
						<td>
							<bean:write name="objIter" property="group2Name" />
						</td>
						<td>
							<bean:write name="objIter" property="group3Name" />
						</td>
					</tr>
				</tbody>
				</logic:iterate>
			</table>
			</div>
		</td>
	</tr>
</table>
<input type="hidden" name="clearSelectInd" value="true"/>
