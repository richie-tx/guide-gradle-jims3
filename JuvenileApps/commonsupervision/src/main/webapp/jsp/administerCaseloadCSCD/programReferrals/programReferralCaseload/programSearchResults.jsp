<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/22/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralCaseload/programSearchResults.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script>

	var action = "";
	
	function onProgramViewCaseload(theForm)
	{
		var isProgramSelected = false;
		
		var selectedPgmElems = document.getElementsByName('selectedId');
		for(var index=0; index < selectedPgmElems.length; index++)
		{
			if(selectedPgmElems[index].checked)
			{
				isProgramSelected = true;
				var url = "";
				if(action == "programName")
				{
					url = "/<msp:webapp/>handleProgramReferralByCaseload.do?submitAction=View+ProgramName+Caseload&selectedProgramName=" + selectedPgmElems[index].value;
				}
				else if(action == "programId")	
				{
					url = "/<msp:webapp/>handleProgramReferralByCaseload.do?submitAction=View+Program+Caseload&selectedId=" + selectedPgmElems[index].value;
				}
				changeFormActionURL(theForm,url,false);
			}
		}
		if(isProgramSelected==false)
		{
			alert("Please select a Program Name / ID.");
			return false;
		}
	}


	function setAction(theAction)
	{
		action = theAction;
	}

	function checkForSingleResult() {
	    var rbs = document.getElementsByName("selectedId");
		if (rbs.length == 1){
			action = "programName";
			rbs[0].checked = true;
		}	
	}
	
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">
<html:form action="/handleProgramReferralByCaseload" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/CSCD_Caseload.htm#|5">
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
																  <bean:message key="prompt.program"/>&nbsp;<bean:message key="title.searchResults"/>&nbsp;</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						
						<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>		
						<logic:present name="errorMsg">
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><bean:write name="errorMsg"/></td>
								</tr>
							</table>		
						</logic:present>							
						<!-- END ERROR TABLE -->
						
						<logic:notPresent name="errorMsg">
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>To view filtered caseload, select a program name or individual identifier, and click View Caseload.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						
						<!--header area start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td bgcolor="#cccccc" colspan="2">
										<!--header start-->
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="headerLabel"><bean:message key="prompt.officer"/></td>
												<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="officerNamePosition"/></td>
											</tr>
										</table>
										<!--header end-->
									</td>
								</tr>
							</table>
						<!--header area end-->	
						
						<div class="spacer4px"></div>
						
						<!-- BEGIN DETAIL TABLE -->
						<table width="98%" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center">
									<b><bean:write name="cscProgRefCaseloadForm" property="programNamesSize" /></b> search results found
								</td>
							</tr>								
						</table>
						
						<!-- BEGIN PROGRAM TABLE -->
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td><bean:message key="prompt.programs" /></td>
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class="formDeLabel">
											<td width="1%"></td>
											<td><bean:message key="prompt.programNameID" /></td>
											<td><bean:message key="prompt.serviceProvider" /></td>
											<td><bean:message key="prompt.CSTSCode" /></td>
											<td><bean:message key="prompt.referralType" /></td>
										</tr>
										<nested:iterate id="eachProgramName" name="cscProgRefCaseloadForm" property="programNamesList" indexId="index11">
											<tr class="<%out.print((index11.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<bean:define id="selectedProgramName" type="java.lang.String" name="eachProgramName" property="programName" />
												<td width="1%"><input type="radio" name="selectedId" value="<%=selectedProgramName%>" onclick="setAction('programName');"/></td>
												<td colspan="4" nowrap="nowrap"><bean:write name="eachProgramName" property="programName"/></td>
											</tr>
											<logic:iterate id="eachProgram" name="eachProgramName" property="programIdsBeanList">
												<tr class="<%out.print((index11.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td width="1%"></td>
												<bean:define id="selectedProgramId" type="java.lang.String" name="eachProgram" property="programId" />
												<td><input type="radio" name="selectedId" value="<%=selectedProgramId%>" onclick="setAction('programId');"/>
												<a href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Link&selectedValue=<bean:write name='eachProgram' property='programId'/>&serviceProviderId=<bean:write name='eachProgram' property='serviceProviderId'/>"><bean:write name="eachProgram" property="programIdentifier"/></a></td>
												<td><a href="/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View&selectedValue=<bean:write name='eachProgram' property='serviceProviderId'/>"><bean:write name="eachProgram" property="serviceProviderName"/></a></td>
												<td><bean:write name="eachProgram" property="cstsCode" /></td>
												<td><bean:write name="eachProgram" property="referralTypeDesc" /></td>
												</tr>
											</logic:iterate>
										</nested:iterate>
									</table>
								</td>
							</tr>
						</table>
						<!-- END PROGRAM TABLE-->
						<!-- END DETAIL TABLE -->	
						<br>
						</logic:notPresent>
						
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<logic:notPresent name="errorMsg">
								<tr>
									<td align="center">
										<input type="submit" value="<bean:message key='button.viewCaseload'/>" name="submitAction" onclick="return onProgramViewCaseload(this.form);" >
									</td>
								</tr>
							</logic:notPresent>
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
							
					</td>
				</tr>
			</table>
		</td>
	</tr>			
</table>	
</div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:form>
</body>
</html:html>