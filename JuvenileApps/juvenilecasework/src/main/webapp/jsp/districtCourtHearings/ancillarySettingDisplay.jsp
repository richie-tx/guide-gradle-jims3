<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>
<%@page import="messaging.calendar.reply.DocketEventResponseEvent"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

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
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtAncillarySetting.js"></script>

<title><bean:message key="title.heading" />/ancillarySettingDisplay.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/submitJuvenileDistrictCourtAncillarySetting" target="content" styleId="ancillarySettingForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Ancillary Setting Display</h2></td>
			</tr>
		</table>
		 <!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
  
  <!-- END Error Message Table -->
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
		<br />
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<li>Click the radio button to update the record.</li>
						<li>Click the Update Setting  button to update the ancillary setting record.</li>
						<li>Click Cancel button to return to the Court Main Menu page.</li>
					</ul>
				</td>
			</tr>
		</table>
		<%-- END INSTRUCTION TABLE --%>	
		
		
		<!-- Ancillary Display Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="ancillaryDisplayTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
						
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.court"/></td>
							<td class='formDe'><html:text name="courtHearingForm" property="courtId" styleId="courtId"  maxlength="10" size="10"/></td>
						
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.date"/></td>
							<td class='formDe'><html:text name="courtHearingForm" property="courtDate" styleId="courtDate"  maxlength="10" size="10"/></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br/>
		<br/>
		
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="ancillaryDisplayTbl">
		<tr>
		<td>
		<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead" colspan=6>
							<td></td>	
							<td><bean:message key="prompt.timeHHMM"/>
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="formattedCourtTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" defaultSort="true"/>
							</td>
							<td><bean:message key="prompt.petition"/>
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" />
							</td>
							<td><bean:message key="prompt.respondent"/>
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="respondantName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
							</td>
							<td><bean:message key="prompt.settingReason"/>
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="settingReason" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" />
							</td>
							<td><bean:message key="prompt.attorney"/>
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="attorneyName" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							</td>
						</tr>
	<logic:iterate id="ancillarySettings" name="courtHearingForm" property="dktSearchResults" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">	
				<td class='formDe' >
				 <input type="radio" name="docketEventsRec" value=<bean:write name='ancillarySettings' property='docketEventId'/> styleId = "docketEventId" >
<%-- 				<html:radio name="ancillarySettings" styleId = "docketEventId" property="docketEventId" value="<%=((DocketEventResponseEvent)ancillarySettings).getDocketEventId()%>" checked = false/> --%>
				</td>	 	
				<td class='formDe' ><bean:write name="ancillarySettings" property="formattedCourtTime"/></td>
				<td class='formDe' ><bean:write name="ancillarySettings" property="petitionNumber"/></td>
				<td class='formDe' maxlength="25" size="25"><bean:write name="ancillarySettings" property="respondantName"/></td>
				<td class='formDe' ><span title='<bean:write name="ancillarySettings" property="hearingTypeDesc"/>'><bean:write name="ancillarySettings" property="settingReason"/></td>
				<td class='formDe' ><bean:write name="ancillarySettings" property="attorneyName"/></td>
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
							
	  </td>
  </tr>	
</table>
					
		<!-- Ancillary Add Information ends-->
		
		</pg:pager>
		<%-- Pagination Closing Tag --%>
		<!-- Button Table Starts-->
				<br/>
		<table width="100%" border="0">
			<tr>
				<td align="center">
					<html:submit property="submitAction" styleId="updateAncillarySettingBtn"><bean:message key="button.updateSetting"/></html:submit> 
					<html:submit property="submitAction" styleId="cancelBtn"><bean:message key="button.cancel" /></html:submit>
				</td>
			</tr>
		</table>
		<!--  Button Table ends-->
		
		<!-- html hidden fields starts -->
		<html:hidden styleId="action" name="courtHearingForm" property="action"/>
		<!-- html hidden fields ends -->
	
	</html:form>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>