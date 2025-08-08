<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/16/2007	Create JSP -->
<!-- CShimkec    12/05/2008 Defect#55894 removed errant bean:message displaying at top of page  -->
<!-- DWilliamson 07/30/2010 ER#55920 Added text counter for comments field -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<html:html>

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
<title><bean:message key="title.heading"/> - serviceProviderCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javaScript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>			
		//this function determines which address entry page to go to depending on the InHouse radio button selection - defaults to inhouse if user selects nothing...
		//params: theForm - the form object
		function validateServiceProvCreateUpdate(theForm){

			if(!(validatePhone("phoneNumber.areaCode","phoneNumber.prefix","phoneNumber.fourDigit", "phoneNumber.ext","Phone Number",theForm))){
				return false;
			}
			if(!(validatePhone("faxNumber.areaCode","faxNumber.prefix","faxNumber.fourDigit", "","Fax Number",theForm))){
				return false;
			}
			clearAllValArrays();
			
			customValRequired("name","<bean:message key='errors.required' arg0='Name'/>","");
			customValMinLength("name","<bean:message key='errors.minlength' arg0='Name' arg1='1'/>","1");
			customValMaxLength("name","<bean:message key='errors.maxlength' arg0='Name' arg1='100'/>","100"); 
			addAlphaNumericNoFirstSpacewSymbolsValidation("name","<bean:message key='errors.comments' arg0='Name'/>","");
			
			<logic:equal name="cscServiceProviderForm" property="action" value="create">
				customValRequired("startDateAsStr","<bean:message key='errors.required' arg0='Start Date'/>","");
				addMMDDYYYYDateValidation("startDateAsStr","<bean:message key='errors.date' arg0='Start Date'/>","");
			
				if(!validateRadios(theForm,"inHouseAsStr","<bean:message key='errors.required' arg0='In House'/>")){
					return false;
				}
			</logic:equal>
						
			addAlphaNumericValidation("ifasNumber","<bean:message key='errors.alphanumeric' arg0='IFAS Number'/>","");
			customValMinLength("ifasNumber","<bean:message key='errors.minlength' arg0='IFAS Number' arg1='1'/>","1");
			customValMaxLength("ifasNumber","<bean:message key='errors.maxlength' arg0='IFAS Number' arg1='15'/>","15"); 
			 
			customValRequired("phoneNumber.areaCode","<bean:message key='errors.required' arg0='Phone Number Area Code'/>","");
			addNumericValidation("phoneNumber.areaCode","<bean:message key='errors.integer' arg0='Phone Number Area Code'/>","");
			customValMinLength("phoneNumber.areaCode","<bean:message key='errors.minlength' arg0='Phone Number Area Code' arg1='3'/>","3");
			addNumericValidation("phoneNumber.prefix","<bean:message key='errors.integer' arg0='Phone Number Prefix'/>","");
			customValMinLength("phoneNumber.prefix","<bean:message key='errors.minlength' arg0='Phone Number Prefix' arg1='3'/>","3");
			addNumericValidation("phoneNumber.fourDigit","<bean:message key='errors.integer' arg0='Phone Number Last 4 Digits'/>","");
			customValMinLength("phoneNumber.fourDigit","<bean:message key='errors.minlength' arg0='Phone Number Last 4 Digits' arg1='4'/>","4");
			addNumericValidation("phoneNumber.ext","<bean:message key='errors.integer' arg0='Phone Number Extension'/>","");
			customValMinLength("phoneNumber.ext","<bean:message key='errors.minlength' arg0='Phone Number Extension' arg1='1'/>","1");
			customValMaxLength("phoneNumber.ext","<bean:message key='errors.maxlength' arg0='Phone Number Extension' arg1='6'/>","6");
			
			addNumericValidation("faxNumber.areaCode","<bean:message key='errors.integer' arg0='Fax Number Area Code'/>","");
			customValMinLength("faxNumber.areaCode","<bean:message key='errors.minlength' arg0='Fax Number Area Code' arg1='3'/>","3");
			addNumericValidation("faxNumber.prefix","<bean:message key='errors.integer' arg0='Fax Number Prefix'/>","");
			customValMinLength("faxNumber.prefix","<bean:message key='errors.minlength' arg0='Fax Number Prefix' arg1='3'/>","3");
			addNumericValidation("faxNumber.fourDigit","<bean:message key='errors.integer' arg0='Fax Number Last 4 Digits'/>","");
			customValMinLength("faxNumber.fourDigit","<bean:message key='errors.minlength' arg0='Fax Number Last 4 Digits' arg1='4'/>","4");
			
			customValMask("website","Website  must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / @ _ ","/^[a-zA-Z0-9][a-zA-Z0-9 \.\\\\'@_;()\x26\x2f\-]*$/");
			customValMaxLength("website","<bean:message key='errors.maxlength' arg0='Website' arg1='50'/>","50");
			
			customValEmail("email", "<bean:message key='errors.email' arg0='Email'/>", "");
			customValMaxLength("email","<bean:message key='errors.maxlength' arg0='Email' arg1='100'/>","100");
			
			customValMask("ftp","FTP  must be alphanumeric with no leading spaces and the following symbols . ' \ & ; ( ) / @ _ ","/^[a-zA-Z0-9][a-zA-Z0-9 \.\\\\'@_;()\x26\x2f\-]*$/");
			customValMaxLength("ftp","<bean:message key='errors.maxlength' arg0='FTP' arg1='50'/>","50");
			
			addDB2FreeTextValidation("comments", "<bean:message key='errors.comments' arg0='Comments'/>", "");
			customValMaxLength("comments","<bean:message key='errors.maxlength' arg0='Comments' arg1='250'/>","250");
			
			return validateCustomStrutsBasedJS(theForm);
		}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCSCServiceProviderUpdate" target="content" focus="name">
