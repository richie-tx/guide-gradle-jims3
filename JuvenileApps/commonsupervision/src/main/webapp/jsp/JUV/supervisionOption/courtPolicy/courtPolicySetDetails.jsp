<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/18/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to next button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - courtPolicySetDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>

</head>

<bean:define id="courtPolicyTitle" value="title.consequence" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" value="prompt.category" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" value="prompt.type" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" value="prompt.subtype" type="java.lang.String"/>
<bean:define id="courtPolicyLiteralCaption" value="prompt.literal" type="java.lang.String"/>
<bean:define id="conditionGroup1Caption" value="prompt.category" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" value="prompt.type" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCourtPolicyDetailsExceptions" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
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
					<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
							<tr>
								<td align="center" class="header">
									<logic:equal name="courtPolicyForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|55">
										<bean:message key="prompt.copy" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
									</logic:equal>
									
									<logic:equal name="courtPolicyForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|31">
										<bean:message key="prompt.create" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
									</logic:equal>
									
									<logic:equal name="courtPolicyForm" property="action" value="update">
										<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|44">
											<bean:message key="prompt.update" />
											<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
										</logic:notEqual>
										<logic:equal name="courtPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|45">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.inUse" />
											<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
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
						<tiles:insert page="courtInfoSec1Tile.jsp" flush="true">
						</tiles:insert>	
						<br>
						<!--task info start-->
						<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="courtPolicyForm" beanProperty="tasks"/>
									</tiles:insert>	
								<!--task info end-->
						<br>

						<tiles:insert page="../../../common/courts.jsp" flush="true">
							<tiles:put name="beanName" beanName="courtPolicyForm" />
							<tiles:put name="mode" value="setExceptionCourts" />
						</tiles:insert>
						<br>
						
						<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
												<tiles:put name="associatedConditions" beanName="courtPolicyForm" beanProperty="associatedConditions"/>
												<tiles:put name="conditionGroup2Caption" value="prompt.type"/>
											    <tiles:put name="conditionGroup3Caption" value="prompt.subtype"/>
											</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
						</logic:notEqual>
						
						 <logic:notEmpty name="courtPolicyForm" property="removedAssociations"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/removedAssociationsView.jsp" flush="true">
												<tiles:put name="removedAssociations" beanName="courtPolicyForm" beanProperty="removedAssociations"/>
												<tiles:put name="associationType" value="court"/>
												<tiles:put name="group2Caption" value="prompt.type"/>
												<tiles:put name="group3Caption" value="prompt.subtype"/>
											</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
						 </logic:notEmpty> 
						
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  	<tr>
						    	<td align="center">
						      	<input type="button" value="Back" name="return" onClick="history.go(-1)">
						      	<html:submit property="submitAction" onclick="return (validateVariableElements(this.form) && validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
							  	<input type="reset" value="Reset" name="reset">

									<input type=button value='<bean:message key="button.cancel" />' 
										<logic:equal name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicyCreate.do', true);"
										</logic:equal>
										<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);"
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</html:html>
