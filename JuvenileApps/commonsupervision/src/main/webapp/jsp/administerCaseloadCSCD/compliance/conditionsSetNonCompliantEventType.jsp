<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/04/2007	 Debbie Williamson - Create JSP -->
<!-- 06/19/2008  C Shimek          - 51908 revised time from 24 to 12 hour clock with AM/PM drop down -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised Case# to use displayCaseNum for case number dispaly -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/conditionsSetNonCompliantEventType.jsp</title>

<!-- JavaScripts -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerCompliance/conditionsSetNonCompliantEventType.js"></script>
</head>  
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="pageLoad()">
<html:form action="/displaySetToNonCompliantCasenotes" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|5">
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|5"--%>
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>   
		<td valign="top"><!--tabs start-->
			<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
				<tiles:put name="tabid" value="conplianceTab"/>
			 </tiles:insert>
<!--tabs end-->
		</td>
	</tr>
	<tr>
		<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
	<tr>
		<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFO TABLE  -->
			<tiles:insert page="../../common/superviseeInfoForComplianceHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFO TABLE -->	
		</td>
	</tr>
	<tr>
		<td valign="top" align="center">						
<!-- BEGIN SUPERVISEE TABS TABLE -->
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>						
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
		   				 	<tiles:put name="tab" value="ComplianceTab"/> 
			     		</tiles:insert>	 
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END SUPERVISEE TABS TABLE -->
		</td>
	</tr>
	<tr>
		<td valign="top" align="center">
<!-- BEGIN GREEN BORDER TABLE -->					
			<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>		
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
									<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;-
									<bean:message key="prompt.setConditionsToNoncompliant" />&nbsp;-&nbsp;<bean:message key="prompt.event(s)" />
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
										<li>Enter required fields, click Next</li>
									</ul>
								</td>
							</tr>
			                <tr>
								<td class="required"><bean:message key="prompt.3.diamond" />Required Fields&nbsp;*All date fields must be in the format of mm/dd/yyyy.</td>
							</tr>
						</table>
<!-- END INSTRUCTION TABLE -->
						<%	int recordCtr = 0; %> 	
       					<SCRIPT LANGUAGE="JavaScript" ID="js1">
								var cal1 = new CalendarPopup();
								cal1.showYearNavigation();
						</SCRIPT>
<!-- BEGIN CONTENT TABLE -->
						<logic:empty name="complianceForm" property="selectedLikeConditionsEvents">
							<logic:empty name="complianceForm" property="selectedUniqueConditionsEvents">
								<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td >&nbsp;</td>
									</tr>
									<tr>
										<td >No condition events for this person.</td>
									</tr>
								</table>	
							</logic:empty>	
						</logic:empty>
						<input type="hidden" name="sprvnBeginDate" value="<bean:write name="complianceForm" property="supervisionPeriodBeginDateAsString"/>" >											
<!-- BEGIN LIKE CONDITIONS DISPLAY TABLE -->						
		 				<logic:iterate id="selectedLikeConditionsEvents" name="complianceForm" property="selectedLikeConditionsEvents">
       						<table width="98%" border="0" cellspacing="1" cellpadding="2">                  
								<tr class="detailHead">
									<td colspan="4">
										<table width="100%" cellpadding="1" cellspacing="2">
		        			                <tr>
               									<td nowrap="nowrap">
													<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='selectedLikeConditionsEvents' property='sprOrderConditionId'/>')" title=""><bean:write name='selectedLikeConditionsEvents' property='orderConditionName' /></a>															
	                       						</td>
												<td align="right">
													<span class="boldText"><bean:message key="prompt.case#" />:
										 				<logic:iterate id="caseNumbers" name="selectedLikeConditionsEvents" property="createNonCompliantEventEvent" >
															&nbsp;<bean:write name="caseNumbers" property="displayCaseNum" />
														</logic:iterate> 
													</span>
												</td>
		        			                </tr>
           								</table>
	                   				</td>
       			   				</tr>
      			   				<tr>	
           							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.occurrenceDate" /></td>
	                   				<td class="formDe">
										<html:text name="selectedLikeConditionsEvents" property="occurrenceDate" maxlength="10" size="10" indexed="true"/> 
											<a href="#" onClick="cal1.select(document.forms[0]['selectedLikeConditionsEvents[' +  <%out.print(recordCtr);%> + '].occurrenceDate'],'anchor<%out.print(recordCtr);%>','MM/dd/yyyy'); return false;" )
													NAME="anchor<%out.print(recordCtr);%>" ID="anchor<%out.print(recordCtr);%>"><bean:message key="prompt.3.calendar"/></a> 
               						</td>
	                  				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.occurrenceTime" /></td>
	                   				<td class="formDe">
	               						<html:text name="selectedLikeConditionsEvents" property="occurrenceTime" maxlength="5" size="5" indexed="true" />	               						
										<html:select name="selectedLikeConditionsEvents" property="AMPMId" indexed="true">										    										    
											<html:option value="AM">AM</html:option>
											<html:option value="PM">PM</html:option>
										</html:select>										 
				                	</td>
				               	</tr>   
					            <tr>
					            	<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.eventType" /></td>
					            	<td class="formDe" colspan="3">
					               		<html:select name="selectedLikeConditionsEvents" property="eventTypeCodeIds" indexed="true" size="3" multiple="true" onchange="eventTypeCheck(this, 'newLikeEventType')">
											<html:optionsCollection name="selectedLikeConditionsEvents" property="eventTypeList" value="nonComplianceEventTypeCodeId" label="nonComplianceEventTypeCodeDesc" /> 
											<%-- 		<html:option value="NEW">NEW EVENT TYPE</html:option> --%>
										</html:select>
									</td>
								</tr>  
								<tr id="newLikeEventType<%out.print(recordCtr);%>" class="hidden">
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.new" /> <bean:message key="prompt.eventType" /></td>
									<td class="formDe" colspan="3">
										<html:text name="selectedLikeConditionsEvents" property="newEventType" maxlength="50" size="50" indexed="true"/> 
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.details" /></td>
									<td class="formDe" colspan="3">
										<html:text name="selectedLikeConditionsEvents" property="details" maxlength="51" size="50" indexed="true"  onkeydown="alertTextAreaToLarge( this, 50, 'Details' )"/> 
									</td>
								</tr> 
								<tr>
									<td colspan="4"><img src="/<msp:webapp/>images/spacer.gif"></td>
								</tr>
								<% recordCtr++; %>
							</table>	
						</logic:iterate>
