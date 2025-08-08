<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/29/2005	 mjt - Create JSP -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to netx button -->
<!-- 04/03/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/23/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - deptPolicyCreateName.jsp</title>

<html:javascript formName="deptPolicyCreateName" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="departmentGroup1Caption" value="prompt.group1" type="java.lang.String"/>
<bean:define id="departmentGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="departmentGroup3Caption" value="prompt.group3"  type="java.lang.String"/>
<bean:define id="departmentPolicyLiteralCaption" value="prompt.policyTableTitle" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" value="prompt.group2" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" value="prompt.group3" type="java.lang.String"/>
<bean:define id="deptPolicyTitle" value="prompt.policyTableTitle" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayDepartmentPolicySummary" target="content" focus="name">
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
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>

			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align=center>
						

						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
							<tr>
								<td align="center" class="header">
									<logic:equal name="departmentPolicyForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|101">
										<bean:message key="prompt.copy" />
									</logic:equal>
									
									<logic:equal name="departmentPolicyForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|91">
										<bean:message key="prompt.create" />
									</logic:equal>
									
									<logic:equal name="departmentPolicyForm" property="action" value="update">
										<bean:message key="prompt.update" />
										<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|96">
										</logic:notEqual>
										<logic:equal name="departmentPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|97">
											<bean:message key="prompt.inUse" />
										</logic:equal>
									</logic:equal>
									
									<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="prompt.policyName" />
								</td>
							</tr>
						</table>											  
						<!-- END HEADING TABLE -->
						<!-- BEGIN Error TABLE -->
						<table width=98% align=center>							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
			
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td><ul><li>Change Name and/or select Next</li></ul></td>
							</tr>
						</table>

						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
							<tr>
								<td class="detailHead"><bean:message key="<%=deptPolicyTitle%>"/></td>
							</tr>
							<tr>
								<td>
									<table width=100% cellpadding=4 cellspacing=1>
										<tr>
											<td class=formDeLabel><bean:message key="prompt.4.diamond"/><bean:message key="prompt.name" /></td>
											<td class=formDe>
												<logic:equal name="departmentPolicyForm" property="action" value="<%=UIConstants.UPDATE%>">
													<html:text name="departmentPolicyForm" property="name" size="50" maxlength="50"/>
												</logic:equal>	
												<logic:equal name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>">
													<html:text name="departmentPolicyForm" property="name" size="50" maxlength="50"/>
													
												</logic:equal>
												<logic:equal name="departmentPolicyForm" property="action" value="<%=UIConstants.COPY%>">
													<html:text name="departmentPolicyForm" property="name" size="50" maxlength="50"/>
													
												</logic:equal>
												<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>">
												<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.UPDATE%>">
												<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.COPY%>"> 
												<bean:write name="departmentPolicyForm" property="name"/>
												</logic:notEqual>
												</logic:notEqual>
												</logic:notEqual>
											</td>
										</tr>     										
										<tr>
											<td class=formDeLabel width="1%" nowrap><bean:message key="<%=departmentGroup1Caption%>"/></td>
											<td class=formDe><bean:write name="departmentPolicyForm" property="group1Name" /></td>
										</tr>
										<tr>
											<td class=formDeLabel width="1%" nowrap><bean:message key="<%=departmentGroup2Caption%>"/></td>
											<td class=formDe><bean:write name="departmentPolicyForm" property="group2Name" /></td>
										</tr>
										<tr>
											<td class=formDeLabel width="1%" nowrap><bean:message key="<%=departmentGroup3Caption%>"/></td>
											<td class=formDe><bean:write name="departmentPolicyForm" property="group3Name" /></td>
										</tr>
										<tr>
											<td class=formDeLabel width="1%" nowrap valign="top"><bean:message key="<%=departmentPolicyLiteralCaption%>" /></td>
											<td class=formDe><bean:write name="departmentPolicyForm" property="departmentPolicy"  filter="false"/> </td>
										</tr>
										<tr>
											<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.effectiveDate"/></td>
											<td class=formDe><bean:write name="departmentPolicyForm" property="effectiveDate" /></td>
										</tr>

										<logic:equal name="departmentPolicyForm" property="action" value="update">
											<tr>
												<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
												<td class=formDe><bean:write name="departmentPolicyForm" property="inactiveDate" /></td>
											</tr>
										</logic:equal>

										<tr>
											<td class=formDeLabel valign="top" width="1%" nowrap><bean:message key="prompt.notes"/></td>
											<td class=formDe><bean:write name="departmentPolicyForm" property="notes" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br>
													
						<table width="98%" border="0" cellpadding=4 cellspacing=0 class=borderTableBlue>
							<tr>
								<td class="detailHead">
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td width="1%"><a href="javascript:showHide('courts','row', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="courts"></a></td>
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
									
						<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
												<tiles:put name="associatedConditions" beanName="departmentPolicyForm" beanProperty="associatedConditions"/>
												<tiles:put name="conditionGroup2Caption"  value="prompt.group2"/>
												<tiles:put name="conditionGroup3Caption" value="prompt.group3"/>
											</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
						</logic:notEqual>
						
						<logic:notEmpty name="departmentPolicyForm" property="removedAssociations">
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/removedAssociationsView.jsp" flush="true">
												<tiles:put name="removedAssociations" beanName="departmentPolicyForm" beanProperty="removedAssociations"/>
												<tiles:put name="associationType" value="department"/>
												<tiles:put name="group2Caption" value="prompt.group2"/>
												<tiles:put name="group3Caption" value="prompt.group3"/>
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
								<html:submit property="submitAction" onclick="return validateDeptPolicyCreateName(this.form) && disableSubmit(this, this.form);">
								  <bean:message key="button.next"></bean:message> 
								</html:submit>&nbsp;      
								<html:reset />&nbsp;								 
								<logic:equal name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>">
								   <input type="button" onclick="goNav('/<msp:webapp/>displayDepartmentPolicyCreate.do');" value="<bean:message key='button.cancel' />">									    							
								</logic:equal>
								<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
								   <input type="button" onclick="goNav('/<msp:webapp/>displayDepartmentPolicySearch.do');" value="<bean:message key='button.cancel' />">
								</logic:notEqual>								
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
