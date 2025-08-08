<!DOCTYPE HTML>
<%------------------MODIFICATIONS ----------------------------%>
<%-- User selects the "Rules" tab on Casefile Details page after searching for a casefile --%>
<%-- 06/07/2006	Debbie Williamson		Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 12/11/2012 C Shimek    #74731 add primary contact indicator next to name --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.UIConstants"%>
<%@ page import="ui.common.UIUtil"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<html:base />
<title><bean:message key="title.heading" /> - guardianPhoneList.jsp</title>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/submitWorkshopCancellation" target="content">
	<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header">Juvenile Casework - Guardian Telephone List</td>
		</tr>
	</table>
	<%-- END HEADING TABLE --%>
	<table align="center" width="98%" cellpadding="3" cellspacing="1"
		class="borderTableBlue">
		<tr>
			<td class="detailHead" colspan="3">Guardian List</td>
		</tr>
		<logic:notEmpty name="juvenileGuardianForm" property="guardianList">

			<logic:iterate id="personsIter"	name="juvenileGuardianForm" property="guardianList">

				<tr>
					<td class="formDeLabel" width='1%' nowrap="nowrap">Name</td>
					<td class="formDe" nowrap="nowrap" colspan="2">
						<bean:write name="personsIter" property="guardianName" />
						<logic:equal name="personsIter" property="primaryContact" value="true">
    						<img alt='primary contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
    					</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" width='1%' nowrap="nowrap">Home Phone</td>
					<td class="formDe" nowrap="nowrap" colspan="2">
						<bean:write name="personsIter" property="homePhone" /> 
						<logic:notEqual name="personsIter" property="homeExtn" value="">
    						Ext. <bean:write name="personsIter" property="homeExtn"/>
    					</logic:notEqual>
    				</td>   				
				</tr>
				<tr>
					<td class="formDeLabel" width='1%' nowrap="nowrap">Office Phone</td>
					<td class="formDe" nowrap="nowrap" colspan="2">
						<bean:write name="personsIter"	property="workPhone" /> 
						<logic:notEqual name="personsIter" property="workExtn" value="">
    						Ext. <bean:write name="personsIter" property="workExtn"/>
    					</logic:notEqual>
    				</td>
				</tr>
				<tr>
					<td class="formDeLabel" width='1%' nowrap="nowrap">Mobile Phone</td>
					<td class="formDe" nowrap="nowrap" colspan="2">
						<bean:write name="personsIter" property="mobilePhone" />
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" width='1%' nowrap="nowrap">Juvenile Mobile Phone</td>
					<td class="formDe" nowrap="nowrap" colspan="2">
						<bean:write name="personsIter" property="juvMobilePhone" />
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" width='1%'>Address</td>
					<td class="formDe" nowrap="nowrap">
					<logic:notEmpty name="personsIter" property="addressId">
					<a href="javascript:openMapquest('<bean:write name="personsIter" property="streetNumber"/>', '<bean:write name="personsIter" property="streetName" /> <bean:write name="personsIter" property="streetTypeId" />', '<bean:write name="personsIter" property="city" />', '<bean:write name="personsIter" property="stateId" />', '<bean:write name="personsIter" property="zipCode" />', '','<bean:write name="personsIter" property="county" />')" title="Click to open Mapquest">       					  								  

				    	<bean:write name="personsIter" property="streetNumber"/>&nbsp;
				    	<logic:notEmpty name="personsIter" property="streetNumSuffix">
				    	   	<logic:notEqual name="personsIter" property="streetNumSuffix" value="">
  								<bean:write name="personsIter" property="streetNumSuffix"/>&nbsp;
	    					</logic:notEqual>
				    	</logic:notEmpty>
						<bean:write name="personsIter" property="streetName"/>
						<logic:notEmpty name="personsIter" property="streetType">
    						<logic:notEqual name="personsIter" property="streetType" value="">
  							&nbsp;<bean:write name="personsIter" property="streetType"/>
	    					</logic:notEqual>
    					</logic:notEmpty>	
    				
						<logic:notEmpty name="personsIter" property="aptNumber">
							<logic:notEqual name="personsIter" property="aptNumber" value="">
    							 &nbsp;<bean:write name="personsIter" property="aptNumber"/>
    						</logic:notEqual>
    					</logic:notEmpty>	

						,&nbsp; 
						
						<bean:write name="personsIter" property="city"/>&nbsp;
						<bean:write name="personsIter" property="state"/> &nbsp;
						<bean:write name="personsIter" property="zipCode"/>
						<logic:notEmpty name="personsIter" property="additionalZipCode">
							-<bean:write name="personsIter" property="additionalZipCode"/>
						</logic:notEmpty>
						
					</a>
					</logic:notEmpty>
					<logic:empty name="personsIter" property="addressId">
						&nbsp;
					</logic:empty>
					</td>
					<td class="formDe" nowrap="nowrap">
			   			<%-- based on the Address validation, display a specific icon --%>
						<logic:notEmpty name="personsIter" property="validated">
							<logic:equal name="personsIter" property="validated" value="Y">
								<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
							</logic:equal>
							<logic:equal name="personsIter" property="validated" value="N">
  								<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
							</logic:equal>
 						</logic:notEmpty>
					</td>
				</tr>

				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
				</tr>
				<br>
			</logic:iterate>
		</logic:notEmpty>

		<logic:empty name="juvenileGuardianForm" property="guardianList">
			<tr>
				<td class="formDeLabel" width='1%' nowrap="nowrap">There are no Guardians for this Juvenile</td>
			</tr>
		</logic:empty>

		<tr>
			<td><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
		</tr>
		<tr>
			<td colspan="2" align='center'><input type="button"
				value='Close Window' onclick="javascript:window.close();">
			</td>
		</tr>
	</table>
	<%-- END INSTRUCTION TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>