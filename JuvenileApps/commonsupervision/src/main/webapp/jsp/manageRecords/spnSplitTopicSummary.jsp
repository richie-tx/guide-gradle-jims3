<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/03/2008	 C Shimek   - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
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
<title><bean:message key="title.heading" /> - manageRecords/spnSplitTopicSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSpnSplitTopic" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/spn/spn.htm#|0">
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
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
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
									<bean:message key="prompt.summary" />
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
												Review selections and click Finish. Click on the Assessment to view details
											</logic:equal>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSOCIATE%>">
												Review selections and click Finish. Click on the Name to view details. 
											</logic:equal>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.LOS%>">
												Review selections and click Finish. Click on the LOS to view details.
											</logic:equal>
											<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.SUPERVISION_PLAN%>">
												Review selections and click Finish. Click on the Last Change Date to view details.  
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
							<logic:notEmpty name="spnSplitForm" property="selectedAssessments">
								<bean:define id="showFinish" value="Y"/>
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
													<td>
														<bean:message key="prompt.assessmentDate" />
														<jims2:sortResults beanName="spnSplitForm" results="selectedAssessments" primaryPropSort="assessmentDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" levelDeep="2" hideMe="true" />
													</td>
													<td><bean:message key="prompt.assessment" /></td>
												</tr>
												 <nested:iterate property="selectedAssessments" >
													<tr>
														<td><nested:write property="assessmentDate" formatKey="date.format.mmddyyyy"/></td>
														<td>
															<a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.ASSESSMENT%>&assessmentId=<nested:write property="specificTypeAssessmentId"/>&assessSupervisionPrd=Active&assessmentType=<nested:write property="assessmentTypeId"/>&masterAssessmentId=<nested:write property="masterAssessmentId"/>&SPN=<bean:write name="eSPN"/>')"><nested:write property="assessmentTypeName"/></a>
														</td>
													</tr>
												</nested:iterate>
											</table>
										</td>
									</tr>
								</table>
							</logic:notEmpty>	
						</logic:equal>
<!-- END ASSESSMENT LIST TABLE -->
<!-- BEGIN ASSOCIATE LIST TABLE -->
						<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.ASSOCIATE%>">
							<logic:notEmpty name="spnSplitForm" property="selectedAssociates">
								<bean:define id="showFinish" value="Y"/>
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
													<td>
														<bean:message key="prompt.name" />
														<jims2:sortResults beanName="spnSplitForm" results="selectedAssociates" primaryPropSort="assocFormattedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" levelDeep="2" hideMe="true"/>
													</td>
													<td><bean:message key="prompt.relationship" /></td>
													<td><bean:message key="prompt.homePhone" /></td>
													<td><bean:message key="prompt.cellPhone" /></td>
													<td><bean:message key="prompt.workPhone" /></td>
												</tr>
												 <nested:iterate property="selectedAssociates">
													<tr>
														<td><a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.ASSOCIATE%>&SPN=<nested:write property="associateId"/>')"><nested:write property="assocFormattedName"/></a></td>
														<td><nested:write property="relationship"/></td>
														<td><nested:write property="homePhone.formattedPhoneNumber"/></td>
														<td><nested:write property="cellPhone.formattedPhoneNumber"/></td>
														<td><nested:write property="workPhone.formattedPhoneNumber"/></td>
													</tr>
												</nested:iterate>
											</table>
										</td>
									</tr>
								</table>
							</logic:notEmpty>	
						</logic:equal>
<!-- END ASSOCIATE LIST TABLE  -->
<!-- BEGIN SUPERVISION PLAN LIST TABLE -->
						<logic:equal name="spnSplitForm" property="selectedTopic" value="<%=UIConstants.SUPERVISION_PLAN%>">
							<logic:notEmpty name="spnSplitForm" property="selectedSupervisionPlans">
								<bean:define id="showFinish" value="Y"/>
								<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td>													
											<bean:message key="prompt.supervision" /> <bean:message key="prompt.plans" /> for <bean:message key="prompt.erroneousSPN" /> 
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="formDeLabel">
													<td>
														<bean:message key="prompt.last" /> <bean:message key="prompt.changeDate" />
														<jims2:sortResults beanName="spnSplitForm" results="selectedSupervisionPlans" primaryPropSort="lastChangeDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="3" levelDeep="2" hideMe="true"/>
													</td>
													<td><bean:message key="prompt.status" /></td>
												</tr>
												 <nested:iterate property="selectedSupervisionPlans">
													<tr>
														<td><a href="javascript:openWindow('/<msp:webapp/>handleSpnSplitTopicSelectionDetails.do?submitAction=View&Type=<%=UIConstants.SUPERVISION_PLAN%>&selectedSupervisionPlanId=<nested:write property="supervisionPlanId"/>')"><nested:write property="lastChangeDate" formatKey="date.format.mmddyyyy"/></a></td>
														<td><nested:write property="statusLit"/></td>
													</tr>
												</nested:iterate>
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
									<logic:present name="showFinish">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
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
