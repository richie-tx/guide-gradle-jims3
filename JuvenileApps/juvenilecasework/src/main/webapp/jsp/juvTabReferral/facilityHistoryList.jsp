<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 08/19/2013 C Shimek    #75923 revised page to function at profile tabs level and added Locker and Receipt Numbers to details list.  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>


<%--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --%>
<%-- <html:html locale="true"> --%>

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvFacilityHistory.js"></script>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>

<script type="text/javascript">
$(function() { 
	$("#historyLink").click(function(){		
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="assignedReferralsForm" property="juvenileNum"/>';
		 window.myVariable=juvNum;
		 var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Facility&actionType=popup&juvnum=" +juvNum;
		 var facilityWindow = window.open(url, "facilityHistWin", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 facilityWindow.focus();	
		localStorage.setItem("facilityHistWin", "open");
		localStorage.setItem("juvnum", juvNum);
		return false;		
		});
 });

 </script>


<title><bean:message key="title.heading" /> - facilityHistoryList.jsp</title>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileProfileReferralList" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|301">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.juvenileProfile" /> - Facility History List</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on a hyperlinked Admit Date/Time to view detention details.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader" />
</tiles:insert>
<%--juv profile header end--%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
<%-- BEGIN PROFILE TABS TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="facilityHistorytab" />
							<tiles:put name="juvnum"value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM)%>' />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66' align="right"><img src="/<msp:webapp/>images/spacer.gif" width="5"> <a href="#" id="historyLink"><bean:message key="prompt.facilityHistory" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
				</tr>
			</table>
<%-- END PROFILE TABS TABLE --%>	
<%-- BEGIN PROFILE TABS GREEN BORDER TABLE --%>	
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
					<div class='spacer'></div>
<%-- BEGIN FACILITY HISTORY DISPLAY BLCOK TABLE --%>							
					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
						<tr>
							<td class="detailHead">Facility History List <logic:equal name="assignedReferralsForm" property="isResidentialCasefile" value="true">&nbsp; &nbsp; See Journal or Activities tab for additional information.</logic:equal></td>
						</tr>
						<tr>
							<td>
