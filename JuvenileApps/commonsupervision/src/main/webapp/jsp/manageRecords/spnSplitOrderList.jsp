<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/09/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> - manageRecords/orderList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displaySpnSplitSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|4">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->    	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUMMARY PAGE SECTION -->
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header"><bean:message key="prompt.spnSplit" />&nbsp;<bean:message key="prompt.order" />&nbsp;<bean:message key="prompt.list" /></td>
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
										<li>Select which order(s) to move from Erroneous SPN to Valid SPN</li>
									</ul>
								</td>
							</tr>									
						</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SPN INFORMATION TABLE -->									
						<tiles:insert page="../common/spnSplitInfoTile.jsp" flush="true">
							<tiles:put name="erroneousSpn" beanName="spnSplitForm" beanProperty="erroneousSpn"/>
							<tiles:put name="validSpn" beanName="spnSplitForm" beanProperty="validSpn"/>
						</tiles:insert>		
<!-- END SPN INFORMATION TABLE -->
<!-- END SUMMARY PAGE SECTION -->
						<br/>
<!-- BEGIN ORDERS LIST TABLE -->
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>													
									Orders for Erroneous SPN for Supervision Period
									<bean:write name="spnSplitForm" property="currentSupPeriod.supPeriodBeginDateAsStr"/> -
									<bean:write name="spnSplitForm" property="currentSupPeriod.supPeriodEndDateAsStr"/>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class="formDeLabel">
										<bean:size id="ordersSize" name="spnSplitForm" property="currentSupPeriod.cases" />	
											<td width="1%"><input type="checkbox" name="checkForSplitAll" onclick="checkAllByName(this, 'currentSupPeriod.cases','selected', <bean:write name='ordersSize' />)"></td>
											<td><bean:message key="prompt.CDI" /></td>
											<td><bean:message key="prompt.case#" /></td>
		               						 <td><bean:message key="prompt.CRT" /></td>
											<td><bean:message key="prompt.orderFiled" /></td>
											<td><bean:message key="prompt.orderStatus" /></td>
											<td><bean:message key="prompt.version" /></td>
											<td><bean:message key="prompt.agency" /></td>
										</tr>
										<input type="hidden" name="clearOrdersSelection" value=""/>
										<nested:nest property="currentSupPeriod">
											 <nested:iterate property="cases">
												<tr>
													<td><nested:checkbox property="selected" onclick="uncheckCheckAll(this,'checkForSplitAll')" value="true"/></td>
													<td><nested:write property="cdi"/></td>
													<td><a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<nested:write property="orderId"/>')"><nested:write property="caseNum"/></a></td>
													<td><nested:write property="court"/> </td>
													<td><nested:write property="orderFileDateAsStr"/></td>
													<td><nested:write property="orderStatus"/></td>
													<td><nested:write property="orderVersionTitle"/></td>
													<td><nested:write property="agencyId"/></td>
												</tr>
											</nested:iterate>
										</nested:nest>
									</table>
								</td>
							</tr>
						</table>
<!-- END ORDER TABLE LIST -->
						<br>
<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>											
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
									 <html:reset ><bean:message key="button.reset"/></html:reset>&nbsp;	 								
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>								
							</tr>		
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->	
		<br><br>			
		</td>
	</tr>
</table>
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
