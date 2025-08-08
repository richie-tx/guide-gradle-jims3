<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@page import="naming.UIConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>







<tiles:importAttribute name="eventsList"/>
<tiles:useAttribute id="view" name="view" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="reorder" name="reorder" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="attrPrefix" name="attrPrefix" classname="java.lang.String" ignore="true" />

<logic:notEmpty name="eventsList">
<table width='100%' cellpadding="2" cellspacing="1" border="0">
	
	
		<tr class="formDeLabel">
		<logic:equal name="view" value="true">
			<td width="1%"><input type=checkbox name=checkAllFvs onclick="toggleCheckAll(this, 'selectedFVEventId')" title="Check/Uncheck All"></td>
			<td><bean:message key="prompt.#" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="sequenceNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/></nobr></td>
		</logic:equal>
		<logic:equal name="view" value="false">
		<td width="3%"> </td>
		</logic:equal>
			<td>Name / SPN <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeName" primarySortType="STRING" sortId="2" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.SSN" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeSSN" primarySortType="STRING" sortId="3" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.purpose" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="purpose" primarySortType="STRING" sortId="4" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.time" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="startTime" primarySortType="DATE" sortId="5" levelDeep="3"/></nobr></td>

			<td><bean:message key="prompt.address" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeAddress.streetName" primarySortType="STRING" sortId="6" levelDeep="3"/></nobr></td>
			<td>Zip <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeAddress.zipCode" primarySortType="STRING" sortId="7" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.keymap" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="keyMap" primarySortType="STRING" sortId="8" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.type" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="fvType" primarySortType="STRING" sortId="9" levelDeep="3"/></nobr></td>
		</tr>
	
	
	<%int RecordCounter = 0;
        String bgcolor = "";%>
	<logic:iterate indexId="eventsCount" id="eventsIter" name="eventsList">
		
		<tr
			class=<%RecordCounter++;
			bgcolor = "alternateRow";
			if (RecordCounter % 2 == 1)
				bgcolor = "normalRow";
			out.print(bgcolor);%>>
			<logic:equal name="view" value="true">
				<td class="borderTopBlack2px" width="1%"><input type="checkbox" name="selectedFVEventId" 
					value="<bean:write name='eventsIter' property='fvEventId' />" 
					onclick=""> 
					<input type="hidden" name=<bean:write name='eventsIter' property='fvEventId' />
						 value=<bean:write name='eventsIter' property='eventStatusCd' />/<bean:write name='eventsIter' property='outcomeCd' /> > 	
				</td>		
			
			
				<td class="borderTopBlack2px" align="center" class="itineraryOrder"><a href="/<msp:webapp/>handleCSFVSelection.do?submitAction=View&selectedFVEventId=<bean:write name='eventsIter' property='fvEventId'/>"><bean:write name="eventsIter" property="sequenceNum"/></a></td>
			</logic:equal>
			<logic:notEqual name="view" value="true">
			<td width="3%" class="borderTopBlack2px"><a href="/<msp:webapp/>handleCSFVSelection.do?submitAction=View&selectedFVEventId=<bean:write name='eventsIter' property='fvEventId'/>">View Details</a> </td>
			</logic:notEqual>
			<td class="borderTopBlack2px">				
				
				<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='eventsIter' property='superviseeId'/>"></logic:notEqual>
				<bean:write name="eventsIter" property="superviseeName"/></a>
				<div><bean:write name="eventsIter" property="superviseeId"/>&nbsp;</div>
			</td>
			<td class="borderTopBlack2px"><bean:write name="eventsIter" property="superviseeSSN"/>&nbsp;</td>
			<td class="borderTopBlack2px"><bean:write name="eventsIter" property="purpose"/></td>
			<td class="borderTopBlack2px"><bean:write name="eventsIter" property="startTime" formatKey="time.format.hhmma"/> - <bean:write name="eventsIter" property="endTime" formatKey="time.format.hhmma"/></td>
			<td class="borderTopBlack2px">
				<a href="javascript:openMapquest('<bean:write name='eventsIter' property='superviseeAddress.streetNum'/>', 
					'<bean:write name='eventsIter' property='superviseeAddress.streetName'/> <bean:write name='eventsIter' property='superviseeAddress.streetType'/>', 
					'<bean:write name='eventsIter' property='superviseeAddress.city'/>', 
					'<bean:write name='eventsIter' property='superviseeAddress.state'/>', 
					'<bean:write name='eventsIter' property='superviseeAddress.zipCode'/>', '')" title="Click to open Mapquest">
				<div>
					<bean:write name="eventsIter" property="superviseeAddress.streetNum"/>
					<bean:write name="eventsIter" property="superviseeAddress.streetName"/>
					<bean:write name="eventsIter" property="superviseeAddress.streetType"/>
				</div><div><bean:write name="eventsIter" property="superviseeAddress.city"/>, 
				<bean:write name="eventsIter" property="superviseeAddress.state"/></div></a>
				<div><bean:write name="eventsIter" property="superviseePhone"/></div>
			</td>
			<td class="borderTopBlack2px"><bean:write name="eventsIter" property="superviseeAddress.zipCode"/>&nbsp;</td>
			<td class="borderTopBlack2px"><bean:write name="eventsIter" property="keyMap"/>&nbsp;</td>
			<td class="borderTopBlack2px"><bean:write name="eventsIter" property="fvType"/>&nbsp;</td>
		</tr>
		<tr
			class=<%
			bgcolor = "alternateRow";
			if (RecordCounter % 2 == 1)
				bgcolor = "normalRow";
			out.print(bgcolor);%>>
			<td valign="top" colspan="12" class="borderTopGrey">
				<table cellpadding="2" cellspacing="1" border="0" width="100%" >
					<tr>
						<td class="formDeLabel" width="1%">Offenses</td>
						<td width="70%">
							<logic:iterate id="offensesIter" name="eventsIter" property="offenses">
								<bean:write name="offensesIter"/><br>
							</logic:iterate>
						</td>
						<td class="formDeLabel" width="1%">Outcome</td>
						<td width="28%">
							<logic:empty name="eventsIter" property="outcomeCd">
								SCHEDULED
							</logic:empty>
							<logic:notEmpty name="eventsIter" property="outcomeCd">
								<bean:write name="eventsIter" property="outcome"/>
							</logic:notEmpty>
						
						</td>

					</tr>
					<tr>
						<td class="formDeLabel" width="1%">Caution</td>
						<td colspan="3"><bean:write name="eventsIter" property="caution"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="1%">Noteworthy Conditions</td>

						<td colspan="3"><div><bean:write name="eventsIter" property="conditions"/></div></td>
					</tr>
				</table>
			</td>
		</tr>
	</logic:iterate>
	
</table>
</logic:notEmpty>