<logic:equal name="cscServiceProviderForm" property="action" value="update">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|18">
</logic:equal>									
<logic:equal name="cscServiceProviderForm" property="action" value="create">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|1">          
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
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"/>
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
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp; - 
									<logic:equal name="cscServiceProviderForm" property="action" value="update">
									   <bean:message key="title.update"/>
									</logic:equal>									
									<logic:equal name="cscServiceProviderForm" property="action" value="create">
									   <bean:message key="title.create"/>          
									</logic:equal>
									<bean:message key="title.serviceProvider"/>  
								</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<%-- BEGIN ERROR TABLE --%>
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
										<li>Enter the required fields and click Next to view summary.</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.serviceProvider"/>&nbsp;<bean:message key="prompt.info"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<!--SP info start-->
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.name"/></td>
											<td class="formDe" colspan="3">
												<html:text name="cscServiceProviderForm" property="name" size="50" maxlength="100"/>
											</td>
										</tr>
										<logic:equal name="cscServiceProviderForm" property="action" value="create">
										<tr id="createRow">
											<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.startDate"/></td>
											<td class="formDe">
												<SCRIPT LANGUAGE="JavaScript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT>
												<html:text name="cscServiceProviderForm" property="startDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"/>
											<A HREF="#" onClick="cal1.select(document.forms[0].startDateAsStr,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1"><bean:message key="prompt.3.calendar"/></A> </td>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.inHouse"/></td>
											<td class="formDe"> Yes
												<html:radio name="cscServiceProviderForm" property="inHouseAsStr" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_YES%>"/>
												No
												<html:radio name="cscServiceProviderForm" property="inHouseAsStr" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_NO%>"/>
											</td>
										</tr>
										</logic:equal>
										<logic:equal name="cscServiceProviderForm" property="action" value="update">
										<tr id="updateRow">
											<td class="formDeLabel" nowrap><bean:message key="prompt.startDate"/></td>
											<td class="formDe"><bean:write name="cscServiceProviderForm" property="startDateAsStr"/></td>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inHouse"/></td>
											<td class="formDe"><bean:write name="cscServiceProviderForm" property="inHouseAsStr"/>
											</td>
										</tr>
										</logic:equal>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.IFAS"/>&nbsp;<bean:message key="prompt.number"/></td>
											<td class="formDe" >
												<html:text name="cscServiceProviderForm" property="ifasNumber" size="15" maxlength="15"/>
											</td>
											<td class="formDeLabel" width="1%" nowrap>Faith Based</td>
											<td class="formDe">												
												<html:checkbox name="cscServiceProviderForm" property="isFaithBased"/>
												<input type="hidden" name="isFaithBased" value="false">
												
											</td>											
										</tr>
											
								        <tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.3.diamond"/>Phone</td>
											<td class="formDe" colspan="3">
												<html:text name="cscServiceProviderForm" property="phoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							                    <html:text name="cscServiceProviderForm" property="phoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							                    <html:text name="cscServiceProviderForm" property="phoneNumber.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
												<bean:message key="prompt.ext"/>
												<html:text name="cscServiceProviderForm" property="phoneNumber.ext" size="6" maxlength="10" onkeyup="return autoTab(this, 10);" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.fax"/></td>
											<td class="formDe" colspan="3">
												<html:text name="cscServiceProviderForm" property="faxNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							                    <html:text name="cscServiceProviderForm" property="faxNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							                    <html:text name="cscServiceProviderForm" property="faxNumber.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.website"/></td>
											<td class="formDe" colspan="3">
												<html:text name="cscServiceProviderForm" property="website" size="50" maxlength="50"/>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.email"/></td>
											<td class="formDe" colspan="3">
												<html:text name="cscServiceProviderForm" property="email" size="50" maxlength="100"/>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.ftp"/></td>
											<td class="formDe" colspan="3">
												<html:text name="cscServiceProviderForm" property="ftp" size="50" maxlength="50"/>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments"/>&nbsp;(Max Characters Allowed: 250)</td>
										</tr>
										<tr>
											<td class="formDe" nowrap colspan="4"><html:textarea name="cscServiceProviderForm" property="comments" style="width:100%" rows="3" onmouseout="textLimit( this, 250 )" onkeyup="textLimit( this, 250 )"/></td>
										</tr>
									</table>
									<!--SP info End-->
								</td>
							</tr>
						</table>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return validateServiceProvCreateUpdate(this.form)"> <bean:message key="button.submit" /></html:submit>
									<html:reset><bean:message key="button.reset" /></html:reset>
	                                <logic:equal name="cscServiceProviderForm" property="action" value="update">
	                                  <logic:equal name="cscServiceProviderForm" property="statusId" value="P">
									   <jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_INACTIVATE_CSC%>">	           
	                                       <html:submit property="submitAction"> <bean:message key="button.inactivate" /></html:submit> 
	                                 </jims2:isAllowed> 
	                                  </logic:equal>
	                                </logic:equal>        
									<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
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
				<!-- END  TABLE -->
			</div>
			<br>
		</html:form>
	<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
