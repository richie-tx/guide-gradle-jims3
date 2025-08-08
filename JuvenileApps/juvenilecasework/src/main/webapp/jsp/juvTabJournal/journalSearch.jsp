<!DOCTYPE HTML>
<%-- User selects the "Journal" tab --%>
<%--MODIFICATIONS --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
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

<!--JQUERY FRAMEWORK LOCAL REFERENCE-->
 <%@include file="../jQuery.fw"%> 

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!-- JQuery function -->
<script type="text/javaScript" src="/<msp:webapp/>js/juvTabJournal/journalSearch.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework.js"></script>
<html:base />
<title><bean:message key="title.heading"/>juvTabJournal\journalSearch.jsp</title>

<script type="text/javascript">

</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" onload="">
<html:form action="/displayJuvenileJournalList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|272">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Journal Search</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
   <td>
	  <ul>
      <li>Click on hyperlinked Supervision Number to view casefile details.</li>
    </ul>
 	</td>
 </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" >
  <tr>
    <td valign="top">
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
						<%--tabs start--%>
						<tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="casefilestab"/>
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
						</tiles:insert>				
						<%--tabs end--%>
          </td>
        </tr>
        <tr>
         <td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
        </tr>
      </table>

  	  <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">
					<div class="spacer"></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td valign="top"> 
  								<%--tabs start--%>
  								<tiles:insert page="/jsp/caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
  									<tiles:put name="tabid" value="journal"/>
  									<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
  								</tiles:insert>			
  								<%--tabs end--%>
  							</td>
  						</tr>
  						<tr>
  							<td bgcolor='#6699ff'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
  						</tr>
						</table>
									
							<%-- BEGIN TRAITS TABLE --%>
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td>
								<%-- BEGIN TRAITS TABLE --%>
      					<div class="spacer"></div>								
								<table width='98%' align="center" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr>
										<td colspan='4' class="required"><bean:message
														key="prompt.2.diamond" />
											<bean:message key="prompt.requiredForThisSection" /></td>
									</tr>
									<tr>
									<!-- table title bar -->
									<td valign='top' colspan='2' class='detailHead'>Journal
											Search</td>
									</tr>
									<tr>
									<td colspan="2">
  									<table width='100%' border="0" cellpadding="2" cellspacing="1">
  										<tr>
  											<td colspan='2'>
											<%-- this span simulates a scrolling table after 5 entries --%>
											<div style="overflow-y: scroll; height:145px;">
											<table width="100%" cellpadding="2" cellspacing="1" border='0'>
											<thead>
  												<td class=formDeLabel>
  												<td class=formDeLabel><bean:message key="prompt.supervision" />&nbsp;#</td>
												<td nowrap="nowrap" width="3%" class="hidden" ><bean:message key="prompt.sequence" />&nbsp;#
												<jims2:sortResults beanName="journalForm" results="juvenileProfileCasefileList" primaryPropSort="sequenceNum"   primarySortType="INTEGER"  defaultSortOrder="DESC" defaultSort="true" sortId="1" /></td>
												<td class=formDeLabel><bean:message key="prompt.probationOfficer" /></td>
												<td class=formDeLabel><bean:message key="prompt.caseStatus" /></td>
												<td class=formDeLabel><bean:message key="prompt.supervisionType"/></td>
												<td class=formDeLabel><bean:message key="prompt.assignmentDate"/></td>  												
  											</td>  										
  										</tr>
  										</thead>
										<tbody>
  												<logic:empty name="journalForm" property="juvenileProfileCasefileList">
	  												<tr><td>No casefiles for this Juvenile.</td></tr>
	  											</logic:empty>
										
													<logic:notEmpty name="journalForm" property="juvenileProfileCasefileList">
	  												<logic:iterate id="casefiles" name="journalForm" property="juvenileProfileCasefileList" indexId="indexer"> 
	  			  									<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
	    												<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
	 							  	    				<td><input type="radio" name="available" value="<bean:write name="casefiles" property="supervisionNum"/>" id="sprvisionNumRadio"/></td>
	 							  	    				<td><a onclick="spinner();" href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>
	 							  	    			</logic:equal>
	
								  	    			<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
								  	    				<td><input type="checkbox" name="selectedValue" value="<bean:write name="casefiles" property="supervisionNum"/>" id="sprvisionNumRadio"/></td>
								  	    				<td><a onclick="spinner();" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<%out.print(naming.PDJuvenileCaseConstants.SUPERVISIONNUM_PARAM);%>=<bean:write name="casefiles" property="supervisionNum"/>"><bean:write name="casefiles" property="supervisionNum"/></a></td>
								  	    			</logic:notEqual>			 	
	  													<td class="hidden"><bean:write name="casefiles" property="sequenceNum"/></td>										
	  													<td><bean:write name="casefiles" property="probationOfficer"/></td>										
	  													<td><bean:write name="casefiles" property="caseStatus"/></td>
	  													<td><bean:write name="casefiles" property="supervisionType"/></td>
	  													<td><bean:write name="casefiles" property="assignmentAddDate"/></td>
	  												</tr>
													</logic:iterate>
	  										</logic:notEmpty>
	  										</tbody>
											</table>
											</div>
  											<div class="spacer"></div>
  												<tr>
													<td colspan='2'>
														<!-- begin search criteria section -->
														<table width='100%' border="0" cellpadding="2"
															cellspacing="1">
															<tr>
																<td width='1%' nowrap='nowrap' class='formDeLabel'>&nbsp;Journal
																	Category</td>
																<td class="formDe">
																	<html:select styleId="journalCategory" name="journalForm" property="journalCategoryCd" size="1" >
												        				<html:option value="">Please Select</html:option>
												        					<jims2:codetable codeTableName="JOURNAL_CATEGORY" sort="true"/>
												        			</html:select>
																</td>
															</tr>
															<tr>
																<td colspan='2'>
																	<table align="center" width="100%" border="0"
																		cellpadding="2" cellspacing="1"
																		class='borderTableBlue'>
																		<tr>
																			<td colspan='4' class='detailHead'>Date or Date
																				Range</td>
																		</tr>
																		<tr>
																			<td valign='top' class="formDeLabel" width='5%'>From</td>
																			<td valign='top' class="formDe"><html:text
																					name="journalForm" property="fromDate" size="10"
																					maxlength="10" styleId="fromDate" />&nbsp;</td>
																			<td valign='top' class="formDeLabel" width='5%'>To</td>
																			<td valign='top' class="formDe"><html:text
																					name="journalForm" property="endDate" size="10"
																					maxlength="10" styleId="toDate" />&nbsp;</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table> <!-- end search criteria section -->
													</td>
												</tr>
  										

  							<%-- Begin Pagination navigation Row--%>
								<div class="spacer"></div>
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

								<%-- BEGIN BUTTON TABLE --%>
								<table border="0" align='center'>      
									<tr>
										<td align='center'>
											<input type="button" id="searchBtn" onclick="" value="<bean:message key="button.submit" />"/>&nbsp;
											<%-- <html:submit property="submitAction" styleId="searchBtn"><bean:message key="button.submit" /></html:submit> --%>
										</td>
									</tr>
									<tr>										
										<td>
											<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</td>      	
									</tr>
								</table>
								<%-- END BUTTON TABLE --%>
							</td>
	  				</tr>
	  			</table>
					<%--end blue table for activities--%>
					<div class='spacer'></div>

					</td>
				</tr>		
			</table> 
			<%--end green table--%>
		</td>
	</tr>
</table>
<%--end main table--%>
<%-- END DETAIL TABLE --%>

<div class="spacer"></div>
<%-- END BUTTON TABLE --%>
</pg:pager>
<html:hidden styleId="selectedCasefileIds"  name="journalForm" property="selectedCasefiles" />
<html:hidden styleId="selectedCategory"  name="journalForm" property="journalCategoryCd" />
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
