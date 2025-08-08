<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/22/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>




<tiles:useAttribute id="tableHeader" name="tableHeader" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="isExpandable" name="isExpandable" classname="java.lang.String" ignore="true" />
<tiles:importAttribute name="placementInfo"/>

<table cellpadding='2' cellspacing='1' width='100%' class="borderTableBlue">
	<tr>
		<td class='detailHead'> 
			<logic:present name="isExpandable">
				<logic:equal name="isExpandable" value="true"/>
					<a href="javascript:showHideMulti('placementInfo', 'phChar', 1, '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="placementInfo"></a>
				</logic:equal>
			</logic:present>
			
			<logic:present name="tableHeader">
				<bean:write name="tableHeader"/>
			</logic:present>
			<logic:notPresent name="tableHeader">
				<bean:message key="prompt.placement"/> <bean:message key="prompt.info"/>
			</logic:notPresent>
		</td>
	</tr>
	
	<%String hidden="";%>
	<logic:present name="isExpandable">
			<logic:equal name="isExpandable" value="true"/>
				<%hidden="class=hidden";%>
			</logic:equal>
	</logic:present>
	
	<tr id="phChar0" <%=hidden%>>
		<td>
			<table width='100%' border="0" cellpadding="2" cellspacing="1">
				<tr valign='top'>
					 <td class="formDeLabel"><bean:message key="prompt.entry"/> <bean:message key="prompt.date"/></td>
					 <td class="formDe"><bean:write name="placementInfo" property="entryDate"/></td>
				</tr>
				<tr valign='top'>	
					 <td class="formDeLabel">Is the closest facility available which best meets the child's specific needs?</td>
					 <td class="formDe"><bean:write name="placementInfo" property="closestFacilityAvailable"/></td>
				</tr>
				<tr valign='top'>
					 <td class="formDeLabel">Is the least restrictive environment available which best meets the child's specific needs?</td>
					 <td class="formDe"><bean:write name="placementInfo" property="leastRestrictiveEnv"/></td>
				</tr>
				<tr valign='top'>
					 <td  width="50%" class="formDeLabel">Was proximity to the child's former school district taken into consideration when selecting this facility?</td>
					 <td class="formDe"><bean:write name="placementInfo" property="proximityConsidered"/></td>
				</tr>

				<tr valign='top'>
					 <td class="formDeLabel" colspan="2">Explain why this child requires placement. Discuss the child's behavior and family situation</td>					 
				</tr>
				<tr valign='top'>
					 <td class="formDe" colspan="2"><bean:write name="placementInfo" property="reasonChildRequiresPlacement"/></td>
				</tr>

				<tr valign='top'>
					 <td class="formDeLabel" colspan="2">Explain what specific services are being provided to safely meet the the child's needs</td>					 
				</tr>
				<tr valign='top'>
					 <td class="formDe" colspan="2"><bean:write name="placementInfo" property="specificServicesProvided"/></td>
				</tr>

				<tr valign='top'>
					 <td class="formDeLabel" colspan="2">If the child is placed outside of Texas, explain why this is in the best interest of the child</td>
				</tr>
				<tr valign='top'>
					 <td class="formDe" colspan="2"><bean:write name="placementInfo" property="reasonChildIsPlacedOutsideOfTexas"/></td>
				</tr>

				<tr valign='top'>
					 <td class="formDeLabel">Facility</td>
					 <td class="formDe"><bean:write name="placementInfo" property="facilityStr"/></td>
				</tr>
				<tr valign='top'>
					 <td class="formDeLabel">Facility Release Reason</td>
					 <td class="formDe"><bean:write name="placementInfo" property="facilityReleaseReasonStr"/></td>
				</tr>
				<tr valign='top'>
					 <td class="formDeLabel">Expected Release Date</td>
					 <td class="formDe"><bean:write name="placementInfo" property="expectedReleaseDate"/></td>
				</tr>
				<tr valign='top'>
					 <td class="formDeLabel">Level Of Care</td>
					 <td class="formDe"><bean:write name="placementInfo" property="levelOfCareStr"/></td>
				</tr>
				<tr valign='top'>
					 <td class="formDeLabel">Permanency Plan</td>
					 <td class="formDe"><bean:write name="placementInfo" property="permanencyPlanStr"/></td>
				</tr>

				<tr valign='top'>
					 <td class="formDeLabel" colspan="2">Special Notes</td>					
				</tr>
				<tr valign='top'>
					 <td class="formDe" colspan="2"><bean:write name="placementInfo" property="specialNotes"/></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:html>
