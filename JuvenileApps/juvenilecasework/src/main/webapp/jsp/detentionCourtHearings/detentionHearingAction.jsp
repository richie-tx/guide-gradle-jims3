<!DOCTYPE HTML>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>
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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/detentionCourt/detentionHearings.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/detentionHearingAction.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/handleJuvenileSearchDetentionHearings" target="content">
<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Search Detention Hearings<br/><br/>Detention Hearing Action</h2></td>
	</tr>
</table>


<!-- BEGIN ERROR TABLE  -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr> 
	<tr>		  
		<td align="center" class="errorAlert"><bean:write name='detentionHearingForm' property='errMessage' /></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE  -->
  
 <!-- BEGIN Error Message Table -->
	<table width="100%">  	
		<tr>		  
			<td align="center" class="messageAlert"><bean:write name='detentionHearingForm' property='message' /></td>		  
		</tr>   	  
	</table>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select a setting and click on Delete Setting to delete a setting.</li>
        <li>Click Court Main Menu to return to the main page.</li>
      </ul>
    </td>
  </tr>
  
 <logic:notEmpty name="detentionHearingForm" property="detentionSearchResults">
   <tr>
    <td align="center">(<bean:write name="detentionHearingForm" property="searchResultSize"/>) Settings Found</td>
  </tr>
</logic:notEmpty>
<br/>
</table>
<jims:isAllowed requiredFeatures="<%=Features.JCW_DETACTN_UPDATE%>">
<%-- END INSTRUCTION TABLE --%>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue" colspan="5">      
 <tr class='crtDetailHead'>
		<td align='left' colspan="7" class='paddedFourPix'>&nbsp;</td>
	</tr>    
	<tr>
		<td class='formDeLabel' colspan="1" nowrap="nowrap" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.court"/></td>
		<td class='formDe' colspan="1"><bean:write name="detentionHearingForm" property="courtId"/></td>
		<td class='formDeLabel' colspan="1" nowrap="nowrap" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date"/></td>
		<td class='formDe' width="1%">	
			<html:text name="detentionHearingForm" property="date" styleId="date" size="10" maxlength="10" size="10"/>
		</td>
		<td class='formDe'  width="50%" align="left" colspan="1"><html:submit property="submitAction" styleId="goBtn"><bean:message key="button.go"/></html:submit></td>
	</tr>
</table>
<br><br/>

