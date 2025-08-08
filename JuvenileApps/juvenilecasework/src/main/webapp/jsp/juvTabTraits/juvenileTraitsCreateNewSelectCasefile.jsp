<!DOCTYPE HTML>
<%-- Used to display juvenile traits details off Traits Tab in both Casefile and Juvenile Profile --%>
<%--MODIFICATIONS --%>
<%-- 06/10/2005		DWilliamson	Create Traits tile --%>
<%-- 06/15/2012		CShimek		#73782 Added onload function to preselect single supervision --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>


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

<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - juvenileTraitsCreateNewSelectCasefile.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTraits.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/dualstatus.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<html:form action="/displayJuvenileProfileCreateTraits" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|220">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.select" /> <bean:message key="title.casefileList" /></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
  	  <ul>
        <li>Click on hyperlinked supervision number to view casefile details.</li>
        <li>Select casefile then click next button to add Gang Traits. </li>
  	  </ul>
  	</td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
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

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
			  <tr>
				  <td valign="top">
  					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<logic:empty name="juvenileTraitsForm" property="categoryName">
  							<tiles:put name="tabid" value="traitstab"/>
  						</logic:empty>
  						<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
  							<tiles:put name="tabid" value="interviewinfotab"/>
  						</logic:notEmpty>		
  						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  					</tiles:insert>				
				  </td>
			  </tr>
			  <tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
			  </tr>
		  </table>

		  <table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableGreen">
  			<tr>
  				<td valign="top" align="center">
<%-- BEGIN TABLE --%>
			<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
            <div class='spacer'></div>
  					<table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
			</logic:notEmpty>								
							<logic:notEmpty name="juvenileTraitsForm" property="categoryName">
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign='top'>
    										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  											</tiles:insert>	
    										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
									</tr>
								</table>
							</logic:notEmpty>
							
							<logic:equal name="juvenileTraitsForm" property="categoryName" value="ABUSE"> 
							<div class=spacer></div>
							<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				              <tr>
				                <td valign="top">
				                  <tiles:insert page="../caseworkCommon/abuseDualTabs.jsp" flush="true">
				                  	<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
				                  	<%-- <tiles:put name="tabid" beanName="juvenileAbuseForm" beanProperty="subCategory"/> --%>
				                   <%-- <tiles:put name="tabid" beanName="juvenileDualStatusForm" beanProperty="categoryName" /> --%>
				                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
				                  </tiles:insert>
				                </td>
				              </tr>
				              <tr>
				                <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				              </tr>
				            </table>
				            </logic:equal>
				            <logic:equal name="juvenileTraitsForm" property="categoryName" value="DUALSTATUS"> 
							<div class=spacer></div>
							<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				              <tr>
				                <td valign="top">
				                  <tiles:insert page="../caseworkCommon/abuseDualTabs.jsp" flush="true">
				                  	<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
				                  	<%-- <tiles:put name="tabid" beanName="juvenileAbuseForm" beanProperty="subCategory"/> --%>
				                   <%-- <tiles:put name="tabid" beanName="juvenileDualStatusForm" beanProperty="categoryName" /> --%>
				                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
				                  </tiles:insert>
				                </td>
				              </tr>
				              <tr>
				                <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				              </tr>
				            </table>
				            </logic:equal>
				            <%-- </logic:notEmpty> --%>
							<div class='spacer'></div>
							<%-- BEGIN casefile TABLE --%>
							<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.add" /> <bean:message key="prompt.traits" /> - <bean:message key="prompt.select" /> <bean:message key="prompt.casefile" /></td>
								</tr>
								<tr>
									<td>
										<table cellpadding='2' cellspacing='1' width='100%'>
											<tr bgcolor='#cccccc'>
												<td width='1%'></td>
												<td class=subHead align="left"><bean:message key="prompt.supervision" />&nbsp;#</td>
												<td class=subHead align="left"><bean:message key="prompt.sequence" />&nbsp;#
												<jims:sortResults beanName="juvenileTraitsForm" results="casefilesAbuse" primaryPropSort="sequenceNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="DESC" sortId="1" /></td>
												<td class=subHead align="left"><bean:message key="prompt.probationOfficer" />&nbsp;
																  <bean:message key="prompt.name" /></td>
												<td class=subHead align="left"><bean:message key="prompt.supervision" />&nbsp;
																  <bean:message key="prompt.type" /></td>
												<td class=subHead align="left"><bean:message key="prompt.supervision" />&nbsp;<bean:message key="prompt.endDate" /></td>
												<td class=subHead align="left"><bean:message key="prompt.caseStatus" /></td>
											</tr>
        									<% 
        										//String casefileUrl = naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=" + request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM);
        									%> 
        									<logic:notEmpty name="juvenileTraitsForm" property="casefilesAbuse">
        										<logic:iterate id="traits" name="juvenileTraitsForm" property="casefilesAbuse" indexId="index">
        											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">        											
        											<jims2:if name="traits" property="caseStatus" value="ACTIVE" op="equal">
        											<jims2:then>
       													<td width='1%'>
       														<html:radio idName="traits" property="supervisionNum" value="supervisionNum"/>
       													</td>
       												</jims2:then>
       												<jims2:else>
       													<td width='1%'>
       													</td>
       												</jims2:else>
       												</jims2:if>
       													<td align="left"><a href="javascript:openWindow('/JuvenileCasework/displayJuvenileProfileCasefileDetails.do?casefileId=<bean:write name="traits" property="supervisionNum"/>')">
       															<bean:write name="traits" property="supervisionNum"/>
       														</a>
       													</td>
       													<td align="left"><bean:write name="traits" property="sequenceNum"/></td>
       													<td align="left"><bean:write name="traits" property="officerFullName"/></td>
       													<td align="left"><bean:write name="traits" property="supervisionType"/></td>
       													<td align="left"><bean:write name="traits" property="supervisionEndDate" format="MM/dd/yyyy" /></td>
       													<td align="left"><bean:write name="traits" property="caseStatus"/></td>
       												</tr>
        										</logic:iterate>
        									</logic:notEmpty>
        									<logic:empty name="juvenileTraitsForm" property="casefiles">
        										<tr><td colspan="5" align="left">There are no active casefiles associated with this juvenile profile.</td></tr>
        									</logic:empty>
  									</table>
  								</td>
  							</tr>
  	        	  </table>
         			<%-- END casefile TABLE --%>
                <div class="spacer"></div>
                <%-- BEGIN BUTTON TABLE --%>
                <table border="0" width="100%">
                  <tr>
                    <td align="center">
<%--                 		<html:button property="back" onclick="history.go(-1);"><bean:message key="button.back"/></html:button>&nbsp; --%>
                        <input type="button" value="Back" name="return" id="btnGangsTabBack">
                 		<logic:notEmpty name="juvenileTraitsForm" property="casefilesAbuse">
               	  		  	<html:submit property="submitAction" styleId="selectCasefileNext">
               	  	  			<bean:message key="button.next"/>
               	  	  		</html:submit>&nbsp;
                  	  	</logic:notEmpty>
                  		<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                    </td>
                  </tr>
                </table>
                <%-- END BUTTON TABLE --%>
<logic:notEmpty name="juvenileTraitsForm" property="categoryName">					          
				          </td>
			          </tr>
		         </table>
</logic:notEmpty>		         
            <div class="spacer"></div>
 	        </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div class="spacer"></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>