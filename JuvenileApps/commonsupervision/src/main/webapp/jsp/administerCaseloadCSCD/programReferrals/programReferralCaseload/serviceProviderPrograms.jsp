<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 10/09/2009 RYoung - #62156 Filter Caseload - sort search results by Name -->
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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralCaseload/serviceProviderPrograms.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>

	function onProgramViewCaseload(theForm)
	{
		var isProgramSelected = false;
		
		var selectedProgElements = document.getElementsByName('selectedId');
		for(var index=0; index < selectedProgElements.length; index++)
		{
			if(selectedProgElements[index].checked)
			{
				isProgramSelected = true;
				var url = "/<msp:webapp/>handleProgramReferralByCaseload.do?submitAction=View+Program+Caseload&selectedId=" + selectedProgElements[index].value;
				changeFormActionURL(theForm,url,false);
			}
		}
		if(isProgramSelected==false)
		{
			alert("Please select a Program.");
			return false;
		}
	}


	function validateOnViewLocations()
	{
		var isProgramSelected = false;
		
		var selectedSPElements = document.getElementsByName('selectedId');
		for(var index=0; index < selectedSPElements.length; index++)
		{
			if(selectedSPElements[index].checked)
			{
				isProgramSelected = true;
			}
		}
		if(isProgramSelected == false)
		{
			alert("Please select a Program.");
			return false;
		}
	}

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("selectedId");
		if (rbs.length == 1){
			rbs[0].checked = true;
		}	
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
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.filterCaseload"/>-&nbsp;
																  <bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="prompt.programs"/>&nbsp;</td>
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
										<li>Select a Program. Click View Caseload to view all supervisees in the current caseload with program referrals to the selected program.</li>
										<li>Click View Locations to view all locations where the selected program is offered.</li>
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
												<tr>
													<td class="headerLabel"><bean:message key="prompt.serviceProvider"/></td>
													<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="spName"/></td>
												</tr>
											</table>
											<!--header end-->
										</td>
									</tr>
								</table>
							<!--header area end-->	
							
							<div class=spacer4px></div>
							
							<!-- BEGIN DETAIL TABLE -->
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.programs" /></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1" id="uniqueID">
											<tr class="formDeLabel">
												<td width="1%"></td>
												<td><bean:message key="prompt.identifier" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="programsList" primaryPropSort="programIdentifier" primarySortType="STRING"  defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4"/></td>
												<td><bean:message key="prompt.name" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="programsList" primaryPropSort="programName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="4"/></td>
												<td><bean:message key="prompt.CSTSCode" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="programsList" primaryPropSort="cstsCode" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="4"/></td>
												<td><bean:message key="prompt.referralType" /><jims2:sortResults beanName="cscProgRefCaseloadForm" results="programsList" primaryPropSort="referralTypeDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" levelDeep="4"/></td>
											</tr>
											<logic:iterate id="eachProgram" name="cscProgRefCaseloadForm" property="programsList" indexId="index11">
												<tr class="<%out.print((index11.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
													<bean:define id="selectedProgramId" type="java.lang.String" name="eachProgram" property="programId" />
													<td width="1%"><input type="radio" name="selectedId" value="<%=selectedProgramId%>"/></td>
													<td><a href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Link&selectedValue=<bean:write name='eachProgram' property='programId'/>&serviceProviderId=<bean:write name='eachProgram' property='serviceProviderId'/>"><bean:write name="eachProgram" property="programIdentifier"/></a> </td>
													<td><bean:write name="eachProgram" property="programName"/></td>
													<td><bean:write name="eachProgram" property="cstsCode" /></td>
													<td><bean:write name="eachProgram" property="referralTypeDesc" /></td>
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
										<input type="submit" value="<bean:message key='button.viewCaseload'/>" name="submitAction" onclick="return onProgramViewCaseload(this.form);" >
										<html:submit property="submitAction" onclick="return validateOnViewLocations();"> <bean:message key="button.viewLocations" /></html:submit>
									</td>
								</tr>
								<tr>
									<td align="center">
										<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
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