<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>

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

<tiles:importAttribute name="csCalendarOtherForm"/>

<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">
				<bean:write name="csCalendarOtherForm" property="eventTypeDescription"/>
				Information</td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding="2" cellspacing="1">
				<tr>
					<td class="formDeLabel" nowrap width=15%>
						<logic:equal name="csCalendarOtherForm" property="activityFlow" value="reschedule">
							<bean:message key="prompt.3.diamond"/>
						</logic:equal>
						Event Date
					</td>
					<td class="formDe" width=35%>
						<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="reschedule">
							<bean:write name="csCalendarOtherForm" property="eventDate" formatKey="date.format.mmddyyyy"/>
						</logic:notEqual>
						
						<logic:equal name="csCalendarOtherForm" property="activityFlow" value="reschedule">
							<%-- Calendar popup javascript --%> 
							<html:text name="csCalendarOtherForm" property="eventDateStr" size="10" maxlength="10"/>
								<script type="text/javascript" id="js1">
									var cal1 = new CalendarPopup();
									cal1.showYearNavigation();
								</script>
							<A HREF="#" onClick="cal1.select(document.forms[0]['eventDateStr'],'anchor1','MM/dd/yyyy'); return false;"
								NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar" /></A>
							
							</script>
						</logic:equal>
					</td>
					<td class="formDeLabel" nowrap width=15%><bean:message key="prompt.eventName"/></td>
					<td class="formDe" width=35%>
						<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterResults">
							<bean:write name="csCalendarOtherForm" property="eventName"/>
						</logic:equal>
						<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterResults">
							<html:text name="csCalendarOtherForm" property="eventName" size="20" maxlength="20"/>
						</logic:notEqual>
					</td>
					
				</tr>
				<tr>
					<td class="formDeLabel" nowrap>Start Time</td>
					<td class="formDe">
						<html:text name="csCalendarOtherForm" size="6" maxlength="5" property="startTime"/>
							<html:select name="csCalendarOtherForm" property="AMPMId1">
								<html:option value="AM">AM</html:option>
								<html:option value="PM">PM</html:option>
					    </html:select>
					</td>
					<td class="formDeLabel" nowrap>End Time</td>
					<td class="formDe">
						<html:text name="csCalendarOtherForm" size="6" maxlength="5" property="endTime"/>
						 <html:select name="csCalendarOtherForm" property="AMPMId2">
							<html:option value="AM">AM</html:option>
							<html:option value="PM">PM</html:option>
						 </html:select> 
					</td>
				</tr>
				
				<!-- Update a COMPLETED event, ENTER RESULTS flow -->
				<jims:if name="csCalendarOtherForm" property="activityFlow" op="equal" value="enterResults">
					<jims:or name="csCalendarOtherForm" property="statusCd" op="equal" value="C" />
					<jims:then>
					
				
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/>Outcome</td>
							<td class="formDe" colspan=3>
								<html:select name="csCalendarOtherForm" property="outcomeCd">
									<option>Please Select</option>
									<html:optionsCollection name="csCalendarOtherForm" property="filteredFVOutcomeList" value="supervisionCode" label="description"/>
								</html:select>
							</td>
						</tr>
					</jims:then>
				</jims:if>
				
				<logic:equal name="csCalendarOtherForm" property="activityFlow" value="enterResults">
				    <tr>
					    <td colspan=4 class=formDeLabel>
						    Purpose
					    </td>
				    </tr>
				    <tr>
					    <td colspan=4 class=formDe>
							<bean:write name="csCalendarOtherForm" property="purpose"/>&nbsp;
						</td>
					</tr>		
				</logic:equal>
				<logic:notEqual name="csCalendarOtherForm" property="activityFlow" value="enterResults">
				    <tr>
					    <td colspan=4 class=formDeLabel>
						    Purpose (Max Characters Allowed: 3500)
					    </td>
				    </tr>
				    <tr>
				        <td colspan=4 class=formDe>
							<html:textarea name="csCalendarOtherForm" property="purpose" style="width:100%" rows="4" onmouseout="textLimit( this, 3500 )" onkeyup="textLimit( this, 3500 )"/>				
						</td>
				    </tr>
				</logic:notEqual>
				<jims:if name="csCalendarOtherForm" property="activityFlow" op="equal" value="enterResults">
					<jims:or name="csCalendarOtherForm" property="statusCd" op="equal" value="C" />
					<jims:then>
						<tr>
							<td class="formDeLabel" colspan=4><bean:message key="prompt.3.diamond"/>Narrative</td>
						</tr>
						<style>
							.mceEditor{
							width: "100%";
							height: "150"
							}
						</style>
						<tr class=formDe>
							<td colspan=4 align=center>
								<%--<html:textarea styleClass="mceEditor" style="width:100%" rows="12" property="narrative" ondblclick="myReverseTinyMCEFix(this)"/> --%>
								<html:textarea styleClass="mceEditor" style="width:100%" rows="12" name="csCalendarOtherForm"  property="narrative"  ondblclick="myReverseTinyMCEFix(this)"/>
			                    <tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
		                          <tiles:put name="agencyCode" beanName="csCalendarOtherForm" beanProperty="agencyId"/>
		                        </tiles:insert>
							</td>
						</tr>
					</jims:then>
				</jims:if>
				
			</table>
		</td>
	</tr>
</table>

</body>
</html:html>