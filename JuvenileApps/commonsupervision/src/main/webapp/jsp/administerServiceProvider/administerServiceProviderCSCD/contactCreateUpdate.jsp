<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- User selects the 'Create' button Service Provider Details page -->
<!--MODIFICATIONS -->
<!-- 12/05/2007 Debbie Williamson Create Contact JSP -->

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>


<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ page import="naming.PDCodeTableConstants" %>
<html:html>
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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - contactCreateUpdate.jsp</title>
<html:javascript formName="contactForm"/>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript">
function performValidation(theForm){
 		clearAllValArrays();
		
	  <logic:equal name="cscServiceProviderContactForm" property="lastAdminContact" value="false">	 
				if(!validateRadios(theForm,'adminContactAsStr','Administrative Contact is required')){
					return false;
				}
			</logic:equal>
				if(!(validatePhone("officePhone.areaCode","officePhone.prefix","officePhone.fourDigit", "officePhone.ext","Office Phone",theForm))){
					return false;
				}
				if(!(validatePhone("fax.areaCode","fax.prefix","fax.fourDigit", "","Fax",theForm))){
					return false;
				}
				if(!(validatePhone("cellPhone.areaCode","cellPhone.prefix","cellPhone.fourDigit", "","Cell Phone",theForm))){
					return false;
				}
				if(!(validatePhone("pager.areaCode","pager.prefix","pager.fourDigit", "","Pager",theForm))){
					return false;
				}
				
				<logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
					addAlphaNumericNoFirstSpacewSymbolsValidation("jobTitle", "<bean:message key='errors.comments' arg0='Job Title'/>", "");
					customValMinLength("jobTitle","<bean:message key='errors.minlength' arg0='Job Title' arg1='1'/>","1");
					customValMaxLength("jobTitle","<bean:message key='errors.maxlength' arg0='Job Title' arg1='50'/>","50");
				</logic:equal>
				
				customValRequired("contactName.lastName","<bean:message key='errors.required' arg0='Last Name'/>","");
				customValMinLength("contactName.lastName","<bean:message key='errors.minlength' arg0='Last Name' arg1='2'/>","2");
				customValMaxLength("contactName.lastName","<bean:message key='errors.maxlength' arg0='Last Name' arg1='75'/>","75"); 
				addAlphaNumericNoFirstSpacewSymbolsValidation("contactName.lastName","<bean:message key='errors.comments' arg0='Last Name'/>","");
				customValMinLength("contactName.firstName","<bean:message key='errors.minlength' arg0='First Name' arg1='1'/>","1");
				customValMaxLength("contactName.firstName","<bean:message key='errors.maxlength' arg0='First Name' arg1='50'/>","50"); 
				addAlphaNumericNoFirstSpacewSymbolsValidation("contactName.firstName","<bean:message key='errors.comments' arg0='First Name'/>","");
				customValMinLength("contactName.middleName","<bean:message key='errors.minlength' arg0='Middle Name' arg1='1'/>","1");
				customValMaxLength("contactName.middleName","<bean:message key='errors.maxlength' arg0='Middle Name' arg1='50'/>","50"); 
				addAlphaNumericNoFirstSpacewSymbolsValidation("contactName.middleName","<bean:message key='errors.comments' arg0='Middle Name'/>","");
			
				customValRequired("officePhone.areaCode","<bean:message key='errors.required' arg0='Office Phone Area Code'/>","");
				addNumericValidation("officePhone.areaCode","<bean:message key='errors.integer' arg0='Office Phone Area Code'/>","");
				customValMinLength("officePhone.areaCode","<bean:message key='errors.minlength' arg0='Office Phone Area Code' arg1='3'/>","3");
				addNumericValidation("officePhone.prefix","<bean:message key='errors.integer' arg0='Office Phone Prefix'/>","");
				customValMinLength("officePhone.prefix","<bean:message key='errors.minlength' arg0='Office Phone Prefix' arg1='3'/>","3");
				addNumericValidation("officePhone.fourDigit","<bean:message key='errors.integer' arg0='Office Phone Last 4 Digits'/>","");
				customValMinLength("officePhone.fourDigit","<bean:message key='errors.minlength' arg0='Office Phone Last 4 Digits' arg1='4'/>","4");
				addNumericValidation("officePhone.ext","<bean:message key='errors.integer' arg0='Office Phone Extension'/>","");
				customValMinLength("officePhone.ext","<bean:message key='errors.minlength' arg0='Office Phone Extension' arg1='1'/>","1");
				customValMaxLength("officePhone.ext","<bean:message key='errors.maxlength' arg0='Office Phone Extension' arg1='6'/>","6");
				
				addNumericValidation("fax.areaCode","<bean:message key='errors.integer' arg0='Fax Number Area Code'/>","");
				customValMinLength("fax.areaCode","<bean:message key='errors.minlength' arg0='Fax Number Area Code' arg1='3'/>","3");
				addNumericValidation("fax.prefix","<bean:message key='errors.integer' arg0='Fax Number Prefix'/>","");
				customValMinLength("fax.prefix","<bean:message key='errors.minlength' arg0='Fax Number Prefix' arg1='3'/>","3");
				addNumericValidation("fax.fourDigit","<bean:message key='errors.integer' arg0='Fax Number Last 4 Digits'/>","");
				customValMinLength("fax.fourDigit","<bean:message key='errors.minlength' arg0='Fax Number Last 4 Digits' arg1='4'/>","4");
				
				addNumericValidation("cellPhone.areaCode","<bean:message key='errors.integer' arg0='Cell Phone Number Area Code'/>","");
				customValMinLength("cellPhone.areaCode","<bean:message key='errors.minlength' arg0='Cell Phone Number Area Code' arg1='3'/>","3");
				addNumericValidation("cellPhone.prefix","<bean:message key='errors.integer' arg0='Cell Phone Number Prefix'/>","");
				customValMinLength("cellPhone.prefix","<bean:message key='errors.minlength' arg0='Cell Phone Number Prefix' arg1='3'/>","3");
				addNumericValidation("cellPhone.fourDigit","<bean:message key='errors.integer' arg0='Cell Phone Number Last 4 Digits'/>","");
				customValMinLength("cellPhone.fourDigit","<bean:message key='errors.minlength' arg0='Cell Phone Number Last 4 Digits' arg1='4'/>","4");
			
				addNumericValidation("pager.areaCode","<bean:message key='errors.integer' arg0='Pager Number Area Code'/>","");
				customValMinLength("pager.areaCode","<bean:message key='errors.minlength' arg0='Pager Number Area Code' arg1='3'/>","3");
				addNumericValidation("pager.prefix","<bean:message key='errors.integer' arg0='Pager Number Prefix'/>","");
				customValMinLength("pager.prefix","<bean:message key='errors.minlength' arg0='Pager Number Prefix' arg1='3'/>","3");
				addNumericValidation("pager.fourDigit","<bean:message key='errors.integer' arg0='Pager Number Last 4 Digits'/>","");
				customValMinLength("pager.fourDigit","<bean:message key='errors.minlength' arg0='Pager Number Last 4 Digits' arg1='4'/>","4");
			
				
				customValEmail("email", "<bean:message key='errors.email' arg0='Email'/>", "");
				customValMaxLength("email","<bean:message key='errors.maxlength' arg0='Email' arg1='100'/>","100");
			
				addDB2FreeTextValidation("notes", "<bean:message key='errors.comments' arg0='Notes'/>", "");
				customValMaxLength("notes","<bean:message key='errors.maxlength' arg0='Notes' arg1='255'/>","255");
			
    return validateCustomStrutsBasedJS(theForm);
}

