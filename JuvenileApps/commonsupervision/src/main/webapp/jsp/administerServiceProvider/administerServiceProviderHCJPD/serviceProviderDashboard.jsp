<!DOCTYPE HTML>
<%-- MODIFICATIONS --%>
<%-- 05/24/2006 Uma Gopinath Service Provider Dashboard JSP --%>
<%-- 12/08/2006 C Shimek    Defect#37651  Add status diaplay in program block  --%>
<%-- 06/18/2006 C Shimek    removed phone displaying at bottom of page, found while testing defect on another page --%>
<%-- 10/13/2015 R Capestani Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@page import="java.util.*"%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%-- msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<html:base />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<title><bean:message key="title.heading"/> - serviceProviderDashboard.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script>
function setAction(action)
{
	document.forms[0].actionType.value = action;
}

function checkRadioFieldSelected(thisForm)
{
	var myOption = 0;
	var radioFound = false ;    
	
	for(var i = 0; i < thisForm.length; i++)
  {
	  if(thisForm.elements[i].type == 'radio')
    {
		  radioFound = true ;
      if(thisForm.elements[i].checked)
      {
        myOption = 1;
      }   
    }
  }// for
     
	if( radioFound  &&  !myOption ) 
	{
		alert("You must select a record to perform the operation.");
		return false;
	}
	
	return true;
	
}

var currentSelectedValue = "";

function setSelectedVal(programCode)
{
 currentSelectedValue = programCode;
}

function loadView(file, actionVal)
{	
	var myURL = file + "&selectedValue=" + escape(currentSelectedValue) + "&actionType=" + actionVal;

	load(myURL, top.opener);
	window.close();
}

function load(file,target) 
{
   window.location.href = file;
}

</script>
</head>
<%--END HEADER TAG--%>

