<!DOCTYPE HTML>
<%-- User selects the "Entry Date" hyperlink on one of the row --%>
<%--MODIFICATIONS --%>
<%-- 06/22/2005	Hien Rodriguez	Create JSP --%>
<%-- 12/15/2006 Hien Rodriguez  ER#37077 Remove CasefileOperation box --%>
<%-- 07/16/2007 Leslie Deen		Defect #43633 changed trait description to trait type description to match create screen --%>

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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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
<title><bean:message key="title.heading"/> - InterviewInfoAbuseDetails.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/displayJuvenileAbuseCreate" target="content">
<logic:equal name="juvenileAbuseForm" property="action" value="view">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|269">
</logic:equal>
<logic:equal name="juvenileAbuseForm" property="action" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|184">
</logic:equal>
<logic:equal name="juvenileAbuseForm" property="action" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|185">
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
  <tr>
    <td align="center" class="header">
      <logic:equal name="juvenileAbuseForm" property="action" value="view">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Abuse Details
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>
  					<ul>
              <li>Click Add More Abuse Info button to add.</li>
            </ul>
 					</td>
        </tr>
      </logic:equal>

      <logic:equal name="juvenileAbuseForm" property="action" value="summary">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -&nbsp;Create Abuse Summary  				
        <tr><td>&nbsp;</td></tr>
        <tr>
        	<td>
        		<ul>						
        			<li>Click Finish button to complete addition of Abuse Information.</li>
        		</ul>
        	</td>
        </tr>
      </logic:equal>
      
      <logic:equal name="juvenileAbuseForm" property="action" value="confirm">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -&nbsp;Create Abuse Confirmation   				
        <tr><td>&nbsp;</td></tr>
        <tr>
        	<td class="confirm">Abuse Information successfully created.</td>
        </tr>
      </logic:equal>
    </td>
  </tr> 	
</table>    	
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>

