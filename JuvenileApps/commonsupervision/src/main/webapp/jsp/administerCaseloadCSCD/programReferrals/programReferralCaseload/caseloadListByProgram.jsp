<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 11/19/2009 C Shimek          - #62124 removed nowrap from Referral Type so description would wrap to match PT -->
<!-- 11/20/2009 C Shimek          - #62124 revised Show Exited Program href -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.Features" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.CSAdministerProgramReferralsConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralCaseload/caseloadListByProgram.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>
	function validate()
	{
		var isPgmRefSelected = false;
		
		var selPgmRefElements = document.getElementsByName('selectedId');
		for(var index=0; index < selPgmRefElements.length; index++)
		{
			if(selPgmRefElements[index].checked)
			{
				isPgmRefSelected = true;
				break;
			}
		}
		if(isPgmRefSelected==false)
		{
			alert("Please select a Program Referral.");
			return false;
		}
	}

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("selectedId");
		if (rbs.length == 1){
			rbs[0].checked = true;
		}	
	}
	function validateSelect()
	{
		var selPgmRefElements = document.getElementsByName('selectedId');
		for(var index=0; index < selPgmRefElements.length; index++)
		{
			if(selPgmRefElements[index].checked)
			{
				document.forms[0].submit();
				return true;
			}
		}
		alert("Please select a Program Referral.");
		return false;
	}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.caseloadBy"/>&nbsp;
																  <bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.provider"/>&nbsp;</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>								
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Select a supervisee. Click View Program Referral to view Program Referral details.</li>
										<li>Click the Supervisee's name to view detailed information about that supervisee.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						
						<html:form action="/handleProgramReferralByCaseload" target="content">
							<!--header area start-->
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr class="paddedFourPix">
										<td class="formDeLabel"><bean:message key="prompt.currentCaseload" /></td>
									</tr>
									<tr>
										<td bgcolor="#cccccc" colspan="2">
											<!--header start-->
											<table width="100%" border="0" cellpadding="2" cellspacing="1">
												<tr>
													<td class="headerLabel"><bean:message key="prompt.officer"/></td>
													<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="officerNamePosition"/></td>
												</tr>
												<logic:equal name="cscProgRefCaseloadForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_VIEW_SP_PRGREF_CASELD%>">
													<tr>
														<td class="headerLabel"><bean:message key="prompt.serviceProvider"/></td>
														<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="spName" /></td>
													</tr>
												</logic:equal>
												<logic:equal name="cscProgRefCaseloadForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_VIEW_PGM_NAME_PRGREF_CASELD%>">
													<tr>
														<td class="headerLabel"><bean:message key="prompt.program"/></td>
														<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="progName"/></td>
													</tr>
												</logic:equal>
												<logic:equal name="cscProgRefCaseloadForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_VIEW_PGM_PRGREF_CASELD%>">
													<tr>
														<td class="headerLabel"><bean:message key="prompt.serviceProvider"/></td>
														<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="spName" /></td>
													</tr>
													<tr>
														<td class="headerLabel"><bean:message key="prompt.program"/></td>
														<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="progIdentifier"/>&nbsp;|&nbsp;<bean:write name="cscProgRefCaseloadForm" property="progName"/></td>
													</tr>
												</logic:equal>
												<logic:equal name="cscProgRefCaseloadForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_VIEW_LOC_PRGREF_CASELD%>">
													<tr>
														<td class="headerLabel"><bean:message key="prompt.serviceProvider"/></td>
														<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="spName" /></td>
													</tr>
													<tr>
														<td class="headerLabel"><bean:message key="prompt.program"/></td>
														<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="progIdentifier"/>&nbsp;|&nbsp;<bean:write name="cscProgRefCaseloadForm" property="progName"/></td>
													</tr>
													<tr>
														<td class="headerLabel"><bean:message key="prompt.location"/></td>
														<td class="headerData">
															<div>
																<bean:write name="cscProgRefCaseloadForm" property="streetNumber" />
	                                                            <bean:write name="cscProgRefCaseloadForm" property="streetName" />
	                                                            <bean:write name="cscProgRefCaseloadForm" property="streetTypeCd" />
	                                                            <bean:write name="cscProgRefCaseloadForm" property="aptNum" />
                                                            </div>
	                                                       	<div>
		                                                       	<bean:write name="cscProgRefCaseloadForm" property="city" />
	                                                            <bean:write name="cscProgRefCaseloadForm" property="state" />
	                                                            <bean:write name="cscProgRefCaseloadForm" property="zipCode" />
                                                            </div>
														</td>
													</tr>
												</logic:equal>	
											</table>
											<!--header end-->
										</td>
									</tr>
								</table>
							<!--header area end-->	
							
							<div class=spacer4px></div>
							
							<!-- BEGIN DETAIL TABLE -->
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center">
										Supervisee Count: <b><bean:write name="cscProgRefCaseloadForm" property="superviseeSize" /></b>
									</td>
									<td align="center">
										Active Referrals: <b><bean:write name="cscProgRefCaseloadForm" property="activeReferrals" /></b>
									</td>
									<td align="center">
										Exited Referrals: <b><bean:write name="cscProgRefCaseloadForm" property="exitedReferrals" /></b>
									</td>
								</tr>								
							</table>
							
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.caseloadSupervisees"/></td>
								</tr>		
								<tr>
									<td colspan=2>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td width="1%"></td>
												<td nowrap><bean:message key="prompt.superviseeName" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="superviseeName" primarySortType="STRING"  defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4"/></td>
												<td><bean:message key="prompt.SPN" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="defendantId" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="4"/></td>
												<td><bean:message key="prompt.case" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="caseNumber" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="4"/></td>
												<td><bean:message key="prompt.referralType" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="referralTypeDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" levelDeep="4"/></td>
												<td><bean:message key="prompt.programId" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="programIdentifier" primarySortType="STRING"  defaultSortOrder="ASC" sortId="5" levelDeep="4"/></td>
												<td title="Program Referral Date"><bean:message key="prompt.referralDt" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="referralDateAsStr" primarySortType="STRING"  defaultSortOrder="ASC" sortId="6" levelDeep="4"/></td>
												<td title="Program Begin Date"><bean:message key="prompt.beginDt" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="referralBeginDateAsStr" primarySortType="STRING"  defaultSortOrder="ASC" sortId="7" levelDeep="4"/></td>
												<td title="Program End Date"><bean:message key="prompt.endDt" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="referralsList" primaryPropSort="referralEndDateAsStr" primarySortType="STRING"  defaultSortOrder="ASC" sortId="8" levelDeep="4"/></td>
											</tr>
											<logic:iterate id="eachprogramReferral" name="cscProgRefCaseloadForm" property="referralsList" indexId="index11">
												<tr class="<%out.print((index11.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
													<bean:define id="selReferralId" type="java.lang.String" name="eachprogramReferral" property="programReferralId" />
													<td width="1%"><input type="radio" name="selectedId" value="<%=selReferralId%>"/></td>
													<td nowrap><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name='eachprogramReferral' property='defendantId'/>"><bean:write name="eachprogramReferral" property="superviseeName"/></a></td>
													<td><bean:write name="eachprogramReferral" property="defendantId"/></td>
													<td><a href="javascript:openWindow('/<msp:webapp/>handleProgramReferralByCaseload.do?submitAction=View Order Versions&selectedCriminalCaseId=<bean:write name="eachprogramReferral" property="criminalCaseId"/>')"><bean:write name="eachprogramReferral" property="caseNumber" /></a></td>
													<td><bean:write name="eachprogramReferral" property="referralTypeDesc" /></td>
													<td nowrap><a href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Link&selectedValue=<bean:write name='eachprogramReferral' property='programId'/>&serviceProviderId=<bean:write name='eachprogramReferral' property='serviceProviderId'/>"><bean:write name="eachprogramReferral" property="programIdentifier" /></a></td>
													<td><bean:write name="eachprogramReferral" property="referralDateAsStr" /></td>
													<td><bean:write name="eachprogramReferral" property="referralBeginDateAsStr" /></td>
													<td><bean:write name="eachprogramReferral" property="referralEndDateAsStr" /></td>
												</tr>
											</logic:iterate>
										</table>	
									</td>
								</tr>						
							</table>
							
							<!-- END DETAIL TABLE -->	
							
							<br>
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return validate();"><bean:message key="button.viewPgmReferral" /></html:submit>
									</td>
								</tr>
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</table>
							<!-- END BUTTON TABLE -->
							
						</html:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>			
</table>	
</div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>