<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 12/06/2017	UGopinath	Create JSP --%>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>




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
<title><bean:message key="title.heading" /> - facilityHistoryPopUp.jsp</title>


<script type="text/javascript">
$(function () {
	window.onbeforeunload  = function (e) {		
		var referralWinStatus = localStorage.getItem("referralWin");	
		localStorage.setItem("facilityHistWin", "close");
		localStorage.setItem("juvnum", "");
	//	$(window).off("beforeunload");
		};

});

 </script>
 
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


<%--juv profile header start--%>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="98%">
	<tr>
		<td bgcolor='#cccccc'>
			<logic:notEmpty name="juvenileProfileHeader" property="juvenileNum">
				<table width="100%" border="0" cellpadding="2" cellspacing="1">				
           			<tr class=bodyText>
             				<td class="formDeLabel" width="30%" nowrap><bean:message key="prompt.profile" /> <bean:message key="prompt.info" />&nbsp;&nbsp;       			  		
        			  		</td>           			
             				<td class="formDeLabel" nowrap align="right">
              					JPO Of Record - <span title='<bean:write name="juvenileProfileHeader" property="jpoOfRecord"/>'><bean:write name="juvenileProfileHeader" property="jpoOfRecID"/></span></a> 
             				</td>
           			</tr>
           		</table>
           		<table width="100%" border="0" cellpadding="2" cellspacing="1">
  					<tr>
  						<td class="headerLabel" width="16%"><bean:message key="prompt.juvenile" />&nbsp;#</td>
  						<td class="headerData" 	width="27%"><logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><font color="red"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></font></logic:equal>
  						<logic:notEqual name="juvenileProfileHeader" property="restrictedAccess" value="true"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></logic:notEqual></td>
  						<td class="headerLabel" width="16%"><bean:message key="prompt.juvenileName" /> </td>
  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="juvenileName"/> 
  						<logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><span title='RESTRICTED ACCESS'><font color="red">(RESTRICTED)</font></span></logic:equal></td>
  					</tr>
  				</table>
  					<table width="100%" border="0" cellpadding="2" cellspacing="1">
  					<tr>
  						<td class="headerLabel" nowrap width="16%"><bean:message key="prompt.currentAge" /></td>
  						<td class="headerData" width="27%">	  						
  						  <div style="float:left;width:50%;"><bean:write name="juvenileProfileHeader" property="age"/></div>
						  <div style="float:right;width:50%;"><bean:message key="prompt.dob" />:
  								<bean:write name="juvenileProfileHeader" property="dateOfBirth"/></div>
  						</td>
  						<td class="headerLabel" nowrap width="16%"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.status" /></td>
  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="status"/>&nbsp;&nbsp;&nbsp;
  						<logic:notEmpty name="juvenileProfileHeader" property="detentionFacilityId">
    						<logic:notEqual name="juvenileProfileHeader" property="detentionFacilityId" value="">
	      						<bean:write name="juvenileProfileHeader" property="detentionFacilityId"/>:
	      						<bean:write name="juvenileProfileHeader" property="detentionStatusId"/>
    						</logic:notEqual>
  						</logic:notEmpty>
  						</td>
  					</tr>
  				</table>
			</logic:notEmpty>

			<logic:empty name="juvenileProfileHeader" property="juvenileNum">
  				<table width="100%" border="0" cellpadding="2" cellspacing="1">
  					<tr>
  						<td class="headerLabel" width="100%" align="center">Error:  No juvenile profile supplied on the request.</td>
  					</tr>
  				</table>				
			</logic:empty>		
		</td>
	</tr>
</table>		
<%--juv profile header end--%>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>

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
											<td class='subHead' width='1%' nowrap="nowrap"><bean:message key="prompt.referral" /> #</td>
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
													
														<bean:write name="facilityHistory" property="admitDate" formatKey="date.format.mmddyyyy"/>
														<bean:write name="facilityHistory" property="admitTime"  />
													
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
												<td><bean:write name="facilityHistory" property="releaseAuthorizedBy" /></td>
												<td><bean:write name="facilityHistory" property="lockerNumber" /></td>
												<td><bean:write name="facilityHistory" property="receiptNumber" /></td>
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
									<html:button property="submitAction" onclick="window.close();"><bean:message key="button.close"/></html:button>   
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