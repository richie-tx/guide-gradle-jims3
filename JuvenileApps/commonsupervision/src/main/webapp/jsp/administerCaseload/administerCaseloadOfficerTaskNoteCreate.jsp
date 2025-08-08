<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 05/26/2010  Clarence Shimek   - #65373 revised to use tinyMCECustomInitCasenote.js -->
<!-- 07/29/2010  C Shimek          - cleaned up coding so it display properly -->
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title>CommonSupervision/administerCaseload/administerCaseloadOfficerTaskNoteCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/CommonSupervision/js/tinyMCECustomInitCasenote.js"></script>

<script type="text/javascript"> 

function validateForm(theForm){
	clearAllValArrays();
	trimCasenote('casenoteText');
	customValRequired("casenoteText","Casenote Text is required","");
	customValMaxLength("casenoteText","Casenote Text cannot be more than 3500 characters","3500");
	addDefinedTinyMCEFieldMask("casenoteText","Casenote Text cannot have % or _ entries","");
	return validateCustomStrutsBasedJS(theForm)
}
 </script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/reassignSuperviseeToOfficerCasenoteAction" 	target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|28">
<div align="center">
<!-- BEGIN FULL PAGE TABLE -->	
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tab" value="caseloadTab" />
							</tiles:insert>						
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END BLUE TABS TABLE -->				
<!-- BEGIN BLUE BORDER TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header">
										<bean:message key="title.CSCD" />&nbsp;-&nbsp; Reassign Supervisee to Officer - Casenote
									</td>
								</tr>
							</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>
										<ul>
											<li>Enter Casenote. Click Next</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td class="required"><bean:message key="prompt.2.diamond"/>Required Field</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->	
							<div class="spacer4px"></div>						
<!-- BEGIN ASSIGNMENT HEADER -->
							<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
							</tiles:insert>
<!-- END ASSIGNMENT HEADER -->
							<div class="spacer4px"></div>
<!-- BEGIN OFFICER ASSIGNMENT -->
							<tiles:insert page="../common/selectedOffToReassignSupToTile.jsp" flush="true">
							</tiles:insert>
<!-- END OFFICER ASSIGNMENT -->
							<div class="spacer4px"></div>
<!-- END CASENOTE TABLE -->
							<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
								<tr class="detailHead">
									<td>New Casenote Information</td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.2.diamond"/> Casenote</td>
											</tr>
											<tr class="formDe">
												<td>
												<html:textarea name="caseAssignmentForm" property="casenoteText" onkeyup="textCounter(this.form.casenoteText,1000);"  ondblclick="myReverseTinyMCEFix(this)"
														styleClass="mceEditor" style="width:100%" rows="15" >
												</html:textarea>
												<tiles:insert page="../common/spellCheckButtonTile.jsp" flush="false">
											    </tiles:insert>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
<!-- END CASENOTE TABLE -->
							<div class="spacer4px"></div>
<!-- BEGIN BUTTON TABLE -->
							<table cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td>
										<html:submit property="submitAction">
											<bean:message key="button.back" />
										</html:submit>
										<html:submit property="submitAction" onclick="return myTinyMCEFix() &amp;&amp; validateForm(this.form) &amp;&amp; disableSubmit(this, this.form);">
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
<!-- END BUTTON TABLE -->		
							<div class="spacer4px"></div>
						</td>
					</tr>					
				</table>
<!-- END BLUE BORDER TABLE -->						
			</td>
		</tr>
	</table>
<!-- END FULL PAGE TABLE -->	
	<br>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>