<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 06/06/2008	 Mo Chowdhury - Create JSP -->
<!-- 04/06/2009  CShimek      - #57814 removed extraneous phone number display in details -->
<!-- 05/20/2009  Young  - #58957 access to Caseload provides access to Create Task added feature around button -->
<!-- 09/21/2009  CShimek - #61739 revised Case# to use displayCaseNum(no cdi) instead of criminalCaseId(includes cdi) -->
<!-- 02/24/2010  RYoung  - #64103 reactivate radio button and add function to preselect radio -->
<!-- 05/12/2010  RCapestani  - #63952 add Supplemental Reports button -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@page import="naming.Features"%>
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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> -
posttrial/activeCasesView.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
 function selectSingleCase(){
    var rb = document.getElementsByName("selectedCaseValue");
    if (rb.length == 1){
          rb[0].checked = true;
          show("SupplementalReports", 1);
    } 
    if (rb.length > 1){
       for(x=0; x < rb.length; x++){

            if (rb[x].checked == true){
            	  show("SupplementalReports", 1);
            	  break;
            }    
       }    
   }        
 }
 
 function displaySRbutton(){
		show("SupplementalReports", 1);
 }
	
</script>
</head>
<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)"
	onload="selectSingleCase()">
<html:form action="/handleActiveCasesSelection" target="content">
	<input type="hidden" name="helpFile"
		value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|19">

	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top"><!-- BEGIN BLUE TABS TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>
					<!--tabs end  --></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img
						src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<!-- END BLUE TABS TABLE --> <!-- BEGIN BLUE BORDER TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header"><bean:message
								key="title.CSCD" /> - <bean:message
								key="prompt.viewActiveCases" /></td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <%-- Begin Pagination Header Tag--%> <bean:define
						id="paginationResultsPerPage" type="java.lang.String">
						<bean:message key="pagination.recordsPerPage"></bean:message>
					</bean:define> <pg:pager index="center"
						maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
						maxIndexPages="10" export="offset,currentPageNumber=pageNumber"
						scope="request">
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<%-- End Pagination header stuff --%>

						<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
								<ul>
									<li>Click Create Task to manually create a task for the
									next assignment transaction.</li>
									<li>Select Case and click Supplemental Documents to print
									report.</li>
								</ul>
								</td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						<logic:notEmpty name="superviseeSearchForm" property="activeCases">
							<table width="98%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#cccccc">
								<tr>
									<td class="headerLabel"><bean:message key="prompt.SPN" /></td>
									<td class="headerData" colspan="3"><bean:write
										name="superviseeSearchForm" property="spn" /></td>
									<td class="headerLabel"><bean:message key="prompt.SSN" /></td>
									<td class="headerData"><bean:write
										name="superviseeSearchForm" property="ssn" /></td>
								</tr>
								<tr>
									<td class="headerLabel" width="13%"><bean:message
										key="prompt.race" /></td>
									<td class="headerData" width="20%"><bean:write
										name="superviseeSearchForm" property="race" /></td>
									<td class="headerLabel" width="13%"><bean:message
										key="prompt.sex" /></td>
									<td class="headerData" width="20"><bean:write
										name="superviseeSearchForm" property="sex" /></td>
									<td class="headerLabel" width="14%"><bean:message
										key="prompt.dob" /></td>
									<td class="headerData" width="20%"><bean:write
										name="superviseeSearchForm" property="dateOfBirth"
										formatKey="date.format.mmddyyyy" /></td>
								</tr>
							</table>
							<div class="spacer4px"><bean:size id="searchResultsSize"
								name="superviseeSearchForm" property="activeCases" /> <b><bean:write
								name="searchResultsSize" /></b>&nbsp; active case(s) found in search
							results.</div>
							<!-- BEGIN DETAILS TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="1"
								class="borderTableBlue">
								<tr class="detailHead">
									<td width="1%"></td>
									<td><bean:message key="prompt.name" /> <jims:sortResults
										beanName="superviseeSearchForm" results="activeCases"
										primaryPropSort="defendantName" primarySortType="STRING"
										defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
									<td><bean:message key="prompt.case#" /> / <bean:message
										key="prompt.CDI" /> | <bean:message key="prompt.CRT" /> <jims:sortResults
										beanName="superviseeSearchForm" results="activeCases"
										primaryPropSort="criminalCaseId" primarySortType="INTEGER"
										defaultSort="true" defaultSortOrder="ASC" sortId="2" /></td>
									<td><bean:message key="prompt.programUnit" /> / <bean:message
										key="prompt.date" /> <jims:sortResults
										beanName="superviseeSearchForm" results="activeCases"
										primaryPropSort="programUnitName" primarySortType="STRING"
										defaultSort="true" defaultSortOrder="ASC" sortId="3" /></td>
									<td><bean:message key="prompt.supervisor" /> / <bean:message
										key="prompt.date" /> <jims:sortResults
										beanName="superviseeSearchForm" results="activeCases"
										primaryPropSort="supervisorName" primarySortType="STRING"
										sortId="4" /></td>
									<td><bean:message key="prompt.position" /> / <bean:message
										key="prompt.poi" /> / <bean:message key="prompt.officer" />
									/ <bean:message key="prompt.date" /> <jims:sortResults
										beanName="superviseeSearchForm" results="activeCases"
										primaryPropSort="assignedStaffPositionName"
										primarySortType="STRING" sortId="5" /></td>
									<td><bean:message key="prompt.last" /> Acknowledge <bean:message
										key="prompt.userName" /> / <bean:message key="prompt.userId" />
									/ <bean:message key="prompt.date" /> <jims:sortResults
										beanName="superviseeSearchForm" results="activeCases"
										primaryPropSort="acknowledgeUserName" primarySortType="STRING"
										sortId="6" /></td>
								</tr>

								<%int RecordCounter = 0;%>

								<logic:iterate id="searchResultsIndex"
									name="superviseeSearchForm" property="activeCases">
									<logic:equal name="RecordCounter" value="0">
									</logic:equal>
									<%-- Begin Pagination item wrap --%>
									<pg:item>
										<tr
											class='<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow"); %>'>
											<td width="1%"><input type="radio"
												name="selectedCaseValue"
												value='<bean:write name="searchResultsIndex" property="criminalCaseId"/>'
												onclick="displaySRbutton()" /></td>
											<td valign="top"><bean:write name="searchResultsIndex"
												property="superviseeName" /></td>
											<td valign="top">
											<div><a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&amp;selectedValue=<bean:write name="searchResultsIndex" property="supervisionOrderId"/>')">
											<bean:write name="searchResultsIndex"
												property="displayCaseNum" /></a>&nbsp;</div>
											<div><bean:write name="searchResultsIndex"
												property="cdi" /> | <bean:write name="searchResultsIndex"
												property="courtId" /></div>
											</td>
											<td valign="top">
											<div><bean:write name="searchResultsIndex"
												property="programUnitName" />&nbsp;</div>
											<bean:write name="searchResultsIndex"
												property="programUnitAssignDate"
												formatKey="date.format.mmddyyyy" /></td>
											<td valign="top">
											<div><bean:write name="searchResultsIndex"
												property="supervisorName" />&nbsp;</div>
											<bean:write name="searchResultsIndex"
												property="supervisorAllocationDate"
												formatKey="date.format.mmddyyyy" /></td>
											<td valign="top">
											<div><bean:write name="searchResultsIndex"
												property="assignedStaffPositionName" />&nbsp;</div>
											<div><bean:write name="searchResultsIndex"
												property="probationOfficerInd" />&nbsp;</div>
											<div><bean:write name="searchResultsIndex"
												property="officerName" />&nbsp;</div>
											<bean:write name="searchResultsIndex"
												property="officerAssignDate"
												formatKey="date.format.mmddyyyy" /></td>
											<td valign="top">
											<div><bean:write name="searchResultsIndex"
												property="acknowledgeUserName" />&nbsp;</div>
											<div><bean:write name="searchResultsIndex"
												property="acknowledgeUserId" />&nbsp;</div>
											<bean:write name="searchResultsIndex"
												property="acknowledgeDate" formatKey="date.format.mmddyyyy" /></td>
										</tr>
									</pg:item>
									<%-- Begin Pagination navigation Row--%>
								</logic:iterate>
							</table>
							<!-- END DETAIL TABLE -->
							<table align="center">
								<tr>
									<td><pg:index>
										<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
											<tiles:put name="pagerUniqueName" value="pagerSearch" />
											<tiles:put name="resultsPerPage"
												value="<%=paginationResultsPerPage%>" />
										</tiles:insert>
									</pg:index></td>
								</tr>
							</table>
							<%-- End Pagination navigation Row--%>
						</logic:notEmpty>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="10%">
							<tr>
								<td align="center"><html:submit property="submitAction"
									onclick="return disableSubmit(this, this.form)">
									<bean:message key="button.back" />
								</html:submit></td>
								<td id="SupplementalReports" class="hidden"><html:submit
									property="submitAction"
									onclick="return disableSubmit(this, this.form)">
									<bean:message key="button.supplementalReports" />
								</html:submit></td>
								<jims:isAllowed
									requiredFeatures='<%=Features.CSCD_TASKS_CREATE%>'>
									<td><html:submit property="submitAction"
										onclick="return disableSubmit(this, this.form)">
										<bean:message key="button.createTask" />
									</html:submit></td>
								</jims:isAllowed>
								<%--<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>--%>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</pg:pager></td>
				</tr>
			</table>
			<!-- END BLUE BORDER TABLE --></td>
		</tr>
	</table>
	</div>
</html:form>
<div align="center">[<a href="#top">Back to Top</a>]</div>
</body>
</html:html>