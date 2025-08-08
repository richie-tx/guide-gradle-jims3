<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/13/2007	 Hien Rodriguez - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG--><head>
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
<title><bean:message key="title.heading" /> - adminStaff/positionSearch.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
 <html:javascript formName="positionSearchForm" /> 
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/organization.js"></script>
<script>
function valPage(theFormElem){
	var newValAr=new Array(7); 
	newValAr[0]="userId";
	newValAr[1]="name.lastName";
	newValAr[2]="cjad";
	newValAr[3]="positionName";
	newValAr[4]="divisionId"; 
	newValAr[5]="workgroupName";
	newValAr[6]="officerTypeId";
	var elemLastName=document.getElementsByName("name.lastName")[0];
	var elemFirstName=document.getElementsByName("name.firstName")[0];
	var elemMiddleName=document.getElementsByName("name.middleName")[0];
	if(trimAll(elemLastName.value)=='' && (trimAll(elemFirstName.value)!='' || trimAll(elemMiddleName.value)!='')){
		alert("Last name is required if searching by First or Middle Name.");
	}
	else{
		var retVal=atLeastOneReq(newValAr);
		if(retVal){
			return validatePositionSearchForm(theFormElem);
		}
	}
	return false;
	
}


<logic:iterate indexId="organizationIterIndex" id="organizationIter" name="adminStaffSearchForm" property="organizations">
	organizations[<bean:write name="organizationIterIndex"/>] = new suborganization("<bean:write name="organizationIter" property="organizationId" />", "<bean:write name="organizationIter" property="description" />");
	<logic:notEmpty  name="organizationIter" property="children">	
	<logic:iterate indexId="organizationIterIndex2" id="organizationIter2" name="organizationIter" property="children">
		var innerOrganization = new suborganization("<bean:write name="organizationIter2" property="organizationId" />", "<bean:write name="organizationIter2" property="description" />");
		organizations[<bean:write name="organizationIterIndex"/>].suborganizations[<bean:write name="organizationIterIndex2"/>] = innerOrganization;
		<logic:notEmpty name="organizationIter2" property="children">
		<logic:iterate indexId="organizationIterIndex3" id="organizationIter3" name="organizationIter2" property="children">
			var innerOrganization = new suborganization("<bean:write name="organizationIter3" property="organizationId" />", "<bean:write name="organizationIter3" property="description" />");
			organizations[<bean:write name="organizationIterIndex"/>].suborganizations[<bean:write name="organizationIterIndex2"/>].suborganizations[<bean:write name="organizationIterIndex3"/>] = innerOrganization;
		</logic:iterate>
		</logic:notEmpty>
	</logic:iterate>
	</logic:notEmpty>
</logic:iterate>
sortOrganizations(organizations); 

</script> 

</head>
<body topmargin="0" leftmargin="0" onload="reloadOrganization(document.forms[0], 'divisionId','programUnitId','programSectionId','<bean:write name="adminStaffSearchForm" property="programUnitId"/>', '<bean:write name="adminStaffSearchForm" property="programSectionId"/>')" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayPositionSearch" target="content" focus="userId">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Positions/CSCD_Staff_Position.htm#|9">


					<div align="center">
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top"><img src="../../common/images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="/jsp/common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab"/>
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
								<!--tabs start-->
									<tiles:insert page="/jsp/common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="positionsTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
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
							    			<td align="center" class="header">
							    				<bean:message key="title.CSCD" /> - <bean:message key="prompt.position" /> <bean:message key="prompt.search" />
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
															
															<!-- BEGIN INSTURCTION TABLE -->
															<table width="98%" border="0">
																<tr>
																	<td>
																		<ul>
																			<li>Enter search criteria. Click Submit.</li>
																		</ul>
																	</td>
																</tr>
																<tr>
																	<td class="required" colspan="2">At least one search criteria required. + Last Name is Required if searching by First/Middle Name.</td>
																</tr>
															</table>
															<!-- END INSTRUCTION TABLE -->
															<!-- SEARCH start -->
															<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr class="detailHead">
																	<td>
																		<bean:message key="prompt.search" /> <bean:message key="prompt.staff" /> <bean:message key="prompt.positions" />
																	</td>
																</tr>
																<tr>
																	<td>																
																		<table width="100%" cellpadding="2" cellspacing="1">
																			<!-- SEARCH BY user info-->
																			<tr>
																				<td class="formDeLabel" nowrap><bean:message key="prompt.userId" /></td>
																				<td class="formDe">
																					<html:text property="userId" size="8" maxlength="8"/>
																				</td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap><bean:message key="prompt.lastName" /></td>
																				<td class="formDe">
																					<html:text property="name.lastName" size="30" maxlength="75"/>
																				</td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap><bean:message key="prompt.plusSign"/><bean:message key="prompt.firstName" /></td>
																				<td class="formDe">
																					<html:text property="name.firstName" size="25" maxlength="50"/>
																				</td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.plusSign"/><bean:message key="prompt.middleName" /></td>
																				<td class="formDe">
																					<html:text property="name.middleName" size="25" maxlength="50"/>
																				</td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.cjad" /></td>
																				<td class="formDe">
																					<html:text property="cjad" size="9" maxlength="9"/>
																				</td>
																			</tr>																			
																			<tr>
																				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.cstsOfficerType"/></td>
																				<td class="formDe">
																					<%-- ER JIMS200040641 need officerType codeTable--%> 
																					<html:select property="officerTypeId">
																						<html:option value=""><bean:message key="select.generic"/></html:option>
																						<html:optionsCollection property="CSTSOfficerTypes" value="code" label="description" />
																					</html:select>
																				</td>
																			</tr>
																			<!-- SEARCH BY position info-->
																			<tr>
																			  <td class="formDeLabel" nowrap><bean:message key="prompt.positionName" /> </td>
																			  <td class="formDe"><html:text property="positionName" size="50" maxlength="50"/></td>
																		  </tr>

																		  
																			<tr>
																				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.division" /></td>
																				<td class="formDe">
																					<html:select size="1" property="divisionId" onchange="updateOrganization2(document.forms[0], 'divisionId','programUnitId','programSectionId')">
																						<html:option value=""><bean:message key="select.generic"/></html:option>
																						 <html:optionsCollection property="organizations" value="organizationId" label="description" />
																					</html:select>
																				</td>
																			</tr>
														
																			<tr>
																				<td class="formDeLabel" nowrap><bean:message key="prompt.programUnit" /></td>
																				<td class="formDe">
																					<html:select size="1" property="programUnitId" disabled="true" onchange="updateOrganization3(document.forms[0], 'divisionId','programUnitId','programSectionId')">
																						<html:option value=""><bean:message key="select.generic"/></html:option>
																					</html:select>
																				</td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.programSection" /></td>
																				<td class="formDe">
																					<html:select size="1" property="programSectionId" disabled="true">
																						<html:option value=""><bean:message key="select.generic"/></html:option>
																					</html:select>
																				</td>
																			</tr>
																			<!-- SEARCH BY other cscd info-->
																			<tr>
																				<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.workgroup" /></td>
																				<td class="formDe">
																					<html:text property="workgroupName" size="25" maxlength="25"/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
															<!-- SEARCH end-->
															<br>
															<!-- BEGIN BUTTON TABLE -->
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction" onclick="return valPage(this.form) && disableSubmit(this, this.form)"><bean:message key="button.submit" /></html:submit>
																		<html:submit property="submitAction"><bean:message key="button.refresh" /></html:submit>
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
					<script>
					updateOrganization2(document.forms[0], 'divisionId','programUnitId','programSectionId')
			
</script>
				</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
