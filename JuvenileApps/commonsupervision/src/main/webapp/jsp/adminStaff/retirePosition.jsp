<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - adminStaff/retirePosition.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="workgroupSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function validateRetirementDate(){
	var inDate = document.getElementsByName("position.retirementDateAsStr");
	if (inDate.size != 0 ){
		if (inDate[0].value == ""){
			alert("Retirement date is required.")
			inDate[0].focus();
			return false;
		}	
		var isDateValid = isDate(inDate[0].value, "MM/dd/yyyy");
		if (isDateValid == false){
			alert("Retirement date is invalid.")
			inDate[0].focus();
		}	
		return isDateValid;;
	}	
}

function setRetireFlag()
{
	var retire_flag = document.getElementById("retirePositionSummaryFlag");
	retire_flag.value="true";
	return true;
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handlePositionSelection" target="content" focus="position.retirementDateAsStr">
<input type="hidden" id="retirePositionFlag" name="retirePositionSummaryFlag" value="" >
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|30">
<input type="hidden" name="selectedValue" value="<bean:write name="adminStaffForm" property="position.positionId"/> "/>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
						height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="../common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="positionsTab" />
							</tiles:insert> <!--tabs end--></td>
						</tr>

					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message
										key="title.CSCD" /> - 
															
										Retire Position
																				
										
								
									</td>
								</tr>
							</table>
							<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
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
																			<li>Enter Required Fields and Click Next.</li>
																		</ul>
																	</td>
																</tr>
																<tr>
																	<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
																</tr>

															</table>
															<!-- END INSTRUCTION TABLE -->
															<!-- BEGIN pos info TABLE -->
															<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
																<tr>
																	<td class="detailHead">
																		<table width="100%" cellpadding="2" cellspacing="0">
																			<tr>
																				<td class="detailHead"><bean:message key="prompt.position"/> <bean:message key="prompt.information"/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<tiles:insert page="positionInfoTile.jsp" flush="true">
																			<tiles:put name="position" beanName="adminStaffForm" beanProperty="position"/>
																		</tiles:insert>
																	</td>
																</tr>
															</table>
															<!--end pos info table-->
															<!-- BEGIN supervisor list TABLE -->
															
															<!-- END supervisor list TABLE -->
															<br>
															<!-- BEGIN BUTTON TABLE -->
															
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																		<html:submit property="submitAction" onclick="return validateRetirementDate() && setRetireFlag() && disableSubmit(this, this.form)"><bean:message key="button.next" /></html:submit>			
																		<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																		
																	</td>
																</tr>
															</table>
											
															<!-- END BUTTON TABLE -->
															
														</td>
													</tr>
												</table>
												<br>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					
					</div>
				</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
