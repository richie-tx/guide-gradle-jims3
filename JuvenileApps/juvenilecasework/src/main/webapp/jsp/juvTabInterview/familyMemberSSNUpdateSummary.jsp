<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page and selects to update SSn--%>
<%--MODIFICATIONS --%>
<%-- 09/11/2017	 Uma Gopinath	Create JSP --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



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
<html:base />

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="ssnUpdateForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/PopupWindow.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvTabInterview/familyMemberCreate.js"></script>
<title><bean:message key="title.heading" /> - familyMemberSSNUpdateSummary.jsp</title>
</head>
<%--END HEADER TAG--%>


<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/submitFamilyMemberSSNUpdate">

<logic:equal name="juvenileMemberForm" property="action" value="createMember">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|234">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="updateMember">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|245">
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family" /> <bean:message key="prompt.member" />
		 - <bean:message key="prompt.SSN" /> <bean:message key="prompt.update" /> 
		  <logic:equal name="juvenileMemberForm" property="action" value="update">
		  	<bean:message key="title.summary"/>
		 </logic:equal>
		 <logic:equal name="juvenileMemberForm" property="action" value="confirm">
      		<bean:message key="title.confirmation"/>
      		<tr>
				<td align="center" class="confirm">SSN successfully updated.</td>
			</tr>
      	</logic:equal>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- begin instruction table --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
			 <logic:notEqual name="juvenileMemberForm" property="action" value="confirm">				
				<li>Review the information.</li>
    			<li>Select the <b>Finish</b> button to save SSN information.</li>
    			<li>Select <b>Back</b> button to return to previous page.</li>		
			</logic:notEqual>
			 <logic:equal name="juvenileMemberForm" property="action" value="confirm">
			 
			 </logic:equal>
			
			</ul>
		</td>
	</tr>	
</table>
<%-- end instruction table --%>

<%-- begin detail table --%>
<table align="center" cellpadding='1' cellspacing='0' border='0' width='100%'>
	<tr>
    	<td>
  			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
      			<tiles:put name="headerType" value="profileheader" />
      		</tiles:insert> <%--header info end--%>
		</td>
 	</tr>
</table>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td><%-- begin green tabs (1st row) --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert> 
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66" height="5"></td>
				</tr>
			</table>
			<%-- end green tabs --%> 

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					
					  <%-- begin red tabs (3rd row) --%>
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top">
 									  <tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
  										<tiles:put name="tabid" value="MemberInformation" />
  										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  									</tiles:insert> 
									</td>
								</tr>
								<tr>
									<td bgcolor="#6699FF" height="5"></td>
								</tr>
						</table>
						<%-- end red tabs --%> 

						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					
								<tr>
									<td valign='top' align='center'>

										<div class='spacer'></div>
 											<table cellpadding='2' cellspacing='1' border='0' width="98%" class="borderTableBlue">
											<tr>
												<td valign='top' class='detailHead' colspan="4"><bean:message key="prompt.family" />
																<bean:message key="prompt.member" />
																<bean:message key="prompt.info" /> - SSN Update</td>
											</tr>
											
											<tr>
												<td class='formDeLabel' valign="top" nowrap='nowrap' width='1%'><bean:message key="prompt.SSN" /></td>
												<td class='formDe' valign="top">
													<bean:write name="juvenileMemberForm" property="completeSSN.SSN1"/>- 
													<bean:write name="juvenileMemberForm" property="completeSSN.SSN2"/>- 
													<bean:write name="juvenileMemberForm" property="completeSSN.SSN3"/>															
												</td>														
											</tr>							
											
												</table>

												<div class='spacer'></div>
										<%-- BEGIN BUTTON TABLE --%>
 											<table width="98%" border="0" align="center">
											<tr>
												<td align="center">
													 <logic:equal name="juvenileMemberForm" property="action" value="update">		
														<html:submit property="submitAction" styleId="masterUpdateBack"><bean:message key="button.back" /></html:submit>&nbsp;
														<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>&nbsp;
														<html:submit property="submitAction"><bean:message key='button.cancel' /></html:submit>
													</logic:equal>
														
													 <logic:equal name="juvenileMemberForm" property="action" value="confirm">
													 	<html:submit property="submitAction"><bean:message key="button.returnToFamilyMemberUpdate" /></html:submit>
													 </logic:equal>
												</td>
											</tr>
       									</table>
										<%-- END BUTTON TABLE --%>        									
       									<div class='spacer'></div>
        							</td>
  		    					</tr>
						</table>
		    			<div class='spacer'></div>
						<%-- end outer red --%>
					</td>
				</tr>
			</table>
		<%-- end outer green --%>
		</td>
	</tr>
</table>
<div class='spacer'></div>
<logic:equal name="juvenileMemberSearchForm" property="popUp" value="true">
	<html:hidden name="juvenileMemberSearchForm" property="popUp" styleId="popUp" value="true"/>
</logic:equal>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>