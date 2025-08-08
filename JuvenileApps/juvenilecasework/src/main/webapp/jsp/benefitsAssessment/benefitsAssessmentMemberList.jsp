<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>
<%-- 07/17/2009  C Shimek      - #61004 added timeout.js  --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/> - benefitsAssessmentMemberList.jsp</title>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0" onLoad=maintainChks('0')> 
<html:form action="/displayBenefitsAssessmentForm" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|3"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr> 
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.benefitsAssessmentGuardianList"/></td> 
  </tr>
  <tr>
    <td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div> 
<table width="98%" border="0"> 
  <tr> 
    <td> 
      <ul>
        <li>Select Radio button for member to process benefits assessment.</li> 
      </ul> 
    </td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign=top>

      <%-- BEGIN Current Constellation TABLE --%> 
      <table align=center width='100%' cellspacing=0 cellpadding=2 class=borderTableBlue> 
      	<tr> 
      		<td valign=top class="detailHead"><bean:message key="prompt.currentConstellation"/> - <bean:message key="prompt.family#"/> <bean:write name="processBenefitsAssessmentForm" property="familyNumber"/></td> 
      	</tr> 
      	<tr> 
      		<td colspan="2">
      			<table align="center" width="100%" border="0" cellpadding="4" cellspacing="1" >
      			  <tr bgcolor='#cccccc'> 
      					<td width='3%'></td> 
      					<td width="15%" class=subHead><bean:message key="prompt.member#"/></td> 
      					<td class=subHead><bean:message key="prompt.name"/></td> 
      					<td class=subHead><bean:message key="prompt.relationship"/></td> 
      				</tr> 
      				
      				<logic:notEmpty name="processBenefitsAssessmentForm" property="currentAssessment.listOfGuardians">
	      				<logic:iterate indexId="index" name="processBenefitsAssessmentForm" property="currentAssessment.listOfGuardians" id="juvGuardiansIter">
			            <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	      						<bean:define id="memberNumber" name="juvGuardiansIter" property="memberNumber" type="java.lang.String"/>
	      						<td width='3%'><html:radio name="processBenefitsAssessmentForm" property="currentAssessment.guardian.memberNumber" value="<%=memberNumber%>"/></td> 
	      						<td><bean:write name="juvGuardiansIter" property="memberNumber"/></td>
	      						<td><bean:write name="juvGuardiansIter" property="name.formattedName"/></td>
	      						<td><bean:write name="juvGuardiansIter" property="relationship"/></td> 
	      					</tr>
	      				</logic:iterate>
      				</logic:notEmpty> 
      			</table>
      		</td> 
      	</tr> 
      </table>
      <%-- END Current Constellation TABLE --%>   

      <%-- BEGIN BUTTON TABLE --%> 
      <div class='spacer'></div>
      <table border="0" width="100%">
      	<tr>
      		<td align="center">
      			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
      			<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
      			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
      		</td>
      	</tr>
      </table> 
      <%-- END BUTTON TABLE --%> 
    </td>
  </tr>
</table>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div> 

</body>
</html:html>