<body topmargin=0 leftmargin="0">
<html:form action="/handleServiceProviderDashboard" >
<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|364">

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><bean:message key="prompt.3.spacer"/></td>
  </tr>
  <tr>
    <td><html:hidden name="serviceProviderForm" property="actionType"/></td>
  </tr>
  <tr>
    <td valign=top>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
					<td valign=top>
						<%--tabs start--%>
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>													
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>		
        <tr>
          <td bgcolor='#6699FF'><bean:message key="prompt.3.spacer"/></td>
        </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td><bean:message key="prompt.3.spacer"/></td>
        </tr>
        <tr>
          <td valign=top align=center>
          	<%-- BEGIN HEADING TABLE --%>
            <table width='100%'>
              <tr>
                <td align="center" class="header"> <bean:message key="title.serviceProvider"/> </td>
              </tr>
            </table>
            <%-- END HEADING TABLE --%>

            <%-- BEGIN INSTRUCTION TABLE --%>
            <table width="98%" border="0" cellpadding=0 cellspacing=0>
              <logic:notEqual name="serviceProviderForm" property="confirmMessage" value="">
              <tr>
                <td class="confirm"><bean:write name="serviceProviderForm" property="confirmMessage"/></td>
              </tr>
              </logic:notEqual>              

              <logic:equal name="serviceProviderForm" property="actionType" value="createProvider">
              <tr>
                <td>
                  <ul>
                    <li>Add at least 1 Program and 1 Contact by clicking the Add button under the appropriate section.</li>
                  </ul>
                </td>
              </tr>
              </logic:equal>

              <logic:notEqual name="serviceProviderForm" property="actionType" value="createProvider">
              <tr>
                <td>
                  <ul>
                    <li>Add a new Program or Contact or select one and click the appropriate button to Edit/Delete. </li>
                  </ul>
                </td>
              </tr>
              </logic:notEqual>

              <logic:equal name="serviceProviderForm" property="actionType" value="updateProviderProgram">
               <tr>
                 <td class=confirm>Program Successfully Updated </td>
               </tr>
              </logic:equal>

              <logic:equal name="serviceProviderForm" property="actionType" value="updateProviderContact">
               <tr>
                 <td class=confirm>Contact Successfully Updated </td>
               </tr>
             </logic:equal>
            </table>
            <%-- BEGIN  TABLE --%>

            <%-- BEGIN SP TABLE --%>
            <table width="98%" border="0" cellspacing=0 class=borderTableBlue>
              <tr>
                <td class=detailHead>
                  <table width='100%' cellpadding=2 cellspacing=0>
                    <tr>
                      <td width='1%'><a href=""></a></td>
                       <td width='1%'><a href="javascript:showHideMulti('provider', 'sp', 1,'/<msp:webapp/>')"><img border=0 src="/<msp:webapp/>/images/expand.gif" name="provider"></a></td>
                      <td class=detailHead><bean:write name="serviceProviderForm" property="providerName"/></td>
                    </tr>
                  </table>
                </td>
              </tr>

              <tr class=hidden id='sp0'>
                <td>
                  <table width="100%" border=0 cellpadding="4" cellspacing="1">
                    <tr>
                      <td class=formDeLabel nowrap><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
                      <td class=formDe><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/> </td>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.inHouse" /></td>
                      <td class=formDe> <bean:write name="serviceProviderForm" property="isInHouse"/> </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.admin" /> <bean:message key="prompt.userId" /></td>
                      <td class=formDe> <bean:write property="adminContactId" name="serviceProviderForm"/> </td>
                     <%--  <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.contact" /> <bean:message key="prompt.userId" /></td>
                      <td class=formDe><bean:write property="contactUserId" name="serviceProviderForm"/></td> --%> <!-- 86318 -->
                      <td class=formDeLabel width=1% nowrap><bean:message key="prompt.maxYouth" /></td>
                      <td class=formDe> <bean:write name="serviceProviderForm" property="maxYouth"/> </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel nowrap>Email</td>
                      <td colspan=1 class=formDe> <bean:write name="serviceProviderForm" property="email"/> </td>
                       <td class=formDeLabel width=1% nowrap>Send Email Notification</td>
                      <td class=formDe> <bean:write name="serviceProviderForm" property="isEmailCheck"/> </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.phone" /></td>
                      <td class=formDe colspan=3> <msp:formatter name="serviceProviderForm" property="phoneNum" format="A-P-F"/>  <logic:notEqual name="serviceProviderForm" property="phoneNum.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="serviceProviderForm" property="phoneNum" format="X"/></logic:notEqual></td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.fax" /></td>
                      <td class=formDe colspan=3> <msp:formatter name="serviceProviderForm" property="fax" format="A-P-F"/> </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.website" /></td>
                      <td class=formDe colspan=3> <a href="<bean:write name="serviceProviderForm" property="webSite"/>" target="_blank"><bean:write name="serviceProviderForm" property="webSite"/></a></td>
                    </tr>
                    <tr>
                      <td class=formDeLabel width='1%' nowrap><%-- <bean:message key="prompt.ftp" /> --%>Notes</td>
                      <td class=formDe colspan=3 height="15%"><div class=notesftp> <bean:write name="serviceProviderForm" property="ftp"/> </div> </td>
                    </tr>
                    <tr>
                      <td colspan=4>
                        <table width="100%" cellpadding="1" cellspacing="1">
                          <%-- BEGIN ADDRESS INFORMATION TABLES --%>
                          <tr>
                            <td class=detailHead>
                              <table width='100%' cellpadding=2 cellspacing=0>
                                <tr>
                                  <td width='1%'><a href=""></a></td>
                                  <td class=detailHead>&nbsp;<bean:message key="prompt.mailingAddress" /></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td>
                              <table width="100%" border=0 cellpadding="2" cellspacing="1">
                                <tr>
                                  <td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.streetNumber"/></td>										
                									<td class="formDeLabel" nowrap ><bean:message key="prompt.streetName"/></td>									
                									<td class="formDeLabel" nowrap><bean:message key="prompt.streetType"/></td>										
                									<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.aptSuite"/></td>	
                                </tr>
                                <tr>
                                  <td class=formDe><bean:write name="serviceProviderForm" property="mailingAddress.streetNumber"/> </td>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.streetName"/> </td>
                                  <td class=formDe> <bean:write	name="serviceProviderForm" property="mailingAddress.streetType"/> </td>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.aptNumber"/>  </td>
                                </tr>
                                <tr>
                                  <td class="formDeLabel" nowrap width='1%'> <bean:message key="prompt.city"/></td>
              										<td class="formDeLabel" nowrap> <bean:message key="prompt.state"/></td>
              										<td class="formDeLabel" nowrap colspan=6><bean:message key="prompt.zipCode"/></td>
                                </tr>
                                <tr>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.city"/> </td>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="mailingAddress.state"/> </td>
                                  <td class=formDe colspan="2"> 
																		<bean:write name="serviceProviderForm" property="mailingAddress.zipCode"/> 
																		<logic:notEqual name="serviceProviderForm" property="mailingAddress.additionalZipCode" value="">- 
                                      <bean:write name="serviceProviderForm" property="mailingAddress.additionalZipCode"/> 
																		</logic:notEqual> 
																	</td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                          <%-- END ADDRESS INFORMATION TABLE --%>
                        </table>

                        <table width="100%" cellpadding="1" cellspacing="1">
                          <%-- BEGIN ADDRESS INFORMATION TABLES --%>
                          <tr>
                            <td class=detailHead>
                              <table width='100%' cellpadding=2 cellspacing=0>
                                <tr>
                                  <td width='1%'><a href=""></a></td>
                                  <td class=detailHead>&nbsp;<bean:message key="prompt.billingAddress" /></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td>
                              <table width="100%" border=0 cellpadding="2" cellspacing="1">
                                <tr>
                                  <td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.streetNumber"/></td>										
              										<td class="formDeLabel" nowrap ><bean:message key="prompt.streetName"/></td>									
              										<td class="formDeLabel" nowrap><bean:message key="prompt.streetType"/></td>										
              										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.aptSuite"/></td>	
                                </tr>
                                <tr>
                                  <td class=formDe><bean:write name="serviceProviderForm" property="billingAddress.streetNumber"/> </td>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.streetName"/> </td>
                                  <td class=formDe> <bean:write	name="serviceProviderForm" property="billingAddress.streetType"/> </td>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.aptNumber"/>  </td>
                                </tr>
                                <tr>
                                  <td class="formDeLabel" nowrap width='1%'> <bean:message key="prompt.city"/></td>
              										<td class="formDeLabel" nowrap> <bean:message key="prompt.state"/></td>
              										<td class="formDeLabel" nowrap colspan=6><bean:message key="prompt.zipCode"/></td>
                                </tr>
                                <tr>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.city"/> </td>
                                  <td class=formDe> <bean:write name="serviceProviderForm" property="billingAddress.state"/> </td>
                                  <td class=formDe colspan="2"> <bean:write name="serviceProviderForm" property="billingAddress.zipCode"/> <logic:notEqual name="serviceProviderForm" property="billingAddress.additionalZipCode" value=""> - 
                                  <bean:write name="serviceProviderForm" property="billingAddress.additionalZipCode"/> </logic:notEqual> </td>
                                </tr>
                        			</table>
                      			</td>
                          </tr>
                          <%-- END ADDRESS INFORMATION TABLE --%>
                    		</table>
                  		</td>
                    </tr>                          
                 	</table>
                </td>
              </tr>
            </table>
            <%-- END SP INFORMATION TABLE --%>

            <br>
            <table width="98%" cellpadding="0" cellspacing="0" border=0>
              <tr>
                <td width='100%' valign=top>
                  <table width="100%" cellpadding="2" cellspacing="0" class=borderTableBlue>
                    <tr class=detailHead>
                      <td> <bean:message key="prompt.programs"/> </td>
                    </tr>
                    <tr>
                      <td>
                        <table width="100%" cellpadding="2" cellspacing="1">
                          <tr class=formDeLabel>                          
                            <td width='1%'>&nbsp;</td>	                           
                            <td class="subhead" valign=top> <bean:message key="prompt.name" /> </td>
                            <td class="subhead" valign=top> <bean:message key="prompt.code"/> </td>
                            <td class="subhead" valign=top><bean:message key="prompt.createProgram" /> </td> <!--//added for U.S #11099-->
                            <td class="subhead" valign=top><bean:message key="prompt.targetIntervention" /> </td>
            				<td class="subhead" valign=top><bean:message key="prompt.status"/> </td>	                                
                            <%-- 
  														<td class="subhead" valign=top><bean:message key="prompt.type"/> </td>
                              <td class="subhead" valign=top><bean:message key="prompt.subType"/> </td>
														--%>
                          </tr>
                          <% int RecordCounter = 0;
                             String bgcolor = "";
                          %>
                          
                          <logic:iterate name="serviceProviderForm" id="programsIndex" property="programs">
    <bean:define id="program" name="programsIndex" property="programName"/>

    <!-- Display row when programStatusId is not "I" -->
    <logic:notEqual name="programsIndex" property="programStatusId" value="I">
        <tr class="<%= (RecordCounter++ % 2 == 1) ? "normalRow" : "alternateRow" %>">
            <td>
                <logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                    <input type="radio" 
                           onclick="javascript:show('updateProgramButtons', 1, 'inline');" 
                           name="selectedValue" 
                           id="programRadio<%=RecordCounter%>" 
                           value="<%=program%>"/>
                </logic:notEqual>
            </td>
            <td>
                <logic:equal name="programsIndex" property="programStatusId" value="P">
                    <a href="javascript:loadView('/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=View', 'viewProgram')" 
                       style="font-style: Italic" 
                       onclick='setSelectedVal("<bean:write name='programsIndex' property='programName'/>")'>
                </logic:equal>
                <logic:notEqual name="programsIndex" property="programStatusId" value="P">
                    <a href="javascript:loadView('/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=View', 'viewProgram')" 
                       onclick='setSelectedVal("<bean:write name='programsIndex' property='programName'/>")'>
                </logic:notEqual>
                <bean:write name="programsIndex" property="programName"/> 
                </a>
            </td>
            <td><bean:write name="programsIndex" property="programCodeId"/> </td>
            <td><bean:write name="programsIndex" property="programScheduleTypeId"/> </td>
            <td><bean:write name="programsIndex" property="targetInterventionId"/> </td>
            <td>
                <logic:equal name="programsIndex" property="programStatusId" value="A">ACTIVE</logic:equal>
                <logic:equal name="programsIndex" property="programStatusId" value="P">PENDING</logic:equal>
                <logic:equal name="programsIndex" property="programStatusId" value="I">INACTIVE</logic:equal>
            </td>
        </tr>
    </logic:notEqual>

    <!-- Display row when programStatusId is "I" and user has permission -->
    <logic:equal name="programsIndex" property="programStatusId" value="I">
        <jims2:isAllowed requiredFeatures="CS-ASP-VIEW-INACTPGMORCNTCT">
            <tr class="<%= (RecordCounter++ % 2 == 1) ? "normalRow" : "alternateRow" %>">
                <td></td>
                <td>
                    <logic:equal name="programsIndex" property="programStatusId" value="P">
                        <a href="javascript:loadView('/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=View', 'viewProgram')" 
                           style="font-style: Italic" 
                           onclick='setSelectedVal("<bean:write name='programsIndex' property='programName'/>")'>
                    </logic:equal>
                    <logic:notEqual name="programsIndex" property="programStatusId" value="P">
                        <a href="javascript:loadView('/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=View', 'viewProgram')" 
                           onclick='setSelectedVal("<bean:write name='programsIndex' property='programName'/>")'>
                    </logic:notEqual>
                    <bean:write name="programsIndex" property="programName"/> 
                    </a>
                </td>
                <td><bean:write name="programsIndex" property="programCodeId"/> </td>
                <td><bean:write name="programsIndex" property="programScheduleTypeId"/> </td>
                <td><bean:write name="programsIndex" property="targetInterventionId"/> </td>
                <td>
                    <logic:equal name="programsIndex" property="programStatusId" value="A">ACTIVE</logic:equal>
                    <logic:equal name="programsIndex" property="programStatusId" value="P">PENDING</logic:equal>
                    <logic:equal name="programsIndex" property="programStatusId" value="I">INACTIVE</logic:equal>
                </td>
            </tr>
        </jims2:isAllowed>
    </logic:equal>
