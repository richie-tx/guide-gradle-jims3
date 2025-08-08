<!DOCTYPE HTML>
<%-- Used to display juvenile profile master details off Main Tab from Juvenile Profile Search --%>
<%--MODIFICATIONS --%>
<%-- 06/15/2005		LDeen	Create JSP --%>
<%-- 06/16/2005		LDeen	Convert Physical Characteristics section --%>

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
<title><bean:message key="title.heading"/> - juvenileProfileMasterUpdateConfirm.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<%--  <bean:parameter id="isSubmitted" name="submitted" value="false"/> --%>

<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">
  <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|7">
  <%-- BEGIN HEADING TABLE --%>
  <table width='100%'>
    <tr>
      <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.update"/>&nbsp;<bean:message key="prompt.summary"/></td>
      
    </tr>
  </table>
  <%-- END HEADING TABLE --%>
		
  <%-- BEGIN INSTRUCTION TABLE --%>
  <table width="98%" border="0">   
  	<tr>
  		<td>
			  <ul>
  				<li>View Physical Characteristics - click + to expand</li>
  				<li>View Physical Characteristics history by selecting an Entry Date in Physical Characteristics section then clicking Go.</li>
  		  </ul>
			</td>
  	</tr>
  </table>
  <%-- END INSTRUCTION TABLE --%>
</logic:notEqual>

<logic:notEqual name="juvenileProfileMainForm" property="action" value="UPDATE">	
  <logic:equal name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|8">
  	<%-- BEGIN HEADING TABLE --%>
		<table width='100%'>
		  <tr>
		    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.update"/> <bean:message key="prompt.confirmation"/>
				</td>
		  </tr>
			<tr>
				<td align=center class=confirm>Juvenile Profile Master successfully updated.</td>
			</tr>
		</table>
		<%-- END HEADING TABLE --%>
	</logic:equal>
</logic:notEqual>

<%-- Added --%>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<%-- End --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
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
  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="maintab"/>
  						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
  					</tiles:insert>				
  				</td>
  			</tr>
  			<tr>
 			  	<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
  			</tr>
  		</table>

  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  	  	<tr>
  				<td valign=top align=center>
        		<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
						<div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign=top>
  										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="profileInfo"/>
  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  											</tiles:insert>	
  										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
 									  	<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
  								  </tr>
  							  </table>
									<%-- END INTERVIEW INFO TABS INNER TABLE --%>

									<%-- BEGIN TAB BLUE BORDER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top align=center>

                        <%-- BEGIN MASTER INFO TABLE --%>
                    		<html:form action="submitJuvenileProfileMain.do" target="content">

                      		<div class='spacer'></div>
                      		<tiles:insert page="../caseworkCommon/juvProfileDetailsTile.jsp" flush="true"></tiles:insert>
                        	<%--  <input type="hidden" name="submitted" value="true"> --%>
                          <%-- END MASTER INFO TABLE --%>
  
                      		<%-- BEGIN BUTTON TABLE --%>
                          <div class=spacer></div>
                          <table border="0">
                           	<tr align="center">
                          		<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">
                          			<td align="center">
                          				<html:button property="org.apache.struts.taglib.html.BUTTON" styleId="updateSuccessBack"><bean:message key="button.back"></bean:message></html:button>&nbsp;
                          			</td>
                          			<td align="center">
                          				<html:submit property="submitAction" styleId="updateSuccessSubmit">
                          					<logic:equal name="juvenileProfileMainForm" property="action" value="UPDATE">
                          						<bean:message key="button.finish"></bean:message>
                          					</logic:equal>
                          					<logic:equal name="juvenileProfileMainForm" property="action" value="">
                          						<bean:message key="button.update"></bean:message>
                          					</logic:equal>
                          			  </html:submit>&nbsp;
                          			</td>
                          		</logic:notEqual>	
                        </html:form>
                   			<%--END SUBMIT ACTION FORM--%>

                        		<logic:notEqual name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">
                        			<%--BEGIN CANCEL ACTION FORM--%>
                       				<html:form action="/displayJuvenileMasterInformation.do"> 
                           			<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td>
                       				</html:form>
                        			<%--END CANCEL ACTION FORM--%> 
                        		</logic:notEqual>

                        		<logic:equal name="juvenileProfileMainForm" property="action" value="confirmUpdateSuccess">
                        			<td align="center">
                        				<%--BEGIN MainPage action FORM--%>
                        				<html:form action="/displayJuvenileMasterInformation" target="content"> 
                          				<html:submit><bean:message key="button.juvenileProfileMasterDetails"></bean:message></html:submit>
                        				</html:form>
                        			<%--END MainPage action ACTION FORM--%> 
                        			</td>
                        		</logic:equal>
                          </tr>
                        </table>
                        <%-- END BUTTON TABLE --%>
                     
                  		</td> 
				  				  </tr> 
									</table>
				 	
									<%-- END TAB BLUE BORDER TABLE --%>
								</td>
     					</tr> 
    				</table>
						<div class='spacer'></div>
            <%-- END INTERVIEW INFO TABS OUTER TABLE --%>

	        </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

<br />

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
<html:errors></html:errors>

</html:html>

