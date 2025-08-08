<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<script>
function loadView(file, selectVal)
{
	var myURL = file + "&selectedValue=" + selectVal;		
	
	load( myURL, top.opener );
	window.close();
}

function load(file,target) 
{
    window.location.href = file;
}
</script>
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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- goalDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>


</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/displayGoalDetails" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|71"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Goal Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul>
				<li>Select Associated Rules hyperlink to view Associated Rule details.</li>
				<li>Select Back button to return to Goals list. </li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER TABLE --%>
<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
  <tr>
    <td>
		<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
			<tiles:put name="headerType" value="profileheader"/>
		</tiles:insert> <%--header info end--%></td>
  </tr>
</table>
<%-- END HEADER TABLE --%>
<br>


<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	    <td valign=top>
	   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<%--tabs start--%> 
						<tiles:insert
							page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
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
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr><td bgcolor='#ffffff'><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
				<tr>
    				<td valign=top align=center>
    					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
									<td valign=top>
										<tiles:insert page="../caseworkCommon/juvenileCasePlanTabs.jsp" flush="true">
											<tiles:put name="tabid" value="Caseplan"/>
											<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
										</tiles:insert>				
									</td>
							</tr>
								<tr>
									<td bgcolor='#6699FF'><div class=spacer></div></td>
								</tr>
						</table>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
							</tr>
							<tr>
								<td valign="top" align="center">
						<div class=spacer></div>     
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class=detailHead colspan=2 nowrap>Goal Information</td>
							</tr>
							<tr>
								<td colspan=2>
									<table align="center" width='100%' cellpadding="1" cellspacing="1">
										<tr>
											<td class=formDeLabel valign=top width='1%' nowrap> <bean:message key="prompt.domain"/> <bean:message key="prompt.type"/></td>
											<td class=formDe><bean:write name="caseplanForm" property="currentGoalInfo.domainTypeStr"/></td>
										</tr>
										<tr>
											<td class=formDeLabel nowrap> <bean:message key="prompt.personsResponsible"/></td>
											<td class=formDe >
												<table width='100%' cellpadding="0" cellspacing="0">
												<logic:notEmpty name="caseplanForm" property="currentGoalInfo.personsResponsibleIds">
													<logic:iterate name="caseplanForm" property="currentGoalInfo.personsResponsibleIds" id="idIndex">
													<tr>
													<td>
														<bean:write name="idIndex"/></td>
													</tr>
													</logic:iterate>
												</logic:notEmpty>
												</table>
											</td>
										</tr>
										<tr>
											<td class=formDeLabel> <bean:message key="prompt.time"/> <bean:message key="prompt.frame"/></td>
											<td class=formDe><bean:write name="caseplanForm" property="currentGoalInfo.timeFrameStr"/></td>
										</tr>
										<tr>
											<td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.goal"/></td>
										</tr>
										<tr>
											<td class=formDe colspan=4><bean:write name="caseplanForm" property="currentGoalInfo.goal"/></td>
										</tr>
										<tr>
											<td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.progress"/> <bean:message key="prompt.notes"/></td>
										</tr>
										
										<%-- this is a unique field, since it doesn't get set during goal create, but it will be set during goal update --%>
										<logic:notEmpty name="caseplanForm" property="currentGoalInfo.progressNotes">
											<tr>
												<td class=formDe colspan=4><bean:write name="caseplanForm" property="currentGoalInfo.progressNotes"/></td>
											</tr>
											<logic:notEqual name="caseplanForm" property="action" value="create">
												<tr>
													<td class=formDeLabel  colspan=4><bean:message key="prompt.endRecommendations"/></td>
												</tr>
												<tr>
													<td class=formDe  colspan=4> <bean:write name="caseplanForm" property="currentGoalInfo.endRecommendations"/></td>
												</tr>
											</logic:notEqual>
										</logic:notEmpty>
									</table>
								</td>
							</tr>
						</table>
						<%-- END GOAL INFORMATION TABLE --%>	
					</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td valign=top align=center>
						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class=detailHead><bean:message key="prompt.associated"/> <bean:message key="prompt.rules"/></td>
							</tr>
							<tr>
								<td>
									<table cellpadding=2 cellspacing=1 width='100%'>
											<% int RecordCounter=0;
											   String bgcolor="";
											%>
											 <tr bgcolor=#cccccc>
											<td></td>
											<td class=subHead><bean:message key="prompt.rule"/> <bean:message key="prompt.id"/></td>
											<td class=subHead><bean:message key="prompt.category"/></td>
											<td class=subHead><bean:message key="prompt.type"/></td>
											<td class=subHead><bean:message key="prompt.subType"/></td>
											<td class=subHead><bean:message key="prompt.completion"/> <bean:message key="prompt.date"/></td>
											<td class=subHead><bean:message key="prompt.completion"/> <bean:message key="prompt.status"/></td>
										</tr>
									
										<logic:notEmpty name="caseplanForm" property="currentGoalInfo.associatedRules">
										<logic:iterate id="rulesIndex" name="caseplanForm" property="currentGoalInfo.associatedRules">
											<tr class= <% RecordCounter++;
											  bgcolor = "alternateRow";                      
											  if (RecordCounter % 2 == 1)
												  bgcolor = "normalRow";
											   out.print(bgcolor); %>>
												<td>
													<logic:notEqual name="status" value="view">
													<input type="checkbox" name="currentGoalInfo.selectedRules" value="<bean:write name="rulesIndex" property="ruleId"/>"/>
													</logic:notEqual>
												</td>
												<td>						
													<a href="/<msp:webapp/>displayJuvenileProfileRuleDetail.do?submitAction=Display Rule Details&selectedValue=<bean:write name="rulesIndex" property="ruleId"/>"><bean:write name="rulesIndex" property="ruleId"/></a>																		
												</td>
													<td nowrap><bean:write name="rulesIndex" property="condCategoryId"/></td> 
												<td nowrap><bean:write name="rulesIndex" property="condTypeId"/></td> 
												<td nowrap><bean:write name="rulesIndex" property="condSubTypeId"/></td>
												<td nowrap><bean:write name="rulesIndex" property="ruleCompletionDate" formatKey="date.format.mmddyyyy"/></td>
												<td><bean:write name="rulesIndex" property="ruleCompletionStatus"/></td>										
											</tr>
										</logic:iterate>
										</logic:notEmpty>
										
									</table>
								</td>
							</tr>
						</table>					
					</td>
				</tr>	
				<tr>
				   <td>
				       <%-- BEGIN BUTTON fdas TABLE --%>
						<div class=spacer></div> 
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
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




</html:form>


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
