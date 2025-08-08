<!DOCTYPE HTML>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - contract/contractSummary.jsp</title>
<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/submitContractCreateUpdate" target="content">
<logic:equal name="contractForm" property="action" value="view">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|416">	
</logic:equal>
<logic:equal name="contractForm" property="action" value="searchResult">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|416">	
</logic:equal>

<logic:equal name="contractForm" property="action" value="create">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|422">	
</logic:equal>
<logic:equal name="contractForm" property="action" value="renew">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|425">
</logic:equal>
<logic:equal name="contractForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|419">
</logic:equal>
<logic:equal name="contractForm" property="action" value="createConfirm">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|423">	
</logic:equal>
<logic:equal name="contractForm" property="action" value="renewConfirm">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|426">	
</logic:equal>
<logic:equal name="contractForm" property="action" value="updateConfirm">
    <input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|420">	
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
							<table width="100%">
								<logic:equal name="contractForm" property="action" value="view">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.details" />	
									</td>
								</tr>	
								</logic:equal>
								<logic:equal name="contractForm" property="action" value="searchResult">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.details" />	
									</td>
								</tr>	
								</logic:equal>
								
								<logic:equal name="contractForm" property="action" value="create">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.summary" />	
									</td>
								</tr>	
								</logic:equal>
								<logic:equal name="contractForm" property="action" value="renew">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.renew" />&nbsp;<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.summary" />	
									</td>
								</tr>
								</logic:equal>
								<logic:equal name="contractForm" property="action" value="update">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.summary" />	
									</td>
								</tr>
								</logic:equal>
								<jims:if name="contractForm" property="action" value="create" op="equal">
								<jims:elseif name="contractForm" property="action" value="renew" op="equal"/>	
								<jims:or name="contractForm" property="action" value="update" op="equal"/>
   								<jims:then> 
								<tr>
									<td>Verify that information is correct and select Finish button.</td>
								</tr>
								</jims:then>			
								</jims:if> 
								<logic:equal name="contractForm" property="action" value="createConfirm">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.confirmation" />	
									</td>
								</tr>	
								<tr>
									<td class="confirm"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.successfully" />&nbsp;<bean:message key="prompt.created" /></td>
								</tr>	
								</logic:equal>
								<logic:equal name="contractForm" property="action" value="renewConfirm">
								<tr>	
									<td align="center" class="header">
										<bean:message key="prompt.renew" />&nbsp;<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.confirmation" />	
									</td>
								</tr>	
								<tr>
									<td class="confirm"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.successfully" />&nbsp;<bean:message key="prompt.renewed" /></td>
								</tr>	
								</logic:equal>
								<logic:equal name="contractForm" property="action" value="updateConfirm">
								<tr>		
									<td align="center" class="header">
										<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.confirmation" />	
									</td>												
								</tr>
								<tr>
									<td class="confirm"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.successfully" />&nbsp;<bean:message key="prompt.updated" /></td>
								</tr>	
								</logic:equal>
							</table>
				<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
				<!-- END ERROR TABLE -->
				<!-- BEGIN INSTRUCTION TABLE -->
				<!-- BEGIN  SERVICE PROVIDER TABLE -->
							<logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
								<tiles:insert page="../common/contractServiceProviderInfo.jsp" flush="true"></tiles:insert>	
							</logic:equal>					
				<!-- END SERVICE PROVIDER TABLE -->			
							<div class="spacer"></div>
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.info" /></td>
								</tr>
								<tr>
									<td>
									<table width="100%" border="0" cellpadding="4" cellspacing="1">
									    	<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.number" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="contractNum" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="contractName" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.startDate" /></td>
												<td class="formDe"><bean:write name="contractForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel" nowrap><bean:message key="prompt.endDate" /></td>
												<td class="formDe"><bean:write name="contractForm" property="endDate" formatKey="date.format.mmddyyyy"/></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.fundingType" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="contractType" /></td>
											</tr>
											<tr>	
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.gLAccountKey" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="glAccountKey" /></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.programFunding" />&nbsp;<bean:message key="prompt.description" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="programFundingDesc" /></td>
											</tr>
											<logic:equal name="contractForm" property="departmentId" value="CSC">											
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.tracerNumber" />&nbsp;<bean:message key="prompt.range" /></td>
													<td class="formDe">
														<bean:write name="contractForm" property="tracerNumberRangeFrom" />&nbsp;-&nbsp;
														<bean:write name="contractForm" property="tracerNumberRangeTo"/>
													</td>
													<td class="formDeLabel"><bean:message key="prompt.renewal#" /></td>
													<td class="formDe"><bean:write name="contractForm" property="renewalNum" /></td>
												</tr>
											</logic:equal>	
											<logic:notEqual name="contractForm" property="departmentId" value="CSC">											
												<tr>
													<td class="formDeLabel"><bean:message key="prompt.renewal#" /></td>
													<td class="formDe" colspan="3"><bean:write name="contractForm" property="renewalNum" /></td>
												</tr>
											</logic:notEqual>	
											<logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
												<tr>
													<td class="formDeLabel" nowrap><bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.value" /></td>
													<td class="formDe"><bean:write name="contractForm" property="totalValue" /></td>
													<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.service" />&nbsp;<bean:message key="prompt.provider" />&nbsp;<bean:message key="prompt.value" /></td>
													<td class="formDe"><bean:write name="contractForm" property="serviceProviderValue" /></td>
												</tr>
												</logic:equal>	
											<logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y">
												<tr>
													<td class="formDeLabel" nowrap><bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.value" /></td>
													<td class="formDe" colspan="3"><bean:write name="contractForm" property="totalValue" /></td>
												</tr>
											</logic:notEqual>	
										</table> 
									</td>
								</tr>
							</table>
							<jims:if name="contractForm" property="action" value="update" op="equal">
					
						    <jims:or name="contractForm" property="action" value="view" op="equal"/>
	
   						    <jims:or name="contractForm" property="action" value="searchResult" op="equal"/>
   	
						    <jims:then>
						    
						    <br>	
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
												String bgcolor = ""; %> 								
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
							</jims:then>
								
																		
							</jims:if> 
 				<!-- BEGIN BUTTON TABLE -->
						<logic:equal name="contractForm" property="action" value="create">
							<table border="0">
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;								
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>&nbsp;										
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
									</td>
								</tr> 
							</table>  
						</logic:equal>																		
						<logic:equal name="contractForm" property="action" value="renew">
							<table border="0">
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;								
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>&nbsp;										
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
									</td>
								</tr> 
							</table>  
						</logic:equal>	
						<logic:equal name="contractForm" property="action" value="update">
							<table border="0">
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;								
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>&nbsp;										
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
									</td>
								</tr> 
							</table>  
						</logic:equal>							

						<logic:equal name="contractForm" property="action" value="createConfirm">
							<table border="0">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.backToSearch"/></html:submit>
									</td>
								</tr> 
							</table>  
						</logic:equal>					
						<logic:equal name="contractForm" property="action" value="renewConfirm">
							<table border="0">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.backToSearch"/></html:submit>
									</td>
								</tr> 
							</table>  
						</logic:equal>					
						<logic:equal name="contractForm" property="action" value="updateConfirm">
							<table border="0">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.backToSearch"/></html:submit>
									</td>
								</tr> 
							</table>  
						</logic:equal>	
						<logic:equal name="contractForm" property="action" value="view">
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<input type="button" value="Back" onclick="history.go(-1)">&nbsp;								
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
								</td>
							</tr>
						</table>
						</logic:equal>
<%-- </div> --%>
</html:form>						
						<logic:equal name="contractForm" property="action" value="searchResult">
						<html:form action="/handleContractDetailsSelection" target="content">						
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>&nbsp;							
										<jims:if name="contractForm" property="availableContractValue" value="0.00" op="notEqual">
										<jims:and name="contractForm" property="contractExpired" value="false" op="equal"/>
										<jims:then> 
											<html:submit property="submitAction"><bean:message key="button.renew"/></html:submit>&nbsp;						
										</jims:then>
																						
										</jims:if> 
										<html:submit property="submitAction"><bean:message key="button.createContract"/></html:submit>							
									</td>
								</tr>
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;								
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
									</td>
								</tr>
							</table>
						</html:form>	
						</logic:equal>
				<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