<%-- BEGIN FACILITY HISTORY DETAILS LIST TABLE --%>							
								<table cellpadding="2" cellspacing="1" border="0" width='100%'>
									
									<logic:empty name="assignedReferralsForm" property="facilityHistoryList">
										<tr bgcolor='#cccccc'>
											<td colspan="5" class='subHead'>No Facility History Available</td>
										</tr>
									</logic:empty>

									<logic:notEmpty name="assignedReferralsForm" property="facilityHistoryList">
										<tr bgcolor='#cccccc'>
											<td class='subHead' width='1%' nowrap="nowrap"><bean:message key="prompt.referral" /></td>
											<td class='subHead' nowrap="nowrap">(<bean:message key="prompt.plusSign" />)</td>
											<td class='subHead' width='1%' nowrap="nowrap">
												<bean:message key="prompt.facilityName" />
											</td>
											<td class='subHead'>Admit <bean:message key="prompt.reason" /></td>												
											<td class='subHead'><bean:message key="prompt.admitDateTime" /></td>
											<td class='subHead'><bean:message key="prompt.releaseDateTime" /></td>
											<td class='subHead'><bean:message key="prompt.releasedTo" /></td>
											<td class='subHead' ><bean:message key="prompt.releasedBy" /></td>
											<td class='subHead' ><bean:message key="prompt.releasedAuthority" /></td>
											<td class='subHead'><bean:message key="prompt.locker" /> <bean:message key="prompt.number" /></td>
											<td class='subHead'><bean:message key="prompt.receipt" /> <bean:message key="prompt.number" /></td>
										</tr>
										<logic:iterate indexId="index" id="facilityHistory" name="assignedReferralsForm" property="facilityHistoryList">
											<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
												<td><bean:write name="facilityHistory" property="referralNumber" /></td>
												<td><logic:notEmpty name="facilityHistory" property="originalAdmitDate">+</logic:notEmpty></td>
												<td>
													<span title='<bean:write name="facilityHistory" property="detainedFacilityDesc"/>'><bean:write name="facilityHistory" property="detainedFacility" /></span>
												</td>													
												<td>	
													<span style="padding:0; margin:0" title="<bean:write name="facilityHistory" property="admitReasonDesc" />">												
														<bean:write name="facilityHistory" property="admitReason" />	
													</span>- <span style="padding:0; margin:0"><bean:write name="facilityHistory"	property="secureStatus" /></span>													
												</td>
												<td>
													<logic:notEmpty name="facilityHistory" property="admitDate">
													<a href="javascript:newCustomWindow('/JuvenileCasework/displayJuvenileProfileReferralList.do?submitAction=View&detentionId=<bean:write name="facilityHistory" property="detentionId"/>','viewDetails',1000,800);">
														<bean:write name="facilityHistory" property="admitDate" formatKey="date.format.mmddyyyy"/>
														<bean:write name="facilityHistory" property="admitTime"  />
													</a>
													</logic:notEmpty>
												</td>
												<td>
													<logic:notEmpty name="facilityHistory" property="releaseDate">
														<bean:write name="facilityHistory" property="releaseDate" formatKey="date.format.mmddyyyy"/>
														<bean:write name="facilityHistory" property="releaseTime" />
													</logic:notEmpty>
												</td>
												<td><logic:notEmpty name="facilityHistory" property="releaseTo"><span title="<bean:write name="facilityHistory" property="relToDesc"/>"><bean:write name="facilityHistory" property="releaseTo"/></span></logic:notEmpty></td>
												<td><span title="<bean:write name="facilityHistory" property="relByDesc"/>"><bean:write name="facilityHistory" property="releaseBy" /></span></td>
												<td><span title="<bean:write name="facilityHistory" property="relAuthByDesc"/>"><bean:write name="facilityHistory" property="releaseAuthorizedBy" /></span></td>
												<td><bean:write name="facilityHistory" property="lockerNumber" /></td>
												<td><bean:write name="facilityHistory" property="receiptNumber" /></td>
											</tr>
											<tr>
													<td colspan="12"><logic:notEmpty name="facilityHistory" property="activitiesByDetention">
																<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue"> 
																	<tr> 
																		<td width="1%" class="detailHead"><a href="javascript:showHideMulti('activity<bean:write name="index"/>', 'pchar<bean:write name="facilityHistory" property="detentionId"/>', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="activity<bean:write name="index"/>"></a></td> 
																		<td class="detailHead" nowrap='nowrap' align='left' valign='top'>Activities</td>
																	</tr> 
																	<tr id="pchar<bean:write name="facilityHistory" property="detentionId"/>0" class='hidden'>
																		<td valign="top" align="center" colspan="2"> 
																			<table cellpadding="4" cellspacing="1" width="100%"> 
																				<tr>																					
																					<td valign='middle' class='formDeLabel' width="2%">Date<jims:sortResults beanName="facilityHistory" results="activitiesByDetention" primaryPropSort="activityDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
																					</td>
																					<td valign='middle' class='formDeLabel' width="2%">Time<%-- <jims:sortResults beanName="facilityHistory" results="activitiesByDetention" primaryPropSort="time" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2"/> --%></td>
																					<td valign='middle' class='formDeLabel' width="3%">Activity ID<jims:sortResults beanName="facilityHistory" results="activitiesByDetention" primaryPropSort="activityId" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3"/> </td>
																					<td valign='middle' class='formDeLabel' width="8%">Activity<jims:sortResults beanName="facilityHistory" results="activitiesByDetention" primaryPropSort="activityDesc" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4"/>
																					</td>
																				</tr>				                                    
																				<logic:iterate id="listActivities" name="facilityHistory" property="activitiesByDetention" indexId="index">
																					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																						<td><bean:write name="listActivities" property="activityDate" formatKey="date.format.mmddyyyy"/></td>
																						<td><bean:write name="listActivities" property="time"/></td>	                  					
																						<td><a href="/<msp:webapp/>displayActivitySummary.do?submitAction=<bean:message key='button.linkActivity'/>&activityId=<bean:write name='listActivities' property='activityId' />"><bean:write name="listActivities" property="activityId"/></a></td>
																						<td><bean:write name="listActivities" property="activityDesc"/></td>
																					</tr>
																				</logic:iterate>		                                      
																			</table>
																		</td> 
																	</tr>																	
																</table>
													</logic:notEmpty></td>
												
										</tr>					
										</logic:iterate>
									</logic:notEmpty>
								</table>
<%-- END FACILITY HISTORY DETAILS LIST TABLE --%>										
							</td>
						</table>
<%-- END FACILITY HISTORY DISPLAY BLCOK TABLE --%>						
						<div class='spacer'></div>						
<%-- BEGIN BUTTON TABLE --%>
						<table border="0" width="100%" align="center">
							<tr>
								<td align="center">
									<html:button property="org.apache.struts.taglib.html.BUTTON" styleId="facHistListBack">
										<bean:message key="button.back"></bean:message>
									</html:button>
									<input type="button" value="Cancel" id="facHistListCancel" data-location="/<msp:webapp/>displayJuvenileMasterInformation.do">
								</td>
							</tr>
						</table>
<%-- END BUTTON TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<%-- END PROFILE TABS GREEN BORDER TABLE --%>				
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%> 
</html:form>
<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>