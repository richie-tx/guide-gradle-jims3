<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--03/04/2008	LDeen	Defect #49819 integrate help    --%>
<%-- 05/09/2008 DWilliamson Defect #51308 Create/Update page validations --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<title><bean:message key="title.heading" /> - manageAssociate/associateCreateUpdate.jsp</title>

<html:javascript formName="associateForm2" />

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/manageAssociate/associateCreateUpdate.js"></script>
			
<!-- STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript">
</script>
</head>

<body onload="document.forms[0]['associateName.lastName'].focus();">
<%--	<bean:define id="webPath" type="java.lang.String">/<msp:webapp/></bean:define>--%>
	<html:form action="/displayAssociateSummary" target="content">
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
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center">
								<!--HEADER AREA START-->
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td bgcolor="#cccccc" colspan="2">
											<!--header start-->
											<tiles:insert page="../common/superviseeInfoForManageAssociateHeader.jsp" flush="true">
											</tiles:insert>
											<!--header end-->
										</td>
									</tr>
									<tr>
										<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
									</tr>
								</table>
								<!--HEADER AREA END-->
								<!--CASELOAD CSCD TABS START-->
								<table width="98%" border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
											<tiles:put name="tab" value="AssociatesTab"/>
										</tiles:insert>		
										<!--tabs end-->
										</td>
									</tr>
									<tr>
										<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
									</tr>
								</table>
								<!--CASELOAD CSCD TABS END-->
									<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
										<tr>
											<td valign="top" align="center">
												<!-- BEGIN HEADING TABLE -->
												<table width="100%">
													<tr>
														<td align="center" class="header" >
															<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
																<bean:message key="prompt.create" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|7">
															</logic:equal>
															<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
																<bean:message key="prompt.update" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|5">										
															</logic:equal>
															Associate
														</td>
													</tr>
												</table>
												<!-- END HEADING TABLE -->
												<!-- BEGIN INSTRUCTION TABLE -->
												<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
													<tr>
														<td>
															<ul>
																<li>Enter the required information, and select Next button to view a summary of your entries.</li>
															</ul>
														</td>
													</tr>
														<tr>
															<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;Required Fields&nbsp;&nbsp;+ At Least One of These is Required</td>
														</tr>
												</table>
												<!-- END INSTRUCTION TABLE -->
												<!-- BEGIN Responsible Adult NAME TABLE -->
												<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1">
													<tr>
														<td class="detailHead" colspan="4">
															<table width="100%" cellpadding="0" cellspacing="0">
																<tr class="detailHead">
																	<td class="detailHead">Associate Information</td>
																	<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
																	<td class="legendSmallText" align="right">Last Updated:&nbsp;
																		<bean:write name="associateForm" property="updateDate" formatKey="date.format.mmddyyy" />&nbsp;
																		<bean:write name="associateForm" property="updateJIMS2User" />
																	</td>
																	</logic:equal>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap>+Last Name</td>
														<td class="formDe" colspan="3">
														<html:text name="associateForm" property="associateName.lastName" size="30" maxlength="75" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap>+First Name</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="associateName.firstName" size="25" maxlength="50" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap>Middle Name</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="associateName.middleName" size="25" maxlength="50" />
														</td>
													</tr>
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
														<tr id="updateRow">
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" />Relationship</td>
															<td class="formDe">
																<html:select name="associateForm" property="relationshipId" >
		 													   		<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																	<jims:codetable codeTableName="<%=PDCodeTableConstants.CSCD_RELATIONSHIP%>"/>
																</html:select>
															</td>
															<td class="formDeLabel" width="1%">Status</td>
															<td class="formDe">
																<img src="/<msp:webapp/>images/thumbs_up.gif" hspace=0 title="This Associate has a Good Status"/>
																<%--<input type=radio name=status value=good checked>--%>
																<html:radio name="associateForm" property="status" value="true"/>
																<img src="/<msp:webapp/>images/thumbs_down.gif" hspace=0 title="This Associate has a Bad Status"/>
																<html:radio name="associateForm" property="status" value="false"/>
																<%--<input type=radio value=bad name=status>--%>
															</td>
														</tr>
													</logic:equal> 
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
														<tr id="createRow">
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" />Relationship</td>
															<td class="formDe" colspan="3">
																<html:select name="associateForm" property="relationshipId" >
																    <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																	<jims:codetable codeTableName="<%=PDCodeTableConstants.CSCD_RELATIONSHIP%>" />
																</html:select>
															</td>
														</tr>
													</logic:equal>
													<tr>
														<td class="formDeLabel" nowrap colspan="4">Comments</td>
													</tr>
													<tr>
														<td class="formDe" colspan="4">
															<html:textarea name="associateForm" property="comments" style="width:100%" rows="5" onkeyup="textCounter(this.form.comments,500);"></html:textarea>
														</td>
													</tr>
													<!-- End of Responsible Adult Name table -->
													<tr>
														<td>&nbsp;</td>
													</tr>
													<!-- Begin Contact Table -->
													<tr>
														<td class="detailHead" colspan="4">Contact Information</td>
													</tr>
													<tr>
														<td class="formDeLabel">Home Phone</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="homePhone.areaCode" size="3" onkeyup="return autoTab(this, 3);" />
															-
															<html:text name="associateForm" property="homePhone.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="homePhone.last4Digit" size="4" onkeyup="return autoTab(this, 4);"/>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel">Work Phone</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="workPhone.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="workPhone.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="workPhone.last4Digit" size="4" onkeyup="return autoTab(this, 4);"/>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel">Cell Phone</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="cellPhone.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="cellPhone.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="cellPhone.last4Digit" size="4" onkeyup="return autoTab(this, 4);"/>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel">Pager</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="pager.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="pager.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
															-
															<html:text name="associateForm" property="pager.last4Digit" size="4" onkeyup="return autoTab(this, 4);"/>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel">Email</td>
														<td class="formDe" colspan="3">
															<html:text name="associateForm" property="email" size="30" maxlength="100"/>
														</td>
													</tr>
													<!-- End Contact Table -->
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
												<!-- Begin Associate Address Table 1 -->
												<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr>
														<td class="detailHead">Residence Address 
															<a href="javascript:changeFormActionURL(document.forms[0],'/<msp:webapp/>displayAssociateSummary.do?submitAction=<bean:message key="button.copy"/>&secondaryAction=<%=UIConstants.COPY_TO_PRIMARY_ADDRESS%>',true)">
																<bean:message key="button.copySuperviseeResidenceAddress"/>
															</a>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellspacing="1" width="100%" id="addressForm">
																<tr>
																	<td class="formDeLabel" width="25%">Street Number</td>
																	<td class="formDeLabel" colspan="2">Street Name</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:text name="associateForm" property="primaryResidenceAddress.streetNumber" size="9" maxlength="9"/>
																	</td>
																	<td class="formDe" colspan="2">
																		<html:text name="associateForm" property="primaryResidenceAddress.streetName" size="50" maxlength="50"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">Street Type</td>
																	<td nowrap class="formDeLabel" colspan="2" >Apt/Suite</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:select name="associateForm" property="primaryResidenceAddress.streetTypeId" >
																		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.STREET_TYPE%>" />
																		</html:select>
																	</td>
																	<td class="formDe" colspan="2">
																		<html:text name="associateForm" property="primaryResidenceAddress.aptNumber" size="10" maxlength="10"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">City</td>
																	<td class="formDeLabel">State</td>
																	<td class="formDeLabel">Zip Code</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:text name="associateForm" property="primaryResidenceAddress.city" size="15" maxlength="25"/>
																	</td>
																	<td class="formDe">
																		<html:select name="associateForm" property="primaryResidenceAddress.stateId" >
																		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.STATE_ABBR%>" />
																		</html:select>
																	</td>
																	<td class="formDe">
																		<html:text name="associateForm" property="primaryResidenceAddress.zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/>
																		-
																		<html:text name="associateForm" property="primaryResidenceAddress.additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" colspan="3">Address Complex Name</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="3">
																		<html:text name="associateForm" property="primaryResidenceAddress.addressComplexName" size="50" maxlength="100"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" colspan="3">County</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="3">
																		<html:select name="associateForm" property="primaryResidenceAddress.countyId" >
																		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.COUNTY%>" />
																		</html:select>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												<!-- End Associate Address Table 1 -->														
												<br/>
												<!-- Begin Associate Address Table 2 -->
												<table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr>
														<td class="detailHead">Other Address 
