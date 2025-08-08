<!DOCTYPE HTML>
<%-- User selects the "Add New Alias" button from Juvenile Profile Master Details Main tab --%>
<%--MODIFICATIONS --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nest"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>




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
<title><bean:message key="title.heading"/> - juvenileScarsAndTatoos.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <logic:equal name="juvenileAliasInfoForm" property="action" value="delete">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Delete Alias Summary</td>
    	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|320">
  	</tr>
  </logic:equal>

  <logic:equal name="juvenileAliasInfoForm" property="action" value="summary">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Create Alias Summary</td>
    	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|303">
  	</tr>
  </logic:equal>
  <logic:equal name="juvenileAliasInfoForm" property="action" value="confirm">
   	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Create Alias Confirmation</td>
    	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|333">
  	</tr>
  	<tr>
		<td class="confirm">Alias name successfully created.</td>
	</tr>
	</logic:equal>
	<logic:equal name="juvenileAliasInfoForm" property="action" value="deleteSuccess">
   	<tr>
    	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Delete Alias Confirmation</td>
    	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|337">
  	</tr>
  	<tr>
		<td class="confirm">Alias name successfully deleted.</td>
	</tr>
	</logic:equal>  	
	  	
</table>
<%-- END HEADING TABLE --%>

<html:form action="/submitJuvenileAliasInfoCreate" target="content">
<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
    <logic:equal name="juvenileAliasInfoForm" property="action" value="summary">
      <ul>
        <li>Review information, then select the Finish button to view the Confirmation screen.</li>
        <li>Select the Back button to change any information.</li>
      </ul>
     </logic:equal>
	 <logic:equal name="juvenileAliasInfoForm" property="action" value="confirm">
      <ul>
        <li>Select the Juvenile Profile Master Details button.</li>
      </ul>
     </logic:equal>
     <logic:equal name="juvenileAliasInfoForm" property="action" value="deleteSuccess">
      <ul>
        <li>Select the Juvenile Profile Master Details button.</li>
      </ul>
     </logic:equal>
     
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- BEGIN DETAIL TABLE --%>  
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
   	<td valign="top">
   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
     		<tr>
     			<td valign="top">
       			<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	       			<tiles:put name="tabid" value="maintab"/>
	       			<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
       			</tiles:insert>	
     			</td>
	   		</tr>
	   		<tr>
     			<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
	   		</tr>
	 		</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">	
						<%-- BEGIN ALIAS INFO TABS OUTER TABLE --%>
						<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<%-- BEGIN ALIAS INFO TABS INNER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="profileInfo"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
												<%--tabs end--%>
											</td>
										</tr>
										<tr>
									  	<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
								  	</tr>
							  	</table>

									<%-- END ALIAS INFO TABS INNER TABLE --%>
								  <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
      							<tr>
       								<td valign="top" align="center">

          							<!-- BEGIN ALIAS INFO TABLE -->
												<div class='spacer'></div>
           							<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	       									<tr>
	       										<td class="detailHead">Alias Information</td>
	       									</tr>
				             			<tr>
	       										<td valign="top">
		 												<table width='100%' cellpadding='2' cellspacing='1' border="0">
	           									<tr> 
	             									<td class="formDeLabel" valign="top" nowrap="nowrap">Alias Name</td>
	             								<logic:equal name="juvenileAliasInfoForm" property="action" value="summary"> 
	             									<td valign="top" class="formDe"><bean:write name="juvenileAliasInfoForm" property="lastName" />,&nbsp;<bean:write name="juvenileAliasInfoForm" property="firstName" />&nbsp;<bean:write name="juvenileAliasInfoForm" property="middleName" /></td>
	             								</logic:equal> 
	             								<logic:equal name="juvenileAliasInfoForm" property="action" value="confirm"> 
	             									<td valign="top" class="formDe"><bean:write name="juvenileAliasInfoForm" property="lastName" />,&nbsp;<bean:write name="juvenileAliasInfoForm" property="firstName" />&nbsp;<bean:write name="juvenileAliasInfoForm" property="middleName" /></td>
	             								</logic:equal> 
	             								
	             								<logic:equal name="juvenileAliasInfoForm" property="action" value="delete">
	             									<td valign="top" class="formDe"><bean:write name="juvenileAliasInfoForm" property="aliasName" /></td>
	             								</logic:equal>
	             								<logic:equal name="juvenileAliasInfoForm" property="action" value="deleteSuccess">
	             									<td valign="top" class="formDe"><bean:write name="juvenileAliasInfoForm" property="aliasName" /></td>
	             								</logic:equal>
	           									</tr> 
		 													<tr>
		 														<td nowrap="nowrap" class="formDeLabel">Type</td>
		 													<logic:equal name="juvenileAliasInfoForm" property="action" value="summary">
		 														<td class="formDe"><bean:write name="juvenileAliasInfoForm" property="juvenileTypeDescription" /></td>
		 													</logic:equal>
		 													<logic:equal name="juvenileAliasInfoForm" property="action" value="confirm">
		 														<td class="formDe"><bean:write name="juvenileAliasInfoForm" property="juvenileTypeDescription" /></td>
		 													</logic:equal>
		 													
		 													<logic:equal name="juvenileAliasInfoForm" property="action" value="delete">
		 														<td class="formDe"><bean:write name="juvenileAliasInfoForm" property="juvenileType" /></td>
		 													</logic:equal>
		 													<logic:equal name="juvenileAliasInfoForm" property="action" value="deleteSuccess">
		 														<td class="formDe"><bean:write name="juvenileAliasInfoForm" property="juvenileType" /></td>
		 													</logic:equal>
		 													
		 													</tr>
		 													<tr>
		 														<td width="1%" nowrap="nowrap" class="formDeLabel">Notes</td>
		 														<td class="formDe"><bean:write name="juvenileAliasInfoForm" property="notes" /></td>
		 													</tr>
		 												</table>
		 											</td>
		 										</tr>
		 									</table>

                      <!-- BEGIN BUTTON TABLE -->
                 			<div class="spacer"></div>
                 			<table border="0" width="100%">
                  			<logic:equal name="juvenileAliasInfoForm" property="action" value="summary">
                    			<tr>
                     				<td align="center">
															<html:button property="button.back" styleId="aliasBackSummary">
																<bean:message key="button.back"></bean:message>
													  	</html:button>				
															<html:submit property="submitAction">				
																<bean:message key="button.finish"></bean:message>
															</html:submit>
															<html:submit property="submitAction">
																<bean:message key="button.cancel"></bean:message>
															</html:submit>
                     				</td>
			                    </tr>
				                </logic:equal>
                  			<logic:equal name="juvenileAliasInfoForm" property="action" value="delete">
                    			<tr>
                     				<td align="center">
															<html:button property="button.back" styleId="aliasBackDelete">
																<bean:message key="button.back"></bean:message>
													  	</html:button>				
															<html:submit property="submitAction">				
																<bean:message key="button.finish"></bean:message>
															</html:submit>
															<html:submit property="submitAction">
																<bean:message key="button.cancel"></bean:message>
															</html:submit>
                     				</td>
			                    </tr>
				                </logic:equal>
				                

				                <logic:equal name="juvenileAliasInfoForm" property="action" value="confirm">
			                    <tr>
       											<td align="center">
      												<input type="hidden" name="main" value="main"/>
															<html:submit property="submitAction">
																<bean:message key="button.juvenileProfileMasterDetails"></bean:message>
															</html:submit>
			                      </td>
       										</tr>
       							</logic:equal>
       							

				                <logic:equal name="juvenileAliasInfoForm" property="action" value="deleteSuccess">
			                    <tr>
         										<td align="center">
         											<input type="hidden" name="main" value="main"/>
															<html:submit property="submitAction">
																<bean:message key="button.juvenileProfileMasterDetails"></bean:message>
															</html:submit>
			                      </td>
         								  </tr>
       									</logic:equal>
	                  	</table>
	                  	<div class="spacer"></div>
      		          	<!-- END BUTTON TABLE -->
   										</td>
     								</tr>
   								</table>
   								<div class="spacer"></div>
   							</td>
 							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<%-- END DETAIL TABLE --%>

<%-- END FORM --%>
 </html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:html>