<logic:notEmpty name="detentionHearingForm" property="detentionSearchResults">
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=4%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" id="pagerOffset" value="<%= offset %>">
<input type="hidden" name="pageNum" id="pageNumber" value="<%= currentPageNumber %>">
<!-- End Pagination header stuff -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1" id="data">
	 				<tr class="crtDetailHead" colspan="9">   
	 					<td></td>
	    				<td width="10%">   
							<bean:message key="prompt.juvenile" />#
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvenileNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" /> 
	          			</td>
	          			<td>   
							<bean:message key="prompt.juvenileName" />
							 <jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
	          			</td>
	          			<td> 
							<bean:message key="prompt.raceSexAge" />
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="raceId" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
	          			</td>
	          			<td> 
							<bean:message key="prompt.result" /> 
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="courtResult" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
	          			</td>
	          			<td>
	  						<bean:message key="prompt.transfer" />
	  					</td>
						 <td nowrap="nowrap">
	  						<bean:message key="prompt.transferTo" />
	  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="transferTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.resetTo" />
	  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="resetTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.hearingType" />
	  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" />
		          		</td>
					</tr>
					
					<%int count=0;%>
	  				<logic:iterate id="dockets" name="detentionHearingForm" property="detentionSearchResults" indexId='indexer' type="messaging.calendar.reply.DocketEventResponseEvent"> 	  					
	          			<bean:define id="currDocketEventId" name='dockets' property='docketEventId' type="java.lang.String"></bean:define>
	          			<html:hidden styleId="docketEventId"  name="dockets" property="docketEventId" indexed="true"/>
		    			<%-- Begin Pagination item wrap --%>
	          			<%count++; %>
	          			<pg:item>	          			
		    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="11" id='<bean:write name="dockets" property="recType"/>'>		    			
		    						<html:hidden styleId='<%="prevCourtResult-"+currDocketEventId%>'  name='dockets' property='prevCourtResult' indexed="true"/>		    						
		    						<html:hidden styleId='<%="oldCourtResult-"+currDocketEventId%>'  name='dockets' property='oldcourtResult' indexed="true"/>
									<html:hidden styleId='<%="docketId-"+currDocketEventId%>'  name='dockets' property='docketEventId' indexed="true"/>
		    						<html:hidden styleId='<%="atyConfirmation-"+currDocketEventId%>' name="dockets" property="atyConfirmation" indexed="true"/>
								<td><input type="checkbox" id="<bean:write name='dockets' property='docketEventId'/>" name="detentionSearchResults.selectedDocketsForUpdate" value="<bean:write name='dockets' property='docketEventId'/>"></td>
		    					<td class='formDe'><a onclick="spinner()" href="/<msp:webapp/>processJuvenileDistrictCourtHearings.do?juvnum=<bean:write name="dockets" property="juvenileNumber" />&fromPage=detention&submitAction=courtActivityByYouth"><bean:write name="dockets" property="juvenileNumber" /></a></td>
		    					<td class='formDe'><html:text name="dockets" property="juvenileName" size="32" styleId='<%="juvName-" + currDocketEventId%>' disabled="true" style="border: 0" indexed="true"/></td>
								<td class='formDe'>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dockets" property="raceId"/>&nbsp;/&nbsp;<bean:write name="dockets" property="hispanicInd"/>&nbsp;/&nbsp;<bean:write name="dockets" property="sexId"/>&nbsp;/&nbsp;<bean:write name="dockets" property="age"/></td>
								<td class='formDe'> 
		    						<html:select name="dockets" tabindex="0" property="courtResult" styleId='<%= "courtResult-" + currDocketEventId%>' indexed="true">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="courtDecisions" value="code" label="descriptionWithCode"/> 				
									</html:select>
								</td>
								<td  class='formDe'><input type="checkbox" id='<%="TRN-"+currDocketEventId%>' name="dockets"></td><!-- <td class='formDe'></td> -->
								<html:hidden styleId='<%="TRNValue-"+currDocketEventId%>'  name="dockets" property="transferFlag" indexed="true"/>
		    					<td class='formDe'><html:text name="dockets" property="transferTo" size="3" styleId='<%="transTo-" + currDocketEventId%>' indexed="true" disabled="true"/>
		    					<html:hidden styleId='<%="oldtransTo-"+currDocketEventId%>'  name="dockets" property="oldtransferTo" indexed="true"/>
		 						</td> <!-- new field -->
	    						<td class='formDe'><html:text name="dockets" property="resetTo" styleId='<%="resetToDate-" + currDocketEventId%>' size="10" indexed="true"/>
	    						</td>	    						
	    						<td class='formDe' ><span title='<bean:write name="dockets" property="hearingTypeDesc"/>'><html:text name="dockets" property="hearingType" styleId='<%="hearingType-" + currDocketEventId%>' size="2" disabled="true" style="border: 0" indexed="true"/></td>
	    						<html:hidden styleId='<%="juvNum-"+currDocketEventId%>'  name="dockets" property="juvenileNumber" indexed="true"/>
		    				</tr>
		    				
		    				<tr id='<bean:write name="dockets" property="recType"/>'>
		    			
		    					<td class='formDe'></td>		    					
		    					<td class='formDe'>
		    						<jims:isAllowed requiredFeatures="<%=Features.JCW_DET_ATTORNEY_CONFIRMATION_BYPASS%>">
			    						<html:button property="attorneyBypass" value="Attorney Bypass" disabled="true" styleId='<%="attnyBypass."+currDocketEventId%>' indexed="true"></html:button>
			    						<html:hidden styleId='<%="atyBypass-"+currDocketEventId%>' name="dockets" property="atyBypass" indexed="true"/>
			    					</jims:isAllowed>
			    				</td> 
		    					<td class='formDe'>
		    						<html:button property="attorneyConfirm" value="Attorney Confirmation" disabled="true" styleId='<%="attnyConfm."+currDocketEventId%>' indexed="true"></html:button>
		    					</td>
		    					 <td class='formDe' align="top" nowrap><span class='formDeLabel'><bean:message key="prompt.attorney"/></span> 
		    					 <jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="attorneyName" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
		    					 <!-- </td>
							   <td class='formDe' align="top" nowrap> -->
							   <elogic:if name="dockets" property="atyConfirmation" op="notequal"	value="">
								<elogic:then>
									<input type="text" name="dockets" property="attorneyName" styleId='<%="attorneyName-" + currDocketEventId%>' size="35" maxlength="50" value="<bean:write name="dockets" property="attorneyName"/>"  id="attorneyName-<bean:write name="dockets" property="docketEventId"/>" disabled/>
								</elogic:then>
								<elogic:else>
									<logic:equal name="dockets" property="seqNum" value="10">
										<input type="text" name="dockets" style="border: 0;background-color: yellow" property="attorneyName" styleId='<%="attorneyName-" + currDocketEventId%>' size="35" maxlength="50" value="<bean:write name="dockets" property="attorneyName"/>"  id="attorneyName-<bean:write name="dockets" property="docketEventId"/>" disabled/>
									</logic:equal>
									<logic:notEqual name="dockets" property="seqNum" value="10">
										<input type="text" name="dockets" style="border: 0;background-color: cyan" property="attorneyName" styleId='<%="attorneyName-" + currDocketEventId%>' size="35" maxlength="50" value="<bean:write name="dockets" property="attorneyName"/>"  id="attorneyName-<bean:write name="dockets" property="docketEventId"/>" disabled/>
									</logic:notEqual>	
								</elogic:else>
								</elogic:if>
			    				<input type="hidden" name="validateMsg" value="<bean:write name="detentionHearingForm" property="validateMsg" />"  id="valOffMsgId"/>
							    <html:hidden styleId='<%="oldAttorney-"+currDocketEventId%>'  name="dockets" property="oldattorneyName" indexed="true"/>
		 						</td>
							   <td class='formDe'><input type="submit" name="submitAction" property="searchAttorney" value="Search Attorney" id="searchAttorney.<bean:write name="dockets" property="docketEventId" />" indexed="true"></input>
							   <span class='formDeLabel'><bean:message key="prompt.bar" /></span>
									<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="barNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" />
									<input type="text" name="dockets"  indexed="true" value="<bean:write name="dockets" property="barNum"/>" maxlength="8" size="8"  id="barNum-<bean:write name="dockets" property="docketEventId"/>"/>
									&nbsp;&nbsp;
									<input type="submit" name="submitAction" value="Validate" id="validateBarNumber.<bean:write name="dockets" property="docketEventId"/>" style="width:2">&nbsp;									 
									<html:hidden styleId='<%="oldBarnum-"+currDocketEventId%>'  name="dockets" property="oldbarNum" indexed="true"/>
		 						
							   </td>
	
								<%-- <td class='formDe'>
									<span class='formDeLabel'><bean:message key="prompt.bar" /></span>
									<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="barNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" />
									<input type="text" name="dockets"  indexed="true" value="<bean:write name="dockets" property="barNum"/>" maxlength="8" size="8"  id="barNum-<bean:write name="dockets" property="docketEventId"/>"/>
									&nbsp;&nbsp;
									<input type="submit" name="submitAction" value="Validate" id="validateBarNumber.<bean:write name="dockets" property="docketEventId"/>" style="width:2">&nbsp;									 
									<html:hidden styleId='<%="oldBarnum-"+currDocketEventId%>'  name="dockets" property="oldbarNum" indexed="true"/>
		 						 --%>
		 						<td class='formDe'></td>
								<td class='formDe' nowrap>
								    <span class='formDeLabel'><bean:message key="prompt.connectionCode" /></span><span title='HIRED'>
								    <html:radio name="dockets" property="attorneyConnection" value="HAT" indexed="true" styleId='<%="attyConn1-" + currDocketEventId%>'>HAT</html:radio><span title='APPOINTED'>
									<html:radio name="dockets" property="attorneyConnection" value="AAT" indexed="true" styleId='<%="attyConn2-" + currDocketEventId%>'>AAT</html:radio><span title='PUBLIC DEFENDER'>
									<html:radio name="dockets" property="attorneyConnection" value="PDO" indexed="true" styleId='<%="attyConn3-" + currDocketEventId%>'>PDO</html:radio>
							   		<html:hidden styleId='<%="oldattorneyConn-"+currDocketEventId%>'  name="dockets" property="oldattorneyConnection" indexed="true"/>
									
							   </td>
			    			   	<td class='formDe'>
			    			   	<html:hidden styleId='<%="prevResetTo-"+currDocketEventId%>'  name='dockets' property='prevResetTo' indexed="true"/>
								</td>	
			    			   	<td class='formDe' colspan="3"></td>
							</tr>
							<tr id='<bean:write name="dockets" property="recType"/>'>
							<td class='formDe'></td>
							<logic:empty name="dockets" property="juvenileCoactors">
							<td class='formDe'></td>
							<td class='formDe'></td>
							</logic:empty>
							<logic:notEmpty name="dockets" property="juvenileCoactors">
   							<td class='formDe' align="center"><span class='formDeLabel'>Co-Actor(s):</span></td>
							<td class='formDe' >
							<logic:iterate id="coActors" name="dockets" property="juvenileCoactors" indexId="index">
							<bean:define id="coActorsList" name="dockets" property="juvenileCoactors" type="java.util.List" />
								<span title='<bean:write name="coActors" property="attorneyName"/>'><bean:write name="coActors" property="juvenileNum"/>
								<% if( index.intValue() != coActorsList.size()-1){ %>
                                  ,
                                <% } %>
							</logic:iterate>
							</td>
							</logic:notEmpty>							
							<td class='formDe' align="top" nowrap><span class='formDeLabel'>2nd Atty</span>
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="attorney2Name" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
							<!-- <td class='formDe' align="top" nowrap> -->
			    					<input type="text" name="dockets" property="attorney2Name" size="35" maxlength="50" value="<bean:write name="dockets" property="attorney2Name"/>"  id="2ndAttorney-<bean:write name="dockets" property="docketEventId"/>" disabled/>
									<input type="hidden" name="validateMsg" value="<bean:write name="detentionHearingForm" property="validateMsg" />"  id="valOffMsgId"/>
							    </td>
							   <td class='formDe'>
									<html:button property="search2ndAttorney" value="Search Attorney" styleId='<%="2ndAttorney."+currDocketEventId%>' indexed="true"></html:button>
									<span class='formDeLabel'>Bar#</span>
									<html:text name="dockets" property="attorney2BarNum" styleId='<%="2ndAttorneyBarnum-"+currDocketEventId%>'  maxlength="8" size="8"  indexed="true"/>
									<html:button property="validate2ndAttorneyBarNum" value="Validate" styleId='<%="validate2ndAttorneyBarNum."+currDocketEventId%>' indexed="true"></html:button>
								</td>
								<td  class='formDe'></td>
								<td class='formDe' colspan="3" nowrap>
								    <span class='formDeLabel'><bean:message key="prompt.connectionCode" /></span><span title='HIRED'>
								    <html:radio name="dockets" property="attorney2Connection" value="HAT" indexed="true" styleId='<%="2ndAttorneyHATattorneyConnection-" + currDocketEventId%>'>HAT</html:radio><span title='APPOINTED'>
									<html:radio name="dockets" property="attorney2Connection" value="AAT" indexed="true" styleId='<%="2ndAttorneyAATattorneyConnection-" + currDocketEventId%>'>AAT</html:radio><span title='PUBLIC DEFENDER'>
									<html:radio name="dockets" property="attorney2Connection" value="PDO" indexed="true" styleId='<%="2ndAttorneyPDOattorneyConnection-" + currDocketEventId%>'>PDO</html:radio>
							   	</td>
							</tr>
							<tr id='<bean:write name="dockets" property="recType"/>'>
							<td class='formDe'></td>
							<td class='formDe'></td>
							<td class='formDe'></td>
							<td class='formDe' align="top" nowrap><span class='formDeLabel'>GAL</span>
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="galName" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
							<!-- <td class='formDe' align="top" nowrap> -->
			    					<input type="text" name="dockets" property="galName" size="35" maxlength="50" value="<bean:write name="dockets" property="galName"/>"  id="galName-<bean:write name="dockets" property="docketEventId"/>" disabled/>
									<input type="hidden" name="validateMsg" value="<bean:write name="detentionHearingForm" property="validateMsg" />"  id="valOffMsgId"/>
							    </td>
							   <td class='formDe'>
									<html:button property="searchGAL" value="Search GAL" styleId='<%="galName."+currDocketEventId%>' indexed="true"></html:button>
									<span class='formDeLabel'>GAL Bar#</span>
									<html:text name="dockets" property="galBarNum" styleId='<%="galBarNum-"+currDocketEventId%>'  maxlength="8" size="8"  indexed="true"/>
									<html:button property="validateGAL" value="Validate" styleId='<%="validateGalBarNum."+currDocketEventId%>' indexed="true"></html:button>
								</td>
								<td class='formDe' colspan="4" nowrap>	
									<span>Interpreter?</span>	    					
		    						<html:checkbox styleId='<%="interpreter."+currDocketEventId%>' name="dockets" property="interpreterchk" indexed="true"></html:checkbox>
		    						<html:hidden styleId='<%="interpreter-"+currDocketEventId%>' name="dockets" property="interpreter" indexed="true"/>
		    					</td>
							</tr>
			    			<% if (count % 4!=0) {%>
			    				<tr colspan="11" width="1%"><td colspan="12" width="1%" class="formDe"><div style="border-color:#ACE1AF;border-style: solid; border-width: 5px;"></div> </td></tr>
			    			  <% }%>
			    			</pg:pages>
	  					</pg:item>
	  		  		</logic:iterate> 
		  	 	</table>
			</td>
		</tr>	
	</table>
	<br />
	<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
			<td>
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch" />
					<tiles:put name="resultsPerPage" value="4" />
					</tiles:insert>
				</pg:index>
			</td>
		</tr>
	</table>
