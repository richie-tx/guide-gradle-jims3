<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/19/2008  C Shimek    defect #54106 added code to set cursor based on Search By value -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<html:javascript formName="positionSearchUserForm" /> 
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - adminStaff/positionSearchUser.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript">
function renderSearch(theSelect){
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;
	switch (theSelectValue)
	{
		case "userId":
		show("enterUserSpan", 1)
		document.getElementsByName('userSearchName.lastName')[0].value="";
		document.getElementsByName('userSearchName.firstName')[0].value="";
		document.getElementsByName('userSearchName.middleName')[0].value="";
		document.getElementsByName('userSearchWorkgroupName')[0].value="";
		document.getElementsByName('userSearchCjad')[0].value="";
		show("searchUserSpan", 0)
		document.getElementsByName('userSearchUserId')[0].focus();
		break

		case "userInfo":
		show("enterUserSpan", 1)
		show("searchUserSpan", 1)
		document.getElementsByName('userSearchUserId')[0].value="";
		show("enterUserSpan", 0)
		show("searchUserSpan", 1)
		document.getElementsByName('userSearchName.lastName')[0].focus();
		break
	}
}

function valPage(theFormElem){
	var theSelect=document.getElementsByName("searchBy")[0];
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;
	switch (theSelectValue)
	{
		case "userInfo":
			var newValAr=new Array(4);
			newValAr[0]="userSearchName.lastName";
			newValAr[1]="userSearchCjad";
			newValAr[2]="userSearchWorkgroupName";
			newValAr[3]="userSearchOffTypeId";
			var elemLastName=document.getElementsByName("userSearchName.lastName")[0];
			var elemFirstName=document.getElementsByName("userSearchName.firstName")[0];
			var elemMiddleName=document.getElementsByName("userSearchName.middleName")[0];
			if(trimAll(elemLastName.value)=='' && (trimAll(elemFirstName.value)!='' || trimAll(elemMiddleName.value)!='')){
				alert("Last name is required if searching by First or Middle Name.");
			}
			else{
				var retVal=atLeastOneReq(newValAr);
				if(retVal){
					return validatePositionSearchUserForm(theFormElem);
				}
			}
			return false;
			break;
		case "userId":
			clearAllValArrays();
			customValRequired("userSearchUserId","User ID is required.","");
			addAlphaNumericValidation("userSearchUserId","User ID must be alpha numeric.","");
			
			customValMinLength("userSearchUserId","User ID must be at least 5 characters","5");
			customValMaxLength("userSearchUserId","User ID must be 8 characters or less. ","8");
			return validateCustomStrutsBasedJS(theFormElem);
			break;
	}
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true); " onLoad="renderSearch(document.getElementsByName('searchBy')[0]);">
<html:form action="/displayPositionUserSearchResults" target="content">
<logic:equal name="adminStaffForm" property="action" value="assign">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|23"> 
</logic:equal>
<logic:equal name="adminStaffForm" property="action" value="reassign">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|11"> 
</logic:equal>

<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<tiles:insert page="/jsp/common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="setupTab" />
							</tiles:insert>
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!--  BEGIN BLUE BORDER TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN FEATURE TABS TABLE -->					
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top">
										<tiles:insert page="/jsp/common/manageFeaturesTabs.jsp" flush="true">
											<tiles:put name="tabid" value="positionsTab" />
										</tiles:insert> 
									</td>
								</tr>
							</table>
<!-- END FEATURE TABS TABLE -->					
<!-- BEGIN GREEN BORDER TABLE -->
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
										<table width="100%">
											<tr>
												<td align="center" class="header"><bean:message key="title.CSCD" /> - 
													<logic:equal name="adminStaffForm" property="action" value="assign">
														Assign 
													</logic:equal>
													<logic:equal name="adminStaffForm" property="action" value="reassign">
														Reassign 
													</logic:equal>										
													<bean:message key="prompt.position" /> - User Search	
												</td>
											</tr>
										</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
										<table width="98%" align="center">
											<tr>
												<td align="center" class="errorAlert"><html:errors /></td>
											</tr>
										</table>
