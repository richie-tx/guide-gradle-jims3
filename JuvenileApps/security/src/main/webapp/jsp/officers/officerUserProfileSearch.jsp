<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	08/02/2005	Create JSP -->
<!-- C Shimek			09/25/2006	Defect#35329 revise reset button to refresh button -->
<!-- C Shimek		    09/19/2007  Added pagination  -->
<!-- C Shimek           02/05/2009  #56860 add Back to Top  -->
<!-- R Capestani		10/05/2015  #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--CUSTOM TAG LIBRARIES FOR PRESENTION -->

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> -
	officerUserProfileSearch.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="officerUserForm" />
<!--GENERAL UTILITY JAVASCRIPT FOR THIS PAGE -->
<tiles:insert page="/js/officer/officerUserProfileSearch.js"
	flush="true" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
	function validateSearchForm(theForm) {
		if (validateFirstwithLast(theForm)) {
			if ((theForm.lastNamePrompt.value == null || theForm.lastNamePrompt.value == "")
					&& (theForm.logonIdPrompt.value == null || theForm.logonIdPrompt.value == "")
					&& (theForm.departmentNamePrompt.value == null || theForm.departmentNamePrompt.value == "")) {
				alert("At least 1 input value required for search.");
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	function validateFirstwithLast(theForm) {
		if ((theForm.lastNamePrompt.value == null || theForm.lastNamePrompt.value == "")
				&& (theForm.firstNamePrompt.value != null && theForm.firstNamePrompt.value != "")) {
			alert("Last Name is required if First Name is entered.");
			return false;
		}
		//return validateOfficerUserForm(theForm); added the javascript validations as the struts is not working
		return validateOfficerUser(theForm);
	}
	function validateOfficerUser(theForm) {
		var alphaNumWithSymbolsRegExp = /^[ a-zA-Z0-9-\/  ]+$/;//'^[a-zA-Z0-9 '.\-]*$';		
		if ((theForm.lastNamePrompt.value == null || theForm.lastNamePrompt.value == "")
				&& (theForm.firstNamePrompt.value != null && theForm.firstNamePrompt.value != "")) {
			alert("Last Name is required if First Name is entered.");
			return false;
		}
		if (!theForm.logonIdPrompt.value == null || !theForm.logonIdPrompt.value == "") {

			if (theForm.logonIdPrompt.value.length < 3) {
				alert("User ID can not be less than 3 characters");
				return false;
			}

		}
		if (!theForm.lastNamePrompt.value == null || !theForm.lastNamePrompt.value == "") {

			if (theForm.lastNamePrompt.value.length < 2) {
				alert("Last Name can not be less than 2 characters");
				return false;
			}
			if (alphaNumWithSymbolsRegExp.test(theForm.lastNamePrompt.value
					.trim(), alphaNumWithSymbolsRegExp) == false) {
				alert("Last Name must be alphanumeric.");
				theForm.lastNamePrompt.focus();
				return false;
			}

		}
		
		if (!theForm.firstNamePrompt.value == null || !theForm.firstNamePrompt.value == "") {
			if (theForm.firstNamePrompt.value.length < 1) {
				alert("First Name can not be less than 1 character");
				return false;
			}
			if (alphaNumWithSymbolsRegExp.test(theForm.firstNamePrompt.value
					.trim(), alphaNumWithSymbolsRegExp) == false) {
				alert("First Name must be alphanumeric.");
				theForm.firstNamePrompt.focus();
				return false;
			}
		}
		return true;
	}
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)"
	onload="javascript:document.forms[0].logonIdPrompt.focus();">
	<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header">Create&nbsp;<bean:message
					key="title.officerProfile" />&nbsp;Search</td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors>
			</td>
		</tr>
	</table>
	<!-- END HEADING TABLE -->
	<!-- BEGIN INSTRUCTION TABLE -->
	<table width="100%">
		<tr>
			<td>
				<ul>
					<li>To create a new Officer Profile, click on Create New
						Officer button.</li>
					<li>To create a new Officer Profile based on a User Profile,
						perform User Profile search.</li>
					<li>Then select a User Profile and click on Create New Officer
						button.</li>
				</ul></td>
		</tr>
		<tr>
			<td class="required">At least one field is required for search.</td>
		</tr>
		<tr>
			<td class="required">+ Indicates Last Name is required to use
				this search field.</td>
		</tr>
	</table>
	<!-- END INSTRUCTION TABLE -->

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<html:form action="/displayOfficerUserProfileSearchResults"
			target="content">
			<input type="hidden" name="helpFile"
				value="jims2security/jims2_security.htm#|57">
			<tr>
				<td width="98%" align="center" valign="top">
					<table class="borderTableBlue" cellpadding="4" cellspacing="0"
						border="0" width="98%">
						<tr class="detailHead">
							<td class="detailHead"><bean:message key="prompt.search" />&nbsp;for&nbsp;<bean:message
									key="prompt.userProfile" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<table border="0" cellspacing="1" cellpadding="2" width="100%">
									<tr>
										<td class="formDeLabel"><bean:message key="prompt.userId" />
										</td>
										<td class="formDe"><html:text property="logonIdPrompt"
												size="8" maxlength="8" />
										</td>
									</tr>
									<tr>
										<td class="formDeLabel"><bean:message key="prompt.name" />
										</td>
										<td class="formDe">
											<table>
												<tr>
													<td class="formDeLabel"><bean:message
															key="prompt.last" />
													</td>
													<td class="formDeLabel">+<bean:message
															key="prompt.first" />
													</td>
												</tr>
												<tr>
													<td class="formDe"><html:text
															property="lastNamePrompt" size="30" maxlength="75" />
													</td>
													<td class="formDe"><html:text
															property="firstNamePrompt" size="25" maxlength="50" />
													</td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td class="formDeLabel" nowrap width="1%"><bean:message
												key="prompt.departmentName" />
										</td>
										<td class="formDe"><html:text
												property="departmentNamePrompt" size="60" maxlength="60" />
										</td>
									</tr>
									<tr>
										<td class="formDeLabel"></td>
										<td class="formDe"><html:submit property="submitAction"
												onclick="return validateSearchForm(this.form);">
												<bean:message key="button.findUserProfiles" />
											</html:submit> <html:submit property="submitAction"
												onclick="return disableSubmit(this, this.form)">
												<bean:message key="button.refresh" />
											</html:submit></td>
									</tr>
									</html:form>
									<html:form action="/displayOfficerProfileCreate"
										target="content">
										<!-- BEGIN PAGINATION HEADER TAG -->
										<bean:define id="paginationResultsPerPage"
											type="java.lang.String">
											<bean:message key="pagination.recordsPerPage"></bean:message>
										</bean:define>

										<pg:pager index="center"
											maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
											maxIndexPages="10"
											export="offset,currentPageNumber=pageNumber" scope="request">
											<%--   <input type="hidden" name="pager.offset" value="<%= offset %>"> --%>
											<!-- END PAGINATION HEADER TAG -->
											<logic:notEmpty name="officerForm" property="userProfiles">
												<tr>
													<td colspan="2">

														<table border="0" cellspacing="1" cellpadding="2"
															width="100%">
															<tr>
																<td colspan="2" align="center" style="padding: 2px">
																	<bean:size id="userProfilesSize" name="officerForm"
																		property="userProfiles" /> <bean:write
																		name="userProfilesSize" /> search results found. <!--1 search result found:-->
																	<div class="scrollingDiv200">
																		<table border=0 width="100%" cellspacing=1
																			cellpadding=2>
																			<tr height="100%">
																				<td class="formDeLabel" width="1%">&nbsp;</td>
																				<td class="formDeLabel"><bean:message
																						key="prompt.userName" /> <jims:sortResults
																						beanName="officerForm" results="userProfiles"
																						primaryPropSort="lastName"
																						primarySortType="STRING"
																						secondPropSort="firstName"
																						secondarySortType="STRING" defaultSort="true"
																						defaultSortOrder="ASC" sortId="1" /></td>
																				<td class="formDeLabel"><bean:message
																						key="prompt.userId" /> <jims:sortResults
																						beanName="officerForm" results="userProfiles"
																						primaryPropSort="logonId" primarySortType="STRING"
																						defaultSort="false" defaultSortOrder="ASC"
																						sortId="2" /></td>
																				<td class="formDeLabel"><bean:message
																						key="prompt.departmentName" /> <jims:sortResults
																						beanName="officerForm" results="userProfiles"
																						primaryPropSort="departmentName"
																						primarySortType="STRING" defaultSort="false"
																						defaultSortOrder="ASC" sortId="3" />
																				</td>
																			</tr>
																			<logic:iterate id="userIndex" name="officerForm"
																				property="userProfiles" indexId="index">
																				<pg:item>
																					<tr height="100%"
																						class="<%out.print((index.intValue() % 2 == 1)
										? "alternateRow"
										: "normalRow");%>">
																						<td><input type="radio" name="selectedUser"
																							value=<bean:write name='userIndex' property='logonId'/>>
																						</td>
																						<td><bean:write name="userIndex"
																								property="lastName" />,&nbsp; <bean:write
																								name="userIndex" property="firstName" />&nbsp;

																							<bean:write name="userIndex"
																								property="middleName" />
																						</td>
																						<td><bean:write name="userIndex"
																								property="logonId" />
																						</td>
																						<td><bean:write name="userIndex"
																								property="departmentName" />
																						</td>
																					</tr>
																				</pg:item>
																			</logic:iterate>

																		</table>

																	</div> <%-- <br> --%></td>
															</tr>
															<tr>
																<td>
																	<!-- BEGIN PAGINATION NAVIGATOIN TABLE -->
																	<table align="center">
																		<tr>
																			<td><pg:index>
																					<tiles:insert page="/jsp/jimsPagination.jsp"
																						flush="true">
																						<tiles:put name="pagerUniqueName"
																							value="pagerSearch" />
																						<tiles:put name="resultsPerPage"
																							value="<%=paginationResultsPerPage%>" />
																					</tiles:insert>
																				</pg:index></td>
																		</tr>
																	</table> <!-- END PAGINATION NAVIGATION TABLE --></td>
															</tr>

														</table></td>
												</tr>
											</logic:notEmpty>
								</table> <%-- BEGIN PAGINATION CLOSING TAG --%> </pg:pager> <%-- END PAGINATION CLOSING TAG --%>
							</td>
						</tr>
					</table></td>
			</tr>

			<tr>
				<td align="center">
					<table>
						<tr>
							<td><html:hidden name="officerForm" property="action"
									value="create" /> <html:submit property="submitAction">
									<bean:message key="button.createNewOfficer" />
								</html:submit></td>
						</tr>
					</table></td>
			</tr>
		</html:form>
	</table>

	<div align="center">
		<script type="text/javascript">
			renderBackToTop()
		</script>
	</div>
</body>
</html:html>