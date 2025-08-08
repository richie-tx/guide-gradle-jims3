<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/15/2011	TSVines	#71951	Field Visit Itinerary - Remove SSN(UI) -->
<!-- 12/14/2011	RCapestani	#71957	Display Alternate Address  -->
<!-- 03/16/2012	RCapestani	#71957	Display Address Description  -->

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
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body>

<tiles:importAttribute name="eventsList"/>
<tiles:useAttribute id="view" name="view" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="reorder" name="reorder" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="attrPrefix" name="attrPrefix" classname="java.lang.String" ignore="true" />

<logic:notEmpty name="eventsList">
<table width='100%' cellpadding="2" cellspacing="1" border="0">
	
	<logic:notEqual name="view" value="true">
		<tr class="formDeLabel">
			<td width="1%"></td>
			<td><bean:message key="prompt.#" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="sequenceNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/></nobr></td>
			<td>Name / SPN <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeName" primarySortType="STRING" sortId="2" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.purpose" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="purpose" primarySortType="STRING" sortId="4" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.time" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="startTime" primarySortType="DATE" sortId="5" levelDeep="3"/></nobr></td>

			<td><bean:message key="prompt.address" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeAddress.streetName" primarySortType="STRING" sortId="6" levelDeep="3"/></nobr></td>
			<td>Zip <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="superviseeAddress.zipCode" primarySortType="STRING" sortId="7" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.keymap" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="keyMap" primarySortType="STRING" sortId="8" levelDeep="3"/></nobr></td>
			<td><bean:message key="prompt.type" /> <nobr><jims2:sortResults beanName="eventsList" results="" primaryPropSort="fvType" primarySortType="STRING" sortId="9" levelDeep="3"/></nobr></td>
		</tr>
	</logic:notEqual>
	<logic:equal name="view" value="true">
		<tr class="formDeLabel">
			<td align="center"><bean:message key="prompt.#" /> </td>
			<td>Name / SPN </td>
			<td><bean:message key="prompt.purpose" /> </td>
			<td><bean:message key="prompt.time" /> </td>

			<td><bean:message key="prompt.address" /> </td>
			<td>Zip </td>
			<td><bean:message key="prompt.keymap" /> </td>
			<td><bean:message key="prompt.type" /> </td>
		</tr>
	</logic:equal>
	
	<%int RecordCounter = 0;
        String bgcolor = "";
        int i = 0;%>
	<logic:iterate indexId="eventsCount" id="eventsIter" name="eventsList">
		<%i++;%>
		<tr
			class=<%RecordCounter++;
			bgcolor = "alternateRow";
			if (RecordCounter % 2 == 1)
				bgcolor = "normalRow";
			out.print(bgcolor);%>>
			<logic:notEqual name="view" value="true">
				<td class="borderTopBlack2px" width="1%"><input type="radio" name="selectedFVEventId" 
					value="<bean:write name='eventsIter' property='fvEventId' />" 
					onclick="setStatus('<bean:write name='eventsIter' property='eventStatusCd' />', 
						'<bean:write name='eventsIter' property='outcomeCd' />')"> 
					<input type="hidden" name=<bean:write name='eventsIter' property='fvEventId' />
						 value=<bean:write name='eventsIter' property='eventStatusCd' />/<bean:write name='eventsIter' property='outcomeCd' /> > 	
				</td>
			</logic:notEqual>
			
			<logic:equal name="reorder" value="true">
				<td class="borderTopBlack2px" align="center" class="itineraryOrder">
					
					<input type="text" name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].sequenceNum" 
						value="<bean:write name='eventsIter' property='sequenceNum'/>" id="sequenceNum" maxlength="2" size="2">
				</td>
			</logic:equal>
			<logic:notEqual name="reorder" value="true">
				<td class="borderTopBlack2px" align="center" class="itineraryOrder"><bean:write name="eventsIter" property="sequenceNum"/></td>
			</logic:notEqual>
			
			<td class="borderTopBlack2px">
				<logic:notEqual name="view" value="true"><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='eventsIter' property='superviseeId'/>"></logic:notEqual>
				<bean:write name="eventsIter" property="superviseeName"/></a>
				<div><bean:write name="eventsIter" property="superviseeId"/>&nbsp;</div>
			</td>
				<td class="borderTopBlack2px"><bean:write name="eventsIter" property="purpose"/></td>
			
			<logic:notEqual name="reorder" value="true">
			 <td class="borderTopBlack2px"><bean:write name="eventsIter" property="startTime" formatKey="time.format.hhmma"/>- <bean:write name="eventsIter" property="endTime" formatKey="time.format.hhmma"/></td>
			</logic:notEqual>
			<logic:equal name="reorder" value="true"> 
			  <td class="borderTopBlack2px"><input type="text" name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].startTime1" value="<bean:write name='eventsIter' property='startTime1'/>" id="startTime1" maxlength="5" size="6" ><select name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].AMPMId1" value="<bean:write name='eventsIter' property='AMPMId1'/>" >
                  <option value="AM">AM</option>
                  <option value="PM" <logic:equal name="eventsIter" property="AMPMId1" value="PM">selected</logic:equal>>PM</option>
                 </select>
			  - <input type="text" name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].endTime1" value="<bean:write name='eventsIter' property='endTime1'/>" id="endTime1" maxlength="5" size="6" ><select name="<bean:write name='attrPrefix'/>[<bean:write name="eventsCount"/>].AMPMId2" value="<bean:write name='eventsIter' property='AMPMId2'/>">
                  <option value="AM">AM</option>
                  <option value="PM"<logic:equal name="eventsIter" property="AMPMId2" value="PM">selected </logic:equal>>PM</option>
                 </select></td>
            </logic:equal>
			<td class="borderTopBlack2px">
				<logic:notEmpty name="eventsIter" property="alternateAddress.streetNum">
					<a href="javascript:openMapquest('<bean:write name='eventsIter' property='alternateAddress.streetNum'/>' , 
						'<bean:write name="eventsIter" property="alternateAddress.streetName"/>',
					 	'<bean:write name='eventsIter' property='alternateAddress.city'/>', 
						'<bean:write name='eventsIter' property='alternateAddress.state'/>', 
						'<bean:write name='eventsIter' property='alternateAddress.zipCode'/>', '')" title="Click to open Mapquest">
					<div>
						<bean:write name="eventsIter" property="alternateAddress.streetNum"/>
						<bean:write name="eventsIter" property="alternateAddress.streetName"/>
						<bean:write name="eventsIter" property="alternateAddress.streetType"/>
						<logic:notEqual name="eventsIter" property="alternateAddress.aptNum" value="">
							<bean:message key="prompt.aptSuite"/>
							<bean:write name="eventsIter" property="alternateAddress.aptNum"/>
						</logic:notEqual>
					</div>
					<div><bean:write name="eventsIter" property="alternateAddress.city"/>, 
						<bean:write name="eventsIter" property="alternateAddress.state"/>
					</div>
					</a>
				</logic:notEmpty>
				<logic:empty name="eventsIter" property="alternateAddress.streetNum">
					<a href="javascript:openMapquest('<bean:write name='eventsIter' property='superviseeAddress.streetNum'/>' , 
						'<bean:write name="eventsIter" property="superviseeAddress.streetName"/>',
					 	'<bean:write name='eventsIter' property='superviseeAddress.city'/>', 
						'<bean:write name='eventsIter' property='superviseeAddress.state'/>', 
						'<bean:write name='eventsIter' property='superviseeAddress.zipCode'/>', '')" title="Click to open Mapquest">
					<div>
						<bean:write name="eventsIter" property="superviseeAddress.streetNum"/>
						<bean:write name="eventsIter" property="superviseeAddress.streetName"/>
						<bean:write name="eventsIter" property="superviseeAddress.streetType"/>
						<logic:notEqual name="eventsIter" property="superviseeAddress.aptNum" value="">
							<bean:message key="prompt.aptSuite"/>
							<bean:write name="eventsIter" property="superviseeAddress.aptNum"/>
						</logic:notEqual>
					</div>
					<div><bean:write name="eventsIter" property="superviseeAddress.city"/>, 
						<bean:write name="eventsIter" property="superviseeAddress.state"/>
					</div>
					</a>
				</logic:empty>
				<div>
					<logic:notEmpty name="eventsIter" property="formattedAlternatePhone">
						<bean:write name="eventsIter" property="formattedAlternatePhone"/>
					</logic:notEmpty>
					<logic:empty name="eventsIter" property="formattedAlternatePhone">
						<bean:write name="eventsIter" property="formattedSuperviseePhone"/>
					</logic:empty>
				</div>
			</td>
			<td class="borderTopBlack2px">
				<logic:notEmpty name="eventsIter" property="alternateAddress.zipCode">
					<bean:write name="eventsIter" property="alternateAddress.zipCode"/> -
					<bean:write name="eventsIter" property="alternateAddress.additionalZipCode"/>&nbsp;
				</logic:notEmpty>
				<logic:empty name="eventsIter" property="alternateAddress.zipCode">
					<bean:write name="eventsIter" property="superviseeAddress.zipCode"/> -
					<bean:write name="eventsIter" property="superviseeAddress.additionalZipCode"/>&nbsp;
				</logic:empty>
			</td>
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
					<logic:notEmpty name="eventsIter" property="addrDesc">
						<tr>
							<td class="formDeLabel" width="1%">Address Description</td>
							<td colspan="3"><div><bean:write name="eventsIter" property="addrDesc"/></div></td>
						</tr>
					</logic:notEmpty>
					<logic:notEmpty name="eventsIter" property="caution">
						<tr>
							<td class="formDeLabel" width="1%">Caution</td>
							<td colspan="3"><bean:write name="eventsIter" property="caution"/></td>
						</tr>
					</logic:notEmpty>
					<logic:notEmpty name="eventsIter" property="conditions">
						<tr>
							<td class="formDeLabel" width="1%">Noteworthy Conditions</td>
							<td colspan="3"><div><bean:write name="eventsIter" property="conditions"/></div></td>
						</tr>
					</logic:notEmpty>
				</table>
			</td>
		</tr>
	</logic:iterate>
	<input type="hidden" name="count" id ="count" value ="<%=i%>">
</table>
</logic:notEmpty>

</body>
</html:html>
