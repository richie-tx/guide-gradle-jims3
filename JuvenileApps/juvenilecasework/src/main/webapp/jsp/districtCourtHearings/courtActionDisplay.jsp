<!DOCTYPE HTML>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>
<%@ page import="ui.common.UIUtil" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>







<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />
<meta charset="UTF-8" />
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
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtActionDisplay.js"></script>

<html:base />
<title><bean:message key="title.heading"/>/courtActionDisplay.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/submitJuvenileDistrictCourtHearings" target="content">
<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Court Action Update</h2></td>
	</tr>
</table>

<!-- BEGIN ERROR TABLE -->
<div id="results">
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>

<!-- END ERROR TABLE -->
 <!-- BEGIN Error Message Table -->
<logic:messagesPresent message="true"> 
	<table width="100%">
		<tr>		  
			<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
		</tr>   	  
	</table>
</logic:messagesPresent> 
</div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Actions fields are required: Court and Date.</li>
        <li>Input fields are updateable.</li>
        <li>Select a setting and click on Delete Setting to delete a setting.</li>
        <li>Click Court Main Menu to return to the main page.</li>
      </ul>
    </td>
  </tr>
  
  <logic:notEmpty name="courtHearingForm" property="allDktSearchResults">
	<logic:notEqual name="courtHearingForm" property="action" value="error">
	   <tr>
		    <td align="center"><span id="results"><bean:write name="courtHearingForm" property="searchResultSize"/> Setting(s) Found </span></td>
		  </tr>
	</logic:notEqual>
 </logic:notEmpty>

</table>
<%-- END INSTRUCTION TABLE --%>
<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">      
   <tr class='crtDetailHead' width="100%" >
		<td align='left' colspan="5" class='paddedFourPix'>&nbsp;</td>
	</tr>    
	<tr>
		<td class='formDeLabel' colspan="1" nowrap="nowrap" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.court"/></td>
		<td class='formDe' colspan="1"><html:text name="courtHearingForm" property="courtId" styleId="courtId"  maxlength="3" size="3"/></td>
		<td class='formDeLabel'  colspan="1" nowrap="nowrap" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date"/></td>
		<td class='formDe'  width="1%"><html:text name="courtHearingForm" property="courtDate" styleId="courtDate"  maxlength="10" size="10"/></td>
		<td class='formDe'  width="50%" align="left" colspan="1"><html:button property="submitAction" styleId="goBtn"><bean:message key="button.go"/></html:button></td>
	</tr>
