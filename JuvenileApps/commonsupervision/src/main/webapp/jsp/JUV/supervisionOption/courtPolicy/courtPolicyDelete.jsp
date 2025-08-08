<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/20/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 01/25/2006  Clarence Shimek Defect 26469 - add validation for Reason for Delete to be required -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to next button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

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
<title><bean:message key="title.heading" /> - courtPolicyDelete.jsp</title>

<html:javascript formName="courtPolicyDelete" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="courtPolicyTitle" value="title.consequence" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" value="prompt.category" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" value="prompt.type" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" value="prompt.subtype" type="java.lang.String"/>
<bean:define id="courtPolicyLiteralCaption" value="prompt.literal" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" value="prompt.type" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" value="prompt.subtype" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayCourtPolicySummary" target="content" >
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
									<logic:equal name="courtPolicyForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|80">
										<bean:message key="prompt.inactivate" />&nbsp;
										<bean:message key="prompt.inUse" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;
									 	<bean:message key="title.setNotes" />										
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
										<bean:message key="prompt.delete" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;
									 	<bean:message key="title.setNotes" />										
									</logic:notEqual>
									</td>
								</tr>
							<tr>
								<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
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
								<tr>
									<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
								</tr>
							</table>
							<!-- BEGIN  TABLE -->
								<tiles:insert page="courtInfoSec1Tile.jsp" flush="true">
						</tiles:insert>	
							<br>	
								
							<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="courtPolicyForm" beanProperty="tasks"/>
									</tiles:insert>	
							<!--task info end-->
							
							<br>	
								
							<table width="98%" border="0" cellpadding=4 cellspacing=0 class=borderTableBlue>
								<tr>
									<td class=detailHead>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td width=1%><a href="javascript:showHide('courts','row', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="courts"></a></td>
												<td class=detailHead>&nbsp;<bean:message key="prompt.selectedCourts" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_3.gif" vspace=0></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="courtsSpan" class="visibleTR">
									<td>
										<tiles:insert page="../../../common/courts.jsp" flush="true">
											<tiles:put name="beanName" beanName="courtPolicyForm" />
											<tiles:put name="mode" value="view" />
											<tiles:put name="displayDDNA" value="true" />
										</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
							
							<logic:notEmpty name="courtPolicyForm" property="variableElementResponseEvents">
							<bean:size id="varElementRESize" name="courtPolicyForm" property="variableElementResponseEvents"/>
							
								
							<table width="98%" border="0" cellspacing=1 cellpadding=4>
								<tr>
										<td colspan=2 class=detailHead>
											<table width=100% cellpadding=0 cellspacing=0>
												<tr>
													<td width=1%><a href="javascript:showHideMulti('setDetails', 'sd', '<bean:write name="varElementRESize"/>' )" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="setDetails"></a></td>
													<td class=detailHead>&nbsp;<bean:message key="prompt.setDetailsForSelectedCourts" /></td>
													<td align=right><img src="/<msp:webapp/>images/step_4.gif" vspace=0></td>
												</tr>
											</table>
										</td>
								</tr>
								
								<logic:notEmpty name="courtPolicyForm" property="variableElementResponseEvents">
									<logic:iterate indexId="varElementRECount" id="varElementREIter" name="courtPolicyForm" property="variableElementResponseEvents">						  
										<logic:notEmpty name="varElementREIter" property="name">
											<tr id="sd<bean:write name='varElementRECount'/>" class="visibleTR">
												<td class=formDeLabel nowrap width=1%><bean:write name="varElementREIter" property="name"/></td>
												<td class=formDe>
													<bean:write name="varElementREIter" property="value"/>
													<logic:equal name="varElementREIter" property="fixed" value="true">
														<bean:message key="prompt.fixed" />
													</logic:equal>
													<logic:equal name="varElementREIter" property="fixed" value="false">
														<bean:message key="prompt.variable" />
													</logic:equal>
												</td>
											</tr>
										</logic:notEmpty>
									</logic:iterate>
								</logic:notEmpty>
							</table>
							
							</logic:notEmpty>
							
							<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr><td><br></td></tr>
							  <tr>
									<td colspan=2 class=detailHead>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td class=detailHead>Exceptions - Set Details</td>
												<td align=right><img src="/<msp:webapp/>images/step_5.gif" vspace=0></td>
											</tr>
										</table>
									</td>
							  </tr>
								
								<logic:notEmpty name="courtPolicyForm" property="exceptionCourtVarElemBeans">
									<logic:iterate id="exceptionCourtVarElemBeansIter" name="courtPolicyForm" property="exceptionCourtVarElemBeans">
										<tr>
											<td class=boldText colspan=2><bean:write name="exceptionCourtVarElemBeansIter" property="courtNumber"/></td>
										</tr>
										<logic:iterate id="variableElementsIter" name="exceptionCourtVarElemBeansIter" property="variableElements">
											<logic:notEmpty name="variableElementsIter" property="name">
												<tr>
													<td class=formDeLabel nowrap width=1%><bean:write name="variableElementsIter" property="name"/></td>
													<td class=formDe>
														<bean:write name="variableElementsIter" property="value"/>
														
														<logic:equal name="variableElementsIter" property="fixed" value="true">
															<bean:message key="prompt.fixed" />
														</logic:equal>
														<logic:equal name="variableElementsIter" property="fixed" value="false">
															<bean:message key="prompt.variable" />
														</logic:equal>
													</td>
												</tr>
											</logic:notEmpty>
										</logic:iterate>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						<br>
						<table width="98%" border="0" cellspacing="1" cellpadding="2">
						<tr>
									<td class=detailHead colspan=2>
									<logic:equal name="courtPolicyForm" property="inUse" value="true">
										<bean:message key="prompt.inactivateReason" />
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
										<bean:message key="prompt.deleteNotes" />
									</logic:notEqual>
									
									</td>
								</tr>
								<tr>
									<td class=formDeLabel valign=top width=1% nowrap>
										<bean:message key="prompt.4.diamond"/>
										<bean:message key="prompt.notes" />
									</td>
									<td class=formDe>
										<html:textarea name="courtPolicyForm" property="deleteNote" style="width:100%" rows="5"/>
									</td>
								</tr>
						</table>
						<br>
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
						
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  <tr>
						    <td align="center">
								<input type="button" value="Back" name="return" onClick="history.go(-1)">
								<html:submit property="submitAction" onclick="return (validateCourtPolicyDelete(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
								<input type="button" value='<bean:message key="button.cancel" />' name="return" onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);">
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
