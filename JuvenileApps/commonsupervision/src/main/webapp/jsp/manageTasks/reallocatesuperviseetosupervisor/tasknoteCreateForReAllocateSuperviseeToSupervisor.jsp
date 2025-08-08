<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- CHANGE LOG -->
<!-- 08/24/2010 DWilliamson - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->
<!-- 03/16/2012	TSVines		- #71808 Changed title from "Reallocate" to "Allocate" -->

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
<title><bean:message key="title.heading" /> - assignSupervisee/tasknoteCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/CommonSupervision/js/tinyMCECustomInitCasenote.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/addcasenoteforsupervisorreallocation" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|24">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>

			<td valign="top">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--blue tabs start--> <tiles:insert
						page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> <!--blue tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>

					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header">
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;														
									Allocate Supervisee to Supervisor - Casenote
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
					<tiles:insert page="../../common/assignmentHeader.jsp" flush="true">
					</tiles:insert> 
					<!--header end-->
					<br>
					
					<!-- BEGIN DETAIL TABLE --> 
					
						<tiles:insert page="../../common/selectedSupervisorTile.jsp" flush="true">
						</tiles:insert>
					<br>
					<span id="addCN"> <!-- add casenote start-->
					<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
						<tr class="detailHead">
							<td>New Casenote Information</td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel"><img
										src="/<msp:webapp/>images/required.gif" title="required"
										alt="required image" border="0" width="10" height="9">Casenote</td>
								</tr>
								<tr class="formDe">
									<td>
										<bean:define id="userAgency" name="caseAssignmentForm" property="agencyId"/>
										<html:textarea name="caseAssignmentForm" property="casenoteText" onkeyup="textCounter(this.form.casenoteText,1000);"  ondblclick="myReverseTinyMCEFix(this)"
														styleClass="mceEditor" style="width:100%" rows="15" >
										</html:textarea> 
										<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
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
					<script>
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
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<html:submit property="submitAction">
									<bean:message key="button.back" />
								</html:submit> 
								<html:submit property="submitAction" onclick="return myTinyMCEFix() && validateField(this.form) && disableSubmit(this, this.form);">
									<bean:message key="button.allocatesuperviseetosupervisor.selectsupervisor.addCasenote" />
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
					<!-- END BUTTON TABLE --></td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
