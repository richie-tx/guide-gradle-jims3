<!DOCTYPE HTML>
<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/21/2005		DWilliamson	Create JSP --%>
<%-- 09/16/2013		CShimek 	#76047 added confirm message area for trait status update. --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewInfoSpecialInterest.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Special Interest</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
<%--
		<logic:equal name="juvenileProfileForm" property="action" value="confirm">			
		   <tr><td class=confirm align="center">Special Interest Traits successfully added.</td></tr>   				
		</logic:equal>
		--%>
	<logic:equal name="juvenileTraitsForm" property="action" value="confirm">
		<tr>
			<td class="confirm">Traits successfully added.</td>
		</tr>
	</logic:equal>
	<logic:equal name="juvenileTraitsForm" property="confirmMessage" value="">
		<tr>
			<td class="confirm"><bean:write name="juvenileTraitsForm" property="confirmMessage"/></td>
		</tr>
	</logic:equal>
			
	<br>
	<tr>
		<td>
			<ul>
				<li>Select a Trait Type and Click View Traits button to see list of traits for that type.</li>
				<li>Click Add More Traits button to add additional traits.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<table align="center" cellpadding=1 cellspacing=0 border=0 width='100%'>
	<tr>
		<td>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
		</td>
	</tr>
</table>
<%--juv profile header end--%>


<%-- BEGIN DETAIL TABLE --%>
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td valign=top>
				<%--tabs start--%>
					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>	
				<%--tabs end--%>
				</td>
			</tr>
			<tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			  </tr>
			</table>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			<tr>
				<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			</tr>
			<tr>
				<td valign=top align=center>

					<%-- BEGIN TABLE --%>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
										<table width='100%' border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td valign=top>
												<%--tabs start--%>
													<%--script type='text/javascript'>renderInterviewInfo("Special Interest")</script--%>
													<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
														<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
														<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													</tiles:insert>	
												<%--tabs end--%>
												</td>
											</tr>
											<tr>
									  	<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
									  </tr>
									</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
										</tr>
										<tr>
											<td valign=top align=center>
											    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|172">											
												<%-- BEGIN TRAITS TABLE --%>
												<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp"> 
													<tiles:put name="actionPath" value="/handleJuvenileProfileTraits"/>
												</tiles:insert>								
											</td>
										</tr>
									</table>

								</td>
							</tr>
						</table><br><%-- END TABLE --%>
					</td>
				</tr>
			</table>
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>