</table>
<br/>
<div id="results">
<logic:notEmpty name="courtHearingForm" property="allDktSearchResults">
<jims:isAllowed requiredFeatures="<%=Features.JCW_CRTACTN_UPDATE%>"> 
	<%-- Begin Pagination Header Tag --%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="3" maxIndexPages="3" export="offset,currentPageNumber=pageNumber" scope="request">
   <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

	<%-- End Pagination header stuff --%>
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1">
	 				<tr class="crtDetailHead">   
	 					<td align='left' class='paddedFourPix'>&nbsp;</td>
	    				<td>   
							<bean:message key="prompt.petition" />
							<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" /> 
	          			</td>
	          			<td>   
							<bean:message key="prompt.juvenileName"/>
							 <jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2"/>
	          			</td>
	          			<%-- <td> 
							<bean:message key="prompt.type" />
							<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
	          			</td> --%>
	          			<td> 
							<bean:message key="prompt.result"/> 
							<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="courtResult" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
	          			</td>
						 <td>
	  						<bean:message key="prompt.disp"/>.
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="disposition" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.transfer" />
	  					</td>
		          		<td>
	  						<bean:message key="prompt.transferTo"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="transferTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.date"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="actionDate" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.time"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="courtTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" /> 
		          		</td>
		          		<td>
	  						<bean:message key="prompt.hearing"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="resetHearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
		          		</td>
					</tr>
					
					<%int count=0;%>
	  				<logic:iterate id="allDktSearchResults" name="courtHearingForm" property="allDktSearchResults" indexId='indexer' type="messaging.calendar.reply.DocketEventResponseEvent">
	  					<bean:define id="docketId" name="allDktSearchResults" property="docketEventId" type="java.lang.String"></bean:define> 	 
	  					<bean:define id="docketEvtIdKey" name="allDktSearchResults" property="docketEventIdKey" type="java.lang.String"></bean:define>				
	          			<html:hidden styleId="docketEventId"  name="allDktSearchResults" property="docketEventId" indexed="true"/>
	          			<html:hidden styleId="docketEventIdKey"  name="allDktSearchResults" property="docketEventIdKey" indexed="true"/>
	          			<html:hidden styleId="atyConfirmation" name="allDktSearchResults" property="atyConfirmation" indexed="true"/>
	          			<%-- Begin Pagination item wrap --%>
	          			<%count++; %>
	          			<pg:item>	          			
		    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="10" id='<bean:write name="allDktSearchResults" property="recType"/>'>
		    					<html:hidden styleId='<%="docketType-"+docketEvtIdKey%>'  name="allDktSearchResults" property="docketType" indexed="true"/>	
		    					<html:hidden styleId='<%="deleteFlag-"+docketEvtIdKey%>'  name="allDktSearchResults" property="deleteFlag" indexed="true"/>	    
		    					<html:hidden styleId='<%="recType-"+docketEvtIdKey%>'  name="allDktSearchResults" property="recType" indexed="true"/>
		    						   					
		    					<td class='formDe'  width="1%">
		    						<html:checkbox name="allDktSearchResults" property="deleteSetting" styleId='<%="deleteSetting-"+docketEvtIdKey%>' indexed="true" disabled="true"></html:checkbox>
		    						<%-- <html:radio name="allDktSearchResults" property="deleteSetting" value="<%=docketId%>" styleId='<%="deleteSetting-"+docketId%>'></html:radio> --%>
		    					</td> <!-- delete setting -->
		    					<td class='formDe' colspan="1">
		    						<bean:write name="allDktSearchResults" property="petitionNumber"/>&nbsp;&nbsp;
		    						<html:hidden name="allDktSearchResults" property="petitionNumber" indexed="true" styleId='<%="petitionNum-"+docketEvtIdKey%>'/>
		    						<logic:notEmpty name="allDktSearchResults" property="petitionStatusCd">
		    							<span title='<bean:write name="allDktSearchResults" property="petitionStatus"/>'><bean:write name="allDktSearchResults" property="petitionStatusCd"/></span>
					 					<bean:write name="allDktSearchResults" property="petitionAmendment"/>
					 				</logic:notEmpty>
					 			</td>
		    					<td class='formDe'>
		    						<bean:write name="allDktSearchResults" property="juvenileName"/>&nbsp;&nbsp;
		    						<logic:notEmpty name="allDktSearchResults" property="petitionType">{<span title='<bean:write name="allDktSearchResults" property="petitionTypeDesc"/>'><bean:write name="allDktSearchResults" property="petitionType"/></span>}</logic:notEmpty>
		    					</td>
								
								<td class='formDe' width="10%">
									<logic:equal name="allDktSearchResults" property="docketType" value="DETENTION">
										<html:select name="allDktSearchResults" property="courtResult" styleId='<%="courtResult-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="detentionHearingResults" value="code" label="code"/> 				
										</html:select>
									</logic:equal>
									<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
										<html:select name="allDktSearchResults" property="courtResult" styleId='<%="courtResult-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtDecisionsResponses" value="codeAlpha" label="codeAlpha"/> 				
										</html:select>
									</logic:equal>
									<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
										<html:select name="allDktSearchResults" property="courtResult" styleId='<%="courtResult-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtResultResponses" value="codeAlpha" label="descriptionWithCode"/> 
										</html:select>
									</logic:equal>
									<%-- <logic:notEqual name="allDktSearchResults" property="docketType" value="DETENTION">
										<html:select name="allDktSearchResults" property="courtResult" styleId='<%="courtResult-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtResultResponses" value="codeAlpha" label="descriptionWithCode"/> 
										</html:select>
									</logic:notEqual> --%>
									<html:hidden styleId='<%="oldCourtResult-"+docketId%>'  name="allDktSearchResults" property="oldcourtResult" indexed="true"/>
		 						</td> 
		    					<td class='formDe' width="8%">
		    						<logic:equal name="allDktSearchResults" property="docketType" value="DETENTION">
										<html:select name="allDktSearchResults" property="disposition" styleId='<%="disposition-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="detentionHearingResults" value="code" label="code"/> 				
										</html:select>
									</logic:equal>
									<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
										<html:select name="allDktSearchResults" property="disposition" styleId='<%="disposition-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtDecisionsResponses" value="codeAlpha" label="codeAlpha"/> 					
										</html:select>
									</logic:equal>
									<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
										<html:select name="allDktSearchResults" property="disposition" styleId='<%="disposition-"+docketEvtIdKey%>'  indexed="true">  
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtDispositionResponses" value="codeAlpha" label="descriptionWithCode"/> 
										</html:select>
									</logic:equal>
									<%-- <logic:notEqual name="allDktSearchResults" property="docketType" value="DETENTION">
			    						<html:select name="allDktSearchResults" property="disposition" styleId='<%="disposition-"+docketEvtIdKey%>'  indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtDispositionResponses" value="codeAlpha" label="descriptionWithCode"/>  				
										</html:select>
									</logic:notEqual> --%>
									<html:hidden styleId='<%="oldDisposition-"+docketId%>'  name="allDktSearchResults" property="olddisposition" indexed="true"/>
		 						</td>
		 						<td  class='formDe'>
			 							<input type="checkbox" id='<%="TRN-"+docketEvtIdKey%>'>		 											
			 						<html:hidden styleId='<%="TRNValue-"+docketEvtIdKey%>'  name="allDktSearchResults" property="transferFlag" indexed="true"/>
			 					</td>
								
								<td class='formDe' width="6%">
									<html:text name="allDktSearchResults" property="transferTo" styleId='<%="transferTo-"+docketEvtIdKey%>' disabled="true" maxlength="3" size="3" indexed="true"/>
									<html:hidden styleId='<%="oldtransferTo-"+docketId%>'  name="allDktSearchResults" property="oldtransferTo" indexed="true"/>
		 						</td>
		    					<td class='formDe'>
		    						<logic:equal name="allDktSearchResults" property="docketType" value="DETENTION">
		    							<logic:equal name="allDktSearchResults" property="disableResetDate" value="true">
		    								<html:text name="allDktSearchResults" property="actionDate" styleId='<%="actionDate-"+docketEvtIdKey%>' disabled="true" maxlength="10" size="10" indexed="true"/>
		    								<html:hidden name="allDktSearchResults" property="originalActionDate" indexed="true" styleId='<%="orginalActionDate-"+docketEvtIdKey%>'/>
		    							</logic:equal>
		    							<logic:notEqual name="allDktSearchResults" property="disableResetDate" value="true">
		    								<html:text name="allDktSearchResults" property="actionDate" styleId='<%="actionDate-"+docketEvtIdKey%>' maxlength="10" size="10" indexed="true"/>
		    							</logic:notEqual>
 	    						   </logic:equal>
 	    						   <logic:notEqual name="allDktSearchResults" property="docketType" value="DETENTION">
		    							<html:text name="allDktSearchResults" property="actionDate" styleId='<%="actionDate-"+docketEvtIdKey%>' maxlength="10" size="10" indexed="true"/>
 	    						   </logic:notEqual>
		    						<html:hidden  styleId='<%="courtDate-"+docketId%>'name="allDktSearchResults" property="courtDate" indexed="true"/>
		    						<html:hidden styleId='<%="oldActionDate-"+docketId%>'  name="allDktSearchResults" property="oldactionDate" indexed="true"/>
		     					</td>
		    					<td class='formDe' width="6%">
		    						<logic:notEqual name="allDktSearchResults" property="docketType" value="DETENTION">
		    							<html:text name="allDktSearchResults" property="actionTime" styleId='<%="actionTime-"+docketEvtIdKey%>' maxlength="4" size="2" indexed="true"/>
		    							<html:hidden  styleId='<%="courtTime-"+docketId%>'name="allDktSearchResults" property="courtTime" indexed="true"/>
		    							<html:hidden name="allDktSearchResults" property="originalActionTime" indexed="true" styleId='<%="orginalActionTime-"+docketId%>'/>
		    						</logic:notEqual>
		    						<logic:equal name="allDktSearchResults" property="docketType" value="DETENTION">
		    							<logic:equal name="allDktSearchResults" property="disableResetDate" value="true">
		    								<html:text name="allDktSearchResults" property="actionTime" styleId='<%="actionTime-"+docketEvtIdKey%>' maxlength="4" size="2" indexed="true"/>
		    							</logic:equal>
		    							<logic:notEqual name="allDktSearchResults" property="disableResetDate" value="true">
		    								<html:text name="allDktSearchResults" property="actionTime" styleId='<%="actionTime-"+docketEvtIdKey%>' maxlength="4" size="2" indexed="true"/>
		    							</logic:notEqual>
		    							<html:hidden  styleId='<%="courtTime-"+docketId%>'name="allDktSearchResults" property="courtTime" indexed="true"/>
		    						</logic:equal>
		    						<html:hidden styleId='<%="oldActionTime-"+docketId%>'  name="allDktSearchResults" property="oldactionTime" indexed="true"/>
		    						<html:hidden styleId='<%="oldCourtTime-"+docketId%>'  name="allDktSearchResults" property="oldcourtTime" indexed="true"/>
		     					</td>
		    					<td class='formDe'>
		    						 <logic:equal name="allDktSearchResults" property="docketType" value="COURT">
			    						<html:select name="allDktSearchResults" property="resetHearingType" styleId='<%="hearingType-"+docketEvtIdKey%>' indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="courtHearingTypes" value="code" label="code"/> 				
										</html:select>
		    						</logic:equal>
		    						<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
		    							<html:select name="allDktSearchResults" property="resetHearingType" styleId='<%="hearingType-"+docketEvtIdKey%>' indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="ancillaryHearingTypes" value="code" label="code"/> 				
										</html:select>
		    						</logic:equal>
		    						<logic:equal name="allDktSearchResults" property="docketType" value="DETENTION">
		    							<span title='<bean:write name="allDktSearchResults" property="resetHearingTypeDesc"/>'><bean:write name="allDktSearchResults" property="resetHearingType"/></span>
		    							<%-- <html:select name="allDktSearchResults" property="resetHearingType" styleId='<%="hearingType-"+docketId%>'  disabled="true" indexed="true">
											<html:option key="select.generic" value=""/>
											<html:optionsCollection property="hearingTypes" value="code" label="code"/> 				
										</html:select> --%>
		    						</logic:equal> 
		    						<html:hidden styleId='<%="oldResetHearingType-"+docketId%>'  name="allDktSearchResults" property="oldresetHearingType" indexed="true"/>
		 						</td> 
		    				</tr>
		    				<tr id='<bean:write name="allDktSearchResults" property="recType"/>'>		    					
		    					<td class='formDe'></td>
		    					<td class='formDe'>		    					
		    					<span class='formDeLabel'  valign="top">Type</span>
								<span title='<bean:write name="allDktSearchResults" property="hearingTypeDesc"/>'><bean:write name="allDktSearchResults" property="hearingType"/></span>
		    					</td>
		    					<td class='formDe'>
			    					<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
			    						<jims:isAllowed requiredFeatures="<%=Features.JCW_DISTRICT_ATTORNEY_CONFIRMATION_BYPASS%>">
				    						<html:button property="attorneyBypass" value="Attorney Bypass" disabled="true" styleId='<%="attnyBypass."+docketEvtIdKey%>' indexed="true"></html:button>
				    						<html:hidden styleId='<%="atyBypass-"+docketEvtIdKey%>' name="allDktSearchResults" property="atyBypass" indexed="true"/>
				    					</jims:isAllowed>
				    				</logic:notEqual>
				    				<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
			    						<html:button property="attorneyBypass" value="Attorney Bypass" style="display: none;" disabled="true" styleId='<%="attnyBypass."+docketEvtIdKey%>' indexed="true"></html:button>
				    				</logic:equal>
				    				<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
		    						<html:button property="attorneyConfirm" value="Attorney Confirmation" disabled="true" styleId='<%="attnyConfm."+docketEvtIdKey%>' indexed="true"></html:button>
				    			 </logic:notEqual>	
				    			 <logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
			    						<html:button property="attorneyConfirm" value="Attorney Confirmation" style="display: none;" disabled="true" styleId='<%="attnyConfm."+docketEvtIdKey%>' indexed="true"></html:button>
				    			 </logic:equal>
			    				</td>		    					
							 	<td class='formDe' nowrap>
			    					<span class='formDeLabel'  valign="top"><bean:message key="prompt.attorney"/></span>
			    					<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
			    					<elogic:if name="allDktSearchResults" property="atyConfirmation" op="notequal"	value="">
										<elogic:then>
											<html:text name="allDktSearchResults"  property="attorneyName" styleId='<%="attorneyName-"+docketEvtIdKey%>' size="35" maxlength="50" indexed="true" disabled="true"/>
										</elogic:then>
										<elogic:else>
											<logic:equal name="allDktSearchResults" property="seqNum" value="10">
												<html:text name="allDktSearchResults" style="border: 0;background-color: yellow" property="attorneyName" styleId='<%="attorneyName-"+docketEvtIdKey%>' size="35" maxlength="50" indexed="true" disabled="true"/>
											</logic:equal>
											<logic:notEqual name="allDktSearchResults" property="seqNum" value="10">
												<html:text name="allDktSearchResults" style="border: 0;background-color: cyan" property="attorneyName" styleId='<%="attorneyName-"+docketEvtIdKey%>' size="35" maxlength="50" indexed="true" disabled="true"/>
											</logic:notEqual>
										</elogic:else>
									</elogic:if>
			    					</logic:notEqual>
			    					<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
			    						<html:text name="allDktSearchResults" property="attorneyName" styleId='<%="attorneyName-"+docketEvtIdKey%>' size="35" maxlength="50" indexed="true" disabled="true"/>
			    					</logic:equal>
			    					<html:hidden styleId='<%="oldAttorney-"+docketId%>'  name="allDktSearchResults" property="oldattorneyName" indexed="true"/>
			    					<html:button onclick="spinner()" property="searchAttorney" value="Search" styleId='<%="searchAttorney."+docketEvtIdKey%>' indexed="true"></html:button>
									<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId"/>
							   </td>
								<td class='formDe' nowrap >
									<span class='formDeLabel'><bean:message key="prompt.bar"/></span>
									<html:text name="allDktSearchResults" property="barNum" styleId='<%="barNum-"+docketEvtIdKey%>'  maxlength="8" size="8"  indexed="true"/>
									<html:hidden styleId='<%="oldBarNum-"+docketId%>'  name="allDktSearchResults" property="oldbarNum"/>
									<html:button property="validateBarNum" value="Validate" styleId='<%="validateBarNumber."+docketEvtIdKey%>' indexed="true"></html:button>
									&nbsp; 
									<span class='formDeLabelNoTextAlign' align="right"><bean:message key="prompt.aty" /></span>
									<html:hidden styleId='<%="oldAttorneyConn-"+docketId%>'  name="allDktSearchResults" property="oldattorneyConnection" indexed="true"/>
									<html:radio name="allDktSearchResults" property="attorneyConnection" value="HAT" indexed="true" styleId='<%="HATattorneyConnection."+docketEvtIdKey%>'><span title='Hired Attorney'>HAT</span></html:radio>
									<html:radio name="allDktSearchResults" property="attorneyConnection" value="AAT" indexed="true" styleId='<%="AATattorneyConnection."+docketEvtIdKey%>'><span title='Appointed Attorney'>AAT</span></html:radio>
									<html:radio name="allDktSearchResults" property="attorneyConnection" value="PDO" indexed="true" styleId='<%="PDOattorneyConnection."+docketEvtIdKey%>'><span title='Public Defender'>PDO</span></html:radio>
									<html:hidden styleId='<%="atyConfirmation-"+docketEvtIdKey%>' name="allDktSearchResults" property="atyConfirmation" indexed="true"/>
								</td>
								<td class='formDe' nowrap colspan="8">
		    					<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
		    						<html:checkbox styleId='<%="appAttorney."+docketEvtIdKey%>' name="allDktSearchResults" property="appAttorneychk" indexed="true"></html:checkbox>
		    						<span title='Appellate'>APP</span>
		    						<html:hidden styleId='<%="appAttorney-"+docketEvtIdKey%>' name="allDktSearchResults" property="appAttorney" indexed="true"/>
		    					</logic:equal>
		    					</td>
							</tr>
							<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
							<tr id='<bean:write name="allDktSearchResults" property="recType"/>'>
									<logic:empty name="allDktSearchResults" property="juvenileCoactors">
										<td class='formDe'></td>
										<td class='formDe'></td>
										<td class='formDe'></td>
									</logic:empty>
									<logic:notEmpty name="allDktSearchResults" property="juvenileCoactors">
										<td class='formDe'></td>
			   							<td class='formDe' align="center"><span class='formDeLabel'>Co-Actor(s):</span></td>
										<td class='formDe' >
										<logic:iterate id="coActors" name="allDktSearchResults" property="juvenileCoactors" indexId="index">
										<bean:define id="coActorsList" name="allDktSearchResults" property="juvenileCoactors" type="java.util.List" />
											<span title='<bean:write name="coActors" property="attorneyName"/>'><bean:write name="coActors" property="juvenileNum"/>
											<% if( index.intValue() != coActorsList.size()-1){ %>
			                                  ,
			                                <% } %>
										</logic:iterate>
										</td>
									</logic:notEmpty>									  
				    				<td class='formDe' nowrap>
				    					<span class='formDeLabel'  valign="top">2nd Attorney</span>
				    					<html:text name="allDktSearchResults" property="attorney2Name" styleId='<%="2ndAttorney-"+docketEvtIdKey%>' size="35" maxlength="50" indexed="true" disabled="true"/>
				    					<html:button onclick="spinner()" property="search2ndAttorney" value="Search" styleId='<%="2ndAttorney."+docketEvtIdKey%>' indexed="true"></html:button>
										<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId"/>
								   	</td>
								   	<td class='formDe' nowrap colspan="9">
										<span class='formDeLabel'>Bar#</span>
										<html:text name="allDktSearchResults" property="attorney2BarNum" styleId='<%="2ndAttorneyBarnum-"+docketEvtIdKey%>'  maxlength="8" size="8"  indexed="true"/>
										<html:button property="validate2ndAttorneyBarNum" value="Validate" styleId='<%="validate2ndAttorneyBarNum."+docketEvtIdKey%>' indexed="true"></html:button>
										&nbsp; 
										<span class='formDeLabelNoTextAlign' align="right"><bean:message key="prompt.aty" /></span>
										<html:radio name="allDktSearchResults" property="attorney2Connection" value="HAT" indexed="true" styleId='<%="2ndAttorneyHATattorneyConnection."+docketEvtIdKey%>'><span title='Hired Attorney'>HAT</span></html:radio>
										<html:radio name="allDktSearchResults" property="attorney2Connection" value="AAT" indexed="true" styleId='<%="2ndAttorneyAATattorneyConnection."+docketEvtIdKey%>'><span title='Appointed Attorney'>AAT</span></html:radio>
										<html:radio name="allDktSearchResults" property="attorney2Connection" value="PDO" indexed="true" styleId='<%="2ndAttorneyPDOattorneyConnection."+docketEvtIdKey%>'><span title='Public Defender'>PDO</span></html:radio>
									</td>								
							</tr>
							</logic:notEqual>
							<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
							<tr id='<bean:write name="allDktSearchResults" property="recType"/>'>
								<td class='formDe'></td>
								<td class='formDe'>
								<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
									<bean:write name="allDktSearchResults" property="juvenileNumber"/>
								</logic:equal>
								<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
									<a onclick="spinner()" href="/<msp:webapp/>processJuvenileDistrictCourtHearings.do?juvnum=<bean:write name="allDktSearchResults" property="juvNum" />&fromPage=court&submitAction=courtActivityByYouth"><bean:write name="allDktSearchResults" property="juvenileNumber" /></a>
								</logic:notEqual>
								<html:hidden styleId='<%="juvenileNumber-"+docketId%>'  name="allDktSearchResults" property="juvenileNumber" indexed="true"/>
								</td>				    				
				    			<td class='formDe'></td>  
				    			<td class='formDe' nowrap>
				    				<span class='formDeLabel'  valign="top">GAL</span>
				    				<html:text name="allDktSearchResults" property="galName" styleId='<%="galName-"+docketEvtIdKey%>' size="35" maxlength="50" indexed="true" disabled="true"/>
				    				<html:button onclick="spinner()" property="searchGAL" value="Search" styleId='<%="galName."+docketEvtIdKey%>' indexed="true"></html:button>
									<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId"/>
								</td>
								<td class='formDe' nowrap colspan="9">
									<span class='formDeLabel'>GAL Bar#</span>
									<html:text name="allDktSearchResults" property="galBarNum" styleId='<%="galBarNum-"+docketEvtIdKey%>'  maxlength="8" size="8"  indexed="true"/>
									<html:button property="validateGAL" value="Validate" styleId='<%="validateGalBarNum."+docketEvtIdKey%>' indexed="true"></html:button>
								</td>								
							</tr>
							</logic:notEqual>
			    			<tr id='<bean:write name="allDktSearchResults" property="recType"/>'>
			    				<td class='formDe'></td>
			    				<td class='formDe'>		    					
		    					<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
		    						<logic:notEmpty name="allDktSearchResults" property="probationOfficerCd">
		    							<span title='<bean:write name="allDktSearchResults" property="probationOfficer"/>'>(<bean:write name="allDktSearchResults" property="probationOfficerCd"/>)</span>
		    						</logic:notEmpty>
		    							<logic:empty name="allDktSearchResults" property="probationOfficerCd">
		    								( )
		    							</logic:empty>
		    					</logic:equal>
		    					<logic:equal name="allDktSearchResults" property="docketType" value="DETENTION">
		    						<logic:notEmpty name="allDktSearchResults" property="probationOfficerCd">
		    							<span title='<bean:write name="allDktSearchResults" property="probationOfficer"/>'>(<bean:write name="allDktSearchResults" property="probationOfficerCd"/>)</span>
		    						</logic:notEmpty>
		    							<logic:empty name="allDktSearchResults" property="probationOfficerCd">
		    								( )
		    							</logic:empty>
		    					</logic:equal>
		    					</td>
			    				<td class='formDe' nowrap>
				    				<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
				    					<span class='formDeLabel'><bean:message key="prompt.prevNotes" /></span>
										<html:text name="allDktSearchResults" property="prevNotes"  styleId='<%="prevNotes-"+docketEvtIdKey%>' indexed="true" maxlength="20" size="20"/>
				    				</logic:equal>
				    				<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
					    				<logic:notEmpty name="allDktSearchResults" property="prevNotes">
											<html:text name="allDktSearchResults" property="prevNotes" disabled="true"  styleId='<%="prevNotes-"+docketEvtIdKey%>' indexed="true" maxlength="20" size="20"/>
										</logic:notEmpty>
										<logic:empty name="allDktSearchResults" property="prevNotes"> <!-- bug no: 68565 -->
											<html:text name="allDktSearchResults" property="setNote" disabled="true"  styleId='<%="setNote-"+docketEvtIdKey%>' indexed="true" maxlength="20" size="20"/>
										</logic:empty>
									</logic:equal>
									<html:hidden styleId='<%="oldPrevNotes-"+docketId%>'  name="allDktSearchResults" property="oldprevNotes" indexed="true"/>
							 	</td> 
							 	<td align="right" class='formDeLabel'> <bean:message key="prompt.comments"/></td>
				    			<td class='formDe'>
									<html:text name="allDktSearchResults" property="comments" size="50" maxlength="50" styleId='<%="comments-"+docketEvtIdKey%>' indexed="true"/>
									<html:hidden styleId='<%="oldComments-"+docketId%>'  name="allDktSearchResults" property="oldcomments" indexed="true"/>
		 						</td>
								<!-- task 168662 -->
								<td class='formDe' colspan="6" nowrap>	
									<span>Interpreter?</span>	    					
		    						<html:checkbox styleId='<%="interpreter."+docketEvtIdKey%>' name="allDktSearchResults" property="interpreterchk" indexed="true"></html:checkbox>
		    						<html:hidden styleId='<%="interpreter-"+docketEvtIdKey%>' name="allDktSearchResults" property="interpreter" indexed="true"/>
		    					</td>
			    			</tr>
			    			<% if (count % 3!=0) {%>
			    				<tr colspan="11" width="1%"><td colspan="12" width="1%" class="formDe"><div style="border-color:#ACE1AF;border-style: solid; border-width: 5px;"></div></td></tr>
			    			  <% }%>
	  					</pg:item>
	  		  		</logic:iterate> 
		  	 	</table>
			</td>
		</tr>	
	</table>

	<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
			<td>
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch" />
					<tiles:put name="resultsPerPage" value="3" />
					</tiles:insert>
				</pg:index>
			</td>
		</tr>
	</table>
