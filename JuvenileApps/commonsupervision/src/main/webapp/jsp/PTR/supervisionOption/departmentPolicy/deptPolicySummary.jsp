<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/29/2005	 mjt - Create JSP -->
<!-- 03/03/2006  Clarence Shimek ER#28542 add disable button to finish and delete buttons -->
<!-- 04/03/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around buttons -->
<!-- 09/12/2006  Hien Rodriguez - defect#34977 change button label of delete option -->
<!-- 01/23/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - deptPolicyCreateSummary.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<bean:define id="departmentGroup1Caption" name="departmentPolicyForm" property="departmentGroup1Caption" type="java.lang.String"/>
<bean:define id="departmentGroup2Caption" name="departmentPolicyForm" property="departmentGroup2Caption" type="java.lang.String"/>
<bean:define id="departmentGroup3Caption" name="departmentPolicyForm" property="departmentGroup3Caption" type="java.lang.String"/>
<bean:define id="departmentPolicyLiteralCaption" name="departmentPolicyForm" property="departmentPolicyLiteralCaption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="departmentPolicyForm" property="conditionGroup2Caption" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" name="departmentPolicyForm" property="conditionGroup3Caption" type="java.lang.String"/>
<bean:define id="deptPolicyTitle" name="departmentPolicyForm" property="deptPolicyTitle" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitDepartmentPolicy" target="content">
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
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
									<!-- Summary header & help section -->
									<logic:equal name="departmentPolicyForm" property="pageType" value="summary">
										<logic:equal name="departmentPolicyForm" property="action" value="asscConditions">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|110">
											<bean:message key="prompt.associate" />
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="copy">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|102">
											<bean:message key="prompt.copy" />
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|92">
											<bean:message key="prompt.create" />
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="update">
											<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|98">
													<bean:message key="prompt.update" />
													<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:notEqual>
											<logic:equal name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|116">
												<bean:message key="prompt.update" />
												<bean:message key="prompt.inUse" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:equal>
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="delete">
											<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|104">
												<bean:message key="prompt.delete" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:notEqual>
											<logic:equal name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|107">
												<bean:message key="prompt.inactivate" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:equal>
										</logic:equal>

									</logic:equal>
									<!-- Summary header & help section end -->
									
									<!-- Confirmation header & help section -->
									<logic:equal name="departmentPolicyForm" property="pageType" value="confirm">
										<logic:equal name="departmentPolicyForm" property="action" value="asscConditions">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|111">
											<bean:message key="prompt.associate" />
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="copy">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|103">
											<bean:message key="prompt.copy" />
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|93">
											<bean:message key="prompt.create" />
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="update">
											<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|99">
												<bean:message key="prompt.update" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
											</logic:notEqual>
											<logic:equal name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|117">
												<bean:message key="prompt.update" />
												<bean:message key="prompt.inUse" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
											</logic:equal>
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="delete">
											<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|105">
												<bean:message key="prompt.delete" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
											</logic:notEqual>
											<logic:equal name="departmentPolicyForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|108">
												<bean:message key="prompt.inactivate" />
												<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
											</logic:equal>
										</logic:equal>
									</logic:equal>
									<!-- Confirmation header & help section end -->
								</td>
							</tr>
						  
							<logic:present name="<%=Action.ERROR_KEY%>"> 
								<tr>
									<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
								</tr>
							</logic:present>
					
							<logic:equal name="departmentPolicyForm" property="pageType" value="confirm">
								<logic:notPresent name="<%=Action.ERROR_KEY%>">
									<tr>
										<td align="center" class="confirm">
											<bean:message key="<%=deptPolicyTitle%>"/>&nbsp;<bean:message key="prompt.successfully" />  
											<logic:equal name="departmentPolicyForm" property="action" value="create">
												<bean:message key="prompt.created" />
											 </logic:equal>
											 <logic:equal name="departmentPolicyForm" property="action" value="asscConditions">
										<bean:message key="prompt.updated" />
									</logic:equal>
											 <logic:equal name="departmentPolicyForm" property="action" value="copy">
												<bean:message key="prompt.copied" />
											 </logic:equal>
											 <logic:equal name="departmentPolicyForm" property="action" value="update">
												<bean:message key="prompt.updated" />
											 </logic:equal>
											 <logic:equal name="departmentPolicyForm" property="action" value="delete">
											 <logic:equal name="departmentPolicyForm" property="inUse" value="true">
												<bean:message key="prompt.inactivated" />
											</logic:equal>
											<logic:notEqual name="departmentPolicyForm" property="inUse" value="true">
												<bean:message key="prompt.deleted" />
											</logic:notEqual>
												
											 </logic:equal>
										</td>
									</tr>
								</logic:notPresent>
							</logic:equal>
				
						</table>
						<!-- END HEADING TABLE -->
					
						<!-- BEGIN INSTRUCTION TABLE -->
						 <logic:equal name="departmentPolicyForm" property="pageType" value="summary">
						<table width="98%" border="0">
							<tr>
								<td><ul><li>Review fields. Select Finish.</li></ul></td>
							</tr>
						</table>
						</logic:equal>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing=0 class="borderTableBlue">
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
											<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=departmentGroup1Caption%>"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="group1Name" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=departmentGroup2Caption%>"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="group2Name" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=departmentGroup3Caption%>"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="group3Name" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap valign="top"><bean:message key="<%=departmentPolicyLiteralCaption%>" /></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="departmentPolicy"  filter="false"/> </td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.effectiveDate"/></td>
											<td class="formDe"><bean:write name="departmentPolicyForm" property="effectiveDate" /></td>
										</tr>

										<logic:equal name="departmentPolicyForm" property="action" value="update">
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
												<td class="formDe"><bean:write name="departmentPolicyForm" property="inactiveDate" /></td>
											</tr>
										</logic:equal>

										<tr>
											<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.notes"/></td>
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
							
						<logic:equal name="departmentPolicyForm" property="action" value="delete">
						<logic:equal name="departmentPolicyForm" property="inUse" value="true">
							<!--delete reason start-->			
							<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class="detailHead">
									<td><bean:message key="prompt.inactivateReason" /></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.notes" /></td>
	
												<td class="formDe">
													<bean:write name="departmentPolicyForm" property="deleteNote"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!--delete reason end-->
							<br>
							</logic:equal>
						</logic:equal>
			
						
						<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
												<tiles:put name="associatedConditions" beanName="departmentPolicyForm" beanProperty="associatedConditions"/>
												<tiles:put name="conditionGroup2Caption" beanName="departmentPolicyForm" beanProperty="conditionGroup2Caption"/>
												<tiles:put name="conditionGroup3Caption" beanName="departmentPolicyForm" beanProperty="conditionGroup3Caption"/>
												
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
											<tiles:put name="group2Caption" beanName="departmentPolicyForm" beanProperty="conditionGroup2Caption"/>
											<tiles:put name="group3Caption" beanName="departmentPolicyForm" beanProperty="conditionGroup3Caption"/>
										</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
						</logic:notEmpty>

						<!-- BEGIN BUTTON TABLE -->
						<br>
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<logic:equal name="departmentPolicyForm" property="pageType" value="summary">
										<input type="button" value="Back" name="return" onClick="history.go(-1)">
										<logic:equal name="departmentPolicyForm" property="action" value="delete">
											<jims2:isAllowed requiredFeatures="CS-DPOL-DELETE">		
												<input type="button" value="<bean:message key='button.finish'/>" property="submitAction" onclick="return  changeFormActionURL(this.form.name, '/<msp:webapp/>submitDepartmentPolicyDelete.do', false) && disableSubmit(this, this.form);">	
											</jims2:isAllowed>
										</logic:equal>
										<logic:equal name="departmentPolicyForm" property="action" value="create">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
										</logic:equal>
										<logic:equal name="departmentPolicyForm" property="action" value="update">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
										</logic:equal>
										<logic:equal name="departmentPolicyForm" property="action" value="copy">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
										</logic:equal>
										<logic:equal name="departmentPolicyForm" property="action" value="asscConditions">		
											<input type="button" value="<bean:message key='button.finish'/>" property="submitAction" onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>submitDepartmentPolicyAssociateToCondition.do', false) && disableSubmit(this, this.form);">	
										</logic:equal>
										
										<logic:equal name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											<input type=button value='<bean:message key="button.cancel" />' onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>displayDepartmentPolicyCreate.do', false) && disableSubmit(this, this.form)">
										</logic:equal>
										<logic:notEqual name="departmentPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											<input type=button value='<bean:message key="button.cancel" />' onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>displayDepartmentPolicySearch.do', false) && disableSubmit(this, this.form)">
										</logic:notEqual>
									</logic:equal>
	
									<logic:equal name="departmentPolicyForm" property="pageType" value="confirm"> 
										
										 <input type="button" value="<bean:message key='button.backToSearch'/>" onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>displayDepartmentPolicySearch.do', false) && disableSubmit(this, this.form)" >  
										
										<logic:notEqual name="departmentPolicyForm" property="action" value="delete">
											<logic:notEqual name="departmentPolicyForm" property="action" value="asscConditions">
												<jims2:isAllowed requiredFeatures="CS-DPOL-UPDATE">	
													<input type="button" value="<bean:message key='button.assocToCondition'/>" onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>handleDepartmentPolicySelection.do?submitAction=Update Associations', false) && disableSubmit(this, this.form);" >
												</jims2:isAllowed>
											</logic:notEqual>
										</logic:notEqual>
									</logic:equal>
								</td>
							</tr>
						</table>
		      			<!-- END BUTTON TABLE -->
                	</td>
              	</tr>
            </table><br>
		</td>
	</tr>
</table>
<!-- END  TABLE -->

</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
