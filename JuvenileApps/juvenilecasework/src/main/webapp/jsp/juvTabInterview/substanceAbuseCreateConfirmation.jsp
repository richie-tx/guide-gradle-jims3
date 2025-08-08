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
<title><bean:message key="title.heading"/> - substanceAbuseCreateConfirmation.jsp</title>

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileDrugForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/substanceAbuse.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Substance Abuse - Confirmation</td>	  	    	 
  	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<br>

<logic:notEqual name="juvenileDrugForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
		<td class="confirm" colspan="4">
			<bean:write name="juvenileDrugForm" property="msg" />
	 	</td>
	</tr>
	</table>
</logic:notEqual>


<%-- END INSTRUCTION TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<html:form styleId="juvenileDrugTestingForm" action="/handleSubstanceAbuseCreateAction" target="content">
<html:hidden styleId="referralNum" name="juvenileDrugForm" property="referralNum"/>
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
										<tiles:put name="tabid" value="DRUGS" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
							</td>
						</tr>
						<tr>
					  		<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
					  	</tr>
			  		</table>
					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
						<tr>
							<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
						</tr>
						<tr>
							<td valign=top align=center>
									<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td colspan=6 class=detailHead>Substance Abuse(TJJD Required)</td>
										</tr>
										<tr>
											<td width='24%' class=formDeLabel>Associated Casefile #</td>	
											<td width='25%' class=formDe>
												<bean:write name="juvenileDrugForm" property="associateCasefile"/>
											</td>
											<td width='24%' class=formDeLabel>Referral Number</td>
											<td width='25%' width='25%' id="referralNumber" class=formDe>
												<bean:write name="juvenileDrugForm" property="referralNum"/>
											</td>
										</tr>
										<tr>
											<td width='24%' class=formDeLabel>Substance Abuse ?</td>	
											<td width='25%' class=formDe>
												<bean:write name="juvenileDrugForm" property="substanceAbuseDesc"/>
											</td>
											<td width='24%' class=formDeLabel>Substance Type</td>
											<td width='25%' width='25%' class=formDe>
												<bean:write name="juvenileDrugForm" property="substanceTypeDesc"/>
											</td>
										</tr>
										<tr>
											<td width='24%' class=formDeLabel>Treatment Location</td>
											<td width='25%' width='25%' class=formDe>
												<bean:write name="juvenileDrugForm" property="treatmentLocation"/>
											</td>
											<td width='24%' class=formDeLabel></td>
											<td width='25%' class=formDe></td>
										</tr>
									</table>
									<div class=spacer></div>		
										<table width='98%'>	
										  <tr>
											<td align="center">
												  <html:submit onclick="spinner()" property="submitAction">				
													<bean:message key="button.backToList"></bean:message>
												  </html:submit>
											</td>
										</tr>
										</table>
									<div class=spacer></div>
								</td>
							</tr>
							
						</table>
    				</td>
    			</tr>
    			<tr>
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5/></td>
				</tr>
    		</table>
    	</td>
    </tr>
</table>
</html:form>


<div align=center><script type="text/javascript">renderBackToTop()</script>
</body>
</html:html>
