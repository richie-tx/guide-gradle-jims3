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
<script type="text/javaScript" src="/<msp:webapp/>js/activity.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/activityList.jsp</title>
<script  type='text/javascript'>


var currentSelectedValue = "";
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



 function validateMandatoryFields(theForm) 
{
 	var regexp = /^[a-zA-Z0-9_\- ]*$/;
 	var msg = "";
	var parentId = theForm.selectedCategoryId.value;

	if(parentId == "" || parentId == "Please Select")
	{
		msg += "Please Select an Activity Category.";
		theForm.selectedCategoryId.focus();					
	}
	
  /* Defect 48875 ... fix for checking valid dates
	*/
 	var dateStr = theForm.startDateAsStr.value ;
	if( dateStr.length > 1 )
	{
	  if( ! isDate( dateStr, "MM/dd/yyyy" ) )
		  msg += "\nStart date is an invalid date." ;
		  
	}

	dateStr = theForm.endDateAsStr.value ;
	if( dateStr.length > 1 )
	{
	  if( ! isDate( dateStr, "MM/dd/yyyy" ) )
		  msg += "\nEnd date is an invalid date." ;
	}
  /* end Defect 48875	*/

	
 	if (msg == "")
	{ 
 		return true;
 	}

	alert(msg);     
  return false; 
}

