<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
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
<title>Common Supervision - common/enterAddressTile.jsp</title>

<tiles:importAttribute name="address"/>

<tiles:useAttribute id="attrPrefix" name="attrPrefix" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="title" name="title" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="headerColor" name="headerColor" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="originatePage" name="originatePage" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="numberOfDiamonds" name="numberOfDiamonds" classname="java.lang.String" ignore="true" />


<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>

<script>
//***Begin TRIM Script
function Trim( s )
{
	// Remove leading spaces and carriage returns
	while( (s.substring( 0,1 ) == ' ') || (s.substring( 0,1 ) == '\n') || (s.substring( 0,1 ) == '\r') )
	{ 
		s = s.substring( 1, s.length ); 
	}
	
	// Remove trailing spaces and carriage returns
	while(( s.substring( s.length -1, s.length ) == ' ' ) || 
			(s.substring( s.length -1, s.length ) == '\n') ||
			(s.substring( s.length -1, s.length ) == '\r') )
	{ 
		s = s.substring( 0, s.length -1 ); 
	}
	
	return s;
}

function validateStreetName(theForm)
{    
    clearAllValArrays();
    customValMaxLength("<bean:write name='attrPrefix'/>streetName", "Street Name cannot be validated if it is more than 28 characters",28);
    return validateCustomStrutsBasedJS(theForm);
}    
</script>
</head>
<body>


