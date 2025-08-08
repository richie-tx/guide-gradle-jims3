<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 09/04/2012 C Shimek    #74125 revise display layout from Referral number concentric to Admit Date/Time concentric --%>
<%-- 08/21/2015     RCapestani #29399 MJCW: Adapt MJCW and Warrants to IE10 and 11 (Casefile Referrals UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<title><bean:message key="title.heading"/> - facilityHistoryList.jsp</title>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayJuvenileProfileReferralList" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|301">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
  		<td align="center" class="header">
  			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/>
  	        <bean:message key="prompt.facility"/>&nbsp;<bean:message key="prompt.history"/>&nbsp;<bean:message key="title.list"/>
        </td>	  	    	 
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
		<%-- 		<li>Click on a hyperlinked Referral # to view Referral details.</li>  --%>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 

<div class="spacer"></div>
<%-- BEGIN TABS AND DETAILS TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
		<td valign="top"> 
		<%--tabs start--%> 
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="assignedreferralstab"/>
				<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
			</tiles:insert>				
	    <%--tabs end--%>
		</td>
	</tr>
	<tr>	
		<td>
<%-- BEGIN CASEFILE TABS BORDER TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
<%-- BEGIN REFERRAL TABS TABLE --%>					
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true"></tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
							</tr>
						</table>
<%-- END REFERRAL TABS TABLE --%>	
<%-- BEGIN REFERRAL TABS BORDER TABLE --%>	
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">			
							<tr>
								<td valign="top" align="center"> 			
									<div class="spacer"></div>
<%-- BEGIN FACILITY LIST TABLE --%>										
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead" align="center">Facility History List <logic:equal name="juvenileCasefileForm" property="isResidentialCasefile" value="true">&nbsp; &nbsp; See Journal or Activities tab for additional information.</logic:equal></td>
										</tr>
										<tr>
											<td>
<%-- BEGIN FACILITY LIST DETAILS TABLE --%>										
												<table cellpadding="2" cellspacing="1" border="0" width='100%'>
													<logic:empty name="juvenileCasefileForm" property="juvenileFacilityList">
														<tr bgcolor='#cccccc'>
															<td align='left' colspan="5" class='subHead'>No Facility History Available</td>
														</tr>
													</logic:empty>
													<logic:notEmpty name="juvenileCasefileForm" property="juvenileFacilityList">
														<tr bgcolor='#cccccc'>
															<td align='left' class='subHead' width='1%' nowrap="nowrap"><bean:message key="prompt.referral" /> #</td>
															<td align='left' class='subHead' nowrap="nowrap" align="center">(<bean:message key="prompt.plusSign" />)</td>
															<td align='left' class='subHead' width='1%' nowrap="nowrap"><bean:message key="prompt.facilityName" /></td>
															<td align='left' class='subHead'>Admit <bean:message key="prompt.reason" /></td>												
															<td align='left' class='subHead'><bean:message key="prompt.admitDateTime" /></td>
															<td align='left' class='subHead'><bean:message key="prompt.releaseDateTime" /></td>
															<td align='left' class='subHead'><bean:message key="prompt.releasedTo" /></td>
														</tr>
														<logic:iterate id="facility" name="juvenileCasefileForm" property="juvenileFacilityList" indexId="index1">
															<tr class="<%out.print( (index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																<td><bean:write name="facility" property="referralNumber" /></td>
																<td>
																	<logic:empty name="facility" property="originalAdmitDate" ><span title='Original Placement'>&nbsp;+</span></logic:empty>
																</td>
																<td><span title='<bean:write name="facility" property="detainedFacilityDesc"/>'><bean:write name="facility" property="detainedFacility" /></span></td>
																<td><bean:write name="facility" property="admitReason" />-<bean:write name="facility" property="secureStatus" /></td>
																<td>
																	<logic:notEmpty name="facility" property="admitDate">
																	<a href="javascript:newCustomWindow('/JuvenileCasework/displayJuvenileCasefileAssignedReferralsList.do?submitAction=View&detentionId=<bean:write name="facility" property="detentionId"/>','viewDetails',1000,800);">
																		<bean:write name="facility" property="admitDate" formatKey="date.format.mmddyyyy"/>
																		<bean:write name="facility" property="admitTime"  />
																	</a>
																		
																	</logic:notEmpty>
																</td>
																<td>
																	<logic:notEmpty name="facility" property="releaseDate">
																		<bean:write name="facility" property="releaseDate" formatKey="date.format.mmddyyyy"/>
																		<bean:write name="facility" property="releaseTime" />
																	</logic:notEmpty>
																</td>
																<td><logic:notEmpty name="facility" property="releaseTo"><span title="<bean:write name="facility" property="relToDesc"/>"><bean:write name="facility" property="releaseTo" /></span></logic:notEmpty></td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
<%-- END FACILITY LIST DETAILS TABLE --%>															
												<div class="spacer"></div>
											</td>
										</tr>
									</table>
<%-- END FACILITY LIST TABLE --%>	
									<div class="spacer"></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%" align="center">
										<tr>
											<td align="center">
												<%--html:submit property="submitAction"><bean:message key="button.back"/></html:submit--%>
												<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="goNav('back')">
													<bean:message key="button.back"></bean:message>
												</html:button>
												<%--html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit--%>
												<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do')">
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>							
									<div class="spacer"></div>
								</td>
							</tr>
						</table>
<%-- END REFERRAL TABS BORDER TABLE --%>
						<div class="spacer"></div>			
					</td>
				</tr>
			</table>
<%-- END CASEFILE TABS BORDER TABLE --%>					
		</td>
	</tr>
</table>			
<%-- END TABS AND DETAILS TABLE --%>
</html:form> 
<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>