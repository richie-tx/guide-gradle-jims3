<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- goalUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitUpdatePlacementInfo" target="content">

<logic:equal name="caseplanForm" property="action" value="create">
  <logic:notEqual name="status" value="confirm">
     <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|165">
  </logic:notEqual>
  <logic:equal name="status" value="confirm">
     <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|166">
  </logic:equal> 
</logic:equal>
<logic:equal name="caseplanForm" property="action" value="update">
  <logic:notEqual name="status" value="confirm">
     <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|171">
  </logic:notEqual>
  <logic:equal name="status" value="confirm">
     <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|172">
  </logic:equal>
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - 
			<logic:equal name="caseplanForm" property="action" value="create">Create</logic:equal>
			<logic:equal name="caseplanForm" property="action" value="update">Update</logic:equal>
			Placement Plan
			
			<logic:notEqual name="status" value="confirm">Summary</logic:notEqual>
			<logic:equal name="status" value="confirm">Confirmation</logic:equal>
		</td>
	</tr>
	<logic:equal name="status" value="confirm">
		<tr id='confMessage'><td align=center class='confirm'>Placement information has been saved.</td></tr>
	</logic:equal>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<logic:notEqual name="status" value="confirm">
<div class=spacer></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<li>Review information entered, then select Finish button to save Placement information.</li>
				<li>To change information required, select the Back button.</li>
			</ul>
		</td>
	</tr>
</table>
</logic:notEqual>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign=top>

  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				
  
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top align=center>

					  <div class=spacer></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  						<tr>
  							<td valign=top>
    							<tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
    								<tiles:put name="tabid" value="Caseplan"/>
    								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    							</tiles:insert>				
    						</td>
  						</tr>
  					 	<tr>
					  		<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
					  	</tr>
					  </table>
					
					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
						<tr>
					    <td valign=top align=center>
  							<div class=spacer></div>
  							<table width='98%' border=0 cellpadding=0 cellspacing=0>
									<tr>
										<td>
											<tiles:insert page="placementInfoTile.jsp" flush="true">
												<tiles:put name="placementInfo" beanName="caseplanForm" beanProperty="currentPlacementInfo"/>	
											</tiles:insert>
										</td>
									</tr>
								</table><div class=spacer></div>

                <%-- BEGIN BUTTONS TABLE --%>
                <div class=spacer></div>
                  <table width="100%">
                  	<tr>
                    	<td align="center">
                  			<logic:notEqual name="status" value="confirm">
                  				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                  				<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit>
                  				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                  			</logic:notEqual>
        
                  			<logic:equal name="status" value="confirm">
                  				<input type="submit" name="submitAction" value="<bean:message key='button.addCaseplanComments'/>" 
                  					onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanComments.do', false);">
                  				<input type="submit" name="submitAction" value="<bean:message key='button.returnToCaseplanDetails'/>" 
                  					onclick="changeFormActionURL('/<msp:webapp/>displayCaseplanDetails.do', false);">	
                  			</logic:equal>
                    	</td>
                  	</tr>
                  </table>
                  <%-- END BUTTONS TABLE --%>
                  <div class=spacer></div>
  							</td>
  						</tr>
  					</table><div class=spacer></div>

  				</td>
  			</tr>
      </table>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
