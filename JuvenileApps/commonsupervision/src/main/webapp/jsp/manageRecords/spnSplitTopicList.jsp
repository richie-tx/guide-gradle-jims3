<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/09/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
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
<title><bean:message key="title.heading" /> - manageRecords/spnSplitTopicList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript">
function checkAll(el, checkboxName)
{
	var setValue = el.checked;

// set the check value for all check boxes
	var cb = document.getElementsByName(checkboxName);
	if (cb.length > 0){
		for(var x = 0; x < cb.length; x++){
			cb[x].checked = setValue;
		}
	}	
}

function uncheckCheckAll(el, checkAllName)
	{
		var theForm = el.form;
		if (!el.checked){
			theForm.elements[checkAllName].checked = false;
		}
	}

function checkSelect()
{
	var cb = document.getElementsByTagName("input");
	var flowTypes = document.getElementsByName("flowType");
	var flowType = flowTypes[0].value 

	if (flowType.indexOf("supervision") > -1){
		flowType = "supervision plan";
	}
	if (cb.length > 0){
		for(var x = 0; x < cb.length; x++){
			if (cb[x].type == "checkbox" && cb[x].checked == true) {
				return true;
			}		
		}
	}
	alert("At least 1 " + flowType + " must be selected");
	return false;
}
</script> 

<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySpnSplitTopicSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|0">
<input type="hidden" name="flowType" value="<bean:write name='spnSplitForm' property='selectedTopic'/>" >
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
								<td align="center" class="header"><bean:message key="prompt.spnSplit" /> -
									<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSESSMENT%>">
										<bean:message key="prompt.assessments" />
									</logic:equal>
									<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSOCIATE%>">
										<bean:message key="prompt.associate" />s
									</logic:equal>
									<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.LOS%>">
										<bean:message key="prompt.LOS" />
									</logic:equal>
									<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.SUPERVISION_PLAN%>">
										<bean:message key="prompt.supervision" /> <bean:message key="prompt.plans" />
									</logic:equal>
									<bean:message key="prompt.list" />
								</td>
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
										<li>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSESSMENT%>">
												Select assessments to move from Erroneous SPN to Valid SPN. Click on the assessment to view details
											</logic:equal>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSOCIATE%>">
												Select associates to move from Erroneous SPN to Valid SPN. Click on the name to view details. 
											</logic:equal>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.LOS%>">
												Select LOS to move from Erroneous SPN to Valid SPN. Click on the los to view details.
											</logic:equal>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.SUPERVISION_PLAN%>">
												Select supervision Plans to move from Erroneous SPN to Valid SPN. Click on the Last Change Date to view details. 
											</logic:equal>
										</li>
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
						<bean:define id="eSPN" name="spnSplitForm" property="erroneousSpn.spn"></bean:define>
<!-- BEGIN ASSESSMENT LIST TABLE -->
						<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSESSMENT%>">
							<logic:notEmpty name="spnSplitForm" property="currentAssessments">
								<bean:define id="showNext" value="Y"/>
								<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td>													
											<bean:message key="prompt.assessments" /> for <bean:message key="prompt.erroneousSPN" /> 
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="formDeLabel">
													<td width="1%"><input type="checkbox" name="checkForSplitAllAssessment" onclick="checkAll(this, 'selectedAssessmentIds')"></td>
													<td>
														<bean:message key="prompt.assessmentDate" />
														<jims2:sortResults beanName="spnSplitForm" results="currentAssessments" primaryPropSort="assessmentDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" levelDeep="2"/>
													</td>
													<td><bean:message key="prompt.assessment" /></td>
												</tr>
												<logic:iterate id="cat" name="spnSplitForm" property="currentAssessments">
													<tr>
														<td><input type="checkbox" name="selectedAssessmentIds" value="<bean:write name="cat" property="assessmentId"/>" onclick="uncheckCheckAll(this,'checkForSplitAllAssessment')" ></td>
														<td><bean:write name="cat" property="assessmentDate" formatKey="date.format.mmddyyyy" /></td>
														<td>
															<a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.ASSESSMENT%>&assessmentId=<bean:write name="cat" property="specificTypeAssessmentId"/>&assessSupervisionPrd=Active&assessmentType=<bean:write name="cat" property="assessmentTypeId"/>&masterAssessmentId=<bean:write name="cat" property="masterAssessmentId"/>&SPN=<bean:write name="eSPN"/>')"><bean:write name="cat" property="assessmentTypeName"/></a>
														</td>
													</tr>
												</logic:iterate>
											</table>
										</td>
									</tr>
								</table>
							</logic:notEmpty>
						</logic:equal>
