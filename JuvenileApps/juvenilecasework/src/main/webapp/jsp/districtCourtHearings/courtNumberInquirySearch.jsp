<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/numberInquiry.js"></script>
<html:base />

<title><bean:message key="title.heading" />/courtNumberSearchResults.jsp</title>
<style>
tr.separated td {
    /* set border style for separated rows */
    border-bottom: 2px solid green;
} 

table {
    /* make the border continuous (without gaps between columns) */
    border-collapse: collapse;
}
</style>
</style>

</head>
<%--END HEAD TAG--%>
<table width='100%'>
	<tr>
		<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Number Inquiry</h2></td>
	</tr>
</table>
        
        <!-- END HEADING TABLE -->
        <!-- BEGIN INSTRUCTION TABLE -->
        <br>
        <table width="98%" border="0">
          <tr>
            <td>
              <ul>
                <li>Click the radio button and then click the Master Display button to navigate to the Master Display screen.</li>
                <li>Click the radio button and then click the Initial Setting button to navigate to the Initial Setting screen.</li>
                <li>Click the Court Main Menu button to navigate to the Court Main Menu screen.</li>
                <li>Click the REF# hyperlink to expand info about the referrals.</li>
              </ul>
            </td>
          </tr>
        </table>
        <!-- END INSTRUCTION TABLE -->
<%--BEGIN BODY TAG--%>
<body>
   <html:form action="/handleNumberInquiry" target="content" styleId="initialSettingForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">
		<br/>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->
