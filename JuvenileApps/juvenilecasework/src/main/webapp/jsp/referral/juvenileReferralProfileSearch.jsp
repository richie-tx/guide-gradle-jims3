<!DOCTYPE HTML>

<%-- Used for searching of juvenile profile in MJCW --%>
<%--MODIFICATIONS --%>
<%-- SDaripa  07/22/2014	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




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

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - juvenileReferralProfileSearch.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STRUTS VALIDATIONS--%>
<html:javascript formName="juvenileProfileSearchForm" />

<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casefileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/referral/referral.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/referral/juvReferralProfileSearch.js"></script>

<script>
/***
* Combination existing javascript validation (SearchValidator) and Struts javascript generated
* validation (validatejuvenileProfileSearchForm) - only on dateOfBirth
*/
function validateForm(theForm)
{
	if( SearchValidator(theForm) && validateJuvenileProfileSearchForm(theForm)){
		spinner();
		return true;
	}
	
	return false;
}

$(document).ready(function(){		
	$("#createJuvenileBtn").click(function(){
		spinner();
	})
	$("#refreshBtn").click(function(){
		spinner();
	})
	$("#cancelBtn").click(function(){
		spinner();
	})
})
</script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/displayJuvenileProfileSearchResults" target="content" focus="juvenileNum" onsubmit="return validateForm(this);" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<br/>
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Process Referrals - Search Juvenile</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
</br></br>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
   <tr>
     <td>
  	  <ul>
        <li>Select the type of search you wish or enter Juvenile Number.</li>
    	<li>Enter/Select required fields and other search values then select submit button to search.</li> 
     </ul>	
	</td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
  </tr>
  
  <tr>     	
   	<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
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


<%-- BEGIN INQUIRY TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
   <tr>
     <td class='referralDetailHead' width='1%' nowrap='nowrap' ><bean:message key="prompt.searchBy"/></td>
     <td class="referralDetailHead">
		<html:select property="searchType" styleId="searchType">
         <html:optionsCollection property="searchTypes" value="code" label="description" /> 
         <html:option value="JDALG">DALOG NUMBER</html:option>
  		</html:select> 
	 </td>
  </tr>
</table>

<%-- SEARCH BY JUVENILE NAME --%>
<br>
<span id="juvenileName" class='visible'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
	  <tr>
	    <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileLastName" /></td>
	    <td class="referralDetailHead" colspan="3"><html:text property="lastName" size="60" maxlength="75" styleId="lastName"/></td>
	  </tr>
	  
	  <tr>
	    <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.juvenileFirstName"/></td>
	    <td class="referralDetailHead"><html:text property="firstName" size="50" maxlength="50" styleId="firstName"/></td>
	    
	    <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.race"/></td>
	    <td class="referralDetailHead">
			<html:select property="raceId" style="raceId">
	    		<html:option key="select.generic" value=""/>
	    		<html:optionsCollection name="juvenileProfileSearchForm" property="races" value="code" label="description" />
			</html:select>
		 </td>
	  </tr>
	  
	  <tr>
	    <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.juvenileMiddleName"/></td>
	    <td class="referralDetailHead"><html:text property="middleName" size="50" maxlength="50" styleId="middleName"/></td>
	    
	    <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.sex"/></td>
	    <td class="referralDetailHead">
			<html:select property="sexId" styleId="sexId">
	    		<html:option key="select.generic" value="" />
	    		<html:optionsCollection name="juvenileProfileSearchForm" property="sexes" value="code" label="description"/>
			</html:select>
	    </td>
	  </tr>
	  <tr>
	  	 <td class="referralDetailHead"  nowrap='nowrap' width='1%'>+<bean:message key="prompt.juvenile"/> Master <bean:message key="prompt.status" /></td>
		 <td class="referralDetailHead" colspan='6'>
			<html:select property="statusId" styleId="statusId">
		    	<html:option key="select.generic" value="" />
		    	<html:optionsCollection name="juvenileProfileSearchForm" property="juvMasterStatusCodes" value="code" label="description" />
		    </html:select> 
		 </td>
		 
	  </tr>
	</table>
</span>

<%-- SEARCH BY JUVENILE NUMBER --%>
<span id="juvNumber" class='visible'>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
  <tr>
     <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
     <td class="referralDetailHead"><html:text property="juvenileNum" size="8" maxlength="8" styleId="juvenileNum"/></td>
  </tr>
</table>
</span>

<%-- SEARCH BY DATE OF BIRTH --%>
<span id="juvDOB" class='visible'>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
  <tr>
     <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.dateOfBirth"/></td>
     <td class="referralDetailHead"><html:text property="juvenileDOB" size="10" styleId="dateOfBirth"/></td> 	  
  </tr>
</table>
</span>
<%-- END INQUIRY TABLE --%>

<%-- SEARCH BY SOCIAL SECURITY NUMBER --%>
<span id="juvSSN" >
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
 <tr>
	<td class="referralDetailHead" nowrap width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.ssn"/></td>
	<td class="referralDetailHead">
		<html:text name="juvenileProfileSearchForm" property="ssn.SSN1" styleId="SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />
		<html:text name="juvenileProfileSearchForm" property="ssn.SSN2" styleId="SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" />
		<html:text name="juvenileProfileSearchForm" property="ssn.SSN3" styleId="SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
	</td>
 </tr>
</table>
</span>
<%-- SEARCH BY DALOG NUMBER --%>
<span id="dalogNumber" class='visible'>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
  <tr>
     <td class="referralDetailHead" nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.daLogNumber"/></td>
     <td class="referralDetailHead"><html:text property="dalogNum" size="8" maxlength="8" styleId="dalogNum"/></td>
  </tr>
</table>
</span>
<%-- BEGIN BUTTON TABLE --%>

<table align="center" border="0" width="100%">
<tr><td></td></tr>
	<tr>
		<td align="center">
		  <html:submit property="submitAction"  styleId="submitBtn"> <bean:message key="button.submit" /></html:submit>&nbsp;
  		  <logic:equal name="juvenileProfileSearchForm" property="referralAction" value="failure">
  		  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CREATEJUVENILE%>'>		
  		  		<html:submit property="submitAction"  styleId="createJuvenileBtn"> <bean:message key="button.createJuvenile" /></html:submit>&nbsp;
  		  	</jims2:isAllowed>
  		  </logic:equal>
 		  <input type="button" id="refreshBtn" onclick="goNav('/<msp:webapp/>displayJuvenileProfileSearch.do?isReferral=true')" value="<bean:message key='button.refresh'/>"/>
  		  &nbsp;
  		  <html:button property="org.apache.struts.taglib.html.CANCEL" styleId="cancelBtn" onclick="goNav('/appshell/displayHome.do')">
  			<bean:message key="button.cancel"></bean:message>
  		  </html:button>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<br>
<span id="searchResults" class='visible'>
	<logic:equal name="juvenileProfileSearchForm" property="referralAction" value="referralSearchResults">	
		<html:hidden  property="referralAction" value="referralSearchResults" styleId="referralAction"/> <!-- used for show/hide search results -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
			</tr>
			<tr>
				<td valign="top" align="center">
				<%-- BEGIN TRAITS TABLE --%>
		    	<tiles:insert page="juvenileReferralProfileSearchResults.jsp"> 
					<tiles:put name="actionPath" value="/displayJuvenileProfileSearchResults"/>
				</tiles:insert>
				<%-- END TRAITS TABLE --%>
				</td>
			</tr>
		</table>
	 </logic:equal>
</span>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
