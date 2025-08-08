<!DOCTYPE HTML>
<%-- User selects the "Activities" tab --%>
<%--MODIFICATIONS --%>
<%-- 11/15/2006	Debbie Williamson	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/> - activityDetails.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<script type="text/javaScript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<html:base />
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitActivity" target="content">


<logic:equal name="activitiesForm" property="action" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|134">
</logic:equal>
<logic:equal name="activitiesForm" property="action" value="confirm">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|133">
</logic:equal>
<logic:equal name="activitiesForm" property="action" value="viewDetail">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|131">
</logic:equal>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" > 
        <logic:equal name="activitiesForm" property="action" value="summary">
          <bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/><br>
	      	<bean:message key="title.create"/>
    	    <bean:message key="title.casefile"/>
    	    <bean:message key="prompt.activity"/>
        </logic:equal>
        <logic:equal name="activitiesForm" property="action" value="confirm">
          <bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/><br>
	      	<bean:message key="title.create"/>
    	    <bean:message key="title.casefile"/>
    	    <bean:message key="prompt.activity"/>
        </logic:equal>    	    
        
        <logic:equal name="activitiesForm" property="action" value="summary">
	        <bean:message key="title.summary"/>
  	    </logic:equal>    

        <logic:equal name="activitiesForm" property="action" value="confirm">
	        <bean:message key="title.confirmation"/>
  				<tr align="center">
  					<td class="confirm">Activity has been saved.</td>
  				</tr>  	        
  	    </logic:equal>	     
        <logic:equal name="activitiesForm" property="action" value="viewDetail">
          <bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/>
          <bean:message key="prompt.activity"/>
	        <bean:message key="title.details"/>
	    </logic:equal>
		</td>    
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class=spacer></div>
<logic:equal name="activitiesForm" property="action" value="summary">
	<table width="98%" border="0">
	  <tr>
	    <td>
	      <ul>
          <li>Select Finish button to save Activity information.</li>
	      </ul>
	    </td>
	  </tr>
	 </table>
</logic:equal>
<logic:equal name="activitiesForm" property="action" value="viewDetail">
	<table width="98%" border="0">
	  <tr>
	    <td>
	      <ul>
          <li>Select Back button to return to previous page.</li>
	      </ul>
	    </td>
	  </tr>
	 </table>
