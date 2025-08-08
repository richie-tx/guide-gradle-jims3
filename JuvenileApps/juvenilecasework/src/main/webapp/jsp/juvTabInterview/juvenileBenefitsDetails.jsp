<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 05/23/2007		NAggarwal Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileBenefitsDetails.jsp</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/benefits.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitJuvenileBenefitsCreate" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|291">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Benefits
      <bean:message key="prompt.information"/>
      <bean:message key="title.summary"/>
		</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td align="left">
      <ul>
        <li>Verify that information is correct and select the Finish button.</li>
        <li>If any changes are needed, click Back button.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign=top>
            <tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
						</tiles:insert>
          </td>
        </tr>
        <tr>
          <td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
        </tr>
      </table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>

		        <%-- BEGIN TABLE --%>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
	  										<tiles:insert page="/jsp/caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value='benefitstab' />
												</tiles:insert>
											</td>
										</tr>
										<tr>
									  	<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
									  </tr>
									</table>
	
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top align=center>
											  <div class='spacer'></div>
												<tiles:insert page="/jsp/caseworkCommon/juvenileBenefitsDisplayTile.jsp" flush="true">
													<tiles:put name="tableTitle" value="Benefits Information"/>
													<tiles:put name="benefits" beanName="juvenileBenefitsInsuranceForm" beanProperty="juvBenefits"/>
													<tiles:put name="benefits" beanName="juvenileBenefitsInsuranceForm" beanProperty="newJuvBenefits"/>													
												</tiles:insert>
												
												<div class='spacer'></div>
												<tiles:insert page="/jsp/caseworkCommon/juvenileInsuranceDisplayTile.jsp" flush="true">
													<tiles:put name="tableTitle" value="Insurance Information"/>
													<tiles:put name="insurances" beanName="juvenileBenefitsInsuranceForm" beanProperty="juvInsurances"/>
												</tiles:insert>
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
									
								</td>
							</tr>
						</table>
		        <%-- END TABLE --%>
	
						<%-- BEGIN BUTTON TABLE --%>
						<div class=spacer></div> 
						<table border=0 align='center'>	
							<tr>
								<td>
									<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>
								</td>								
								<td>
									<html:submit property="submitAction" styleId="BenefitDetailFinish" ><bean:message key="button.finish"/></html:submit>
								</td>
</html:form>
								<html:form action="/displayJuvenileMasterInformation" target="content">
									<td><html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit></td>
								</html:form>
							</tr>
						</table>

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
