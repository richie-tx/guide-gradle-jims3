<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td bgcolor="#cccccc">
		<table width="100%" border="0" cellpadding="2" cellspacing="1">
			<tr>
				<td class="headerLabel">Name</td>
				<td class="headerData" colspan="3">
					<logic:equal name="superviseeCreditDataControlForm" property="currentlySupervised" value="true">
						<img style='float:left; padding-right:1px' src='../../images/greenStarCircleIcon.png' title='Supervisee Actively Supervised'>
					</logic:equal>					
					<bean:write name="superviseeCreditDataControlForm" property="lastName" />, <bean:write name="superviseeCreditDataControlForm" property="firstName" />					
				</td>
				<td class="headerLabel">SPN</td>
				<td class="headerData"><bean:write name="superviseeCreditDataControlForm" property="spn" /></td>
			</tr>
			<tr>
				<td class="headerLabel">SSN</td>
				<td class="headerData"><bean:write name="superviseeCreditDataControlForm" property="ssn" /></td>
				<td class="headerLabel">Sex</td>
				<td class="headerData"><bean:write name="superviseeCreditDataControlForm" property="sex" /></td>
				<td class="headerLabel">DOB</td>
				<td class="headerData"><bean:write name="superviseeCreditDataControlForm" property="dateOfBirthStr" /></td>
			</tr>
		</table>
	</td>
	</tr>
</table>
