<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/04/2008	 Aswin Widjaja - Create JSP -->
<!-- 07/23/2010  CShimek       - defect #66465 changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js so narrative formatting  perserved -->
<!-- 07/19/2011  RCapestani	   - defect #70646 CSCD- Calendar-Spell Check does not display on Group Office Visit Results page -->
<!-- 04/12/2012  TSVines       - 72079 added the class=buttonBar" to the <tr> -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
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
<title><bean:message key="title.heading" /> - csCalendar - groupOfficeVisitEnterResults.jsp</title>

		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<!-- This does not work because the Narrative field has tiny mce logic which triggers a javascript error when receiving a cursor from the previous field.  daw -->
<%--html:javascript formName="officeVisitGenericEnterResults"/--%>

        <script type="text/javascript">
                
		     
            function validateField(theForm){
			    clearAllValArrays();
			    trimCasenote("currentOfficeVisit.narrative");
	            customValRequired("currentOfficeVisit.outcomeCd", "<bean:message key='errors.required' arg0='Outcome'/>","");
			    customValRequired("currentOfficeVisit.narrative", "<bean:message key='errors.required' arg0='Narrative'/>","");
			    addDefinedTinyMCEFieldMask("currentOfficeVisit.narrative","Narrative Text cannot have % or _ entries","");
			    customValMinLength("currentOfficeVisit.narrative","<bean:message key='errors.minlength' arg0='Narrative' arg1='1'/>","1");
			    customValMaxLength("currentOfficeVisit.narrative","<bean:message key='errors.maxlength' arg0='Narrative' arg1='3500'/>","7000");
		      
			    if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(document.getElementsByName("currentOfficeVisit.narrative")[0].value, 'Narrative is required')){
					return true;
				}else {
					return false;
				}
		 }
	    </script>
		
	</head>
	<body topmargin=0 leftmargin="0">
		<html:form action="/displayCSGVSummary" target="content">
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
										<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
											<tiles:put name="tab" value="caseloadTab"/>
										</tiles:insert>	
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
											<bean:message key="title.CSCD" />&nbsp;-&nbsp;
												<logic:equal name="csCalendarOVForm" property="activityFlow" value="updateResults">
													Update
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|21">
												</logic:equal>
												Group Office Visit Results
												<logic:equal name="csCalendarOVForm" property="activityFlow" value="enterResults">
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|13">
												</logic:equal>    
											</td>
										</tr>
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Enter required fields. Click Next.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required" colspan="2"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									
									<tiles:insert page="superviseeListTile.jsp" flush="true">
										<tiles:put name="superviseeList" beanName="csCalendarOVForm" beanProperty="superviseeList" />
									</tiles:insert>
									<div class=spacer4px></div>
									
									<!-- BEGIN DETAIL TABLE -->
									<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Group Office Visit Information</td>
										</tr>
										<tr>
											<td colspan=2>
												<table width='100%' cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" nowrap width=15%>
															Office Visit Date</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventDate" formatKey="date.format.mmddyyyy"/></td>
														<td class="formDeLabel" nowrap width=15%>Event Name</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.eventName" /></td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap>Start Time</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.startTime"/> <logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.startTime"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.AMPMId1"/></logic:notEmpty></td>
														<td class="formDeLabel" nowrap>End Time</td>
														<td class="formDe"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.endTime"/> <logic:notEmpty name="csCalendarOVForm" property="currentOfficeVisit.endTime"><bean:write name="csCalendarOVForm" property="currentOfficeVisit.AMPMId2"/></logic:notEmpty></td>
													</tr>
													<tr>
														<td class="formDeLabel" colspan=4>Purpose of Group Office Visit</td>
													</tr>
													
													<tr class=formDe>
														<td colspan=4>
															<bean:write name="csCalendarOVForm" property="currentOfficeVisit.purpose" filter="false"/>&nbsp;
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.3.diamond"/>Outcome</td>
														<td class=formDe colspan=3>
															<html:select name="csCalendarOVForm" property="currentOfficeVisit.outcomeCd">
																<option>Please Select</option>
																<html:optionsCollection name="csCalendarOVForm" property="filteredOVOutcomeList" value="supervisionCode" label="description"/>
															</html:select>
															
														</td>
													</tr>
													
													<tr>
														<td class="formDeLabel" colspan=4><bean:message key="prompt.3.diamond"/>Narrative</td>
													</tr>
													<style>
														.mceEditor{
														width: "100%";
														height: "150"
														}
													</style>
													<tr class=formDe>
														<td colspan=4 align=center>
															<html:textarea styleClass="mceEditor" style="width:100%" rows="12" property="currentOfficeVisit.narrative"/>
															<bean:define id="userAgency" name="csCalendarOVForm" property="userAgency"/>
															<tiles:insert page="../../common/spellCheckButtonTile.jsp" flush="false">
															     <tiles:put name="agencyCode"><%=userAgency%></tiles:put>
															</tiles:insert>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!-- BEGIN DETAIL TABLE -->
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr class="buttonBar">
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateField(this.form))" ><bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table><br>
					</td>
				</tr>
			</table><br></td>
		</tr>
	</table>
</td>
</tr>
</table>
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
