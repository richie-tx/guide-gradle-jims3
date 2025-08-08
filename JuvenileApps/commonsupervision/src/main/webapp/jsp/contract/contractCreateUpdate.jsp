<!DOCTYPE HTML>
<!-- 10/04/2006	 Clarence Shimek    Create JSP -->
<!-- 07/27/2007	 Leslie Deen	    Defect #43346 -->
<!-- 10/26/2007	 Clarence Shimek    #46036 add cursor set -->
<!-- 10/19/2015  R Young          - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - contract/contractCreateUpdate.jsp</title>

<%@ include file="../jQuery.fw" %>


<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/condition_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/contracts/contractCreateUpdate.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayContractSummary" target="content" focus="contractNum">
<logic:equal name="contractForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|421">
</logic:equal> 
<logic:equal name="contractForm" property="action" value="update">
	<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|418">
</logic:equal>
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
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value=""/>
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
						<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header">
										<logic:equal name="contractForm" property="action" value="create">
											<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.contract" />
										</logic:equal> 
										<logic:equal name="contractForm" property="action" value="update">
											<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.contract" />
										</logic:equal> 									
									</td>
								</tr>
							</table>
              <!-- END HEADING TABLE -->
              <!-- BEGIN ERROR TABLE -->
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
											<li>Enter the required fields and click Next.</li>
										</ul>
									</td>
								</tr>
							</table>
              <!-- END INSTRUCTION TABLE -->							
							<logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
								<tiles:insert page="../common/contractServiceProviderInfo.jsp" flush="true"></tiles:insert>							
			  <!-- END SERVICE PROVIDER TABLE -->	
								<table width="98%" cellpadding="1" cellspacing="1">
									<tr>
										<td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" /> <span id="spContentInstructions">+Service Provider Value required if a Contract Value is enterred</span>&nbsp;&nbsp;&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
									</tr>  
								</table> 
							</logic:equal>		
							<logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y">					
								<table width="98%" cellpadding="1" cellspacing="1">
									<tr>
										<td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
									</tr>
								</table>
							</logic:notEqual>
           					<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
				                <tr>
                					<td class="detailHead"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.info" /> </td>
				                </tr>
                				<tr>
									<td>
										<table width="100%" cellpadding="4" cellspacing="1">
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.number" /></td>
											<td class="formDe" colspan="3">
												<html:text property="contractNum" size="10" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.name" /></td>
											<td class="formDe" colspan="3">
												<html:text property="contractName" size="50" maxlength="50"/>												
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.startDate" /></td>
											<td class="formDe">
												<html:text name="contractForm" property="startDate" size="10" maxlength="10" styleId="startDate"/>
											</td>
											<td class="formDeLabel" width="1%" nowrap>
												<bean:message key="prompt.endDate" />
												<input type="hidden" name="originalEndDate" value=<bean:write name="contractForm" property="endDate" formatKey="date.format.mmddyyyy"/> >	
											</td>
											<td class="formDe" width="1%" nowrap>
												<html:text name="contractForm" property="endDate" size="10" maxlength="10" styleId="endDate"/>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.fundingType" /></td>
											<td class="formDe" colspan="3">
												<html:select property="contractTypeId">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="contractTypes"  value="code" label="description" name="contractForm"/>
												</html:select>  
											</td>
										</tr>
										<tr>	
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.gLAccountKey" /></td>
											<td class="formDe" colspan="3">
												<html:select property="glAccountKeyId">
													<html:option key="select.generic" value="" />
													<html:optionsCollection property="glAccountKeys"  value="code" label="description"  name="contractForm"/>
												</html:select> 
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.programFunding" />&nbsp;<bean:message key="prompt.description" /></td>
											<td class="formDe" colspan="3" >
												<html:text property="programFundingDesc" size="50" maxlength="50"/>											
											</td>
										</tr>
										<logic:equal name="contractForm" property="departmentId" value="CSC">
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.tracerNumber" />&nbsp;<bean:message key="prompt.range" /></td>
												<td class="formDe" colspan="3">
													<html:text property="tracerNumberRangeFrom" size="10"/>
													-
													<html:text property="tracerNumberRangeTo" size="10"/>												
												</td>
											</tr>
										</logic:equal> 
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.value" /></td>
											<logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
												<td class="formDe">
													<html:text property="totalValue" size="10" /> <%-- &nbsp;<bean:write name="contractForm" property="availableContractValue" />	--%>
												</td>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.plusSign" /><bean:message key="prompt.service" />&nbsp;<bean:message key="prompt.provider" />&nbsp;<bean:message key="prompt.value" /></td>
												<td class="formDe">
													<html:text property="serviceProviderValue" size="10" />
												</td>
											</logic:equal>  
											<logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y"> 
												<td class="formDe" colspan="3">
													<html:text property="totalValue" size="10" />
													 <%-- &nbsp;<bean:write name="contractForm" property="availableContractValue" />	 --%>
												</td>
											</logic:notEqual>
										</tr> 
									</table> 
									</td>
								</tr>
							</table> 
							<br>
							<logic:equal name="contractForm" property="action" value="update">
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.associated" />&nbsp;<bean:message key="prompt.service" />s</td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td><bean:message key="prompt.name" /></td>
												<td><bean:message key="prompt.code" /></td>
												<td><bean:message key="prompt.type" /></td>
												<td><bean:message key="prompt.service"/> <bean:message key="prompt.location"/> Unit(s)</td>
											</tr>
											<%  int RecordCounter = 0;
												String bgcolor = "";%>  								
											<logic:iterate id="serviceIterator" name="contractForm" property="services">
											<tr
												class=<%RecordCounter++;
												bgcolor = "alternateRow";
												if (RecordCounter % 2 == 1)
													bgcolor = "normalRow";
												out.print(bgcolor);%>>										
													<td>
														<a href="/<msp:webapp/>displayServiceDetails.do?serviceId=<bean:write name="serviceIterator" property="serviceId"/>" ><bean:write name="serviceIterator" property="serviceName"/></a> 
													</td>
													<td><bean:write name="serviceIterator" property="serviceCode" /></td>
													<td><bean:write name="serviceIterator" property="serviceType" /></td>
													<td><bean:write name="serviceIterator" property="locationName" /></td>
											</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
							</logic:equal>  
							<br>
				<!-- BEGIN BUTTON TABLE -->
 						<table border="0">
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;										
										<html:submit property="submitAction" onclick="return checkInputs(this.form);"><bean:message key="button.next" /></html:submit>&nbsp;
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
										<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
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
  <!-- END TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
