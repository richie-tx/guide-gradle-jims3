<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 05/26/2010  Clarence Shimek   - #65373 revised to use tinyMCECustomInitCasenote.js -->
<!-- 05/06/2013  R.Young	 	  ER #75492 RapidSpellWeb file changesV3.2.0 for CS -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
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
<title>CommonSupervision/admininsterCaseload/administerCaseloadCasenoteCLOCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/CommonSupervision/js/tinyMCECustomInitCasenote.js"></script>
<script> 

function validateForm(theForm){
	clearAllValArrays();
	trimCasenote('casenoteText');
	customValRequired("casenoteText","Casenote Text is required","");
	customValMaxLength("casenoteText","Casenote Text cannot be more than 3500 characters","3500");
	addDefinedTinyMCEFieldMask("casenoteText","Casenote Text cannot have % or _ entries","");
	if (validateCustomStrutsBasedJS(theForm) ){
		return true;
	}else {
		return false;
	}
}
 </script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/reassignToCLOCasenoteAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|20">

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
									<!--blue tabs start--> 
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab" />
									</tiles:insert> 
									<!--blue tabs end-->
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
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;Reassign Supervisee to CLO - Casenote</td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Enter Casenote.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<!--header start--> 
									<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									</tiles:insert> 
									<!--header end-->
									
									<div class="spacer4px"></div>
									<!-- BEGIN DETAIL TABLE -->
									
									<!-- COURT SELECTED TILE START -->
									<%-- 
									<tiles:insert page="../common/courtSelectedTile.jsp" flush="true">
									</tiles:insert>
									--%>
									<!-- COURT SELECTED TILE END -->
									
									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Court Selected</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="4" cellspacing="1">
													<tr>
														<td class="formDeLabel" width="1%">Court</td>
														<td class="formDe">
															<bean:write name="caseAssignmentForm" property="reassignedCourtId" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap">CLO Name</td>
														<td class="formDe">
															<bean:write name="caseAssignmentForm" property="officerName" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									
									
									<div class="spacer4px"></div>
									
									<!-- WORKGROUP SELECTED TILE START -->
									<%-- 
									<tiles:insert page="../common/workgroupSelectedTile.jsp" flush="true">
									</tiles:insert>
									 --%>
									<!-- WORKGROUP SELECTED TILE END -->
									
									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Workgroup Selected</td>
										</tr>
										<tr>
											<td>
															<table width="100%" border="0" cellpadding="2" cellspacing="1">
																<tr class="formDeLabel">															
																	<td>Name</td>
																	<td>Description</td>
																</tr>
																<tr>
																<!-- 
																	<td><a href="javascript: openWindow('../workgroupCSCD/workgroupDetailPopUp.htm')">Court Services Receiving</a></td>
																	<td>This is a the Court Services Receiving Workgroup</td>
																 -->
																 <td><bean:write name="caseAssignmentForm" property="reassignedWorkGroupName"/></td> 
																 <td><bean:write name="caseAssignmentForm" property="reassignedWorkGroupDescription"/></td>																 
																</tr>
															</table>
														</td>
										</tr>
									</table>
									
									
									<div class="spacer4px"></div>
									<!-- add casenote start-->
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td>New Casenote Information</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">Casenote</td>
													</tr>
													<tr class="formDe">
														<td>
															<html:textarea name="caseAssignmentForm" property="casenoteText" onkeyup="textCounter(this.form.casenoteText,1000);"  ondblclick="myReverseTinyMCEFix(this)"
																			styleClass="mceEditor" style="width:100%" rows="15" >
															</html:textarea> 
															<bean:define id="userAgency" name="caseAssignmentForm" property="agencyId"/>
															<tiles:insert page="../common/spellCheckButtonTile.jsp" flush="false">
																<tiles:put name="agencyCode"><%=userAgency%></tiles:put> 
															</tiles:insert>	
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<br>
									<!--add casenotes end-->
									<!-- END DETAIL TABLE -->
									<!-- BEGIN BUTTON TABLE -->
									<table cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<html:submit property="submitAction">
													<bean:message key="button.back" />
												</html:submit> 
												<html:submit property="submitAction" onclick="return myTinyMCEFix() && validateForm(this.form) && disableSubmit(this, this.form);">
													<bean:message key="button.next" />
												</html:submit> 
												<html:reset property="submitAction">
													<bean:message key="button.reset" />
												</html:reset> 
												<html:submit property="submitAction">
													<bean:message key="button.cancel" />
												</html:submit>
											</td>
										</tr>
									</table>
									<br>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<br>
					</td>
				</tr>
			</table>
		</div>
	</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
