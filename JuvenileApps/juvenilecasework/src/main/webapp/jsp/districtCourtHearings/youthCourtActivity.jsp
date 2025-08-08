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
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ page import="naming.Features" %>
<%@page import="messaging.calendar.reply.DocketEventResponseEvent"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




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
<style type="text/css">
.panel-title
	{
		font-family: Geneva, Arial, Helvetica, sans-serif;
		color: #000000;
		background-color: #cccccc;
		padding-right:5px;
		text-align:left;
		font-weight: 500 !important;
		color: #272C36;
	}

.crtDetailHead
{
	/*font-family: Arial, Helvetica, sans-serif;*/
	font-size: 12px;
	font-weight: bold;
	color: #191919;
	background-color: #ACE1AF;
	padding: 3px;
}

.borderTableX {
	border-style:thin solid;
	border-color:#000000;
}
</style>	
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtActivityByYouth.js"></script>
<script type="text/javascript"> 
$(function() { 
	$("#historyLink").click(function(){		
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="courtHearingForm" property="juvenileNumber"/>';
		 window.myVariable=juvNum;
		 var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Facility&actionType=popup&juvnum=" +juvNum;
		 var facilityWindow = window.open(url, "facilityHistWin", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 facilityWindow.focus();	
		localStorage.setItem("facilityHistWin", "open");
		localStorage.setItem("juvnum", juvNum);
		return false;		
		});
 });
$(document).ready(function () 
		{
				var webApp = "/<msp:webapp/>";
			 	var juvNum = '<bean:write name="courtHearingForm" property="juvenileNumber"/>';
				url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Facility&actionType=popup&juvnum=" +juvNum;	
				var facilityWinStatus = localStorage.getItem("facilityHistWin");
				var juvInWindow = localStorage.getItem("juvnum");				
				if(facilityWinStatus == "open")
				{	
					if(juvInWindow!= null && juvInWindow == juvNum)
						return false;
					//window is open just refresh it
					 var facilityWindow = window.open(url, "facilityHistWin", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
					 localStorage.setItem("facilityHistWin", "open");				
					 facilityWindow.focus();		
					return false;
				}	
		});
 </script>
<title><bean:message key="title.heading" />/youthCourtActivity.jsp</title>
</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/handleJuvenileDistrictCourtActivityByYouth" target="content" styleId="courtHearingForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Court Activity By Youth</h2></td>
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
					    <li>Enter a Juvenile Number and Click the GO button to Search again</li>
						<li>Click the Master Display button to transfer to the Court Briefing Page.</li>
						<li>Click Back button to return to the Court Main Menu page.</li>
					</ul>
				</td>
			</tr>
		</table>
		<%-- END INSTRUCTION TABLE --%>	
		
		
		<!-- Ancillary Display Information  starts-->
		<table width="98%" cellpadding="0" cellspacing="0" align="center" class="borderTableX" colspan="11" id="youthCourtDisplayTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
					   <tr class="crtDetailHead">
							<td align='left' colspan="11" class='paddedFourPix'>&nbsp;JUVENILE INFORMATION</td>
							<td align='right'>
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>
									<a href="#" id="historyLink"><bean:message key="prompt.facilityHistory" /></a>
					   	 			&nbsp;
					   	 		</jims2:isAllowed>
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_CASEWORKBRIEFINGDETAILSSCREEN%>'>
									<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?juvenileNum=<bean:write name='courtHearingForm' property='juvenileNumber'/>&fromProfile=profileBriefingDetails&submitAction=Link">Casework Briefing</a>
								</jims2:isAllowed>
							</td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap">JUVENILE NO.</td>
							<td class='formDe'><html:text name="courtHearingForm" property="juvenileNumber" styleId="juvNum" readonly="false"  maxlength="6" size="6"/>
							&nbsp;&nbsp;&nbsp;&nbsp;<html:submit property="submitAction" styleId="reSubmitbtn"><bean:message key="button.go"/></html:submit></td>
							
							<td class='formDeLabel'  width="5%" nowrap="nowrap">JUVENILE NAME</td>
							<td class='formDe'><bean:write name="courtHearingForm" property="juvenileName"/>
							<logic:equal name="courtHearingForm" property="rectype" value="I.JUVENILE">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
							</logic:equal>
							<logic:equal name="courtHearingForm" property="rectype" value="S.JUVENILE">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
							</logic:equal>
							&nbsp;&nbsp;
							<logic:notEqual name="courtHearingForm" property="profileDetail.preferredFirstName" value="">
								<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="courtHearingForm" property="profileDetail.preferredFirstName"/></span>
							&nbsp;
							</logic:notEqual>
							</td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap">RACE</td>
							<td class='formDe'><bean:write name="courtHearingForm" property="raceId"/></td>
							
							<td class='formDeLabel'  width="5%" nowrap="nowrap">SEX</td>
							<td class='formDe'><bean:write name="courtHearingForm" property="sexId"/></td>
							
							<td class='formDeLabel'  width="5%" nowrap="nowrap">AGE</td>
							<td class='formDe'><bean:write name="courtHearingForm" property="juvAge"/></td>
							
							<td class='formDeLabel'  width="5%" nowrap="nowrap">Facility : Status</td>
							<logic:notEmpty name="courtHearingForm" property="facilityHeader">
								<logic:notEqual name="courtHearingForm" property="facilityStatus"  value="">
									<td class='formDe'><strong><bean:write name="courtHearingForm" property="facilityHeader.facilityCode"/></strong>
										: <bean:write name="courtHearingForm" property="facilityStatus"/></td>
								</logic:notEqual>
								<logic:equal name="courtHearingForm" property="facilityStatus"  value="">
									<td class='formDe'></td>
								</logic:equal>
								
							</logic:notEmpty>
							<logic:empty name="courtHearingForm" property="facilityHeader">
								<td class='formDe'></td>
							</logic:empty>
																
						</tr>
				
					</table>
				</td>
			</tr>
		</table>
		<br/>
		<table align="center">
			<tr align="center">
				<td style='font-weight: bold; font-size: small '><bean:write name="courtHearingForm" property="searchResultSize"/> SETTINGS FOUND FOR COURT ACTIVITY.</td>
			</tr>
		</table>
		<br>
		
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="youthCourtDisplayTbl">
		<tr>
		<td>
		<table width='100%' cellpadding="2" cellspacing="1">
					<tr class="crtDetailHead" colspan="9">
						<td class="crtDetailHead">COURT #						
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="court" primarySortType="STRING" defaultSortOrder="ASC" sortId="2"/>
						</td>
						
						<td class="crtDetailHead">SETTING <br /> DATE
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="courtDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="3" />
						</td>
						
						<td class="crtDetailHead">PETITION <br /> INFORMATION
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4"/>
						</td>
						
						<td class="crtDetailHead">ALLEG.
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionAllegation" primarySortType="STRING"  defaultSortOrder="ASC" sortId="5"/>
						</td>
						
						<td class="crtDetailHead">REF <br /> NO.
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="referralNum" primarySortType="STRING" defaultSortOrder="DESC" sortId="6" />
						</td>
						
						<td class="crtDetailHead">HR <br /> TY
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="7"/>
						</td>
						
						<td class="crtDetailHead">DISPOSITION / RESULT
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="dispositionDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
						</td>
						<td class="crtDetailHead">TRN
						</td>
						<td class="crtDetailHead">FILING DATE
						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="filingDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="9" />
						</td>
						
					</tr>
	<logic:iterate id="courtSettings" name="courtHearingForm" property="dktSearchResults" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">	
				<td class='formDe' ><bean:write name="courtSettings" property="court"/></td>
				<td class='formDe' ><bean:write name="courtSettings" property="courtDate"/></td>
				<td class='formDe' ><bean:write name="courtSettings" property="petitionNumber"/>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title='<bean:write name="courtSettings" property="petitionStatus"/>'><bean:write name="courtSettings" property="petitionStatusCd"/>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="courtSettings" property="petitionAmendment"/></td>
				<td class='formDe' ><span title="<bean:write name="courtSettings" property="petitionAllegationDesc"/>, <bean:write name="courtSettings" property="petitionallegationCategory"/>"><bean:write name="courtSettings" property="petitionAllegation"/></td>
				<td class='formDe' ><bean:write name="courtSettings" property="referralNum"/></td>
				<td class='formDe' ><span title='<bean:write name="courtSettings" property="hearingTypeDesc"/>'><bean:write name="courtSettings" property="hearingType"/></td>
				<td class='formDe' ><bean:write name="courtSettings" property="dispositionDesc"/></td>
				<td class='formDe'>
										<elogic:if name="courtSettings" property="transferTo" op="equal" value="">
											<elogic:then>
												<font size="-1"></font>
											</elogic:then>
											<elogic:else>
												<span title='<bean:write name="courtSettings" property="transferTo"/>'><font size="-1">T</font></span>
											</elogic:else>
										</elogic:if> 
				</td>
				<td class='formDe' ><bean:write name="courtSettings" property="filingDate"/></td>
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
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTMASBRIEFG%>'>
				    	<html:submit property="submitAction" styleId="updateMasterBtn"><bean:message key="button.masterDisplay"/></html:submit>
				    </jims2:isAllowed>
					<input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileDistrictCourtNameSearchResults.do?submitAction=<bean:write name='courtHearingForm' property='fromPage'/>')" value="<bean:message key='button.back'/>"/>&nbsp;
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTN%>'>
						<html:submit onclick="spinner()" property="submitAction" styleId="courtActionBtn"><bean:message key="button.courtAction2"/></html:submit>
					</jims2:isAllowed>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTREFINQ%>'>
						<input id="referralSummary" type="button" onclick="goNav('/<msp:webapp/>processJuvenileDistrictCourtHearings.do?submitAction=REFERRAL INQUIRY')" value="Referral Summary"/>&nbsp;
					</jims2:isAllowed>
				</td>
			</tr>
		</table>
		<!--  Button Table ends-->
		
		<!-- html hidden fields starts -->
		<html:hidden styleId="action" name="courtHearingForm" property="action"/>
		<html:hidden styleId="fromPage" name="courtHearingForm" property="fromPage"/>
		<!-- html hidden fields ends -->
	
	</html:form>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>