<%-- End Pagination navigation Row--%>
</pg:pager>
<br />
<table width="100%" border="0">
	<table width="100%" border="0">
	<tr>
		<td align="center">
			<html:button property="submitAction" styleId="submitBtn"><bean:message key="button.submit" /></html:button>
			<jims:isAllowed requiredFeatures="<%=Features.JCW_CRTACTN_DELETE%>"> 
				<html:button property="submitAction" styleId="deleteSettingBtn"><bean:message key="button.deleteSetting"/></html:button>
			</jims:isAllowed>			
			<html:submit onclick="spinner()" property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu"/></html:submit>
    	</td>
	</tr>
</table>
	<tr>
	 <td><input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileSearchDetentionHearings.do?submitAction=Link')" value="<bean:message key='button.back'/>"/>&nbsp;</td>
	 </tr>
</table>
</logic:notEmpty>
</jims:isAllowed>

<!--if no feature show read only fields.  -->
<jims:isAllowed requiredFeatures="<%=Features.JCW_DETACTN_UPDATE%>" value="false">
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue" colspan="5">      
   <tr class='crtDetailHead'>
		<td align='left' colspan="7" class='paddedFourPix'>&nbsp;</td>
	</tr>    
	<tr>
		<td class='formDeLabel' colspan="1" nowrap="nowrap" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.court"/></td>
		<td class='formDe' colspan="1"><bean:write name="detentionHearingForm" property="courtId"/></td>
		<td class='formDeLabel' colspan="1" nowrap="nowrap" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date"/></td>
		<td class='formDe' width="1%">	
			<html:text name="detentionHearingForm" property="date" styleId="date" size="10" maxlength="10" size="10"/>
		</td>
		<td class='formDe'  width="50%" align="left" colspan="1"><html:submit property="submitAction" styleId="goBtn"><bean:message key="button.go"/></html:submit></td>
	</tr>
