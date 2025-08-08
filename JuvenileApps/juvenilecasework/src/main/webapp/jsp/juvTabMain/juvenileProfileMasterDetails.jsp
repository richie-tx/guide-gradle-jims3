<!DOCTYPE HTML>
<%-- Used to display juvenile profile master details off Main Tab from Juvenile Profile Search --%>
<%--MODIFICATIONS --%>
<%-- 06/15/2005		LDeen	Create JSP --%>
<%-- 06/16/2005		LDeen	Convert Physical Characteristics section --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek     #73565 added age > 20 check (updatable) to Update button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - juvenileProfileMasterDetails.jsp</title>


</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.masterDetails"/></td>
	</tr>
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
	  <logic:equal name="juvenileProfileMainForm" property="action" value="confirmJIS">
	  <tr>
	    <td align='center' class='confirm'>JIS Information Details successfully created.</td>
	  </tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<logic:notEqual name="juvenileProfileMainForm" property="action" value="UPDATE">
	<table width="98%" border="0" align="center">   
		<tr>
			<td>
				<ul>
					<li>To view Photos, Physical Characteristics, or Aliases, click + to expand.</li>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_U%>'>
						<logic:equal name="juvenileProfileMainForm" property="hasCasefile" value="true">
							<logic:equal name="juvenileProfileMainForm" property="updatable" value="true">
								<li>Click Update button to edit master details.</li>
							</logic:equal>
						</logic:equal>
					</jims2:isAllowed>
				</ul>
			</td>
		</tr>
	</table>
</logic:notEqual>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>
<%--BEGIN FORM TAG--%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="maintab"/>
							<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
							<tiles:put name="juvenileProfileMainForm" beanName="juvenileProfileMainForm"/>	
						</tiles:insert>				
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  	  			<tr>
					<td valign='top' align='center'>
  					<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
	  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
	  						<tr>
	  							<td>
  								<%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
									<div class='spacer'></div>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="profileInfo"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
													<tiles:put name="juvenileProfileMainForm" beanName="juvenileProfileMainForm"/>	
												</tiles:insert>	
												<%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
										</tr>
									</table>  
									<%-- END INTERVIEW INFO TABS INNER TABLE --%>

  									<%-- BEGIN TAB BLUE BORDER TABLE --%>
  									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  										<tr>
  											<td valign='top' align='center'>
									<%-- BEGIN PHYSICAL CHARACTERISTICS TABLE INSERT --%>
												<logic:notEqual name="juvenileProfileMainForm" property="action" value="UPDATE">
													<tiles:insert page="../juvTabMain/juvenilePhysicalCharacteristicDetails.jsp" flush="true">
														<tiles:put name="juvenileProfileMainForm" beanName="juvenileProfileMainForm"/>	
													</tiles:insert>
												</logic:notEqual>
									<%-- END PHYSICAL CHARACTERISTICS TABLE INSERT --%>
  												
									<%-- BEGIN MASTER INFO TABLE --%>
												<html:form action="displayJuvenileProfileUpdate.do" target="content">
													<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|51">
													<tiles:insert page="../caseworkCommon/juvProfileDetailsTile.jsp" flush="true"></tiles:insert>
      												
                        	  <%-- BEGIN BUTTON TABLE --%>
													<div class='spacer'></div>
													<table border="0" align="center">
                				           				<tr>
                              								<td align="center">
                              									<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
                            										<bean:message key="button.back"></bean:message>
                            									</html:button>&nbsp;
                            								</td>
                            								<td align="center">
                            									<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_U%>'>
                              										<logic:equal name="juvenileProfileMainForm" property="hasCasefile" value="true">
                              		  									<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
                              		  									<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
                               			  									<html:submit property="submitAction" onclick="spinner()"><bean:message key="button.update"></bean:message></html:submit>&nbsp;
                             	  	  									</logic:notEqual>
                             	  	  									<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
																		<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
																			<html:submit property="submitAction" onclick="spinner()"><bean:message key="button.update"></bean:message></html:submit>&nbsp;
																		</jims2:isAllowed>
																		</logic:equal>	
                             	  	  									</logic:equal>
                              	  									</logic:equal>
                           		 								</jims2:isAllowed>
                            								</td>
                          						</html:form>		
                          <%--END SUBMIT ACTION FORM--%>
                          
  							<%--BEGIN CANCEL ACTION FORM--%>
                         						 			<html:form action="/displayJuvenileProfileSearch.do">
                           										<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
                          									</html:form>
                          <%--END CANCEL ACTION FORM--%> 
                      									</tr>
                      								</table>
												<div class='spacer'></div>
                      <%-- END BUTTON TABLE --%>
                      						</td> 
        								</tr> 
    								</table>
    							<%-- END TAB BLUE BORDER TABLE --%>
								</td>
							</tr> 
						</table>
						<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<%-- END DETAIL TABLE --%>
<div class='spacer'></div>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
