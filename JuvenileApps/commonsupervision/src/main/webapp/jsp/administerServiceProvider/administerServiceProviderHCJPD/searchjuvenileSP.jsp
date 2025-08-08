<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- STRUTS VALIDATION --%>
<html:javascript formName="juvenileProfileSearchForm"/>
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading" /> - searchjuvenileSP.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<%-- <script type="text/javascript" src="/<msp:webapp/>js/commonsupervision.js"></script> --%>
<%-- <script type="text/javascript" src="/<msp:webapp/>js/juvProfileSearch.js"></script> --%>

<script type="text/javascript">
function validateForm(theForm)
{	
	 if(validateJuvenileProfileSearchForm(theForm))
	{
		spinner();
		return true;
	}
	return false;
} 
</script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/displayServiceProviderJuvenileSearch" target="content" focus="lastName" onsubmit="return validateForm(this);">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">  
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>    
    <td class="header" align="center"><bean:message key="title.serviceProvider"/> - Juvenile Profile - Search</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0" align="center">
   <tr>
     <td>
  	  <ul>        	
    		<li>Enter/Select required fields and other search values then select submit button to search.</li> 
     </ul>	
		</td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.3.diamond"/>&nbsp;Required Fields</td>
  </tr>
  <tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
  </tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<%-- SEARCH BY JUVENILE NAME --%>
<br>
<span id="juvenileName" class='visible'>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
   <tr>
    <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.juvenileLastName" /></td>
    <td class="formDe"><html:text property="lastName" size="60" maxlength="75" styleId="lastName"/></td>
   </tr>
   <tr>
    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.juvenileFirstName"/></td>
    <td class="formDe"><html:text property="firstName" size="50" maxlength="50" styleId="firstName"/></td>
  </tr>
  <tr>
    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.juvenileMiddleName"/></td>
    <td class="formDe"><html:text property="middleName" size="50" maxlength="50" styleId="middleName"/></td>
  </tr>
  <tr>
    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.race"/></td>
    <td class="formDe">
		  <html:select property="raceId" styleId="raceId">
    			<html:option key="select.generic" value=""/>
    			<html:optionsCollection name="juvenileProfileSearchForm" property="races" value="code" label="description" />
		  </html:select>
	 </td>
   </tr>
   <tr>
    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.sex"/></td>
    <td class="formDe">
		  <html:select property="sexId" styleId="sexId">
    			<html:option key="select.generic" value="" />
    			<html:optionsCollection name="juvenileProfileSearchForm" property="sexes" value="code" label="description"/>
		  </html:select>
    </td>
   </tr>   
</table>
</span>

<%-- BEGIN BUTTON TABLE --%>
<br>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
		  <html:submit property="submitAction" styleId="submitBtn">
  			<bean:message key="button.submit" />
  		</html:submit>&nbsp;
  		<input type="button" onclick="goNav('/<msp:webapp/>displayServiceProviderJuvenileSearch.do?submitAction=Link')" value="<bean:message key='button.refresh'/>"/>
		
  		&nbsp;
  		<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="document.location.href='/appshell/displayHomeSecurity.do'">
  			<bean:message key="button.cancel"></bean:message>
  		</html:button>
		</td>
	</tr>
	
</table>

<%-- END BUTTON TABLE --%>
<logic:notEmpty  name="juvenileProfileSearchForm" property="juvenileProfiles">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
</tr>
<tr>
    <td align="center"><bean:write name="juvenileProfileSearchForm" property="searchResultSize"/> juvenile profiles found in search results</td>
 </tr>
<tr>
<td valign="top" align="center">

<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
  <tr>
	 <td>
		<table  width='100%' cellpadding="2" cellspacing="1">
			<tr class="formDeLabel">
				<td>   
					<bean:message key="prompt.juvenileName" />
					<jims2:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" sortId="1" levelDeep="3" />
				</td>
				<td>   
					<bean:message key="prompt.race" />
					<jims2:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="race" primarySortType="STRING" sortId="2" levelDeep="3"  />				
				</td>
				<td>   
					<bean:message key="prompt.sex" />
					<jims2:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="sex" primarySortType="STRING" sortId="3" levelDeep="3"  />
				</td>
				<td>   
					<%-- <bean:message key="prompt.JPOofRecord" /> --%>JPO of Record
					<jims2:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="jpoOfRecID" primarySortType="STRING" sortId="4" levelDeep="3"  />
				</td>
				<td>   
					<%-- <bean:message key="prompt.JPOofRecord" /> --%>JPO's email
					 <jims2:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="jpoEmail" primarySortType="STRING" sortId="5" levelDeep="3"  /> 
				</td>
				<td>   
					<%-- <bean:message key="prompt.JPOofRecord" /> --%>JPO's Phone No
					<jims2:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="jpoPhoneNumber" primarySortType="STRING" sortId="6" levelDeep="3"/> 
				</td>			
			</tr>

		<logic:iterate id="juvenileProfiles" name="juvenileProfileSearchForm" property="juvenileProfiles" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" heignt="100%">				 	
				<td><bean:write name="juvenileProfiles" property="lastName"/>,&nbsp;<bean:write name="juvenileProfiles" property="firstName"/>&nbsp;<bean:write name="juvenileProfiles" property="middleName"/>&nbsp;<bean:write name="juvenileProfiles" property="nameSuffix"/>
				</td>
				<td><bean:write name="juvenileProfiles" property="race"/></td>
				<td><bean:write name="juvenileProfiles" property="sex"/></td>
				<logic:notEmpty name="juvenileProfiles" property="jpoOfRecID">				
					<td>					
							<logic:notEmpty name="juvenileProfiles" property="sprvsionTypeCd"><bean:write name="juvenileProfiles" property="sprvsionType"/>/</logic:notEmpty>
							<span title="<bean:write name="juvenileProfiles" property="jpoOfRecord"/>"><bean:write name="juvenileProfiles" property="jpoOfRecID"/></span>
					</td>
					<td><a href='mailto:<bean:write name="juvenileProfiles" property="jpoEmail"/>'><bean:write name="juvenileProfiles" property="jpoEmail"/></a></td>
					<td><bean:write name="juvenileProfiles" property="jpoPhoneNumber"/></td>
				</logic:notEmpty>
				<logic:empty name="juvenileProfiles" property="jpoOfRecID"><td colspan="3" align="left"><b>Contact Data Corrections for assistance</b></td></logic:empty>
			</tr>
		  </pg:item>
		  <%-- End Pagination item wrap --%>
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
	    						<tiles:put name="pagerUniqueName" value="pagerSearch"/>
	    						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
	    					</tiles:insert>
	    			 	</pg:index>
	  		    	</td>
			    </tr>
		  </table>
		  <%-- End Pagination navigation Row--%>
<%-- END DETAIL TABLE --%>
<html:hidden styleId="countr" name="juvenileProfileSearchForm" property="searchResultSize"/>
</td>
</tr>
</table>
</logic:notEmpty>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
