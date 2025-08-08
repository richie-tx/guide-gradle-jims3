<!DOCTYPE HTML>

<%-- Used for Risk Categories Create Summary in MJCW --%>
<%--MODIFICATIONS --%>
<%-- Dwilliamson  01/09/2011	Create JSP --%>
<%-- 05/17/2012		CShimke		Revised Sort Order to UI Display Order in question listing block, per email request --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" /> - riskAnalysisDataControl/category/riskCategoryCreateSummary.jsp</title>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskCategoryCreate" target="content">
<span align="center">
<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.category"/>
				<logic:equal name="riskCategoryCreateForm" property="actionType" value="summary">
					<bean:message key="prompt.summary"/>
				</logic:equal>
				<logic:equal name="riskCategoryCreateForm" property="actionType" value="confirm">
					<bean:message key="prompt.confirmation"/>
				</logic:equal>
			</td>
		</tr>
  		<logic:equal name="riskCategoryCreateForm" property="actionType" value="confirm">	
  			<tr align="center"><td class="confirm">Category successfully created.</td></tr>
  		</logic:equal>
	</table>
<!-- END HEADING TABLE -->

<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
	<div class="spacer4px"></div>
<!-- BEGIN INSTRUCTION TABLE -->
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Back button to return to previous page.</li>        
					<li>Select Finish button to save Category Information</li>
				</ul>
			</td>
		</tr>  
	</table>
<!-- END INSTRUCTION TABLE -->
<%-- BEGIN CATEGORY DISPLAY TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<table width="100%" cellpadding="2" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.category"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="4"><bean:message key="prompt.category"/>&nbsp;<bean:message key="prompt.information"/></td>
					</tr>
					<logic:equal name="riskCategoryCreateForm" property="actionType" value="summary">
						<tr>
							<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.categoryName"/></td>
							<td class="formDe" colspan="3"><bean:write name="riskCategoryCreateForm" property="category.categoryName" /></td>
						</tr>
						<tr>
							<td width="1%" nowrap class="formDeLabel"><bean:message key="prompt.riskResultGroup"/></td>
							<td class="formDe" colspan="3"><bean:write name="riskCategoryCreateForm" property="category.riskResultGroup" /></td>
						</tr>
					</logic:equal>	
					<logic:equal name="riskCategoryCreateForm" property="actionType" value="confirm">
						<tr>
							<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.categoryName"/></td>
							<td class="formDe" width="40%"><bean:write name="riskCategoryCreateForm" property="category.categoryName" /></td>
							<td class="formDeLabel" width="1%" nowrap="nowrap">&nbsp;<bean:message key="prompt.entryDate"/></td>
							<td class="formDe"><bean:write name="riskCategoryCreateForm" property="category.entryDate" formatKey="date.format.mmddyyyy" /></td>
						</tr>
						<tr>
							<td width="1%" nowrap class="formDeLabel"><bean:message key="prompt.riskResultGroup"/></td>
							<td class="formDe"><bean:write name="riskCategoryCreateForm" property="category.riskResultGroup" /></td>
							<td class="formDeLabel" width="1%" nowrap="nowrap">&nbsp;<bean:message key="prompt.version"/></td>
							<td class="formDe"><bean:write name="riskCategoryCreateForm" property="category.version" /></td>
						</tr>
					</logic:equal>	
					<tr>
						<td class='formDeLabel' colspan="4"><bean:message key="prompt.description"/></td>
					</tr>
					<tr>
   						<td class='formDe' colspan="4"><bean:write name="riskCategoryCreateForm" property="category.categoryDescription" /></td>
    				</tr>
				</table>
				<div class="spacer4px"></div>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">    
					<tr class="detailHead">
						<td colspan="5"><bean:message key="prompt.questions"/></td>
					</tr>				 
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td class="detailHead"><bean:message key="prompt.selectedList"/></td>
											</tr>
										</table>
										<bean:size id="sel2" name="riskCategoryCreateForm" property="questionList"/>
										<table width="100%" cellpadding="0" cellspacing="0" class=borderTable>
											<tr>
												<td>
													<table width=100% cellpadding=2 cellspacing=0>
														<tr class="listHeader">
															<td class="formDeLabel" width="27%"><bean:message key="prompt.questionName"/></td>
															<td class="formDeLabel" width="28%"><bean:message key="prompt.questionText"/></td>
															<td class="formDeLabel" width="10%"><bean:message key="prompt.UIDisplayOrder"/></td>
															<td class="formDeLabel" width="15%"><bean:message key="prompt.UIControlType"/></td>
															<td class="formDeLabel" width="15%"><bean:message key="prompt.initialAction"/></td>
														</tr>
													</table>
												</td>
												<logic:greaterThan name="sel2" value="4">
													<td width="2%" class="formDeLabel">&nbsp;</td>
												</logic:greaterThan>	
											</tr>
										</table>
										<logic:empty  name="riskCategoryCreateForm" property="questionList" >
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="formDe">
													<td>No Questions found for this category.</td>
												</tr>
											</table>
										</logic:empty>
										<logic:notEmpty  name="riskCategoryCreateForm" property="questionList" >	       
											<div class="scrollingDiv100">
												<table width="100%" cellpadding="2" cellspacing="1">
													<logic:iterate id="quest" name="riskCategoryCreateForm" property="questionList" indexId="index2">
														<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
															<td width=27%><bean:write name="quest" property="questionName" /></td>
															<td width=28%><bean:write name="quest" property="questionText" /></td>
															<td width=10%><bean:write name="quest" property="uiDisplayOrder" /></td>
															<td width=15%><bean:write name="quest" property="uiControlType" /></td>
															<td width=15%><bean:write name="quest" property="initialAction" /></td>
														</tr>
													</logic:iterate>  
												</table>
											</div>
										</logic:notEmpty> 
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</span>
<div class="spacer"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal name="riskCategoryCreateForm" property="actionType" value="summary">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit> 
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish"/></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
			</logic:equal>
			<logic:equal name="riskCategoryCreateForm" property="actionType" value="confirm">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.categorySearch"/></html:submit>
			</logic:equal>
		</td>
	</tr>
</table>
</html:form> 
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>