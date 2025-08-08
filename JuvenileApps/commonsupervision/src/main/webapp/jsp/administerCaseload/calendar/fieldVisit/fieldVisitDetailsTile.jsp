<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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


<tiles:importAttribute name="fieldVisit"/>

<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td class="detailHead">Field Visit Information</td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding="4" cellspacing="1">
				<logic:notEmpty name="fieldVisit" property="outcomeCd">
					<tr>
						<td class="formDeLabel" nowrap>Outcome</td>
						<td class="formDe" colspan="3"><bean:write name="fieldVisit" property="outcomeDesc"/></td>
					</tr>
				</logic:notEmpty>
				<logic:notEmpty name="fieldVisit" property="narrative">
					<tr>
						<td colspan=4 class=formDeLabel>Narrative</td>
					</tr>
					<tr>
						<td colspan=4 class=formDe>
							<bean:write name="fieldVisit" property="narrative" filter="false"/>&nbsp;
						</td>
					</tr>
				</logic:notEmpty>
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Purpose</td>
					
					<!-- TODO:  Use constant variable for OT-->
					<logic:equal name="fieldVisit" property="purposeCd" value="OT">
						<td class="formDe"><bean:write name="fieldVisit" property="purposeDesc"/></td>
						<td class="formDeLabel" nowrap>Specify Other Purpose</td>
						<td class="formDe"><bean:write name="fieldVisit" property="otherPurpose"/></td>
					</logic:equal>
					
					<logic:notEqual name="fieldVisit" property="purposeCd" value="OT"> 
						<td class="formDe" colspan="3"><bean:write name="fieldVisit" property="purposeDesc"/></td>
					</logic:notEqual>
						
				</tr>
				<tr>
					<td class="formDeLabel" nowrap>Field Visit Type</td>
					<td class="formDe"><bean:write name="fieldVisit" property="fieldVisitTypeDesc"/></td>
					<td class="formDeLabel" nowrap>Field Visit Date</td>
					<td class="formDe">
						<bean:write name="fieldVisit" property="fieldVisitDate" formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
				<logic:equal name="fieldVisit" property="fieldVisitTypeCd" value="SO">
					<tr>
						<td class="formDeLabel" nowrap>Sex Offender Category</td>
						<td class="formDe" colspan=3>
							<bean:write name="fieldVisit" property="sexOffenderCategoryDesc" />
						</td>
					</tr>		
				
				<jims:if name="fieldVisit" property="sexOffenderCategoryCd" value="CZR" op="equal">
				<jims:or name="fieldVisit" property="sexOffenderCategoryCd" value="CZE" op="equal"/>
				<jims:then>				
				<logic:notEmpty name="fieldVisit" property="measurementResultCd">
				 <tr>
					<td class="formDeLabel" nowrap>Measurement Result</td>
					<td class="formDe" colspan=3>
						<bean:write name="fieldVisit" property="measurementResultDesc" />
					</td>
				 </tr>
				</logic:notEmpty>
				</jims:then>
			   </jims:if>	
			  </logic:equal>
				<tr>
					<td class="formDeLabel"  colspan=4>Comments</td>
				</tr>
				<tr>
					<td class="formDe"  colspan=4>
						<bean:write name="fieldVisit" property="comments" />
					</td>
				</tr>
				<tr>
					<td class="formDeLabel"  colspan=4>Noteworthy Conditions</td>
				</tr>
				<tr>
					<td class="formDe"  colspan=4>
						<bean:write name="fieldVisit" property="noteworthyConditions" />&nbsp;
					</td>
				</tr>
				<logic:notEmpty name="fieldVisit" property="methodOfContactCd">
					<tr>
						<td class="formDeLabel" nowrap>Method of Contact</td>
						<td class="formDe" 
							<logic:notEqual name="fieldVisit" property="methodOfContactCd" value="AS">colspan="3"</logic:notEqual>
							><bean:write name="fieldVisit" property="methodOfContactDesc"/></td>
						
						<logic:equal name="fieldVisit" property="methodOfContactCd" value="AS">
					
							<td class="formDeLabel" nowrap>Associate Names</td>
							<td class="formDe">
								<logic:notEmpty name="fieldVisit" property="selectedAssociates">
									<logic:iterate id="assocIter" name="fieldVisit" property="selectedAssociates">
										<bean:write name="assocIter" property="displayLabel"/><br>
									</logic:iterate>
								</logic:notEmpty>
							</td>
						
						</logic:equal>	
					</tr>
				</logic:notEmpty>	
				
				
				<tr>
					<td class="formDeLabel" nowrap>Start Time</td>
					<td class="formDe"><bean:write name="fieldVisit" property="startTime"/> <logic:notEmpty name="fieldVisit" property="startTime"><bean:write name="fieldVisit" property="AMPMId1"/></logic:notEmpty></td>
					<td class="formDeLabel" nowrap>End Time</td>
					<td class="formDe"><bean:write name="fieldVisit" property="endTime"/> <logic:notEmpty name="fieldVisit" property="endTime"><bean:write name="fieldVisit" property="AMPMId2"/></logic:notEmpty></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Supervisee Address</td>
					<td class="formDe"><div><bean:write name="fieldVisit" property="superviseeAddress.streetAddress"/></div>
						<div><bean:write name="fieldVisit" property="superviseeAddress.cityStateZip"/></div></td>
					<td class="formDeLabel" width=1% nowrap>Key Map</td>
					<td class="formDe"><bean:write name="fieldVisit" property="keyMap"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Phone</td>
					<td class="formDe" colspan=3><bean:write name="fieldVisit" property="superviseePhone.formattedPhoneNumber" /></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap>Alternate Address</td>
					<td class="formDe"><div><bean:write name="fieldVisit" property="alternateAddress.streetAddress"/></div>
						<div><bean:write name="fieldVisit" property="alternateAddress.cityStateZip"/></div></td>
					<td class="formDeLabel" width=1% nowrap>Alternate Phone</td>
					<td class="formDe"><bean:write name="fieldVisit" property="alternatePhone.formattedPhoneNumber" /></td>
				</tr>
				<tr>
					<td colspan=4 class=formDeLabel>Address Description</td>
				</tr>
				<tr>
					<td colspan=4 class=formDe><bean:write name="fieldVisit" property="addressDescription"/>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=4 class=formDeLabel>Caution</td>
				</tr>
				<tr>
					<td colspan=4 class=formDe>
						<bean:write name="fieldVisit" property="caution"/>&nbsp;
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>

</html:html>