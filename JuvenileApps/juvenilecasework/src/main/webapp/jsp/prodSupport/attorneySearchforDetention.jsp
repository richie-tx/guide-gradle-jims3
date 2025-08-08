<!DOCTYPE HTML>
<!-- User selects the "Search for Offense Code" on Transferred Offenses Create page -->
<%--MODIFICATIONS --%>
<%--06/11/2013 CShimek CREATE JSP --%>
<%-- 08/31/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>




<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" />&nbsp;<bean:message
		key="title.juvenileCasework" />&nbsp;- attorneySearchforDetention.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">
function selectSingleAttorney()
{
	document.getElementById("attorneyName").focus();
	document.getElementById("selectBtn").disabled = true;
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1 && typeof(document.forms[0].pagerSearch) == "undefined"){
		rbs[0].checked = true;
		document.getElementById("selectBtn").disabled = false;
	}
}

function enableSelectButton()
{
	document.getElementById("selectBtn").disabled = false;
}
</script>
</head>
<body topmargin="0" leftmargin="0" onload="selectSingleAttorney();" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >	
	<html:form action="/PerformUpdateDetentionCourtRecord"
		onsubmit="return this;"
		focus="attorneyName">
		<!-- BEGIN HEADING TABLE -->
		<div class="page-header">
			<h2>Update Detention Court Record</h2>
			<h2>Search Attorney Name</h2>
		</div>
		<!-- END HEADING TABLE -->
		<!-- BEGIN ERROR TABLE  -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors>
				</td>
			</tr>
			<tr>
				<td align="center" class="errorAlert"><bean:write
						name='ProdSupportForm' property='errMessage' />
				</td>
			</tr>
		</table>
		<!-- END ERROR TABLE  -->
		<!-- BEGIN INSTRUCTION TABLE -->
		<table width="100%">
			<tr>
				<td>
					<ul>
						<li>Enter search criteria and then select "Find Attorney
							Search" button to perform search.</li>
						<li>To select a record click "Select" button.</li>
					</ul></td>
			</tr>
			<tr>
				<td class="required">&nbsp;Attorney Name is required for
					search.</td>
			</tr>
		</table>
		<!-- END INSTRUCTION TABLE -->
		<%-- BEGIN DETAILS TABLE --%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="98%" align="center" valign="top">
					<%-- BEGIN BLUE BORDER DETAILS TABLE --%>
					<table width="98%" cellpadding="0" cellspacing="0" border="0"
						class="borderTableBlue">
						<tr>
							<td align='center'>
								<%-- BEGIN SEARCH INPUT TABLE --%>
								<table border="0" cellspacing='1' cellpadding="2" width="100%">
									<tr class='crtDetailHead'>
										<td colspan="2" class='crtDetailHead'><bean:message
												key="prompt.searchFor" /> <bean:message
												key="prompt.attorneyName" />
										</td>
									</tr>
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
												key="prompt.2.diamond" /> <bean:message
												key="prompt.attorneyName" />
										</td>
										<td class="formDe"><html:text name='ProdSupportForm'
												property='attorneyName' size="30" maxlength="50"
												styleId="attorneyName" />
										</td>
									</tr>

									<tr>
										<td class="formDeLabel"></td>
										<td class="formDe"><html:submit property="submitAction"
												 styleId="submitBtn">
												<bean:message key="button.findAttorney" /></html:submit>
											 <html:submit property="submitAction">
												<bean:message key="button.refresh" />
											</html:submit> </td>
									</tr>
								</table> <%-- END SEARCH INPUT TABLE --%>
								<div class="spacer4px"></div> <logic:notEmpty
									name="ProdSupportForm" property="attorneyDataList">
									<!-- BEGIN SEARCH RESULTS TABLE -->
									<table border="0" cellspacing='1' cellpadding="2" width="100%">
										<tr>
											<td align="center" style="padding: 2px" colspan="2"><br>
												<bean:size id="collSize" name="ProdSupportForm"
													property="attorneyDataList" /> <bean:write name="collSize" />
												search results found.</td>
										</tr>
										<tr>
											<td>
												<!-- BEGIN SEARCH RESULTS LIST TABLE -->
												<table border="0" width="100%" cellspacing="1"
													cellpadding="2">
													<tr bgcolor="#cccccc">
														<td width="1%"></td>
														<td class="subhead"><bean:message
																key="prompt.barNumber" /> <jims2:sortResults
																beanName="ProdSupportForm" results="attorneyDataList"
																primaryPropSort="barNum" primarySortType="STRING"
																defaultSort="true" defaultSortOrder="ASC" sortId="1" />
														</td>
														<td class="subhead"><bean:message
																key="prompt.attorneyName" /> <jims2:sortResults
																beanName="ProdSupportForm" results="attorneyDataList"
																primaryPropSort="attyName" primarySortType="STRING"
																defaultSortOrder="ASC" sortId="2" />
														</td>
													</tr>

													<!-- Begin Pagination Header Tag -->
													<bean:define id="paginationResultsPerPage"
														type="java.lang.String">
														<bean:message key="pagination.recordsPerPage" />
													</bean:define>
													<pg:pager index="center"
														maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
														maxIndexPages="10"
														export="offset,currentPageNumber=pageNumber"
														scope="request">
														<input type="hidden" name="pager.offset"
															value="<%=offset%>">
														<!--  End Pagination header stuff -->
														<logic:iterate id="offIndex" name="ProdSupportForm"
															property="attorneyDataList" indexId="index">
															<!-- Begin Pagination item wrap -->
															<pg:item>
																<%-- <bean:define id="offId" name="offIndex" property="barNum" type="java.lang.String"/> --%>
																<tr
																	class="<%out.print((index.intValue() % 2 == 1)
											? "alternateRow"
											: "normalRow");%>"
																	height="100%">
																	<td align="center">
																		<%-- <html:radio name="ProdSupportForm" property="selectedValue" value='<%=offId%>' onclick="enableSelectButton();" /> --%>
																		<input type="radio" name="selectedValue"
																		indexed="true" onclick="enableSelectButton();"
																		value="<bean:write name='offIndex' property='barNum'/>" />
																	</td>
																	<td><bean:write name='offIndex' property='barNum' />
																	</td>
																	<td><bean:write name='offIndex'
																			property='attyName' />
																	</td>
																</tr>
															</pg:item>
															<!-- End Pagination item wrap -->
														</logic:iterate>
												</table> <!-- END SEARCH RESULTS LIST TABLE										
