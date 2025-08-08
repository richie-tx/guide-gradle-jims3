<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/01/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 08/12/2010 CShimek           - #66918, correct casenote user formatting. Fixed spellcheck(not part of defect) -->
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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prCasenote.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>
	function validateFields(theForm)
	{
		clearAllValArrays();
		trimCasenote('casenoteComments');
		customValRequired("casenoteComments","<bean:message key='errors.required' arg0='Casenote'/>","");
		customValMaxLength("casenoteComments","Casenote cannot be more than 3500 characters","10500");
        addDefinedTinyMCEFieldMask("casenoteComments","<bean:message key='errors.freeTextDB2' arg0='Casenote'/>","");
        if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(theForm.casenoteComments.value, 'Casenote is required')){
			return true;
		}else {
			return false;
		}
	}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayProgRefCasenote" target="content" focus="casenoteComments">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|18">
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
											<bean:message key="title.create"/>&nbsp;<bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.casenote"/></td>
										</tr>
									</table>
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td>
												<ul>
													<li>Enter required fields. Click Next.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									
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
									
									<div class="spacer4px"></div>
									<bean:define id="userAgency" name="cscProgRefForm" property="userAgency"/>									
									<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.3.diamond"/><bean:message key="prompt.casenote"/></td>
										</tr>
										<tr>
											<td>
												<html:textarea name="cscProgRefForm" property="casenoteComments" styleClass="mceEditor" style="width:100%" rows="15" ondblclick="myReverseTinyMCEFix(this)"></html:textarea>
												<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
													<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
												</tiles:insert>
											</td>
										</tr>
									</table>
									
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateFields(this.form));"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
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
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>