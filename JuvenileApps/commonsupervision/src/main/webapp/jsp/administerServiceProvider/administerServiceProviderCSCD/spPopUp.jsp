<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation Summary (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 12/10/2007	Create JSP -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<title><bean:message key="title.heading"/> - spPopUp.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKey(event,true)">
  <div align="center">
    <table width="98%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
      </tr>
      <tr>
        <td valign="top">          
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
            </tr>
            <tr>
              <td valign="top" align="center">
              	<!-- BEGIN HEADING TABLE -->
                <table width="100%">
                  <tr>
                    <td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="title.details"/></td>
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
                <!-- BEGIN SP TABLE -->
                <table width="98%" border="0" cellspacing="0" class="borderTableBlue">
                  <tr>
                    <td class="detailHead">
                      <table width="100%" cellpadding="2" cellspacing="0">
                        <tr>
                       	  <td width="1%"><a href="javascript:showHideMulti('serviceProviderSpanRow', 'serviceProviderSpanRow', 1,'/<msp:webapp/>')"><img border="0" src="../../../images/expand.gif" name="serviceProviderSpanRow"></a></td>
                          <td class="detailHead"><bean:write name="cscServiceProviderForm" property="name"/></td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                  <tr id="serviceProviderSpanRow0" class="hidden">
                    <td>
                      <table width="100%" border="0" cellpadding="4" cellspacing="1">
                        <tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.startDate" /></td>
							<td class="formDe"><bean:write name="cscServiceProviderForm" property="startDateAsStr" /></td>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inHouse" /></td>
							<td class="formDe"><bean:write name="cscServiceProviderForm" property="inHouseAsStr"/></td>
                        </tr>
                        <tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.IFAS" /></td>
							<td colspan="3" class="formDe"><bean:write name="cscServiceProviderForm" property="ifasNumber"/></td>
                        </tr>
                        <tr>
                            <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone" /></td>
							<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="A-P-F"/><logic:notEqual name="cscServiceProviderForm" property="phoneNumber.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="X"/></logic:notEqual></td>                        </tr>
                        <tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.fax" /></td>
							<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="faxNumber" format="A-P-F"/></td>
                        </tr>
                        <tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.website" /></td>
							<td class="formDe" colspan="3">
								<logic:notEmpty name="cscServiceProviderForm" property="website">
									<bean:define id="web1" name="cscServiceProviderForm" property="website"/>
	                           		<a href="//<%=web1%>" target="_new"><bean:write name="cscServiceProviderForm" property="website"/></a>
                       	 		</logic:notEmpty>
                             	<logic:empty name="cscServiceProviderForm" property="website"><bean:write name="cscServiceProviderForm" property="website"/></logic:empty>
                            </td>
                        </tr>
                        <tr>
						    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email" /></td>
							<td class="formDe" colspan="3"><a href='mailto:<bean:write name="cscServiceProviderForm" property="email"/>'><bean:write name="cscServiceProviderForm" property="email"/> </a></td>
                        </tr>
                        <tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ftp" /></td>
							<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="ftp"/></td>
                        </tr>
						<tr>
							<td class="formDeLabel" nowrap colspan="4"><bean:message key="prompt.comments"/></td>
						</tr>
						<tr>
							<td class="formDe" nowrap colspan="4"><bean:write name="cscServiceProviderForm" property="comments"/></td>
						</tr>
                      </table>
                      <table width="100%" cellpadding="1" cellspacing="1">
                        <!-- BEGIN ADDRESS INFORMATION TABLES -->
                        <tr>
                          <td class="detailHead">
                            <table width="100%" cellpadding="2" cellspacing="0">
                              <tr>
                                <td width="1%"><a href="javascript:showHideMulti('mailing', 'ma', 1,'/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="mailing"></a></td>
                                <td class="detailHead">
                                	<logic:equal name="cscServiceProviderForm" property="inHouse" value="true">
										<bean:write name="cscServiceProviderForm" property="name"/> <bean:message key="prompt.address"/>
									</logic:equal>
									<logic:notEqual name="cscServiceProviderForm" property="inHouse" value="true">
										<bean:message key="prompt.mailingAddress"/>
									</logic:notEqual>
                                </td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                        <tr class="hidden" id="ma0">
                          <td>
                            <table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr class="formDeLabel">
									<td width="20%"><bean:message key="prompt.addressType"/></td>
									<td width="70%"><bean:message key="prompt.address"/></td>
									<td width="10%"><bean:message key="prompt.county"/></td>
								</tr>
								<tr class="formDe">
									<td><bean:write name="cscServiceProviderForm" property="mailingAddress.addressType"/></td>
									<td>
									<div><bean:write name="cscServiceProviderForm" property="mailingAddress.streetAddress"/></div>
									<div><bean:write name="cscServiceProviderForm" property="mailingAddress.cityStateZip"/></div>
										
									</td>
									<td><bean:write name="cscServiceProviderForm" property="mailingAddress.county"/></td>
								</tr>
							</table>
                          </td>
                        </tr>
                        <!-- END ADDRESS INFORMATION TABLE -->
                      </table>
                      <logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
						<table width="100%" cellpadding="1" cellspacing="1">
							<!-- BEGIN ADDRESS INFORMATION TABLES -->
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHideMulti('billing', 'ba', 1,'/<msp:webapp/>')"><img border="0" src="../../../images/expand.gif" name="billing"></a></td>
											<td class="detailHead"><bean:message key="prompt.billingAddress" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="hidden" id="ba0">
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr class="formDeLabel">
											<td width="20%"><bean:message key="prompt.addressType" /></td>
											<td width="70%"><bean:message key="prompt.address" /></td>
											<td width="10%"><bean:message key="prompt.county" /></td>
										</tr>
										<tr class="formDe">
											<td><bean:write name="cscServiceProviderForm" property="billingAddress.addressType"/></td>
											<td>
													<div><bean:write name="cscServiceProviderForm" property="billingAddress.streetAddress"/></div>
											<div><bean:write name="cscServiceProviderForm" property="billingAddress.cityStateZip"/></div>
											</td>
											<td><bean:write name="cscServiceProviderForm" property="billingAddress.county"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- END ADDRESS INFORMATION TABLE -->
						</table>
						</logic:equal>
                    </td>
                  </tr>
                </table>
                <!-- END SP INFORMATION TABLE -->
                <br>
                <!-- BEGIN programs TABLE -->
                <table width="98%" cellpadding="0" cellspacing="0" border="0">
                  <tr>
                    <td width="100%" valign="top">
                      <table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
                        <tr class="detailHead">
                          <td><bean:message key="prompt.programs"/></td>
                        </tr>
                        <tr>
                          <td>
                            <table width="100%" cellpadding="2" cellspacing="1" class="sortable" id="programsTable">
                              <tr class="formDeLabel">
                                <td><bean:message key="prompt.identifier"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programIdentifier" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1" levelDeep="3"/></td>
                                <td><bean:message key="prompt.name"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="3"/></td>
                                <td><bean:message key="prompt.programGroup"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programGroupDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="3"/></td>
                                <td><bean:message key="prompt.status"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="currentStatusDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" levelDeep="3"/></td>
                              </tr>
                           <% int RecordCounter2=0;
						   String bgcolor2="";
						  %>
                          <logic:iterate id="programIndex" name="cscServiceProviderForm" property="programs">
							  <tr class= <% RecordCounter2++;
											  bgcolor2 = "alternateRow";                      
											  if (RecordCounter2 % 2 == 1)
												  bgcolor2 = "normalRow";
											   out.print(bgcolor2); %>>
                              <!-- list all programs start-->
                                <td><bean:write name="programIndex" property="programIdentifier"/></td>
                                <td><bean:write name="programIndex" property="programName"/></td>
                                <td><bean:write name="programIndex" property="programGroupDesc"/></td>
                                <td><bean:write name="programIndex" property="programStatusDesc"/></td>
                              </tr>
                               </logic:iterate>
                            </table>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
                <br>
                <table width="98%" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="90%" valign="top">
                      <table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
                        <tr class="detailHead">
                          <td><bean:message key="prompt.contacts"/></td>
                        </tr>
                        <tr>
                          <td>
                            <table width="100%" cellpadding="2" cellspacing="1" class="sortable" id="contactsTable">
                              <tr class="formDeLabel">
                                <td><bean:message key="prompt.name"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="contactName.lastName" primarySortType="STRING"  secondPropSort="contactName.firstName" secondarySortType="STRING" defaultSortOrder="ASC" sortId="1" levelDeep="3"/></td>
                                <td width="5%" nowrap><bean:message key="prompt.admin"/>&nbsp;<bean:message key="prompt.contact"/></td>
                                <td><bean:message key="prompt.email"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="email" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="3"/></td>
                                <td><bean:message key="prompt.officePhone"/>
                                    <jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="contactOfficePhoneNumber.formattedPhoneNumber" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="3"/></td>
                              </tr>
                             <% int RecordCounter3=0;
						   String bgcolor3="";
						  %>
                              <logic:iterate id="contactIndex" name="cscServiceProviderForm" property="contacts">
							  <tr class= <% RecordCounter3++;
						  bgcolor3 = "alternateRow";                      
						  if (RecordCounter3 % 2 == 1)
							  bgcolor3 = "normalRow";
						   out.print(bgcolor3); %>>
                                
                                <td>
                                   <msp:formatter name="contactIndex" property="contactName" format="L, F M"/>
								</td>
                                <td align="center"><bean:write name="contactIndex" property="adminContactAsStr"/></td>
                                <td> <a href='mailto:<bean:write name="contactIndex" property="contactEmail"/>'><bean:write name="contactIndex" property="contactEmail"/> </a></td>
                                <td><bean:write name="contactIndex" property="contactOfficePhoneNumber" formatKey="phone.format"/> &nbsp; 
									<logic:notEqual name="contactIndex" property="contactOfficePhoneNumber.ext" value="">Ext-<bean:write name="contactIndex" property="contactOfficePhoneNumber.ext"/></logic:notEqual></td>
                              </tr>
                             </logic:iterate>
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
                    <td>
                    	<input type="button" value="Close" onclick="window.close();" />
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