<table width="100%" cellpadding="1" cellspacing="1">
	<!-- BEGIN ADDRESS INFORMATION TABLES -->
	<tr>
		<td class=detailHead>
			<table width=100% cellpadding=0 cellspacing=0>
				<tr class=
					<logic:equal name="headerColor" value="blue">
						detailHead
					</logic:equal>
					<logic:notEqual name="headerColor" value="blue">
						formDeLabel
					</logic:notEqual>
					>
					<td>
						<bean:write name="title"/>
					</td>
					<td align=right><bean:message key="prompt.addressStatus"/>: <span class=errorAlert>	
						<logic:equal name="address" property="addressStatus" value="">
							UNPROCESSED
						</logic:equal>       	    
						<logic:equal name="address" property="addressStatus" value="U">
							UNPROCESSED
						</logic:equal>
						<logic:equal name="address" property="addressStatus" value="Y">
							VALID
						</logic:equal>
						<logic:equal name="address" property="addressStatus" value="N">
							INVALID
						</logic:equal>
						&nbsp;</span>
					 </td>
				</tr>
				<tr class=
					<logic:equal name="headerColor" value="blue">
						detailHead
					</logic:equal>
					<logic:notEqual name="headerColor" value="blue">
						formDeLabel
					</logic:notEqual>
				>
					<td colspan=2 align=center>
						<input type=button value="<bean:message key='button.validate' />" name="submitAction" 
							onClick="return validateStreetName(this.form) && validateAddrAction('csCalendarFVForm','/<msp:webapp/>validateCSFVAlternateAddress.do',
								'', 'currentFieldVisit.alternateAddress','<bean:write name="originatePage"/>', true);"></input>
							<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="return displayResearchWindow();">
							  <bean:message key="button.research"></bean:message>
							</html:button>	
																							
					
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" cellpadding="1" cellspacing="1" border=0>
				<tr>
					<td class=formDeLabel>
						<img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><logic:equal name="numberOfDiamonds" value="2"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0>
						</logic:equal>
						<bean:message key="prompt.streetNumber"/>
					</td>
					<td class=formDeLabel colspan="2">
						<img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><logic:equal name="numberOfDiamonds" value="2"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0>
						</logic:equal>
						<bean:message key="prompt.streetName"/>
					</td>
				</tr>
				<tr>
					<td class=formDe>
						<input type="text" name="<bean:write name='attrPrefix'/>streetNumber" id="streetNum"
							value="<bean:write name='address' property='streetNumber'/>" maxlength="9" size="9">
					</td>
					<td class=formDe colspan="2">
						<input type="text" name="<bean:write name='attrPrefix'/>streetName" id="streetName"
							value="<bean:write name='address' property='streetName'/>" maxlength="50" size="30">
					</td>
				</tr>
				<tr>
					<td class=formDeLabel><bean:message key="prompt.streetType"/></td>
					<td class=formDeLabel nowrap colspan="2"><bean:message key="prompt.aptSuite"/></td>
				</tr>
				<tr>
					<td class=formDe>
						<html:select
							name="address" property="streetTypeId" size="1">
							<html:option value=""><bean:message key="select.generic"/></html:option>
							<jims:codetable codeTableName="<%=PDCodeTableConstants.STREET_TYPE%>"/></html:select>
						<script language="javascript">
							document.forms[0]["streetTypeId"].name="<bean:write name='attrPrefix'/>streetTypeId";
						</script>		
					</td>						
					<td class=formDe colspan="2">
						<input type="text" name="<bean:write name='attrPrefix'/>aptNumber" 
							value="<bean:write name='address' property='aptNumber'/>" maxlength="10" size="10">
					</td>
				</tr>
				<tr>
					<td class=formDeLabel>
						<img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><logic:equal name="numberOfDiamonds" value="2"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0>
						</logic:equal>
						<bean:message key="prompt.city"/>
					</td>
					<td class=formDeLabel>
						<img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><logic:equal name="numberOfDiamonds" value="2"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0>
						</logic:equal>
						<bean:message key="prompt.state"/>
					</td>
					<td class=formDeLabel>
						<img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0><logic:equal name="numberOfDiamonds" value="2"><img src="/<msp:webapp/>images/required.gif" title=required alt="required image" hspace=0 vspace=0>
						</logic:equal>
						<bean:message key="prompt.zipCode"/>
					</td>
				</tr>
				<tr>
					<td class=formDe><input type="text" name="<bean:write name='attrPrefix'/>city" 
							value="<bean:write name='address' property='city'/>" id="city" maxlength="15" size="15"></td>
					<td class="formDe"><html:select
											name="address" property="stateId" size="1"
											styleId="stateId"
											onchange="javascript:enableCounty(this, 'countyId')">
											<html:option value="" ><bean:message key="select.generic"/></html:option>
											<jims:codetable codeTableName="<%=PDCodeTableConstants.STATE_ABBR%>"/>
										</html:select>
										<script language="javascript">
											document.forms[0]["stateId"].name="<bean:write name='attrPrefix'/>stateId";
										</script>	
					</td>
					<td class=formDe>
						<input type="text" name="<bean:write name='attrPrefix'/>zipCode" 
							value="<bean:write name='address' property='zipCode'/>" id="zipCode" maxlength="5" size="5"
							onkeyup="return autoTab(this, 5);">
						<input type="text" name="<bean:write name='attrPrefix'/>additionalZipCode" 
							value="<bean:write name='address' property='additionalZipCode'/>" maxlength="5" size="5"
							onkeyup="return autoTab(this, 4);">
 					</td>
				</tr>
				<tr>
					<td class=formDeLabel><bean:message key="prompt.addressType"/></td>
					<td class=formDeLabel colspan="2"><bean:message key="prompt.county"/></td>
				</tr>
				<tr>
					<td class=formDe>
						<html:select name="address" property="addressTypeCode" styleId="addressTypeId">
							<html:option value="" ><bean:message key="select.generic"/></html:option>
							<jims:codetable codeTableName="<%=PDCodeTableConstants.ADDRESS_TYPE%>"/>
						</html:select> 
						<script language="javascript">
							document.forms[0]["addressTypeId"].name="<bean:write name='attrPrefix'/>addressTypeCode";
						</script>
					</td>
					<td class="formDe" colspan="2">
						<html:select name="address" property="countyId" styleId="countyId" disabled="true">
											<html:option value="" ><bean:message key="select.generic"/></html:option>
											<jims:codetable codeTableName="<%=PDCodeTableConstants.COUNTY%>"/>
						</html:select> 
						<script language="javascript">
							document.forms[0]["countyId"].name="<bean:write name='attrPrefix'/>countyId";
							
							var stateDropDown = document.forms[0]["stateId"];
							var countyDropDown = document.forms[0]["countyId"];
							var selectedState = stateDropDown.options[ stateDropDown.selectedIndex ].value;
							if(selectedState == "TX") {
								countyDropDown.disabled=false;
							} else {
								countyDropDown.selectedIndex = 0;
								countyDropDown.disabled=true;
							}
 							
						</script>	
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<!-- END ADDRESS INFORMATION TABLE -->
	 <html:hidden property="validStreetNum" value="" />
		  <html:hidden property="validStreetName" value="" />
		  <html:hidden property="validZipCode" value="" />
		  <html:hidden property="validAddrNum" value="" />
  	  <html:hidden property="inputPage" value="" />
		  <html:hidden property="currentAddressInd" value="" />
</table>
</html:html>