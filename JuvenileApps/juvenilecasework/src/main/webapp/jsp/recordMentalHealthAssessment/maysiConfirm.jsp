<!DOCTYPE HTML>
<%--This JSP will be used to create, update and create additional addresses.--%>
<%--MODIFICATIONS --%>
<%-- DApte	  05/16/2004  Create JSP --%>
<%-- CShimek  02/28/2006  Reformatted page to match input and replaced hardcoded prompt with bean writes --%>
<%-- CShimek  09/26/2012  #74333 revised Was the MAYSI administered prompt to ARP entry with new value --%>
<%-- CShimek  07/26/2013  #75802 Added Date of Scheduled Office Interview display --%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
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
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<html:base />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>
<title><bean:message key="title.heading" /> - maysiConfirm.jsp</title>
</head>

<%--END HEADER TAG--%>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|188">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <logic:equal name="mentalHealthForm" property="action" value="update"> Update</logic:equal>
    <logic:notEqual name="mentalHealthForm" property="action" value="update"> Create </logic:notEqual> <bean:message key="title.mentalAssessment"/>&nbsp;<bean:message key="prompt.confirmation"/></td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class=spacer></div>
<table width="98%" border="0">
   <tr>
   	<td align=center class=confirm>Mental Health Assessment completed and Assessment Sheet generated. </td>
   </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<!-- BEGIN INSTRUCTION TABLE -->
<div class=spacer></div>
<table width="98%" border="0">
  <tr>
    <td align="left">
      <ul>
        <li>Select MAYSI List button to return to the Mental Health Assessment List screen.</li>
      </ul>	
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->


<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" > 
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert> 
<%-- END JUVENILE HEADER INCLUDE  --%>

