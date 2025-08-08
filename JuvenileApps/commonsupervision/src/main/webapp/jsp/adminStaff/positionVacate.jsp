<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 05/12/2008  C Shimek    defect#50128 added "Position" to position message to match PT  -->
<!-- 09/23/2008  C Shimek    defect#51068 revised positionTypeDesc to positionName in position info to match PT -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ page import="naming.Features"%>
<%@ page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - adminStaff/positionVacate.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript'>
customValRequired("position.effectiveDateAsStr","Effective Date is required.","");
addMMDDYYYYDateValidation("position.effectiveDateAsStr","Effective Date must be in MM/DD/YYYY format.","");
</script>
<script>
function performValidation(theFormElem){

	clearAllValArrays();
	customValRequired("position.effectiveDateAsStr","Effective Date is required","");
	addMMDDYYYYDateValidation("position.effectiveDateAsStr","Effective Date must be in MM/DD/YYYY format.","");

	return validateCustomStrutsBasedJS(theFormElem);
}
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<script language="JavaScript" id="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</script>

<html:form action="/submitPositionUpdate" target="content" focus="position.effectiveDateAsStr">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|29">

	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><!--tabs start-->
							<tiles:insert page="/jsp/common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="setupTab" />
							</tiles:insert> <!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- BEGIN BLUE BORDER TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN GREEN TABS TABLE  -->						
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top"><!--tabs start--> <tiles:insert
										page="/jsp/common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="positionsTab" />
									</tiles:insert> <!--tabs end--></td>
								</tr>
							</table>
<!-- END GREEN TABS TABLE -->							
<!-- BEGIN GREEN BORDER TABLE -->							
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
										<table width="100%">
											<tr>
												<td align="center" class="header"><bean:message key="title.CSCD" /> - 
													<bean:message key="button.vacatePosition" /> 	
												</td>
											</tr>
										</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
										<table width="98%" align="center">
											<tr>
												<td align="center" class="errorAlert"><html:errors /></td>
											</tr>
										</table>
<!-- END ERROR TABLE --> 
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Enter required fields. Click Next.</li>
													</ul>
												</td>
											</tr>											
											<tr>
												<td class="required"> <bean:message key="prompt.requiredFields" /></td>
											</tr>
										</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN POSITION INFO HEADER TABLE -->
										<table width="98%" cellpadding="0" cellspacing="0" bgcolor="#cccccc">
											<tr class="paddedFourPix">
												<td class="formDeLabel"><bean:message key="prompt.position" /> <bean:message key="prompt.information" /></td>
												<td align="right" class="formDeLabel">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="2">
													<tiles:insert page="/jsp/adminStaff/positionInfoHeaderTile.jsp" flush="true">
														<tiles:put name="position" beanName="adminStaffForm" beanProperty="position"/>
													</tiles:insert>
												</td>
											</tr>
											
										</table>
<!-- END POSITION INFO HEADER TABLE -->
										<br>
												<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td>
														Vacate Position Information
														</td>
													</tr>
													<tr>

														<td>
															<table width="100%" cellpadding="2" cellspacing="1">
																<tr>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.diamond"/><bean:message key="prompt.effectiveDate"/></td>
																	<td class="formDe">
																	<html:text styleId="effectiveDateAsStr" property="position.effectiveDateAsStr" maxlength="10" size="10" />
																	<a href="#" onClick="cal1.select(document.forms[0].effectiveDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar"/></a>
																	<script>
																		document.getElementsByName("position.effectiveDateAsStr")[0].value=getCurrentDate();
																	</script>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>																											
										<br>
										
<!-- BEGIN BUTTON TABLE -->
										<table cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return performValidation(this.form)"><bean:message key="button.submit" /></html:submit>
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->								
							<br>
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
			</td>
		</tr>
	</table>						
	</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>