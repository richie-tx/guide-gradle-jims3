<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/31/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 10/13/2010 R. Capestani      - #65440 hide comments box for CSCD VIP forms -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.UIConstants"%>
<%@ page import="naming.CSAdministerProgramReferralsConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prFormFieldsSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="type/javascript" src="/<msp:webapp/>js/sorttable.js"></script>
<script>
	function setComments(){
		var fld2 = document.getElementsByName("referralFormName");
	     if (fld2[0].value.indexOf("CSCD VIP") == 0){
	           show("flds", 0);
	     }     
	}
</script>
</head>
<body topmargin="0" leftmargin="0" onload="setComments()" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitReferralForm" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|25">
<html:hidden name="cscProgRefForm" property="referralFormName" />
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
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
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
						<!--header area start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<!--header start-->
										<tiles:insert page="../../common/caseloadHeaderCase.jsp" flush="true">
										</tiles:insert>	
									<!--header end-->
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<!--header area end-->
						<!--casefile tabs start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
										<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
											<tiles:put name="tab" value="ProgramReferralsTab" />
										</tiles:insert>
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;- 
											<bean:message key="title.programReferral"/>&nbsp;<bean:message key="prompt.form"/>&nbsp;
											<logic:notEqual name="cscProgRefForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
												<bean:message key="title.summary"/>
											</logic:notEqual>	
											<logic:equal name="cscProgRefForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
												<bean:message key="title.confirmation"/>
											</logic:equal>	
											</td>
										</tr>
                                    <logic:equal name="cscProgRefForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
										<tr>
											<td class="confirm">
												Program Referral form information successfully saved.
											</td>
										</tr>
                                    </logic:equal>        
									</table>
									
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									
									<logic:equal name="cscProgRefForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
										<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
											<tr>
												<td>
													<ul>
														<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
													</ul>
												</td>
											</tr>
										</table>
									</logic:equal>
									<!-- END HEADING TABLE -->
									
									<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_GENERATE_FORM %>">
										<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td width="1%"><a href="javascript:showHide('pr', 'row', '/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="pr"></a></td>
												<td  class="detailHead"><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
											</tr>
											<tr id="prSpan" class="hidden">
												<td colspan="2">
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.referralType"/></td>
															<td class="formDe">
																<bean:write name="cscProgRefForm" property="referralTypeDesc"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>							
									</logic:notEqual>
								
									<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_GENERATE_FORM %>">		
									<!--Program Referral Information start-->
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<!--Program Referral Information tile start-->
												<tiles:insert page="programReferralInformationTile.jsp" flush="true">
												</tiles:insert> 
												<!--Program Referral Information tile end-->
											</td>
										</tr>
									</table>
									<!--Program Referral Information end-->
									</logic:equal>			
									
									<div class="spacer4px"></div>
									
									<span id="flds" class="visible">
									<!--Referral Form Fields start-->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:write name="cscProgRefForm" property="referralFormName"/>&nbsp;<bean:message key="prompt.info"/></td>
											<td align="right"><a href="javascript:openWindow('/<msp:webapp/>displayProgRefCasenote.do?submitAction=View&selectedValue=<bean:write name="cscProgRefForm" property="progRefId" />')">View Program Referral Casenotes</a></td>
										</tr>
										<tr>
											<td colspan="2">
												<table width='100%' cellpadding="4" cellspacing="1" border="0">
													<jims2:csreferralforms name="cscProgRefForm" property="referralformFieldsList" type="summary" columns="1"/>	          										
												</table>												
											</td>
										</tr>										
									</table>
									<!--Referral Form Fields end-->
									</span>
								
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<logic:equal name="cscProgRefForm" property="secondaryAction" value="<%=UIConstants.SUMMARY%>">
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</logic:equal>
												<logic:equal name="cscProgRefForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
													<html:button property="submitAction" onclick="openWindow('handleReferralForm.do?submitAction=Print')"><bean:message key="button.printForm" /></html:button>
													<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_GENERATE_FORM %>">
														<html:submit property="submitAction"><bean:message key="button.referralList" /></html:submit> 
													</logic:equal>
													<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_GENERATE_FORM %>">
														<html:submit property="submitAction"><bean:message key="button.backToSelectServiceProvider" /></html:submit> 
													</logic:notEqual>
												</logic:equal>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<div class="spacer4px"></div>
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<br>
<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
