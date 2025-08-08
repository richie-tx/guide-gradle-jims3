<!DOCTYPE HTML>
<%-- User selects the "Search Report Link --%>
<%--MODIFICATIONS --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>





<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<!-- JQuery function -->
<script type="text/javaScript" src="/<msp:webapp/>js/searchProgramReferral.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/programReferralSearch.jsp</title>
<script  type='text/javascript'>
	$(document).ready(function(){
		$("#submitBtn").click({
			console.log("spinner running");
			spinner();
		})
	})

</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayProgramReferralSearch" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|129">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >Program Referral Search - Search for Active Program Referrals</td>
	</tr>
	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0" align="center">
		<tr>
			<td>
				<ul>
					<li>Select or Enter required fields</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><img src=../../images/required.gif title=required alt="required image" hspace=0 vspace=0>Required Fields</td>
		</tr>
		<tr>
			<td class="required">+ At least one of these fields is required.</td>
		</tr>
		<tr>
			<td class="required">++ indicates Last Name is required to use this search field.</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>

			<!-- <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue"> -->
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
	  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td>
  									

									<!-- <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen"> -->
	  									<tr>
	  										<td valign='top' align='center'>
				              					<%-- BEGIN Activities TABLE --%>
												<div class='spacer'></div>
              									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	              									<tr>
	              										<td valign='top' colspan='2' class='detailHead'>Search Program Referral Statistics</td>
	              									</tr>
	              									<tr>
	              										<td colspan='2'>
	              											<table width='100%' border="0" cellpadding="2" cellspacing="1">
	              												<tr>
	              													<td width='2%' class='formDeLabel'>+<bean:message key="prompt.serviceProvider"/></td>
	              													
	              													<td class='formDe'>
					              									<html:select property="selectedValue" styleId="serviceProviderId">
					              											<html:option value=""><bean:message key="select.generic" /></html:option>
					              											<html:optionsCollection property="serviceProviderList" value="juvServProviderId" label="juvServProviderName" />					              											
					              									</html:select>
					              									
					              									</td>
					              								</tr>
																 <tr>
																	<td class='formDeLabel' nowrap='nowrap'>+<bean:message key="prompt.programName"/></td>
																		<td class='formDe'>
																		<html:select property="selectedPrograms" styleId="programIds" multiple="true">
					              											<html:option value=""><bean:message key="select.generic" /></html:option>
					              											<html:optionsCollection property="programNameList" value="providerProgramId" label="programName" />					              											
					              										</html:select>
					              										</td>
																</tr>
																
																<tr>
																	<td class="formDeLabel"nowrap="nowrap" colspan="1">+<bean:message key="prompt.supervisionType" /></td>
																	<td class="formDe" colspan="1" width="50%">
																		<html:select property="supervisionTypeId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection property="supervisionTypes" value="code" label="description" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap="nowrap" colspan="1">+<bean:message key="prompt.location" /></td>
																	<td class="formDe" colspan="3">
																		<html:select property="locationId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection property="juvLocations" value="juvLocationUnitId" label="locationUnitName" />
																		</html:select>
																	</td>
																</tr>
																<tr><td colspan='2'></td></tr>
																<tr>
																	<td class="formDeLabel">
																		<span id="csSearch1">+<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.lastName" /></span>
																	</td>	
																	<td class="formDe"><html:text property="officerLastName" size="60" maxlength="75" styleId="officerLName"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel">
																		<span id="csSearch2">++<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.firstName" /></span>
																	</td>
																	<td class="formDe"><html:text property="officerFirstName" size="50" maxlength="50" styleId="officerFName"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap="nowrap">
																		<span id="csSearch3">++<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.middleName" /></span>
																	</td>
																	<td class="formDe"><html:text property="officerMiddleName" size="50" maxlength="50" styleId="officerMName" /></td>
																</tr>
															</table>
														</td>
													</tr>
												<!-- </table> -->
											</td>
										</tr>
	
										
									<!-- </table> -->
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
						<br>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>			
		</td>
	</tr>
	<tr>																	
			<td align="center">
				<html:submit property="submitAction" styleId="submitBtn"><bean:message key="button.submit" /></html:submit> 
				<html:submit property="submitAction" styleId="refreshBtn"><bean:message key="button.refresh" /></html:submit>
				<html:submit property="submitAction" styleId="cancelBtn"><bean:message key="button.cancel" /></html:submit>	
			</html:button>								
			</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

</pg:pager>

<!-- <script  type='text/javascript'>updateTypeForView(document.forms[0]);</script> -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>