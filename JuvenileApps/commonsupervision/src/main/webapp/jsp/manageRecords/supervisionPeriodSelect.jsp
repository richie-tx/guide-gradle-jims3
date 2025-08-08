<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/09/2006	 Hien Rodriguez - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
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
<title><bean:message key="title.heading" /> - manageRecords/supervisionPeriodSelect.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/displaySpnSplitOrders" target="content">
			<div align="center">
				<table width="98%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign="top">
							<table width=100% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<!--tabs start-->
											<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
							</table>
							<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
								</tr>
								<tr>
									<td valign="top" align=center>

										<!-- BEGIN HEADING TABLE -->
										<table width=100%>
											<tr>
												<td align="center" class="header" ><bean:message key="prompt.spnSplit" /> - <bean:message key="prompt.select" /> <bean:message key="prompt.supervisionPeriod" /></td>
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
										<!-- BEGIN INSTURCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Select a supervision period. Click Next.</li>
													</ul>
												</td>
											</tr>
										</table>
										<!-- END INSTRUCTION TABLE -->
										
										<!-- supervisee info start -->
											<tiles:insert page="../common/spnSplitInfoTile.jsp" flush="true">
												<tiles:put name="erroneousSpn" beanName="spnSplitForm" beanProperty="erroneousSpn"/>
												<tiles:put name="validSpn" beanName="spnSplitForm" beanProperty="validSpn"/>
											</tiles:insert>		
										<!-- supervisee info end -->
										<div class=spacer4px></div>
										<!-- supervision periods list start -->
										<table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
		                  <tr class="detailHead">
		                    <td width=1%></td>
		                    <td><bean:message key="prompt.supervisionPeriod" /></td>
		                    <td><bean:message key="prompt.case#" /></td>
		                    <td><bean:message key="prompt.CRT" /></td>
		                    <td><bean:message key="prompt.case" /> <bean:message key="prompt.supervisionPeriod" /></td>
		                  </tr>
		                  
		                  <input type="hidden" name="clearSupPeriodSelection" value=""/>
		                  <nested:iterate id="supPeriodList" name="spnSplitForm" property="supPeriods">
		                  <tr>
		                    <td width=1% valign="top">
		                    
		                      <input type="radio" name="selectedValue" value='<nested:write property="supPeriodId"/>'/>
		                      <br>
		                    </td>
		                    <td valign="top"><bean:write name="supPeriodList" property="supPeriodBeginDateAsStr"/> - <bean:write name="supPeriodList" property="supPeriodEndDateAsStr"/> <br></td>
		                    <td valign="top">
		                    	<logic:iterate id="caseList" name="supPeriodList" property="cases">
		                    	<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="caseList" property="orderId"/>')"><bean:write name="caseList" property="caseNum"/> </a>
		                    	<br>
		                    	</logic:iterate>
		                   	</td>
		                    <td valign="top">
		                    <logic:iterate id="caseList" name="supPeriodList" property="cases">
		                    	<bean:write name="caseList" property="court"/> 
		                    	<br>
		                    </logic:iterate>
		                    </td>
		                    <td valign="top">
		                  
		                     <logic:iterate id="caseList" name="supPeriodList" property="cases">
		                    	<bean:write name="caseList" property="caseSupPeriodBeginDateAsStr"/>  - <bean:write name="caseList" property="caseSupPeriodEndDateAsStr"/>
		                    	<br>
		                    </logic:iterate>
		                    </td>
		                  </tr>
		                </nested:iterate>
		                </table>
										<!-- supervision periods list end -->
										<br>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
												<html:submit property="submitAction">
										<bean:message key="button.back"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return (disableSubmit(this, this.form));">
										<bean:message key="button.next"></bean:message></html:submit>&nbsp;
									<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