<br/>
	
		<%-- Begin Pagination Header Tag--%>
			<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
			<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
			    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
			<input type="hidden" name="pager.offset" value="<%= offset %>">
		<%-- End Pagination header stuff --%>

		<table width="98%" cellpadding="0" cellspacing="0" align="center" class="borderTableX" colspan="3" id="ancillaryDisplayTbl">
			<tr class='crtDetailHead'>
				<td align='left' colspan="8" class='paddedFourPix'>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
							<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqJuvNum"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.juvNo"/></td>
							<td class='formDe' width="15%" colspan='1' nowrap>
							<html:text name="courtHearingForm" property="juvenileNumber" styleId="juvNum" maxlength="12" size="12"/>
							</td>
							<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span id="reqRef"><bean:message key="prompt.2.diamond"/></span><bean:message key="prompt.refNo"/></td>
							<td class='formDe' width="15%" colspan='1' nowrap>
								<html:text name="courtHearingForm" property="referralNumber" styleId="refNum"  maxlength="4" size="4"/>
								<html:submit property="submitAction" styleId="submitNumberInquiry"><bean:message key="button.go" /></html:submit>
							</td> 							
						</tr>				
					</table>
				</td>
			</tr>
		</table>
		<br/>
		<%-- BEGIN INSTRUCTION TABLE --%>
		<table width="98%" border="0" align="center">
		  <tr>
		    <td align="center"><bean:write name="courtHearingForm" property="searchResultSize"/> Record(s) Found in search results</td>
		  </tr>
		</table> 
		<%-- END INSTRUCTION TABLE --%>
		<%-- BEGIN DETAIL TABLE --%>
		<div id="searchResults">
		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
		  <tr>
			 <td colspan="7">
				<table width='100%' cellpadding="2" cellspacing="1">
					<tr class="crtDetailHead">
					<td width="1%"></td>
						<td width='10%'>   
							<bean:message key="prompt.juv" />#
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="juvenileNumber" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
						</td>
						<td width='10%'> 
							REF#&nbsp;  
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="referralNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
						</td>
						<td width='10%'> 
							<bean:message key="prompt.supervision#" /> 							
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="supervisionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
						</td>
						<td width='21%'>   
							<bean:message key="prompt.name" /> 	
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" sortId="3" />
						</td>
						<td width='8%'>   
							<bean:message key="prompt.referralStatus" />
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="status" primarySortType="STRING" sortId="4" />
						</td>
						<td width='20%'>   
							SUPERVISION CATEGORY
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="unit" primarySortType="DATE" sortId="6" />
						</td>
						<td width='10%'>   
							<bean:message key="prompt.JPO" />
							<jims:sortResults beanName="courtHearingForm" results="juvCourtHearingList" primaryPropSort="jpo" primarySortType="DATE" sortId="7" />
						</td>						
					</tr>
				<logic:iterate id="juvCourtHearingList" name="courtHearingForm" property="juvCourtHearingList" indexId="indexer"> 
				  <%-- Begin Pagination item wrap --%>
				  <pg:item>
					<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" heignt="100%">		 	
						<bean:define id="selectedJuvId" type="java.lang.String" name="juvCourtHearingList" property="juvenileNumber" />
						<bean:define id="selectedRefNum" type="java.lang.String" name="juvCourtHearingList" property="referralNumber" />
						<td width="1%"><input type="radio" name="selectedId" value="<%=selectedJuvId%>|<%=selectedRefNum%>"/></td>
						<td width='10%'><bean:write name='juvCourtHearingList' property='juvenileNumber'/></td>
						<td width='10%'><a href="#" class="toggler" data-prod-cat="1"><span class="hist-plus"></span><bean:write name="juvCourtHearingList" property="referralNumber" /></a></td>
						<td width='10%'><bean:write name="juvCourtHearingList" property="supervisionNumber"/></td>   		
						<td width='21%'><bean:write name="juvCourtHearingList" property="juvenileName"/></td>
						<td width='8%'><span title='<bean:write name="juvCourtHearingList" property="statusDesc"/>'><bean:write name="juvCourtHearingList" property="statusCd"/></td>						
						<td width='20%'><bean:write name="juvCourtHearingList" property="supervisionCategory"/></td>
						<td width='10%'><span title='<bean:write name="juvCourtHearingList" property="officerName"/>'><bean:write name="juvCourtHearingList" property="jpoOfficer"/></td>
					</tr>
					<br\>
					<tr class="history1" style="display:none">
					<logic:notEmpty name="juvCourtHearingList" property="historyList">
                  		<td valign='top' colspan="8">
                  			<table width='100%' cellpadding="2" cellspacing="1">
	                   			<logic:iterate id="historyIt" name="juvCourtHearingList" property="historyList"  indexId="index">
	                          		<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	                          		<td width="2%"></td>
                  					<td width='10%'></td>
									<td width='10%'></td>
									<td width='10%'><bean:write name="historyIt" property="supervisionNumber"/></td>   		
									<td width='21%'><bean:write name="historyIt" property="juvenileName"/></td>
									<td width='8%'><span title='<bean:write name="historyIt" property="statusDesc"/>'><bean:write name="historyIt" property="statusCd"/></td>						
									<td width='20%'><bean:write name="historyIt" property="supervisionCategory"/></td>
									<td width='10%'><span title='<bean:write name="historyIt" property="officerName"/>'><bean:write name="historyIt" property="jpoOfficer"/></td>
	       	        				</tr>
  								</logic:iterate>     
							</table>
	                	</td>
	          		</logic:notEmpty>	          									
					</tr>
					<tr class="separated">
     					 <td colspan="7"><td>
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
		</div>
		<%-- END DETAIL TABLE --%>
		</pg:pager>
		<%-- Pagination Closing Tag --%>
	</html:form>
	
<!-- Button Table Starts-->
				<br/>
		<table width="100%" border="0">
		<%-- <tr>
				<td align="center">
					<html:submit property="submitAction" styleId="submitNumberInquiry"><bean:message key="button.submit"/></html:submit>					
				</td>
			</tr> --%>
			<tr>
				<td align="center">			
					<html:submit property="submitAction" styleId="submitYouthCrtActBtn">Court Activity By Youth</html:submit>
					<html:submit property="submitAction" styleId="updateMasterBtn"><bean:message key="button.masterDisplay"/></html:submit>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTINISETTNG%>'>					
						<html:submit property="submitAction"  styleId="submitInitialSettingBtn" ><bean:message key="button.initialSettingBtn"/></html:submit>
					</jims2:isAllowed>
					<html:submit property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu" /></html:submit>
					<input id="submitReferralSearchBtn" type="button" value="Referral Summary"/>&nbsp;
				</td>
			</tr>
		</table>
		<br/>
		<!--  Button Table ends-->
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>