</logic:iterate>
                  </table>
                      </td>
                    </tr>
                    <tr>
                      <td align=center>
                        <table cellpadding="2" cellspacing="0">
                          <logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                            <tr>
                              <td>
                                <jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
                                  <html:submit property="submitAction" onclick="setAction('addProgram');"><bean:message key="button.add"></bean:message></html:submit>
  
                    							<logic:notEmpty name="serviceProviderForm" property="programs">
                                    <span id='updateProgramButtons' class='hidden'>
                        								<html:submit property="submitAction" onclick="setAction('updateProgram');">
                        									<bean:message key="button.update"></bean:message>
                        								</html:submit>
                        							  <%-- 
      																		<html:submit property="submitAction" onclick="checkRadioFieldSelected(this.form);">
                          								<bean:message key="button.delete"></bean:message>
                          								</html:submit>
    																		--%>
                        								<html:submit property="submitAction" onclick="setAction('createService');">
                        									<bean:message key="button.addService"/>
                        								</html:submit>
                                    </span>
                    							</logic:notEmpty>
                  							</jims2:isAllowed>
                              </td>
                            </tr>
                          </logic:notEqual>
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
                <td width='90%' valign=top>
                  <table width="100%" cellpadding="2" cellspacing="0" class=borderTableBlue>
                    <tr class=detailHead>
                      <td> Contacts</td>
                    </tr>
                    <tr>
                      <td>
                        <table width="100%" cellpadding="2" cellspacing="1">
                          <tr class=formDeLabel>
                          <!--   <td width="3%">&nbsp;</td> 86318 -->
                            <td class="subhead" valign=top><bean:message key="prompt.name"/>
                            <jims2:sortResults beanName="serviceProviderForm" results="contacts" primaryPropSort="lastName" primarySortType="STRING" defaultSort="false" sortId="1" levelDeep="3"/> 
                            </td>
                           <%--  <td class="subhead" valign=top><bean:message key="prompt.empId"/></td> 86318--%>
                          <%--   <td class="subhead" valign=top> <bean:message key="prompt.admin"/> <bean:message key="prompt.contact"/> </td>86318 --%>
                            <td class="subhead" valign=top><bean:message key="prompt.email"/>
                            <%-- <jims2:sortResults beanName="serviceProviderForm" results="contacts" primaryPropSort="contactEmail" primarySortType="STRING" defaultSort="false" sortId="2" /> --%>
                             </td>
                            <td class="subhead" valign=top>Login
                            <%-- <jims2:sortResults beanName="serviceProviderForm" results="contacts" primaryPropSort="email" primarySortType="STRING" defaultSort="false" sortId="3" /> --%>
                            </td>
                            <td class="subhead" valign=top><bean:message key="prompt.officePhone"/>
                            <%-- <jims2:sortResults beanName="serviceProviderForm" results="contacts" primaryPropSort="workPhone" primarySortType="STRING" defaultSort="false" sortId="4" /> --%>
                             </td>
                            <td class="subhead" valign=top>Status
                            <jims2:sortResults beanName="serviceProviderForm" results="contacts" primaryPropSort="statusCd" primarySortType="STRING" defaultSort="false" sortId="5"  levelDeep="3"/> 
                            </td>
                          </tr>
                      
                          <logic:iterate name="serviceProviderForm" id="contactsIndex" property="contacts">
                          <logic:notEqual name="contactsIndex" property="statusCd" value="INACTIVE">
                            <tr class= <% RecordCounter++;
									            bgcolor = "normalRow";                      
									            if (RecordCounter % 2 == 1)
									               bgcolor = "alternateRow";
						                  out.print(bgcolor); %>
														>
                             <%-- 86318 <td width='1%'>
                                <bean:define id="contact" name="contactsIndex" property="employeeId"/>
                              		<logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                              			<logic:notEqual name="contactsIndex" property="statusId" value="I">
                                			<input type="radio" name="selectedValue" value='<bean:write name="contactsIndex" property="employeeId"/>'/>	
                              			</logic:notEqual>
                              		</logic:notEqual>
                               </td> --%>
                               <td>
								   <a href="javascript:loadView('/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=View', 'viewContact')" onclick="setSelectedVal('<bean:write name="contactsIndex" property="employeeId"/>')">			                                
                                   	<%-- <bean:write name="contactsIndex" property="prefix"/> 86318--%>
                                   	<bean:write name="contactsIndex" property="lastName"/><logic:notEqual name="contactsIndex" property="firstName" value="">,</logic:notEqual>
                                   	<bean:write name="contactsIndex" property="firstName"/> <bean:write name="contactsIndex" property="middleName"/> <bean:write name="contactsIndex" property="suffix"/>
								   </a>
								</td>
                              <%--  <td> <bean:write name="contactsIndex" property="employeeId"/></td> 86318--%>			                               
                               <%-- <td><jims2:displayBoolean name="contactsIndex" property="adminContact" trueValue="YES" falseValue="NO"/></td> 86318--%> 
                               <td> <a href='mailto:<bean:write name="contactsIndex" property="contactEmail"/>'><bean:write name="contactsIndex" property="contactEmail"/></a></td>		                               
                               <td> <bean:write name="contactsIndex" property="email"/> </td>
                               <td> 
                               	 <bean:write name="contactsIndex" property="workPhone" formatKey="phone.format"/> 
								<%-- <logic:notEqual name="contactsIndex" property="extnNum" value="">Ext-<bean:write name="contactsIndex" property="extnNum"/></logic:notEqual> 86318--%>
							   </td>
							   <td><bean:write name="contactsIndex" property="statusCd"/> <%-- <jims2:displayBoolean name="contactsIndex" property="inactivated" trueValue="INACTIVE" falseValue="ACTIVE"/> --%></td>			         
                            </tr>
                             </logic:notEqual>
                             <logic:equal name="contactsIndex" property="statusCd" value="INACTIVE">
                             <jims2:isAllowed requiredFeatures="CS-ASP-VIEW-INACTPGMORCNTCT">
                            <tr class= <% RecordCounter++;
									            bgcolor = "normalRow";                      
									            if (RecordCounter % 2 == 1)
									               bgcolor = "alternateRow";
						                  out.print(bgcolor); %>
														>
                             <%-- 86318 <td width='1%'>
                                <bean:define id="contact" name="contactsIndex" property="employeeId"/>
                              		<logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                              			<logic:notEqual name="contactsIndex" property="statusId" value="I">
                                			<input type="radio" name="selectedValue" value='<bean:write name="contactsIndex" property="employeeId"/>'/>	
                              			</logic:notEqual>
                              		</logic:notEqual>
                               </td> --%>
                               <td>
								   <a href="javascript:loadView('/<msp:webapp/>handleServiceProviderDashboard.do?submitAction=View', 'viewContact')" onclick="setSelectedVal('<bean:write name="contactsIndex" property="employeeId"/>')">			                                
                                   	<%-- <bean:write name="contactsIndex" property="prefix"/> 86318--%>
                                   	<bean:write name="contactsIndex" property="lastName"/><logic:notEqual name="contactsIndex" property="firstName" value="">,</logic:notEqual>
                                   	<bean:write name="contactsIndex" property="firstName"/> <bean:write name="contactsIndex" property="middleName"/> <bean:write name="contactsIndex" property="suffix"/>
								   </a>
								</td>
                              <%--  <td> <bean:write name="contactsIndex" property="employeeId"/></td> 86318--%>			                               
                               <%-- <td><jims2:displayBoolean name="contactsIndex" property="adminContact" trueValue="YES" falseValue="NO"/></td> 86318--%> 
                               <td> <a href='mailto:<bean:write name="contactsIndex" property="contactEmail"/>'><bean:write name="contactsIndex" property="contactEmail"/></a></td>		                               
                               <td> <bean:write name="contactsIndex" property="email"/> </td>
                               <td> 
                               	 <bean:write name="contactsIndex" property="workPhone" formatKey="phone.format"/> 
								<%-- <logic:notEqual name="contactsIndex" property="extnNum" value="">Ext-<bean:write name="contactsIndex" property="extnNum"/></logic:notEqual> 86318--%>
							   </td>
							   <td><bean:write name="contactsIndex" property="statusCd"/> <%-- <jims2:displayBoolean name="contactsIndex" property="inactivated" trueValue="INACTIVE" falseValue="ACTIVE"/> --%></td>			         
                            </tr>
                            </jims2:isAllowed>
                             </logic:equal>
                         	</logic:iterate>
                        </table>
                      </td>
                    </tr>

                  <%-- 86318  <tr>
                      <td align=center>
                        <table cellpadding="2" cellspacing="0">
                          <logic:notEqual name="serviceProviderForm" property="statusId" value="I">
                            <tr>
                              <td>
                                <jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
                                  <html:submit property="submitAction" onclick="setAction('addContact');"><bean:message key="button.add"></bean:message></html:submit>
  
                                  <logic:notEmpty name="serviceProviderForm" property="contacts">
                  									<html:submit property="submitAction" onclick=" setAction('updateContact'); return checkRadioFieldSelected(this.form);">
                  										<bean:message key="button.update"></bean:message>
                  									</html:submit>																
                  								</logic:notEmpty>
                								</jims2:isAllowed>
              								</td>
                            </tr>
                          </logic:notEqual>
                        </table>
                      </td>
                    </tr> --%>
                  </table>
                </td>
              </tr>
            </table>
          
            <%-- BEGIN BUTTON TABLE --%>
            <br>
            <table border="0">
              <tr>
                <td>
                  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
                  
                  	<logic:equal name="serviceProviderForm" property="statusId" value="I">
  						<html:submit property="submitAction" onclick="setAction('reactivate');"><bean:message key="button.reactivate"></bean:message></html:submit>
  				 	</logic:equal>
  				 	
  					<logic:notEqual name="serviceProviderForm" property="statusId" value="I">
 						<jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
 							<html:submit property="submitAction" onclick="setAction('updateSP');"><bean:message key="button.updateSP"></bean:message></html:submit>
 						</jims2:isAllowed>	
  					</logic:notEqual>
					<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
                </td>
              </tr>
            </table>
            <%-- END BUTTON TABLE --%>
          </td>
        </tr>
      </table><br>
    </td>
  </tr>
</table><br>
<%-- END  TABLE --%>
</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>

