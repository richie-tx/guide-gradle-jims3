<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>

<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title></title>
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head>
<logic:equal name="complianceForm" property="displayAction" value="<%=UIConstants.ADVANCED%>">
	<table width="98%" border="0">
		<tr>
			<td class=required colspan="2"> <img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> At least 1 required&nbsp;&nbsp; +Last Name required if searching by First Name</td>
		</tr>
	</table>
</logic:equal>
<tiles:useAttribute name="returnPage" ignore="true" />

<!-- BEGIN SEARCH TABLE -->
<%-- debugging display <bean:write name="returnPage" />  --%>
<!-- BEGIN ADVANCED SEARCH -->
	<logic:equal name="complianceForm" property="displayAction" value="<%=UIConstants.ADVANCED%>">
		<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			<tr>
				<td class="detailHead"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.casenotes" />&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="returnPage" value="NonCompliant">
						<a href="/<msp:webapp/>displaySetToNonCompliantSummary.do?submitAction=<bean:message key="button.basicSearch"/>"><bean:message key="button.basicSearch"/></a>
					</logic:equal>	
					<logic:equal name="returnPage" value="Compliant">
						<a href="/<msp:webapp/>displayResolveNonComplianceSummary.do?submitAction=<bean:message key="button.basicSearch"/>"><bean:message key="button.basicSearch"/></a>
					</logic:equal>	
				</td>	
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="2">
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dateRange" /></td>
							<td class="formDe">
								<table cellpadding="2" cellspacing="1">
									<tr>
										<td class="formDeLabel"><bean:message key="prompt.begin" /></td>
										<td class="formDeLabel"><bean:message key="prompt.end" /></td>
									</tr>
									<tr class="formDe">    
										<td>
											<html:text name="complianceForm" property="searchBeginDateAsString" maxlength="10" size="10" />
												<A HREF="#" onClick="cal1.select(document.getElementById('searchBeginDateAsString'),'anchor1','MM/dd/yyyy'); return false;"
					                                NAME="anchor1" ID="anchor1"><bean:message key="prompt.3.calendar"/></A>&nbsp;-   
										</td>
										<td>
											<html:text name="complianceForm" property="searchEndDateAsString" maxlength="10" size="10" />
										        <A HREF="#" onClick="cal1.select(document.getElementById('searchEndDateAsString'),'anchor2','MM/dd/yyyy'); return false;"
										            NAME="anchor2" ID="anchor2"><bean:message key="prompt.3.calendar"/></A>
										</td> 
									</tr>
								</table> 
							</td> 
							<td class="formDeLabel"><bean:message key="prompt.court" /></td>
							<td class="formDe">
								<html:text name="complianceForm" property="searchCourt" maxlength="5" size="5" />
							</td>
						</tr> 
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.collateral" /></td>
							<td class="formDe" colspan="3" valign="top">
								<table cellpadding="1" cellspacing="1">
									<tr>
										<td valign="top">
				    	                    <html:select name="complianceForm" property="searchCollateralId" size="1" onchange="showHideCollateralAssociates(this.value, 'SearchCollateralAssociates', 'row');">
												<html:option value=""><bean:message key="select.generic" /></html:option>
							                    <html:optionsCollection name="complianceForm" property="collateralList" value="code" label="description" />
											</html:select>
										</td>
										<td id="SearchCollateralAssociates" class="hidden">
			                                <html:select name="complianceForm" property="searchAssociateIds" size="3"  multiple="true">
							                    <html:option value=""><bean:message key="select.generic" /></html:option>
							                    <html:optionsCollection name="complianceForm" property="associatesList" value="associateId" label="displayLabel" />
							                </html:select>
										</td>
									</tr>
								</table>
							</td>
						</tr> 
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.howGenerated" /></td>
							<td class="formDe" colspan="3">
			                   	<html:select name="complianceForm" property="searchGeneratedById" size="1" onchange="showHideCreatedBy(this.value, 'createdBy', 'row')"> 
						       		<html:option value=""><bean:message key="select.generic" /></html:option>
					            	<html:optionsCollection name="complianceForm" property="generatedByList" value="code" label="description"/> 
					            </html:select> 
							</td>
						</tr>
						<tr id="createdBy" class="hidden">
							<td class="formDeLabel"><bean:message key="prompt.createdBy" /></td>
							<td class="formDe" colspan="3">
								<table cellpadding="2" cellspacing="1">
									<tr class="formDeLabel">
										<td>+<bean:message key="prompt.lastName" /></td>
										<td><bean:message key="prompt.firstName" /></td>
									</tr>
									<tr>
										<td>
											<html:text name="complianceForm" property="createdByName.lastName" maxlength="75" size="30" />
										</td>
										<td>
											<html:text name="complianceForm" property="createdByName.firstName" maxlength="50" size="30" />
										</td>
									</tr>
								</table>
							</td>
						</tr> 
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.casenoteType" /></td>
							<td class="formDe">
			            	  	<html:select name="complianceForm" property="searchCasenoteTypeId" size="1">
				            	  	<html:option value=""><bean:message key="select.generic" /></html:option>
							       	<html:option value="ALL"><bean:message key="select.all" /></html:option>
							        <html:optionsCollection name="complianceForm" property="casenoteTypeList" value="code" label="description" />
							   	</html:select>
							</td>
							<td class="formDeLabel" valign="top"><bean:message key="prompt.cases" /></td>
							<td class="formDe" valign="top">
								<logic:empty name="complianceForm" property="caseNumbers">
									<html:select property="group2Id" disabled="true">
										<html:option value=""><bean:message key="select.generic" /></html:option>
									</html:select>
								</logic:empty>
								<logic:notEmpty name="complianceForm" property="caseNumbers">
						   			<html:select name="complianceForm" property="searchCaseNumberIds" multiple="true">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection property="caseNumbers" value="key" label="value" />
									</html:select>
								</logic:notEmpty>											
							</td>
						</tr> 
						<tr>
							<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.casenoteSubjects" /></td>
							<td class="formDe" valign="top" colspan="3">
			                   	<html:select name="complianceForm" property="searchSubjectIds" size="3" multiple="true">
						           	<html:option value=""><bean:message key="select.generic" /></html:option>
						           	<html:optionsCollection name="complianceForm" property="casenoteSubjectList" value="code" label="description" />
						        </html:select>
							</td>
						</tr> 
						<tr>
							<td class="formDeLabel"></td>
							<td class="formDe" colspan="3">
								<html:submit property="submitAction" onclick="return checkAdvancedSearchCriteria(this.form) && disableSubmit(this, this.form);"><bean:message key="button.search" /></html:submit>
								<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.viewAll" /></html:submit>
							</td>
						</tr> 
					</table> 
				</td>
			</tr>
		</table>		
	</logic:equal>
