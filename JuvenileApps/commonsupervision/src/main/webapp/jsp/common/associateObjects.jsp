<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- associated object List-->
<!-- 01/20/2006  C Shimek    defect #27425, made group2 and group3 titles dynamic -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency --> 
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="ui.supervision.SupervisionOptions.form.AssociateBean, java.util.*" %>

<head>
<title>Common Supervision - common/associateObjects.jsp</title>
</head>

<tiles:importAttribute name="form"/>
<tiles:importAttribute name="mode"/>
<tiles:importAttribute name="tableHeadingName"/>
<tiles:importAttribute name="title"/>
<tiles:importAttribute name="removeURL"/>

<tiles:importAttribute name="group2Caption" />
<tiles:importAttribute name="group3Caption" />
<bean:define id="group2Caption" name="group2Caption" type="java.lang.String"/>
<bean:define id="group3Caption" name="group3Caption" type="java.lang.String"/>

<tiles:useAttribute id="beanIter" name="beanIter" classname="java.util.Collection" />

<input type="hidden" name="removeId" value="">
<script type="text/javascript">
function setRemoveId( id )
{
	document.forms['supervisionConditionForm'].elements['removeId'].value = id;
	return true;
}
</script>
<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
	<tr>
		<td class="detailHead"><bean:write name="title" /></td>
	</tr>
	<tr>
		<td>
			<table width="100%" cellspacing="1">
				<tr bgcolor="#cccccc">
					<logic:equal name="mode" value="edit">
						<td class="subHead" nowrap width="1%" align="center"></td>
					</logic:equal>
					<td class="subHead"><bean:write name="tableHeadingName" /></td>
					<td class="subHead"><bean:message key="<%=group2Caption%>" /></td>
					<td class="subHead"><bean:message key="<%=group3Caption%>" /></td>
				</tr>
				<% 
					int rowCount = 0; 
					Iterator iter = beanIter.iterator();
					while ( iter.hasNext() )
					{
						AssociateBean obj = (AssociateBean)iter.next();
				%>
					<% if ( ++rowCount % 2 > 0 ) { %> 
						<tr>
					<% } else { %>
						<tr class="alternateRow">
					<% } %>
						<td width="1%">
						<logic:equal name="mode" value="edit">
							<!-- <input type="submit" name="submitAction" value="Remove" onClick="return setRemoveId('< % =obj.getObjId()%>')"  style="color: blue;background-color: white; border-width: 0px; border-style: none; text-decoration: underline; cursor: pointer;" /> -->
						    <a href="/<msp:webapp/><bean:write name="removeURL" />.do?removeId=<%=obj.getObjId()%>" title='Remove <%=obj.getObjName()%>'>Remove</a>
						</logic:equal>
						</td>
						<td>
							<%=obj.getObjName()%>
						</td>
						<td>
							<%if(obj.getGroup2Name() != null)
									out.println(obj.getGroup2Name());%>
						</td>
						<td>
							<%if(obj.getGroup3Name() != null)
								out.println(obj.getGroup3Name());%>
						</td>
					</tr>
				<%
					}
				%>
			</table>
		</td>
	</tr>
</table>
