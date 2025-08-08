<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/04/2006	 Clarence Shimek    Create JSP -->

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
<title><bean:message key="title.heading" /> - contract/contractEnterSPValue1.jsp</title>

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
		if (spfld > avfld){
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
function setFieldFocus(fldName)
{
	for(i=0; i<document.forms[0].length; i++) 
	{
		if (document.forms[0].elements[i].name == fldName)
		{ 
			document.forms[0].elements[i].focus();
	        break; 
	  	}
	}  
	return;   	
}
</script>
</head>
<body topmargin="0" leftmargin="0">
<html:form action="/displayServiceProviderContractSummary" target="content">
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
							<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
				<!-- END ERROR TABLE -->
			
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
			
			<!-- BEGIN SERVICE INFORMATION TABLE -->  <%--img border=0 src="../../common/images/expand.gif" name="servInfo" --%>
			<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td class="detailHead">
						<table width="100%" cellpadding="2" cellspacing="0">
							<tr>
								<td width="1%"><a href="javascript:showHide('contractInfo', 'row',/<msp:webapp/>)" border="0"><img src="/<msp:webapp/>images/expand.gif" name="contractInfo" border="0" ></a></td>
								<td class="detailHead"><bean:message key="prompt.contract"/>&nbsp;<bean:message key="prompt.info"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr id="contractInfoSpan" class="hidden">
					<td>
						<table width="100%" cellpadding="4" cellspacing="1">
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.number" /></td>
								<td class="formDe"><bean:write name="contractForm" property="contractNum" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.name" /></td>
								<td class="formDe"><bean:write name="contractForm" property="contractName" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.startDate" /></td>
								<td class="formDe"><bean:write name="contractForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
								<td class="formDeLabel" nowrap><bean:message key="prompt.endDate" /></td>
								<td class="formDe"><bean:write name="contractForm" property="endDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.fundingType" /></td>
								<td class="formDe"><bean:write name="contractForm" property="contractType" /></td>
								<td class="formDeLabel" nowrap><bean:message key="prompt.gLAccountKey" /></td>
								<td class="formDe"><bean:write name="contractForm" property="glAccountKey" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.programFunding" />&nbsp;<bean:message key="prompt.description" /></td>
								<td class="formDe" colspan="3"><bean:write name="contractForm" property="programFundingDesc" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.tracerNumber" />&nbsp;<bean:message key="prompt.range" /></td>
								<td class="formDe">
									<bean:write name="contractForm" property="tracerNumberRangeFrom" />
									<logic:notEqual name="contractForm" property="tracerNumberRangeFrom" value="">
									&nbsp;-&nbsp;
									</logic:notEqual>
									<bean:write name="contractForm" property="tracerNumberRangeTo"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.value" /></td>
								<td class="formDe"><bean:write name="contractForm" property="totalValue" /></td>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.service" />&nbsp;<bean:message key="prompt.provider" />&nbsp;<bean:message key="prompt.value" /></td>
								<td class="formDe"><bean:write name="contractForm" property="serviceProviderValue" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END SERVICE INFORMATION TABLE -->
			<br>
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
												<td><bean:message key="prompt.serviceProviderName"/></td>
												<td><bean:message key="prompt.serviceName"/></td>
												<td><bean:message key="prompt.serviceProvider"/>&nbsp;<bean:message key="prompt.value"/></td>
											</tr>
											<logic:iterate id="serviceProviderServices" name="contractForm" property="serviceProviderServices" indexId="contractIndex">
											
											<tr>										
												<td><bean:write name="serviceProviderServices" property="serviceProviderName"/></td> 											
												<td><bean:write name="serviceProviderServices" property="serviceName"/></td> 
												<td class="formDe" width="30%"> 
													<html:text name="serviceProviderServices" property="SPValue" size="10" maxlength="10" indexed="true"/> 
												</td>
												<bean:define id="contractNumber">serviceProviderServices[<%=contractIndex %>].SPValue</bean:define>
															<script>
															addCurrencyValidation('<%=contractNumber%>','Service Provider Value <bean:write name='serviceProviderServices' property='serviceName'/> is not in currency format.');
															customValRequired('<%=contractNumber%>','Service Provider Value is required.',null);
															</script>
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