<%-- End Pagination navigation Row--%>
</pg:pager>
<table width="100%" border="0">
	<tr>
		<td align="center">
			<html:button property="submitAction" styleId="updateBtn"><bean:message key="button.submit"/></html:button>
			<jims:isAllowed requiredFeatures="<%=Features.JCW_CRTACTN_DELETE%>"> 
				<html:button property="submitAction" styleId="deleteBtn"><bean:message key="button.deleteSetting"/></html:button>
			</jims:isAllowed>
			<html:button onclick="spinner()" property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu"/></html:button>
    	</td>
	</tr>
</table>
</jims:isAllowed>
<!--if no feature show read only fields.  -->
<jims:isAllowed requiredFeatures="<%=Features.JCW_CRTACTN_UPDATE%>" value="false">
<%-- Begin Pagination Header Tag --%>
	<pg:pager index="center" maxPageItems="3" maxIndexPages="3" export="offset,currentPageNumber=pageNumber" scope="request">
	<input type="hidden" name="pager.offset" value="<%= offset %>" >
	<%-- End Pagination header stuff --%>
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1">
	 				<tr class="crtDetailHead">   
	 					<td align='left' class='paddedFourPix'>&nbsp;</td>
	    				<td>   
							<bean:message key="prompt.petition" />
							<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" /> 
	          			</td>
	          			<td>   
							<bean:message key="prompt.juvenileName"/>
							 <jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2"/>
	          			</td>
	          			<%-- <td> 
							<bean:message key="prompt.type" />
							<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
	          			</td> --%>
	          			<td> 
							<bean:message key="prompt.result"/> 
							<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="courtResult" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
	          			</td>
						 <td>
	  						<bean:message key="prompt.disp"/>.
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="disposition" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.transferTo"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="transferTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.date"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="courtDate" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.time"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="courtTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" /> 
		          		</td>
		          		<td>
	  						<bean:message key="prompt.hearing"/>
	  						<jims:sortResults beanName="courtHearingForm" results="allDktSearchResults" primaryPropSort="resetHearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
		          		</td>
					</tr>
					
					<%int count=0;%>
	  				<logic:iterate id="allDktSearchResults" name="courtHearingForm" property="allDktSearchResults" indexId='indexer' type="messaging.calendar.reply.DocketEventResponseEvent">
	  					<bean:define id="docketId" name="allDktSearchResults" property="docketEventId" type="java.lang.String"></bean:define> 	
	          			<%-- <bean:define id="docketEvtIdKey" name="allDktSearchResults" property="docketEventIdKey" type="java.lang.String"></bean:define>
	          			<html:hidden styleId="docketEventId"  name="allDktSearchResults" property="docketEventId" indexed="true"/>
	          			<html:hidden styleId="docketEventIdKey"  name="allDktSearchResults" property="docketEventIdKey" indexed="true"/>
	          			 --%><html:hidden styleId="atyConfirmation" name="allDktSearchResults" property="atyConfirmation" indexed="true"/>
	          			 <%-- Begin Pagination item wrap --%>
	          			<%count++; %>
	          			<pg:item>
		    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="10">
		    					<td class='formDe'  width="1%"></td> 
		    					<td class='formDe' colspan="1">
		    						<bean:write name="allDktSearchResults" property="petitionNumber"/>&nbsp;&nbsp;
		    						<html:hidden name="allDktSearchResults" property="petitionNumber" indexed="true" styleId='petitionId'/>
		    						<logic:notEmpty name="allDktSearchResults" property="petitionStatusCd">
		    							<span title='<bean:write name="allDktSearchResults" property="petitionStatus"/>'><bean:write name="allDktSearchResults" property="petitionStatusCd"/></span>
					 					&nbsp;<bean:write name="allDktSearchResults" property="petitionAmendment"/>
					 				</logic:notEmpty>
					 			</td>
		    					<td class='formDe'>
		    						<bean:write name="allDktSearchResults" property="juvenileName"/>&nbsp;&nbsp;
		    						<logic:notEmpty name="allDktSearchResults" property="petitionType">{<span title='<bean:write name="allDktSearchResults" property="petitionTypeDesc"/>'><bean:write name="allDktSearchResults" property="petitionType"/></span>}</logic:notEmpty>
		    					</td>
								<%-- <td class='formDe'>
									<span title='<bean:write name="allDktSearchResults" property="hearingTypeDesc"/>'><bean:write name="allDktSearchResults" property="hearingType"/></span>
								</td> --%>
								<td class='formDe' width="10%">
									<span title='<bean:write name="allDktSearchResults" property="courtResultDesc"/>'><bean:write name="allDktSearchResults" property="courtResult"/></span>
								</td> 
		    					<td class='formDe' width="10%">
		    						<span title='<bean:write name="allDktSearchResults" property="dispositionDesc"/>'><bean:write name="allDktSearchResults" property="disposition"/></span>
								</td>
								<td class='formDe'><bean:write name="allDktSearchResults" property="transferTo"/></td>
		    					<td class='formDe'><bean:write name="allDktSearchResults" property="actionDate"/></td>
		    					<td class='formDe'>
		    						<bean:write name="allDktSearchResults" property="actionTime"/>
		    					</td>
		    					<td class='formDe'>
		    						<span title='<bean:write name="allDktSearchResults" property="resetHearingTypeDesc"/>'><bean:write name="allDktSearchResults" property="resetHearingType"/></span>
								</td> 
		    				</tr>
		    				<tr>
		    					<td class='formDe'></td>
		    					<td class='formDe'>
									<span class='formDeLabel'  valign="top">Type</span>
									<span title='<bean:write name="allDktSearchResults" property="hearingTypeDesc"/>'><bean:write name="allDktSearchResults" property="hearingType"/></span>
								</td>
		    					<td class='formDe' align="top" nowrap>
		    					<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">
			    					<span class='formDeLabel'>Attorney Confirmation</span>
			    					<elogic:if name="allDktSearchResults" property="atyConfirmation" op="notequal"	value="">
										<elogic:then>
											<font size="-1"> YES </font>
										</elogic:then>
										<elogic:else>
											<font size="-1"> NO </font>
										</elogic:else>
									</elogic:if>
									</logic:notEqual>								
								</td>
		    					<td class='formDe' align="top" nowrap>
			    					<span class='formDeLabel'  valign="top"><bean:message key="prompt.attorney"/></span>
			    					<bean:write name="allDktSearchResults" property="attorneyName"/>
							   </td>
								<td class='formDe' nowrap>
									<span class='formDeLabel'><bean:message key="prompt.bar"/></span>
									<bean:write name="allDktSearchResults" property="barNum"/>
									&nbsp; 
									<span class='formDeLabelNoTextAlign' align="right"><bean:message key="prompt.aty" /></span>
								    <span title='<bean:write name="allDktSearchResults" property="attorneyConnectionDesc"/>'><bean:write name="allDktSearchResults" property="attorneyConnection"/></span>
								</td>
								<td class='formDe' align="top" nowrap>
		    					<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
			    					<span class='formDeLabel'>Appellate Attorney</span>
				    					<elogic:if name="allDktSearchResults" property="appAttorney" op="equal"	value="1">
											<elogic:then>
												<font size="-1"> YES </font>
											</elogic:then>
											<elogic:else>
												<font size="-1"> NO </font>
											</elogic:else>
										</elogic:if>
									</logic:equal>								
								</td>
							</tr>				
							<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">							
							<tr>
								<td class='formDe'></td>
			    				<logic:empty name="allDktSearchResults" property="juvenileCoactors">
								<td class='formDe'></td>
								<td class='formDe'></td>
								</logic:empty>	
								<logic:notEmpty name="allDktSearchResults" property="juvenileCoactors">
			    				<td class='formDe'><span class='formDeLabel'>Co-Actor(s):</span></td>
								<%-- <td class='formDe' ><span title='<bean:write name="dockets" property="hearingTypeDesc"/>'><html:text name="dockets" property="hearingType" styleId='<%="hearingType-" + currDocketEventId%>' size="2" disabled="true" style="border: 0" indexed="true"/></td> --%>
								<td class='formDe' >
								<logic:iterate id="coActors" name="allDktSearchResults" property="juvenileCoactors" indexId="index">
								<bean:define id="coActorsList" name="allDktSearchResults" property="juvenileCoactors" type="java.util.List" />
									<span title='<bean:write name="coActors" property="attorneyName"/>'><bean:write name="coActors" property="juvenileNum"/>
									<% if( index.intValue() != coActorsList.size()-1){ %>
	                                  ,
	                                <% } %>
								</logic:iterate>
								</td>														    				
			    				</logic:notEmpty>		    				
				    				<td class='formDe' align="top" nowrap>
			    						<span class='formDeLabel'  valign="top">2nd Attorney</span>
			    						<bean:write name="allDktSearchResults" property="attorney2Name"/>
							   		</td> 
				    				
								   <td class='formDe' nowrap>
										<span class='formDeLabel'>Bar#</span>
										<bean:write name="allDktSearchResults" property="attorney2BarNum"/>
										&nbsp; 
										<span class='formDeLabelNoTextAlign' align="right"><bean:message key="prompt.aty" /></span>
								    	<span title='<bean:write name="allDktSearchResults" property="attorney2ConnectionDesc"/>'><bean:write name="allDktSearchResults" property="attorney2Connection"/>							
									</td>								
							</tr>
							</logic:notEqual>	
							<logic:notEqual name="allDktSearchResults" property="docketType" value="ANCILLARY">						
							<tr>
								<td class='formDe'></td>
			    				<td class='formDe'><bean:write name="allDktSearchResults" property="juvenileNumber"/></td>							
								<td class='formDe'></td>
				    				<td class='formDe' align="top" nowrap>
			    						<span class='formDeLabel'  valign="top">GAL</span>
			    						<bean:write name="allDktSearchResults" property="galName"/>
							   		</td> 
				    				
								   <td class='formDe' nowrap>
										<span class='formDeLabel'>GAL Bar#</span>
										<bean:write name="allDktSearchResults" property="galBarNum"/>
									</td>
								
							</tr>
							</logic:notEqual>
			    			<tr>
			    				<td class='formDe'></td>
			    				<td class='formDe'>
			    					<logic:notEmpty name="allDktSearchResults" property="probationOfficerCd">
		    							<span title='<bean:write name="allDktSearchResults" property="probationOfficer"/>'>(<bean:write name="allDktSearchResults" property="probationOfficerCd"/>)</span>
		    						</logic:notEmpty>
		    						<logic:empty name="allDktSearchResults" property="probationOfficerCd">
		    								( )
		    						</logic:empty>
		    					</td>
			    				<td class='formDe' nowrap>
				    				<logic:equal name="allDktSearchResults" property="docketType" value="COURT">
				    					<span class='formDeLabel'><bean:message key="prompt.prevNotes"/></span>
				    					<bean:write name="allDktSearchResults" property="prevNotes"/>
				    				</logic:equal>
				    				<logic:equal name="allDktSearchResults" property="docketType" value="ANCILLARY">
					    				<logic:notEmpty name="allDktSearchResults" property="prevNotes">
					    					<bean:write name="allDktSearchResults" property="prevNotes"/>
										</logic:notEmpty>
										<logic:empty name="allDktSearchResults" property="prevNotes"> <!-- bug no: 68565 -->
											<bean:write name="allDktSearchResults" property="setNote"/>
										</logic:empty>
									</logic:equal>
							 	</td>
							 	<td align="right" class='formDeLabel'> <bean:message key="prompt.comments"/></td>
				    			<td class='formDe'>
				    				<bean:write name="allDktSearchResults" property="comments"/>
								</td> 
								<!-- task 168662 -->
								<td class='formDe' colspan="7" nowrap>		    					
			    					<span class='formDeLabel'>Interpreter </span>
				    					<elogic:if name="allDktSearchResults" property="interpreter" op="equal"	value="1">
											<elogic:then>
												<font size="-1"> YES </font>
											</elogic:then>
											<elogic:else>
												<font size="-1"> NO </font>
											</elogic:else>
										</elogic:if>																
								</td>
			    			</tr>
			    			<% if (count % 3!=0) {%>
			    				<tr colspan="11" width="1%"><td colspan="12" width="1%" class="formDe"><div style="border-color:#ACE1AF;border-style: solid; border-width: 5px;"></div></td></tr>
			    			  <% }%>
	  					</pg:item>
	  		  		</logic:iterate> 
		  	 	</table>
			</td>
		</tr>	
	</table>
	<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
			<td>
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch" />
					<tiles:put name="resultsPerPage" value="3" />
					</tiles:insert>
				</pg:index>
			</td>
		</tr>
	</table>
