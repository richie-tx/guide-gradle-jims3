<!--MODIFICATIONS -->
<!-- 11/11/2009 CShimek     - #62579 added please select to court drop down list and needed validation script -->
<!-- 08/19/2010 CShimek     - #66961 added onload script to preSelect Court value -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
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
	<title>CommonSupervision/administerCaseload/administerCaseloadReassignToCLOSelectCRT.jsp</title>

	<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript">
		function hideButton(){
			show("Next", 0);
		}

		function showButton(){
			show("Next", 1);
		}
		function validateSelect(theForm){
			if (theForm.reassignedCourtId.selectedIndex == 0){
				alert("Court selection required.");
				theForm.reassignedCourtId.focus();
				return false;
			}	
			return true; 
		}

		function checkForSingleResult() {
		    var rbs = document.getElementsByName("reassignedWorkGroupId");
			if (rbs.length == 1){
				rbs[0].checked = true;
				showButton();
			}	
		}	
		function setCourtSelection() {
			 var crt = document.getElementsByName("assignedCourt");
			 var sels = document.getElementsByName("reassignedCourtId");
			 sels[0].selectedIndex = 0;  // default in case no match found 
			 for (x=1; x< sels[0].options.length; x++){
				if (sels[0].options[x].value == crt[0].value){
					sels[0].selectedIndex = x;
					break;
				}
			 }	 
		}	
	</script>

</head>

<body topmargin="0" leftmargin="0" onload="hideButton(); checkForSingleResult(); setCourtSelection()">
	<html:form action="/reassignSuperviseeToCLOAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|19">
	<input type="hidden" name="assignedCourt" value="<bean:write name='caseAssignmentForm' property='courtNumber' />" /> 
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top">
						<table width=100% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
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
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;Reassign Supervisee to CLO
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

									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select a court and a workgroup.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->

									<!--header start-->
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert>
									<!--header end-->
									<br>

									<!-- begin search TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class="detailHead">
											<td>Reassign to CLO</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="0">
													<tr>
														<td class=formDeLabel width=1%><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9"><bean:message key="prompt.court" /></td>
														<td class=formDe>
															<html:select property="reassignedCourtId">
																<html:option value=""><bean:message key="select.generic" /></html:option> 
																<html:optionsCollection name="caseAssignmentForm" property="courts" value="courtId" label="courtId"/>
															</html:select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<div class="spacer4px"></div>
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
										<tr class="detailHead">
											<td><bean:message key="prompt.workgroups" /></td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.name" /></td>
														<td><bean:message key="prompt.description" /></td>
													</tr>
								                    <logic:iterate id="workGroup" name="caseAssignmentForm" property="courtServicesWorkGroups" indexId="index">
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<input type=radio name="reassignedWorkGroupId" value=<bean:write name="workGroup" property="workgroupId" /> onclick="showButton();"/>
															</td>
															<td>
																<a href="javascript:openWindow('/<msp:webapp/>handleWorkgroupSelection.do?submitAction=<bean:message key="button.details"/>&workgroupId=<bean:write name="workGroup" property="workgroupId"/>')">
																	<bean:write name="workGroup" property="workgroupName"/>
																</a>
															</td>
															<td><bean:write name="workGroup" property="workgroupDescription"/></td>
														</tr>
													</logic:iterate>
												</table>
											</td>
										</tr>
									</table>
									<!-- end search TABLE -->
									<br>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0">
										<tr align="center">
											<td>
												<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
											</td>
											<td id="Next">
												<html:submit property="submitAction" onclick="return validateSelect(this.form)"><bean:message key="button.next" /></html:submit>
											</td>
											<td>
												<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<!-- END DETAIL TABLE -->
						<br>
					</td>
				</tr>
			</table>
			<!-- END  TABLE -->
		</div>
		<br>
	</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>