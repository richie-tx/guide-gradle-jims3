<!DOCTYPE HTML>
<%--This JSP will be used to create, update and create additional addresses.--%>
<%--MODIFICATIONS --%>
<%-- DApte	05/16/2005	Create JSP --%>
<%-- CShimek 07/14/2006 Activity#33117 Flip flop questions and add type of provider question --%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

 

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

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

<title><bean:message key="title.heading" /> - subsAssessSummary.jsp</title>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >

<%-- BEGIN FORM --%>		
<html:form action="/submitSubsequentMAYSI" target="content">
<input type="hidden" name="useCase" value="manageJuvenileCasework"/>
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|314">


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr> 
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Subsequent Mental Health Assessment Summary</td> 
  </tr> 
</table> 
<%-- END HEADING TABLE --%> 


<%-- BEGIN INSTRUCTION TABLE --%> 
<br> 
<table width="98%" border="0"> 
  <tr> 
     <td align="left"> 
       <ul> 
         <li>Verify that the information is correct, then select the Finish button to save assessment.</li> 
          <li>Select the Back button to return to previous page to make change. </li> 
       </ul>
     </td> 
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 


<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" > 
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>  
<%-- END JUVENILE HEADER INCLUDE  --%>

<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  	<td valign=top>
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
					
					  <div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
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

            <%--   --%>
           	<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
              <tr>
    					  <td valign=top align=center>
					
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
            					<td valign=top align=center>
			
              					<%-- BEGIN Program Referral History TABLE --%> 
            					  <div class='spacer'></div>
            						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
            							<tr> 
            								<td class=detailHead colspan="2">Subsequent Mental Health Assessment</td> 
            							</tr> 
            							<tr> 
            								<td> 
            									<table width='100%' cellpadding=2 cellspacing=1> 
            										<tr> 
            											<td class=formDeLabel>Referral Number</td> 
            											<td class="formDe" nowrap> <bean:write name="mentalHealthForm" property="referralNum"/></td> 
            										</tr> 
            										<tr> 
            											<td class=formDeLabel width='60%'>Was the child referred to a mental health professional for a subsequent assessment based on the MAYSI results?</td> 
            											<td class=formDe>
            												<elogic:switch name="mentalHealthForm" property="subsAssessmentReferral">
            													<elogic:case value="true">YES</elogic:case>
            													<elogic:default>NO</elogic:default>
            												</elogic:switch>
            											</td> 
            										</tr>
            										<tr> 
            											<td class=formDeLabel><bean:message key="prompt.toWhatTypeOfProviderWasTheJuvReferred"/></td> 
            											<td class=formDe><bean:write name="mentalHealthForm" property="providerReferredType"/></td> 
            										</tr>
            										<tr> 
            											<td class=formDeLabel>Was a subsequent assessment completed on this youth?</td> 
            											<td class=formDe>
            												<elogic:switch name="mentalHealthForm" property="wasSubsAssessmentCompleted">
            													<elogic:case value="true">YES</elogic:case>
            													<elogic:default>NO</elogic:default>
            												</elogic:switch>
            											</td> 
            										</tr>
            										<tr> 
            											<td class=formDeLabel colspan=2 nowrap>Subsequent Assessment Comments</td> 
            										</tr>
            										<tr> 
            											<td class="formDe" colspan=2><bean:write name="mentalHealthForm" property="subsAssessmentComments"/></td> 
            										</tr> 
            									</table>
            								</td> 
            							</tr> 
            						</table> 
            						<%-- END Program Referral History TABLE --%> 
            
            						<%-- BEGIN BUTTON TABLE --%> 
            					  <div class='spacer'></div>
            						<table border="0" width="100%"> 
            							<tr> 
            								<td align="center">
            									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
            									<html:submit property="submitAction" styleId="finish"><bean:message key="button.finish"/></html:submit>
            									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
            								</td>
            							</tr> 
              					</table>
                		  </td>
                  	</tr>
              		</table><br>
          			</td>
      				</tr>
      			</table><br>
      		</td>
      	</tr>
      </table>
		</td>
	</tr>
</table>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>

