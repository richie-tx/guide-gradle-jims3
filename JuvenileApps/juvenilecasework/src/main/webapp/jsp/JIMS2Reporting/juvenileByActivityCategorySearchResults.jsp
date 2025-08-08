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
<title><bean:message key="title.heading"/>/juvenileByActivityCategorySearchResults.jsp</title>

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
		<td align="center" class="header" >Activities -- Search Activities Across Youth -- Results
</td>
	</tr>
	
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Print button to print the generated report.</li>
				<li>Select Cancel button to return to search page.</li>				
			</ul>
		</td>		
	</tr>
</table>
<%-- BEGIN GROUPING TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0">
	<tr>
		<td align="left" class="header" >Activity -- Statistics</td>
	</tr>
	<tr>
	<td>
	<table align="left" width="35%" border="2" cellpadding="1" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td class="formDeLabel" width='5%'></td>
				<td class="formDeLabel" width='10%'>Activity
				<jims2:sortResults beanName="activitiesForm" results="statistics" primaryPropSort="activityName" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
			  </td>
			  <td class="formDeLabel" class="formDeLabel" width='10%'>
				 Activity Count	
				 <jims2:sortResults beanName="activitiesForm" results="statistics" primaryPropSort="activityCount" primarySortType="INT" defaultSortOrder="ASC" sortId="2" />
			  </td>	
		   </tr>
		<logic:iterate id="mapEntry" name="activitiesForm" property="statistics" indexId="index">
		  <tr>
		  	<td></td>
		    <td align="left" nowrap="nowrap" width='10%'><bean:write name="mapEntry" property="activityName"/></td>
		    <td align="left" nowrap="nowrap" width='10%'><bean:write name="mapEntry" property="activityCount"/></td>
		  </tr>
		</logic:iterate>
		<td class="formDeLabel" width='5%'>Total</td>
		<td align="left" nowrap="nowrap"><bean:write name="activitiesForm" property="actCounter"/></td>
		<td align="left" nowrap="nowrap"><bean:write name="activitiesForm" property="total"/></td>		
	</table>
	</td>
	</tr>	
</table>
<%-- END INSTRUCTION TABLE --%>
<logic:notEmpty name="activitiesForm" property="juvenilebycasefileActivity">
 <tr>
   	 <td align="center"><bean:write name="activitiesForm" property="listTotal"/> Record(s) Found </td>
 </tr>
 </logic:notEmpty>

	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
		<tr>
			<td class="detailHead"><bean:message key="prompt.juvenileNumber" />
			 <jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="juvenileId" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />						
          	</td>		
			<td class="detailHead"><bean:message key="prompt.casefileID" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="casefileId" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
			</td>
			<td class="detailHead"><bean:message key="prompt.juvenileName" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="juvenilelastName" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
			</td>
			
			<td class="detailHead"><bean:message key="prompt.activityCategory" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="categoryId" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
			</td>
			<td class="detailHead"><bean:message key="prompt.activityType" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="typeId" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
			</td>
			<td class="detailHead" width="15%"><bean:message key="prompt.activity" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="activitycodeDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
			</td>
			<td class="detailHead"><bean:message key="prompt.activityTime" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="activityTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" />
			</td>	
			<td class="detailHead">Activity End Time
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="activityEndTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
			</td>
			<td class="detailHead"><bean:message key="prompt.activityDate" />
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="activityDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="9" /> <%-- #32059 fix --%>
			</td>	
			<td class="detailHead">Activity Create Date
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="activityCreateDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="10" /> 
			</td>
			<td class="detailHead">Days Diff
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="daysDiff" primarySortType="NUMBER" defaultSortOrder="ASC" sortId="11" />
			</td>	
			<td class="detailHead">JPO
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="logonId" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" />
			</td>
			<td class="detailHead">Created User
				<jims2:sortResults beanName="activitiesForm" results="juvenilebycasefileActivity" primaryPropSort="createUser" primarySortType="STRING" defaultSortOrder="ASC" sortId="13" />
			</td>
			 
		</tr>
		<logic:iterate id="tots" name="activitiesForm" property="juvenilebycasefileActivity">
		<%-- Begin Pagination item wrap --%>
          	<pg:item>
				<tr>
					<td align="left"><bean:write name="tots" property="juvenileId"/></td>
					<td align="left"><bean:write name="tots" property="casefileId"/></td>
					<td align="left"><bean:write name="tots" property="juvenilelastName"/>,&nbsp<bean:write name="tots" property="juvenilefirstName"/>&nbsp<bean:write name="tots" property="juvenilemiddleName"/></td>
					<td align="left"><span title='<bean:write name="tots" property="categoryDesc"/>'><bean:write name="tots" property="categoryId"/></span>
					<td align="left"><span title='<bean:write name="tots" property="typeDesc"/>'><bean:write name="tots" property="typeId"/></span>
						
	             				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_VIEW_MAP%>'>
	             					<logic:notEmpty name="tots" property="latitude">
									<td align="left" width="15%"/>
									<a href="javascript:openWindow('https://www.google.com/maps/?q=loc:<bean:write name="tots" property="latitude"/>,<bean:write name="tots" property="longitude" />')"><bean:write name="tots" property="activitycodeDesc"/></a>
									<html:hidden name='tots' property='latitude' />
									<html:hidden name='tots' property='longitude' />
								 </td> 
								 </logic:notEmpty>
								 <logic:empty name="tots" property="latitude"> 
								 	<td align="left" width="15%"><bean:write name="tots" property="activitycodeDesc"/>
								  </logic:empty>          										
			            	</jims2:isAllowed>
			            	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_VIEW_MAP%>' value="false">
			            		<td align="left" width="15%"><bean:write name="tots" property="activitycodeDesc"/>
			            	</jims2:isAllowed>
			            	
			     	<td align="left"><bean:write name="tots" property="activityTime"/></td>	
					<td align="left"><bean:write name="tots" property="activityEndTime"/></td>	
					<td align="left"><bean:write name="tots" property="activityDate" formatKey="date.format.mmddyyyy"/></td>	
					<td align="left"><bean:write name="tots" property="activityCreateDate" formatKey="date.format.mmddyyyy"/></td>
					<td align="left"><bean:write name="tots" property="daysDiff" /></td>	
					<td align="left"><span title='<bean:write name="tots" property="officerfullName"/>'><bean:write name="tots" property="logonId"/></span></td>	
					<td align="left"><span title='<bean:write name="tots" property="createuserLiteral"/>'><bean:write name="tots" property="createUser"/></span></td>
				</tr>
			</pg:item>
  		   <%-- End Pagination item wrap --%>
		</logic:iterate>
		
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

</pg:pager>
<%-- End Pagination Closing Tag --%>
<div class="spacer4px" />
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	
	<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
			<html:submit styleId="printBtn" property="submitAction"><bean:message key="button.print" /></html:submit>
		</td>
	</tr>
</table>
</table>
<%-- END DETAIL TABLE --%>

</pg:pager>

<!-- <script  type='text/javascript'>updateTypeForView(document.forms[0]);</script> -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>