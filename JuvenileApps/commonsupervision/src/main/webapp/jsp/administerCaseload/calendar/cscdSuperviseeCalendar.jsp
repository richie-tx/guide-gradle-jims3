<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/08/2008	 AWidjaja - Create JSP -->
<!-- 07/14/2009	 LDeen - Defect #60965-Revise Page Title -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>  
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.Features"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - supervisee/superviseeDetails.jsp</title>

<!-- Javascript -->
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<!-- FUNCTIONS FOR FILTER CONDITIONS GROUPS  -->
<script type="text/javascript">
</script>  
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayAddCSEvent" target="content" >
<input type="hidden" name="context" value="S">

<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|64">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
					   		<tiles:put name="tab" value="caseloadTab"/> 
				     	</tiles:insert>					
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->  
<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>						
							<tr>
								<td valign="top">
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
					   				 	<tiles:put name="tab" value="CalendarTab"/> 
						     		</tiles:insert>					
								</td>
							</tr>
							<tr>
								<td  bgcolor="#33cc66"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
							</tr>
						</table>
					</td>
				</tr>		
<!-- END GREEN TABS TABLE -->				
				<tr>
					<td valign="top" align="center">
<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.supervisee" />&nbsp;<bean:message key="prompt.calendar" />
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
<!-- END ERROR TABLE -->
<!-- BEGIN CONFIRMATION TABLE -->
							 
<!-- BEGIN CONFIRMATION TABLE -->    	
<!-- BEGIN INSTRUCTION TABLE -->
									
<!-- END INSTRUCTION TABLE -->
								</td>											
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
			 					<bean:define id="spn" name="superviseeHeaderForm" property="superviseeId" type="String"/>
								<bean:define id="superviseeName" name="superviseeHeaderForm" property="superviseeNameDesc" type="String"/>
								<bean:define id="eventLinkURL">/<msp:webapp/>displayCSEventList.do?submitAction=Link&context=S&superviseeId=<bean:write name="csCalendarDisplayForm" property="superviseeId"/></bean:define>
											
									<%
										Boolean need = (Boolean)request.getAttribute("calendarNeedsRefresh");
										boolean needsRefresh = true;
										if (need!=null){
											needsRefresh = need.booleanValue();
										}
									%>
									
								<jims2:cscalendar
									calendarStyleSheet="blueCalendarSkin.css"
									serviceEvent="messaging.cscdcalendar.GetMonthlyCSCalendarEvent"
									eventTimeFormat="h:mma"
									weekDayViewType="FULLTEXT"
									eventLink="<%=eventLinkURL%>"
									dayDisplayClass="ui.taglib.ConsolidatedCSEventDayPresentation"
									title="<%=superviseeName%>"
									currentContext="S"
									needsRefresh="<%=needsRefresh%>"
									superviseeId="<%=spn%>">
									
								</jims2:cscalendar>									
						
							
								
<!-- END GREEN BORDER TABLE -->
					</td>
				</tr>
				<tr>
				<td>
				<!-- BEGIN BUTTON TABLE -->
					<table border="0" width="100%">
					  <jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_OTR_CREATE%>'>
						<tr>
							<td align="center">
								
								
								<html:submit property="submitAction"><bean:message key="button.addNewEvent"/></html:submit>
								
							</td>
						</tr>
					  </jims2:isAllowed>	
					</table>
					<!-- END BUTTON TABLE -->
				</td>
				</tr>	
			</table>	<br>
	</td>
							</tr>
						</table>
						<br>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
<!-- END  TABLE -->

</pg:pager>
</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>