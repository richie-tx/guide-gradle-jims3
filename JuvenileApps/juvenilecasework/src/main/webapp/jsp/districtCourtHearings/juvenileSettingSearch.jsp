<!DOCTYPE HTML>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>






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
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/juvenileSetting.js"></script>

<html:base />
<title><bean:message key="title.heading"/>/juvenileSettingSearch.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/submitJuvenileDistrictCourtJuvenileSetting" target="content">
<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Search Juvenile Setting</h2></td>
	</tr>
</table>
<br/>

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

<table width="98%" border="0">
  <tr>
	  <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
	  </tr>
	  <tr>   
	   	<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
	  </tr>
	  <tr>
	  	 <td class="required">+ One of these fields is required.</td>
	  </tr>
 </table>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Action field is required:Juvenile Number.</li>
        <li>Click Submit button to inquire Juvenile Setting.</li>
      </ul>
    </td>
  </tr>
  <logic:notEmpty name="courtHearingForm" property="dktSearchResults">
   <tr>
   	 <td align="center"><bean:write name="courtHearingForm" property="searchResultSize"/> Record(s) Found </td>
  </tr>
 </logic:notEmpty>
</table>

<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue" colspan="5">      
   <tr class='crtDetailHead'>
		<td align='left' colspan="7" class='paddedFourPix'>&nbsp;</td>
	</tr>    
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<html:text name="courtHearingForm" property="juvenileNumber" styleId="juvNum" maxlength="12" size="12"/>
		</td>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.juvenileName"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<bean:write name="courtHearingForm" property="juvenileName"/>
		</td>
	</tr>
	
</table>
<br/>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">    
	<tr>
		<td align="center" colspan="4">
			<span align="center"><html:button property="submitAction" styleId="submitBtn"><bean:message key="button.submit"/></html:button></span>
	   	</td>
	</tr>
	<tr>
	<td align="left" colspan="4">
		<logic:empty name="courtHearingForm" property="dktSearchResults"><jims2:if name="courtHearingForm" property="showmessage" value="true" op="equal"><jims2:then><h3>&nbsp;&nbsp;&nbsp;&nbsp;No Pending Court Case.</h3></jims2:then></jims2:if></logic:empty>
	</td>
	</tr>
</table>
<br>
<%-- <logic:empty name="courtHearingForm" property="dktSearchResults">No Pending Court Case</logic:empty> --%>

<logic:notEmpty name="courtHearingForm" property="dktSearchResults">
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1">
	 				<tr class="crtDetailHead">   
	 					<td>
	  						<bean:message key="prompt.barNumber"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="barNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="1"/>
		          		</td>
		          			<td>
	  						<bean:message key="prompt.attorneyName"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="attorneyName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2"/>
		          		</td>
	    				<td>   
							<bean:message key="prompt.date" />
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="courtDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="3"/> 
	          			</td>
	          			<td>   
							<bean:message key="prompt.time" />
							 <jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="courtTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="4"/>
	          			</td>
	          			<td>
	  						<bean:message key="prompt.court"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="court" primarySortType="STRING" defaultSortOrder="ASC" sortId="5"/>
		          		</td>
		          		<%-- <td>
	  						<bean:message key="prompt.transferTo"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="transferTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="6"/>
		          		</td> --%>
	          			<td> 
							<bean:message key="prompt.petition" />#
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="7"/>
	          			</td>
	          			<td> 
							<bean:message key="prompt.hrngType"/> 
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="8"/>
	          			</td>
						<td>
	  						<bean:message key="prompt.connection"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="attorneyConnection" primarySortType="STRING" defaultSortOrder="ASC" sortId="9"/>
		          		</td>
					</tr>
					
	  				<logic:iterate id="dockets" name="courtHearingForm" property="dktSearchResults" indexId='indexer'> 
	          			<pg:item>
			    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="10">
			    					<td class='formDe' colspan="1"><bean:write name="dockets" property="barNum"/></td>
			    					<td class='formDe' colspan="1"><bean:write name="dockets" property="attorneyName"/></td>
			    					<td class='formDe'><bean:write name="dockets" property="courtDate"/></td>
			    					<td class='formDe'><bean:write name="dockets" property="courtTime"/></td>
			    					<td class='formDe'><bean:write name="dockets" property="court"/></td>
			    					<%-- <td class='formDe'><bean:write name="dockets" property="transferTo"/></td> --%>
			    					<td class='formDe' colspan="1"><bean:write name="dockets" property="petitionNumber"/></td>
			    					<td class='formDe'><span title='<bean:write name="dockets" property="hearingTypeDesc"/>'><bean:write name="dockets" property="hearingType"/></span></td>
			    					<td class='formDe' colspan="1"><span title='<bean:write name="dockets" property="attorneyConnectionDesc"/>'><bean:write name="dockets" property="attorneyConnection"/></span></td>
			    				</tr>
	  					</pg:item>
	  		  		</logic:iterate> 	  		  		
		  	 	</table>
			</td>
		</tr>
		
	</table>
	<br>
	</br>	
	<table align="center">
		<tr>
			<td>
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
					<tiles:put name="pagerUniqueName" value="pagerSearch" />
					<tiles:put name="resultsPerPage" value="10" />
					</tiles:insert>
				</pg:index>
			</td>
		</tr>
	</table>
<%-- End Pagination navigation Row--%>
</pg:pager>
</logic:notEmpty>
<logic:empty name="courtHearingForm" property="jjslastAttorney"><jims2:if name="courtHearingForm" property="showmessage" value="true" op="equal"><jims2:then>Last attorney record not found.</jims2:then></jims2:if></logic:empty>
	<logic:notEmpty name="courtHearingForm" property="jjslastAttorney">
	<br>
	<br>
	<br>
	<tr>
		<td align="center"><h3>Last Attorney of Record</h3></td>
	</tr>
	<%-- Begin Pagination navigation Row--%>
	<table width="48%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue"> 
	
	<tr class="crtDetailHead">   
	 					<td>
	  						<bean:message key="prompt.barNumber"/>
	  					</td>
		          		<td>
	  						<bean:message key="prompt.attorneyName"/>
	  					</td>
	    				<td>   
							<bean:message key="prompt.connection" />
						</td>
						<td>   
							<bean:message key="prompt.lastUpdate" />
						</td>
		</tr>
		<tr>   
	 					<td class='formDe' colspan="1"><bean:write name="courtHearingForm" property="jjslastAttorney.atyBarNum"/></td>
		          		<td class='formDe' colspan="1"><bean:write name="courtHearingForm" property="jjslastAttorney.atyName"/></td>
		          		<td class='formDe' colspan="1"><bean:write name="courtHearingForm" property="jjslastAttorney.attConnect"/></td>
		          		<jims2:if name="courtHearingForm" property="jjslastAttorney.updateTimestamp" value="" op="equal">
		          			<jims2:then>
		          				<td class='formDe' colspan="1"><bean:write name="courtHearingForm" property="jjslastAttorney.createTimestamp" format="MM/dd/yyyy" /></td>
		          			</jims2:then>
		          			<jims2:else>
		          				<td class='formDe' colspan="1"><bean:write name="courtHearingForm" property="jjslastAttorney.updateTimestamp" format="MM/dd/yyyy" /></td>
		          			</jims2:else>
		          		</jims2:if>
		</tr>		
	</table>
	</logic:notEmpty>
<html:hidden styleId="action" name="courtHearingForm" property="action"/>
<html:hidden styleId="actionError" name="courtHearingForm" property="actionError"/>
<html:hidden styleId="validateMsg" name="courtHearingForm" property="validateMsg"/>
<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>