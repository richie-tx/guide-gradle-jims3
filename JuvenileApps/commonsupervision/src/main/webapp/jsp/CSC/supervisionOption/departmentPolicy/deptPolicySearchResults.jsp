<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/29/2005	 mjt - Create JSP -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to copy, update and delete buttons -->
<!-- 04/03/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around Update Association button -->
<!-- 01/16/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!-- LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


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
<title><bean:message key="title.heading" /> - deptPolicySearchResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script type="text/javascript">
function checkForSingleResult() {
    var rbs = document.getElementsByName("policyId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>

<bean:define id="departmentGroup1Caption" value="prompt.group1" type="java.lang.String"/>
<bean:define id="departmentGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="departmentGroup3Caption" value="prompt.group3"  type="java.lang.String"/>
<bean:define id="deptPolicyTitle" value="prompt.policyTableTitle" type="java.lang.String"/>
<bean:define id="btnsEnabled" value="false" type="java.lang.String"/>
<jims2:isAllowed requiredFeatures="CS-DPOL-COPY">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
<jims2:isAllowed requiredFeatures="CS-DPOL-UPDATE">
	<% btnsEnabled="true"; %>
</jims2:isAllowed>
	
<jims2:isAllowed requiredFeatures="CS-DPOL-DELETE">
<% btnsEnabled="true"; %>
</jims2:isAllowed>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult()">
<html:form action="/handleDepartmentPolicySelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|87">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
		</tr>
		<tr>
			<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign=top><!--tabs start--> <tiles:insert
						page="../../../common/commonSupervisionTabs.jsp" flush="true" /> <!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif"
						height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center><!-- BEGIN HEADING TABLE -->
					<table width=100%>
						<tr>
							<td align="center" class="header"><bean:message
								key="<%=deptPolicyTitle%>" />&nbsp;<bean:message
								key="title.searchResults" /></td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> 
					<%-- Begin Pagination Header Tag--%>
					<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
					<br>
					<pg:pager
					    index="center"
					    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
					    maxIndexPages="10"
					    export="offset,currentPageNumber=pageNumber"
					    scope="request">
					    <input type="hidden" name="pager.offset" value="<%= offset %>">
					<%-- End Pagination header stuff --%>
					<!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Click on a hyperlink to View.</li>
								 <logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
								<li>Or, select radio button, then select appropriate button.</li>
								</logic:notEqual>
							</ul>
							</td>
						</tr>
					</table>

					<!-- BEGIN  TABLE -->
					<div align=center><logic:empty name="departmentPolicyForm"
						property="searchResults">
						<bean:message key="error.no.search.results.found" />
					</logic:empty> <logic:notEmpty name="departmentPolicyForm"
						property="searchResults">
						<bean:size id="departmentPolicyResultsSize"
							name="departmentPolicyForm" property="searchResults" />
						<bean:write name="departmentPolicyResultsSize" />
						
						<bean:message key="<%=departmentGroup1Caption%>" />: 
								<logic:notEmpty name="departmentPolicyForm" property="group1Name">
									<logic:notEqual name="departmentPolicyForm" property="group1Name" value="">
										<bean:write	name="departmentPolicyForm" property="group1Name" />&nbsp;
									</logic:notEqual>
									<logic:equal name="departmentPolicyForm" property="group1Name" value="">>
										All &nbsp;
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="departmentPolicyForm" property="group1Name">
									All &nbsp;
								</logic:empty>	

						<logic:notEmpty name="departmentPolicyForm" property="group2Name">
							<bean:message key="<%=departmentGroup2Caption%>" />: <bean:write
								name="departmentPolicyForm" property="group2Name" />&nbsp;</logic:notEmpty>
						<logic:notEmpty name="departmentPolicyForm" property="group3Name">
							<bean:message key="<%=departmentGroup3Caption%>" />: <bean:write
								name="departmentPolicyForm" property="group3Name" />&nbsp;</logic:notEmpty>
						<logic:equal name="departmentPolicyForm"
							property="allCourtsSelected" value="true">
							<bean:message key="prompt.courtsAll" />
						</logic:equal>
						<logic:notEqual name="departmentPolicyForm"
							property="allCourtsSelected" value="true">Selected Courts</logic:notEqual>
					</logic:notEmpty></div>

					<!-- next -property- had: deptPolicySearchResults --> <logic:notEmpty
						name="departmentPolicyForm" property="searchResults">
						<table width="98%" border="0" cellpadding="2" cellspacing="1"
							class=borderTableBlue>
							<tr class="detailHead">
								<td width=1%></td>
								<td><bean:message key="prompt.policyTableTitle" />&nbsp;<bean:message
									key="prompt.name" />
									<jims2:sortResults beanName="departmentPolicyForm" results="searchResults" primaryPropSort="name" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4"/>	
								</td>
								<td><bean:message key="<%=departmentGroup1Caption%>" />
									<jims2:sortResults beanName="departmentPolicyForm" results="searchResults" primaryPropSort="group1Name" primarySortType="STRING" sortId="2" levelDeep="4"/>
								</td>
								<td><bean:message key="<%=departmentGroup2Caption%>" />
									<jims2:sortResults beanName="departmentPolicyForm" results="searchResults" primaryPropSort="group2Name" primarySortType="STRING" sortId="3" levelDeep="4"/>
								</td>
								<td><bean:message key="<%=departmentGroup3Caption%>" />
									<jims2:sortResults beanName="departmentPolicyForm" results="searchResults" primaryPropSort="group3Name" primarySortType="STRING" sortId="4" levelDeep="4"/>
								</td>
							</tr>

							<%int RecordCounter = 0;
String bgcolor = "";
RecordCounter = 0;
bgcolor = "";%>

							<logic:iterate id="deptPolicySearchResultsIter"
								name="departmentPolicyForm" property="searchResults">
								<pg:item>
								<tr
									class=<%RecordCounter++;
bgcolor = "alternateRow";
if (RecordCounter % 2 == 1)
	bgcolor = "normalRow";
out.print(bgcolor);%>>
									<td width=1%>
									 <logic:notEqual name="btnsEnabled" value="<%=btnsEnabled%>">
									<html:radio idName="deptPolicySearchResultsIter"
										property="policyId" value="agencyPolicyId" />
										</logic:notEqual>
										</td>
									<td><a
										href="/<msp:webapp/>displayDepartmentPolicyView.do?policyId=<bean:write name='deptPolicySearchResultsIter' property='agencyPolicyId'/>">
									<bean:write name="deptPolicySearchResultsIter" property="name" /></a>
									</td>
									<td><bean:write name="deptPolicySearchResultsIter"
										property="group1Name" /></td>
									<td><bean:write name="deptPolicySearchResultsIter"
										property="group2Name" /></td>
									<td><bean:write name="deptPolicySearchResultsIter"
										property="group3Name" /></td>
								</tr>
								</pg:item>
							</logic:iterate>
						</table>
					</logic:notEmpty> <!-- BEGIN BUTTON TABLE --> 
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
				  <br>
					<table border="0" width="100%">
						<tr>
							<td align="center"><input type="button" value="Back" name="return" onClick="history.go(-1)">
								<jims2:isAllowed requiredFeatures="CS-DPOL-COPY">
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form))"><bean:message key="button.copy" /></html:submit>
								</jims2:isAllowed>
								<jims2:isAllowed requiredFeatures="CS-DPOL-UPDATE">
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form))"><bean:message key="button.update" /></html:submit>
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form))"><bean:message key="button.updateAssociations" /></html:submit>
							 	</jims2:isAllowed>
							 	<jims2:isAllowed requiredFeatures="CS-DPOL-DELETE">
									<html:submit property="submitAction" onclick="return (isAnySelected(this.form, 'policyId') && disableSubmit(this, this.form))"><bean:message key="button.delete" /></html:submit>
								</jims2:isAllowed>
								<!-- <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> -->
									<input type="button" value='<bean:message key="button.cancel" />'
								name="return"
								onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>displayDepartmentPolicySearch.do', false) && disableSubmit(this, this.form)">
							</td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- END  TABLE --></div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
