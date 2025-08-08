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
<%@ page import="naming.Features" %>
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
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/courtNameSearch.js"></script>

<title><bean:message key="title.heading" />/courtNameSearch.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/displayJuvenileDistrictCourtNameSearchResults" target="content" styleId="courtNameSearchForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Search Juvenile Records</h2></td>
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
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<li>Enter/Select required fields and other search values then select submit button to search.</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" />&nbsp;Required
					Fields</td>
			</tr>
			<tr>
				<td class="required">+ indicates Last Name is required to use this search field.</td>
			</tr>
			<tr>
				<td class="required"><bean:message
						key="prompt.dateFieldsInstruction" /></td>
			</tr>
		</table>
		<br/>
		<%-- END INSTRUCTION TABLE --%>	
		
		<!-- HEADER Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="nameSearchTb2">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead">
							<td colspan="8"><bean:message key="prompt.juvenileSearch" />
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<br/>
		<!-- HEADER Information  ENDS-->	
		<%-- BEGIN INQUIRY TABLE --%>
			<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="nameSearchTbl">
			<tr>
			<td>
				<table width='100%' cellpadding="2" cellspacing="1">
				<tr class="formDeLabel">
				<td width='1%' nowrap='nowrap'><bean:message key="prompt.searchBy" />
				</td>
				<td>
					<html:select property="searchType" styleId="searchType">
						<option value="JNAM">JUVENILE NAME</option>
						<option value="JDOB">DATE OF BIRTH</option>
					</html:select>
					</td>
				</tr>
				</table>
			</td>
			</tr>
		    </table>
		
		<%-- JUVENILE NAME SEARCH STARTS--%>
		<br/>
		<span id="juvenileName">
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue' id="nameSearchTb3">
		   <tr>
		    <td class="formDeLabel" nowrap="nowrap" width='1%' ><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileLastName" /></td>
		    <td class="formDe"><html:text property="juvenileLastName" size="60" maxlength="75" styleId="lastName"/></td>
		   </tr>
		   <tr>
		    <td class="formDeLabel" nowrap="nowrap" width='1%' >+<bean:message key="prompt.juvenileFirstName"/></td>
		    <td class="formDe"><html:text property="juvenileFirstName" size="50" maxlength="50" styleId="firstName"/></td>
		  </tr>
		  <tr>
		    <td class="formDeLabel" nowrap="nowrap" width='1%' >+<bean:message key="prompt.juvenileMiddleName"/></td>
		    <td class="formDe"><html:text property="juvenileMiddleName" size="50" maxlength="50" styleId="middleName"/></td>
		  </tr>
		  <tr>
		    <td class="formDeLabel" nowrap="nowrap" width='1%' >+<bean:message key="prompt.race"/></td>
		    <td class="formDe">
				  <html:select property="raceId" styleId="raceId">
		    			<html:option key="select.generic" value=""/>
		    			<html:optionsCollection name="courtHearingForm" property="races" value="code" label="description" />
				  </html:select>
			 </td>
		   </tr>
		   <tr>
		    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.sex"/></td>
		    <td class="formDe">
				  <html:select property="sexId" styleId="sexId">
		    			<html:option key="select.generic" value="" />
		    			<html:optionsCollection name="courtHearingForm" property="sexes" value="code" label="description"/>
				  </html:select>
		    </td>
		   </tr>
		   <tr>
		    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.dateOfBirth"/></td>
		    <td class="formDe">
		      <html:text property="dateOfBirth" size="10" maxlength="10" styleId="dateOfBirth"/></td>
		   </tr>
		</table>
		</span>

	<%-- JUVENILE NAME SEARCH ENDS --%>
	<%-- SEARCH BY DATE OF BIRTH --%>
		<span id="juvDOB" class='hidden'>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
		  <tr>
		     <td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateOfBirth"/></td>
		     <td class="formDe"><html:text property="juvenileDOB" size="10" maxlength="10" styleId="jDOB"/></td> 	  
		  </tr>
		</table>
		</span>
		
	<%-- SEARCH BY DATE OF BIRTH ENDS--%>

	<%-- END INQUIRY TABLE --%>
		<%-- BEGIN BUTTON TABLE --%>
		<br>
		<table align="center" border="0" width="100%">
			<tr>
				<td align="center">
				  <html:submit property="submitAction" styleId="submitBtn"><bean:message key="button.submit" /></html:submit>&nbsp;
				  <%-- <html:submit property="submitAction" styleId="refreshBtn"><bean:message key="button.refresh"/></html:submit>&nbsp;
				  <html:submit property="submitAction" styleId="cancelBtn"><bean:message key="button.cancel"/></html:submit>&nbsp; --%>
				  <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileDistrictCourtNameSearchResults.do?submitAction=refresh')" value="<bean:message key='button.refresh'/>"/>&nbsp;
				  <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileDistrictCourtNameSearchResults.do?submitAction=cancel')" value="<bean:message key='button.cancel'/>"/>&nbsp;
				</td>
			</tr>
		</table>
		<%-- END BUTTON TABLE --%>		
		<span id="searchResults" class='visible'>
		<logic:equal name="courtHearingForm" property="resultsPage" value="resultsPage">	
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
			</tr>
			<tr>
				<td valign="top" align="center">
				<%-- BEGIN TRAITS TABLE --%>
		    	<tiles:insert page="courtNameSearchResults.jsp"> 
					<tiles:put name="actionPath" value="/displayJuvenileDistrictCourtNameSearchResults"/>
				</tiles:insert>
				<%-- END TRAITS TABLE --%>
				</td>
			</tr>
		</table>
		</logic:equal>
		</span>
		<html:hidden  property="resultsPage" value="resultsPage" styleId="resultsPage"/> <!-- used for show/hide search results -->
		<html:hidden styleId="action" name="courtHearingForm" property="action"/>
	</html:form>
</body>
</html:html>