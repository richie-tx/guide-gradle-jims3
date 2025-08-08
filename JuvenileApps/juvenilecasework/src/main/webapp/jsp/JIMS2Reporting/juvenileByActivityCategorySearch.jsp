<!DOCTYPE HTML>
<%-- User selects the "Activities" tab --%>
<%--MODIFICATIONS --%>
<%-- 11/15/2006	Debbie Williamson	Create JSP --%>
<%-- 02/08/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 04/19/2012 C Shimek	    #73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/16/2012 C Shimek     #73565 added age > 20 check (juvUnder21) to Add/Update buttons --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>





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

<!-- JQUERY LOCAL FRAMEWORK -->
 <%@include file="../jQuery.fw"%> 

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/groups.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework.js"></script>
<!-- JQuery function -->
<script type="text/javaScript" src="/<msp:webapp/>js/searchactivity.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/juvenileByActivityCategorySearch.jsp</title>
<script  type='text/javascript'>


 <logic:notEmpty name='activitiesForm' property='activityCodes'>
	<logic:iterate indexId='groupIterIndex' id='groupIter' name='activitiesForm' property='activityCodes'>
		activityCodes[<bean:write name='groupIterIndex'/>] = new subTypes("<bean:write name='groupIter' property='code' />", "<bean:write name='groupIter' property='description' />");
		<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subTypes">
			var innerGroup = new subTypes("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description" />");
			activityCodes[<bean:write name="groupIterIndex"/>].subTypes[<bean:write name="groupIterIndex2"/>] = innerGroup;
			
			<logic:iterate indexId='groupIterIndex3' id='groupIter3' name='groupIter2' property='subTypes'>
				var innerGroup = new subTypes("<bean:write name='groupIter3' property='code' />", "<bean:write name='groupIter3' property='description' />");
				activityCodes[<bean:write name='groupIterIndex'/>].subTypes[<bean:write name='groupIterIndex2'/>].subTypes[<bean:write name='groupIterIndex3'/>] = innerGroup;
			</logic:iterate>
		</logic:iterate>
	</logic:iterate>
</logic:notEmpty>


function resetPage()
{
	document.getElementById("selectedCategoryId").selectedIndex = 0;
	document.getElementById("selectedTypeId").selectedIndex = 0;
	document.getElementById("selectedDescriptionId").selectedIndex = 0;
	document.getElementById("supervisionTypeId2").selectedIndex = 0;
	document.getElementById("locationId").selectedIndex = 0;
	$("[name='selectSystemActivitieschk']").prop('checked',false);
	document.getElementById("startDateAsStr").value = "";
	document.getElementById("endDateAsStr").value = "";
	document.getElementById("offlastName").value = "";
	document.getElementById("offfirstName").value = "";
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/juvenileCasefileActivitySearch" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|129">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >Activities - Search Activities Across Youth</td>
	</tr>
	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select an Activity Category, Activity Type, Date Range and optional fields if any.</li>
				<li>Select <b>Submit</b> to see a filtered list of activities matching your selections.</li>
				<li><bean:message key="prompt.2.diamond"/>&nbsp;Required fields.</li>			
				<li><b>++</b> indicates Last Name is required to use this search field.</li>
			</ul>
		</td>		
	</tr>
	<%-- <tr>
		<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;</td>
		
	</tr> --%>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

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
	              										<td valign='top' colspan='2' class='detailHead'><bean:message key="prompt.searchactivities"/></td>
	              									</tr>
	              									<tr>
	              										<td colspan='2'>
	              											<table width='100%' border="0" cellpadding="2" cellspacing="1">
	              												<tr>
	              													<td  nowrap='nowrap' class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activityCategory"/></td>
	              													
	              													<td class='formDe'>
					              									<html:select property="selectedCategoryId" styleId="selectedCategoryId" onchange="updateTypeForView(this.form);">
					              											<html:option value=""><bean:message key="select.generic" /></html:option>
					              											<html:optionsCollection property="activityCodes" value="code" label="description" />					              											
					              									</html:select>
					              									
					              									</td>
					              								</tr>
																<tr>
																	<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activityType"/></td>
																	<td class='formDe'>
																		<html:select property="selectedTypeId" styleId="selectedTypeId" onchange="updateActivity(this.form);">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</td>
																</tr>
																
																<tr>
																	<td width='1%' class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.activity"/></td>
																	<td class='formDe'>
																		<html:select property="selectedDescriptionId" styleId="selectedDescriptionId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</td>
																</tr>		
																<tr>								
																	<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateRange"/></td>
																	<td class='formDe' colspan='3'> 
																		<b><bean:message key="prompt.from"/></b>
																		<html:text name="activitiesForm" property="startDateAsStr" size="10" maxlength="10" styleId="startDateAsStr"/>
																		&nbsp;&nbsp;
																		<b><bean:message key="prompt.to"/></b>
																		<html:text name="activitiesForm" property="endDateAsStr" size="10" maxlength="10" styleId="endDateAsStr"/>						    		      
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">+<bean:message key="prompt.juvenileNumber" />
																	</td>
																	<td class="formDe"><html:text property="juvenileNumber" styleId="juvenileNum" size="10" maxlength="8" /></td>
																</tr>
																<tr>
																	<td class="formDeLabel"></td>
																	<td class="formDe" nowrap="nowrap" colspan="1">Include closed casefiles?<input type="checkbox" id="selectclosedCasefileschk" name="selectclosedCasefileschk" checked="true">
																	&nbsp;&nbsp;
																	Include system generated activities?<input type="checkbox" id="selectSystemActivitieschk" name="selectSystemActivitieschk" checked="true">
																	</td>
																	<!-- <td class="formDe" colspan="1"><input type="checkbox" id="selectclosedCasefileschk" name="selectclosedCasefileschk" checked="true"> -->
																	<!-- <td class="formDeLabel" nowrap="nowrap" colspan="1">Include system generated activities?<input type="checkbox" id="selectSystemActivitieschk" name="selectSystemActivitieschk" checked="true"></td> -->
																	<!-- <td class="formDe" colspan="1"><input type="checkbox" id="selectSystemActivitieschk" name="selectSystemActivitieschk" checked="true"> -->
																	</td>
																	<html:hidden styleId="selectclosedCasefilesId" name="activitiesForm" property="selectclosedCasefiles"/>
																	<html:hidden styleId="selectSystemActivitiesId" name="activitiesForm" property="selectSystemActivities"/>
																</tr>
																<%-- <tr>
																<td class="formDeLabel" nowrap="nowrap">Include closed casefiles?</td>
																<td class="formDe"><input type="checkbox" id="selectclosedCasefileschk" name="selectclosedCasefileschk" checked="true">
																<html:hidden styleId="selectclosedCasefilesId" name="activitiesForm" property="selectclosedCasefiles"/>
																</td>
																</tr> --%>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1">+<bean:message key="prompt.supervisionType" /></td>
																	<td class="formDe" colspan="1" width="50%">
																		<html:select property="supervisionTypeId2" styleId="supervisionTypeId2">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection property="supervisionTypes" value="code" label="description" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1">+<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.unit" /></td>
																	<td class="formDe" colspan="3">
																		<html:select property="locationId" styleId="locationId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<%-- <html:optionsCollection property="location" value="locationId" label="locationName" /> --%>
																			<html:optionsCollection property="location" value="locationId" label="locationUnitName" />
																			<html:option value=""><bean:message key="select.all" /></html:option> 
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="12%" nowrap="nowrap" colspan="1">
																		<%-- <span id="poSearch1" ><bean:message key="prompt.2.diamond" /><bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.lastName" /></span>
																		 --%><span id="csSearch1">+<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.lastName" /></span>
																	</td>	
																	<td class="formDe"><html:text property="officerLastName" styleId="offlastName" size="60" maxlength="75" /></td>
																</tr>
																<tr>
																	<td class="formDeLabel">
																		<%-- <span id="poSearch2"><bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.firstName" /></span> --%>
																		<span id="csSearch2">++<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.firstName" /></span>
																	</td>
																	<td class="formDe"><html:text property="officerFirstName" styleId="offfirstName" size="50" maxlength="50" /></td>
																</tr>
																	
																<%-- <logic:empty name="activitiesForm" property="vendorActivity" >
																<input type="hidden"  value="" id="vendor"/>
																</logic:empty>
																<logic:notEmpty name="activitiesForm" property="vendorActivity" >
																<input type="hidden"  value="vendorActivity" id="vendor"/>
																</logic:notEmpty> --%>
															</table>
														</td>
													</tr>
													<tr id="traitsList1">
														<td valign='top' colspan='2'>															
            							<%-- Begin Pagination navigation Row--%>
															<table align="center">
																<tr>
																	<td>
																		<pg:index>
																			<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																				<tiles:put name="pagerUniqueName" value="pagerSearch"/>
																				<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
																			</tiles:insert>
																		</pg:index>
																	</td>
																</tr>
															</table>
            							<%-- End Pagination navigation Row--%>
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
						<div class='spacer'></div>
					</td>
				</tr>
			</table>			
		</td>
	</tr>
	<tr>																	
			<td align="center">
				<html:submit property="submitAction" styleId="viewActivity"><bean:message key="button.submit" /></html:submit> 	
				<input type="button" onclick="return resetPage()" value="<bean:message key='button.refresh'/>"/>
			<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="document.location.href='/security.war/jsp/mainMenu.jsp'">
				<bean:message key="button.cancel"></bean:message>
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