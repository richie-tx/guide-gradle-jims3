<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/10/2006		AWidjaja Create JSP--%>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileBenefitsInsuranceList.jsp</title>

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
<html:form action="/displayJuvenileBenefitsInsuranceList" target="content">
<logic:equal name="confirm" value="true">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|292">
</logic:equal>
<logic:notEqual name="confirm" value="true">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|203">
</logic:notEqual>   
<bean:define id="requestParam"><%=PDJuvenileCaseConstants.JUVENILENUM_PARAM%>=<bean:write name="juvenileProfileHeader" property="juvenileNum"/></bean:define>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<logic:notEqual name="confirm" value="true">
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Benefits Details</td>
	  </tr>
	</logic:notEqual>
	<logic:equal name="confirm" value="true">
	  <tr>
	    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Benefits
				<bean:message key="prompt.information"/>
        <bean:message key="title.confirmation"/>
			</td>
	  </tr>
	</logic:equal>  
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>

<table width="98%" border="0">
	<logic:equal name="confirm" value="true">
   <tr>
     <td class="confirm">Benefits information has been updated.</td>
   </tr>
   <div class='spacer'></div>
	  <tr>
	    <td align="left">
	      <ul>
	        <li>Select the Return to Benefits button to return to the Benefits screen.</li>
	      </ul>
	    </td>
	  </tr>
	</logic:equal>  
	<logic:notEqual name="confirm" value="true">
	  <tr>
	    <td align="left">
	      <ul>
	        <li>Click on Add Benefits button to add additional benefit information.</li>
	        <li>Click on Add  Insurance button to add additional insurance information.</li>
	      </ul>
	    </td>
	  </tr>
	</logic:notEqual>  
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
											<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
										</tr>
										<tr>
											<td valign=top align=center>
												<tiles:insert page="/jsp/caseworkCommon/juvenileBenefitsDisplayTile.jsp" flush="true">
													<tiles:put name="tableTitle" value="Benefits Information"/>
													<tiles:put name="benefits" beanName="juvenileBenefitsInsuranceForm" beanProperty="juvBenefits"/>
													<logic:notEqual name="confirm" value="true">													
														<tiles:put name="addButton" value="true"/>
														<tiles:put name="buttonForward">/<msp:webapp/>jsp/juvTabInterview/juvenileBenefitsCreate.jsp</tiles:put>
													</logic:notEqual>														
												</tiles:insert>
												
												<div class='spacer'></div>
												<tiles:insert page="/jsp/caseworkCommon/juvenileInsuranceDisplayTile.jsp" flush="true">
													<tiles:put name="tableTitle" value="Insurance Information"/>
													<tiles:put name="insurances" beanName="juvenileBenefitsInsuranceForm" beanProperty="juvInsurances"/>
													<logic:notEqual name="confirm" value="true">
														<tiles:put name="addButton" value="true"/>
														<tiles:put name="buttonForward">/<msp:webapp/>jsp/juvTabInterview/juvenileInsuranceCreate.jsp</tiles:put>
													</logic:notEqual>
												</tiles:insert>
												<div class=spacer></div>
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
							<logic:equal name="confirm" value="true">
								<tr>
									<td>									
										<input type="button" id="benefitsReturn" data-href="/<msp:webapp/>displayJuvenileBenefitsInsuranceList.do?<bean:write name="requestParam" />" value="<bean:message key='button.returnToBenefits'/>"/>	    	    										
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual name="confirm" value="true">
								<tr>
									<td>
										<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>
									</td>
							</logic:notEqual>							
						</html:form>

						  <logic:notEqual name="confirm" value="true">
								<html:form action="/displayJuvenileMasterInformation" target="content">
									<td>
										<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</html:form>
							</tr>
    					</logic:notEqual>
						</table>
          </td>
     		</tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN BUTTON TABLE --%>
<div class='spacer'></div>
<table border="0" width="100%">
  <tr>
    <td align="center">
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