<%-- BEGIN DETAIL TABLE --%>  
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign=top>
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
              <tiles:put name="tabid" value="interviewinfotab"/>
              <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
            </tiles:insert>
          </td>
        </tr>
        <tr>
          <td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
        </tr>
      </table>
  			
      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
         <tr>
          <td valign=top align=center>
          <div class=spacer></div>
            <table width='98%' border="0" cellpadding="0" cellspacing="0" >
              <tr>
                <td valign=top>
                  <tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
                    <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                  </tiles:insert>
                </td>
              </tr>
              <tr>
                <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
              </tr>              
            </table>
            <div class=spacer></div>
			 <table width='98%' border="0" cellpadding="0" cellspacing="0" >
              <tr>
                <td valign="top">
                  <tiles:insert page="../caseworkCommon/abuseDualTabs.jsp" flush="true">
                  	<%-- <tiles:put name="tabid" value="dualStatus"/> --%>
                   <tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
                    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
                  </tiles:insert>
                </td>
              </tr>
              <tr>
                <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
              </tr>
            </table>
            <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
              <tr>
                <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
              </tr>
              <tr>
                <td valign=top align=center>																		
                  <%-- BEGIN ABUSE INFO TABLE --%>																		
                  <table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">						 
      							<tr class=detailHead>
      								<td colspan=4><bean:message key="prompt.abuseInfo" /></td>	
      							</tr>
      
                    <logic:equal name="juvenileAbuseForm" property="action" value="view">			
        							<tr>
        								<td class=formDeLabel><bean:message key="prompt.entryDate" /></td>
        								<td class=formDe><bean:write name="juvenileAbuseForm" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
        							</tr>
                    </logic:equal>


      							<tr> <%-- abuse description --%>
      								<td class=formDeLabel><bean:message key="prompt.trait" />&nbsp;<bean:message key="prompt.type" />&nbsp;<bean:message key="prompt.description" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="traitTypeName" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel>CPS Case Number</td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="cpsCaseNumber" /></td>
      							</tr>
      							<tr> 
      								<td class=formDeLabel><bean:message key="prompt.info" />&nbsp;<bean:message key="prompt.source" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="informationSrcDesc" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel><bean:message key="prompt.abuseLevel" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="abuseLevel" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel>Was CPS Involved?</td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="cpsInvolvementString" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel><bean:message key="prompt.informationBasis" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="informationBasis" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.relationshipOfAllegedAbuser" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="allegedAbuserRelationship" /></td>			
      							</tr>
      							<logic:notEmpty name="juvenileAbuseForm" property="multiplePrep">
      							<tr>								
      								<td class=formDeLabel nowrap><bean:message key="prompt.perpetratorName" /></td>
      								<td class=formDe colspan="3"><bean:write name="juvenileAbuseForm" property="namewithRelationShips" />
      								</td>			
      							</tr>
      							<%-- <tr>
      								<!-- <td class=formDeLabel width="1%" nowrap></td> -->
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.relationshipToJuvenile" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="relationShips" /></td>			
      							</tr> --%>
      							
      							<%-- <tr>
      								<td class=formDeLabel><bean:message key="prompt.wasCpsInvolved" /></td>
      								<td class=formDe colspan="3"><bean:write name="juvenileAbuseForm" property="cpsInvolvementString" /></td>				    
      							</tr> --%>
      							<tr>
      								<td class=formDeLabel><bean:message key="prompt.didCPStakeCustody" /></td>
      								<td class=formDe colspan="3"><bean:write name="juvenileAbuseForm" property="cpsCustodyString" /></td>				    
      							</tr>
      							<tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.abuseTreatment" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="abuseTreatment" /></td>			
      							</tr>
      
      							<%-- <tr>
      								<td class=formDeLabel colspan="4"><bean:message key="prompt.describeWhatThePerpetratorDid" /></td>
      							</tr>
      							<tr>
      								<td class=formDe colspan="4"><bean:write name="juvenileAbuseForm" property="abuseEvent" /></td>		
      							</tr> --%>
      
      							<tr>
      								<td class=formDeLabel colspan="4"><bean:message key="prompt.describeTheCircumstancesOfTheIndicatedAbuse" /></td>
      							</tr>
      							<tr>
      								<td class=formDe colspan="4"><bean:write name="juvenileAbuseForm" property="abuseDetails" /></td>
      							</tr>	
      							</logic:notEmpty>											
      							<logic:empty name="juvenileAbuseForm" property="multiplePrep">
      							
      							<tr>								
      								<td class=formDeLabel nowrap><bean:message key="prompt.perpetratorName" /></td>
      								<td class=formDe colspan="3"><bean:write name="juvenileAbuseForm" property="namewithRelationShips" /></td>			
      							</tr>
      							<%-- <tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.relationshipToJuvenile" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="relationshipToJuvenile" /></td>			
      							</tr> --%>
      							<%-- <tr>
      								<td class=formDeLabel><bean:message key="prompt.wasCpsInvolved" /></td>
      								<td class=formDe colspan="3"><bean:write name="juvenileAbuseForm" property="cpsInvolvementString" /></td>				    
      							</tr> --%>
      							<tr>
      								<td class=formDeLabel><bean:message key="prompt.didCPStakeCustody" /></td>
      								<td class=formDe colspan="3"><bean:write name="juvenileAbuseForm" property="cpsCustodyString" /></td>				    
      							</tr>
      							<tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.abuseTreatment" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="abuseTreatment" /></td>			
      							</tr>
      
      							<%-- <tr>
      								<td class=formDeLabel colspan="4"><bean:message key="prompt.describeWhatThePerpetratorDid" /></td>
      							</tr>
      							<tr>
      								<td class=formDe colspan="4"><bean:write name="juvenileAbuseForm" property="abuseEvent" /></td>		
      							</tr> --%>
      
      							<tr>
      								<td class=formDeLabel colspan="4"><bean:message key="prompt.describeTheCircumstancesOfTheIndicatedAbuse" /></td>
      							</tr>
      							<tr>
      								<td class=formDe colspan="4"><bean:write name="juvenileAbuseForm" property="abuseDetails" /></td>
      							</tr>		
      							
      							
      							</logic:empty>
						  </table>
                          <%-- END ABUSE INFO TABLE --%>
                          <%-- BEGIN BUTTON TABLE --%>
					      <div class=spacer></div>
					      <table width="98%">	
					      	<tr>		
					      		<td align="center">   		 		
					      			<logic:equal name="juvenileAbuseForm" property="action" value="view">
					      				<html:submit property="submitAction"><bean:message key="button.backToAbuseList"></bean:message></html:submit>
					      			</logic:equal>
					      
					      			<logic:equal name="juvenileAbuseForm" property="action" value="summary">
					      				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
					      				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					      					<bean:message key="button.finish"></bean:message>
					      				</html:submit>
					      				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					      			</logic:equal>	
					      			
					      			<logic:equal name="juvenileAbuseForm" property="action" value="confirm">
					      				<html:submit property="submitAction">
					      					<bean:message key="button.backToAbuseList"></bean:message>
					      				</html:submit>
					      			</logic:equal>				    	    	
					      	    </td>		
					      	</tr>
					      </table>
					      <div class=spacer></div>
					      <%-- END BUTTON TABLE --%>
          </td>
        </tr>
      </table>
     <br>
    </td>
  </tr>
</table>	
<%-- END DETAIL TABLE --%>

<br>
</html:form>

<%-- END FORM --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
