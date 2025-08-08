<!DOCTYPE HTML>
<%-- User selects the "Activities" tab --%>
<%--MODIFICATIONS --%>
<%-- 11/15/2006	Debbie Williamson	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
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
<script type="text/javaScript" src="/<msp:webapp/>js/activity.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/profileActivityList.jsp</title>

<script type="text/javascript">
/* for group drop downs - imitating configurator
	params: groupNum - which group select u are activating, theForm - the form obj
*/
var typeDescriptionSelectValue = "0";

function enableSelect( groupNum, theForm )
{
	if(groupNum == 2)
	  theForm.group2.disabled = false;
	else if(groupNum == 3)
	  theForm.group3.disabled = false;
}
  
function renderFilterList( theSelectTypeDesc )
{
 	typeDescriptionSelectValue = theSelectTypeDesc.value;
}
  
<logic:iterate indexId="groupIterIndex" id="groupIter" name="activitiesForm" property="activityCodes">
	activityCodes[<bean:write name="groupIterIndex"/>] = new subTypes("<bean:write name="groupIter" property="code" />", "<bean:write name="groupIter" property="description" />");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subTypes">
		var innerGroup = new subTypes("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description" />");
		activityCodes[<bean:write name="groupIterIndex"/>].subTypes[<bean:write name="groupIterIndex2"/>] = innerGroup;
		
		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subTypes">
			var innerGroup = new subTypes("<bean:write name="groupIter3" property="code" />", "<bean:write name="groupIter3" property="description" />");
			activityCodes[<bean:write name="groupIterIndex"/>].subTypes[<bean:write name="groupIterIndex2"/>].subTypes[<bean:write name="groupIterIndex3"/>] = innerGroup;
		</logic:iterate>
	</logic:iterate>
</logic:iterate>


/*  Replaced with JQuery function  
 function validateMandatoryFields(theForm) 
{
 	var regexp = /^[a-zA-Z0-9_\- ]*$/;
 	var msg = "";
 	var focusSet = false;
	var parentId = theForm.selectedCategoryId.value;

	if(parentId == "" || parentId == "Please Select")
	{
			msg += "Please Select an Activity Category";
  			theForm.selectedCategoryId.focus();					
	}

 	if (msg == "")
	{ 
 		return true;
 	}

 	alert(msg);     
	return false;
}  */

</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" onload="updateTypeVendor(document.forms[0]); updateActivity(document.forms[0]); updateTypeForView(this.form); checkType();">
<html:form action="/displayProfileActivityList" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|272">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Casefile Activity List</td>
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
  									<tiles:put name="tabid" value="activities"/>
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
										<td valign="top" colspan="2" class="detailHead"><bean:message key="prompt.activities"/></td>
									</tr>
									<tr>
									<td colspan="2">
  									<table width='100%' border="0" cellpadding="2" cellspacing="1">
  										<tr>
  											<td width='1%' nowrap="nowrap" class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activityCategory"/></td>
  											<td class="formDe">
  												<logic:equal name="activitiesForm" property="vendorActivity" value="Y" >
	              													<html:select property="selectedCategoryId" styleId="selectedCategoryId" disabled="true" onchange="updateTypeVendor(this.form)">
																			<html:optionsCollection property="activityCodes" value="code" label="description" />
					              										</html:select>
	              								</logic:equal>
					              				<logic:notEqual name="activitiesForm" property="vendorActivity" value="Y">
					              									<html:select property="selectedCategoryId" styleId="selectedCategoryId" onchange="updateTypeForView(this.form);">
					              											<html:option value=""><bean:message key="select.generic" /></html:option>
					              											<html:optionsCollection property="activityCodes" value="code" label="description" /> 
					              											<html:option value="selectAll"><bean:message key="select.all" /></html:option>
					              									</html:select>
					              				</logic:notEqual>
  											</td>
  										</tr>
  										<tr>
  											<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.activityType"/></td>
  											<td class="formDe">
  												<logic:equal name="activitiesForm" property="vendorActivity" value="Y" >
																		<html:select property="selectedTypeId" styleId="selectedTypeId" disabled="true" onchange="updateActivity(this.form);">
																		</html:select>
																		<input type="hidden" name="selectedGrp2Id" value=<bean:write name="activitiesForm" property="selectedTypeId"/>>
												</logic:equal>
												<logic:notEqual name="activitiesForm" property="vendorActivity" value="Y">
																		<html:select property="selectedTypeId" styleId="selectedTypeId" onchange="updateActivity(this.form);">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
											</logic:notEqual>
  											</td>
  										</tr>
  															
  		                <tr>
  											<td width='1%' class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.activity"/></td>
  											<td class="formDe">
  												<html:select property="selectedDescriptionId" styleId="selectedDescriptionId" >
  													<html:option value=""><bean:message key="select.all" /></html:option>
  												</html:select>
	  										</td>
  										</tr>	
  										<tr>								
  											<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.dateRange"/></td>
  											<td class="formDe" colspan="3"> 
  												<bean:message key="prompt.from"/>
  												<html:text name="activitiesForm" property="startDateAsStr" size="10" maxlength="10" styleId="startDateAsStr"/>
  												&nbsp;&nbsp;
  												<bean:message key="prompt.to"/>
  												<html:text name="activitiesForm" property="endDateAsStr" size="10" maxlength="10" styleId="endDateAsStr"/>						    		      
  											</td>
  										</tr>
  										<tr>
											<td class='formDeLabel' nowrap='nowrap' width='1%'>Time</td>
											<td class="formDe">
												<html:select styleId="activityTime" property="activityTimeStr">
													<html:option key="select.generic" value="" />
													<html:optionsCollection name="activitiesForm" property="workDays" value="description" label="description" />
												</html:select>
											</td>
										</tr>	
  		                				<tr>
  											<td width='1%' class="formDeLabel" nowrap="nowrap"></td>
  											<td class="formDe"><html:submit property="submitAction" styleId="viewActivity"><bean:message key="button.viewActivities" /></html:submit></td>
  										</tr>																						
  									</table>
									</td>
								</tr>

								<tr id="traitsList1">
									<td valign="top" colspan="2">
										<table width='100%' cellpadding="4" cellspacing="1" bgcolor='#999999'>
											  <logic:notEmpty name="activitiesForm" property="activityResults">
													<tr bgcolor='#cccccc'>
														<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.entryDate"/></td>
														<td class="subhead" valign='top' nowrap='nowrap'>Entry Time</td>
														<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.supervision#"/></td>
														<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.category"/></td>
														<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.type"/></td>
														<td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.activity"/></td>
													</tr>

  	                      							<logic:iterate indexId="index" id="activityIndex" name="activitiesForm" property="activityResults">
														<%-- Begin Pagination item wrap --%>
    											 		<pg:item>
	    													<tr class="<% out.print( (index.intValue() % 2 == 1) ? "normalRow" : "alternateRow" );%>" height="100%">
		                               							 <td>
		      													  <a href="/<msp:webapp/>displayProfileActivitySummary.do?submitAction=<bean:message key='button.link'/>&activityId=<bean:write name="activityIndex" property="activityId" />&casefileId=<bean:write name="activityIndex" property="casefileId" />">
		       						              				  <bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy" /></a>
		      													</td>
		      													<td valign='top'><bean:write name="activityIndex" property="activityTime" /></td>       
		        						          				<td valign='top'><bean:write name="activityIndex" property="casefileId" /></td>
		      													<td valign='top'><bean:write name="activityIndex" property="categoryDesc" /></td>
		      													<td valign='top'><bean:write name="activityIndex" property="typeDesc" /></td>
		      													<td valign='top'><bean:write name="activityIndex" property="activityDesc" /></td>
	      													</tr>
      													</pg:item>
      										  <%-- End Pagination item wrap --%>
			                         			 </logic:iterate>
			                     			   </logic:notEmpty>   
          
						                        <logic:equal name="activitiesForm" property="secondaryAction" value="displayActivities">
						                          <logic:empty name="activitiesForm" property="activityResults">    
						                          	<tr><td colspan="5">No Activities available.</td></tr>
						                          </logic:empty>
						                        </logic:equal>
											</table>					
											<div class="spacer"></div>
										</td>
									</tr>

									<bean:define id="typeId" name="activitiesForm" property="selectedTypeId"/>
									<bean:define id="act" name="activitiesForm" property="selectedDescriptionId"/>
									<input type="hidden" name="activityType" value="<%=typeId%>" />
									<input type="hidden" name="activity" value="<%=act%>" />
								</table>
								<%--end activites table--%>

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
<script type="text/javaScript">updateTypeForView(document.forms[0]);</script>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

