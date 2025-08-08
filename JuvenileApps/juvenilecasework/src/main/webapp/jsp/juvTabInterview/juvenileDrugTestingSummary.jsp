<!DOCTYPE HTML>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileDrugTestingSummary.jsp</title>

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileDrugForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/drugs.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Drug/Substance Testing - Summary</td>	  	    	 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<br>
<logic:notEqual name="juvenileDrugForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="juvenileDrugForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
</logic:notEqual>
<%-- END INSTRUCTION TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<script language=javascript>

</script>
<br>

<%-- BEGIN DETAIL TABLE --%>  
<% int RecordCounter = 0; 
									String bgcolor = "";%>	
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  	<tr>
    	<td valign=top>
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="main"/>
							<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
			  	</tr>
			</table>
			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
				</tr>
				<tr>
					<td valign=top align=center>
					
					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
							<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
								<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
								<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
							</tiles:insert>	
					</td>
				</tr>
				<tr>
			  		<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			  	
		<html:form action="/handleJuvenileDrugTestingCreate" target="content">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|174">
			
			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
				<tr>
					<td valign=top align=center>																							
			<%-- BEGIN SUBSTANCE USE INFO TABLE --%>					
						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr>
								<td colspan=6 class=detailHead>Drug Testing</td>
							</tr>
							<tr>
								<td class=formDeLabel>Associated Casefile #</td>	
								<td class=formDe>
									<bean:write name="juvenileDrugForm" property="associateCasefile"/>
									
								</td>
								<td class=formDeLabel>Administered ? </td>
								<td class=formDe>
									<bean:write name="juvenileDrugForm" property="testAdministeredDescr"/>
								</td>
							</tr>
							<tr>
								<td class=formDeLabel>Test Date</td>	
								<td class=formDe>
									<bean:write name="juvenileDrugForm" property="testDate"/>
								</td>
								<td class=formDeLabel>Test Time</td>
								<td class=formDe>
									<bean:write name="juvenileDrugForm" property="testTime"/>	
								</td>
							</tr>
							
							<tr>
								<td class=formDeLabel>Substance Tested</td>	
								<td class=formDe>
									<bean:write  name="juvenileDrugForm" property="substanceTestedDescr"/>
								</td>
								<td class=formDeLabel>Results</td>
								<td class=formDe>
									<bean:write  name="juvenileDrugForm" property="drugTestResultDescr"/>
								</td>
								
							</tr>
							<tr>
								<td class=formDeLabel>Test Location</td>	
								<td class=formDe>
									<bean:write  name="juvenileDrugForm" property="testLocationDescr"/>
								</td>
								<td class=formDeLabel>Administered By</td>
								<td class=formDe>
									<bean:write name="juvenileDrugForm" property="administeredBy"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" colspan="6">
									Comments
								</td>
							</tr>
							<tr>
								<td class="formDe" colspan="6">
									<bean:write name="juvenileDrugForm" property="comments"/>
								</td>
							</tr>
						</table>			    		 
			<%-- END SUBSTANCE USE INFO TABLE --%>
			<%-- BEGIN BUTTON TABLE --%>
						<div class=spacer></div>		
						<table width='98%'>	
						  <tr>
							<td align="center">
								<input type="button" value="Back" onclick="history.back();"/>
								  <html:submit styleId="finishBtn" onclick="spinner()" property="submitAction">				
									<bean:message key="button.finish"></bean:message>
								  </html:submit>			
								<html:submit property="submitAction">
									<bean:message key="button.cancel"></bean:message>
								</html:submit>
							</td>
						</tr>
					</table>
					<div class=spacer></div>
					<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
			<br>		
   		</td>
  	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<br>
<%-- END FORM --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

