<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/25/2008 C Shimek  Defect#51585 add validation check to not allow more than 3500 characters in casenoteText field, textCounter function does not work on cut and paste -->
<!-- 10/03/2008 C Shimek  Defect#51419 add taglib and code to make spellcheck function  -->
<!-- 03/05/2010 C Shimek  Revised tinyMCE to use new standard layout, found file testing Activity #64921 -->
<!-- 08/23/2010 D Williamson #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
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
<title>CommonSupervision/administerCaseload/tasknoteCreateForAssignSuperviseeToProgramUnit.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>	

<script type="text/javascript">
function validateField(theForm){
    clearAllValArrays();
    trimCasenote('casenoteText');
	customValRequired("casenoteText","Casenote Text is required","");
	customValMaxLength("casenoteText","Casenote Text cannot be more than 3500 characters","3500");
	addDefinedTinyMCEFieldMask("casenoteText","Casenote Text cannot have % or _ entries","");
    if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(theForm.casenoteText.value, 'Casenote Text is required')){
		return true;
	}else {
		return false;
	}
 }
 
</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/addcasenoteforprogramunitassignment" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|11">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--blue tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> <!--blue tabs end-->
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
							<td align="center" class="header">
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
									Assign Supervisee to Program Unit - Casenote
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
							<td class="required"><img src="/<msp:webapp/>images/required.gif"
								title="required" alt="required image" border="0" width="10" height="9">
							Required Field</td>
						</tr>
					</table>
					<!-- END INSTRUCTION TABLE -->
					
					<!--header start--> 
					<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
					</tiles:insert> 
					<!--header end-->
					<br>
					
					<!-- BEGIN DETAIL TABLE --> 
					
					<!-- PROGRAM UNIT ASSIGNMENT START -->
					<tiles:insert page="../common/programUnitAssignmentTile.jsp" flush="true">
					</tiles:insert>
					<!-- PROGRAM UNIT ASSIGNMENT END -->
					<br>
					<span id="addCN"> <!-- add casenote start-->
					<table width="98%" border="0" cellspacing="0" cellpadding="2"
						class="borderTableBlue">
						<tr class="detailHead">
							<td>New Casenote Information</td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title="required"
										alt="required image" border="0" width="10" height="9">Casenote</td>
								</tr>
								<tr class="formDe">
									<td>
										<html:textarea name="caseAssignmentForm" property="casenoteText" onkeyup="textCounter(this.form.casenoteText,3500);"  ondblclick="myReverseTinyMCEFix(this)"
														styleClass="mceEditor" style="width:100%" rows="15" >
										</html:textarea> 
										<nest:define id="userAgency" name="caseAssignmentForm" property="agencyId"/>
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
					<!--add casenotes end--> </span> <!-- END DETAIL TABLE --> <!-- BEGIN BUTTON TABLE -->
					
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:submit property="submitAction">
									<bean:message key="button.back" />
								</html:submit> 
								<html:submit property="submitAction" onclick="return myTinyMCEFix() && validateField(this.form) && disableSubmit(this, this.form);">
									<bean:message key="button.assignsuperviseetoprogramunit.addcasenote.summary" />
								</html:submit> 
								<html:reset><bean:message key="button.reset" /></html:reset>
								<html:submit property="submitAction">
									<bean:message key="button.cancel" />
								</html:submit>
							</td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --></td>
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