</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCSCContactUpdate" target="content" focus="contactName.lastName">
<logic:equal name="cscServiceProviderContactForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|5"></logic:equal>
<logic:equal name="cscServiceProviderContactForm" property="action" value="update">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|11">
</logic:equal>
  <div align="center">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
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
              <td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
            </tr>
          </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
            <tr>
              <td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
            </tr>
            <tr>
              <td valign="top" align="center">
                <!-- BEGIN HEADING TABLE -->
                <table width="100%">
                  <tr>
                    <td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-<bean:message key="title.serviceProvider"/>&nbsp;– 
                    <logic:equal name="cscServiceProviderContactForm" property="action" value="create">
						<bean:message key="prompt.create"/>
					</logic:equal>
					<logic:equal name="cscServiceProviderContactForm" property="action" value="update">
						<bean:message key="prompt.update"/>
					</logic:equal>
                    <bean:message key="prompt.contact"/></td>
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
                        <li>Enter the required fields and click Submit</li>
                      </ul>
                    </td>
                  </tr>
                  <tr>
                    <td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
                  </tr>
                </table>
                <!-- BEGIN  TABLE -->
				
				<tiles:insert page="../../common/serviceProviderHeader.jsp" flush="true" />												
                <div class="spacer4px"></div>
                <table width="98%" border="0" cellspacing="0" class="borderTableBlue">
                  <tr>
                    <td class="detailHead">
                      <table width="100%" cellpadding="2" cellspacing="0">
                        <tr>
                          <td class="detailHead"><bean:message key="prompt.contactInfo"/></td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <table width="100%" border="0" cellpadding="2" cellspacing="1">
                       <logic:equal name="cscServiceProviderForm" property="inHouse" value="true">
                        <tr class="formDe">
                          <td class="formDeLabel" nowrap><bean:message key="prompt.userId"/></td>
                          <td colspan="3" class="formDe">                          	
	                            <html:text name="cscServiceProviderContactForm" property="userId" size="8" maxlength="8"/>
	                            <html:submit property="submitAction">
								    <bean:message key="button.find"/>
                                </html:submit> 
                                                    	
                          </td>
                        </tr>
                       </logic:equal>
                        <tr id="foundName">
                          <td class="formDeLabel" valign="top"><bean:message key="prompt.name"/></td>
                          <td class="formDe" colspan="3">
                            <table border="0" cellspacing="1">
                              <tr>
                                <td class="formDeLabel" colspan="2"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.last"/></td>
                              </tr>
                              <tr>
                                <td class="formDe" colspan="2">
                                  <html:text name="cscServiceProviderContactForm" property="contactName.lastName" size="30" maxlength="75"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="formDeLabel"><bean:message key="prompt.first"/></td>
                                <td class="formDeLabel"><bean:message key="prompt.middle"/></td>
                              </tr>
                              <tr>
                                <td class="formDe">
                                  <html:text name="cscServiceProviderContactForm" property="contactName.firstName" size="30" maxlength="50"/>
                                </td>
                                <td class="formDe">
                                  <html:text name="cscServiceProviderContactForm" property="contactName.middleName" size="30" maxlength="50"/>
                                </td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                  <logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
                       
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.jobTitle"/></td>
							<td class="formDe" colspan="3">
								<html:text name="cscServiceProviderContactForm" property="jobTitle" maxlength="50" />
							</td>
						</tr>
					</logic:equal>
                        <tr>
                          <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.administrativeContact"/></td>
                          <td class="formDe" colspan="3">
                           <logic:equal name="cscServiceProviderContactForm" property="lastAdminContact" value="false">
                              Yes <html:radio name="cscServiceProviderContactForm" property="adminContactAsStr" value="<%=PDCodeTableConstants.ASP_CS_CONTACT_ADMINCONTACT_YES%>"/>
                              No  <html:radio name="cscServiceProviderContactForm" property="adminContactAsStr" value="<%=PDCodeTableConstants.ASP_CS_CONTACT_ADMINCONTACT_NO%>"/>
                           </logic:equal>
                           <logic:notEqual name="cscServiceProviderContactForm" property="lastAdminContact" value="false">	                          
							   
								   <bean:write name="cscServiceProviderContactForm" property="adminContactAsStr"/>
							   
						   </logic:notEqual>
                          </td>
                        </tr>
                        <tr>
                          <td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.officePhone"/></td>
                          <td class="formDe" colspan="3">
                            <html:text name="cscServiceProviderContactForm" property="officePhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="officePhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="officePhone.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
							<bean:message key="prompt.ext"/> <html:text name="cscServiceProviderContactForm" property="officePhone.ext" size="4" maxlength="6" onkeyup="return autoTab(this, 6);" />
                          </td>
                        </tr>
                        <tr>
                          <td class="formDeLabel" nowrap><bean:message key="prompt.fax"/></td>
                          <td class="formDe" colspan="3">
							<html:text name="cscServiceProviderContactForm" property="fax.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="fax.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="fax.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
                          </td>
                        </tr>
                        <tr>
                          <td class="formDeLabel" nowrap><bean:message key="prompt.cellPhone"/></td>
                          <td class="formDe">
                            <html:text name="cscServiceProviderContactForm" property="cellPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="cellPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="cellPhone.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
						  </td>
                          <td class="formDeLabel" nowrap><bean:message key="prompt.pager"/></td>
                          <td class="formDe">
                            <html:text name="cscServiceProviderContactForm" property="pager.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="pager.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
							<html:text name="cscServiceProviderContactForm" property="pager.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
                          </td>
                        </tr>
                       	<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email"/></td>
							<td class="formDe" colspan="3">
								<html:text name="cscServiceProviderContactForm" property="email"  size="35" maxlength="100"/>
							</td>
						</tr>
                        <tr>
                          <td class="formDeLabel" colspan="4"><bean:message key="prompt.notes"/>&nbsp;(Max Characters Allowed: 250)</td>
                        </tr>
                        <tr>
                          <td class="formDe" colspan="4">
                            <html:textarea rows="4" style="width:100%" name="cscServiceProviderContactForm" property="notes" onmouseout="textLimit( this, 250 )" onkeyup="textLimit( this, 250 )"></html:textarea>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
                <br>
                <!-- BEGIN BUTTON TABLE -->
                <table border="0">
                  <tr>
                    <td align="center">
                       <html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
					   <html:submit property="submitAction" onclick="return performValidation(this.form)"> <bean:message key="button.next" /></html:submit>
					   <html:reset><bean:message key="button.reset" /></html:reset>
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