</table>
<br><br/>

<logic:notEmpty name="detentionHearingForm" property="detentionSearchResults">
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=4%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" id="pagerOffset" value="<%= offset %>">
<input type="hidden" name="pageNum" id="pageNumber" value="<%= currentPageNumber %>">
<!-- End Pagination header stuff -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1" id="data">
	 				<tr class="crtDetailHead" colspan="9">   
	 					<td></td>
	    				<td width="10%">   
							<bean:message key="prompt.juvenile" />#
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvenileNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" /> 
	          			</td>
	          			<td>   
							<bean:message key="prompt.juvenileName" />
							 <jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
	          			</td>
	          			<td> 
							<bean:message key="prompt.raceSexAge" />
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="raceId" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
	          			</td>
	          			<td> 
							<bean:message key="prompt.result" /> 
							<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="courtResult" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
	          			</td>
						 <td nowrap="nowrap">
	  						<bean:message key="prompt.transferTo" />
	  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="transferTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.resetTo" />
	  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="resetTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.hearingType" />
	  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" />
		          		</td>
					</tr>
					
					<%int count=0;%>
	  				<logic:iterate id="dockets" name="detentionHearingForm" property="detentionSearchResults" indexId='indexer' type="messaging.calendar.reply.DocketEventResponseEvent"> 	  					
	          			<%-- Begin Pagination item wrap --%>
	          			<%count++; %>
	          			<pg:item>	          			
		    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="9" id='<bean:write name="dockets" property="recType"/>'>		    			
		    						<bean:define id="currDocketEventId" name='dockets' property='docketEventId' type="java.lang.String"></bean:define>
		    						<html:hidden styleId='<%="prevCourtResult-"+currDocketEventId%>'  name='dockets' property='prevCourtResult' indexed="true"/>
		    						<html:hidden styleId='<%="docketId-"+currDocketEventId%>'  name='dockets' property='docketEventId' indexed="true"/>
		    						<td><input type="checkbox" id="<bean:write name='dockets' property='docketEventId'/>" name="detentionSearchResults.selectedDocketsForUpdate" value="<bean:write name='dockets' property='docketEventId'/>"></td>		    						
		    						<td class='formDe'><html:text name="dockets" property="juvenileNumber" size="6" styleId='<%="juvNum-" + currDocketEventId%>' disabled="true" style="border: 0" indexed="true"/></td>
		    						<td class='formDe'><html:text name="dockets" property="juvenileName" size="32" styleId='<%="juvName-" + currDocketEventId%>' disabled="true" style="border: 0" indexed="true"/></td>
									<td class='formDe'>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="dockets" property="raceId"/>&nbsp;/&nbsp;<bean:write name="dockets" property="hispanicInd"/>&nbsp;/&nbsp;<bean:write name="dockets" property="sexId"/>&nbsp;/&nbsp;<bean:write name="dockets" property="age"/></td>
									<td class='formDe'> 
		    						<html:select name="dockets" tabindex="0" property="courtResult" disabled="true" styleId='<%= "courtResult-" + currDocketEventId%>' indexed="true">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="courtDecisions" value="code" label="descriptionWithCode"/> 				
									</html:select>
									</td>
		    					<td class='formDe'><html:text name="dockets" property="transferTo" size="3" styleId='<%="transTo-" + currDocketEventId%>' disabled="true" indexed="true"/></td> <!-- new field -->
	    						<td class='formDe'><html:text name="dockets" property="resetTo" styleId='<%="resetToDate-" + currDocketEventId%>' size="10" disabled="true" indexed="true"/></td>	    						
	    						<td class='formDe' ><span title='<bean:write name="dockets" property="hearingTypeDesc"/>'><html:text name="dockets" property="hearingType" styleId='<%="hearingType-" + currDocketEventId%>' size="2" disabled="true" style="border: 0" indexed="true"/></td>
		    				</tr>
		    				
		    				<tr id='<bean:write name="dockets" property="recType"/>'>
		    			
		    					<td class='formDe'></td>
		    					<td class='formDe'></td>
		    					<td class='formDe' align="top" nowrap>
		    						<span class='formDeLabel'>Attorney Confirmation</span>
			    					<elogic:if name="dockets" property="atyConfirmation" op="notequal"	value="">
										<elogic:then>
											<font size="-1"> YES </font>
										</elogic:then>
										<elogic:else>
											<font size="-1"> NO </font>
										</elogic:else>
									</elogic:if>																	
								</td>
		    					 <td class='formDe' align="top" nowrap><span class='formDeLabel'><bean:message key="prompt.attorney"/></span> 
		    					 <jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="attorneyName" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
		    					 <bean:write name="dockets" property="attorneyName"/>
		    					 	
								</td>	
								<td class='formDe' nowrap>
									<span class='formDeLabel'><bean:message key="prompt.bar" /></span>
									<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="barNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" />
									<bean:write name="dockets" property="barNum"/>								  
								</td>
								<td class='formDe' nowrap>
									<span class='formDeLabel'><bean:message key="prompt.connectionCode" /></span><span title='HIRED'>
								    <html:radio name="dockets" property="attorneyConnection" disabled="true" value="HAT" indexed="true" styleId='<%="attyConn1-" + currDocketEventId%>'>HAT</html:radio><span title='APPOINTED'>
									<html:radio name="dockets" property="attorneyConnection" disabled="true" value="AAT" indexed="true" styleId='<%="attyConn2-" + currDocketEventId%>'>AAT</html:radio><span title='PUBLIC DEFENDER'>
									<html:radio name="dockets" property="attorneyConnection" disabled="true" value="PDO" indexed="true" styleId='<%="attyConn2-" + currDocketEventId%>'>PDO</html:radio>
								</td>
			    			   	<td class='formDe'>
			    			   	<html:hidden styleId='<%="prevResetTo-"+currDocketEventId%>'  name='dockets' property='prevResetTo' indexed="true"/>
								</td>	
			    			   	<td class='formDe' colspan="3"></td>
							</tr>
							<tr>
								<!-- added for task 151689 -->
								<td class='formDe'></td>
								<logic:empty name="dockets" property="juvenileCoactors">
								<td class='formDe'></td>
								<td class='formDe'></td>
								</logic:empty>
								<logic:notEmpty name="dockets" property="juvenileCoactors">
			    				<td class='formDe'><span class='formDeLabel'>Co-Actor(s):</span></td>
								<%-- <td class='formDe' ><span title='<bean:write name="dockets" property="hearingTypeDesc"/>'><html:text name="dockets" property="hearingType" styleId='<%="hearingType-" + currDocketEventId%>' size="2" disabled="true" style="border: 0" indexed="true"/></td> --%>
								<td class='formDe' >
								<logic:iterate id="coActors" name="dockets" property="juvenileCoactors"  indexId="index">
									<bean:define id="coActorsList" name="dockets" property="juvenileCoactors" type="java.util.List" />
									<span title='<bean:write name="coActors" property="attorneyName"/>'><bean:write name="coActors" property="juvenileNum"/>
									<% if( index.intValue() != coActorsList.size()-1){ %>
	                                  ,
	                                <% } %>
								</logic:iterate>
								</td>														    				
			    				</logic:notEmpty>
				    			<td class='formDe' align="top" nowrap>
			    						<span class='formDeLabel'  valign="top">2nd Atty</span>
			    						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="attorney2Name" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
										 <bean:write name="dockets" property="attorney2Name"/>
							   	</td> 
				    				
								<td class='formDe' nowrap>
										<span class='formDeLabel'>Bar#</span>
										<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="attorney2BarNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="11" />
										 <bean:write name="dockets" property="attorney2BarNum"/>
								</td>	
								<td class='formDe' colspan="3" nowrap>
									<span class='formDeLabel'><bean:message key="prompt.connectionCode" /></span>&nbsp;&nbsp;
									<span title='<bean:write name="dockets" property="attorney2ConnectionDesc"/>'><bean:write name="dockets" property="attorney2Connection"/>									
								</td>						
							</tr>
							<tr>
								<td class='formDe'></td>
			    				<td class='formDe'></td>							
								<td class='formDe'></td>															    				
			    				
				    			<td class='formDe' align="top" nowrap>
			    						<span class='formDeLabel'  valign="top">GAL</span>
			    						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="galName" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" />
										<bean:write name="dockets" property="galName"/>
							   	</td> 
				    				
								<td class='formDe' nowrap>
										<span class='formDeLabel'>GAL Bar#</span>
										<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="galBarNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="13" />
										<bean:write name="dockets" property="galBarNum"/>
								</td>	
								<td class='formDe' colspan="3" nowrap>		    					
			    					<span class='formDeLabel'>Interpreter </span>
				    					<elogic:if name="dockets" property="interpreter" op="equal"	value="1">
											<elogic:then>
												<font size="-1"> YES </font>
											</elogic:then>
											<elogic:else>
												<font size="-1"> NO </font>
											</elogic:else>
										</elogic:if>																
								</td>						
							</tr>
			    			<% if (count % 4!=0) {%>
			    				<tr colspan="11" width="1%"><td colspan="12" width="1%" class="formDe"><div style="border-color:#ACE1AF;border-style: solid; border-width: 5px;"></div> </td></tr>
			    			  <% }%>
			    			</pg:pages>
	  					</pg:item>
	  		  		</logic:iterate> 
		  	 	</table>
			</td>
		</tr>	
	</table>
	<br />
	<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
			<td>
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch" />
					<tiles:put name="resultsPerPage" value="4" />
					</tiles:insert>
				</pg:index>
			</td>
		</tr>
	</table>
