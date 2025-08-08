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
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
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
<%@include file="../jQuery.fw"%> 
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - InterviewInfoDualStatusDetails.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/dualstatus.js"></script>

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
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Dual Status Details
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>
  					<ul>
              <li>Click Add More Dual Status Info button to add.</li>
            </ul>
 					</td>
        </tr>
      </logic:equal>

      <logic:equal name="juvenileAbuseForm" property="action" value="summary">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -&nbsp;Create Dual Status Summary  				
        <tr><td>&nbsp;</td></tr>
        <tr>
        	<td>
        		<ul>						
        			<li>Click Finish button to complete addition of Dual Status Information.</li>
        		</ul>
        	</td>
        </tr>
      </logic:equal>
      
      <logic:equal name="juvenileAbuseForm" property="action" value="confirmdual">			
        <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> -&nbsp;Create Dual Status Confirmation   				
        <tr><td>&nbsp;</td></tr>
        <tr>
        	<td class="confirm">Dual Status Information successfully created.</td>
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
                   <%-- <tiles:put name="tabid" beanName="juvenileDualStatusForm" beanProperty="categoryName" /> --%>
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
      								<td colspan=2><bean:message key="prompt.dualstatusInfo" /></td>	
      							</tr>
      
                    <logic:equal name="juvenileAbuseForm" property="action" value="view">			
        							<tr>
        								<td class=formDeLabel><bean:message key="prompt.entryDate" /></td>
        								<td class=formDe><bean:write name="juvenileAbuseForm" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
        							</tr>
                    </logic:equal>


      							<tr>
      								<td class=formDeLabel><bean:message key="prompt.referralRegion" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="referralRegion" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel><bean:message key="prompt.custodyStatus" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="custodyStatus" /></td>
      							</tr>
      							<tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.CPSlevelofCare" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="cpslevelofCare" /></td>			
      							</tr>
      							<tr>								
      								<td class=formDeLabel nowrap><bean:message key="prompt.parentalRights" /></td>
      								<td class=formDe width="1%" nowrap><bean:write name="juvenileAbuseForm" property="parentalrightsTermination" />
      								<%-- <elogic:if name="juvenileAbuseForm" property="parentalrightsTermination" op="equal" value="true">
	                								<elogic:then>Yes</elogic:then>
													<elogic:else>No</elogic:else>
                					</elogic:if> --%>
      							</td>			
      							</tr>
      							<tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.CPSServices" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="CPSServices" /></td>			
      							</tr>
      							<tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.dualstatusComments" /></td>
      								<td class=formDe><bean:write name="juvenileAbuseForm" property="dualstatusComments" /></td>			
      							</tr>
      							<%-- <tr>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.placementHistory" /></td>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.placementDate" /></td>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.placementType" /></td>
      								<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.placementRemovalReason" /></td>
      							</tr>
      							<tr>
      							<!-- load the history values -->
      								<td class=formDe width="1%" nowrap></td>
      								<td class=formDe width="1%" nowrap><bean:write name="juvenileAbuseForm" property="placementDate" formatKey="date.format.mmddyyyy" /></td>			
      								<td class=formDe width="1%" nowrap><bean:write name="juvenileAbuseForm" property="placementType" /></td>
      								<td class=formDe width="1%" nowrap><bean:write name="juvenileAbuseForm" property="placementRemovalReason" /></td>
      							</tr> --%>
      							
      							</table>
      							<logic:empty name="juvenileAbuseForm" property="newPlacements">
					  			<div class=spacer></div>
					    		<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
					    			<tr class=detailHead>
					    			<td>No placement history found</td>
					    			</tr>
					    		</table>
    							</logic:empty>
      							<logic:notEmpty name="juvenileAbuseForm" property="newPlacements">
					  			<div class=spacer></div>
					    		<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
					    		<tr class=detailHead>
      								<td colspan=5><bean:message key="prompt.placementHistory" /></td>	
      							</tr>					    		
					    			<tr bgcolor='#cccccc'>    				
					    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.placementDt" /> (Month/Year)</td>
					    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.placementType" /></td>
					    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.placementTypeOtherReason" /></td>
					    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.placementRemovalReason" /></td>
					    				<td class="subhead" valign=top width="14%" align="left"><bean:message key="prompt.placementotherReason" /></td>
					    			</tr>
					
					  				<logic:iterate id="placement" name="juvenileAbuseForm" property="newPlacements" indexId="index">
					  				   <tr>
								   						<td nowrap align="left"><bean:write name="placement" property="placementDate"/>&nbsp;&nbsp;</td>
								  						<td nowrap align="left"><bean:write name="placement" property="placementType"/>&nbsp;&nbsp;</td>
								  						<td nowrap align="left"><bean:write name="placement" property="placementtypeotherReason"/>&nbsp;&nbsp;</td>
								  						<td nowrap align="left"><bean:write name="placement" property="placementRemovalReason"/>&nbsp;&nbsp;</td>
								  						<td nowrap align="left"><bean:write name="placement" property="placementremovalreasonOther"/>&nbsp;&nbsp;</td><!-- add other from db -->
								  						
								  		 </tr>
					  				</logic:iterate> 
					  				
								</table>
								</logic:notEmpty>
      							
      							
						  
                          <%-- END ABUSE INFO TABLE --%>
                          <%-- BEGIN BUTTON TABLE --%>
					      <div class=spacer></div>
					      <table width="98%">	
					      	<tr>		
					      		<td align="center">   		 		
					      			<logic:equal name="juvenileAbuseForm" property="action" value="view">
					      				<html:submit property="submitAction"><bean:message key="button.backToDualStatusList"></bean:message></html:submit>
					      			</logic:equal>
					      
					      			<logic:equal name="juvenileAbuseForm" property="action" value="summary">
					      				<%-- <html:submit property="submitAction"><bean:message key="button.backDual"/></html:submit> --%>
					      				<html:submit property="submitAction" styleId="btnBack">Back</html:submit>
					      				<%-- <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>
					      				 --%>
					      				 <html:submit property="submitAction" styleId="btnFinish">Finish</html:submit>
					      				 <%-- <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit> --%>
					      				 <html:submit property="submitAction" styleId="btndetailsCancel">Cancel</html:submit>
					      			</logic:equal>	
					      			
					      			<logic:equal name="juvenileAbuseForm" property="action" value="confirmdual">
					      				<html:submit property="submitAction">
					      					<bean:message key="button.backToDualStatusList"></bean:message>
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
