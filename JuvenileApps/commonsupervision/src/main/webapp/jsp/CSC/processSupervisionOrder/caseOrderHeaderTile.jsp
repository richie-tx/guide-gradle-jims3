<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Display Case Order Header on top of every PASO pages-->
<!-- 02/15/2006  Hien Rodriguez		Create JSP -->
<!-- 06/11/2012  Richard Capestani  - #73568 CSCD: Order Status Date does not display correct date change orderFileDate to statusChangeDate -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!-- BEGIN DETAIL HEADER TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
	<tr class="detailHead">											
		<td><bean:message key="prompt.name" /></td>
		<td><bean:message key="prompt.SPN" /></td>																			
		<td><bean:message key="prompt.CON" /></td>
		<td><bean:message key="prompt.CDI" /></td>
		<td><bean:message key="prompt.case#" /></td>
		<td><bean:message key="prompt.CRT" /></td>
		<td><bean:message key="prompt.offense" /></td>
		<td><bean:message key="prompt.pretrialInterview" /></td>
		<td><bean:message key="prompt.caseFiled" /></td>
		<td><bean:message key="prompt.orderStatus" /></td>
		<td><bean:message key="prompt.version" /></td>
		<td><bean:message key="prompt.statusChangeDate" /></td>
	</tr>
	<tr>											
		<td><bean:write name="supervisionOrderForm" property="name" /></td>
		<td><bean:write name="supervisionOrderForm" property="spn" /></td>
		<td><bean:write name="supervisionOrderForm" property="connectionId" /></td>
		<td><bean:write name="supervisionOrderForm" property="cdi" /></td>																	
		<td><bean:write name="supervisionOrderForm" property="caseNum" /></td>														
		<td><bean:write name="supervisionOrderForm" property="currentCourtNum" /></td>
		<td><bean:write name="supervisionOrderForm" property="offense" /></td>
		<td>Not available yet</td>													
		<td><bean:write name="supervisionOrderForm" property="caseFileDate" formatKey="date.format.mmddyyyy" /></td>
		<td><bean:write name="supervisionOrderForm" property="orderStatus" /></td>
		<td><bean:write name="supervisionOrderForm" property="orderVersion" /></td>
		<td><bean:write name="supervisionOrderForm" property="statusChangeDate" formatKey="date.format.mmddyyyy" /></td>							
	</tr>					
</table>
<!-- END DETAIL HEADER TABLE -->
					