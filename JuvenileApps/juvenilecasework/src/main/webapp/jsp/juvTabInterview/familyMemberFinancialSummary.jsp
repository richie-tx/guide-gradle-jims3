<!DOCTYPE HTML>
<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
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
<html:base />

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<title><bean:message key="title.heading" /> - familyMemberFinancialSummary.jsp</title>
</head>
<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayManageFamilyFinancial">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|261">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/>  <bean:message key="prompt.member"/> 
 		 <bean:message key="prompt.financial"/> <bean:message key="prompt.info"/> 
    </td>
  </tr>
</table>

<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>

<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
  		<ul>		
  			<li>Press Back to return to previous screen </li>
  		</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%>
<div class="spacer"></div>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="100%">
	<tr>
		<td>
		  <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader" />
			</tiles:insert> 
		</td>
	</tr>
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE  Main Table Begin --%>
<div class="spacer"></div>
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
					<td bgcolor="#33cc66"><img src="../../images/spacer.gif" width="5"></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">

            <div class="spacer"></div>							
    				<table width="98%" border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td valign="top">
								  <tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="familyFinancial" />
    								<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
    							</tiles:insert>
  							</td>
  						</tr>
  						<tr>
  							<td bgcolor="#6699FF"><img src="../../images/spacer.gif" width="5"></td>
  						</tr>
    				</table>
				
    				<%-- end red tabs --%> <%--begin red outer border --%>
    				<table width='98%' cellspacing="0" cellpadding="2" border="0" class="borderTableBlue">
      				<tr>
      					<%-- Be sure to include the autoTab.js --%>
      					<td valign="top" align="center">
      					   <div class="spacer"></div>
                    <table width='98%' cellspacing="0" cellpadding="2" border="0" class="borderTableBlue">
                      <tr>
                        <td align="left" class="detailHead" colspan="4"> Family Member Financial Information - <bean:write name="juvenileMemberForm" property="name.formattedName"/></td>
                      </tr>
                      <logic:empty name="juvenileMemberForm" property="guardianList">
	                      <tr>
	                        <td align="left" class="class=borderTableGrey" colspan="4"> No Family Financial Information </td>
	                      </tr>
                      </logic:empty>

                      <logic:notEmpty name="juvenileMemberForm" property="guardianList">
	                      <tr>
	                        <td colspan="4">
                        
		                        <%--BEGIN Member 1 Inner Table --%>
	                          <table width='100%' cellspacing="1">
	                            <tr bgcolor='#cccccc'>
	                              <td valign="top" class="subHead">Entry Date</td>
	                              <td valign="top" class="subHead">Family Number</td>
	                              <td valign="top" class="subHead">Juvenile Name</td>
	                              <td valign="top" class="subHead">Relationship</td>
	                            </tr>
	                            
                 							<logic:iterate indexId="index" id="guardians" name="juvenileMemberForm" property="guardianList">
                                <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
                    							<td>
                    								<a href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=GO&selectedValue=<bean:write name='guardians' property='financialId'/>&relationToJuv=<bean:write name="guardians" property="relationshipToJuv"/>"> 
                    								<bean:write name='guardians' property='entryDate' formatKey="date.format.mmddyyyy"/></a>
                    							</td>
                    							<td><bean:write name="guardians" property="constellationMemberId"/></td>
                    							<td><bean:write name="guardians" property="juvenileName.formattedName"/></td>
                    							<td><bean:write name="guardians" property="relationshipToJuv"/></td>
  															</tr>
                 							</logic:iterate>
	                          </table>
	                          <%--END Member 1 Inner Table --%> 
	  											</td>
	  										</tr>
											</logic:notEmpty>
                    </table>
										<%-- End Family constellation List TABLE -Table 4 End--%> 
				  					<%-- BEGIN BUTTON TABLE --%>
				  					<div class="spacer"></div>
				  					<table border="0" width="100%">
			  							<tr>
			  								<td align="center">
			  									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
			  								</td>
			  							</tr>
				  					</table>
				  					<%-- END BUTTON TABLE --%>
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



</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