<!-- END LIKE CONDITIONS DISPLAY TABLE -->	
						<%	recordCtr = 0; %> 							
<!-- BEGIN UNIQUE CONDITIONS DISPLAY TABLE -->
						<logic:iterate id="selectedUniqueConditionsEvents" name="complianceForm" property="selectedUniqueConditionsEvents">
       						<table width="98%" border="0" cellspacing="1" cellpadding="2">                  
								<tr class="detailHead">
									<td colspan="4">
										<table width="100%" cellpadding="1" cellspacing="2">
		        			                <tr>
               									<td>
													<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='selectedUniqueConditionsEvents' property='sprOrderConditionId'/>')" title=""><bean:write name='selectedUniqueConditionsEvents' property='orderConditionName' /></a>                        							
	                       						</td>
												<td align="right"><span class="boldText"><bean:message key="prompt.case#" />:&nbsp;<bean:write name="selectedUniqueConditionsEvents" property="displayCaseNum" /></span></td>
		        			                </tr>
           								</table>
	                   				</td>
       			   				</tr>
       			   				<tr>	
           							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.occurrenceDate" /></td>
	                   				<td class="formDe">
										<html:text name="selectedUniqueConditionsEvents" property="occurrenceDate" maxlength="10" size="10" indexed="true" /> 
											<a href="#" onClick="cal1.select(document.forms[0]['selectedUniqueConditionsEvents[' +  <%out.print(recordCtr);%> + '].occurrenceDate'],'anchor<%out.print(recordCtr);%>','MM/dd/yyyy'); return false;" )
													NAME="anchor<%out.print(recordCtr);%>" ID="anchor<%out.print(recordCtr);%>"><bean:message key="prompt.3.calendar"/></a> 
               						</td>
	                  				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.occurrenceTime" /></td>
	                   				<td class="formDe">
	               						<html:text name="selectedUniqueConditionsEvents" property="occurrenceTime" maxlength="5" size="5" indexed="true" />	               						
	               						<html:select name="selectedUniqueConditionsEvents" property="AMPMId" indexed="true">	               						    	               						    
											<html:option value="AM">AM</html:option>
											<html:option value="PM">PM</html:option>
										</html:select>										  
				                	</td>
				               	</tr>   
					            <tr>
					            	<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.eventType" /></td>
					            	<td class="formDe" colspan="3">
					               		<html:select name="selectedUniqueConditionsEvents" property="eventTypeCodeIds" indexed="true" size="3" multiple="true" onchange="eventTypeCheck(this, 'newEventType')">
											<html:optionsCollection name="selectedUniqueConditionsEvents" property="eventTypeList" value="nonComplianceEventTypeCodeId" label="nonComplianceEventTypeCodeDesc" /> 
										</html:select>
									</td>
								</tr>  
								<tr id="newEventType<%out.print(recordCtr);%>" class="hidden">
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" /><bean:message key="prompt.new" /> <bean:message key="prompt.eventType" /></td>
									<td class="formDe" colspan="3">
										<html:text name="selectedUniqueConditionsEvents" property="newEventType" maxlength="50" size="50" indexed="true"/> 
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.details" /></td>
									<td class="formDe" colspan="3">
										<html:text name="selectedUniqueConditionsEvents" property="details" maxlength="50" size="50" indexed="true"/> 
									</td>
								</tr> 
								<tr>
									<td colspan="4"><img src="/<msp:webapp/>images/spacer.gif"></td>
								</tr>
								<% recordCtr++; %>
							</table>	
						</logic:iterate>
			<%-- 			<script>
							var elem2=document.getElementById('occurrenceTime');
							elem2.value = getCurrentTime12Hour();
							var ap = convertTimeto2DigitHr(getCurrentTime(false));
							var hm = ap.split(":");
							if (hm[0] > 11){
								var elem3 = document.getElementById("AMPMId");
								elem3.selectedIndex = 1;
							}
						</script>  --%>
<!-- END UNIQUE CONDITIONS DISPLAY TABLE -->
<!-- END CONTENT TABLE-->
<!-- BEGIN BUTTON TABLE -->                
						<table border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return checkRequired(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next" /></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>	
<!-- END GREEN BORDER TABLE -->					
		</td>
	</tr>
	<tr>
		<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr> 
</table>
</td>
</tr>
</table>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>