<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 03/03/2006  C Shimek  	   - ER#28542 add disable button function to buttons -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around Update Associations buttons --> 
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>


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
<title><bean:message key="title.heading" /> - conditionView.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="conditionGroup1Caption" name="supervisionConditionForm" property="conditionGroup1Caption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="supervisionConditionForm" property="conditionGroup2Caption" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" name="supervisionConditionForm" property="conditionGroup3Caption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSupervisionConditionSelection" target="content">
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tabid" value="manageFeaturesTab"/>
						</tiles:insert>	
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
							    <td align="center" class="header">
							    	<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|4">
								    	<bean:message key="title.view" />
								    	<bean:message key="title.supervisionCondition" />
									</logic:notEqual>
							    	<logic:equal name="supervisionConditionForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|5">
								    	<bean:message key="title.view" />
										<bean:message key="prompt.inUse" />
										<bean:message key="title.supervisionCondition" />
									</logic:equal>
									</td>
							  </tr>
							</table>
							<br>
							<!-- END HEADING TABLE -->
							<table width="98%" border="0" cellspacing=1 cellpadding=4>
								<tr>
									<td colspan=2 class="detailHead">
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td class="detailHead"><bean:message key="title.supervisionCondition" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width=1% nowrap class="formDeLabel"><bean:message key="prompt.name" /></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionName"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" width=1% nowrap><bean:message key="<%=conditionGroup1Caption%>"/></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="group1Name"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" width=1% nowrap><bean:message key="<%=conditionGroup2Caption%>"/></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="group2Name"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" width=1% nowrap><bean:message key="<%=conditionGroup3Caption%>"/></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="group3Name"/></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.standard" /></td>
									<td class="formDe"><logic:equal name="supervisionConditionForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="supervisionConditionForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
								</tr>
								<tr>
									<td class="formDeLabel" valign="top" width=1% nowrap><bean:message key="prompt.literal" /></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionLiteral"  filter="false"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" valign="top" width=1% nowrap><bean:message key="prompt.spanishLiteral" /></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionSpanishLiteral" filter="false"/></td>
								</tr>
								<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.supervisionType" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="dispSelSupTypes"/></td>
									</tr>
								</logic:equal>
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.documents" /></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="selDocuments"/></td>
								</tr>
								<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.severityLevel" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="severityLevel"/></td>
									</tr>
									
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.jurisdiction" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="jurisdiction"/></td>
									</tr>
								</logic:equal>	
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.effectiveDate" /></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="effectiveDate"/></td>
								</tr>
	
								<logic:equal name="supervisionConditionForm" property="action" value="update">
									<tr>
										<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="inactiveDate" /></td>
									</tr>
								</logic:equal>
								<logic:notEqual name="supervisionConditionForm" property="action" value="update">
									<logic:notEmpty name="supervisionConditionForm" property="inactiveDate" >
									<logic:notEqual name="supervisionConditionForm" property="inactiveDate" value="">
									<tr>
										<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="inactiveDate" /></td>
									</tr>
									</logic:notEqual>
									</logic:notEmpty>
								</logic:notEqual>
																													
								<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.eventType" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="selectedEventTypes"/></td>
									</tr>
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.eventCount" /></td>
										<td class="formDe"><bean:write name="supervisionConditionForm" property="eventCountDesc"/></td>
									</tr>
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.period" /></td>
										<td class="formDe">
														<logic:notEmpty name="supervisionConditionForm" property="periodValue">
														<logic:notEqual name="supervisionConditionForm" property="periodValue" value="0">
														<bean:write name="supervisionConditionForm" property="periodValue"/> <bean:write name="supervisionConditionForm" property="period"/>
														</logic:notEqual>
														</logic:notEmpty>
													</td></tr>
								</logic:equal>	
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.notes" /></td>
									<td class="formDe"><bean:write name="supervisionConditionForm" property="notes"/></td>
								</tr>
							</table>
								<br>
								<!--task info start--> <tiles:insert
												page="../../../common/taskListTile.jsp" flush="true">
												<tiles:put name="taskConfig"
													beanName="supervisionConditionForm" beanProperty="tasks" />
											</tiles:insert> <!--task info end--> <br>
								
								
								
								<!--task info end-->
							
							
							<table width="98%" border="0" cellpadding=4 cellspacing=0 class=borderTableBlue>
								<tr>
									<td class="detailHead">
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td width=1%><a href="javascript:showHide('courts','row', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="courts"></a></td>
												<td class="detailHead">&nbsp;<bean:message key="prompt.selectedCourts" /></td>
												<td align=right><img
																		src="/<msp:webapp/>images/step_3.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="courtsSpan" class="visibleTR">
									<td>
										<tiles:insert page="../../../common/courts.jsp" flush="true">
											<tiles:put name="beanName" beanName="supervisionConditionForm" />
											<tiles:put name="mode" value="view" />
											<tiles:put name="displayDDNA" value="true" />
										</tiles:insert>
									</td>
								</tr>
							</table>
						<br>
						
						<logic:notEmpty name="supervisionConditionForm" property="variableElementResponseEvents">
						<bean:size id="varElementRESize" name="supervisionConditionForm" property="variableElementResponseEvents"/>
						
							
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
									<td colspan=2 class="detailHead">
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td class="detailHead"><bean:message key="prompt.setDetailsForSelectedCourts" /></td>
												<td align=right><img
																		src="/<msp:webapp/>images/step_4.gif"></td>
											</tr>
										</table>
									</td>
							</tr>
						  
						  <logic:iterate indexId="varElementRECount" id="varElementREIter" name="supervisionConditionForm" property="variableElementResponseEvents">						  
								<logic:equal name="varElementREIter" property="reference" value="false">
									<logic:notEmpty name="varElementREIter" property="name">
										<tr id="sd<bean:write name='varElementRECount'/>" class="visibleTR">
											<td class="formDeLabel" nowrap width=1%><bean:write name="varElementREIter" property="name"/></td>
											<td class="formDe">
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
								</logic:equal>
						  </logic:iterate>
						</table>
						<br>
						</logic:notEmpty>
						
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
						<%--<tr><td><br></td></tr>--%>
						  <tr>
								<td colspan=2 class="detailHead">
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td class="detailHead"><bean:message key="prompt.exceptions" /> - <bean:message key="prompt.setDetails" /></td>
											<td align=right><img
																		src="/<msp:webapp/>images/step_5.gif"></td>
										</tr>
									</table>
								</td>
						  </tr>
						  
							<logic:iterate id="exceptionCourtVarElemBeansIter" name="supervisionConditionForm" property="exceptionCourtVarElemBeans">
								<tr>
									<td class=boldText colspan=2><bean:write name="exceptionCourtVarElemBeansIter" property="courtNumber"/></td>
								</tr>
								<logic:iterate id="variableElementsIter" name="exceptionCourtVarElemBeansIter" property="variableElements">
									<logic:equal name="variableElementsIter" property="reference" value="false">
										<logic:notEmpty name="variableElementsIter" property="name">
											<tr>
												<td class="formDeLabel" nowrap width=1%><bean:write name="variableElementsIter" property="name"/></td>
												<td class="formDe">
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
									</logic:equal>
								</logic:iterate>
							</logic:iterate>
						</table>
						<br>
						
						<!-- Associations -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2">
							<tr>
								<td>
									<!-- Associations -->
										<tiles:insert page="../../../common/conditionAssociationsView.jsp" flush="true">
											<tiles:put name="associatedCourtPolicies" beanName="supervisionConditionForm" beanProperty="associatedCourtPolicies"/>
											<tiles:put name="associatedDepartmentPolicies" beanName="supervisionConditionForm" beanProperty="associatedDeptPolicies"/>
										</tiles:insert>
								</td>
							</tr>
						</table>
						<br>
						
						<!-- Associations -->
						<logic:notEmpty name="supervisionConditionForm" property="removedAssociations">
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/removedAssociationsView.jsp" flush="true">
												<tiles:put name="removedAssociations" beanName="supervisionConditionForm" beanProperty="removedAssociations"/>
												<tiles:put name="associationType" value="condition"/>
												<tiles:put name="group2Caption" beanName="supervisionConditionForm" beanProperty="courtGroup2Caption"/>
												<tiles:put name="group3Caption" beanName="supervisionConditionForm" beanProperty="courtGroup3Caption"/>
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
								
								<logic:equal name="supervisionConditionForm" property="viewOnly" value="false"> 
									<jims2:isAllowed requiredFeatures="CS-COND-COPY">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.copy"/></html:submit>
									</jims2:isAllowed>
									<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>
									</jims2:isAllowed>
									<jims2:isAllowed requiredFeatures="CS-COND-DELETE">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"/></html:submit>
									</jims2:isAllowed>
									<input type="button" value='<bean:message key="button.cancel" />' name="return" onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionSearch.do', false) && disableSubmit(this, this.form)">
								</logic:equal>						
						    </td>
						  </tr>
						  <logic:equal name="supervisionConditionForm" property="viewOnly" value="false"> 
						  <tr>
					  		<td align="center">
					  			<jims2:isAllowed requiredFeatures="CS-COND-UPDATE">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateCourtPolicyAssociations"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateDeptPolicyAssociations"/></html:submit>
					  			</jims2:isAllowed>
					  		</td>
					  	</tr>
					  	</logic:equal>
						</table>
						<!-- END BUTTON TABLE -->
						<br>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
<!-- END  TABLE -->
</div>

<br>
<!-- BEGIN NOTES TABLE -->
<%--<table width="100%">
	<tr>
		<td align="left" class="subhead"><bean:message key="prompt.notes" /></td>
	</tr>
	<tr>
		<td>
			<ol>
				<li>If "Fixed" is selected in the Set Details area, the input field corresponding to it is required</li>
				<li>Example of exception flow where "Details Do Not Apply" - is in Court Policies <a href="courtPolicySetDetails.htm" target="_new">here</a></li>
			</ol>
		</td>
	</tr>
</table> --%>
<!-- END NOTES TABLE -->
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>