<!-- END ADVANCED SEARCH -->	
<!-- BEGIN BASIC SEARCH  -->		
	<logic:notEqual name="complianceForm" property="displayAction" value="<%=UIConstants.ADVANCED%>">				

		<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			<tr>
				<td class="detailHead"><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.casenotes" />&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="returnPage" value="NonCompliant">
					<%--	<a href="/<msp:webapp/>displaySetToNonCompliantSummary.do?submitAction=<bean:message key="button.advancedSearch"/>"><bean:message key="button.advancedSearch"/></a> --%>
					</logic:equal>	
					<logic:equal name="returnPage" value="Compliant">
					<%--	<a href="/<msp:webapp/>displayResolveNonComplianceSummary.do?submitAction=<bean:message key="button.advancedSearch"/>"><bean:message key="button.advancedSearch"/></a> --%>
					</logic:equal>	
				</td>	
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="1">
						<tr>
							<td valign="top" class="formDeLabel" nowrap width="1%"><bean:message key="prompt.searchBy" />:&nbsp;&nbsp;
			                  	<html:select name="complianceForm" property="searchById" onchange="displayBasicSearchFields(this)">
					            	<html:option value=""><bean:message key="select.generic" /></html:option>
					            	<html:optionsCollection name="complianceForm" property="searchByList" value="code" label="description" />
				                 </html:select> 
							</td>
							<td class="formDe" valign="top">
								<span id="howGeneratedField" class="hidden">
									<bean:message key="prompt.3.diamond" />
				                   	<html:select name="complianceForm" property="searchGeneratedById" size="1" onchange="showHideCreatedBy(this.value, 'createdByFieldBasic', 'row');">
							        	<html:option value=""><bean:message key="select.generic" /></html:option>
							        	<html:optionsCollection name="complianceForm" property="generatedByList" value="code" label="description"/>
							        </html:select>
									<span class="hidden" id="createdByFieldBasic">
										<table cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td>+<bean:message key="prompt.lastName" /></td>
												<td><bean:message key="prompt.firstName" /></td>
											</tr>
											<tr>
												<td>
													<html:text name="complianceForm" property="createdByName.lastName" maxlength="75" size="30"/> 
												</td>
												<td>
													<html:text name="complianceForm" property="createdByName.firstName" maxlength="50" size="30"/> 
												</td>
											</tr>
										</table>
									</span> 
								</span>  
								<span id="dateRange" class="hidden">
									<table cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.begin"/></td>
											<td class="formDeLabel"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.end"/></td>
										</tr>
										<tr class="formDe">
											<td>
												<html:text name="complianceForm" property="searchBeginDateAsString" maxlength="10" size="10"/>
													<A HREF="#" onClick="cal1.select(document.getElementById('searchBeginDateAsString'),'anchor3','MM/dd/yyyy'); return false;"
									                   NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar"/></A> -
											</td>
											<td>
												<html:text name="complianceForm" property="searchEndDateAsString" maxlength="10" size="10"/>
											        <A HREF="#" onClick="cal1.select(document.getElementById('searchEndDateAsString'),'anchor4','MM/dd/yyyy'); return false;"
											           NAME="anchor4" ID="anchor4"><bean:message key="prompt.3.calendar"/></A>
											</td>
										</tr> 
									</table>
								</span>
								<span id="courtField" class="hidden">
									<bean:message key="prompt.3.diamond" /><html:text property="searchCourt" maxlength="5" size="5"/>
								</span>
								<span id="typeField" class="hidden">
					 				<bean:message key="prompt.3.diamond" />
		                            <html:select name="complianceForm" property="searchCasenoteTypeId" size="1">
			                            <html:option value=""><bean:message key="select.generic" /></html:option>
			                            <html:optionsCollection name="complianceForm" property="casenoteTypeList" value="code" label="description" />
			                        </html:select>
								</span>
								<span id="casesField" class="hidden">
									<logic:empty name="complianceForm" property="caseNumbers">
										<html:select property="group2Id" disabled="true">
											<html:option value=""><bean:message key="select.generic" /></html:option>
										</html:select>
									</logic:empty>
									<logic:notEmpty name="complianceForm" property="caseNumbers">
							   			<html:select name="complianceForm" property="searchCaseNumberIds"  multiple="true">
											<html:option value=""><bean:message key="select.all" /></html:option>
											<html:optionsCollection property="caseNumbers" value="key" label="value" />
										</html:select>
									</logic:notEmpty>											
								</span>    
								<span id="subjectField" class="hidden">
									<table cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><bean:message key="prompt.3.diamond" /></td>
											<td>
												<html:select name="complianceForm" property="searchSubjectIds" size="3" multiple="true">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="complianceForm" property="casenoteSubjectList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
									</table>
								</span>
								<span id="contactField" class="hidden">
									<table cellpadding="2" cellspacing="2">
										<tr>
											<td valign="top" nowrap><bean:message key="prompt.3.diamond" />
			                                	<html:select name="complianceForm" property="searchCollateralId" size="1" onchange="showHideCollateralAssociates(this.value, 'SearchCollateralAssociates', 'row');">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="complianceForm" property="collateralList" value="code" label="description" />
												</html:select>
											</td>
											<td id="SearchCollateralAssociates" class="hidden">
												<html:select name="complianceForm" property="searchAssociateIds" size="3" multiple="true">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="complianceForm" property="associatesList" value="associateId" label="displayLabel"/>
												</html:select>
											</td>
										</tr>
									</table> 
								</span>  
							</td>
							<td class="formDe" valign="top"></td>
						</tr>
						<tr>
							<td align="center" colspan="3">
								<html:submit property="submitAction" onclick="return checkBasicSearchCriteria(this.form)&& disableSubmit(this, this.form);"><bean:message key="button.search" /></html:submit>
							    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.viewAll" /></html:submit>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
<!-- END BASIC SEARCH -->
	</logic:notEqual>	
