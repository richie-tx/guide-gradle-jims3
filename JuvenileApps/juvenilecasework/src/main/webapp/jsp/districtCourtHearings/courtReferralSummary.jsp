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
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtReferralSummary.js"></script>

<title><bean:message key="title.heading" />/courtReferralSummary.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/handleJuvenileDistrictCourtReferralSummary" target="content" styleId="ancillarySettingForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Referral Summary</h2></td>
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
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.juvenileNumber"/></td>
							<%-- <td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.juvenileNum"/></td>  --%>
							<logic:notEqual name ="courtHearingForm" property="action" value="DALognumSearch">	
							<td class='formDe' >
							<html:text name="courtHearingForm" property="profileDetail.juvenileNum" styleId="juvNum" readonly="false"  maxlength="6" size="6"/>
							&nbsp;&nbsp;<html:submit property="submitAction" styleId="reSubmitbtn"><bean:message key="button.go"/></html:submit></td>
							</logic:notEqual>
							<logic:equal name ="courtHearingForm" property="action" value="DALognumSearch">	
							<td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.juvenileNum"/>
							</logic:equal>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.juvenileName"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="juvenileName"/>
							<logic:equal name="courtHearingForm" property="profileDetail.recType" value="I.JUVENILE">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
							</logic:equal>
							<logic:equal name="courtHearingForm" property="profileDetail.recType" value="S.JUVENILE">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
							</logic:equal>
								&nbsp;&nbsp;
							<logic:notEqual name="courtHearingForm" property="profileDetail.preferredFirstName" value="">
								<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="courtHearingForm" property="profileDetail.preferredFirstName"/></span>
							&nbsp;
							</logic:notEqual>
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
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.verified"/></td>
							<td class='formDe' >
								<logic:equal name="courtHearingForm" property="profileDetail.verifiedDOB" value="true"><span title="Yes">Y</span></logic:equal>
								<logic:equal name="courtHearingForm" property="profileDetail.verifiedDOB" value="false"><span title="No">N</span></logic:equal>
							</td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.juv"/></td>
							<td class='formDe' >
								<span title="<bean:write name="courtHearingForm" property="profileDetail.status"/>">&nbsp;&nbsp;<bean:write name="courtHearingForm" property="profileDetail.statusId"/></span>
							</td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.facility/Status"/></td>
							<%--<td class='formDe' >
								<logic:notEmpty  name="courtHearingForm" property="profileDetail.detentionFacilityId">
								<span title="<bean:write name="courtHearingForm" property="profileDetail.detentionFacility"/>">
								<bean:write name="courtHearingForm" property="profileDetail.detentionFacilityId"/></span></logic:notEmpty>&nbsp;/&nbsp;
								<span title="<bean:write name="courtHearingForm" property="profileDetail.detentionStatusId"/>">
								<bean:write name="courtHearingForm" property="profileDetail.detentionStatus"/></span>
							</td>--%>
						 	<td class='formDe' >
								<logic:notEmpty  name="courtHearingForm" property="facilityStatusDesc">
								<span title="<bean:write name="courtHearingForm" property="detainedFacilityDesc"/>">
								<bean:write name="courtHearingForm" property="detainedFacility"/></span>&nbsp;/&nbsp;
								<span title="<bean:write name="courtHearingForm" property="facilityStatusDesc"/>"><bean:write name="courtHearingForm" property="facilityStatus"/></span>
								</logic:notEmpty>
							</td> 
							<%-- <td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.supervisionType"/></td>
							<td class='formDe' ><span title="<bean:write name="courtHearingForm" property="supervisionTypeId"/>"><bean:write name="courtHearingForm" property="supervisionType"/></td> --%>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.supervisionCategory"/></td>
							<td class='formDe' ><span title="<bean:write name="courtHearingForm" property="supervisionCategory"/>"><bean:write name="courtHearingForm" property="supervisionCategoryId"/></span></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.JPO"/></td>
							<td class='formDe'><span title="<bean:write name="courtHearingForm" property="profileDetail.jpoOfRecord"/>"><bean:write name="courtHearingForm" property="profileDetail.jpoOfRecID"/></span></td>
							<logic:equal name ="courtHearingForm" property="action" value="DALognumSearch">											
								<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.daLogNumber"/></td>
								<td class='formDe'><bean:write name="courtHearingForm" property="daLogNum"/></td>						
							</logic:equal>							
						</tr>
						
					</table>
				</td>
			</tr>
		</table>		
		<%-- <logic:equal name ="courtHearingForm" property="action" value="DALognumSearch">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="dalognumTbl">
			<tr align="center">
				<td class='formDeLabel'  width="5%" nowrap="nowrap" valign="center"><bean:message key="prompt.daLogNumber"/></td>
				<td class='formDe' valign="center"><bean:write name="courtHearingForm" property="daLogNum"/></td>		
			</tr>
		</table>
		</logic:equal> --%>
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
							<td></td>
							<td><bean:message key="prompt.refNo"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="referralNumber" primarySortType="STRING" defaultSortOrder="DESC" sortId="1" defaultSort="true"/>
							</td>
							<td><bean:message key="prompt.referralDt"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="referralDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="2" />
							</td>
							<td><bean:message key="prompt.offense"/>/<bean:message key="prompt.petitionAllegation"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="offense" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
							</td>
							<td><bean:message key="prompt.levelUnit"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="supervisionCategoryId" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" />
							</td>
							<td><bean:message key="prompt.JPO"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="jpo" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							</td>
							<td><bean:message key="prompt.intake"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="intakeDecisionId" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
							</td>
							<td>
								<bean:message key="prompt.intakeDate"/>
								<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="intakeDecDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="7" />							
							</td>
							<td><bean:message key="prompt.courtId"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="courtId" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
							</td>
							<td><bean:message key="prompt.courtDate"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="courtDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="9" />
							</td>
							<td><bean:message key="prompt.adjDSN"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="courtResult" primarySortType="STRING"  defaultSortOrder="ASC" sortId="10" />
							</td>
							<td><bean:message key="prompt.finalDisposition"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="finalDisposition" primarySortType="STRING"  defaultSortOrder="ASC" sortId="11" />
							</td>
							<td><bean:message key="prompt.petition"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" />
							</td>
							<td><bean:message key="prompt.dateClosed"/>
							<jims:sortResults beanName="courtHearingForm" results="referralList" primaryPropSort="closeDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="13" />
							</td>
						</tr>
	<logic:iterate id="referralList" name="courtHearingForm" property="referralList" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">	
			<td class='formDe' >
				 <input type="radio" name="referralRec" value=<bean:write name='referralList' property='referralNumber'/> styleId = "referralId" >
				</td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="ncicCode"/>"><bean:write name="referralList" property="referralNumber"/></td>
				<td class='formDe' ><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' >
					<span title="<bean:write name="referralList" property="offenseDesc"/>, <bean:write name="referralList" property="offenseCategory"/>">
						<bean:write name="referralList" property="offense"/>				
					</span>				
					<logic:notEmpty  name="referralList" property="petitionAllegation">
						/<span title="<bean:write name="referralList" property="petitionAllegationDescr"/>, <bean:write name="referralList" property="petitionallegationCategory"/>"><bean:write name="referralList" property="petitionAllegation"/></span>
					</logic:notEmpty>
				</td>
				<td class='formDe' >
				<logic:notEmpty  name="referralList" property="supervisionCategory">
					<span title="<bean:write name="referralList" property="supervisionCategory"/>"><bean:write name="referralList" property="supervisionCategoryId"/></span>/<span title="<bean:write name="referralList" property="supervisionType"/>"><bean:write name="referralList" property="supervisionTypeId"/></span>
				</logic:notEmpty>
				</td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="jpo"/>"><bean:write name="referralList" property="jpoId"/></span></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="intakeDecision"/>"><bean:write name="referralList" property="intakeDecisionId"/></span></td>
				<td class='formDe' ><span title="FIRST PROGRAM BEGIN"><bean:write name="referralList" property="intakeDecDate"/></span></td>
				<logic:notEmpty  name="referralList" property="transferredFrom">
					<td class='formDe' style="color:red;" title='<bean:write name="referralList" property="transferredFrom"/>'><b><bean:write name="referralList" property="courtId"/></b></td>
				</logic:notEmpty>
				<logic:empty name="referralList" property="transferredFrom">
                    <td class='formDe' ><bean:write name="referralList" property="courtId"/></td>
                </logic:empty> 
				<td class='formDe' ><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="courtResultDesc"/>"><bean:write name="referralList" property="courtResult"/></span></td>
				<td class='formDe' ><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td>
				<td class='formDe' ><bean:write name="referralList" property="petitionNumber"/></td>
				<td class='formDe' ><bean:write name="referralList" property="closeDate" formatKey="date.format.mmddyyyy"/></td>
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
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTINISETTNG%>'>
						<html:submit property="submitAction" styleId="initialSettingBtn"><bean:message key="button.initialSettingBtn"/></html:submit> 
					</jims2:isAllowed>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTPETUPDATE%>'>
						<html:submit property="submitAction" styleId="petitionUpdateBtn"><bean:message key="button.petitionUpdateBtn" /></html:submit>
					</jims2:isAllowed>
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
		<!-- html hidden fields ends -->
	
	</html:form>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>