<%--															<a href="/<msp:webapp/>displayAssociateSummary.do?submitAction=<bean:message key="button.copySuperviseeResidenceAddress"/>&secondaryAction=<%=UIConstants.COPY_TO_OTHER_ADDRESS%>">--%>
															<a href="javascript:changeFormActionURL(document.forms[0],'/<msp:webapp/>displayAssociateSummary.do?submitAction=<bean:message key="button.copy"/>&secondaryAction=<%=UIConstants.COPY_TO_OTHER_ADDRESS%>',true)">
																<bean:message key="button.copySuperviseeResidenceAddress"/>
															</a>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellspacing="1" width="100%">
																<tr>
																	<td class="formDeLabel" width="25%">Street Number</td>
																	<td class="formDeLabel" colspan="2">Street Name</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:text name="associateForm" property="otherAddress.streetNumber" size="9" maxlength="9" />
																	</td>
																	<td class="formDe" colspan="2">
																		<html:text name="associateForm" property="otherAddress.streetName" size="50" maxlength="50" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">Street Type</td>
																	<td class="formDeLabel" colspan="2">Apt/Suite</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:select name="associateForm" property="otherAddress.streetTypeId" >
																		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.STREET_TYPE%>" />
																		</html:select>
																	</td>
																	<td class="formDe" colspan="2">
																		<html:text name="associateForm" property="otherAddress.aptNumber" size="10" maxlength="10" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">City</td>
																	<td class="formDeLabel">State</td>
																	<td class="formDeLabel">Zip Code</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:text name="associateForm" property="otherAddress.city" size="15" maxlength="25" />
																	</td>
																	<td class="formDe">
																		<html:select name="associateForm" property="otherAddress.stateId" >
																		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.STATE_ABBR%>" />
																		</html:select>
																	</td>
																	<td class="formDe">
																		<html:text name="associateForm" property="otherAddress.zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);" />
																		-
																		<html:text name="associateForm" property="otherAddress.additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
																	</td>
																</tr>
														
																<tr>
																	<td class="formDeLabel" colspan="3">Address Complex Name</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="3">
																		<html:text name="associateForm" property="otherAddress.addressComplexName" size="50" maxlength="100" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">Address Type</td>
																	<td class="formDeLabel" colspan="2">County</td>
																</tr>
																<tr>
																	<td class="formDe">
																		<html:select name="associateForm" property="otherAddress.addressTypeId" >
																	 	   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.ADDRESS_TYPE%>"/>
																		</html:select>
																	</td>
															 		<td class="formDe" colspan="2">
																		<html:select name="associateForm" property="otherAddress.countyId" >
																		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
																		   <jims:codetable codeTableName="<%=PDCodeTableConstants.COUNTY%>" />
																		</html:select>
																	</td>
																</tr> 
														</table>
													</td>
												</tr>
											</table>
											<table border="0" width="98%">
												<tr>
													<td align="center">
														<html:submit property="submitAction">
															<bean:message key="button.back" />
														</html:submit> 
														<html:submit property="submitAction" onclick="return validateFields()&& validateCreateUpdateFields();">
															<bean:message key="button.next" />
														</html:submit>
												<%--		<html:reset>
															<bean:message key="button.reset" />
														</html:reset>--%>
														<html:submit property="submitAction">
															<bean:message key="button.reset" />
														</html:submit> 
														<html:submit property="submitAction">
															<bean:message key="button.cancel" />
														</html:submit>
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
				<br>
	</html:form>
	<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
