<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/23/2006  Hien Rodriguez  JIMS200026607 - Add validateSelectedCourts -->
<!-- 03/03/2006  C Shimek  	   - ER#28542 add disable button to next button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 
<!-- 11/26/2007  Clarence Shimek - defect#47334 remove extra spaces in heading (merge removed this correction made under defedt39326) -->
<!-- 03/12/2010	 Dawn Gibler - #64355 MSO - Allow Deselect of court on in-use condition -->
<!-- 3/24/2014   R Carter      - #76751 Removed Court and Department Association list/buttons per requirements -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>


<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - conditionUpdateSelectCourts.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String" value="prompt.group1"/>
<bean:define id="conditionGroup2Caption" type="java.lang.String" value="prompt.group2"/>
<bean:define id="conditionGroup3Caption" type="java.lang.String" value="prompt.group3"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionConditionUpdateSetDetails" target="content" >
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
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
								<logic:equal name="supervisionConditionForm" property="action" value="copy">
									<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|33">
									<bean:message key="prompt.copy" />
									<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.selectCourts" />
								</logic:equal>
								
								<logic:equal name="supervisionConditionForm" property="action" value="create">
									<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|9">
									<bean:message key="prompt.create" />
									<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.selectCourts" />
								</logic:equal>
								
								<logic:equal name="supervisionConditionForm" property="action" value="update">
									<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|20">
										<bean:message key="prompt.update" />
										<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.selectCourts" />
									</logic:notEqual>
									<logic:equal name="supervisionConditionForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|21">
										<bean:message key="prompt.update" />	
										<bean:message key="prompt.inUse" />
										<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.selectCourts" />
									</logic:equal>
								</logic:equal>
							 </td>	
						  </tr>
						</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
						   	<tr>
							 	<td><ul>
									<li>Enter the required fields </li>
									<li>Click Next.</li>
								</ul></td>
						  	</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<tiles:insert page="conditionInfoSec1Tile.jsp" flush="true">
						</tiles:insert>	
							
							<br>
							
							<!--task info start-->
								<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="supervisionConditionForm" beanProperty="tasks"/>
									</tiles:insert>	
								<!--task info end-->
							
						<br>
						
						<table class="borderTableBlue" border="0" cellpadding="2" cellspacing="0" width="98%">
							<tbody>
								<tr class="detailHead">
									<td><bean:message key="prompt.selectCourts" /></td>
									<td align="right"><img src="/<msp:webapp/>images/step_3.gif" hspace="1" vspace="0"></td>
								</tr>
								<tr>
									<tiles:insert page="../../../common/courts.jsp" flush="true">
										<tiles:put name="beanName" beanName="supervisionConditionForm" />
										<tiles:put name="ASOSpecialDisplay" value="ASOSpecialDisplay" />
										<tiles:put name="mode" value="select" />
										<tiles:put name="courtUpdateAllowed" value="true" />
									</tiles:insert>								
								</tr>
							</tbody>
						</table>
						<br>
						
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  	<tr>
						    	<td align="center">
									<input type="button" value="Back" name="return" onClick="history.go(-1)">
						      	<html:submit property="submitAction" onclick="return (validateSelectedCourts(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
						       	<input type="reset" onclick="return customCourtReset(this.form)"/>

									<input type="button" value='<bean:message key="button.cancel" />' 
										<logic:equal name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionCreate.do', false) && disableSubmit(this, this.form)"
										</logic:equal>
										<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionSearch.do', false) && disableSubmit(this, this.form)"
										</logic:notEqual>
									>
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

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
