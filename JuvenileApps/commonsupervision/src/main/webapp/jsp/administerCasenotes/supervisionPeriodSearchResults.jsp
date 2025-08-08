<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose	Create JSP -->
<!-- 08/28/2006  H Rodriguez	ER#34507 Implement new UI Guidelines for all date fields -->
<!-- 05/13/2008  L Deen		  	Removed CSCD from page title  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - administerCasenotes/supervisionPeriodSearchResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSupervisionPeriodSelect" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Casenotes/CSCD_Casenotes.htm#|5">
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
						<tiles:put name="tab" value="casenotesTab"/>
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
                  <td align="center" class="header">
					<bean:message key="prompt.casenotes" />&nbsp;<bean:message key="prompt.supervisionPeriod" />&nbsp;<bean:message key="prompt.list" />
				  </td>
                  </tr>
                </table>
                <!-- END HEADING TABLE -->
                <!-- BEGIN ERROR TABLE -->
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
                        <li>Select a Supervision Period & click Submit.</li>
                      </ul>
                    </td>
                  </tr>
                </table>
                <!-- END INSTRUCTION TABLE -->
               <tiles:insert page="../common/superviseeInfoHeader.jsp" flush="true">
						<tiles:put name="hideQuickLinks" value="true"/>
					</tiles:insert>		
					<br>
                <!-- BEGIN DETAIL TABLE -->
                <table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
                  <tr class="detailHead">
                    <td width="1%"></td>
                    <td>Supervision Period</td>
                    <td>Case#</td>
                    <td>CRT</td>
                    <td>Case Begin/End Date</td>
                  </tr>
                 <nest:iterate id="periodsList" name="superviseeSearchForm" property="searchSupPeriodsResults">
                  <tr bgcolor="#F0F0F0">
                    <td width="1%">
                      <input type="radio" name="selectedValue" value='<nest:write property="supPeriodId"/>'/>
                    </td>
                    <td>
                      <nest:write property="supPeriodBeginDateAsStr"/> - <nest:write property="supPeriodEndDateAsStr"/>
                    </td>
                    <td valign="top">
                    	<nest:iterate property ="cases">
                    		<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<nest:write property="orderId"/>')"><nest:write property="caseNum"/></a><br>
                    	</nest:iterate>
                    </td>
                    <td valign="top">
						<nest:iterate property ="cases">
							<nest:write property="court"/><br>
						</nest:iterate>
					</td>
                    <td valign="top">
                    	<nest:iterate property ="cases">
                    		<nest:write property="caseSupPeriodBeginDateAsStr"/> - <nest:write property="caseSupPeriodEndDateAsStr"/><br>
                    	</nest:iterate>
                    </td>
                  </tr>
                  </nest:iterate>
                </table>
                <!-- END DETAIL TABLE -->
                <br>
                <!-- BEGIN BUTTON TABLE -->
                <table border="0" width="100%">
                  <tr>
                    <td align="center">
                    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
                    <html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedValue', 'Please select a supervision period.');">Submit</html:submit>
												
					<html:reset><bean:message key="button.reset"></bean:message></html:reset>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>	
                    </td>
                  </tr>
                </table>
                <!-- END BUTTON TABLE -->
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <!-- END  TABLE -->
  </div>
  <br>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
