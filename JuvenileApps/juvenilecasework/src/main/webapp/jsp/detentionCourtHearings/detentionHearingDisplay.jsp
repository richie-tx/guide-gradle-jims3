<!DOCTYPE HTML>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
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
<script type='text/javascript' src="/<msp:webapp/>js/detentionCourt/detentionHearingDisplay.js"></script>

<html:base />
<title><bean:message key="title.heading"/>/detentionHearingDisplay.jsp</title>
</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileSearchDetentionHearings" target="content" >

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<table width='100%'>
	<tr>
		<td align="center"><h2>Search Detention Hearings<br/><br/>Detention Hearing Display</h2></td>
	</tr>
</table>
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


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Click "Print" to print the docket.</li>
        <li>Click "Cancel" to return to search page.</li>
      </ul>
    </td>
  </tr>
  <logic:notEmpty name="detentionHearingForm" property="detentionSearchResults">
   <tr>
    <td align="center">(<bean:write name="detentionHearingForm" property="searchResultSize"/>) Settings Found</td>
  </tr>
</logic:notEmpty>
</table>
<%-- END INSTRUCTION TABLE --%>

<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="7">          
	<tr class='crtDetailHead'>
		<td align='left' colspan="7" class='paddedFourPix'>&nbsp;</td>
	</tr>	
	<tr>
		<td class='formDeLabel' nowrap="nowrap" width="2%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.court"/></td>
		<td class='formDe' width="30%"><bean:write name="detentionHearingForm" property="courtId"/></td>
		<td class='formDeLabel' valign='top' align="right" width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.for" /></td>
		<td class='formDe' >	
			<html:text name="detentionHearingForm" property="date" styleId="date" size="10" maxlength="10" size="10"/>
			<html:submit property="submitAction" styleId="btnCrtDocket"><bean:message key="button.go"/></html:submit>
		</td>
		
		<td class='formDeLabel' valign='top' align="right" width='5%' nowrap><bean:message key="prompt.facility" /></td>
		<td class='formDe' width="20%"  nowrap>
			<span title='<bean:write name="detentionHearingForm" property="facilityDesc"/>'><bean:write name="detentionHearingForm" property="facility"/></span>
		</td>
	</tr>
	<br><br/>
	<tr>
		<td class='formDeLabel' valign='top' width='1%' align="right" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.time"/></td>
		<td class='formDe' width="25%" nowrap>
			<html:text name="detentionHearingForm" property="docketTime" size="3" styleId="docketTime"/>
		</td>
		<td class='formDeLabel' valign='top' width='1%' align="right" nowrap><bean:message key="prompt.assistantDA" /></td>
		<td class='formDe' width="50%" nowrap>
			<html:text name="detentionHearingForm" property="assistantDA" size="23"/>
		</td>
		<td class='formDe' width="5%" nowrap>
		<td class='formDe' width="5%" nowrap>
	</tr>
</table>
<br><br/>
<pg:pager index="center" maxPageItems="10" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<!-- End Pagination header stuff -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
 	<tr>
		<td>
	  		<table width='100%' cellpadding="2" cellspacing="1">
 				<tr class="detailHead">   
    				<td>   
						<bean:message key="prompt.juvenile" />&nbsp;#
						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvenileNumber" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="1"/>				
          			</td>
          			<td>   
						<bean:message key="prompt.juvenileName" />&nbsp;
						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvenileName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />				
          			</td>
          			<td> 
						<bean:message key="prompt.race" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="race" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
          			</td>
          			<td> 
						<bean:message key="prompt.hispanic" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="hispanicInd" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
          			</td>
          			<td> 
						<bean:message key="prompt.gender" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="gender" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
          			</td>
					 <td>
  						<bean:message key="prompt.age" />
         				<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="age" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
	          		</td>
					<td> 
						<bean:message key="prompt.admitDate" />  
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="admitDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="7" />
          			</td>
          			<td>   
  						<bean:message key="prompt.probableCauseDate" />
  						<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="probableCauseDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="8" />				
          			</td>
          			<td>   
  						<bean:message key="prompt.courtDate" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="lastCourtDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="9" />
          			</td>
          			<td>   
  						<bean:message key="prompt.courtId" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="court" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
          			</td>
          			<td>   
  						<bean:message key="prompt.petitionNumber" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="petitionNumber" primarySortType="STRING" defaultSortOrder="ASC" sortId="11" />
          			</td>
          			<td>   
            			<bean:message key="prompt.JPO" />
            			<jims:sortResults beanName="detentionHearingForm" results="detentionSearchResults" primaryPropSort="juvProbationOfficerCd" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" /> 
          			</td>
				</tr>
				<%int count=0;%>
  				<logic:iterate id="dockets" name="detentionHearingForm" property="detentionSearchResults" indexId='indexer'> 
          			<%-- Begin Pagination item wrap --%>
          			<%count++; %>
          			<pg:item>
	    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" colspan="11">
	    					<td class='formDe'><bean:write name="dockets" property="juvenileNumber"/></td>
	    					<td class='formDe'><bean:write name="dockets" property="juvenileName"/></td>
							<td class='formDe'><bean:write name="dockets" property="race"/></td>
							<td class='formDe'><bean:write name="dockets" property="hispanicInd"/></td>
							<td class='formDe'><bean:write name="dockets" property="gender"/></td>
	    					<td class='formDe'><bean:write name="dockets" property="age"/></td>
	    					<td class='formDe'><bean:write name="dockets" property="admitDate"/></td> 
	    					<td class='formDe'><bean:write name="dockets" property="probableCauseDate"/></td> <!-- new field -->
	    					<td class='formDe'><bean:write name="dockets" property="lastCourtDate"/></td>
	    					<td class='formDe'><bean:write name="dockets" property="court"/></td>
	    					<td class='formDe'><bean:write name="dockets" property="petitionNumber"/></td> <!-- new field - limit to size 75-->
	    					<td class='formDe'><span title='<bean:write name="dockets" property="probationOfficer"/>'><bean:write name="dockets" property="probationOfficerCd"/></td>
	    					<html:hidden styleId='hearingType'  name='dockets' property='hearingType' indexed="true"/>
	    				</tr>
			    		</pg:pages>
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
</pg:pager>
<table width="100%" border="0">
	<tr>
		<td align="center">
		    <input id='back' type='button' value="Back" onclick="history.go(-1)"/>
			<html:submit property="submitAction" styleId="printDocketBtn"><bean:message key="button.print"/></html:submit>
	  	    <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
    	</td>
	</tr>
</table>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>