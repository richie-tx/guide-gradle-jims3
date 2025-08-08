<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/09/2011  R Capestani   - 71943 add Field Visit Officer and Radio Call Number -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<tiles:importAttribute name="itinerary"/>
<tiles:useAttribute id="displayEvents" name="displayEvents" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="updatable" name="updatable" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="expanded" name="expanded" classname="java.lang.String" ignore="true" />


<table width='98%' cellpadding=2 cellspacing=0  class=borderTableBlue>
	
	<logic:notEqual name="updatable" value="true">
		<tr class=detailHead>
			<td> <a href="javascript:showHide('itineraryInfo', 'row', '/<msp:webapp/>')" border=0>
				<img border=0 src="/<msp:webapp/>images/contract.gif" name="itineraryInfo"></a>
				Itinerary Information - <bean:write name="itinerary" property="eventDate" formatKey="date.format.mmddyyyy"/>
			</td>
		</tr>
	</logic:notEqual>
	<logic:equal name="updatable" value="true">
		<tr class=detailHead>
			<td><a href="javascript:showHide('itineraryInfo', 'row', '/<msp:webapp/>')" border=0>
				<img border=0 src="/<msp:webapp/>images/contract.gif" name="itineraryInfo"></a>
				Itinerary Information - <bean:write name="itinerary" property="eventDate" formatKey="date.format.mmddyyyy"/>
			</td>
			<td align=right><a href="/<msp:webapp/>displayCSFVItineraryUpdate.do?submitAction=Update">Update</a> | <a href="/<msp:webapp/>displayCSFVRescheduleEvents.do?submitAction=View">Reschedule Multiple Field Visits</a></td>
		</tr>
	</logic:equal>										
											
	<tr id="itineraryInfoSpan"
	<%--	<logic:notEqual name="expanded" value="true">
			class="hidden"
		</logic:notEqual> --%>
		
		>
		<td colspan="2">
			<table width=100% cellpadding=4 cellspacing=1>
				<logic:notEmpty name="itinerary" property="quadrantCd">
					<tr>
						<td class=formDeLabel nowrap>Quadrant</td>
						<td colspan=3 class=formDe><bean:write name="itinerary" property="quadrantDesc"/></td>
					</tr>
				</logic:notEmpty>				
				<tr><!-- FV Officer Name Added -->
					<td class="formDeLabel" nowrap>FV Officer</td>
					<td colspan="3" class="formDe"><bean:write name="itinerary" property="fvOfficer" /></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>In Office From</td>
					<td colspan=3 class=formDe><bean:write name="itinerary" property="inOfficeFrom" /><logic:notEmpty name="itinerary" property="inOfficeFrom"> <bean:write name="itinerary" property="inOfficeAMPMId1" /> to </logic:notEmpty><bean:write name="itinerary" property="inOfficeTo" /> <logic:notEmpty name="itinerary" property="inOfficeTo"><bean:write name="itinerary" property="inOfficeAMPMId2" /></logic:notEmpty></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>In Field From</td>
					<td colspan=3 class=formDe><bean:write name="itinerary" property="inFieldFrom" /> <bean:write name="itinerary" property="inFieldAMPMId1" /> to <bean:write name="itinerary" property="inFieldTo" /> <bean:write name="itinerary" property="inFieldAMPMId2" /></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>Mobile/Pager</td>
					<td colspan=3 class=formDe><bean:write name="itinerary" property="mobilePager.formattedPhoneNumber" /></td>
				</tr>
				<tr><!-- Radio Call Number Added -->
					<td class="formDeLabel" nowrap>Radio Call Number</td>
					<td colspan="3" class="formDe"><bean:write name="itinerary" property="radioCallNum" /></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>Passenger 1</td>
					<td class=formDe colspan=3>
						<bean:write name="itinerary" property="passenger1.formattedName"/>
					</td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>Passenger 2</td>
					<td class=formDe colspan=3>
						<bean:write name="itinerary" property="passenger2.formattedName" />
					</td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>Passenger 3</td>
					<td colspan=3 class=formDe>
						<bean:write name="itinerary" property="passenger3.formattedName" />
					</td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap>Car Type</td>
					<td colspan=3 class=formDe>
					<bean:write name="itinerary" property="carTypeDesc" />
					</td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap width=1%>Mileage In</td>
					<td class=formDe><bean:write name="itinerary" property="mileageIn" /></td>
					<td class=formDeLabel nowrap width=1%>Mileage Out</td>
					<td class=formDe><bean:write name="itinerary" property="mileageOut" /></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap width=1%>Automobile License #</td>
					<td class=formDe colspan=3><bean:write name="itinerary" property="autoLicense" /></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap width=1%>Year</td>
					<td class=formDe><bean:write name="itinerary" property="autoYear" /></td>
					<td class=formDeLabel nowrap width=1%>Make</td>
					<td class=formDe><bean:write name="itinerary" property="autoMake" /></td>
				</tr>
				<tr>
					<td class=formDeLabel nowrap width=1%>Model</td>
					<td class=formDe><bean:write name="itinerary" property="autoModel" /></td>
					<td class=formDeLabel nowrap width=1%>Color</td>
					<td class=formDe><bean:write name="itinerary" property="autoColor" /></td>
				</tr>
			</table>
			<logic:notEmpty name="csCalendarFVForm" property="eventsList">
				<logic:equal name="displayEvents" value="true">
					<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitEventListTile.jsp" flush="true">
						<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="eventsList" />
						<tiles:put name="view" value="true"/>
					</tiles:insert>
				</logic:equal>
			</logic:notEmpty>
		</td>
	</tr>
</table>
