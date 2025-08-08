<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 12/11/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features  --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek        #73565 added age > 20 check (juvUnder21) to Update button and href --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading"/> - familyMemberDetails.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayFamilyMemberDetails" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|244">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.member"/>&nbsp;<bean:message key="prompt.details"/></td>	  	    	 
 	</tr>  	
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<logic:equal name="juvenileFamilyForm" property="action" value="">
				<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
					<ul>
						<li>Select Update Member button to update family member details.</li>
					</ul>
				</logic:equal>
			</logic:equal>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding="1" cellspacing="0" border="0" width='100%'>
	<tr>
		<td>
			<%--header info start--%>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
			<%--header info end--%>
		</td>
	</tr>
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">
    	<%-- BEGIN GREEN TABS TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  			<tr>
  				<td valign="top">
    				<%--tabs start--%>
  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
 							<tiles:put name="tabid" value="family"/>
 							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  					</tiles:insert>	
    				<%--tabs end--%>
  				</td>
  			</tr>
  			<tr>
    			<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
 		    </tr>
  		</table>
<%-- END GREEN TABS TABLE --%>
<%-- BEGIN TAB GREEN BORDER TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">
					<div class='spacer'></div>
<%-- begin blue tabs --%>						
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><%--tabs start--%> 
								<tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
									<tiles:put name="tabid" value="MemberInformation" />
									<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
								</tiles:insert> <%--tabs end--%>
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
						</tr>
					</table>
<%-- end blue tabs --%>
<%--begin blue tabs outer border --%>
  					<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  						<tr>
  							<td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
  						</tr>
  						<tr>
  							<td valign="top" align="center">
      							<%-- member details start--%>
								<tiles:insert page="familyMemberDetailsTile.jsp" flush="true">
								</tiles:insert>
            			</table>			
						<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
	  					<table border="0" width="100%">
	  					 	<tr>
								<td align="center">
								
									<input id='juvStatus' type='hidden' value='<bean:write name="juvenileFamilyForm" property="isClosedJuvStatus"/>' >
									<input type="button" value="Back" name="return" onclick="history.go(-1)">
									<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
										<logic:equal name="juvenileFamilyForm" property="action" value="">
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>
													<input type="button" value="Update Member" id="updateMemberButton" data-href="/<msp:webapp/>displayCreateFamilyMember.do?submitAction=<bean:message key="button.update"/>">											
											</jims2:isAllowed>											
										</logic:equal>
									</logic:equal>
								</td>
							</tr>
	  					</table>
<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
  		</td> 
	</tr> 
</table> 
<%-- END DETAIL TABLE --%> 
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>