<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/17/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 03/03/2006  Clarence Shimek ER#28542 add disable button to finish button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency --> 
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around Update Association button -->
<!-- 12/01/2006  Hien Rodriguez  defect#37050 change delete to deleted in comfirm message-->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
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
<title><bean:message key="title.heading" /> - courtPolicySummary.jsp</title>
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
<html:form action="/submitCourtPolicyCreate" target="content">
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
										<!-- Summary header & help section -->
										<logic:equal name="courtPolicyForm" property="pageType" value="summary">
											<logic:equal name="courtPolicyForm" property="action" value="copy">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|57">
												<bean:message key="prompt.copy" />
												<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:equal>
									
											<logic:equal name="courtPolicyForm" property="action" value="create">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|33">
												<bean:message key="prompt.create" />
												<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:equal>
											
											<logic:equal name="courtPolicyForm" property="action" value="update">
												<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|48">
													<bean:message key="prompt.update" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
												</logic:notEqual>
												<logic:equal name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|114">
													<bean:message key="prompt.update" />
													<bean:message key="prompt.inUse" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
												</logic:equal>
											</logic:equal>
											
											<logic:equal name="courtPolicyForm" property="action" value="delete">
												<logic:equal name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|59">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|81">
													<bean:message key="prompt.inactivate" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
												</logic:equal>
												<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|95">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|78">
													<bean:message key="prompt.delete" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
												</logic:notEqual>
											</logic:equal>
											
											<logic:equal name="courtPolicyForm" property="action" value="asscConditions">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|36">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|84">
												<bean:message key="prompt.associate" />
												<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.summary" />
											</logic:equal>	
									
										</logic:equal>
										<!-- Summary header & help section end -->
									
										<!-- Confirm header & help section -->
										<logic:equal name="courtPolicyForm" property="pageType" value="confirm">
											<logic:equal name="courtPolicyForm" property="action" value="copy">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|58">
												<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|77">
												<bean:message key="prompt.copy" />
												<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
											</logic:equal>
									
											<logic:equal name="courtPolicyForm" property="action" value="create">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|34">
												<bean:message key="prompt.create" />
												<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
											</logic:equal>
											
											<logic:equal name="courtPolicyForm" property="action" value="update">
												<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|50">
													<bean:message key="prompt.update" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
												</logic:notEqual>
												<logic:equal name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|51">
													<bean:message key="prompt.update" />
													<bean:message key="prompt.inUse" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
												</logic:equal>
											</logic:equal>
											
											<logic:equal name="courtPolicyForm" property="action" value="delete">
												<logic:equal name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|94">
													<bean:message key="prompt.inactivate" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
												</logic:equal>
												<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|60">
													<bean:message key="prompt.delete" />
													<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />
												</logic:notEqual>
											</logic:equal>
											
											<logic:equal name="courtPolicyForm" property="action" value="asscConditions">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|37">
												<bean:message key="prompt.associate" />
												<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="title.confirmation" />												
											</logic:equal>	
											
										</logic:equal>
										<!-- Confirm header & help section end -->
									</td>
								</tr>
								
								<logic:present name="<%=Action.ERROR_KEY%>"> 
								<tr>
									<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
								</tr>
								</logic:present>
								
								<logic:equal name="courtPolicyForm" property="pageType" value="confirm">
									<logic:notPresent name="<%=Action.ERROR_KEY%>">
									<tr>
										<td align="center" class="confirm">
											<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;<bean:message key="prompt.successfully" /> 
											
											<logic:equal name="courtPolicyForm" property="action" value="create">
												<bean:message key="prompt.created" />
											 </logic:equal>
											 <logic:equal name="courtPolicyForm" property="action" value="copy">
												<bean:message key="prompt.copied" />
											 </logic:equal>
											 <logic:equal name="courtPolicyForm" property="action" value="update">
												<bean:message key="prompt.updated" />
											 </logic:equal>
											 <logic:equal name="courtPolicyForm" property="action" value="delete">
												<logic:equal name="courtPolicyForm" property="inUse" value="true">
													<bean:message key="prompt.inactivated" />
												</logic:equal>
												<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
													<bean:message key="prompt.deleted" />
												</logic:notEqual>
											 </logic:equal>
											 <logic:equal name="courtPolicyForm"
														property="action" value="asscConditions">
													<bean:message key="prompt.updated" />
													</logic:equal>
										</td>
									</tr>
									</logic:notPresent>
								</logic:equal>
								
							 
							</table>
							<!-- END HEADING TABLE -->
							
							<logic:equal name="courtPolicyForm" property="pageType" value="summary">
								<!-- BEGIN INSTRUCTION TABLE -->
								<table width="98%" border="0">
									<tr>
									<td><ul>
										<li>Review fields. Click Finish.</li>
										</ul></td>
								</tr>
								</table>
							</logic:equal>
							
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
												<td width=1%><a href="javascript:showHideMulti('setDetails', 'sd', '<bean:write name="varElementRESize"/>', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="setDetails"></a></td>
												<td class=detailHead>&nbsp;<bean:message key="prompt.setDetailsForSelectedCourts" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_4.gif" vspace=0></td>
											</tr>
										</table>
									</td>
							</tr>
						  
						  <logic:iterate indexId="varElementRECount" id="varElementREIter" name="courtPolicyForm" property="variableElementResponseEvents">						  
								<logic:equal name="varElementREIter" property="reference" value="false">
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
								</logic:equal>
						  </logic:iterate>
						</table>
						<br>
						</logic:notEmpty>
						
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
						
						  <tr>
								<td colspan=2 class=detailHead>
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td class=detailHead><bean:message key="prompt.exceptions" /> - <bean:message key="prompt.setDetails" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_5.gif" vspace=0></td>
										</tr>
									</table>
								</td>
						  </tr>
						  
							<logic:iterate id="exceptionCourtVarElemBeansIter" name="courtPolicyForm" property="exceptionCourtVarElemBeans">
								<tr>
									<td class=boldText colspan=2><bean:write name="exceptionCourtVarElemBeansIter" property="courtNumber"/></td>
								</tr>
								<logic:iterate id="variableElementsIter" name="exceptionCourtVarElemBeansIter" property="variableElements">
									<logic:equal name="variableElementsIter" property="reference" value="false">
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
									</logic:equal>
								</logic:iterate>
							</logic:iterate>
						</table>
						<br>
						
						<!-- Delete note for delete action only -->
						<logic:equal name="courtPolicyForm" property="action" value="delete">
							<logic:equal name="courtPolicyForm" property="inUse" value="true">
							<table width="98%" border="0" cellspacing=1 cellpadding=4>
								<tr>
									<td class="detailHead" colspan="2">
									
										<bean:message key="prompt.inactivateReason" />
									
									
									
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap="nowrap" valign="top" width="1%">
										
										<bean:message key="prompt.notes" />
									</td>
									<td class="formDe"><bean:write name="courtPolicyForm" property="deleteNote"/></td>
								</tr>
								<br>
							</table>
							</logic:equal>
						</logic:equal>	
						<%-- 	<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
										<bean:message key="prompt.deleteNotes" />
									</logic:notEqual> --%>
							
						
						
						
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
														
								<logic:equal name="courtPolicyForm" property="pageType" value="summary">
									<input type="button" value="Back" name="return" onClick="history.go(-1)">
									<logic:equal name="courtPolicyForm" property="action" value="delete">		
										<input type="button" value="<bean:message key='button.finish'/>" property="submitAction" onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>submitCourtPolicyDelete.do', false) && disableSubmit(this, this.form);">	
									</logic:equal>
									<logic:equal name="courtPolicyForm" property="action" value="create">		
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
									</logic:equal>
									<logic:equal name="courtPolicyForm" property="action" value="update">		
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
									</logic:equal>
									<logic:equal name="courtPolicyForm" property="action" value="copy">		
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
									</logic:equal>
									<logic:equal name="courtPolicyForm" property="action" value="asscConditions">		
										<input type="button" value="<bean:message key='button.finish'/>" property="submitAction" onclick="return (changeFormActionURL(this.form.name, '/<msp:webapp/>submitCourtPolicyAssociateToCondition.do', false) && disableSubmit(this, this.form));">	
									</logic:equal>

									<logic:equal name="courtPolicyForm" property="action" value="create"> 
										<input type=button value='<bean:message key="button.cancel" />' onclick="changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicyCreate.do', true);">
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="action" value="create"> 
										<input type=button value='<bean:message key="button.cancel" />' onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);">
									</logic:notEqual>
								</logic:equal>

								<logic:equal name="courtPolicyForm" property="pageType" value="confirm">
								
									<input type=button value='<bean:message key="button.backToSearch" />' onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);">
								<logic:notEqual name="courtPolicyForm" property="action" value="delete"> 
								<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
									<logic:notEqual name="courtPolicyForm" property="action" value="asscConditions">
										<jims2:isAllowed requiredFeatures="CS-CPOL-UPDATE">	
											<input type="button" value="<bean:message key='button.assocToCondition'/>" onclick="changeFormActionURL(this.form.name, '/<msp:webapp/>handleCourtPolicySelection.do?submitAction=Update Associations', false) && disableSubmit(this, this.form);" >	
										</jims2:isAllowed>
									</logic:notEqual>
								</logic:notEqual>
								</logic:notEqual>
								</logic:equal>
									
								
								
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
