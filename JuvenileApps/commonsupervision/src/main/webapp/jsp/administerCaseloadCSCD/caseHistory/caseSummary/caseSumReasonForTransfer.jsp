<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/27/2008 C. Shimek - Converted PT to JSP -->
<!-- 03/25/2009 C. Shimek - 57921 added scripts to handle Region Transfer selection -->
<!-- 07/02/2010 C. Shimek - #66278 revised comment max. check from onsubmit to active -->
<!-- 03/12/2013 R. Young  - #75066 Add isExtended radio to the jsp -->

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/caseSumReasonForTransfer.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseSummary/caseSumReasonForTransfer.js"></script>
<script type="text/javascript">
function disableReasons(el){
	var cbs = document.getElementsByName("selectedReasonForTransferIds");
	for (x=0; x<cbs.length; x++){
		if (cbs[x].value != "RNTR"){
			if (el.checked == true){
				cbs[x].checked = false;
				cbs[x].disabled = true;
			} else {
				cbs[x].disabled = false;
			}		
		}	
	}
}
function checkReasonForTranfers(){
	var cbs = document.getElementsByName("selectedReasonForTransferIds");
	for (x=0; x<cbs.length; x++){
		if (cbs[x].value == "RNTR" && cbs[x].checked == true){
			disableReasons(cbs[x]);
			break;
		}
	}		
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkReasonForTranfers();">
<html:form action="/displayReasonForTransferSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|3">
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--blue tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
						<!--blue tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- ENE BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif"  height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFO TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE INFO TABLE -->							
					</td>
				</tr>							
				<tr>
					<td valign="top" align="center">
<!-- BEGIN GREEN TABS TABLE -->							
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->								
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
													<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.caseSummary"/>&nbsp;-
													<bean:message key="prompt.reasonForTransfer"/>
											</td>
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
													<li>Select reason for transfer. Click next when complete.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<% int bgCtr=0; 
									   String leftRight="R";
									   String checkItem = "";%>									
<!-- BEGIN REASON FOR TRANSFER TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.4.diamond"/> <bean:message key="prompt.reasonForTransfer"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<logic:iterate id="scaIter" name="caseSummaryForm" property="create1ElementsList" indexId="index">
												 		<%  checkItem = ""; %>
														<logic:equal name="scaIter" property="visible" value="true">
															<% checkItem = "checked=true"; %> 
														</logic:equal>  
														<% leftRight = (index.intValue() % 2 == 1) ? "R" : "L"; %>
														<% if(leftRight == "L" ) { %>
															<% bgCtr++; %>
													 		<tr class="<%out.print((bgCtr % 2 == 1) ? "normalRow" : "alternateRow"); %>">
																<td>
																	<logic:notEqual name="scaIter" property="code" value="RNTR">
																		<input type="checkbox" name="selectedReasonForTransferIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> > 
																	</logic:notEqual>
																	<logic:equal name="scaIter" property="code" value="RNTR">
																		<input type="checkbox" name="selectedReasonForTransferIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> onclick="disableReasons(this)">
																	</logic:equal>
																	<bean:write name="scaIter" property="description" />
																</td>
														<% } %>	
														<% if(leftRight == "R" ) { %>
																<td>
																	<logic:notEqual name="scaIter" property="code" value="RNTR">
																		<input type="checkbox" name="selectedReasonForTransferIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> >
																	</logic:notEqual>
																	<logic:equal name="scaIter" property="code" value="RNTR">
																		<input type="checkbox" name="selectedReasonForTransferIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> onclick="disableReasons(this)">
																	</logic:equal>
																	<bean:write name="scaIter" property="description" /> 
																</td>
															</tr>
														<% } %>	
													</logic:iterate>
													<% if(leftRight == "L" ) { %>
															<td>&nbsp;</td>
														</tr>
													<% } %>	
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="1" cellspacing="0">
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.4.diamond"/> Extended?</td>
														<td class="formDe">
															<logic:equal name="caseSummaryForm" property="currentReasonForTransferComments" value="">
																<logic:equal name="caseSummaryForm" property="previousReasonForTransferComments" value="YES">
																	<input type="radio" name="isExtended" value="YES" checked> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
																<logic:equal name="caseSummaryForm" property="previousReasonForTransferComments" value="NO">
																	<input type="radio" name="isExtended" value="YES"> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
																<logic:equal name="caseSummaryForm" property="previousReasonForTransferComments" value="">
																	<input type="radio" name="isExtended" value="YES"> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
															</logic:equal>
															<logic:notEqual name="caseSummaryForm" property="currentReasonForTransferComments" value="">
																<logic:equal name="caseSummaryForm" property="currentReasonForTransferComments" value="YES">
																	<input type="radio" name="isExtended" value="YES" checked> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
																<logic:equal name="caseSummaryForm" property="currentReasonForTransferComments" value="NO">
																	<input type="radio" name="isExtended" value="YES"> Yes
																	<input type="radio" name="isExtended" value="NO" checked> No
																</logic:equal>
															</logic:notEqual>		
														</td>
													</tr>
												</table>			
											</td>
										</tr>
									</table>
<!-- END REASON FOR TRANSFER TABLE -->									
									<br> 									
<!-- BEGIN COMMENT TABLE -->			
<%--			
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/> (Max. characters allowed: 500)</td>
										</tr>
										<tr>
											<td class="formDe">
												<html:textarea name="caseSummaryForm" property="create1Comments" 
													onmouseout="textLimit(this, 500);" 
       												onkeyup="textLimit(this, 500);" 
													style="width:100%" rows="5">
												</html:textarea>
											</td>
										</tr>
									</table>
--%>									
<!-- END COMMENT TABLE -->										
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return validateInput(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>												
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
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
<!-- END BLUE BORDERE TABLE -->				
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->	
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>