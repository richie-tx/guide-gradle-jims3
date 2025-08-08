<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/29/2005	 mjt - Create JSP -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to finish and delete buttons -->
<!-- 04/03/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/23/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!-- LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


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
<html:javascript formName="departmentPolicyDelete" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<!-- Javascript for emulated navigation -->
<link href="/<msp:webapp/>css/base.css" rel="stylesheet" type="text/css">

<title><bean:message key="title.heading" /> - deptPolicyDelete.jsp</title>
</head>

<bean:define id="departmentGroup1Caption" value="prompt.category" type="java.lang.String"/>
<bean:define id="departmentGroup2Caption" value="prompt.type"  type="java.lang.String"/>
<bean:define id="departmentGroup3Caption" value="prompt.subtype" type="java.lang.String"/>
<bean:define id="deptPolicyTitle" value="prompt.policyTableTitle" type="java.lang.String"/>
<bean:define id="departmentPolicyLiteralCaption" value="prompt.policyTableTitle" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayDepartmentPolicySummary" target="content">

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
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
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align=center>
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
									<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|106">
										<bean:message key="prompt.delete" />
										<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;-&nbsp;
										<bean:message key="title.setNotes" />										
									</logic:notEqual>
									<logic:equal name="departmentPolicyForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|106">
										<bean:message key="prompt.inactivate" />
										<bean:message key="prompt.inUse" />
										<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;-&nbsp;
										<bean:message key="title.setNotes" />										
									</logic:equal>
								</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
					
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul><li>Enter delete note and click next.</li></ul></td>
							</tr>
						</table>

						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
							<tr>
								<td class="detailHead"><bean:message key="<%=deptPolicyTitle%>"/></td>
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding=4 cellspacing=1>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.name" /></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="name" /></td>
											</tr>
					
										<tr>
											<td class="formDeLabel" width=1% nowrap><bean:message key="<%=departmentGroup1Caption%>"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="group1Name" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width=1% nowrap><bean:message key="<%=departmentGroup2Caption%>"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="group2Name" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width=1% nowrap><bean:message key="<%=departmentGroup3Caption%>"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="group3Name" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" valign="top"><bean:message key="<%=departmentPolicyLiteralCaption%>" /></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="departmentPolicy"  filter="false"/> </td>
										</tr>
										<tr>
											<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.effectiveDate"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="effectiveDate" /></td>
										</tr>
											<tr>
											<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
												<td class="formDe"><bean:write name="departmentPolicyForm" property="inactiveDate" /></td>
											</tr>
										<tr>
											<td class="formDeLabel" valign="top" width=1% nowrap><bean:message key="prompt.notes"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="notes" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br>
							
						<table width="98%" border="0" cellpadding=4 cellspacing=0 class=borderTableBlue>
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding=0 cellspacing=0>
										<tr>
											<td width=1%><a href="javascript:showHide('courts','row', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="courts"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectedCourts" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="courtsSpan" class="visibleTR">
								<td>
									<tiles:insert page="../../../common/courts.jsp" flush="true">
										<tiles:put name="beanName" beanName="departmentPolicyForm" />
										<tiles:put name="mode" value="view" />
										
									</tiles:insert>
								</td>
							</tr>
						</table>
						<br>
						
						<!--delete reason start-->
						<table width="98%" border="0">							
							<tr>
								<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
							</tr>
						</table>					
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>
								<logic:equal name="departmentPolicyForm" property="inUse" value="true">
										<bean:message key="prompt.inactivateReason" />
									</logic:equal>
									<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
										<bean:message key="prompt.deleteNotes" />
									</logic:notEqual>
								
								
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel" valign="top" width=1% nowrap>
												<bean:message key="prompt.4.diamond"/>
												<bean:message key="prompt.notes" />
											</td>
											<td class="formDe">
												<html:textarea name="departmentPolicyForm" property="deleteNote" style="width:100%" rows="5"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br>
						
						
						<table width="98%" border="0" cellspacing="0" cellpadding="2">
							<tr>
								<td>
									<!-- Associations -->
										<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
											<tiles:put name="associatedConditions" beanName="departmentPolicyForm" beanProperty="associatedConditions"/>
											<tiles:put name="conditionGroup2Caption" value="prompt.type"/>
											<tiles:put name="conditionGroup3Caption" value="prompt.subtype"/>
										</tiles:insert>
								</td>
							</tr>
						</table>
						<br>
		
								
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  <tr>
							<td align="center">
									 <input type="button" value="Back" name="return" onClick="history.go(-1)">
									<html:submit property="submitAction" onclick="return (validateDepartmentPolicyDelete(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
									<input type="button" value='<bean:message key="button.cancel" />' name="return" onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>displayDepartmentPolicySearch.do', false) && disableSubmit(this, this.form)">
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
</body>
</html:html>