<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  	<td valign='top'>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0">
    		<tr>
    			<td valign=top>
    				<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
      				<tiles:put name="tabid" value="interviewinfotab"/>
      				<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
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
					
  				  <div class=spacer></div>
            <table width='98%' border="0" cellpadding="0" cellspacing="0" >
            	<tr>
            		<td valign='top'>
            			<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
            				<tiles:put name="tabid" value="mentalhealthtab"/>
            				<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
            			</tiles:insert>	
            		</td>
            	</tr>
            	<tr>
            		<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
            	</tr>
            </table>
      
            <%--  Insert here --%>
           	<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
              <tr>
        		  <td valign='top' align='center'>
					  <div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
									<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
										<tiles:put name="tabid" value="maysi"/>
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
					  			<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				  			</tr>
						</table>   	

            			<table width='98%' align=center border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
            				<tr>
            					<td valign="top" align="center">
            
            						<%-- BEGIN Program Referral History TABLE --%>
            						<html:form action="/requestMAYSIInformationReport.do" target="content">
            					<%--	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|187"> --%>
												<div class=spacer></div> 
            						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            							<tr>
            								<td class="detailHead"><bean:message key="prompt.mentalHealthAssessment"/></td>		
            							</tr>
            							<tr>
            								<td align='center'>
              									<table width='100%' cellpadding='4' cellspacing='1'>
            										<tr>
                  										<td class="formDeLabel"><bean:message key="prompt.race"/></td>
                  										<td class="formDe"><bean:write name="mentalHealthForm" property="race"/></td>
                  										<td class="formDeLabel" width="20%"><bean:message key="prompt.hispanic"/>?</td>
                  										<td class="formDe" width="30%"><bean:write name="mentalHealthForm" property="ethnicityHispanic"/></td>
                  									</tr>
                  									<tr>
                  										<td class="formDeLabel"><bean:message key="prompt.sex"/></td>
                  										<td class="formDe" colspan='3'><bean:write name="mentalHealthForm" property="sex"/></td>
                  									</tr>
            										<tr>					
            											<td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.wasTheMaysiAdministered"/></td>
            											<td class="formDe"  >
            												<elogic:switch name="mentalHealthForm" property="administer">
            													<elogic:case value="true">YES</elogic:case>
            													<elogic:default>NO</elogic:default>
            												</elogic:switch>
            											</td>
            											<logic:equal name="mentalHealthForm" property="otherReasonNotDone" value="">
	            											<td class="formDe"  ></td>
	            											<td class="formDe"  ></td>
            											</logic:equal>
            											
            											<logic:notEqual name="mentalHealthForm" property="otherReasonNotDone" value="">

							            					<td valign="top" class="formDeLabel" width='1%'>Detailed reason for other</td>
							            					<td valign="top" class="formDe">
							           								<bean:write name="mentalHealthForm" property="otherReasonNotDone"/>
						           							</td>
						            						

						            					</logic:notEqual>
            										</tr>
            										<logic:equal name="mentalHealthForm" property="administer" value="false">
            											<tr>					
            												<td class="formDeLabel">Reason MAYSI was not administered</td>
            												<td class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="reasonNotDone"/></td>
            											</tr>
            											<logic:equal name="mentalHealthForm" property="reasonNotDoneId" value="OIP">
	            											<tr>					
	            												<td class=formDeLabel><bean:message key="prompt.dateOfScheduledOfficeInterview"/></td>
	            												<td class=formDe colspan='3'><bean:write name="mentalHealthForm" property="scheduledOffIntDateStr"/></td>
	            											</tr>	
            											</logic:equal>
            										</logic:equal>
            										<tr>
            											<td class="formDeLabel"><bean:message key="prompt.referralNumber"/></td>
            											<td class=formDe colspan="3"><bean:write name="mentalHealthForm" property="referralNum"/></td>
            										</tr>
            										<tr>	
            											<td class="formDeLabel"><bean:message key="prompt.location"/> Unit</td>
            											<td class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="locationUnit"/></td>
            										</tr>
            										<tr>					
            											<td class="formDeLabel"><bean:message key="prompt.howLongHasYouthBeenHere"/></td>
            											<td class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="lengthOfStay"/></td>
            										</tr>
            										<tr>	
            											<td class="formDeLabel" ><bean:message key="prompt.typeOfFacility"/></td>
            											<td class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="facilityType"/></td>
            										</tr>
            										<tr>
            											<td class="formDeLabel"><bean:message key="prompt.hasYouthTakenMAYSIBefore"/></td>
            											<elogic:switch name="mentalHealthForm" property="hasPreviousMAYSI">
            												<elogic:case value="true"><td class="formDe" colspan="3">YES</td></elogic:case>
            												<elogic:default><td class="formDe" colspan="3">NO</td></elogic:default>
            											</elogic:switch>
            										</tr>
            										<tr>				
            											<td class="formDeLabel"><bean:message key="prompt.assessment"/>&nbsp;<bean:message key="prompt.date"/></td>
            											<td class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="screenDate" formatKey="date.format.mmddyyyy"/></td>
            										</tr>
            										<tr>	
            											<td class="formDeLabel" ><bean:message key="prompt.assessment"/>&nbsp;<bean:message key="prompt.officer"/></td>
            											<td class="formDe" colspan="3"><bean:write name="mentalHealthForm" property="assessmentOfficerName"/></td>
            										</tr>
            										<tr>
            											<td class="formDeLabel"><bean:message key="prompt.assessmentOption"/></td>
            											<elogic:switch name="mentalHealthForm" property="administer">
            													<elogic:case value="true"><td class="formDe"><bean:write name="mentalHealthForm" property="assessmentOption"/></td></elogic:case>
            													<elogic:default><td class="formDe"></td></elogic:default>
            											</elogic:switch>
            											
            										</tr>
            									</table>
            								</td>
            							</tr>
            						</table>
            						<%-- END Program Referral History TABLE --%>
                        <%-- BEGIN BUTTON TABLE --%>
                        <table border="0" width="100%">
                          <tr>
                            <td align="center">
                              <html:submit property="submitAction"> <bean:message key="button.maysiList"/></html:submit>
                               <html:submit property="submitAction"> <bean:message key="button.print"/></html:submit>                              
                            </td>
                          </tr>
                        </table>
                        <%-- END BUTTON TABLE --%>
             					</td>
             				</tr>
             			</table>
             			<div class=spacer></div>
       					</td>
       				</tr>
       			</table>
       			<div class=spacer></div>
     			</td>
 				</tr>
 			</table>
 			<div class=spacer></div>
		</td>
	</tr>
</table>

</table>
</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
