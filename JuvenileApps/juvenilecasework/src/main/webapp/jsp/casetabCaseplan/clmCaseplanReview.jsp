<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- caseplanDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script  type='text/javascript'>
function loadView(file, selectVal)
{
	var myURL = file  + "&selectedValue=" +selectVal;
			
	load( myURL,top.opener );
	window.close();
}

function load(file,target) 
{
  window.location.href = file;
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitCLMReview" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - CLM Review - Caseplan Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
<logic:equal name="displayConfirmation" value="yes">
	<table>
		<tr>
			<td class="confirm">Caseload Manager Review has been saved and Notification sent.</td>
		</tr>
	</table>
</logic:equal>
<logic:messagesPresent>
<div class='spacer'></div>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors></td>		  
	</tr>   	  
</table>
<div class='spacer'></div>
</logic:messagesPresent> 

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Goal # hyperlink to view details and modify the goal.</li>

				<logic:equal name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">
					<logic:equal name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">
	         <li>Select the Accept or Reject button, as appropriate. </li>
	        </logic:equal>
	      </logic:equal>

				<logic:equal name="caseplanForm" property="currentCaseplan.status" value="REVIEWED">
					<logic:equal name="caseplanForm" property="action" value="CLMREVIEWINPROGRESS">
	         <li>Select the Notification button.</li>
	        </logic:equal>
	      </logic:equal>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign=top>

  		<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign=top align=center>
						<div class='spacer'></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
				  			<td valign=top>
				  				<tiles:insert page="/jsp/caseworkCommon/casePlanTabs.jsp" flush="true">
				  					<tiles:put name="tabid" value="Caseplan"/>
				  					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
				  				</tiles:insert>				
				  			</td>
				  		</tr>
	  				 	<tr>
						 		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
						 	</tr>
    				</table>
					
    				<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
    					<tr>
				        <td valign=top align=center>

      						<%-- BEGIN Today's Interview TABLE --%>
        					<div class='spacer'></div>
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
      										<tr>
      											 <td nowrap class="formDeLabel" width='1%'><bean:message key="prompt.reviewDate"/></td>
      											 <td class="formDe"><bean:write name="caseplanForm" property="currentCaseplan.reviewDate"/> </td>
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
      										 <tr bgcolor='#cccccc'>
      											<td class=subHead><bean:message key="prompt.goalNumber"/></td>
      											<td class=subHead><bean:message key="prompt.entryDate"/></td>
      											<td class=subHead><bean:message key="prompt.closedDate"/></td>
      											<td class=subHead><bean:message key="prompt.status"/></td>
      											<td class=subHead><bean:message key="prompt.goal"/></td>
      										</tr>
      										<logic:notEmpty name="caseplanForm" property="currentCaseplan.goalList">
      										<logic:iterate indexId="index" id="goalsIndex" name="caseplanForm" property="currentCaseplan.goalList">
      											<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
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

    						<logic:notEqual name="caseplanForm" property="placementInfoExist" value="N">
    							<div class='spacer'></div>
    							<table width='98%' border=0 cellpadding=0 cellspacing=0>
    								<tr>
    									<td>
    										<tiles:insert page="/jsp/casetabCaseplan/placementInfoTile.jsp" flush="true">
    											<tiles:put name="placementInfo" beanName="caseplanForm" beanProperty="currentPlacementInfo"/>	
    										</tiles:insert>
    									</td>
    								</tr>
    							</table>
    						</logic:notEqual>
                <div class='spacer'></div>
    						<%-- END GOALS TABLE --%>	

    						<%-- JPO REVIEW REQUEST COMMENTS --%>
    						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    							<tr>
    								<td class=detailHead>JPO Review Request Comments</td>
    							</tr>
    							<tr>
    								<td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.comments"/></td>
    							</tr>
    							<tr>
    								<td class=formDe colspan=4><bean:write name="caseplanForm" property="reqReviewComments"/></td>
    							</tr>
    						</table>
    						<%-- END JPO REVIEW REQUEST COMMENTS --%>			
							
  							<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
  						    <logic:equal name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">
  						       <table width="100%">
						  	       <tr>
						    	       <td align="center">
  									      <html:submit property="submitAction"><bean:message key="button.accept"/></html:submit>			
  									      <html:submit property="submitAction"><bean:message key="button.reject"/></html:submit>
  									      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
  								       </td>
  							       </tr>
  						       </table>
  						    </logic:equal>
  							</logic:equal>

									<table width="100%" >
						        <tr>
											<elogic:if name="displayConfirmation" op="equal" value="yes">
												<elogic:or name="caseplanForm" property="currentCaseplan.status" op="equal" value="PENDING" />
	
												<elogic:or name="caseplanForm" property="currentCaseplan.status" op="equal" value="REVIEWED">
													<elogic:and name="caseplanForm" property="action" op="equal" value="CLMREVIEWINPROGRESS"  />
												</elogic:or>
												<elogic:then>
									        <td align="center">
												    <html:submit property="submitAction"><bean:message key="button.notification"/></html:submit>
					   						  </td>
												</elogic:then>
											</elogic:if>
						        </tr>
							    </table>
									<div class=spacer></div>
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>


</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
