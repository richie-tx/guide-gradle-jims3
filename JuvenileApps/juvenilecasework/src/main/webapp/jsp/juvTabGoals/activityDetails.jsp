<!DOCTYPE HTML>
<%-- User selects the "Activities" tab --%>
<%--MODIFICATIONS --%>
<%-- 11/15/2006	Debbie Williamson	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<html:base />
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/handleCaseplan" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|212">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>

  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Activity Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
	<table width="98%" border="0">
	  <tr>
	    <td>
	      <ul>
	           <li>Select Back button to return to previous page.</li>
	      </ul>
	    </td>
	  </tr>
	 </table>
	 
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER TABLE --%>
<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
  <tr>
    <td bgcolor='#cccccc'>
				<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
					<tiles:put name="headerType" value="profileheader"/>
				</tiles:insert> <%--header info end--%></td>
  </tr>
</table>
<%-- END HEADER TABLE --%>
<%-- BEGIN DETAIL TABLE --%>
<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
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
			  	<td bgcolor=#6699FF><img src=../images/spacer.gif width=5></td>
			</tr>
		</table>
		
		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			<tr>
				<td valign=top><br></td>
			</tr>
			<tr>
				<td valign=top align=center>
					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr>
							<td class=detailHead><bean:message key="prompt.activityDetail"/></td>
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

									<tr>								
										<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.category"/></td>
										<td class=formDe><bean:write name="activitiesForm" property="categoryDesc" /></td>
										<td class=formDeLabel nowrap width='1%'><bean:message key="prompt.type"/></td>
										<td class=formDe><bean:write name="activitiesForm" property="typeDesc" /></td>
                                    </tr>
									<logic:notEmpty name="activitiesForm" property="comments">
                                    <tr>
										<td class=formDeLabel nowrap><bean:message key="prompt.activity"/></td>
										<td class=formDe colspan=3><bean:write name="activitiesForm" property="activityDesc" /></td>
									</tr>						
									<tr>
										<td colspan=4 class=formDeLabel><bean:message key="prompt.activityComments"/></td>
									</tr>
									<tr>
										<td colspan=4 class=formDe><bean:write name="activitiesForm" property="comments"/></td>
									</tr>
									</logic:notEmpty>
								</table>
						
							</td>
						 </tr>
					</table>
								
					<div class=spacer4px></div>
					<%-- BEGIN BUTTON TABLE --%>
					<table width="100%">
					  <tr>
						<td align="center">
								<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
						</td>
					  </tr>
					</table>
			<%-- END BUTTON TABLE --%>
				<div class=spacer4px></div>							 
				</td>
			 </tr>
		</table>
		
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>					
					
					



<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
