<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- profileCaseplanDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script  type='text/javascript'>
function loadView(file, selectVal){
	var myURL=file  + "&selectedValue=" +selectVal;		
	load(myURL,top.opener);
	window.close();
}
function load(file,target) {
   
    window.location.href = file;
}
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/handleCaseplan" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|64"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Caseplan Version Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Goal # hyperlink to view details of the goal.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<logic:messagesPresent>
<br>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors></td>		  
	</tr>   	  
</table>
</logic:messagesPresent> 

<%-- BEGIN HEADER TABLE --%>
<br>
<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
  <tr>
    <td bgcolor='#cccccc'>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert> <%--header info end--%>
		</td>
  </tr>
</table>
<%-- END HEADER TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign=top>
   		<table width='98%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<%--tabs start--%> 
            <tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
  						<tiles:put name="tabid" value="goalstab" />
  						<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
					  </tiles:insert> 
            <%--tabs end--%>		
					</td>
				</tr>
				<tr>
			  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
				</tr>
			</table>

			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr><td bgcolor='#ffffff'><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
				<tr>
					<td valign=top align=center>
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
					  		<td bgcolor='#33cc66'><img src="/<msp:webapp/>spacer.gif" width=5></td>
					  	</tr>
					  </table>

  					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
    					<tr>
    						<td valign=top><img src="/<msp:webapp/>spacer.gif" border=0 width=5></td>
    					</tr>
    					<tr>
				        <td valign=top align=center>
      						<%-- BEGIN Today's Interview TABLE --%>
						
      						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      							<tr>
      								<td class=detailHead> Caseplan Details</td>
      							</tr>
      							<tr>
      							<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
      								<td>								
      									<table width='100%' border="0" cellpadding="4" cellspacing="1">
      										<tr>
      											<td nowrap class="formDeLabel" width='1%'><bean:message key="prompt.casePlan"/> <bean:message key="prompt.status"/></td>
      												<td class="formDe"><bean:write name="caseplanForm" property="currentCaseplan.status"/></td>
      										</tr>
      										<tr>
      											 <td nowrap class="formDeLabel" width='1%'><bean:message key="prompt.createDate"/></td>
      											 <td class="formDe"><bean:write name="caseplanForm" property="currentCaseplan.createDate"/></td>
      								   </tr>
      									</table>
      								</td>
      							</logic:equal>
      							<logic:equal name="caseplanForm" property="caseplanExist" value="N">
      							<td class="formDeLabel" colspan=4>No Caseplan available for this case</td> 
      							</logic:equal>
      							</tr>
      						</table>
      
      						<br>
      						<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
      						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
      							<tr>
      								<td class=detailHead><bean:message key="prompt.goals"/></td>
      							</tr>
      							<tr>
      								<td>
      									<table cellpadding=2 cellspacing=1 width='100%'>
      											<% int RecordCounter=0;
      										   String bgcolor="";
      										%>
      										 <tr bgcolor='#cccccc'>
      											<td class=subHead><bean:message key="prompt.goalNumber"/></td>
      											<td class=subHead><bean:message key="prompt.entryDate"/></td>
      											<td class=subHead><bean:message key="prompt.closedDate"/></td>
      											<td class=subHead><bean:message key="prompt.status"/></td>
      											<td class=subHead><bean:message key="prompt.goal"/></td>
      										</tr>
      										<logic:notEmpty name="caseplanForm" property="currentCaseplan.goalList">
      										<logic:iterate id="goalsIndex" name="caseplanForm" property="currentCaseplan.goalList">
      										
      											<tr class= <% RecordCounter++;
      								              bgcolor = "alternateRow";                      
      								              if (RecordCounter % 2 == 1)
      								                  bgcolor = "normalRow";
      							                   out.print(bgcolor); %>>
      											
      												<td>						
      													<a href="javascript:loadView('/<msp:webapp/>displayGoalDetails.do?submitAction=Link', '<bean:write name="goalsIndex" property="goalID"/>')"><bean:write name="goalsIndex" property="goalID"/></a>																		
      												</td>
      												
      												<td><bean:write name="goalsIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td> 
      												<td><bean:write name="goalsIndex" property="closedDate" formatKey="date.format.mmddyyyy"/></td>
      												<td><bean:write name="goalsIndex" property="statusId"/></td>
      												<td><bean:write name="goalsIndex" property="goalDescription"/></td>										
      											</tr>
      										</logic:iterate>
      										</logic:notEmpty>		
      									</table>
      									</td>
      								</tr>							
      						</table>
      						</logic:equal>
      						<%-- END GOALS TABLE --%>							
      						
      						<logic:notEqual name="caseplanForm" property="placementInfoExist" value="N">
      							<table width='98%' border=0 cellpadding=0 cellspacing=0>
      								<tr>
      									<td>
      										<tiles:insert page="../casetabCaseplan/placementInfoTile.jsp" flush="true">
      											<tiles:put name="placementInfo" beanName="caseplanForm" beanProperty="currentPlacementInfo"/>	
      										</tiles:insert>
      									</td>
      								</tr>
      							</table>
      						</logic:notEqual>
      															                                 
      						<table width="100%">
      							<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
      						  	<tr>
      						    	<td align="center">
      									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>			
      						    	</td>
      						  	</tr>
      							</jims2:isAllowed>
      						</table>
      						<div class=spacer></div>
      					</td>
    					</tr>
  					</table><br>
					</td>
				</tr>
			</table><br>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- BEGIN URL TABLE - the following JS call does NOT display buttons,
     but a group of hyperlinked menu options --%>
</html:form>

<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