<%-- End Pagination navigation Row--%>
</pg:pager>
<br />
<table width="100%" border="0">
	<tr>
		<td align="center">		
			<html:submit onclick="spinner()" property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu"/></html:submit>
    	</td>
	</tr>
	<tr>
	 <td><input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileSearchDetentionHearings.do?submitAction=Link')" value="<bean:message key='button.back'/>"/>&nbsp;</td>
	 </tr>
</table>
</logic:notEmpty>
</jims:isAllowed>
<html:hidden styleId="facility" name="detentionHearingForm" property="facility"/>
<html:hidden styleId="cursorPosition" name="detentionHearingForm" property="cursorPosition"/>
<html:hidden styleId="tempBarNumber" name="detentionHearingForm" property="barNum"/>
<html:hidden styleId="tempAttorneyName" name="detentionHearingForm" property="attorneyName"/>
<html:hidden styleId="tempDocketEventId" name="detentionHearingForm" property="dcktEvtId"/>
<html:hidden styleId="selectedVal" name="detentionHearingForm" property="selectedValue"/>
<html:hidden styleId="action" name="detentionHearingForm" property="action"/>
<html:hidden styleId="errorMessage" name="detentionHearingForm" property="errMessage"/>
<html:hidden styleId="pgrOffset" name="detentionHearingForm" property="pagerOffset"/>
<html:hidden styleId="tempResult" name="detentionHearingForm" property="courtResult"/>
<html:hidden styleId="boolResultOnly" name="detentionHearingForm" property="onlyResultChanged"/>
<html:hidden styleId="selectedDockets" name="detentionHearingForm" property="selectedDocketsToUpdate"/>
<html:hidden styleId="searchResultSize" name="detentionHearingForm" property="searchResultSize"/>
<input type="hidden" id="holidaysList" name="detentionHearingForm" value='<bean:write name="detentionHearingForm" property="holidaysList"/>'/>
<input type="hidden" id="courtDecisions" name="detentionHearingForm" value='<bean:write name="detentionHearingForm" property="detentionCourtDecisions"/>'/>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>