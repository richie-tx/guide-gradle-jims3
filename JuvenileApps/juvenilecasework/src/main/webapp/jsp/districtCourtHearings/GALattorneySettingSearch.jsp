<!DOCTYPE HTML>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
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
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/GALattorneySettingSearch.js"></script>

<html:base />
<script type="text/javascript">
$(document).ready(function(){
	
	$("#printBtn").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/submitJuvenileDistrictCourtGALAttorneySetting.do?submitAction=Print',
	        type: 'post',
	        data: $('form#crtdocketDisplay').serialize(),
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
})
</script>
<title><bean:message key="title.heading"/>/GALattorneySettingSearch.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/submitJuvenileDistrictCourtGALAttorneySetting" target="content">
<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Search GAL Attorney Setting</h2></td>
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
		

<!-- BEGIN ERROR TABLE  -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr> 
	<tr>		  
		<td align="center" class="errorAlert"><bean:write name='courtHearingForm' property='errMessage' /></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE  -->

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
        <li>Action field Bar Number or GAL Attorney Name or Juvenile Number is required.</li>
        <li>Click Submit button to inquire GAL Attorney Setting.</li>
      </ul>
    </td>
  </tr>
  <logic:notEmpty name="courtHearingForm" property="dktSearchResults">
   <tr>
   	 <td align="center"><bean:write name="courtHearingForm" property="searchResultSize"/> Record(s) Found </td>
  </tr>
 </logic:notEmpty>
</table>
<%-- END INSTRUCTION TABLE --%>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue" colspan="5">      
   <tr class='crtDetailHead'>
		<td align='left' colspan="7" class='paddedFourPix'>&nbsp;</td>
	</tr>    
	<tr>
		<td class='formDeLabel' colspan="1" width="5%" nowrap="nowrap"><span>+</span><bean:message key="prompt.barNumber"/></td>
		<td class='formDe' nowrap>
		  <html:text name="courtHearingForm" property="galBarNumber" styleId="barNumber"  maxlength="8" size="8"/>  
		  <html:button property="submitAction" styleId="validateBarNumber"><bean:message key="button.validateBarNumber"/></html:button>
		</td>
		<td class='formDeLabel'  colspan="1"  width="5%" nowrap="nowrap"><span>+</span><bean:message key="prompt.galattorneyName"/></td>
		<td class='formDe' colspan="7" width="5%" nowrap="nowrap" colspan="3">
			<input type="text" name="attorneyName" id="attorneyName" size="50" maxlength="50" value="<bean:write name="courtHearingForm" property="galName"/>" disabled/> <html:button property="submitAction" styleId="searchAttorney" value="Search GAL Attorney"></html:button>
			<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId" />
		</td>
	</tr>
	<tr>
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><bean:message key="prompt.hearingDate"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<html:text name="courtHearingForm" property="courtDate" styleId="courtDate"  maxlength="10" size="10"/>
		</td> 
		<td class='formDeLabel' colspan= '1' valign='top' width='1%' nowrap><span>+</span><bean:message key="prompt.juvenileNumber"/></td>
		<td class='formDe' width="15%" colspan='1' nowrap>
			<html:text name="courtHearingForm" property="juvenileNumber" styleId="juvNum" maxlength="12" size="12"/> 
		</td>
	</tr>
	
</table>

<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">    
	<tr>
		<td align="center" colspan="4">
			<span align="center"><html:button property="submitAction" styleId="submitBtn"><bean:message key="button.submit"/></html:button></span>
	   	</td>
	</tr>
</table>
<br>
<logic:notEmpty name="courtHearingForm" property="dktSearchResults">
<!-- Begin Pagination Header Tag-->
<pg:pager index="center" maxPageItems="10" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<!-- End Pagination header stuff -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
	 	<tr>
			<td>
		  		<table width='100%' cellpadding="1" cellspacing="1">
	 				<tr class="crtDetailHead">   
	    				<td>   
							<bean:message key="prompt.date" />
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="courtDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="1" /> 
	          			</td>
	          			<td>   
							<bean:message key="prompt.time" />
							 <jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="courtTime" primarySortType="STRING" defaultSortOrder="ASC" sortId="2"/>
	          			</td>
	          			<td>
	  						<bean:message key="prompt.court"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="court" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
		          		</td>
		          		<%-- <td>
	  						<bean:message key="prompt.transferTo"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="transferTo" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" />
		          		</td> --%>
	          			<td> 
							<bean:message key="prompt.petition" />#
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
	          			</td>
	          			<td> 
							<bean:message key="prompt.hrngType"/> 
							<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="hearingType" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
	          			</td>
						<td>
	  						<bean:message key="prompt.connection"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="attorneyConnection" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.juvenileName"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" />
		          		</td>
		          		<td>
	  						<bean:message key="prompt.galattorneyName"/>
	  						<jims:sortResults beanName="courtHearingForm" results="dktSearchResults" primaryPropSort="galName" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
		          		</td>
					</tr>
					
	  				<logic:iterate id="dockets" name="courtHearingForm" property="dktSearchResults" indexId='indexer'> 
	          			<pg:item>
			    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="10">
			    					<td class='formDe'><bean:write name="dockets" property="courtDate"/></td>
			    					<td class='formDe'><bean:write name="dockets" property="courtTime"/></td>
			    					<td class='formDe'><bean:write name="dockets" property="court"/></td>
			    					<%-- <td class='formDe'><bean:write name="dockets" property="transferTo"/></td> --%>
			    					<td class='formDe' colspan="1"><bean:write name="dockets" property="petitionNumber"/></td>
			    					<td class='formDe'><span title='<bean:write name="dockets" property="hearingTypeDesc"/>'><bean:write name="dockets" property="hearingType"/></span></td>
			    					<td class='formDe' colspan="1"><span title='<bean:write name="dockets" property="attorneyConnectionDesc"/>'><bean:write name="dockets" property="attorneyConnection"/></span></td>
			    					<td class='formDe'><bean:write name="dockets" property="juvenileName"/><logic:empty name="dockets" property="juvenileName"><bean:write name="dockets" property="respondantName"/></logic:empty></td>
									<td class='formDe' colspan="1"><bean:write name="dockets" property="galName"/></td>
			    				</tr>
	  					</pg:item>
	  		  		</logic:iterate> 
		  	 	</table>
			</td>
		</tr>			
	</table>
	<%-- Begin Pagination navigation Row--%>
	<table align="center">
		<tr>
			<td align="center" colspan="4">				
				<span align="center"><html:submit property="submitAction" styleId="printBtn"><bean:message key="button.print"/></html:submit></span>
		   	</td>
		</tr>
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
<html:hidden styleId="action" name="courtHearingForm" property="action"/>
<html:hidden styleId="actionError" name="courtHearingForm" property="actionError"/>
<html:hidden styleId="validateMsg" name="courtHearingForm" property="validateMsg"/>
<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>