<%-- End Pagination navigation Row--%>
</pg:pager>
<table width="100%" border="0">
	<tr>
		<td align="center">
			<html:button onclick="spinner()" property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu"/></html:button>
    	</td>
	</tr>
</table>
</jims:isAllowed> 
</logic:notEmpty>
<!-- <div align='center'><script type="text/javascript">renderBackToTop()</script></div> -->
</div>
<!--  Hidden Fields -->
<html:hidden styleId='deleteSettingDate'  name="courtHearingForm" property="deleteSettingDate"/>
<html:hidden styleId='deleteFlag'  name="courtHearingForm" property="deleteFlag"/>
<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>
<html:hidden styleId="tempAttorneyName" name="courtHearingForm" property="attorneyName"/>
<html:hidden styleId="tempDocketEventIdKey" name="courtHearingForm" property="dcktEvtIdKey"/>
<html:hidden styleId="tempDocketEventId" name="courtHearingForm" property="dcktEvtId"/>
<html:hidden styleId="tempBarNumber" name="courtHearingForm" property="barNumber"/>
<html:hidden styleId="galBarNumber" name="courtHearingForm" property="galBarNumber"/>
<html:hidden styleId="pagerOffset" name="courtHearingForm" property="pagerOffset"/>
<html:hidden styleId="action" name="courtHearingForm" property="action"/>
<html:hidden styleId="searchResultSize" name="courtHearingForm" property="searchResultSize"/>
<html:hidden styleId="disableResetDate" name="courtHearingForm" property="disableResetDate"/>
<html:hidden styleId="tempDocketId" name="courtHearingForm" property="selectedValue"/>
<input type="hidden" id="holidaysList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="holidaysList"/>'/>
<input type="hidden" id="courtDecisionsList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="courtDecisionsList"/>'/>
<input type="hidden" id="districtcourtDecisionsList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="districtcourtDecisionsList"/>'/> 
</html:form>

</body>
</html:html>