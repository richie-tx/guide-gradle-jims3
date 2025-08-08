<!DOCTYPE HTML>

<%-- User selects the 'Create HCJDP' link on the left UI Navigation --%>
<%--MODIFICATIONS --%>
<%-- 05/24/2006 Uma Gopinath Create Contact Summary JSP --%>
<%-- 09/20/2006 Uma Gopinath Update/Inactivate summary flow added--%>
<%-- 06/05/2007 C Shimek	 #42434 removed errata coding "hree" on notes display --%>
<%-- 10/13/2015 R Capestani  #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
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

<title><bean:message key="title.heading"/> - contactSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin=0 leftmargin="0">

<html:form action="/submitJuvContactCreate" target="content" >
<logic:equal name="serviceProviderForm" property="actionType" value="addContact">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|386">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="updateContact">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|384"> 
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="inactivateContact">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|387">
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="viewContact">
   <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|382">
</logic:equal>

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>    	
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
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td valign=top align=center>
            <%-- BEGIN HEADING TABLE --%>
            <div class='spacer'></div>
            <table width='100%'>
              <tr>
    			      <td class="header" align="center">
      			      <logic:equal name="serviceProviderForm" property="actionType" value="addContact">  <bean:message key="prompt.add"/> </logic:equal>
      			      <logic:equal name="serviceProviderForm" property="actionType" value="updateContact">  <bean:message key="prompt.update"/> </logic:equal>
      			      <logic:equal name="serviceProviderForm" property="actionType" value="inactivateContact">  <bean:message key="prompt.inactivate"/> </logic:equal>
      			      <bean:message key="prompt.contact"/> <logic:notEqual name="serviceProviderForm" property="actionType" value="viewContact"> - <bean:message key="prompt.summary"/> </logic:notEqual>
      			      <logic:equal name="serviceProviderForm" property="actionType" value="viewContact"> <bean:message key="prompt.details"/> </logic:equal>
      			    </td>	
    				  </tr>      
            </table>
            <%-- END HEADING TABLE --%>

        	<%-- BEGIN ERROR TABLE --%>
      		<table width="98%" border="0" align="center">
      			<tr>
      				<td align="center" class="errorAlert"><html:errors></html:errors></td>
      			</tr>
      		</table>

          <%-- BEGIN INSTRUCTION TABLE --%>
          <table width="98%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td>
                <ul>
                  <logic:notEqual name="serviceProviderForm" property="actionType" value="viewContact">
                    <li>Review entries and click Save & Continue or click Back to make changes.</li>
                  </logic:notEqual>
                </ul>
              </td>
            </tr>
          </table>

         <%--header start--%>
        <table cellpadding=1 cellspacing=0 border=0 width='98%'>
    			<tr>
      			<td bgcolor='#cccccc'>
        			<table width='100%' border=0 cellpadding=2 cellspacing=1>
          			<tr>
          				<td class="headerLabel" width='1%' nowrap>Provider <bean:message key="prompt.name" /></td>
          				<td colspan=3 class=headerData><bean:write name="serviceProviderForm" property="providerName"/></td>
          			</tr>
          			<tr>
          				<td class="headerLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
          				<td class="headerData"><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
          				<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.inHouse" /></td>
          				<td class="headerData"><bean:write name="serviceProviderForm" property="isInHouse"/></td>
          			</tr>
          		</table>
        		</td>
      		</tr>
    		</table>
        <%--header end--%>

        <%-- BEGIN  TABLE --%>
        <div class='spacer'></div>
        <table width="98%" border="0" cellspacing=0 class=borderTableBlue>
          <tr>
            <td class=detailHead>
              <table width='100%' cellpadding=2 cellspacing=0>
                <tr>
            		  <td class=detailHead nowrap colspan=4><bean:message key="prompt.contact" /> <bean:message key="prompt.info" /></td>
            		</tr>
            	</table>
              <tr>
                <td>
                  <bean:define id="contact" name="serviceProviderForm" property="currentContact"/>
                  <table width="100%" border=0 cellpadding="4" cellspacing="1">
                    <logic:equal name="serviceProviderForm" property="isInHouse" value="YES">
                      <tr class=formDe>
                        <td class=formDeLabel nowrap><bean:message key="prompt.userId" /></td>
                        <td colspan=3 class=formDe><bean:write name="contact" property="logonId"/></td>                  
                      </tr>
                      <tr>
                        <td class="formDeLabel" valign=top><bean:message key="prompt.name"/></td>
                        <td class="formDe" colspan=3>
                          <bean:write name="contact" property="contactName.prefix"/>
                         	 <bean:write name="contact" property="contactName.lastName"/><logic:notEqual name="contact" property="contactName.firstName" value="">,</logic:notEqual>
                         	 <bean:write name="contact" property="contactName.firstName"/> <bean:write name="contact" property="contactName.middleName"/> <bean:write name="contact" property="contactName.suffix"/>
                        </td>
                      </tr>
                    </logic:equal>
    
                    <logic:notEqual name="serviceProviderForm" property="isInHouse" value="YES">
                      <tr>
                        <td class="formDeLabel" valign=top><bean:message key="prompt.name"/></td>
                        <td class="formDe" colspan=3><msp:formatter name="contact" property="contactName" format="P L, F M S"/></td>
                      </tr> 
                     <%--  <tr>
                        <td class="formDeLabel" valign=top><bean:message key="prompt.jobTitle"/></td>
                        <td class="formDe" colspan=3><bean:write name="contact" property="jobTitle"/></td>
                      </tr>   --%>       
                    </logic:notEqual>
                           
                   <%--  <tr>//86318
                      <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.administrative"/> <bean:message key="prompt.contact"/></td>
                      <td class=formDe colspan=3><bean:write name="contact" property="isAdminContact"/></td>
                    </tr> --%>
                    <tr>
                      <td class=formDeLabel nowrap><bean:message key="prompt.officePhone"/></td>
                      <td class=formDe colspan=3><msp:formatter name="contact" property="officePhone" format="A-P-F"/>  <logic:notEqual name="contact" property="officePhone.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="contact" property="officePhone" format="X"/></logic:notEqual></td>
                    </tr>
                  <%--   <tr> //86318
                      <td class=formDeLabel nowrap><bean:message key="prompt.fax"/></td>
                      <td class=formDe colspan=3><msp:formatter name="contact" property="fax" format="A-P-F"/> </td>
                    </tr>
                    <tr>
                      <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.pager"/></td>
                      <td class=formDe colspan=3><msp:formatter name="contact" property="pager" format="A-P-F"/></td>
                    </tr> --%>
                   <%--86318  <tr>
                      <td class=formDeLabel nowrap><bean:message key="prompt.cellPhone"/></td>
                      <td class=formDe colspan=3><msp:formatter name="contact" property="cellPhone" format="A-P-F"/></td>
                    </tr> --%>
                    <tr>
                      <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.email"/></td>
                      <td class=formDe colspan=3><bean:write name="contact" property="contactEmail"/></td>
                    </tr>
                    <tr>
                      <td class=formDeLabel nowrap width='1%'>Login</td>
                      <td class=formDe colspan=3><bean:write name="contact" property="email"/></td>
                    </tr>
                   <%-- 86318  <tr>
                      <td class=formDeLabel colspan=4><bean:message key="prompt.notes"/></td>
                    </tr>
                    <tr>
                      <td class=formDe colspan=4><bean:write name="contact" property="notes"/></td>                          
                    </tr> --%>
                  </table>
    	
    	         		<logic:notEmpty name="serviceProviderForm" property="serviceEvents">
    	           		<br>
    		           	<table width='98%' cellpadding=2 cellspacing=0 class=borderTableBlue>
                      <tr>
                        <td class=detailHead><bean:message key="prompt.future" /> <bean:message key="prompt.service" /> <bean:message key="prompt.events" /></td>
                      </tr>
                      <tr>
    	                  <td>
          								<table width="100%" cellpadding="2" cellspacing="1">
          								<% int RecordCounter=0;
          								   String bgcolor="";
          								%>
        								   <tr bgcolor='#cccccc'>
    								   	
       								   		<td class="subhead" valign=top><bean:message key="prompt.program"/></td>
        										<td class="subhead" valign=top nowrap><bean:message key="prompt.service"/></td>
        										<td class="subhead" valign=top><bean:message key="prompt.service"/> <bean:message key="prompt.provider"/></td>										
        										<td class="subhead" valign=top><bean:message key="prompt.dateTime"/> </td>
        										<td class="subhead" valign=top><bean:message key="prompt.#Scheduled"/></td>
        									</tr>
    
     								   	  <logic:iterate id="svIndex" name="serviceProviderForm" property="serviceEvents">
    											  <tr class= <% RecordCounter++;
    						              bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";                      
    					                out.print(bgcolor); %>
    												>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td><bean:write name="svIndex" property="startDateTime" formatKey="datetime.format.mmddyyyyHHmm"/></td>
                              <td></td>
    			                </tr>
    									  </logic:iterate>
    								  </table>
    							  </td>
    							</tr>
    						</table>
    					</logic:notEmpty>
            </td>
          </tr>
        </table>

      <%-- BEGIN BUTTON TABLE --%>
      <table border="0">
        <tr>
          <td align="center">
					  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
					   <html:submit property="submitAction"><bean:message key="button.createFundSource"></bean:message></html:submit>
					  

      			<logic:notEqual name="serviceProviderForm" property="actionType" value="viewContact">
      				<html:submit property="submitAction"  onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
      				<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
      			</logic:notEqual>
    			</td>
        </tr>
      </table>       
      <%-- END BUTTON TABLE --%>     
      </table>
      <div class='spacer'></div>
    </td>
  </tr>
</table>
<%-- END  TABLE --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>
