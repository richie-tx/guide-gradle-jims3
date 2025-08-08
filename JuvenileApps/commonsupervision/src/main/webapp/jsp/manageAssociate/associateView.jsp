<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--03/04/2008	LDeen	Defect #49819 integrate help    --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - manageAssociate/associateView.jsp</title>

<script language="JavaScript" src="/<msp:webapp/>js/case_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
function confirmDelete(){
	if (confirm("Are you sure you want to delete this Associate")){
		changeFormActionURL(document.forms[0], '/<msp:webapp/>submitAssociateSummary.do?submitAction=Finish&action=delete',true);
		return true;
	}else	return false;
}
</script>

</head>
<body>
<!-- BEGIN FORM -->
<html:form action="/submitAssociateSummary" target="content">
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
												<td align="center" class="header">
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
														<bean:message key="prompt.create" /> <bean:message key="prompt.associate" /> <bean:message key="prompt.summary" />										
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|3">
													</logic:equal>
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
														<bean:message key="prompt.update" /> <bean:message key="prompt.associate" /> <bean:message key="prompt.summary" />											
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|6">
													</logic:equal>
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.VIEW%>">
														<bean:message key="prompt.associate" /> <bean:message key="prompt.details" />
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|1">
													</logic:equal>
													<logic:equal name="associateForm" property="action" value="<%=UIConstants.DELETE%>">
														<bean:message key="prompt.delete" /> <bean:message key="prompt.associate" /> <bean:message key="prompt.summary" />
														<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload_subfolders/Manage_Associates/Manage_Associates.htm#|4">									
													</logic:equal>
												</td>		
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										
										<!-- BEGIN ERROR TABLE -->
										<table width="98%" align="center">							
											<tr>
												<td align="center" class="errorAlert"><html:errors/></td>
											</tr>		
										</table>
										<!-- END ERROR TABLE -->
										
										<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
											<tr>
												<td><ul>
													<li>
														<logic:equal name="associateForm" property="action" value="<%=UIConstants.VIEW%>">
															Click Update or Delete.										
														</logic:equal>
														<logic:equal name="associateForm" property="action" value="<%=UIConstants.DELETE%>">
															Review associate. Click Finish.										
														</logic:equal>
														<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
															Review entries and click Finish, or click Back to change entries.									
														</logic:equal>
														<logic:equal name="associateForm" property="action" value="<%=UIConstants.UPDATE%>">
															Review entries and click Finish, or click Back to change entries.										
														</logic:equal>
													</li>																			
												</ul>	
																														
												</td>
											</tr>
										</table>
										<!-- BEGIN associates info  TABLE -->
										<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1">
											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="0" cellspacing="0">
														<tr class="detailHead">
															<td class="detailHead">Associate Information</td>
															<logic:equal name="associateForm" property="action" value="<%=UIConstants.VIEW%>">
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
												<td class="formDeLabel" nowrap>Name</td>
												<td class="formDe" colspan="3">
  													<bean:write name="associateForm" property="associateName.formattedName" />
												</td>
											</tr>
											<logic:equal name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
											<tr id="createRow">
												<td class="formDeLabel" width="1%" nowrap>Relationship</td>
												<td class="formDe" colspan="3"><bean:write name="associateForm" property="relationship" /></td>
											</tr>
											</logic:equal>
											<logic:notEqual name="associateForm" property="action" value="<%=UIConstants.CREATE%>">
											<tr id="viewUpdateRow">
												<td class="formDeLabel" width="1%" nowrap>Relationship</td>
												<td class="formDe"><bean:write name="associateForm" property="relationship" /></td>
												<td class="formDeLabel" width="1%">Status</td>
												<td class="formDe">
												<logic:equal name="associateForm" property="status" value="true">
													<img src="/<msp:webapp/>images/thumbs_up.gif" hspace="0" title="This Associate has a Good Status">
												</logic:equal>
												<logic:equal name="associateForm" property="status" value="false">
													<img src="/<msp:webapp/>images/thumbs_down.gif" hspace="0" title="This Associate has a Bad Status"/>
												</logic:equal>
												</td>
											</tr>
											</logic:notEqual>
											<tr>
												<td class="formDeLabel" width="1%" nowrap colspan=4>Comments</td>
											</tr>
											<tr>
												<td class="formDe" colspan="4">
													<bean:write name="associateForm" property="comments" />
												</td>
											</tr>
											<!-- End of associates info table -->
											
											<tr>
												<td>&nbsp;</td>
											</tr>
											<!-- Begin Contact Table -->
											<tr>
												<td class="detailHead" colspan="4">Contact Information</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap>Home Phone</td>
												<td class="formDe" colspan="3"><bean:write name="associateForm" property="homePhone.formattedPhoneNumber" /></td>
											</tr>
											<tr>
												<td class="formDeLabel">Work Phone</td>
												<td class="formDe" colspan="3"><bean:write name="associateForm" property="workPhone.formattedPhoneNumber" /></td>
											</tr>
											<tr>
												<td class="formDeLabel">Cell Phone</td>
												<td class="formDe" colspan="3"><bean:write name="associateForm" property="cellPhone.formattedPhoneNumber" /></td>
											</tr>
											<tr>
												<td class="formDeLabel">Pager</td>
												<td class="formDe" colspan="3"><bean:write name="associateForm" property="pager.formattedPhoneNumber" /></td>
											</tr>
											<tr>
												<td class="formDeLabel">Email</td>
												<td class="formDe" colspan="3"><bean:write name="associateForm" property="email" /></td>
											</tr>
											<!-- End Contact Table -->
											<tr>
												<td>&nbsp;</td>
											</tr>
											</table>
											<!-- Begin Associate Address Table 1 -->
											<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="detailHead" colspan="3">
														<table width="100%" border="0" cellpadding="4" cellspacing="1">
															<tr>
																<td class="detailHead" nowrap>Residence Address </td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<table border="0" cellpadding="4" cellspacing=1 width="100%">
															<tr>
																<td class="formDeLabel" width="45%">Street Number</td>
																<td class="formDeLabel" colspan="2">Street Name</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.streetNumber" /></td>
																<td class="formDe" colspan="2"><bean:write name="associateForm" property="primaryResidenceAddress.streetName" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">Street Type</td>
																<td nowrap class="formDeLabel" colspan="2" >Apt/Suite</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.streetType" /></td>
																<td class="formDe" colspan="2"><bean:write name="associateForm" property="primaryResidenceAddress.aptNumber" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">City</td>
																<td class="formDeLabel">State</td>
																<td class="formDeLabel">Zip Code</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.city" /></td>
																<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.state" /></td>
															 	<td class="formDe">
															 		<bean:write name="associateForm" property="primaryResidenceAddress.zipCode" />
															 		- 
															 		<bean:write name="associateForm" property="primaryResidenceAddress.additionalZipCode" />
															 	</td>
															</tr>
															<tr>
																<td class="formDeLabel" colspan="3">Address Complex Name</td>
															</tr>
															<tr>
																<td class="formDe" colspan="3"><bean:write name="associateForm" property="primaryResidenceAddress.addressComplexName" /></td>
															</tr>
															<tr>
																<td class="formDeLabel" colspan="3">County</td>
															</tr>
															<tr>
																<td class="formDe" colspan="3"><bean:write name="associateForm" property="primaryResidenceAddress.county" /></td>
															</tr>
														</table>
													</td>
												</tr>
												<!-- End Associate Address Table 1 -->
												<tr>
													<td>&nbsp;</td>
												</tr>
												<!-- Begin Associate Address Table 2 -->
												<tr>
													<td class="detailHead" colspan="3" >
														<table width="100%" border="0" cellpadding="2" cellspacing="1">
															<tr>
																<td class="detailHead" nowrap>Other Address </td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<table border="0" cellpadding=4 cellspacing=1 width=100%>
															<tr>
																<td class="formDeLabel" width="45%">Street Number</td>
																<td class="formDeLabel" colspan="2">Street Name</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="otherAddress.streetNumber" /></td>
																<td class="formDe" colspan="2"><bean:write name="associateForm" property="otherAddress.streetName" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">Street Type</td>
																<td nowrap class="formDeLabel" colspan="2" >Apt/Suite</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="otherAddress.streetType" /></td>
																<td class="formDe" colspan="2"><bean:write name="associateForm" property="otherAddress.aptNumber" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">City</td>
																<td class="formDeLabel">State</td>
																<td class="formDeLabel">Zip Code</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="otherAddress.city" /></td>
																<td class="formDe"><bean:write name="associateForm" property="otherAddress.state" /></td>
																<td class="formDe">
																	<bean:write name="associateForm" property="otherAddress.zipCode" />
																	- 
																	<bean:write name="associateForm" property="otherAddress.additionalZipCode" />
																</td>
															</tr>
															<tr>
																<td class="formDeLabel" colspan="3">Address Complex Name</td>
															</tr>
															<tr>
																<td class="formDe" colspan="3"><bean:write name="associateForm" property="otherAddress.addressComplexName" /></td>
															</tr>
															<tr>
																<td class="formDeLabel">Address Type</td>
																<td class="formDeLabel" colspan="2">County</td>
															</tr>
															<tr>
																<td class="formDe"><bean:write name="associateForm" property="otherAddress.addressType" /></td>
																<td class="formDe" colspan="2"><bean:write name="associateForm" property="otherAddress.county" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<logic:equal name="associateForm" property="action" value="<%=UIConstants.VIEW%>">
											<table border="0" width="98%">
												<tr>
													<td align="center">
														<html:submit property="submitAction">
															<bean:message key="button.back" />
														</html:submit> 
														<input type="submit" property="submitAction" onclick="changeFormActionURL(document.forms[0], '/<msp:webapp/>handleAssociateDisplayOptions.do?submitAction=Update',true);"
																	value="<bean:message key="button.update"/>"/>
														<input type="submit" property="submitAction" onclick="return confirmDelete();"
																	value="<bean:message key="button.delete"/>"/>
														<html:submit property="submitAction">
															<bean:message key="button.cancel" />
														</html:submit>	
													</td>
												</tr>
											</table>
											</logic:equal>
											<logic:notEqual name="associateForm" property="action" value="<%=UIConstants.VIEW%>">
											<table border="0" width="98%">
												<tr>
													<td align="center">
														<html:submit property="submitAction">
															<bean:message key="button.back" />
														</html:submit> 
														<html:submit property="submitAction">
															<bean:message key="button.finish" />
														</html:submit>
														<html:submit property="submitAction">
															<bean:message key="button.cancel" />
														</html:submit>
													</td>
												</tr>
											</table>
											</logic:notEqual>
											<!-- END BUTTON TABLE -->
										</td>
									</tr>
								</table>
								<br/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
