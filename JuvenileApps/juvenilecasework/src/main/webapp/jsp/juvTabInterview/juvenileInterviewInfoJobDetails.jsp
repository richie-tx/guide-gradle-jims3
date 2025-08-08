<!DOCTYPE HTML>
<%-- User selects the "Entry Date" hyperlink on one of the row --%>
<%--MODIFICATIONS --%>
<%-- 06/13/2005	Hien Rodriguez	Create JSP --%>
<%-- 07/20/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 09/02/2015 R Capestani    	#27637  Adapt MJCW and Warrants to IE10 and 11 (Juvenile Profile Jobs UI) --%>

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
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileInterviewInfoJobDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileJobsCreate" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|200">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Job Details</td>
		</tr>
</table>    	
<%-- END INSTRUCTION TABLE --%>


<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<%-- BEGIN DETAIL TABLE --%>  
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign="top">
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
              <tiles:put name="tabid" value="main"/>
              <tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
            </tiles:insert>				
          </td>
        </tr>
        <tr>
          <td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
        </tr>
      </table>
  			
      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        <tr>
          <td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
        </tr>
        <tr>
          <td valign="top" align="center">
      
            <table width='98%' border="0" cellpadding="0" cellspacing="0" >
      				<tr>
      					<td valign="top">
      						<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
      							<tiles:put name="tabid" value="job"/>
      							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
      						</tiles:insert>	
      					</td>
      				</tr>
      				<tr>
      			  	<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
      			  </tr>
      			</table>
			
      			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
      				<tr>
      					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
      				</tr>
      				<tr>
      					<td valign="top" align="center">																		
              		<%-- BEGIN JOB TABLE --%>																		
      						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">						 
      							<tr class="detailHead">
      								<td class="detailHead" colspan="2"><bean:message key="prompt.juvenileEmploymentInfo" /></td>																
      							</tr>
      							<tr>
     							    <td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.entryDate" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="entryDate" /></td>
      							</tr>

      							<tr>
     							    <td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.employmentStatus" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="employmentStatusDesc" /></td>
      							</tr>
      							<tr>									
      								<td class="formDeLabel"><bean:message key="prompt.placeEmployed" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="employmentPlace" /></td>			
      							</tr>
      
       							<tr>									
                      <td class="formDeLabel"><bean:message key="prompt.salary" /></td>
                      <td class="formDe">$<bean:write name="juvenileJobForm" property="salary" formatKey="currency.format"/></td>
                    </tr>
                    <tr>								
                      <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.salary" /> Rate</td>
                      <td class="formDe"><bean:write name="juvenileJobForm" property="salaryRateDesc" /></td>
                    </tr>	
      
      							<tr>
      								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.supervisorName" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="supervisorLastName" /><logic:notEqual name="juvenileJobForm" property="supervisorLastName" value=""><logic:notEqual name="juvenileJobForm" property="supervisorFirstName" value="">,</logic:notEqual></logic:notEqual> 
								                 <bean:write name="juvenileJobForm" property="supervisorFirstName" />
								                 <bean:write name="juvenileJobForm" property="supervisorMiddleName" /> </td>
      							</tr>
      							<tr>				
      								<td class="formDeLabel"><bean:message key="prompt.supervisorFamilyMember#" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="supervisorFamilyNum" />
      									<a href="/<msp:webapp/>displayFamilyMber.do?familyMemberNum=<bean:write name='juvenileJobForm' property='supervisorFamilyNum'/>"></a>
      								</td>
      							</tr>
      							<tr>
      								<td class="formDeLabel"><bean:message key="prompt.workHours" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="workHours" /></td>			
      							</tr>
      							<tr>
      								<td class="formDeLabel"><bean:message key="prompt.jobDescription" /></td>
      								<td class="formDe"><bean:write name="juvenileJobForm" property="jobDescription" /></td>				    
      							</tr>
                  </table>
                  <%-- END JOB TABLE --%>	
                  <%-- BEGIN BUTTON TABLE --%>
                  <div class="spacer"></div>
		          <table align='center'>	
		          	<tr>		
		           		<td>   		 		
		           			<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>
		           		</td>
		           	</tr>
		          </table>
		          <div class="spacer"></div>
		          <%-- END BUTTON TABLE --%>
                </td>
              </tr>
            </table>
            <div class="spacer"></div><%-- END DETAIL TABLE --%>
           </td>
        </tr>
      </table>
      <div class="spacer"></div>
    </td>
  </tr>
</table>

<%-- END FORM --%>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