<!-- END ASSESSMENT LIST TABLE -->
<!-- BEGIN ASSOCIATE LIST TABLE -->
						<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSOCIATE%>">
							<logic:notEmpty name="spnSplitForm" property="currentAssociates">
								<bean:define id="showNext" value="Y"/>						
								<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td>	
											<bean:message key="prompt.associate" />s for <bean:message key="prompt.erroneousSPN" />												
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="formDeLabel">
													<td width="1%">
														<input type="checkbox" name="checkForSplitAllAssociates" onclick="checkAll(this, 'selectedAssociateIds')">
													</td>
													<td>
														<bean:message key="prompt.name" />
														<jims2:sortResults beanName="spnSplitForm" results="currentAssociates" primaryPropSort="assocFormattedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" levelDeep="2"/>
													</td>
													<td><bean:message key="prompt.relationship" /></td>
													<td><bean:message key="prompt.homePhone" /></td>
													<td><bean:message key="prompt.cellPhone" /></td>
													<td><bean:message key="prompt.workPhone" /></td>
												</tr>
												<logic:iterate id="cas" name="spnSplitForm" property="currentAssociates">
													<tr>
														<td>
															<input type="checkbox" name="selectedAssociateIds" value="<bean:write name="cas" property="associateId"/>" onclick="uncheckCheckAll(this,'checkForSplitAllAssociates')" >
														</td>
														<td> <a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.ASSOCIATE%>&SPN=<bean:write name="cas" property="associateId" />')"><bean:write name="cas" property="assocFormattedName" /></a></td>
														<td><bean:write name="cas" property="relationship"/></td>
														<td><bean:write name="cas" property="homePhone.formattedPhoneNumber" /></td>
														<td><bean:write name="cas" property="cellPhone.formattedPhoneNumber"/></td>
														<td><bean:write name="cas" property="workPhone.formattedPhoneNumber"/></td>
													</tr>
												</logic:iterate>
											</table>
										</td>
									</tr>
								</table>
							</logic:notEmpty>	
						</logic:equal>
<!-- END ASSOCIATE LIST TABLE  -->
<!-- BEGIN LOS LIST TABLE -->  <!-- This section on hold -->
<%-- 					<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.LOS%>">
						</logic:equal>  --%>
<!-- END LOS LIST TABLE -->
<!-- BEGIN SUPERVISION PLAN LIST TABLE -->
						<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.SUPERVISION_PLAN%>">
							<logic:notEmpty name="spnSplitForm" property="currentSupervisionPlans">
								<bean:define id="showNext" value="Y"/>						
								<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td>													
											<bean:message key="prompt.supervision" /> <bean:message key="prompt.plans" /> for <bean:message key="prompt.erroneousSPN" /> 
										</td>
									</tr>
							<%-- 		<tr>
										<td>
									      <a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.SUPERVISION_PLAN%>&selectedSupervisionPlanId=111&SPN=4375')">11/01/2008</a> should get failure msg
										</td>
									</tr>
									<tr>
										<td>
											<a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.SUPERVISION_PLAN%>&selectedSupervisionPlanId=101')">11/01/2008</a>  should return data
										</td>
									</tr>  --%>
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="formDeLabel">
													<td width="1%">
														<input type="checkbox" name="checkForSplitAllSupervisionPlans" onclick="checkAll(this, 'selectedSupervisionPlanIds')">
													</td>
													<td>
														<bean:message key="prompt.last" /> <bean:message key="prompt.changeDate" />
														<jims2:sortResults beanName="spnSplitForm" results="currentSupervisionPlans" primaryPropSort="lastChangeDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="3" levelDeep="2" />
													</td>	
													<td><bean:message key="prompt.status" /></td>
												</tr>
												<logic:iterate id="csp" name="spnSplitForm" property="currentSupervisionPlans">
													<tr>
														<td>
															<input type="checkbox" name="selectedSupervisionPlanIds" value="<bean:write name="csp" property="supervisionPlanId"/>" onclick="uncheckCheckAll(this,'checkForSplitAllSupervisionPlans')" >
														</td>
														<td>
															<a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.SUPERVISION_PLAN%>&selectedSupervisionPlanId=<bean:write name="csp" property="supervisionPlanId"/>&SPN=<bean:write name="spnSplitForm" property="erroneousSpn.spn" /> ')"><bean:write name="csp" property="lastChangeDate"  formatKey="date.format.mmddyyyy"/></a>
														</td>	
														<td><bean:write name="csp" property="statusLit"/></td>
													</tr>
												</logic:iterate>
											</table>
										</td>
									</tr>
								</table>
							</logic:notEmpty>	
						</logic:equal>
<!-- END SUPERVISION PLAN LIST TABLE -->
						<br>
<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>											
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
									<logic:present name="showNext">
										<html:submit property="submitAction" onclick="return checkSelect(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
									 	<html:reset><bean:message key="button.reset"/></html:reset>&nbsp;	
						 			</logic:present>  								
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