function checkType()
{
	var y = "<bean:write name='activitiesForm' property='activityCatIdForReload'/>";	
	var x = "<bean:write name='activitiesForm' property='activityTypeIdForReload'/>";		
	var z = "<bean:write name='activitiesForm' property='activityDescForReload'/>";
	
	if(y != " ")
	{
		if(x!="")
		{
			for(i in activityCodes)
			{
				if(activityCodes[i].code == y)
				{
					for(j in activityCodes[i].subTypes)
					{
						if(activityCodes[i].subTypes[j].code == x)	
						{
							for(k in activityCodes[i].subTypes[j].subTypes)
							{
								
								document.forms[0].selectedDescriptionId.options[document.forms[0].selectedDescriptionId.options.length] = new Option( activityCodes[i].subTypes[j].subTypes[k].description, activityCodes[i].subTypes[j].subTypes[k].code);
								if(activityCodes[i].subTypes[j].subTypes[k].code == z)									
									document.forms[0].selectedDescriptionId.selectedIndex = ++k;							
							}
							document.forms[0].selectedTypeId.selectedIndex = ++j;		
						
						}
					}
					
				}
			}
		}
		
	}
	return;
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" onload="updateTypeVendor(document.forms[0]); updateActivity(document.forms[0]); updateTypeForView(this.form); checkType();">
<html:form action="/displayUpdateActivity" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|129">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" >
			<bean:message key="title.juvenileCasework"/> - 
			<bean:message key="title.casefile"/>
			<bean:message key="prompt.activity"/>
			<bean:message key="prompt.list"/>
		</td>
	</tr>
	<logic:equal name="activitiesForm" property="action" value="finish">
	  <tr>
	      <td align="center" class="confirm"> Activity successfully added.</td>
	  </tr>
	 </logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select an Activity Category and an optional Activity Type</li>
				<li>Select <b>View Activities</b> to see a filtered list of activities matching your selections.</li>
				<li>Select <b>Add Activity</b> to add a new activity.</li>
				<li>Select a hyperlinked Entry Date to view an activity detail.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
<%--tabs start--%>
			<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
<%--tabs end--%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
	  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td>
  									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  										<tr>
	  										<td valign='top'>
	    										<%--tabs start--%>
	  											<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
	  												<tiles:put name="tabid" value="activitiestab"/>
	   												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	  											</tiles:insert>	
	    										<%--tabs end--%>
	  										</td>
	  									</tr>
	  									<tr>
										  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
										</tr>
	 							  	</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
	  									<tr>
	  										<td valign='top' align='center'>
				              					<%-- BEGIN Activities TABLE --%>
												<div class='spacer'></div>
              									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	              									<tr>
	              										<td valign='top' colspan='2' class='detailHead'><bean:message key="prompt.activities"/></td>
	              									</tr>
	              									<tr>
	              										<td colspan='2'>
	              											<table width='100%' border="0" cellpadding="2" cellspacing="1">
	              												<tr><input type="hidden" name="progrefStatus" value=<bean:write name="activitiesForm" property="progrefStatus"/>>   
	              													<td width='1%' nowrap='nowrap' class='formDeLabel'><bean:message key="prompt.2.diamond"/><bean:message key="prompt.activityCategory"/></td>
	              													
	              													<td class='formDe'>
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
																	<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.activityType"/></td>
																	<td class='formDe'>
																	<logic:equal name="activitiesForm" property="vendorActivity" value="Y" >
																		<html:select property="selectedTypeId" styleId="selectedTypeId" disabled="true">
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
																	<td width='1%' class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.activity"/></td>
																	<td class='formDe'>
																		<html:select property="selectedDescriptionId" styleId="selectedDescriptionId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		</html:select>
																	</td>
																</tr>		
																<tr>								
																	<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.dateRange"/></td>
																	<td class='formDe' colspan='3'> 
																		<b><bean:message key="prompt.from"/></b>
																		<html:text name="activitiesForm" property="startDateAsStr" size="10" maxlength="10" styleId="startDateAsStr"/>
																		&nbsp;&nbsp;
																		<b><bean:message key="prompt.to"/></b>
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
																	<td width='1%' class='formDeLabel' nowrap='nowrap'></td>
																	<td class='formDe'>
																		<html:submit property="submitAction" styleId="viewActivity" onclick="return validateMandatoryFields(this.form)"><bean:message key="button.viewActivities" /></html:submit> 									
																	</td>
																</tr>	
																<logic:empty name="activitiesForm" property="vendorActivity" >
																<input type="hidden"  value="" id="vendor"/>
																</logic:empty>
																<logic:notEmpty name="activitiesForm" property="vendorActivity" >
																<input type="hidden"  value="vendorActivity" id="vendor"/>
																</logic:notEmpty>
															</table>
														</td>
													</tr>
	
													<tr id="traitsList1">
														<td valign='top' colspan='2'>
															<table width='100%' cellpadding='4' cellspacing='1' bgcolor='#999999'>
															<logic:notEqual name="activitiesForm" property="vendorActivity" value="Y" >
																<logic:empty name="activitiesForm" property="activityResults">
																	<tr bgcolor="#cccccc">
																		<td colspan="4" class="subHead" align="left">No Activities available.</td>
																	</tr>
																</logic:empty>
															</logic:notEqual>
																	
																<logic:notEmpty name="activitiesForm" property="activityResults">
																	<tr bgcolor='#cccccc'>
																		<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.entryDate"/></td>
																		<td class="subhead" valign='top' nowrap='nowrap'>Entry Time</td>
																		<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.activity"/> <bean:message key="prompt.id"/></td>
																		<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.category"/></td>
																		<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.type"/></td>
																		<td class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.activity"/></td>
																	</tr>
	
																	<logic:iterate indexId="index" id="activityIndex" name="activitiesForm" property="activityResults">
																	<%-- Begin Pagination item wrap --%>
																		<pg:item>
																			<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																				<td><a href="/<msp:webapp/>displayActivitySummary.do?submitAction=<bean:message key='button.link'/>&activityId=<bean:write name='activityIndex' property='activityId' />">
																					<bean:write name="activityIndex" property="activityDate" formatKey="date.format.mmddyyyy" /></a>
																				</td>
																				<td valign='top'><bean:write name="activityIndex" property="activityTime" /></td>
																				<td valign='top'><bean:write name="activityIndex" property="activityId" /></td>
																				<td valign='top'><bean:write name="activityIndex" property="categoryDesc" /></td>
																				<td valign='top'><bean:write name="activityIndex" property="typeDesc" /></td>
																				<td valign='top'><bean:write name="activityIndex" property="activityDesc" /></td>
																			</tr>
																		</pg:item>
																	<%-- End Pagination item wrap --%>
																	</logic:iterate>
																</logic:notEmpty>
															</table>
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
												</table>
											</td>
										</tr>
	
										<tr>
											<td align='center'>
												<div class='spacer'></div>
												<table>
													<td align="left">
														<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>
														<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
															<logic:equal name="activitiesForm" property="allowUpdates" value="true">																
																<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_ACT_U%>'>
																	<logic:equal name="activitiesForm" property="progrefStatus" value="AC">
																		<html:submit property="submitAction"><bean:message key="button.addMoreActivities" /></html:submit>
																	</logic:equal>
																	<logic:equal name="activitiesForm" property="progrefStatus" value="">
																		<html:submit property="submitAction"><bean:message key="button.addMoreActivities" /></html:submit>
																	</logic:equal>
																</jims2:isAllowed>
															</logic:equal>
														</logic:equal>
														<input type="hidden" value="activity" id="activityPage"/> <%-- added for bug fix 39771 --%>
														<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
													</td>
												</table>
											</td>
										</tr>
									</table>
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
</table>
<%-- END DETAIL TABLE --%>

</pg:pager>

<script  type='text/javascript'>updateTypeForView(document.forms[0]);</script>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>