</logic:equal>    
	 
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
      <%--tabs start--%>
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="casefiledetailstab"/>
        <tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
      </tiles:insert>				
      <%--tabs end--%>

		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign=top align=center>
  			  
  			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class=spacer></div>
  			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign=top>
										<%--tabs start--%>
											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="activitiestab"/>
  												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
											</tiles:insert>	
										<%--tabs end--%>
										</td>
									</tr>
									<tr>
								  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
								  </tr>
						  	</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign=top align=center>

										<div class=spacer></div>
            					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            						<tr>
            							<td class=detailHead><bean:message key="prompt.activityDetail"/> ( <bean:write name="activitiesForm" property="activityId" /> )</td>
            						</tr>
            						<tr>
            							<td valign=top align=center>
            								<table width='100%' border="0" cellpadding="4" cellspacing="1" >
            									<tr>								
            										<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.activityDate"/></td>
            										<td class=formDe colspan=3>										
            											<logic:equal name="activitiesForm" property="action" value="summary">
            												<bean:write name="activitiesForm" property="activityDateAsStr" formatKey="date.format.mmddyyyy" />
            											</logic:equal>
            											<logic:equal name="activitiesForm" property="action" value="confirm">
            												<bean:write name="activitiesForm" property="activityDateAsStr" formatKey="date.format.mmddyyyy" />
            											</logic:equal>
            											<logic:equal name="activitiesForm" property="action" value="viewDetail">
            												<bean:write name="activitiesForm" property="activityDate" formatKey="date.format.mmddyyyy" />
            											</logic:equal>
            										</td>
            										
                               				  </tr>
                               				  <tr >
                               				  		<td class=formDeLabel nowrap width='1%'>Activity Time</td>
                               				  		<td class=formDe><bean:write name="activitiesForm" property="activityTimeStr" /></td>
                               				  		
                               				  		<td class=formDeLabel nowrap width='1%'>Activity End Time</td>
                               				  		<td class=formDe colspan=3><bean:write name="activitiesForm" property="activityEndTimeStr" /></td>                               				  		
                               				  </tr>
            <%-- 3apr08 - mjt - not sure why this is commented out ?!?! 
            									<tr>								
            										<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.category"/></td>
            										<td class=formDe><bean:write name="activitiesForm" property="categoryDesc" /></td>
            										<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.type"/></td>
            										<td class=formDe><bean:write name="activitiesForm" property="typeDesc" /></td>
                              					</tr>
            --%>
            									<tr>								
            										<td class=formDeLabel nowrap><bean:message key="prompt.category"/></td>
            										<td class=formDe colspan=3><bean:write name="activitiesForm" property="categoryDesc" /></td>
            									</tr>
            									<tr>
            										<td class=formDeLabel nowrap><bean:message key="prompt.type"/></td>
            										<td class=formDe colspan=3><bean:write name="activitiesForm" property="typeDesc" /></td>
                              					</tr>
                              					<tr>
            										<td class=formDeLabel nowrap><bean:message key="prompt.activity"/></td>
            										<td class=formDe colspan=3><bean:write name="activitiesForm" property="activityDesc" /></td>
            									</tr>
            									<logic:notEmpty name="activitiesForm" property="latitude">
            										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_VIEW_MAP%>'>
            									 <tr>
            										<td class=formDeLabel nowrap><bean:message key="prompt.activity"/>Address</td>
            										<td class=formDe colspan=3 />
            										<a href="javascript:openWindow('https://www.google.com/maps/?q=loc:<bean:write name="activitiesForm" property="latitude" />,<bean:write name="activitiesForm" property="longitude" />')">Address</a>
            										<html:hidden name='activitiesForm' property='latitude' />
            										<html:hidden name='activitiesForm' property='longitude' />
           										 </td>            										
            									</tr>
            									</jims2:isAllowed>
            									</logic:notEmpty>
            									<logic:notEmpty name="activitiesForm" property="comments">						
            									<tr>
            										<td colspan=4 class=formDeLabel><bean:message key="prompt.activityComments"/></td>
            									</tr>
            									<tr>
            										<td colspan=4 class=formDe>
            										<html:textarea readonly="true" name="activitiesForm" property="comments" 
														styleClass="mceEditor" style="width:100%" rows="15">
													</html:textarea>
													</td>
            									</tr>
            									</logic:notEmpty>
            									<tr>
            										<td colspan=4 class=formDeLabel><bean:message key="prompt.update"/> Comments</td>
            									</tr>
            									<tr>
            										<td colspan=4 class=formDe><bean:write name="activitiesForm" property="updateComments"/></td>
            									</tr>
            								</table>
            						
            							</td>
            						 </tr>
            					</table>
       
            					<div class=spacer></div>
            					<table width="100%">
            					  <tr>
              						<td align="center">
              							<logic:equal name="activitiesForm" property="action" value="viewDetail">	                        
              								<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
              								<logic:empty name="activitiesForm" property="updateComments">
              									<logic:equal name="activitiesForm" property="updateBtnAllowed" value="Y">
              										<html:submit property="submitAction"><bean:message key="button.updateComments" /></html:submit>
              									</logic:equal>
              								</logic:empty>
       		                				<html:submit property="submitAction"><bean:message key="button.casefileActivityList" /></html:submit>
              								<html:submit property="submitAction"><bean:message key="button.profileActivityList" /></html:submit>
              								<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
  	                        </logic:equal>
              						</td>
            					  </tr>
            					</table>
      								<%-- BEGIN BUTTON TABLE --%>
            					<table width="100%">
            					  <tr>
              						<td align="center">
             						    <logic:equal name="activitiesForm" property="action" value="summary">
              								<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
              								<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
              								<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
           					        </logic:equal>					

      			                <logic:equal name="activitiesForm" property="action" value="confirm">
      			                	<logic:notEqual name="activitiesForm" property="secondaryAction" value="profile">
      			                		<html:submit property="submitAction"><bean:message key="button.casefileActivityList" /></html:submit>
      			                	</logic:notEqual>

      			                	<logic:equal name="activitiesForm" property="secondaryAction" value="profile">
            									<html:submit property="submitAction"><bean:message key="button.profileActivityList" /></html:submit>
              								</logic:equal>
                            </logic:equal>
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
  				</td>
			 </tr>
  		</table>
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>					

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
