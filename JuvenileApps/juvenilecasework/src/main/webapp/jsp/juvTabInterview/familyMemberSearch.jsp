<!DOCTYPE HTML>
<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" /> - familyMemberSearch.jsp</title>
<html:javascript formName="juvenileMemberSearchForm"/>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>


</head>
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayFamilyMemberSearchResults" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|232">
	<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.member"/> <bean:message key="prompt.search"/></td>
		</tr>
	</table>
	<table width="100%">
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
		</tr>   	  
	</table>
	<%-- END HEADING TABLE --%>
	<div class="spacer" ></div>
	<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Please search for the member you want to add. S/he may already exist in the system.</li>
				</ul>
			</td>
		</tr>
	</table>
	<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
	<table cellpadding="1" cellspacing="0" border="0" width="100%">
		<tr>
			<td><%--header info start--%> 
				<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
					<tiles:put name="headerType" value="profileheader" />
				</tiles:insert> <%--header info end--%>
			</td>
		</tr>
	</table>
	<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
	<div class="spacer4px" ></div>
	<%-- BEGIN DETAIL TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><%-- BEGIN GREEN TABS TABLE --%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><%--tabs start--%>
							<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
								<tiles:put name="tabid" value="family" />
								<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert> <%--tabs end--%>
						</td>
					</tr>
					<tr>
						<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
					</tr>
				</table>
				<%-- END GREEN TABS TABLE --%>
				<%-- BEGIN TAB GREEN BORDER TABLE --%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
					<tr>
						<td valign="top"><img src="../../images/spacer.gif" width="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
							<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center">
										<%-- BEGIN INSTRUCTION TABLE --%>
										<table width="100%" border="0">
											<tr>
												<td>
													<ul>
														<li>Enter the search criteria.</li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="required"><bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" />
												</td>
											</tr>
										</table>
										<%-- END INSTRUCTION TABLE --%>
										<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.search"/> Within</td>
												<td class="formDe">
													<html:select name="juvenileMemberSearchForm" property="searchById" >
														<option value="All">All Members</option>
														<!-- <option value="juvNumber">Juvenile Number</option>  bug no #41735-->
													</html:select>
													 <%--<input type=button value=Go onClick="evalSearch(this.form)">--%>
												</td>
											</tr>
										</table>
										<div class="spacer4px" ></div>
										<%-- SEARCH BY JUVENILE NUMBER or ALL MEMBERS--%>
										<span id="juvNumber" class="hidden">
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
												<tr>
													<td class="formDeLabel" width="1%" nowrap>
														<bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.juvenile"/>&nbsp<bean:message key="prompt.number"/>
													</td>
													<td class="formDe">
														<html:text name="juvenileMemberSearchForm" property="juvenileNumber" size="10" maxlength="10" />
													</td>
												</tr>
											</table>
											<br>
										</span>
										<%-- BEGIN SEARCH MEMBERS TABLE --%>
										<span id="All" class="visible">
											<table width="100%" cellspacing="0" cellpadding="2" class="borderTableBlue">
												<tr>
													<td class="detailHead" valign="top"><bean:message key="prompt.searchFor"/> <bean:message key="prompt.members"/></td>
												</tr>
												<tr>
													<td>
														<table width="100%" cellspacing="1">
															<tr>
																<td class="formDeLabel" nowrap>
																	<bean:message key="prompt.diamond" /><bean:message key="prompt.member"/> <bean:message key="prompt.lastName"/>
																</td>
																<td class="formDe">
																	<html:text name="juvenileMemberSearchForm" property="name.lastName" size="30" maxlength="30" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>
																	<bean:message key="prompt.member"/> <bean:message key="prompt.firstName"/>
																</td>
																<td class="formDe">
																	<html:text name="juvenileMemberSearchForm" property="name.firstName" size="25" maxlength="25" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap>
																	<bean:message key="prompt.member"/> <bean:message key="prompt.middleName"/>
																</td>
																<td class="formDe">
																	<html:text name="juvenileMemberSearchForm" property="name.middleName" size="25" maxlength="25" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.sex"/></td>
																<td class="formDe">
																	<html:select name="juvenileMemberSearchForm" property="sexId">
																		<option value="">Please Select...</option>
																		<html:optionsCollection name="juvenileMemberSearchForm" property="sexList"  value="code" label="description"/>
																	</html:select>
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap><bean:message key="prompt.dateOfBirth"/></td>
																<td class="formDe">
																	<html:text styleId="dateOfBirth" name="juvenileMemberSearchForm" property="dateOfBirth" size="10" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.ssn"/></td>
																<td class="formDe">
																	<html:text name="juvenileMemberSearchForm" property="ssn.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />
																	<html:text name="juvenileMemberSearchForm" property="ssn.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" />
																	<html:text name="juvenileMemberSearchForm" property="ssn.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<%-- END Other members List TABLE --%>
										</span>
										<div class=spacer></div>
										<%-- BEGIN BUTTON TABLE --%>
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
													<html:submit styleId="submitMemberSearch" property="submitAction"><bean:message key="button.submit"/></html:submit> 
													<html:submit property="submitAction"><bean:message key="button.refresh" /></html:submit>
													<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
												</td>
											</tr>
										</table>
										<div class=spacer></div>
										<%-- END BUTTON TABLE --%>	
									</td>
								</tr>
							</table>
							<%-- END DETAIL TABLE --%>
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>