<!DOCTYPE HTML>

<!-- User selects the 'Create HCJDP' link on the left UI Navigation -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath Create Service JSP -->
<!-- 09/20/2006 Uma Gopinath Update/Inactivate flow added for ASP part 2 -->
<!-- 06/05/2007 C Shimek     #42540 added missing job title field for update and in house = No. -->
<!-- 06/06/2007 C Shimek     #42528 added suffix for in-house contact name  -->
<!-- 10/13/2015 R Capestani  #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />
<html:base />

<title><bean:message key="title.heading"/> - contactCreateUpdate.jsp</title>
<html:javascript formName="contactForm"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/administerServiceProviderHCJPD/contactCreate.js"></script>

<script type='text/javascript'>
function handleButton(theButton)
{
	var thisForm = document.forms[0];
	
	if(thisForm["currentContact.logonId"].value == "")
	{
		alert("User ID has to be provided to find Profile.");
   	thisForm["currentContact.logonId"].focus();

   	return false;
	}
	
	if (theButton.value == "Find")
	{
		document.forms[0].action = "/<msp:webapp/>displayJuvContactCreateUpdateSummary.do?submitAction=Find";
	}
	else 
	{
		document.forms[0].action = "/<msp:webapp/>displayJuvContactCreateUpdateSummary.do?submitAction=Clear";
		theButton.value = "Find";
	}
	return true;
}

function load(file,target) 
{
  window.location.href = file;
}

function showName()
{
	showHide2("foundName1","row", 1);
}
</script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayJuvContactCreateUpdateSummary" target="content" >

<logic:equal name="serviceProviderForm" property="actionType" value="addContact">
	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|385">
</logic:equal>

<logic:equal name="serviceProviderForm" property="actionType" value="updateContact">
	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|383">
</logic:equal>

