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
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtPetitionCJISSearch.js"></script>

<title><bean:message key="title.heading" />/courtPetitionCJISSearch.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/handleJuvenileDistrictCourtPetitionCJISSearch" target="content" styleId="ancillarySettingForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Petition/CJIS Search</h2></td>
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

		<%--  INSTRUCTION TABLE DELETED --%>	
		
		<!-- Referral Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="ancillaryDisplayTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.juvenileNumber"/>/Petition Number/CJIS Number</td>
							<%-- <td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.juvenileNum"/></td>  --%>
							<td class='formDe' >
							<html:text name="courtHearingForm" property="juvenileNumber" styleId="juvNum" readonly="false"  maxlength="14" size="18"/>
							<td class='formDeLabel'  width="5%" nowrap="nowrap">Type code</td>
							<td class='formDe' colspan="7"><html:text name="courtHearingForm" property="typeCode" styleId="typeCd" readonly="false"  maxlength="5" size="5"/>
							&nbsp;&nbsp;<html:submit property="submitAction" styleId="reSubmitbtn"><bean:message key="button.go"/></html:submit></td>
						</tr>
						<tr>
							
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.juvenileName"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="juvenileName"/>							
							</td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.race"/></td>
							<td class='formDe' >
								<span title="<bean:write name="courtHearingForm" property="profileDetail.race"/>">
									<logic:equal  name="courtHearingForm" property="profileDetail.originalRaceId" value="">
										<bean:write name="courtHearingForm" property="profileDetail.raceId"/>
									</logic:equal>
									<login:notEqual name="courtHearingForm" property="profileDetail.originalRaceId" value="">
										<bean:write name="courtHearingForm" property="profileDetail.originalRaceId"/>
									</login:notEqual>
								</span>
							</td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.sex"/></td>
							<td class='formDe' ><span title="<bean:write name="courtHearingForm" property="profileDetail.sex"/>"><bean:write name="courtHearingForm" property="profileDetail.sexId"/></span></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.dateOfBirth"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
							<td class='formDeLabel' width="5%" nowrap="nowrap">Juv Status</td>
											<td class='formDe'>
												<span title="<bean:write name="courtHearingForm" property="profileDetail.masterStatus" />">
													<bean:write name="courtHearingForm" property="profileDetail.masterStatusId" />
												</span>
											</td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
		<br/>
		<table align="center">
			<tr align="center">
				<td style='font-weight: bold; font-size: medium '><bean:write name="courtHearingForm" property="searchResultSize"/> Settings Found.</td>
			</tr>
		</table>
		<br>
		
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="ancillaryDisplayTbl">
		<tr>
		<td>
		<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead" colspan=6>
							
							<td><bean:message key="prompt.refNo"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="referralNum" primarySortType="STRING" defaultSortOrder="DESC" sortId="1" defaultSort="true"/>
							</td>
							<td><bean:message key="prompt.referralDt"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="referralDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="2" />
							</td>
							<td><bean:message key="prompt.offense"/>/<bean:message key="prompt.petitionAllegation"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="offense" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
							</td>
							<td><bean:message key="prompt.courtId"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="courtId" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
							</td>
							<td><bean:message key="prompt.courtDate"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="courtDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="9" />
							</td>
							 <td><bean:message key="prompt.adjDSN"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="courtResult" primarySortType="STRING"  defaultSortOrder="ASC" sortId="10" />
							</td>
							<td><bean:message key="prompt.finalDisposition"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="finalDisposition" primarySortType="STRING"  defaultSortOrder="ASC" sortId="11" />
							</td>
							<td><bean:message key="prompt.petition"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="petitionNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" />
							</td>
							<td><bean:message key="prompt.CJISNum"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="CJISNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" />
							</td>
							<td><bean:message key="prompt.dateterminated"/>
							<jims:sortResults beanName="courtHearingForm" results="petitionList" primaryPropSort="termination_Date" primarySortType="DATE" defaultSortOrder="ASC" sortId="13" />
							</td>
						</tr>
	<logic:iterate id="referralList" name="courtHearingForm" property="petitionList" indexId="indexer"> 
			  <!-- Begin Pagination item wrap -->
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">	
			
				<td class='formDe' ><bean:write name="referralList" property="referralNum"/></td>
				<td class='formDe' ><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
										<td class='formDe'><bean:write name="referralList"
												property="offense" /> 
									<logic:notEmpty name="referralList"
												property="petitionAllegation">
						/<bean:write name="referralList" property="petitionAllegation" />
											</logic:notEmpty></td>

										<td class='formDe' ><bean:write name="referralList" property="courtId"/></td>
				<td class='formDe' ><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="courtResultDesc"/>"><bean:write name="referralList" property="courtResult"/></span></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td> 
				<td class='formDe' ><bean:write name="referralList" property="petitionNum"/></td>
				<td class='formDe' ><bean:write name="referralList" property="CJISNum"/></td>
				<td class='formDe' ><bean:write name="referralList" property="termination_Date" /></td>
			</tr>
		  </pg:item>
		  <!-- End Pagination item wrap -->
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
				
					<html:submit onclick="spinner()" property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu" /></html:submit>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTVTYYOUTH%>'>
						<html:submit property="submitAction" styleId="submitYouthCrtAct"><bean:message key="button.courtActivityByYouth2" /></html:submit>
					</jims2:isAllowed>
				</td>
			</tr>
		</table>
		<!--  Button Table ends-->
		
		<!-- html hidden fields starts -->
		<html:hidden styleId="action" name="courtHearingForm" property="action"/>
		<html:hidden styleId="origjuvNumId" name="courtHearingForm" property="origjuvNum"/>
		<!-- html hidden fields ends -->
	
	</html:form>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>