<!DOCTYPE HTML>
<!-- 10/04/2006	 Clarence Shimek    Create JSP -->
<!-- 10/26/2007	 Clarence Shimek    #46464 revised setFieldFocus() to use type instead of name -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />

<html:base />
<title><bean:message key="title.heading" /> - contract/contractEnterSPValue.jsp</title>

<%-- script type="text/javascript" src="/<msp:webapp/>js/app.js"></script> --%>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript">
function checkInputs(theForm){
	var spFldName = "currentContractsList[0].serviceProviderValue";
	var spValues = document.getElementsByName(spFldName);
	var avFldName = "";
	var avValues = "";
	var spfld = "";
	var avfld = "";
	var indx = 0;
	while (spValues.length > 0){
		spfld = trimAll(spValues[0].value);
		avFldName = "currentContractsList[" + indx + "].availableContractValue";
		avValues = document.getElementsByName(avFldName);
		avfld = avValues[0].value;
//		alert("Is " + spfld + " greater than " + avfld + "?");
		if (parseInt(spfld) > parseInt(avfld)){
			alert("Service Provider Value can not be larger than Available Value.");
			setFieldFocus(spFldName);
			return false;
		}
		indx++;
		spFldName = 'currentContractsList[' + indx + '].serviceProviderValue'; 	
		spValues = document.getElementsByName(spFldName);
	}
	return true;
}

/* set focus on select field */
function setFieldFocus()
{
	for(i=0; i<document.forms[0].length; i++) 
	{
		if (document.forms[0].elements[i].type == 'text')
		{ 
			document.forms[0].elements[i].focus();
	        break; 
	  	}
	}  
	return;   	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onload="setFieldFocus()">
<html:form action="/displayServiceProviderContractSummary" target="content" >
<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|427">
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
									<td align="center" class="header"><bean:message key="prompt.serviceProviderContractValue" /></td>
								</tr>
							</table>
			<!-- END HEADING TABLE -->
			<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Enter the Service Provider Value for the following contract(s).</li>
										</ul>
									</td>
								</tr>
							</table>
			<!-- BEGIN  SERVICE PROVIDER TABLE -->
							<tiles:insert page="../common/contractServiceProviderInfo.jsp" flush="true"></tiles:insert>
			<!-- END SERVICE PROVIDER TABLE -->
							<div class="spacer"></div>
							<table width="98%" cellpadding="1" cellspacing="1">
								<tr>
									<td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction"/></td>
								</tr>
							</table>
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.contract"/>&nbsp;<bean:message key="prompt.info"/></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td><bean:message key="prompt.name"/></td>
												<td><bean:message key="prompt.number"/></td>
												<td><bean:message key="prompt.type"/></td>
												<td><bean:message key="prompt.startDate"/></td>
												<td><bean:message key="prompt.endDate"/></td>
											</tr>
											<logic:iterate id="currentContractsList" name="contractForm" property="currentContractsList" indexId="contractNum">
											
											<tr>										
												<td><bean:write name="currentContractsList" property="contractName"/></td> 											
												<td><bean:write name="currentContractsList" property="number"/></td> 
												<td><bean:write name="currentContractsList" property="contractType"/></td> 
												<td><bean:write name="currentContractsList" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
												<td><bean:write name="currentContractsList" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
											</tr>
											<tr>
												<td colspan="5">
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="20%" nowrap><bean:message key="prompt.available"/>&nbsp;<bean:message key="prompt.value"/></td>
															<td class="formDe" width="30%">
																<bean:write name="currentContractsList" property="availableContractValue" formatKey="currency.format" /> 
																<html:hidden name="currentContractsList" property="availableContractValue" indexed="true"/> 
															</td>
															<td class="formDeLabel" width="20%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.serviceProvider"/>&nbsp;<bean:message key="prompt.value"/></td>
															<td class="formDe" width="30%"> 
																   <logic:equal name="currentContractsList" property="serviceProviderValue" value="0.0">
																       <html:text name="currentContractsList" property="serviceProviderValue" size="10" maxlength="10" value="" indexed="true"/>
																   </logic:equal>
																   <logic:notEqual name="currentContractsList" property="serviceProviderValue" value="0.0">
																       <html:text name="currentContractsList" property="serviceProviderValue" size="10" maxlength="10" indexed="true"/>
																   </logic:notEqual> 
															</td>
															<bean:define id="contractNumber">currentContractsList[<%=contractNum %>].serviceProviderValue</bean:define>
															<script>
															addCurrencyValidation('<%=contractNumber%>','Service Provider Value is not in currency format.');
															customValRequired('<%=contractNumber%>','Service Provider Value is required.',null);
															</script>
														</tr>
													</table>
												</td>
											</tr> 
											</logic:iterate> 
										</table>
									</td>
								</tr> 
							</table>	
							<br>
				<!-- BEGIN BUTTON TABLE -->
							<table border="0">
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;
										<html:submit property="submitAction" onclick="return (validateCustomStrutsBasedJS(this.form) && checkInputs(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>&nbsp;										
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>
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
</div>	
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
