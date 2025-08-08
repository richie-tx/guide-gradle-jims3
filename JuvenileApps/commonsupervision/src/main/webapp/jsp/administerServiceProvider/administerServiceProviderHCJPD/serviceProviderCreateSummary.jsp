<!DOCTYPE HTML>
<!-- User hits the 'Submit'button on the Create Service Provider page -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath Create Summary JSP -->
<!-- 09/20/2006 Uma Gopinath Added Inactivate Summary flow -->
<!-- 10/14/2015 Richard Capestani  #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading"/> - serviceProviderCreateSummary.jsp</title>

<script language="JavaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
</head>
<body topmargin=0 leftmargin="0">
<html:form action="/submitJuvServiceProviderCreate" >
<logic:equal name="serviceProviderForm" property="actionType" value="createSummary">
   	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|368">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="updateSummary">
   	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|366">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="inactivateSummary">
   	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|1">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="inactivateConfirm">
	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|1">
</logic:equal>
  <div align="center">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td valign=top><bean:message key="prompt.3.spacer"/></td>
      </tr>
     <tr>
    	<td valign=top align=center>
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<!--tabs start-->
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>													
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>			
			</table> 
		<!-- END BLUE TAB TABLE -->
          <table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
            <tr>
              <td><bean:message key="prompt.3.spacer"/></td>
            </tr>
            <tr>
              <td valign=top align=center>
                <!-- BEGIN HEADING TABLE -->
                <table width=100%>
                  <tr>
                  	<logic:equal name="serviceProviderForm" property="actionType" value="createSummary">
                    	<td align="center" class="header"><bean:message key="prompt.create"/> <bean:message key="title.serviceProvider"/> - <bean:message key="title.summary"/></td>
                    </logic:equal>
                   	<logic:equal name="serviceProviderForm" property="actionType" value="updateSummary">
                    	<td align="center" class="header"><bean:message key="prompt.update"/> <bean:message key="title.serviceProvider"/> - <bean:message key="title.summary"/></td>
                    </logic:equal>
                   <logic:equal name="serviceProviderForm" property="actionType" value="inactivateSummary">
                    	<td align="center" class="header"><bean:message key="prompt.inactivate"/> <bean:message key="title.serviceProvider"/> - <bean:message key="title.summary"/></td>
                    </logic:equal>
                    <logic:equal name="serviceProviderForm" property="actionType" value="inactivateConfirm">
                   		<td align="center" class="header"><bean:message key="prompt.inactivate"/> <bean:message key="title.serviceProvider"/> - <bean:message key="prompt.confirmation"/></td>
                    </logic:equal>
                  </tr>
                </table>
                <!-- END HEADING TABLE -->
                <!-- BEGIN INSTRUCTION TABLE -->
                <table width="98%" border="0">             
	                  <tr>
	                   <logic:notEqual name="serviceProviderForm" property="actionType" value="inactivateConfirm">
	                    <td>
	                      <ul>
	                        <li>Click Save & Continue</li>
	                      </ul>
	                    </td>
	                    </logic:notEqual>
	                    <logic:equal name="serviceProviderForm" property="actionType" value="inactivateConfirm">
	                    <td class="confirm"><bean:write name="serviceProviderForm" property="confirmMessage"/></td>
	                    </logic:equal>
	                  </tr>
	                
                  <tr> </tr>
                
                </table>
                <!-- END INSTRUCTION TABLE -->
                <!-- BEGIN  sp Info TABLE -->
                <table width="98%" border="0" cellspacing=0 class=borderTableBlue>
                  <tr>
                    <td class=detailHead>
                      <table width=100% cellpadding=2 cellspacing=0>
                        <tr>
                          <td class=detailHead><bean:message key="title.serviceProvider" /> <bean:message key="prompt.info" /></td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <table width="100%" border=0 cellpadding="4" cellspacing="1">
                        <tr>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.name" /></td>
                          <td class=formDe colspan=3> <bean:write name="serviceProviderForm" property="providerName"/> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel nowrap><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
                          <td class=formDe><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/> </td>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.inHouse" /></td>
                          <td class=formDe> <bean:write name="serviceProviderForm" property="isInHouse"/> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.admin" /> <bean:message key="prompt.userId" /></td>
                          <td class=formDe> <bean:write property="adminContactId" name="serviceProviderForm"/> </td>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.maxYouth" /></td>
                          <td class=formDe> <bean:write name="serviceProviderForm" property="maxYouth"/> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel width=1% nowrap>Email</td>
                          <td colspan=1 class=formDe> <bean:write name="serviceProviderForm" property="email"/> </td>
                           <td class=formDeLabel width=1% nowrap>Send Email Notification</td>
                          <td class=formDe> <bean:write name="serviceProviderForm" property="isEmailCheck"/> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.phone" /></td>
                          <td class=formDe colspan=3> <msp:formatter name="serviceProviderForm" property="phoneNum" format="A-P-F"/>  <logic:notEqual name="serviceProviderForm" property="phoneNum.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="serviceProviderForm" property="phoneNum" format="X"/></logic:notEqual></td>
                        </tr>
                        <tr>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.fax" /></td>
                          <td class=formDe colspan=3> <msp:formatter name="serviceProviderForm" property="fax" format="A-P-F"/> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel width=1% nowrap><bean:message key="prompt.website" /></td>
                          <bean:define id="web1" name="serviceProviderForm" property="webSite"/>
                        
                          <td class=formDe colspan=3>  <a href="<%=web1%>" target="_new"><bean:write name="serviceProviderForm" property="webSite"/></a> </td>
                        </tr>
                        <tr>
                          <td class=formDeLabel width=1% nowrap><%-- <bean:message key="prompt.ftp" />--%> Notes</td>
                          <td class=formDe colspan=3><div class=notesftp> <bean:write name="serviceProviderForm" property="ftp"/> </div></td>
                        </tr>
                        <tr>
                          <td colspan=4>
                            <table width="100%" cellpadding="1" cellspacing="1">
                              <!-- BEGIN ADDRESS INFORMATION TABLES -->
                              <tr>
                                <td class=detailHead>
                                  <table width=100% cellpadding=2 cellspacing=0>
                                    <tr>
                                      <td width=1%><a href="javascript:showHideMulti('mailing', 'ma', 4,'/<msp:webapp/>')"><img border=0 src="../../../images/contract.gif" name="mailing"></a></td>
                                      <td class=detailHead>&nbsp;<bean:message key="prompt.mailingAddress" /></td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                              <tr>
                                <td>
                                  <table width="100%" border=0 cellpadding="2" cellspacing="1">
                                    <tr  class=visibleTR id=ma0>
                                     <td class="formDeLabel" nowrap width=1%><bean:message key="prompt.streetNumber"/></td>										
										<td class="formDeLabel" nowrap ><bean:message key="prompt.streetName"/></td>									
										<td class="formDeLabel" nowrap><bean:message key="prompt.streetType"/></td>										
										<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.aptSuite"/></td>	
                                    </tr>
                                    <tr  class=visibleTR id=ma1>
                                      <td class=formDe><bean:write name="serviceProviderForm" property="mailingAddress.streetNumber"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.streetName"/> </td>
                                      <td class=formDe> <bean:write	name="serviceProviderForm" property="mailingAddress.streetType"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.aptNumber"/>  </td>
                                    </tr>
                                    <tr  class=visibleTR id=ma2>
                                     <td class="formDeLabel" nowrap width=1%> <bean:message key="prompt.city"/></td>
										<td class="formDeLabel" nowrap> <bean:message key="prompt.state"/></td>
										<td class="formDeLabel" nowrap><bean:message key="prompt.zipCode"/></td>
										<td class="formDeLabel" nowrap colspan=5><bean:message key="prompt.validationSymbol"/></td>
                                    </tr>
                                    <tr  class=visibleTR id=ma3>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.city"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.state"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.zipCode"/> <logic:notEqual name="serviceProviderForm" property="mailingAddress.additionalZipCode" value="">- 
                                      <bean:write name="serviceProviderForm" property="mailingAddress.additionalZipCode"/> </logic:notEqual> </td>
                                      <td class=formDe>
                                            <%-- based on the Address validation, display a specific icon --%>
																		<logic:notEmpty name="serviceProviderForm" property="mailingAddress.validated">
																			<logic:equal name="serviceProviderForm" property="mailingAddress.validated" value="Y">
																				<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																			</logic:equal>
																			<logic:equal name="serviceProviderForm" property="mailingAddress.validated" value="N">
																				<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
																			</logic:equal>
																		</logic:notEmpty> 
																	</td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                              <!-- END ADDRESS INFORMATION TABLE -->
                            </table>
                            <table width="100%" cellpadding="1" cellspacing="1">
                              <!-- BEGIN ADDRESS INFORMATION TABLES -->
                              <tr>
                                <td class=detailHead>
                                  <table width=100% cellpadding=2 cellspacing=0>
                                    <tr>
                                      <td width=1%><a href="javascript:showHideMulti('billing', 'ba', 4,'/<msp:webapp/>')"><img border=0 src="../../../images/contract.gif" name="billing"></a></td>
                                      <td class=detailHead>&nbsp;<bean:message key="prompt.billingAddress" /></td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                              <tr >
                                <td>
                                  <table width="100%" border=0 cellpadding="2" cellspacing="1">
                                    <tr class=visibleTR id=ba0>
                                     <td class="formDeLabel" nowrap width=1%><bean:message key="prompt.streetNumber"/></td>										
										<td class="formDeLabel" nowrap ><bean:message key="prompt.streetName"/></td>									
										<td class="formDeLabel" nowrap><bean:message key="prompt.streetType"/></td>										
										<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.aptSuite"/></td>	
                                    </tr>
                                    <tr class=visibleTR id=ba1>
                                       <td class=formDe><bean:write name="serviceProviderForm" property="billingAddress.streetNumber"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.streetName"/> </td>
                                      <td class=formDe> <bean:write	name="serviceProviderForm" property="billingAddress.streetType"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.aptNumber"/>  </td>
                                    </tr>
                                    <tr class=visibleTR id=ba2>
                                     <td class="formDeLabel" nowrap width=1%> <bean:message key="prompt.city"/></td>
										<td class="formDeLabel" nowrap> <bean:message key="prompt.state"/></td>
										<td class="formDeLabel" nowrap><bean:message key="prompt.zipCode"/></td>
										<td class="formDeLabel"  nowrap colspan=5><bean:message key="prompt.validationSymbol"/></td>
                                    </tr>
                                    <tr class=visibleTR id=ba3>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.city"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.state"/> </td>
                                      <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.zipCode"/> <logic:notEqual name="serviceProviderForm" property="billingAddress.additionalZipCode" value=""> - 
                                      <bean:write name="serviceProviderForm" property="billingAddress.additionalZipCode"/> </logic:notEqual> </td>
                                      <td class=formDe>
                                            <%-- based on the Address validation, display a specific icon --%>
																		<logic:notEmpty name="serviceProviderForm" property="billingAddress.validated">
																			<logic:equal name="serviceProviderForm" property="billingAddress.validated" value="Y">
																				<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																			</logic:equal>
																			<logic:equal name="serviceProviderForm" property="billingAddress.validated" value="N">
																				<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
																			</logic:equal>
																		</logic:notEmpty>
																	</td>
                                    </tr>
                                  </table>
                                </td>
                              </tr>
                              <!-- END ADDRESS INFORMATION TABLE -->
                            </table>
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
                  <logic:notEqual name="serviceProviderForm" property="actionType" value="inactivateConfirm">
                    <td align="center"><html:submit property="submitAction">
											<bean:message key="button.back"></bean:message>
										</html:submit>
										<logic:equal name="serviceProviderForm" property="actionType" value="inactivateSummary">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
												<bean:message key="button.finish"></bean:message>
											</html:submit>
										</logic:equal>
										<logic:notEqual name="serviceProviderForm" property="actionType" value="inactivateSummary">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
												<bean:message key="button.saveAndContinue"></bean:message>
											</html:submit>
										</logic:notEqual>
										 <html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit></td>
				  </logic:notEqual>
				   <logic:equal name="serviceProviderForm" property="actionType" value="inactivateConfirm">
	                    <td align="center"><html:submit property="submitAction">
												<bean:message key="button.returnToServiceProviderSearch"></bean:message>
											</html:submit>
						</td>
				  </logic:equal>				  
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
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>