<div align="center">
  
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>    	
	</tr>
	<tr>
		<td valign="top">
			<!-- BEGIN TABS TABLE-->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tabid" value="suggestedOrderTab"/>
						</tiles:insert>													
					</td>
				</tr>
				<tr>
					<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<!-- END TABS TABLE  -->				

			<!-- BEGIN BLUE BORDER TABLE -->
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<div class='spacer'></div>
						<table width="100%">
							<tr>
								<td class="header" align="center">
									<logic:equal name="serviceProviderForm" property="actionType" value="addContact">
										<bean:message key="prompt.add"/>
									</logic:equal>
									<logic:equal name="serviceProviderForm" property="actionType" value="updateContact">
										<bean:message key="prompt.update"/>
									</logic:equal>
									<bean:message key="prompt.contact"/>               
								</td>	
							</tr>      
						</table>
						<!-- END HEADING TABLE -->

						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<ul>
										<li>Enter the required fields and click Next</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.3.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" /></td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->							

						<!-- BEGIN ERRORS TABLE -->
						<table width="100%">
							<tr>		  
								<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
							</tr>   	  
						</table>
						<!-- END ERRORS TABLE -->

						<!-- BEGIN HEADER TABLE-->
						<table cellpadding="1" cellspacing="0" border="0" width="98%">
							<tr>
								<td bgcolor='#cccccc'>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="headerLabel" width="1%" nowrap>Provider <bean:message key="prompt.name" /></td>
											<td colspan="3" class="headerData"><bean:write name="serviceProviderForm" property="providerName"/></td>
										</tr>
										<tr>
											<td class="headerLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
											<td class="headerData"><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="headerLabel" width="1%" nowrap><bean:message key="prompt.inHouse" /></td>
											<td class="headerData"><bean:write name="serviceProviderForm" property="isInHouse"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- END HEADER TABLE -->

						<!-- BEGIN TABLE -->			
						<div class='spacer'></div>
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead" nowrap colspan="4">
												<bean:message key="prompt.contact" /> <bean:message key="prompt.info" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border=0 cellpadding="2" cellspacing="1">
										<tr>
											<td>
												<input type="hidden" name="inHouse" value="<bean:write name="serviceProviderForm" property="isInHouse"/>"/>
											</td>
										</tr>

										<logic:equal name="serviceProviderForm" property="actionType" value="addContact">
											<logic:equal name="serviceProviderForm" property="isInHouse" value="YES">
												<tr>
													<td class="formDeLabel" colspan="1" nowrap><bean:message key="prompt.userId" /></td>
													<td colspan="3" class="formDe">
														<html:text name="serviceProviderForm" property="currentContact.logonId" size="8" maxlength="8"/>
															<logic:equal name="serviceProviderForm" property="currentContact.logonId" value="">
																<html:submit styleId="idButton" property="submitAction" onclick="return handleButton(this);"><bean:message key='button.find' /></html:submit>	
															</logic:equal>
															<logic:notEqual name="serviceProviderForm" property="currentContact.logonId" value="">
																<html:submit styleId="idButton" property="submitAction" onclick="return handleButton(this);"><bean:message key='button.clear' /></html:submit>	
															</logic:notEqual>				
													</td>
												</tr>

                        <%-- ADDing in here --%>
  											<tr>
  												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
  												<td class="formDe" colspan="3">
  													<table border="0" cellspacing="1">
  														<tr>
  															<td class="formDeLabel" colspan="2"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.last"/></td>
  														</tr>
  														<tr>
  															<td class="formDe" colspan="2"><html:text name="serviceProviderForm" property="currentContact.contactName.lastName" size="30" /></td>
  														</tr>
  														<tr>
  															<td class="formDeLabel"><bean:message key="prompt.first"/></td>
  															<td class="formDeLabel"><bean:message key="prompt.middle"/></td>
  														</tr>
  														<tr>
  															<td class="formDe"><html:text name="serviceProviderForm" property="currentContact.contactName.firstName" size="25" /></td>
  															<td class="formDe"><html:text name="serviceProviderForm" property="currentContact.contactName.middleName" size="25" /></td>
  														</tr>
  													</table>
  												</td>
  											</tr>
  											<tr>
  												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.suffix" /></td>
  												<td class="formDe" colspan="3"><html:text name="serviceProviderForm" property="currentContact.contactName.suffix" size="10" maxlength="10"/></td>
  											</tr>

												<%-- logon provided per Find --%>
                        <%-- actually UPDATing in here --%>
												<logic:equal name="serviceProviderForm" property="currentContact.logonId" value="fdsafdsafdsafsdafsafsd">
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
														<bean:define id="contact" name="serviceProviderForm" property="currentContact"/>
														<td class="formDe" colspan="3"><msp:formatter name="contact" property="contactName" format="L, F M"/></td>
													</tr>
												</logic:equal>
											</logic:equal>

											<logic:equal name="serviceProviderForm" property="isInHouse" value="NO">
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.prefix"/></td>
													<td class="formDe" colspan="3">
														<html:select name="serviceProviderForm" property="currentContact.contactName.prefix" size="1">
															<option value=""><bean:message key="select.generic"/></option>
															<html:optionsCollection name="serviceProviderForm" property="prefixList"  value="description" label="description"/>
														</html:select>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
													<td class="formDe" colspan="3">
														<table border="0" cellspacing="1">
															<tr>
																<td class="formDeLabel" colspan=2><bean:message key="prompt.3.diamond"/><bean:message key="prompt.last"/></td>
															</tr>
															<tr>
																<td class="formDe" colspan="2"><html:text name="serviceProviderForm" property="currentContact.contactName.lastName" size="30" /></td>
															</tr>
															<tr>
																<td class="formDeLabel"><bean:message key="prompt.first"/></td>
																<td class="formDeLabel"><bean:message key="prompt.middle"/></td>
															</tr>
															<tr>
																<td class="formDe"><html:text name="serviceProviderForm" property="currentContact.contactName.firstName" size="25" /></td>
																<td class="formDe"><html:text name="serviceProviderForm" property="currentContact.contactName.middleName" size="25"/></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.suffix" /></td>
													<td class="formDe" colspan="3">
														<html:text name="serviceProviderForm" property="currentContact.contactName.suffix" size="10" maxlength="10"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.jobTitle" /></td>
													<td class="formDe" colspan="3" ><html:text name="serviceProviderForm" property="currentContact.jobTitle" size="25" maxlength="50"/></td>
												</tr>
											</logic:equal>  
										</logic:equal>
										<%-- actionType == addContact--%>

										<logic:equal name="serviceProviderForm" property="actionType" value="updateContact">
  										<logic:equal name="serviceProviderForm" property="actionType" value="addContact">
  											<logic:equal name="serviceProviderForm" property="isInHouse" value="YES">
    											<tr>
    												<td class="formDeLabel" colspan="1" nowrap><bean:message key="prompt.userId" /></td>
    												<td colspan="3" class="formDe">
    													<html:text name="serviceProviderForm" property="currentContact.logonId" size="8" maxlength="8"/>
    														<logic:equal name="serviceProviderForm" property="currentContact.logonId" value=""> 
    															<html:submit styleId="idButton" property="submitAction" onclick="return handleButton(this);"><bean:message key='button.find' /></html:submit>	
    														</logic:equal>
    														<logic:notEqual name="serviceProviderForm" property="currentContact.logonId" value=""> 
    															<html:submit styleId="idButton" property="submitAction" onclick="return handleButton(this);"><bean:message key='button.clear' /></html:submit>	
    														</logic:notEqual>				
    												</td>
    											</tr>
    										</logic:equal> 
  										</logic:equal>

  										<tr>
  											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.prefix"/></td>
  											<td class="formDe" colspan="3"> 
  												<html:select name="serviceProviderForm" property="currentContact.contactName.prefix" size="1">
  													<option value=""><bean:message key="select.generic"/></option>
  													<html:optionsCollection name="serviceProviderForm" property="prefixList"  value="description" label="description"/>
  												</html:select>
  											</td>
  										</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
												<td class="formDe" colspan="3">
													<table border="0" cellspacing="1">
														<tr>
															<td class="formDeLabel" colspan=2><bean:message key="prompt.3.diamond"/><bean:message key="prompt.last"/></td>
														</tr>
														<tr>
															<td class="formDe" colspan="2">
																<html:text name="serviceProviderForm" property="currentContact.contactName.lastName" size="30" />
															</td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.first"/></td>
															<td class="formDeLabel"><bean:message key="prompt.middle"/></td>
														</tr>
														<tr>
															<td class="formDe"><html:text name="serviceProviderForm" property="currentContact.contactName.firstName" size="25" /></td>
															<td class="formDe"><html:text name="serviceProviderForm" property="currentContact.contactName.middleName" size="25"/></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.suffix" /></td>
												<td class="formDe" colspan="3"><html:text name="serviceProviderForm" property="currentContact.contactName.suffix" size="10" maxlength="10"/></td>
											</tr>
											<logic:equal name="serviceProviderForm" property="isInHouse" value="NO">
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.jobTitle"/></td>
													<td class="formDe" colspan="3">
														<html:text name="serviceProviderForm" property="currentContact.jobTitle" maxlength="50" />
													</td>
												</tr>
											</logic:equal>	
										</logic:equal>
										<%-- actionType == updateContact--%>

										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.administrative"/> <bean:message key="prompt.contact"/></td>

												<td class="formDe" colspan="3">
													 <bean:message key="prompt.yes" /><html:radio name="serviceProviderForm" property="currentContact.isAdminContact" value="YES" />
													 <bean:message key="prompt.no" /><html:radio name="serviceProviderForm" property="currentContact.isAdminContact" value="NO" />
												</td>
										</tr>
																						

											<logic:equal name="serviceProviderForm" property="isInHouse" value="YES">																					
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.officePhone"/></td>
														<td class="formDe" colspan="3">
															<html:text name="serviceProviderForm" property="currentContact.officePhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
															<html:text name="serviceProviderForm" property="currentContact.officePhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
															<html:text name="serviceProviderForm" property="currentContact.officePhone.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
															<bean:message key="prompt.ext"/> <html:text name="serviceProviderForm" property="currentContact.officePhone.ext" size="4" maxlength="6" onkeyup="return autoTab(this, 6);" />
														</td>
													</tr>											
											</logic:equal>			
																	
											<logic:equal name="serviceProviderForm" property="isInHouse" value="NO">										
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.officePhone"/></td>
													<td class="formDe" colspan="3">
														<html:text name="serviceProviderForm" property="currentContact.officePhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
														<html:text name="serviceProviderForm" property="currentContact.officePhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
														<html:text name="serviceProviderForm" property="currentContact.officePhone.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
														<bean:message key="prompt.ext"/> <html:text name="serviceProviderForm" property="currentContact.officePhone.ext" size="4" maxlength="6" onkeyup="return autoTab(this, 6);" />
													</td>
												</tr>										
											</logic:equal>									
										
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.fax"/></td>
												<td class="formDe">
													<html:text name="serviceProviderForm" property="currentContact.fax.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
													<html:text name="serviceProviderForm" property="currentContact.fax.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
													<html:text name="serviceProviderForm" property="currentContact.fax.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.pager"/></td>
												<td class="formDe">
													<html:text name="serviceProviderForm" property="currentContact.pager.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
													<html:text name="serviceProviderForm" property="currentContact.pager.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
													<html:text name="serviceProviderForm" property="currentContact.pager.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.cellPhone"/></td>
												<td class="formDe">
													<html:text name="serviceProviderForm" property="currentContact.cellPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
													<html:text name="serviceProviderForm" property="currentContact.cellPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
													<html:text name="serviceProviderForm" property="currentContact.cellPhone.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
												</td>
											</tr>												
																										
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
												<td class="formDe"> 
													<html:text name="serviceProviderForm" property="currentContact.email"  size="35" maxlength="100"/>
												</td>
											</tr>										
									</logic:equal>	
										
  										<tr>
  											<td class="formDeLabel" colspan="4"><bean:message key="prompt.notes"/>
	               					<tiles:insert page="../../common/spellCheckTile.jsp" flush="false">
	               						<tiles:put name="tTextField" value="currentContact.notes"/>
	               						<tiles:put name="tSpellCount" value="spellBtn1" />
	             						</tiles:insert>   											
  											</td>
  										</tr>
  										<tr>
  											<td class="formDe" colspan="4"><html:textarea rows="4" style="width:100%" name="serviceProviderForm" property="currentContact.notes"></html:textarea></td>                          
  										</tr>
  									</table>
  								</table>

										<!-- BEGIN BUTTON TABLE -->
									<div class='spacer'></div>
									<table border="0" align='center'>
  									<tr>
  										<td align="center">
  											<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
  											<html:submit property="submitAction" onclick="return validateFields();"><bean:message key="button.next"></bean:message></html:submit>
  
  											<logic:equal name="serviceProviderForm" property="actionType" value="updateContact">
  												<jims2:isAllowed requiredFeatures="CS-ASP-INACTIVATEJUV">
  													<html:submit property="submitAction" onclick="return validateFields();">
  														<bean:message key="button.inactivate"></bean:message>
  													</html:submit>
  												</jims2:isAllowed>
  											</logic:equal>
  											<html:reset><bean:message key="button.reset"/></html:reset>
  										</td>
  									</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END  TABLE -->
</div>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