Begin Pagination navigation TABLE -->
												<table align="center">
													<tr>
														<td><pg:index>
																<tiles:insert page="/jsp/jimsPagination.jsp"
																	flush="true">
																	<tiles:put name="pagerUniqueName" value="pagerSearch" />
																	<tiles:put name="resultsPerPage"
																		value="<%=paginationResultsPerPage%>" />
																</tiles:insert>
															</pg:index></td>
													</tr>
												</table> <!-- End Pagination navigation TABLE
Begin Pagination Header Closing Tag --> </pg:pager> <!-- End Pagination Header Closing Tag -->
											</td>
										</tr>
									</table>
									<!-- END SEARCH RESULTS TABLE	 -->
								</logic:notEmpty></td>
						</tr>
					</table> <%-- END BLUE BORDER DETAILS TABLE --%></td>
			</tr>
		</table>
		<%-- BEGIN DETAILS TABLE --%>
		<div class="spacer4px"></div>
		<%-- BEGIN BUTTON TABLE --%>
		<table border="0" width="100%">
			<tr>
				<td align="center"><html:submit property="submitAction" styleId="backBtn">
						<bean:message key="button.back" />
					</html:submit> 
					<html:submit property="submitAction" styleId="selectBtn" disabled="true">
						<bean:message key="button.select" />
					</html:submit> <html:submit property="submitAction" styleId="cancelBtn">
						<bean:message key="button.cancel" />
					</html:submit></td>
			</tr>
		</table>
		<%-- END BUTTON TABLE --%>
		<%-- <html:hidden styleId="barNum" name="ProdSupportForm"
			property="referralId" /> --%>
	</html:form>
	<div align="center">
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>