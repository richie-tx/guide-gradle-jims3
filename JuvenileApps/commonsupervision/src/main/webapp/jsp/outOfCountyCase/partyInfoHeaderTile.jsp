<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Display Party Information Header on top of every OOC pages-->
<!-- 04/13/2006  Hien Rodriguez		Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:importAttribute name="partyHeader" />

<!-- BEGIN DETAIL HEADER TABLE -->
<table cellpadding=1 cellspacing=0 border=0 width=98%>
	<tr>
  		<td bgcolor=#cccccc>
            <table width=100% border=0 cellpadding=2 cellspacing=1>
				<tr>
					<td class=headerLabel width=1%><bean:message key="prompt.name" /></td>
					<td class=headerData colspan="5"><bean:write name="partyHeader" property="name" /></td>
				</tr>
				<tr>
					<td class=headerLabel><bean:message key="prompt.SPN" /></td>
					<td class=headerData><bean:write name="partyHeader" property="spn" /></td>
					<td class=headerLabel width=1%><bean:message key="prompt.SSN" /></td>
					<td class=headerData><bean:write name="partyHeader" property="ssn" /></td>
					<td class=headerLabel width=1%><bean:message key="prompt.dob" /></td>
					<td class=headerData><bean:write name="partyHeader" property="dateOfBirthAsString" /></td>
				</tr>
				<tr>
					<td class=headerLabel><bean:message key="prompt.race" /></td>
					<td class=headerData><bean:write name="partyHeader" property="race" /></td>
					<td class=headerLabel><bean:message key="prompt.sex" /></td>
					<td class=headerData><bean:write name="partyHeader" property="sex" /></td>
					<td class=headerLabel><bean:message key="prompt.SID" /></td>
					<td class=headerData><bean:write name="partyHeader" property="sid" /></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<br>
<!-- END DETAIL HEADER TABLE -->
					