<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 04/19/2012 C Shimek 	    #73232 added allowUpdate edit to buttons for closed casefile status  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- goalDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayGoalDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|71"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - Goal Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<!--<li>Select Associated Rules hyperlink to view Associated Rule details.</li>-->
				<li>Select Back button to return to Goals list. </li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign=top>
  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top align=center>
					
					  <div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  						<tr>
  							<td valign=top>
    							<tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="Caseplan"/>
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>				
    						</td>
  						</tr>
  					 	<tr>
  					  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
  					  </tr>
  					</table>

      			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
      				<tr>
      					<td valign=top align=center>
      					  <div class='spacer'></div>
      						<tiles:insert page="goalInformationTile.jsp" flush="true">
      							<tiles:put name="goalInfo" beanName="caseplanForm" beanProperty="currentGoalInfo" />	
      						</tiles:insert>

      						<div class='spacer'></div>
      						<!--commented for ER 11225 - remove all rules from goals
      						<table width='100%' border='0'>
            				<tr>
            					<td valign=top align=center>
            						<tiles:insert page="rulesAssociatedToGoalTile.jsp" flush="true"></tiles:insert>
            					</td>
            				</tr>	
            			</table>   
      				    -->
      				    <%-- BEGIN BUTTON TABLE --%>
      						<div class='spacer'></div> 
      						<table border="0" width="100%">
      							<tr>
      								<td align="center">
      									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
										<!-- Goals associated to casefile that is closed or closed approved cannot be modified.-->
										<logic:equal name="caseplanForm" property="allowUpdates" value="true">
											<!--  Goals that have status ENDED cannot be modified.-->
	      									<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusStr" value="ENDED">
	      										<logic:notEqual name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">  
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
														<!-- Additionally, when a Goal is LOCKED, or the Caseplan status is IN-REVIEW, it cannot be updated as well. -->
														<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">  
															<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusCd" value="L">
																<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
															</logic:notEqual>
															<logic:notEqual name="caseplanForm" property="currentGoalInfo.statusCd" value="L">
																 <%-- <logic:notEqual name="caseplanForm" property="currentGoalInfo.statusStr" value="ACCEPTED">
																	<input  type="submit" name="submitAction" value="<bean:message key='button.associateGoalToRules'/>" 
																		onclick="return changeFormActionURL('caseplanForm', '/<msp:webapp/>displayAssociateRules.do', false)">
																</logic:notEqual>  --%>
															</logic:notEqual>
														</logic:notEqual>
	      											</jims2:isAllowed>
	      										</logic:notEqual>
	      									</logic:notEqual>
										</logic:equal>      									
      								</td>
      							</tr>
      						</table>
      						<div class='spacer'></div>
      						<%-- END BUTTON TABLE --%>

      					</td>
      				</tr>
						</table>
						<div class=spacer></div>
      		</td>
      	</tr>
      </table>
		</td>
	</tr>
</table>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
