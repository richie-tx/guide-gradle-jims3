<!DOCTYPE HTML>
<!-- User selects the 'Create HCJDP' link on the left UI Navigation -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath 	Create Service JSP -->
<!-- 09/20/2006 Uma Gopinath 	Update/Inactivate flow added for ASP part 2-->
<!-- 06/04/2007 C Shimek		#42381 made code field editable for udpate when status is pending (P) -->
<!-- 10/13/2015 R Capestani     #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>




<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt"%>

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<html:javascript formName="serviceCodeForm" />
<html:javascript formName="serviceForm" />
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>/css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading" /> -
serviceCreateUpdate.jsp</title>
<script type="text/javascript"
	src="/<msp:webapp/>js/administerServiceProviderHCJPD/serviceCreate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<body topmargin="0" leftmargin="0">
<html:form action="/displayJuvServiceCreateUpdateSummary"
	target="content" focus="currentProgram.programService.updatedName">
	<logic:notEqual name="serviceProviderForm" property="actionType"
		value="updateService">
		<input type="hidden" name="helpFile"
			value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|379">
	</logic:notEqual>
	<logic:equal name="serviceProviderForm" property="actionType"
		value="updateService">
		<input type="hidden" name="helpFile"
			value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|377">
	</logic:equal>
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tabid" value="suggestedOrderTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img
						src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><bean:message key="prompt.3.spacer" /></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td class="header" align="center"><logic:notEqual
								name="serviceProviderForm" property="actionType"
								value="updateService">
								<bean:message key="prompt.add" />
							</logic:notEqual> <logic:equal name="serviceProviderForm" property="actionType"
								value="updateService">
								<bean:message key="prompt.update" />
							</logic:equal> <bean:message key="prompt.service" /></td>
						</tr>
					</table>

					<!-- END HEADING TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<ul>
								<li>Enter the required fields and click Next</li>
							</ul>
							</td>
						</tr>
						<tr>
							<td class="required"><bean:message key="prompt.3.diamond" />
							<bean:message key="prompt.requiredFieldsInstruction" /></td>
						</tr>
					</table>

					<!-- BEGIN ERRORS TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<table cellpadding="1" cellspacing="0" border="0" width="98%">
						<tr>
							<td bgcolor="#cccccc">
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td class="headerLabel" width="1%" nowrap="nowrap">Provider
									<bean:message key="prompt.name" /></td>
									<td colspan="3" class="headerData"><bean:write
										name="serviceProviderForm" property="providerName" /></td>
								</tr>
								<tr>
									<td class="headerLabel"><bean:message key="prompt.start" />
									<bean:message key="prompt.date" /></td>
									<td class="headerData"><bean:write
										name="serviceProviderForm" property="startDate"
										formatKey="date.format.mmddyyyy" /></td>
									<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.inHouse" /></td>
									<td class="headerData"><bean:write
										name="serviceProviderForm" property="isInHouse" /></td>
								</tr>
								<tr>
									<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.program" /> <bean:message key="prompt.name" /></td>
									<td colspan="3" class="headerData"><bean:write
										name="serviceProviderForm"
										property="currentProgram.programName" /></td>
								</tr>
								<tr>
									<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.targetIntervention" /></td>
									<td colspan="3" class="headerData"><bean:write
										name="serviceProviderForm"
										property="currentProgram.targetIntervention" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					<!-- END ERRORS TABLE --> <!-- BEGIN  TABLE --> <br>
					<table width="98%" cellpadding="2" cellspacing="0"
						class="borderTableBlue">
						<tr>
							<td class="detailHead" nowrap="nowrap" colspan="4"><bean:message
								key="prompt.service" /> <bean:message key="prompt.info" /></td>
						</tr>
						<tr>
							<td><html:hidden name="serviceProviderForm"
								property="actionType" /></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr>
									<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
										key="prompt.3.diamond" /><bean:message key="prompt.name" /></td>
									<td class="formDe"><html:text name="serviceProviderForm"
										property="currentProgram.programService.updatedName" size="30"
										maxlength="50" styleId="serviceName"/></td>
									<logic:equal name="serviceProviderForm" property="actionType"
										value="createService">
										<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
											key="prompt.3.diamond" /><bean:message key="prompt.code" /></td>
										<td class="formDe"><html:text name="serviceProviderForm"
											property="currentProgram.programService.code" size="10"
											maxlength="10" /></td>
									</logic:equal>

									<logic:equal name="serviceProviderForm" property="actionType"
										value="updateService">
										<logic:equal name="serviceProviderForm" property="statusId"
											value="P">
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
												key="prompt.3.diamond" /><bean:message key="prompt.code" /></td>
											<td class="formDe"><html:text name="serviceProviderForm"
												property="currentProgram.programService.code" size="10"
												maxlength="10" /></td>
										</logic:equal>
										<logic:notEqual name="serviceProviderForm" property="statusId"
											value="P">
											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
												key="prompt.code" /></td>
											<td class="formDe"><bean:write
												name="serviceProviderForm"
												property="currentProgram.programService.code" /></td>
										</logic:notEqual>
									</logic:equal>
									<input type="hidden" name="statusId"
										value='<bean:write name="serviceProviderForm" property="statusId" />'>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.3.diamond" /><bean:message key="prompt.type" /></td>
									<td class="formDe" colspan="3"><html:select
										property="currentProgram.programService.typeId" styleId="serviceType"
										name="serviceProviderForm">
										<html:option value="">
											<bean:message key="select.generic" />
										</html:option>
										<html:optionsCollection property="serviceTypeList"
											value="serviceTypeCode" label="description"
											name="serviceProviderForm" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
										key="prompt.3.diamond" /><bean:message
										key="prompt.maxEnrollment" /></td>
									<td class="formDe"><html:text name="serviceProviderForm"
										property="currentProgram.programService.maxEnrollment" styleId="txtMaxenrollment"
										size="4" maxlength="4" /></td>
									<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
										key="prompt.cost" /></td>
									<td class="formDe"><html:text name="serviceProviderForm"
										property="currentProgram.programService.cost" size="6" styleId="txtCost"
										maxlength="6" /> <html:select
										property="currentProgram.programService.costBasisId"
										name="serviceProviderForm">
										<html:option value="">
											<bean:message key="select.generic" />
										</html:option>
										<html:optionsCollection property="costBasisList" value="code"
											label="description" name="serviceProviderForm" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.service" /> <bean:message key="prompt.location" />
									Unit(s)</td>

									<td class="formDe" colspan="3"><html:select
										name="serviceProviderForm"
										property="currentProgram.programService.locationNames"
										multiple="true" size="4">
										<html:option key="select.multiple" value="" />
										<html:optionsCollection name="serviceProviderForm"
											property="serviceLocationNames" value="juvLocationUnitId"
											label="locationUnitName" />
									</html:select> <br>
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" colspan="4"><bean:message
										key="prompt.3.diamond" /><bean:message
										key="prompt.description" />
										(Max. characters allowed: 1000)
									 <tiles:insert
										page="../../common/spellCheckTile.jsp" flush="false">
										<tiles:put name="tTextField"
											value="currentProgram.programService.description" />
										<tiles:put name="tSpellCount" value="spellBtn1" />
									</tiles:insert></td>
								</tr>
								<tr>
									<td class="formDe" colspan="4"><html:textarea rows="8"
										style="width:100%" name="serviceProviderForm"
										property="currentProgram.programService.description" 
										onmouseout="textCounter(this, 1000);" onkeydown="textCounter( this, 1000 )"></html:textarea>
									</td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					<!-- BEGIN BUTTON TABLE -->
					<table border="0">
						<tr>
						</tr>
						<tr>
							<td align="center"><html:submit property="submitAction">
								<bean:message key="button.back"></bean:message>
							</html:submit> <html:submit property="submitAction"
								onclick="return validateFields();">
								<bean:message key="button.next"></bean:message>
							</html:submit> <html:reset>
								<bean:message key="button.reset"></bean:message>
							</html:reset> <logic:equal name="serviceProviderForm" property="actionType"
								value="updateService">
								<jims:isAllowed requiredFeatures="CS-ASP-INACTIVATEJUV">
									<html:submit property="submitAction">
										<bean:message key="button.inactivate"></bean:message>
									</html:submit>
								</jims:isAllowed>
							</logic:equal> <html:submit property="submitAction">
								<bean:message key="button.cancel"></bean:message>
							</html:submit></td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --></td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	<!-- END  TABLE --></div>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