<!-- END ERROR TABLE --> 
<!-- BEGIN INSTURCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Enter the User ID of the user or alternatively, Search for the User.</li>
													</ul>
												</td>
											</tr>
										</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN POSTION HEADER TABLE -->
										<table width="98%" cellpadding="0" cellspacing="0">
											<tr class="paddedFourPix">
												<td class="formDeLabel"><bean:message key="prompt.position" /> <bean:message key="prompt.information" /></td>
												<td align="right" class="formDeLabel">&nbsp;</td>
											</tr>
											<tr>
												<td bgcolor="#cccccc" colspan="2">
													<tiles:insert page="/jsp/adminStaff/positionInfoHeaderTile.jsp" flush="true">
														<tiles:put name="position" beanName="adminStaffForm" beanProperty="position"/>
													</tiles:insert>
												</td>
											</tr>
										</table>
<!-- END POSITOIN HEADER TABLE -->
										<br>
<!-- BEGIN SEARCH BY TABLE -->							
										<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr>
												<td class="formDeLabel" width="1%" nowrap>
													<bean:message key="prompt.searchBy" />
												</td>
												<td class="formDe">
													<select name="searchBy" onchange="renderSearch(this)">
														<option value="userId"><bean:message key="prompt.userId" /></option>
														<option value="userInfo" selected><bean:message key="prompt.user" /> <bean:message key="prompt.information" /></option>
													</select>
												</td>
											</tr>
										</table>
<!-- END SEARCH BY TABLE -->														
										<br>
<!-- BEGIN SEARCH BY USERID TABLES -->
										<span class="hidden" id="enterUserSpan">
											<table width="98%" cellpadding="0" cellspacing="0">
												<tr>
													<td class="required" colspan="2"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9"> Required Field</td>
												</tr>
											</table>
											<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td>
														Enter Search Information
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" cellpadding="2" cellspacing="1">
															<tr>
																<td class="formDeLabel" nowrap width="1%"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9"><bean:message key="prompt.userId" /></td>
																<td class="formDe">
																	<html:text name="adminStaffSearchForm" property="userSearchUserId" size="8" maxlength="8"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</span>
<!-- END SEARCH BY USERID TABLES -->							
<!-- BEGIN SEARCH BY USER INFO TABLES -->
										<span class="visible" id="searchUserSpan">
											<table width="98%" cellpadding="0" cellspacing="0">
												<tr>
													<td class="required" colspan="4">
														At least 1 search criteria required. +Last Name is required if searching by First Name/Middle Name.
													</td>
												</tr>
											</table>
											<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td>
														Enter Search Information
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" cellpadding="2" cellspacing="1">
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.plusSign"/><bean:message key="prompt.last"/> <bean:message key="prompt.name"/></td>
																<td class="formDe">
																	<html:text name="adminStaffSearchForm" property="userSearchName.lastName" size="30" maxlength="30"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.first"/> <bean:message key="prompt.name"/></td>
																<td class="formDe">
																	<html:text name="adminStaffSearchForm" property="userSearchName.firstName" size="25" maxlength="25"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.middle"/> <bean:message key="prompt.name"/></td>
																<td class="formDe">
																	<html:text name="adminStaffSearchForm" property="userSearchName.middleName" size="25" maxlength="25"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.cjad"/></td>
																<td class="formDe">
																	<html:text name="adminStaffSearchForm" property="userSearchCjad" size="9" maxlength="9"/>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.cstsOfficerType"/></td>
																<td class="formDe">
																	<html:select property="userSearchOffTypeId">
																		<html:option value=""><bean:message key="select.generic"/></html:option>
																		<html:optionsCollection property="CSTSOfficerTypes" value="code" label="description" />
																	</html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.workgroup"/></td>
																<td class="formDe">
																	<html:text name="adminStaffSearchForm" property="userSearchWorkgroupName" size="25" maxlength="25"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</span>
<!-- END SEARCH BY USER INFO TABLES -->							
										<br>
<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return valPage(this.form)"><bean:message key="button.submit" /></html:submit>
													<html:submit property="submitAction"><bean:message key="button.refresh" /></html:submit>
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->							
							<br>
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
			</td>
		</tr>
	</table>
							
</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>