<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	07/28/2005	Create JSP -->
<!-- Clarence Shimek    03/31/2006  Defect#28288 changed last and first name input values so values on form not displayed -->
<!-- Clarence Shimek    01/11/2007  Activity#38306 add multiple submit functionality  -->
<!-- Clarence Shimek    01/24/2007  #38494 revised cancel buttons to transfer to search page -->
<!-- Clarence Shimek    07/27/2007  revised reset button to refresh button -->
<!-- C Shimek           02/05/2009  #56860 add Back to Top  -->
<!-- R Capestani		10/05/2015  #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--CUSTOM TAG LIBRARIES FOR PRESENTION -->

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> -
officerManagerSelection.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<html:javascript formName="managerForm" />
<!--GENERAL UTILITY JAVASCRIPT FOR THIS PAGE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript"
	src="/<msp:webapp/>js/officer/officerManagerSelection.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js"></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message
			key="title.officerProfile" />&nbsp;-&nbsp;<bean:message
			key="title.managerSearch" /></td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="100%">
	<tr>
		<td>
		<ul>
			<li>Enter search criteria and then select "Find" button to perform 	search.</li>
			<li>Select a manager's name in order to view User Profile details.</li>
			<li>Only active managers will be returned with search.</li>
		</ul>
		</td>
	</tr>
	<tr>
		<td class="required">At least one field is required for search.</td>
	</tr>
	<tr>
		<td class="required">+ Indicates Last Name is required to use this search field.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<html:form action="/displayOfficerManagerSearchResults" target="content" focus="managerId">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|174">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="98%" align="center" valign="top"><!-- BEGIN SEARCH TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0"
				width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.manager" />&nbsp;<bean:message
						key="prompt.info" /></td>
				</tr>
				<tr>
					<td align="center">
					<table border="0" cellspacing="1" cellpadding="2" width="100%">
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message
								key="prompt.manager" />&nbsp;<bean:message key="prompt.userId" /></td>
							<td class="formDe"><html:text property="managerId" size="5"
								maxlength="5" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.manager" />&nbsp;<bean:message
								key="prompt.name" /></td>
							<td class="formDe">
							<table>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.last" /></td>
									<td class="formDeLabel">+&nbsp;<bean:message key="prompt.first" /></td>
								</tr>
								<tr>
									<td><html:text property="lastNamePrompt" size="30" maxlength="30" /></td>
									<td><html:text property="firstNamePrompt" size="25" maxlength="25" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"></td>
							<td class="formDe"><html:submit property="submitAction" onclick="return validateSearchFields(this.form);">
								<bean:message key="button.find"></bean:message>
							</html:submit> <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
								<bean:message key="button.refresh"></bean:message>
							</html:submit></td>
						</tr>
					</table>
					</td>
				</tr>

				<logic:notEmpty name="officerForm" property="managerProfiles">
					<tr>
						<td align="center" style="padding: 2px" colspan="2"><bean:size id="managerProfilesSize" name="officerForm"
							property="managerProfiles" /> <br>
						<bean:write name="managerProfilesSize" />&nbsp;search results found. <br>

						<div class="scrollingDiv200">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<table width="100%" border="0" cellpadding="1" cellspacing="1">
									<tr height="20%" class="detailHead">
										<td width="3%">&nbsp;</td>
										<td class="subhead" valign="top"><bean:message
											key="prompt.manager" />&nbsp;<bean:message key="prompt.name" />
										<jims:sortResults beanName="officerForm"
											results="managerProfiles" primaryPropSort="lastName"
											primarySortType="STRING" secondPropSort="firstName"
											secondarySortType="STRING" defaultSort="true"
											defaultSortOrder="ASC" sortId="1" /></td>
										<td class="subhead" valign="top"><bean:message
											key="prompt.userId" />	
										<jims:sortResults beanName="officerForm"
											results="managerProfiles" primaryPropSort="userId"
											primarySortType="STRING" defaultSort="false"
											defaultSortOrder="ASC" sortId="2" /></td>
										<td class="subhead" valign="top"><bean:message
											key="prompt.departmentCode" />	
										<jims:sortResults beanName="officerForm"
											results="managerProfiles" primaryPropSort="departmentId"
											primarySortType="STRING" defaultSort="false"
											defaultSortOrder="ASC" sortId="3" /></td>		
									</tr>

									<logic:iterate id="managerIndex" name="officerForm" property="managerProfiles" indexId="index">
										<tr height="100%" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											<td align="center" width="1%">
												<input type="radio" name="selectedManager" value=<bean:write name='managerIndex' property='userId'/>
												onclick="javascript:showSelectButton();"></td>

											<td><a href="/<msp:webapp/>displayUserProfileDetails.do?action=submit&logonId=<bean:write name='managerIndex' property='userId'/>"
								target="_blank"
								title="click on Name to View User Profile">
								<bean:write name="managerIndex" property="lastName" />,&nbsp;
								<bean:write name="managerIndex" property="firstName" />&nbsp;
								<bean:write name="managerIndex" property="middleName" /></a></td>
								            <td><bean:write name="managerIndex" property="userId" /></td>
								            <td><bean:write name="managerIndex" property="departmentId" /></td> 
										</tr>
									</logic:iterate>
								</table>
								</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
				</logic:notEmpty>
			</table>                                                                                         
			</td>
		</tr>
	</table>
	<!--/td>
		</tr>
	</table-->
	<!--BEGIN BUTTON TABLE-->
	<table align="center" >
		<tr>
			<td>
				<html:button property="button.back" onclick="history.back(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
			</td>
			<logic:notEmpty name="officerForm" property="managerProfiles">
				<td id="selectManagerButton" class="hidden">
					<input type="submit"
					value="<bean:message key='button.selectManager'/>" property="submitAction"  
					onclick="javascript:changeFormActionURL('officerForm', '/<msp:webapp/>selectOfficerManagerSelection.do', false);">
				</td>
			</logic:notEmpty> 
			<td>
				<html:button
					property="org.apache.struts.taglib.html.CANCEL"
					onclick="document.location.href='/security.war/jsp/officers/officerSearch.jsp'">
					<%--	onclick="document.location.href='jsp/officers/officerUpdate.jsp'"> --%>
					<bean:message key="button.cancel"></bean:message>
				</html:button>
			</td>
		</tr>
	</table>
	<!--END BUTTON TABLE-->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
